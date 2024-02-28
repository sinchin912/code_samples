/**
 * 
 */
package com.hbg.rp.search.constants;

/**
 * @author ravi.kumar
 *
 */
public class ClaimConstants {
	
	public static final String GET_CLAIM_DETAIL = "action=getClaimDetail";
	
	public static final String CLAIM_DATA_JSP = "../claim_detail.jsp";
	public static final String CLAIM_DATA_JSP_PATH = "../claim_detail";
	public static final String CLAIM_PERMISSION_ERROR_JSP = "../rpInvoice/permission-error2";
	public static final String CLAIM_TYPE_ARRAY = "claimTypeJsonArray";
	public static final String JSON_ARRAY = "jsonArray";
	
	public static final String CLAIM_FORM_MAPPING = "submitClaimForm";
	
	public static final String SUBMIT_CLAIM_DTO = "submitClaimDTO";
	public static final String CLAIM_INTEGRATION_STATUS = "NEW";
	public static final String CLAIM_STATUS = "SUBMITTED";
	public static final String INVOICE_DATE = "invoiceDate";
	public static final String CLAIM_FORM_PAGE = "claimFormPage";
	public static final String BILLTO_KEY = "billToKey";
	public static final String BILLTO_NAME = "billToName";
	public static final String CLAIM_TYPE_KEY = "claimTypeKey";
	public static final String INVOICE_HEADER_KEY = "invoiceHeaderKey";
	public static final String INVOICE_NUMBER = "invoiceNumber";
	public static final String CLAIM_COMMENTS = "claimComments";
	public static final String CLAIM_INTEGRATION_STATUS_OBJ = "claimIntegrationStatus";
	public static final String REQUESTED_BY = "requestedBy";
	public static final String REQUESTED_BY_EMAIL = "requestedByEmail";
	public static final String RETAILER_CLAIM_NUMBER = "retailerClaimNumber";
	public static final String CLAIM_STATUS_OBJ = "claimStatus";
	
	public static final String CLAIM_LINE_NUMBER = "claimLineNumber";
	public static final String INVOICE_LINE_KEY = "invoiceLineKey";
	public static final String PRODUCT_KEY = "productKey";
	public static final String ISBN = "isbn";
	public static final String SHORT_TITTLE = "shortTittle";
	public static final String SHORT_AUTHOR = "shortAuthor";
	public static final String UNIT_PRICE= "unitPrice";
	public static final String DISCOUNT_PCT= "discountPct";
	public static final String SHIP_QTY = "shipQty";
	public static final String CLAIM_QTY = "claimQty";
	public static final String LINE_AMOUNT = "lineAmount";
	public static final String RESHIP_FLAG = "reshipFlag";
	public static final String TOTAL_CLAIM_QTY = "totalClaimQty";
	
	public static final String CLAIM_SOURCE = "Retailer Portal Online Form";
	public static final String ZENDESK_CLAIM_TYPE = "Damaged Product";
	public static final String ZENDESK_CLAIM_EMAIL_DESCRPTION = "Thank you for submitting your claim online! Below is a copy of your claim. If necessary, please reply back to this email with any additional comments or questions you may have.";
	public static final String ZENDESK_CLAIM_EMAIL_SUBJECT = "Thank you for submitting your claim on the HBG Retailer Portal";

	public static final String RESHIP_FLAG_YES = "Yes";

	public static final String RESHIP_FLAG_NO = "No";

	public static final String CLAIM_ACCOUNT_NUMBER = "accountNumber";

	public static final String CLAIM_RULE_NO_OF_DAYS_LIMIT = "NUMBER_OF_DAYS_LIMIT";

	public static final String INVOICE_GENERATED_DAYS = "invoiceGeneratedDays";

	public static final String SUCCESS_STR = "success";

	public static final int SUCCESS_CODE = 200;

	public static final String ZENDESK_INFO_STR = "Response from Zendesk claim ticket :";
	
	public static final String DELIMITER = ";";

	public static final String CLAIM_CALLING_PAGE = "claimCallingPage";

	public static final String CLAIM_HEADER_LIST = "claimHeaderList";

	public static final String CLAIM_COMMENT = "claimComment";

	public static final String CLAIM_TYPE = "claimType";

	public static final String CLAIM_TYPE_DESC = "claimTypeDesc";
	
	public static final String ERROR_STR = "error";

	public static final String ZENDESK_ERROR_MSG = "Something went wrong. Please try submitting your claim again.";

	public static final String REPORTING_GROUP_NAME = "REPORTING_GROUP_NAME";

	public static final String REPORTING_GROUP_CODE = "REPORTING_GROUP_CODE";

	public static final String EEE_MMM_DD_KK_MM_SS_YYYY = "EEE MMM dd kk:mm:ss z yyyy";
	public static final String CLAIM_HEADER_KEY = "claimHeaderKey";
	public static final String ZENDESK_API_ERROR_STR = "Create claim Api...";
	public static final String DATE_CREATED = "dateCreated";

	public static final String CREATE_CLAIM_PROC_ERROR = "Create claim Procedure...";
	public static final String NON_RP_CLAIM_FLAG = "nonRpClaimFlag";
	public static final String BLANK = "";
	public static final Object TRUE = "true";
	
	public static final String CLAIM_LINE_ITEMS_DATA = "claimLineItemData";
	public static final String NON_RP_CLAIM = "nonRpCLaim";
	public static final String CREATED_DATE = "createdDate";
	public static final String HEADER_KEY = "headerKey";

	public static final String USER_AGENT = "USER-AGENT";

	public static final String CHROME_BROWSER = "Chrome";

	public static final String FIREFOX_BROWSER = "Firefox";

	public static final String IE_BELOW_11 = "MSIE";

	public static final String IE_BROWSER = "Trident/";

	public static final String SAFARI_BROWSER = "Safari";

	public static final String IS_RESHIP = "isReship";

	public static final String IS_VALID_INVOICE_FOR_CLAIM = "isValidInvoiceForClaim";
	
	private ClaimConstants() {
		
	}
}
