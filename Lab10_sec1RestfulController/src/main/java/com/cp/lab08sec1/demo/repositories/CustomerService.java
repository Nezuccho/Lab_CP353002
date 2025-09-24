package com.cp.lab08sec1.demo.repositories;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.cp.lab08sec1.demo.dto.AddressResponse;
import com.cp.lab08sec1.demo.dto.CustomerRequest;
import com.cp.lab08sec1.demo.dto.CustomerResponse;
import com.cp.lab08sec1.demo.model.Address;
import com.cp.lab08sec1.demo.model.Customer;

@Service
public class CustomerService {
	private final CustomerRepository repo;
	public CustomerService(CustomerRepository repo) { this.repo = repo; }

	@Transactional
	public CustomerResponse create(CustomerRequest req) {
		Customer c = new Customer();
		c.setName(req.name());
		c.setEmail(req.email());

		Address a = new Address();
		a.setLine1(req.address().line1());
		a.setLine2(req.address().line2());
		a.setCity(req.address().city());
		a.setState(req.address().state());
		a.setPostalCode(req.address().postalCode());
		a.setCountry(req.address().country());

		c.setAddress(a); // sets both sides; cascade persists Address
		Customer saved = repo.save(c); // save once

		Address sa = saved.getAddress();
		return new CustomerResponse(saved.getId(), saved.getName(), saved.getEmail(), new AddressResponse(sa.getId(),
				sa.getLine1(), sa.getLine2(), sa.getCity(), sa.getState(), sa.getPostalCode(), sa.getCountry()));
	}

	@Transactional(readOnly = true)
	public CustomerResponse get(Long id) {
		Customer c = repo.findById(id).orElseThrow(() ->
		new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer %d not found".formatted(id)));
		Address a = c.getAddress();
		return new CustomerResponse(
				c.getId(), c.getName(), c.getEmail(),new AddressResponse(a.getId(), a.getLine1(), a.getLine2(), a.getCity(),
						a.getState(), a.getPostalCode(), a.getCountry()));
	}
}