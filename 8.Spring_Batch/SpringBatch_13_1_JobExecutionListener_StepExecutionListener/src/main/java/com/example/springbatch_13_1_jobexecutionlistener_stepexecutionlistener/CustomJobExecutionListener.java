package com.example.springbatch_13_1_jobexecutionlistener_stepexecutionlistener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * packageName    : com.example.springbatch_13_1_jobexecutionlistener_stepexecutionlistener
 * fileName       : CustomJobExecutionListener
 * author         : namhyeop
 * date           : 2022/08/25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/25        namhyeop       최초 생성
 */
public class CustomJobExecutionListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("Job is Started");
        System.out.println("JobName : " + jobExecution.getJobInstance() .getJobName());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        long time = jobExecution.getEndTime().getTime() - jobExecution.getStartTime().getTime();
        System.out.println("총 소요시간 = " + time);
    }
}
