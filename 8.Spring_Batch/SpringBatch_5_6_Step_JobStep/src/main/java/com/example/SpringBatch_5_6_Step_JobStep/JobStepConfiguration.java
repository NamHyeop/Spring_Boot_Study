package com.example.SpringBatch_5_6_Step_JobStep;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.job.DefaultJobParametersExtractor;
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
 * JobStep에 대한 흐름을 파악하는 예제
 * 디버깅하면서 쳅터에서 배운 개념들의 클래스를 확인해보자
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/25        namhyeop       최초 생성
 */
@RequiredArgsConstructor
@Configuration
public class JobStepConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job parentJob() {
        return this.jobBuilderFactory.get("parentJob")
                .start(jobStep(null))
                .next(step2())
                .build();
    }

    @Bean
    public Step jobStep(JobLauncher jobLauncher) {
        return this.stepBuilderFactory.get("jobStep")
                .job(childJob())
                .launcher(jobLauncher)
                .listener(new StepExecutionListener() {
                    //name이란 키값에 value라는 값이 user1이라는 의미이다. parametersExtractor를 이용해 추출할 수 있다.
                    //name=user1 매개변수를 설정했다고 생각하면 이해하기 쉽다.
                    @Override
                    public void beforeStep(StepExecution stepExecution) {
                        stepExecution.getExecutionContext().putString("name", "user1");
                    }

                    @Override
                    public ExitStatus afterStep(StepExecution stepExecution) {
                        return null;
                    }
                })
                .parametersExtractor(jobParametersExtractor())
                .build();
    }

    @Bean
    public Job childJob() {
        return this.jobBuilderFactory.get("childJob")
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                        throw new RuntimeException("step1 was failed");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        throw new RuntimeException("step2 was failed");
//                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public DefaultJobParametersExtractor jobParametersExtractor() {
        DefaultJobParametersExtractor extractor = new DefaultJobParametersExtractor();
        extractor.setKeys(new String[]{"name"});
        return extractor;
    }
}