package com.example.springbatch_9_5_jobbatchitemwriter;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class SpringBatch95JobBatchItemWriterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatch95JobBatchItemWriterApplication.class, args);
    }

}
