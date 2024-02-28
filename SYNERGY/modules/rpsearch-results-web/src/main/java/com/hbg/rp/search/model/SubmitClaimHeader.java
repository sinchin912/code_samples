package com.hbg.rp.search.model;

import java.util.Date;

/**
 * @author ravi.kumar
 */
public class SubmitClaimHeader {

	private Long billToKey;
	private String billToName;
	private Long claimTypeKey;
	private Long invoiceHeaderKey;
	private Long invoiceNumber;
	private String invoceDate;
	private Date claimSubmittedDate;
	private String retailerClaimNumber;
	private String claimStatus;
	private String claimComments;
	private String requestedBy;
	private String requestedByEmail;
	private String claimFormPage;
	private String claimIntegrationStatus;
	private Long totalClaimQty;
	private String accountNumber;
	
	public Long getBillToKey() {
		return billToKey;
	}
	public void setBillToKey(Long billToKey) {
		this.billToKey = billToKey;
	}
	public String getBillToName() {
		return billToName;
	}
	public void setBillToName(String billToName) {
		this.billToName = billToName;
	}
	public Long getClaimTypeKey() {
		return claimTypeKey;
	}
	public void setClaimTypeKey(Long claimTypeKey) {
		this.claimTypeKey = claimTypeKey;
	}
	public Long getInvoiceHeaderKey() {
		return invoiceHeaderKey;
	}
	public void setInvoiceHeaderKey(Long invoiceHeaderKey) {
		this.invoiceHeaderKey = invoiceHeaderKey;
	}
	public Long getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(Long invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	
	public Date getClaimSubmittedDate() {
		return claimSubmittedDate;
	}
	public void setClaimSubmittedDate(Date claimSubmittedDate) {
		this.claimSubmittedDate = claimSubmittedDate;
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
	public String getClaimComments() {
		return claimComments;
	}
	public void setClaimComments(String claimComments) {
		this.claimComments = claimComments;
	}
	public String getRequestedBy() {
		return requestedBy;
	}
	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}
	public String getRequestedByEmail() {
		return requestedByEmail;
	}
	public void setRequestedByEmail(String requestedByEmail) {
		this.requestedByEmail = requestedByEmail;
	}
	public String getClaimFormPage() {
		return claimFormPage;
	}
	public void setClaimFormPage(String claimFormPage) {
		this.claimFormPage = claimFormPage;
	}
	public String getClaimIntegrationStatus() {
		return claimIntegrationStatus;
	}
	public void setClaimIntegrationStatus(String claimIntegrationStatus) {
		this.claimIntegrationStatus = claimIntegrationStatus;
	}
	public Long getTotalClaimQty() {
		return totalClaimQty;
	}
	public void setTotalClaimQty(Long totalClaimQty) {
		this.totalClaimQty = totalClaimQty;
	}
	public String getInvoceDate() {
		return invoceDate;
	}
	public void setInvoceDate(String invoceDate) {
		this.invoceDate = invoceDate;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	
	
	
	
}
