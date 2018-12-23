package com.yuma.app.resources;

import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yuma.app.document.User;
import com.yuma.app.service.UserService;
import com.yuma.app.to.UserTO;

@Slf4j
@RestController
@RequestMapping("user")
public class AuthenticationResource {
	
	private UserService userService;
	final Logger logger = LoggerFactory.getLogger("auth resource");


	public AuthenticationResource(UserService userService){
		this.userService = userService;
	}
	
	@PostMapping("/register")
	public HttpStatus register(@RequestBody UserTO user, BindingResult bindingResult){
		logger.info("attempting to register user with email: {}", user.getEmail());
		
		HttpStatus httpStatus;
		Optional<User> optionalUser = userService.findUserByEmail(user.getEmail());
		
		if (optionalUser.isPresent()){
			logger.info("user with email: {} already exists", user.getEmail());
			bindingResult.rejectValue("email", "error.user", "There's already a registered user with the email provided");
		}
		if (bindingResult.hasErrors()){
			httpStatus = HttpStatus.CONFLICT;
		}
		else {
			userService.saveUser(user);
			httpStatus = HttpStatus.OK;
			logger.info("user with email: {} registered successfully", user.getEmail());
		}
		
		return httpStatus;
	}
}
