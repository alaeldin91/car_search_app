package com.alaeldin.dowanload_car_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableEurekaServer
public class DowanloadCarServiceApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(DowanloadCarServiceApplication.class, args);
	}

	@Bean
	public WebClient webClient(){

		return WebClient.builder().build();
	}
}
