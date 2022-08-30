package com.example.springbatch_7_5_itemstream;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class SpringBatch75ItemStreamApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatch75ItemStreamApplication.class, args);
    }

}
