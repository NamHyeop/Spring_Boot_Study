package com.example.springbatch_8_4_exceptionhandling;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class SpringBatch84ExceptionHandlingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatch84ExceptionHandlingApplication.class, args);
    }

}
