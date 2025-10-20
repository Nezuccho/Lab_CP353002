package com.cpLab8Sec1.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cpLab8Sec1.entity.Customer;



@Repository
public interface CustomerRepository  extends CrudRepository<Customer, Long>{

}
// long primitive data type, Long is a Long class
