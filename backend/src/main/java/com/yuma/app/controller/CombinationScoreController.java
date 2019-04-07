package com.yuma.app.controller;

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

@Slf4j
@RestController
@RequestMapping("api/combination")
public class CombinationScoreController {
	private CombinationScoreService combinationScoreService;
	private ProteinTypeScoringService proteinTypeScoringService;

	public CombinationScoreController(CombinationScoreService combinationScoreService, ProteinTypeScoringService proteinTypeScoringService) {
		this.combinationScoreService = combinationScoreService;
		this.proteinTypeScoringService = proteinTypeScoringService;
	}

	@GetMapping("/user/{userId}")
	public EnumMap<ProteinType, Double> getProteinPercentage(@PathVariable String userId) {
		log.info("fetching best protein combination for user");
		return combinationScoreService.bestCombinationForUser(proteinTypeScoringService.calculateProteinTypeScore(), userId);
	}
}
