package com.yuma.app.service;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.convert.ConversionService;

import com.yuma.app.document.CombinationReport;
import com.yuma.app.document.Consumer;
import com.yuma.app.document.Ingredients;
import com.yuma.app.document.Meal;
import com.yuma.app.document.Plan;
import com.yuma.app.repository.CombinationReportRepository;
import com.yuma.app.repository.MealRepository;

@RunWith(MockitoJUnitRunner.class)
public class CombinationReportServiceTest {

	private CombinationReportService combinationReportService;

	@Mock
	private ConversionService conversionService;

	@Mock
	private MealRepository mealRepository;

	@Mock
	private List<Meal> addedMeals;

	@Mock
	private List<CombinationReport> possibleCombinations;

	@Mock
	private CombinationReportRepository combinationReportRepository;

	private CombinationReport combinationReport;


	@Before
	public void setup() {
		combinationReportService = new CombinationReportService(conversionService);
		combinationReport = new CombinationReport(0, 0, new ArrayList<>(), new ArrayList<>());
		initMocks(this);
	}

	@Test
	public void givenMealAndNonMatchingUserLikesWhenCheckIfMealWorksThenShouldReturnFalse() {

		Meal meal = prepareAndReturnMeal();
		Consumer user = prepareAndReturnUser();

		boolean result = combinationReportService.checkIfMealWorks(meal, user);
		Assert.assertFalse(result);
	}

	@Test
	public void givenMealAndMatchingUserLikesWhenCheckIfMealWorksThenShouldReturnTrue() {

		Meal meal = prepareAndReturnMeal();
		Consumer user = prepareAndReturnUser();
		user.getDislikesList().clear();
		combinationReport.setMealsList(prepareMealList());

		boolean result = combinationReportService.checkIfMealWorks(meal, user);

		Assert.assertTrue(result);
	}

	@Test
	public void givenMealWithOptionalIngredientWhenCheckIfMealWorksThenShouldReturnTrue() {

		Meal meal = prepareAndReturnMeal();
		meal.getIngredients().get(0).setOptional(true);
		meal.setHealthLabels(new HashSet<>());
		meal.setProteinTypes(new HashSet<>());
		Consumer user = prepareAndReturnUser();
		boolean result = combinationReportService.checkIfMealWorks(meal, user);

		Assert.assertTrue(result);
	}

	@Test
	public void givenMealListAndUserListWhenScoreMealCalledThenShouldReturnMealListWithScore() {

		List<Meal> meals = prepareMealList();
		List<Consumer> users = prepareUserList();

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

	@Test
	public void testGetLowestRankedMeal() {

		List<Meal> meals = prepareMealList();
		meals.get(2).setMealScore(1);
		meals.get(1).setMealScore(2);
		meals.get(0).setMealScore(3);

		int result = combinationReportService.getLowestRankedMeal(meals);

		Assert.assertEquals(result, 2);

	}

	@Test
	public void testReplaceLowestScore() {

		List<Meal> meals = prepareMealList();
		meals.get(0).setMealScore(1);
		meals.get(1).setMealScore(2);
		meals.get(2).setMealScore(3);

		combinationReport.setMealsList(meals);

		when(mealRepository.findTop3ByOrderByMealScoreDesc()).thenReturn(meals);

		List<Meal> highlyRankedMeals = mealRepository.findTop3ByOrderByMealScoreDesc();

		combinationReportService.replaceLowestScore(combinationReport, 0, highlyRankedMeals);

		Assert.assertEquals(combinationReport.getMealsList().get(0).getMealScore(), 1);
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

		combinationReportService.generatePossibleMealsForUser(combinationReport, consumer, 0);

		Assert.assertEquals(consumer.getMealList().size(), 4);
	}

	@Test
	public void getReportCombinationByDateTest(){
		CombinationReport combinationReport = CombinationReport.builder().userList(prepareUserList()).combinationScore(30).mealsList(prepareMealList()).id(UUID.randomUUID().toString()).build();
		Optional<CombinationReport> co = Optional.of(combinationReport);
		DateTime start = new DateTime();
		DateTime end = start.plusWeeks(1);

		when(combinationReportRepository.findCombinationReportByCreatedOnBetween(start.toDate(),end.toDate())).thenReturn(co);
		Optional<CombinationReport> combinationReport1 = combinationReportRepository.findCombinationReportByCreatedOnBetween(start.toDate(), end.toDate());
		Assert.assertEquals(combinationReport1.get().getCombinationScore(),30);
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

	private Consumer prepareAndReturnUser() {

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
