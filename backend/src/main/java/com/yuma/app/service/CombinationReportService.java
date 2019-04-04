package com.yuma.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.yuma.app.document.WeeklyCombination;
import com.yuma.app.util.CombinationReportHelper;
import com.yuma.app.util.WeeklyCombinationHelper;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.yuma.app.document.CombinationReport;
import com.yuma.app.document.Consumer;
import com.yuma.app.document.Meal;
import com.yuma.app.exception.ResourceNotFoundException;
import com.yuma.app.repository.CombinationReportRepository;
import com.yuma.app.repository.MealRepository;
import com.yuma.app.repository.UserRepository;
import com.yuma.app.to.CombinationReportTO;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class CombinationReportService {

	
	@Autowired
	private MealRepository mealRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CombinationReportRepository combinationReportRepository;
	private List<CombinationReport> possibleCombinations;
	private List<Meal> addedMeals;
	private ConversionService conversionService;
	
	private CombinationReportHelper combinationReportHelper;
	private WeeklyCombinationHelper weeklyCombinationHelper;

	public CombinationReportService(ConversionService conversionService) {
		this.conversionService = conversionService;
		this.addedMeals = new ArrayList<>();
		this.possibleCombinations = new ArrayList<>();
		this.combinationReportHelper = new CombinationReportHelper();
	}

	public CombinationReportService(ConversionService conversionService, List<CombinationReport> possibleCombinations) {
		this.conversionService = conversionService;
		this.addedMeals = new ArrayList<>();
		this.possibleCombinations = possibleCombinations;
		this.combinationReportHelper = new CombinationReportHelper();
	}

	public CombinationReport getMostRecentlyAdded(){
		CombinationReport combinationReport = combinationReportRepository.findTopByOrderByCreatedOnDesc().orElseThrow(() ->
			new ResourceNotFoundException("Combination report", "most recently added", null)
		);
		return combinationReport;
	}

	public List<CombinationReportTO> generateWeeklyCombination() {
		log.info("instantiating Combination Report in generate Weekly Combination");
		if (!possibleCombinations.isEmpty()){
			possibleCombinations.clear();
		}
		List<Meal> availableMeals = mealRepository.findByIsAvailableIsTrue();
		List<Consumer> activeUsers = userRepository.findByIsActiveIsTrue();

		int i = 0;
		CombinationReport combinationReport = new CombinationReport();
		WeeklyCombination weeklyCombination = new WeeklyCombination(combinationReport, possibleCombinations, availableMeals, activeUsers);
		weeklyCombinationHelper.runFirstMealCombinationAlgorithm(weeklyCombination, addedMeals);

		while (i < 2 && (combinationReport.getNumberOfBlanks() != 0)) {
			weeklyCombinationHelper.reRunMealCombinationAlgorithm(weeklyCombination, i, addedMeals);
		}
		
		return possibleCombinations.stream().map(combinationReport1 ->
			conversionService.convert(combinationReport1, CombinationReportTO.class)).collect(Collectors.toList());
	}

	public void saveCombinationReport(int i){
		CombinationReport combinationReport = this.possibleCombinations.get(i);
		combinationReport.setCreatedOn(new Date());
		try {
			this.combinationReportRepository.save(combinationReport);
			this.userRepository.save(combinationReport.getUserList());
		}
		catch (IndexOutOfBoundsException e){
			log.info("index out of bound");
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
