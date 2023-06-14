package com.base;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import org.hamcrest.core.IsEqual;

public class Base {

	public static void main(String[] args) {
		RestAssured.baseURI = "http://localhost:5000";

		given()
			.get("/users/6488a94bc095a7f45293ab6d")
			.then()
			.assertThat()
			.statusCode(200)
			.body("username", IsEqual.equalTo("walter"))
			.body("email", IsEqual.equalTo("walter@gmail.com"))
			.body("password", IsEqual.equalTo("12345678"));
	}

}
