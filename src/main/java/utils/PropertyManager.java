package utils;

import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {
	
	private static Properties configProp;
	private static Properties testDataProp;
	
	public static Properties getConfig() {
		if(configProp == null) {
			configProp= loadProperty("config.properties");
		}
		return configProp;
	}
	
	public static Properties getTestData() {
		if(testDataProp == null) {
			testDataProp= loadProperty("testData.properties");
		}
		return testDataProp;
	}
	
	
	private static Properties loadProperty(String fileName) {
		try {
			Properties prop= new Properties();
			InputStream input= PropertyManager.class
					.getClassLoader().getResourceAsStream(fileName);
			if(input == null) {
				throw new RuntimeException("File not found in classPath : " + fileName);
			}
			prop.load(input);
			return prop;
		}catch (Exception e) {
			throw new RuntimeException("Failed to load property file" + fileName, e);
		}
	}

}
