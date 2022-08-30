package com.example.springbatch_6_11_jobscopeandstepscope;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

/**
 * packageName    : com.example.springbatch_6_11_jobscopeandstepscope
 * fileName       : CustomStepListener
 * author         : namhyeop
 * date           : 2022/08/03
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/03        namhyeop       최초 생성
 */
public class CustomStepListener implements StepExecutionListener {
    @Override
    public void beforeStep(StepExecution stepExecution) {
        stepExecution.getExecutionContext().putString("name2", "user2");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }
}
