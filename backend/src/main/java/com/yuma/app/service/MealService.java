package com.yuma.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;
import com.yuma.app.catalog.MealCatalog;
import com.yuma.app.document.Consumer;
import com.yuma.app.document.Meal;
import com.yuma.app.repository.ConsumersRepository;
import com.yuma.app.repository.MealRepository;
import com.yuma.app.to.MealTO;
import com.yuma.app.util.Helper;

@Service
public class MealService {

	private MealRepository mealRepository;
	private ConsumersRepository consumersRepository;
	private ConversionService conversionService;
	private MealCatalog mealCatalog = new MealCatalog();

	public MealService(MealRepository mealRepository, ConsumersRepository consumersRepository, ConversionService conversionService) {
		this.mealRepository = mealRepository;
		this.consumersRepository = consumersRepository;
		this.conversionService = conversionService;
		this.mealCatalog = mealCatalog;
	}

	public List<MealTO> listByPredicate(Predicate predicate) {


		List<MealTO> mealTos = new ArrayList<>();
		List<Meal> mealList = Helper.toMealList(mealRepository.findAll(predicate));

		for (Meal meal : mealList) {
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
	
	public List<MealTO> weeklyCombo(){
		List<MealTO> mealTOS = new ArrayList<>();
		List<Meal> mealList = mealRepository.findAll();
		List<Consumer> consumerList = consumersRepository.findAll();
		//List<ConsumerTO> consumersTOs = new ArrayList<>();
//		for (Consumer consumerTO: consumerList){
//			consumersTOs.add(conversionService.convert(consumerTO, ConsumerTO.class));
//		}
		
		mealList = mealCatalog.getWeeklyCombination(mealList, consumerList);

		for (Meal meal : mealList) {
			mealTOS.add(conversionService.convert(meal, MealTO.class));
		}
		
		return mealTOS;
		
	}
	
	public void deleteMeal(UUID mealId){
		 mealRepository.delete(mealRepository.findOne(mealId));
	}
}
