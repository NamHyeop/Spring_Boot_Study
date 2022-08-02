package com.example.springbatch_6_7_simpleflow;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * packageName    : com.example.springbatch_6_7_simpleflow
 * fileName       : SimpleFlowConfiguration
 * author         : namhyeop
 * date           : 2022/08/02
 * description    :
 * SimpleFlow 흐름을 확인하는 테스트 예제
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/02        namhyeop       최초 생성
 */

@Configuration
@RequiredArgsConstructor
public class SimpleFlowConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job(){
        return jobBuilderFactory.get("batchJob")
                .start(flow())
                .next(step3())
                .end() // end에서 simpleflow를 생성하는것을 잊지 말자
                .build();
    }

    @Bean
    public Flow flow(){
        FlowBuilder<Flow> builder = new FlowBuilder<>("flow");
        builder.start(step1())
                .next(step2())
                .end();
        return builder.build();
    }

    @Bean
    public Step step1(){
        return stepBuilderFactory.get("step1")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">> step1 has executed");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step step2(){
        return stepBuilderFactory.get("step2")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">> step2 has executed");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step step3(){
        return stepBuilderFactory.get("step3")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">> step3 has executed");
                    return RepeatStatus.FINISHED;
                }).build();
    }
}
