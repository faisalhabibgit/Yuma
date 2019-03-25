package com.yuma.app.document;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import org.springframework.data.mongodb.core.mapping.Document;

import com.yuma.app.document.enums.Diet;
import com.yuma.app.document.enums.ProteinType;
import com.yuma.app.document.enums.SpecialRequest;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Plan {
	private int numOfMeals;
	private Set<SpecialRequest> specialRequests = new HashSet<>();
	private Set<ProteinType> requestedProteinTypes = new HashSet<>();
	private Diet diet;
}
