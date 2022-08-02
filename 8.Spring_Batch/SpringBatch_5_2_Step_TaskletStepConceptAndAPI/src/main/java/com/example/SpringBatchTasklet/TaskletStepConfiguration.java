package com.example.SpringBatchTasklet;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;


/**
 * packageName    : com.example.springbatch_4_1_batchinitconfiguration
 * fileName       : TaskletStepConfiguration
 * author         : namhyeop
 * date           : 2022/07/25
 * description    :
 * Task, Chunk 타입 두 가지 비교테스트를 작성한다.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/25        namhyeop       최초 생성
 */

@Configuration
@RequiredArgsConstructor
public class TaskletStepConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job batchJob(){
        return this.jobBuilderFactory.get("batchJob")
                .start(taskStep())
                .build();
    }

    @Bean
    public Step taskStep(){
        return stepBuilderFactory.get("taskStep")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("step was executed");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step chunkStep(){
        return stepBuilderFactory.get("chunkStepNamePosition")
                .<String, String>chunk(10)
                .reader(new ListItemReader<>(Arrays.asList("item1","item2","item3","item4","item5")))
                .processor(new ItemProcessor<String, String>() {
                    @Override
                    public String process(String item) throws Exception {
                        return item.toUpperCase();
                    }
                })
                .writer(new ItemWriter<String>() {
                    @Override
                    public void write(List<? extends String> items) throws Exception {
                        items.forEach(item -> System.out.println(items));
                    }
                }).build();
    }
}
