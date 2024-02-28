/**
 * 
 */
package com.hbg.rp.search.util;

import com.hbg.rp.exception.NoSuchClaimHeaderException;
import com.hbg.rp.model.ClaimHeader;
import com.hbg.rp.model.ClaimLine;
import com.hbg.rp.model.ClaimTypeDetail;
import com.hbg.rp.model.InvoiceLine;
import com.hbg.rp.search.model.ClaimDTO;
import com.hbg.rp.search.model.ClaimHeaderDTO;
import com.hbg.rp.search.model.SubmitClaimDTO;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 * The com.hbg.rp.util.IInvoiceUtil is an interface for Claim Util.
 * 
 * @author ravi.kumar
 */
public interface IClaimUtil {

	ClaimDTO getInvoiceDetailForClaim(String invoiceNumber);
	
	String submitClaimForm(SubmitClaimDTO submitClaimDTO);
	
	JSONArray getClaimTypes() throws SystemException;
	
	public String updateZendeskData(Long claimHeaderKey, Long id, long userId, String publisherTags) throws SystemException;
	
	String getCodeValueForClaimRule(String claimRule) throws SystemException;
	
	List<ClaimHeader> getClaimHeaderForInvoiceHeaderId(long invoiceHeaderId) throws SystemException;
	
	List<ClaimLine> getClaimLineForClaimHeaderId(long claimHeaderId) throws SystemException;
	
	ClaimTypeDetail getClaimTypedetailForKey(long claimTypeKey)throws SystemException;//, NoSuchClaimTypeDetailException;
	
	String logClaimError(Long claimHeaderKey,Long invoiceNumber,Long errorCode,String errorMsg, String type, String comments, Long userId, String userClient, String requestDataJson) throws SQLException ;
	
	List<ClaimHeaderDTO> getClaimHeaderDTOforInvoice(String invoiceNo, long invoiceHeaderId) throws SystemException, ParseException;
	
	List<InvoiceLine> getInvoiceLineForClaimByInvoiceHeaderId(long invoiceHeaderId,long extRepId,boolean hasAllTerritories) throws SystemException;
	
	String getPublisherTags(Long claimHeaderKey) throws SystemException;
	
	String convertClaimObjToJsonObj(SubmitClaimDTO submitClaimDTO);
	
	boolean isValidInvoiceForClaim(Long invoiceNumber) throws SystemException;

	String cancelClaim(Long claimHeaderKey, String status, Long userId) throws SystemException;

}
