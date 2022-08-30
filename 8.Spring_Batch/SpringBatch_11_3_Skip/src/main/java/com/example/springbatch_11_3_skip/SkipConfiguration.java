package com.example.springbatch_11_3_skip;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.skip.LimitCheckingItemSkipPolicy;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.item.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * packageName    : com.example.springbatch_11_3_skip
 * fileName       : SkipConfiguration
 * author         : namhyeop
 * date           : 2022/08/16
 * description    :
 * Skip 예제
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/16        namhyeop       최초 생성
 */
@RequiredArgsConstructor
@Configuration
public class SkipConfiguration {

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
    public Step step1(){
        return stepBuilderFactory.get("step1")
                .<String, String>chunk(5)
                .reader(new ItemReader<String>() {
                    int i = 0;
                    @Override
                    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
                        i++;
                        if(i == 3){
                            throw new SkipAbleException("skip");
                        }
                        System.out.println("ItemReader = " + i);
                        return i > 20 ? null : String.valueOf(i);
                    }
                })
                .processor(itemProcess())
                .writer(itemWriter())
                .faultTolerant()
                //아래 설정이 위의 설정을 덮어쓴다.
                //(1) 사용자 설정 Skip 정책
                .skipPolicy(limitCheckingItemSkipPolicy())
                //(2) NoSkip
                //.noSkip(SkippableException.class)
                //(3) Skip
                //.skip(SkipAbleException.class)
                //(4) skipLimit
                //.skipLimit(2)
                //(5) retry
                //.retry(SkipAbleException.class)
                //(6) retryLimit
                //.retryLimit(2)
                //(7) noRollback
                //.noRollback(SkippableException.class)

                .build();
    }

    //Custom 설정 옵션
   @Bean
    public SkipPolicy limitCheckingItemSkipPolicy(){
        Map<Class<? extends Throwable>, Boolean> exceptionClass = new HashMap<>();
        exceptionClass.put(SkipAbleException.class, true);

        LimitCheckingItemSkipPolicy limitCheckingItemSkipPolicy = new LimitCheckingItemSkipPolicy(4, exceptionClass);
        return limitCheckingItemSkipPolicy;
    }

    @Bean
    public ItemWriter<? super String> itemWriter() {
        return new SkipItemWriter();
    }

    @Bean
    public ItemProcessor<? super String, String> itemProcess(){
        return new SkipItemProcessor();
    }
}
