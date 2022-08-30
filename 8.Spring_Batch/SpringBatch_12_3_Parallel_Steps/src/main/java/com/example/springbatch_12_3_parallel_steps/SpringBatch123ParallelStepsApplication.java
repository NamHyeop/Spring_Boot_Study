package com.example.springbatch_12_3_parallel_steps;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class SpringBatch123ParallelStepsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatch123ParallelStepsApplication.class, args);
    }

}
