package com.yuma.app.document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.yuma.app.document.enums.HealthLabels;
import com.yuma.app.document.enums.ProteinType;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Meal {

	@Id
	private String mealId;
	private String name;
	private String description;
	private boolean isAvailable;
	private int mealScore;
	private HashSet<String> flags = new HashSet<>();
	private List<Ingredients> ingredients = new ArrayList<>();
	private Set<HealthLabels> healthLabels = new HashSet<>();
	private Set<ProteinType> proteinTypes = new HashSet<>();
	
	public Meal(Meal deepCopy){
		this.setMealId(deepCopy.mealId);
		this.setName(deepCopy.getName());
		this.setDescription(deepCopy.getDescription());
		this.setAvailable(deepCopy.isAvailable);
		this.setIngredients(new ArrayList<>(deepCopy.getIngredients()));
		this.setHealthLabels(new HashSet<>(deepCopy.getHealthLabels()));
		this.setProteinTypes(new HashSet<>(deepCopy.getProteinTypes()));
	}

	public void updateFrom(Meal mealToUpdate) {
		this.setName(mealToUpdate.getName());
		this.setDescription(mealToUpdate.getDescription());
		this.setAvailable(mealToUpdate.isAvailable());
		this.setIngredients(mealToUpdate.getIngredients());
		this.setMealScore(mealToUpdate.getMealScore());
		this.setHealthLabels(mealToUpdate.getHealthLabels());
		this.setProteinTypes(mealToUpdate.getProteinTypes());
	}
}
