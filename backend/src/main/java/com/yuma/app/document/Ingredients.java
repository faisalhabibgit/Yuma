package com.yuma.app.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ingredients {
	
	private String name;
	private double weight;
	private double calories;
	private double price;

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
