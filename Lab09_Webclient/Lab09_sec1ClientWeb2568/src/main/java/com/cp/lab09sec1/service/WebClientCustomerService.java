package com.cp.lab09sec1.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatusCode;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

import com.cp.lab09sec1.dto.CustomerRequest;
//import com.cp.kku.kku.demo.model.Author;
import com.cp.lab09sec1.dto.CustomerResponse;

import reactor.core.publisher.Flux;


@Service
public class WebClientCustomerService {
	@Autowired
	 private final WebClient webClient;
	 @Autowired
	 public WebClientCustomerService(WebClient webClient) {
	        this.webClient = webClient;
	 }
	 //get request
	 public Flux<CustomerResponse> getAllCustomer() {// List<Author>
	        return webClient.get()
	                //.uri("/authors") 
	                .retrieve()
	                .bodyToFlux(CustomerResponse.class) ;
	    }
	 public Mono<CustomerResponse> getCustomerById(Long id) {
	        return webClient.get()
	        		.uri("/{id}", id)
	                //.uri("/authors/{id}", id)
	                .retrieve()
	                .bodyToMono(CustomerResponse.class);
	    }
	 public Mono<CustomerResponse> createCustomer(CustomerRequest customer) {
		    System.out.println("Sending Customer: " + customer);

		    return webClient.post()
		            //.uri("/authors")
		            .body(Mono.just(customer), CustomerRequest.class)
		            .retrieve()
		            .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> 
		                Mono.error(
		                		new RuntimeException("Client error during createAuthor")))
		            .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> 
		                Mono.error(new RuntimeException("Server error during createAuthor")))
		            .bodyToMono(CustomerResponse.class)
		            .doOnNext(response ->
		                  System.out.println("Response received: " + response))
		            .doOnError(error ->
		                   System.out.println("Error occurred: " + error.getMessage()));
		}


	    public Mono<CustomerResponse> updateCustomer(long id, CustomerRequest customer) {
	    	System.out.println("Edited Customer "+ customer.toString());
	        return webClient.put().uri("/{id}", id) //uri("/authors/{id}", id)
	        		.bodyValue(customer).retrieve().bodyToMono(CustomerResponse.class);
	    }

	   
	    public Mono<Void> deleteCustomerById(Long id) {
	    	//System.out.println("deleting an author having id ="+id);
	        return webClient.delete()
	                .uri("/{id}", id) //.uri("/authors/{id}", id)
	                .retrieve()
	                .bodyToMono(Void.class);
	    }
}
