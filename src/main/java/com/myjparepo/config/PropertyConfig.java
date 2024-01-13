package main.java.com.myjparepo.config;

import java.io.FileInputStream;
import java.util.Properties;

import main.java.com.myjparepo.contant.RepositoryContants;

public class PropertyConfig {

	private PropertyConfig() {

	}

	private static Properties applicationProperties = null;

	static {
		try {
			FileInputStream fileStream = new FileInputStream(RepositoryContants.APPLICATION_PROP_PATH);
			applicationProperties = new Properties();
			applicationProperties.load(fileStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getApplicationProperty(String key) {
		return applicationProperties.getProperty(key);
	}

}
