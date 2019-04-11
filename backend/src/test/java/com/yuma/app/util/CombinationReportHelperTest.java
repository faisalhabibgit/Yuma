package com.yuma.app.util;

import com.yuma.app.HelperCombo;
import com.yuma.app.document.*;
import com.yuma.app.repository.CombinationReportRepository;
import com.yuma.app.repository.MealRepository;
import com.yuma.app.service.CombinationReportService;
import com.yuma.app.service.HelperCombo.CombinationReportHelper;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.convert.ConversionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class CombinationReportHelperTest {
	
	@InjectMocks
	private CombinationReportHelper combinationReportHelper;
	
	@Mock
	private ConversionService conversionService;

	@Mock
	private MealRepository mealRepository;

	@Mock
	private List<Meal> addedMeals;

	@Mock
	private CombinationReportRepository combinationReportRepository;

	private CombinationReport combinationReport;
	
	private CombinationReportService combinationReportService;
	
	@Before
	public void setup() {
		combinationReportService = new CombinationReportService(conversionService);
		combinationReportHelper = new CombinationReportHelper();
		combinationReport = new CombinationReport(0, 0, new ArrayList<>(), new ArrayList<>());
		initMocks(this);
	}

	@Test
	public void givenMealAndNonMatchingUserLikesWhenCheckIfMealWorksThenShouldReturnFalse() {

		Meal meal = HelperCombo.prepareAndReturnMeal();
		Consumer user = HelperCombo.prepareAndReturnUser();

		boolean result = combinationReportHelper.checkIfMealWorks(meal, user, addedMeals);
		Assert.assertFalse(result);
	}

	@Test
	public void givenMealAndMatchingUserLikesWhenCheckIfMealWorksThenShouldReturnTrue() {
		
		Meal meal = HelperCombo.prepareAndReturnMeal();
		Consumer user = HelperCombo.prepareAndReturnUser();
		user.getDislikesList().clear();
		combinationReport.setMealsList(HelperCombo.prepareMealList());

		boolean result = combinationReportHelper.checkIfMealWorks(meal, user, addedMeals);

		Assert.assertTrue(result);
	}

	@Test
	public void givenMealWithOptionalIngredientWhenCheckIfMealWorksThenShouldReturnTrue() {

		Meal meal = HelperCombo.prepareAndReturnMeal();
		meal.getIngredients().get(0).setOptional(true);
		Consumer user = HelperCombo.prepareAndReturnUser();
		boolean result = combinationReportHelper.checkIfMealWorks(meal, user, addedMeals);

		Assert.assertTrue(result);
	}

	@Test
	public void givenMealListAndUserListWhenScoreMealCalledThenShouldReturnMealListWithScore() {

		List<Meal> meals = HelperCombo.prepareMealList();
		List<Consumer> users = HelperCombo.prepareUserList();

		combinationReportHelper.setMealScores(meals, users);

		Assert.assertEquals(((Meal)meals.get(0)).getName(), "meal1");
		Assert.assertEquals(((Meal)meals.get(1)).getName(), "meal2");
		Assert.assertEquals(((Meal)meals.get(2)).getName(), "meal3");
		Assert.assertEquals(((Meal)meals.get(0)).getMealScore(), 3);
		Assert.assertEquals(((Meal)meals.get(1)).getMealScore(), 2);
		Assert.assertEquals(((Meal)meals.get(2)).getMealScore(), 2);
	}

	@Test
	public void givenMealsWithScoresWhenCountCombinationScoreThenShouldReturnTotalScore() {

		List<Meal> meals = HelperCombo.prepareMealList();
		meals.get(0).setMealScore(2);
		meals.get(1).setMealScore(2);
		meals.get(2).setMealScore(3);

		int score = combinationReportHelper.countCombinationScore(meals);

		Assert.assertEquals(score, 7);
	}

	@Test
	public void testGenerateNewMealWithModifiedIngredients() {

		List<Ingredients> ingredients = HelperCombo.prepareAndReturnIngredientsList();
		Meal mealWithOnions = HelperCombo.prepareAndReturnMeal();

		Meal resultMeal = combinationReportHelper.generateNewMealWithModifiedIngredients(mealWithOnions, mealWithOnions.getIngredients());

		//fix me
		Assert.assertTrue(resultMeal.getIngredients().isEmpty());
	}

	@Test
	public void testGetLowestRankedMeal() {

		List<Meal> meals = HelperCombo.prepareMealList();
		meals.get(2).setMealScore(1);
		meals.get(1).setMealScore(2);
		meals.get(0).setMealScore(3);

		int result = combinationReportHelper.getLowestRankedMeal(meals);

		Assert.assertEquals(result, 2);

	}

	@Test
	public void testReplaceLowestScore() {

		List<Meal> meals = HelperCombo.prepareMealList();
		meals.get(0).setMealScore(1);
		meals.get(1).setMealScore(2);
		meals.get(2).setMealScore(3);

		combinationReport.setMealsList(meals);

		when(mealRepository.findTop3ByOrderByMealScoreDesc()).thenReturn(meals);

		List<Meal> highlyRankedMeals = mealRepository.findTop3ByOrderByMealScoreDesc();

		combinationReportHelper.replaceLowestScore(combinationReport, 0, highlyRankedMeals);

		Assert.assertEquals(combinationReport.getMealsList().get(0).getMealScore(), 1);
	}

	@Test
	public void testGeneratePossibleMealsForUser() {

		CombinationReport combinationReport = new CombinationReport();
		combinationReport.setMealsList(HelperCombo.prepareMealList());
		Consumer consumer = new Consumer();
		consumer.setDislikesList(new ArrayList<>());
		Plan plan = new Plan();
		plan.setNumOfMeals(4);
		consumer.setPlan(plan);

		combinationReportHelper.generatePossibleMealsForUser(combinationReport, consumer, 0, addedMeals);

		Assert.assertEquals(consumer.getMealList().size(), 4);
	}

	@Test
	public void getReportCombinationByDateTest(){
		CombinationReport combinationReport = CombinationReport.builder().userList(HelperCombo.prepareUserList()).combinationScore(30).mealsList(HelperCombo.prepareMealList()).id(UUID.randomUUID().toString()).build();
		Optional<CombinationReport> co = Optional.of(combinationReport);
		DateTime start = new DateTime();
		DateTime end = start.plusWeeks(1);

		when(combinationReportRepository.findCombinationReportByCreatedOnBetween(start.toDate(),end.toDate())).thenReturn(co);
		Optional<CombinationReport> combinationReport1 = combinationReportRepository.findCombinationReportByCreatedOnBetween(start.toDate(), end.toDate());
		Assert.assertEquals(combinationReport1.get().getCombinationScore(),30);
	}

}
