package com.example.springbatch_3_1_8_ExecutionContext;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class ExecutionContextTasklet3 implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        System.out.println("Step3 was executed");
        Object name = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().get("name");
        if (name == null) {
            chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().put("name", "user1");
            throw new RuntimeException("step2 was failed");
        }
        return RepeatStatus.FINISHED;
    }
}
