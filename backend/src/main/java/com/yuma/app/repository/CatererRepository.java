package com.yuma.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.yuma.app.document.Caterer;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.util.UUID;

public interface CatererRepository extends MongoRepository<Caterer, UUID>, QueryDslPredicateExecutor<Caterer> {

}
