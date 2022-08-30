package com.example.springbatch_13_1_jobexecutionlistener_stepexecutionlistener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.annotation.BeforeJob;

/**
 * packageName    : com.example.springbatch_13_1_jobexecutionlistener_stepexecutionlistener
 * fileName       : CustomJobExecutionListener
 * author         : namhyeop
 * date           : 2022/08/25
 * description    :
 * Annotaiton Listener Example
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/25        namhyeop       최초 생성
 */
public class CustomAnnotationJobExecutionListener implements JobExecutionListener {
    //메소드 이름은 bjob, bprocess등 뭐든 상관없다
    @BeforeJob
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("Job is Started");
        System.out.println("JobName : " + jobExecution.getJobInstance() .getJobName());
    }

    @AfterJob
    public void afterJob(JobExecution jobExecution) {
        long time = jobExecution.getEndTime().getTime() - jobExecution.getStartTime().getTime();
        System.out.println("총 소요시간 = " + time);
    }
}
