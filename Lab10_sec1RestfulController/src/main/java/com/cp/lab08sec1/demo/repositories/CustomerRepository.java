package com.cp.lab08sec1.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.cp.lab08sec1.demo.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {}

// long primitive data type, Long is a Long class
