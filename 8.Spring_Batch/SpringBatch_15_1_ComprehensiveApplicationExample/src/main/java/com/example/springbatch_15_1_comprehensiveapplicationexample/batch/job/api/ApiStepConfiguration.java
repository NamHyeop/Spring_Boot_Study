package com.example.springbatch_15_1_comprehensiveapplicationexample.batch.job.api;

import com.example.springbatch_15_1_comprehensiveapplicationexample.batch.chunk.processor.ApiItemProcessor1;
import com.example.springbatch_15_1_comprehensiveapplicationexample.batch.chunk.processor.ApiItemProcessor2;
import com.example.springbatch_15_1_comprehensiveapplicationexample.batch.chunk.processor.ApiItemProcessor3;
import com.example.springbatch_15_1_comprehensiveapplicationexample.batch.chunk.writer.ApiItemWriter1;
import com.example.springbatch_15_1_comprehensiveapplicationexample.batch.chunk.writer.ApiItemWriter2;
import com.example.springbatch_15_1_comprehensiveapplicationexample.batch.chunk.writer.ApiItemWriter3;
import com.example.springbatch_15_1_comprehensiveapplicationexample.batch.classifier.ProcessorClassifier;
import com.example.springbatch_15_1_comprehensiveapplicationexample.batch.classifier.WriterClassifier;
import com.example.springbatch_15_1_comprehensiveapplicationexample.batch.domain.ApiRequestVO;
import com.example.springbatch_15_1_comprehensiveapplicationexample.batch.domain.ProductVO;
import com.example.springbatch_15_1_comprehensiveapplicationexample.batch.partition.ProductPartitioner;
import com.example.springbatch_15_1_comprehensiveapplicationexample.service.ApiService1;
import com.example.springbatch_15_1_comprehensiveapplicationexample.service.ApiService2;
import com.example.springbatch_15_1_comprehensiveapplicationexample.service.ApiService3;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.batch.item.support.ClassifierCompositeItemProcessor;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * packageName    : com.example.springbatch_15_1_comprehensiveapplicationexample.batch.job.api
 * fileName       : ApiStepConfiguration
 * author         : namhyeop
 * date           : 2022/08/29
 * description    :
 * ApiJobConfiguration에서 JobStep으로 구현하였다. 이곳에서 JobStep 관련된 Step 설정을 구현한다.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/29        namhyeop       최초 생성
 */
@Configuration
@RequiredArgsConstructor
public class ApiStepConfiguration {

    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;
    private final ApiService1 apiService1;
    private final ApiService2 apiService2;
    private final ApiService3 apiService3;

    private int chunksize = 10;

    @SneakyThrows
    @Bean
    public Step apiMasterStep() throws Exception {
        return stepBuilderFactory.get("apiMasterStep")
                .partitioner(apiSlaveStep().getName(), partitioner())
                .step(apiSlaveStep())
                .gridSize(3)
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(3);
        taskExecutor.setMaxPoolSize(6);
        taskExecutor.setThreadNamePrefix("api-thread-");

        return taskExecutor;
    }

    @Bean
    public Step apiSlaveStep() throws Exception {
        //이 영역의 JDBC로만 해결
        return stepBuilderFactory.get("apiSlaveStep")
                .<ProductVO, ProductVO>chunk(chunksize)
                .reader(itemReader(null))
                //각 스레드 3개가 멀티 스레드로 동작 중이게 한다, 각 스레드는 type 번호에 맞게 write 작업을 진행하고 type 번호에 맞는 API Server와 통신하도록 한다.
                .processor(itemProcessor())
                .writer(itemWriter())
                .build();
    }

    //Thread 생성
    @Bean
    public ProductPartitioner partitioner(){
        ProductPartitioner productPartitioner = new ProductPartitioner();
        productPartitioner.setDataSource(dataSource);
        return productPartitioner;
    }

    @Bean
    @StepScope
    public ItemReader<ProductVO> itemReader(@Value("#{stepExecutionContext['product']}") ProductVO productVO) throws Exception {

        JdbcPagingItemReader<ProductVO> reader = new JdbcPagingItemReader<>();

        reader.setDataSource(dataSource);
        reader.setPageSize(chunksize);
        reader.setRowMapper(new BeanPropertyRowMapper<>(ProductVO.class));

        MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
        queryProvider.setSelectClause("id, name, price, type");
        queryProvider.setFromClause("from product");
        queryProvider.setWhereClause("where type = :type");

        Map<String, Order> sortKeys = new HashMap<>(1);
        sortKeys.put("id", Order.DESCENDING);
        queryProvider.setSortKeys(sortKeys);

        //여기서 설정한 Parameter 값이 84번 줄의 type에 매핑된다.
        reader.setParameterValues(QueryGenerator.getParameterForQuery("type", productVO.getType()));
        reader.setQueryProvider(queryProvider);
        reader.afterPropertiesSet();

        return reader;
    }

    //==============================itemProcessor, itemWriter에서는 Thread마다 type의 정보에 의해 Thread 별로할당한다. ==============================
    @Bean
    public ItemProcessor itemProcessor(){
        ClassifierCompositeItemProcessor<ProductVO, ApiRequestVO> processor = new ClassifierCompositeItemProcessor<>();
        //ProcessorClassifier는 Key는 Classifer, value는 반환할 T이다. 객체에 맞게 프로세서슬 반환해주려고 하는것이 목적이다.
        ProcessorClassifier<ProductVO, ItemProcessor<?, ? extends ApiRequestVO>> classifier = new ProcessorClassifier();
        Map<String, ItemProcessor<ProductVO, ApiRequestVO>> processorMap = new HashMap<>();
        processorMap.put("1", new ApiItemProcessor1());
        processorMap.put("2", new ApiItemProcessor2());
        processorMap.put("3", new ApiItemProcessor3());
        
        classifier.setProcessorMap(processorMap);
        
        processor.setClassifier(classifier);

        return processor;
    }

    @Bean
    public ItemWriter itemWriter(){
        ClassifierCompositeItemWriter<ApiRequestVO> writer = new ClassifierCompositeItemWriter<>();
        //ProcessorClassifier는 Key는 Classifer, value는 반환할 T이다. 객체에 맞게 프로세서슬 반환해주려고 하는것이 목적이다.
        WriterClassifier<ApiRequestVO, ItemWriter<? super ApiRequestVO>> classifier = new WriterClassifier<>();
        Map<String, ItemWriter<ApiRequestVO>> writerMap = new HashMap<>();
        writerMap.put("1", new ApiItemWriter1(apiService1));
        writerMap.put("2", new ApiItemWriter2(apiService2));
        writerMap.put("3", new ApiItemWriter3(apiService3));

        classifier.setWriterMap(writerMap);

        writer.setClassifier(classifier);

        return writer;
    }
}
