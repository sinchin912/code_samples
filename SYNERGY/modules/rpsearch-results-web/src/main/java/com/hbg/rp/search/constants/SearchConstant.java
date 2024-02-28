package com.hbg.rp.search.constants;

import com.liferay.portal.kernel.util.PropsUtil;

/**
 * @author ravi.kumar
 */
public class SearchConstant {
	
	public static final String RESPONSE_DATA = "data";
	public static final String PARAM_RENDER_JSP = "renderjsp";
	public static final String PARAM_ACTION = "action";
	public static final String PARAM_FORMPARAM = "formparam";
	public static final String PARAM_PORTAL_MAPPING_UTIL = "portalMappingsUtil";
	public static final String ORDERS_UTIL = "ordersUtil";
	public static final String PARAM_LABEL = "label";

	public static final String ZENDESK_TICKET_SUBJECT = "zendesk.ticket.subject";
	public static final String ZENDESK_SUBJECT = PropsUtil.get(ZENDESK_TICKET_SUBJECT);
	public static final String NRP_DROPDOWN_LIMIT = "hbg.nrp.dropdown.limit";

	public static final String MAX_NUMBER_OF_ACCOUNTS = PropsUtil.get(NRP_DROPDOWN_LIMIT);

	public static final String SEARCH_STR = "searchStr";

	public static final String ALL_ACCOUNT = "allAccount";

	public static final String BLANK = "";

	public static final String LIFERAY_SHARED_USER_ALL_ACCOUNTS = "LIFERAY_SHARED_user_all_accounts";

	public static final String LIFERAY_SHARED_USER_ACCOUNTS = "LIFERAY_SHARED_user_accounts";

	public static final String ID = "id";

	public static final String VAL_999 = "-999";

	public static final String NO_MATCH_FOUND = "No match Is found";

	public static final String GET_ACCOUNTS = "getAccounts";

	public static final String VIEW_ALL_ORDERS = "viewallOrders";

	public static final String SEARCH_ORDERS = "searchOrders";

	public static final String SET_ORDER_DETAIL = "setOrderDetail";

	public static final String SEARCH_SHIPMENTS = "searchShipments";

	public static final String SET_SHIPMENT_DETAIL = "setShipmentDetail";

	public static final String SHIPMENT_RESULTS_JSP = "/WEB-INF/views/shipment_results.jsp";

	public static final String SEARCH_SHIPMENT = "search-shipment";
	
	public static final String SEARCH_INVOICE = "search-invoices";
	
	public static final String SEARCH_DEBIT = "search-debits";
	
	public static final String SEARCH_CREDIT = "search-credits";
	
	public static final String SEARCH_TRANSACTION = "search-transactions";
	
	public static final String SEARCH_CATALOG = "search-catalogs";

	public static final String DATA = "data";
	
	public static final String SHIP_DATA = "shipData";

	public static final String SHIPMENT_LIST = "shipmentList";

	public static final String SHIPMENT_LINES = "shipmentLines";

	public static final String HEADER_IDS = "headerIds";

	public static final String ITEM_CODE = "itemCode";

	public static final String SUPPORTED_CARRIER_DATA = "supportedCarrierData";

	public static final String SHIPMENT_LOAD_MORE = "shipmentLoadMore";

	public static final String SHIP_ITEM_CODE = "ship-itemcode";

	public static final String SEARCH_PARAM = "searchParam";

	public static final String INVOICE_HEADER_IDS = "invoiceHeaderIds";

	public static final String WIDTH_8020_JSP = "common/width_8020";

	public static final String SEARCH_ORDER = "search-order";

	public static final String TRUE = "true";

	public static final String ORDER_RESULTS = "/WEB-INF/views/order_results.jsp";

	public static final String ORDER_LOAD_MORE = "order-loadmore";

	public static final String FALSE = "false";

	public static final String ORDER_OFFSET = "order-offset";

	public static final String ORDER_IS_COUNTNEEDED = "order-isCountNeeded";

	public static final String GET_ORDER_DETAIL_MAPPING = "action=getOrderDetail";

	public static final String ORDER_DETAIL_JSP = "/WEB-INF/views/order_detail.jsp";
	
	public static final String ORDER_STATUS_DATA_JSP = "/WEB-INF/views/order_detail.jsp";

	public static final String ORDER_DETAILS_EXPORT = "orderDetailsExport";

	public static final String WIDTH_100_JSP = "common/width_100";

	public static final String FIND_ALL_STORES = "findAllStores";

	public static final String SUCCESS = "success";

	public static final String FAILURE = "failure";

	public static final String ACCOUNT_NAME_KEY = "accountNameKey";

	public static final String FIND_TYPE_AHEAD_STORES = "findTypeAheadStores";

	public static final String GET_ORDER_STATUS_DATA = "getOrderStatusData";

	public static final String CRITERIA_MAP = "criteriaMap";

	public static final String VIEW = "view";

	public static final String CALLING_PAGE = "callingPage";

	public static final String SET_ORDER_STATUS_DATA = "setOrderStatusData";

	public static final String LOGGED_USER_ID = "loggedUserId";

	public static final String HAS_ALL_ACCOUNTS = "hasAllAccounts";

	public static final String GET_CATALOG_DETAIL_PAGE = "getCatalogDetailPage";

	public static final String STATUS_DATA = "statusData";

	public static final String SHIPMENT_DETAILS_EXPORT = "shipmentDetailsExport";

	public static final String ORDER_STATUS_PAGE_MAPPING = "action=getOrderStatusPage";

	public static final String SET_SHIPMENT_DETAIL_MAPPING = "action=setShipmentDetail";

	public static final String GET_LINE_ITEMSFORSHIPMENT_MAPPING = "getLineItemsForShipment";

	public static final String CATALOG_DETAILS_PORTLET = "catalogdetailweb_WAR_catalogdetailweb";

	public static final String SHIPMENT_HEADER_ID = "shipmentHeaderId";

	public static final String SET_LINE_ITEMS_FOR_SHIPMENT = "setLineItemsForShipment";

	public static final String SET_LINE_ITEMS_FOR_SHIPMENT_MAPPING = "action=setLineItemsForShipment";

	public static final String LINE_ITEMS_DATA = "lineItemsData";

	public static final String ORDER_SHIPMENT_LINE_ITEMS_JSP = "../rpOrder/order_shipment_line_items";

	public static final String GET_SHIPMENT_TRACKING_MAPPING = "getShipmentTracking";

	public static final String GET_SHIPMENT_TRACKING_RENDER = "getShipmentTrackingRender";

	public static final String GET_SHIPMENT_TRACKING_RENDER_MAPPING = "action=getShipmentTrackingRender";

	public static final String ZENDESK = "ZENDESK";

	public static final String TRACKING_DATA = "trackingData";

	public static final String ERROR_IO = "ERROR_IO";
	public static final String ERROR = "ERROR";

	public static final String INVALID_SLUG = "INVALID_SLUG";

	public static final String CONNECTION_TIMEOUT = "CONNECTION_TIMEOUT";

	public static final String INVALID_CARRIER = "INVALID_CARRIER";

	public static final String ORDER_STATUS_TEMPLATE_JSP = "/WEB-INF/views/order_status_template.jsp";

	public static final String INITIATE_ZENDESK_TICKET_MAPPING = "initiateZendeskTicket";

	public static final String STATUS = "STATUS";

	public static final String TICKET_ID = "TICKETID";

	public static final String UNCHECKED = "unchecked";

	public static final String INVOICE_NBR = "invoiceNbr";

	public static final String SET_ORDER_DETAIL_MAPPING = "action=" + SET_ORDER_DETAIL;

	public static final String GET_CATALOG_DETAIL_PAGE_MAPPING = "action=" + GET_CATALOG_DETAIL_PAGE;

	public static final String GET_PDF_CONDUENT_MAPPING = "action=getPdfConduent";

	public static final String REDIRECT_CONDUENT_PDF_JSP = "conduent/redirect_conduent_pdf";

	public static final String ROW_INV_TYPE = "rowInvType";

	public static final String CATALOG_RESULTS_JSP = "/WEB-INF/views/catalog_results.jsp";

	public static final String INVOICE_DATA = "invoiceData";

	public static final String SEARCH_INVOICES = "searchInvoices";

	public static final String SET_INVOICES_DETAIL_JSP = "action=setInvoicesDetail";

	public static final String GET_INVOICE_PDF_URL_MAPPING = "getInvoicePdfUrl";

	public static final String INVOICE_RESULTS_JSP = "/WEB-INF/views/invoice_results.jsp";

	public static final String SET_CATALOG_DETAILS = "setCatalogDetails";

	public static final String INVOICE_NO = "invoiceno";

	public static final String SEARCH_CATALOGS_MAPPING = "searchCatalogs";

	public static final String SET_INVOICE_DETAIL = "setInvoicesDetail";

	public static final String SET_CATALOG_DETAILS_MAPPING = "action=setCatalogDetails";

	public static final String SET_ORDER_STATUS_DATA_MAPPING = "action=setOrderStatusData";

	public static final String STORES = "stores";

	public static final String INVOICE_DETAIL_JSP = "/WEB-INF/views/invoice_detail.jsp";
	
	public static final String SEARCH_ERROR_JSP ="/WEB-INF/views/permission-error.jsp";

	public static final String GET_INVOICE_DETAIL_MAPPING = "action=getInvoiceDetail";

	public static final String INVOICE_DETAILS_EXPORT = "invoiceDetailsExport";

	public static final String GET_MEMOS = "getMemos";

	public static final String GET_MEMOS_ACTION_MAPPING = "getMemosAction";

	public static final String GET_MEMOS_MAPPING = "action=getMemos";

	public static final String CRITERIA_TAB_TEMPLATE_JSP = "../rpInvoice/credit/credit_tab_template";

	public static final String DEBIT_TAB_TEMPLATE_JSP = "../rpInvoice/debit/debit_tab_template";

	public static final String GET_INVOICE_HIERARCHY_MAPPING = "getInvoiceHierarchyData";

	public static final String CHILDREN = "children";

	public static final String TEXT = "text";

	public static final String INVOICE_NUMBER = "INVOICE_NUMBER";

	public static final String ORIG_INVOICE_NUMBER = "ORIG_INVOICE_NUMBER";

	public static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

	public static final String FORMAT_M_D_YY = "M/d/yy";

	public static final String INVOICE_DATE = "INVOICE_DATE";

	public static final String INVOICE_CATEGORY = "INVOICE_CATEGORY";

	public static final String INVOICE_CATEGORY_DESC = "INVOICE_CATEGORY_DESC";

	public static final String LEVEL = "LEVEL";

	public static final String CATEGORY_MAPPING = "CATEGORY_MAPPING";

	public static final String INVOICE_HEADER_DATA = "invoiceHeaderData";

	public static final String EXPORT_TO_EXCEL_MAPPING = "exportToExcel";

	public static final String EXPORT_OPTION = "exportOption";

	// STRING BUILDER CONSTANTS

	public static final String SB_TRACKING_REQUEST = " Tracking Request (RP) ";

	public static final String SB_PORTAL_ACCOUNT_LOGIN_NAME = "Portal Account Login Name :";

	public static final String SB_EMAIL_ADDRESS = "Email Address :";

	public static final String SB_ACCOUNT_NAME = "Account Name :";

	public static final String SB_ACCOUNT_NUMBER = "Account Number :";

	public static final String SB_INVOICE_NUMBER = "Invoice Number :";

	public static final String SB_SHIP_GROUP = "Ship Group :";

	public static final String SB_PO = "PO# :";

	public static final String SB_REF_NO = "Reference Number :";

	public static final String SB_OFFER_CODE = "Offer Code :";

	public static final String SB_ORDER_SOURCE = "Order Source :";

	public static final String SB_ORDER_RECEIVED_DATE = "Order Received Date :";

	public static final String SB_ORDER_PROCESSED_DATE = "Order Processed Date :";

	public static final String SB_SHIP_DATE = "Ship Date :";

	public static final String SB_STORE = "Store # :";

	public static final String SB_DESTINATION_ADDR = "Destination Address :";

	public static final String SB_SHIPMENT = "Shipment :";

	public static final String SB_SHIPMENT_METHOD = "Shipment Method :";

	public static final int ZERO = 0;

	public static final int HUNDRED = 100;

	public static final int TEN = 10;

	public static final int ONE = 1;

	public static final int TWO = 2;

	public static final int THREE = 3;

	public static final int FOUR = 4;

	public static final int FIVE = 5;
	
	public static final String NRP_VIEW_AND_SEARCH_CATALOG_DATA = "NRP_VIEW_AND_SEARCH_CATALOG_DATA";
	
	public static final String NRP_VIEW_AND_SEARCH_INVOICE_CREDIT_DEBIT_DATA = "NRP_VIEW_AND_SEARCH_INVOICE_CREDIT_DEBIT_DATA";
	
	public static final String ORDER_CALLING_PAGE = "orderCallingPage";
	
	public static final String GET_CLAIM_LINE_FOR_CLAIM = "getClaimLineForClaim";
	public static final String CLAIM_HEADER_KEY = "claimHeaderKey";
	public static final String CLAIM_LINES = "claimLine";
	public static final String SET_CLAIM_LINE_FOR_CLAIM = "setClaimLineForClaim";
	public static final String SET_CLAIM_LINE_FOR_CLAIM_MAPPING = "action=setClaimLineForClaim";
	public static final String CLAIM_LINE_ITEMS_DATA = "claimLineItemData";
	public static final String CLAIM_LINE_ITEM_JSP = "common/claim_line_item";

	/**
	 * Private constructor. To ensure not instantiated outside class.
	 */
	private SearchConstant() {
		throw new UnsupportedOperationException();
	}
}
