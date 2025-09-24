package com.cp.lab08sec1.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "customers")
public class Customer {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank @Size(max=100)
	private String name;

	@Email @Size(max=120) @Column(unique = true)
	private String email;

	@OneToOne(mappedBy = "customer",
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.LAZY)
	private Address address;

	public void setAddress(Address address) {
		if (address == null) {

			if (this.address != null) this.address.setCustomer(null);
			this.address = null;
		} else {
			address.setCustomer(this);
			this.address = address;
		}
	}

	//getters/setters ...
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

}