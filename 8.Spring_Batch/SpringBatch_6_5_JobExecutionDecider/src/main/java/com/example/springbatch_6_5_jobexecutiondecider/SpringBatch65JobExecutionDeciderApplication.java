package com.example.springbatch_6_5_jobexecutiondecider;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class SpringBatch65JobExecutionDeciderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatch65JobExecutionDeciderApplication.class, args);
    }

}
