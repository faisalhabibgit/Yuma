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
import com.yuma.app.exception.ResourceNotFoundException;
import com.yuma.app.repository.MealRepository;
import com.yuma.app.repository.UserRepository;
import com.yuma.app.to.MealTO;

@Service
public class MealServiceImp  implements  MealServiceInt{

	private Logger mealServiceLogger = LoggerFactory.getLogger(MealServiceImp.class);
	private MealRepository mealRepository;
	private UserRepository userRepository;
	private ConversionService conversionService;
	private MealCatalog mealCatalog = new MealCatalog();

	public MealServiceImp(MealRepository mealRepository, UserRepository userRepository, ConversionService conversionService) {
		this.mealRepository = mealRepository;
		this.userRepository = userRepository;
		this.conversionService = conversionService;
	}

	@Override
	public List<MealTO> list() {
		mealServiceLogger.info("fetching list of all meals in %s", MealServiceImp.class);

		List<MealTO> mealTos = convertMealToMealTO(mealRepository.findAll());
		return mealTos;
	}

	@Override
	public MealTO update(MealTO mealTo) {
		mealServiceLogger.info("updating meal with description %s, in %s", mealTo.getDescription(), MealServiceImp.class);

		Meal meal = mealRepository.findByMealId(mealTo.getMealId()).orElseThrow(() -> new ResourceNotFoundException("Meal", "mealID", mealTo.getName()));

		Meal mealToUpdate = conversionService.convert(mealTo, Meal.class);
		meal.updateFrom(mealToUpdate);
		Meal newMealCreated = mealRepository.save(meal);
		return conversionService.convert(newMealCreated, MealTO.class);
	}

	@Override
	public MealTO create(MealTO mealTo) {
		mealServiceLogger.info("creating Meal in %s", MealServiceImp.class);

		mealTo.setMealId(UUID.randomUUID());
		Meal mealToCreate = conversionService.convert(mealTo, Meal.class);
		Meal meal = mealRepository.save(mealToCreate);
		return conversionService.convert(meal, MealTO.class);
	}

	@Override
	public List<MealTO> weeklyCombo() {
		mealServiceLogger.info("generating optimal weekly combo in %s", MealServiceImp.class);

		List<MealTO> mealTOS;
		//List<Meal> mealList = mealRepository.findAll();
		List<Meal> mealList = mealRepository.findByIsAvailableIsTrue().orElseThrow(() -> new ResourceNotFoundException("Meals", "isAvailable",true));
		List<User> userList = userRepository.findByIsActiveIsTrue().orElseThrow(() -> new ResourceNotFoundException("User", "isActive", true));

		mealList = mealCatalog.getWeeklyCombination(mealList, userList);
		mealTOS = convertMealToMealTO(mealList);

		return mealTOS;

	}
	
	@Override
	public List<MealTO> availableMeals(){
		mealServiceLogger.info("retrieving available meals in %s", MealServiceImp.class);
		
		List<Meal> availableMeals = mealRepository.findByIsAvailableIsTrue().orElseThrow(() -> new ResourceNotFoundException("Meal", "isAvailable", true));
		List<MealTO> mealTOS = convertMealToMealTO(availableMeals);
		return mealTOS;

	}

	@Override
	public void deleteMeal(UUID mealId) {
		mealServiceLogger.info("deleting meal in %s", MealServiceImp.class);

		mealRepository.delete(mealRepository.findOne(mealId));
	}

	@Override
	public MealTO findByDescription(String description) {
		mealServiceLogger.info("fetching with description %s in %s", description, MealServiceImp.class);

		Optional<Meal> optionalMeal = mealRepository.findByDescription(description);
		if (!optionalMeal.isPresent()) {
			throw new IllegalArgumentException();
		} else {
			return conversionService.convert(optionalMeal.get(), MealTO.class);
		}
	}
	
	@Override
	public List<MealTO> convertMealToMealTO(List<Meal> meals){
		mealServiceLogger.info("converting Meal List to MealTO list with description %s in %s", MealServiceImp.class);

		List<MealTO> mealTos = new ArrayList<>();
		List<Meal> mealList = meals;

		for (Meal meal : mealList) {
			mealTos.add(conversionService.convert(meal, MealTO.class));
		}
		
		return mealTos;
	}
}
