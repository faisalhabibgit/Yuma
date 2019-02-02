package com.yuma.app.controller;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yuma.app.service.CombinationReportService;
import com.yuma.app.to.CombinationReportTO;

@Slf4j
@RestController
@RequestMapping("api/combinationreport")
public class CombinationReportController {
	
	CombinationReportService combinationReportService;
	
	@Autowired
	public CombinationReportController(CombinationReportService combinationReportService) {
		this.combinationReportService = combinationReportService;
	}

	@GetMapping("/weeklycombo")
	public List<CombinationReportTO> getWeeklyCombo() {
		return this.combinationReportService.generateWeeklyCombination();
	}
	
	@PostMapping("{index}")
	public void chosenCombo(@PathVariable int index){
		combinationReportService.saveCombinationReport(index);
	}
	
}
