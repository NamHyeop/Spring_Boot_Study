package com.example.springbatch_9_2_flatfileitemwriter_format;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.util.Arrays;
import java.util.List;

/**
 * packageName    : com.example.springbatch_9_2_flatfileitemwriter_format
 * fileName       : FlatFilesFormattedConfiguration
 * author         : namhyeop
 * date           : 2022/08/14
 * description    :
 * format 방식 예제
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/14        namhyeop       최초 생성
 */
@RequiredArgsConstructor
@Configuration
public class FlatFilesFormattedConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job(){
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
    public ItemWriter customItemWriter() {
        return new FlatFileItemWriterBuilder<Customer>()
                .name("flatFileWriter")
                .resource(new FileSystemResource("/Users/namhyeop/Desktop/git자료/Spring_Boot_Study/8.Spring_Batch/springbatch_9_2_flatfileitemwriter_format/src/main/resources/customer.txt"))
                //덮어쓰기 옵션
                //.append(true)
                .formatted()
                //format 지정시 맨앞에 '%'를 붙쳐주는게 문법이다. 맨 뒤에는 %를 안붙친다
                .format("%-2d%-15s%-2d")
                .names(new String[]{"id","name","age"})
                .build();
    }

    @Bean
    public ListItemReader customItemReader(){
        List<Customer> customers = Arrays.asList(new Customer(1, "Kim Nam Hyeop", 27),
                new Customer(2, "Kim ji Hwan", 27),
                new Customer(3, "Ji KIm Hwan", 28));

        ListItemReader<Customer> reader = new ListItemReader<>(customers);
        return reader;
    }
}
