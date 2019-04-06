package com.yuma.app.service;

import com.yuma.app.document.enums.ProteinType;
import com.yuma.app.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

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

	public Map<ProteinType, Double> highestOrder(EnumMap<ProteinType, Double> combination) {
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
