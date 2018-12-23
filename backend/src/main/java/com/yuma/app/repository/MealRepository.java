package com.yuma.app.repository;

import java.util.Optional;
import java.util.UUID;

import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.yuma.app.document.Meal;

public interface MealRepository extends MongoRepository<Meal, UUID>, QueryDslPredicateExecutor<Meal> {
	
	Optional<Meal> findByMealId(UUID id);

}
