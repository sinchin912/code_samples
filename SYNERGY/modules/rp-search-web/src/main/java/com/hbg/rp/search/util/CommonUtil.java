package com.hbg.rp.search.util;

import com.hbg.rp.search.constants.ApplicationConstant;
import com.hbg.rp.search.helper.ICommonUtilHelper;
import com.hbg.rp.service.ProductLocalServiceUtil;
import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoTable;
import com.liferay.expando.kernel.model.ExpandoTableConstants;
import com.liferay.expando.kernel.service.ExpandoColumnLocalServiceUtil;
import com.liferay.expando.kernel.service.ExpandoTableLocalServiceUtil;
import com.liferay.expando.kernel.service.ExpandoValueLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The com.hbg.rp.util.CommonUtil is an interface for Common Utilities.
 * 
 * @author ravi.kumar
 */
@Component
public class CommonUtil implements ICommonUtil {
	
	@Autowired
	private ICommonUtilHelper commonUtilHelper;
	
	/**
	 * Empty Constructor.
	 */
	public CommonUtil() {
		logger.info("CommonUtil() << ");
		logger.info("CommonUtil() >> ");
	}

	/**
	 * Get criteria map for resource request.
	 * @param request
	 * @return Map
	 * @throws SystemException 
	 */
	@Override
	@SuppressWarnings({ "rawtypes"})
	public Map getCriteriaMapForResourceRequest(PortletRequest request) throws SystemException {
		logger.info("getCriteriaMapForResourceRequest() <<");
		
		Map<String, String[]> parametersMap =  request.getParameterMap();
		if(parametersMap == null) {
			return new HashMap<>();
		}
		
		logger.info("getCriteriaMapForResourceRequest() >>");
		return populateCriteriaMapFromParametersMap(parametersMap, request);
	}	
	
	
	@Override
	@SuppressWarnings({ "rawtypes"})
	public Map getMapForSearchDTO(Map<String, String[]> parametersMap,PortletRequest request) throws SystemException {
		logger.info("getMapForSearchDTO() <<");
		
		if(parametersMap == null) {
			return new HashMap<>();
		}
		
		logger.info("getMapForSearchDTO() >>");
		return populateCriteriaMapFromParametersMap(parametersMap, request);
	}
	
	/**
	 * Calls populates the criteriaMap
	 * @param parametersMap
	 * @param request
	 * @return criteriaMap Map
	 * @throws SystemException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map populateCriteriaMapFromParametersMap(Map<String, String[]> parametersMap, PortletRequest request) throws SystemException {
		Map criteriaMap = new HashMap<>();
		if(parametersMap == null) {
			return criteriaMap;
		}
		
		criteriaMap = commonUtilHelper.getCriteriaMapFromEntrySet(parametersMap.entrySet());
		
		JSONArray accountList = (JSONArray) request.getPortletSession().getAttribute("LIFERAY_SHARED_user_accounts",
			    PortletSession.APPLICATION_SCOPE);
		if(null != accountList && accountList.length()==1){
			//Map<String, Object> storeCriteria = new HashMap<>();
			criteriaMap.put("accountKey", String.valueOf(accountList.getJSONObject(0).getLong("id")));
			// NRP-2032 Remove auto selection of store
			/*storeCriteria.put("accountNameKey", accountList.getJSONObject(0).getLong("id"));
			JSONArray storeArray = ShipToAccountLocalServiceUtil.getShipToData(storeCriteria);
			if(null != storeArray && storeArray.length()==1){
				criteriaMap.put("storedataRetain", String.valueOf(storeArray.getJSONObject(0).getLong("id")));
			}*/
		}
		
		criteriaMap.put("loggedUserId", getLoggedInUserId(request));
		criteriaMap.put("hasAllAccounts", String.valueOf((Boolean) request.getPortletSession()
				.getAttribute("LIFERAY_SHARED_user_all_accounts", PortletSession.APPLICATION_SCOPE)));
		
		logger.info("populateCriteriaMapFromParametersMap() >> criteriaMap: " + criteriaMap);
		return criteriaMap;
	}
	
	
	@Override
	public Map getCriteriaMapForRenderRequest(PortletRequest request) throws SystemException {

		logger.info("getCriteriaMapForRenderRequest() <<");
		
		Map<String, String[]> parametersMap = (Map<String, String[]>) request.getAttribute(ApplicationConstant.PARAM);
		if(parametersMap == null) {
			return new HashMap<>();
		}
		
		logger.info("getCriteriaMapForRenderRequest() >>");
		return  populateCriteriaMapFromParametersMap(parametersMap, request);
	
	}
	
	/**
	 * Get all reporting groups 
	 */
	@Override
	public String getAllReportingGroups() throws PortalException, SystemException {
		List<String> allReportingGroups = ProductLocalServiceUtil.getAllReportingGroups();
		return null != allReportingGroups ? allReportingGroups.toString().substring(1, allReportingGroups.toString().length() - 1) : "";
	}
	
	/**
	 * Get permitted reporting groups.
	 */
	@SuppressWarnings("deprecation")
	@Override
	public String getPermittedReportingGroups(PortletRequest request) throws PortalException, SystemException {
		String finalReportingGroup = "";
		try {
			User user = PortalUtil.getUser(request);

			String[] reportingGroups = {};
			ExpandoTable userCustomFields = ExpandoTableLocalServiceUtil.getTable(user.getCompanyId(),
					User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME);
			// find reporting groups
			ExpandoColumn reportingGroupColumn = ExpandoColumnLocalServiceUtil.getColumn(user.getCompanyId(),
					User.class.getName(), userCustomFields.getName(), ApplicationConstant.PARAM_REPORTING_GROUP);
			reportingGroups = ExpandoValueLocalServiceUtil.getData(user.getCompanyId(),User.class.getName(), userCustomFields.getName(),
					reportingGroupColumn.getName(), user.getUserId(), reportingGroups);
			if (null != reportingGroups && reportingGroups.length > 0) {
				if(Arrays.asList(reportingGroups).contains("0"))
					finalReportingGroup = "[0]";
				else
					finalReportingGroup = Arrays.asList(reportingGroups).toString();
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(),exception);
		}
		return finalReportingGroup.length() > 1 ? finalReportingGroup.substring(1, finalReportingGroup.length() - 1)
				: "";
	}
	
	/**
	 * Get user id of logged-In user.
	 */
	@Override
	public String getLoggedInUserId(PortletRequest request) {
		String userId = "";
		try {
			User user = PortalUtil.getUser(request);
			if (null != user)
				userId = String.valueOf(user.getUserId());
		} catch (PortalException | SystemException e) {
			logger.info(e.getMessage(),e);
		}
		return userId;
	}

	private static final Log logger = LogFactoryUtil.getLog(CommonUtil.class);
	
}
