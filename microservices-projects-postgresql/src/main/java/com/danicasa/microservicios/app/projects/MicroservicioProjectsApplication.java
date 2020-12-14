package com.danicasa.microservicios.app.projects;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableEurekaClient
@EnableSwagger2
@SpringBootApplication
public class MicroservicioProjectsApplication {

	@RequestMapping("/")
    public String home() {
        return "Hello World mongo!";
    }
	
	public static void main(String[] args) {
		SpringApplication.run(MicroservicioProjectsApplication.class, args);
	}

}
