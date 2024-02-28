/**
 * 
 */
package com.hbg.rp.search.util;

import com.hbg.rp.exception.NoSuchClaimHeaderException;
import com.hbg.rp.model.ClaimHeader;
import com.hbg.rp.model.ClaimLine;
import com.hbg.rp.model.ClaimNonRP;
import com.hbg.rp.model.ClaimRule;
import com.hbg.rp.model.ClaimTypeDetail;
import com.hbg.rp.model.InvoiceLine;
import com.hbg.rp.model.Product;
import com.hbg.rp.model.ClaimInvoiceDetail;
import com.hbg.rp.service.ClaimInvoiceDetailLocalServiceUtil;
import com.hbg.rp.service.ClaimTypeDetailLocalServiceUtil;
import com.hbg.rp.service.InvoiceLineLocalServiceUtil;
import com.hbg.rp.search.constants.ClaimConstants;
import com.hbg.rp.search.constants.SearchConstant;
import com.hbg.rp.search.exceptions.NoSuchClaimTypeDetailException;
import com.hbg.rp.search.mapper.ClaimDetailMapper;
import com.hbg.rp.search.model.ClaimDTO;
import com.hbg.rp.search.model.ClaimHeaderDTO;
import com.hbg.rp.search.model.InvoiceDetail;
import com.hbg.rp.search.model.SubmitClaimDTO;
import com.hbg.rp.search.model.SubmitClaimLine;
import com.hbg.rp.service.ClaimHeaderLocalServiceUtil;
import com.hbg.rp.service.ClaimLineLocalServiceUtil;
import com.hbg.rp.service.ClaimNonRPLocalServiceUtil;
import com.hbg.rp.service.ClaimRuleLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.log.Log;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Collections;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * The com.hbg.rp.util.IInvoiceUtil is an interface for Claim Util.
 * 
 * @author ravi.kumar
 */
@Component
public class ClaimUtil implements IClaimUtil{
	
	@Autowired
	private ICommonUtil commonUtil;
	
	@Override
	public String getCodeValueForClaimRule(String rule) throws SystemException {
		ClaimRule claimRule = ClaimRuleLocalServiceUtil.fetchClaimRuleByRule(rule);
		return claimRule.getCodeValue();
	}
	
	@Override
	public List<ClaimHeaderDTO> getClaimHeaderDTOforInvoice(String invoiceNo, long invoiceHeaderId)
			throws SystemException, ParseException {
		List<ClaimHeader> rpClaimList = ClaimHeaderLocalServiceUtil.getClaimHeaderForInvoiceHeaderKey(invoiceHeaderId);	
		List<ClaimNonRP> nonRpClaimList = ClaimNonRPLocalServiceUtil.getNonRpClaimForInvoiceNumber(Long.parseLong(invoiceNo));
		return this.mergeRpAndNonRPClaimsHeader(rpClaimList,nonRpClaimList);
	}
	
	private List<ClaimHeaderDTO> mergeRpAndNonRPClaimsHeader(List<ClaimHeader> rpClaimList,	List<ClaimNonRP> nonRpClaimList) throws ParseException {
		SimpleDateFormat dateFormatter=new SimpleDateFormat(SearchConstant.FORMAT_YYYY_MM_DD);	
		List<ClaimHeaderDTO> claimHeaderDtoList = new ArrayList<>();
		for (ClaimHeader rpClaim : rpClaimList) {
			
			ClaimHeaderDTO claimHeaderDto = new ClaimHeaderDTO();
			claimHeaderDto.setInvoiceNumber(rpClaim.getInvoiceNumber());
			claimHeaderDto.setClaimInvoiceNumber((long)rpClaim.getClaimInvoiceNumber());
			claimHeaderDto.setClaimComment(rpClaim.getClaimComment());
			claimHeaderDto.setClaimHeaderKey(rpClaim.getClaimHeaderKey());
			claimHeaderDto.setClaimStatus(rpClaim.getClaimStatus());
			claimHeaderDto.setClaimTypeKey(rpClaim.getClaimTypeKey());
			claimHeaderDto.setCreatedDate(rpClaim.getCreatedDate());
			claimHeaderDto.setRequestedByEmail(rpClaim.getRequestedByEmail());
			claimHeaderDto.setRetailerClaimNumber(rpClaim.getRetailerClaimNumber());
			claimHeaderDto.setZendeskTicketNumber(rpClaim.getZendeskTicketNumber());
			claimHeaderDto.setClaimIntegStatus(rpClaim.getClaimIntegStatus());
			claimHeaderDtoList.add(claimHeaderDto);
		}
		for (ClaimNonRP nonRpClaim : nonRpClaimList) {
			ClaimHeaderDTO claimHeaderDto = new ClaimHeaderDTO();
			claimHeaderDto.setNonRpClaim(SearchConstant.TRUE);
			claimHeaderDto.setInvoiceNumber(nonRpClaim.getClaimInvoiceNumber());
			claimHeaderDto.setClaimInvoiceNumber(nonRpClaim.getInvoiceNumber());
			claimHeaderDto.setRetailerClaimNumber(nonRpClaim.getRetailClaimNumber());
			claimHeaderDto.setInvoiceHeaderKey(nonRpClaim.getInvoiceHeaderKey());
			claimHeaderDto.setClaimStatus(nonRpClaim.getCurrentStatus());
			claimHeaderDto.setClaimTypeDesc(nonRpClaim.getClaimType());
			claimHeaderDto.setCreatedDate(dateFormatter.parse(nonRpClaim.getInvoiceClaimDate()));
			claimHeaderDtoList.add(claimHeaderDto);
	}
		claimHeaderDtoList.sort( new Comparator<ClaimHeaderDTO>() {
			@Override
			public int compare(ClaimHeaderDTO o1, ClaimHeaderDTO o2) {
				if(o1.getCreatedDate() ==  null || o2.getCreatedDate() == null){
					return 0;
				}
				return o2.getCreatedDate().compareTo(o1.getCreatedDate());
			}
		});
		return claimHeaderDtoList;
	}
	
	@Override
	public boolean isValidInvoiceForClaim(Long invoiceNumber) throws SystemException {
		
		boolean isValid = Boolean.FALSE;
		if(CollectionUtils.isEmpty(ClaimLineLocalServiceUtil.getInvoiceTypes(invoiceNumber))) {
			isValid = Boolean.TRUE;
		}
		
		return isValid;
	}
	
	
	/////////////////////////////

	@Override
	public ClaimDTO getInvoiceDetailForClaim(String invoiceNumber) {
		ClaimDTO claimDto = new ClaimDTO();		
		//List<InvoiceDetail> defaultInvoiceDetailList = new ArrayList<>();
		List<InvoiceDetail> invoiceDetailList = new ArrayList<>();
		claimDto.setInvoiceNumber(invoiceNumber);
		if(null != invoiceNumber){
			try {
				List<ClaimInvoiceDetail> claimObjects = ClaimInvoiceDetailLocalServiceUtil.getClaimDetailByInvoiceNumber(Long.parseLong(invoiceNumber));
				if(!claimObjects.isEmpty()) {
					for(ClaimInvoiceDetail view : claimObjects){
						InvoiceDetail invoiceDetail = ClaimDetailMapper.mapInvoiceDetailwithView(view);
						invoiceDetailList.add(invoiceDetail);
					}
					//invoiceDetailList = addLongTitleInvoiceDetail(defaultInvoiceDetailList); //NRP-2746 Change short Title to Long title
				}
			} catch (Exception e) {
				//logger.error(e.getMessage());
			}			
		}
			claimDto.setInvoiceDetail(invoiceDetailList);
		return claimDto;

	}
	
	@Override
	public String submitClaimForm(SubmitClaimDTO submitClaimDTO){
		String responseMsg = "";
		String jsonString = convertClaimObjToJsonObj(submitClaimDTO);
		try {
			responseMsg = ClaimLineLocalServiceUtil.saveClaimDetails(jsonString);
		} catch (Exception e) {
			//logger.error(e.getMessage());
		}
		
		
		return responseMsg;
	}

	@Override
	public JSONArray getClaimTypes() throws SystemException {
		return ClaimLineLocalServiceUtil.getClaimTypeDetails();
	}

	@Override
	public String updateZendeskData(Long claimHeaderKey, Long zendeskTicketNumber, long userId,String publisherTags) throws SystemException {
	
		return ClaimLineLocalServiceUtil.updateZendeskData(claimHeaderKey, zendeskTicketNumber, userId, publisherTags);
		
	}

	@Override
	public List<ClaimHeader> getClaimHeaderForInvoiceHeaderId(long invoiceHeaderId) throws SystemException {
		return ClaimHeaderLocalServiceUtil.getClaimHeaderForInvoiceHeaderKey(invoiceHeaderId);
		 
	}


	@Override
	public List<ClaimLine> getClaimLineForClaimHeaderId(long claimHeaderId) throws SystemException {
		List<ClaimLine> claimLineList = ClaimLineLocalServiceUtil.getClaimLinesForClaimHeaderKey(claimHeaderId);
		//List<ClaimLine> claimLineList = addLongTitle(defaultClaimLineList); //NRP 2746 Release 1.6: Change Short Title to Title on site and reports
		
		return claimLineList.stream().sorted(Comparator.comparing(ClaimLine::getClaimLineNumber, Comparator.nullsLast(Comparator.reverseOrder()))).collect(Collectors.toList());
	}

	@Override
	public ClaimTypeDetail getClaimTypedetailForKey(long claimTypeKey) throws SystemException{//, NoSuchClaimTypeDetailException;
		ClaimTypeDetail ctdt = null;
		try{
			ctdt = ClaimTypeDetailLocalServiceUtil.getClaimTypeDetailForKey(claimTypeKey);
			if(ctdt == null) {
				throw new NoSuchClaimTypeDetailException();
			}
		}
		catch(NoSuchClaimTypeDetailException ne) {
			ne.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return ctdt;
	}

	@Override
	public String logClaimError(Long claimHeaderKey, Long invoiceNumber, Long errorCode, String errorMsg, String type, String comments, Long userId,String userClient,String requestDataJson) throws SQLException {
		return ClaimLineLocalServiceUtil.logClaimError(claimHeaderKey, invoiceNumber, errorCode, errorMsg, type, comments, userId, userClient,requestDataJson);
	}

	@Override
	public List<InvoiceLine> getInvoiceLineForClaimByInvoiceHeaderId(long invoiceHeaderId,long extRepId,boolean hasAllTerritories) throws SystemException {
		List<InvoiceLine> invoiceLineList = InvoiceLineLocalServiceUtil.findByinvoiceHeaderId(invoiceHeaderId,extRepId,hasAllTerritories);
		for (Object[] object : ClaimLineLocalServiceUtil.fetchQuantityForInvoiceHeader(invoiceHeaderId+StringPool.BLANK)){
			for (InvoiceLine invoiceLine : invoiceLineList) {
				if(invoiceLine.getIsbn().equals(object[1]+StringPool.BLANK)){
					invoiceLine.setQuantityClaim(Long.parseLong(object[2]+StringPool.BLANK));
			}}		
		}
		return invoiceLineList.stream().sorted(Comparator.comparing(InvoiceLine::getInvoiceLineNumber, Comparator.nullsLast(Comparator.reverseOrder()))).collect(Collectors.toList());
		
//		invoiceLineList.sort(new Comparator<InvoiceLine>() {
//			@Override
//			public int compare(InvoiceLine o1, InvoiceLine o2) {
//				 return Long.valueOf(o1.getInvoiceLineNumber()).compareTo(o2.getInvoiceLineNumber());
//			}
//		});
//		return invoiceLineList;
	}

	@Override
	public String getPublisherTags(Long claimHeaderKey) throws SystemException {

		String publisherTags = StringUtils.EMPTY;
		for (Object[] object :ClaimLineLocalServiceUtil.getPublisherTags(claimHeaderKey)) {
			publisherTags = object[1].toString();
		}
		return publisherTags;
	}

	@Override
	public String convertClaimObjToJsonObj(SubmitClaimDTO submitClaimDTO) {
		JSONObject jsonObj = JSONFactoryUtil.createJSONObject();
		 
		jsonObj.put(ClaimConstants.BILLTO_KEY, (null != submitClaimDTO.getSubmitClaimHeader().getBillToKey()) ? submitClaimDTO.getSubmitClaimHeader().getBillToKey().longValue() : Long.valueOf("0"));
		jsonObj.put(ClaimConstants.BILLTO_NAME, (null != submitClaimDTO.getSubmitClaimHeader().getBillToName()) ? submitClaimDTO.getSubmitClaimHeader().getBillToName() : "");
		jsonObj.put(ClaimConstants.CLAIM_TYPE_KEY, (null != submitClaimDTO.getSubmitClaimHeader().getClaimTypeKey()) ? submitClaimDTO.getSubmitClaimHeader().getClaimTypeKey().longValue() : Long.valueOf("0"));
		jsonObj.put(ClaimConstants.INVOICE_HEADER_KEY, (null != submitClaimDTO.getSubmitClaimHeader().getInvoiceHeaderKey()) ? submitClaimDTO.getSubmitClaimHeader().getInvoiceHeaderKey().longValue() : Long.valueOf("0"));
		jsonObj.put(ClaimConstants.INVOICE_NUMBER, (null != submitClaimDTO.getSubmitClaimHeader().getInvoiceNumber()) ? submitClaimDTO.getSubmitClaimHeader().getInvoiceNumber().longValue() : Long.valueOf("0"));
		jsonObj.put(ClaimConstants.INVOICE_DATE, (null != submitClaimDTO.getSubmitClaimHeader().getInvoceDate()) ? submitClaimDTO.getSubmitClaimHeader().getInvoceDate() : "");
		jsonObj.put(ClaimConstants.RETAILER_CLAIM_NUMBER, (null != submitClaimDTO.getSubmitClaimHeader().getRetailerClaimNumber()) ? submitClaimDTO.getSubmitClaimHeader().getRetailerClaimNumber() : "");
		jsonObj.put(ClaimConstants.CLAIM_FORM_PAGE, submitClaimDTO.getSubmitClaimHeader().getClaimFormPage());
		jsonObj.put(ClaimConstants.CLAIM_INTEGRATION_STATUS_OBJ, submitClaimDTO.getSubmitClaimHeader().getClaimIntegrationStatus());
		jsonObj.put(ClaimConstants.CLAIM_STATUS_OBJ, submitClaimDTO.getSubmitClaimHeader().getClaimStatus());
		
		jsonObj.put(ClaimConstants.REQUESTED_BY, submitClaimDTO.getSubmitClaimHeader().getRequestedBy());
		jsonObj.put(ClaimConstants.REQUESTED_BY_EMAIL, submitClaimDTO.getSubmitClaimHeader().getRequestedByEmail());
		
		
		//adding Claim lines
		JSONArray claimLineArray = JSONFactoryUtil.createJSONArray();
		JSONObject claimHeaderObject = JSONFactoryUtil.createJSONObject();
		List<String> wrongIsbns = new ArrayList<>();
		for (SubmitClaimLine submitClaimLine : submitClaimDTO.getSubmitClaimLineList()) {
			if(null!=submitClaimLine.getClaimLineNumber()) {
				String isReship = "N";
				JSONObject lineObj = JSONFactoryUtil.createJSONObject();
				JSONObject headerObj = JSONFactoryUtil.createJSONObject();
			
				lineObj.put(ClaimConstants.CLAIM_LINE_NUMBER, submitClaimLine.getClaimLineNumber().longValue());
				lineObj.put(ClaimConstants.INVOICE_LINE_KEY, submitClaimLine.getInvoiceLineKey().longValue());
				lineObj.put(ClaimConstants.PRODUCT_KEY, submitClaimLine.getProductKey().longValue());
				lineObj.put(ClaimConstants.ISBN, submitClaimLine.getIsbn());
				lineObj.put(ClaimConstants.SHORT_TITTLE, submitClaimLine.getShortTittle());
				
				lineObj.put(ClaimConstants.SHORT_AUTHOR, submitClaimLine.getShortAuthor());
				lineObj.put(ClaimConstants.UNIT_PRICE, submitClaimLine.getUnitPrice());
				lineObj.put(ClaimConstants.DISCOUNT_PCT, submitClaimLine.getDiscountPct());
				lineObj.put(ClaimConstants.LINE_AMOUNT, submitClaimLine.getLineAmount());
				lineObj.put(ClaimConstants.SHIP_QTY, submitClaimLine.getShipQty().longValue());
				lineObj.put(ClaimConstants.CLAIM_QTY, submitClaimLine.getClaimQty().longValue());
				lineObj.put(ClaimConstants.REPORTING_GROUP_CODE, submitClaimLine.getReportingGroupCode());
				lineObj.put(ClaimConstants.REPORTING_GROUP_NAME, submitClaimLine.getReportingGroupName());
				lineObj.put(ClaimConstants.CLAIM_TYPE_KEY, submitClaimLine.getClaimTypeKey().longValue());
				lineObj.put(ClaimConstants.WRONG_ISBN, submitClaimLine.getWrongIsbn());

				if(StringUtils.isNotBlank(submitClaimLine.getReshipFlag())) {
					lineObj.put(ClaimConstants.RESHIP_FLAG, "Y");
					isReship = "Y";
				}
				else {
					lineObj.put(ClaimConstants.RESHIP_FLAG, "N");
				}
				
				String claimTypeKey = submitClaimLine.getClaimTypeKey().toString();
				if (claimHeaderObject.has(claimTypeKey)) {
					JSONObject upatedClaimHeaderObj = claimHeaderObject.getJSONObject(claimTypeKey);
					upatedClaimHeaderObj.put(ClaimConstants.TOTAL_CLAIM_QTY, Long.sum(
							upatedClaimHeaderObj.getLong(ClaimConstants.TOTAL_CLAIM_QTY), submitClaimLine.getClaimQty().longValue()));

					if (upatedClaimHeaderObj.getString(ClaimConstants.IS_RESHIP).equals("N")) {
						upatedClaimHeaderObj.put(ClaimConstants.IS_RESHIP, isReship);
					} else {
						upatedClaimHeaderObj.put(ClaimConstants.IS_RESHIP, "Y");
					}
					claimHeaderObject.put(claimTypeKey, upatedClaimHeaderObj);
				} else {
					headerObj.put(ClaimConstants.TOTAL_CLAIM_QTY, submitClaimLine.getClaimQty().longValue());
					headerObj.put(ClaimConstants.IS_RESHIP, isReship);
					headerObj.put(ClaimConstants.CLAIM_COMMENTS, null != submitClaimDTO.getSubmitClaimHeader().getClaimComments() ? submitClaimDTO.getSubmitClaimHeader().getClaimComments() : "");
					headerObj.put(ClaimConstants.CLAIM_TYPE_DESC, submitClaimLine.getClaimTypeDesc());
					claimHeaderObject.put(claimTypeKey, headerObj);
				}
				
				if(null != submitClaimLine.getWrongIsbn() && StringUtils.isNotBlank(submitClaimLine.getWrongIsbn())) {
					wrongIsbns.add(submitClaimLine.getWrongIsbn());
				}
				claimLineArray.put(lineObj);
			}
		}
		
		jsonObj.put(ClaimConstants.CLAIM_HEADER_JSON_OBJECT, claimHeaderObject);
		jsonObj.put("claimLineArray", claimLineArray);
		jsonObj.put(ClaimConstants.WRONG_ISBN, String.join(ClaimConstants.DELIMITER, wrongIsbns));
		return jsonObj.toString();
	}
	/**
	 * 
	 * @param claimLines
	 * @return
	 * @throws SystemException
	 */
	private List<ClaimLine> addLongTitle(List<ClaimLine> claimLines) throws SystemException {
		if (!claimLines.isEmpty()) {
			List<String> isbnList = new ArrayList<>();
			for (ClaimLine claimLine : claimLines) {
				isbnList.add(claimLine.getIsbn());
			}
			Map<String, Product> productTitleMap = commonUtil.getProductTitleMap(isbnList);
			for (ClaimLine claimLine : claimLines) {
				if(claimLine.getIsbn()!= null && productTitleMap.get(claimLine.getIsbn())!= null) {
					if(productTitleMap.get(claimLine.getIsbn()).getTitle()!= null) {
						claimLine.setShortTitle(productTitleMap.get(claimLine.getIsbn()).getTitle());
					}
					else {
						claimLine.setShortTitle("");
					}
					if(productTitleMap.get(claimLine.getIsbn()).getByLine()!= null) {
						claimLine.setShortAuthor(productTitleMap.get(claimLine.getIsbn()).getByLine()); //NRP-2876 Change Short Author value to original Author in NRP
					}
					else {
						claimLine.setShortAuthor("");
					}
				}
				else {
					claimLine.setShortTitle("");
					claimLine.setShortAuthor("");
				}
			}
		}
		return claimLines;
	}
	
	/**
	 * 
	 * @param claimLines
	 * @return
	 * @throws SystemException
	 */
	private List<InvoiceDetail> addLongTitleInvoiceDetail(List<InvoiceDetail> claimLines) throws SystemException {
		if (!claimLines.isEmpty()) {
			List<String> isbnList = new ArrayList<>();
			for (InvoiceDetail claimLine : claimLines) {
				isbnList.add(claimLine.getIsbn());
			}
			Map<String, Product> productTitleMap = commonUtil.getProductTitleMap(isbnList);
			for (InvoiceDetail claimLine : claimLines) {
				if(claimLine.getIsbn()!= null && productTitleMap.get(claimLine.getIsbn())!= null) {
					if(productTitleMap.get(claimLine.getIsbn()).getTitle()!= null) {
						claimLine.setTitle(productTitleMap.get(claimLine.getIsbn()).getTitle());
					}
					else {
						claimLine.setTitle("");
					}
					if(productTitleMap.get(claimLine.getIsbn()).getByLine()!= null) {
						claimLine.setAuthor(productTitleMap.get(claimLine.getIsbn()).getByLine()); //NRP-2876 Change Short Author value to original Author in NRP
					}
					else {
						claimLine.setAuthor("");
					}
				}
				else {
					claimLine.setTitle("");
					claimLine.setAuthor("");
				}
				
			}
		}
		return claimLines;
	}
	
	@Override
	public String cancelClaim(Long claimHeaderKey, String status, Long userId) throws SystemException {
		String responseMsg = "";
		try {
			responseMsg = ClaimLineLocalServiceUtil.updateClaimStatus(claimHeaderKey, status, userId);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return responseMsg;
	}
	
	  private static final Log logger = LogFactoryUtil.getLog(ClaimUtil.class);
}
