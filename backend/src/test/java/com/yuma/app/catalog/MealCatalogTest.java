package com.yuma.app.catalog;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.yuma.app.document.Consumer;
import com.yuma.app.document.Ingredients;
import com.yuma.app.document.Meal;
import com.yuma.app.document.Plan;
import com.yuma.app.repository.MealRepository;

@RunWith(MockitoJUnitRunner.class)
public class MealCatalogTest {

	@InjectMocks
	private MealCatalog mealCatalog = new MealCatalog();
	
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
		Consumer consumer = prepareAndReturnUser();
		
		boolean result = mealCatalog.checkIfMealWorks(meal, consumer);
		Assert.assertFalse(result);
	}

	@Test
	public void givenMealAndMatchingUserLikesWhenCheckIfMealWorksThenShouldReturnTrue() {
		
		Meal meal = prepareAndReturnMeal();
		Consumer consumer = prepareAndReturnUser();
		consumer.getDislikesList().clear();
		combinationReport.setMealsList(prepareMealList());

		boolean result = mealCatalog.checkIfMealWorks(meal, consumer);

		Assert.assertTrue(result);
	}

	@Test
	public void givenMealWithOptionalIngredientWhenCheckIfMealWorksThenShouldReturnTrue() {
		
		Meal meal = prepareAndReturnMeal();
		meal.getIngredients().get(0).setOptional(true);
		Consumer consumer = prepareAndReturnUser();
		boolean result = mealCatalog.checkIfMealWorks(meal, consumer);

		Assert.assertTrue(result);
	}

	@Test
	public void givenMealListAndUserListWhenScoreMealCalledThenShouldReturnMealListWithScore() {
		
		List<Meal> meals = prepareMealList();
		List<Consumer> consumers = prepareUserList();

		mealCatalog.setMealScores(meals, consumers);

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
		
		int score = mealCatalog.countCombinationScore(meals);
		
		Assert.assertEquals(score, 7);
	}
	
	@Test
	public void testGenerateNewMealWithModifiedIngredients() {
		
		List<Ingredients> ingredients = prepareAndReturnIngredientsList();
		Meal mealWithOnions = prepareAndReturnMeal();
		
		Meal resultMeal = mealCatalog.generateNewMealWithModifiedIngredients(mealWithOnions, mealWithOnions.getIngredients());
		
		//fix me
		Assert.assertTrue(resultMeal.getIngredients().isEmpty());
	}
	
	@Test
	public void testGetLowestRankedMeal() {
		
		List<Meal> meals = prepareMealList();
		meals.get(2).setMealScore(1);
		meals.get(1).setMealScore(2);
		meals.get(0).setMealScore(3);
		
		int result = mealCatalog.getLowestRankedMeal(meals);
		
		Assert.assertEquals(result, 2);
		
	}
	
	@Test
	public void testReplaceLowestScore() {

		List<Meal> meals = prepareMealList();
		meals.get(0).setMealScore(1);
		meals.get(1).setMealScore(2);
		meals.get(2).setMealScore(3);

		CombinationReport combinationReport = prepareAndReturnCombinationReport();

		when(mealRepository.findTop3ByOrderByMealScoreDesc()).thenReturn(meals);

		mealCatalog.replaceLowestScore(combinationReport, 1);
		
		Assert.assertEquals(combinationReport.getMealsList().get(0).getMealScore(), 2);
	}
	
	@Test
	public void testRunMealCombinationAlgorithm() {
		CombinationReport combinationReport = new CombinationReport();
		combinationReport.setConsumerList(new ArrayList<>());
		
		mealCatalog.runMealCombinationAlgorithm(combinationReport);
		
		Assert.assertTrue(!mealCatalog.getPossibleCombinations().isEmpty());
	}
	
	@Test
	public void testGeneratePossibleMealsForUser() {
		
		CombinationReport combinationReport = new CombinationReport();
		combinationReport.setMealsList(prepareMealList());
		Consumer consumer = new Consumer();
		consumer.setDislikesList(new ArrayList<>());
		Plan plan = new Plan();
		plan.setNumOfMeals(4);
		consumer.setPlan(plan);
		
		mealCatalog.generatePossibleMealsForUser(combinationReport, consumer, 0);
		
		Assert.assertEquals(consumer.getMealList().size(), 4);
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

	private List<Consumer> prepareUserList() {
		
		List<Consumer> consumers = new ArrayList<>();
		Consumer consumer1 = new Consumer();
		List<String> dislikesList1 = new ArrayList<>();
		dislikesList1.add("onions");
		consumer1.setDislikesList(dislikesList1);

		Consumer consumer2 = new Consumer();
		List<String> dislikesList2 = new ArrayList<>();
		consumer2.setDislikesList(dislikesList2);

		Consumer consumer3 = new Consumer();
		List<String> dislikesList3 = new ArrayList<>();
		dislikesList3.add("tomatoes");
		consumer3.setDislikesList(dislikesList3);

		consumers.add(consumer1);
		consumers.add(consumer2);
		consumers.add(consumer3);

		return consumers;
	}

	private Consumer prepareAndReturnUser() {
		
		Consumer consumer = Consumer.builder()
			.firstName("ahmad")
			.lastName("baiazid")
			.email("ahmad.baiz@got.com")
			.password("passs")
			.mealList(new ArrayList<>())
			.build();
		consumer.setDislikesList(prepareAndReturnDislikesList());
		return consumer;
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
