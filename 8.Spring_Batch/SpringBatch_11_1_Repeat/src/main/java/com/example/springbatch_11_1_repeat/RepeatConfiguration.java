package com.example.springbatch_11_1_repeat;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.*;
import org.springframework.batch.repeat.CompletionPolicy;
import org.springframework.batch.repeat.RepeatCallback;
import org.springframework.batch.repeat.RepeatContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.batch.repeat.exception.ExceptionHandler;
import org.springframework.batch.repeat.exception.SimpleLimitExceptionHandler;
import org.springframework.batch.repeat.policy.CompositeCompletionPolicy;
import org.springframework.batch.repeat.policy.SimpleCompletionPolicy;
import org.springframework.batch.repeat.policy.TimeoutTerminationPolicy;
import org.springframework.batch.repeat.support.RepeatTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * packageName    : com.example.springbatch_11_1_repeat
 * fileName       : RepeatConfiguration
 * author         : namhyeop
 * date           : 2022/08/16
 * description    :
 * Batch의 Repeat 예제
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/16        namhyeop       최초 생성
 */
@RequiredArgsConstructor
@Configuration
public class RepeatConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job(){
        return jobBuilderFactory.get("batchJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }

    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<String,String>chunk(5)
                .reader(new ItemReader<String>() {

                    int i = 0;
                    @Override
                    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
                        i++;
                        return i > 3 ? null : "item" + i;
                    }
                })
                .processor(new ItemProcessor<String, String>() {

                    RepeatTemplate repeatTemplate = new RepeatTemplate();

                    @Override
                    public String process(String item) throws Exception {
                        //(1)예외처리중 SimpleCompletionPolicy 예제, 내부 chunk 3초과시 중단
                        //repeatTemplate.setCompletionPolicy(new SimpleCompletionPolicy(3));
                        //(2)예외처리중 TimeoutTerminationPolicy예제, 3초 이후 반복 중단, 위의 예제와 중첩되게 적용시 맨 밑에 있는 한 개만 적용된다.
                        //repeatTemplate.setCompletionPolicy(new TimeoutTerminationPolicy(3000));

                        //(3)CompositePolicy 예제, SimpleCompletionPolicy와 TimeoutTeminationPolicy를 둘 다 정책으로 채택하는 예제이다. 두 조건중 하나라도 만족시 반복 중지
                        //CompositeCompletionPolicy completionPolicy = new CompositeCompletionPolicy();
                        //CompletionPolicy[] completionPolicies = new CompletionPolicy[]{
                                //new SimpleCompletionPolicy(3),
                                //new TimeoutTerminationPolicy(3000)};
                        //completionPolicy.setPolicies(completionPolicies);
                        //repeatTemplate.setCompletionPolicy(completionPolicy);

                        //(4)ExceptionHandler 예제
                        repeatTemplate.setExceptionHandler(simpleLimitExceptionHandler());
                        //(4)번의 안좋은 예시 객체를 계속 새로 생성하기 때문에 의도한대로 예외처리가 되지 않는다.
                        //repeatTemplate.setExceptionHandler((new SimpleLimitExceptionHandler(3)));
                        repeatTemplate.iterate(new RepeatCallback() {
                            @Override
                            public RepeatStatus doInIteration(RepeatContext repeatContext) throws Exception {
                                System.out.println("repeatTemplate is testing");
                                throw new RuntimeException("Exception is occured");
                                //return RepeatStatus.CONTINUABLE;
                            }
                        });
                        return item;
                    }
                })
                .writer(items -> System.out.println(items))
                .build();
    }

    @Bean
    public ExceptionHandler simpleLimitExceptionHandler(){
        return new SimpleLimitExceptionHandler(3);
    }
}
