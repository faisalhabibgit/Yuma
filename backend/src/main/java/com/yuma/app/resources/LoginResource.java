package com.yuma.app.resources;

import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yuma.app.document.User;
import com.yuma.app.service.CustomUserDetailsService;

@Slf4j
@RestController
@RequestMapping("login")
public class LoginResource {

	@Autowired
	private CustomUserDetailsService userService;
	
	@PostMapping("/register")
	public HttpStatus register(@RequestBody User user, BindingResult bindingResult){
		HttpStatus httpStatus;
		Optional<User> optionalUser = userService.findUserByEmail(user.getEmail());
		if (optionalUser.isPresent()){
			bindingResult.rejectValue("email", "error.user", "There's already a registered user with the email provided");
		}
		if (bindingResult.hasErrors()){
			httpStatus = HttpStatus.CONFLICT;
		}
		else {
			userService.saveUser(user);
			httpStatus = HttpStatus.OK;
		}
		return httpStatus;
	}
}
