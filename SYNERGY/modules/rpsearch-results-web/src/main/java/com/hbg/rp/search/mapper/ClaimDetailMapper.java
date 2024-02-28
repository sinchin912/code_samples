/**
 * 
 */
package com.hbg.rp.search.mapper;

import com.hbg.rp.model.ClaimInvoiceDetail;
import com.hbg.rp.search.model.InvoiceDetail;;

/**
 * @author rahul.chahar
 *
 */
public class ClaimDetailMapper {
	private ClaimDetailMapper(){
		
	}
	public static InvoiceDetail mapInvoiceDetailwithView(ClaimInvoiceDetail view) {
		InvoiceDetail invoiceDetail = new InvoiceDetail();
		
		invoiceDetail.setBillToKey(view.getBillToKey());
		invoiceDetail.setBillToName(null != view.getBillToName() && (!"null".equalsIgnoreCase(view.getBillToName())) ? view.getBillToName() : "");
		invoiceDetail.setPreviouslyClaimed((int)view.getTotalClaimQtyPerIsbn());
		invoiceDetail.setInvoiceLineId(view.getInvoiceLineKey());
		invoiceDetail.setInvoiceHeaderId(view.getInvoiceHeaderKey());
		invoiceDetail.setOrderLineId(view.getOrderLineKey());
		invoiceDetail.setProductId(view.getProductKey());
		invoiceDetail.setInvoiceLineNumber(view.getInvoiceLineNumber());
		invoiceDetail.setQuantity(view.getQuantity());
		invoiceDetail.setUnitPrice(view.getUnitPrice());
		invoiceDetail.setDiscount(view.getDiscount());
		invoiceDetail.setNetCharge(view.getLineAmount());
		invoiceDetail.setLineAmount(view.getLineAmount());
		invoiceDetail.setLineStatus(null != view.getLineStatus() && (!"null".equalsIgnoreCase(view.getLineStatus())) ? view.getLineStatus() : "");
		invoiceDetail.setSalesForceCode(null != view.getSalesForceCode() && (!"null".equalsIgnoreCase(view.getSalesForceCode())) ? view.getSalesForceCode() : "");
		invoiceDetail.setDivisionCode(null != view.getDivisionCode() && (!"null".equalsIgnoreCase(view.getDivisionCode())) ? view.getDivisionCode() : "");
		invoiceDetail.setTerritoryCode(null != view.getTerritoryCode() && (!"null".equalsIgnoreCase(view.getTerritoryCode())) ? view.getTerritoryCode() : "");
		invoiceDetail.setIsbn(null != view.getIsbn() && (!"null".equalsIgnoreCase(view.getIsbn())) ? view.getIsbn() : "");
		invoiceDetail.setShortTitle(null != view.getShortTitle() && (!"null".equalsIgnoreCase(view.getShortTitle())) ? view.getShortTitle() : "");
		invoiceDetail.setShortAuthor(null != view.getShortAuthor() && (!"null".equalsIgnoreCase(view.getShortAuthor())) ? view.getShortAuthor() : "");
		invoiceDetail.setReportingGroupCode(null != view.getReportingGroupCode() && (!"null".equalsIgnoreCase(view.getReportingGroupCode())) ? view.getReportingGroupCode() : "");
		invoiceDetail.setReportingGroupName(null != view.getReportingGroupName() && (!"null".equalsIgnoreCase(view.getReportingGroupName())) ? view.getReportingGroupName() : "");
		invoiceDetail.setOwnerCode(null != view.getOwnerCode() && (!"null".equalsIgnoreCase(view.getOwnerCode())) ? view.getOwnerCode() : "");
		invoiceDetail.setOwnerName(null != view.getOwnerName() && (!"null".equalsIgnoreCase(view.getOwnerName())) ? view.getOwnerName() : "");
		invoiceDetail.setQuantityClaim(view.getQuantityClaim());		
		invoiceDetail.setBomType(null != view.getBomType() && (!"null".equalsIgnoreCase(view.getBomType())) ? view.getBomType() : "");
		invoiceDetail.setTitle(null != view.getTitle() && (!"null".equalsIgnoreCase(view.getTitle())) ? view.getTitle() : "");
		invoiceDetail.setAuthor(null != view.getAuthor() && (!"null".equalsIgnoreCase(view.getAuthor())) ? view.getAuthor() : "");
		
		return invoiceDetail;
	}
	
}
