package com.hbg.conduent;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import com.hbg.conduent.wsdl.*;
import com.hbg.rp.search.constants.ApplicationPropertyReader;

/**
 * This class com.hbg.conduent.QuoteClient is the Client for the Conduent API.
 * 
 * @author Gurpreet.Singh
 */
public class QuoteClient extends WebServiceGatewaySupport {
	
	private static final String URL = ApplicationPropertyReader.getProperty("conduent-url");
	private static final Log log = LogFactoryUtil.getLog(QuoteClient.class);
	
	
	private static final String ACTION_GET_DOCUMENTS = "GetDocumentsList";
	private static final String ACTION_GET_CATEGORIES = "GetCategories";
	private static final String ACTION_GET_REPORTS_BY_CATEGORY = "GetReportsbyCategory";
	private static final String LOGGER_PARAM_REQUESTING = "Requesting";
	
	/**
	 * Gets the specific invoice.
	 * <p> Action = GetDocumentsList is used. </p>
	 * 
	 * @param categoryName Name of category
	 * @param reportName Name of Report
	 * @param invoiceNumber Invoice Number associated
	 * @return GetDocumentsListResponse
	 */
	public GetDocumentsListResponse getInvoice(String categoryName,
			String reportName, String invoiceNumber) {
		String action = ACTION_GET_DOCUMENTS;
		GetDocumentsList request = new GetDocumentsList();
		request.setReportName(reportName);
		request.setCategoryName(categoryName);
		request.setInvoiceNumber(invoiceNumber);
		log.info(LOGGER_PARAM_REQUESTING + action);
		return (GetDocumentsListResponse) getWebServiceTemplate()
				.marshalSendAndReceive(URL, request, new SecurityHeader( action ));
	}
	
	/**
	 * Gets all the categories.
	 * <p> Action = GetCategories is used. </p>
	 * 
	 * @return GetCategoriesResponse
	 */
	public GetCategoriesResponse getCategories() {
		String action = ACTION_GET_CATEGORIES;
		GetCategories request = new GetCategories();
		log.info(LOGGER_PARAM_REQUESTING + action);
		return (GetCategoriesResponse) getWebServiceTemplate()
				.marshalSendAndReceive(URL, request, new SecurityHeader( action ));
	}
	
	/**
	 * This method gets reports by category.
	 * <p> Action = GetReportsbyCategory is used. </p>
	 * 
	 * @param categoryName Name of category
	 * @return GetReportsbyCategoryResponse
	 */
	public GetReportsbyCategoryResponse getReportsByCategory(String categoryName) {
		String action = ACTION_GET_REPORTS_BY_CATEGORY;
		GetReportsbyCategory request = new GetReportsbyCategory();
		request.setCategoryName(categoryName);
		log.info(LOGGER_PARAM_REQUESTING + action);
		return (GetReportsbyCategoryResponse) getWebServiceTemplate()
				.marshalSendAndReceive(URL, request, new SecurityHeader(action));
	}
	
	/**
	 * Gets the statement.
	 * <p> Action = GetDocumentsList is used. </p>
	 * 
	 * @param categoryName Name of category
	 * @param reportName Name of Report
	 * @param accountNumber Account Number associated
	 * @return GetDocumentsListResponse
	 */
	public GetDocumentsListResponse getStatement(String categoryName, String reportName,
			String accountNumber, String pageSize, String pageNumber) {
		String action = ACTION_GET_DOCUMENTS;
		GetDocumentsList request = new GetDocumentsList();
		request.setAccountNumber(accountNumber);
		request.setReportName(reportName);
		request.setCategoryName(categoryName);
		request.setPageSize(pageSize);
		request.setPageNumber(pageNumber);
		log.info(LOGGER_PARAM_REQUESTING + action);
		return (GetDocumentsListResponse) getWebServiceTemplate()
				.marshalSendAndReceive(URL, request, new SecurityHeader( action ));
	}
	
} 