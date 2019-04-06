package com.yuma.app.service;

import com.yuma.app.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CombinationScoreService {

	private UserRepository userRepository;

	@Autowired
	public CombinationScoreService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

}
