package com.cpLab8Sec1.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name="name",nullable = false, length = 200)
	private String name;
	@Column(name="email",nullable = false, unique = true)
	private String email;

	// constructors
	public Customer() {
	}

	public Customer(String name, String email) {
		this.name = name;
		this.email = email;
	}
	// getters/setters

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", email=" + email + "]";
	}
	
}
