/**
 * 
 */
package com.hbg.rp.search.model;

import java.util.Date;

/**
 * DTO will be used for integrated list of claims via RP and Non-RP portal.
 * 
 * @author ravi.kumar
 */
public class ClaimHeaderDTO{

	private long claimHeaderKey;
	private long claimTypeKey;
	private String claimTypeDesc;
	private long invoiceHeaderKey;
	private long invoiceNumber;
	private String nonRpClaim;
	private String retailerClaimNumber;
	private String claimStatus;
	private String claimComment;
	private String requestedByEmail;
	private String claimProcessingType;
	private long zendeskTicketNumber;
	private Date createdDate;
	private long claimInvoiceHeaderKey;
	private long claimInvoiceNumber;
	private String claimIntegStatus;
	
	public long getClaimHeaderKey() {
		return claimHeaderKey;
	}
	public void setClaimHeaderKey(long claimHeaderKey) {
		this.claimHeaderKey = claimHeaderKey;
	}
	public long getClaimTypeKey() {
		return claimTypeKey;
	}
	public void setClaimTypeKey(long claimTypeKey) {
		this.claimTypeKey = claimTypeKey;
	}
	public String getClaimTypeDesc() {
		return claimTypeDesc;
	}
	public void setClaimTypeDesc(String claimTypeDesc) {
		this.claimTypeDesc = claimTypeDesc;
	}
	
	public String getNonRpClaim() {
		return nonRpClaim;
	}
	public void setNonRpClaim(String nonRpClaim) {
		this.nonRpClaim = nonRpClaim;
	}
	public long getInvoiceHeaderKey() {
		return invoiceHeaderKey;
	}
	public void setInvoiceHeaderKey(long invoiceHeaderKey) {
		this.invoiceHeaderKey = invoiceHeaderKey;
	}
	public long getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(long invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public String getRetailerClaimNumber() {
		return retailerClaimNumber;
	}
	public void setRetailerClaimNumber(String retailerClaimNumber) {
		this.retailerClaimNumber = retailerClaimNumber;
	}
	public String getClaimStatus() {
		return claimStatus;
	}
	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}
	public String getClaimComment() {
		return claimComment;
	}
	public void setClaimComment(String claimComment) {
		this.claimComment = claimComment;
	}
	public String getRequestedByEmail() {
		return requestedByEmail;
	}
	public void setRequestedByEmail(String requestedByEmail) {
		this.requestedByEmail = requestedByEmail;
	}
	public String getClaimProcessingType() {
		return claimProcessingType;
	}
	public void setClaimProcessingType(String claimProcessingType) {
		this.claimProcessingType = claimProcessingType;
	}
	public long getZendeskTicketNumber() {
		return zendeskTicketNumber;
	}
	public void setZendeskTicketNumber(long zendeskTicketNumber) {
		this.zendeskTicketNumber = zendeskTicketNumber;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public long getClaimInvoiceHeaderKey() {
		return claimInvoiceHeaderKey;
	}
	public void setClaimInvoiceHeaderKey(long claimInvoiceHeaderKey) {
		this.claimInvoiceHeaderKey = claimInvoiceHeaderKey;
	}
	public long getClaimInvoiceNumber() {
		return claimInvoiceNumber;
	}
	public void setClaimInvoiceNumber(long claimInvoiceNumber) {
		this.claimInvoiceNumber = claimInvoiceNumber;
	}
	public String getClaimIntegStatus() {
		return claimIntegStatus;
	}
	public void setClaimIntegStatus(String claimIntegStatus) {
		this.claimIntegStatus = claimIntegStatus;
	}

}