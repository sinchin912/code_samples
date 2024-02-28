package com.hbg.rp.search.zendesk;

import com.hbg.rp.search.constants.ApplicationConstant;
import com.hbg.rp.search.constants.SearchConstant;
import com.hbg.rp.search.dto.ZendeskClaimDTO;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.springframework.stereotype.Component;

import javax.net.ssl.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * ZendeskServiceHelperImpl class
 * @author vladimir.iagoupov
 */
@Component
public class ZendeskServiceHelperImpl implements IZendeskServiceHelper {
	private final Log logger = LogFactoryUtil.getLog(this.getClass());
	
	private static final String TLS = "TLS";
	
	@Override
	public Client getSSLClient() throws Exception {
		logger.info(" getSSLClient() <<");

	    SSLContext sslcontext = SSLContext.getInstance(TLS);
	    sslcontext.init(null, new TrustManager[]{new X509TrustManager() {
	        public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}
	        public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}
	        public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0]; }
	    }}, new SecureRandom());

	    HostnameVerifier allowAll = new HostnameVerifier() {
	        @Override
	        public boolean verify(String hostname, SSLSession session) {
	            return true;
	        }
	    };

	    Client client = ClientBuilder.newBuilder()
        .sslContext(sslcontext)
        .hostnameVerifier(allowAll)
        .build();
	    
	    logger.info(" getSSLClient() >> client: " + client);
	    return client;
	}

	@Override
	public String getZendeskClaimDTOUpdatedSubject(ZendeskClaimDTO zendeskClaimDTO){
		logger.info(" getZendeskClaimDTOUpdatedSubject() <<");

		 if(zendeskClaimDTO == null){
		 	logger.info(" getZendeskClaimDTOUpdatedSubject() >> Returning null since zendeskClaimDTO is " + zendeskClaimDTO);
		 	return null;
		 }

		StringBuilder sb = new StringBuilder(SearchConstant.ZENDESK_SUBJECT);
		sb.append(ApplicationConstant.SPACE_STRING);
		sb.append(zendeskClaimDTO.getSubject());

		String zendeskClaimDTOUpdatedSubject = sb.toString();

		logger.info(" getZendeskClaimDTOUpdatedSubject() >> " + zendeskClaimDTOUpdatedSubject);
		return zendeskClaimDTOUpdatedSubject;
	}
}
