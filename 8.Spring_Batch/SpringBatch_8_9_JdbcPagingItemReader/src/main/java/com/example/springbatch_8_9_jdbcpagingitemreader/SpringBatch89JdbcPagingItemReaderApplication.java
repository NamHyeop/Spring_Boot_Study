package com.example.springbatch_8_9_jdbcpagingitemreader;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class SpringBatch89JdbcPagingItemReaderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatch89JdbcPagingItemReaderApplication.class, args);
    }

}
