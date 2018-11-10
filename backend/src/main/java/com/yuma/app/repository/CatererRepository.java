package com.yuma.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.yuma.app.document.Caterer;

public interface CatererRepository extends MongoRepository<Caterer, String> {

}