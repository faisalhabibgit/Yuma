package com.yuma.app.to;

import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.yuma.app.document.Ingredients;
import com.yuma.app.document.enums.HealthLabels;
import com.yuma.app.document.enums.ProteinType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MealTO {

	private String mealId;
	private String name;
	private String description;
	private boolean isAvailable;
	private int mealScore;
	private List<Ingredients> ingredients;
	private Set<HealthLabels> healthLabels;
	private Set<ProteinType> proteinTypes;

}
