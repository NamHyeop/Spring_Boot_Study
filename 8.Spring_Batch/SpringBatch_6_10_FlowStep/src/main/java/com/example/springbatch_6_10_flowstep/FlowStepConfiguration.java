package com.example.springbatch_6_10_flowstep;

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
 * packageName    : com.example.springbatch_6_10_flowstep
 * fileName       : FlowStepConfiguration
 * author         : namhyeop
 * date           : 2022/08/03
 * description    :
 * FlowStep 흐름
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/03        namhyeop       최초 생성
 */
@Configuration
@RequiredArgsConstructor
public class FlowStepConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job(){
        return jobBuilderFactory.get("batchJob")
                .start(flowStep())
                .next(step2())
                .build();
    }

    public Step flowStep(){
        return stepBuilderFactory.get("flowStep")
                .flow(flow())
                .build();
    }

    public Flow flow(){
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("Flow");
        flowBuilder.start(step1()).end();
        return flowBuilder.build();
    }

    @Bean
    public Step step1(){
        return stepBuilderFactory.get("step1")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("step1 has executed");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step step2(){
        return stepBuilderFactory.get("step2")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("step2 has executed");
                    return RepeatStatus.FINISHED;
                }).build();
    }
}
