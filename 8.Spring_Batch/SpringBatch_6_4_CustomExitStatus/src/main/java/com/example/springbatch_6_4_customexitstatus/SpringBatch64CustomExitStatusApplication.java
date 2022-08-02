package com.example.springbatch_6_4_customexitstatus;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class SpringBatch64CustomExitStatusApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatch64CustomExitStatusApplication.class, args);
    }

}
