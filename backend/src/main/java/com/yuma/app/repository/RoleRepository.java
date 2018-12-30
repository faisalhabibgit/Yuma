package com.yuma.app.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.yuma.app.document.Role;

public interface RoleRepository extends MongoRepository<Role, String> {

	Optional<Role> findByRole(String role);
}
