package com.example.springboot_9_7_itemwriteadapter;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class SpringBoot97ItemWriteAdapterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot97ItemWriteAdapterApplication.class, args);
    }

}
