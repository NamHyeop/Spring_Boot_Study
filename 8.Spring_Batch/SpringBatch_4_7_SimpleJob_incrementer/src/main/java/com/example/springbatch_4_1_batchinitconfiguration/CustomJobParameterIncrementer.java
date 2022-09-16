package com.example.SpringBatchTasklet;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersIncrementer;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * packageName    : com.example.springbatch_4_1_batchinitconfiguration
 * fileName       : CustomJobParameterIncrementer
 * author         : namhyeop
 * date           : 2022/07/24
 * description    :
 * JobParamterIncrenter를 직접 만들어서 Incrementer의 이해도를 높여보자
 * id값을 1씩 올리면 있는거를 만드는거니까 날짜 데이터를 활용해서 JobParamters의 중복도를 낮춰보자
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/24        namhyeop       최초 생성
 */
public class CustomJobParameterIncrementer implements JobParametersIncrementer {

    static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd-hhmmss");
    @Override
    public JobParameters getNext(JobParameters parameters) {
        String id = format.format(new Date());
        return new JobParametersBuilder().addString("run.id", id).toJobParameters();
    }
}
