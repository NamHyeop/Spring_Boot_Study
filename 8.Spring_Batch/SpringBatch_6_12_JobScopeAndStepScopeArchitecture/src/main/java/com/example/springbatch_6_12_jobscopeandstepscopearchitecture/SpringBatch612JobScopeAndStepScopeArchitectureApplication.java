package com.example.springbatch_6_12_jobscopeandstepscopearchitecture;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class SpringBatch612JobScopeAndStepScopeArchitectureApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatch612JobScopeAndStepScopeArchitectureApplication.class, args);
    }

}
