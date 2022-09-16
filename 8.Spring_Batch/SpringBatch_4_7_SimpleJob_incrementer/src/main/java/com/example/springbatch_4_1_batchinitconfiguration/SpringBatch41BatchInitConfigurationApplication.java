package com.example.SpringBatchTasklet;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class SpringBatch41BatchInitConfigurationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatch41BatchInitConfigurationApplication.class, args);
    }

}
