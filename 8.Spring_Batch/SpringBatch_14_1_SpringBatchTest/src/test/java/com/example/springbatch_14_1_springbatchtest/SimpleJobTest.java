package com.example.springbatch_14_1_springbatchtest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.*;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * packageName    : com.example.springbatch_14_1_springbatchtest
 * fileName       : SimpleJobTest
 * author         : namhyeop
 * date           : 2022/08/28
 * description    :
 * Spring Batch의 testcode를 작성하는 예제
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/28        namhyeop       최초 생성
 */
@RunWith(SpringRunner.class)
@SpringBatchTest
// 항상 한 테스트에는 하나의 Job 테스트만 등록해야한다. TestBatchConfig.calss는 Job 클래스가 아니라 SpringBatch를 동작시키는 EnableBatchProcessing과 같은 어노테이션이 등록되어있다.
@SpringBootTest(classes = {SimpleJobConfiguration.class, TestBatchConfig.class})
public class SimpleJobTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void simpleJob_test() throws Exception {

        //given
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("name", "user1")
                .addLong("date", new Date().getTime())
                .toJobParameters();

        //when
        //(1) Job Test, 동작할 Job 설정은 SpringBootTest 설정에서 한다.
//        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
        //(2)Step Test, 동작할 Step이름으로 설정
        JobExecution jobExecution1 = jobLauncherTestUtils.launchStep("step1");


        //then
        //(1-1)
//        Assert.assertEquals(jobExecution.getStatus(), BatchStatus.COMPLETED);
        //(1-2)
//        Assert.assertEquals(jobExecution.getExitStatus(), ExitStatus.COMPLETED);

        //(2-1)Step에 대한 Execution 결과 가져오기
        StepExecution stepExeution = (StepExecution)((List)jobExecution1.getStepExecutions()).get(0);
        //(2-2)Step의 ChunkSize가 100으로 설정되어있다. 그러므로 총 CommitCount는 10번이어야 한다.
        // 그런데 데이터 size가 1000이고 chunkSize가 100d이라면? 그러면 청크와 읽은 데이터가 나누어 떨어지면 맨 마지막 데이터가 null인지를 확인하고 commit이 한 번 더 이뤄지므로 11이어야한다.
        Assert.assertEquals(stepExeution.getCommitCount(),10);
        //(2-3)Step에서 읽는 DB의 데이터 사이즈가 1000이므로 총 1000번의 Readcount가 일어나야 한다.
        Assert.assertEquals(stepExeution.getReadCount(), 999);
        //(2-4)Step에서 읽는 DB의 데이터 사이즈가 1000이므로 총 1000번의 ReadWrite가 일어나야 한다.
        Assert.assertEquals(stepExeution.getWriteCount(), 999);
    }

    @After
    public void clean() {
        jdbcTemplate.execute("delete from customer2");
    }
}
