package com.yuma.app.converter;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

import com.yuma.app.document.Ingredients;
import com.yuma.app.document.Meal;
import com.yuma.app.to.MealTo;

public class MealConverterTest {

	@Test
	public void testMealConverter() {

		MealTo mealTo = prepareMealTo();

		MealConverter mealConverter = new MealConverter();

		Meal meal = mealConverter.convert(mealTo);

		Assert.assertEquals(meal.getMealId(), mealTo.getMealId());
		Assert.assertEquals(meal.getDescription(), mealTo.getDescription());
		Assert.assertEquals(meal.isAvailable(), mealTo.isAvailable());
		Assert.assertEquals(meal.getIngredients().size(), mealTo.getIngredients().size());

	}

	public MealTo prepareMealTo() {
		MealTo mealTo = new MealTo();
		mealTo.setMealId(UUID.randomUUID());
		mealTo.setDescription("this is a meal to description");
		mealTo.setAvailable(true);
		mealTo.setIngredients(new ArrayList<Ingredients>() {
			{
				add(new Ingredients("Tomatoes", 30, 30, 30));
			}
		});
		return mealTo;
	}
}
