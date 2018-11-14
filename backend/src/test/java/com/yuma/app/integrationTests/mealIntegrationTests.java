//package com.yuma.app.integrationTests;
//
//import com.yuma.app.Application;
//
//import io.restassured.RestAssured;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import static io.restassured.RestAssured.given;
//import static io.restassured.RestAssured.when;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootApplication()
//@WebAppConfiguration
//public class mealIntegrationTests {
//	
//
//	@Before
//	public void setup() {
//		RestAssured.baseURI = "localhost";
//		RestAssured.port = 8080;
//	}
//	
//	@Test
//	public void getMealsTest() {
//		when().request("GET", "/meals").then().statusCode(200);
////			when().get("/meals").then().statusCode(200);
//	}
//
//}
