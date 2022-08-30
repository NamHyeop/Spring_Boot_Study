package com.example.springbatch_8_7_jdbccursoritemreader;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class SpringBatch87JdbcCursorItemReaderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatch87JdbcCursorItemReaderApplication.class, args);
    }

}
