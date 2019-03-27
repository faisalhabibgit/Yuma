package com.yuma.app.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
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
	private MealService mealService;

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
		Ingredients chickpeas = Ingredients.builder()
			.name("chickpeas")
			.weight(123.343)
			.calories(0.002)
			.price(59.00)
			.optional(false)
			.build(); 
		
		Ingredients cheese = Ingredients.builder()
			.name("cheese")
			.weight(123.343)
			.calories(0.002)
			.price(59.00)
			.optional(false)
			.build();
		
		Ingredients chicken = Ingredients.builder()
			.name("chicken")
			.weight(13.343)
			.calories(0.002)
			.price(57.00)
			.optional(false)
			.build();
		
		Ingredients brocolie = Ingredients.builder()
			.name("brocolie")
			.weight(1.343)
			.calories(0.002)
			.price(5.00)
			.optional(false)
			.build();
		
		
		Ingredients beef =  Ingredients.builder()
			.name("beef")
			.weight(1.343)
			.calories(0.002)
			.price(5.00)
			.optional(false)
			.build();
			
			
		Ingredients rice =  Ingredients.builder()
			.name("rice")
			.weight(1.343)
			.calories(0.002)
			.price(5.00)
			.optional(false)
			.build();
		
		Ingredients tomato =  Ingredients.builder()
			.name("tomato")
			.weight(1.343)
			.calories(0.002)
			.price(5.00)
			.optional(false)
			.build(); 
			
		Ingredients raisin =  Ingredients.builder()
			.name("raisin")
			.weight(1.343)
			.calories(0.002)
			.price(5.00)
			.optional(false)
			.build();
		Ingredients banana = Ingredients.builder()
			.name("banana")
			.weight(1.343)
			.calories(0.002)
			.price(5.00)
			.optional(false)
			.build();
		Ingredients fish =  Ingredients.builder()
			.name("fish")
			.weight(1.343)
			.calories(0.002)
			.price(5.00)
			.optional(false)
			.build();
		Ingredients potato =  Ingredients.builder()
			.name("potato")
			.weight(1.343)
			.calories(0.002)
			.price(5.00)
			.optional(false)
			.build();
		Ingredients nuts =  Ingredients.builder()
			.name("nuts")
			.weight(1.343)
			.calories(0.002)
			.price(5.00)
			.optional(false)
			.build();
		
		Ingredients lamb =  Ingredients.builder()
			.name("lamb")
			.weight(1.343)
			.calories(0.002)
			.price(5.00)
			.optional(false)
			.build();
		Ingredients nutsOptional =  Ingredients.builder()
			.name("nuts")
			.weight(1.343)
			.calories(0.002)
			.price(5.00)
			.optional(true)
			.build();
		
		Ingredients onionOptional =  Ingredients.builder()
			.name("onion")
			.weight(1.343)
			.calories(0.002)
			.price(5.00)
			.optional(true)
			.build();
		Ingredients chickpeasOptional =  Ingredients.builder()
			.name("chickpeas")
			.weight(1.343)
			.calories(0.002)
			.price(5.00)
			.optional(true)
			.build();
		Ingredients pork =  Ingredients.builder()
			.name("pork")
			.weight(1.343)
			.calories(0.002)
			.price(5.00)
			.optional(true)
			.build();
		Ingredients milk = Ingredients.builder()
			.name("milk")
			.weight(1.343)
			.calories(0.002)
			.price(5.00)
			.optional(true)
			.build();
		


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

		meal1 = Meal.builder()
			.mealId(UUID.randomUUID().toString())
			.name("chick broc")
			.description("chicken and broccolie topped with a touch of love")
			.isAvailable(true)
			.ingredients(ingredientsList1)
			.mealScore(40)
			.build();
		
		meal2 = Meal.builder()
			.mealId(UUID.randomUUID().toString())
			.name("beef and rice")
			.description("beef and rice topped with a touch of love")
			.isAvailable(true)
			.ingredients(ingredientsList2)
			.mealScore(40)
			.build(); 
			
		meal3 =  Meal.builder()
			.mealId(UUID.randomUUID().toString())
			.name("fish and rice")
			.description("fish and rice topped with a touch of love")
			.isAvailable(true)
			.ingredients(ingredientsList3)
			.mealScore(40)
			.build(); 
		
		mealTO =  MealTO.builder()
			.mealId(UUID.randomUUID().toString())
			.name("chicken and veg")
			.description("chicken and veg")
			.isAvailable(true)
			.mealScore(40)
			.build();
		
		mealTO2 = MealTO.builder()
			.mealId(meal1.getMealId())
			.name("chick broc")
			.description(" chicken and broccolie topped with a touch of love")
			.isAvailable(true)
			.mealScore(40)
			.build();

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void MealServiceListTest() {
		List<Meal> actualMeals = new ArrayList<>();
		List<MealTO> expectedMeals;
		actualMeals.add(meal1);
		actualMeals.add(meal2);
		actualMeals.add(meal3);

		Mockito.when(mealRepository.findAll()).thenReturn(actualMeals);

		expectedMeals = mealService.list();
		Assert.assertEquals(expectedMeals.size(), actualMeals.size());
	}

	@Test
	public void MealServiceListByPredicateTest(){

		String desc = "chicken";
		MealTO expectedMeals;
		Optional<Meal> optionalMeal = Optional.ofNullable(new Meal());

		Mockito.when(mealRepository.findByDescription(desc)).thenReturn(optionalMeal);
		Mockito.when(mealRepository.findByDescription(desc)).thenReturn(optionalMeal);

		expectedMeals = mealService.findByDescription(desc);
		Assert.assertNotNull(expectedMeals);
	}

	@Test 
	public void MealServiceUpdateTest(){
		String uuid = mealTO.getMealId();
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
	public void givenAvailableMeal_updateMealAvailabilityToFalse(){
		meal1.setAvailable(true);
		Mockito.when(mealRepository.findByMealId(meal1.getMealId())).thenReturn(Optional.of(meal1));
		mealService.updateAvailability(meal1.getMealId());

		ArgumentCaptor<Meal> captor = ArgumentCaptor.forClass(Meal.class);
		Mockito.verify(mealRepository).save(captor.capture());
		Assert.assertEquals(false, captor.getValue().isAvailable());
	}
}
