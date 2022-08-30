package com.example.springbatch_6_11_jobscopeandstepscope;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * packageName    : com.example.springbatch_6_11_jobscopeandstepscope
 * fileName       : JobListener
 * author         : namhyeop
 * date           : 2022/08/03
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/03        namhyeop       최초 생성
 */
public class CustomJobListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        jobExecution.getExecutionContext().put("name","user1");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {

    }
}
