package com.hbg.rp.search.constants;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The com.hbg.rp.constant.ApplicationPropertyReader is the reader class
 * for properties file application.properties.
 * 
 * @author ravi.kumar
 *
 */
public class ApplicationPropertyReader {
	
	private static final Log logger = LogFactoryUtil.getLog(ApplicationPropertyReader.class);
	
	private static final Properties properties = new Properties();
	
	/**
	 * Private constructor. To ensure not instantiated outside class.
	 */
	private ApplicationPropertyReader() {
		/** Empty private constructor **/
	}
	
	static {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream stream = loader
				.getResourceAsStream("application.properties");
		try {
			properties.load(stream);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	/**
	 * Gets the specific property of key passed.
	 * 
	 * @param key Key of property to be fetched.
	 * @return
	 */
	public static String getProperty(String key) {
		String value = properties.getProperty(key);
		logger.debug("Reading value for " + key + ": " + value);
		return value;
	}

}