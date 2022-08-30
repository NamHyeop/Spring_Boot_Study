package com.example.springbatch_9_4_jsonfileitemwriter;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class Springbatch94JsonFileItemWriterApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springbatch94JsonFileItemWriterApplication.class, args);
    }

}
