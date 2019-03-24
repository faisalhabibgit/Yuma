package com.yuma.app.updater;

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

	private static final String endpoint = "http://itsyuma.com/preferences";


	public static void main(String[] args){
		fetchYumaUsers();
	}

	public static void fetchYumaUsers(){
		HashMap<String, YumaPreferencePOJO> usersCovered = new HashMap<>();
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

				for (Object key : jsonArray){
					System.out.println(key);
					System.out.println();
					for (int i=0; i < jsonArray.size(); i++){
						JSONObject preferenceObject = (JSONObject) jsonArray.get(i);
						String userId = preferenceObject.get("user._id").toString();
						if (usersCovered.containsKey(userId)){
							YumaPreferencePOJO yumaPreferencePOJO = usersCovered.get(userId);
							String preferenceKeyType = preferenceObject.get("key").toString();
							String value = preferenceObject.get("value").toString();
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
							}
						}
						else {
							YumaGroupPOJO yumaGroupPOJO = YumaGroupPOJO.builder()
								.id(preferenceObject.get("user.group._id").toString())
								.alias(preferenceObject.get("user.group.alias").toString())
								.build();

							YumaUserPOJO yumaUserPOJO = YumaUserPOJO.builder()
								.id(preferenceObject.get("user._id").toString())
								.firstName((String) preferenceObject.get("user.firstName"))
								.lastName((String) preferenceObject.get("user.lastName"))
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
							usersCovered.put(userId,yumaPreferencePOJO);
						}
					}
				}
//				JSONArray preferencesList = new JSONArray();
//				String[] objects = preferencesList.toJSONString().split("object");
//				for(String s: objects){
//					System.out.println(s);
//					System.out.println();
//				}
				
			}
//			System.out.println("Output from Server .... \n");
//			while ((output = br.readLine()) != null) {
//				System.out.println(" ");
//				System.out.println(output);
//			}
		}catch (Exception e){
			e.printStackTrace();
		}

	}
}
