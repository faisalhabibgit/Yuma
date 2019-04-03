package com.yuma.app.service.scoring;

import java.text.DecimalFormat;
import java.util.EnumMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuma.app.document.Consumer;
import com.yuma.app.document.Plan;
import com.yuma.app.document.enums.ProteinType;
import com.yuma.app.repository.UserRepository;

@Slf4j
@Service
public class ProteinTypeScoringService {

	private static EnumMap<ProteinType, Double> proteinTypePercentageMap = new EnumMap<>(ProteinType.class);
	private UserRepository userRepository;

	@Autowired
	public ProteinTypeScoringService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public EnumMap<ProteinType, Double> calculateProteinTypeScore() {
		if (proteinTypePercentageMap.size() == 0) {
			log.info("generating new score percentages");
			EnumMap<ProteinType, Integer> proteinTypeCountMap = calculateProteinTypeCount();
			calculatePercentages(proteinTypeCountMap);
		}

		return proteinTypePercentageMap;
	}

	public void modifyProteinTypeScoring(EnumMap<ProteinType, Double> proteinTypeDoubleEnumMap) {
		proteinTypePercentageMap = proteinTypeDoubleEnumMap;
	}

	private EnumMap<ProteinType, Integer> calculateProteinTypeCount() {
		log.info("calculating counts");
		EnumMap<ProteinType, Integer> proteinTypeCountMap = new EnumMap<>(ProteinType.class);

		List<Set<ProteinType>> listOfSetsOfUserProteinTypes = getListOfSetsOfUserProteinTypes();

		for (Set<ProteinType> set : listOfSetsOfUserProteinTypes) {
			for (ProteinType type : set) {
				incrementValue(proteinTypeCountMap, type);
			}
		}

		return proteinTypeCountMap;
	}

	private void calculatePercentages(EnumMap<ProteinType, Integer> countMap) {
		double total = countMap.values().stream().mapToDouble(Integer::doubleValue).sum();
		DecimalFormat decimalFormat = new DecimalFormat("##.#####");

		for (ProteinType type : countMap.keySet()) {
			proteinTypePercentageMap.put(type, Double.parseDouble(decimalFormat.format(countMap.get(type) / total)));
		}
	}

	private void incrementValue(EnumMap<ProteinType, Integer> proteinTypeMap, ProteinType key) {
		proteinTypeMap.putIfAbsent(key, 0);
		proteinTypeMap.put(key, proteinTypeMap.get(key) + 1);
	}

	private List<Set<ProteinType>> getListOfSetsOfUserProteinTypes() {
		List<Consumer> usersList = userRepository.findAll();
		List<Plan> usersPlans = usersList.stream().map(Consumer::getPlan).collect(Collectors.toList());

		return usersPlans.stream().map(Plan::getRequestedProteinTypes).collect(Collectors.toList());
	}
}
