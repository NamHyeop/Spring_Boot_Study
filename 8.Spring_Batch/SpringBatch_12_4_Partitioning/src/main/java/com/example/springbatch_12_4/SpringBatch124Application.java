package com.example.springbatch_12_4;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class SpringBatch124Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatch124Application.class, args);
    }

}
