package com.example.springbatch_15_1_comprehensiveapplicationexample.scheduler;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.HashMap;

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
public class FileJobRunner extends JobRunner {

    @Autowired
    private Scheduler scheduler;

    @Override
    protected void doRun(ApplicationArguments args) {

        String[] sourceArgs = args.getSourceArgs();

        JobDetail jobDetail = buildJobDetail(FileSchJob.class, "fileJob", "batch", new HashMap<>());
        //CronExpression을 사용, 맨 앞에서부터 "초", "분", "시", "일", "주", "월"을 의미함
        Trigger trigger = buildJobTrigger("0/50 * * * * ?");
        //실행 초에 설정한 requestDate, FileJob은 날짜기반 데이터를 읽어온다.
        jobDetail.getJobDataMap().put("requestDate", sourceArgs[0]);
        try {
            //이 곳에서 실제로 Quarz가 실행된다, JobDeatail에는 Job에대한 정보가 들어있고 Trigger에는 언제 동작할 지에 대한 정보가 담겨있다.
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
