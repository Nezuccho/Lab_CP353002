package com.cp.lab08sec1.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cp.lab08sec1.demo.repositories.CustomerService;

import com.cp.lab08sec1.demo.model.*;
@RestController
//@RequestMapping("/api/authors"
@RequestMapping("/api/customer")
public class CustomerRestController {
  @Autowired
  CustomerService customerService;
  @GetMapping
  public ResponseEntity<List<Customer>> getCustomer(){
	  List<Customer> customer = customerService.getCustomer();
	  return new ResponseEntity<>(customer, HttpStatus.OK);
  }
  
  @GetMapping("/{id}")  //http://localhost:8085/api/authors/1
  public ResponseEntity<Customer> getCustomerById(@PathVariable("id")
  Long Id){
	  Customer customer = customerService.getCustomerById(Id);
	  return new ResponseEntity<>(customer, HttpStatus.OK);
  }
  //Post to add a new author  http://localhost:8085/api/authors/?author=
  @PostMapping
  public Customer addNewCustomer(@RequestBody Customer customer) {
	  System.out.println(customer);
	 
	  Customer savedCustomer = customerService.addCustomer(customer);
	  return savedCustomer;
  }
  
  @PutMapping("/{id}") //update http://localhost:8085/api/authors/1?author=
  ResponseEntity<Customer> updateCustomer(@RequestBody Customer newCustomer, 
		  @PathVariable Long id) {
	  
     Customer updateCustomer = customerService.updateCustomer(id, newCustomer);
     return ResponseEntity.ok(updateCustomer);
  }

  @DeleteMapping("/{id}") // http://localhost:8085/api/authors/1
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        customerService.deleteById(id);
        return ResponseEntity.ok("Customer deleted successfully");
    }
}
