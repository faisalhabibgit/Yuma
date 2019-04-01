package com.yuma.app.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.yuma.app.util.CombinationReportHelper;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.yuma.app.document.CombinationReport;
import com.yuma.app.document.Consumer;
import com.yuma.app.document.Ingredients;
import com.yuma.app.document.Meal;
import com.yuma.app.exception.ResourceNotFoundException;
import com.yuma.app.repository.CombinationReportRepository;
import com.yuma.app.repository.MealRepository;
import com.yuma.app.repository.UserRepository;
import com.yuma.app.to.CombinationReportTO;

@Service
public class CombinationReportService {

	private Logger logger = LoggerFactory.getLogger(CombinationReportService.class);
	
	@Autowired
	private MealRepository mealRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CombinationReportRepository combinationReportRepository;
	private List<CombinationReport> possibleCombinations;
	private List<Meal> addedMeals;
	private ConversionService conversionService;
	protected CombinationReportHelper combinationReportHelper;

	public CombinationReportService(ConversionService conversionService) {
		this.conversionService = conversionService;
		this.addedMeals = new ArrayList<>();
		this.possibleCombinations = new ArrayList<>();
		this.combinationReportHelper = new CombinationReportHelper();
	}

	public CombinationReport getMostRecentlyAdded(){
		CombinationReport combinationReport = combinationReportRepository.findTopByOrderByCreatedOnDesc().orElseThrow(() ->
			new ResourceNotFoundException("Combination report", "CreatedOn", null)
		);
		return combinationReport;
	}

	public List<CombinationReportTO> generateWeeklyCombination() {
		logger.info("instantiating Combination Report in generate Weekly Combination");
		if (!possibleCombinations.isEmpty()){
			possibleCombinations.clear();
		}
		List<Meal> availableMeals = mealRepository.findByIsAvailableIsTrue();
		List<Consumer> activeUsers = userRepository.findByIsActiveIsTrue();
		List<Meal> highlyRankedMeals;
		int i = 0;
		CombinationReport combinationReport;
		combinationReportHelper.setMealScores(availableMeals, activeUsers);
		combinationReport = new CombinationReport(0, combinationReportHelper.countCombinationScore(availableMeals), activeUsers, availableMeals);
		combinationReportHelper.runMealCombinationAlgorithm(combinationReport, possibleCombinations, addedMeals);
		combinationReport.getMealsList().addAll(addedMeals);
		highlyRankedMeals = mealRepository.findTop3ByOrderByMealScoreDesc();

		while (i < 2 && (combinationReport.getNumberOfBlanks() != 0)) {
			combinationReport = new CombinationReport(0, combinationReportHelper.countCombinationScore(availableMeals), activeUsers, availableMeals);
			combinationReportHelper.replaceLowestScore(combinationReport, i, highlyRankedMeals);
			combinationReportHelper.runMealCombinationAlgorithm(combinationReport, possibleCombinations, addedMeals);
			combinationReport.getMealsList().addAll(addedMeals);
			i++;
		}
		
		return possibleCombinations.stream().map(combinationReport1 ->
			conversionService.convert(combinationReport1, CombinationReportTO.class)).collect(Collectors.toList());
	}


	public void saveCombinationReport(int i){
		CombinationReport combinationReport = this.possibleCombinations.get(i);
		combinationReport.setCreatedOn(new Date());
		try {
			this.combinationReportRepository.save(combinationReport);
		}
		catch (IndexOutOfBoundsException e){
			logger.info("index out of bound");
			return;
		}
		this.possibleCombinations.clear();
	}

	public CombinationReportTO getCombinationReportByDate(DateTime startDate){
		DateTime endDate = startDate.plusWeeks(1);
		
		CombinationReport combinationReport = this.combinationReportRepository.findCombinationReportByCreatedOnBetween(startDate.toDate(), endDate.toDate()).orElseThrow(() ->
			new ResourceNotFoundException("CombinationReport", "Date", endDate)
		);
		
		return conversionService.convert(combinationReport, CombinationReportTO.class);
	}
}
