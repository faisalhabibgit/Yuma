package com.yuma.app.service;

import com.yuma.app.document.User;
import com.yuma.app.payload.SignUpRequest;
import com.yuma.app.repository.RoleRepository;
import com.yuma.app.repository.UserRepository;
import com.yuma.app.to.UserTO;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public interface UserServiceImp {
	public UserService(UserRepository userRepository, ConversionService conversionService, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {


	}

	public User saveUser(SignUpRequest req) {
		
	}

	public List<UserTO> list() {
		
	}

	public UserTO findUserByEmail(String email) {
		
	}

	public void deleteUserByUserID(String uuid){
		
	}

	public UserTO create(UserTO userTO) {
		
	}

	public UserTO updateUser(UserTO userTO){
		
	}

	public boolean existsByEmail(String email){
		
	}

	public List<UserTO> activeUsers(){
		
	}
	protected List<UserTO> convertUserListToUserTOList(List<User> userList){
		
	}

	








	}
