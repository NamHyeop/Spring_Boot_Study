package com.example.springbatch_8_11_itemreaderadapter;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.adapter.ItemReaderAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * packageName    : com.example.springbatch_8_10_itemreaderadapter
 * fileName       : ItemReaderAdapterConfiguration
 * author         : namhyeop
 * date           : 2022/08/13
 * description    :
 * batch 작업중 별도로 등록한 Service를 사용하고 싶을때 사용하는 ItemReader 예제
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/13        namhyeop       최초 생성
 */
@RequiredArgsConstructor
@Configuration
public class ItemReaderAdapterConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() {
        return jobBuilderFactory.get("batchJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<String, String>chunk(10)
                .reader(customItemReader())
                .writer(customItemWriter())
                .build();
    }

    @Bean
    public ItemReaderAdapter customItemReader() {
        ItemReaderAdapter reader = new ItemReaderAdapter();
        reader.setTargetObject(customService());
        reader.setTargetMethod("joinCustomer");
        return reader;
    }

    @Bean
    public ItemWriter<String> customItemWriter(){
        return items ->{
            System.out.println(items);
        };
    }

    private CustomService<String> customService(){
        return new CustomService<>();
    }
}
