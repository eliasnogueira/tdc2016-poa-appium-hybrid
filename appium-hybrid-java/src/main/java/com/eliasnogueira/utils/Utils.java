package com.eliasnogueira.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utils {

	
	public static String loadProperties(String property) {
		String value = "";
		
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("config/config.properties")));
			value = prop.getProperty(property);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return value;
	}
	
}
