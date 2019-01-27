package com.yuma.app.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import com.yuma.app.document.Meal;

public interface MealRepository extends MongoRepository<Meal, UUID>, CrudRepository<Meal, UUID> {
	Optional<Meal> findByDescription(String description);
	Optional<Meal> findByMealId(UUID mealID);
	Optional<List<Meal>> findByIsAvailableIsTrue();
	List<Meal> findTop3ByOrderByMealScoreDesc();
}
