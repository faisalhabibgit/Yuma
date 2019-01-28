package com.yuma.app.catalog;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.yuma.app.document.Meal;
import com.yuma.app.document.User;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CombinationReport {
	private int numberOfBlanks;
	private int combinationScore;
	private List<User> userList;
	private List<Meal> mealsList;
	
	
	public void updateCombinationScore(int previousMeal, int newMeal) {
		this.combinationScore = combinationScore - previousMeal + newMeal;
	}
}
