package com.yuma.app.service;

import com.yuma.app.document.Consumer;
import com.yuma.app.document.Plan;
import com.yuma.app.document.enums.Allergens;
import com.yuma.app.document.enums.HealthLabels;
import com.yuma.app.document.enums.ProteinType;
import com.yuma.app.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CombinationScoreServiceTest {

	@Mock
	private UserRepository userRepository;
	private CombinationScoreService combinationScoreService;
	private ProteinTypeScoringService proteinTypeScoringService;


	@Before
	public void setUp() {
		initMocks(this);
		combinationScoreService = new CombinationScoreService(userRepository);
		proteinTypeScoringService = new ProteinTypeScoringService(userRepository);
	}
	
	@Test
	public void saveComboWithHealthLabels(){

		List<Consumer> consumers = createConsumersList();
		consumers.get(0).setUserId("1234");
		consumers.get(0).setActive(true);
		Plan plan = new Plan();
		plan.setNumOfMeals(2);
		Set<Allergens> allergens = new HashSet<>(Arrays.asList(Allergens.DAIRY, Allergens.PEANUT));
		Set<ProteinType> proteinTypesConsumer1 = new HashSet<>(Arrays.asList(ProteinType.FISH, ProteinType.BEEF, ProteinType.LAMB));
		plan.setRequestedProteinTypes(proteinTypesConsumer1);
		consumers.get(0).setPlan(plan);
		consumers.get(0).setAllergies(allergens);
		when(userRepository.findAll()).thenReturn(consumers);
		when(userRepository.findByUserId("1234")).thenReturn(Optional.of(consumers.get(0)));
		EnumMap<ProteinType, Double> proteinTypeScore = proteinTypeScoringService.calculateProteinTypeScore();
		EnumMap<ProteinType, Double> combinationMap = combinationScoreService.bestCombinationForUser(proteinTypeScore, consumers.get(0).getUserId());
		EnumMap<ProteinType, Set<HealthLabels>> combinationMapHealthLabel = combinationScoreService.saveComboWithHealthLabels(combinationMap, consumers.get(0).getUserId());
		Set<HealthLabels> healthLabels = combinationMapHealthLabel.entrySet().iterator().next().getValue();
		assertEquals(healthLabels.size(), allergens.size());
	}
	
	@Test
	public void bestCombinationForUserTest(){
		List<Consumer> consumers = createConsumersList();
		consumers.get(0).setUserId("1234");
		consumers.get(0).setActive(true);
		Plan plan = new Plan();
		plan.setNumOfMeals(2);
		Set<ProteinType> proteinTypesConsumer1 = new HashSet<>(Arrays.asList(ProteinType.FISH, ProteinType.BEEF, ProteinType.LAMB));
		plan.setRequestedProteinTypes(proteinTypesConsumer1);
		consumers.get(0).setPlan(plan);	
		when(userRepository.findAll()).thenReturn(consumers);
		when(userRepository.findByUserId("1234")).thenReturn(Optional.of(consumers.get(0)));
		EnumMap<ProteinType, Double> map = proteinTypeScoringService.calculateProteinTypeScore();
		EnumMap<ProteinType, Double> map1 = combinationScoreService.bestCombinationForUser(map, consumers.get(0).getUserId());
		assertEquals(plan.getNumOfMeals(), map1.size());
	}
	
	private List<Consumer> createConsumersList() {
		List<Consumer> consumerList = new ArrayList<>(10);
		int i = 0;

		while (i < 5) {
			consumerList.add(createConsumer());
			i++;
		}

		return consumerList;
	}

	private Consumer createConsumer() {
		Set<ProteinType> proteinTypes = new HashSet<>(Arrays.asList(ProteinType.values()));

		Plan plan = Plan.builder()
			.requestedProteinTypes(proteinTypes)
			.build();

		return Consumer.builder()
			.plan(plan)
			.build();
	}

}
