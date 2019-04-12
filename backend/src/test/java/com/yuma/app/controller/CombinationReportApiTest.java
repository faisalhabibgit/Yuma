//package com.yuma.app.controller;
//
//import com.yuma.app.to.CombinationReportTO;
//import com.yuma.app.to.MealTO;
//import com.yuma.app.to.UserTO;
//import io.restassured.module.mockmvc.RestAssuredMockMvc;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import javax.inject.Inject;
//import java.util.*;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest()
//public class CombinationReportApiTest {
//
//	@Inject
//	WebApplicationContext context;
//	
//	private CombinationReportTO combinationReportTO1, combinationReportTO2;
//	private String id = "123456hjg234";
//	private int numberOfBlanks = 1;
//	private int combinationScore = 4;
//	private Date createdOn = new Date();
//	private List<UserTO> userTOS;
//	private List<MealTO> mealTOS;
//	private MealTO mealTO1;
//	private UserTO consumerTo1;
//
//
//
//	@Before
//	public void setup() {
//		RestAssuredMockMvc.mockMvc(MockMvcBuilders.webAppContextSetup(context).build());	
//		userTOS = new ArrayList<>();
//		mealTOS = new ArrayList<>();
//		mealTO1 = new MealTO(UUID.randomUUID().toString(), "chicken", "chicken", false, new HashSet<>(), 30, new ArrayList<>());
//		mealTOS.add(mealTO1);
//		consumerTo1 = prepareUserTO1();
//		combinationReportTO1 = new CombinationReportTO(id,numberOfBlanks,combinationScore, createdOn, new ArrayList<>(), mealTOS);
//
//		RestAssuredMockMvc.
//			given().
//			contentType("application/json").
//			body(combinationReportTO1).
//			when().
//			post("/api/combinationreport/weeklycombo/1").
//			then().
//			statusCode(200);
//	}
//	
//	@Test
//	public void getWeeklCombo(){
//		RestAssuredMockMvc
//			.given()
//			.when()
//			.get("/api/combinationreport/weeklycombo")
//			.then()
//			.statusCode(200);
//		
//	}
//	
//	public UserTO prepareUserTO1() {
//		UserTO userTO = new UserTO();
//		userTO.setUserId("id");
//		userTO.setFirstName("first name");
//		userTO.setLastName("last name");
//		userTO.setPassword("password");
//		userTO.setEnabled(true);
//		userTO.setEmail("test@email.com");
//
//		return userTO;
//	}
//}
