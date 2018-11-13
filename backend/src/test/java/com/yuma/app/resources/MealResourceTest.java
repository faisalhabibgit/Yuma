package com.yuma.app.resources;

import com.yuma.app.repository.MealRepository;
import com.yuma.app.service.MealService;
import com.yuma.app.to.MealTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MealResourceTest {

	@MockBean
	private MealService mealService;

	@Mock
	private Logger logger;
	
	@Mock
	MealRepository mealRepository;

	@Autowired
	private MealResource mealResource;

	private MealTO mealTO1;
	private MealTO mealTO1updated ;
	private MealTO mealTO2;
	

	@Before
	public void setUp() throws Exception {
		
		mealTO1 = new MealTO(new ArrayList<>(), UUID.randomUUID(), "chicken", false,new HashSet<>());
		mealTO1updated = new MealTO(new ArrayList<>(), mealTO1.getMealId(), "chicken", true,new HashSet<>());
		mealTO2 = new MealTO(new ArrayList<>(), UUID.randomUUID(), "mutton", true,new HashSet<>());
		
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void mealResourceGetAllTest() {
		List<MealTO> actualMeals = new ArrayList<>();
		actualMeals.add(mealTO1);
		actualMeals.add(mealTO2);
		Mockito.when(mealService.list()).thenReturn(actualMeals);
		List<MealTO> expectedMeals = mealResource.getAll();

		Assert.assertEquals(expectedMeals.size(), actualMeals.size());
	}

	@Test
	public void mealResourceUpdateTest() {
		
		Mockito.when(mealService.update(mealTO1)).thenReturn(mealTO1updated);
		
		MealTO mealT0actual = mealResource.update(mealTO1);
		
		Assert.assertEquals(mealTO1updated.getMealId(),mealT0actual.getMealId());
		Assert.assertEquals(mealTO1updated.getDescription(),mealT0actual.getDescription());
		Assert.assertEquals(mealTO1updated.isAvailable(),mealT0actual.isAvailable());	
		
	}
	
	
}
