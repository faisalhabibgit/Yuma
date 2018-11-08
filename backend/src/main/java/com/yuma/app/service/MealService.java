package com.yuma.app.service;

import com.yuma.app.document.Meal;
import com.yuma.app.repository.MealRepository;
import com.yuma.app.to.MealTo;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MealService {

    private MealRepository mealRepository;
    private ConversionService conversionService;

    public MealService(MealRepository mealRepository, ConversionService conversionService) {
        this.mealRepository = mealRepository;
        this.conversionService = conversionService;
    }

    public List<MealTo> list() {

        List<MealTo> mealTos = new ArrayList<>();
        List<Meal> mealList = mealRepository.findAll();

        for (Meal meal: mealList) {
            mealTos.add(conversionService.convert(meal, MealTo.class));
        }
        return mealTos;
    }

    public MealTo update(MealTo mealTo) {

        Meal meal = mealRepository.findOne(mealTo.getMealId());

        if(meal == null) {
            throw new IllegalArgumentException("Entity doesn't exist in the database");
        }

        Meal mealToUpdate = conversionService.convert(mealTo, Meal.class);
        meal.updateFrom(mealToUpdate);
        meal = mealRepository.save(meal);
        return conversionService.convert(meal, MealTo.class);
    }
}
