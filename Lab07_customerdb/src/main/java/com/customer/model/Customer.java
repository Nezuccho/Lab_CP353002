package com.customer.model;

import jakarta.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(nullable = false, length = 200)

private String name;

// Required by JPA & helpful for Jackson
protected Customer() { }

public Customer(String name) {
this.name = name;
}

public Long getId() { return id; }
public String getName() { return name; }
public void setName(String name) { this.name = name; }

@Override
public String toString() { return id + " : " + name; }
}