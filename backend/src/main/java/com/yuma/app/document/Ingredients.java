package com.yuma.app.document;

import java.util.HashSet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.mongodb.core.mapping.Document;

import com.yuma.app.document.enums.Allergens;
import com.yuma.app.document.enums.HealthLabels;

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
	private boolean optional;
	private HashSet<HealthLabels> healthLabels;
	private HashSet<Allergens> allergens;

	@Override
	public String toString() {
		return "Ingredients{" +
			"name='" + name + '\'' +
			", weight=" + weight +
			", calories=" + calories +
			", price=" + price +
			", optional=" + optional +
			'}';
	}
}
