package com.hbg.rp.search.model;

import com.hbg.rp.model.InvoiceComment;
import com.hbg.rp.model.InvoiceHeader;
import com.hbg.rp.model.InvoiceLine;

import java.util.List;

/**
 * DTO class for Credits & Debits.
 * 
 * @author ravi.kumar
 */
public class CreditDebitDTO {
	
	private InvoiceHeader invoiceHeader;
	private List<InvoiceLine> invoiceLines;
	private List<InvoiceComment> invoiceComments;
	
	public List<InvoiceLine> getInvoiceLines() {
		return invoiceLines;
	}

	public void setInvoiceLines(List<InvoiceLine> invoiceLines) {
		this.invoiceLines = invoiceLines;
	}

	public InvoiceHeader getInvoiceHeader() {
		return invoiceHeader;
	}

	public void setInvoiceHeader(InvoiceHeader invoiceHeader) {
		this.invoiceHeader = invoiceHeader;
	}

	public List<InvoiceComment> getInvoiceComments() {
		return invoiceComments;
	}

	public void setInvoiceComments(List<InvoiceComment> invoiceComments) {
		this.invoiceComments = invoiceComments;
	}
	
}
