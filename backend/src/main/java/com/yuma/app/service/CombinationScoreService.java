package com.yuma.app.service;

import com.yuma.app.document.Consumer;
import com.yuma.app.document.enums.Allergens;
import com.yuma.app.document.enums.HealthLabels;
import com.yuma.app.document.enums.ProteinType;
import com.yuma.app.exception.ResourceNotFoundException;
import com.yuma.app.repository.UserRepository;
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

	public EnumMap<ProteinType, Double> bestCombinationForUser(EnumMap<ProteinType, Double> scoreMap, String userId) {
		Consumer consumer = userRepository.findByUserId(userId).orElseThrow(() -> new ResourceNotFoundException("Consumer", "userId", userId));
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
	
	public EnumMap<ProteinType, Set<HealthLabels>> saveComboWithHealthLabels(EnumMap<ProteinType, Double> combinationForUser, String userId) {
		Consumer consumer = userRepository.findByUserId(userId).orElseThrow(() -> new ResourceNotFoundException("Consumer", "userId", userId));
		Set<HealthLabels> healthLabels = getHealthLabelsFromUser(consumer);
		EnumMap<ProteinType, Set<HealthLabels>> proteinMapWithLabels = new EnumMap<>(ProteinType.class);
		for(ProteinType proteinType: combinationForUser.keySet()){
			proteinMapWithLabels.put(proteinType, healthLabels); 
		} 
		return proteinMapWithLabels;
	}
	
	private Set<HealthLabels> getHealthLabelsFromUser(Consumer consumer){
		return convertAllergensToHealthLabels(consumer.getAllergies());
	}
	
	private Set<HealthLabels> convertAllergensToHealthLabels(Set<Allergens> allergens){
		Set<HealthLabels> healthLabels = new HashSet<>();
		for(Allergens allergies: allergens){
			switch (allergies){
				case DAIRY: healthLabels.add(HealthLabels.DAIRY_FREE);
				break;
				case GLUTEN: healthLabels.add(HealthLabels.GLUTEN_FREE);
				break;
				case PEANUT: healthLabels.add(HealthLabels.PEANUT_FREE);
				break;
				case SHELLFISH: healthLabels.add(HealthLabels.SHELLFISH_FREE);
				break;
				case SOY: healthLabels.add(HealthLabels.SOY_FREE);
				break;
				case TREE_NUTS: healthLabels.add(HealthLabels.TREE_NUT_FREE);
				break;
				default:  healthLabels.add(HealthLabels.NONE);
			}
		}
		return healthLabels;
	}
	
	private EnumMap<ProteinType, Double> topCombination(int numOfmeals, Map<ProteinType, Double> proteinTypeMap ){
		EnumMap<ProteinType, Double> proteinTypeEnumMap = new EnumMap<>(ProteinType.class);
		int i = 0;
		for(Map.Entry<ProteinType, Double> entry: proteinTypeMap.entrySet()){
			if(i  < numOfmeals){ proteinTypeEnumMap.put(entry.getKey(), entry.getValue());}
			else {break;}
			i++;
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
