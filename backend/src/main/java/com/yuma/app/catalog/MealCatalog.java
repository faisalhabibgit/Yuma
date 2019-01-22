package com.yuma.app.catalog;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
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

@Slf4j
@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MealCatalog {
	private HashMap<Meal, Integer> mealsMap = new HashMap<>();
	private ArrayList<Meal> meals;
	private ArrayList<User> consumers;
	private Logger logger = LoggerFactory.getLogger(MealCatalog.class);

	private static int randomIntGenerator(int upperBound) {
		Random rand = new Random();

		return rand.nextInt(upperBound);
	}

	public List<Meal> getWeeklyCombination(List<Meal> availableMeals, List<User> activeUsers) {
		List<Meal> possibleMeals = new ArrayList<>();
		mealsMap.clear();
		logger.info("generating combo meals");
		
		for (User user : activeUsers) {
			generatePossibleMealsForUser(availableMeals, user, user.getPlan().getNumOfMeals(), 0);
			
		}
		mealsMap = sortByValue(mealsMap);

		logger.info("creating meals arraylist");

		possibleMeals.addAll(mealsMap.keySet());

		//possibleMeals = filterList(possibleMeals);

		return possibleMeals;
	}

	protected boolean equals(HashSet<?> set1, HashSet<?> set2) {

		if (set1 == null || set2 == null) {
			return false;
		}

		if (set1.size() != set2.size()) {
			return false;
		}

		return set1.containsAll(set2);

	}

	protected HashMap<Meal, Integer> sortByValue(HashMap<Meal, Integer> pm) {
		// Create a list from elements of HashMap 
		logger.info("sorting hashmap");

		List<Map.Entry<Meal, Integer>> list = new LinkedList<>(pm.entrySet());

		// Sort the list
		list.sort(new Comparator<Map.Entry<Meal, Integer>>() {
			@Override
			public int compare(Map.Entry<Meal, Integer> o1, Map.Entry<Meal, Integer> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});
		
		// put data from sorted list to hashmap
		HashMap<Meal, Integer> temp = new LinkedHashMap<>();
		for (Map.Entry<Meal, Integer> aa : list) {
			temp.put(aa.getKey(), aa.getValue());
		}
		return temp;
	}

	protected void generatePossibleMeals(List<Meal> meals, User consumer) {
		logger.info("inside generate possible meals");
		int i = 0;
		while (i < 4) {
			Meal meal = meals.get(randomIntGenerator(meals.size()));
			if (equals(meal.getFlags(), consumer.getPlan().getDetails())) {
				if (mealsMap.get(meal) != null) {
					Integer mealReps = mealsMap.get(meal) + 1;
					mealsMap.put(meal, mealReps);
				} else {
					mealsMap.put(meal, 1);
				}
			}
			i++;
		}
	}
	
	protected void generatePossibleMealsForUser(List<Meal> mealList, User user, int numOfMeals, int mealCounter){
		logger.info("inside generate possible meals for that user");

		if (numOfMeals == mealCounter) {
			return;
		}
		
		for(Meal meal: mealList){
			if(checkIfMealWorks(meal, user)){
				mealCounter++;
			}
			if(mealCounter == numOfMeals) break;
		}

		if (mealCounter != numOfMeals){
			generatePossibleMealsForUser(mealList, user, numOfMeals, mealCounter);
		}
	}
	
	protected boolean checkIfMealWorks(Meal meal, User user){
		List<String> userDislikesList = user.getDislikesList();
		List<Ingredients> toRemove = new ArrayList<>();
		Meal newMeal = meal;
		
		for (Ingredients ingredient : newMeal.getIngredients()){ 
			if (userDislikesList.contains(ingredient.getName())){
				if (!ingredient.isOptional()){
					return false;
				}
				else {
					toRemove.add(ingredient);
				}
			}
		}
		if (toRemove.size() != 0){
			newMeal = new Meal(meal);
			newMeal.getIngredients().removeAll(toRemove);
			newMeal.setMealId(UUID.randomUUID());
		}
		
		if (user.getMealList() == null){
			user.setMealList(new ArrayList<>());
		}
		
		user.getMealList().add(newMeal);
		if (mealsMap.get(newMeal) != null) {
			Integer mealReps = mealsMap.get(newMeal) + 1;
			mealsMap.put(newMeal, mealReps);
		} else {
			mealsMap.put(newMeal, 1);
		}
		return true;
	}

	protected List<Meal> filterList(List<Meal> mealList) {
		List<Meal> filteredList = new ArrayList<>();

		for (int i = 0; i < 3; i++) {
			filteredList.add(mealList.get(i));
		}

		return filteredList;

	}
}
