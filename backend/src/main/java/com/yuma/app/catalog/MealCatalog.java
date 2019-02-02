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

import com.yuma.app.document.Consumer;
import com.yuma.app.document.Ingredients;
import com.yuma.app.document.Meal;
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
	
	public List<CombinationReport> generateWeeklyCombination(List<Meal> availableMeals, List<Consumer> activeConsumers) {
		logger.info("instantiating Combination Report in generate Weekly Combination");

		int i = 0;
		CombinationReport combinationReport;
		setMealScores(availableMeals, activeConsumers);
		combinationReport = new CombinationReport(0, countCombinationScore(availableMeals), activeConsumers, availableMeals);
		runMealCombinationAlgorithm(combinationReport);
		combinationReport.getMealsList().addAll(addedMeals);

		while (i < 2 && (combinationReport.getNumberOfBlanks() != 0)) {
			combinationReport = new CombinationReport(0, countCombinationScore(availableMeals), activeConsumers, availableMeals);
			replaceLowestScore(combinationReport, i);
			runMealCombinationAlgorithm(combinationReport);
			combinationReport.getMealsList().addAll(addedMeals);
			i++;
		}
		return possibleCombinations;
	}

	protected void runMealCombinationAlgorithm(CombinationReport combinationReport) {
		logger.info("running meal combo Algorithm");
		for (Consumer consumer : combinationReport.getConsumerList()) {
			generatePossibleMealsForUser(combinationReport, consumer, 0);
		}
		possibleCombinations.add(combinationReport);
	}

	protected void setMealScores(List<Meal> mealList, List<Consumer> consumerList) {
		logger.info("Setting meal scores");
		
		boolean scorable = true;
		List<String> userDislikesList;
		for (Consumer consumer : consumerList) {
			userDislikesList = consumer.getDislikesList();
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

	protected void generatePossibleMealsForUser(CombinationReport combinationReport, Consumer consumer, int mealCounter) {
		logger.info("inside generate possible meals for that consumer");
		int numOfBlanks;

		for (Meal meal : combinationReport.getMealsList()) {
			if (consumer.getMealList().size() < consumer.getPlan().getNumOfMeals()) {
				if (checkIfMealWorks(meal, consumer)) {
					mealCounter++;
				} 
			} else {
				break; 
			}
		}

		if (consumer.getPlan().getNumOfMeals() == mealCounter) {
			return;
		}
		
		if (consumer.getMealList().size() != consumer.getPlan().getNumOfMeals()) {
			if (consumer.getPlan().getNumOfMeals() > combinationReport.getMealsList().size()) {
				numOfBlanks = combinationReport.getMealsList().size() - consumer.getMealList().size();
				if (numOfBlanks > 0) {
					int timesToRun = consumer.getPlan().getNumOfMeals() - numOfBlanks - mealCounter;
					generatePossibleMealsForUserWithBlanks(combinationReport, consumer, timesToRun);
				} else {
					generatePossibleMealsForUser(combinationReport, consumer, mealCounter);
				}
			}
		}
		numOfBlanks = consumer.getPlan().getNumOfMeals() - consumer.getMealList().size();
		combinationReport.setNumberOfBlanks(combinationReport.getNumberOfBlanks() + numOfBlanks);
	}
	
	protected void generatePossibleMealsForUserWithBlanks(CombinationReport combinationReport, Consumer consumer, int timesToRun){
		int timesRan = 0;
		
		for (Meal meal : combinationReport.getMealsList()) {
			if (timesRan <= timesToRun) {
				if (consumer.getMealList().size() < consumer.getPlan().getNumOfMeals()) {
					checkIfMealWorks(meal, consumer);
				} else {
					break;
				}
				timesRan++;
			}
		}
	}

	protected void replaceLowestScore(CombinationReport combinationReport, int index) {
		logger.info("replacing lowest scored meal with a higher one");
		
		List<Meal> highlyRankedMeals = mealRepository.findTop3ByOrderByMealScoreDesc();
		int lowestRankedIndex = getLowestRankedMeal(combinationReport.getMealsList());
		Meal lowestRankedMeal = combinationReport.getMealsList().get(lowestRankedIndex);

		if (lowestRankedMeal.getMealScore() <= highlyRankedMeals.get(index).getMealScore()) {
			combinationReport.getMealsList().add(lowestRankedIndex, highlyRankedMeals.get(index));
			combinationReport.updateCombinationScore(lowestRankedMeal.getMealScore(), highlyRankedMeals.get(index).getMealScore());
		}
	}

	protected int getLowestRankedMeal(List<Meal> mealList) {
		int lowestIndex;
		List<Integer> integers = new ArrayList<>();
		for (Meal meal : mealList) {
			integers.add(meal.getMealScore());
		}
		
		lowestIndex = integers.indexOf(Collections.min(integers));

		return lowestIndex;
	}

	protected boolean checkIfMealWorks(Meal meal, Consumer consumer) {
		logger.info("checking if: " + meal.getName() + " works for " + consumer.getFirstName());
		
		List<String> userDislikesList = consumer.getDislikesList();
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
			logger.info(meal.getName() + "works for " + consumer.getFirstName() + " but some ingredients have to change");
		
			newMeal = generateNewMealWithModifiedIngredients(meal, ingredientsToRemove);
			addedMeals.add(newMeal);
		}

		consumer.getMealList().add(newMeal);
		return true;
	}

	protected Meal generateNewMealWithModifiedIngredients(Meal meal, List<Ingredients> ingredientsToRemove) {
		logger.info("generating modified meal");

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
