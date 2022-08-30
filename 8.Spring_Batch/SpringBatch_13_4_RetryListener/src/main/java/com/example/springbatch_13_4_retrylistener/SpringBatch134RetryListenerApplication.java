package com.example.springbatch_13_4_retrylistener;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class SpringBatch134RetryListenerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatch134RetryListenerApplication.class, args);
    }

}
