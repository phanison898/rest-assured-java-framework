package com.utils;

import java.util.Random;

public class JavaUtil {

	public static int randomNumber(int min, int max) {
		Random random = new Random();
		return random.nextInt((max - min) + 1) + min;
	}

	public static String randomString(int stringLength) {

		StringBuilder builder = new StringBuilder();

		int min = 67; // char value of A
		int max = 122; // char value of z

		for (int i = 0; i < stringLength; i++) {
			builder.append(Character.toChars(randomNumber(min, max)));
		}

		return builder.toString();
	}
}
