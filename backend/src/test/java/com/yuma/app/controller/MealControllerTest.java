package com.yuma.app.controller;

import static org.mockito.MockitoAnnotations.initMocks;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.yuma.app.repository.MealRepository;
import com.yuma.app.service.MealService;
import com.yuma.app.to.MealTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MealControllerTest {

	@MockBean
	private MealRepository mealRepository;
	@MockBean
	private MealService mealService;
	@Autowired
	private MealController mealResource;

	private MealTO mealTO1;
	private MealTO mealTO1updated;
	private MealTO mealTO1Created;
	private MealTO mealTO2;

	@Before
	public void setUp(){
		mealTO1 = MealTO.builder()
			.mealId(UUID.randomUUID().toString())
			.name("chicken")
			.description("chicken")
			.isAvailable(false)
			.flags(new HashSet<>())
			.mealScore(30)
			.ingredients(new ArrayList<>())
			.build();
			
		mealTO1updated =  MealTO.builder()
			.mealId(UUID.randomUUID().toString())
			.name("chicken")
			.description("chicken")
			.isAvailable(true)
			.flags(new HashSet<>())
			.mealScore(24)
			.ingredients(new ArrayList<>())
			.build();
		
		mealTO1Created =  MealTO.builder()
			.mealId(UUID.randomUUID().toString())
			.name("chicken")
			.description("chicken")
			.isAvailable(false)
			.flags(new HashSet<>())
			.mealScore(5)
			.ingredients(new ArrayList<>())
			.build();
		
		mealTO2 =  MealTO.builder()
			.mealId(UUID.randomUUID().toString())
			.name("mutton")
			.description("mutton")
			.isAvailable(false)
			.flags(new HashSet<>())
			.mealScore(67)
			.ingredients(new ArrayList<>())
			.build();
		
		initMocks(this);
	}

	@Test
	public void mealResourceGetAllTest() {
		List<MealTO> actualMeals = new ArrayList<>();
		List<MealTO> expectedMeals;
		actualMeals.add(mealTO1);
		actualMeals.add(mealTO2);
		Mockito.when(mealService.list()).thenReturn(actualMeals);
		expectedMeals = mealResource.getAll();

		Assert.assertEquals(expectedMeals.size(), actualMeals.size());
	}

	@Test
	public void mealResourceUpdateTest() {
		Mockito.when(mealService.update(mealTO1)).thenReturn(mealTO1updated);
		MealTO mealT0actual = mealResource.update(mealTO1);

		Assert.assertEquals(mealTO1updated.getMealId(), mealT0actual.getMealId());
		Assert.assertEquals(mealTO1updated.getDescription(), mealT0actual.getDescription());
		Assert.assertEquals(mealTO1updated.isAvailable(), mealT0actual.isAvailable());
	}

	@Test
	public void mealResourceCreateTest() {
		Mockito.when(mealService.create(mealTO1)).thenReturn(mealTO1Created);
		mealResource.create(mealTO1);
		Mockito.verify(mealService).create(mealTO1);
	}
}
