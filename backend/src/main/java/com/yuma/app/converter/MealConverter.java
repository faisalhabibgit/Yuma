package com.yuma.app.converter;

import org.springframework.core.convert.converter.Converter;

import com.yuma.app.document.Meal;
import com.yuma.app.to.MealTO;

public class MealConverter implements Converter<MealTO, Meal> {

	@Override
	public Meal convert(MealTO mealTO) {
		Meal meal = new Meal();
		meal.setMealId(mealTO.getMealId());
		meal.setDescription(mealTO.getDescription());
		meal.setAvailable(mealTO.isAvailable());
		meal.setIngredients(mealTO.getIngredients());
		meal.setFlags(mealTO.getFlags());
		return meal;
	}
}
