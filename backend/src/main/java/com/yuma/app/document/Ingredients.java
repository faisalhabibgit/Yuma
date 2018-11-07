package com.yuma.app.document;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
public class Ingredients {
	
	private String name;
	private double weight;
	private double calories;
	private double price;

	public Ingredients(String name, double weight, double calories, double price) {
		this.name = name;
		this.weight = weight;
		this.calories = calories;
		this.price = price;
	}

	@Override
	public String toString() {
		return "Ingredients{" +
			"name='" + name + '\'' +
			", weight=" + weight +
			", calories=" + calories +
			", price=" + price +
			'}';
	}
}
