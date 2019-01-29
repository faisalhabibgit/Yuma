package com.yuma.app.catalog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.mapping.Document;

import com.yuma.app.document.Ingredients;
import com.yuma.app.document.Meal;
import com.yuma.app.document.User;
import com.yuma.app.repository.MealRepository;

@Slf4j
@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MealCatalog {
	private Logger logger = LoggerFactory.getLogger(MealCatalog.class);
	private MealRepository mealRepository;
	private List<CombinationReport> possibleCombinations;
	private List<Meal> addedMeals;

	public MealCatalog(MealRepository mealRepository) {
		this.mealRepository = mealRepository;
		this.addedMeals = new ArrayList<>();
		this.possibleCombinations = new ArrayList<>();
	}
	
	public List<CombinationReport> generateWeeklyCombination(List<Meal> availableMeals, List<User> activeUsers) {
		int i = 0;
		CombinationReport combinationReport;
		setMealScores(availableMeals, activeUsers);
		combinationReport = new CombinationReport(0, countCombinationScore(availableMeals), activeUsers, availableMeals);

		runMealCombinationAlgorithm(combinationReport);
		
		if(!addedMeals.isEmpty()){
			combinationReport.getMealsList().addAll(addedMeals);
		}

		while (i < 2 && (combinationReport.getNumberOfBlanks() != 0)) {
			combinationReport = new CombinationReport(0, countCombinationScore(availableMeals), activeUsers, availableMeals);
			replaceLowestScore(combinationReport, i);
			runMealCombinationAlgorithm(combinationReport);
			i++;
		}
		return possibleCombinations;
	}

	private void runMealCombinationAlgorithm(CombinationReport combinationReport) {
		for (User user : combinationReport.getUserList()) {
			generatePossibleMealsForUser(combinationReport, user, 0);
		}
		possibleCombinations.add(combinationReport);
	}

	protected void setMealScores(List<Meal> mealList, List<User> userList) {
		boolean scorable = true;
		List<String> userDislikesList;
		for (User user : userList) {
			userDislikesList = user.getDislikesList();
			for (Meal meal : mealList) {
				for (Ingredients ingredient : meal.getIngredients()) {
					if (userDislikesList.contains(ingredient.getName())) {
						if (!ingredient.isOptional()) {
							scorable = false;
							break;
						}
					}
				}
				if (scorable) {
					meal.setMealScore((meal.getMealScore() + 1));
				}
			}
		}
	}

	protected void generatePossibleMealsForUser(CombinationReport combinationReport, User user, int mealCounter) {
		logger.info("inside generate possible meals for that user");

		if (user.getPlan().getNumOfMeals() == mealCounter) {
			return;
		}

		for (Meal meal : combinationReport.getMealsList()) {
			if (user.getMealList().size() < user.getPlan().getNumOfMeals()) {
				if (checkIfMealWorks(combinationReport, meal, user)) {
					mealCounter++;
				}
			} else {
				break;
			}
		}

		if (user.getMealList().size() != user.getPlan().getNumOfMeals()) {
			if (user.getPlan().getNumOfMeals() < combinationReport.getMealsList().size()) {
				combinationReport.setNumberOfBlanks(combinationReport.getNumberOfBlanks() + 1);
			} else if (user.getPlan().getNumOfMeals() > combinationReport.getMealsList().size()) {
				generatePossibleMealsForUser(combinationReport, user, mealCounter);
			}
		}
	}

	protected void replaceLowestScore(CombinationReport combinationReport, int index) {
		List<Meal> highlyRankedMeals = mealRepository.findTop3ByOrderByMealScoreDesc();
		int lowestRankedIndex = getLowestRankedMeal(combinationReport.getMealsList());
		Meal lowestRankedMeal = combinationReport.getMealsList().get(lowestRankedIndex);

		if (lowestRankedMeal.getMealScore() <= highlyRankedMeals.get(index).getMealScore()) {
			combinationReport.getMealsList().add(lowestRankedIndex, highlyRankedMeals.get(index));
			combinationReport.updateCombinationScore(lowestRankedMeal.getMealScore(), highlyRankedMeals.get(index).getMealScore());
		}
	}

	protected int getLowestRankedMeal(List<Meal> mealList) {
		
		int lowestIndex = 0;
		
		List<Integer> integers = new ArrayList<>();
		for (Meal meal : mealList) {
			integers.add(meal.getMealScore());
		}
		
		lowestIndex = integers.indexOf(Collections.min(integers));

		return lowestIndex;
		
	}

	protected boolean checkIfMealWorks(CombinationReport combinationReport, Meal meal, User user) {
		List<String> userDislikesList = user.getDislikesList();
		List<Ingredients> ingredientsToRemove = new ArrayList<>();
		Meal newMeal = meal;

		for (Ingredients ingredient : newMeal.getIngredients()) {
			if (userDislikesList.contains(ingredient.getName())) {
				if (!ingredient.isOptional()) {
					return false;
				} else {
					ingredientsToRemove.add(ingredient);
				}
			}
		}

		if (ingredientsToRemove.size() > 0) {
			newMeal = generateNewMealWithModifiedIngredients(meal, ingredientsToRemove);
			addedMeals.add(newMeal);
		}

		user.getMealList().add(newMeal);
		return true;
	}

	protected Meal generateNewMealWithModifiedIngredients(Meal meal, List<Ingredients> ingredientsToRemove) {
		Meal newMeal = new Meal(meal);
		newMeal.getIngredients().removeAll(ingredientsToRemove);
		newMeal.setMealId(UUID.randomUUID());
		return newMeal;
	}

	protected int countCombinationScore(List<Meal> meals) {
		int comboScore = 0;
		for (Meal meal : meals) {
			comboScore += meal.getMealScore();
		}
		
		return comboScore;
	}
}
