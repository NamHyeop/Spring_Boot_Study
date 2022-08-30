package com.example.springbatch_15_1_comprehensiveapplicationexample.scheduler;

import com.thoughtworks.xstream.io.xml.JDomReader;
import org.quartz.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Map;

import static org.quartz.JobBuilder.newJob;

/**
 * packageName    : com.example.springbatch_15_1_comprehensiveapplicationexample.scheduler
 * fileName       : JobR
 * author         : namhyeop
 * date           : 2022/08/30
 * description    :
 * ApiJobRunner, FileJobRunner 두 개에서 중복되는 부분을 추상화한 파일
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/30        namhyeop       최초 생성
 */
@Component
public abstract class JobRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        doRun(args);
    }

    protected abstract void doRun(ApplicationArguments args);

    public Trigger buildJobTrigger(String scheduleExp){
        return TriggerBuilder.newTrigger()
                .withSchedule(CronScheduleBuilder.cronSchedule(scheduleExp)).build();
    }

    public JobDetail buildJobDetail(Class job, String name, String group, Map params){
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.putAll(params);

        return newJob(job).withIdentity(name, group)
                .usingJobData(jobDataMap)
                .build();
    }
}
