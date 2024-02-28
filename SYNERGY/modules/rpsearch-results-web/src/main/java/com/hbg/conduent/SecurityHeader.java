package com.hbg.conduent;

import java.io.IOException;
import java.util.UUID;

import javax.xml.namespace.QName;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.TransformerException;

import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.soap.saaj.SaajSoapMessage;

import com.hbg.rp.search.constants.ApplicationPropertyReader;

/**
 * The com.hbg.conduent.SecurityHeader is the class which helps in adding SOAP headers.
 * 
 * @author Gurpreet.Singh
 */
public class SecurityHeader implements WebServiceMessageCallback {

	private String conduentURL = ApplicationPropertyReader
			.getProperty("conduent-url");
	private String conduentServiceURL = ApplicationPropertyReader
			.getProperty("conduent-service-url");
	private String conduentNamespaceRoleUri = ApplicationPropertyReader
			.getProperty("namespace-uri-role");

	private String action;

	public SecurityHeader(String action) {
		this.action = action;
	}
	
	/**
	 * This method adds the SOAP Headers to the message. 
	 * 
	 * @see org.springframework.ws.client.core.WebServiceMessageCallback#doWithMessage(org.springframework.ws.WebServiceMessage)
	 */
    @Override
    public void doWithMessage(WebServiceMessage message) throws IOException, TransformerException {
    	String prefix = "wsa";
        String namespaceURI = ApplicationPropertyReader.getProperty("namespace-uri");
        try {
        	SOAPMessage soapMessage = ((SaajSoapMessage)message).getSaajMessage();
	        SOAPHeader soapHeader = soapMessage.getSOAPHeader();
	        soapHeader.addHeaderElement(new QName(namespaceURI, "Action", prefix)).setTextContent(conduentServiceURL+action);
	        soapHeader.addHeaderElement(new QName(namespaceURI, "MessageID", prefix)).setTextContent("urn:uuid:" + UUID.randomUUID());
	        soapHeader.addHeaderElement(new QName(namespaceURI, "To", prefix)).setTextContent(conduentURL);
	        SOAPElement replyTo = soapHeader.addHeaderElement(new QName(namespaceURI, "ReplyTo", prefix));
	        replyTo.addChildElement(new QName(namespaceURI, "Address", prefix)).setTextContent(conduentNamespaceRoleUri);
	        MimeHeaders mimeHeaders = soapMessage.getMimeHeaders(); 
	        mimeHeaders.setHeader("SOAPAction", conduentServiceURL + action+"");
        } catch (SOAPException ee) {
            throw new IOException("error while marshalling authentication."+ee);
        }
    }
    
}