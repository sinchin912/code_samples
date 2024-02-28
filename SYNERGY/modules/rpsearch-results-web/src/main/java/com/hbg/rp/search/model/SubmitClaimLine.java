package com.hbg.rp.search.model;

/**
 * @author ravi.kumar
 */
public class SubmitClaimLine {
	
	private Long claimLineNumber;
	private Long invoiceLineKey;
	private Long productKey;
	private String isbn;
	private String shortTittle;
	private String shortAuthor;
	private Double unitPrice;
	private Double discountPct;
	private Double lineAmount;
	private Long shipQty;
	private Long claimQty;
	private String reshipFlag;
	private String reportingGroupName;
	private String reportingGroupCode;
	private Long claimTypeKey;
	private String claimTypeDesc;
	private String wrongIsbn;
	
	public Long getClaimLineNumber() {
		return claimLineNumber;
	}
	public void setClaimLineNumber(Long claimLineNumber) {
		this.claimLineNumber = claimLineNumber;
	}
	public Long getInvoiceLineKey() {
		return invoiceLineKey;
	}
	public void setInvoiceLineKey(Long invoiceLineKey) {
		this.invoiceLineKey = invoiceLineKey;
	}
	public Long getProductKey() {
		return productKey;
	}
	public void setProductKey(Long productKey) {
		this.productKey = productKey;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getShortTittle() {
		return shortTittle;
	}
	public void setShortTittle(String shortTittle) {
		this.shortTittle = shortTittle;
	}
	public String getShortAuthor() {
		return shortAuthor;
	}
	public void setShortAuthor(String shortAuthor) {
		this.shortAuthor = shortAuthor;
	}
	
	public Long getShipQty() {
		return shipQty;
	}
	public void setShipQty(Long shipQty) {
		this.shipQty = shipQty;
	}
	public Long getClaimQty() {
		return claimQty;
	}
	public void setClaimQty(Long claimQty) {
		this.claimQty = claimQty;
	}
	public String getReshipFlag() {
		return reshipFlag;
	}
	public void setReshipFlag(String reshipFlag) {
		this.reshipFlag = reshipFlag;
	}
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Double getDiscountPct() {
		return discountPct;
	}
	public void setDiscountPct(Double discountPct) {
		this.discountPct = discountPct;
	}
	public Double getLineAmount() {
		return lineAmount;
	}
	public void setLineAmount(Double lineAmount) {
		this.lineAmount = lineAmount;
	}
	public String getReportingGroupName() {
		return reportingGroupName;
	}
	public void setReportingGroupName(String reportingGroupName) {
		this.reportingGroupName = reportingGroupName;
	}
	public String getReportingGroupCode() {
		return reportingGroupCode;
	}
	public void setReportingGroupCode(String reportingGroupCode) {
		this.reportingGroupCode = reportingGroupCode;
	}
	public Long getClaimTypeKey() {
		return claimTypeKey;
	}
	public void setClaimTypeKey(Long claimTypeKey) {
		this.claimTypeKey = claimTypeKey;
	}
	public String getClaimTypeDesc() {
		return claimTypeDesc;
	}
	public void setClaimTypeDesc(String claimTypeDesc) {
		this.claimTypeDesc = claimTypeDesc;
	}
	public String getWrongIsbn() {
		return wrongIsbn;
	}
	public void setWrongIsbn(String wrongIsbn) {
		this.wrongIsbn = wrongIsbn;
	}
}
