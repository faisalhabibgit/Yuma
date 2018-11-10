package com.yuma.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.querydsl.core.types.Predicate;
import com.yuma.app.util.Helper;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.yuma.app.document.Meal;
import com.yuma.app.repository.MealRepository;
import com.yuma.app.to.MealTo;

@Service
public class MealService {

	private MealRepository mealRepository;
	private ConversionService conversionService;

	public MealService(MealRepository mealRepository, ConversionService conversionService) {
		this.mealRepository = mealRepository;
		this.conversionService = conversionService;
	}

	public List<MealTo> listByPredicate(Predicate predicate) {

		List<MealTo> mealTos = new ArrayList<>();
		List<Meal> mealList = Helper.toMealList(mealRepository.findAll(predicate));

		for (Meal meal: mealList) {
			mealTos.add(conversionService.convert(meal, MealTo.class));
		}
		return mealTos;
	}
	
	public List<MealTo> list() {

		List<MealTo> mealTos = new ArrayList<>();
		List<Meal> mealList = mealRepository.findAll();

		for (Meal meal : mealList) {
			mealTos.add(conversionService.convert(meal, MealTo.class));
		}
		return mealTos;
	}

	public MealTo update(MealTo mealTo) {

		Meal meal = mealRepository.findOne(mealTo.getMealId());

		if (meal == null) {
			throw new IllegalArgumentException("Entity doesn't exist in the database");
		}

		Meal mealToUpdate = conversionService.convert(mealTo, Meal.class);
		meal.updateFrom(mealToUpdate);
		meal = mealRepository.save(meal);
		return conversionService.convert(meal, MealTo.class);
	}
	
	public void deleteMeal(UUID mealId){
		 mealRepository.delete(mealRepository.findOne(mealId));
	}
}
