package com.yuma.app.service.scoring;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.yuma.app.document.Consumer;
import com.yuma.app.document.Plan;
import com.yuma.app.document.enums.ProteinType;
import com.yuma.app.repository.UserRepository;

public class ProteinTypeScoringTest {

	@Mock
	private UserRepository userRepository;

	private ProteinTypeScoringService proteinTypeScoring;

	@Before
	public void setUp() {
		initMocks(this);
		proteinTypeScoring = new ProteinTypeScoringService(userRepository);
	}

	@Test
	public void calculateProteinTypeScore() {
		when(userRepository.findAll()).thenReturn(createConsumersList());
		EnumMap<ProteinType, Double> map = proteinTypeScoring.calculateProteinTypeScore();

		assertEquals(ProteinType.values().length, map.size());
	}

	@Test
	public void modifyProteinTypeScoring() {
		when(userRepository.findAll()).thenReturn(createConsumersList());
		EnumMap<ProteinType, Double> map = proteinTypeScoring.calculateProteinTypeScore();
		map.replace(ProteinType.PLANT_BASED_PROTEIN, 20.00);
		map.replace(ProteinType.BEEF, 20.00);
		proteinTypeScoring.modifyProteinTypeScoring(map);

		map = proteinTypeScoring.calculateProteinTypeScore();
		assertEquals(ProteinType.values().length, map.size());

		assertEquals(Optional.ofNullable(map.get(ProteinType.PLANT_BASED_PROTEIN)), Optional.of(20.00));
		assertEquals(Optional.ofNullable(map.get(ProteinType.BEEF)), Optional.of(20.00));
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
