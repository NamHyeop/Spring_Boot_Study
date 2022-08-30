package com.example.springbatch_15_1_comprehensiveapplicationexample.scheduler;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static org.quartz.JobBuilder.newJob;

/**
 * packageName    : com.example.springbatch_15_1_comprehensiveapplicationexample.scheduler
 * fileName       : JobRunner
 * author         : namhyeop
 * date           : 2022/08/30
 * description    :
 * Quartz를 동작시키는 핵심 class
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/30        namhyeop       최초 생성
 */
@Component
public class ApiJobRunner extends JobRunner{

    @Autowired
    private Scheduler scheduler;

    @Override
    protected void doRun(ApplicationArguments args) {
        JobDetail jobDetail = buildJobDetail(ApiSchJob.class, "apiJob", "batch", new HashMap<>());
        //CronExpression을 사용, 맨 앞에서부터 "초", "분", "시", "일", "주", "월"을 의미함
        Trigger trigger = buildJobTrigger("0/30 * * * * ?");

        try{
            //이 곳에서 실제로 Quarz가 실행된다, JobDeatail에는 Job에대한 정보가 들어있고 Trigger에는 언제 동작할 지에 대한 정보가 담겨있다.
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }
}
