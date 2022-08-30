package com.example.springbatch_10_2_classifiercompositeitemprocessor;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.*;
import org.springframework.batch.item.support.ClassifierCompositeItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * packageName    : com.example.springbatch_10_2_classifiercompositeitemprocessor
 * fileName       : ClassfileConfiguration
 * author         : namhyeop
 * date           : 2022/08/16
 * description    :
 * ClassifierCompositeItemProcessor 예제, Classfier를 통해 3개의 ItemProcessor중 하나를 고르는 예제 코드
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/16        namhyeop       최초 생성
 */
@RequiredArgsConstructor
@Configuration
public class ClassfileConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job(){
        return jobBuilderFactory.get("batchJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }

    @Bean
    public Step step1(){
        return stepBuilderFactory.get("step1")
                .<ProcessorInfo, ProcessorInfo>chunk(10)
                .reader(new ItemReader<ProcessorInfo>() {

                    int i = 0;

                    @Override
                    public ProcessorInfo read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
                        i++;
                        ProcessorInfo processorInfo = ProcessorInfo.builder().id(i).build();
                        return i > 3 ?  null : processorInfo;
                    }
                })
                .processor(customItemProcessor())
                .writer(items -> System.out.println(items))
                .build();
    }

    //classfier 정의
    @Bean
    public ItemProcessor<? super ProcessorInfo, ? extends ProcessorInfo> customItemProcessor() {
        ClassifierCompositeItemProcessor<ProcessorInfo, ProcessorInfo> processor = new ClassifierCompositeItemProcessor<>();

        //구분자 설정 과정
        ProcessorClassifier<ProcessorInfo, ItemProcessor<?, ? extends ProcessorInfo>> classifier = new ProcessorClassifier<>();
        Map<Integer, ItemProcessor<ProcessorInfo, ProcessorInfo>> processorMap = new HashMap<>();
        //반환값 1일 경우 CustomItmeProcessor1() 실행. 조회하는 로직은 ProcessorClassifier의 classfiy이 메소드를 통해 실행된다.
        processorMap.put(1, new CustomItemProcessor1());
        //반환값 2일 경우 CustomItmeProcessor2() 실행.
        processorMap.put(2, new CustomItemProcessor2());
        //반환값 3일 경우 CustomItmeProcessor3() 실행.
        processorMap.put(3, new CustomItemProcessor3());
        classifier.setProcessorMap(processorMap);

        //구분자를 ClassifierCompositeItemProcessor에 등록
        processor.setClassifier(classifier);

        return processor;
    }
}
