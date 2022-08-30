package com.example.springboot_9_6_jpaitemwriter;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class SpringBoot96JpaItemWriterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot96JpaItemWriterApplication.class, args);
    }

}
