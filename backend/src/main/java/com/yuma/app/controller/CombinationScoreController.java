package com.yuma.app.controller;

import com.yuma.app.document.enums.HealthLabels;
import com.yuma.app.document.enums.ProteinType;
import com.yuma.app.service.CombinationScoreService;
import com.yuma.app.service.ProteinTypeScoringService;
import com.yuma.app.to.ConsumerTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.EnumMap;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("api/proteinCombination")
public class CombinationScoreController {
	private CombinationScoreService combinationScoreService;
	private ProteinTypeScoringService proteinTypeScoringService;

	public CombinationScoreController(CombinationScoreService combinationScoreService, ProteinTypeScoringService proteinTypeScoringService) {
		this.combinationScoreService = combinationScoreService;
		this.proteinTypeScoringService = proteinTypeScoringService;
	}

	@GetMapping("/user/{userId}")
	public EnumMap<ProteinType, Double> getCombinationforUser(@PathVariable String userId) {
		log.info("fetching best protein combination for user");
		return combinationScoreService.bestCombinationForUser(proteinTypeScoringService.calculateProteinTypeScore(), userId);
	}
	
	@GetMapping("/user/{userId}/healthLabels")
	public EnumMap<ProteinType, Set<HealthLabels>> getCombinationHealthLabel(@PathVariable String userId) {
		log.info("fetching highest combination for user with health label");
		return combinationScoreService.saveComboWithHealthLabels(getCombinationforUser(userId), userId);
	}
}
