package com.yuma.app.converter;

import com.yuma.app.document.CombinationReport;
import com.yuma.app.to.CombinationReportTO;
import com.yuma.app.to.MealTO;
import com.yuma.app.to.UserTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import java.util.stream.Collectors;

public class CombinationReportTOConverter implements Converter<CombinationReport, CombinationReportTO> {
	

	ConversionService conversionService;
	
	@Autowired
	public CombinationReportTOConverter(ConversionService conversionService) {
		this.conversionService = conversionService;
		
	}

	@Override
	public CombinationReportTO convert(CombinationReport combinationReport) {
		
		CombinationReportTO combinationReportTO = new CombinationReportTO();
		
		combinationReportTO.setId(combinationReport.getId());
		combinationReportTO.setCombinationScore(combinationReport.getCombinationScore());
		combinationReportTO.setNumberOfBlanks(combinationReport.getNumberOfBlanks());
		combinationReportTO.setMealTOS(combinationReport.getMealsList().stream().map(meal -> conversionService.convert(meal, MealTO.class)).collect(Collectors.toList()));
		combinationReportTO.setUserTOS(combinationReport.getUserList().stream().map(user -> conversionService.convert(user, UserTO.class)).collect(Collectors.toList()));
		return combinationReportTO;
		
	}
}
