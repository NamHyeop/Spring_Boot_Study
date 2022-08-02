package com.example.springbatch_6_8_simpleflowexample;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class SpringBatch68SimpleFlowExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatch68SimpleFlowExampleApplication.class, args);
    }

}
