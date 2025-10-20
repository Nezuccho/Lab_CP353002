package com.cpLab8Sec1.service;

public class CustomerNotFoundException extends RuntimeException {

	 public CustomerNotFoundException (Long id) {
	    super("Could not find customer " + id);
	  }

}


 