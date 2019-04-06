package com.yuma.app.service;

import com.yuma.app.document.Consumer;
import com.yuma.app.document.enums.ProteinType;
import com.yuma.app.exception.ResourceNotFoundException;
import com.yuma.app.repository.UserRepository;
import com.yuma.app.to.ConsumerTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

@Slf4j
@Service
public class CombinationScoreService {

	private UserRepository userRepository;

	@Autowired
	public CombinationScoreService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public EnumMap<ProteinType, Double> bestCombinationForUser(EnumMap<ProteinType, Double> scoreMap, ConsumerTO consumerTO) {
		Consumer consumer = userRepository.findByUserId(consumerTO.getUserId()).orElseThrow(() -> new ResourceNotFoundException("Consumer", "userId", consumerTO.getUserId()));
		int numOfMeals = consumer.getPlan().getNumOfMeals();
		Set<ProteinType> userProteinPreference = consumer.getPlan().getRequestedProteinTypes();
		EnumMap<ProteinType, Double> proteinTypeCountMap = new EnumMap<>(ProteinType.class);
		for (ProteinType type : userProteinPreference) {
			proteinTypeCountMap.put(type, scoreMap.get(type));
		}

		Map<ProteinType, Double> proteinTypeMap = highestOrder(proteinTypeCountMap);

		EnumMap<ProteinType, Double> topCombo = topCombination(numOfMeals, proteinTypeMap);
		return topCombo;
	}
	
	private EnumMap<ProteinType, Double> topCombination(int numOfmeals, Map<ProteinType, Double> proteinTypeMap ){
		EnumMap<ProteinType, Double> proteinTypeEnumMap = new EnumMap<>(ProteinType.class);
		for (int i = 0; i < numOfmeals; i++) {
			proteinTypeEnumMap.put(proteinTypeMap.entrySet().iterator().next().getKey(), proteinTypeMap.entrySet().iterator().next().getValue());
		}
		return proteinTypeEnumMap;
	}
	
	private Map<ProteinType, Double> highestOrder(EnumMap<ProteinType, Double> combination) {
		Map<ProteinType, Double> sorted;
		sorted = combination
			.entrySet()
			.stream()
			.sorted(Collections.reverseOrder(comparingByValue()))
			.collect(
				toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,
					HashMap::new));
		return sorted;
	}
}
