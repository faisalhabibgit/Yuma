package com.yuma.app.catalog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.yuma.app.document.Ingredients;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.yuma.app.document.Meal;
import com.yuma.app.document.Plan;
import com.yuma.app.document.User;

public class MealCatalogTest {

	private MealCatalog mealCatalog;

	@Before
	public void setup() {
		mealCatalog = new MealCatalog();
	}

	@Test
	public void givenTwoHashSetsOneDifferentWhenEqualsCalledThenShouldReturnFalse() {

		HashSet<String> flags1 = prepareHashsetWithOnePreference();
		HashSet<String> flags2 = prepareHashsetWithOnePreference();
		flags2.add("Meat");

		boolean result = mealCatalog.equals(flags1, flags2);
		

		Assert.assertEquals(result , false);
	}

	@Test
	public void givenTwoHashSetsOneNullWhenEqualsCalledThenShouldReturnFalse() {

		HashSet<String> flags1 = prepareHashsetWithOnePreference();
		HashSet<String> flags2 = null;

		boolean result = mealCatalog.equals(flags1, flags2);


		Assert.assertEquals(result , false);
	}

	@Test
	public void givenTwoHashSetsBothTheSameWhenEqualsCalledThenShouldReturnTrue() {

		HashSet<String> flags1 = prepareHashsetWithOnePreference();
		HashSet<String> flags2 = prepareHashsetWithOnePreference();

		boolean result = mealCatalog.equals(flags1, flags2);

		Assert.assertEquals(result , true);
	}

	@Test
	public void givenAHashMapUnsortedWhenSortByValueCalledThenShouldReturnSortedHashMap() {

		HashMap<Meal, Integer> pm = new HashMap<>();

		Meal meal1 = new Meal();
		meal1.setDescription("meal1 description");
		Meal meal2 = new Meal();
		meal2.setDescription("meal2 description");
		Meal meal3 = new Meal();
		meal3.setDescription("meal3 description");
		Meal meal4 = new Meal();
		meal4.setDescription("meal4 description");

		pm.put(meal1, 1);
		pm.put(meal2, 2);
		pm.put(meal3, 3);
		pm.put(meal4, 4);

		HashMap<Meal, Integer> result = mealCatalog.sortByValue(pm);

		List<Integer> integers = new ArrayList<>();
		//putting values into a list of integers so it's easy to verify the output
		Iterator it = result.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			integers.add((Integer)pair.getValue());
			it.remove();
		}

		Assert.assertEquals(integers.get(0) , new Integer(4));
		Assert.assertEquals(integers.get(1) , new Integer(3));
		Assert.assertEquals(integers.get(2) , new Integer(2));
		Assert.assertEquals(integers.get(3) , new Integer(1));
	}

	@Test
	public void givenMealListWhenFilterListCalledShouldReturnThreeMealsOnly() {

		List<Meal> mealList = new ArrayList<Meal>(){
			{
				add(new Meal());
				add(new Meal());
				add(new Meal());
				add(new Meal());
				add(new Meal());
			}
		};

		List<Meal> meals =  mealCatalog.filterList(mealList);

		Assert.assertEquals(meals.size(), 3);
	}
	
	@Test
	public void testTwoMealFlagHashSetAreEqual() {
		HashSet<String> flag1 = new HashSet<>();
		flag1.add("onion");
		HashSet<String> flag2 = new HashSet<>();
		flag2.add("onion");
		Assert.assertTrue(mealCatalog.equals(flag1, flag2));
		
	}

	@Test
	public void testTwoMealFlagHashSetAreNotEqual() {
		HashSet<String> flag1 = new HashSet<>();
		flag1.add("onion");
		HashSet<String> flag2 = new HashSet<>();
		Assert.assertFalse(mealCatalog.equals(flag1, flag2));

	}
	
	@Test
	public void givenMealAndNonMatchingUserLikesWhenCheckIfMealWorksThenShouldReturnFalse() {
		Meal meal = prepareAndReturnMeal();
		User user = prepareAndReturnUser();
		
		boolean result = mealCatalog.checkIfMealWorks(meal, user);
		
		Assert.assertFalse(result);
	}

	@Test
	public void givenMealAndMatchingUserLikesWhenCheckIfMealWorksThenShouldReturnTrue() {
		Meal meal = prepareAndReturnMeal();
		User user = prepareAndReturnUser();
		user.getDislikesList().clear();

		boolean result = mealCatalog.checkIfMealWorks(meal, user);

		Assert.assertTrue(result);
	}

	@Test
	public void givenMealWithOptionalIngredientWhenCheckIfMealWorksThenShouldReturnTrue() {
		Meal meal = prepareAndReturnMeal();
		meal.getIngredients().get(0).setOptional(true);
		User user = prepareAndReturnUser();

		boolean result = mealCatalog.checkIfMealWorks(meal, user);

		Assert.assertTrue(result);
	}

	@Test
	public void givenMealListWhenGenerateMealsThenShouldSuccessfullyGenerate() {
		int numOfMeals = 3;
		Meal meal = prepareAndReturnMeal();
		meal.getIngredients().get(0).setOptional(true);
		List<Meal> meals = new ArrayList<Meal>(){
			{
				add(meal);
			}
		};
		User user = prepareAndReturnUser();

		mealCatalog.generatePossibleMealsForUser(meals,
			user, numOfMeals, 0);

		Assert.assertTrue(mealCatalog.getMealsMap().size() == 3);
	}
	
	@Test
	public void givenMealListAndUserListWhenScoreMealCalledThenShouldReturnMealListWithScore() {
		
		List<Meal> meals = prepareMealList();
		List<User> users = prepareUserList();
		
		List<Meal> mealsReturnedWithScore = mealCatalog.scoreMeals(meals, users);
		
		Assert.assertEquals(((Meal)mealsReturnedWithScore.get(0)).getName(), "meal1");
		Assert.assertEquals(((Meal)mealsReturnedWithScore.get(1)).getName(), "meal2");
		Assert.assertEquals(((Meal)mealsReturnedWithScore.get(2)).getName(), "meal3");
		Assert.assertEquals(((Meal)mealsReturnedWithScore.get(0)).getMealScore(), 3);
		Assert.assertEquals(((Meal)mealsReturnedWithScore.get(1)).getMealScore(), 2);
		Assert.assertEquals(((Meal)mealsReturnedWithScore.get(2)).getMealScore(), 2);
	}
	
	private List<Meal> prepareMealList() {
		
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

	private List<User> prepareUserList() {
		List<User> users = new ArrayList<>();
		User user1 = new User();
		List<String> dislikesList1 = new ArrayList<>();
		dislikesList1.add("onions");
		user1.setDislikesList(dislikesList1);
		
		User user2 = new User();
		List<String> dislikesList2 = new ArrayList<>();
		user2.setDislikesList(dislikesList2);
		
		User user3 = new User();
		List<String> dislikesList3 = new ArrayList<>();
		dislikesList3.add("tomatoes");
		user3.setDislikesList(dislikesList3);

		users.add(user1);
		users.add(user2);
		users.add(user3);
		
		return users;
	}

	private User prepareAndReturnUser() {

		User user = new User("ahmad", "baiazid", "ahmad.baiz@got.com", "passs");
		user.setDislikesList(prepareAndReturnDislikesList());
		return user;
	}

	private HashSet<String> prepareHashsetWithOnePreference() {
		HashSet<String> flags = new HashSet<>();
		flags.add("dairy");
		return flags;
	}
	
	Meal prepareAndReturnMeal() {
		
		Meal meal = new Meal();
		meal.setName("meal name");
		meal.setIngredients(prepareAndReturnIngredientsList());
		
		return meal;
	}

	private List<Ingredients> prepareAndReturnIngredientsList() {

		List<Ingredients> ingredients = new ArrayList<>();
		Ingredients ingredients1 = new Ingredients();
		ingredients1.setName("onions");
		ingredients.add(ingredients1);
		
		return ingredients;
	}
	
	private List<String> prepareAndReturnDislikesList() {
		List<String> dislikes = new ArrayList<>();
		dislikes.add("onions");
		return dislikes;
	}
}
