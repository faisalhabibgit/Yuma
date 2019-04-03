package com.yuma.app;

import com.yuma.app.document.CombinationReport;
import com.yuma.app.document.Consumer;
import com.yuma.app.document.Ingredients;
import com.yuma.app.document.Meal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class HelperCombo {

	public static List<Meal> prepareMealList() {

		List<Meal> meals = new ArrayList<>();
		Meal meal1 = new Meal();
		meal1.setName("meal1");
		List<Ingredients> ingredientsList1 = new ArrayList<>();
		Ingredients ingredients1 = new Ingredients();
		ingredients1.setName("onions");
		ingredients1.setOptional(true);
		ingredientsList1.add(ingredients1);
		meal1.setIngredients(ingredientsList1);

		Meal meal2 = new Meal();
		meal2.setName("meal2");
		List<Ingredients> ingredientsList2 = new ArrayList<>();
		Ingredients ingredients2 = new Ingredients();
		ingredients2.setName("tomatoes");
		ingredients2.setOptional(false);
		ingredientsList2.add(ingredients2);
		meal2.setIngredients(ingredientsList2);

		Meal meal3 = new Meal();
		meal3.setName("meal3");
		List<Ingredients> ingredientsList3 = new ArrayList<>();
		Ingredients ingredients3 = new Ingredients();
		ingredients3.setName("mushrooms");
		ingredients3.setOptional(false);
		ingredientsList3.add(ingredients3);
		meal3.setIngredients(ingredientsList3);

		meals.add(meal1);
		meals.add(meal2);
		meals.add(meal3);

		return meals;
	}

	public static List<Consumer> prepareUserList() {

		List<Consumer> users = new ArrayList<>();
		Consumer user1 = new Consumer();
		List<String> dislikesList1 = new ArrayList<>();
		dislikesList1.add("onions");
		user1.setDislikesList(dislikesList1);

		Consumer user2 = new Consumer();
		List<String> dislikesList2 = new ArrayList<>();
		user2.setDislikesList(dislikesList2);

		Consumer user3 = new Consumer();
		List<String> dislikesList3 = new ArrayList<>();
		dislikesList3.add("tomatoes");
		user3.setDislikesList(dislikesList3);

		users.add(user1);
		users.add(user2);
		users.add(user3);

		return users;
	}

	public static Consumer prepareAndReturnUser() {

		Consumer user = Consumer.builder()
			.firstName("ahmad")
			.lastName("baiazid")
			.email("ahmad.baiz@got.com")
			.password("passs")
			.mealList(new ArrayList<>())
			.build();
		user.setDislikesList(prepareAndReturnDislikesList());
		return user;
	}

	public static HashSet<String> prepareHashsetWithOnePreference() {

		HashSet<String> flags = new HashSet<>();
		flags.add("dairy");
		return flags;
	}

	public static Meal prepareAndReturnMeal() {
		Meal meal = new Meal();
		meal.setName("meal name");
		meal.setIngredients(prepareAndReturnIngredientsList());

		return meal;
	}

	public static List<Ingredients> prepareAndReturnIngredientsList() {
		List<Ingredients> ingredients = new ArrayList<>();
		Ingredients ingredients1 = new Ingredients();
		ingredients1.setName("onions");
		ingredients.add(ingredients1);
		return ingredients;
	}

	public static List<String> prepareAndReturnDislikesList() {
		List<String> dislikes = new ArrayList<>();
		dislikes.add("onions");
		return dislikes;
	}

	public static CombinationReport prepareAndReturnCombinationReport() {

		List<Meal> meals = prepareMealList();
		meals.get(0).setMealScore(1);
		meals.get(1).setMealScore(2);
		meals.get(2).setMealScore(3);

		CombinationReport combinationReport = new CombinationReport();
		combinationReport.setMealsList(meals);

		return combinationReport;
	}
}
