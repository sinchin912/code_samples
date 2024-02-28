/**
 * 
 */
package com.hbg.rp.search.model;

/**
 * @author ravi.kumar
 *
 */
public class InvoiceDetail {

	private long billToKey;
	private String billToName;
	private long invoiceLineId;
	private long invoiceHeaderId;
	private long orderLineId;
	private long productId;
	private long invoiceLineNumber;
	private double lineAmount;
	private String lineStatus;
	private String salesForceCode;
	private String divisionCode;
	private String territoryCode;
	private long quantity;
	private String isbn;
	private String shortTitle;
	private String shortAuthor;
	private int previouslyClaimed;
	private String reShipFlag; //
	private double unitPrice;
	private double discount;
	private double netCharge;
	private String reportingGroupCode;
	private String reportingGroupName;
	private String ownerCode;
	private String ownerName;
	private String bomType;
	private long quantityClaim;
	private String title;
	private String author;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public long getBillToKey() {
		return billToKey;
	}

	public void setBillToKey(long billToKey) {
		this.billToKey = billToKey;
	}

	public String getBillToName() {
		return billToName;
	}

	public void setBillToName(String billToName) {
		this.billToName = billToName;
	}

	public long getInvoiceLineId() {
		return invoiceLineId;
	}

	public void setInvoiceLineId(long invoiceLineId) {
		this.invoiceLineId = invoiceLineId;
	}

	public long getInvoiceHeaderId() {
		return invoiceHeaderId;
	}

	public void setInvoiceHeaderId(long invoiceHeaderId) {
		this.invoiceHeaderId = invoiceHeaderId;
	}

	public long getOrderLineId() {
		return orderLineId;
	}

	public void setOrderLineId(long orderLineId) {
		this.orderLineId = orderLineId;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public long getInvoiceLineNumber() {
		return invoiceLineNumber;
	}

	public void setInvoiceLineNumber(long invoiceLineNumber) {
		this.invoiceLineNumber = invoiceLineNumber;
	}

	public double getLineAmount() {
		return lineAmount;
	}

	public void setLineAmount(double lineAmount) {
		this.lineAmount = lineAmount;
	}

	public String getLineStatus() {
		return lineStatus;
	}

	public void setLineStatus(String lineStatus) {
		this.lineStatus = lineStatus;
	}

	public String getSalesForceCode() {
		return salesForceCode;
	}

	public void setSalesForceCode(String salesForceCode) {
		this.salesForceCode = salesForceCode;
	}

	public String getDivisionCode() {
		return divisionCode;
	}

	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}

	public String getTerritoryCode() {
		return territoryCode;
	}

	public void setTerritoryCode(String territoryCode) {
		this.territoryCode = territoryCode;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getShortTitle() {
		return shortTitle;
	}

	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}

	public String getShortAuthor() {
		return shortAuthor;
	}

	public void setShortAuthor(String shortAuthor) {
		this.shortAuthor = shortAuthor;
	}

	public int getPreviouslyClaimed() {
		return previouslyClaimed;
	}

	public void setPreviouslyClaimed(int previouslyClaimed) {
		this.previouslyClaimed = previouslyClaimed;
	}

	public String getReShipFlag() {
		return reShipFlag;
	}

	public void setReShipFlag(String reShipFlag) {
		this.reShipFlag = reShipFlag;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getNetCharge() {
		return netCharge;
	}

	public void setNetCharge(double netCharge) {
		this.netCharge = netCharge;
	}

	public String getReportingGroupCode() {
		return reportingGroupCode;
	}

	public void setReportingGroupCode(String reportingGroupCode) {
		this.reportingGroupCode = reportingGroupCode;
	}

	public String getReportingGroupName() {
		return reportingGroupName;
	}

	public void setReportingGroupName(String reportingGroupName) {
		this.reportingGroupName = reportingGroupName;
	}

	public String getOwnerCode() {
		return ownerCode;
	}

	public void setOwnerCode(String ownerCode) {
		this.ownerCode = ownerCode;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getBomType() {
		return bomType;
	}

	public void setBomType(String bomType) {
		this.bomType = bomType;
	}

	public long getQuantityClaim() {
		return quantityClaim;
	}

	public void setQuantityClaim(long quantityClaim) {
		this.quantityClaim = quantityClaim;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
