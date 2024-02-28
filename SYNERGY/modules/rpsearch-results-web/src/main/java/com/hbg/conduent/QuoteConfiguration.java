package com.hbg.conduent;

import org.apache.ws.security.WSConstants;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;

import com.hbg.conduent.log.LogHttpHeaderClientInterceptor;
import com.hbg.rp.search.constants.ApplicationPropertyReader;

/**
 * The com.hbg.conduent.QuoteConfiguration is the configuration class that
 * provides the QuoteClient instance.
 * 
 * @author Gurpreet.Singh
 */
public class QuoteConfiguration {

	private static QuoteClient quoteClient;
	private static String conduentUsername = ApplicationPropertyReader.getProperty("conduent-username");
	private static String conduentPassword = ApplicationPropertyReader.getProperty("conduent-password");
	private static String conduentURL = ApplicationPropertyReader.getProperty("conduent-url");
	
	private QuoteConfiguration() {

	}

	public static Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		// This package must match the package in the <generatePackage>
		// specified in pom.xml.
		marshaller.setContextPath("com.hbg.conduent.wsdl");
		return marshaller;
	}

	/**
	 * This method is to retrieve single instance of QuoteClient.
	 * 
	 * @return QuoteClient
	 */
	public static QuoteClient getClientInstance() {
		if (quoteClient == null) {
			quoteClient = quoteClient();
		}
		return quoteClient;
	}

	/**
	 * This private method is used to prepare the object of QuoteClient.
	 * 
	 * @return QuoteClient
	 */
	private static QuoteClient quoteClient() {
		Jaxb2Marshaller marshaller = marshaller();
		QuoteClient client = new QuoteClient();
		client.setDefaultUri(conduentURL);
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		ClientInterceptor[] interceptors = new ClientInterceptor[] { securityInterceptor(),
				new LogHttpHeaderClientInterceptor() };
		client.setInterceptors(interceptors);
		return client;
	}

	/**
	 * This private method is used to prepare the object of SecurityInterceptor.
	 * Called above in method quoteClient().
	 * 
	 * @return Wss4jSecurityInterceptor
	 */
	private static Wss4jSecurityInterceptor securityInterceptor() {
		Wss4jSecurityInterceptor wss4jSecurityInterceptor = new Wss4jSecurityInterceptor();
		wss4jSecurityInterceptor.setSecurementActions("Timestamp UsernameToken");
		wss4jSecurityInterceptor.setSecurementUsername(conduentUsername);
		wss4jSecurityInterceptor.setSecurementPassword(conduentPassword);
		wss4jSecurityInterceptor.setSecurementPasswordType(WSConstants.PW_TEXT);
		wss4jSecurityInterceptor.setSecurementUsernameTokenCreated(true);
		wss4jSecurityInterceptor.setSecurementUsernameTokenNonce(true);
		return wss4jSecurityInterceptor;
	}

}
