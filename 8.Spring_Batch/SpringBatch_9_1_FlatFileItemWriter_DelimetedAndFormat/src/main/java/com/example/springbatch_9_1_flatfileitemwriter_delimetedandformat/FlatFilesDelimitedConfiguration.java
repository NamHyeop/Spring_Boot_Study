package com.example.springbatch_9_1_flatfileitemwriter_delimetedandformat;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.util.Arrays;
import java.util.List;

/**
 * packageName    : com.example.springbatch_9_1_flatfileitemwriter_delimetedandformat
 * fileName       : FlatFilesDelimitedConfiguration
 * author         : namhyeop
 * date           : 2022/08/14
 * description    :
 * FlatFilesDelimited 예제
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/14        namhyeop       최초 생성
 */
@RequiredArgsConstructor
@Configuration
public class FlatFilesDelimitedConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() {
        return jobBuilderFactory.get("batchJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Customer, Customer>chunk(10)
                .reader(customItemReader())
                .writer(customItemWriter())
                .build();
    }

    @Bean
    public ItemWriter<? super Customer> customItemWriter() {
        return new FlatFileItemWriterBuilder<>()
                .name("flatFileWriter")
                //합친 문자열을 저장할 위치
                .resource(new FileSystemResource("/Users/namhyeop/Desktop/git자료/Spring_Boot_Study/8.Spring_Batch/SpringBatch_9_1_FlatFileItemWriter_DelimetedAndFormat/src/main/resources/customer.txt"))
                //(1).파일에 지속적으로 내용을 덮어 쓰는 옵션
                .append(true)
                //(2).Reader에서 읽은 내용이 아무것도 없을 경우 삭제하는 옵션
//                .shouldDeleteIfEmpty(true)
                .delimited()
                .delimiter("|")
                //객체의 필드 자료 입력
                .names(new String[]{"id", "name", "age"})
                .build();
    }

    @Bean
    public ItemReader<? extends Customer> customItemReader() {
        List<Customer> customers = Arrays.asList(
                new Customer(1, "Kin Nam Hyeop", 27),
                new Customer(2, "Kim ji Hwan", 27),
                new Customer(3, "Hwan ji kim", 28));

        ListItemReader<Customer> reader = new ListItemReader<>(customers);
        //reader가 아무것도 없는 경우 파일을 삭제하는 예제, CustomeItemWriter에서 (2)번 주석을 해제하자
//        ListItemReader<Customer> reader = new ListItemReader<>(Collections.emptyList());
        return reader;
    }
}
