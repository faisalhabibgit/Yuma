package com.yuma.app.controller;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import com.yuma.app.service.UserService;
import com.yuma.app.to.UserTO;

@Slf4j
@RestController
@RequestMapping("api/rest")
public class UserController {
	final Logger logger = LoggerFactory.getLogger(UserController.class);
	private UserService userService;

	public UserController(UserService consumerService) {
		this.userService = consumerService;
	}

	@GetMapping("/all")
	public List<UserTO> getAll() {
		this.logger.info("retrieving user list from DB");
		return this.userService.list();
	}
	
	@DeleteMapping("/{uuid}")
	public void deleteUserByUserID(@PathVariable String uuid){
		this.logger.info("deleting user from DB in controller");
		this.userService.deleteUserByUserID(uuid);
	}

	@RequestMapping(method = RequestMethod.POST)
	public UserTO createUser(@RequestBody UserTO userTO){
		this.logger.info("creating user from DB in controller");
		return this.userService.create(userTO);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public UserTO updateUser(@RequestBody UserTO userTO){
		this.logger.info("updating user from DB in controller");
		return this.userService.updateUser(userTO);
	}
}
