package com.weather.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationProperties {
	
	private String configFileName = "application.properties";
	private Properties properties;



	public ApplicationProperties() throws IOException {

		InputStream inputStream = null;
		properties = new Properties();
		
		inputStream = getClass().getClassLoader().getResourceAsStream(configFileName);
		
		if (inputStream != null) {
			properties.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + configFileName + "' not found in the classpath");
		}
		
	}
	
	public String getPropertyFileValue(String key) {
		String paramValue = properties.getProperty(key);
		return paramValue;
	}
}
