package com.yuma.app.controller;

import com.yuma.app.service.CombinationReportService;
import com.yuma.app.to.CombinationReportTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
	
	
}
