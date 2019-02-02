package com.yuma.app.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.yuma.app.document.Admin;

public interface AdminRepository extends MongoRepository<Admin, String> {
	Optional<Admin> findByEmail(String email);
	Boolean existsByEmail(String email);
	Optional<Admin> findByUserId(String userID);
}
