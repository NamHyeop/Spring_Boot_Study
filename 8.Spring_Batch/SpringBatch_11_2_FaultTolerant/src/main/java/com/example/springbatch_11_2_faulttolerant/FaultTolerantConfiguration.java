package com.example.springbatch_11_2_faulttolerant;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * packageName    : com.example.springbatch_11_2_faulttolerant
 * fileName       : FaultTolerantConfiguration
 * author         : namhyeop
 * date           : 2022/08/16
 * description    :
 * Faluttolerant를 활용해 예외를 Handling 하는 예제
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/16        namhyeop       최초 생성
 */
@RequiredArgsConstructor
@Configuration
public class FaultTolerantConfiguration {

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
                .<String,String>chunk(5)
                .reader(new ItemReader<String>() {
                    int i = 0;
                    @Override
                    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
                        i++;
                       if(i == 1){
                           //밑에서 faultTolerant()에서 skip 해주기 때문에 이 예외는 실행되지 않는다.
                           throw new IllegalArgumentException("this exception is skipped");
                       }
                       return i > 3 ? null : "item" + i;
                    }
                })
                .processor(new ItemProcessor<String, String>() {
                    @Override
                    public String process(String s) throws Exception {
                        //밑에서 faultTolerant()에서 Retry 해주기 때문에 이 예외는 Retry된다.
                        throw new IllegalStateException("this exception is retried");
//                        return null;
                    }
                })
                .writer(items -> System.out.println(items))
                .faultTolerant()
                .skip(IllegalArgumentException.class)
                .skipLimit(2)
                .retry(IllegalStateException.class)
                .retryLimit(2)
                .build();
    }

}
