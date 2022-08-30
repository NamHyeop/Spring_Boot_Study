package com.example.springbatch_6_15_itemreaderanditemwriteranditemprocessor;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * packageName    : com.example.springbatch_6_15_itemreaderanditemwriteranditemprocessor
 * fileName       : ItemReader_ItemProcessor_ItemWriter_Configuration
 * author         : namhyeop
 * date           : 2022/08/06
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/06        namhyeop       최초 생성
 */

@RequiredArgsConstructor
@Configuration
public class ItemReader_ItemProcessor_ItemWriter_Configuration {

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
                .<Customer, Customer> chunk(3)
                .reader(itemReader())
                .processor(itemProcessor())
                .writer(itemWriter())
                .build();
    }

    @Bean
    public ItemWriter<? super Customer> itemWriter(){
        return new CustomerItemWriter();
    }
    @Bean
    public ItemProcessor<? super Customer, ? extends Customer> itemProcessor(){
        return new CustomItemProcessor();
    }

    @Bean
    public ItemReader<Customer> itemReader(){
        return new CustomItemReader(Arrays.asList(
                new Customer("user1"),
                new Customer("user2"),
                new Customer("user3")));
    }


    @Bean
    public Step step2(){
        return stepBuilderFactory.get("step2")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("step2 has executed");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
