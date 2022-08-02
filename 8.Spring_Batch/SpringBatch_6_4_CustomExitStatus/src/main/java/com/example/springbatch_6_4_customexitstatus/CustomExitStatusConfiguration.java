package com.example.springbatch_6_4_customexitstatus;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * packageName    : com.example.springbatch_6_4_customexitstatus
 * fileName       : CustomExitStatusConfiguration
 * author         : namhyeop
 * date           : 2022/07/30
 * description    :
 * ExitStatus를 Copplted, Stop 말고 사용자가 원하는 ExitStatus를 만드는 예제
 * 쉽께 말해서 ExitStaus의 커스텀을 만드는예제이다.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/30        namhyeop       최초 생성
 */
@RequiredArgsConstructor
@Configuration
public class CustomExitStatusConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job batchJob() {
        return this.jobBuilderFactory.get("batchJob")
                .start(step1())
                    .on("FAILED")
                    .to(step2())
                    .on("PASS")
                    .stop()
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">> step1 has executed");
                    contribution.getStepExecution().setExitStatus(ExitStatus.FAILED);
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">>step2 ahs executed");
                    //아래 처럼 ExitStatus를 Completed로 바꿔줘다 Exeution의 실행결과는 FAILD이다.
                    //이러한 이유는 BatchJob에서 step2의 분기로직을 검증하는 on 단계에서 실패했기 때문이다.
                    //Execution의 실행결과가 마지막 Step의 성공이냐 실패냐의 전체원칙은 변하지 않으니 기억하자
//                    contribution.getStepExecution().setExitStatus(ExitStatus.COMPLETED);

                    return RepeatStatus.FINISHED;
                })
                .listener(new PassCheckingListener())
                .build();
    }
}
