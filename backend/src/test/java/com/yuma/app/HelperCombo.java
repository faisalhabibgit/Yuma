package com.yuma.app;

import com.yuma.app.document.CombinationReport;
import com.yuma.app.document.Consumer;
import com.yuma.app.document.Ingredients;
import com.yuma.app.document.Meal;
import com.yuma.app.document.enums.Allergens;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HelperCombo {

	public static List<Meal> prepareMealList() {

		List<Meal> meals = new ArrayList<>();
		HashSet<Allergens> allergens1 = new HashSet<>();
		HashSet<Allergens> allergens2 = new HashSet<>();
		allergens1.add(Allergens.DAIRY);
		allergens2.add(Allergens.NONE);
		Meal meal1 = new Meal();
		meal1.setName("meal1");
		List<Ingredients> ingredientsList1 = new ArrayList<>();
		Ingredients ingredients1 = new Ingredients();
		ingredients1.setName("onions");
		ingredients1.setOptional(true);
		ingredients1.setAllergens(allergens1);
		ingredientsList1.add(ingredients1);
		meal1.setIngredients(ingredientsList1);
		

		Meal meal2 = new Meal();
		meal2.setName("meal2");
		List<Ingredients> ingredientsList2 = new ArrayList<>();
		Ingredients ingredients2 = new Ingredients();
		ingredients2.setName("tomatoes");
		ingredients2.setOptional(false);
		ingredients2.setAllergens(allergens2);
		ingredientsList2.add(ingredients2);
		meal2.setIngredients(ingredientsList2);

		Meal meal3 = new Meal();
		meal3.setName("meal3");
		List<Ingredients> ingredientsList3 = new ArrayList<>();
		Ingredients ingredients3 = new Ingredients();
		ingredients3.setName("mushrooms");
		ingredients3.setOptional(false);
		ingredients3.setAllergens(allergens2);
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
		Set<Allergens> allergens1 = new HashSet<>();
		Set<Allergens> allergens2 = new HashSet<>();

		allergens1.add(Allergens.DAIRY);
		allergens2.add(Allergens.NONE);
		
		user1.setAllergies(allergens1);

		Consumer user2 = new Consumer();
		user2.setAllergies(allergens2);

		Consumer user3 = new Consumer();
		user3.setAllergies(allergens2);

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
		user.setAllergies(prepareAndReturnAllergensSet());
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

	public static Meal prepareAndReturnMealWithAllergies() {
		Meal meal = new Meal();
		meal.setName("meal name");
		meal.setIngredients(prepareAndReturnIngredientsListWithAllergies());

		return meal;
	}

	public static List<Ingredients> prepareAndReturnIngredientsList() {
		List<Ingredients> ingredients = new ArrayList<>();
		HashSet<Allergens> allergens1 = new HashSet<>();
		allergens1.add(Allergens.NONE);
		Ingredients ingredients1 = new Ingredients();
		ingredients1.setName("onions");
		ingredients1.setAllergens(allergens1);
		ingredients.add(ingredients1);
		return ingredients;
	}

	public static List<Ingredients> prepareAndReturnIngredientsListWithAllergies() {
		List<Ingredients> ingredients = new ArrayList<>();
		HashSet<Allergens> allergens1 = new HashSet<>();
		allergens1.add(Allergens.DAIRY);
		Ingredients ingredients1 = new Ingredients();
		ingredients1.setName("onions");
		ingredients1.setAllergens(allergens1);
		ingredients.add(ingredients1);
		return ingredients;
	}
	
	public static Set<Allergens> prepareAndReturnAllergensSet() {
		Set<Allergens> allergens = new HashSet<>();
		allergens.add(Allergens.DAIRY);
		return allergens;
	}

	public static CombinationReport prepareAndReturnCombinationReport() {

		List<Meal> meals = prepareMealList();
		meals.get(0).setMealScore(1);
		meals.get(1).setMealScore(2);
		meals.get(2).setMealScore(3);
		
		List<Consumer> users = prepareUserList();

		CombinationReport combinationReport = new CombinationReport();
		combinationReport.setMealsList(meals);
		combinationReport.setUserList(users);

		return combinationReport;
	}
}
