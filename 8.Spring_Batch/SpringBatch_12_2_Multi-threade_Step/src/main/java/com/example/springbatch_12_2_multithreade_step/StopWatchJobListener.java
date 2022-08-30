package com.example.springbatch_12_2_multithreade_step;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * packageName    : com.example.springbatch_12_2_multithreade_step
 * fileName       : StopWatchJobListener
 * author         : namhyeop
 * date           : 2022/08/21
 * description    :
 * JobExectuionListenr 구현체, 실행 시간 측정을 확인하는 용도
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/21        namhyeop       최초 생성
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
