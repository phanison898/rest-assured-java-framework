package com.utils;

import com.github.javafaker.Faker;

public class DataUtil {

	public static Faker faker = new Faker();

	public static String getFirstName() {
		return faker.name().firstName();
	}

	public static String getLastName() {
		return faker.name().lastName();
	}

	public static String getUsername() {
		return faker.name().username();
	}

	public static String getEmail(String username) {
		return username + Integer.toString(JavaUtil.randomNumber(100, 1000)) + "@gmail.com";
	}

	public static String getEmail() {
		return getUsername() + Integer.toString(JavaUtil.randomNumber(100, 1000)) + "@gmail.com";
	}

	public static String getPassword(int passwordLength) {
		return JavaUtil.randomString(passwordLength);
	}

}
