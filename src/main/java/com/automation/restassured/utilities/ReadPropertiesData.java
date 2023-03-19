package com.automation.restassured.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertiesData {
	
	static FileInputStream fis;
	static Properties prop;
	
	public static void loadPropertiesFile() {
		try {
			fis = new FileInputStream("data.properties");
			prop = new Properties();
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static String readData(String key) {
		if(prop.containsKey(key)) {
			return prop.getProperty(key);
		}
		return null;
	}
	
//	static {
//		try {
//			fis = new FileInputStream("data.properties");
//			Properties prop = new Properties();
//			prop.load(fis);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

}
