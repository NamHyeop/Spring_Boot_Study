package com.example.springbatch_8_2_flatfileitemreader_delimetedlinetokenizer;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.util.List;

/**
 * packageName    : com.example.springbatch_8_1_flatfileitemreader
 * fileName       : FlatFilesConfiguration
 * author         : namhyeop
 * date           : 2022/08/08
 * description    :
 * FlatFilesItemReader-delimetedlinetokenizer 예제
 * 이전 8-1에서는 직접 delimitedlinetokenizer를 구현해서 사용했다면 이번에는 실제 존재하는 라이브러리를 itemReadr에 적용한다.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/08        namhyeop       최초 생성
 */

@RequiredArgsConstructor
@Configuration
public class FlatFilesConfiguration {
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
                .<String, String>chunk(3)
                .reader(itemReader())
                .writer(new ItemWriter<Customer>() {
                    @Override
                    public void write(List<? extends Customer> list) throws Exception {
                        System.out.println("list = " + list);
                    }
                }).build();
    }

    @Bean
    public Step step2(){
        return stepBuilderFactory.get("step2")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("step2 has executed");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public ItemReader itemReader(){
        return new FlatFileItemReaderBuilder<Customer>()
                .name("flatFile")
                .resource(new ClassPathResource("customer.csv"))
                //1.아래의 직접 만든 CustomerFieldSeMapper를 사용하지 않고 Batch의 BeanWrapperFieldSetMapper를 사용
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>())
                //2.BeanWrapperFieldSetMapper를 사용한다면 targetType을 반드시 명시해줘야한다.
                .targetType(Customer.class)
//                .fieldSetMapper(new CustomerFieldSetMapper())
                .linesToSkip(1)
                .delimited().delimiter(",")
                .names("name","age","year")
                .build();
    }
}
