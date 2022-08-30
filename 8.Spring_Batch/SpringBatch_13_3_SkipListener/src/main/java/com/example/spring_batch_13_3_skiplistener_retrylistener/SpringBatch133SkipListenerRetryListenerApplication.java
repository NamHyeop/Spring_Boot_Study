package com.example.spring_batch_13_3_skiplistener_retrylistener;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class SpringBatch133SkipListenerRetryListenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatch133SkipListenerRetryListenerApplication.class, args);
	}

}
