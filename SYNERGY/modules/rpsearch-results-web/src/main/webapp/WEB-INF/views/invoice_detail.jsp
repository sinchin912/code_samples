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
<%@page import="com.hbg.rp.model.ClaimHeader"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayPortletContext"%>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" type="text/css" />
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.hbg.rp.search.model.InvoiceDTO"%>
<%@page import="java.util.Map"%>
<%@page import="com.hbg.rp.search.util.IPortalMappingsUtil"%>
<%@page import="com.hbg.rp.model.OrderLine"%>
<%@page import="com.hbg.rp.search.util.PortalMappingsUtil"%>
<%@page import="com.hbg.rp.search.constants.ApplicationConstant"%>
<%@page import="com.hbg.rp.search.util.CommonUtil"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ include file="init.jsp" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="com.liferay.portal.kernel.model.Role"%>
<%@page import="java.util.List"%>
<%@page import="javax.portlet.PortletSession"%>

<%
	LiferayPortletContext context = (LiferayPortletContext) portletConfig.getPortletContext();
	long timestamp = context.getPortlet().getTimestamp();
    Boolean showZendeskFlag = (Boolean) themeDisplay.getUser().getExpandoBridge().getAttribute("showZendeskFlag");
	String pageName = themeDisplay.getLayout().getName(themeDisplay.getLocale());
	IPortalMappingsUtil portalMappingsUtil = (IPortalMappingsUtil)request.getAttribute("portalMappingsUtil");
	Map<String, String> addressFieldsMap = portalMappingsUtil.getHBGAddressMap();
	InvoiceDTO invoiceDTO = (InvoiceDTO) request.getAttribute("data");
	String orderCallingPage = (String) request.getAttribute("orderCallingPage");
	String claimCallingPage = (String) request.getAttribute("claimCallingPage");
	String invoiceCategoryMapping = "";
	if (invoiceDTO.getInvoiceHeader() != null) {
		invoiceCategoryMapping = portalMappingsUtil.getFinancialTransactionType(invoiceDTO.getInvoiceHeader().getInvoiceCategory());
	}
	if (StringUtils.isEmpty(invoiceCategoryMapping) && invoiceDTO.getInvoiceHeader() != null) { // In case the map returns null, then use invoice category description as it ease.
		invoiceCategoryMapping = invoiceDTO.getInvoiceHeader().getInvoiceCategoryDesc();
	}
	ClaimHeader claimHeader = ((ClaimHeader) pageContext.getAttribute("claimHeader"));
	Boolean showClaimsFlag = (Boolean) themeDisplay.getUser().getExpandoBridge().getAttribute("showClaimsFlag");
		CommonUtil commonUtil = new CommonUtil();
        String allReportingGroups = commonUtil.getAllReportingGroups();
        String reportingGroups = commonUtil.getPermittedReportingGroups(renderRequest);
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
<c:set value="<%=addressFieldsMap %>" var="addressFieldsMap" />
<c:set value="<%=invoiceCategoryMapping %>" var="invoiceCategoryMapping" />
<c:set value="<%=orderCallingPage %>" var="orderCallingPage" />
<c:set value="<%=claimCallingPage %>" var="claimCallingPage" />
<c:set var="isRepUser" value="<%=isRepUser%>" />
<c:set var="encryptedAmount" value="<%=ApplicationConstant.ENCRYPTED_AMOUNT %>" />


<c:forEach var="role" items="<%=loggedInUserRoles%>">
	<c:set var="userRole" value="${role.getName()}"></c:set>
	<jsp:useBean id="userRole" type="java.lang.String" />

	<c:if test='<%=userRole.equalsIgnoreCase("all")%>'>
       	<c:set var="ALL" value="true"></c:set>
    </c:if>

	<c:if test='<%=userRole.equalsIgnoreCase(ApplicationConstant.NRP_RETRIEVE_INVOICE_PDF)%>'>
		<c:set var="NRP_RETRIEVE_INVOICE_PDF" value="true"></c:set>
	</c:if>

	<c:if test='<%=userRole.equalsIgnoreCase(ApplicationConstant.NRP_TRACK_CLAIMS)%>'>
		<c:set var="NRP_TRACK_CLAIMS" value="true"></c:set>
	</c:if>
	
	<c:if test='<%=userRole.equalsIgnoreCase(ApplicationConstant.NRP_SUBMIT_CLAIMS)%>'>
		<c:set var="NRP_SUBMIT_CLAIMS" value="true"></c:set>
	</c:if>
</c:forEach>

<portlet:resourceURL id="initiateZendeskTicket" var="initiateZendeskTicketURL"> </portlet:resourceURL>
<c:set var="invoiceno" value="${data.invoiceHeader.invoiceNumber }" scope="page" />
<portlet:actionURL var="getCreditMemos" name="getMemosAction">
	<portlet:param name="<%=ApplicationConstant.INV_INVOICE_TYPE %>" 
		value="<%=ApplicationConstant.INV_INVOICE_TYPE_CREDIT %>" />
	<portlet:param name="<%=ApplicationConstant.INV_INVOICE_NBR %>" 
		value="${invoiceno}" />
</portlet:actionURL>
<portlet:actionURL  var="getDebitMemos" name="getMemosAction">
	<portlet:param name="<%=ApplicationConstant.INV_INVOICE_TYPE %>" 
		value="<%=ApplicationConstant.INV_INVOICE_TYPE_DEBIT %>" />
	<portlet:param name="<%=ApplicationConstant.INV_INVOICE_NBR %>" 
		value="${invoiceno}" />
</portlet:actionURL>
<portlet:resourceURL id="getInvoiceHierarchyData" var="getInvoiceHierarchyData"> </portlet:resourceURL>
<portlet:renderURL var="getDetails">
	<portlet:param name="action" value="getInvoiceDetail" />
</portlet:renderURL>
<portlet:actionURL var="trackingDataURL" name="getShipmentTracking"></portlet:actionURL>
<portlet:actionURL var="lineItemsURL" name="getLineItemsForShipment"></portlet:actionURL>
<portlet:actionURL var="orderStatusDataURL" name="getOrderStatusData">
	<portlet:param name="<%=ApplicationConstant.INV_INVOICE_NBR %>"
		value="${invoiceno}" />
	<portlet:param name="callingPage" value="invoice" />
</portlet:actionURL>
<portlet:resourceURL var="exportToExcel" id="exportToExcel">
	   <portlet:param name="exportOption" value="invoiceDetailsExport${invoiceno}" />
</portlet:resourceURL>
<portlet:resourceURL var="exportToShipmentExcel" id="exportToExcel">
	   <portlet:param name="exportOption" value="shipmentDetailsExport${data.invoiceHeader.referenceNumber}" />
</portlet:resourceURL>
<div style="display:none;" id="loader-wrapper"><div id="loader"></div></div>
<div class="modal fade" id="cancelConfirmationModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog claim-modal">
		<div class="modal-content">
			<div class="modal-body">
				<div class="text-center">
					<h3 class="my-3">
						<!-- <i class="fa fa-check fa-exclamation-circle text-warning" aria-hidden="true"></i>
						<span> Alert </span> -->
					</h3>
					<input type="hidden" id="cancelClaimHeaderKey" name="cancelClaimHeaderKey" value=""/>
					<h5 class="text-center font-merriweather">Are you sure you want to cancel this claim?</h5>
					<a class="btn btn-theme search-btn mt-1 btn-ok openCancelClaim text-white" id="btnCancelClaim">Yes</a>
					<a class="btn btn-theme-gray search-btn mt-1 btn-ok text-white" style="margin-left: 5px;" data-dismiss="modal">No</a>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="cancelClainSuccessModalData" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog claim-modal">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-body">
				<div class="text-center">
					<h4 class="my-3 font-merriweather">
						<i class="fa fa-check check-success" aria-hidden="true"></i>
						<span> Claim Cancel Request Submitted Successfully </span>
					</h4>
					<a class="btn btn-theme search-btn mt-1 btn-ok successCancelClaim text-white">Ok</a>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="cancelClainErroModalData" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog claim-modal">
		<div class="modal-content">
			<div class="modal-body">
				<div class="text-center">
					<h3 class="my-3 font-merriweather">
						<i class="fa fa-exclamation-circle text-warning" aria-hidden="true"></i>
						<span> Error </span>
					</h3>
					<h5 class="text-center" id="errorClaimMsg"></h5>
					<button type="button" class="btn btn-theme search-btn mt-1 btn-ok errorCancelClaim  text-white">Ok</button>
				</div>
			</div>
		</div>
	</div>
</div>
<portlet:resourceURL id="getShipmentTrackingData" var="shipmentTrackingDataURL"> </portlet:resourceURL>
					
<div class="tracking-data-form">
	<springfm:form name="trackingDataForm" id="tracking-data-form" action="<%=trackingDataURL%>" >
	</springfm:form>
</div>
<div class="view-line-items-form">
	<aui:form name="lineItemsForm" id="view-line-items-form" action="<%=lineItemsURL%>">
		
	</aui:form>
</div>
<input type="hidden" id="shipmentReloadTime"
	value="<%=ApplicationConstant.SHIPMENT_RELOAD_TIME_MILLIS %>" />
<input type="hidden" id="shipmentReloadCount"
	value="<%=ApplicationConstant.SHIPMENT_RELOAD_COUNT %>" />
<c:choose>
<c:when test="${empty data.invoiceHeader }">
	Sorry, Information is not available.
</c:when>
<c:otherwise>
<c:set var="rowInvType" value="${data.invoiceHeader.invoiceType == 'DBN' ? 'Debit' : (data.invoiceHeader.invoiceType == 'CRN' ? 'Credit' : 'Invoice')}"></c:set>
<!--======= .Wrapper =========-->
<div class="main_wrapper" style="margin: 30px auto;">
    <div class="container">
        <!-- .order-details -->
        <div class="row">
            <div class="col-md-12">
                <div class="recent-orders order-details">
                   <div class="row headding-row" id="heading-part">
                        <div class="col-md-8">
                            <h2 class="table_hedding">
                            	<c:choose>
                            		<c:when test="${not empty data.orderDTO.orderHeader }">
	                            		<portlet:renderURL var="getOrderDetail">
											<portlet:param name="action" value="getOrderDetail" />
											<portlet:param name="refno" value="${data.invoiceHeader.referenceNumber}" />
										</portlet:renderURL>
                            			Order <span> <a href="<%=getOrderDetail %>">${data.invoiceHeader.referenceNumber} </a> </span> 
                                		> Invoices, Credits and Debits
                            		</c:when>
                            		<c:when test="${rowInvType eq 'Invoice' }">
                            			${invoiceCategoryMapping } <span> <b> ${invoiceno} </b> </span>
                            		</c:when>
                            		<c:otherwise>
                            			${invoiceCategoryMapping } Memo <span> <b> ${invoiceno} </b> </span>
                            		</c:otherwise>
                            	</c:choose>
                            </h2>
                        </div>
                        <div class="col-md-4 text-right">
                            <a class="btn btn-theme" href="<%=exportToExcel %>">
								<i class="fa fa-external-link-square"></i>
								Export Invoice XLS
							</a>
                        </div> 
                    </div>
				<div id="summary-part">
					<c:set value="${data}" scope="request" var="data" />
					<c:if test="${not empty data.orderDTO.orderHeader }">
						<jsp:include page="/WEB-INF/views/order_summary_template.jsp"/>
	                </c:if>
					<jsp:include page="/WEB-INF/views/invoice_header_template.jsp"></jsp:include>
				</div>
				<div class="row"> 
				<!-- All Related Transactions -->
					<div class="col-sm-12"> <h4> All Related Transactions </h4> </div>
					</div>
					<%-- <div class="col-sm-12">
						${rowInvType} ${invoiceno}
					</div> --%>
					<div class="row">
                        <div class="col-md-12">
                            <div class="hierarchical-tree-view">
                                <ul class="nav nav-list" id="all-related-transactions" data-bind="${invoiceno}">
                                </ul>
                                <div style="clear: both;">
                            </div>
                        </div>
                    </div>
				</div>
		<div class="row">
           <div class="col-md-12">
			<div id="detail-part"> 
				<!--  INVOICE - TAB CODE: STARTED -->
				<c:choose>
					<c:when test="${rowInvType eq 'Invoice' }">
	                    <div id="invoice-detail" class="order_overview transaction-detail">
	                        <!-- Nav tabs -->
	                        <div class="">
	                        	<div class="" style="padding-right: 0px;">
								<%-- <c:choose>
									<c:when test="${!empty statusData.orderLines || !empty statusData.shipments}">
										<c:set var="invoiceShipmentActive" value="active" />
									</c:when>
									<c:otherwise>
										<c:set var="invoiceItemsActive" value="active" />
									</c:otherwise>
								</c:choose> --%>
		                        	<ul class="nav nav-tabs" role="tablist" id="customInvoiceTab">
			                            <li role="presentation" class="active">
			                                <a href="#invoice-items" aria-controls="invoice-items" role="tab" data-toggle="tab">Invoice Items</a>
			                            </li>
			                            <c:choose>
											<c:when test="${data.hasShipments}">
												<li role="presentation" class="">
													<a href="#view-order-by-status"
													aria-controls="view-order-by-status" role="tab"
													data-toggle="tab"
													class="view-order-status">View Shipment</a>
												</li>
			                            	</c:when>
										</c:choose>
			                            <li class="download-link active-link ml-auto">
								 			<c:if test="${(not empty ALL && ALL) || (not empty NRP_RETRIEVE_INVOICE_PDF && NRP_RETRIEVE_INVOICE_PDF)}">
				                            	<portlet:renderURL var="getPdfConduent" windowState="<%=LiferayWindowState.MAXIMIZED.toString()%>">
													<portlet:param name="action" value="getPdfConduent" />
													<portlet:param name="<%=ApplicationConstant.INV_INVOICE_NBR %>" value="${invoiceno}" />
												</portlet:renderURL>
				                              	
				                              	<a href="<%=getPdfConduent %>" target="_blank">
				                              		<i class="fa fa-download"></i> Download Invoice ${invoiceno} PDF
				                              	</a>
	                              	 	   </c:if>
			                            </li>
			                            <c:choose>
											<c:when test="${data.hasShipments}">
												<li class="download-link active-link export-link" style="display:none">			                            		                            			                            
						                            <a href="<%=exportToShipmentExcel %>">
														<i class="fa fa-external-link-square"></i>
														Export Shipment XLS
													</a>
				                           	 	</li>
				                            </c:when>
										</c:choose>
			                        </ul>
		                        </div>
	                        </div>
	                        
	                        <!-- Tab panes -->
	                        <div class="tab-content">
	                            <div role="tabpanel" class="tab-pane active" id="invoice-items">
	                                <div class="table_outer">
	                                    <div class="inline_table">
	                                    	<table class="table">
			                                    <thead>
			                                        <tr>
			                                            <th>Item Code<br> (ISBN/EAN)</th>
			                                            <th>Title</th>
														<th class="long_title">Author(s)</th>
			                                            <th>Quantity Shipped</th>
			                                            <th>Unit Price</th>
			                                            <th>Discount %</th>
			                                            <th>Net Charge</th>
			                                        </tr>
			                                    </thead>
			                                    <tbody>
			                                    	<%-- <c:set value="${data.invoiceLines}" scope="request" var="invoiceLines" /> --%>
			                                    	<c:set value="${data}" scope="request" var="data" />
													<jsp:include page="/WEB-INF/views/invoiceline_template.jsp"></jsp:include>
													<c:set scope="request" value="0" var="totalQty"></c:set>
													<c:set scope="request" value="0" var="totalNetCharge"></c:set>
													<c:forEach items="${data.invoiceLines }" var="invLine">
														<c:if test="${invLine.lineStatus eq 'Fulfilled' }">
															<c:set scope="request" value="${totalQty + invLine.quantity }" var="totalQty"></c:set>
															<c:set scope="request" value="${totalNetCharge + invLine.lineAmount }" var="totalNetCharge"></c:set>
														</c:if>
													</c:forEach>
													<tr class="total-row invoice-total rep-invoice-total">
		                                                    <td>&nbsp;</td>
		                                                    <td colspan="2" style="text-align: right;font-weight: bold;">
			                                                    <c:if test="${isRepUser}">
						                                              <%=ApplicationConstant.SALES_REPRESENTATIVE %>
						                                        </c:if>
			                                                    Total Shipped: 
			                                                </td>
			                                                 <td><b>${totalQty}</b></td>
			                                                 <td colspan="3">&nbsp; </td>
		                                            </tr>
	                                                <tr class="invoice-total-row">
		                                                <td colspan="3">
	                                                    	<c:if test="${not empty data.invoiceComments }">
	                                                       		<p>Comments:</p>
	                                                      		<c:forEach var="comment" items="${data.invoiceComments }">
	                                                        		<p> ${comment } </p>
	                                                        	</c:forEach>
	                                                    	</c:if>
		                                                </td>
	                                                    <td colspan="4">
	                                                        <c:if test="${data.invoiceHeader.totalFreightAmt > 0 }">
	                                                        	<p>Shipping Charge: 
	                                                        	<c:choose>
																    <c:when test="${isRepUser}">
																		 <span style="font-style: italic;font-weight:bold"><c:out value="${encryptedAmount}" /></span>
																    </c:when>
																    <c:otherwise>
																		$<fmt:formatNumber pattern="#,##0.00;-#,##0.00" type="currency" minFractionDigits="2" 
						                                                                   maxFractionDigits="2" value="${data.invoiceHeader.totalFreightAmt }" />
																    </c:otherwise>
														        </c:choose>
	                                                        	</p>
	                                                        </c:if>
	                                                        <c:if test="${data.invoiceHeader.totalTaxAmt > 0 }">
	                                                        	<p>Sales Tax: 
	                                                        	<c:choose>
																    <c:when test="${isRepUser}">
																		 <span style="font-style: italic;font-weight:bold"><c:out value="${encryptedAmount}" /></span>
																    </c:when>
																    <c:otherwise>
																		$<fmt:formatNumber pattern="#,##0.00;-#,##0.00" type="currency" minFractionDigits="2" 
	                                                        		                       maxFractionDigits="2" value="${data.invoiceHeader.totalTaxAmt }" />
																    </c:otherwise>
														        </c:choose>
	                                                           </p>
	                                                        </c:if>
	                                                        <c:if test="${data.invoiceHeader.totalHandlingAmt > 0 }">
	                                                        	<p>Handling Charge: 
	                                                        	<c:choose>
																    <c:when test="${isRepUser}">
																		<span style="font-style: italic;font-weight:bold"><c:out value="${encryptedAmount}" /></span>
																    </c:when>
																    <c:otherwise>
																		$<fmt:formatNumber pattern="#,##0.00;-#,##0.00" type="currency" minFractionDigits="2" 
	                                                        		                       maxFractionDigits="2" value="${data.invoiceHeader.totalHandlingAmt }" /> 
																    </c:otherwise>
														        </c:choose>
	                                                           </p>
	                                                        </c:if>
	                                                        <c:if test="${data.invoiceHeader.prepaidAmt > 0 }">
	                                                        	<p>Pre-paid Amount: 
	                                                        	<c:choose>
																    <c:when test="${isRepUser}">
																		 <span style="font-style: italic;font-weight:bold"><c:out value="${encryptedAmount}" /></span>
																    </c:when>
																    <c:otherwise>
																		$<fmt:formatNumber pattern="#,##0.00;-#,##0.00" type="currency" minFractionDigits="2" 
	                                                        		                       maxFractionDigits="2" value="${data.invoiceHeader.prepaidAmt }" /> 
																    </c:otherwise>
														        </c:choose>
	                                                        	</p>
	                                                        </c:if>
	                                                        <p><b style="text-align: right;"> 
	                                                        	  <c:choose>
																	    <c:when test="${isRepUser}">
																			Total Net Charge(Tax Not Included): $<fmt:formatNumber pattern="#,##0.00;-#,##0.00" type="currency" minFractionDigits="2" 
		                                                        																   maxFractionDigits="2" value="${totalNetCharge}" /> 
																	    </c:when>
																	    <c:otherwise>
																			Total Net Charge: $<fmt:formatNumber pattern="#,##0.00;-#,##0.00" type="currency" minFractionDigits="2" 
		                                                        												 maxFractionDigits="2" value="${data.invoiceHeader.totalAmt }" />
																	    </c:otherwise>
														          </c:choose>
	                                                        </b></p>
	                                                    </td>
	                                                </tr>
			                                    </tbody>
			                                </table>
	                                    </div>
	                                </div>
	                                <!-- <div class="request-tracking-block">
	                                    <div class="inner-section">
	                                        <div class="row">
	                                            <div class="col-sm-6">
	                                                <h3>Start a Claim for Invoice <span> ? </span></h3>
	                                                <p>All claims must be made within ? days from invoice date. Prices subject
	                                                    to change without notice.</p>
	                                                <a class="view-claims-faq" data-attr="none" href="javascript:void(0)">View Claims FAQ
	                                                    <i class="fa fa-chevron-right" aria-hidden="true"></i>
	                                                </a>
	                                            </div>
	                                            <div class="col-sm-4 col-sm-offset-1">
	                                                <button type="button" class="btn btn-theme btn-block">Start a Claim</button>
	                                            </div>
	                                        </div>
	                                    </div>
	                                </div> -->
	                            </div><!-- End of Invoice-Items TAB -->
									<div role="tabpanel" class="tab-pane"
										id="view-order-by-status">
										
										<div class="table_outer">
											<!--Start: Shipment data inside -->

											<c:if test="${!empty statusData.shipments}">
												<div class="inline_table" id="Shipped">
													<div class="all-shipments-bar">
														<div class="row">
															<div class="col-sm-6">
															</div>
															<div class="col-sm-6 text-right">
																<span class="right-link"> <span><a
																		href="/web/guest/faqs">Read FAQs about this status</a></span>
																	<i class="fa fa-question-circle"></i>
																</span>
															</div>
														</div>
													</div>
													<c:forEach var="entry" items="${statusData.shipments}">
														<c:forEach var="shipment" items="${entry.shipments}"
															varStatus="count">
															<div class="table_outer shipment-blocks"
																id="Shipped-${shipment.invoiceNumber}">
																<header class="table_header">
																	<div class="row">
																		<div class="col-sm-6">
																			<span class="serial_number">${count.index +1}.</span>
																			<span class="headding">Shipment Summary</span>
																		</div>
																		<portlet:renderURL var="getInvoiceDetail">
																			<portlet:param name="action" value="getInvoiceDetail" />
																			<portlet:param
																				name="<%=ApplicationConstant.INV_INVOICE_NBR %>"
																				value="${shipment.invoiceNumber}" />
																		</portlet:renderURL>
																		<portlet:actionURL name="getSearchByShipGroup" var="getSearchByShipGroupURL">
																			<portlet:param name="shipGroup" value="${shipment.shipGroupId}" />
																			<portlet:param name="search-shipment" value="true" />
																			<portlet:param name="shipgroupno" value="${shipment.shipGroupId}" />
																			<portlet:param name="ship-shipGroup" value="${shipment.shipGroupId}" />
																		</portlet:actionURL>
																		
																		<div class="col-sm-6 text-right">
																			<span class="invoice-number">Invoice Number: <a
																				href="<%=getInvoiceDetail %>">
																					${shipment.invoiceNumber} </a>
																			</span>
																		</div>
																	</div>
																</header>
																<div class="shipment-status-step">
																	<c:set value="${shipment.trackingNumber}"
																		var="trackingNumber"></c:set>
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
																			  <a href="<%=getSearchByShipGroupURL%>"> <b>${shipment.shipGroupId}</b>
																				 <span id="shipgroupno" hidden>${shipment.shipGroupId}</span>
																			  </a>
																		</div>
																		<div class="col-sm-4 text-center">
																			<p>Carrier Tracking Number:</p>
																			<p class="trackingNumberClass">
																				<c:set var="carrierCode"
																					value="${shipment.carrierId}"></c:set>
																				<c:set var="shipmentCarrierDetails"
																					value="${statusData.supportedCarrierData[carrierCode]}"></c:set>
																				<c:set var="supportedTrackingType"></c:set>
																				<c:set var="suffixedId"
																					value="_${shipment.orderHeaderId}_${shipment.invoiceNumber}_${shipment.shipmentHeaderId}"></c:set>
																				<c:if
																					test="${not empty shipmentCarrierDetails && not empty shipmentCarrierDetails.supportedTrackingType}">
																					<c:set var="supportedTrackingType"
																						value="${shipmentCarrierDetails.supportedTrackingType}"></c:set>
																				</c:if>
																				<jsp:useBean id="now" class="java.util.Date" />
																				<fmt:parseNumber
																					value="${now.time / (1000*60*60*24) }"
																					integerOnly="true" var="nowDays" />
																				<fmt:parseNumber
																					value="${shipment.shipDate.time / (1000*60*60*24) }"
																					integerOnly="true" var="shipDateDays" />
																				<c:choose>
																					<c:when
																						test="${not empty supportedTrackingType && supportedTrackingType eq 'BOL'}">
																						<a
																							id="${shipment.billOfLadingNumber}${suffixedId}"
																							href="javascript:void(0);">${shipment.billOfLadingNumber}</a>
																						<c:set value="${shipment.billOfLadingNumber}"
																							var="trackingNumber"></c:set>
																					</c:when>
																					<c:when
																						test="${not empty supportedTrackingType && supportedTrackingType eq 'PRO'}">
																						<a id="${shipment.proNumber}${suffixedId}"
																							href="javascript:void(0);">${shipment.proNumber}</a>
																						<c:set value="${shipment.proNumber}"
																							var="trackingNumber"></c:set>
																					</c:when>
																					<c:otherwise>
																						<c:choose>
																							<c:when
																								test="${not empty shipment.trackingNumber}">
																								<a id="${shipment.trackingNumber}${suffixedId}"
																									href="javascript:void(0);">${shipment.trackingNumber}</a>
																							</c:when>
																							<c:when
																								test="${empty shipment.trackingNumber && not empty shipment.proNumber}">
																								<a id="${shipment.proNumber}${suffixedId}"
																									href="javascript:void(0);">${shipment.proNumber}</a>
																								<c:set value="${shipment.proNumber}"
																									var="trackingNumber"></c:set>
																							</c:when>
																							<c:when
																								test="${empty shipment.trackingNumber && empty shipment.proNumber && not empty shipment.billOfLadingNumber}">
																								<a
																									id="${shipment.billOfLadingNumber}${suffixedId}"
																									href="javascript:void(0);">${shipment.billOfLadingNumber}</a>
																								<c:set value="${shipment.billOfLadingNumber}"
																									var="trackingNumber"></c:set>
																							</c:when>
																							<c:when
																								test="${empty shipment.trackingNumber && empty shipment.proNumber &&  empty shipment.billOfLadingNumber && ((nowDays-shipDateDays)<120) }">
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
																	</div>
																	<!--  END : row data-row div -->
																	<div class="statusDiv"
																		id="<c:out value='zendeskParam${shipment.shipmentHeaderId}'/>">
															
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
																		<input type="hidden"
																			value="${shipment.referenceNumber}" id="refNo" /> <input
																			type="hidden" value="${shipment.poNbr}" id="poNo" />
																		<input type="hidden" value="${shipment.invoiceNumber}"
																			id="invoiceNo" /> <input type="hidden"
																			value="${shipment.shipGroupId}" id="shipGroup" /> <input
																			type="hidden" value="${shipment.carrierName}"
																			id="shipment" /> <input type="hidden"
																			value="${shipment.shipmentType}" id="shipmentMethod" />
																		<input type="hidden"
																			value="${shipment.shipmentHeaderId}"
																			id="shipmentHeaderId" /> <input type="hidden"
																			value="${shipment.accountName}" id="accountName" /> <input
																			type="hidden" value="${shipment.accountNumber}"
																			id="accountNumber" /> <input type="hidden"
																			value="${shipment.orderRecievedDate}"
																			id="orderRcvdDate" /> <input type="hidden"
																			value="${shipment.shipDate}" id="shippDate" />
																	</div>
																</div>
																<!-- END : shipment-status-step div -->
															</div>
															<!--  END : table_outer div -->
															<div class="table_accourdian">
																<a href="javascript:void(0)"
																	data-bind="${shipment.shipmentHeaderId}"
																	class="shipmentlineItemsLink">view line items <i
																	class="caret"></i>
																<div id="${count.index}"
																		data-bind="${shipment.shipmentHeaderId}"></div></a>
															</div>
															<div id="show-line-items${count.index}"
																class="inline_table hidden">
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
		                                                                                                <a href="<%=getSearchByShortAuthorURL%>" >${lineItem.shortAuthor} </a>
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
														</c:forEach>
													</c:forEach>
												</div>
											</c:if>
										</div>
										<!--End: Shipment data inside -->
									</div>
								</div>
								<div class="table_outer">
									<!-- For Invoice detail data -->
		                        <div class="body_content">
		                            <div class="row">
		                                <div class="col-sm-3">
		                                    <div class="table_content">
		                                        <table class="table">
		                                            <tbody>
		                                                <tr>
		                                                    <td>Invoice date:
		                                                        <b>
		                                                        	<fmt:parseDate var="invoiceDate" value="${data.invoiceHeader.invoiceDate}" pattern="yyyy-MM-dd" />
																	<fmt:formatDate value="${invoiceDate}" pattern="M/d/yy" />		
																</b>
		                                                    </td>
		                                                </tr>
		                                                <tr>
		                                                    <td>Total Amount:
		                                                    	<c:choose>
																	<c:when test="${isRepUser}">
																	   <b style="font-style: italic;"><c:out value="${encryptedAmount}" /></b>
																	</c:when>
																	<c:otherwise>
																		<b>$<fmt:formatNumber pattern="#,##0.00;-#,##0.00" type="currency" minFractionDigits="2" 
	                                                        				maxFractionDigits="2" value="${data.invoiceHeader.totalAmt }" /> </b>
																	</c:otherwise>
																</c:choose>
		                                                    </td>
		                                                </tr>
		                                                <tr>
		                                                    <td>Pre-paid Amount:
		                                                    	<c:choose>
																	<c:when test="${isRepUser}">
																	   <b style="font-style: italic;"><c:out value="${encryptedAmount}" /></b>
																	</c:when>
																	<c:otherwise>
																	   <b>$<fmt:formatNumber pattern="#,##0.00;-#,##0.00" type="currency" minFractionDigits="2" 
	                                                        				maxFractionDigits="2" value="${data.invoiceHeader.prepaidAmt }" /> </b>
																	</c:otherwise>
																</c:choose>
		                                                    </td>
		                                                </tr>
		                                                <tr>
		                                                    <td>Amount Due:
		                                                        <c:choose>
																	<c:when test="${isRepUser}">
																	   <b style="font-style: italic;"><c:out value="${encryptedAmount}" /></b>
																	</c:when>
																	<c:otherwise>
																	   <b>$<fmt:formatNumber pattern="#,##0.00;-#,##0.00" type="currency" minFractionDigits="2" 
                                                        		           maxFractionDigits="2" value="${data.invoiceHeader.totalAmt - data.invoiceHeader.prepaidAmt }" /></b>
																	</c:otherwise>
																</c:choose>
		                                                    </td>
		                                                </tr>
		                                                <tr>
		                                                	<c:if test="${ not empty data.invoiceHeader.termCode && fn:trim(data.invoiceHeader.termCode) != '' 
		                                                		&& fn:trim(data.invoiceHeader.termCode) != '0'}">
 																<td>Terms:
			                                                        <b>NET ${data.invoiceHeader.termCode} Days</b>
			                                                    </td>
		                                                	</c:if>
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
		                                                    <td>
		                                                        <b>Please send remittance to:</b>
		                                                    </td>
		                                                </tr>
		                                                <tr>
		                                                    <td> <c:out value="${addressFieldsMap['hachette_address_name']}"/> </td>
		                                                </tr>
		                                                <tr>
		                                                    <td> <c:out value="${addressFieldsMap['hachette_address_po']}"/> </td>
		                                                </tr>
		                                                <tr>
		                                                    <td> <c:out value="${addressFieldsMap['hachette_address_1']}"/> </td>
		                                                </tr>
		                                                <tr>
		                                                    <td> <c:out value="${addressFieldsMap['hachette_address_2']}"/> </td>
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
		                                                    <td>
		                                                        <b>To pay by Credit Card, please call:</b>
		                                                    </td>
		                                                </tr>
		                                                <tr>
		                                                    <td>Customer Financial Services: <br>
		                                                        <b> <c:out value="${addressFieldsMap['customer_financial_service']}"/> </b>
		                                                    </td>
		                                                </tr>
		                                                <tr>
		                                                    <td>Customer Service: <br>
		                                                        <b> <c:out value="${addressFieldsMap['customer_service']}"/> </b>
		                                                    </td>
		                                                </tr>
		                                                <tr>
		                                                    <td>Email:
		                                                        <a href="mailto:<c:out value="${addressFieldsMap['cs_email']}"/>">
		                                                            <b> <c:out value="${addressFieldsMap['cs_email']}"/> </b>
		                                                        </a>
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
		                                                    <td>DUNS <c:out value="${addressFieldsMap['duns']}"/></td>
		                                                </tr>
		                                                <tr>
		                                                    <td>TIN <c:out value="${addressFieldsMap['tin']}"/></td>
		                                                </tr>
		                                                <tr>
		                                                    <td style="padding-top: 43px;">
		                                                    	<c:choose>
		                                                    		<c:when test="${data.invoiceHeader.currCode eq 'CAD'}">
		                                                    			<em>Billed in Canadian Dollars</em>
		                                                    		</c:when>
		                                                    		<c:otherwise>
		                                                    			<em>Billed in US Dollars</em>
		                                                    		</c:otherwise>
		                                                    	</c:choose>
		                                                    </td>
		                                                </tr>
		                                            </tbody>
		                                        </table>
		                                    </div>
		                                </div>
		                            </div>
		                        </div>
		                    </div> <!-- For Invoice detail data -->
		                    <c:if test="${isRepUser}">
							    <p><b><%=ApplicationConstant.SALES_REPRESENTATIVE_DISTRIBUTION_CLIENTS %> <a href="mailto:<%=ApplicationConstant.MAIL_TO_INVOICES_HBG%>"><%=ApplicationConstant.MAIL_TO_INVOICES_HBG%></a>. </b></p>
							</c:if>
		                    
		                    <input type="hidden" id="orderDetailHidden" name="orderDetailHidden" value="${orderCallingPage}">	
		                    <input type="hidden" id="claimCallHidden" name="claimCallHidden" value="${claimCallingPage}">
		                    								
		                    <!-- NRP-Claim functionality -->
		                    
		                      <portlet:renderURL var="getClaimDetail">
								<portlet:param name="action" value="getClaimDetail" />
								<portlet:param name="invoiceno" value="${data.invoiceHeader.invoiceNumber}" />
								<portlet:param name="invoiceDate" value="${data.invoiceHeader.invoiceDate}" />
								<portlet:param name="accountNumber" value="${data.orderDTO.orderHeader.accountNumber}" />
								<portlet:param name="invoiceGeneratedDays" value="${invoiceGeneratedDays}" />
								<c:choose>
                       				<c:when test="${orderCallingPage eq ''}">
										<portlet:param name="claimFormPage" value="INVOICE" />
									</c:when>
									<c:otherwise>
										<portlet:param name="claimFormPage" value="SHIPMENT" />
									</c:otherwise>
								</c:choose>
							</portlet:renderURL>
							
							<!-- RPC9 Starts-->	
 							<c:if test="${(not empty ALL && ALL) || (not empty NRP_TRACK_CLAIMS && NRP_TRACK_CLAIMS)}">
							<c:if test="${showClaimsFlag && !empty claimHeaderList}">
							<div class="border-gray p-2 mb-2">
                            <div class="bg-light-gray border-gray p-3">
                                <h3 class="table_hedding m-0 mb-1">Claims for Invoice <span>${invoiceno}</span></h3>
		                      				<h5>All claims must be made within ${invoiceGeneratedDays} days from invoice date. <br>Prices subject to change without notice.</h5>
		                      				<p class="w-100 faq-claim-view" style="float:left;"><a href="/web/guest/faqs" class="nav-header link-invoice">
		                       				<b>View Claims FAQ</b> <i class="fa fa-angle-right"></i></a></p>
                                <c:forEach items="${claimHeaderList}" var="claimHeader" varStatus="count">
	                                <portlet:renderURL var="getInvoiceDetail">
											<portlet:param name="action" value="getInvoiceDetail" />
											<portlet:param name="invoiceno" value="${claimHeader.claimInvoiceNumber}" />
											<portlet:param name="invoice-originvoiceno" value="${claimHeader.invoiceNumber}" />
									</portlet:renderURL>
									<portlet:actionURL var="claimLineItemsURL" name="getClaimLineForClaim">
										<portlet:param name="retailerClaimNumber" value="${claimHeader.retailerClaimNumber}" />
										<portlet:param name="claimComment" value="${claimHeader.claimComment}" />
										<portlet:param name="claimTypeKey" value="${claimHeader.claimTypeKey}" />
										<portlet:param name="nonRpCLaim" value="${claimHeader.nonRpClaim}" />
										<portlet:param name="createdDate" value="${claimHeader.createdDate}" />
										<portlet:param name="claimTypeDesc" value="${claimHeader.claimTypeDesc}" />
										</portlet:actionURL>
									<portlet:resourceURL id="cancelClaim" var="cancelClaimURL"> </portlet:resourceURL>
									<div class="view-claim-line-items-form${count.index}">
										<springfm:form name="claimLineItemsForm${count.index}" id="view-claim-line-items-form${count.index}" action="<%=claimLineItemsURL%>"></springfm:form>
									</div>
									<div class="cancel-claim-form${claimHeader.claimHeaderKey}">
										<springfm:form  name="cancelClaimForm" id="${claimHeader.claimHeaderKey}" action="<%=cancelClaimURL%>"></springfm:form>
									</div>
	                                <div class="claim-Tracker">
	                                    <div class="statusbar">
	                                        	<c:choose>
											    <c:when test="${claimHeader.nonRpClaim eq 'true'}">
											    <div class="row">
											    <div class="col-sm-6">
											    <p class="statusheading">Submitted via Email
												<c:if test="${(fn:toLowerCase(claimHeader.claimStatus) eq fn:toLowerCase('Credit Issued')) && (claimHeader.claimInvoiceNumber ne 0) }">
											    <a class="statusheading view-credit-link" href="<%=getInvoiceDetail %>">View Credit</a>
											    </c:if>							    
											    </p>
											    </div>
											    
											    </div>
											    </c:when>
											    <c:otherwise>
											    <div class="row">
	                                            <div class="col-sm-6">
	                                                <p class="statusheading">ZenDesk Ticket #: <strong>${claimHeader.zendeskTicketNumber eq '0' ? 'NA' : claimHeader.zendeskTicketNumber}</strong>
	                                                <c:if test="${(fn:toLowerCase(claimHeader.claimStatus) eq fn:toLowerCase('Credit Issued')) && (claimHeader.claimInvoiceNumber ne 0) }">
											    <a class="statusheading view-credit-link" href="<%=getInvoiceDetail %>">View Credit</a>
											    </c:if>	
	                                                </p>
	                                            </div>
	                                            
											    
	                                       <div class="col-sm-6 text-right">
	                                                <p class="statusheading">Requestor:
	                                                    <strong>${claimHeader.requestedByEmail}</strong>
 	                                                    <c:if test="${fn:toLowerCase(claimHeader.claimStatus) eq fn:toLowerCase('SUBMITTED') && fn:toLowerCase(claimHeader.claimIntegStatus) eq fn:toLowerCase('READY')}">
															<span style="margin-left: 20px">
		                                                    	<a class="btn btn-theme search-btn text-white cancel-claim-button"  onclick="showCancelConfirmationModel(${claimHeader.claimHeaderKey})">Cancel Claim</a>
		                                                    </span>													   
		                                                </c:if>
	                                                </p>
	                                            </div>
	                                        	</div>
											    </c:otherwise>
											    </c:choose>
	                                        <div>
	                                         <c:choose>
											    <c:when test="${fn:toLowerCase(claimHeader.claimStatus) eq fn:toLowerCase('SUBMITTED')}">
											       <div class="progress m-0">
														<div class="status-bar submitted" style="width:33.33%">
															<p class="percent">Current Status: <b>Submitted</b></p>
														</div>
														<div class="status-bar" style="width:33.33%">
															<p class="percent"><b>Under Review</b></p>
														</div>
														<div class="status-bar" style="width:33.33%">
															<p class="percent"><b>Claim Processed</b></p>
														</div>
		                                        	</div>
											    </c:when>
											    <c:when test="${fn:toLowerCase(claimHeader.claimStatus) eq fn:toLowerCase('Under Review')}">
											    <div class="progress m-0">
							                            <div class="status-bar" style="width:33.33%">
							                                <p class="percent"><b>Submitted</b></p>
							                            </div>
							                            <div class="status-bar under-review" style="width:33.33%">
							                                <p class="percent">Current Status: <b>Under Review</b></p>
							                            </div>
							                            <div class="status-bar" style="width:33.33%">
							                                <p class="percent"><b>Claim Processed</b></p>
							                            </div>
							                        </div>
											    </c:when>
											    <c:when test="${fn:toLowerCase(claimHeader.claimStatus) eq fn:toLowerCase('Approved')}">
											    	<div class="progress m-0">
							                            <div class="status-bar" style="width:33.33%">
							                                <p class="percent"><b>Submitted</b></p>
							                            </div>
							                            <div class="status-bar" style="width:33.33%">
							                                <p class="percent"><b>Under Review</b></p>
							                            </div>
							                            <div class="status-bar approved" style="width:33.33%">
							                                <p class="percent">Current Status: <b>Claim Approved</b></p>
							                            </div>
							                        </div>
											    </c:when>
											    <c:when test="${fn:toLowerCase(claimHeader.claimStatus) eq fn:toLowerCase('Credit Issued')}">
							                        <div class="progress m-0">
							                            <div class="status-bar" style="width:33.33%">
							                                <p class="percent"><b>Submitted</b></p>
							                            </div>
							                            <div class="status-bar" style="width:33.33%">
							                                <p class="percent"><b>Under Review</b></p>
							                            </div>
							                            <div class="status-bar approved" style="width:33.33%">
							                                <p class="percent">Current Status: <b>Credit Issued</b></p>
							                            </div>
							                        </div>
							                        </c:when>
							                        <c:when test="${fn:toLowerCase(claimHeader.claimStatus) eq fn:toLowerCase('Denied')}">
								                        <div class="progress m-0">
								                            <div class="status-bar" style="width:33.33%">
								                                <p class="percent"><b>Submitted</b></p>
								                            </div>
								                            <div class="status-bar" style="width:33.33%">
								                                <p class="percent"><b>Under Review</b></p>
								                            </div>
								                            <div class="status-bar claimed-denied" style="width:33.33%">
								                                <p class="percent">Current Status: <b>Claim Denied</b></p>
								                            </div>
								                        </div>
												     </c:when>
												    <c:when test="${fn:toLowerCase(claimHeader.claimStatus) eq fn:toLowerCase('CANCELLING')}">
												       <div class="progress m-0">
															<div class="status-bar cancelling" style="width:33.33%">
																<p class="percent">Current Status: <b>Cancel Requested</b></p>
															</div>
															<div class="status-bar" style="width:33.33%">
																<p class="percent"><b>Cancel Under Review</b></p>
															</div>
															<div class="status-bar" style="width:33.33%">
																<p class="percent"><b>Claim Cancelled</b></p>
															</div>
			                                        	</div>
												    </c:when>
												    <c:when test="${fn:toLowerCase(claimHeader.claimStatus) eq fn:toLowerCase('Cancel Under Review')}">
												    	<div class="progress m-0">
								                           <div class="status-bar" style="width:33.33%">
																<p class="percent"><b>Cancel Requested</b></p>
															</div>
								                            <div class="status-bar cancelled" style="width:33.33%">
								                                <p class="percent">Current Status: <b>Cancel Under Review</b></p>
								                            </div>
								                            <div class="status-bar" style="width:33.33%">
																<p class="percent"><b>Claim Cancelled</b></p>
															</div>
								                        </div>
												    </c:when>
												    <c:when test="${fn:toLowerCase(claimHeader.claimStatus) eq fn:toLowerCase('CANCELLED')}">
												       <div class="progress m-0">
															<div class="status-bar" style="width:33.33%">
																<p class="percent"><b>Cancel Requested</b></p>
															</div>
															<div class="status-bar" style="width:33.33%">
																<p class="percent"><b>Cancel Under Review</b></p>
															</div>
															<div class="status-bar cancelled" style="width:33.33%">
																<p class="percent">Current Status: <b>Claim Cancelled</b></p>
															</div>
			                                        	</div>
												    </c:when>
											    <c:otherwise>
											    </c:otherwise>
											</c:choose>
											</div>
	                                    </div>
	                                    <div class="panel-group m-0 mb-1" id="accordion_${count.index}">
	                                        <div class="panel custom-blue">
	                                            <div class="panel-heading text-right"  id="headingOne_${count.index}">
	                                            <c:choose>
											    <c:when test="${claimHeader.nonRpClaim eq 'true'}">
											    <div class="panel-title">
	                                                    <a href="javascript:void(0)" data-bind="${claimHeader.invoiceHeaderKey}" class="claimLineItemsLink"> <span>View Claim Details </span><i class="fa fa-caret-down"></i>
														<div id="${count.index}" data-bind="${claimHeader.invoiceHeaderKey}" ></div>
	                                                    </a>
	                                                </div>
											    </c:when>
											    <c:otherwise>
											    <div class="panel-title">
	                                                    <a href="javascript:void(0)" data-bind="${claimHeader.claimHeaderKey}" class="claimLineItemsLink"> <span>View Claim Details </span><i class="fa fa-caret-down"></i>
														<div id="${count.index}" data-bind="${claimHeader.claimHeaderKey}" ></div>
	                                                    </a>
	                                                </div>
											    </c:otherwise>
											    </c:choose>
											    
	                                                
	                                            </div>
												<div id="show-claim-line-items${count.index}" class="inline_table">
												</div>
	                                        </div>
	                                    </div>
	                                </div>
                                </c:forEach>
                            </div>
                        </div>
                        </c:if>
                        </c:if>
					</div>
					
					<!-- RPC9 Ends Here-->	
							<c:if test="${(not empty ALL && ALL) || (not empty NRP_SUBMIT_CLAIMS && NRP_SUBMIT_CLAIMS)}">
								<c:if test="${showClaimsFlag && (rowInvType eq 'Invoice') && (data.invoiceHeader.origInvoiceNumber eq '0') }">
									<div class="border-gray p-2" id="claim-data-container">
		                    			<div class="gray-bg border-gray p-3">
		                      				<h3 class="table_hedding m-0 mb-1">Start a Claim for Invoice <span>${invoiceno}</span></h3>
		                      				<h5 class="font-merriweather">All claims must be made within ${invoiceGeneratedDays} days from invoice date. <br>Prices subject to change without notice.</h5>
		                      				<p class="w-100 float-left"><a href="/web/guest/faqs" class="nav-header link-faq">
		                       				<b>View Claims FAQ</b> <i class="fa fa-angle-right"></i></a></p>
		                       				<jsp:useBean id="currentDate" class="java.util.Date" />
		                       				<fmt:parseNumber  value="${currentDate.time / (1000*60*60*24) }" integerOnly="true" var="totalNoOfDays"  />
											<fmt:parseNumber  value="${invoiceDate.time / (1000*60*60*24) }" integerOnly="true" var="invoiceNoOfDays"  />
									        <c:choose>
			                       				<c:when test="${((totalNoOfDays-invoiceNoOfDays) <= invoiceGeneratedDays) 
			                       				&& ((not empty data.orderDTO) && (data.orderDTO.orderHeader.orderType ne '70')) 
			                       				&& (data.invoiceHeader.invoiceCategory ne 'PREINV' ) && isValidInvoiceForClaim }">
													<a class="btn btn-theme search-btn" href="<%=getClaimDetail %>">Start a Claim</a>
												</c:when>
												<c:otherwise>
													<h6>Please contact Customer Service at <a href = "mailto:customerservice@hbgusa.com">customerservice@hbgusa.com</a> if you have issues with this invoice.</h6>
												</c:otherwise>
											</c:choose>
		                   				 </div>
	                 				</div>
	                 			</c:if>
	                 		</c:if>
	                    </div> <!--  INVOICE - TAB CODE: STARTED -->
	                    </div>
	                    </div>
					</c:when>
					<c:otherwise>
	                    <div id="memo-detail" class="transaction-detail">
	                    	<c:set value="${data }" scope="request" var="data" />
							<jsp:include page="/WEB-INF/views/credit_tab_template.jsp"></jsp:include>
	                    </div>
					</c:otherwise>
				</c:choose>
				</div> <!-- detail-part ended -->
              </div>
              <!-- /.order-details -->
            </div>
        </div>
    </div>
</div>
</c:otherwise></c:choose>
<!--======= /.Wrapper =========-->
<script
	src="<%=request.getContextPath()%>/js/order_detail.js?t=<%=timestamp%>"
	portlet_namespace_orders="<portlet:namespace/>"
	zendesk_url="<%=initiateZendeskTicketURL %>"
	reference_number="${refNo}" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/invoice_detail.js?t=<%=timestamp%>"
	portlet_namespace_orders="<portlet:namespace/>" hierarchy_data="<%=getInvoiceHierarchyData %>" 
	details_url="<%=getDetails %>" credit_url="<%=getCreditMemos %>" debit_url="<%=getDebitMemos %>"
	zendesk_url="<%=initiateZendeskTicketURL %>" reference_number="${refNo}" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/clear_form.js?t=<%=timestamp%>"
	type="text/javascript"></script>
<script>
$('body').on('click','.shipmentlineItemsLink',function(event){
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
     
	function searchByShipGroup(formId){
		document.getElementById(formId).submit();
	}
	
	function searchByShortAuthor(formId){
		document.getElementById(formId).submit();
	}
	
 </script>