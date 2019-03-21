package com.yuma.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import com.yuma.app.document.Meal;

public interface MealRepository extends MongoRepository<Meal, String>, CrudRepository<Meal, String> {
	Optional<Meal> findByDescription(String description);
	Optional<Meal> findByMealId(String mealID);
	List<Meal> findByIsAvailableIsTrue();
	List<Meal> findTop3ByOrderByMealScoreDesc();
}
