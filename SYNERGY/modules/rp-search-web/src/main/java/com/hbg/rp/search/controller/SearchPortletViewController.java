package com.hbg.rp.search.controller;

import com.hbg.rp.search.constants.SearchConstant;
import com.hbg.rp.search.dto.SearchOrderDTO;
import com.hbg.rp.search.util.ICatalogUtil;
import com.hbg.rp.search.util.ICommonUtil;
import com.hbg.rp.search.util.IGenericCacheHandler;
import com.hbg.rp.search.util.IInvoiceUtil;
import com.hbg.rp.search.util.IOrderUtil;
import com.hbg.rp.service.ShipToAccountLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portletmvc4spring.bind.annotation.ActionMapping;
import com.liferay.portletmvc4spring.bind.annotation.EventMapping;
import com.liferay.portletmvc4spring.bind.annotation.RenderMapping;
import com.liferay.portletmvc4spring.bind.annotation.ResourceMapping;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.EventPortlet;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import javax.xml.namespace.QName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ravi.kumar
 */
@Controller
@RequestMapping("VIEW")
public class SearchPortletViewController implements EventPortlet{
	
	@Autowired
	private IOrderUtil orderUtil;

	@Autowired
	private IInvoiceUtil invoiceUtil;
	
	@Autowired
	private ICatalogUtil catalogUtil;
	
	@Autowired
	private ICommonUtil commonUtil;
	
	@Autowired
	private IGenericCacheHandler genericCacheHandler;

	private static final String JSP_PAGE = "jspPage";
	private static final String SEARCH_ORDER_DTO = "searchOrderDTO";
	private static final String REQUEST = "request";

	@SuppressWarnings("unchecked")
	@RenderMapping
	public String renderSearchPage(Model model, RenderResponse response, RenderRequest request) throws SystemException {
		model.addAllAttributes(orderUtil.getPreRequisiteModel());
		model.addAllAttributes(invoiceUtil.getPrerequisitModel());
		model.addAllAttributes(catalogUtil.getPrerequisitModel());
		model.addAttribute(SearchConstant.NRP_VIEW_AND_SEARCH_CATALOG_DATA,
				checkPermission(SearchConstant.NRP_VIEW_AND_SEARCH_CATALOG_DATA, request));
		SearchOrderDTO searchOrderDTO;
		String jspPage = (String) request.getAttribute(JSP_PAGE);
		if (jspPage != null) {
			searchOrderDTO = (SearchOrderDTO) request.getAttribute(SEARCH_ORDER_DTO);
			PortletRequest actionRequest = (PortletRequest) request.getAttribute(REQUEST);
			Map<String, String> mapObj = commonUtil.getMapForSearchDTO(searchOrderDTO.getMapObj(), actionRequest);
			model.addAttribute(SEARCH_ORDER_DTO, searchOrderDTO);
			model.addAttribute("isSearchForm", true);
			if (jspPage.equalsIgnoreCase(SearchConstant.SEARCH_SHIPMENT)) {
				mapObj.put(SearchConstant.SEARCH_SHIPMENT, SearchConstant.TRUE);
			} else if (jspPage.equalsIgnoreCase(SearchConstant.SEARCH_INVOICE)) {
				mapObj.put(SearchConstant.SEARCH_INVOICE, SearchConstant.TRUE);
			} else if (jspPage.equalsIgnoreCase(SearchConstant.SEARCH_DEBIT)) {
				mapObj.put(SearchConstant.SEARCH_DEBIT, SearchConstant.TRUE);
			} else if (jspPage.equalsIgnoreCase(SearchConstant.SEARCH_CREDIT)) {
				mapObj.put(SearchConstant.SEARCH_CREDIT, SearchConstant.TRUE);
			} else if (jspPage.equalsIgnoreCase(SearchConstant.SEARCH_TRANSACTION)) {
				mapObj.put(SearchConstant.SEARCH_TRANSACTION, SearchConstant.TRUE);
			} else if (jspPage.equalsIgnoreCase(SearchConstant.SEARCH_CATALOG)) {
				mapObj.put(SearchConstant.SEARCH_CATALOG, SearchConstant.TRUE);
			} else {
				mapObj.put(SearchConstant.SEARCH_ORDER, SearchConstant.TRUE);
			}
			model.addAttribute(SearchConstant.PARAM_FORMPARAM, mapObj);
			request.setAttribute(JSP_PAGE, jspPage);
		} else {
			model.addAttribute(SEARCH_ORDER_DTO, null);
			model.addAttribute("isSearchForm", false);
			request.setAttribute(JSP_PAGE, "null");
		}
		return SearchConstant.VIEW;
	}
	
	/**
	 * Get accounts.
	 * 
	 * @param request
	 * @param response
	 * @throws SystemException
	 * @throws IOException
	 */
	@ResourceMapping(value = SearchConstant.GET_ACCOUNTS)
	public void getAccountData(ResourceRequest request, ResourceResponse response) throws SystemException, IOException {
		String searchString = request.getParameter(SearchConstant.SEARCH_STR);
		String allAccount = request.getParameter(SearchConstant.ALL_ACCOUNT);
		if (null == allAccount) {
			allAccount = SearchConstant.BLANK;
		}
		searchString = null == searchString ? SearchConstant.BLANK : searchString.trim();
		
		JSONArray accountList;
		Boolean hasAllAccounts = (Boolean) request.getPortletSession()
				.getAttribute(SearchConstant.LIFERAY_SHARED_USER_ALL_ACCOUNTS, PortletSession.APPLICATION_SCOPE);
		if (null != hasAllAccounts && hasAllAccounts) {
			accountList = genericCacheHandler.getAccountList();
		} else {
			accountList = (JSONArray) request.getPortletSession()
					.getAttribute(SearchConstant.LIFERAY_SHARED_USER_ACCOUNTS, PortletSession.APPLICATION_SCOPE);
		}
		JSONArray filteredAccountList = JSONFactoryUtil.createJSONArray();
		if (null != accountList) {
			for (int i = SearchConstant.ZERO; i < accountList.length(); i++) {
				JSONObject jsonObject = accountList.getJSONObject(i);
				if (jsonObject.getString(SearchConstant.PARAM_LABEL).toLowerCase()
						.contains(searchString.toLowerCase())) {
					filteredAccountList.put(jsonObject);
					if (filteredAccountList.length() == (null != SearchConstant.MAX_NUMBER_OF_ACCOUNTS
							? Integer.parseInt(SearchConstant.MAX_NUMBER_OF_ACCOUNTS) : SearchConstant.HUNDRED))
						break;
				}
			}
		}
	   if (allAccount.equals(SearchConstant.BLANK)) {
			if (SearchConstant.BLANK.equals(searchString) && filteredAccountList.length() > SearchConstant.TEN) {
				response.getWriter().write(SearchConstant.BLANK);
			} else {
				if (filteredAccountList.length() == SearchConstant.ZERO) {
					JSONObject emptyJsonObject = JSONFactoryUtil.createJSONObject();
					emptyJsonObject.put(SearchConstant.ID, SearchConstant.VAL_999);
					emptyJsonObject.put(SearchConstant.PARAM_LABEL, SearchConstant.NO_MATCH_FOUND);
					filteredAccountList.put(emptyJsonObject);
				}
				response.getWriter().write(filteredAccountList.toString());
			}
		} else {
			response.getWriter().write(genericCacheHandler.getAccountList().toString());
		}
	}
	
	/**
	 * Get store data.
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws SystemException
	 */
	@ResourceMapping(value = SearchConstant.FIND_ALL_STORES)
	public void getStoreData(ResourceRequest request, ResourceResponse response) throws IOException, SystemException {
		JSONArray storeArray = fetchStoreData(request);
		if (storeArray.length() == 1) {
			response.getWriter().write(storeArray.toString());
		} else {
			response.getWriter().write(storeArray.length() > 0 ? SearchConstant.SUCCESS : SearchConstant.FAILURE);
		}
	}
	
	/**
	 * Fetch stores data.
	 * 
	 * @param request
	 * @return
	 * @throws SystemException
	 */
	private JSONArray fetchStoreData(ResourceRequest request) throws SystemException {
		Map<String, Object> storeCriteria = commonUtil.getCriteriaMapForResourceRequest(request);
		return ShipToAccountLocalServiceUtil.getShipToData(storeCriteria);
	}
	
	/**
	 * Get type ahead stores.
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws SystemException
	 */
	@ResourceMapping(value = SearchConstant.FIND_TYPE_AHEAD_STORES)
	public void getTypeAheadStores(ResourceRequest request, ResourceResponse response)
			throws IOException, SystemException {
		HttpServletRequest actualRequest = PortalUtil
				.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
		String searchString = request.getParameter(SearchConstant.SEARCH_STR);
		searchString = null == searchString ? SearchConstant.BLANK : searchString.trim();
		
		JSONArray storeList = (JSONArray) actualRequest.getSession().getAttribute(SearchConstant.STORES);
		JSONArray filteredStoreList = JSONFactoryUtil.createJSONArray();
		if (storeList == null
				&& null != commonUtil.getCriteriaMapForResourceRequest(request).get(SearchConstant.ACCOUNT_NAME_KEY)) {
			storeList = fetchStoreData(request);
		}
		if (storeList != null) {
			for (int i = SearchConstant.ZERO; i < storeList.length(); i++) {
				JSONObject jsonObject = storeList.getJSONObject(i);
				if (jsonObject.getString(SearchConstant.PARAM_LABEL).toLowerCase()
						.contains(searchString.toLowerCase())) {
					filteredStoreList.put(jsonObject);
					if (filteredStoreList.length() == (null != SearchConstant.MAX_NUMBER_OF_ACCOUNTS
							? Integer.parseInt(SearchConstant.MAX_NUMBER_OF_ACCOUNTS) : SearchConstant.HUNDRED))
						break;
				}
			}
		}
		if (filteredStoreList.length() == SearchConstant.ZERO) {
			JSONObject emptyJsonObject = JSONFactoryUtil.createJSONObject();
			emptyJsonObject.put(SearchConstant.ID, SearchConstant.VAL_999);
			emptyJsonObject.put(SearchConstant.PARAM_LABEL, SearchConstant.NO_MATCH_FOUND);
			filteredStoreList.put(emptyJsonObject);
		}
		response.getWriter().write(filteredStoreList.toString());
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

	@ActionMapping(value = SearchConstant.SEARCH_ORDERS)
	public void searchOrders(ActionRequest actionRequest, ActionResponse actionResponse) {
		setSearchDetail(actionRequest, actionResponse);
		actionRequest.setAttribute(JSP_PAGE,SearchConstant.SEARCH_ORDER);
	}

	@ActionMapping(value = SearchConstant.SEARCH_SHIPMENTS)
	public void searchShipments(ActionRequest actionRequest, ActionResponse actionResponse) {
		setSearchDetail(actionRequest, actionResponse);
		actionRequest.setAttribute(JSP_PAGE,SearchConstant.SEARCH_SHIPMENT);
	}

	@ActionMapping(value = SearchConstant.SEARCH_INVOICES)
	public void searchInvoices(ActionRequest actionRequest, ActionResponse actionResponse) {
		setSearchDetail(actionRequest, actionResponse);
		actionRequest.setAttribute(JSP_PAGE,SearchConstant.SEARCH_INVOICE);
	}

	@ActionMapping(value = SearchConstant.SEARCH_DEBITS)
	public void searchDebits(ActionRequest actionRequest, ActionResponse actionResponse) {
		setSearchDetail(actionRequest, actionResponse);
		actionRequest.setAttribute(JSP_PAGE,SearchConstant.SEARCH_DEBIT);
	}

	@ActionMapping(value = SearchConstant.SEARCH_CREDITS)
	public void searchCredits(ActionRequest actionRequest, ActionResponse actionResponse) {
		setSearchDetail(actionRequest, actionResponse);
		actionRequest.setAttribute(JSP_PAGE,SearchConstant.SEARCH_CREDIT);
	}

	@ActionMapping(value = SearchConstant.SEARCH_TRANSACTIONS)
	public void searchTransactions(ActionRequest actionRequest, ActionResponse actionResponse) {
		setSearchDetail(actionRequest, actionResponse);
		actionRequest.setAttribute(JSP_PAGE,SearchConstant.SEARCH_TRANSACTION);
	}

	@ActionMapping(value = SearchConstant.SEARCH_CATALOGS)
	public void searchCatalogs(ActionRequest actionRequest, ActionResponse actionResponse) {
		setSearchDetail(actionRequest, actionResponse);
		actionRequest.setAttribute(JSP_PAGE,SearchConstant.SEARCH_CATALOG);
	}
	
	public void setSearchDetail(ActionRequest actionRequest, ActionResponse actionResponse) {
		Map<String, String[]> mapObj=actionRequest.getParameterMap();
		SearchOrderDTO searchOrderDTO = new SearchOrderDTO();
		searchOrderDTO.setMapObj(mapObj);
		actionRequest.setAttribute(SEARCH_ORDER_DTO, searchOrderDTO);
		actionRequest.setAttribute(REQUEST,actionRequest);
		QName qName = new QName("http://liferay.com/events", "searchDetailBean1", "x");
		actionResponse.setEvent(qName,searchOrderDTO);
	}

	@SuppressWarnings("unchecked")
	@EventMapping
	public void processEvent(EventRequest request, EventResponse response) {
		javax.portlet.Event event = request.getEvent();
		SearchOrderDTO searchOrderDTO = (SearchOrderDTO) event.getValue();
		request.setAttribute(SEARCH_ORDER_DTO,searchOrderDTO);
		request.setAttribute(REQUEST,request);
		Map searchMapObj = getMapBySearch(searchOrderDTO.getMapObj().entrySet());
		if (searchMapObj.containsKey(SearchConstant.SEARCH_SHIPMENT)) {
			String value = (String) searchMapObj.get(SearchConstant.SEARCH_SHIPMENT);
			if (SearchConstant.TRUE.equalsIgnoreCase(value)) {
				request.setAttribute(JSP_PAGE,SearchConstant.SEARCH_SHIPMENT);
			}
		} else if (searchMapObj.containsKey(SearchConstant.SEARCH_INVOICE)) {
			String value = (String) searchMapObj.get(SearchConstant.SEARCH_INVOICE);
			if (SearchConstant.TRUE.equalsIgnoreCase(value)) {
				request.setAttribute(JSP_PAGE, SearchConstant.SEARCH_INVOICE);
			}
		} else if (searchMapObj.containsKey(SearchConstant.SEARCH_DEBIT)) {
			String value = (String) searchMapObj.get(SearchConstant.SEARCH_DEBIT);
			if (SearchConstant.TRUE.equalsIgnoreCase(value)) {
				request.setAttribute(JSP_PAGE, SearchConstant.SEARCH_DEBIT);
			}
		} else if (searchMapObj.containsKey(SearchConstant.SEARCH_CREDIT)) {
			String value = (String) searchMapObj.get(SearchConstant.SEARCH_CREDIT);
			if (SearchConstant.TRUE.equalsIgnoreCase(value)) {
				request.setAttribute(JSP_PAGE, SearchConstant.SEARCH_CREDIT);
			}
		} else if (searchMapObj.containsKey(SearchConstant.SEARCH_TRANSACTION)) {
			String value = (String) searchMapObj.get(SearchConstant.SEARCH_TRANSACTION);
			if (SearchConstant.TRUE.equalsIgnoreCase(value)) {
				request.setAttribute(JSP_PAGE, SearchConstant.SEARCH_TRANSACTION);
			}
		} else if (searchMapObj.containsKey(SearchConstant.SEARCH_CATALOG)) {
			String value = (String) searchMapObj.get(SearchConstant.SEARCH_CATALOG);
			if (SearchConstant.TRUE.equalsIgnoreCase(value)) {
				request.setAttribute(JSP_PAGE, SearchConstant.SEARCH_CATALOG);
			}
		} else {
			request.setAttribute(JSP_PAGE, SearchConstant.SEARCH_ORDER);
		}
		QName qName = new QName("http://liferay.com/events", "searchDetailBean1", "x");
		response.setEvent(qName,searchOrderDTO);
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

}