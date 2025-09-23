package com.cp.lab08sec1.demo.repositories;

public class CustomerNotFoundException extends RuntimeException {

	 public CustomerNotFoundException (Long id) {
	    super("Could not find customer " + id);
	  }

}


 