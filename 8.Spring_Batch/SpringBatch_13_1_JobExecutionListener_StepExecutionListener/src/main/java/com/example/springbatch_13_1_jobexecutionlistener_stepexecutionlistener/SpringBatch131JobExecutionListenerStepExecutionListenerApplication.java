package com.example.springbatch_13_1_jobexecutionlistener_stepexecutionlistener;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class SpringBatch131JobExecutionListenerStepExecutionListenerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatch131JobExecutionListenerStepExecutionListenerApplication.class, args);
    }

}
