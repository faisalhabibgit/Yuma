package com.yuma.app.service;

import com.yuma.app.document.*;
import com.yuma.app.repository.MealRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.convert.ConversionService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CombinationReportServiceTest {

	@InjectMocks
	private CombinationReportService combinationReportService;
	
	@Mock
	ConversionService conversionService;
	
	@Mock
	private MealRepository mealRepository;
	
	@Mock
	private List<Meal> addedMeals;
	
	@Mock
	List<CombinationReport> possibleCombinations;
	
	private CombinationReport combinationReport;


	@Before
	public void setup() {
		combinationReport = new CombinationReport(0, 0, new ArrayList<>(), new ArrayList<>());
	}

	@Test
	public void givenMealAndNonMatchingUserLikesWhenCheckIfMealWorksThenShouldReturnFalse() {
		
		Meal meal = prepareAndReturnMeal();
		User user = prepareAndReturnUser();
		
		boolean result = combinationReportService.checkIfMealWorks(meal, user);
		Assert.assertFalse(result);
	}

	@Test
	public void givenMealAndMatchingUserLikesWhenCheckIfMealWorksThenShouldReturnTrue() {
		
		Meal meal = prepareAndReturnMeal();
		User user = prepareAndReturnUser();
		user.getDislikesList().clear();
		combinationReport.setMealsList(prepareMealList());

		boolean result = combinationReportService.checkIfMealWorks(meal, user);

		Assert.assertTrue(result);
	}

	@Test
	public void givenMealWithOptionalIngredientWhenCheckIfMealWorksThenShouldReturnTrue() {
		
		Meal meal = prepareAndReturnMeal();
		meal.getIngredients().get(0).setOptional(true);
		User user = prepareAndReturnUser();
		boolean result = combinationReportService.checkIfMealWorks(meal, user);

		Assert.assertTrue(result);
	}

	@Test
	public void givenMealListAndUserListWhenScoreMealCalledThenShouldReturnMealListWithScore() {
		
		List<Meal> meals = prepareMealList();
		List<User> users = prepareUserList();

		combinationReportService.setMealScores(meals, users);

		Assert.assertEquals(((Meal)meals.get(0)).getName(), "meal1");
		Assert.assertEquals(((Meal)meals.get(1)).getName(), "meal2");
		Assert.assertEquals(((Meal)meals.get(2)).getName(), "meal3");
		Assert.assertEquals(((Meal)meals.get(0)).getMealScore(), 3);
		Assert.assertEquals(((Meal)meals.get(1)).getMealScore(), 2);
		Assert.assertEquals(((Meal)meals.get(2)).getMealScore(), 2);
	}
	
	@Test
	public void givenMealsWithScoresWhenCountCombinationScoreThenShouldReturnTotalScore() {
		
		List<Meal> meals = prepareMealList();
		meals.get(0).setMealScore(2);
		meals.get(1).setMealScore(2);
		meals.get(2).setMealScore(3);
		
		int score = combinationReportService.countCombinationScore(meals);
		
		Assert.assertEquals(score, 7);
	}
	
	@Test
	public void testGenerateNewMealWithModifiedIngredients() {
		
		List<Ingredients> ingredients = prepareAndReturnIngredientsList();
		Meal mealWithOnions = prepareAndReturnMeal();
		
		Meal resultMeal = combinationReportService.generateNewMealWithModifiedIngredients(mealWithOnions, mealWithOnions.getIngredients());
		
		//fix me
		Assert.assertTrue(resultMeal.getIngredients().isEmpty());
	}
	
//	@Test
//	public void testGetLowestRankedMeal() {
//		
//		List<Meal> meals = prepareMealList();
//		meals.get(2).setMealScore(1);
//		meals.get(1).setMealScore(2);
//		meals.get(0).setMealScore(3);
//		
//		int result = combinationReportService.getLowestRankedMeal(meals);
//		
//		Assert.assertEquals(result, 2);
//		
//	}
	
//	@Test
//	public void testReplaceLowestScore() {
//
//		List<Meal> meals = prepareMealList();
//		meals.get(0).setMealScore(1);
//		meals.get(1).setMealScore(2);
//		meals.get(2).setMealScore(3);
//
//		CombinationReport combinationReport = prepareAndReturnCombinationReport();
//
//		when(mealRepository.findTop3ByOrderByMealScoreDesc()).thenReturn(meals);
//
//		combinationReportService.replaceLowestScore(combinationReport, 1);
//		
//		Assert.assertEquals(combinationReport.getMealsList().get(0).getMealScore(), 2);
//	}
	
	@Test
	public void testGeneratePossibleMealsForUser() {
		
		CombinationReport combinationReport = new CombinationReport();
		combinationReport.setMealsList(prepareMealList());
		User user = new User();
		user.setDislikesList(new ArrayList<>());
		Plan plan = new Plan();
		plan.setNumOfMeals(4);
		user.setPlan(plan);

		combinationReportService.generatePossibleMealsForUser(combinationReport, user, 0);
		
		Assert.assertEquals(user.getMealList().size(), 4);
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
		
		User user = User.builder()
			.firstName("ahmad")
			.lastName("baiazid")
			.email("ahmad.baiz@got.com")
			.password("passs")
			.mealList(new ArrayList<>())
			.build();
		user.setDislikesList(prepareAndReturnDislikesList());
		return user;
	}

	private HashSet<String> prepareHashsetWithOnePreference() {
		
		HashSet<String> flags = new HashSet<>();
		flags.add("dairy");
		return flags;
	}

	private Meal prepareAndReturnMeal() {
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
	
	private CombinationReport prepareAndReturnCombinationReport() {
		
		List<Meal> meals = prepareMealList();
		meals.get(0).setMealScore(1);
		meals.get(1).setMealScore(2);
		meals.get(2).setMealScore(3);
		
		CombinationReport combinationReport = new CombinationReport();
		combinationReport.setMealsList(meals);
		
		return combinationReport;
	}
}
