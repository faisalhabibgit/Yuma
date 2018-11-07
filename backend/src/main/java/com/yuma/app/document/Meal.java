package com.yuma.app.document;

import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
public class Meal {
	@Id
	private final UUID mealId;
	private String description;
	private boolean isAvailable;
	protected List<Ingredients> ingredients;
	

	public Meal(UUID mealId, String description, boolean isAvailable, List<Ingredients> ingredients) {
		this.mealId = mealId;
		this.description = description;
		this.isAvailable = isAvailable;
		this.ingredients = ingredients;
	}

	@Override
	public String toString() {
		return "Meal{" +
			"mealId=" + mealId +
			", description='" + description + '\'' +
			", isAvailable=" + isAvailable +
			", ingredients=" + ingredients +
			'}';
	}
}
