package com.example.springbatch_6_6_flowjob;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class SpringBatch66FlowJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatch66FlowJobApplication.class, args);
    }

}
