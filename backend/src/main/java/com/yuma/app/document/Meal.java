package com.yuma.app.document;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Meal {

	protected List<Ingredients> ingredients;
	@Id
	private UUID mealId;
	private String description;
	private boolean isAvailable;
	private HashSet<String> flags;

	@Override
	public String toString() {
		return "Meal{" +
			"ingredients=" + ingredients +
			", mealId=" + mealId +
			", description='" + description + '\'' +
			", isAvailable=" + isAvailable +
			", flags=" + flags +
			'}';
	}

	public void updateFrom(Meal mealToUpdate) {
		this.setDescription(mealToUpdate.getDescription());
		this.setAvailable(mealToUpdate.isAvailable());
		this.setIngredients(mealToUpdate.getIngredients());
		this.setFlags(mealToUpdate.getFlags());
		
	}
}
