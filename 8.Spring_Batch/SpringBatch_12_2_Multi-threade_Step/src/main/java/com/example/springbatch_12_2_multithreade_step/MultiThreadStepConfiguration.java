package com.example.springbatch_12_2_multithreade_step;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.*;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * packageName    : PACKAGE_NAME
 * fileName       : com.example.springbatch_12_2_multithreade_step.MultiThreadStepConfiguration
 * author         : namhyeop
 * date           : 2022/08/21
 * description    :
 * Multi-Threaded-Step 예제
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/21        namhyeop       최초 생성
 */
@RequiredArgsConstructor
@Configuration
public class MultiThreadStepConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;

    @Bean
    public Job job(){
        return jobBuilderFactory.get("batchJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .listener(new StopWatchJobListener())
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Customer,Customer>chunk(100)
                .reader(pagingItemReader())
                //(1).지정된 JdbcPagingImterReader, JpaPaginItemReader와 다르게 동시성 처리가 되지 않는 JdbcCursorItemReder를 사용했을 경우 발생하는 문제점을 확인하는 예제, 같은 데이터를 두 번 읽는 현상이 발생한다.
                //.reader(customItemReader())
                .listener(new CustomItemReaderListener())
                .processor((ItemProcessor<Customer,Customer>)item -> item)
                .listener(new CustomItemProcessListener())
                .writer(customItemWriter())
                .listener(new CustomItemWriteListener())
                //taskExecutor를 명시하지 않을 경우 단일 스레드로 작업이 돌아간다.
                //(2).ThreadSafe한 설정을 위한 takeExecutor, SimpleAsynctaskExcetuor에서 ThreadSafe 기능 유지를 해준다.'
                //.taskExecutor(new SimpleAsyncTaskExecutor())
                //(3)taskExecutor 커스터마이징, SimpleAsyncTaskExecutor보다 여러 설정을 할 수 있기에 이 방식을 더 권장, ThreadSafe한 설정을 위한 takeExecutor, SimpleAsynctaskExcetuor에서 ThreadSafe 기능 유지를 해준다.
                .taskExecutor(taskExecutor())
                .build();
    }

    //이해를 돕기 위해 직접 만들어서 등록해보는 tasㅏExecutor
    @Bean
    public TaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        //몇 개의 Thread로 관리할건지
        taskExecutor.setCorePoolSize(4);
        //작업처리시 아직 처리되지 않을 작업이 존재할 경우 몇 개의 thread로 작업을 처리할지에 대한 명시
        taskExecutor.setMaxPoolSize(8);
        //Thread 이름을 확인하기 위해 "async-thread" 고정
        taskExecutor.setThreadNamePrefix("async-thread");
        return taskExecutor;
    }

    @Bean
    public JdbcPagingItemReader pagingItemReader() {
        JdbcPagingItemReader<Customer> reader = new JdbcPagingItemReader<>();

        reader.setDataSource(this.dataSource);
        reader.setPageSize(100);
        reader.setRowMapper(new CustomerRowMapper());

        MySqlPagingQueryProvider queryProvider = getMySqlPagingQueryProvider();

        reader.setQueryProvider(queryProvider);
        return reader;
    }

    @Bean
    public JdbcCursorItemReader<Customer> customItemReader(){
        return new JdbcCursorItemReaderBuilder()
                .name("jdbcCursorItemReader")
                .fetchSize(100)
                .sql("select id, firstName, lastName, birthdate from customer order by id")
                .beanRowMapper(Customer.class)
                .dataSource(dataSource)
                .build();
    }

    public MySqlPagingQueryProvider getMySqlPagingQueryProvider() {
        MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
        queryProvider.setSelectClause("id, firstName, lastName, birthdate");
        queryProvider.setFromClause("from customer");

        Map<String, Order> sortKeys = new HashMap<>(1);
        sortKeys.put("id", Order.ASCENDING);
        queryProvider.setSortKeys(sortKeys);
        return queryProvider;
    }

    @Bean
    public ItemWriter customItemWriter() {
        JdbcBatchItemWriter<Object> itemWriter = new JdbcBatchItemWriter<>();

        itemWriter.setDataSource(dataSource);
        itemWriter.setSql("insert into customer2 values (:id, :firstName, :lastName, :birthdate)");
        itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        itemWriter.afterPropertiesSet();

        return itemWriter;
    }
}
