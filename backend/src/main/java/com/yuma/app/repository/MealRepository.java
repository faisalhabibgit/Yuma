package com.yuma.app.repository;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.yuma.app.document.Meal;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface MealRepository extends MongoRepository<Meal, UUID>, QueryDslPredicateExecutor<Meal> {

}
