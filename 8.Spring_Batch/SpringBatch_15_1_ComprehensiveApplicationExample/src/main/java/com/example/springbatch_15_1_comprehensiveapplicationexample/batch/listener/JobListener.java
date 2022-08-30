package com.example.springbatch_15_1_comprehensiveapplicationexample.batch.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * packageName    : com.example.springbatch_15_1_comprehensiveapplicationexample.batch.job.api
 * fileName       : JobListener
 * author         : namhyeop
 * date           : 2022/08/29
 * description    :
 * Job의 수행시간을 측정하는 listener
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/29        namhyeop       최초 생성
 */
public class JobListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {

    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        long time = jobExecution.getEndTime().getTime() - jobExecution.getStartTime().getTime();
        System.out.println("===========================================");
        System.out.println("총 소요시간 = " + time);
        System.out.println("===========================================");
    }
}
