package com.example.springbatch_12_3_parallel_steps;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

/**
 * packageName    : com.example.springbatch_12_3_parallel_steps
 * fileName       : CustomTasklet
 * author         : namhyeop
 * date           : 2022/08/23
 * description    :
 * Test에서 실행할 Tasklet
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/23        namhyeop       최초 생성
 */
public class CustomTasklet implements Tasklet {

    private long sum;
    private Object lock = new Object();

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        synchronized (lock) {
            for (int i = 0; i < 1000000000; i++) {
                sum++;
            }
            System.out.println(String.format("%s has been executed on thread %s",
                    chunkContext.getStepContext().getStepName(),
                    Thread.currentThread().getName()));
            System.out.println(String.format("sum : %d", sum));
        }
        return RepeatStatus.FINISHED;
    }
}
