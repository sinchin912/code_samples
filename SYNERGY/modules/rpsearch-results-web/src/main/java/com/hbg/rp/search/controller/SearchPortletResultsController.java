package com.hbg.rp.search.controller;

import static com.hbg.rp.search.constants.ApplicationConstant.SHIPMENT_SEARCH_PARAM;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.hbg.rp.model.ClaimLine;
import com.hbg.rp.model.ClaimTypeDetail;
import com.hbg.rp.model.InvoiceHeader;
import com.hbg.rp.model.InvoiceLine;
import com.hbg.rp.model.OrderHeader;
import com.hbg.rp.search.constants.ApplicationConstant;
import com.hbg.rp.search.constants.ClaimConstants;
import com.hbg.rp.search.constants.SearchConstant;
import com.hbg.rp.search.constants.SearchStaticData;
import com.hbg.rp.search.dto.SearchOrderDTO;
import com.hbg.rp.search.dto.Tracking;
import com.hbg.rp.search.dto.ZendeskClaimDTO;
import com.hbg.rp.search.dto.ZendeskDTO;
import com.hbg.rp.search.exceptions.ShipmentTrackingException;
import com.hbg.rp.search.model.ClaimDTO;
import com.hbg.rp.search.model.ClaimHeaderDTO;
import com.hbg.rp.search.model.InvoiceDTO;
import com.hbg.rp.search.model.InvoiceDetail;
import com.hbg.rp.search.model.OrderDTO;
import com.hbg.rp.search.model.OrderExportDTO;
import com.hbg.rp.search.model.OrderStatusDTO;
import com.hbg.rp.search.model.SubmitClaimDTO;
import com.hbg.rp.search.util.ICatalogUtil;
import com.hbg.rp.search.util.IClaimUtil;
import com.hbg.rp.search.util.ICommonUtil;
import com.hbg.rp.search.util.IExportUtil;
import com.hbg.rp.search.util.IGenericCacheHandler;
import com.hbg.rp.search.util.IInvoiceUtil;
import com.hbg.rp.search.util.IOrderUtil;
import com.hbg.rp.search.util.IPortalMappingsUtil;
import com.hbg.rp.search.util.IShipmentTrackingUtil;
import com.hbg.rp.search.util.IShipmentUtil;
import com.hbg.rp.search.zendesk.ZendeskService;
import com.hbg.rp.service.InvoiceHeaderLocalServiceUtil;
import com.hbg.rp.service.OrderHeaderLocalServiceUtil;
import com.hbg.rp.service.ShipmentLocalServiceUtil;
import com.hbg.rp.service.ClaimLineLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portletmvc4spring.bind.annotation.ActionMapping;
import com.liferay.portletmvc4spring.bind.annotation.EventMapping;
import com.liferay.portletmvc4spring.bind.annotation.RenderMapping;
import com.liferay.portletmvc4spring.bind.annotation.ResourceMapping;

import java.io.IOException;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.EventPortlet;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.MutableRenderParameters;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;
import javax.xml.namespace.QName;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zendesk.client.v2.model.Ticket;

/**
 * @author ravi.kumar
 */
@Controller
@RequestMapping("VIEW")
public class SearchPortletResultsController implements EventPortlet{

	@Autowired
	private IOrderUtil orderUtil;

	@Autowired
	private IInvoiceUtil invoiceUtil;

	@Autowired
	private ICatalogUtil catalogUtil;

	@Autowired
	private IGenericCacheHandler genericCacheHandler;

	@Autowired
	private ICommonUtil commonUtil;

	@Autowired
	private IPortalMappingsUtil portalMappingsUtil;

	@Autowired
	private IClaimUtil claimUtil;

	@Autowired
	private IExportUtil exportUtil;

	@Autowired
	private IShipmentUtil shipmentUtil;

	@Autowired
	private IShipmentTrackingUtil shipmentTrackingUtil;

	@Autowired
	private ZendeskService zendeskService;

	private static final String NRP_VIEW_AND_SEARCH_INVOICE_CREDIT_DEBIT_DATA = "NRP_VIEW_AND_SEARCH_INVOICE_CREDIT_DEBIT_DATA";
	private static final String RESPONSE_DATA = "data";
	private static final String RESPONSE_SHIP_DATA = "shipData";
	private static final String RESPONSE_ORDER_DATA = "orderData";
	private static final String ORDERS_UTIL = "ordersUtil";
	private static final String SET_INVOICE_DETAIL = "setInvoiceDetail";
	private static final String PARAM_LIFERAY_SHARED_EXT_REP_ID = "LIFERAY_SHARED_Ext_Rep_Id";
	private static final String LIFERAY_SHARED_REP_USER_ALL_TERRITORIES = "LIFERAY_SHARED_rep_user_all_territories";

	@SuppressWarnings("unchecked")
	@RenderMapping
	public String renderSearchPage(Model model, RenderResponse response, RenderRequest request) throws SystemException {
		long extRepId = (long) request.getPortletSession().getAttribute(PARAM_LIFERAY_SHARED_EXT_REP_ID, PortletSession.APPLICATION_SCOPE);
		boolean hasAllTerritories = (boolean) request.getPortletSession().getAttribute(LIFERAY_SHARED_REP_USER_ALL_TERRITORIES, PortletSession.APPLICATION_SCOPE);
		model.addAllAttributes(orderUtil.getPreRequisiteModel());
		model.addAllAttributes(invoiceUtil.getPreRequisiteModel());
		model.addAllAttributes(catalogUtil.getPrerequisitModel());
		model.addAttribute(SearchConstant.NRP_VIEW_AND_SEARCH_CATALOG_DATA,
				checkPermission(SearchConstant.NRP_VIEW_AND_SEARCH_CATALOG_DATA, request));
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		String pageName = themeDisplay.getLayout().getName(themeDisplay.getLocale());
		if (SearchStaticData.orderPortletNamespace.isEmpty()) {
			SearchStaticData.orderPortletNamespace = response.getNamespace();
		}

		@SuppressWarnings("unchecked")
		Map<String, String> orderSearchCriteriaMap = commonUtil.getCriteriaMapForResourceRequest(request);
		model.addAttribute("formparam", orderSearchCriteriaMap);
		List<OrderDTO> orderDtoList = orderUtil.getOrderHeadersForDefaultLanding(orderSearchCriteriaMap, pageName,extRepId, hasAllTerritories);
		model.addAttribute(RESPONSE_DATA, orderDtoList);
		model.addAttribute("isRecentActivity", true);
		orderSearchCriteriaMap.put("search-order", "false");

		if (SearchStaticData.shipmentPortletNamespace.isEmpty()) {
			SearchStaticData.shipmentPortletNamespace = response.getNamespace();
		}
		model.addAttribute("formparam", orderSearchCriteriaMap);
		Map<String, Object> resultMap = shipmentUtil.getShipments(orderSearchCriteriaMap,
				orderSearchCriteriaMap.get(SHIPMENT_SEARCH_PARAM), extRepId,hasAllTerritories);
		model.addAttribute("supportedCarrierData", shipmentTrackingUtil.getAllCarrierDetails());
		model.addAttribute(RESPONSE_SHIP_DATA, resultMap.get("shipmentList"));
		model.addAttribute(ApplicationConstant.PARAM_RESULT_SIZE, resultMap.get(ApplicationConstant.PARAM_RESULT_SIZE));
		model.addAttribute("invoiceHeaderData", invoiceUtil.getInvoiceHeaders((List<Long>) resultMap.get("invoiceHeaderIds")));
		return modelAndView("view", model);
	}

	/**
	 * Adds the default objects to model attribute for render call.
	 * @throws SystemException
	 */
	private String modelAndView(String fileName, Model model) throws SystemException {
		// Default additions.
		model.addAttribute("portalMappingsUtil", portalMappingsUtil);
		model.addAttribute(ORDERS_UTIL, orderUtil);
		model.addAllAttributes(orderUtil.getPreRequisiteModel());
		return fileName;
	}

	public boolean checkPermission(String actionId, RenderRequest request) {
		boolean result = false;
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		List<Role> userRoles = RoleLocalServiceUtil.getUserRoles(themeDisplay.getUserId());
		for (Role role : userRoles) {
			if (role.getName().equalsIgnoreCase("all") || role.getName().equalsIgnoreCase(actionId)) {
				result = true;
				break;
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@EventMapping
	public void processEvent(EventRequest request, EventResponse response)  {
		javax.portlet.Event event = request.getEvent();
		SearchOrderDTO searchOrderDTO = (SearchOrderDTO) event.getValue();
		Map<String, String[]> mapObj=searchOrderDTO.getMapObj();
		request.setAttribute(ApplicationConstant.PARAM, mapObj);
		MutableRenderParameters mutableRenderParameters = response.getRenderParameters();
		Map searchMapObj = getMapBySearch(mapObj.entrySet());
		String setRenderPage = "";
		if (searchMapObj.containsKey(SearchConstant.SEARCH_SHIPMENT)) {
			String value = (String) searchMapObj.get(SearchConstant.SEARCH_SHIPMENT);
			if (SearchConstant.TRUE.equalsIgnoreCase(value)) {
				setRenderPage = "setShipmentDetail";
			}
		} else if (searchMapObj.containsKey(SearchConstant.SEARCH_INVOICE)) {
			String value = (String) searchMapObj.get(SearchConstant.SEARCH_INVOICE);
			if (SearchConstant.TRUE.equalsIgnoreCase(value)) {
				setRenderPage = SET_INVOICE_DETAIL;
			}
		} else if (searchMapObj.containsKey(SearchConstant.SEARCH_DEBIT)) {
			String value = (String) searchMapObj.get(SearchConstant.SEARCH_DEBIT);
			if (SearchConstant.TRUE.equalsIgnoreCase(value)) {
				setRenderPage = SET_INVOICE_DETAIL;
			}
		} else if (searchMapObj.containsKey(SearchConstant.SEARCH_CREDIT)) {
			String value = (String) searchMapObj.get(SearchConstant.SEARCH_CREDIT);
			if (SearchConstant.TRUE.equalsIgnoreCase(value)) {
				setRenderPage = SET_INVOICE_DETAIL;
			}
		} else if (searchMapObj.containsKey(SearchConstant.SEARCH_TRANSACTION)) {
			String value = (String) searchMapObj.get(SearchConstant.SEARCH_TRANSACTION);
			if (SearchConstant.TRUE.equalsIgnoreCase(value)) {
				setRenderPage = SET_INVOICE_DETAIL;
			}
		} else if (searchMapObj.containsKey(SearchConstant.SEARCH_CATALOG)) {
			String value = (String) searchMapObj.get(SearchConstant.SEARCH_CATALOG);
			if (SearchConstant.TRUE.equalsIgnoreCase(value)) {
				setRenderPage = "setCatalogDetail";
			}
		} else {
			setRenderPage = "setOrderDetail";
		}
		mutableRenderParameters.removeParameter("action");
		mutableRenderParameters.setValue("action", setRenderPage);
	}

	@RenderMapping(params = "action=setOrderDetail")
	public String setOrdersDetail(Model model, RenderRequest request, RenderResponse response)
			throws SystemException {
		long extRepId = (long) request.getPortletSession().getAttribute(PARAM_LIFERAY_SHARED_EXT_REP_ID, PortletSession.APPLICATION_SCOPE);
		boolean hasAllTerritories = (boolean) request.getPortletSession().getAttribute(LIFERAY_SHARED_REP_USER_ALL_TERRITORIES, PortletSession.APPLICATION_SCOPE);
		@SuppressWarnings(SearchConstant.UNCHECKED)
		Map<String, String> titleCriteria = commonUtil.getCriteriaMapForRenderRequest(request);
		titleCriteria.put(SearchConstant.SEARCH_ORDER, SearchConstant.TRUE);
		model.addAllAttributes(orderUtil.getPreRequisiteModel());
		model.addAllAttributes(invoiceUtil.getPreRequisiteModel());
		model.addAllAttributes(catalogUtil.getPrerequisitModel());
		model.addAttribute(SearchConstant.NRP_VIEW_AND_SEARCH_CATALOG_DATA,
				checkPermission(SearchConstant.NRP_VIEW_AND_SEARCH_CATALOG_DATA, request));
		model.addAttribute(SearchConstant.PARAM_PORTAL_MAPPING_UTIL, portalMappingsUtil);
		model.addAttribute(SearchConstant.ORDERS_UTIL, orderUtil);
		model.addAttribute(SearchConstant.PARAM_RENDER_JSP, SearchConstant.ORDER_RESULTS);
		model.addAttribute(SearchConstant.PARAM_FORMPARAM, titleCriteria);
		if (titleCriteria.containsKey(SearchConstant.ORDER_LOAD_MORE)) {
			String value = titleCriteria.get(SearchConstant.ORDER_LOAD_MORE);
			if (!SearchConstant.TRUE.equalsIgnoreCase(value)) {
				titleCriteria.put(SearchConstant.ORDER_OFFSET, SearchConstant.BLANK);
			} else {
				titleCriteria.put(SearchConstant.ORDER_IS_COUNTNEEDED, SearchConstant.FALSE);
			}
		}
		model.addAttribute(SearchConstant.DATA, orderUtil.getOrderHeaders(titleCriteria,extRepId, hasAllTerritories));
		return SearchConstant.WIDTH_8020_JSP;
	}

	@RenderMapping(params = SearchConstant.GET_ORDER_DETAIL_MAPPING)
	public String getOrderDetail(Model model, RenderRequest request, RenderResponse response) throws Exception {
		logger.info("*************getOrderDetail() start************* " + java.time.LocalDateTime.now());
		long extRepId = (long) request.getPortletSession().getAttribute(PARAM_LIFERAY_SHARED_EXT_REP_ID, PortletSession.APPLICATION_SCOPE);
		boolean hasAllTerritories = (boolean) request.getPortletSession().getAttribute(LIFERAY_SHARED_REP_USER_ALL_TERRITORIES, PortletSession.APPLICATION_SCOPE);
		String refNo = request.getParameter(ApplicationConstant.PARAM_REFNO);
		request.setAttribute(ApplicationConstant.REF_NO, refNo);
		OrderDTO orderDetails = orderUtil.getOrderDetailData(refNo, true, request, extRepId,hasAllTerritories);
		model.addAttribute(SearchConstant.RESPONSE_DATA, orderDetails);
		model.addAttribute(SearchConstant.PARAM_RENDER_JSP, SearchConstant.ORDER_DETAIL_JSP);
		model.addAllAttributes(orderUtil.getPreRequisiteModel());
		model.addAllAttributes(invoiceUtil.getPreRequisiteModel());
		model.addAllAttributes(catalogUtil.getPrerequisitModel());
		model.addAttribute(SearchConstant.NRP_VIEW_AND_SEARCH_CATALOG_DATA,
				checkPermission(SearchConstant.NRP_VIEW_AND_SEARCH_CATALOG_DATA, request));
		model.addAttribute(SearchConstant.PARAM_PORTAL_MAPPING_UTIL, portalMappingsUtil);
		model.addAttribute(SearchConstant.ORDERS_UTIL, orderUtil);
		model.addAttribute("isRecentActivity", false);
		HttpSession session = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request))
				.getSession();
		session.setAttribute(SearchConstant.ORDER_DETAILS_EXPORT + refNo, new OrderExportDTO(orderDetails));

		Map<String, String> criteriaMap = new HashMap<>();
		criteriaMap.put(ApplicationConstant.PARAM_REFNO, refNo);
		request.setAttribute(SearchConstant.CRITERIA_MAP, criteriaMap);
		String callingPage = ParamUtil.getString(request, SearchConstant.CALLING_PAGE);
		request.setAttribute(SearchConstant.CALLING_PAGE, callingPage);
		request.setAttribute("tab", "orders");
		// Call
		model.addAttribute(RESPONSE_ORDER_DATA, orderDetails);
		setOrderStatusData(model, request, response);
		logger.info("*************getOrderDetail() end************* " + java.time.LocalDateTime.now());
		return "order_detail";
	}

	/**
	 * Get order status page by reference number.
	 *
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 */
	@RenderMapping(params = SearchConstant.ORDER_STATUS_PAGE_MAPPING)
	public String getOrderStatusPage(Model model, RenderRequest request, RenderResponse response)
			throws SystemException, PortalException {
		long extRepId = (long) request.getPortletSession().getAttribute(PARAM_LIFERAY_SHARED_EXT_REP_ID, PortletSession.APPLICATION_SCOPE);
		boolean hasAllTerritories = (boolean) request.getPortletSession().getAttribute(LIFERAY_SHARED_REP_USER_ALL_TERRITORIES, PortletSession.APPLICATION_SCOPE);
		String refNo = ParamUtil.getString(request, ApplicationConstant.PARAM_REFNO);
		request.setAttribute(ApplicationConstant.REF_NO, ParamUtil.getString(request, ApplicationConstant.PARAM_REFNO));
		OrderDTO orderDto = orderUtil.getOrderDetailData(refNo, true, request, extRepId,hasAllTerritories);
		model.addAttribute(SearchConstant.RESPONSE_DATA, orderDto);
		HttpSession session = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request)).getSession();
		session.setAttribute(SearchConstant.ORDER_DETAILS_EXPORT + refNo, new OrderExportDTO(orderDto));
		model.addAttribute(SearchConstant.PARAM_PORTAL_MAPPING_UTIL, portalMappingsUtil);
		model.addAttribute(SearchConstant.ORDERS_UTIL, orderUtil);
		model.addAllAttributes(orderUtil.getPreRequisiteModel());
		model.addAllAttributes(invoiceUtil.getPreRequisiteModel());
		model.addAllAttributes(catalogUtil.getPrerequisitModel());
		model.addAttribute(SearchConstant.NRP_VIEW_AND_SEARCH_CATALOG_DATA,
				checkPermission(SearchConstant.NRP_VIEW_AND_SEARCH_CATALOG_DATA, request));
		model.addAttribute(SearchConstant.PARAM_RENDER_JSP, SearchConstant.ORDER_DETAIL_JSP);
		Map<String, String> criteriaMap = new HashMap<>();
		criteriaMap.put(ApplicationConstant.PARAM_REFNO, refNo);
		request.setAttribute(SearchConstant.CRITERIA_MAP, criteriaMap);
		request.setAttribute("tab", "shipments");
		// Call
		model.addAttribute(RESPONSE_ORDER_DATA, orderDto);
		setOrderStatusData(model, request, response);
		return SearchConstant.WIDTH_100_JSP;
	}

	/**
	 * Render mapping for getting order status data. Called from
	 *
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws SystemException
	 */
	@RenderMapping(params = SearchConstant.SET_ORDER_STATUS_DATA_MAPPING)
	public String setOrderStatusData(Model model, RenderRequest request, RenderResponse response)
			throws SystemException, PortalException {
		HttpSession session = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request))
				.getSession();
		long extRepId = (long) request.getPortletSession().getAttribute(PARAM_LIFERAY_SHARED_EXT_REP_ID, PortletSession.APPLICATION_SCOPE);
		boolean hasAllTerritories = (boolean) request.getPortletSession().getAttribute(LIFERAY_SHARED_REP_USER_ALL_TERRITORIES, PortletSession.APPLICATION_SCOPE);
		Map<String, String> criteriaMap = (Map<String, String>) request.getAttribute(SearchConstant.CRITERIA_MAP);
		criteriaMap.put(SearchConstant.LOGGED_USER_ID, commonUtil.getLoggedInUserId(request));
		criteriaMap.put(SearchConstant.HAS_ALL_ACCOUNTS, String.valueOf(request.getPortletSession()
				.getAttribute(SearchConstant.LIFERAY_SHARED_USER_ALL_ACCOUNTS, PortletSession.APPLICATION_SCOPE)));

		OrderStatusDTO orderStatusDTO = new OrderStatusDTO();
		OrderDTO orderData = (OrderDTO) model.getAttribute(RESPONSE_ORDER_DATA);
		String referenceNumber;
		if (null != orderData) {
			OrderHeader orderHeader = orderData.getOrderHeader();
			orderUtil.setOrderAndLineStatusData(orderHeader, orderData.getOrderLines(), orderStatusDTO, request, extRepId,hasAllTerritories);
			referenceNumber = null != orderHeader ? String.valueOf(orderHeader.getReferenceNbr()) : SearchConstant.BLANK;
			session.setAttribute(SearchConstant.ORDER_DETAILS_EXPORT + referenceNumber, new OrderExportDTO(orderData));
		} else {
			List<OrderHeader> orderHeaderData = OrderHeaderLocalServiceUtil.getOrderHeaders(criteriaMap, SearchConstant.TEN, extRepId, hasAllTerritories);
			orderUtil.setOrderStatusData(orderHeaderData, orderStatusDTO, request, extRepId,hasAllTerritories);
			referenceNumber = orderHeaderData.size() > SearchConstant.ZERO
					? String.valueOf(orderHeaderData.get(SearchConstant.ZERO).getReferenceNbr())
					: SearchConstant.BLANK;

			session.setAttribute(SearchConstant.ORDER_DETAILS_EXPORT + referenceNumber,
					new OrderExportDTO(orderUtil.getOrderDetailData(referenceNumber, true, request,extRepId,hasAllTerritories)));
		}

		String codeValue = claimUtil.getCodeValueForClaimRule(ClaimConstants.CLAIM_RULE_NO_OF_DAYS_LIMIT);
		model.addAttribute(ClaimConstants.INVOICE_GENERATED_DAYS, codeValue);
		model.addAttribute(SearchConstant.STATUS_DATA, orderStatusDTO);
		model.addAttribute(SearchConstant.PARAM_PORTAL_MAPPING_UTIL, portalMappingsUtil);
		model.addAttribute(SearchConstant.ORDERS_UTIL, orderUtil);
		String callingPage = (String) request.getAttribute(SearchConstant.CALLING_PAGE);
		String tab = (String) request.getAttribute("tab");
		model.addAttribute(SearchConstant.CALLING_PAGE, callingPage);
		model.addAttribute("tab", tab);
		setInvoiceHeaders(model, orderStatusDTO.getInvoiceHeaderIds());
		session.setAttribute(SearchConstant.SHIPMENT_DETAILS_EXPORT + referenceNumber, orderStatusDTO);
		return "order_detail";
	}

	private void setInvoiceHeaders(Model model, List<Long> invoiceHeaders) throws SystemException {
		model.addAttribute(SearchConstant.INVOICE_HEADER_DATA, invoiceUtil.getInvoiceHeaders(invoiceHeaders));
	}

	@ResourceMapping(value="orderSearchExport")
	public void orderSearchExport(ResourceRequest request, ResourceResponse response) throws IOException, SystemException, PortalException, ParseException {
		long extRepId = (long) request.getPortletSession().getAttribute(PARAM_LIFERAY_SHARED_EXT_REP_ID, PortletSession.APPLICATION_SCOPE);
		boolean hasAllTerritories = (boolean) request.getPortletSession().getAttribute(LIFERAY_SHARED_REP_USER_ALL_TERRITORIES, PortletSession.APPLICATION_SCOPE);
		String exportMapString = ParamUtil.getString(request, SearchConstant.EXPORT_OPTION);
		Map<String, String> orderSearchOption  = convertMapToString(exportMapString);
		List<Object []> orderSearchResult = OrderHeaderLocalServiceUtil.searchAndExportOrderData(orderSearchOption, ApplicationConstant.SEARCH_SIZE,extRepId, hasAllTerritories);
		String permittedGroups = commonUtil.getPermittedReportingGroups(request);
		exportUtil.writeSearchResultExcel(response, orderSearchResult, permittedGroups,"Order",extRepId,hasAllTerritories);
	}

	private Map<String,String> convertMapToString(String mapString){
		String exportMapString = mapString.trim().substring(1, mapString.trim().length() - 1);
		Map<String, String> exportOption = new HashMap<>();
		String[] pairs = exportMapString.split(",");
		for (String pair : pairs) {
			String[] keyValue = pair.split("=");
			if (keyValue.length == 2 && !keyValue[1].trim().equalsIgnoreCase("null")) {
				exportOption.put(keyValue[0].trim(), keyValue[1].trim());
			}
		}
		return exportOption;
	}


	@RenderMapping(params = "action=setCatalogDetail")
	public String setCatalogDetails(Model model, RenderRequest request, RenderResponse response)
			throws SystemException {
		Map<String, String> catalogCriteria = commonUtil.getCriteriaMapForRenderRequest(request);
		model.addAttribute(SearchConstant.RESPONSE_DATA, catalogUtil.getProduct(catalogCriteria));
		model.addAllAttributes(orderUtil.getPreRequisiteModel());
		model.addAllAttributes(catalogUtil.getPrerequisitModel());
		model.addAttribute(SearchConstant.PARAM_PORTAL_MAPPING_UTIL, portalMappingsUtil);
		model.addAttribute(SearchConstant.ORDERS_UTIL, orderUtil);
		model.addAttribute(SearchConstant.NRP_VIEW_AND_SEARCH_CATALOG_DATA,
				checkPermission(SearchConstant.NRP_VIEW_AND_SEARCH_CATALOG_DATA, request));
		if (checkPermission(SearchConstant.NRP_VIEW_AND_SEARCH_CATALOG_DATA, request)) {
			model.addAttribute(SearchConstant.PARAM_FORMPARAM, catalogCriteria);
			model.addAttribute(SearchConstant.PARAM_RENDER_JSP, SearchConstant.CATALOG_RESULTS_JSP);
		} else {
			model.addAttribute(SearchConstant.PARAM_RENDER_JSP, SearchConstant.SEARCH_ERROR_JSP);
		}
		return SearchConstant.WIDTH_8020_JSP;
	}

	@RenderMapping(params = "action=setShipmentDetail")
	public String setShipmentDetail(Model model, RenderRequest request, RenderResponse response)
			throws SystemException {
		long extRepId = (long) request.getPortletSession().getAttribute(PARAM_LIFERAY_SHARED_EXT_REP_ID, PortletSession.APPLICATION_SCOPE);
		boolean hasAllTerritories = (boolean) request.getPortletSession().getAttribute(LIFERAY_SHARED_REP_USER_ALL_TERRITORIES, PortletSession.APPLICATION_SCOPE);
		@SuppressWarnings(SearchConstant.UNCHECKED)
		Map<String, String> shipmentCriteria = commonUtil.getCriteriaMapForRenderRequest(request);
		model.addAllAttributes(orderUtil.getPreRequisiteModel());
		model.addAllAttributes(invoiceUtil.getPreRequisiteModel());
		model.addAllAttributes(catalogUtil.getPrerequisitModel());
		model.addAttribute(SearchConstant.NRP_VIEW_AND_SEARCH_CATALOG_DATA,
				checkPermission(SearchConstant.NRP_VIEW_AND_SEARCH_CATALOG_DATA, request));
		model.addAttribute(SearchConstant.PARAM_RENDER_JSP, SearchConstant.SHIPMENT_RESULTS_JSP);
		Map<String, Object> resultMap = shipmentUtil.getShipments(shipmentCriteria,
				shipmentCriteria.get(SearchConstant.SEARCH_SHIPMENT),extRepId,hasAllTerritories);
		model.addAttribute(SearchConstant.SHIP_DATA, resultMap.get(SearchConstant.SHIPMENT_LIST));
		model.addAttribute(SearchConstant.SHIPMENT_LINES,
				shipmentUtil.getShipmentLines((List<Long>) resultMap.get(SearchConstant.HEADER_IDS),
						(String) resultMap.get(SearchConstant.ITEM_CODE),extRepId,hasAllTerritories));
		model.addAttribute(SearchConstant.SUPPORTED_CARRIER_DATA, shipmentTrackingUtil.getAllCarrierDetails());
		model.addAttribute(ApplicationConstant.PARAM_RESULT_SIZE, resultMap.get(ApplicationConstant.PARAM_RESULT_SIZE));
		shipmentCriteria.put(SearchConstant.SHIPMENT_LOAD_MORE,
				(String) resultMap.get(SearchConstant.SHIPMENT_LOAD_MORE));

		shipmentCriteria.put(SearchConstant.SHIP_ITEM_CODE, (String) resultMap.get(SearchConstant.ITEM_CODE));
		model.addAttribute(SearchConstant.PARAM_FORMPARAM, shipmentCriteria);
		model.addAttribute(SearchConstant.SEARCH_PARAM, resultMap.get(SearchConstant.SEARCH_PARAM));
		setInvoiceHeaders(model, (List<Long>) resultMap.get(SearchConstant.INVOICE_HEADER_IDS));
		return SearchConstant.WIDTH_8020_JSP;
	}

	/**
	 * Get shipment tracking data
	 * @param request
	 * @param response
	 * @throws ShipmentTrackingException
	 * @throws IOException
	 * @throws SystemException
	 */
	@ResourceMapping(value = "getShipmentTrackingData")
	public void getShipmentTrackingData(ResourceRequest request, ResourceResponse response)
			throws ShipmentTrackingException, IOException, SystemException {
		String shipmentCarrier = ParamUtil.getString(request, "carrier");
		String trackingNumber = ParamUtil.getString(request, "trackingNo");
		String orderHeaderId = ParamUtil.getString(request, "orderHeaderId");
		String shipDate = ParamUtil.getString(request, "shipDate");

		Tracking tracking = null;
		JSONObject trackingJSONObj = null;
		try {
			trackingJSONObj = JSONFactoryUtil.createJSONObject();
			tracking = shipmentTrackingUtil.getShipmentTrackingDetails(shipmentCarrier, trackingNumber, orderHeaderId, shipDate);
		} catch (ShipmentTrackingException se) {
			if ("ERROR_IO".equals(se.getMessage())) {
				request.setAttribute("ERROR", "CONNECTION_TIMEOUT");
			} else if ("INVALID_SLUG".equals(se.getMessage())) {
				request.setAttribute("ERROR", "INVALID_CARRIER");
				tracking = new Tracking();
			} else {
				throw se;
			}
		}
		if (tracking != null) {
			trackingJSONObj.put("trackingNumber", tracking.getTrackingNumber());
			trackingJSONObj.put("trackingURL", tracking.getUrl());
		}else {
			trackingJSONObj.put("trackingURL", "null");
		}
		response.getWriter().write(trackingJSONObj.toString());
	}

	@RenderMapping(params = "action=setInvoiceDetail")
	public String setInvoicesDetail(Model model, RenderRequest request, RenderResponse response)
			throws SystemException {
		long extRepId = (long) request.getPortletSession().getAttribute(PARAM_LIFERAY_SHARED_EXT_REP_ID, PortletSession.APPLICATION_SCOPE);
		boolean hasAllTerritories = (boolean) request.getPortletSession().getAttribute(LIFERAY_SHARED_REP_USER_ALL_TERRITORIES, PortletSession.APPLICATION_SCOPE);
		@SuppressWarnings(SearchConstant.UNCHECKED)
		Map<String, String> invoiceCriteria = commonUtil.getCriteriaMapForRenderRequest(request);
		model.addAttribute(SearchConstant.PARAM_RENDER_JSP, SearchConstant.INVOICE_RESULTS_JSP);
		model.addAttribute(SearchConstant.RESPONSE_DATA, invoiceUtil.getInvoices(invoiceCriteria,extRepId,hasAllTerritories));
		model.addAttribute(SearchConstant.PARAM_FORMPARAM, invoiceCriteria);
		model.addAllAttributes(orderUtil.getPreRequisiteModel());
		model.addAllAttributes(invoiceUtil.getPreRequisiteModel());
		model.addAllAttributes(catalogUtil.getPrerequisitModel());
		model.addAttribute(SearchConstant.NRP_VIEW_AND_SEARCH_CATALOG_DATA,
				checkPermission(SearchConstant.NRP_VIEW_AND_SEARCH_CATALOG_DATA, request));
		model.addAttribute(SearchConstant.PARAM_PORTAL_MAPPING_UTIL, portalMappingsUtil);
		model.addAttribute(SearchConstant.ORDERS_UTIL, orderUtil);
		return SearchConstant.WIDTH_8020_JSP;
	}

	/**
	 * Get invoice details.
	 *
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @throws ParseException
	 */
	@RenderMapping(params = SearchConstant.GET_INVOICE_DETAIL_MAPPING)
	public String getInvoiceDetail(Model model, RenderRequest request, RenderResponse response)
			throws SystemException, PortalException, ParseException {
		long extRepId = (long) request.getPortletSession().getAttribute(PARAM_LIFERAY_SHARED_EXT_REP_ID, PortletSession.APPLICATION_SCOPE);
		boolean hasAllTerritories = (boolean) request.getPortletSession().getAttribute(LIFERAY_SHARED_REP_USER_ALL_TERRITORIES, PortletSession.APPLICATION_SCOPE);
		String invoiceNo = ParamUtil.getString(request, ApplicationConstant.INV_INVOICE_NBR);
		String orderCallingPage = ParamUtil.getString(request, ApplicationConstant.ORDER_CALLING_PAGE);
		String claimCallingPage = ParamUtil.getString(request, "claimCallingPage");
		InvoiceDTO invoiceDto = invoiceUtil.getInvoiceDetailData(invoiceNo, request, extRepId,hasAllTerritories);
		String codeValue = claimUtil.getCodeValueForClaimRule(ClaimConstants.CLAIM_RULE_NO_OF_DAYS_LIMIT);
		boolean isValidInvoiceForClaim = claimUtil.isValidInvoiceForClaim(Long.valueOf(invoiceNo));
		HttpSession session = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request))
				.getSession();
		session.setAttribute(SearchConstant.INVOICE_DETAILS_EXPORT + invoiceNo,
				invoiceUtil.getInvoiceExportData(invoiceDto, portalMappingsUtil.getHBGAddressMap(), extRepId,hasAllTerritories));
		if (checkPermission(ApplicationConstant.NRP_TRACK_CLAIMS, request) && invoiceDto.getInvoiceHeader() != null ) {
			List<ClaimHeaderDTO> claimHeaderList = claimUtil.getClaimHeaderDTOforInvoice(invoiceNo, invoiceDto.getInvoiceHeader().getInvoiceHeaderId());
			model.addAttribute(ClaimConstants.CLAIM_HEADER_LIST, claimHeaderList);
		}
		model.addAttribute(ClaimConstants.INVOICE_GENERATED_DAYS, codeValue);
		model.addAttribute(SearchConstant.RESPONSE_DATA, invoiceDto);
		model.addAttribute(SearchConstant.PARAM_PORTAL_MAPPING_UTIL, portalMappingsUtil);
		model.addAttribute(SearchConstant.ORDERS_UTIL, orderUtil);
		model.addAttribute(SearchConstant.ORDER_CALLING_PAGE, orderCallingPage);
		model.addAttribute("claimCallingPage", claimCallingPage);
		model.addAttribute(ClaimConstants.IS_VALID_INVOICE_FOR_CLAIM, isValidInvoiceForClaim);
		model.addAllAttributes(orderUtil.getPreRequisiteModel());
		model.addAllAttributes(invoiceUtil.getPreRequisiteModel());
		model.addAllAttributes(catalogUtil.getPrerequisitModel());
		model.addAttribute(SearchConstant.NRP_VIEW_AND_SEARCH_CATALOG_DATA,
				checkPermission(SearchConstant.NRP_VIEW_AND_SEARCH_CATALOG_DATA, request));

		if(invoiceDto.isHasShipments()) {
			Map<String, String> criteriaMap = new HashMap<>();
			criteriaMap.put(ApplicationConstant.PARAM_REFNO, String.valueOf(invoiceDto.getInvoiceHeader().getReferenceNumber()));
			criteriaMap.put(ApplicationConstant.PARAM_SEARCH_SHIP_INVOICE_NBR, invoiceNo);
			request.setAttribute(SearchConstant.CRITERIA_MAP, criteriaMap);
			// Call
			model.addAttribute(RESPONSE_ORDER_DATA, invoiceDto.getOrderDTO());
			setOrderStatusData(model, request, response);
		}

		if (checkPermission(NRP_VIEW_AND_SEARCH_INVOICE_CREDIT_DEBIT_DATA, request)) {
			model.addAttribute(SearchConstant.PARAM_RENDER_JSP, SearchConstant.INVOICE_DETAIL_JSP);
		} else {
			model.addAttribute(SearchConstant.PARAM_RENDER_JSP, SearchConstant.SEARCH_ERROR_JSP);
		}

		return SearchConstant.WIDTH_100_JSP;
	}

	@RenderMapping(params = SearchConstant.GET_CATALOG_DETAIL_PAGE_MAPPING)
	public String getCatalogDetailPage(Model model, RenderRequest request, RenderResponse response) {
		Layout layout = (Layout) request.getAttribute(WebKeys.LAYOUT);
		PortletURLFactoryUtil.create(request, SearchConstant.CATALOG_DETAILS_PORTLET, layout.getPlid(),
				PortletRequest.RENDER_PHASE);
		return null;
	}

	@ActionMapping(value = "getSearchOrderDetail")
	public void getSearchOrderDetail(ActionRequest actionRequest, ActionResponse actionResponse)  {
		getSearchList(actionRequest, actionResponse);
	}

	@ActionMapping(value = "getSearchByStore")
	public void getSearchByStore(ActionRequest actionRequest, ActionResponse actionResponse) {
		getSearchList(actionRequest, actionResponse);
	}

	@ActionMapping(value = "getSearchByZip")
	public void getSearchByZip(ActionRequest actionRequest, ActionResponse actionResponse)  {
		getSearchList(actionRequest, actionResponse);
	}

	@ActionMapping(value = "getSearchByAccount")
	public void getSearchByAccount(ActionRequest actionRequest, ActionResponse actionResponse)  {
		getSearchList(actionRequest, actionResponse);
	}

	@ActionMapping(value = "getSearchByShipGroup")
	public void getSearchByShipGroup(ActionRequest actionRequest, ActionResponse actionResponse)  {
		getSearchList(actionRequest, actionResponse);
	}

	@ActionMapping(value = "getSearchCatalogByAuthor")
	public void getSearchCatalogByAuthor(ActionRequest actionRequest, ActionResponse actionResponse) {
		getSearchList(actionRequest, actionResponse);
	}

	@ActionMapping(value = "getSearchByShortAuthor")
	public void getSearchByShortAuthor(ActionRequest actionRequest, ActionResponse actionResponse) {
		getSearchList(actionRequest, actionResponse);
	}

	@ActionMapping(value = "getViewAllOrders")
	public void getViewAllOrders(ActionRequest actionRequest, ActionResponse actionResponse) {
		getSearchList(actionRequest, actionResponse);
	}

	@ActionMapping(value = "getViewAllShipments")
	public void getViewAllShipments(ActionRequest actionRequest, ActionResponse actionResponse)  {
		getSearchList(actionRequest, actionResponse);
	}

	@SuppressWarnings("rawtypes")
	private void getSearchList(ActionRequest actionRequest, ActionResponse actionResponse) {
		Map mapObj=actionRequest.getParameterMap();
		SearchOrderDTO searchOrderDTO = new SearchOrderDTO();
		searchOrderDTO.setMapObj(mapObj);
		actionRequest.setAttribute("searchOrderDTO", searchOrderDTO);
		actionRequest.setAttribute("request",actionRequest);
		QName qName = new QName("http://liferay.com/events", "searchDetailBean2", "x");
		actionResponse.setEvent(qName,searchOrderDTO);
	}

	public Map getMapBySearch(Set<Entry<String, String[]>> entrySet) {
		Map mapObj = new HashMap<>();
		if(entrySet == null) {
			return mapObj;
		}
		for (Entry<String, String[]> entry : entrySet) {
			if(entry == null) {
			   continue;
			}
			String key = entry.getKey();
			String value = entry.getValue()[0];
			value = value != null ? value.trim() : value;
			mapObj.put(key, value);
		}
		return mapObj;
	}

	@ResourceMapping(value="shipmentSearchExport")
	public void shipmentSearchExport(ResourceRequest request, ResourceResponse response) throws IOException, SystemException, PortalException, ParseException {
		long extRepId = (long) request.getPortletSession().getAttribute(PARAM_LIFERAY_SHARED_EXT_REP_ID, PortletSession.APPLICATION_SCOPE);
		boolean hasAllTerritories = (boolean) request.getPortletSession().getAttribute(LIFERAY_SHARED_REP_USER_ALL_TERRITORIES, PortletSession.APPLICATION_SCOPE);
		String exportMapString = ParamUtil.getString(request, SearchConstant.EXPORT_OPTION);
		Map<String, String> shipmentSearchOption  = convertMapToString(exportMapString);
		List<Object []> shipmentSearchResult = ShipmentLocalServiceUtil.searchAndExportShipmentData(shipmentSearchOption, ApplicationConstant.SEARCH_SIZE, extRepId,hasAllTerritories);
		String permittedGroups = commonUtil.getPermittedReportingGroups(request);
		exportUtil.writeSearchResultExcel(response, shipmentSearchResult, permittedGroups,"Shipment",extRepId,hasAllTerritories);
	}

	@ResourceMapping(value="invoiceSearchExport")
	public void invoiceSearchExport(ResourceRequest request, ResourceResponse response) throws IOException, SystemException, PortalException, ParseException {
		long extRepId = (long) request.getPortletSession().getAttribute(PARAM_LIFERAY_SHARED_EXT_REP_ID, PortletSession.APPLICATION_SCOPE);
		boolean hasAllTerritories = (boolean) request.getPortletSession().getAttribute(LIFERAY_SHARED_REP_USER_ALL_TERRITORIES, PortletSession.APPLICATION_SCOPE);
		String exportMapString = ParamUtil.getString(request, SearchConstant.EXPORT_OPTION);
		Map<String, String> invoiceSearchOption  = convertMapToString(exportMapString);
		List<Object []> invoiceSearchResult = InvoiceHeaderLocalServiceUtil.searchAndExportInvoiceData(invoiceSearchOption, ApplicationConstant.SEARCH_SIZE, extRepId,hasAllTerritories);
		String permittedGroups = commonUtil.getPermittedReportingGroups(request);
		exportUtil.writeSearchResultExcel(response,invoiceSearchResult, permittedGroups,invoiceSearchOption.get("invoice-search-param"),extRepId,hasAllTerritories);
	}

	@ResourceMapping(value = SearchConstant.EXPORT_TO_EXCEL_MAPPING)
	public void exportToExcel(ResourceRequest request, ResourceResponse response,
			@RequestParam(SearchConstant.EXPORT_OPTION) final String exportOption)
			throws IOException, SystemException, PortalException, ParseException {
		long extRepId = (long) request.getPortletSession().getAttribute(PARAM_LIFERAY_SHARED_EXT_REP_ID, PortletSession.APPLICATION_SCOPE);
		boolean hasAllTerritories = (boolean) request.getPortletSession().getAttribute(LIFERAY_SHARED_REP_USER_ALL_TERRITORIES, PortletSession.APPLICATION_SCOPE);
		HttpSession session = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request)).getSession();
		Object exportData = session.getAttribute(exportOption);
		String permittedGroups = commonUtil.getPermittedReportingGroups(request);
		exportUtil.writeExcel(response, exportData, permittedGroups,extRepId,hasAllTerritories);
	}

	@RenderMapping(params = ClaimConstants.GET_CLAIM_DETAIL)
	public String getClaimDetail(Map<String, Object> map, Model model, RenderRequest request, RenderResponse response)
			throws SystemException {
		model.addAllAttributes(orderUtil.getPreRequisiteModel());
		model.addAllAttributes(invoiceUtil.getPreRequisiteModel());
		model.addAllAttributes(catalogUtil.getPrerequisitModel());
		model.addAttribute(SearchConstant.NRP_VIEW_AND_SEARCH_CATALOG_DATA,
		checkPermission(SearchConstant.NRP_VIEW_AND_SEARCH_CATALOG_DATA, request));
		if (checkPermission(ApplicationConstant.NRP_SUBMIT_CLAIMS, request)) {
			String invoiceNo = ParamUtil.getString(request, SearchConstant.INVOICE_NO);
			ClaimDTO claimDto = claimUtil.getInvoiceDetailForClaim(invoiceNo);
			JSONArray claimTypeJsonArray = claimUtil.getClaimTypes();
			logger.info("claimDto Size:: " + claimDto.getInvoiceDetail().size());

			SubmitClaimDTO submitClaimDTO = new SubmitClaimDTO();
			map.put("submitClaimDTO", submitClaimDTO);
			map.put(SearchConstant.RESPONSE_DATA, claimDto);
			map.put(ClaimConstants.INVOICE_DATE, ParamUtil.getString(request, ClaimConstants.INVOICE_DATE));
			map.put(ClaimConstants.CLAIM_FORM_PAGE, ParamUtil.getString(request, ClaimConstants.CLAIM_FORM_PAGE));
			map.put(ClaimConstants.CLAIM_ACCOUNT_NUMBER, ParamUtil.getString(request, ClaimConstants.CLAIM_ACCOUNT_NUMBER));
			map.put(ClaimConstants.CLAIM_TYPE_ARRAY, claimTypeJsonArray);
			map.put(ClaimConstants.INVOICE_GENERATED_DAYS, ParamUtil.getString(request, ClaimConstants.INVOICE_GENERATED_DAYS));
			Gson gson = new Gson();
			JsonElement element = gson.toJsonTree(claimDto.getInvoiceDetail(), new TypeToken<List<InvoiceDetail>>() {}.getType());

			JsonArray jsonArray = element.getAsJsonArray();
			map.put(ClaimConstants.JSON_ARRAY, jsonArray);
			model.addAttribute(SearchConstant.PARAM_RENDER_JSP, ClaimConstants.CLAIM_DATA_JSP);
		} else {
			model.addAttribute(SearchConstant.PARAM_RENDER_JSP, SearchConstant.SEARCH_ERROR_JSP);
		}

		return SearchConstant.WIDTH_100_JSP;
	}

	@ResourceMapping(ClaimConstants.CLAIM_FORM_MAPPING)
	public void submitClaimForm(@ModelAttribute(ClaimConstants.SUBMIT_CLAIM_DTO) SubmitClaimDTO submitClaimDTO,
			ResourceRequest request, ResourceResponse response) throws SystemException, IOException, SQLException {
		Ticket ticket ;
		String responseMsg;
		Long defaultClaimHeaderKey = 0L;
		String userId = StringUtils.EMPTY;
		String userClient;
		String requestDataJson = StringUtils.EMPTY;
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			String requesterEmail = themeDisplay.getUser().getEmailAddress();
			userId = String.valueOf(themeDisplay.getUserId());
			String requestorName = themeDisplay.getUser().getFullName();

			submitClaimDTO.getSubmitClaimHeader().setRequestedBy(userId);
			submitClaimDTO.getSubmitClaimHeader().setRequestedByEmail(requesterEmail);
			submitClaimDTO.getSubmitClaimHeader().setClaimIntegrationStatus(ClaimConstants.CLAIM_INTEGRATION_STATUS);
			submitClaimDTO.getSubmitClaimHeader().setClaimStatus(ClaimConstants.CLAIM_STATUS);
			requestDataJson = claimUtil.convertClaimObjToJsonObj(submitClaimDTO);
			responseMsg = claimUtil.submitClaimForm(submitClaimDTO);
			logger.info("responseMsg: " + responseMsg);

			// Zendesk create ticket call
			if (responseMsg.contains(ClaimConstants.SUCCESS_STR)) {
				String[] responseSplit = responseMsg.split(ClaimConstants.DELIMITER);
				 String[] claimResponses = responseSplit[1].split(",");
				 for(String claimResponse : claimResponses) {

			    	String[] claimResponseValues = claimResponse.split("_");
				    Long claimHeaderKey = Long.valueOf(claimResponseValues[0]);
				    Long claimTypeKey = Long.valueOf(claimResponseValues[1]);
				    String publisherTags = claimUtil.getPublisherTags(claimHeaderKey);
					ZendeskClaimDTO zendeskClaimDTO = zendeskService.createZendeskClaimDTO(submitClaimDTO, requestorName,claimTypeKey);
					zendeskClaimDTO.setPublisherTags(publisherTags);
					Response responseFromZen = zendeskService.createClaimTicket(zendeskClaimDTO);
					if (responseFromZen.getStatus() == ClaimConstants.SUCCESS_CODE) {

						ticket = responseFromZen.readEntity(Ticket.class);
						if (ticket != null && ticket.getId() != null && ticket.getId() > SearchConstant.ZERO) {
							responseMsg = claimUtil.updateZendeskData(claimHeaderKey, ticket.getId(),themeDisplay.getUserId(),publisherTags);
						}
					} else {
						//Entry in CLAIM_JOB_AUDIT_ERROR for Zendesk Api Error
						ClaimLineLocalServiceUtil.updateZendeskFailureStatus(claimHeaderKey);
						userClient = getUserClient(request);
						responseMsg = logClaimErrorData(claimHeaderKey, submitClaimDTO.getSubmitClaimHeader().getInvoiceNumber(),
								Long.valueOf(responseFromZen.getStatusInfo().getStatusCode()), ClaimConstants.ZENDESK_API_ERROR_STR+responseFromZen.getStatusInfo().getReasonPhrase(),
								ClaimConstants.ERROR_STR.toUpperCase(),StringUtils.EMPTY,Long.valueOf(userId),userClient,requestDataJson,ClaimConstants.ERROR_STR);
						logger.error(ClaimConstants.ZENDESK_API_ERROR_STR + responseFromZen.getStatus());
					}
				}
			}
			else {
				//Entry in CLAIM_JOB_AUDIT_ERROR for create claim procedure  Error
				userClient = getUserClient(request);
				String errorMsg ;
				boolean claimErrorLogged = false;
				if(responseMsg.contains(ClaimConstants.DELIMITER)) {
					String[] responseSplit = responseMsg.split(ClaimConstants.DELIMITER);
					errorMsg = responseSplit[1];
					if(responseSplit.length > 2 && null != responseSplit[2]) {
						String[] claimResponses = responseSplit[2].split(",");
					    for(String claimResponse : claimResponses) {
					    	String[] claimResponseValues = claimResponse.split("_");
						    Long claimHeaderKey = Long.valueOf(claimResponseValues[0]);

						    responseMsg = logClaimErrorData(claimHeaderKey, submitClaimDTO.getSubmitClaimHeader().getInvoiceNumber(),
									1L, ClaimConstants.CREATE_CLAIM_PROC_ERROR+errorMsg,ClaimConstants.ERROR_STR.toUpperCase(),StringUtils.EMPTY,Long.valueOf(userId)
									,userClient,requestDataJson,responseMsg);
						    claimErrorLogged = true;
					    }
					}
				}
				else {
					errorMsg = responseMsg;
				}

				if(!claimErrorLogged) {
					responseMsg = logClaimErrorData(defaultClaimHeaderKey, submitClaimDTO.getSubmitClaimHeader().getInvoiceNumber(),
							1L, ClaimConstants.CREATE_CLAIM_PROC_ERROR+errorMsg,ClaimConstants.ERROR_STR.toUpperCase(),StringUtils.EMPTY,Long.valueOf(userId)
							,userClient,requestDataJson,responseMsg);
				}
				logger.error(ClaimConstants.CREATE_CLAIM_PROC_ERROR + errorMsg);
			}

		}
		catch (Exception e) {

			//Entry in CLAIM_JOB_AUDIT_ERROR for general Error
			userClient = getUserClient(request);
			responseMsg = logClaimErrorData(defaultClaimHeaderKey, submitClaimDTO.getSubmitClaimHeader().getInvoiceNumber(),
					Long.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), e.getMessage(),ClaimConstants.ERROR_STR.toUpperCase(),StringUtils.EMPTY,
					Long.valueOf(userId),userClient,requestDataJson,ClaimConstants.ERROR_STR);
			logger.error(e.getMessage(), e);
		}
		response.getWriter().write(responseMsg);
	}
	/**
	 * @param request
	 * @return get userClient
	 */
	private String getUserClient(ResourceRequest request) {
		HttpServletRequest hsr = PortalUtil.getHttpServletRequest(request);
		String userAgent = hsr.getHeader(ClaimConstants.USER_AGENT);
		String userClient = StringUtils.EMPTY;
		try {
		  if(userAgent.contains(ClaimConstants.CHROME_BROWSER)){ //checking if Chrome
			  userClient =userAgent.substring(userAgent.indexOf(ClaimConstants.CHROME_BROWSER)).split(" ")[0];
		    }
		    else if(userAgent.contains(ClaimConstants.FIREFOX_BROWSER)){  //Checking if Firefox
		    	userClient = userAgent.substring(userAgent.indexOf(ClaimConstants.FIREFOX_BROWSER)).split(" ")[0];
		    }
		    else if(userAgent.contains(ClaimConstants.IE_BELOW_11)) {
		    	userClient = userAgent.substring(userAgent.indexOf(ClaimConstants.IE_BELOW_11)).split(" ")[0];
		    	userClient=userClient.split("/")[0];
		    	String[] parts = userAgent.split(ClaimConstants.IE_BELOW_11);
	            int version = NumberFormat.getInstance().parse(parts[1]).intValue();
	            userClient = userClient+"/"+version;

		    }
		    else if (userAgent.contains(ClaimConstants.IE_BROWSER)) {
		    	userClient = userAgent.substring(userAgent.indexOf(ClaimConstants.IE_BROWSER)).split("; ")[0];
		    	userClient=userClient.split("/")[0];
		    	String[] parts = userAgent.split("rv:");
	            int version = NumberFormat.getInstance().parse(parts[1]).intValue();
	            userClient = userClient+"/"+version;
		    }
		    else if (userAgent.contains(ClaimConstants.SAFARI_BROWSER)) {
		    	userClient = userAgent.substring(userAgent.indexOf(ClaimConstants.SAFARI_BROWSER)).split(" ")[0];
		    }
		}
		catch (ParseException ex) {
			logger.error(ex.getMessage());
        }
		  return userClient;
	}

	/**
	 * @param claimHeaderKey
	 * @param invoiceNumber
	 * @param errorCode
	 * @param errorMsg
	 * @param type
	 * @param comments
	 * @param userId
	 * @param userClient
	 * @return response after logging claim error
	 * @throws SQLException
	 */
	private String logClaimErrorData(Long claimHeaderKey,Long invoiceNumber,Long errorCode,String errorMsg, String type, String comments, Long userId,
			String userClient, String requestDataJson, String errorStr) throws SQLException {
		String responseMsg ;
		String logClaimErrorResponseMsg = claimUtil.logClaimError(claimHeaderKey, invoiceNumber,
				errorCode,errorMsg,type,comments,userId,userClient,requestDataJson);
		if(!logClaimErrorResponseMsg.equalsIgnoreCase(ClaimConstants.SUCCESS_STR)) {
			responseMsg = logClaimErrorResponseMsg;
		}
		else {
			responseMsg = errorStr;
		}
		return responseMsg;
	}

	@ResourceMapping(value = ClaimConstants.CANCEL_CLAIM_MAPPING)
	public void cancelClaim(ResourceRequest request, ResourceResponse response) throws SystemException,IOException {
		String claimHeaderKey = ParamUtil.getString(request, ClaimConstants.CLAIM_HEADER_KEY);
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		String userId = String.valueOf(themeDisplay.getUserId());
		String responseMsg = null;
		try {
			responseMsg = claimUtil.cancelClaim(Long.valueOf(claimHeaderKey), ClaimConstants.CANCEL_CLAIM_STATUS, Long.valueOf(userId));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		response.getWriter().write(responseMsg);
	}

	@SuppressWarnings("unchecked")
	@RenderMapping(params = SearchConstant.GET_PDF_CONDUENT_MAPPING)
	public String getPdfConduent(Model model, RenderRequest request, RenderResponse response) throws SystemException {
		request.setAttribute(ApplicationConstant.PARAM, request.getParameterMap());
		Map<String, String> invoiceCriteria = commonUtil.getCriteriaMapForRenderRequest(request);
		try {
			String invoiceNoStr = invoiceCriteria.get(SearchConstant.INVOICE_NO);
			long val = invoiceNoStr != null && !SearchConstant.BLANK.equals(invoiceNoStr) ? Long.parseLong(invoiceNoStr)
					: SearchConstant.ZERO;
			logger.debug("Method GetPdfConduent: Val is " + val);
			long extRepId = (long) request.getPortletSession().getAttribute(PARAM_LIFERAY_SHARED_EXT_REP_ID, PortletSession.APPLICATION_SCOPE);
			boolean hasAllTerritories = (boolean) request.getPortletSession().getAttribute(LIFERAY_SHARED_REP_USER_ALL_TERRITORIES, PortletSession.APPLICATION_SCOPE);
			Map<String, Object> invoicesData = invoiceUtil.getInvoices(invoiceCriteria,extRepId,hasAllTerritories);
			List<InvoiceHeader> invoiceHeaderList = (List<InvoiceHeader>) invoicesData.get(SearchConstant.INVOICE_DATA);
			if (invoiceHeaderList != null && invoiceHeaderList.size() > SearchConstant.ZERO) {
				model.addAttribute(SearchConstant.ROW_INV_TYPE,
						invoiceHeaderList.get(SearchConstant.ZERO).getInvoiceType());
			}
		} catch (NumberFormatException e) {
			logger.info(e.getMessage(), e);
		}
		return SearchConstant.REDIRECT_CONDUENT_PDF_JSP;
	}

	@ResourceMapping(value = SearchConstant.GET_INVOICE_PDF_URL_MAPPING)
	public void getInvoicePdfUrl(ResourceRequest request, ResourceResponse response)
			throws IOException, SystemException {
		String categoryName = ParamUtil.getString(request, ApplicationConstant.INV_CATEGORY_NAME);
		String reportName = ParamUtil.getString(request, ApplicationConstant.INV_REPORT_NAME);
		String invoiceNumber = ParamUtil.getString(request, ApplicationConstant.INV_INVOICE_NBR);
		String responseStr = invoiceUtil.getInvoicePdf(categoryName, reportName, invoiceNumber);
		if (!StringUtils.isEmpty(responseStr)) {
			response.getWriter().write(responseStr);
		}
	}

	/**
	 * <p>
	 * Action Mapping
	 * </p>
	 * Get Claimline for claims.
	 * @param request
	 * @param response
	 * @throws SystemException
	 * @throws NumberFormatException

	 */
	@ActionMapping(value = SearchConstant.GET_CLAIM_LINE_FOR_CLAIM)
	public void getClaimLineForClaim(ActionRequest request, ActionResponse response)
			throws SystemException, NumberFormatException {
		String claimHeaderId = ParamUtil.getString(request, ClaimConstants.HEADER_KEY);
		String nonRpClaimFlag = ParamUtil.getString(request, ClaimConstants.NON_RP_CLAIM);
		String createdDate = ParamUtil.getString(request, ClaimConstants.CREATED_DATE);
		String claimTypeDesc = ParamUtil.getString(request, ClaimConstants.CLAIM_TYPE_DESC);
		String retailerClaimNumber = ParamUtil.getString(request, ClaimConstants.RETAILER_CLAIM_NUMBER);
		String claimComment = ParamUtil.getString(request, ClaimConstants.CLAIM_COMMENT);
		String claimTypekey = ParamUtil.getString(request, ClaimConstants.CLAIM_TYPE_KEY);
		request.setAttribute(SearchConstant.CLAIM_HEADER_KEY, claimHeaderId);
		request.setAttribute(ClaimConstants.RETAILER_CLAIM_NUMBER, retailerClaimNumber);
		request.setAttribute(ClaimConstants.CLAIM_COMMENT, claimComment);
		request.setAttribute(ClaimConstants.CLAIM_TYPE, claimTypekey);
		request.setAttribute(ClaimConstants.NON_RP_CLAIM_FLAG, nonRpClaimFlag);
		request.setAttribute(ClaimConstants.DATE_CREATED, createdDate);
		request.setAttribute(ClaimConstants.CLAIM_TYPE_DESC, claimTypeDesc);
		response.setRenderParameter(SearchConstant.PARAM_ACTION, SearchConstant.SET_CLAIM_LINE_FOR_CLAIM);
		}
	/**
	 * Render mapping for line items for claim. Called from
	 * @throws NumberFormatException
	 * @throws SystemException
	 * @throws ParseException
	 */
	@RenderMapping(params = SearchConstant.SET_CLAIM_LINE_FOR_CLAIM_MAPPING)
	public String setClaimLineForClaim(Model model, RenderRequest request, RenderResponse response)
			throws SystemException, NumberFormatException, ParseException {
		SimpleDateFormat dateFormatter=new SimpleDateFormat(ClaimConstants.EEE_MMM_DD_KK_MM_SS_YYYY, Locale.ENGLISH);
		String keyValue = (String) request.getAttribute(ClaimConstants.CLAIM_HEADER_KEY);
		String createdDate = (String) request.getAttribute(ClaimConstants.DATE_CREATED);
		boolean nonRpClaim = request.getAttribute(ClaimConstants.NON_RP_CLAIM_FLAG).equals(ClaimConstants.TRUE);
		long headerId = (keyValue == null || keyValue.equals(ClaimConstants.BLANK)) ? 0 : Long.parseLong(keyValue);
		long extRepId = (long) request.getPortletSession().getAttribute(PARAM_LIFERAY_SHARED_EXT_REP_ID, PortletSession.APPLICATION_SCOPE);
		boolean hasAllTerritories = (boolean) request.getPortletSession().getAttribute(LIFERAY_SHARED_REP_USER_ALL_TERRITORIES, PortletSession.APPLICATION_SCOPE);
		if(nonRpClaim){
			List<InvoiceLine> claimLineList = claimUtil.getInvoiceLineForClaimByInvoiceHeaderId(headerId,extRepId,hasAllTerritories);
			model.addAttribute(ClaimConstants.CLAIM_LINE_ITEMS_DATA  , claimLineList);
			model.addAttribute(ClaimConstants.NON_RP_CLAIM  , ClaimConstants.TRUE);
			model.addAttribute(ClaimConstants.CLAIM_TYPE_DESC  , request.getAttribute(ClaimConstants.CLAIM_TYPE_DESC));
			model.addAttribute(ClaimConstants.CREATED_DATE ,dateFormatter.parse(createdDate));
			model.addAttribute(ClaimConstants.RETAILER_CLAIM_NUMBER  , request.getAttribute(ClaimConstants.RETAILER_CLAIM_NUMBER));
		}else{
			String claimTypeKey = (String) request.getAttribute(ClaimConstants.CLAIM_TYPE);
			List<ClaimLine> claimLineList = claimUtil.getClaimLineForClaimHeaderId(headerId);
			long claimTypeId = (claimTypeKey == null || claimTypeKey.equals(ClaimConstants.BLANK)) ? 0 : Long.parseLong(claimTypeKey);
			ClaimTypeDetail claimTypeDetail =  claimUtil.getClaimTypedetailForKey(claimTypeId);
			model.addAttribute(ClaimConstants.CLAIM_LINE_ITEMS_DATA  , claimLineList);
			model.addAttribute(ClaimConstants.RETAILER_CLAIM_NUMBER  , request.getAttribute(ClaimConstants.RETAILER_CLAIM_NUMBER));
			model.addAttribute(ClaimConstants.CLAIM_COMMENT  , request.getAttribute(ClaimConstants.CLAIM_COMMENT));
			model.addAttribute(ClaimConstants.CLAIM_TYPE_DESC, claimTypeDetail.getClaimTypeDesc());
		}
		return SearchConstant.CLAIM_LINE_ITEM_JSP;
	}

	@ResourceMapping(value = "getInvoiceHierarchyData")
	public void getInvoiceHierarchyData(ResourceRequest request, ResourceResponse response)
			throws SystemException, IOException {
		long invoiceNbr = ParamUtil.getLong(request, "invoiceNbr");
		List<Object []> list = InvoiceHeaderLocalServiceUtil.getDataByInvoiceNumber(invoiceNbr);
		JSONArray filteredData = JSONFactoryUtil.createJSONArray();
		if (list != null && !list.isEmpty()) {
			Map<String, JSONObject> lastLevelMap = new HashMap<>();
			JSONObject mainO = JSONFactoryUtil.createJSONObject();
			JSONObject jsonO = convertObjectToJsonObject(list.get(0));
			mainO.put("text", jsonO);
			lastLevelMap.put(list.get(0)[0] != null ? list.get(0)[0].toString() : "", mainO);
			filteredData.put(mainO);
			for (int i=1; i<list.size(); i++) {
				Object[] object = list.get(i);
				JSONObject mainOb = lastLevelMap.get(object[1] != null ? object[1].toString() : "");
				JSONArray childrenArray = mainOb.getJSONArray("children");
				if (childrenArray == null || childrenArray.length() == 0) {
					childrenArray = JSONFactoryUtil.createJSONArray();
					mainOb.put("children", childrenArray);
				}
				JSONObject mainChildOb = JSONFactoryUtil.createJSONObject();
				JSONObject jsonOb = convertObjectToJsonObject(object);
				mainChildOb.put("text", jsonOb);
				childrenArray.put(mainChildOb);
				lastLevelMap.put(object[0] != null ? object[0].toString() : "", mainChildOb);
			}
			response.getWriter().write(filteredData.toString());
		}
	}

	private JSONObject convertObjectToJsonObject(Object[] object) {
		JSONObject jsonObj = JSONFactoryUtil.createJSONObject();
		jsonObj.put("INVOICE_NUMBER", object[0] != null ? object[0].toString() : "");
		jsonObj.put("ORIG_INVOICE_NUMBER", object[1] != null ? object[1].toString() : "");
		if(object[2] != null && !StringUtils.isEmpty(object[2].toString())) {
			SimpleDateFormat fromSdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat toSdf = new SimpleDateFormat("M/d/yy");
			Date date;
			try {
				date = fromSdf.parse(object[2].toString());
				if(date != null) {
					object[2] = toSdf.format(date);
				}
			} catch (ParseException e) {
				logger.error(e.getMessage(), e);
			}
		}
		jsonObj.put("INVOICE_DATE", object[2] != null ? object[2].toString() : "");
		jsonObj.put("INVOICE_CATEGORY", object[3] != null ? object[3].toString() : "");
		jsonObj.put("INVOICE_CATEGORY_DESC", object[4] != null ? object[4].toString() : "");
		jsonObj.put("LEVEL", object[5] != null ? object[5].toString() : "");

		String invoiceCategoryMapping = portalMappingsUtil.getFinancialTransactionType(object[3] != null ? object[3].toString() : "");
		jsonObj.put("CATEGORY_MAPPING", invoiceCategoryMapping);

		return jsonObj;
	}

	@ResourceMapping(value = "initiateZendeskTicket")
	public void initiateZendeskTicket(ResourceRequest request, ResourceResponse response) throws IOException {
		logger.info("initiateZendeskTicket >>>>>>>>>>>>>> ");

		Ticket ticket = null;
		boolean ticketRaised = false;
		long ticketId = SearchConstant.ZERO;
		try {
			ZendeskDTO zendeskDTO = zendeskService.createZendeskData(request);
			Response responseFromZen = zendeskService.createTicket(zendeskDTO);
			if (responseFromZen.getStatus() == 200) {
				ticket = responseFromZen.readEntity(Ticket.class);
			} else {
				logger.info("Error in creating Zendesk ticket :" + responseFromZen.getStatus());
			}

			if (ticket != null && ticket.getId() != null && ticket.getId() > SearchConstant.ZERO) {
				ticketRaised = true;
				ticketId = ticket.getId();
				// Save this ticket details in DB with requester details
				ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
				String userId = String.valueOf(themeDisplay.getUserId());
				String shipmentHeaderId = ParamUtil.getString(request, ApplicationConstant.SHIPMENT_HEADER_ID);
				ShipmentLocalServiceUtil.entryForZendesk(userId, shipmentHeaderId);
			}

		} catch (Exception e) {
			logger.info(e.getMessage(), e);
		}

		JSONObject zendeskResponse = JSONFactoryUtil.createJSONObject();
		zendeskResponse.put(SearchConstant.STATUS, ticketRaised);
		zendeskResponse.put(SearchConstant.TICKET_ID, ticketId);
		response.getWriter().write(zendeskResponse.toString());
	}

	private static final Log logger = LogFactoryUtil.getLog(SearchPortletResultsController.class);
}