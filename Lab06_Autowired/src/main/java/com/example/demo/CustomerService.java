package com.example.demo;

import org.springframework.stereotype.Service;

import com.example.model.Customer;

@Service
public class CustomerService { // Factory service, its function is to create an instance of Customer
	
	public Customer getCustomerById(Long id) {
		return new Customer(id,"Alice");
	}
	
	public Customer getCustomerByIdName(Long id, String name) {
		return new Customer(id,name);
	}
	
}
