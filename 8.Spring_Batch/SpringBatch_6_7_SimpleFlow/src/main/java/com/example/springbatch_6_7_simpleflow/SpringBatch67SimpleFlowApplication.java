package com.example.springbatch_6_7_simpleflow;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class SpringBatch67SimpleFlowApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatch67SimpleFlowApplication.class, args);
    }

}
