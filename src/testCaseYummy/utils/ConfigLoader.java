package testCaseYummy.utils;

import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
	private final Properties properties;
	private static ConfigLoader configLoader;

	private ConfigLoader() throws IOException {
		properties = PropertyUtils.propertyLoader("src/config.properties");
}

	public static ConfigLoader getInstance() throws IOException{
		if(configLoader == null) {
			configLoader = new ConfigLoader();
		}
		return configLoader;
	}
	
	public String getBaseUrl() {
		String prop = properties.getProperty("baseUrl");
		if(prop != null) return prop;
		else throw new RuntimeException("property baseUrl is not specified in config.properties");
	}
	
	public String getUsername() {
		String prop = properties.getProperty("username");
		if(prop != null) return prop;
		else throw new RuntimeException("property username is not specified in config.properties");
	}
	
	public String getUserPhone() {
		String prop = properties.getProperty("userPhone");
		if(prop != null) return prop;
		else throw new RuntimeException("property userPhone is not specified in config.properties");
	}
	
	public String getUserEmail() {
		String prop = properties.getProperty("email");
		if(prop != null) return prop;
		else throw new RuntimeException("property email is not specified in config.properties");
	}
	
	public String getUserRoomDetail() {
		String prop = properties.getProperty("roomDetail");
		if(prop != null) return prop;
		else throw new RuntimeException("property roomDetail is not specified in config.properties");
	}
	
	public String getPaymentMethod() {
		String prop = properties.getProperty("paymentMethod");
		if(prop != null) return prop;
		else throw new RuntimeException("property paymentMethod is not specified in config.properties");
	}
}
