//package com.example.springbatch_12_5_synchronizeditemstreamreader;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.ItemReadListener;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
//import org.springframework.batch.item.database.JdbcBatchItemWriter;
//import org.springframework.batch.item.database.JdbcCursorItemReader;
//import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.task.TaskExecutor;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//
//import javax.sql.DataSource;
//
///**
// * packageName    : com.example.springbatch_12_5_synchronizeditemstreamreader
// * fileName       : NotSynchronizedConfiguration
// * author         : namhyeop
// * date           : 2022/08/24
// * description    : SynchronizedItemStreamReader를 사용 안해서 Not-safe한 ItemReader를 만드는 예제
// * ===========================================================
// * DATE              AUTHOR             NOTE
// * -----------------------------------------------------------
// * 2022/08/24        namhyeop       최초 생성
// */
//@RequiredArgsConstructor
//@Configuration
//@Slf4j
//public class NotSynchronizedConfiguration {
//
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//    private final DataSource dataSource;
//
//    @Bean
//    public Job job(){
//        return jobBuilderFactory.get("step1")
//                .incrementer(new RunIdIncrementer())
//                .start(step1())
//                .build();
//    }
//
//    @Bean
//    public Step step1(){
//        return stepBuilderFactory.get("step1")
//                .<Customer, Customer>chunk(60)
//                .reader(customItemReader())
//                .listener(new ItemReadListener<Customer>() {
//                    @Override
//                    public void beforeRead() {
//
//                    }
//
//                    @Override
//                    public void afterRead(Customer item) {
//                        System.out.println("Thread : " + Thread.currentThread().getName() + ", item.getId() " + item.getId());
//                    }
//
//                    @Override
//                    public void onReadError(Exception ex) {
//
//                    }
//                })
//                .writer(customerItemWriter())
//                .taskExecutor(taskExecutor())
//                .build();
//    }
//
//    @Bean
//    @StepScope
//    public JdbcCursorItemReader<Customer> customItemReader(){
//        return new JdbcCursorItemReaderBuilder<Customer>()
//                .fetchSize(60)
//                .dataSource(dataSource)
//                .rowMapper(new BeanPropertyRowMapper<>(Customer.class))
//                .sql("select id, firstname, lastName, birthdate from customer")
//                .name("NotSafetyReader")
//                .build();
//    }
//
//    @Bean
//    @StepScope
//    public JdbcBatchItemWriter<Customer> customerItemWriter(){
//        JdbcBatchItemWriter<Customer> itemWriter = new JdbcBatchItemWriter<>();
//
//        itemWriter.setDataSource(this.dataSource);
//        itemWriter.setSql("insert into customer2 values (:id, :firstName, :lastName, :birthdate)");
//        itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
//        itemWriter.afterPropertiesSet();
//
//        return itemWriter;
//    }
//
//    @Bean
//    public TaskExecutor taskExecutor(){
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(4);
//        executor.setMaxPoolSize(8);
//        executor.setThreadNamePrefix("not-safety-thread-");
//        return executor;
//    }
//}
