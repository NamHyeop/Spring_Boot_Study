package com.example.springbatch_11_2_faulttolerant;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class SpringBatch112FaultTolerantApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatch112FaultTolerantApplication.class, args);
    }

}
