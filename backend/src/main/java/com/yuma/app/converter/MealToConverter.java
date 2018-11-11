package com.yuma.app.converter;

import org.springframework.core.convert.converter.Converter;

import com.yuma.app.document.Meal;
import com.yuma.app.to.MealTO;

public class MealTOConverter implements Converter<Meal, MealTO> {
	@Override
	public MealTO convert(Meal meal) {
		MealTO mealTO = new MealTO();
		mealTO.setMealId(meal.getMealId());
		mealTO.setDescription(meal.getDescription());
		mealTO.setAvailable(meal.isAvailable());
		mealTO.setIngredients(meal.getIngredients());
		mealTO.setFlags(mealTO.getFlags());
		return mealTO;
	}
}
