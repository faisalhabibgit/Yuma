package com.yuma.app.resources;

import java.util.List;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;
import com.yuma.app.document.QMeal;
import com.yuma.app.service.MealService;
import com.yuma.app.to.MealTO;

@Slf4j
@RestController
@RequestMapping("api/meals")
public class MealResource {

	final Logger logger = LoggerFactory.getLogger("meal Logger");
	private MealService mealService;

	@Autowired
	public MealResource(MealService mealService) {
		this.mealService = mealService;
	}

	@GetMapping
	public List<MealTO> getAll() {
		logger.info("retrieving meals list from DB");
		return this.mealService.list();
	}

	@GetMapping("/{description}")
	public List<MealTO> getByDescription(@PathVariable String description) {
		QMeal qMeal = new QMeal("meal");
		Predicate predicate = qMeal.description.eq(description);
		logger.info("retrieving meals list from DB by description {}", description);
		return this.mealService.listByPredicate(predicate);
	}

	@GetMapping("/weeklycombo")
	public List<MealTO> getWeeklyCombo() {
		return this.mealService.weeklyCombo();
	}

	@RequestMapping(method = RequestMethod.PUT)
	public MealTO update(@RequestBody MealTO mealTO) {
		logger.info("updating meal into the database");
		return this.mealService.update(mealTO);
	}

	@DeleteMapping("/delete/{mealId}")
	public void deleteMeal(@PathVariable UUID mealId) {
		logger.info("deleting meal with mealId {}", mealId);
		this.mealService.deleteMeal(mealId);
	}
}
