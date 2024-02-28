package com.hbg.rp.search.model;

import com.hbg.rp.model.InvoiceHeader;
import com.hbg.rp.model.InvoiceLine;
import com.hbg.rp.model.OrderHeader;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author ravi.kumar
 */
@Component
@Scope("session")
public class InvoiceExportDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private long refrenceNumber;
	private String pageHeader;
	private OrderHeader orderHeader;
	private List<String> invoiceShipmentInfo;
	private List<String> relatedTransactions;
	private List<InvoiceLine> invoiceLines;
	private List<String> comments;
	private String netCharge;
	private String totalShipped;
	private List<String> footerSummary;	
	private OrderExportDTO orderExportDTO;
	private InvoiceHeader invoiceHeader;
	private String currencyText;	

	public InvoiceExportDTO() {
		super();
	}
	
	public long getRefrenceNumber() {
		return refrenceNumber;
	}
	public void setRefrenceNumber(long refrenceNumber) {
		this.refrenceNumber = refrenceNumber;
	}

	public String getPageHeader() {
		return pageHeader;
	}

	public void setPageHeader(String pageHeader) {
		this.pageHeader = pageHeader;
	}

	public OrderHeader getOrderHeader() {
		return orderHeader;
	}

	public void setOrderHeader(OrderHeader orderHeader) {
		this.orderHeader = orderHeader;
	}

	public List<String> getInvoiceShipmentInfo() {
		return invoiceShipmentInfo;
	}

	public void setInvoiceShipmentInfo(List<String> invoiceShipmentInfo) {
		this.invoiceShipmentInfo = invoiceShipmentInfo;
	}

	public List<String> getRelatedTransactions() {
		return relatedTransactions;
	}

	public void setRelatedTransactions(List<String> relatedTransactions) {
		this.relatedTransactions = relatedTransactions;
	}

	public List<InvoiceLine> getInvoiceLines() {
		return invoiceLines;
	}

	public void setInvoiceLines(List<InvoiceLine> invoiceLines) {
		this.invoiceLines = invoiceLines;
	}

	public List<String> getComments() {
		return comments;
	}

	public void setComments(List<String> comments) {
		this.comments = comments;
	}

	public String getNetCharge() {
		return netCharge;
	}

	public void setNetCharge(String netCharge) {
		this.netCharge = netCharge;
	}

	public String getTotalShipped() {
		return totalShipped;
	}

	public void setTotalShipped(String totalShipped) {
		this.totalShipped = totalShipped;
	}

	public List<String> getFooterSummary() {
		return footerSummary;
	}

	public void setFooterSummary(List<String> footerSummary) {
		this.footerSummary = footerSummary;
	}
	

	public OrderExportDTO getOrderExportDTO() {
		return orderExportDTO;
	}

	public void setOrderExportDTO(OrderExportDTO orderExportDTO) {
		this.orderExportDTO = orderExportDTO;
	}

	public InvoiceHeader getInvoiceHeader() {
		return invoiceHeader;
	}

	public void setInvoiceHeader(InvoiceHeader invoiceHeader) {
		this.invoiceHeader = invoiceHeader;
	}

	public String getCurrencyText() {
		return currencyText;
	}

	public void setCurrencyText(String currencyText) {
		this.currencyText = currencyText;
	}
}
