package com.example.springbatch_3_1_3_instance;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

/**
 * 사용자 설정의 별도 Tasklet을 구현해서 로직을 작성해보기
 */
//Bean의 기능을 사용할거면 Componet를 사용해서 추가하고 아니라면 추가 X
//@Component
public class CustomTasklet implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        System.out.println("step2 was executed");
        return RepeatStatus.FINISHED;
    }
}
