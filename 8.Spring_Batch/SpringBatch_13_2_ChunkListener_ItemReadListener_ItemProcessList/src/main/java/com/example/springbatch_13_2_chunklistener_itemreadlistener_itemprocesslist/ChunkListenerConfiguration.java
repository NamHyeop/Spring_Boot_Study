package com.example.springbatch_13_2_chunklistener_itemreadlistener_itemprocesslist;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/**
 * packageName    : com.example.springbatch_13_2_chunklistener_itemreadlistener_itemprocesslist
 * fileName       : ChunkListenerConfiguration
 * author         : namhyeop
 * date           : 2022/08/25
 * description    :
 * ChunkListner테스트 예제
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/25        namhyeop       최초 생성
 */
@RequiredArgsConstructor
@Configuration
@Slf4j
public class ChunkListenerConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job(){
        return jobBuilderFactory.get("batchJbo")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }

    @Bean
    public Step step1(){
        return stepBuilderFactory.get("step1")
                .<Integer,String>chunk(10)
                //(1)Chunk, REad, Process, Write 리스너 예제
                .listener(new CustomChunkListener())
                .listener(new CustomItemReadListener())
                .listener(new CustomItemProcessListener())
                .listener(new CustomItemWriterListener())
                .reader(listItemReader())
                .processor((ItemProcessor) item ->{
                //(2)오류 메시지 확인 예제
//                    throw new RuntimeException("failed");
                    return "item" + item;
                })
                .writer((ItemWriter<String>) items -> {
                    System.out.println("items = " + items);
                })
                .build();
    }

    @Bean
    public ItemReader<Integer> listItemReader(){
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        return new ListItemReader<>(list);
    }
}
