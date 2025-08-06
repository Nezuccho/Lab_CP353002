package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Customer;

@RestController
@RequestMapping("/setter")
public class SetterInjectionController {
	
	CustomerService customerService; // Setter Injection
	@Autowired
	public void SetCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@GetMapping("/{id}") // Get http://localhost:8080/constructor/15
	public String getCustomer(@PathVariable Long id) {
		Customer cust = customerService.getCustomerById(id);
		return "Setter Injection -> Customer ID:" + cust.getId() + ", Name: " + cust.getName();	
	}
	
	@GetMapping("/customer/{id}/{name}") // Get http://localhost:8080/field/customer/15/Alice
	public String getCustomer(@PathVariable Long id, @PathVariable String name) {
		Customer cust = customerService.getCustomerByIdName(id,name);
		return "Setter Injection -> Customer ID:" + cust.getId() + ", Name: " + cust.getName();	
	}
}
