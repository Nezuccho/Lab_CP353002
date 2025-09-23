package com.cp.lab08sec1.demo.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cp.lab08sec1.demo.model.Customer;


@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepo;
	
	public List<Customer> getCustomer(){
		List<Customer> customer = (List<Customer>) customerRepo.findAll();
		return customer;
	}
	public  Customer getCustomerById(Long id){
		return customerRepo.findById(id).orElseThrow(()->
		new CustomerNotFoundException(id));
	}	 
	public void save(Customer c) {		
		customerRepo.save(c);
	}
	public Customer addCustomer(Customer c) {
		customerRepo.save(c);
		return c;
	}
	public void deleteById(Long id) {
		Customer customer=customerRepo.findById(id).orElseThrow(()->
		new CustomerNotFoundException(id));
		
		//authorRepo.deleteById(id);
		customerRepo.delete(customer);
		
	}
	public Customer updateCustomer(Long id, Customer c) {
		Customer existingCustomer = customerRepo.findById(id).get();
		existingCustomer.setName(c.getName());
		existingCustomer.setEmail(c.getEmail());		
	    return customerRepo.save(existingCustomer);
	}
}
