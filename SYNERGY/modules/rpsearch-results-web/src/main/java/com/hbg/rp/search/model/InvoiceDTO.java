package com.hbg.rp.search.model;

import com.hbg.rp.model.InvoiceHeader;
import com.hbg.rp.model.InvoiceLine;

import java.util.List;

/**
 * DTO class for Invoices.
 * 
 * @author ravi.kumar
 */
public class InvoiceDTO {
	
	private InvoiceHeader invoiceHeader;
	private String billToAddress;
	private String shipToAddress;
	private String destinationAddress;
	
	private List<InvoiceLine> invoiceLines;
	private List<String> invoiceComments;
	private OrderDTO orderDTO;
	
	private boolean hasShipments;
	
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

	public OrderDTO getOrderDTO() {
		return orderDTO;
	}

	public void setOrderDTO(OrderDTO orderDTO) {
		this.orderDTO = orderDTO;
	}

	public List<String> getInvoiceComments() {
		return invoiceComments;
	}

	public void setInvoiceComments(List<String> invoiceComments) {
		this.invoiceComments = invoiceComments;
	}

	public String getBillToAddress() {
		return billToAddress;
	}

	public void setBillToAddress(String billToAddress) {
		this.billToAddress = billToAddress;
	}

	public String getShipToAddress() {
		return shipToAddress;
	}

	public void setShipToAddress(String shipToAddress) {
		this.shipToAddress = shipToAddress;
	}

	public String getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	public boolean isHasShipments() {
		return hasShipments;
	}

	public void setHasShipments(boolean hasShipments) {
		this.hasShipments = hasShipments;
	}
	
}
