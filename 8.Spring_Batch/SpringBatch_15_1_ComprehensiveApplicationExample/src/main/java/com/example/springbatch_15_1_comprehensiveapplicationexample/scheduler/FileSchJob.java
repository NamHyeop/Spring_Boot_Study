package com.example.springbatch_15_1_comprehensiveapplicationexample.scheduler;

import lombok.SneakyThrows;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.*;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName    : com.example.springbatch_15_1_comprehensiveapplicationexample.scheduler
 * fileName       : FileSchjob
 * author         : namhyeop
 * date           : 2022/08/30
 * description    :
 * Quartz에도 Job이라는 개념이 존재한다. Batch의 Job과 완벽히 똑같은개념을 아니나 Schedule을 조정하는데 있어서는 유사하다.
 * 그렇기에 Quartz에서 제공하는 인터페이스를 상속받아 어떻게 동작할지 executeInternal에 정의한다.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/30        namhyeop       최초 생성
 */
public class FileSchJob extends QuartzJobBean {

    @Autowired
    private Job fileJob;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private JobExplorer jobExplorer;

    @SneakyThrows
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        //requestDate안에 들어있는 값 뽑기
        String requestDate = (String) context.getJobDetail().getJobDataMap().get("requestDate");


        //현재 파일을 읽고 난후 또 읽어서 DB의 DuplicteKey 오류가 발생하게 된다.
        //그래서 JobExplore를 통해 DB의 모든 인스턴스를 조회하고 각 인스턴스의 JobExecution Id를 활용해 인스턴스의 requestDate 이름을 알아낸다.
        //이후 File 이름이 미리 있는 경우 실행된 파일이라는것을 알 수 있으므로 읽지 않도록 예외처리를 진행해준다.
        int jobInstanceCount = jobExplorer.getJobInstanceCount(fileJob.getName());
        List<JobInstance> jobInstances = jobExplorer.getJobInstances(fileJob.getName(), 0, jobInstanceCount);

        //JobInstances.size가 0인 경우는 아직 실행된게 없는것이므로 예외처리
        if(jobInstances.size() > 0){
            for (JobInstance jobInstance : jobInstances) {
                List<JobExecution> jobExecutions = jobExplorer.getJobExecutions(jobInstance);
                List<JobExecution> jobExecutionList = jobExecutions.stream().filter(jobExecution ->
                        jobExecution.getJobParameters().getString("requestDate").equals(requestDate)).collect(Collectors.toList());
                if(jobExecutionList.size() > 0){
                    throw new JobExecutionException(requestDate + " already exists");
                }
            }
        }
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("id", new Date().getTime())
                .addString("requestDate", requestDate)
                .toJobParameters();

        jobLauncher.run(fileJob, jobParameters);
    }
}
