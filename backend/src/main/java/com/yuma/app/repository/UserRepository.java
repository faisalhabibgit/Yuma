package com.yuma.app.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.yuma.app.document.User;

public interface UserRepository extends MongoRepository<User, String> {
	Optional<User> findByEmail(String email);
	Optional<User> findByUserId(String userID);
	Boolean existsByEmail(String email);
}
