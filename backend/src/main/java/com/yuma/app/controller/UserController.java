package com.yuma.app.controller;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yuma.app.service.UserService;
import com.yuma.app.to.UserTO;

@Slf4j
@RestController
@RequestMapping("api/rest")
public class UserController {

	final Logger logger = LoggerFactory.getLogger("initial Logger");

	private UserService userService;

	public UserController(UserService consumerService) {
		this.userService = consumerService;
	}

	@GetMapping("/all")
	public List<UserTO> getAll() {
		this.logger.info("retrieving user list from DB");
		return this.userService.list();
	}
	
	@PutMapping("/{uuid}")
	public void deleteUserByUserID(@PathVariable String uuid){
		this.logger.info("deleting user from DB in controller");

		this.userService.deleteUserByUserID(uuid);
	}
	
	@PutMapping("/update")
	public UserTO updateUser(@PathVariable UserTO userTO){
		this.logger.info("deleting user from DB in controller");

		return this.userService.updateUser(userTO);
	}
}
