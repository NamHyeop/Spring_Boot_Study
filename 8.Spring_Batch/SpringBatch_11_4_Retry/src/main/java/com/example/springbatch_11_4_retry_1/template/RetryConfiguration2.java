package com.example.springbatch_11_4_retry_1.template;

import com.example.springbatch_11_4_retry_1.RetryableException;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * packageName    : com.example.springbatch_11_4_retry_1
 * fileName       : RetryConfiguration2
 * author         : namhyeop
 * date           : 2022/08/16
 * description    :스
 * Retry 예제
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/16        namhyeop       최초 생성
 */
@RequiredArgsConstructor
@Configuration
public class RetryConfiguration2 {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job(){
        return jobBuilderFactory.get("batchJob2")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();

    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step2")
                .<String,Customer> chunk(5)
                .reader(reader())
                .processor(processor())
                .writer(items -> items.forEach(item -> System.out.println(item)))
                .faultTolerant()
                //(1).Skip 예제
//                .skip(RetryableException.class)
//                .skipLimit(2)
                //(2).retry 설정
                //.retry(RetryableException.class)
                //.retryLimit(2)
                //(3).RetryPolicy 예제
//                .retryPolicy(retryPolicy())
                .build();
    }

    @Bean
    public ItemProcessor<? super String, Customer> processor() {
        return new RetryItemProcessor2();
    }

    @Bean
    public ListItemReader<String> reader(){
        List<String> items = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            items.add(String.valueOf(i));
        }
        return new ListItemReader<>(items);
    }

    //Retry에 대한 정책을 2번처럼 한 개가 아닌 여러 개로 설정할 수 있는 RetryPolicy 예제
    @Bean
    public RetryPolicy retryPolicy(){
        Map<Class<? extends Throwable>, Boolean> exceptionClass = new HashMap();
        exceptionClass.put(RetryableException.class, true);

        //첫 번째 매개변수가 Retry 최대 반복 횟수를 의미
        SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy(2, exceptionClass);

        return simpleRetryPolicy;
    }

    @Bean
    public RetryTemplate retryTemplate(){
        Map<Class<? extends Throwable>, Boolean> exceptionClass = new HashMap<>();
        exceptionClass.put(RetryableException.class, true);

        FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(2000);

        SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy(2, exceptionClass);
        RetryTemplate retryTemplate = new RetryTemplate();
        retryTemplate.setRetryPolicy(simpleRetryPolicy);
        //재시작 지연시간 설정
        //retryTemplate.setBackOffPolicy(backOffPolicy);
        return retryTemplate;
    }
}
