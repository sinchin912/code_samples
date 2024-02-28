package com.hbg.conduent.log;

import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.context.MessageContext;

/**
 * The com.hbg.conduent.log.LogHttpHeaderClientInterceptor is the Interceptor
 * used for logging HTTP Headers.
 * 
 * @author Gurpreet.Singh
 *
 */
public class LogHttpHeaderClientInterceptor implements ClientInterceptor {
	
	/**
	 * Callback after completion of request and response (fault) processing. Will be called on any outcome, thus
	 * allows for proper resource cleanup.
	 * 
	 * (non-Javadoc)
	 * @see org.springframework.ws.client.support.interceptor.ClientInterceptor#afterCompletion(org.springframework.ws.context.MessageContext, java.lang.Exception)
	 */
	@Override
	public void afterCompletion(MessageContext arg0, Exception arg1)
			throws WebServiceClientException {
		// No-op
	}
	
	/**
	 * Processes the incoming response fault. Called for response fault messages before payload handling in the {@link
	 * org.springframework.ws.client.core.WebServiceTemplate}.
	 * 
	 * @see org.springframework.ws.client.support.interceptor.ClientInterceptor#handleFault(org.springframework.ws.context.MessageContext)
	 */
	@Override
	public boolean handleFault(MessageContext messageContext)
			throws WebServiceClientException {
		// No-op
		return true;
	}
	
	/**
	 * Processes the outgoing request message. Called after payload creation and callback invocation, but before the
	 * message is sent.
	 * 
	 * @see org.springframework.ws.client.support.interceptor.ClientInterceptor#handleRequest(org.springframework.ws.context.MessageContext)
	 */
	@Override
	public boolean handleRequest(MessageContext messageContext)
			throws WebServiceClientException {
		HttpLoggingUtils.logMessage("Client Request Message",
				messageContext.getRequest());

		return true;
	}

	/**
	 * Processes the incoming response message. Called for non-fault response messages before payload handling in the
	 * {@link org.springframework.ws.client.core.WebServiceTemplate}.
	 * 
	 * @see org.springframework.ws.client.support.interceptor.ClientInterceptor#handleResponse(org.springframework.ws.context.MessageContext)
	 */
	@Override
	public boolean handleResponse(MessageContext messageContext)
			throws WebServiceClientException {
		HttpLoggingUtils.logMessage("Client Response Message",
				messageContext.getResponse());

		return true;
	}
}