package com.example.springbatch_6_11_jobscopeandstepscope;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class SpringBatch611JobScopeAndStepScopeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatch611JobScopeAndStepScopeApplication.class, args);
    }

}
