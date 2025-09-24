package com.cp.lab08sec1.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.cp.lab08sec1.demo.model.Address;


@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {}

// long primitive data type, Long is a Long class
