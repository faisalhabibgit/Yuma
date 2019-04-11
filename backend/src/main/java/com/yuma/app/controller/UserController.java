package com.yuma.app.controller;

import java.util.List;

import lombok.extern.slf4j.Slf4j;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yuma.app.service.ConsumerService;
import com.yuma.app.to.UserTO;

@Slf4j
@RestController
@RequestMapping("api/rest")
public class UserController {
	
	private ConsumerService userService;

	public UserController(ConsumerService consumerService) {
		this.userService = consumerService;
	}

	@GetMapping("/all")
	public List<UserTO> getAll() {
		log.info("retrieving user list from DB");
		return this.userService.list();
	}
	
	@DeleteMapping("/{uuid}")
	public void deleteUserByUserID(@PathVariable String uuid) {
		log.info("deleting user from DB in controller");
		this.userService.deleteUserByUserID(uuid);
	}
	@GetMapping("/active")
	public List<UserTO> getActiveUsers() {
		log.info("retrieving active users list from DB");
		return this.userService.activeUsers();
	}
	
	@GetMapping("/company/{company}")
	public List<UserTO> getUsersByCompany(@PathVariable String company){
		log.info("retrieving list of users by company");
		return this.userService.findUsersByCompany(company);
	}
	

	@RequestMapping(method = RequestMethod.POST)
	public UserTO createUser(@RequestBody UserTO userTO){
		log.info("creating user from DB in controller");
		return this.userService.create(userTO);
	}
	
	@PutMapping("/update")
	public UserTO updateUser(@RequestBody UserTO userTO){
		log.info("updating user from DB in controller");
		return this.userService.updateUser(userTO);
	}

	@GetMapping("/listcompanies")
	public List<String> listCompanies(){
		log.info("updating user companies from DB in controller");
		return this.userService.listUserCompanies();
	}
}
