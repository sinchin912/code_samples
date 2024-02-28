package com.hbg.rp.search.model;

import com.hbg.rp.model.OrderHeader;
import com.hbg.rp.model.OrderLine;
import com.hbg.rp.model.SpecialOffer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * DTO for exporting order details data.
 * 
 * @author ravi.kumar
 */
@Component
@Scope("session")
public class OrderExportDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private OrderHeader orderHeader;
	private List<OrderLine> orderLines = new ArrayList<>();
	private List<SpecialOffer> specialOffers;
	
	private String statusMessage;
	private String statusText;
	private String destinationName;
	private String destinationAddress;
	private String destinationZip;
	private String offerCode;
	
	public OrderExportDTO(OrderDTO orderDTO) {
		super();
		if(orderDTO != null){
			this.orderHeader = orderDTO.getOrderHeader();
			this.orderLines = orderDTO.getOrderLines();
			this.specialOffers = orderDTO.getSpecialOffers();
			this.statusMessage = orderDTO.getStatusMessage();
			this.statusText = orderDTO.getStatusText();
			this.destinationName = orderDTO.getDestinationName();
			this.destinationAddress = orderDTO.getDestinationAddress();
			this.destinationZip = orderDTO.getDestinationZip();
			this.offerCode = orderDTO.getOfferCode();
		}
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

	public List<SpecialOffer> getSpecialOffers() {
		return specialOffers;
	}

	public void setSpecialOffers(List<SpecialOffer> specialOffers) {
		this.specialOffers = specialOffers;
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

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
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
	
	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}

	public String getOfferCode() {
		return offerCode;
	}

}
