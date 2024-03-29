package com.example.springbatch_12_1_asyncitemprocessor_asyncitemwriter;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.integration.async.AsyncItemProcessor;
import org.springframework.batch.integration.async.AsyncItemWriter;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * packageName    : com.example.springbatch_12_1_asyncitemprocessor_asyncitemwriter
 * fileName       : AsyncConfiguration
 * author         : namhyeop
 * date           : 2022/08/19
 * description    :
 * 비동기식 Step을 학습하고 동기식 Step과 비동기식 Step의 성능차이를 진행해보자, 진행 프로세스는 customer 데이터를 customer2로 옮기는것이다.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/19        namhyeop       최초 생성
 */

@RequiredArgsConstructor
@Configuration
public class AsyncConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;

    @Bean
    public Job job() throws Exception{
        return jobBuilderFactory.get("batchJob")
                .incrementer(new RunIdIncrementer())
                //(1)동기식 테스트
                .start(step1())
                //(2)비동기식 테스트
//                .start(asyncStep1())
                .listener(new StopWatchJobListener())
                .build();
    }

    //(1).동기식 Step
    @Bean
    public Step step1() throws Exception {
        return stepBuilderFactory.get("step1")
                .chunk(100)
                .reader(pagingItemReader())
                //동기식 프로세서
                .processor(customItemProcessor())
                .writer(customItemWriter())
                .build();
    }

//    (2).비동기식 Step
    @Bean
    public Step asyncStep1() throws Exception {
        return stepBuilderFactory.get("asyncStep1")
                .<Customer,Customer>chunk(100)
                .reader(pagingItemReader())
                .processor(asyncItemProcessor())
                .writer(asyncItemWriter())
                .build();
    }

    @Bean
    public ItemProcessor customItemProcessor() throws InterruptedException {
        return new ItemProcessor<Customer, Customer>() {
            @Override
            public Customer process(Customer item) throws Exception {
                //동기와 비동기 성능 수치 확인을 위한 sleep
                Thread.sleep(10);
                return new Customer(item.getId(),
                         item.getFirstName().toUpperCase(),
                         item.getLastName().toUpperCase(),
                         item .getBirthdate());
            }
        };
    }

//    AsyncItemProcessor나 AsyncItemWriter 전부 자기 스스로가 Processor를 지정하는게 아니라 위임하여 실행시킨다. 이렇게 하면 멀티 쓰레드로 실행이 가능하다.
    @Bean
    public AsyncItemProcessor asyncItemProcessor() throws Exception {
        //비동기 환경을 제공하는 class
        AsyncItemProcessor<Customer, Customer> asyncItemProcessor = new AsyncItemProcessor<>();
        asyncItemProcessor.setDelegate(customItemProcessor());
        asyncItemProcessor.setTaskExecutor(new SimpleAsyncTaskExecutor());
        return asyncItemProcessor;
    }

    //AsyncItemProcessor나 AsyncItemWriter 전부 자기 스스로가 Processor를 지정하는게 아니라 위임하여 실행시킨다. 이렇게 하면 멀티 쓰레드로 실행이 가능하다.
    @Bean
    public AsyncItemWriter asyncItemWriter() {
        AsyncItemWriter<Customer> asyncItemWriter = new AsyncItemWriter<>();
        asyncItemWriter.setDelegate(customItemWriter());

        return asyncItemWriter;
    }

    @Bean
    public JdbcPagingItemReader<Customer> pagingItemReader() {
        JdbcPagingItemReader<Customer> reader = new JdbcPagingItemReader<>();

        reader.setDataSource(this.dataSource);
        reader.setPageSize(100);
        reader.setRowMapper(new CustomerRowMapper());

        MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
        queryProvider.setSelectClause("id, firstName, lastName, birthdate");
        queryProvider.setFromClause("from customer");

        Map<String, Order> sortKeys = new HashMap<>(1);

        sortKeys.put("id", Order.ASCENDING);

        queryProvider.setSortKeys(sortKeys);

        reader.setQueryProvider(queryProvider);

        return reader;
    }

    @Bean
    public JdbcBatchItemWriter customItemWriter(){
        JdbcBatchItemWriter<Customer> itemWriter = new JdbcBatchItemWriter<>();

        itemWriter.setDataSource(this.dataSource);
        itemWriter.setSql("insert into customer2 values (:id, :firstName, :lastName, :birthdate)");
        itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        return itemWriter;
    }
}
