package com.example.SpringBatchTasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

/**
 * packageName    : com.example.springbatch_4_1_batchinitconfiguration
 * fileName       : CustomTasklet
 * author         : namhyeop
 * date           : 2022/07/25
 * description    :
 * Tasklet Custom 동작파일
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/25        namhyeop       최초 생성
 */
public class CustomTasklet implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        String stepName = contribution.getStepExecution().getStepName();
        String jobName = chunkContext.getStepContext().getJobName();

        System.out.println("stepName = " + stepName);
        System.out.println("jobName = " + jobName);
        return RepeatStatus.FINISHED;
    }
}
