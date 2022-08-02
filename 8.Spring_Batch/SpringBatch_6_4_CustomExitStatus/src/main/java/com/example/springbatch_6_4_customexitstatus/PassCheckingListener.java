package com.example.springbatch_6_4_customexitstatus;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

/**
 * packageName    : com.example.springbatch_6_4_customexitstatus
 * fileName       : PassCheckingListener
 * author         : namhyeop
 * date           : 2022/07/30
 * description    :
 * 사용자 정의 ExitStatus를 구현한 구현체, ExitStatus가 FAILDE가 아닐 경우 PASS를 반환한다.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/30        namhyeop       최초 생성
 */
public class PassCheckingListener implements StepExecutionListener {
    @Override
    public void beforeStep(StepExecution stepExecution) {

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        String exitCode = stepExecution.getExitStatus().getExitCode();
        if(!exitCode.equals(ExitStatus.FAILED.getExitCode())){
            return new ExitStatus("PASS");
        }
        return null;
    }
}
