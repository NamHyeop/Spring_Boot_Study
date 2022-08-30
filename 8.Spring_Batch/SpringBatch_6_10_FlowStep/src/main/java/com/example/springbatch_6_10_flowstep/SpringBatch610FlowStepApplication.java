package com.example.springbatch_6_10_flowstep;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class SpringBatch610FlowStepApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatch610FlowStepApplication.class, args);
    }

}
