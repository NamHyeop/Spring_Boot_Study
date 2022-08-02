package com.example.springbatch_6_2_flowjob;

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
 * packageName    : com.example.spring_batch_6_3_transition
 * fileName       : BatchStatusExitStatusConfiguration
 * author         : namhyeop
 * date           : 2022/07/26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/26        namhyeop       최초 생성
 */
@RequiredArgsConstructor
@Configuration
public class BatchStatusExitStatusConfiguration2 {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job batchJob(){
        return this.jobBuilderFactory.get("batchJob")
                .start(step1())
                    .on("FAILED")
                    .to(step2())
                    .on("*")
                    .stop()
                .from(step1())
                    .on("*")
                    .to(step5())
                    .next(step6())
                    .on("COMPLETED")
                    .end()
                .end()
                .build();
    }

    @Bean
    public Flow flow(){
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("flow");
        flowBuilder.start(step3())
                .next(step4())
                .end();
        return flowBuilder.build();
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
                .flow(flow())
                .build();
    }

    @Bean
    public Step step3(){
        return stepBuilderFactory.get("step3")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">>step4 has executed");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step step4(){
        return stepBuilderFactory.get("step4")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("step4 has executed");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step step5(){
        return stepBuilderFactory.get("step5")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("step5 has executed");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step step6(){
        return stepBuilderFactory.get("step6")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">> step6 has executed");
                    return RepeatStatus.FINISHED;
                }).build();
    }

}
