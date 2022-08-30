package com.example.springbatch_15_1_comprehensiveapplicationexample.batch.job.file;

import com.example.springbatch_15_1_comprehensiveapplicationexample.batch.chunk.processor.FileItemProcessor;
import com.example.springbatch_15_1_comprehensiveapplicationexample.batch.domain.Product;
import com.example.springbatch_15_1_comprehensiveapplicationexample.batch.domain.ProductVO;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import javax.persistence.EntityManagerFactory;

/**
 * packageName    : com.example.springbatch_15_1_comprehensiveapplicationexample.batch.job.file
 * fileName       : FileJobConfiguration
 * author         : namhyeop
 * date           : 2022/08/29
 * description    :
 * File을 읽어서 DB에 저장하는 fileJob Configuration 클래스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/29        namhyeop       최초 생성
 */
@Configuration
@RequiredArgsConstructor
public class FileJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;

    @Bean
    public Job fileJob(){
        return jobBuilderFactory.get("fileJob")
                .incrementer(new RunIdIncrementer())
                .start(fileStep())
                .build();
    }

    @Bean
    public Step fileStep(){
        return stepBuilderFactory.get("fileStep")
                //읽을 때는 Domain 객체로 쓸때는 Product 타입으로
                .<ProductVO, Product>chunk(10)
                .reader(fileItemReader(null))
                .processor(fileItemProcessor())
                .writer(fileItemWriter())
                .build();
    }

    @Bean
    @StepScope //@Value 사용을 위한 StepScope 어노테이션 사요, lazebiding이 가능함
    public FlatFileItemReader<ProductVO> fileItemReader(@Value("#{jobParameters['requestDate']}") String requestDate){
        return new FlatFileItemReaderBuilder<ProductVO>()
                .name("flatFile")
                //아래 방식의 ClassPathResource를 사용하면 동작안함, 이유를 모르겠음
//                .resource(new ClassPathResource("product_" + requestDate + ".csv"))
                .resource(new FileSystemResource("/Users/namhyeop/Desktop/git자료/Spring_Boot_Study/8.Spring_Batch/SpringBatch_15_1_ComprehensiveApplicationExample/src/main/resources/product_" + requestDate + ".csv"))
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>())
                .targetType(ProductVO.class)
                .linesToSkip(1)
                .delimited().delimiter(",")
                 .names("id", "name", "price", "type")
                .build();
    }

    @Bean
    public ItemProcessor<ProductVO, Product> fileItemProcessor(){
        return new FileItemProcessor();
    }

    @Bean
    public ItemWriter<Product> fileItemWriter(){
        return new JpaItemWriterBuilder<Product>()
                .entityManagerFactory(entityManagerFactory)
                .usePersist(true)
                .build();
    }
}
