package com.yuma.app.converter;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

import com.yuma.app.to.MealTO;
import com.yuma.app.document.Ingredients;
import com.yuma.app.document.Meal;

public class MealTOConverterTest {
	@Test
	public void testMealToConverter() {
		Meal meal = prepareMeal();
		MealTOConverter mealToConverter = new MealTOConverter();
		MealTO mealTO = mealToConverter.convert(meal);
		
		Assert.assertEquals(mealTO.getMealId(), meal.getMealId());
		Assert.assertEquals(mealTO.getDescription(), meal.getDescription());
		Assert.assertEquals(mealTO.isAvailable(), meal.isAvailable());
		Assert.assertEquals(mealTO.getIngredients().size(), meal.getIngredients().size());
		Assert.assertEquals(mealTO.getMealScore(), meal.getMealScore());
	}

	private Meal prepareMeal() {
		Meal meal = new Meal();
		meal.setMealId(UUID.randomUUID().toString());
		meal.setDescription("this is a meal description");
		meal.setAvailable(false);
		meal.setIngredients(new ArrayList<Ingredients>() {
			{
				add(Ingredients.builder()
					.name("Tomatoes")
					.weight(20)
					.calories(21)
					.price(32)
					.build());
			}
		});
		meal.setMealScore(30);
		return meal;
	}
}

