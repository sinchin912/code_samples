package com.hbg.rp.search.constants;

import com.liferay.portal.kernel.util.PropsUtil;

/**
 * The com.hbg.rp.constant.ApplicationConstant is the main constant file on 
 * Application Level.
 * 
 * @author ravi.kumar
 *
 */
public class ApplicationConstant {

	public static final String BUILD_VERSION = ApplicationPropertyReader.getProperty("BUILD_VERSION");
	public static final String INVALID_PDF_MESSAGE = ApplicationPropertyReader.getProperty("invalid_pdf_message");
	public static final String INVALID_PDF_CODE = ApplicationPropertyReader.getProperty("invalid_pdf_code");
	public static final String VALID_PDF_CODE = ApplicationPropertyReader.getProperty("valid_pdf_code");
	
	public static final String ITEM_CODE = "itemcode";
	public static final String PERFORM_SEARCH = "search";
	public static final String SHIPMENT_SEARCH_PARAM = "search-shipment";
	public static final String SHIPMENT_JSP_VIEW = "shipment_results";
	public static final String ORDER_JSP_VIEW = "order_results";
	public static final String PAGE_NAME_RECENT_ACTIVITY = "Recent Activity";
	public static final String PARAM_RESULT_SIZE = "resultSize";
	public static final String PARAM_SHIP_LIMIT = "ship-limit";
	public static final String PARAM_INVOICE_LIMIT = "invoice-limit";
	public static final String PARAM_CATALOG_LIMIT = "catalogs-limit";
	public static final String PARAM_REPORTING_GROUP = "Reporting Group";

	/**
	 * Order search constants.
	 */
	public static final Integer SEARCH_SIZE = Integer.parseInt(PropsUtil.get("excel.export.search.size"));
	public static final Integer PAGE_SIZE = 10;
	public static final Integer PARAM_RECENT_RECORDS_LIMIT = 6;
	public static final String PARAM = "parameters";

	public static final String PREPARING_TO_SHIP = "Preparing to Ship";
	public static final String IN_PROGRESS = "In Progress";
	public static final String ACTION_REQUIRED = "Action Required";
	public static final String BACKORDERED = "Backordered";
	public static final String CANCELED = "Canceled";
	public static final String PARAM_REFNO = "refno";
	public static final String PARAM_PONO = "pono";
	public static final String PARAM_DESTINATION = "address";

	public static final String PARAM_KEY = "key";
	public static final String PARAM_VALUE = "value";

	public static final String ORDER_LINE_STATUS_SHIPPED = "SHIPPED";

	/** Zendesk Constants **/
	public static final String ZENDESK_API_URL = "zendesk.api.url";
	public static final String ZENDESK_API_USER_NAME = "zendesk.api.username";
	public static final String ZENDESK_API_TOKEN = "zendesk.api.token";
	public static final String HBG_ZEN_REST_CLIENT_URI = "hbg.zen.rest.client.uri";
	public static final String HBG_ZEN_REST_CLIENT_CLAIM_TICKET_POST_URI = "hbg.zen.rest.client.claim.ticket.post.uri";
	
	public static final String REF_NO = "refNo";
	public static final String PO_NO = "poNo";
	public static final String INVOICE_NO = "invoiceNo";
	public static final String HIDDEN_ITEM_CODE = "hiddenitemcode";
	
	public static final String PARAM_SHIPMENT_LIST = "shipmentList";
	public static final String PARAM_STATUSES = "statuses";
	public static final String PARAM_TRANSACTION_TYPE = "transactiontypes";
	public static final String PARAM_REPORTING_GROUPS= "reportinggroups";
	
	public static final String ACCOUNT_NAME = "accountName";
	public static final String ACCOUNT_NUMBER = "accountNumber";
	public static final String ORDER_RECV_DATE = "orderRecievedDate";
	public static final String SHIP_DATE = "shipDate";
	public static final String OFFER_CODE = "offerCode";
	public static final String ORDER_SOURCE = "orderSource";
	public static final String ORDER_PROCESSED_DATE = "orderProcessedDate";
	public static final String SHIP_TO_NUMBER = "shiptoNumber";
	public static final String DESITNATION_ADDRESS = "destinationAddress";

	/** Shipment constants */
	public static final String PARAM_SEARCH_SHIP_REFERENCE_NBR = "ship-referenceNbr";
	public static final String PARAM_SEARCH_SHIP_INVOICE_NBR = "ship-invoiceNbr";
	public static final String SHIPGROUP = "shipGroup";
	public static final String SHIPMENT = "shipment";
	public static final String SHIPMENT_METHOD = "shipmentMethod";
	public static final String SHIPMENT_HEADER_ID = "shipmentHeaderId";
	public static final String DEEL_OF_THE_DAY = "DEAL-OF-THE-DAY";
	public static final String CARRIER = 	"carrier";
	public static final String TRACKING_NUM =	"trackingNo";
	public static final String ORDER_HEADER_ID = 	"orderHeaderId";

	public static final String ENCRYPTED_ISBN = "#############";
	public static final String ENCRYPTED_TITLE = "########";
	public static final String ENCRYPTED_AUTHOR = "#####";
	public static final String ENCRYPTED_MSRP = "#####";
	
	public static final Integer SHIPMENT_RELOAD_TIME_MILLIS = 5000;
	public static final Integer SHIPMENT_RELOAD_COUNT = 3;
	
	/** Invoice Constants */
	public static final String INV_INVOICE_NBR = "invoiceno";
	public static final String INV_ORIG_INVOICE_NBR = "invoice-originvoiceno";
	public static final String INV_CATEGORY_NAME = "categoryName";
	public static final String INV_REPORT_NAME = "reportName";
	public static final String INV_INVOICE_TYPE = "invoiceType";
	public static final String INV_INVOICE_TYPE_DEBIT = "DBN";
	public static final String INV_INVOICE_TYPE_CREDIT = "CRN";
	public static final String INV_INVOICE_LIMIT = "invoice-limit";
	public static final String INV_INVOICE_ORDER_REQUIRED = "invoice-order-required";
	public static final String INV_INVOICE_LINE_STATUS_FULFILLED = "Fulfilled";
	public static final String INVOICE_PRINT_DATA_FIELD1_COMMENTS = "35";
	public static final String INVOICE_PRINT_DATA_FIELD1_LINES = "40";
	public static final String INVOICE_LINE_BOM_TYPE = "P";
	
	/** Statement Constants **/
	public static final String INV_PAGE_SIZE = "pageSize";
	public static final String INV_PAGE_NUMBER = "pageNumber";
	public static final String INV_ACCOUNT_NUMBER = "accountNumber";
	public static final String INV_ACCOUNT_LABEL = "accountLabel";
	
	
	public static final String NRP_ADD_EDIT_USERS="NRP_ADD_EDIT_USERS";
	public static final String NRP_ADD_EDIT_HBG_USERS="NRP_ADD_EDIT_HBG_USERS";
	public static final String NRP_EDIT_MARKETING_MATERIALS="NRP_EDIT_MARKETING_MATERIALS";
	public static final String NRP_VIEW_AND_SEARCH_SHIPMENT_DATA="NRP_VIEW_AND_SEARCH_SHIPMENT_DATA";
	public static final String NRP_VIEW_TITLE_AVAILABILITY_CATEGORY="NRP_VIEW_TITLE_AVAILABILITY_CATEGORY";
	public static final String NRP_VIEW_STATEMENTS="NRP_VIEW_STATEMENTS";
	public static final String NRP_RETRIEVE_DEBIT_MEMO_PDF="NRP_RETRIEVE_DEBIT_MEMO_PDF";
	public static final String NRP_RETRIEVE_CREDIT_MEMO_PDF="NRP_RETRIEVE_CREDIT_MEMO_PDF";
	public static final String NRP_RETRIEVE_INVOICE_PDF="NRP_RETRIEVE_INVOICE_PDF";
	public static final String NRP_VIEW_AND_SEARCH_CATALOG_DATA="NRP_VIEW_AND_SEARCH_CATALOG_DATA";
	public static final String NRP_VIEW_AND_SEARCH_INVOICE_CREDIT_DEBIT_DATA="NRP_VIEW_AND_SEARCH_INVOICE_CREDIT_DEBIT_DATA";
	public static final String NRP_SUBMIT_CLAIMS = "NRP_SUBMIT_CLAIMS";
	public static final String NRP_TRACK_CLAIMS = "NRP_TRACK_CLAIMS";

	public static final String NRP_VIEW_INVENTORY_BALANCE="NRP_VIEW_INVENTORY_BALANCE";
	public static final String NRP_SEARCH_BY_AUTHOR_RESIDENCE="NRP_SEARCH_BY_AUTHOR_RESIDENCE";
	
	public static final String RETAIL_PORTAL_SERVICE ="retailportalservice_WAR_retailportalserviceportlet";
	
	
	public static final String VIEW_CATALOG_BOOK_URL = "/catalog-detail?ISBN=";
	
	public static final String EXPORT_CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	
	public static final String BOM_TYPE = "BOM_TYPE";
	
	public static final String TRACKING_NUMBER_TEXT = "Available on Request";



	public static final String ZENDESK = "ZENDESK";
	public static final String CONNECTION_TIMEOUT = "CONNECTION_TIMEOUT";
	public static final String INVALID_CARRIER = "INVALID_CARRIER";
	public static final String ERROR = "ERROR";
	public static final String ERROR_IO = "ERROR_IO";
	public static final String INVALID_SLUG = "INVALID_SLUG";
	public static final String TRACKING_DATA = "trackingData";
	
	public static final String USD_PRICE = "USD";
	public static final String CAD_PRICE = "CAD";
	public static final String TENTATIVE = "tentative";
	public static final String YES = "Y";
	public static final String NO = "N";
	public static final String STATUS_DEFAULT = "D";
	public static final String STATUS_TENTATIVE = "T";
	
	public static final String DEFAULT_PRICE = "0.00";
	public static final String NOT_APPLICABLE_PRICE = "N/A";
	public static final String DEFAULT_PRICE_01 = "0.01";
	
	public static final String EMPTY_STRING = "";
	public static final String SPACE_STRING = " ";
	public static final String NULL_STRING = "null";

	public static final String ORDER_CALLING_PAGE = "orderCallingPage";
	public static final String SUUBSCRIPTION_KEY = "Ocp-Apim-Subscription-Key";
	
	public static final int AUTHOR_SEARCH_MAX_LENGTH = Integer.parseInt(PropsUtil.get("author.search.max.length"));
	public static final String TRACKING_STATUS_DELIVERED = "Delivered";
	
	public static final String LIFERAY_SHARED_EXT_REP_ID = "LIFERAY_SHARED_Ext_Rep_Id";
	public static final String ENCRYPTED_AMOUNT = "(Undisclosed)";
	public static final String SALES_REPRESENTATIVE = "**Sales Representative";
	public static final String SALES_REPRESENTATIVE_DISTRIBUTION_CLIENTS = "**Sales Representative Order Total excludes items sold by other rep groups&#47;publishers. Invoice totals are also unavailable for this reason. However&#44; customers can see full orders and invoice amounts by logging into the Retailer Portal or by requesting invoices at";
	public static final String MAIL_TO_INVOICES_HBG = "invoices@hbgusa.com";
	
	public static final String COMMA = ",";
	public static final String DOLLAR = "$";


	// Excel constants
	public static final String CONTENT_DISPOSITION = "Content-Disposition";
	public static final String DATA_CANNOT_BE_DISPLAYED = "Data cannot display at this time";
	public static final String TEXT_CELL_FORMAT = "text";
	public static final String NUMBER_CELL_FORMAT = "number";
	public static final String CURRENCY_CELL_FORMAT = "currency";
	public static final String MAPPING_LINE_STATUS = "ORDER_LINE_STATUS";
	public static final String FILE_FORMAT = ".xlsx";
	public static final String TRANSACTIONS = "transactions";
	public static final String ITEM_CODE_EAN = "Item Code(ISBN/EAN)";
	public static final String PROMO_CODE = "Promo Code Used on Order";
	public static final String BILL_TO = "Bill To";
	public static final String SHIP_TO = "Ship To";
	public static final String TITLE = "Title";
	public static final String SHORT_AUTHOR = "Short Author";
	public static final String QTY_SHIPPED = "Quantity Shipped";
	public static final String UNIT_PRICE = "Unit Price";
	public static final String DISCOUNT_PCT = "Discount %";
	public static final String NET_CHARGE = "Net Charge";
	public static final String TOTAL_AMOUNT = "Total Amount";
	public static final String PREPAID_AMOUNT = "Pre-paid Amount";
	public static final String AMOUNT_DUE = "Amount Due";
	public static final String ORDER_REFERENCE = "Order Reference #";
	public static final String FULL_TITLE = "Full Title";
	public static final String FULL_AUTHOR = "Full Author";
	public static final String FORMAT = "Format";
	public static final String US_MSRP = "US MSRP";
	public static final String PUBLISHER = "Publisher";
	public static final String PUB_STATUS = "Pub Status";
	public static final String STATUS_DETAILS = "Status Details";
	public static final String ORDER_PLACED_DATE = "Order Placed Date";
	public static final String ORDER_SRC = "Order Source";
	public static final String ORDER_PROCESS_DATE = "Order Processed Date";
	public static final String ACCOUNT_NUM = "Account Number";
	public static final String DESTINATION = "Destination";
	public static final String STORE = "Store #";
	public static final String DESTINATION_ADDRESS = "Destination Address";
	public static final String ORDER_OFFER_CODE = "Offer Code";
	public static final String ORDER_STATUS = "Order Status";
	public static final String ACCOUNT = "Account Name";
	public static final String INVOICE_NUMBER = "Invoice Number";
	public static final String ORDER_RECEIVED_DATE = "Order Received Date";
	public static final String REASON_CODE = "Reason Code";
	public static final String BILLED_IN = "Billed in";
	public static final String[] invoiceFooterCols = { "Invoice date", TOTAL_AMOUNT, PREPAID_AMOUNT, AMOUNT_DUE,
			"Terms", "Please send remittance to", "To pay by Credit Card, please call:", "DUNS", "TIN" };
	public static final String[] orderHeaders = { "PO", ORDER_REFERENCE, "Line Status", "Line Quantity Total",
			ITEM_CODE_EAN, FULL_TITLE, FULL_AUTHOR, FORMAT, "OSD", US_MSRP, PUBLISHER, PUB_STATUS,
			STATUS_DETAILS, ORDER_PLACED_DATE, ORDER_SRC, ORDER_PROCESS_DATE, ORDER_STATUS,
			"Total Shipped", "Total Preparing to Ship", "Total In Progress", "Total Action Required",
			"Total Backordered", "Total Canceled", "Total Order Quantity", "Order Line Cancel Date", ACCOUNT,
			ACCOUNT_NUM, DESTINATION, STORE, DESTINATION_ADDRESS, "Reason Code (Order Line)",
			"Order Discount %", ORDER_OFFER_CODE, "Estimated Net Cost in $" };
	public static final String[] invoiceCols = { "Invoice Date", INVOICE_NUMBER, "PO", ORDER_REFERENCE,
			ORDER_RECEIVED_DATE, ORDER_PROCESS_DATE, ORDER_STATUS, ORDER_SRC, ACCOUNT,
			ACCOUNT_NUM, ORDER_OFFER_CODE, REASON_CODE, DESTINATION, STORE, DESTINATION_ADDRESS,
			TOTAL_AMOUNT, PREPAID_AMOUNT, AMOUNT_DUE, "DUNS", "TIN", BILLED_IN, SHIP_TO, BILL_TO,
			PROMO_CODE, ITEM_CODE_EAN, FULL_TITLE, FULL_AUTHOR, QTY_SHIPPED,
			UNIT_PRICE, DISCOUNT_PCT, NET_CHARGE };
	public static final String[] invoiceShipmentInfoCols = { SHIP_TO, BILL_TO, PROMO_CODE };
	public static final String[] invoiceLineCols = { ITEM_CODE_EAN, TITLE, SHORT_AUTHOR,
			QTY_SHIPPED, UNIT_PRICE, DISCOUNT_PCT, NET_CHARGE };
	public static final String[] invoiceLineColsDebit = { ITEM_CODE_EAN, TITLE, SHORT_AUTHOR,
			"Quantity Debit", "Quantity Claim", UNIT_PRICE, DISCOUNT_PCT, NET_CHARGE };
	public static final String[] invoiceLineColsCredit = { ITEM_CODE_EAN, TITLE, SHORT_AUTHOR,
			"Quantity Credit", "Quantity Claim", UNIT_PRICE, DISCOUNT_PCT, NET_CHARGE };
	public static final String[] shipmentHeaders = { ORDER_REFERENCE, ACCOUNT, ACCOUNT_NUM,
			DESTINATION, DESTINATION_ADDRESS, STORE, "PO", "Invoice #", "Carrier", "Shipment Method",
			"Ship Group", "Carrier Tracking Number", ORDER_PLACED_DATE, QTY_SHIPPED, "Shipment Status",
			"Delivery Date", "Order Quantity Total", "Shipped Date", ITEM_CODE_EAN, FULL_TITLE, FULL_AUTHOR,
			FORMAT, "OSD", US_MSRP, PUBLISHER, PUB_STATUS };
	public static final String[] orderCols = { ORDER_PLACED_DATE, "PO", ORDER_REFERENCE, "Line Status",
			"Line Quantity Total", ITEM_CODE_EAN, FULL_TITLE, FULL_AUTHOR, FORMAT, "OSD", US_MSRP,
			"Canadian MSRP", PUBLISHER, PUB_STATUS, STATUS_DETAILS, ORDER_SRC, ORDER_PROCESS_DATE,
			ORDER_STATUS, "Total Shipped", "Total Preparing to Ship", "Total In Progress", "Total Action Required",
			"Total Backordered", "Total Canceled", "Total Order Quantity", "Order Line Cancel Date", ACCOUNT,
			ACCOUNT_NUM, DESTINATION, STORE, DESTINATION_ADDRESS, "Reason Code (Order Line)",
			"Order Discount %", ORDER_OFFER_CODE, "Estimated Net Cost" };
	public static final String[] orderStyles = { TEXT_CELL_FORMAT,TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, NUMBER_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT,
			TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, NUMBER_CELL_FORMAT, NUMBER_CELL_FORMAT,
			NUMBER_CELL_FORMAT, NUMBER_CELL_FORMAT, NUMBER_CELL_FORMAT, NUMBER_CELL_FORMAT, NUMBER_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT,
			TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, CURRENCY_CELL_FORMAT };
	
	public static final String[] invoiceStyles = { TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT,
			TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, CURRENCY_CELL_FORMAT, CURRENCY_CELL_FORMAT, CURRENCY_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT,
			TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, CURRENCY_CELL_FORMAT, TEXT_CELL_FORMAT, CURRENCY_CELL_FORMAT };

	public static final String[] creditCols = { "Credit Date", "Credit Number", "PO", ORDER_REFERENCE,
			ORDER_RECEIVED_DATE, ORDER_PROCESS_DATE, ORDER_STATUS, ORDER_SRC, ACCOUNT,
			ACCOUNT_NUM, ORDER_OFFER_CODE, REASON_CODE, DESTINATION, STORE, DESTINATION_ADDRESS,
			TOTAL_AMOUNT, PREPAID_AMOUNT, AMOUNT_DUE, "DUNS", "TIN", BILLED_IN, SHIP_TO, BILL_TO,
			PROMO_CODE, ITEM_CODE_EAN, FULL_TITLE, FULL_AUTHOR, QTY_SHIPPED,
			UNIT_PRICE, DISCOUNT_PCT, NET_CHARGE };
	public static final String[] creditStyles = { TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT,
			TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, CURRENCY_CELL_FORMAT, CURRENCY_CELL_FORMAT, CURRENCY_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT,
			TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, CURRENCY_CELL_FORMAT, TEXT_CELL_FORMAT, CURRENCY_CELL_FORMAT };

	public static final String[] debitCols = { "Debit Date", "Debit Number", "PO", ORDER_REFERENCE,
			ORDER_RECEIVED_DATE, ORDER_PROCESS_DATE, ORDER_STATUS, ORDER_SRC, ACCOUNT,
			ACCOUNT_NUM, ORDER_OFFER_CODE, REASON_CODE, DESTINATION, STORE, DESTINATION_ADDRESS,
			TOTAL_AMOUNT, PREPAID_AMOUNT, AMOUNT_DUE, "DUNS", "TIN", BILLED_IN, SHIP_TO, BILL_TO,
			PROMO_CODE, ITEM_CODE_EAN, FULL_TITLE, FULL_AUTHOR, QTY_SHIPPED,
			UNIT_PRICE, DISCOUNT_PCT, NET_CHARGE };
	public static final String[] debitStyles = { TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT,
			TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, CURRENCY_CELL_FORMAT, CURRENCY_CELL_FORMAT, CURRENCY_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT,
			TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, CURRENCY_CELL_FORMAT, TEXT_CELL_FORMAT, CURRENCY_CELL_FORMAT };

	public static final String[] transactionCols = { "Transaction Type", "Invoice Date", INVOICE_NUMBER, "PO",
			ORDER_REFERENCE, ORDER_RECEIVED_DATE, ORDER_PROCESS_DATE, ORDER_STATUS, ORDER_SRC,
			ACCOUNT, ACCOUNT_NUM, ORDER_OFFER_CODE, REASON_CODE, DESTINATION, STORE,
			DESTINATION_ADDRESS, TOTAL_AMOUNT, PREPAID_AMOUNT, AMOUNT_DUE, "DUNS", "TIN", BILLED_IN,
			SHIP_TO, BILL_TO, PROMO_CODE, ITEM_CODE_EAN, FULL_TITLE, FULL_AUTHOR,
			QTY_SHIPPED, UNIT_PRICE, DISCOUNT_PCT, NET_CHARGE };
	public static final String[] transactionStyles = { TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT,
			TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, CURRENCY_CELL_FORMAT, CURRENCY_CELL_FORMAT, CURRENCY_CELL_FORMAT, TEXT_CELL_FORMAT,
			TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, CURRENCY_CELL_FORMAT, TEXT_CELL_FORMAT, CURRENCY_CELL_FORMAT };

	public static final String[] shipmentCols = { "Ship Group", "Carrier Tracking Number", "Shipment Carrier",
			"Shipment Method", INVOICE_NUMBER, ORDER_PLACED_DATE, "Items Shipped Date", "PO", ORDER_REFERENCE,
			"Line Order Quantity Total", "Line Shipped Quantity Total", ITEM_CODE_EAN, FULL_TITLE,
			FULL_AUTHOR, FORMAT, "OSD", US_MSRP, "Canadian MSRP", PUBLISHER, PUB_STATUS, STATUS_DETAILS,
			ORDER_SRC, ACCOUNT, ACCOUNT_NUM, DESTINATION, STORE, DESTINATION_ADDRESS };
	public static final String[] shipmentStyles = { TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT,
			TEXT_CELL_FORMAT, NUMBER_CELL_FORMAT, NUMBER_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT,
			TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT, TEXT_CELL_FORMAT };

	public static final String MAPPING_REASON_CODE_STATUS = "LINE_STATUS_REASON_CODE";
	
	public static final String REPORTING_GROUP_KEY = "REPORTING_GROUP";

	private ApplicationConstant() {
		throw new IllegalAccessError("Utility class");
	}
	
}
