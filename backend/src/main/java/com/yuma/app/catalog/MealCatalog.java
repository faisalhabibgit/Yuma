package com.yuma.app.catalog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.mongodb.core.mapping.Document;

import com.yuma.app.document.Consumer;
import com.yuma.app.document.Meal;

@Document
@Getter
@Setter
public class MealCatalog {
	private HashMap<Meal, Integer> possibleMeals;
	private ArrayList<Meal> meals;
	private ArrayList<Consumer> consumers;

	public MealCatalog(ArrayList<Meal> meals, ArrayList<Consumer> consumers) {
		this.possibleMeals = new HashMap<>();
		this.meals = meals;
		this.consumers = consumers;
	}

	public ArrayList<Meal> getWeeklyCombination(ArrayList<Meal> availableMeals, ArrayList<Consumer> activeConsumers) {
		for(Consumer consumer : activeConsumers){
			generatePossibleMeals(availableMeals, consumer);
		}
		
		

		return null;
	}
	
	private void generatePossibleMeals(ArrayList<Meal> meals,Consumer consumer){
		int i = 0;
		while (i<4){
			for (Meal meal : meals) {
				if (equals(meal.getFlags(), consumer.getPreferences().getDetails())){
					if (possibleMeals.get(meal) != null){
						int mealReps = possibleMeals.get(meal);
						possibleMeals.put(meal, mealReps++);
					}
					else {
						possibleMeals.put(meal, 1);
					}
				}
			}
			i++;
		}
	}

	private static int randomIntGenerator(int upperBound) {
		Random rand = new Random();

		return rand.nextInt(upperBound);
	}

	public static boolean equals(HashSet<?> set1, HashSet<?> set2){

		if(set1 == null || set2 ==null){
			return false;
		}

		if(set1.size()!=set2.size()){
			return false;
		}

		return set1.containsAll(set2);

	}
}
