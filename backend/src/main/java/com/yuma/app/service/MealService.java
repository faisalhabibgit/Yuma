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
import com.yuma.app.TO.MealTO;

@Service
public class MealService {

	private MealRepository mealRepository;
	private ConversionService conversionService;

	public MealService(MealRepository mealRepository, ConversionService conversionService) {
		this.mealRepository = mealRepository;
		this.conversionService = conversionService;
	}

	public List<MealTO> listByPredicate(Predicate predicate) {


		List<MealTO> mealTos = new ArrayList<>();
		List<Meal> mealList = Helper.toMealList(mealRepository.findAll(predicate));

		for (Meal meal: mealList) {
			mealTos.add(conversionService.convert(meal, MealTO.class));
		}
		return mealTos;
	}
	
	public List<MealTO> list() {

		List<MealTO> mealTos = new ArrayList<>();
		List<Meal> mealList = mealRepository.findAll();

		for (Meal meal : mealList) {
			mealTos.add(conversionService.convert(meal, MealTO.class));
		}
		return mealTos;
	}

	public MealTO update(MealTO mealTo) {

		Meal meal = mealRepository.findOne(mealTo.getMealId());

		if (meal == null) {
			throw new IllegalArgumentException("Entity doesn't exist in the database");
		}

		Meal mealToUpdate = conversionService.convert(mealTo, Meal.class);
		meal.updateFrom(mealToUpdate);
		meal = mealRepository.save(meal);
		return conversionService.convert(meal, MealTO.class);
	}
	
	public void deleteMeal(UUID mealId){
		 mealRepository.delete(mealRepository.findOne(mealId));
	}
}
