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
<%@page import="com.hbg.rp.search.util.CommonUtil"%>
<%@page import="com.hbg.rp.search.constants.ApplicationConstant"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayPortletContext"%>

<%@ include file="init.jsp" %>

<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
	String pageName = themeDisplay.getLayout().getName(themeDisplay.getLocale());
	String layoutId = themeDisplay.getLayout().getPlid() + "";
	CommonUtil commonUtil = new CommonUtil();
	String allReportingGroups = commonUtil.getAllReportingGroups();
	String reportingGroups = commonUtil.getPermittedReportingGroups(renderRequest);
	String encryptedIsbn = ApplicationConstant.ENCRYPTED_ISBN;
	String encryptedTitle = ApplicationConstant.ENCRYPTED_TITLE;
	String encryptedAuthor = ApplicationConstant.ENCRYPTED_AUTHOR;
	String encryptedMsrp = ApplicationConstant.ENCRYPTED_MSRP;
	
	LiferayPortletContext context = (LiferayPortletContext) portletConfig.getPortletContext();
	long timestamp = context.getPortlet().getTimestamp();
%>

	<portlet:renderURL var="viewAllShipmentsURL" >
	    <portlet:param name="action" value="viewAllShipments" />
	    <portlet:param name="search-shipment" value="true" />
	</portlet:renderURL>
	
	<portlet:resourceURL id="getShipmentTrackingData" var="shipmentTrackingDataURL"> </portlet:resourceURL>
	
	<c:set var="reportingGroups" value="<%=reportingGroups %>" />
	<c:set var="allReportingGroups" value="<%=allReportingGroups %>" />
	<c:set var="encryptedIsbn" value="<%=encryptedIsbn %>" />
	<c:set var="encryptedTitle" value="<%=encryptedTitle %>" />
	<c:set var="encryptedAuthor" value="<%=encryptedAuthor %>" />
	<c:set var="encryptedMsrp" value="<%=encryptedMsrp %>" />
	
	<portlet:actionURL name="getViewAllShipments" var="getViewAllShipmentsURL">
		 <portlet:param name="search-shipment" value="true" />
	</portlet:actionURL>
<div>

<span style="display:none;" id="main_wrapper_content">
	<c:choose>
		<c:when test="${!empty shipData && shipData.size() > 0}">
			<p>
				1-<span id="current-size-shipments">${shipData.size()}</span> of <span
					id="total-size-shipments"> <b><fmt:formatNumber
							type="number" groupingUsed="true" value="${resultSize}" /></b>
				</span>
				<c:choose>
					<c:when test="${!empty searchParam && shipData.size() == 1}"> Shipment found ${fn:replace(fn:replace(searchParam,'>','&gt;'),'<','&lt;')} </c:when>
					<c:otherwise>
							Shipments found <c:if test="${!empty searchParam }">  ${fn:replace(fn:replace(searchParam,'>','&gt;'),'<','&lt;')} </c:if>
					</c:otherwise>
				</c:choose>
			</p>
		</c:when>
		<c:otherwise>
			<p>
				No Shipments found
				<c:if test="${!empty searchParam}"> ${fn:replace(fn:replace(searchParam,'>','&gt;'),'<','&lt;')}</c:if>
			</p>
		</c:otherwise>
	</c:choose>
</span>
<span style="display:none;" id="excelExport">
	<c:choose>
		<c:when test="${!empty shipData && shipData.size() > 0}">
			<portlet:resourceURL var="exportShipment" id="shipmentSearchExport">
				<portlet:param name="exportOption" value="${formparam}" />
			</portlet:resourceURL>
			<a class="btn btn-theme px-1" href="<%=exportShipment %>"><i class="fa fa-external-link-square"></i> Export Search Results To Excel</a>
			<br>
			<small>You can download up to <%=ApplicationConstant.SEARCH_SIZE %> lines of data.</small>
		</c:when>
	</c:choose>
</span>
<script>
	$('.main-wrapper .row .text-left').html($('#main_wrapper_content').html());	
	$('.main-wrapper .row .text-right').html($('#excelExport').html());
</script>

<div class="recent-orders" id="result-shipments">

	<div class="row">
		<div class="col-md-8">
		<%if("Recent Activity".equalsIgnoreCase(pageName)) {%>
			<c:choose>
				<c:when test="${empty formparam['search-shipment'] || formparam['search-shipment'] == 'false'}">
					<c:if test="${!empty shipData && shipData.size() > 0}">
						<h2 class="table_hedding">Recent Shipments &nbsp;&nbsp;<img src="<%=request.getContextPath()%>/images/shiptruck.svg" /></h2>
					</c:if>
					<c:if test="${empty shipData || shipData.size() <= 0}">
						<h2 class="table_hedding">No Recent Shipments Found</h2>
					</c:if>
				</c:when>
			</c:choose>
		<%} %>
		</div>

		<%if("Recent Activity".equalsIgnoreCase(pageName)) {%>
			<c:if test="${!formparam['search-shipment'] && shipData.size() > 0 }">
				<div class="col-md-4 text-right">
					<a class="shipments-jumpto-link view_shipments" href="<%=getViewAllShipmentsURL%>">View all Shipments &nbsp;&nbsp; 
							<i class="fa fa-chevron-right" aria-hidden="true"></i></a>
				</div>
			</c:if>
		<%} %>
	</div>
	<div class="shipments_rows">
	<c:choose>
		<c:when test="${!empty shipData}">
			<c:forEach items="${shipData}" var="shipment" varStatus="count">
				   <portlet:renderURL var="getOrderDetail">
						<portlet:param name="action" value="getOrderDetail" />
						<portlet:param name="refno" value="${shipment.referenceNumber}" />
					</portlet:renderURL>
					
					<portlet:renderURL var="orderStatusDataURL" >
						<portlet:param name="action" value="getOrderStatusPage" />
						<portlet:param name="refno" value="${shipment.referenceNumber}" />
					</portlet:renderURL>
					
					<portlet:actionURL name="getSearchOrderDetail" var="getSearchOrderDetailURL">
						<portlet:param name="pono" value="${shipment.poNbr}" />
						<portlet:param name="pono_operator" value="EQ" />
					</portlet:actionURL>
					
					<portlet:actionURL name="getSearchByStore" var="getSearchByStoreURL">
						<portlet:param name="storepk" value="${shipment.shiptoId}" />
						<portlet:param name="accountpk" value="${shipment.billtoId}" />
						<portlet:param name="storename" value="${shipment.shipToName}" />
						<portlet:param name="storenumber" value="${shipment.shipToNumber}" />
						<portlet:param name="accountname" value="${shipment.accountName}" />
						<portlet:param name="accountnumber" value="${shipment.accountNumber}" />
						<portlet:param name="storedata" value="${shipment.shipToName} | ${shipment.shipToNumber}" />
						<portlet:param name="storedataRetain" value="${shipment.shiptoId}" />
						<portlet:param name="accountdata" value="${shipment.accountName} | ${shipment.accountNumber}" />
						<portlet:param name="accountKey" value="${shipment.billtoId}" />
					</portlet:actionURL>
					
					<portlet:actionURL name="getSearchByZip" var="getSearchByZipURL">
						<portlet:param name="address" value="${shipment.destinationZip}" />
					</portlet:actionURL>
					
					<portlet:actionURL name="getSearchByZip" var="getSearchByShipZipURL">
						<portlet:param name="address" value="${shipment.shipToZip}" />
					</portlet:actionURL>
					
					<portlet:actionURL name="getSearchByShipGroup" var="getSearchByShipGroupURL">
						<portlet:param name="shipGroup" value="${shipment.shipGroupId}" />
						<portlet:param name="search-shipment" value="true" />
						<portlet:param name="shipgroupno" value="${shipment.shipGroupId}" />
						<portlet:param name="ship-shipGroup" value="${shipment.shipGroupId}" />
					</portlet:actionURL>
					
				<div class="table_outer">
					<header class="table_header">
						<span class="serial_number">${count.index + 1 + formparam['shipments-offset'] }.</span> <span
							class="headding">Latest Shipment for Order Reference <a href="<%=getOrderDetail%>">${shipment.referenceNumber}</a> </span>
					</header>

					<div class="body_content">
						<div class="table_content search_results_page">
							<table class="table"> <!-- table on left side -->
								<tbody>
									<tr>
										<td>PO: <a href="<%=getSearchOrderDetailURL %>">${shipment.poNbr}</a>
									     </td>	
									</tr>
									<tr>
										<td>
										  <c:if test="${ not empty shipment.shipToNumber && shipment.shipToNumber > 0}">
											  Store#:
													<a href="<%=getSearchByStoreURL%>" >${shipment.shipToNumber}
														<span id="storepk" hidden>${shipment.shiptoId}</span>
														<span id="accountpk" hidden>${shipment.billtoId}</span>
														<input id="storename" type="hidden" value="${shipment.shipToName}" />
														<span id="storenumber" hidden>${shipment.shipToNumber}</span>
														<span id="accountname" hidden>${shipment.accountName}</span>
														<span id="accountnumber" hidden>${shipment.accountNumber}</span>
													</a>
										  </c:if>
										</td>
									</tr>
									<tr>
										<td>Destination:
										<c:choose>
											<c:when test="${ not empty shipment.destinationName}">
												<span class="text-capitalize">
													<c:out value="${shipment.destinationName},
													${fn:toLowerCase(shipment.destinationCity)},
													${shipment.destinationState} " />
												</span>
												<c:set var="destination" value="${shipment.destinationZip}" />
													<a href="<%=getSearchByZipURL%>" > ${shipment.destinationZip}
														<span id="zipcode" hidden>${shipment.destinationZip}</span>
													</a>
											</c:when>
											<c:otherwise>
												<span class="text-capitalize">
													<c:out value="${shipment.shipToName},
													${fn:toLowerCase(shipment.shipToCity)},
													${shipment.shipToState} " />
												</span>
												<c:set var="ship_destination" value="${shipment.shipToZip}" />
													<a href="<%=getSearchByShipZipURL%>" > ${shipment.shipToZip}
														<span id="zipcode" hidden>${shipment.shipToZip}</span>
													</a>
											</c:otherwise>
										</c:choose>
										</td>
									</tr>
									<tr>
									    <portlet:renderURL var="getInvoiceDetail">
											<portlet:param name="action" value="getInvoiceDetail" />
											<portlet:param name="<%=ApplicationConstant.INV_INVOICE_NBR %>" value="${shipment.invoiceNumber}" />
										</portlet:renderURL>
										<td>Invoice Number: <b>
										<c:choose>
              						    <c:when test="${fn:contains(invoiceHeaderData, shipment.invoiceHeaderId)}">
                  							<a href="<%=getInvoiceDetail%>"> ${shipment.invoiceNumber} </a> 
              							</c:when>    
              							<c:otherwise>
                  						${shipment.invoiceNumber}
              							</c:otherwise>
          								</c:choose> 
										</b> </td>
									</tr>
									<tr>
										<td>
											Order Received Date: 
											<b><fmt:formatDate type="date" dateStyle="short" value="${shipment.orderRecievedDate}" /></b>
										</td>
									</tr>
								</tbody>
							</table>
							
							<table class="table"> <!-- table on right side -->
								<tbody>
									<tr>
									  <td>
										  Ship Group:<a href="<%=getSearchByShipGroupURL%>" > ${shipment.shipGroupId}
												 <span id="shipgroupno" hidden>${shipment.shipGroupId}</span>
											</a>
										</td>
									</tr>
									<tr>
										<td>Shipment: <b>${shipment.carrierName}</b></td>
									</tr>
									<tr>
				                        <!-- NRP-2497 start -->
									    <c:if test="${!empty shipment.serviceLevelCode}">
											<td>Shipment Method: <b>${shipment.serviceLevelDesc}</b></td>
							            </c:if>
							            <c:if test="${empty shipment.serviceLevelCode}">
											<td>Shipment Method: </td>
							            </c:if>
										<!-- NRP-2497 end -->
									</tr>
									<tr>
										<td>
											Shipped Date: 
											<b><fmt:formatDate type="date" dateStyle="short" value="${shipment.shipDate}" /></b>
										</td>
									</tr>
								</tbody>
							</table>
						</div>

						<div class="right_content text-center carrierTrackingNumber">
							<p>Carrier Tracking Number:</p>
							
							<p class="trackingNbr"> <b>
							<c:set var="trackingNumber" value="${shipment.trackingNumber}"></c:set>
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
							</b> </p>
							<a class="btn btn-theme shipment-data" href="<%=orderStatusDataURL%>#Shipped-${shipment.invoiceNumber}">
								View Shipment</a>
						</div>
					</div>
					
						<c:choose>
			              <c:when test="${!empty shipmentLines && shipmentLines.size() > 0}">
							<div class="inline_table">
								<table class="table">
									<thead>
										<tr>
											<th>Item Code<br> (ISBN/EAN)</th> 
											<th>Title</th>
											<th class="long_title">Author(s)</th>
											<th>Quantity<br>Total</th>
											<th>Shipped<br>Quantity</th>
											<th>Pub Status</th>
											<th>*MSRP</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach items="${shipmentLines}" var="shipmentLine" varStatus="count">
										<c:if test="${shipmentLine.shipmentHeaderId == shipment.shipmentHeaderId}">
										<c:set var="isItemCodeVisible" value="${reportingGroups == '0' ? true : reportingGroups.contains(shipmentLine.reportingGroupCode)}" />
										<c:set var="allowedOwnerCode" value="${allReportingGroups.contains(shipmentLine.reportingGroupName) ? true : false}" />
										 <c:set var="shortAuthor" value="${shipmentLine.shortAuthor}"></c:set>
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
											
											<tr>
													<td class="${isItemCodeVisible ? '' : 'hide-item'}">
														<c:if test="${not allowedOwnerCode}">
															<c:out value="${isItemCodeVisible ? shipmentLine.isbn : encryptedIsbn}" />
														</c:if>
														<c:if test="${allowedOwnerCode}">
															<c:choose>
																<c:when test="${isItemCodeVisible}">
																   <a href="<%=ApplicationConstant.VIEW_CATALOG_BOOK_URL %>${shipmentLine.isbn}"><c:out value="${shipmentLine.isbn}" /></a>
																</c:when>
																<c:otherwise>
																  <b><c:out value="${encryptedIsbn}" /></b>
																</c:otherwise>
															</c:choose>
														</c:if>
													</td>
													<td class="${isItemCodeVisible ? '' : 'hide-item'}">
														<c:if test="${not allowedOwnerCode}">
															<c:out value="${isItemCodeVisible ? shipmentLine.shortTitle : encryptedTitle}" />
														</c:if>
														<c:if test="${allowedOwnerCode}">
															<c:choose>
																<c:when test="${isItemCodeVisible}">
																   <a href="<%=ApplicationConstant.VIEW_CATALOG_BOOK_URL %>${shipmentLine.isbn}"><c:out value="${shipmentLine.shortTitle}" /></a>
																</c:when>
																<c:otherwise>
																  <b><c:out value="${encryptedTitle}" /></b>
																</c:otherwise>
															</c:choose>
														</c:if>
													</td>
													<td class="${isItemCodeVisible ? '' : 'hide-item'}">
														<c:if test="${not allowedOwnerCode}">
															<c:out value="${isItemCodeVisible ? shipmentLine.shortAuthor : encryptedAuthor}" />
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
																			<a href="<%=getSearchByShortAuthorURL%>">${shipmentLine.shortAuthor} </a>
																	</c:when>
																	<c:otherwise>
																		<a href="javascript:void(0)" >"${encryptedAuthor}"</a>
																	</c:otherwise>
														     </c:choose>
														</c:if>
													</td>
													<td>${shipmentLine.orderQuantity}</td>
													<td>${shipmentLine.shippedQuantity}</td>
													<td>${shipmentLine.pubStatusCode}</td>
													<td class="${isItemCodeVisible ? '' : 'hide-item'}">
														<c:choose>
															<c:when test="${not empty fn:trim(shipmentLine.msrp) and fn:trim(shipmentLine.msrp) ne '$' }">
																<c:out value="${isItemCodeVisible ? shipmentLine.msrp : encryptedMsrp}" />
															</c:when>
															<c:otherwise>N/A</c:otherwise>
														</c:choose>
													</td>
												</tr>											
										</c:if>
									</c:forEach>
									</tbody>
								</table>
							</div>
						</c:when>
					</c:choose>
						
				</div>
			</c:forEach>
		</c:when>
	</c:choose>

	<div class="append_div">
		<c:if test="${not empty formparam['shipmentLoadMore'] && formparam['shipmentLoadMore'] eq 'true'}" >
			<div class="row text-center">
				<div class="col-xs-12">
					<button id="loadMore-shipments" class="btn btn-theme loadMore" name="shipments">
						Load Next <%=ApplicationConstant.PAGE_SIZE %> Results
					</button>
				</div>
			</div>
		</c:if>
		<div id="shipments-loading" class=""></div>
	</div>
	
	</div>
	</div>
</div>


<c:if test="${not empty formparam['search-shipment'] || formparam['search-shipment'] == 'true'}">
	<script type="text/javascript">
		document.title='HBG Retailer Portal: Shipment Search Results';
	</script>
</c:if>
<script src="<%=request.getContextPath()%>/js/clear_form.js?t=<%=timestamp%>"
	type="text/javascript"></script>
<script>
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


function searchByPO(formId){
	document.getElementById(formId).submit();
}

function searchByStore(formId){
	document.getElementById(formId).submit();
}

function searchByDestinationZip(formId){
	document.getElementById(formId).submit();
}

function searchByAccountName(formId){
	document.getElementById(formId).submit();
}

function searchByAccountNo(formId){
	document.getElementById(formId).submit();
}

function searchByShipGroup(formId){
	document.getElementById(formId).submit();
}

function searchByViewAllShipments(formId){
	document.getElementById(formId).submit();
}

</script>
