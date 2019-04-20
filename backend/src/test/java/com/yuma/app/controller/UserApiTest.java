package com.yuma.app.controller;

import com.yuma.app.HelperCombo;
import com.yuma.app.to.ConsumerTO;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class UserApiTest {
	@Inject
	WebApplicationContext context;
	private HelperCombo helper;
	private ConsumerTO consumerTo1, consumerTo2;
	private String userId = "pl";
	private String fName = "John";
	private String lName = "Smith";
	private String email = "john.smith@gmail.com";
	private String password = "password";
	private String companyName = "google";


	private String userId1 = "pl";
	@Before
	public void setUp() throws Exception {

	consumerTo1 = prepareUserTO1();
		RestAssuredMockMvc.mockMvc(MockMvcBuilders.webAppContextSetup(context).build());

		RestAssuredMockMvc.
			given().
			when().
			delete("api/rest/{userId1}",userId1 ).
			then().
			statusCode(200);

		RestAssuredMockMvc.
			given().
			contentType("application/json").
			body(consumerTo1).
			when().
			post("/api/rest").
			then().
			statusCode(200);
	}

	@Test
	public void getUserTest(){
			RestAssuredMockMvc.
			given().
			when().
			get("/api/rest/all").
			then().
			statusCode(200);
	}

	@After
	public void release(){

		RestAssuredMockMvc.
			given().
			when().
			delete("api/rest/{userId1}",userId1 ).
			then().
			statusCode(200);
	}

	public ConsumerTO prepareUserTO1() {
		ConsumerTO userTO = new ConsumerTO();
		userTO.setUserId("id");
		userTO.setFirstName("first name");
		userTO.setLastName("last name");
		userTO.setPassword("password");
		userTO.setEnabled(true);
		userTO.setEmail("test@email.com");

		return userTO;
	}
}
