/**
 * 
 */
package com.hbg.rp.search.model;

import java.util.List;

/**
 * @author ravi.kumar
 */
public class ClaimDTO {

	private String invoiceNumber;
	private List<InvoiceDetail> invoiceDetail;
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public List<InvoiceDetail> getInvoiceDetail() {
		return invoiceDetail;
	}
	public void setInvoiceDetail(List<InvoiceDetail> invoiceDetail) {
		this.invoiceDetail = invoiceDetail;
	}
	
	
}
