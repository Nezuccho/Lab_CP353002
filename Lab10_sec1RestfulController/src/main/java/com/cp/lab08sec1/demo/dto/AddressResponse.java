package com.cp.lab08sec1.demo.dto;

public record AddressResponse (
		Long id, String line1, String line2, String city, String state, String postalCode, String country) {}
