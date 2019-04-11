package com.yuma.app.service.HelperCombo;

import com.yuma.app.document.*;
import com.yuma.app.service.CombinationReportService;
import com.yuma.app.document.WeeklyCombination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class CombinationReportHelper {
	
	private Logger logger = LoggerFactory.getLogger(CombinationReportService.class);

	public void runMealCombinationAlgorithm(WeeklyCombination weeklyCombination, List<Meal> addedMeals) {
		logger.info("running meal combo Algorithm");
		for (Consumer user : weeklyCombination.getCombinationReport().getUserList()) {
			generatePossibleMealsForUser(weeklyCombination.getCombinationReport(), user, 0, addedMeals);
		}
		weeklyCombination.getPossibleCombinations().add(weeklyCombination.getCombinationReport());
	}

	public void setMealScores(List<Meal> mealList, List<Consumer> userList) {
		logger.info("Setting meal scores");

		boolean scorable = true;
		List<String> userDislikesList;
		for (Consumer user : userList) {
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

	public void generatePossibleMealsForUser(CombinationReport combinationReport, Consumer user, int mealCounter, List<Meal> addedMeals) {
		logger.info("inside generate possible meals for that user");
		int numOfBlanks;

		for (Meal meal : combinationReport.getMealsList()) {
			if (user.getMealList().size() < user.getPlan().getNumOfMeals()) {
				if (checkIfMealWorks(meal, user, addedMeals)) {
					mealCounter++;
				}
			} else {
				break;
			}	
		}

		if (user.getPlan().getNumOfMeals() == mealCounter) {
			return;
		}

		if (user.getMealList().size() != user.getPlan().getNumOfMeals()) {
			if (user.getPlan().getNumOfMeals() > combinationReport.getMealsList().size()) {
				numOfBlanks = combinationReport.getMealsList().size() - user.getMealList().size();
				if (numOfBlanks > 0) {
					int timesToRun = user.getPlan().getNumOfMeals() - numOfBlanks - mealCounter;
					generatePossibleMealsForUserWithBlanks(combinationReport, user, timesToRun, addedMeals);
				} else {
					generatePossibleMealsForUser(combinationReport, user, mealCounter, addedMeals);
				}
			}
		}
		numOfBlanks = user.getPlan().getNumOfMeals() - user.getMealList().size();
		combinationReport.setNumberOfBlanks(combinationReport.getNumberOfBlanks() + numOfBlanks);
	}

	public void generatePossibleMealsForUserWithBlanks(CombinationReport combinationReport, Consumer user, int timesToRun, List<Meal> addedMeals) {
		int timesRan = 0;

		for (Meal meal : combinationReport.getMealsList()) {
			if (timesRan <= timesToRun) {
				if (user.getMealList().size() < user.getPlan().getNumOfMeals()) {
					checkIfMealWorks(meal, user, addedMeals);
				} else {
					break;
				}
				timesRan++;
			}
		}
	}

	public void replaceLowestScore(CombinationReport combinationReport, int index, List<Meal> highlyRankedMeals) {
		logger.info("replacing lowest scored meal with a higher one");

		int lowestRankedIndex = getLowestRankedMeal(combinationReport.getMealsList());
		Meal lowestRankedMeal = combinationReport.getMealsList().get(lowestRankedIndex);

		if (lowestRankedMeal.getMealScore() <= highlyRankedMeals.get(index).getMealScore()) {
			combinationReport.getMealsList().add(lowestRankedIndex, highlyRankedMeals.get(index));
			combinationReport.updateCombinationScore(lowestRankedMeal.getMealScore(), highlyRankedMeals.get(index).getMealScore());
		}
	}

	public int getLowestRankedMeal(List<Meal> mealList) {
		int lowestIndex;
		List<Integer> integers = new ArrayList<>();
		for (Meal meal : mealList) {
			integers.add(meal.getMealScore());
		}

		lowestIndex = integers.indexOf(Collections.min(integers));

		return lowestIndex;
	}

	public boolean checkIfMealWorks(Meal meal, Consumer user, List<Meal> addedMeals) {
		logger.info("checking if: " + meal.getName() + " works for " + user.getFirstName());

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
			logger.info(meal.getName() + "works for " + user.getFirstName() + " but some ingredients have to change");

			newMeal = generateNewMealWithModifiedIngredients(meal, ingredientsToRemove);
			addedMeals.add(newMeal);
		}

		user.getMealList().add(newMeal);
		return true;
	}

	public Meal generateNewMealWithModifiedIngredients(Meal meal, List<Ingredients> ingredientsToRemove) {
		logger.info("generating modified meal");

		Meal newMeal = new Meal(meal);
		newMeal.getIngredients().removeAll(ingredientsToRemove);
		newMeal.setMealId(UUID.randomUUID().toString());
		return newMeal;
	}

	public int countCombinationScore(List<Meal> meals) {
		int comboScore = 0;
		for (Meal meal : meals) {
			comboScore += meal.getMealScore();
		}

		return comboScore;
	}
}
