package com.example.springbatch_6_1_flowjobconcept;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class SpringBatch61FlowJobConceptApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatch61FlowJobConceptApplication.class, args);
    }

}
