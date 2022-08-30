package com.example.springbatch_8_4_exceptionhandling;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.util.List;

/**
 * packageName    : com.example.springbatch_8_4_exceptionhandling
 * fileName       : ExceptionHandlingConfiguration
 * author         : namhyeop
 * date           : 2022/08/11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/11        namhyeop       최초 생성
 */
@RequiredArgsConstructor
@Configuration
public class ExceptionHandlingConfiguration {
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
                    public void write(List list) throws Exception {
                        list.forEach(item-> System.out.println(item));
                    }
                })
                .build();
    }

    @Bean
    public Step step2(){
        return stepBuilderFactory.get("step2")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">> step2 has executed");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    public FlatFileItemReader itemReader() {
        return new FlatFileItemReaderBuilder<Customer>()
                .name("flatFile")
                .resource(new ClassPathResource("customer.txt"))
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>())
                .targetType(Customer.class)
                .linesToSkip(1)
                .fixedLength()
                .strict(false)
                .addColumns(new Range(1,5))
                .addColumns(new Range(6,9))
                .addColumns(new Range(10,11))
                .names("name","year","age")
                .build();
    }

//    public FlatFileItemReader itemReader(){
//        return new FlatFileItemReaderBuilder<Customer>()
//                .name("flatFile")
//                .resource(new ClassPathResource("customer.txt"))
//                .fieldSetMapper(new BeanWrapperFieldSetMapper<>())
//                .targetType(Customer.class)
//                .linesToSkip(1)
//                .fixedLength()
//                //길이 초과 디버깅 옵션 끄기
//                .strict(false)
//                .addColumns(new Range(1,5))
//                .addColumns(new Range(6,9))
//                //10에서 끝까지
//                .addColumns(new Range(10))
//                .names("name", "year", "age")
//                .build();
//    }
}
