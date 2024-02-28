package com.hbg.rp.search.model;

/**
 * @author ravi.kumar
 */
public class StatementDTO {
	private String date;
	private String accountName;
	private String pdfUrl;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getPdfUrl() {
		return pdfUrl;
	}
	public void setPdfUrl(String pdfUrl) {
		this.pdfUrl = pdfUrl;
	}
	
	@Override
	public String toString() {
		return "StatementDTO [date=" + date + ", accountName=" + accountName
				+ ", pdfUrl=" + pdfUrl + "]";
	}
	
}
