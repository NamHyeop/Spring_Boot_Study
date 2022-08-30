package com.example.springbatch_14_2_joboperationconfiguration;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * packageName    : com.example.springbatch_14_2_joboperationconfiguration
 * fileName       : JobOperationConfiguration
 * author         : namhyeop
 * date           : 2022/08/28
 * description    :
 * JobOperator, JobRegistry, JobExplorer의 메소드를 테스트하기 위한 config파일
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/28        namhyeop       최초 생성
 */
@RequiredArgsConstructor
@Configuration
public class JobOperationConfiguration {

    public final JobBuilderFactory jobBuilderFactory;
    public final StepBuilderFactory stepBuilderFactory;
    public final JobRegistry jobRegistry; //(1)

    @Bean
    public Job job1(){
        return jobBuilderFactory.get("batchJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .next(step2())
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("step1 was executed");
                    //(2-2) JobOperator의 Stop 예제를 확인하기 위한 목적의 sleep 코드
                    Thread.sleep(3000);
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step step2(){
        return stepBuilderFactory.get("step2")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("step2 was executed");
                    Thread.sleep(3000);
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

//    (1-1) (1)의 JobRegistry를 사용해주기 위해서는 아래처럼 반드시 사전에 BeanPostProcessor에서 JobRegistry를 등록해줘야 한다.
    @Bean
    public BeanPostProcessor jobRegistryBeanPostProcessor(){
        JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor = new JobRegistryBeanPostProcessor();
        jobRegistryBeanPostProcessor.setJobRegistry(jobRegistry);
        return jobRegistryBeanPostProcessor;
    }
}
