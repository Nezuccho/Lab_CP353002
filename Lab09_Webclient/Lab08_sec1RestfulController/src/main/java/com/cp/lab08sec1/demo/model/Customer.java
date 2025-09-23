package com.cp.lab08sec1.demo.model;


import jakarta.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false, length = 200)
	private String name;
	@Column(nullable = false, unique = true)
	private String email;
	// constructors
	public Customer() {}
	public Customer(String name, String email) {
		this.name = name;
		this.email = email;
	}
	// getters/setters
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
	
}