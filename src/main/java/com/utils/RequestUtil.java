package com.utils;

import java.util.HashMap;
import java.util.Map;

public class RequestUtil {

	public static Map<String, String> generateBody() {

		String username = DataUtil.getUsername();
		String password = DataUtil.getPassword(8);

		Map<String, String> json = new HashMap<String, String>();
		json.put("username", username);
		json.put("email", DataUtil.getEmail(username));
		json.put("password", password);
		json.put("confirmPassword", password);

		return json;
	}

}
