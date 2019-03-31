package com.yuma.app.controller;

import java.util.EnumMap;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yuma.app.document.enums.ProteinType;
import com.yuma.app.service.scoring.ProteinTypeScoringService;

@Slf4j
@RestController
@RequestMapping("api/score")
public class ScoringController {

	private ProteinTypeScoringService proteinTypeScoringService;

	public ScoringController(ProteinTypeScoringService proteinTypeScoringService) {
		this.proteinTypeScoringService = proteinTypeScoringService;
	}

	@GetMapping("/percentage")
	public EnumMap<ProteinType, Double> getProteinPercentage() {
		log.info("fetching score percentage");

		return proteinTypeScoringService.calculateProteinTypeScore();
	}

	@PutMapping("/modify")
	public void setProteinPercentage(@RequestBody EnumMap<ProteinType, Double> updatedPercentages) {
		log.info("updating score percentage");

		proteinTypeScoringService.modifyProteinTypeScoring(updatedPercentages);
	}
}
