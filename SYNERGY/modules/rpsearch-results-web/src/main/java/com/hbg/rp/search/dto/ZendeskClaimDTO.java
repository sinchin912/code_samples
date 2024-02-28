package com.hbg.rp.search.dto;

import com.hbg.rp.search.zendesk.ClaimItem;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * DTO for Zendesk Claim
 */
public class ZendeskClaimDTO extends ZendeskGenericDTO implements Serializable {
	private static final long serialVersionUID = -1004232534120170569L;

	private String claimSource;
	private String account;
	private String accountName;
	private String invoice;
	private String claimType;
	private String claimComments;
	private String retailerClaim;
	private String publisherTags;
	private boolean isReshipment;
    private BigDecimal amount;
    private List<ClaimItem> claimItemList;

	public String getClaimSource() {
		return claimSource;
	}

	public void setClaimSource(String claimSource) {
		this.claimSource = claimSource;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public String getClaimType() {
		return claimType;
	}

	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}

	public String getClaimComments() {
		return claimComments;
	}

	public void setClaimComments(String claimComments) {
		this.claimComments = claimComments;
	}

	public String getRetailerClaim() {
		return retailerClaim;
	}

	public void setRetailerClaim(String retailerClaim) {
		this.retailerClaim = retailerClaim;
	}

	public List<ClaimItem> getClaimItemList() {
		return claimItemList;
	}

	public void setClaimItemList(List<ClaimItem> claimItemList) {
		this.claimItemList = claimItemList;
	}

	public String getPublisherTags() {
		return publisherTags;
	}

	public void setPublisherTags(String publisherTags) {
		this.publisherTags = publisherTags;
	}

	public boolean isReshipment() {
		return isReshipment;
	}

	public void setReshipment(boolean isReshipment) {
		this.isReshipment = isReshipment;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	
}
