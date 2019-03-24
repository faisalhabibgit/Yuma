package com.yuma.app.updater;

import static org.springframework.util.CollectionUtils.isEmpty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UpdateUsersService {

	private static HashMap<String, YumaPreferencePOJO> usersCovered = new HashMap<>();
	private static final String endpoint = "http://itsyuma.com/preferences";

	public void fetchYumaUsers(){
		BufferedReader responseBufferedReader = makeCall();
		JSONArray jsonArray = getJsonArray(responseBufferedReader);
		parseThroughPreferences(jsonArray);
	}

	private JSONArray getJsonArray(BufferedReader responseBufferedReader) {
		JSONParser jsonParser = new JSONParser();

		try {
			Object obj = jsonParser.parse(responseBufferedReader);
			JSONObject jsonObject = (JSONObject) obj;
			return (JSONArray) jsonObject.get("data");

		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	private BufferedReader makeCall(){
		OkHttpClient client = new OkHttpClient();
		try {
			Response response = client.newCall(setupRequest()).execute();
			return new BufferedReader(new InputStreamReader(response.body().byteStream()));
		}
		catch (IOException e){
			e.printStackTrace();
			return null;
		}
	}

	private Request setupRequest(){
		return new Request.Builder()
			.url(endpoint)
			.get()
			.addHeader("Content-Type", "application/json")
			.build();
	}

	protected void parseThroughPreferences(JSONArray preferencesArray){
		for (Object jsonObject : preferencesArray){
			JSONObject preferenceJsonObject = (JSONObject) jsonObject;
			JSONObject userJsonObject = (JSONObject) preferenceJsonObject.get("user");
			String userId = userJsonObject.get("_id").toString();
			
			if (usersCovered.containsKey(userId)){
				YumaPreferencePOJO yumaPreferencePOJO = usersCovered.get(userId);
				addPreferenceToExistingPreferencePOJO(yumaPreferencePOJO, preferenceJsonObject);
			}
			else if(checkIfUserHasGroup(userJsonObject)) {
				saveToHashMap(serializeObjects(userJsonObject));
			}
		}
	}

	private boolean checkIfUserHasGroup(JSONObject userJsonObject) {
		return !isEmpty(parseGroupJsonObject(userJsonObject));
	}

	private JSONObject parseGroupJsonObject(JSONObject userJsonObject){
		return (JSONObject) userJsonObject.get("group");
	}

	private void addPreferenceToExistingPreferencePOJO(YumaPreferencePOJO yumaPreferencePOJO, JSONObject preferenceJsonObject) {
		String preferenceKeyType = preferenceJsonObject.get("key").toString();
		String value = preferenceJsonObject.get("value").toString();
		switch (preferenceKeyType) {
			case "general":
				yumaPreferencePOJO.getGeneralPreference().add(value);
				break;
			case "diet":
				yumaPreferencePOJO.getDiet().add(value);
				break;
			case "allergy":
				yumaPreferencePOJO.getAllergies().add(value);
				break;
			case "addition":
				yumaPreferencePOJO.getAdditions().add(value);
				break;
			case "other":
				yumaPreferencePOJO.getOther().add(value);
				break;
			case "plan":
				yumaPreferencePOJO.setNumberOfMealsPerWeek(Integer.parseInt(value));
		}
	}

	private YumaPreferencePOJO serializeObjects(JSONObject userJsonObject) {
		YumaGroupPOJO yumaGroupPOJO = serializeYumaGroupPOJO(parseGroupJsonObject(userJsonObject));
		YumaUserPOJO yumaUserPOJO = serializeYumaUserGroupPOJO(userJsonObject);
		yumaUserPOJO.setYumaGroupPOJO(yumaGroupPOJO);

		return serializeYumaPreferencePOJO(yumaUserPOJO);
	}

	private YumaGroupPOJO serializeYumaGroupPOJO(JSONObject groupJsonObject) {
		return YumaGroupPOJO.builder()
			.id(groupJsonObject.get("_id").toString())
			.alias(groupJsonObject.get("alias").toString())
			.build();
	}
	
	private YumaUserPOJO serializeYumaUserGroupPOJO(JSONObject userJsonObject) {
		return YumaUserPOJO.builder()
			.id(userJsonObject.get("_id").toString())
			.firstName(userJsonObject.get("firstName").toString())
			.lastName(userJsonObject.get("lastName").toString())
			.build();
	}

	private YumaPreferencePOJO serializeYumaPreferencePOJO(YumaUserPOJO yumaUserPOJO) {
		return YumaPreferencePOJO.builder()
			.user(yumaUserPOJO)
			.additions(new ArrayList<>())
			.allergies(new ArrayList<>())
			.diet(new ArrayList<>())
			.generalPreference(new ArrayList<>())
			.other(new ArrayList<>())
			.build();
	}

	private void saveToHashMap(YumaPreferencePOJO yumaPreferencePOJO) {
		YumaUserPOJO yumaUserPOJO = yumaPreferencePOJO.getUser();
		usersCovered.put(yumaUserPOJO.getId(), yumaPreferencePOJO);
	}
}
