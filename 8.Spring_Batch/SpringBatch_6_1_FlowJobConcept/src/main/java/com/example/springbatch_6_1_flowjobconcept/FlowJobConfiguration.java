package com.example.springbatch_6_1_flowjobconcept;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * packageName    : com.example.springbatch_6_1_flowjobconcept
 * fileName       : FlowJobConfiguration
 * author         : namhyeop
 * date           : 2022/07/26
 * description    :
 * step1의 성공이냐 실패냐에 따라 다른 Step이 실행되는 예제
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/26        namhyeop       최초 생성
 */
@RequiredArgsConstructor
@Configuration
public class FlowJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job batchJob(){
        return jobBuilderFactory.get("batchJob")
                .start(step1())
                //on은 step에서 반환하는 실행 결과를 개칭하여 매핑하는 역할을 한다. 반환 값으로는 TransitionBuilder 객체를 반환한다.
                .on("COMPLETED").to(step3())
                //from은 이전 단계에서 정의한 step의 flow를 추가적으로 정의한다.
                .from(step1())
                .on("FAILED").to(step2())
                //end는 build() 앞에 위치하면 FlowsBuilder를 종료하고 SimpleFlow 객체를 생성한다.
                .end()
                .build();
    }

    @Bean
    public Step step1(){
        return stepBuilderFactory.get("step1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("step1 has executed");
                        throw new RuntimeException("step1 was failed");
//                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public Step step2(){
        return stepBuilderFactory.get("step2")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("step2 has executed");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step step3(){
        return stepBuilderFactory.get("step3")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("step3 has executed");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }
}
