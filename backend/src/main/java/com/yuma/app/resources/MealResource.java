package com.yuma.app.resources;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yuma.app.document.Meal;
import com.yuma.app.repository.MealRepository;

@Slf4j
@RestController
@RequestMapping("/meals")
public class MealResource {

	final Logger logger = LoggerFactory.getLogger("meal Logger");

	private MealRepository mealRepository;
	public MealResource(MealRepository mealRepository) {
		this.mealRepository = mealRepository;
	}

	@GetMapping("/get")
	public List<Meal> getAll() {
		logger.info("retrieving meals list from DB");
		return this.mealRepository.findAll();
	}
}
