package com.example.springbatch_6_14_chunkorientedtasklet;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class SpringBatch614ChunkOrientedTaskletApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatch614ChunkOrientedTaskletApplication.class, args);
    }

}
