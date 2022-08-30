package com.example.springbatch_12_1_asyncitemprocessor_asyncitemwriter;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class SpringBatch121AsyncItemProcessorAsyncItemWriterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatch121AsyncItemProcessorAsyncItemWriterApplication.class, args);
    }

}
