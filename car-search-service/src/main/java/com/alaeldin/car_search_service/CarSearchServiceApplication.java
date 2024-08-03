package com.alaeldin.car_search_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableEurekaServer
public class CarSearchServiceApplication {

	public static void main(String[] args) {
		SpringApplication
				.run(CarSearchServiceApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public WebClient webClient(){

		return WebClient.builder().build();
	}

}
