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
public class UserServiceImp implements UserServiceInt {
	private Logger userServiceLogger = LoggerFactory.getLogger(UserServiceImp.class);
	private UserRepository userRepository;
	private ConversionService conversionService;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;

	public UserServiceImp(UserRepository userRepository, ConversionService conversionService, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.conversionService = conversionService;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public User saveUser(SignUpRequest req) {
		this.userServiceLogger.info("saving user {}", req.getEmail());

		User user = conversionService.convert(req, User.class);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setActive(true);
		Optional<Role> userRole = roleRepository.findByRole("ADMIN");
		userRole.ifPresent(x -> user.setRoles(new HashSet<>(Collections.singletonList(x))));
		return userRepository.save(user);
	}

	@Override
	public List<UserTO> list() {
		this.userServiceLogger.info("fetching users list");
		List<User> userList = userRepository.findAll();
		return convertUserListToUserTOList(userList);
		}

	@Override
	public UserTO findUserByEmail(String email) {
		userServiceLogger.info("fetching user by email: %s", email);
		User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
		return conversionService.convert(user, UserTO.class); 
	}
	
	@Override
	public void deleteUserByUserID(String uuid){
		this.userServiceLogger.info("deleting user by uuid: {}", uuid);
		userRepository.delete(uuid);
	}

	@Override
	public UserTO create(UserTO userTO) {
		
		userTO.setUserId(UUID.randomUUID().toString());
		User userToCreate = conversionService.convert(userTO, User.class);
		User user = userRepository.save(userToCreate);
		return conversionService.convert(user, UserTO.class);
	}
	
	@Override
	public UserTO updateUser(UserTO userTO){
		this.userServiceLogger.info("fetching user from DB to update");
		User user = userRepository.findByUserId(userTO.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userTO.getUserId()));
		User userToUpdate = conversionService.convert(userTO, User.class);
		user.updateFrom(userToUpdate);
		User updatedUser = userRepository.save(user);
		return conversionService.convert(updatedUser, UserTO.class);
		
	}
	@Override
	public boolean existsByEmail(String email){
		return userRepository.existsByEmail(email);
	}
	
	public List<UserTO> activeUsers(){
		userServiceLogger.info("fetching users list");
		List<User> userList = userRepository.findByIsActiveIsTrue();
		
		return convertUserListToUserTOList(userList);

	}
	@Override
	 public List<UserTO> convertUserListToUserTOList(List<User> userList){
		List<UserTO> userTOS = new ArrayList<>();

		for (User consumer : userList) {
			userTOS.add(conversionService.convert(consumer, UserTO.class));
		}
		return userTOS;
	}
}
