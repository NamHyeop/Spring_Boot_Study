package com.example.springbatch_6_13_chunkconfiguration;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class SpringBatch613ChunkConfigurationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatch613ChunkConfigurationApplication.class, args);
    }

}
