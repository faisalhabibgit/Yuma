package com.yuma.app.converter;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

import com.yuma.app.document.Ingredients;
import com.yuma.app.document.Meal;
import com.yuma.app.TO.MealTO;

public class MealConverterTest {

	@Test
	public void testMealConverter() {

		MealTO mealTO = prepareMealTO();

		MealConverter mealConverter = new MealConverter();

		Meal meal = mealConverter.convert(mealTO);

		Assert.assertEquals(meal.getMealId(), mealTO.getMealId());
		Assert.assertEquals(meal.getDescription(), mealTO.getDescription());
		Assert.assertEquals(meal.isAvailable(), mealTO.isAvailable());
		Assert.assertEquals(meal.getIngredients().size(), mealTO.getIngredients().size());

	}

	public MealTO prepareMealTO() {
		MealTO mealTO = new MealTO();
		mealTO.setMealId(UUID.randomUUID());
		mealTO.setDescription("this is a meal TO description");
		mealTO.setAvailable(true);
		mealTO.setIngredients(new ArrayList<Ingredients>() {
			{
				add(new Ingredients("Tomatoes", 30, 30, 30));
			}
		});
		return mealTO;
	}
}
