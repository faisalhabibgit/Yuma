package com.yuma.app.service;
import com.yuma.app.document.Meal;
import com.yuma.app.repository.MealRepository;
import com.yuma.app.TO.MealTO;
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
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@RunWith(SpringRunner.class)
@SpringBootTest
public class MealServiceTest {
	
	@MockBean
	private MealRepository mealRepository;
	@Mock
	private ConversionService conversionService;
	@Autowired
	private MealService mealService;

	private Meal meal1;
	private Meal meal2;
	private Meal meal3;
	@Before
	public void setUp() throws Exception {
		meal1 = new Meal(new ArrayList<>(), UUID.randomUUID(), "chicken ", true);
		meal2 = new Meal(new ArrayList<>(), UUID.randomUUID(), "meat", false);
		meal3 = new Meal(new ArrayList<>(), UUID.randomUUID(), "mutton", true);
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void MealServiceListTest() {
		List<Meal> actualMeals = new ArrayList<>();
		actualMeals.add(meal1);
		actualMeals.add(meal2);
		actualMeals.add(meal3);
		Mockito.when(mealRepository.findAll()).thenReturn(actualMeals);
		List<MealTO> expectedMeals = mealService.list();

		Assert.assertEquals(expectedMeals.size(), actualMeals.size());
	}
}
