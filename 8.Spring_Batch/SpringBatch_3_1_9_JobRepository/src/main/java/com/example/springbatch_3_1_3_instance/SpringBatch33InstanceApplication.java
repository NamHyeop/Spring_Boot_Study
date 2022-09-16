package com.example.springbatch_3_1_3_instance;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class SpringBatch33InstanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatch33InstanceApplication.class, args);
    }

}
