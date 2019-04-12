package com.yuma.app.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yuma.app.service.UpdateDataMapperService;

@Slf4j
@RestController
@RequestMapping("api/update")
public class UpdateUsersController {

	private UpdateDataMapperService updateDataMapperService;

	@Autowired
	public UpdateUsersController(UpdateDataMapperService updateDataMapperService) {
		this.updateDataMapperService = updateDataMapperService;
	}

	@GetMapping("/users")
	public void updateUsersFromYumaServer() {
		log.info("updating users from Yuma DB in controller");

		updateDataMapperService.mapAndSaveUsers();
	}
}
