package com.jpmc.theater.movietheaterapi.entity;

import java.util.Random;

import lombok.Data;

@Data
public class Customer {

	private final String name;
	private int id;
	
	public Customer(String name) {
		this.name = name;
		this.id = new Random().nextInt();
	}

	@Override
	public String toString() {
		return "name: " + name;
	}
}