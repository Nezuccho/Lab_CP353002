package com.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import
org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.customer")
@EntityScan("com.customer.model")
@EnableJpaRepositories("com.customer.repository")
public class CustomerApplication {
public static void main(String[] args) {
SpringApplication.run(CustomerApplication.class, args);
}
}