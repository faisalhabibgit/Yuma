package com.yuma.app.catalog;

import com.yuma.app.document.Consumer;
import com.yuma.app.document.Meal;
import com.yuma.app.document.Preferences;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class MealCatalogTest {
	
	private MealCatalog mealCatalog;
	
	@Before
	public void setup() {
		mealCatalog = new MealCatalog();
	}

	//will find a better way to test this method since the output can't be predicted because of randomly picked meal
//	@Test
//	public void givenMealListAndConsumerWhenGeneratePossibleMealsCalledShouldReturnPossibleMeals() {
//
//		HashSet<String> flags = prepareHashsetWithOnePreference();
//		Meal meal = new Meal(new ArrayList<>(), UUID.randomUUID(), "description", true, flags);
//
//		Consumer consumer = prepareAConsumer();
//		
//		List<Meal> mealList = new ArrayList<Meal>() {
//			{
//				add(meal);
//				add(new Meal());
//			}
//		};
//
//		mealCatalog.generatePossibleMeals(mealList, consumer);
//
//		Assert.assertEquals(mealCatalog.getMealsMap().get(meal), new Integer(1));
//	}

	@Test
	public void givenTwoHashSetsOneDifferentWhenEqualsCalledThenShouldReturnFalse() {

		HashSet<String> flags1 = prepareHashsetWithOnePreference();
		HashSet<String> flags2 = prepareHashsetWithOnePreference();
		flags2.add("Meat");

		boolean result = mealCatalog.equals(flags1, flags2);
		

		Assert.assertEquals(result , false);
	}

	@Test
	public void givenTwoHashSetsOneNullWhenEqualsCalledThenShouldReturnFalse() {

		HashSet<String> flags1 = prepareHashsetWithOnePreference();
		HashSet<String> flags2 = null;

		boolean result = mealCatalog.equals(flags1, flags2);


		Assert.assertEquals(result , false);
	}

	@Test
	public void givenTwoHashSetsBothTheSameWhenEqualsCalledThenShouldReturnTrue() {

		HashSet<String> flags1 = prepareHashsetWithOnePreference();
		HashSet<String> flags2 = prepareHashsetWithOnePreference();

		boolean result = mealCatalog.equals(flags1, flags2);
		
		Assert.assertEquals(result , true);
	}

	@Test
	public void givenAHashMapUnsortedWhenSortByValueCalledThenShouldReturnSortedHashMap() {

		HashMap<Meal, Integer> pm = new HashMap<>();
		
		Meal meal1 = new Meal();
		meal1.setDescription("meal1 description");
		Meal meal2 = new Meal();
		meal2.setDescription("meal2 description");
		Meal meal3 = new Meal();
		meal3.setDescription("meal3 description");
		Meal meal4 = new Meal();
		meal4.setDescription("meal4 description");
		
		pm.put(meal1, 1);
		pm.put(meal2, 2);
		pm.put(meal3, 3);
		pm.put(meal4, 4);

		HashMap<Meal, Integer> result = mealCatalog.sortByValue(pm);
		
		List<Integer> integers = new ArrayList<>();

		//putting values into a list of integers so it's easy to verify the output
		Iterator it = result.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			integers.add((Integer)pair.getValue());
			it.remove();
		}

		Assert.assertEquals(integers.get(0) , new Integer(4));
		Assert.assertEquals(integers.get(1) , new Integer(3));
		Assert.assertEquals(integers.get(2) , new Integer(2));
		Assert.assertEquals(integers.get(3) , new Integer(1));
	}
	
	@Test
	public void givenMealListWhenFilterListCalledShouldReturnThreeMealsOnly() {
		
		List<Meal> mealList = new ArrayList<Meal>(){
			{
				add(new Meal());
				add(new Meal());
				add(new Meal());
				add(new Meal());
				add(new Meal());
			}
		};
		
		List<Meal> meals =  mealCatalog.filterList(mealList);

		Assert.assertEquals(meals.size(), 3);
	}
	
	private Consumer prepareAConsumer() {

		Preferences preferences = new Preferences();
		preferences.setNumOfMeals(1);
		preferences.setDetails(prepareHashsetWithOnePreference());
		Consumer consumer = new Consumer(UUID.randomUUID(), "Jack", "Sparrow",
			"jacksparrow@caribbean.com", "workemail@gmail.com", preferences, "");
		return consumer;
	}
	
	private HashSet<String> prepareHashsetWithOnePreference() {
		HashSet<String> flags = new HashSet<>();
		flags.add("dairy");
		return flags;
	}
	
}
