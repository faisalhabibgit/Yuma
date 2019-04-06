package com.yuma.app.controller;

import com.yuma.app.document.enums.ProteinType;
import com.yuma.app.service.ProteinTypeScoringService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.EnumMap;

@Slf4j
@RestController
@RequestMapping("api/score/proteinType")
public class ProteinTypeScoringController {

	private ProteinTypeScoringService proteinTypeScoringService;

	public ProteinTypeScoringController(ProteinTypeScoringService proteinTypeScoringService) {
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
