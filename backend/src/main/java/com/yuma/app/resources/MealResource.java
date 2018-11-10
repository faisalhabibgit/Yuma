package com.yuma.app.resources;

import java.util.List;
import java.util.UUID;

import com.querydsl.core.types.Predicate;
import com.yuma.app.document.QMeal;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.yuma.app.service.MealService;
import com.yuma.app.to.MealTo;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/meals")
public class MealResource {

	final Logger logger = LoggerFactory.getLogger("meal Logger");
	private MealService mealService;

	@Autowired
	public MealResource(MealService mealService) {
		this.mealService = mealService;
	}

	@GetMapping
	public List<MealTo> getAll() {
		logger.info("retrieving meals list from DB");
		return this.mealService.list();
	}

	@GetMapping("/{description}")
	public List<MealTo> getByDescription(@PathVariable String description) {
		QMeal qMeal = new QMeal("meal");
		Predicate predicate = qMeal.description.eq(description);
		logger.info("retrieving meals list from DB by description {}", description);
		return this.mealService.listByPredicate(predicate);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public MealTo update(@RequestBody MealTo mealTo) {
		logger.info("updating meal into the database");
		return this.mealService.update(mealTo);
	}

	@DeleteMapping("/delete/{mealId}")
	public void deleteMeal(@PathVariable UUID mealId){
		logger.info("deleting meal with mealId {}", mealId);
		 this.mealService.deleteMeal(mealId);
	}
}
