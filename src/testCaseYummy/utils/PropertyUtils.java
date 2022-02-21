package testCaseYummy.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Properties;

import zmq.ZError.IOException;

public class PropertyUtils {
	
	public static Properties propertyLoader(String filePath) throws java.io.IOException {
		Properties properties = new Properties();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(filePath));
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("failed to load properties file " + filePath);
			}
		
		} catch (FileNotFoundException e) {
			throw new RuntimeException("properties file not found at " + filePath);
		}
		
		return properties;
	}
	
}
