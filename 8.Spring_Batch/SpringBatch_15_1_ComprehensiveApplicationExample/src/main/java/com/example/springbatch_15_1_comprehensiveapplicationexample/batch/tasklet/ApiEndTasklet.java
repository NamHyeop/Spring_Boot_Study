package com.example.springbatch_15_1_comprehensiveapplicationexample.batch.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

/**
 * packageName    : com.example.springbatch_15_1_comprehensiveapplicationexample.batch.job.api
 * fileName       : ApiEndTasklet
 * author         : namhyeop
 * date           : 2022/08/29
 * description    :
 * ApiServer에서 동작할 로직을 작성하는 곳, 간단한 로그 출력으로 구현함
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/29        namhyeop       최초 생성
 */
@Component
public class ApiEndTasklet implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println(" >> ApiService is end");

        return RepeatStatus.FINISHED;
    }
}
