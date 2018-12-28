package com.yuma.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.yuma.app.catalog.MealCatalog;
import com.yuma.app.document.Meal;
import com.yuma.app.document.User;
import com.yuma.app.repository.MealRepository;
import com.yuma.app.repository.UserRepository;
import com.yuma.app.to.MealTO;

@Service
public class MealService {

	private Logger mealServiceLogger = LoggerFactory.getLogger("Meal Service");
	private MealRepository mealRepository;
	private UserRepository userRepository;
	private ConversionService conversionService;
	private MealCatalog mealCatalog = new MealCatalog();

	public MealService(MealRepository mealRepository, UserRepository userRepository, ConversionService conversionService) {
		this.mealRepository = mealRepository;
		this.userRepository = userRepository;
		this.conversionService = conversionService;
	}

	public List<MealTO> list() {
		mealServiceLogger.info("fetching list of meals");

		List<MealTO> mealTos = new ArrayList<>();
		List<Meal> mealList = mealRepository.findAll();

		for (Meal meal : mealList) {
			mealTos.add(conversionService.convert(meal, MealTO.class));
		}
		return mealTos;
	}

	public MealTO update(MealTO mealTo) {
		mealServiceLogger.info("updating meal with description: ", mealTo.getDescription());

		Optional<Meal> meal = mealRepository.findByMealId(mealTo.getMealId());

		if (!meal.isPresent()) {
			throw new IllegalArgumentException("Entity doesn't exist in the database");
		}

		Meal mealToUpdate = conversionService.convert(mealTo, Meal.class);
		meal.get().updateFrom(mealToUpdate);
		Meal newMealCreated = mealRepository.save(meal.get());
		return conversionService.convert(newMealCreated, MealTO.class);
	}

	public MealTO create(MealTO mealTo) {

		mealTo.setMealId(UUID.randomUUID());
		Meal mealToCreate = conversionService.convert(mealTo, Meal.class);
		Meal meal = mealRepository.save(mealToCreate);
		return conversionService.convert(meal, MealTO.class);
	}

	public List<MealTO> weeklyCombo() {
		mealServiceLogger.info("generating optimal weekly combo");

		List<MealTO> mealTOS = new ArrayList<>();
		List<Meal> mealList = mealRepository.findAll();
		List<User> consumerList = userRepository.findAll();

		mealList = mealCatalog.getWeeklyCombination(mealList, consumerList);

		for (Meal meal : mealList) {
			mealTOS.add(conversionService.convert(meal, MealTO.class));
		}

		return mealTOS;

	}

	public void deleteMeal(UUID mealId) {
		mealServiceLogger.info("service deleting meal");

		mealRepository.delete(mealRepository.findOne(mealId));
	}

	public MealTO findByDescription(String description) {
		mealServiceLogger.info("fetching meal from repo");

		Optional<Meal> optionalMeal = mealRepository.findByDescription(description);
		if (!optionalMeal.isPresent()) {
			throw new IllegalArgumentException();
		} else {
			return conversionService.convert(optionalMeal.get(), MealTO.class);
		}
	}
}
