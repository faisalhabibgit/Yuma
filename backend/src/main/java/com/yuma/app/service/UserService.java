package com.yuma.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.yuma.app.document.User;
import com.yuma.app.repository.UserRepository;
import com.yuma.app.to.UserTO;

@Service
public class UserService {
	private UserRepository userRepository;
	private ConversionService conversionService;

	public UserService(UserRepository userRepository, ConversionService conversionService) {
		this.userRepository = userRepository;
		this.conversionService = conversionService;
	}

	public List<UserTO> list() {
		List<UserTO> consumerTOS = new ArrayList<>();
		List<User> consumerList = userRepository.findAll();
		
		for (User consumer: consumerList){
			consumerTOS.add(conversionService.convert(consumer, UserTO.class));
		}
		
		return consumerTOS;
	}
	
}
