package com.testcases;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.hamcrest.core.IsEqual;
import org.testng.annotations.Test;

import com.utils.RequestUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class GetTest {

	private final String BASE_URL = "http://localhost:5000";
	
	@Test
	public void getAllUsers() {
		given()
			.baseUri(BASE_URL)
		.when()
			.get()
		.then()
			.assertThat()
			.statusCode(200);
	}
	
//	@Test
	public void getUserByID() {
		
		String _id = "6489fda91f0a53c2ec20f354";
		String endpoint = "/users/"+_id;
		
		given()
			.baseUri(BASE_URL)
		.when()
			.get(endpoint)
		.then()
			.assertThat()
			.statusCode(200)
			.body("username", IsEqual.equalTo(""))
			.body("email", IsEqual.equalTo(""))
			.body("password", IsEqual.equalTo(""));
	}
	
	@Test
	public void postUser() {
		RestAssured.baseURI = BASE_URL;
		
		Map<String, String> body = RequestUtil.generateBody();
		
		given()
			.baseUri(BASE_URL)
			.contentType(ContentType.JSON)
			.body(body)
			.log()
			.body()
		.when()
			.post("/users")
		.then()
			.assertThat()
			.statusCode(201)
			.body("username", IsEqual.equalTo(body.get("username")))
			.body("email", IsEqual.equalTo(body.get("email")))
			.body("password", IsEqual.equalTo(body.get("password")));
			
	}
}
