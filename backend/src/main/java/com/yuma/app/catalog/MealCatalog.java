package com.yuma.app.catalog;

import java.util.ArrayList;
import java.util.HashMap;

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

	public MealCatalog(HashMap<Meal, Integer> possibleMeals, ArrayList<Meal> meals, ArrayList<Consumer> consumers) {
		this.possibleMeals = possibleMeals;
		this.meals = meals;
		this.consumers = consumers;
	}

	public ArrayList<Meal> generatePossibleMealsForConsumer(Consumer consumer) {

		return null;
	}

	public ArrayList<Meal> getWeeklyCombination() {

		return null;
	}


}
