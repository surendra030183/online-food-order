package com.hcl.foodorder.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@EnableEurekaClient
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Search Service API", version = "2.0", description = "Search Service Information"))
public class SearchServiceApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(SearchServiceApplication.class, args);
	}

}
