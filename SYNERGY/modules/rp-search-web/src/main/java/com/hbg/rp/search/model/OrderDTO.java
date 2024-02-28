package com.hbg.rp.search.model;

import com.hbg.rp.model.OrderHeader;
import com.hbg.rp.model.OrderLine;
import com.hbg.rp.model.SpecialOffer;

import java.util.ArrayList;
import java.util.List;


/**
 * DTO for Orders.
 * 
 * @author ravi.kumar
 */
public class OrderDTO {

	private OrderHeader orderHeader;
	private List<OrderLine> orderLines = new ArrayList<>();
	private List<SpecialOffer> specialOffers;
	
	private String statusMessage;
	private String statusText;
	private String destinationName;
	private String destinationAddress;
	private String destinationZip;
	
	private String offerCode;
	
	public List<SpecialOffer> getSpecialOffers() {
		return specialOffers;
	}

	public void setSpecialOffers(List<SpecialOffer> specialOffers) {
		this.specialOffers = specialOffers;
	}

	public OrderHeader getOrderHeader() {
		return orderHeader;
	}

	public void setOrderHeader(OrderHeader orderHeader) {
		this.orderHeader = orderHeader;
	}

	public List<OrderLine> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}
	
	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	public String getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	public String getDestinationZip() {
		return destinationZip;
	}

	public void setDestinationZip(String destinationZip) {
		this.destinationZip = destinationZip;
	}
	
	public String getOfferCode() {
		return offerCode;
	}

	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}

}
