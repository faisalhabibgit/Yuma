package com.yuma.app.document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CombinationReport {
	
	@Id
	private String id;
	private int numberOfBlanks;
	private int combinationScore;
	private Date createdOn;
	private List<Consumer> userList = new ArrayList<>();
	private List<Meal> mealsList = new ArrayList<>();

	public CombinationReport(int numberOfBlanks, int combinationScore, List<Consumer> activeUsers, List<Meal> availableMeals) {
		this.numberOfBlanks = numberOfBlanks;
		this.combinationScore = combinationScore;
		this.userList = activeUsers;
		this.mealsList = availableMeals;
	}

	@Override
	public String toString() {
		return "CombinationReport{" +
			"numberOfBlanks=" + numberOfBlanks +
			", combinationScore=" + combinationScore +
			", userList=" + userList +
			", mealsList=" + mealsList +
			'}';
	}

	public void updateCombinationScore(int previousMealScore, int newMealScore) {
		this.combinationScore = combinationScore - previousMealScore + newMealScore;
	}
}
