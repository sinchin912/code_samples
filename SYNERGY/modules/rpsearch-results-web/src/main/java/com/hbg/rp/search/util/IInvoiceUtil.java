package com.hbg.rp.search.util;

import com.hbg.rp.search.model.InvoiceDTO;
import com.hbg.rp.search.model.InvoiceExportDTO;
import com.liferay.portal.kernel.exception.SystemException;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;

/**
 * The com.hbg.rp.util.IInvoiceUtil is an interface for Invoice Util. 
 * 
 * @author ravi.kumar
 */
public interface IInvoiceUtil {

	/**
	 * Get prerequisite model.
	 * @return
	 * @throws SystemException
	 */
	Map<String, Object> getPreRequisiteModel() throws SystemException;
	
	/**
	 * Gets the Invoices.
	 **/
	Map<String, Object> getInvoices(Map<String, String> invoiceSearchCriteriaMap, long extRepId,boolean hasAllTerritories) throws SystemException;

	/**
	 * Get invoice detail data based on reference number passed.
	 * @param refNo
	 * @param request
	 * @return
	 * @throws SystemException
	 */
	InvoiceDTO getInvoiceDetailData(String refNo, PortletRequest request,long extRepId,boolean hasAllTerritories) throws SystemException;
	
	/**
	 * Get the invoice PDF.
	 * @param categoryName
	 * @param reportName
	 * @param invoiceNumber
	 * @return
	 */
	String getInvoicePdf(String categoryName, String reportName, String invoiceNumber);

	List<Long> getInvoiceHeaders(List<Long> headerIds) throws SystemException;
	
	InvoiceExportDTO getInvoiceExportData(InvoiceDTO invoiceDto, Map<String, String> hBGAddressMap,Long extRepId,boolean hasAllTerritories)  throws ParseException ;

}
