package com.yuma.app.updater;

import static org.springframework.util.CollectionUtils.isEmpty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import lombok.extern.slf4j.Slf4j;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Slf4j
@Service
public class UpdateUsers {

	private Logger updateUsersLogger = LoggerFactory.getLogger(UpdateUsers.class);
	private static final String endpoint = "http://itsyuma.com/preferences";
	private HashMap<String, YumaPreferencePOJO> fetchedUsersMap;

	public UpdateUsers() {
		this.fetchedUsersMap = new HashMap<>();
	}

	public HashMap<String, YumaPreferencePOJO> fetchYumaUsers(){
		updateUsersLogger.info("fetching users from Yuma servers");
		BufferedReader responseBufferedReader = makeCall();
		JSONArray jsonArray = getJsonArray(responseBufferedReader);

		return parseThroughPreferences(jsonArray);
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
			updateUsersLogger.info("fetched users from Yuma servers Successfully");

			return new BufferedReader(new InputStreamReader(response.body().byteStream()));
		}
		catch (IOException e){
			updateUsersLogger.error("error while fetching users from Yuma servers");
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

	private HashMap<String, YumaPreferencePOJO> parseThroughPreferences(JSONArray preferencesArray){
		for (Object jsonObject : preferencesArray){
			JSONObject preferenceJsonObject = (JSONObject) jsonObject;
			JSONObject userJsonObject = (JSONObject) preferenceJsonObject.get("user");
			String userId = userJsonObject.get("_id").toString();

			if (fetchedUsersMap.containsKey(userId)){
				updatePreferencePOJO(fetchedUsersMap.get(userId), preferenceJsonObject);
			}
			else if(checkIfUserHasGroup(userJsonObject)) {
				saveToHashMap(fetchedUsersMap, serializeObjects(userJsonObject));
			}
		}

		return fetchedUsersMap;
	}

	private boolean checkIfUserHasGroup(JSONObject userJsonObject) {
		return !isEmpty(parseGroupJsonObject(userJsonObject));
	}

	private JSONObject parseGroupJsonObject(JSONObject userJsonObject){
		return (JSONObject) userJsonObject.get("group");
	}

	private void updatePreferencePOJO(YumaPreferencePOJO yumaPreferencePOJO, JSONObject preferenceJsonObject) {
		String preferenceKeyType = preferenceJsonObject.get("key").toString();
		String value = preferenceJsonObject.get("value").toString();
		switch (preferenceKeyType) {
			case "general":
				yumaPreferencePOJO.getProteinTypes().add(value);
				break;
			case "diet":
				yumaPreferencePOJO.setDiet(value);
				break;
			case "allergy":
				yumaPreferencePOJO.getAllergies().add(value);
				break;
			case "addition":
				yumaPreferencePOJO.getSpecialRequests().add(value);
				break;
			case "plan":
				yumaPreferencePOJO.setNumberOfMealsPerWeek(Integer.parseInt(value));
				break;
			case "other":
				yumaPreferencePOJO.getOther().add(value);
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
			.proteinTypes(new ArrayList<>())
			.allergies(new ArrayList<>())
			.specialRequests(new ArrayList<>())
			.other(new ArrayList<>())
			.build();
	}

	private void saveToHashMap(HashMap<String, YumaPreferencePOJO> fetchedUsersMap, YumaPreferencePOJO yumaPreferencePOJO) {
		YumaUserPOJO yumaUserPOJO = yumaPreferencePOJO.getUser();
		fetchedUsersMap.put(yumaUserPOJO.getId(), yumaPreferencePOJO);
	}
}
