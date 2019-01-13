package com.yuma.app.document;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

public class MealTest {


	@Test
	public void updateFromTest() {

		Meal meal = new Meal();

		meal.updateFrom(updatedMeal());

		Assert.assertEquals(meal.getDescription(), updatedMeal().getDescription());
		Assert.assertEquals(meal.isAvailable(), updatedMeal().isAvailable());
		Assert.assertEquals(meal.getIngredients().size(), updatedMeal().getIngredients().size());

	}


	public Meal updatedMeal() {
		Meal meal = new Meal();
		meal.setDescription("chicken and vegetables");
		meal.setAvailable(true);
		meal.setIngredients(new ArrayList<Ingredients>() {
			{
				add(new Ingredients("Tomatoes", 20, 21, 32, false));
				add(new Ingredients("Peppers", 20, 40, 23, false));
			}
		});
		return meal;
	}


}
