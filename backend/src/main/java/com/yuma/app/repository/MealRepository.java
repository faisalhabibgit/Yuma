package com.yuma.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.yuma.app.document.Meal;

public interface MealRepository extends MongoRepository<Meal, String> {
}
