package com.yuma.app.controller;

import com.yuma.app.service.CombinationReportService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CombinationReportControllerTest {

	@Mock
	CombinationReportService combinationReportService;
	
	@InjectMocks
	CombinationReportController combinationReportController;
	
	@Test
	public void testGetWeeklyCombo() {
		
		combinationReportController.getWeeklyCombo();
		
		verify(combinationReportService).generateWeeklyCombination();
	}
	
	@Test
	public void testChosenCombo() {
		
		int index = 1;
		
		combinationReportController.chosenCombo(index);
		
		verify(combinationReportService).saveCombinationReport(index);
		
	}
}
