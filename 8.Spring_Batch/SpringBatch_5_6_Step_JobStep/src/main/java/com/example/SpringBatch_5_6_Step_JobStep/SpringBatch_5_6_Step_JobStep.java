package com.example.SpringBatch_5_6_Step_JobStep;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class SpringBatch_5_6_Step_JobStep {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatch_5_6_Step_JobStep.class, args);
    }

}
