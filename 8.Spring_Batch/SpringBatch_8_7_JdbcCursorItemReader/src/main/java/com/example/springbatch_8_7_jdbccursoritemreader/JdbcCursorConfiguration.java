package com.example.springbatch_8_7_jdbccursoritemreader;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * packageName    : com.example.springbatch_8_7_jdbccursoritemreader
 * fileName       : JdbcCursorConfiguration
 * author         : namhyeop
 * date           : 2022/08/12
 * description    :
 * JdbcCursor 예제
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/12        namhyeop       최초 생성
 */
@RequiredArgsConstructor
@Configuration
public class JdbcCursorConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;
    private int chunkSize = 10;
    @Bean
    public Job job(){
        return jobBuilderFactory.get("batchJob")
                .start(step1())
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean
    public Step step1(){
        return stepBuilderFactory.get("step1")
                .<Customer, Customer>chunk(chunkSize)
                .reader(customItemReader())
                .writer(customItemWriter())
                .build();
    }

    @Bean
    public JdbcCursorItemReader<Customer> customItemReader() {
        return new JdbcCursorItemReaderBuilder<Customer>()
                .name("jdbcCursorItemReader")
                .fetchSize(chunkSize)
                .sql("select id, firstName, lastName, birthdate from customer where firstName like ? order by lastName, firstName")
                .beanRowMapper(Customer.class)
                .queryArguments("A%")
                //조회 시작할 데이터의 위치
                .currentItemCount(2)
                //조회를 끝낼 데이터의 위치
                .maxItemCount(3)
                .maxRows(100)
                .dataSource(dataSource)
                .build();
    }

//    @Bean
//    public JdbcCursorItemReader<Customer> customItemReader() {
//        return new JdbcCursorItemReaderBuilder()
//                .name("jdbcCursorItemReader")
//                .fetchSize(10)
//                .sql("select id, firstName, lastName, birthdate from customer where firstName like ? order by lastName, firstName")
//                .beanRowMapper(Customer.class)
//                .queryArguments("A%")
//                .maxItemCount(3)
//                .currentItemCount(2)
//                .maxRows(100)
//                .dataSource(dataSource)
//                .build();
//    }

    private ItemWriter<Customer> customItemWriter() {
        return items->{
            for (Customer item : items) {
                System.out.println(item.toString());
            }
            System.out.println("=============");
        };
    }
}
