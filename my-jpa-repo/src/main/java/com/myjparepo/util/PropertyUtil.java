package main.java.com.myjparepo.util;

import java.io.FileInputStream;
import java.util.Properties;

import main.java.com.myjparepo.contant.RepositoryContants;

/**
 * The Class PropertyUtil is used to read and get property from loaded property files.
 */
public class PropertyUtil {

	/**
	 * Instantiates a new property util.
	 */
	private PropertyUtil() {

	}

	/** The application properties. */
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

	/**
	 * Gets the application property.
	 *
	 * @param key the key
	 * @return the application property
	 */
	public static String getApplicationProperty(String key) {
		return applicationProperties.getProperty(key);
	}

}
