package com.hbg.rp.search.model;

/**
 * DTO class for customization of invoice line.
 * 
 * @author ravi.kumar
 */
public class InvoiceLinesDTO {
	
	private String isbn;
	private String status;
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
