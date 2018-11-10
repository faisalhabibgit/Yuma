package com.yuma.app.converter;

import org.springframework.core.convert.converter.Converter;

import com.yuma.app.document.Meal;
import com.yuma.app.to.MealTo;

public class MealConverter implements Converter<MealTo, Meal> {

	@Override
	public Meal convert(MealTo mealTo) {
		Meal meal = new Meal();
		meal.setMealId(mealTo.getMealId());
		meal.setDescription(mealTo.getDescription());
		meal.setAvailable(mealTo.isAvailable());
		meal.setIngredients(mealTo.getIngredients());
		meal.setFlags(mealTo.getFlags());
		return meal;
	}
}
