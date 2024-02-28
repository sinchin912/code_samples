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
<%@page import="com.hbg.rp.model.OrderLine"%>
<%@page import="com.hbg.rp.search.util.PortalMappingsUtil"%>
<%@page import="com.hbg.rp.search.constants.ApplicationConstant"%>
<%@page import="com.hbg.rp.search.util.CommonUtil"%>

<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ include file="init.jsp" %>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="springfm"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="theme"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.liferay.portal.kernel.model.Role"%>
<%@page import="java.util.List"%>
<%@page import="javax.portlet.PortletSession"%>

<!-- <script src="https://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
 -->
<%
	LiferayPortletContext context = (LiferayPortletContext) portletConfig.getPortletContext();
	long timestamp = context.getPortlet().getTimestamp();
	String pageName = themeDisplay.getLayout().getName(themeDisplay.getLocale());
	Boolean showClaimsFlag = (Boolean) themeDisplay.getUser().getExpandoBridge().getAttribute("showClaimsFlag");
	CommonUtil commonUtil = new CommonUtil();
    String allReportingGroups = commonUtil.getAllReportingGroups();
    String reportingGroups = commonUtil.getPermittedReportingGroups(renderRequest);
    Boolean showZendeskFlag = (Boolean) themeDisplay.getUser().getExpandoBridge().getAttribute("showZendeskFlag");
    List<Role> loggedInUserRoles = themeDisplay.getUser().getRoles();
	Long extRepId = (long) renderRequest.getPortletSession().getAttribute(ApplicationConstant.LIFERAY_SHARED_EXT_REP_ID, PortletSession.APPLICATION_SCOPE);
	Boolean isRepUser = (extRepId > 0) ? true : false;
%>

 <c:set var="allReportingGroups" value="<%=allReportingGroups %>" />
 <c:set var="reportingGroups" value="<%=reportingGroups %>" />
 <c:set var="encryptedIsbn" value="<%=ApplicationConstant.ENCRYPTED_ISBN %>" />
 <c:set var="encryptedTitle" value="<%= ApplicationConstant.ENCRYPTED_TITLE %>" />
 <c:set var="encryptedAuthor" value="<%=ApplicationConstant.ENCRYPTED_AUTHOR %>" />
 <c:set var="encryptedMsrp" value="<%=ApplicationConstant.ENCRYPTED_MSRP %>" />
 <c:set var="showClaimsFlag" value="<%=showClaimsFlag %>" />
 <c:set var="isRepUser" value="<%=isRepUser%>" />

<c:forEach var="role" items="<%=loggedInUserRoles%>">
	<c:set var="userRole" value="${role.getName()}"></c:set>
	<jsp:useBean id="userRole" type="java.lang.String" />

	<c:if test='<%=userRole.equalsIgnoreCase("all")%>'>
       	<c:set var="ALL" value="true"></c:set>
    </c:if>


	<c:if test='<%=userRole.equalsIgnoreCase(ApplicationConstant.NRP_TRACK_CLAIMS)%>'>
		<c:set var="NRP_TRACK_CLAIMS" value="true"></c:set>
	</c:if>

</c:forEach>

<portlet:resourceURL id="initiateZendeskTicket" var="initiateZendeskTicketURL"> </portlet:resourceURL>



<portlet:actionURL name="getSearchCatalogByAuthor" var="getSearchCatalogByAuthorURL"></portlet:actionURL>
<portlet:resourceURL id="getShipmentTrackingData" var="shipmentTrackingDataURL"> </portlet:resourceURL>

<input type="hidden" id="shipmentReloadTime" value="<%=ApplicationConstant.SHIPMENT_RELOAD_TIME_MILLIS %>" />
<input type="hidden" id="shipmentReloadCount" value="<%=ApplicationConstant.SHIPMENT_RELOAD_COUNT %>" />
<!-- .order-details -->
<div class="main-wrapper">
  <div class="container">

   <c:if test="${!empty data}">
      <c:set value="${data.orderHeader.orderHeaderId}" var="orderHeaderId"></c:set>
   </c:if>
   
   <portlet:resourceURL var="exportToExcel" id="exportToExcel">
	   <portlet:param name="exportOption" value="orderDetailsExport${data.orderHeader.referenceNbr}" />
   </portlet:resourceURL>
   <portlet:resourceURL var="exportToShipmentExcel" id="exportToExcel">
	   <portlet:param name="exportOption" value="shipmentDetailsExport${data.orderHeader.referenceNbr}" />
   </portlet:resourceURL>
 <div class="row">
	<div class="col-md-12">
		<div class="recent-orders order-details">
			<div class="row headding-row">
				<div class="col-md-8">
					<h2 class="table_hedding">Order Summary for <span>${refNo}</span></h2>
				</div>
				<div class="col-md-4 text-right">
					<a class="btn btn-theme" href="<%=exportToExcel %>">
						<i class="fa fa-external-link-square"></i>
						Export Order XLS
						<style type="text/css">
						.js .page-maximized .portlet-topper-toolbar{
							display: none;
						}
						</style>
					</a>
				</div>
			</div>

		<c:choose>
			<c:when test="${!empty data}">
		
		        <portlet:actionURL name="getSearchOrderDetail" var="getSearchOrderDetailURL">
					<portlet:param name="pono" value="${data.orderHeader.poNbr}" />
					<portlet:param name="pono_operator" value="EQ" />
			    </portlet:actionURL>
			    
			    <portlet:actionURL name="getSearchByZip" var="getSearchByZipURL">
					<portlet:param name="address" value="${data.destinationZip}" />
				</portlet:actionURL>
				
			    <portlet:actionURL name="getSearchByStore" var="getSearchByStoreURL">
					<portlet:param name="storepk" value="${data.orderHeader.shipToKey}" />
					<portlet:param name="accountpk" value="${data.orderHeader.billToKey}" />
					<portlet:param name="storename" value="${data.orderHeader.shiptoName}" />
					<portlet:param name="storenumber" value="${data.orderHeader.shiptoNumber}" />
					<portlet:param name="accountname" value="${data.orderHeader.accountName}" />
					<portlet:param name="accountnumber" value="${data.orderHeader.accountNumber}" />
					<portlet:param name="storedata" value="${data.orderHeader.shiptoName} | ${data.orderHeader.shiptoNumber}" />
					<portlet:param name="storedataRetain" value="${data.orderHeader.shipToKey}" />
					<portlet:param name="accountdata" value="${data.orderHeader.accountName} | ${data.orderHeader.accountNumber}" />
					<portlet:param name="accountKey" value="${data.orderHeader.billToKey}" />
				</portlet:actionURL>
			    
			    <portlet:actionURL name="getSearchByAccount" var="getSearchByAccountURL">
					<portlet:param name="accountpk" value="${data.orderHeader.billToKey}" />
					<portlet:param name="accountname" value="${data.orderHeader.accountName}" />
					<portlet:param name="accountnumber" value="${data.orderHeader.accountNumber}" />
					<portlet:param name="accountdata" value="${data.orderHeader.accountName} | ${data.orderHeader.accountNumber}" />
					<portlet:param name="accountKey" value="${data.orderHeader.billToKey}" />
			    </portlet:actionURL>
			    
			     <portlet:actionURL name="getSearchByAccount" var="getSearchByAccountNoURL">
					<portlet:param name="accountpk" value="${data.orderHeader.billToKey}" />
					<portlet:param name="accountname" value="${data.orderHeader.accountName}" />
					<portlet:param name="accountnumber" value="${data.orderHeader.accountNumber}" />
					<portlet:param name="accountdata" value="${data.orderHeader.accountName} | ${data.orderHeader.accountNumber}" />
					<portlet:param name="accountKey" value="${data.orderHeader.billToKey}" />
			    </portlet:actionURL>
			    
				<c:choose>
					<c:when test="${ not empty data.specialOffers }">
						<c:forEach items="${data.specialOffers}" var="currentItem" >
							<c:set var="offerCode" value="${offerCode}${empty offerCode ? '' : ','}${currentItem}" />
			    		</c:forEach>
					</c:when>
					<c:otherwise>
						<c:set var="offerCode" value="N/A" />
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${ not empty data.orderHeader.destinationName }">
						<c:set var="destinationAddress" value="${data.orderHeader.destinationName}, ${fn:toLowerCase(data.orderHeader.destinationCity)}, ${data.orderHeader.destinationState} ${data.orderHeader.destinationZip}" />	
					</c:when>
					<c:otherwise>
						<c:set var="destinationAddress" value="${data.orderHeader.shiptoName}, ${fn:toLowerCase(data.orderHeader.shiptoCity)}, ${data.orderHeader.shiptoState} ${data.orderHeader.shiptoZip}" />
					</c:otherwise>
				</c:choose>
                <input type="hidden" value="${destinationAddress}" id="destinationAddress"/>
				<input type="hidden" value="${offerCode}" id="offerCode"/>
                <input type="hidden" value="${data.orderHeader.orderSource}" id="orderSource"/>
                <input type="hidden" value="${data.orderHeader.orderProcessedDate}" id="orderProcessedDate"/>
                <input type="hidden" value="${data.orderHeader.shiptoNumber}" id="shiptoNumber"/>
				
				
					<div class="table_outer">
						<header class="table_header">
							<span class="headding">Order Summary</span>
						</header>
						<div class="body_content">
							<div class="row">
								<div class="col-sm-3">
									<div class="table_content">
										<table class="table">
											<tbody>
												<tr>
													<td>PO: 
													<a href="<%=getSearchOrderDetailURL%>" >${data.orderHeader.poNbr} </a>
											    </td>	
												</tr>
												<tr>
													<td>Order Received
														Date: <b><fmt:formatDate type="date" dateStyle="short"
																value="${data.orderHeader.orderRecievedDate}" /></b></td>
												</tr>
												<tr>
													<td>Order Processed
														Date: <b><fmt:formatDate type="date" dateStyle="short"
																value="${data.orderHeader.orderProcessedDate}" /></b></td>
												</tr>
												<tr>
													<td>Order Status: 
														<b class="text-capitalize" title="${data.statusMessage}">${fn:toLowerCase(data.statusText)}</b>
													</td>
												</tr>
												<tr>
													<td>Order Source: 
														<b>${data.orderHeader.orderSource}</b>
													</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
					            <div class="col-sm-3">
									<div class="table_content">
										<table class="table">
											<tbody>
												<tr>
													<td>Account Name: 
													    <a href="<%=getSearchByAccountURL%>" >${fn:toLowerCase(data.orderHeader.accountName)}
															<span id="accountpk" hidden>${data.orderHeader.billToKey}</span>
															<span id="accountname" hidden>${data.orderHeader.accountName}</span>
															<span id="accountnumber" hidden>${data.orderHeader.accountNumber}</span>
														</a>
												   </td>
												</tr>
												<tr>
													<td>Account Number: 
														<a href="<%=getSearchByAccountNoURL%>">${data.orderHeader.accountNumber}
															<span id="accountpk" hidden>${data.orderHeader.billToKey}</span>
															<span id="accountname" hidden>${data.orderHeader.accountName}</span>
															<span id="accountnumber" hidden>${data.orderHeader.accountNumber}</span>
														</a>
												   </td>
												</tr>				
																			
												<tr>
													<td>Offer Code: ${data.offerCode}</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
								<div class="col-sm-3">
									<div class="table_content">
										<table class="table">
											<tbody>
												<tr>
													<td>Destination: 
														<span class="text-capitalize">
															<c:out value="${data.destinationName}" />
														</span>
													</td>
												</tr>
												<tr>
													<c:set var="shipToNumber" value="${data.orderHeader.shiptoNumber}" />
													<td><c:if test="${shipToNumber > 0}">
                                                        Store #: 
														   <a href="<%=getSearchByStoreURL%>">${data.orderHeader.shiptoNumber}
																<span id="accountpk" hidden>${data.orderHeader.billToKey}</span>
																<input id="storename" type="hidden" value="${data.orderHeader.shiptoName}" />
																<span id="storenumber" hidden>${data.orderHeader.shiptoNumber}</span>
																<span id="accountname" hidden>${data.orderHeader.accountName}</span>
																<span id="accountnumber" hidden>${data.orderHeader.accountNumber}</span>
                                                           </a>
														</c:if>
													</td>
												</tr>
												<tr>
													<td>Destination Address: <b> 
													<span class="text-capitalize">
														<c:out value="${data.destinationAddress} " />
														</span>
														<a href="<%=getSearchByZipURL%>" > ${data.destinationZip}
															<span id="zipcode" hidden>${data.destinationZip}</span>
														</a>
													</b></td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
								<div class="col-sm-3">
									<div class="table_content">
										<table class="table">
											<tbody>
												<tr>
													<td>Total Order Quantity: <b>${data.orderHeader.totalQty}</b></td>
												</tr>
												<tr>
													<td>Total Shipped: <b>${data.orderHeader.shippedQty}</b></td>
												</tr>
												<tr>
													<td>Total Preparing to Ship: <b>${data.orderHeader.releasedQty}</b></td>
												</tr>
												<tr>
													<td>Total In Progress: <b>${data.orderHeader.inProcessQty}</b></td>
												</tr>
												<tr>
													<td>Total Action Required: <b>${data.orderHeader.creditHoldQty}</b></td>
												</tr>
												<tr>
													<td>Total Backordered: <b>${data.orderHeader.backorderedQty}</b></td>
												</tr>
												<tr>
													<td>Total Canceled: <b>${data.orderHeader.cancelQty}</b></td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</c:when>
			</c:choose> <!-- order summary ends here -->
			
			<div class="order_overview"> <!--  left tab -->
					<c:choose>
						<c:when test="${tab == 'shipments'}">
							<c:set var="orderStatusActive" value="active" />
						</c:when>
						<c:otherwise>
							<c:set var="orderOverviewActive" value="active" />
						</c:otherwise>
					</c:choose>	

					<!-- Nav tabs -->
					<ul class="nav nav-tabs" role="tablist" id="customTab">
						<li role="presentation" class="${orderOverviewActive }">
							<a href="#order-overview" aria-controls="order-overview" role="tab" data-toggle="tab" >
								Order Overview</a></li>
						<li role="presentation" class="${orderStatusActive }">
						    <a href="#view-order-by-status" aria-controls="view-order-by-status" role="tab" data-toggle="tab"  class="view-order-status" >
                            								View Order by Status</a></li>
                         <c:if test="${!empty statusData.shipments}">
                         	<li class="download-link active-link export-link" style="display:none">                     		                            			                            
						        <a href="<%=exportToShipmentExcel %>">
									<i class="fa fa-external-link-square"></i>
									Export Shipment XLS
								</a>
				            </li>
                         </c:if>
					</ul>
	
					<!-- Tab panes -->
					<div class="tab-content">
						<div role="tabpanel" class="tab-pane ${orderOverviewActive }" id="order-overview"> 
							<c:choose>
							  <c:when test="${!empty data.orderLines}">
							   <div class="table_outer"> <!-- tab: order-overview -->
								<div class="inline_table">
									<table class="table">
									  <thead>
										<tr>
											<th>#</th>
											<th>Item Code<br> (ISBN/EAN)</th>
											<th>Title</th>
											<th class="long_title">Author(s)</th>
											<th>Quantity<br>Total</th>
											<th>Pub Status</th>
											<th>Line Status</th>
											<th class="status_details">Status Details</th>
											<!-- Changed for NRP-2630 -->
											<th>*MSRP</th>
										</tr>
									</thead>
									<tbody>
										<c:set scope="request" value="0" var="totalQty"></c:set>
										<c:set value="${data.orderLines}" scope="request" var="orderLines" />
										<c:set scope="request" var="orderDetailPage" value="true" />
										<c:set scope="request" var="refno" value="${data.orderHeader.referenceNbr}" />
										<jsp:include page="/WEB-INF/views/orderline_template.jsp"/>
										<c:forEach items="${data.orderLines}" var="orderLine">
										   <c:set scope="request" value="${totalQty + orderLine.orderQuantity }" var="totalQty"></c:set>
										</c:forEach>
										<tr class="total-row rep-user-total">
												<td>&nbsp;</td>
												<td colspan="3">
												<c:if test="${isRepUser}">
		                                              <%=ApplicationConstant.SALES_REPRESENTATIVE %>
		                                        </c:if>
		                                        Order Total: 
		                                        </td>
		                                        <td style="text-align: center;">
												${totalQty}
												</td>
												<td>&nbsp;</td>
											    <td>&nbsp;</td>
											    <td>&nbsp;</td>
											    <td>&nbsp;</td>
											</tr>
									</tbody>
								</table>
							</div>
							</div>
							 <p><b>*Prices subject to change without notice.</b></p>
							 <c:if test="${isRepUser}">
							    <p><b><%=ApplicationConstant.SALES_REPRESENTATIVE_DISTRIBUTION_CLIENTS %> <a href="mailto:<%=ApplicationConstant.MAIL_TO_INVOICES_HBG%>"><%=ApplicationConstant.MAIL_TO_INVOICES_HBG%></a>. </b></p>
							 </c:if>
						</c:when>
						<c:otherwise>
							<div class="col-sm-12">
								<br>
								<b> Sorry, Information is not available </b>
							</div>
						</c:otherwise>
						</c:choose>
			  		</div> <!-- End of tab: order-overview -->
			
							<div role="tabpanel" class="tab-pane  ${orderStatusActive}" id="view-order-by-status">						
							      <div class="table_outer"> <!--Start: Shipment data inside -->
							          <c:choose>
								          <c:when test="${!empty statusData.shipments or !empty statusData.orderLines}">
								          <c:if test="${callingPage ne 'invoice' }">
									        <div class="view-order-by-status">
	                                            <div class="sub-nav">
	                                                <ul class="nav-links">
	                                                	<c:if test="${!empty statusData.shipments}">
	                                                    	<li role="presentation" class="active"><a href="javascript:void(0)">All Shipments</a></li>
	                                                    </c:if>
	                                                    <c:if test="${statusData.orderLinesExists['Preparing to Ship'] == true}">
	                                                    	<li role="presentation"><a href="javascript:void(0)">Preparing to Ship</a></li>
	                                                    </c:if>
	                                                    <c:if test="${statusData.orderLinesExists['In Progress'] == true}">
	                                                     	<li role="presentation"><a href="javascript:void(0)">In Progress</a></li>
	                                                    </c:if>
	                                                    <c:if test="${statusData.orderLinesExists['Action Required'] == true}">
	                                                     	<li role="presentation"><a href="javascript:void(0)">Action Required</a></li>
	                                                    </c:if>
	                                                    <c:if test="${statusData.orderLinesExists['Backordered'] == true}">
	                                                     	<li role="presentation"><a href="javascript:void(0)">Backordered</a></li>
	                                                    </c:if>
	                                                    <c:if test="${statusData.orderLinesExists['Canceled'] == true}">
	                                                     	<li role="presentation"><a href="javascript:void(0)">Canceled</a></li>
	                                                    </c:if>
	
	                                                </ul>
	                                            </div>
	                                         </div>
	                                         </c:if>
	                                        </c:when>
	                                        <c:otherwise>
												<div class="col-sm-12">
													<br>
													<b> Sorry, Information is not available </b>
												</div>
											</c:otherwise>
                                        </c:choose>
                                        
                                       <c:if test="${!empty statusData.shipments}">
                                        <div class="inline_table min-top-space" id="Shipped">
                                             <div class="${callingPage ne 'invoice' ? 'all-shipments-bar' : 'all-shipments-bar all-invoice-bar'}">
                                                <div class="row">
                                                    <div class="col-sm-6">
                                                    <c:if test="${callingPage ne 'invoice'}">
                                                        <h3 class="font-merriweather">All Shipments <img src="<%=request.getContextPath()%>/images/shiptruck.svg" alt="SHIP TRUCK"></h3>
                                                    </c:if>
                                                    </div>
                                                    <div class="col-sm-6 text-right">
                                                    	<span class="right-link"> 
                                                    		<span><a href="/web/guest/faqs">Read FAQs about this status</a></span>
                                                    		<i class="fa fa-question-circle"></i>
                                                    	</span>
                                                    </div>
                                                </div>
                                             </div>
                                             
                                                <c:forEach var="entry" items="${statusData.shipments}">
                                                	<c:forEach var="shipment" items="${entry.shipments}" varStatus="count">
                                                		<div class="table_outer shipment-blocks" id="Shipped-${shipment.invoiceNumber}-${shipment.trackingNumber}">
		                                             	  <header class="table_header">
		                                                    <div class="row">
		                                                        <div class="col-sm-6"><span class="serial_number">${count.index +1}.</span> <span class="headding">Shipment Summary</span></div>
		                                                        <portlet:renderURL var="getInvoiceDetail" >
																	<portlet:param name="action" value="getInvoiceDetail" />
																	<portlet:param name="<%=ApplicationConstant.INV_INVOICE_NBR %>" value="${shipment.invoiceNumber}" />
																</portlet:renderURL>
																<portlet:renderURL var="getInvoiceDetailForClaim">
																	<portlet:param name="action" value="getInvoiceDetail" />
																	<portlet:param name="<%=ApplicationConstant.INV_INVOICE_NBR %>" value="${shipment.invoiceNumber}" />
																	<portlet:param name="orderCallingPage" value="orderDetailPage" />
																</portlet:renderURL>
																<portlet:actionURL name="getSearchByShipGroup" var="getSearchByShipGroupURL">
																	<portlet:param name="shipGroup" value="${shipment.shipGroupId}" />
																	<portlet:param name="search-shipment" value="true" />
																	<portlet:param name="shipgroupno" value="${shipment.shipGroupId}" />
																	<portlet:param name="ship-shipGroup" value="${shipment.shipGroupId}" />
																</portlet:actionURL>
		                                                        <div class="col-sm-6 text-right">
		                                                        <c:if test="${callingPage ne 'invoice'}">
		                                                        	<span class="invoice-number">Invoice Number: 
																	<c:choose>
                     												<c:when test="${fn:contains(invoiceHeaderData, shipment.invoiceHeaderId)}">
                         												<a href="<%=getInvoiceDetail %>"> ${shipment.invoiceNumber} </a> 
                     												</c:when>    
                     												<c:otherwise>
                         												${shipment.invoiceNumber}
                     												</c:otherwise>
                 													</c:choose> 
		                                                        	</span>
		                                                        </c:if>
		                                                        </div>
		                                                    </div>
		                                                  </header>
		                                                  <div class="shipment-status-step">
		                                                  	<c:set value="${shipment.trackingNumber}" var="trackingNumber"></c:set>
				                                            <div class="row data-row">
		                                                        <div class="col-sm-4">
		                                                            <p>Shipment: <b>${shipment.carrierName}</b></p>
																    <!-- NRP-2497 start -->
																    <c:if test="${!empty shipment.serviceLevelCode}">
																		<p>Shipment Method: <b>${shipment.serviceLevelDesc}</b></p>
														            </c:if>
														            <c:if test="${empty shipment.serviceLevelCode}">
																		<p>Shipment Method: </p>
														            </c:if>
																	<!-- NRP-2497 end -->
															    </div>
		                                                        <div class="col-sm-4">
		                                                            <p>Ship Group:</p>
		                                                            <p><a href="<%=getSearchByShipGroupURL%>" >${shipment.shipGroupId}
		                                                            		<span id="shipgroupno" hidden>${shipment.shipGroupId}</span>
		                                                            </a></p>
		                                                        </div>
		                                                        <div class="col-sm-4 text-center">
		                                                            <p>Carrier Tracking Number:</p>
		                                                            <p class="trackingNumberClass">


                                                                    							<c:set var="suffixedId" value="_${shipment.orderHeaderId}_${shipment.invoiceNumber}_${shipment.shipmentHeaderId}"></c:set>

                                                                    							<c:set var="carrierCode" value="${shipment.carrierId}"></c:set>
                                                                    							<c:set var="shipmentCarrierDetails" value="${supportedCarrierData[carrierCode]}"></c:set>
                                                                    							<c:set var="supportedTrackingType"></c:set>
                                                                    							<c:if test="${not empty shipmentCarrierDetails && not empty shipmentCarrierDetails.supportedTrackingType}">
                                                                    								<c:set var="supportedTrackingType" value="${shipmentCarrierDetails.supportedTrackingType}"></c:set>
                                                                    							</c:if>

                                                                    							<jsp:useBean id="now" class="java.util.Date" />
                                                                    							<fmt:parseNumber  value="${now.time / (1000*60*60*24) }" integerOnly="true" var="nowDays"  />
                                                                    							<fmt:parseNumber  value="${shipment.shipDate.time / (1000*60*60*24) }" integerOnly="true" var="shipDateDays"  />

                                                                                       <c:choose>
                                                                                                                    <c:when test="${not empty supportedTrackingType && supportedTrackingType eq 'BOL'}">
                                                                                                                        <a id="${shipment.billOfLadingNumber}${suffixedId}" href="javascript:void(0)">
                                                                                                                            ${shipment.billOfLadingNumber}</a>
                                                                                                                        <c:set var="trackingNumber" value="${shipment.billOfLadingNumber}"></c:set>
                                                                                                                    </c:when>
                                                                                                                    <c:when test="${not empty supportedTrackingType && supportedTrackingType eq 'PRO'}">
                                                                                                                        <a id="${shipment.proNumber}${suffixedId}" href="javascript:void(0)">${shipment.proNumber}</a>
                                                                                                                        <c:set var="trackingNumber" value="${shipment.proNumber}"></c:set>
                                                                                                                    </c:when>
                                                                                                                    <c:otherwise>
                                                                                                                        <c:choose>
                                                                                                                            <c:when test="${not empty shipment.trackingNumber}">
                                                                                                                                <a id="${shipment.trackingNumber}${suffixedId}" href="javascript:void(0)">
                                                                                                                                    ${shipment.trackingNumber}</a>
                                                                                                                            </c:when>
                                                                                                                            <c:when test="${empty shipment.trackingNumber && not empty shipment.proNumber}">
                                                                                                                                <a id="${shipment.proNumber}${suffixedId}" href="javascript:void(0)">
                                                                                                                                    ${shipment.proNumber}</a>
                                                                                                                                <c:set var="trackingNumber" value="${shipment.proNumber}"></c:set>
                                                                                                                            </c:when>
                                                                                                                            <c:when test="${empty shipment.trackingNumber && empty shipment.proNumber && not empty shipment.billOfLadingNumber}">
                                                                                                                                <a id="${shipment.billOfLadingNumber}${suffixedId}" href="javascript:void(0)">
                                                                                                                                    ${shipment.billOfLadingNumber}</a>
                                                                                                                                <c:set var="trackingNumber" value="${shipment.billOfLadingNumber}"></c:set>
                                                                                                                            </c:when>
                                                                                                                            <c:when test="${empty shipment.trackingNumber && empty shipment.proNumber &&  empty shipment.billOfLadingNumber && ((nowDays-shipDateDays)<120) }">
                                                                                                                                    <c:set var="120days" value="${shipment.billOfLadingNumber}"></c:set>
                                                                                                                                    <b>Available on Request</b>
                                                                                                                            </c:when>
                                                                                                                            <c:otherwise>
                                                                                                                                    <b>N/A</b>
                                                                                                                            </c:otherwise>
                                                                                                                        </c:choose>
                                                                                                                    </c:otherwise>
                                                                                                                </c:choose>

															<fmt:formatDate type="date" dateStyle="short" value="${shipment.shipDate}" var="shipDate" />
                                                            							<script> $(document).ready(function() {
                                                            								loadTrackingURL(this,'${shipment.carrierId}','${trackingNumber}','${shipment.orderHeaderId}','${shipment.invoiceNumber}','${shipDate}','${shipment.shipmentHeaderId}');
                                                            							});
                                                            							</script>

		                                                            </p>
		                                                        </div>
		                                                    </div>  <!--  END : row data-row div -->
		                                      <div class="statusDiv" id="<c:out value='zendeskParam${shipment.shipmentHeaderId}'/>">            
                                              	<c:set var="shipmentHeaderId" value="${''.concat(shipment.shipmentHeaderId)}" />
                                                <c:set var="shipmentTrackingData" value="${statusData.shipmentTracking[shipmentHeaderId]}" />
                                                <c:choose>
                                                     <c:when test="${!empty shipmentTrackingData}">	
                                                        <c:set var="showZendeskFlag" scope="request" value="<%=showZendeskFlag %>" />
                                                        <c:set var="trackingData" scope="request" value="${shipmentTrackingData.trackingData}"></c:set>
                                                        <c:set var="ZENDESK" scope="request" value="${shipmentTrackingData.ZENDESK}"></c:set>
                                                        <c:set var="shipmentHeaderIdRef" scope="request" value="${shipmentHeaderId}" />
                                                        <c:set var="orderRecievedDate" scope="request" value="${shipment.orderRecievedDate}" />
                                                        <c:set var="shipDate" scope="request" value="${shipment.shipDate}" />
                                                        <jsp:include page="/WEB-INF/views/order_status_template.jsp"/>
                                                     </c:when>
                                                     <c:otherwise>
                                                        <div class="col-xs-8">
                                                            <div class="col-sm-12">
                                                            <font color="red"><b>Sorry, tracking information for this shipment is no longer available from the carrier.</b></font>
                                                            </div>
                                                        </div>
                                                     </c:otherwise>
                                                </c:choose>
                                           <input type="hidden" value="${shipment.referenceNumber}" id="refNo"/>
                                           <input type="hidden" value="${shipment.poNbr}" id="poNo"/>
                                           <input type="hidden" value="${shipment.invoiceNumber}" id="invoiceNo"/>
                                           <input type="hidden" value="${shipment.shipGroupId}" id="shipGroup"/>
                                           <input type="hidden" value="${shipment.carrierName}" id="shipment"/>
                                           <input type="hidden" value="${shipment.shipmentType}" id="shipmentMethod"/>
                                           <input type="hidden" value="${shipment.shipmentHeaderId}" id="shipmentHeaderId"/>
                                           <input type="hidden" value="${shipment.accountName}" id="accountName"/>
                                           <input type="hidden" value="${shipment.accountNumber}" id="accountNumber"/>
                                           <input type="hidden" value="${shipment.orderRecievedDate}" id="orderRcvdDate"/>
                                           <input type="hidden" value="${shipment.shipDate}" id="shippDate"/>
                                           </div>
		                                                 
		                                                </div>  <!-- END : shipment-status-step div -->
                                           			</div> <!--  END : table_outer div -->
                                           		<div class="table_accourdian">
                                                    <div class="row">
                                                        <div class="col-sm-6">
                                                            <a href="javascript:void(0)" data-bind="${shipment.shipmentHeaderId}" class="lineShipmentItemsLink">view line items <i class="caret"></i><div id="${count.index}" data-bind="${shipment.shipmentHeaderId}"></div></a>
                                                        </div>
                                                        <div class="col-sm-6 text-right">
														<!--Start: Start a claim -->
 														<c:if test="${(not empty ALL && ALL) || (not empty NRP_TRACK_CLAIMS && NRP_TRACK_CLAIMS)}">
 		                                           			<c:if test="${callingPage ne 'invoice' && showClaimsFlag}">
		                                           				<c:forEach var="invoiceDateEntry" items="${entry.invoiceHeaderDateMap}">
					                                           		 <c:if test="${invoiceDateEntry.key eq shipment.invoiceHeaderId}">
																		<fmt:parseNumber  value="${now.time / (1000*60*60*24) }" integerOnly="true" var="invoiceNowDays"  />
									 									<fmt:parseNumber  value="${invoiceDateEntry.value.time / (1000*60*60*24) }" integerOnly="true" var="invoiceDateDays"  />
																		<c:if test="${((invoiceNowDays-invoiceDateDays) <= invoiceGeneratedDays) && fn:contains(invoiceHeaderData, shipment.invoiceHeaderId)}">
																				<a href="<%=getInvoiceDetailForClaim %>" class="lineItemsLink shipemnt-cliam-link">Start or View a Claim for Items in this Invoice</a>
																		 </c:if>
					                                           		  </c:if>
				                                           		</c:forEach>
				                                           	</c:if>
														</c:if>
		                                           		<!--End: Start a claim -->
                                                            </div>
                                                    </div>
                                                </div>
                                           			<div id="show-line-items${count.index}" class="inline_table hidden">
                                                        <div id="line-items" aria-expanded="false">
                                                        <c:set var="shipmentHeaderId" value="${''.concat(shipment.shipmentHeaderId)}" />
                                                        <c:set var="lineItemsData" value="${statusData.shipmentLine[shipmentHeaderId]}" />
                                                        <c:choose>
                                                            <c:when test="${!empty lineItemsData}">
                                                                <table class="table" id="lineItemsTable">
                                                                    <thead>
                                                                        <tr>
                                                                            <th>#</th>
                                                                            <th>Item Code<br> (ISBN/EAN)</th>
                                                                            <th>Title</th>
																			<th class="long_title">Author(s)</th>
                                                                            <th>Order<br>Quantity</th>
                                                                            <th>Shipped<br>Quantity</th>
                                                                            <th>Pub Status</th>
                                                                            <th>*MSRP</th>
                                                                        </tr>
                                                                    </thead>
                                                                    <tbody>
                                                                        <c:set scope="request" var="total" value="0" />
                                                                        <c:set scope="request" var="rowCount" value="1" />
                                                                        <c:forEach items="${lineItemsData}" var="lineItem" varStatus="lineItemCount">
                                                                            <c:set var="isItemCodeVisible" value="${reportingGroups == '0' ? true : reportingGroups.contains(lineItem.reportingGroupCode)}" />
                                                                            <c:set var="allowedOwnerCode" value="${allReportingGroups.contains(lineItem.reportingGroupName) ? true : false}" />
                                                                            <c:set var="shortAuthor" value="${lineItem.shortAuthor}"></c:set>
																			<jsp:useBean id="shortAuthor" type="java.lang.String" />
																			   <%
																					int authorMaxLength = ApplicationConstant.AUTHOR_SEARCH_MAX_LENGTH;
																					if(shortAuthor == null || shortAuthor.equalsIgnoreCase("null") || shortAuthor.length() == 0) {
																						shortAuthor = "";
																					}
																					if(shortAuthor.length() != 0 && shortAuthor.length() > authorMaxLength) {
																						shortAuthor = shortAuthor.substring(0,authorMaxLength);
																					}
																					
																				%>
                                                                            <tr class="${lineItem.shipmentLineId eq -11 ? 'hidden' : '' } ${lineItem.shipmentLineId eq -11 ? lineItem.orderLineId : '' }">
                                                                                <th scope="row">
                                                                                    <c:if test="${lineItem.shipmentLineId ne -11}">
                                                                                        ${rowCount}
                                                                                        <c:set scope="request" var="rowCount" value="${rowCount + 1}" />
                                                                                        <c:set scope="request" var="total" value="${total + lineItem.shippedQuantity}" />
                                                                                    </c:if>
                                                                                </th>
                                                                                <td class="${isItemCodeVisible ? '' : 'hide-item'}">
                                                                                    <c:if test="${not allowedOwnerCode}">
                                                                                        <c:out value="${isItemCodeVisible ? lineItem.isbn : encryptedIsbn}" />
                                                                                    </c:if>
                                                                                    <c:if test="${allowedOwnerCode}">
                                                                                    	<c:choose>
																							<c:when test="${isItemCodeVisible}">
																							   <a href="<%=ApplicationConstant.VIEW_CATALOG_BOOK_URL %>${lineItem.isbn}"><c:out value="${lineItem.isbn}" /></a>
																							</c:when>
																							<c:otherwise>
																							  <b><c:out value="${encryptedIsbn}" /></b>
																							</c:otherwise>
																						</c:choose>
                                                                                    </c:if>
                                                                                    &nbsp;
                                                                                    <c:if test="${lineItem.shipmentLineId eq -1}">
                                                                                        <a data-toggle="collapse" href="javascript:void(0)" class="${lineItem.orderLineId}" onclick="openLineItems(${lineItem.orderLineId});" aria-expanded="false" aria-controls="secitem">
                                                                                            <i class="caret" style="position: absolute; margin-top: 8px;"></i>
                                                                                        </a>
                                                                                    </c:if>
                                                                                </td>
                                                                                <td class="${isItemCodeVisible ? '' : 'hide-item'}">
                                                                                    <c:if test="${not allowedOwnerCode}">
                                                                                        <c:out value="${isItemCodeVisible ? lineItem.shortTitle : encryptedTitle}" />
                                                                                    </c:if>
                                                                                    <c:if test="${allowedOwnerCode}">
                                                                                         <c:choose>
																							<c:when test="${isItemCodeVisible}">
																							   <a href="<%=ApplicationConstant.VIEW_CATALOG_BOOK_URL %>${lineItem.isbn}"><c:out value="${lineItem.shortTitle}" /></a>
																							</c:when>
																							<c:otherwise>
																							  <b><c:out value="${encryptedTitle}" /></b>
																							</c:otherwise>
																						</c:choose>
                                                                                    </c:if>
                                                                                </td>
                                                                                 <td class="${isItemCodeVisible ? '' : 'hide-item'}">
                                                                                            <c:if test="${not allowedOwnerCode}">
                                                                                                <c:out value="${isItemCodeVisible ? lineItem.shortAuthor : encryptedAuthor}" />
                                                                                            </c:if>
                                                                                            <c:if test="${allowedOwnerCode}">
		                                                                                             <c:choose>
		   																					 <c:when test="${isItemCodeVisible}">
		                                                                                         <portlet:actionURL name="getSearchByShortAuthor" var="getSearchByShortAuthorURL">
																									<portlet:param name="search-catalogs" value="true" />
																									<portlet:param name="catalog-search-param" value="catalogs" />
																									<portlet:param name="viewId" value="search-catalogs" />
																									<portlet:param name="catalog_author" value="<%=shortAuthor%>" />
																								</portlet:actionURL>
		                                                                                              <a href="<%=getSearchByShortAuthorURL%>">${lineItem.shortAuthor} </a>
																							 </c:when>
																			    				<c:otherwise>
																			        				<a href="javascript:void(0)" >"${encryptedAuthor}"</a>
																			    				</c:otherwise>
																			    			  </c:choose>
	                                                                                      </c:if>
                                                                                </td>
                                                                                <td>${lineItem.orderQuantity}</td>
                                                                                <td>${lineItem.shippedQuantity}</td>
                                                                                <td>${lineItem.pubStatusCode}</td>
                                                                                <td class="${isItemCodeVisible ? '' : 'hide-item'}">
                                                                                    <c:choose>
                                                                                        <c:when test="${not empty fn:trim(lineItem.msrp) and fn:trim(lineItem.msrp) ne '$' }">
                                                                                            <c:out value="${isItemCodeVisible ? lineItem.msrp : encryptedMsrp}" />
                                                                                        </c:when>
                                                                                        <c:otherwise>N/A</c:otherwise>
                                                                                    </c:choose>
                                                                                </td>
                                                                            </tr>
                                                                            <c:if test="${lineItemCount.last }">
                                                                                <tr class="total-row rep-user-total">
                                                                                    <td>&nbsp;</td>
                                                                                    <td colspan="4">
                                                                                    <c:if test="${isRepUser}">
											                                              <%=ApplicationConstant.SALES_REPRESENTATIVE %>
											                                        </c:if>
                                                                                     Total: 
                                                                                    </td>
		                                        									<td style="text-align: center;">
                                                                                    ${total} 
                                                                                    </td>
                                                                                    <td>&nbsp;</td>
																					<td>&nbsp;</td>
																					<!-- <td>&nbsp;</td> -->
																				</tr>
                                                                            </c:if>   
                                                                        </c:forEach>
                                                                    </tbody>
                                                                </table>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <div class="col-sm-12">
                                                                    <br> <b>Sorry, Information is not available</b>
                                                                </div>
                                                                <div class="clearfix"></div>
                                                            </c:otherwise>
                                                        </c:choose>
                                                        </div>



                                           			</div>
                                           			<div class="space"></div>
	                                                </c:forEach>
                                                </c:forEach>
                                        </div>
                                     </c:if>
								  </div> 
								  <c:if test="${!empty statusData.shipments and empty statusData.orderLines}">
                                     <c:if test="${isRepUser}">
						    	        <p><b><%=ApplicationConstant.SALES_REPRESENTATIVE_DISTRIBUTION_CLIENTS %> <a href="mailto:<%=ApplicationConstant.MAIL_TO_INVOICES_HBG%>"><%=ApplicationConstant.MAIL_TO_INVOICES_HBG%></a>. </b></p>
						             </c:if>
							      </c:if>
								  <!--End: Shipment data inside -->
								  
								  <c:if test="${!empty statusData.orderLines and callingPage ne 'invoice'}">
							      <div class="order-status-rows">  <!-- Start : Orderlines by status -->
							         <c:forEach var="entry" items="${statusData.orderLines}">
						              <div class="row">
						                <div class="col-md-12">
						                    <div class="recent-orders shipping-status-table" id="${fn:replace(entry.name,' ', '')}">
						                        <div class="all-shipments-bar">
						                            <div class="row">
						                                <div class="col-sm-8">
						                                <c:choose>
						                                  <c:when test="${entry.name == 'Action Required'}">
						                                   	 <h3>${entry.name}&nbsp;<small>Please contact your financial services representative.</small></h3>
						                                  </c:when>
						                                  <c:otherwise>
						                                  	 <h3>${entry.name}</h3>
						                                  </c:otherwise>
						                                </c:choose>
						                                </div>  
						                                <div class="col-sm-4 text-right">
				                                			<span class="right-link"> 
				                                				<span><a href="/web/guest/faqs">Read FAQs about this status</a></span>
				                                				<i class="fa fa-question-circle"></i>
				                                			</span>
				                                		</div>
						                            </div>
						                        </div>
						                        <div id="order-overview1">
						                            <div class="inline_table">
						                                <table class="table">
							                                    <thead>
							                                        <tr>
							                                            <th>#</th>
							                                            <th>Item Code<br> (ISBN/EAN)</th>
							                                            <th>Title</th>
																		<th class="long_title">Author(s)</th>
							                                            <th>Order<br>Quantity</th>
							                                            <th>Pub Status</th>
							                                            <th class="status_details">Status Details</th>
							                                            <th>*MSRP</th>
							                                        </tr>
							                                    </thead>
						                                    <tbody>
						                                    	<c:set value="${entry.lines}" scope="request" var="orderLines" />
						                                    	<c:set scope="request" var="orderDetailPageByStatus" value="true" />
						                                    	<c:set scope="request" var="total" value="0" />
						                                    	<c:forEach items="${orderLines}" var="orderLineQuantity">
																   <c:set scope="request" value="${total + orderLineQuantity.orderQuantity }" var="total"></c:set>
																</c:forEach>
																<jsp:include page="/WEB-INF/views/orderline_template.jsp"/>
						                                    </tbody>
						                                </table>
						                            </div>
						                        </div>
						                    </div>
						                </div>
						            </div>
							        </c:forEach>
								    <p>
								    	<b>*Prices subject to change without notice.</b>
								    </p>
								    <c:if test="${isRepUser}">
							          <p><b><%=ApplicationConstant.SALES_REPRESENTATIVE_DISTRIBUTION_CLIENTS %> <a href="mailto:<%=ApplicationConstant.MAIL_TO_INVOICES_HBG%>"><%=ApplicationConstant.MAIL_TO_INVOICES_HBG%></a>. </b></p>
							       </c:if>
								</div>  <!-- End : order-status-rows div -->
								</c:if>
								
						</div> <!-- End : tab pane div -->
					</div>  <!-- End : tab content div -->
             	</div>  <!-- End : order-overview div -->
	        </div> <!-- End : recent-orders  div -->
		</div>	
	</div>	
  </div> <!-- end of container -->
</div> <!-- end of main_wrapper -->

<script src="<%=request.getContextPath()%>/js/order_detail.js?t=<%=timestamp%>"
	portlet_namespace="<portlet:namespace/>"
	zendesk_url="<%=initiateZendeskTicketURL %>" reference_number="${refNo}" type="text/javascript"></script>
	
<script src="<%=request.getContextPath()%>/js/clear_form.js?t=<%=timestamp%>"
	type="text/javascript"></script>
	
<script>

$('body').on('click','.lineShipmentItemsLink',function(event){
	var count = event.currentTarget.lastElementChild.getAttribute('id');
	if($('#show-line-items'+count).hasClass('hidden')){
    	$('#show-line-items'+count).removeClass('hidden');
    	$('#show-line-items'+count).prev().find('.caret').addClass('rotate-icon');
    }else{
    	$('#show-line-items'+count).addClass('hidden');
    	$('#show-line-items'+count).prev().find('.caret').removeClass('rotate-icon');
    }
});


function loadTrackingURL(context,carrierId,trackingNo,orderHeaderId, invoiceNumber,shipDate,shipmentHeaderId){
	if(!carrierId) {
	   	return false;
 	}
	var Shipment_URL='<%=shipmentTrackingDataURL%>';
	var PORTLET_NAMESPACE = "<portlet:namespace/>";

	AUI().use("aui-base", "aui-io-request-deprecated", function(A) {
	    A.io.request(Shipment_URL, {
			dataType : "jsonp",
			cache: false,
			method : "get",
			data : {
				 'carrier' : carrierId,
				 'trackingNo' : trackingNo,
				 'orderHeaderId' : orderHeaderId,
				 'shipDate' : shipDate
		    },
		    on : {
			  success : function() {
					resp = JSON.parse(this.get("responseData"));
					console.log("Load Tracking URL >>>>>resp>>>>>>> ", resp);
					if (resp == undefined || resp == null || !resp)
						return false;
					var isUrlValid = true;
					var suffixedId = '_'+orderHeaderId+'_'+invoiceNumber+'_'+shipmentHeaderId;
					var trackingData = resp.trackingURL;
					//console.log('trackingData:'+trackingData);
					if (trackingData == 'null' || trackingData == undefined
							|| trackingData.trim() == null || trackingData.trim().length == 0) {
						isUrlValid = false;
					} else if (trackingData && trackingNo) {
						$('#'+trackingNo+suffixedId).prop('href',trackingData);
						$('#'+trackingNo+suffixedId).prop('target','_blank');
						//console.log('Def:'+trackingData +','+ trackingNo +','+ trackingData[1]);
					} else {
						isUrlValid = false;
					}

					// Based on above block, below code will disable the Anchor tag.
					if(!isUrlValid) {
						// Remove the link in this case.
						//console.log('Url invalid case:Tracking information is unavailable at this time');
						$('#'+trackingNo + suffixedId).replaceWith(function() {
					    	return $("<b id='"+$(this).attr('id')+"' title='Tracking information is unavailable at this time'>"
					        	+ $(this).html() +"</b>");
					    });
					}
				}
		    }
		});
	});
 }

function searchByShortAuthor(formId){
	document.getElementById(formId).submit();
}

</script>
