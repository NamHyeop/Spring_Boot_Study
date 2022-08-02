package com.example.SpringBatchTasklet;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.*;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * packageName    : com.example.springbatch_4_1_batchinitconfiguration
 * fileName       : StepBuilderconfiguration
 * author         : namhyeop
 * date           : 2022/07/25
 * description    :
 * StepBuilder Factory와 관련된 예제를 진행해보는 코드이다.
 * 개념에서는 StepBuilderFactory에 5개의 종류가 있다고 설명하였다. 5개의 StepBuilderFactory를 걸치는 과정을 확인해보자
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/25        namhyeop       최초 생성
 */
@Configuration
@RequiredArgsConstructor
public class StepBuilderConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job batchJob(){
        return this.jobBuilderFactory.get("batchJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .next(step2())
//                .next(step3())
                .next(step4())
                .next(step5())
                .build();
    }

    //tasklket -> TaskletStepBuilder
    private Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("step1 has executed");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    //chunk -> SimpleStepBuilder 사용
    @Bean
    public Step step2(){
        return stepBuilderFactory.get("step2")
                .<String, String>chunk(3)
                //데이터를 읽는 영역
                .reader(new ItemReader<String>() {
                    @Override
                    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
                        return null;
                    }
                })
                //데이터 로직을 처리하는 영역
                .processor(new ItemProcessor<String, String>() {
                    @Override
                    public String process(String s) throws Exception {

                        return null;
                    }
                })
                //로직을 처리한 데이터를 쓰는 영역
                .writer(list->{})
                .build();
    }

    //partiioner -> PartitionStepBuilder 반환
    @Bean
    public Step step3(){
        return stepBuilderFactory.get("step3")
                .partitioner(step1())
                .gridSize(2)
                .build();
    }

    //job -> JobStepBuilder 반환
    @Bean
    public Step step4(){
        return stepBuilderFactory.get("step4")
                .job(job())
                .build();
    }

    //flow -> FlowStepBuilder 반환
    @Bean
    public Step step5(){
        return stepBuilderFactory.get("step5")
                .flow(flow())
                .build();
    }

    @Bean
    public Job job(){
        return this.jobBuilderFactory.get("job")
                .start(step1())
                .next(step2())
//                .next(step3())
                .build();
    }

    @Bean
    public Flow flow(){
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("flow");
        flowBuilder.start(step2()).end();
        return flowBuilder.build();
    }
}