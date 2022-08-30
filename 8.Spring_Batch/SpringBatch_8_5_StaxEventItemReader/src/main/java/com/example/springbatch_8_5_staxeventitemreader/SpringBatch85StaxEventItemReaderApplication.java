package com.example.springbatch_8_5_staxeventitemreader;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class SpringBatch85StaxEventItemReaderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatch85StaxEventItemReaderApplication.class, args);
    }

}
