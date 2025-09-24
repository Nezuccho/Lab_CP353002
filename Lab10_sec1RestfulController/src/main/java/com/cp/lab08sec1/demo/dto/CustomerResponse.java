package com.cp.lab08sec1.demo.dto;

public record CustomerResponse (
		Long id, String name, String email, AddressResponse address) {}
