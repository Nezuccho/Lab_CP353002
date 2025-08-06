package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Customer;

@RestController
@RequestMapping("/field")
public class FieldInjectionController {
	
	@Autowired
	CustomerService customerService; // Field Injection
	@GetMapping("/{id}")
	public String getCustomer(@PathVariable Long id) {
		Customer cust = customerService.getCustomerById(id);
		return "Field Injection -> Customer ID:" + cust.getId() + ", Name: " + cust.getName();	
	}
	
	@GetMapping("/customer/{id}/{name}") // Get http://localhost:8080/field/customer/15/Alice
	public String getCustomer(@PathVariable Long id, @PathVariable String name) {
		Customer cust = customerService.getCustomerByIdName(id,name);
		return "Field Injection -> Customer ID:" + cust.getId() + ", Name: " + cust.getName();	
	}
}
