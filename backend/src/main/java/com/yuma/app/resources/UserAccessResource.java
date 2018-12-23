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
import com.yuma.app.to.UserTO;

@Slf4j
@RestController
@RequestMapping("user")
public class UserAccessResource {

	@Autowired
	private CustomUserDetailsService userService;
	
	@PostMapping("/register")
	public HttpStatus register(@RequestBody UserTO userTO, BindingResult bindingResult){
		HttpStatus httpStatus;
		Optional<User> optionalUser = userService.findUserByEmail(userTO.getEmail());
		if (optionalUser.isPresent()){
			bindingResult.rejectValue("email", "error.user", "There's already a registered user with the email provided");
		}
		if (bindingResult.hasErrors()){
			httpStatus = HttpStatus.CONFLICT;
		}
		else {
			userService.saveUser(userTO);
			httpStatus = HttpStatus.OK;
		}
		return httpStatus;
	}
}
