package com.yuma.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.yuma.app.document.Meal;

import java.util.UUID;

public interface MealRepository extends MongoRepository<Meal, UUID> {

}
