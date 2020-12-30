package com.danicasa.microservices.webflux;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MicroservicesProjectsWebfluxApplication {

	private static final Logger log = LoggerFactory.getLogger(MicroservicesProjectsWebfluxApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(MicroservicesProjectsWebfluxApplication.class, args);
	}
	

}
