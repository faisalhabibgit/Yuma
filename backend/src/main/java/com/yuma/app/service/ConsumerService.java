package com.yuma.app.service;

import java.util.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.yuma.app.document.Consumer;
import com.yuma.app.document.Role;
import com.yuma.app.exception.ResourceNotFoundException;
import com.yuma.app.payload.SignUpRequest;
import com.yuma.app.repository.RoleRepository;
import com.yuma.app.repository.UserRepository;
import com.yuma.app.to.UserTO;

@Slf4j
@Service
public class ConsumerService {
	private Logger userServiceLogger = LoggerFactory.getLogger(ConsumerService.class);
	private UserRepository userRepository;
	private ConversionService conversionService;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;

	public ConsumerService(UserRepository userRepository, ConversionService conversionService, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.conversionService = conversionService;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public Consumer saveUser(SignUpRequest req) {
		this.userServiceLogger.info("saving consumer {}", req.getEmail());

		Consumer consumer = conversionService.convert(req, Consumer.class);
		consumer.setPassword(passwordEncoder.encode(consumer.getPassword()));
		consumer.setActive(false);

		Optional<Role> userRole = roleRepository.findByRole("ADMIN");
		userRole.ifPresent(x -> consumer.setRoles(new HashSet<>(Collections.singletonList(x))));
		return userRepository.save(consumer);
	}

	public List<UserTO> list() {
		this.userServiceLogger.info("fetching users list");

		List<Consumer> consumerList = userRepository.findAll();
		return convertUserListToUserTOList(consumerList);
	}

	public UserTO findUserByEmail(String email) {
		userServiceLogger.info("fetching consumer by email: %s", email);
		Consumer consumer = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Consumer", "email", email));
		
		return conversionService.convert(consumer, UserTO.class);
		
	}
	
	public void deleteUserByUserID(String uuid){
		this.userServiceLogger.info("deleting user by uuid: {}", uuid);
		
		userRepository.delete(uuid);
	}
	
	public UserTO create(UserTO userTO) {
		userTO.setUserId(UUID.randomUUID().toString());
		Consumer consumerToCreate = conversionService.convert(userTO, Consumer.class);
		Consumer consumer = userRepository.save(consumerToCreate);
		return conversionService.convert(consumer, UserTO.class);
	}
	
	public UserTO updateUser(UserTO userTO){
		this.userServiceLogger.info("fetching consumer from DB to update");
		Consumer consumer = userRepository.findByUserId(userTO.getUserId()).orElseThrow(() -> new ResourceNotFoundException("Consumer", "userId", userTO.getUserId()));
		
		Consumer consumerToUpdate = conversionService.convert(userTO, Consumer.class);
		consumer.updateFrom(consumerToUpdate);
		Consumer updatedConsumer = userRepository.save(consumer);
		return conversionService.convert(updatedConsumer, UserTO.class);
		
	}
	public boolean existsByEmail(String email){
		return userRepository.existsByEmail(email);
	}
	
	public List<UserTO> activeUsers(){
		userServiceLogger.info("fetching users list");
		List<Consumer> consumerList = userRepository.findByIsActiveIsTrue();
		return convertUserListToUserTOList(consumerList);
	}
	
	protected List<UserTO> convertUserListToUserTOList(List<Consumer> consumerList){
		List<UserTO> userTOS = new ArrayList<>();

		for (Consumer consumer : consumerList) {
			userTOS.add(conversionService.convert(consumer, UserTO.class));
		}
		return userTOS;
	}
}
