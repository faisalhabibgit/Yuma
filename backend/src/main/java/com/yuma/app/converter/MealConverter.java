package com.yuma.app.converter;

import com.yuma.app.document.Meal;
import com.yuma.app.to.MealTo;
import org.springframework.core.convert.converter.Converter;

public class MealConverter implements Converter<MealTo, Meal> {

    @Override
    public Meal convert(MealTo mealTo) {
        Meal meal = new Meal();
        meal.setMealId(mealTo.getMealId());
        meal.setDescription(mealTo.getDescription());
        meal.setAvailable(mealTo.isAvailable());
        meal.setIngredients(mealTo.getIngredients());
        return meal;
    }
}
