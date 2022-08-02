package com.example.SpringBatch_5_6_Step_JobStep;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * packageName    : com.example.SpringBatch_5_5_Step_TaskletStepArchitecture
 * fileName       : TaskletStepArchitectureCongiguration
 * author         : namhyeop
 * date           : 2022/07/25
 * description    :
 * Tasklet의 Architecture를 확인하는 테스트
 * 디버깅하면서 쳅터에서 배운 개념들의 클래스를 확인해보자
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/25        namhyeop       최초 생성
 */
@RequiredArgsConstructor
@Configuration
public class TaskletStepArchitectureConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job batchJob() {
        return this.jobBuilderFactory.get("batchJob")
                .start(step1())
                .next(step2())
                .build();
    }


    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("stepContribution = " + stepContribution + ", chunkContext = " + chunkContext);
                        return RepeatStatus.FINISHED;
                    }
                })
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("stepContribution = " + stepContribution + ", chunkContext = " + chunkContext);
                        throw new RuntimeException("");
//                        return RepeatStatus.FINISHED;
                    }
                })
                .startLimit(3)
                .build();
    }
}