package com.yuma.app.service;

import java.util.*;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.yuma.app.document.Role;
import com.yuma.app.document.User;
import com.yuma.app.exception.ResourceNotFoundException;
import com.yuma.app.payload.SignUpRequest;
import com.yuma.app.repository.RoleRepository;
import com.yuma.app.repository.UserRepository;
import com.yuma.app.to.UserTO;

@Slf4j
@Service
public class UserService {
	private Logger userServiceLogger = LoggerFactory.getLogger("User Service");
	private UserRepository userRepository;
	private ConversionService conversionService;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository, ConversionService conversionService, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.conversionService = conversionService;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public User saveUser(SignUpRequest req) {
		this.userServiceLogger.info("saving user {}", req.getEmail());

		User user = conversionService.convert(req, User.class);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setEnabled(true);

		Optional<Role> userRole = roleRepository.findByRole("ADMIN");
		userRole.ifPresent(x -> user.setRoles(new HashSet<>(Collections.singletonList(x))));
		return userRepository.save(user);
	}

	public List<UserTO> list() {
		this.userServiceLogger.info("fetching users list");

		List<UserTO> consumerTOS = new ArrayList<>();
		List<User> consumerList = userRepository.findAll();

		for (User consumer : consumerList) {
			consumerTOS.add(conversionService.convert(consumer, UserTO.class));
		}

		return consumerTOS;
	}

	public Optional<User> findUserByEmail(String email) {
		this.userServiceLogger.info("fetching user by email: {}", email);

		return userRepository.findByEmail(email);
	}
	
	public void deleteUserByUserID(String uuid){
		this.userServiceLogger.info("deleting user by uuid: {}", uuid);
		
		userRepository.delete(uuid);
	}

	public UserTO create(UserTO userTO) {

		userTO.setUserId(UUID.randomUUID().toString());
		User userToCreate = conversionService.convert(userTO, User.class);
		User user = userRepository.save(userToCreate);
		return conversionService.convert(user, UserTO.class);
	}
	
	public UserTO updateUser(UserTO userTO){
		this.userServiceLogger.info("fetching user from DB to update");
		User user = userRepository.findByUserId(userTO.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userTO.getUserId()));
		
		User userToUpdate = conversionService.convert(userTO, User.class);
		user.updateFrom(userToUpdate);
		User updatedUser = userRepository.save(user);
		return conversionService.convert(updatedUser, UserTO.class);
		
	}
	
	public boolean existsByEmail(String email){
		return userRepository.existsByEmail(email);
	}
}
