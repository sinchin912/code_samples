package com.hbg.rp.search.model;

import com.hbg.rp.model.ShipmentCarrier;
import com.hbg.rp.model.ShipmentLine;
import com.hbg.rp.search.dto.ShipmentTrackingDTO;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * DTO for Order Status.
 * 
 * @author ravi.kumar
 */
public class OrderStatusDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<LineStatusDTO> orderLines;
	private List<ShipmentStatusDTO> shipments;
	private Map<String, ShipmentCarrier> supportedCarrierData;
	private Map<String, Boolean> orderLinesExists;
	private List<Long> invoiceHeaderIds;
	private Long orderRefNo;
	private Map<String, List<ShipmentLine>>  shipmentLine;
	private Map<String, ShipmentTrackingDTO>  shipmentTracking;

	public Map<String, ShipmentTrackingDTO> getShipmentTracking() {
		return shipmentTracking;
	}

	public void setShipmentTracking(Map<String, ShipmentTrackingDTO> shipmentTracking) {
		this.shipmentTracking = shipmentTracking;
	}

	public List<LineStatusDTO> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(List<LineStatusDTO> orderLines) {
		this.orderLines = orderLines;
	}

	public List<ShipmentStatusDTO> getShipments() {
		return shipments;
	}

	public void setShipments(List<ShipmentStatusDTO> shipments) {
		this.shipments = shipments;
	}

	public Map<String, ShipmentCarrier> getSupportedCarrierData() {
		return supportedCarrierData;
	}

	public void setSupportedCarrierData(
			Map<String, ShipmentCarrier> supportedCarrierData) {
		this.supportedCarrierData = supportedCarrierData;
	}

	public Map<String, Boolean> getOrderLinesExists() {
		return orderLinesExists;
	}

	public void setOrderLinesExists(Map<String, Boolean> orderLinesExists) {
		this.orderLinesExists = orderLinesExists;
	}

	public List<Long> getInvoiceHeaderIds() {
		return invoiceHeaderIds;
	}

	public void setInvoiceHeaderIds(List<Long> invoiceHeaderIds) {
		this.invoiceHeaderIds = invoiceHeaderIds;
	}
	
	public Long getOrderRefNo() {
		return orderRefNo;
	}

	public void setOrderRefNo(Long orderRefNo) {
		this.orderRefNo = orderRefNo;
	}

	public Map<String, List<ShipmentLine>> getShipmentLine() {
		return shipmentLine;
	}

	public void setShipmentLine(Map<String, List<ShipmentLine>> shipmentLine) {
		this.shipmentLine = shipmentLine;
	}
}
