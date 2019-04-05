package com.yuma.app.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.yuma.app.document.Consumer;
import com.yuma.app.document.Ingredients;
import com.yuma.app.document.Meal;
import com.yuma.app.repository.CombinationReportRepository;
import com.yuma.app.service.CombinationReportService;
import com.yuma.app.util.WriteCsvToResponse;

@RunWith(MockitoJUnitRunner.class)
public class CombinationReportControllerTest {

	@Mock
	CombinationReportService combinationReportService;

	@Mock
	HttpServletResponse httpServletResponse;

	@Mock
	CombinationReportRepository combinationReportRepository;
	
	@Captor
	private ArgumentCaptor<WriteCsvToResponse> writeCsvToResponseArgumentCaptor;

	CombinationReportController combinationReportController;
	
	@Before
	public void init(){
		initMocks(this);
		combinationReportController = new CombinationReportController(combinationReportService);
	}
	
	@Test
	public void testGetWeeklyCombo() {
		combinationReportController.getWeeklyCombo();
		verify(combinationReportService).generateWeeklyCombination();
	}
	
	@Test
	public void testChosenCombo() {
		int index = 1;
		
		combinationReportController.chosenCombo(index);
		verify(combinationReportService).saveCombinationReport(index);
	}
	
	@Test
	public void testCombinationReportListingInDateRange() {
		
		LocalDate startDate = new LocalDate();
		LocalDate endDate = new LocalDate();
		
		when(combinationReportService.listCombinationReportByDate(startDate, endDate)).thenReturn(new ArrayList<>());
		combinationReportController.getCombinationReportByDate(startDate,  endDate);
		
		verify(combinationReportService).listCombinationReportByDate(startDate, endDate);
	}

	private List<Consumer> prepareList() {
		
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

		ArrayList<Consumer> consumers = new ArrayList<>();
		Consumer consumer1 = Consumer.builder().firstName("ahmad").lastName("baiazid").mealList(meals).build();
		Consumer consumer2 = Consumer.builder().firstName("ali").lastName("express").mealList(meals).build();


		consumers.add(consumer1);
		consumers.add(consumer2);

		return consumers;
	}
}
