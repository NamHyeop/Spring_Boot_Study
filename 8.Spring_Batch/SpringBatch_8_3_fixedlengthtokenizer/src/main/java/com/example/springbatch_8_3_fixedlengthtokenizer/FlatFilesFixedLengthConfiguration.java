package com.example.springbatch_8_3_fixedlengthtokenizer;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.util.List;

/**
 * packageName    : com.example.springbatch_8_3_fixedlengthtokenizer
 * fileName       : FlatFilesFixedLengthConfiguration
 * author         : namhyeop
 * date           : 2022/08/11
 * description    :
 * Exception 설정을 꺼봄으로써 Exeption 예외처리의 존재를 확인하는 예제
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/11        namhyeop       최초 생성
 */
@RequiredArgsConstructor
@Configuration
public class FlatFilesFixedLengthConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job(){
        return jobBuilderFactory.get("batchJob")
                .start(step1())
                .next(step2())
                .build();
    }

    @Bean
    public Step step1(){
        return stepBuilderFactory.get("step1")
                .<String,String>chunk(3)
                .reader(itemReader())
                .writer(new ItemWriter<String>() {
                    @Override
                    public void write(List items) throws Exception {
                        items.forEach(item -> System.out.println(item));
                        System.out.println("==========");
                    }
                }).build();
    }

    public FlatFileItemReader itemReader(){
        return new FlatFileItemReaderBuilder<Customer>()
                .name("flatFile")
                .resource(new FileSystemResource("/Users/namhyeop/Desktop/git자료/Spring_Boot_Study/8.Spring_Batch/SpringBatch_8_3_fixedlengthtokenizer/src/main/resources/customer.txt"))
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>())
                .targetType(Customer.class)
                .linesToSkip(1)
                .fixedLength()
                .strict(false)
                .addColumns(new Range(1,5))
                .addColumns(new Range(6,9))
                .addColumns(new Range(10, 11))
                .names("name", "year", "age")
                .build();
    }

    @Bean
    public Step step2(){
        return stepBuilderFactory.get("step2")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("step2 has executeed");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
