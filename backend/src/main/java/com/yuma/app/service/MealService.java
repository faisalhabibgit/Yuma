package com.yuma.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.yuma.app.document.Meal;
import com.yuma.app.exception.ResourceNotFoundException;
import com.yuma.app.repository.MealRepository;
import com.yuma.app.to.MealTO;

@Slf4j
@Service
public class MealService {

	private MealRepository mealRepository;
	private ConversionService conversionService;

	public MealService(MealRepository mealRepository, ConversionService conversionService) {
		this.mealRepository = mealRepository;
		this.conversionService = conversionService;
	}

	public List<MealTO> list() {
		log.info("fetching list of all meals in %s", MealService.class);
		List<MealTO> mealTos = convertMealToMealTO(mealRepository.findAll());
		return mealTos;
	}

	public MealTO update(MealTO mealTo) {
		log.info("updating meal with description %s, in %s", mealTo.getDescription(), MealService.class);
		Meal meal = mealRepository.findByMealId(mealTo.getMealId()).orElseThrow(() -> new ResourceNotFoundException("Meal", "mealID", mealTo.getName()));
		Meal mealToUpdate = conversionService.convert(mealTo, Meal.class);
		meal.updateFrom(mealToUpdate);
		Meal newMealCreated = mealRepository.save(meal);
		return conversionService.convert(newMealCreated, MealTO.class);
	}

	public MealTO create(MealTO mealTo) {
		log.info("creating Meal in %s", MealService.class);
		mealTo.setMealId(UUID.randomUUID().toString());
		Meal mealToCreate = conversionService.convert(mealTo, Meal.class);
		Meal meal = mealRepository.save(mealToCreate);
		return conversionService.convert(meal, MealTO.class);
	}
	
	public List<MealTO> availableMeals(){
		log.info("retrieving available meals in %s", MealService.class);

		List<Meal> availableMeals = mealRepository.findByIsAvailableIsTrue();
		List<MealTO> mealTOS = convertMealToMealTO(availableMeals);
		return mealTOS;
	}

	public void deleteMeal(String mealId) {
		log.info("deleting meal in %s", MealService.class);

		mealRepository.delete(mealRepository.findOne(mealId));
	}

	public MealTO findByDescription(String description) {
		log.info("fetching with description %s in %s", description, MealService.class);

		Optional<Meal> optionalMeal = mealRepository.findByDescription(description);
		if (!optionalMeal.isPresent()) {
			throw new IllegalArgumentException();
		} else {
			return conversionService.convert(optionalMeal.get(), MealTO.class);
		}
	}
	
	private List<MealTO> convertMealToMealTO(List<Meal> meals){
		log.info("converting Meal List to MealTO list with description %s in %s", MealService.class);

		List<MealTO> mealTos = new ArrayList<>();
		List<Meal> mealList = meals;

		for (Meal meal : mealList) {
			mealTos.add(conversionService.convert(meal, MealTO.class));
		}
		return mealTos;
	}

	public void updateAvailability(String mealId) {
		Meal meal = mealRepository.findByMealId(mealId).map(Meal::new).orElseThrow(() -> new ResourceNotFoundException("Meal", "mealId", mealId));
		switchAvailability(meal);
	}

	private void switchAvailability(Meal meal){
		if (meal.isAvailable()) {
			meal.setAvailable(false);
			mealRepository.save(meal);
		}
		else{
			meal.setAvailable(true);
			mealRepository.save(meal);
		}
	}
}
