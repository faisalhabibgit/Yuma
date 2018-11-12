package com.yuma.app.resources;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;
import com.yuma.app.TO.MealTO;
import com.yuma.app.document.QMeal;
import com.yuma.app.service.MealService;

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

	@RequestMapping(method = RequestMethod.PUT)
	public MealTO update(@RequestBody MealTO mealTO) {
		logger.info("updating meal into the database");
		return this.mealService.update(mealTO);
	}
}
