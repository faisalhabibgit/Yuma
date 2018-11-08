package com.yuma.app.converter;

import com.yuma.app.document.Meal;
import com.yuma.app.to.MealTo;
import org.springframework.core.convert.converter.Converter;

public class MealToConverter implements Converter<Meal, MealTo> {
    @Override
    public MealTo convert(Meal meal) {
        MealTo mealTo = new MealTo();
        mealTo.setMealId(meal.getMealId());
        mealTo.setDescription(meal.getDescription());
        mealTo.setAvailable(meal.isAvailable());
        mealTo.setIngredients(meal.getIngredients());
        return mealTo;
    }
}
