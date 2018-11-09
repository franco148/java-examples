package com.eextreme.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class FeignClientJiraApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignClientJiraApplication.class, args);
	}
}
