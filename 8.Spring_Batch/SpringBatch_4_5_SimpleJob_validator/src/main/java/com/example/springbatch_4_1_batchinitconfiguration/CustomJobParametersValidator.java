package com.example.SpringBatchTasklet;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;

public class CustomJobParametersValidator implements JobParametersValidator {
    /**
     * 파라미터가 JopParamters로 넘어온다
     */
    @Override
    public void validate(JobParameters parameters) throws JobParametersInvalidException {
        System.out.println("parameters.getString(\"name\") = " + parameters.getString("name"));
        if(parameters.getString("name")==null){
            throw new JobParametersInvalidException("name parameters is not found");
        }
    }
}
