package com.example.springbatch_12_3_parallel_steps;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * packageName    : com.example.springbatch_12_3_parallel_steps
 * fileName       : StopWatchJobListener
 * author         : namhyeop
 * date           : 2022/08/23
 * description    :
 * 시간 측정을 위한 목적인 Listener
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/23        namhyeop       최초 생성
 */

public class StopWatchJobListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {

    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        long time = jobExecution.getEndTime().getTime() - jobExecution.getStartTime().getTime();
        System.out.println("===========================");
        System.out.println("총 소요된 시간 : " + time);
        System.out.println("===========================");
    }
}
