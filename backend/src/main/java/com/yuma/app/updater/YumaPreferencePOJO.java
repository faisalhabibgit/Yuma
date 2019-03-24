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
	private List<String> generalPreference;
	private List<String> allergies;
	private List<String> additions;
	private List<String> diet;
	private List<String> other;
	private DateTime lastUpdate;
}
