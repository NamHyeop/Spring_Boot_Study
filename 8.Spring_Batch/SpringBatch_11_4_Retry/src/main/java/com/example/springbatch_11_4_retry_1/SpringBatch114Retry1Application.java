package com.example.springbatch_11_4_retry_1;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class SpringBatch114Retry1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatch114Retry1Application.class, args);
    }

}
