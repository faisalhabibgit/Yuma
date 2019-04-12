package com.yuma.app.converter;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import com.yuma.app.document.CombinationReport;
import com.yuma.app.to.CombinationReportTO;
import com.yuma.app.to.ConsumerTO;
import com.yuma.app.to.MealTO;

public class CombinationReportTOConverter implements Converter<CombinationReport, CombinationReportTO> {
	

	ConversionService conversionService;
	
	@Autowired
	public CombinationReportTOConverter(ConversionService conversionService) {
		this.conversionService = conversionService;
		
	}

	@Override
	public CombinationReportTO convert(CombinationReport combinationReport) {
		
		CombinationReportTO combinationReportTO = new CombinationReportTO();
		
		combinationReportTO.setCombinationScore(combinationReport.getCombinationScore());
		combinationReportTO.setNumberOfBlanks(combinationReport.getNumberOfBlanks());
		combinationReportTO.setCreatedOn(combinationReport.getCreatedOn());
		combinationReportTO.setMealTOS(combinationReport.getMealsList().stream().map(meal -> conversionService.convert(meal, MealTO.class)).collect(Collectors.toList()));
		combinationReportTO.setConsumerTOS(combinationReport.getUserList().stream().map(user -> conversionService.convert(user, ConsumerTO.class)).collect(Collectors.toList()));
		return combinationReportTO;
		
	}
}
