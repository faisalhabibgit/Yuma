package com.yuma.app.updater;

import static org.springframework.util.CollectionUtils.isEmpty;

import java.io.BufferedReader;
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


	public static void main(String[] args){
		fetchYumaUsers();
	}

	public static void fetchYumaUsers(){
		OkHttpClient client = new OkHttpClient();
		JSONParser jsonParser = new JSONParser();

		Request request = new Request.Builder()
			.url("http://itsyuma.com/preferences")
			.get()
			.addHeader("Content-Type", "application/json")
			.addHeader("cache-control", "no-cache")
			.addHeader("Postman-Token", "eda36bdd-ef2e-4a77-8cad-c0a78224a8cb")
			.build();

		try {
			Response response = client.newCall(request).execute();
			if (response.code() == 200) {
				BufferedReader br = new BufferedReader(new InputStreamReader(response.body().byteStream()));
				Object obj = jsonParser.parse(br);

				JSONObject jsonObject = (JSONObject) obj;
				JSONArray jsonArray = (JSONArray) jsonObject.get("data");
				
				for (Object jsonArrayIndex: jsonArray){
					JSONObject preferenceJsonObject = (JSONObject) jsonArrayIndex;
					JSONObject userJsonObject = (JSONObject) preferenceJsonObject.get("user");
					JSONObject groupJsonObject = (JSONObject) userJsonObject.get("group");
					String userId = userJsonObject.get("_id").toString();
					if (usersCovered.containsKey(userId)){
						YumaPreferencePOJO yumaPreferencePOJO = usersCovered.get(userId);
						String preferenceKeyType = preferenceJsonObject.get("key").toString();
						String value = preferenceJsonObject.get("value").toString();
						switch (preferenceKeyType) {
							case "general":
								yumaPreferencePOJO.getGeneralPreference().add(value);
								break;case "diet": yumaPreferencePOJO.getDiet().add(value);
								break;case "allergy": yumaPreferencePOJO.getAllergies().add(value);
								break;case "addition": yumaPreferencePOJO.getAdditions().add(value);
								break;case "other": yumaPreferencePOJO.getOther().add(value);
								break;case "plan": yumaPreferencePOJO.setNumberOfMealsPerWeek(Integer.parseInt(value)); } } else {
						if (!isEmpty(groupJsonObject)) {
							YumaGroupPOJO yumaGroupPOJO = YumaGroupPOJO.builder()
								.alias(groupJsonObject.get("alias").toString())
								.build();
							YumaUserPOJO yumaUserPOJO = YumaUserPOJO.builder()
								.id(userId)
								.firstName(userJsonObject.get("firstName").toString())
								.lastName(userJsonObject.get("lastName").toString())
								.yumaGroupPOJO(yumaGroupPOJO)
								.build();
							YumaPreferencePOJO yumaPreferencePOJO = YumaPreferencePOJO.builder()
								.additions(new ArrayList<>())
								.allergies(new ArrayList<>())
								.diet(new ArrayList<>())
								.generalPreference(new ArrayList<>())
								.other(new ArrayList<>())
								.user(yumaUserPOJO)
								.build();
							usersCovered.put(userId,yumaPreferencePOJO); } } }int num=0;
				for (YumaPreferencePOJO yumaPreferencePOJO: usersCovered.values()){
					System.out.print(yumaPreferencePOJO.getNumberOfMealsPerWeek()+",");
					System.out.print(yumaPreferencePOJO.getUser().getFirstName() +",");
					System.out.print(yumaPreferencePOJO.getUser().getLastName() +",");
					System.out.println();
					num++;
				}
				System.out.println(num);
			}
		}catch (Exception e){
			e.printStackTrace();
		}

	}
}
