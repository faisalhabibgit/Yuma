package com.yuma.app.service.scoring;

import java.util.EnumMap;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuma.app.document.Consumer;
import com.yuma.app.document.enums.HealthLabels;
import com.yuma.app.document.enums.ProteinType;
import com.yuma.app.repository.UserRepository;

@Slf4j
@Service
public class MealChoosingService {

	private UserRepository userRepository;
	private ProteinTypeScoringService proteinTypeScoringService;

	@Autowired
	public MealChoosingService(UserRepository userRepository, ProteinTypeScoringService proteinTypeScoringService) {
		this.userRepository = userRepository;
		this.proteinTypeScoringService = proteinTypeScoringService;
	}

	public void firstStep() {
		EnumMap<ProteinType, Double> map = proteinTypeScoringService.calculateProteinTypeScore();
		EnumMap<ProteinType, EnumMap<HealthLabels, Integer>> possibleMealMap;

		List<Consumer> consumerList = userRepository.findAll();

		/*
		 * 1. get list of all users
		 * 2. get the plan of each user
		 * 3. get number of meals for user
		 * 4. get list of allowed ProteinTypes by user
		 * 5. put allowed ProteinTypes in a EnumMap with their associated values
		 * 6. get the list of highest combination score (using KCombinations)
		 * 7. 
		 */
	}
}
