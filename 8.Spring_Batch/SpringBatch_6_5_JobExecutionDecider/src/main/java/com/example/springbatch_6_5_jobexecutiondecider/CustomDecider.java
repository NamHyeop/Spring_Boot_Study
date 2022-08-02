package com.example.springbatch_6_5_jobexecutiondecider;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

/**
 * packageName    : com.example.springbatch_6_5_jobexecutiondecider
 * fileName       : CustomDecider
 * author         : namhyeop
 * date           : 2022/07/31
 * description    :
 * CustomDecider 예제, count 숫자에 따란 반환 문자열이 달라지는 예제
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/31        namhyeop       최초 생성
 */
public class CustomDecider implements JobExecutionDecider {

    private int count = 0;
    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
        count++;
        System.out.println("count = " + count);
        if(count % 2 == 0){
            return new FlowExecutionStatus("EVEN");
        }else{
            return new FlowExecutionStatus("ODD");
        }
    }
}