package com.cp.lab08sec1.demo.dto;

public record CustomerRequest (
		String name, String email, AddressRequest address) {}
