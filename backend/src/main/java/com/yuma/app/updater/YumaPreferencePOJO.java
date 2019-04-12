package com.yuma.app.updater;

import java.util.List;

import lombok.Builder;
import lombok.Data;

import org.joda.time.DateTime;

@Builder
@Data
public class YumaPreferencePOJO {

	private String id;
	private int numberOfMealsPerWeek;
	private YumaUserPOJO user;
	private String diet;
	private List<String> proteinTypes;
	private List<String> allergies;
	private List<String> specialRequests;
	private List<String> other;
	private DateTime lastUpdate;
}
