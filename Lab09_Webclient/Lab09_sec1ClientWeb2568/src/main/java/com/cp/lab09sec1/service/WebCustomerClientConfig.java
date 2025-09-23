package com.cp.lab09sec1.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebCustomerClientConfig {
	@Bean //bean id = "webclient"
	public WebClient webClient(WebClient.Builder builder) {
		return builder.baseUrl("http://localhost:8085/api/customer").build();
	}
	
}
