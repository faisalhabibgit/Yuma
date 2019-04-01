package com.yuma.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.yuma.app.document.Consumer;

public interface UserRepository extends MongoRepository<Consumer, String> {
	Optional<Consumer> findByEmail(String email);
	Optional<Consumer> findByUserId(String userID);
	Boolean existsByEmail(String email);
	Boolean existsByCompany(String company);
	List<Consumer> findByIsActiveIsTrue();
	List<Consumer> findByCompanyIgnoreCase(String company);
	Consumer findByYumaServerId(String yumaServerId);
}
