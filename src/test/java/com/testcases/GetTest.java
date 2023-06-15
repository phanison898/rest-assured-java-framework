package com.testcases;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.hamcrest.core.IsEqual;
import org.testng.annotations.Test;

import com.utils.RequestUtil;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetTest {

	private final String BASE_URL = "http://localhost:5000";
	private JsonPath jsonPath;
	
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
	
	@Test(dependsOnMethods = {"postUser"} )
	public void getUserByID() {
		
		String id = jsonPath.getString("_id");
		String username = jsonPath.getString("username");
		String email = jsonPath.getString("email");
		
		given()
			.baseUri(BASE_URL)
			.pathParam("id", id)
		.when()
			.get("/users/{id}")
		.then()
			.assertThat()
			.body("username", IsEqual.equalTo(username))
			.body("email", IsEqual.equalTo(email));
	}
	
	@Test
	public void postUser() {
		
		Map<String, String> body = RequestUtil.generateBody();
		
	Response response = 
		given()
			.baseUri(BASE_URL)
			.contentType(ContentType.JSON)
			.body(body)
		.when()
			.post("/users")
		.then()
			.assertThat()
			.statusCode(201)
			.body("username", IsEqual.equalTo(body.get("username")))
			.body("email", IsEqual.equalTo(body.get("email")))
			.body("password", IsEqual.equalTo(body.get("password")))
			.extract()
			.response();
	
	jsonPath = response.body().jsonPath();
			
	}
}
