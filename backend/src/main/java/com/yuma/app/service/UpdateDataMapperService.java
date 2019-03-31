package com.yuma.app.service;

import static com.yuma.app.document.enums.Allergens.DAIRY;
import static com.yuma.app.document.enums.Allergens.GLUTEN;
import static com.yuma.app.document.enums.Allergens.PEANUT;
import static com.yuma.app.document.enums.Allergens.SOY;
import static com.yuma.app.document.enums.Allergens.TREE_NUTS;
import static com.yuma.app.document.enums.Diet.HALAL;
import static com.yuma.app.document.enums.Diet.KOSHER;
import static com.yuma.app.document.enums.ProteinType.BEEF;
import static com.yuma.app.document.enums.ProteinType.FISH;
import static com.yuma.app.document.enums.ProteinType.LAMB;
import static com.yuma.app.document.enums.ProteinType.PLANT_BASED_PROTEIN;
import static com.yuma.app.document.enums.ProteinType.PORK;
import static com.yuma.app.document.enums.ProteinType.POULTRY;
import static com.yuma.app.document.enums.ProteinType.SHELLFISH;
import static com.yuma.app.document.enums.ProteinType.VEGAN;
import static com.yuma.app.document.enums.ProteinType.VEGETARIAN;
import static com.yuma.app.document.enums.SpecialRequest.LARGE_PORTION;
import static com.yuma.app.document.enums.SpecialRequest.LOW_CARBS;
import static com.yuma.app.document.enums.SpecialRequest.NONE;
import static org.reflections.util.Utils.isEmpty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuma.app.document.Consumer;
import com.yuma.app.document.Plan;
import com.yuma.app.document.enums.Allergens;
import com.yuma.app.document.enums.Diet;
import com.yuma.app.document.enums.ProteinType;
import com.yuma.app.document.enums.SpecialRequest;
import com.yuma.app.repository.UserRepository;
import com.yuma.app.updater.UpdateUsers;
import com.yuma.app.updater.YumaGroupPOJO;
import com.yuma.app.updater.YumaPreferencePOJO;
import com.yuma.app.updater.YumaUserPOJO;

@Slf4j
@Service
public class UpdateDataMapperService {

	private UpdateUsers updateUsers;
	private UserRepository userRepository;

	@Autowired
	public UpdateDataMapperService(UpdateUsers updateUsers, UserRepository userRepository) {
		this.updateUsers = updateUsers;
		this.userRepository = userRepository;
	}

	public void mapAndSaveUsers(){
		List<Consumer> updatedConsumersList = new ArrayList<>();
		HashMap<String, YumaPreferencePOJO> yumaPreferencePOJOHashMap = updateUsers.fetchYumaUsers();

		log.info("mapping users from Yuma Server to local Objects");
		for(String  yumaPOJOUserId: yumaPreferencePOJOHashMap.keySet()) {
			YumaPreferencePOJO yumaPreferencePOJO = yumaPreferencePOJOHashMap.get(yumaPOJOUserId);
			Consumer consumer = mapYumaPreferenceToConsumer(yumaPreferencePOJO);
			consumer.setPlan(mapYumaPreferenceToPlan(yumaPreferencePOJO));
			consumer.setCompany(mapYumaGroupToCompany(yumaPreferencePOJO.getUser()));
			updatedConsumersList.add(consumer);
		}

		log.info("saving updated users");
		userRepository.save(updatedConsumersList);
	}

	private String mapYumaGroupToCompany(YumaUserPOJO user) {
		YumaGroupPOJO yumaGroupPOJO = user.getYumaGroupPOJO();

		return yumaGroupPOJO.getAlias();
	}

	private Consumer mapYumaPreferenceToConsumer(YumaPreferencePOJO yumaPreferencePOJO) {
		YumaUserPOJO yumaUserPOJO = yumaPreferencePOJO.getUser();

		return Consumer.builder()
			.allergies(mapAllergyTypesIfExists(yumaPreferencePOJO.getAllergies()))
			.consumerComments(yumaPreferencePOJO.getOther())
			.firstName(yumaUserPOJO.getFirstName())
			.lastName(yumaUserPOJO.getLastName())
			.userId(yumaUserPOJO.getId())
			.email("")
			.dislikesList(new ArrayList<>())
			.likes(new ArrayList<>())
			.isActive(true)
			.build();
	}

	private Set<Allergens> mapAllergyTypesIfExists(List<String> allergiesPojo) {
		HashSet<Allergens> userAllergies = new HashSet<>();
		if (!allergiesPojo.isEmpty()) {
			for (String allergy : allergiesPojo) {
				switch (allergy.toUpperCase()) {
					case "DAIRY":
						userAllergies.add(DAIRY);
						break;
					case "GLUTEN":
						userAllergies.add(GLUTEN);
						break;
					case "PEANUTS":
						userAllergies.add(PEANUT);
						break;
					case "SHELLFISH":
						userAllergies.add(Allergens.SHELLFISH);
						break;
					case "SOY":
						userAllergies.add(SOY);
						break;
					case "TREE-NUTS":
						userAllergies.add(TREE_NUTS);
						break;
					default:
				}
			}
		}
		return userAllergies;
	}

	private Plan mapYumaPreferenceToPlan(YumaPreferencePOJO yumaPreferencePOJO) {
		Plan consumerPlan = Plan.builder()
			.numOfMeals(yumaPreferencePOJO.getNumberOfMealsPerWeek())
			.diet(mapDietTypesIfExists(yumaPreferencePOJO.getDiet()))
			.requestedProteinTypes(mapRequestedProteinTypesIfExists(yumaPreferencePOJO.getProteinTypes()))
			.specialRequests(mapSpecialRequestsTypesIfExists(yumaPreferencePOJO.getSpecialRequests()))
			.build();

		return consumerPlan; 
	}

	private Set<SpecialRequest> mapSpecialRequestsTypesIfExists(List<String> specialRequestsPojo) {
		HashSet<SpecialRequest> specialRequests = new HashSet<>();
		if (!specialRequests.isEmpty()) {
			for (String request : specialRequestsPojo) {
				switch (request.toUpperCase()) {
					case "LOW-CARBS":
						specialRequests.add(LOW_CARBS);
						break;
					case "LARGE-PORTION":
						specialRequests.add(LARGE_PORTION);
						break;
					default:
						specialRequests.add(NONE);
				}
			}
		}
		return specialRequests;
	}

	private Set<ProteinType> mapRequestedProteinTypesIfExists(List<String> proteinTypesPojo) {
		HashSet<ProteinType> proteinTypesHashSet = new HashSet<>();
		if (!proteinTypesPojo.isEmpty()) {
			for (String proteinType : proteinTypesPojo) {
				switch (proteinType.toUpperCase()) {
					case "BEEF":
						proteinTypesHashSet.add(BEEF);
						break;
					case "FISH":
						proteinTypesHashSet.add(FISH);
						break;
					case "LAMB":
						proteinTypesHashSet.add(LAMB);
						break;
					case "PLANT-BASED-PROTEIN":
						proteinTypesHashSet.add(PLANT_BASED_PROTEIN);
						break;
					case "PORK":
						proteinTypesHashSet.add(PORK);
						break;
					case "POULTRY":
						proteinTypesHashSet.add(POULTRY);
						break;
					case "SHELLFISH":
						proteinTypesHashSet.add(SHELLFISH);
						break;
					case "VEGAN":
						proteinTypesHashSet.add(VEGAN);
						break;
					case "VEGETARIAN":
						proteinTypesHashSet.add(VEGETARIAN);
						break;
					default:
				}
			}
		}

		return proteinTypesHashSet;
	}

	private Diet mapDietTypesIfExists(String dietPojo) {
		if (!isEmpty(dietPojo)) {
			switch (dietPojo.toUpperCase()) {
				case "HALAL":
					return HALAL;
				case "KOSHER":
					return KOSHER;
			}
		}

		return Diet.NONE;
	}
}
