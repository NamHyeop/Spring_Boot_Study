package com.example.springbatch_10_1_compositeitemprocessor;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class SpringBatch101CompositeItemProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatch101CompositeItemProcessorApplication.class, args);
    }

}
