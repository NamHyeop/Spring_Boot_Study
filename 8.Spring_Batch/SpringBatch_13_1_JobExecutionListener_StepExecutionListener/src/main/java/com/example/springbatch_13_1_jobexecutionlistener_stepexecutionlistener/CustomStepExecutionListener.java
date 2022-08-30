package com.example.springbatch_13_1_jobexecutionlistener_stepexecutionlistener;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

/**
 * packageName    : com.example.springbatch_13_1_jobexecutionlistener_stepexecutionlistener
 * fileName       : CustomeStepExecutionListener
 * author         : namhyeop
 * date           : 2022/08/25
 * description    :
 * 인터페이스 방식 리스너
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/25        namhyeop       최초 생성
 */
@Component
public class CustomStepExecutionListener implements StepExecutionListener {
    @Override
    public void beforeStep(StepExecution stepExecution) {
        String stepName = stepExecution.getStepName();
        System.out.println("stepName = " + stepName);
        //매개변수 key값은 name, value는 user1 설정이라는 의미
        stepExecution.getExecutionContext().put("name", "user1");
        System.out.println();
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {

        ExitStatus exitStatus = stepExecution.getExitStatus();
        System.out.println("exitStatus = " + exitStatus);
        BatchStatus status = stepExecution.getStatus();
        System.out.println("status = " + status);
        String name = (String) stepExecution.getExecutionContext().get("name");
        System.out.println("name = " + name);

        return ExitStatus.COMPLETED;
    }
}
