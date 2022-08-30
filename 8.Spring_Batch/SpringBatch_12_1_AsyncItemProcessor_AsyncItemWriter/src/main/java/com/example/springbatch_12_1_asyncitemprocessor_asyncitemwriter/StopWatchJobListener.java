package com.example.springbatch_12_1_asyncitemprocessor_asyncitemwriter;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * packageName    : com.example.springbatch_12_1_asyncitemprocessor_asyncitemwriter
 * fileName       : StopWatchJobListener
 * author         : namhyeop
 * date           : 2022/08/19
 * description    :
 * 동기식 Step과 비동기식 Step의 성능차이를 측정하기 위한 Listener
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/19        namhyeop       최초 생성
 */
public class StopWatchJobListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {

    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        long time = jobExecution.getEndTime().getTime() - jobExecution.getStartTime().getTime();
        System.out.println("=============================================================");
        System.out.println("총 소요시간: " + time);
        System.out.println("=============================================================");
    }
}
