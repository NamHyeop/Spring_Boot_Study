package com.example.springbatch_15_1_comprehensiveapplicationexample.batch.job.api;

import com.example.springbatch_15_1_comprehensiveapplicationexample.batch.listener.JobListener;
import com.example.springbatch_15_1_comprehensiveapplicationexample.batch.tasklet.ApiEndTasklet;
import com.example.springbatch_15_1_comprehensiveapplicationexample.batch.tasklet.ApiStartTasklet;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * packageName    : com.example.springbatch_15_1_comprehensiveapplicationexample.batch.job.api
 * fileName       : ApiJobConfiguration
 * author         : namhyeop
 * date           : 2022/08/29
 * description    :
 * FileJob에서 읽어온 이후 DB에 저장한 데이터를
 * 다시 DB에 접근한뒤 API를 걸친 이후 FILE로 저장하는 JOB
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/29        namhyeop       최초 생성
 */
@Configuration
@RequiredArgsConstructor
public class ApiJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final ApiStartTasklet apiStartTasklet;
    private final ApiEndTasklet apiEndTasklet;
    private final Step jobStep;

    @Bean
    public Job apiJob(){
        return jobBuilderFactory.get("apiJob")
                .listener(new JobListener())
                .start(apiStep1())
                .next(jobStep)
                .next(apiStep2())
                .build();
    }

    @Bean
    public Step apiStep1(){
        return stepBuilderFactory.get("apiStep1")
                .tasklet(apiStartTasklet)
                .build();
    }

    @Bean
    public Step apiStep2(){
        return stepBuilderFactory.get("apiStep2")
                .tasklet(apiEndTasklet)
                .build();
    }
}
