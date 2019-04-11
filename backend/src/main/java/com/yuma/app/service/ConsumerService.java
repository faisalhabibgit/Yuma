package com.yuma.app.service;

import java.util.*;
import lombok.extern.slf4j.Slf4j;
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
		log.info("saving consumer {}", req.getEmail());

		Consumer consumer = conversionService.convert(req, Consumer.class);
		consumer.setPassword(passwordEncoder.encode(consumer.getPassword()));
		consumer.setActive(false);

		Optional<Role> userRole = roleRepository.findByRole("ADMIN");
		userRole.ifPresent(x -> consumer.setRoles(new HashSet<>(Collections.singletonList(x))));
		return userRepository.save(consumer);
	}

	public List<UserTO> list() {
		log.info("fetching users list");

		List<Consumer> consumerList = userRepository.findAll();
		return convertUserListToUserTOList(consumerList);
	}

	public Set<String> listUserCompanies() {
		log.info("fetching users list");
		
		Set<String> companies = new HashSet<>();
		
		List<Consumer> consumerList = userRepository.findAll();
		for (Consumer consumer : consumerList) {
			
			if(consumer.getCompany() != null) {
				companies.add(consumer.getCompany());
			}
		}
		return companies;
	}

	public UserTO findUserByEmail(String email) {
		log.info("fetching consumer by email: %s", email);
		Consumer consumer = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Consumer", "email", email));
		
		return conversionService.convert(consumer, UserTO.class);
		
	}
	
	public List<UserTO> findUsersByCompany(String company){
		log.info("fetching consumers from company: %s",company);
		List<Consumer> consumerList = userRepository.findByCompanyIgnoreCase(company);
		return convertUserListToUserTOList(consumerList);	
	}
	
	public void deleteUserByUserID(String uuid){
		log.info("deleting user by uuid: {}", uuid);
		
		userRepository.delete(uuid);
	}
	
	public UserTO create(UserTO userTO) {
		userTO.setUserId(UUID.randomUUID().toString());
		Consumer consumerToCreate = conversionService.convert(userTO, Consumer.class);
		Consumer consumer = userRepository.save(consumerToCreate);
		return conversionService.convert(consumer, UserTO.class);
	}
	
	public UserTO updateUser(UserTO userTO){
		log.info("fetching consumer from DB to update");
		Consumer consumer = userRepository.findByUserId(userTO.getUserId()).orElseThrow(() -> new ResourceNotFoundException("Consumer", "userId", userTO.getUserId()));
		
		Consumer consumerToUpdate = conversionService.convert(userTO, Consumer.class);
		consumer.updateFrom(consumerToUpdate);
		Consumer updatedConsumer = userRepository.save(consumer);
		return conversionService.convert(updatedConsumer, UserTO.class);
		
	}
	public boolean existsByEmail(String email){
		return userRepository.existsByEmail(email);
	}
	
	public boolean existsByCompany(String company){ return userRepository.existsByCompany(company);}
	
	public List<UserTO> activeUsers(){
		log.info("fetching users list");
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
