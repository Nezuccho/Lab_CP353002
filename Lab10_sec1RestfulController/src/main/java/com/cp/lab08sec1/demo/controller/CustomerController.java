package com.cp.lab08sec1.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.cp.lab08sec1.demo.dto.CustomerRequest;
import com.cp.lab08sec1.demo.dto.CustomerResponse;
import com.cp.lab08sec1.demo.repositories.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	private final CustomerService service;
	public CustomerController(CustomerService service) { this.service = service; }

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CustomerResponse create(@RequestBody @Validated CustomerRequest req)
	{
		return service.create(req);
	}

	@GetMapping("/{id}")
	public CustomerResponse get(@PathVariable Long id) {
		return service.get(id);
	}
}