package com.example.SpringBatch_5_4_Step_startLimitAndAllowStartIfComplte;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * packageName    : com.example.SpringBatch_5_4_Step_startLimitAndAllowStartIfComplte
 * fileName       : Limit_AllowConfiguration
 * author         : namhyeop
 * date           : 2022/07/25
 * description    :
 * allowStartIfComplete와 startLimite를 쓴 복합 예제 코드
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/25        namhyeop       최초 생성
 */

@RequiredArgsConstructor
@Configuration
public class Limit_AllowConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job batchJob(){
        return this.jobBuilderFactory.get("batchJob")
                .start(step1())
                .next(step2())
                .build();
    }

    private Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("stepContribution = " + contribution + ", chunkContext = " + chunkContext);
                        return RepeatStatus.FINISHED;
                    }
                })
//                .allowStartIfComplete(true)
                .build();
    }

    private Step step2(){
        return stepBuilderFactory.get("step2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("stepContribution = " + contribution + ", chunkContext = " + chunkContext);
                        throw new RuntimeException("");
//                        return RepeatStatus.FINISHED;
                    }
                })
                .startLimit(3)
                .build();
    }
}
