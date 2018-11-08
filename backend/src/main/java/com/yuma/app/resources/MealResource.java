package com.yuma.app.resources;

import java.util.List;

import com.yuma.app.service.MealService;
import com.yuma.app.to.MealTo;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/meals")
public class MealResource {

	private MealService mealService;

	final Logger logger = LoggerFactory.getLogger("meal Logger");

	@Autowired
	public MealResource(MealService mealService) {
		this.mealService = mealService;
	}

	@GetMapping
	public List<MealTo> getAll() {
		logger.info("retrieving meals list from DB");
		return this.mealService.list();
	}

	@RequestMapping(method = RequestMethod.PUT)
	public MealTo update(@RequestBody MealTo mealTo) {
		logger.info("updating meal into the database");
		return this.mealService.update(mealTo);
	}
}
