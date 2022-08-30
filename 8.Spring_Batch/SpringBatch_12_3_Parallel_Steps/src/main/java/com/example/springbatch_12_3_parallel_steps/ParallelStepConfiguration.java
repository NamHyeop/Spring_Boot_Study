package com.example.springbatch_12_3_parallel_steps;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


/**
 * packageName    : PACKAGE_NAME
 * fileName       : com.example.springbatch_12_3_parallel_steps.ParallelStepConfiguration
 * author         : namhyeop
 * date           : 2022/08/23
 * description    :
 * 각 Step에 스레드가 부여되는 ParalletStep에 관한 예제이다.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/23        namhyeop       최초 생성
 */
@RequiredArgsConstructor
@Configuration
public class ParallelStepConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() {
        return jobBuilderFactory.get("batchJob")
                .incrementer(new RunIdIncrementer())
                .start(flow1())
                //단일 스레드로 실행
                //.next(flow2())
                //병렬 스레드로 실행
                .split(taskExecutor()).add(flow2())
                .end() //simpleflow를 사용하기 위하 end지정, listener 보다 위에 있어야 오류가 발생하지 않는다.
                .listener(new StopWatchJobListener())
                .build();
    }

    @Bean
    public Flow flow1(){
        TaskletStep step = stepBuilderFactory.get("step1")
                .tasklet(tasklet()).build();

        return new FlowBuilder<Flow>("flow1")
                .start(step)
                .build();
    }

    @Bean
    public Flow flow2(){
        TaskletStep step2 = stepBuilderFactory.get("step2")
                .tasklet(tasklet()).build();

        TaskletStep step3 = stepBuilderFactory.get("step3")
                .tasklet(tasklet()).build();
        return new FlowBuilder<Flow>("flow2")
                .start(step2)
                .next(step3)
                .build();
    }

    @Bean
    public Tasklet tasklet() {
        return new CustomTasklet();
    }

    //TaskExecutor에 실행 될 스레드에 정보를 부여하는 과정
    @Bean
    public TaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(4);
        executor.setThreadNamePrefix("async-thread-");
        return executor;
    }
}
