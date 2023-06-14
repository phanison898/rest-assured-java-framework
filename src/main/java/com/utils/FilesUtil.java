package com.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FilesUtil {

	public static String readTextFile(String path) {
		String output = null;

		try {
			output = new String(Files.readAllBytes(Paths.get(path)));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return output;
	}
}
