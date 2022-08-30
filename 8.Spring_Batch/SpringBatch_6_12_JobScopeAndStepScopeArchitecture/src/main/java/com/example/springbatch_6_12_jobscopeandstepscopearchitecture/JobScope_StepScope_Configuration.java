package com.example.springbatch_6_12_jobscopeandstepscopearchitecture;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * packageName    : com.example.springbatch_6_12_jobscopeandstepscopearchitecture
 * fileName       : JobScope_StepScope_Configuration
 * author         : namhyeop
 * date           : 2022/08/04
 * description    :
 * JobScope와 StepScope 아케텍쳐 흐름을 확인하기 위한 예제
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/04        namhyeop       최초 생성
 */

@Configuration
@RequiredArgsConstructor
public class JobScope_StepScope_Configuration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() {
        return jobBuilderFactory.get("batchJob")
                .start(step1(null))
                .build();
    }

    @Bean
    @JobScope
    public Step step1(@Value("#{JobParameters['message']}") String message) {
        System.out.println("message = " + message);
        return stepBuilderFactory.get("step1")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">> step1 has executed");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}

