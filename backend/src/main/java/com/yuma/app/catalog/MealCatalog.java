package com.yuma.app.catalog;

import java.util.ArrayList;
import java.util.HashMap;
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

	public MealCatalog(HashMap<Meal, Integer> possibleMeals, ArrayList<Meal> meals, ArrayList<Consumer> consumers) {
		this.possibleMeals = possibleMeals;
		this.meals = meals;
		this.consumers = consumers;
	}

	private static int randomIntGenerator(int upperBound) {
		Random rand = new Random();

		return rand.nextInt(upperBound);
	}

	public ArrayList<Meal> getWeeklyCombination(ArrayList<Meal> availableMeals, ArrayList<Consumer> activeConsumers) {


		return null;
	}


}
