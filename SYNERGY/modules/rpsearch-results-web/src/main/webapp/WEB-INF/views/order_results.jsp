<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayPortletContext"%>
<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="com.liferay.portal.kernel.util.HtmlUtil"%>
<%@page import="com.hbg.rp.model.OrderLine"%>
<%@page import="com.hbg.rp.search.util.PortalMappingsUtil"%>
<%@page import="com.hbg.rp.search.constants.ApplicationConstant"%>

<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>

<%@ include file="init.jsp" %>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>

 <portlet:resourceURL var="exportToExcel" id="orderSearchExport">
  	<portlet:param name="exportOption" value="${formparam}" />
 </portlet:resourceURL>
 
 
<portlet:actionURL name="getViewAllOrders" var="getViewAllOrdersURL">
</portlet:actionURL>
 

<%
	LiferayPortletContext context = (LiferayPortletContext) portletConfig.getPortletContext();
	long timestamp = context.getPortlet().getTimestamp();
	String pageName = themeDisplay.getLayout().getName(themeDisplay.getLocale());
	String layoutId = themeDisplay.getLayout().getPlid() + "";
%>
<% if (pageName.equalsIgnoreCase("Recent Activity")) { %>
	<c:set var="isRecentActivityPage" value="true"></c:set>
<% } %>


<c:if test="${ (not empty formparam['search-order'] && formparam['search-order'] == 'true') ||
		(empty isRecentActivityPage || isRecentActivityPage == 'false') }" >
	<span style="display:none;" id="main_wrapper_content">
		<c:choose>
			<c:when test="${!empty data && data.size() > 0}"> 
				<c:choose>
					<c:when test="${data.size() == 1 }">
						<p> 1-${data.size()} of <span id="current-size">${data.size()}</span> Order found ${fn:replace(fn:replace(formparam['searchParam'],'>','&gt;'),'<','&lt;')} </p>
					</c:when>
					<c:when test="${data.size() < 10 }">
						<p> 1-${data.size()} of <span id="current-size">${data.size()}</span> Orders found ${fn:replace(fn:replace(formparam['searchParam'],'>','&gt;'),'<','&lt;')} </p>
					</c:when>
					<c:otherwise>
						<p> 1-<span id="current-size">${data.size()}</span> 
						<c:if test="${not empty formparam['order-rowCount'] }"> of 
						    <c:set var="orderRowCount" value="${formparam['order-rowCount']}"/> 
						   <span id="total-size-orders">
						   		<b><fmt:formatNumber type="number" groupingUsed="true" value="${orderRowCount}" /> </b>
						   </span>
						   
							  
						</c:if>
						 Orders found ${fn:replace(fn:replace(formparam['searchParam'],'>','&gt;'),'<','&lt;')} </p>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<p> No Orders found ${fn:replace(fn:replace(formparam['searchParam'],'>','&gt;'),'<','&lt;')} </p>
			</c:otherwise>
		</c:choose> 
	</span>
	<span style="display:none;" id="excelExport">
		<c:if test="${!empty data && data.size() > 0}">
			<a class="btn btn-theme px-1" href="<%=exportToExcel %>"><i class="fa fa-external-link-square"></i> Export Search Results To Excel</a>
			<br>
			<small>You can download up to <%=ApplicationConstant.SEARCH_SIZE %> lines of data.</small>
		</c:if>
	</span>
	
	<script>
		$('.main-wrapper .row .text-left').html($('#main_wrapper_content').html());		
		$('.main-wrapper .row .text-right').html($('#excelExport').html());
	</script>
</c:if>
	<div class="recent-orders" id="result-orders">
		<div class="row">
			<c:choose>
				<c:when test="${not empty isRecentActivityPage && isRecentActivityPage eq 'true' 
					&& (empty formparam['search-order'] || formparam['search-order'] == 'false') }">
					<div class="col-md-8">
					  <c:if test="${!empty data && data.size() > 0}">
						<h2 class="table_hedding">Recent Orders</h2>
					  </c:if>
					  <c:if test="${empty data || data.size() <= 0}">
						<h2 class="table_hedding">No Recent Orders Found</h2>
					  </c:if>
					</div>
					<div class="col-md-4 text-right">
							<a class="view_orders" href="<%=getViewAllOrdersURL%>">View all Orders 
							<i class="fa fa-chevron-right" aria-hidden="true"></i></a>
					</div>
				</c:when>
			</c:choose>
		</div>
	
	<div class="order_rows">
	  <c:choose>
		<c:when test="${!empty data}">
			<c:forEach items="${data}" var="order" varStatus="count">
			
					<portlet:renderURL var="getOrderDetail" >
						<portlet:param name="action" value="getOrderDetail" />
						<portlet:param name="refno" value="${order.orderHeader.referenceNbr}" />
					</portlet:renderURL>
					
				<div class="table_outer">
					<header class="table_header">
						<c:choose>
							<c:when test="${not empty formparam['order-offset']}">
								<span class="serial_number">${count.index + 1 + formparam['order-offset'] }.</span>
							</c:when>
							<c:otherwise>
								<span class="serial_number">${count.index + 1}.</span>	
							</c:otherwise>
						</c:choose>
						<span class="headding">Order Summary</span>
					</header>
					
					<portlet:renderURL var="orderStatusDataURL">
						<portlet:param name="action" value="getOrderStatusPage" />
						<portlet:param name="refno" value="${order.orderHeader.referenceNbr}" />
					</portlet:renderURL>
					
					<portlet:actionURL name="getSearchOrderDetail" var="getSearchOrderDetailURL">
						<portlet:param name="pono" value="${order.orderHeader.poNbr}" />
						<portlet:param name="pono_operator" value="EQ" />
					</portlet:actionURL>
					
					<portlet:actionURL name="getSearchByStore" var="getSearchByStoreURL">
						<portlet:param name="storepk" value="${order.orderHeader.shipToKey}" />
						<portlet:param name="accountpk" value="${order.orderHeader.billToKey}" />
						<portlet:param name="storename" value="${order.orderHeader.shiptoName}" />
						<portlet:param name="storenumber" value="${order.orderHeader.shiptoNumber}" />
						<portlet:param name="accountname" value="${order.orderHeader.accountName}" />
						<portlet:param name="accountnumber" value="${order.orderHeader.accountNumber}" />
						<portlet:param name="storedata" value="${order.orderHeader.shiptoName} | ${order.orderHeader.shiptoNumber}" />
						<portlet:param name="storedataRetain" value="${order.orderHeader.shipToKey}" />
						<portlet:param name="accountdata" value="${order.orderHeader.accountName} | ${order.orderHeader.accountNumber}" />
						<portlet:param name="accountKey" value="${order.orderHeader.billToKey}" />
					</portlet:actionURL>
					
					<portlet:actionURL name="getSearchByZip" var="getSearchByZipURL">
						<portlet:param name="address" value="${order.destinationZip}" />
					</portlet:actionURL>
					
					<div class="body_content">
						<div class="table_content search_results_page">
							<table class="table"> <!-- table on left -->
								<tbody>
									<tr>
										<c:choose>
											<c:when
												test="${ (not empty formparam['search-order'] && formparam['search-order'] == 'true')}">
												<td>PO: <a href="<%=getOrderDetail %>">${order.orderHeader.poNbr}</a>
												</td>
											</c:when>
											<c:otherwise>
												<td>PO: 
													<a href="<%=getSearchOrderDetailURL %>">${order.orderHeader.poNbr}</a>
											    </td>		
											</c:otherwise>
										</c:choose>
									</tr>
									
									<tr><td>
										<c:set var="shipToNumber" value="${order.orderHeader.shiptoNumber}" />
										<c:if test="${shipToNumber > 0}">
											Store#:
											<a href="<%=getSearchByStoreURL%>">${order.orderHeader.shiptoNumber}
												<span id="storepk" hidden>${order.orderHeader.shipToKey}</span>
												<span id="accountpk" hidden>${order.orderHeader.billToKey}</span>
												<input id="storename" type="hidden" value="${order.orderHeader.shiptoName}" />
												<span id="storenumber" hidden>${order.orderHeader.shiptoNumber}</span>
												<span id="accountname" hidden>${order.orderHeader.accountName}</span>
												<span id="accountnumber" hidden>${order.orderHeader.accountNumber}</span>
											</a>
										</c:if>
									</td></tr>
									<tr>
										<td><span>Destination: </span>
											        <span class="text-capitalize">
														<c:out value="${order.destinationAddress} " />
													</span>
													<a href="<%=getSearchByZipURL%>"> ${order.destinationZip}
														<span id="zipcode" hidden>${order.destinationZip}</span>
													</a>
										</td>
									</tr>
									<tr>
										<td>Order Received Date: <b>
											<fmt:formatDate type="date" dateStyle="short" value="${order.orderHeader.orderRecievedDate}" />
										</b></td>
									</tr>
									<tr>
										<td>Order Processed Date: <b>
											<fmt:formatDate type="date" dateStyle="short" value="${order.orderHeader.orderProcessedDate}" />
										</b></td>
									</tr>
									<tr>
										
										<td>Order Status: 
											<b class="text-capitalize" title="${order.statusMessage}">${fn:toLowerCase(order.statusText)}</b> </td>
									</tr>
									<tr>
										<td>&nbsp;</td>
									</tr>
								</tbody>
							</table>
							<table class="table"> <!-- table on right -->
								<tbody>
									<tr>	
										<td>Total Order Quantity: 
											<c:set var="totalOrderQty" value="${order.orderHeader.totalQty}" /> 
											<c:choose>
												<c:when test="${totalOrderQty > 0 }">
													<a href="<%=getOrderDetail %>"> ${totalOrderQty } </a>
												</c:when>
												<c:otherwise> <b> ${totalOrderQty } </b> </c:otherwise>
											</c:choose>
										</td>
									</tr>
									<tr>
										<td>Total Shipped:
											<c:set var="shippedQty" value="${order.orderHeader.shippedQty}" /> 
											<c:choose>
												<c:when test="${shippedQty > 0 }">
													<a href="<%=orderStatusDataURL %>#Shipped"> ${shippedQty } </a>
												</c:when>
												<c:otherwise> <b> ${shippedQty } </b> </c:otherwise>
											</c:choose>
										 </td>
									</tr>
									<tr>
										<td>Total Preparing to Ship: 
											<c:set var="preparingtoShipQty" value="${order.orderHeader.releasedQty}" /> 
											<c:choose>
												<c:when test="${preparingtoShipQty > 0 }">
													<a href="<%=orderStatusDataURL %>#PreparingtoShip"> ${preparingtoShipQty } </a>
												</c:when>
												<c:otherwise> <b> ${preparingtoShipQty } </b> </c:otherwise>
											</c:choose>
										</td>
									</tr>
									<tr>
										<td>Total In Progress: 
											<c:set var="inProgressQty" value="${order.orderHeader.inProcessQty}" /> 
											<c:choose>
												<c:when test="${inProgressQty > 0 }">
													<a href="<%=orderStatusDataURL %>#InProgress"> ${inProgressQty } </a>
												</c:when>
												<c:otherwise> <b> ${inProgressQty } </b> </c:otherwise>
											</c:choose>
										</td>
									</tr>
									<tr>
										<td>Total Action Required: 
											<c:set var="actionRequiredQty" value="${order.orderHeader.creditHoldQty}" /> 
											<c:choose>
												<c:when test="${actionRequiredQty > 0 }">
													<a href="<%=orderStatusDataURL %>#ActionRequired"> ${actionRequiredQty } </a>
												</c:when>
												<c:otherwise> <b> ${actionRequiredQty } </b> </c:otherwise>
											</c:choose>
										</td>
									</tr>
									<tr>
										<td>Total Backordered: 
											<c:set var="backorderedQty" value="${order.orderHeader.backorderedQty}" /> 
											<c:choose>
												<c:when test="${backorderedQty > 0 }">
													<a href="<%=orderStatusDataURL %>#Backordered"> ${backorderedQty } </a>
												</c:when>
												<c:otherwise> <b> ${backorderedQty } </b> </c:otherwise>
											</c:choose>
										</td>
									</tr>
									<tr>
										<td>Total Canceled:
											<c:set var="cancelledQty" value="${order.orderHeader.cancelQty}" /> 
											<c:choose>
												<c:when test="${cancelledQty > 0 }">
													<a href="<%=orderStatusDataURL %>#Canceled"> ${cancelledQty } </a>
												</c:when>
												<c:otherwise> <b> ${cancelledQty } </b> </c:otherwise>
											</c:choose>
										</td>
									</tr>
								</tbody>
							</table>
						</div>

						<div class="right_content text-center">
							<p>Order Reference:</p>

							<p>
								<b>${order.orderHeader.referenceNbr}</b>
							</p>
							
							<a class="btn btn-theme" href="<%=getOrderDetail %>">View Order</a>
						</div>
					</div>
					 <c:choose>
		              <c:when test="${!empty order.orderLines}">
						<div class="inline_table">
							<table class="table">
								<thead>
									<tr>
										<th>Item Code<br> (ISBN/EAN)</th>
										<th>Title</th>
										<th class="long_title">Author(s)</th>
										<th>Quantity<br>Total</th>
										<th>Pub Status</th>
										<th>Line Status</th>
										<th class="status_details">Status Details</th>
									</tr>
								</thead>
								
								<tbody>
									<c:set scope="request" var="orderResultsPage" value="true" />
									<c:set scope="request" var="refno" value="${order.orderHeader.referenceNbr}" />
									<c:set value="${order.orderLines}" scope="request" var="orderLines" />
									<jsp:include page="/WEB-INF/views/orderline_template.jsp"/>
									
								</tbody>
							</table>
						</div>
						</c:when>
						</c:choose>
				</div> <!-- end of table_outer -->
			</c:forEach>
		</c:when>
	  </c:choose>
	     <div class="append_div">
	  		<c:if test="${not empty formparam['showLoadMore'] && formparam['showLoadMore'] eq 'true'}" >
				<div class="row text-center">
					  <div class="col-xs-12" id="orderBtnLoadMore">
						<button id="loadMore" class="btn btn-theme"> 
							Load Next <%=ApplicationConstant.PAGE_SIZE %> Results </button>
					  </div>
					</div>
			 </c:if>
			<div id="order-loading" class=""></div>
		</div>
	</div> <!-- end of order_rows -->
</div> <!-- end of recent-orders -->



<c:if test="${empty isRecentActivityPage }">
	<script type="text/javascript">
		$('.search-options').hide();
		$('#search-orders').show();
	</script>
</c:if>

<c:if test="${not empty formparam['search-order'] && formparam['search-order']=='true'}">
<script type="text/javascript">
	document.title='HBG Retailer Portal: Order Search Results';
</script>
</c:if>

<script src="<%=request.getContextPath()%>/js/clear_form.js?t=<%=timestamp%>"
	type="text/javascript"></script>
