package com.yuma.app.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

import com.yuma.app.document.Ingredients;
import com.yuma.app.document.Meal;
import com.yuma.app.repository.MealRepository;
import com.yuma.app.to.MealTO;
@RunWith(SpringRunner.class)
@SpringBootTest
public class MealServiceTest {

	@MockBean
	private MealRepository mealRepository;

	@Mock
	private ConversionService conversionService;

	@Autowired
	private MealServiceImp mealService;

	private Meal meal1;
	private Meal meal2;
	private Meal meal3;
	private MealTO mealTO;
	private MealTO mealTO2;

	@Before
	public void setUp() throws Exception {

		ArrayList<Ingredients> ingredientsList1 = new ArrayList<>();
		ArrayList<Ingredients> ingredientsList2 = new ArrayList<>();
		ArrayList<Ingredients> ingredientsList3 = new ArrayList<>();
		ArrayList<Ingredients> ingredientsList4 = new ArrayList<>();
		ArrayList<Ingredients> ingredientsList5 = new ArrayList<>();
		//bogus ingredients
		Ingredients chickpeas = new Ingredients("chickpeas", 123.343, 0.002, 59.00, false);
		Ingredients cheese = new Ingredients("cheese", 3.343, 0.02, 58.00, false);
		Ingredients chicken = new Ingredients("chicken", 13.343, 0.2, 57.00, false);
		Ingredients brocolie = new Ingredients("brocolie", 1.343, 0.000234, 5.00, false);
		Ingredients beef = new Ingredients("beef", 1.343, 0.000234, 5.00, false);
		Ingredients rice = new Ingredients("rice", 1.343, 0.000234, 5.00, false);
		Ingredients tomato = new Ingredients("tomato", 1.343, 0.000234, 5.00, false);
		Ingredients raisin = new Ingredients("raisin", 1.343, 0.000234, 5.00, false);
		Ingredients banana = new Ingredients("banana", 1.343, 0.000234, 5.00, false);
		Ingredients fish = new Ingredients("fish", 1.343, 0.000234, 5.00, false);
		Ingredients potato = new Ingredients("potato", 1.343, 0.000234, 5.00, false);
		Ingredients nuts = new Ingredients("nuts", 1.343, 0.000234, 5.00, false);
		Ingredients lamb = new Ingredients("lamb", 1.343, 0.000234, 5.00, false);
		Ingredients nutsOptional = new Ingredients("nuts", 1.343, 0.000234, 5.00, true);
		Ingredients onionOptional = new Ingredients("onion", 1.343, 0.000234, 5.00, true);
		Ingredients chickpeasOptional = new Ingredients("chickpeas", 1.343, 0.000234, 5.00, true);
		Ingredients pork = new Ingredients("pork", 1.343, 0.000234, 5.00, true);
		Ingredients milk = new Ingredients("milk", 1.343, 0.000234, 5.00, false);


		ingredientsList1.add(chickpeas);
		ingredientsList1.add(cheese);
		ingredientsList1.add(chicken);
		ingredientsList1.add(brocolie);

		ingredientsList2.add(beef);
		ingredientsList2.add(rice);
		ingredientsList2.add(tomato);
		ingredientsList2.add(raisin);
		ingredientsList2.add(banana);

		ingredientsList3.add(fish);
		ingredientsList3.add(rice);
		ingredientsList3.add(potato);
		ingredientsList3.add(nuts);

		ingredientsList4.add(lamb);
		ingredientsList4.add(nutsOptional);
		ingredientsList4.add(onionOptional);
		ingredientsList4.add(chickpeasOptional);
		ingredientsList4.add(banana);

		ingredientsList5.add(pork);
		ingredientsList5.add(potato);
		ingredientsList5.add(milk);
		ingredientsList5.add(chicken);

		meal1 = new Meal(UUID.randomUUID(), "chick broc", "chicken and broccolie topped with a touch of love", true, new HashSet<>(),ingredientsList1);
		meal2 = new Meal(UUID.randomUUID(), "beef and rice", "beef and rice topped with a touch of love", true, new HashSet<>(), ingredientsList2);
		meal3 = new Meal(UUID.randomUUID(), "fish and rice", "fish and rice topped with a touch of love", true, new HashSet<>(), ingredientsList3);
		mealTO = new MealTO(meal1.getMealId(), "chicken and veg", "chicken and veg",false,new HashSet<>(), new ArrayList<>());
		mealTO2 = new MealTO(meal2.getMealId(), "chick broc", "chicken and broccolie topped with a touch of love",true,new HashSet<>(), new ArrayList<>());


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

	@Test
	public void MealServiceListByPredicateTest(){

		String desc = "chicken";
		Optional<Meal> optionalMeal = Optional.ofNullable(new Meal());

		Mockito.when(mealRepository.findByDescription(desc)).thenReturn(optionalMeal);
		Mockito.when(mealRepository.findByDescription(desc)).thenReturn(optionalMeal);

		MealTO expectedMeals = mealService.findByDescription(desc);

		Assert.assertNotNull(expectedMeals);


	}

	@Test 
	public void MealServiceUpdateTest(){

		UUID uuid = mealTO.getMealId();

		Optional<Meal> meal = Optional.of(meal1);

		Mockito.when(mealRepository.findByMealId(uuid)).thenReturn(meal);
		Mockito.when(mealRepository.save(meal1)).thenReturn(meal1);

		mealTO = mealService.update(mealTO);

		Assert.assertEquals(mealTO.getDescription(), meal1.getDescription());
		Assert.assertEquals(mealTO.isAvailable(), meal1.isAvailable());
	}

	@Test
	public void MealServiceCreateTest(){

		Mockito.when(conversionService.convert(mealTO, Meal.class)).thenReturn(meal1);
		Mockito.when(mealRepository.save(meal1)).thenReturn(meal1);
		Mockito.when(conversionService.convert(meal1, MealTO.class)).thenReturn(mealTO);

		mealService.create(mealTO);

		Assert.assertEquals(mealTO.getDescription(), "chicken and veg");
		Assert.assertEquals(mealTO.isAvailable(), false);
	}

	@Test
	public void MealServiceAvailableTest(){

		Mockito.when(conversionService.convert(mealTO, Meal.class)).thenReturn(meal2);
		Mockito.when(mealRepository.save(meal2)).thenReturn(meal2);
		Mockito.when(conversionService.convert(meal2, MealTO.class)).thenReturn(mealTO2);

		mealService.create(mealTO2);

		Assert.assertEquals(mealTO2.getDescription(), "chicken and broccolie topped with a touch of love");
		Assert.assertEquals(mealTO2.isAvailable(), true);
	}

}
