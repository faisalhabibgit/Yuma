package com.yuma.app.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.yuma.app.document.Role;
import com.yuma.app.document.User;
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
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	

	public UserService(UserRepository userRepository, ConversionService conversionService, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.conversionService = conversionService;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	public void saveUser(UserTO userTO) {
		userServiceLogger.info("saving user {}", userTO.getEmail());
		User user = conversionService.convert(userTO, User.class);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setEnabled(true);
		Optional<Role> userRole = roleRepository.findByRole("ADMIN");
		userRole.ifPresent(x -> userTO.setRoles(new HashSet<>(Collections.singletonList(x))));
		user.setUserId(UUID.randomUUID());
		userRepository.save(user);
	}

	public List<UserTO> list() {
		userServiceLogger.info("fetching users list");

		List<UserTO> consumerTOS = new ArrayList<>();
		List<User> consumerList = userRepository.findAll();
		
		for (User consumer: consumerList){
			consumerTOS.add(conversionService.convert(consumer, UserTO.class));
		}
		
		return consumerTOS;
	}
	
	public Optional<User> findUserByEmail(String email) {
		userServiceLogger.info("fetching user by email: {}", email);

		return userRepository.findByEmail(email);
	}
}
