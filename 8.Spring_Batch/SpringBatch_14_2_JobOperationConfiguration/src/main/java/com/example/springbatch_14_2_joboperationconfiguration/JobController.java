package com.example.springbatch_14_2_joboperationconfiguration;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.job.SimpleJob;
import org.springframework.batch.core.launch.*;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.Set;

/**
 * packageName    : com.example.springbatch_14_2_joboperationconfiguration
 * fileName       : JobController
 * author         : namhyeop
 * date           : 2022/08/28
 * description    :일
 * JopOperator, JobRepository, JobExplor 메소드중 일부 테스트
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/28        namhyeop       최초 생성
 */
@RestController
public class JobController {

    @Autowired
    private JobRegistry jobRegistry;

    @Autowired
    private JobOperator jobOperator;

    @Autowired
    private JobExplorer jobExplorer;

    //(1)JobOperator를 사용해서 모든 Job을 시작하는 예제
    @PostMapping("/batch/start")
    public String start(@RequestBody JobInfo jobInfo) throws NoSuchJobException, JobInstanceAlreadyExistsException, JobParametersInvalidException {
        for (Iterator<String> iterator = jobRegistry.getJobNames().iterator(); iterator.hasNext();) {
            SimpleJob job = (SimpleJob) jobRegistry.getJob(iterator.next());
            System.out.println("jobName = " + job.getName());
            jobOperator.start(job.getName(),"id=" + jobInfo.getId());
        }
        return "batch is started";
    }

    //(2)JobOperator stop 예제, 현재 진행중인 Step을 제외한 모든 Job을 정지시킨다.
    @PostMapping("/batch/stop")
    public String stop() throws NoSuchJobException, NoSuchJobExecutionException, JobExecutionNotRunningException {
        for (Iterator<String> iterator = jobRegistry.getJobNames().iterator(); iterator.hasNext();) {

            SimpleJob job = (SimpleJob) jobRegistry.getJob(iterator.next());
            System.out.println("jobName = " + job.getName());

            //(2-1)실행중인 모든 Job을 정지 시키기 위해 JobExecution 찾는다.
            Set<JobExecution> runningJobExecutions = jobExplorer.findRunningJobExecutions(job.getName());
            JobExecution jobExecution = runningJobExecutions.iterator().next();

            jobOperator.stop(jobExecution.getId());
        }
        return "batch is stopped";
    }

    //(3)성공한 Job을 제외한 실패하거나 정지된 Job을 재시작하는 옞[
    @PostMapping("/batch/restart")
    public String restart() throws NoSuchJobException, JobInstanceAlreadyCompleteException, NoSuchJobExecutionException, JobParametersInvalidException, JobRestartException {
        for (Iterator<String> iterator = jobRegistry.getJobNames().iterator(); iterator.hasNext();) {
            SimpleJob job = (SimpleJob) jobRegistry.getJob(iterator.next());
            System.out.println("jobName = " + job.getName());

            //(3-1)Batch는 실패한 Job에서부터 재시작하면 되므로 마지막 지점을 조회하기 위해 마지막 인스턴스를 조회한 뒤 마지막 Instance에서 마지막 Execution을 조회한다..
            JobInstance lastJobInstance = jobExplorer.getLastJobInstance(job.getName());
            JobExecution lastJobExecution = jobExplorer.getLastJobExecution(lastJobInstance);

            jobOperator.restart(lastJobExecution.getId());
        }
        return "batch is restarted";
    }
}
