package com.customer.dto;

import jakarta.validation.constraints.NotBlank;

public record CustomerReq(@NotBlank String name) { }