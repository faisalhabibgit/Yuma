package com.yuma.app.document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class Meal {

	@Id
	private UUID mealId;
	private String name;
	private String description;
	private boolean isAvailable;
	private HashSet<String> flags;
	private int mealScore;
	protected List<Ingredients> ingredients;

	
	public Meal(Meal deepCopy){
		this.setMealId(deepCopy.mealId);
		this.setName(deepCopy.getName());
		this.setDescription(deepCopy.getDescription());
		this.setAvailable(deepCopy.isAvailable);
		this.setFlags(deepCopy.getFlags());
		this.setIngredients(new ArrayList<>(deepCopy.getIngredients()));
	}
	@Override
	public String toString() {
		return "Meal{" +
			"ingredients=" + ingredients +
			", mealId=" + mealId +
			", description='" + description + '\'' +
			", isAvailable=" + isAvailable +
			", flags=" + flags +
			", mealScore=" + mealScore +
			'}';
	}

	public void updateFrom(Meal mealToUpdate) {
		this.setName(mealToUpdate.getName());
		this.setDescription(mealToUpdate.getDescription());
		this.setAvailable(mealToUpdate.isAvailable());
		this.setIngredients(mealToUpdate.getIngredients());
		this.setFlags(mealToUpdate.getFlags());
		this.setMealScore(mealToUpdate.getMealScore());
	}
}
