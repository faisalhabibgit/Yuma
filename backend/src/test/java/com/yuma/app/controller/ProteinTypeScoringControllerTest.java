package com.yuma.app.controller;



import com.yuma.app.document.enums.ProteinType;
import com.yuma.app.service.ProteinTypeScoringService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.EnumMap;



import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;



@RunWith(MockitoJUnitRunner.class)
public class ProteinTypeScoringControllerTest {
	
	@InjectMocks
	ProteinTypeScoringController proteinTypeScoringController;
	
	@Mock
	private ProteinTypeScoringService proteinTypeScoringService;


	@Test
	public void calculateProteinTypeScoreTest() {

		when(proteinTypeScoringService.calculateProteinTypeScore()).thenReturn(new EnumMap(ProteinType.class));

		proteinTypeScoringController.getProteinPercentage();

		verify(proteinTypeScoringService).calculateProteinTypeScore();

	}



	@Test
	public void modifyProteinPercentageTest() {

		EnumMap<ProteinType, Double> test = prepareAndReturnProteinTypeScore();

		EnumMap<ProteinType, Double> modify = prepareAndReturnProteinTypeScoreTwo();

		proteinTypeScoringService.modifyProteinTypeScoring(modify);

		when(proteinTypeScoringService.calculateProteinTypeScore()).thenReturn(modify);

		proteinTypeScoringController.getProteinPercentage();

		Assert.assertEquals(proteinTypeScoringController.getProteinPercentage().get(ProteinType.VEGAN), modify.get(ProteinType.VEGAN));

	}





	@Test
	public void resetPercentageTest(){

		EnumMap<ProteinType, Double> test = resetProteinTypeScore();

		proteinTypeScoringService.resetProteinTypeScoring();

		when(proteinTypeScoringService.calculateProteinTypeScore()).thenReturn(test);

		Assert.assertEquals(proteinTypeScoringController.getProteinPercentage().isEmpty(), Boolean.TRUE);





	}

	private EnumMap<ProteinType,Double>  prepareAndReturnProteinTypeScore() {

		EnumMap<ProteinType,Double> score = new EnumMap(ProteinType.class);

		score.put(ProteinType.VEGAN, 0.9);

		return score;

	}



	private EnumMap<ProteinType,Double>  prepareAndReturnProteinTypeScoreTwo() {

		EnumMap<ProteinType,Double> score = new EnumMap(ProteinType.class);

		score.put(ProteinType.VEGAN, 0.8);

		return score;

	}



	private EnumMap<ProteinType,Double>  resetProteinTypeScore() {

		EnumMap<ProteinType,Double> score = new EnumMap(ProteinType.class);
		score.put(ProteinType.VEGAN, 0.8);
		score.clear();
		return score;

	}

}




