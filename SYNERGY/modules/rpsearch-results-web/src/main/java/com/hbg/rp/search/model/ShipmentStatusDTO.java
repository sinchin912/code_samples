package com.hbg.rp.search.model;

import com.hbg.rp.model.Shipment;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * DTO for Shipment Status.
 * 
 * @author ravi.kumar
 */
public class ShipmentStatusDTO {

	private String name;
	private List<Shipment> shipments;
	private Map<Long, Date> invoiceHeaderDateMap;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Shipment> getShipments() {
		return shipments;
	}

	public void setShipments(List<Shipment> shipments) {
		this.shipments = shipments;
	}

	public Map<Long, Date> getInvoiceHeaderDateMap() {
		return invoiceHeaderDateMap;
	}

	public void setInvoiceHeaderDateMap(Map<Long, Date> invoiceHeaderDateMap) {
		this.invoiceHeaderDateMap = invoiceHeaderDateMap;
	}

}
