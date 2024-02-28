package com.hbg.rp.search.util;

import com.hbg.rp.model.OrderHeader;
import com.hbg.rp.model.OrderLine;
import com.hbg.rp.search.model.OrderDTO;
import com.hbg.rp.search.model.OrderStatusDTO;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;

/**
 * The com.hbg.rp.util.IOrderUtil is the interface for {@link OrderUtil}.
 * 
 * @author ravi.kumar
 */
public interface IOrderUtil {

	/**
	 * Get prerequisite model.
	 * @return
	 * @throws SystemException
	 */
	Map<String, Object> getPreRequisiteModel() throws SystemException;
	
	/**
	 * Get order headers.
	 * @param orderSearchCriteriaMap
	 * @return
	 * @throws SystemException
	 */
	List<OrderDTO> getOrderHeaders(Map<String, String> orderSearchCriteriaMap, long extRepId, boolean hasAllTerritories) throws SystemException;

	/**
	 * Create order DTO List.
	 * @param headerData
	 * @param itemCode
	 * @return
	 * @throws SystemException
	 */
	List<OrderDTO> createOrderDTOList(List<OrderHeader> headerData, String itemCode, long extRepId, boolean hasAllTerritories)
			throws SystemException;
	
	/**
	 * Calculate order details.
	 * @param orderDTO
	 */
	void calculateOrderDetail(OrderDTO orderDTO);
	
	/**
	 * Calculate order detail list.
	 * @param orderDTOList
	 */
	void calculateOrderDetailList(List<OrderDTO> orderDTOList);
	
	/**
	 * Get order detail data.
	 * @param refNo
	 * @return
	 * @throws SystemException
	 */
	OrderDTO getOrderDetailData(String refNo, boolean linesRequired, PortletRequest request, long extRepId, boolean hasAllTerritories) throws SystemException;


	/**
	 * Get order headers for default landing page.
	 * @param orderSearchCriteriaMap
	 * @param pageName
	 * @param extRepId
	 * @return
	 * @throws SystemException
	 */
	List<OrderDTO> getOrderHeadersForDefaultLanding(Map<String, String> orderSearchCriteriaMap,
			String pageName, long extRepId, boolean hasAllTerritories) throws SystemException;

	/**
	 * Set the order lines grouped by statuses. 
	 * @param orderHeaderData
	 * @param orderStatusDTO
	 * @throws SystemException
	 * @throws PortalException 
	 */
	void setOrderStatusData(List<OrderHeader> orderHeaderData, OrderStatusDTO orderStatusDTO, PortletRequest request,long extRepId, boolean hasAllTerritories) throws SystemException, PortalException;


	/**
	 * Get the on-sale-date map for list of order lines. Called from order_line.jsp
	 * @param orderLines all order lines of an order
	 */
	Map<String, String> getOnSaleDateMap(List<OrderLine> orderLines) throws SystemException;

	/**
	 * Set the order lines grouped by statuses
	 * @param orderHeader
	 * @param orderLines
	 * @param orderStatusDTO
	 * @param request
	 * @throws SystemException
	 * @throws PortalException
	 */
	void setOrderAndLineStatusData(OrderHeader orderHeader, List<OrderLine> orderLines,
			OrderStatusDTO orderStatusDTO, PortletRequest request, long extRepId, boolean hasAllTerritories) throws SystemException, PortalException;

}