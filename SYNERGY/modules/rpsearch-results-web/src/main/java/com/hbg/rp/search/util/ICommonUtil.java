package com.hbg.rp.search.util;

import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;
import com.hbg.rp.model.Product;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The com.hbg.rp.util.ICommonUtil is the interface for  
 * 
 * @author ravi.kumar
 */
public interface ICommonUtil {

	/**
	 * Get criteria map for render request.
	 * @param request
	 * @return Map
	 * @throws SystemException 
	 */
	Map getCriteriaMapForRenderRequest(PortletRequest request) throws SystemException;

	/**
	 * Get criteria map for resource request.
	 * @param request
	 * @return Map 
	 * @throws SystemException 
	 */
	Map getCriteriaMapForResourceRequest(PortletRequest request) throws SystemException;
	
	/**
	 * Get logged in user id of user.
	 * @param request
	 * @return user id of logged in user
	 */
	String getLoggedInUserId(PortletRequest request);

	/**
	 * Get permitted reporting groups.
	 * @param request
	 * @return 
	 * @throws PortalException
	 * @throws SystemException
	 */
	String getPermittedReportingGroups(PortletRequest request) throws PortalException, SystemException;

	String getAllReportingGroups() throws PortalException, SystemException;
	
	Map<String, Product> getProductTitleMap(List<String> isbnList) throws SystemException;

}