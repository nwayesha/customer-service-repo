package com.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CustomerServiceApplication {
	final static Logger logger = LoggerFactory.getLogger(CustomerServiceApplication.class);

	public static void main(String[] args) {
		System.out.println("Current Directory = " + System.getProperty("user.dir"));
		SpringApplication.run(CustomerServiceApplication.class, args);
		logger.info("Application starting...");
	}
}
