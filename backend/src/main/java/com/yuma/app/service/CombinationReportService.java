package com.yuma.app.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.yuma.app.document.CombinationReport;
import com.yuma.app.document.Ingredients;
import com.yuma.app.document.Meal;
import com.yuma.app.document.User;
import com.yuma.app.repository.CombinationReportRepository;
import com.yuma.app.repository.MealRepository;
import com.yuma.app.repository.UserRepository;
import com.yuma.app.to.CombinationReportTO;

@Service
public class CombinationReportService {

	private Logger logger = LoggerFactory.getLogger(CombinationReportService.class);
	
	@Autowired
	private MealRepository mealRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CombinationReportRepository combinationReportRepository;
	private List<CombinationReport> possibleCombinations;
	private List<Meal> addedMeals;
	private List<Meal> availableMeals;
	private List<User> activeUsers;
	private ConversionService conversionService;

	public CombinationReportService(ConversionService conversionService) {
		this.conversionService = conversionService;
		this.addedMeals = new ArrayList<>();
		this.possibleCombinations = new ArrayList<>();
	}

	public List<CombinationReportTO> generateWeeklyCombination() {
		logger.info("instantiating Combination Report in generate Weekly Combination");

		this.availableMeals = mealRepository.findByIsAvailableIsTrue();
		this.activeUsers = userRepository.findByIsActiveIsTrue();
		int i = 0;
		CombinationReport combinationReport;
		setMealScores(availableMeals, activeUsers);
		combinationReport = new CombinationReport(0, countCombinationScore(availableMeals), activeUsers, availableMeals);
		runMealCombinationAlgorithm(combinationReport);
		combinationReport.getMealsList().addAll(addedMeals);

		while (i < 2 && (combinationReport.getNumberOfBlanks() != 0)) {
			combinationReport = new CombinationReport(0, countCombinationScore(availableMeals), activeUsers, availableMeals);
			replaceLowestScore(combinationReport, i);
			runMealCombinationAlgorithm(combinationReport);
			combinationReport.getMealsList().addAll(addedMeals);
			i++;
		}
		
		return possibleCombinations.stream().map(combinationReport1 ->
			conversionService.convert(combinationReport1, CombinationReportTO.class)).collect(Collectors.toList());
	}

	protected void runMealCombinationAlgorithm(CombinationReport combinationReport) {
		logger.info("running meal combo Algorithm");
		for (User user : combinationReport.getUserList()) {
			generatePossibleMealsForUser(combinationReport, user, 0);
		}
		possibleCombinations.add(combinationReport);
	}

	protected void setMealScores(List<Meal> mealList, List<User> userList) {
		logger.info("Setting meal scores");

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
		int numOfBlanks;

		for (Meal meal : combinationReport.getMealsList()) {
			if (user.getMealList().size() < user.getPlan().getNumOfMeals()) {
				if (checkIfMealWorks(meal, user)) {
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
					generatePossibleMealsForUserWithBlanks(combinationReport, user, timesToRun);
				} else {
					generatePossibleMealsForUser(combinationReport, user, mealCounter);
				}
			}
		}
		numOfBlanks = user.getPlan().getNumOfMeals() - user.getMealList().size();
		combinationReport.setNumberOfBlanks(combinationReport.getNumberOfBlanks() + numOfBlanks);
	}

	protected void generatePossibleMealsForUserWithBlanks(CombinationReport combinationReport, User user, int timesToRun){
		int timesRan = 0;

		for (Meal meal : combinationReport.getMealsList()) {
			if (timesRan <= timesToRun) {
				if (user.getMealList().size() < user.getPlan().getNumOfMeals()) {
					checkIfMealWorks(meal, user);
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

	protected boolean checkIfMealWorks(Meal meal, User user) {
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
	
	protected void saveCombinationReport(int i){
		this.combinationReportRepository.save(this.possibleCombinations.get(i));
	}
}
