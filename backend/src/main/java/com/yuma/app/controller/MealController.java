package com.yuma.app.controller;

import java.util.List;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yuma.app.service.MealServiceImp;
import com.yuma.app.to.MealTO;

@Slf4j
@RestController
@RequestMapping("api/meals")
public class MealController {

	final Logger logger = LoggerFactory.getLogger("meal Logger");
	private MealServiceImp mealService;

	@Autowired
	public MealController(MealServiceImp mealService) {
		this.mealService = mealService;
	}

	@GetMapping("/all")
	public List<MealTO> getAll() {
		logger.info("retrieving meals list from DB");

		return this.mealService.list();
	}

	@GetMapping("/availablemeals")
	public List<MealTO> getAvailableMeals() {
		logger.info("retrieving available meals list from DB");

		return this.mealService.availableMeals();
	}

	@GetMapping("/{description}")
	public MealTO getByDescription(@PathVariable String description) {
		logger.info("retrieving meals list from DB by description {}", description);

		return this.mealService.findByDescription(description);
	}

	@GetMapping("/weeklycombo")
	public List<MealTO> getWeeklyCombo() {
		logger.info("retrieving optimal weekly Combo");

		return this.mealService.weeklyCombo();
	}

	@PutMapping("/update")
	public MealTO update(@RequestBody MealTO mealTO) {
		logger.info("updating meal into the database");

		return this.mealService.update(mealTO);
	}

	@RequestMapping(method = RequestMethod.POST)
	public MealTO create(@RequestBody MealTO mealTO) {
		logger.info("creating meal into the database");
		return this.mealService.create(mealTO);
	}

	@DeleteMapping("/{mealId}")
	public void deleteMeal(@PathVariable UUID mealId) {
		logger.info("deleting meal with mealId {}", mealId);

		this.mealService.deleteMeal(mealId);
	}
}
