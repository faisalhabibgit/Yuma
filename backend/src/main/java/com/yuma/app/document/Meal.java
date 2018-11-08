package com.yuma.app.document;

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

	@Id
	private UUID mealId;
	private String description;
    private boolean isAvailable;
	protected List<Ingredients> ingredients;

	@Override
	public String toString() {
		return "Meal{" +
			"mealId=" + mealId +
			", description='" + description + '\'' +
			", isAvailable=" + isAvailable +
			", ingredients=" + ingredients +
			'}';
	}

	public void updateFrom(Meal mealToUpdate) {
		this.setDescription(mealToUpdate.getDescription());
		this.setAvailable(mealToUpdate.isAvailable());
		this.setIngredients(mealToUpdate.getIngredients());
	}
}
