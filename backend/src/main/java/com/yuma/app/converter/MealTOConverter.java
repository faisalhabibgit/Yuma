package com.yuma.app.converter;

import org.springframework.core.convert.converter.Converter;

import com.yuma.app.TO.MealTO;
import com.yuma.app.document.Meal;

public class MealTOConverter implements Converter<Meal, MealTO> {
	@Override
	public MealTO convert(Meal meal) {
		MealTO mealTO = new MealTO();
		mealTO.setMealId(meal.getMealId());
		mealTO.setDescription(meal.getDescription());
		mealTO.setAvailable(meal.isAvailable());
		mealTO.setIngredients(meal.getIngredients());
		return mealTO;
	}
}
