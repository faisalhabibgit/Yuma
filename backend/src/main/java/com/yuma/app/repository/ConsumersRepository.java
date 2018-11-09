package com.yuma.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.yuma.app.document.Consumer;

public interface ConsumersRepository extends MongoRepository<Consumer, String> {

}
