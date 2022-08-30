package com.example.springbatch_6_14_chunkorientedtasklet;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/**
 * packageName    : com.example.springbatch_6_14_chunkorientedtasklet
 * fileName       : ChunkOrientedTaskletConfiguration
 * author         : namhyeop
 * date           : 2022/08/06
 * description    :
 * Chunk가 2개씩 도달할 때 마다 출력되는 예제
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/06        namhyeop       최초 생성
 */
@RequiredArgsConstructor
@Configuration
public class ChunkOrientedTaskletConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job(){
        return jobBuilderFactory.get("batchJob")
                .start(step1())
                .next(step2())
                .build();
    }

    @Bean
    @JobScope
    public Step step1(){
        return stepBuilderFactory.get("step1")
                .<String,String>chunk(2)
                .reader(new ListItemReader<>(Arrays.asList("item1","item2","item3","item4","item5","item6")))
                .processor(new ItemProcessor<String, String>() {
                    @Override
                    public String process(String item) throws Exception {
                        return "my_" + item;
                    }
                })
                .writer(new ItemWriter<String>() {
                    int count = 0;
                    @Override
                    public void write(List<? extends String> items) throws Exception {
                        System.out.println("===========Cur Repeat " + count++ + " ============");
                        items.forEach(item -> System.out.println(item));
                    }
                })
                .build();
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
