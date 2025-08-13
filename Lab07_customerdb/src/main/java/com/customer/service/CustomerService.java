package com.customer.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.customer.model.Customer;
import com.customer.repository.CustomerRepository;
import com.customer.web.ResourceNotFoundException;

import java.util.List;

@Service
@Transactional
public class CustomerService {

private final CustomerRepository repo;

public CustomerService(CustomerRepository repo) {
this.repo = repo;
}

public Customer create(String name) {
return repo.save(new Customer(name));
}

@Transactional(readOnly = true)
public Customer getById(Long id) {
return repo.findById(id)
.orElseThrow(() -> new
ResourceNotFoundException("Customer %d not found".formatted(id)));
}

@Transactional(readOnly = true)
public List<Customer> getAll() {
return repo.findAll();
}

public Customer update(Long id, String name) {
Customer c = getById(id);
c.setName(name);
return repo.save(c); // explicit save for clarity
}

public void delete(Long id) {
if (!repo.existsById(id)) {
throw new ResourceNotFoundException("Customer %d not found".formatted(id));
}
repo.deleteById(id);
}
}