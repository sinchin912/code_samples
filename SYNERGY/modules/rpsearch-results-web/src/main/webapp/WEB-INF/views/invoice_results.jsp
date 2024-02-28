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
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.hbg.rp.search.util.IPortalMappingsUtil"%>
<%@page import="com.hbg.rp.model.InvoiceHeader"%>
<%@page import="com.hbg.rp.search.util.CommonUtil"%>
<%@page import="com.hbg.rp.search.constants.ApplicationConstant"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ include file="init.jsp" %>
<%@page import="com.liferay.portal.kernel.portlet.LiferayPortletContext"%>
<%@page import="javax.portlet.PortletSession"%>


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
	Long extRepId = (long) renderRequest.getPortletSession().getAttribute(ApplicationConstant.LIFERAY_SHARED_EXT_REP_ID, PortletSession.APPLICATION_SCOPE);
	Boolean isRepUser = (extRepId > 0) ? true : false;

%>

	<c:set var="allReportingGroups" value="<%=allReportingGroups %>" />
	<c:set var="reportingGroups" value="<%=reportingGroups %>" />
	<c:set var="encryptedIsbn" value="<%=encryptedIsbn %>" />
	<c:set var="encryptedTitle" value="<%=encryptedTitle %>" />
	<c:set var="encryptedAuthor" value="<%=encryptedAuthor %>" />
	<c:set var="encryptedMsrp" value="<%=encryptedMsrp %>" />
	<c:set var="loadMoreType" value="${formparam['invoice-search-param']}" />
	<c:set var="isRepUser" value="<%=isRepUser%>" />
	<c:set var="encryptedAmount" value="<%=ApplicationConstant.ENCRYPTED_AMOUNT %>" />
<div>

<c:set var="docSearchType"
	value="${formparam['invoice-search-param'] == 'invoices' ? 'Invoices' : (formparam['invoice-search-param'] == 'debits' ? 'Debits' : (formparam['invoice-search-param'] == 'credits' ? 'Credits' : 'Financial Transactions'))}"></c:set>

<span style="display:none;" id="main_wrapper_content">
	<c:choose>
		<c:when
			test="${!empty data && !empty data.invoiceData && data.invoiceData.size() > 0}">
			<p>
				1-<span id="current-size-invoices">${data.invoiceData.size()}</span>
				of <span id="total-size-invoices"> <b><fmt:formatNumber
							type="number" groupingUsed="true"
							value="${formparam['resultSize']}" /></b>
				</span>
				<c:choose>
					<c:when
						test="${!empty formparam['searchParam'] && data.invoiceData.size() == 1}"> ${fn:substring(docSearchType, 0, (fn:length(docSearchType)-1))} found ${fn:replace(fn:replace(formparam['searchParam'],'>','&gt;'),'<','&lt;')} </c:when>
					<c:otherwise>
							${docSearchType} found <c:if
							test="${!empty formparam['searchParam'] }">  ${fn:replace(fn:replace(formparam['searchParam'],'>','&gt;'),'<','&lt;')} </c:if>
					</c:otherwise>
				</c:choose>
			</p>
		</c:when>
		<c:otherwise>
			<p>
				No ${docSearchType} found
				<c:if test="${!empty formparam['searchParam']}"> ${fn:replace(fn:replace(formparam['searchParam'],'>','&gt;'),'<','&lt;')}</c:if>
			</p>
		</c:otherwise>
	</c:choose>
</span>
<span style="display:none;" id="excelExport">
	<c:choose>
		<c:when test="${!empty data && !empty data.invoiceData && data.invoiceData.size() > 0}">
		 <portlet:resourceURL var="exportInvoice" id="invoiceSearchExport">
				 	<portlet:param name="exportOption" value="${formparam}" />
			</portlet:resourceURL>
		<a class="btn btn-theme px-1" href="<%=exportInvoice %>"><i class="fa fa-external-link-square"></i> Export Search Results To Excel</a>
			<br>
			<small>You can download up to <%=ApplicationConstant.SEARCH_SIZE %> lines of data.</small>
		</c:when>
	</c:choose>
</span>
<script>
	$('.main-wrapper .row .text-left').html($('#main_wrapper_content').html());	
	$('.main-wrapper .row .text-right').html($('#excelExport').html());
</script>

<div class="recent-orders" id="result-invoices">

	<div class="invoices_rows">
	<c:choose>
		<c:when test="${!empty data && !empty data.invoiceData}">
			<c:forEach items="${data.invoiceData}" var="invoice" varStatus="count">
			<portlet:renderURL var="getInvoiceDetail" >
				<portlet:param name="action" value="getInvoiceDetail" />
				<portlet:param name="invoiceno" value="${invoice.invoiceNumber}" />
				<portlet:param name="invoice-originvoiceno" value="${invoice.origInvoiceNumber}" />
			</portlet:renderURL>
			
			<portlet:actionURL name="getSearchOrderDetail" var="getSearchOrderDetailURL">
				<portlet:param name="pono" value="${invoice.poNbr}" />
				<portlet:param name="pono_operator" value="EQ" />
			</portlet:actionURL>
			
			<portlet:actionURL name="getSearchByStore" var="getSearchByStoreURL">
				<portlet:param name="storepk" value="${invoice.shipToKey}" />
				<portlet:param name="accountpk" value="${invoice.billToKey}" />
				<portlet:param name="storename" value="${invoice.shipToName}" />
				<portlet:param name="storenumber" value="${invoice.shiptoNumber}" />
				<portlet:param name="accountname" value="${invoice.accountName}" />
				<portlet:param name="accountnumber" value="${invoice.accountNumber}" />
				<portlet:param name="storedata" value="${invoice.shipToName} | ${invoice.shiptoNumber}" />
				<portlet:param name="storedataRetain" value="${invoice.shipToKey}" />
				<portlet:param name="accountdata" value="${invoice.accountName} | ${invoice.accountNumber}" />
				<portlet:param name="accountKey" value="${invoice.billToKey}" />
			</portlet:actionURL>
			

			<%
				InvoiceHeader invoiceHeader = ((InvoiceHeader)pageContext.getAttribute("invoice"));
				IPortalMappingsUtil portalMappingsUtil = (IPortalMappingsUtil)request.getAttribute("portalMappingsUtil");
				String invoiceCategoryMapping = portalMappingsUtil.getFinancialTransactionType(invoiceHeader.getInvoiceCategory());
			%>
			<c:set var="invoiceCatMapping" value="<%=invoiceCategoryMapping %>" />
			<c:set var="rowInvType" value="${invoice.invoiceType == 'DBN' ? 'Debit' : (invoice.invoiceType == 'CRN' ? 'Credit' : 'Invoice')}"></c:set>
			
				<div class="table_outer">
					<header class="table_header">
						<span class="serial_number">${count.index + 1 + formparam['invoices-offset'] }.</span>
							<span class="headding">${invoiceCatMapping} Summary</span>
					</header>
					
					<c:if test="${!empty invoice.invoiceType && invoice.invoiceType == 'INV'}"> 
						<div class="body_content">
							<div class="table_content search_results_page">
								<table class="table"> <!-- table on left side -->
									<tbody>
										<tr>
											<td>Invoice Number: <a href="<%=getInvoiceDetail %>">${invoice.invoiceNumber}</a></td>
										</tr>
										<tr>
											<c:if test="${not empty invoice.orderHeaderId && invoice.orderHeaderId > 0}">
												<td>Order Reference: 
												<portlet:renderURL var="getOrderDetail" >
													<portlet:param name="action" value="getOrderDetail" />
													<portlet:param name="refno" value="${invoice.referenceNumber}" />
												</portlet:renderURL>
												<a href="<%=getOrderDetail %>">${invoice.referenceNumber}</a> 
												</td>
											</c:if>
										</tr>
										<tr>
											<c:if test="${not empty invoice.poNbr && fn:trim(invoice.poNbr) != ''}">
												<td>PO: 
													<a href="<%=getSearchOrderDetailURL%>" >${invoice.poNbr} </a>
												</td>	
											</c:if>
										</tr>
										<tr>
											<td>
											  <c:if test="${ not empty invoice.shiptoNumber && invoice.shiptoNumber > 0}">
												Store#: 
												<a href="<%=getSearchByStoreURL%>" >
													<fmt:formatNumber groupingUsed="false" value="${invoice.shiptoNumber}" />
													<span id="storepk" hidden>${invoice.shipToKey}</span>
													<span id="accountpk" hidden>${invoice.billToKey}</span>
													<input id="storename" type="hidden" value="${invoice.shipToName}" />
													<span id="storenumber" hidden><fmt:formatNumber groupingUsed="false" value="${invoice.shiptoNumber}" /></span>
													<span id="accountname" hidden>${invoice.accountName}</span>
													<span id="accountnumber" hidden>${invoice.accountNumber}</span>
												</a>
											  </c:if>
											</td>
										</tr>
										<tr>
											<td>
												<c:choose>
													<c:when test="${ not empty invoice.destinationName }">
														   <portlet:actionURL name="getSearchByZip" var="getSearchByZipURL">
																<portlet:param name="address" value="${invoice.destinationZip}" />
																<portlet:param name="invoice-destination" value="${invoice.destinationZip}" />
															</portlet:actionURL>
														Destination:
														<span class="text-capitalize">
															<c:out value="${invoice.destinationName},
															${fn:toLowerCase(invoice.destinationCity)},
															${invoice.destinationState} " />
														</span>
														<c:set var="destination" value="${invoice.destinationZip}" />
														
													  <a href="<%=getSearchByZipURL%>"> ${invoice.destinationZip}
														  <span id="zipcode" hidden>${invoice.destinationZip}</span>
													  </a>
													</c:when>
													<c:when test="${ not empty invoice.shipToName }">
														<portlet:actionURL name="getSearchByZip" var="getSearchByShipZipURL">
															<portlet:param name="address" value="${invoice.shipToZip}" />
															<portlet:param name="invoice-destination" value="${invoice.shipToZip}" />
														</portlet:actionURL>
														Destination:
														<span class="text-capitalize">
															<c:out value="${invoice.shipToName},
																${fn:toLowerCase(invoice.shipToCity)},
																${invoice.shipToState} " />
														</span>
														<c:set var="ship_destination" value="${invoice.shipToZip}" />
														
													   <a href="<%=getSearchByShipZipURL%>"> ${invoice.shipToZip}
														  <span id="zipcode" hidden>${invoice.shipToZip}</span>
													   </a>
													</c:when>
												</c:choose>
											</td>
										</tr>
									</tbody>
								</table>
								
								<table class="table"> <!-- table on right side -->
									<tbody>
										<tr>
											<td>
												Invoice Date: 
												<b>
													<fmt:parseDate var="invoiceDt" value="${invoice.invoiceDate}" pattern="yyyy-MM-dd" />
													<fmt:formatDate value="${invoiceDt}" pattern="M/d/yy" />												
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
													   <b>$<fmt:formatNumber pattern="#,##0.00;-#,##0.00" type="currency" minFractionDigits="2" maxFractionDigits="2" value="${invoice.totalAmt}" /></b>
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
										    		   <b>$<fmt:formatNumber pattern="#,##0.00;-#,##0.00" type="currency" minFractionDigits="2" maxFractionDigits="2" value="${invoice.prepaidAmt}" /></b>
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
													   <c:set var="dueAmount" value="${invoice.totalAmt - invoice.prepaidAmt}"></c:set>
											   		   <b>$<fmt:formatNumber pattern="#,##0.00;-#,##0.00" type="currency" minFractionDigits="2" maxFractionDigits="2" value="${dueAmount}" /></b>
													</c:otherwise>
												</c:choose>
											</td>
										</tr>
										<tr>
											<c:if test="${ not empty invoice.termCode && fn:trim(invoice.termCode) != '' && fn:trim(invoice.termCode) != '0'}">
												<td>Terms: <b>NET ${invoice.termCode} Days</b></td>
											</c:if>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="right_content text-center">
								<p>Invoice Number:</p>
								<p>
									<b>${invoice.invoiceNumber}</b>
								</p>
								<a class="btn btn-theme" href="<%=getInvoiceDetail %>">View Invoice</a>
							</div>
	
						</div>
					</c:if>
					
					<c:if test="${!empty invoice.invoiceType && (invoice.invoiceType == 'DBN' || invoice.invoiceType == 'CRN')}">
						<div class="body_content">
							<div class="table_content search_results_page">
								<table class="table"> <!-- table on left side -->
									<tbody>
										<tr>
											<td>
												${rowInvType} Date: 
												<b>
													<fmt:parseDate var="invoiceDate" value="${invoice.invoiceDate}" pattern="yyyy-MM-dd" />
													<fmt:formatDate value="${invoiceDate}" pattern="M/d/yy" />
												</b>
											</td>
										</tr>
										<tr>
											<c:if test="${not empty invoice.chargebackNumber && fn:trim(invoice.chargebackNumber) != ''}">
												<td>Charge Back Number: <b>${invoice.chargebackNumber}</b></td>
											</c:if>
										</tr>
										<tr>
											<c:if test="${not empty invoice.orderHeaderId && invoice.orderHeaderId > 0}">
												<td>Order Reference: 
													<portlet:renderURL var="getOrderDetail" >
														<portlet:param name="action" value="getOrderDetail" />
														<portlet:param name="refno" value="${invoice.referenceNumber}" />
													</portlet:renderURL>
													<a href="<%=getOrderDetail %>">${invoice.referenceNumber}</a> 
												</td>
											</c:if>
										</tr>
										<tr>
											<c:if test="${not empty invoice.poNbr && fn:trim(invoice.poNbr) != ''}">
												<td>PO: 
													<a href="<%=getSearchOrderDetailURL%>">${invoice.poNbr} </a>
												</td>	
											</c:if>
										</tr>
										
									</tbody>
								</table>
								
								<table class="table"> <!-- table on right side -->
									<tbody>
										<tr>
											<td>Total Net ${rowInvType}: 
											     <c:choose>
														<c:when test="${isRepUser}">
															<b style="font-style: italic;"><c:out
																	value="${encryptedAmount}" /></b>
														</c:when>
														<c:otherwise>
															<b>$<fmt:formatNumber pattern="#,##0.00;-#,##0.00"
																	type="currency" minFractionDigits="2"
																	maxFractionDigits="2" value="${invoice.totalAmt}" /></b>
														</c:otherwise>
													</c:choose>
											</td>
										</tr>
										<tr>
											<td>${rowInvType} Item Total: <b>${invoice.totalQty}</b></td>
										</tr>
										<tr>
											<c:if test="${not empty invoice.origInvoiceNumber && invoice.origInvoiceNumber > 0}">
												<td>Invoice Number: 
													<portlet:renderURL var="getOrigInvoiceDetail" >
														<portlet:param name="action" value="getInvoiceDetail" />
														<portlet:param name="invoiceno" value="${invoice.origInvoiceNumber}" />
													</portlet:renderURL>
													<a href="<%=getOrigInvoiceDetail %>">${invoice.origInvoiceNumber}</a>
												</td>
											</c:if>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="right_content text-center">
								<p>${rowInvType} Number:</p>
								<p>
									<b>${invoice.invoiceNumber}</b>
								</p>
								<a class="btn btn-theme" href="<%=getInvoiceDetail %>">View ${rowInvType}</a>
							</div>
	
						</div>
					</c:if>
					
						<c:choose>
			              <c:when test="${!empty data && !empty data.invoiceLines && data.invoiceLines.size() > 0}">
							<div class="inline_table">
								<table class="table">
									<thead>
										<tr>
											<th>Item Code<br> (ISBN/EAN)</th> 
											<th>Title</th>
											<th class="long_title">Author(s)</th>
											<th>Quantity<br><c:out value="${rowInvType == 'Invoice' ? 'Shipped' : rowInvType}"></c:out></th>
											<c:if test="${rowInvType == 'Credit' || rowInvType == 'Debit'}">
												<th>Quantity Claim</th>
											</c:if>
											<th>Unit Price</th>
											<th>Discount %</th>
											<th>Net Charge</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach items="${data.invoiceLines}" var="invoiceLine" varStatus="count">
										<c:if test="${invoiceLine.invoiceHeaderId == invoice.invoiceHeaderId}">
										<c:set var="isItemCodeVisible" value="${reportingGroups == '0' ? true : reportingGroups.contains(invoiceLine.reportingGroupCode)}" />
										<c:set var="allowedOwnerCode" value="${allReportingGroups.contains(invoiceLine.reportingGroupName) ? true : false}" />
											<tr>
													<td class="${isItemCodeVisible ? '' : 'hide-item'}">
														<c:if test="${not allowedOwnerCode}">
															<c:out value="${isItemCodeVisible ? invoiceLine.isbn : encryptedIsbn}" />
														</c:if>
														<c:if test="${allowedOwnerCode}">
																<c:choose>
																	<c:when test="${isItemCodeVisible}">
																	   <a href="<%=ApplicationConstant.VIEW_CATALOG_BOOK_URL %>${invoiceLine.isbn}"><c:out value="${invoiceLine.isbn}" /></a>
																	</c:when>
																	<c:otherwise>
																	  <b><c:out value="${encryptedIsbn}" /></b>
																	</c:otherwise>
																</c:choose>
														</c:if>
													</td>
													<td class="${isItemCodeVisible ? '' : 'hide-item'}">
														<c:if test="${not allowedOwnerCode}">
															<c:out value="${isItemCodeVisible ? invoiceLine.shortTitle : encryptedTitle}" />
														</c:if>
														<c:if test="${allowedOwnerCode}">
															<c:choose>
																<c:when test="${isItemCodeVisible}">
																   <a href="<%=ApplicationConstant.VIEW_CATALOG_BOOK_URL %>${invoiceLine.isbn}"><c:out value="${invoiceLine.shortTitle}" /></a>
																</c:when>
																<c:otherwise>
																  <b><c:out value="${encryptedTitle}" /></b>
																</c:otherwise>
															</c:choose>
														</c:if>
													</td>
													<td class="${isItemCodeVisible ? '' : 'hide-item'}">
														<c:if test="${not allowedOwnerCode}">
															<c:out value="${isItemCodeVisible ? invoiceLine.shortAuthor : encryptedAuthor}" />
														</c:if>
														<c:if test="${allowedOwnerCode}">
															<a href="javascript:void(0)" class="catalog-jumpto-link" data-attr="short_author"><c:out value="${isItemCodeVisible ? invoiceLine.shortAuthor : encryptedAuthor}" /></a>
														</c:if>
													</td>
													<td>${invoiceLine.quantity}</td>
													<c:if test="${rowInvType == 'Credit' || rowInvType == 'Debit'}">
														<td>${invoiceLine.quantityClaim}</td>
													</c:if>
													<c:choose>
		  												<c:when test="${invoiceLine.lineStatus eq 'Fulfilled' }">
															<td class="${isItemCodeVisible ? '' : 'hide-item'}">
																<c:if test="${isItemCodeVisible}">
																	<fmt:formatNumber pattern="#,##0.00;-#,##0.00" type="number" minFractionDigits="2" maxFractionDigits="2" value="${invoiceLine.unitPrice}" />
																</c:if>
																<c:if test="${not isItemCodeVisible}">
																	<c:out value="${encryptedMsrp}" />
																</c:if>
															</td>
															<td class="${isItemCodeVisible ? '' : 'hide-item'}">
																<c:if test="${isItemCodeVisible}">
																	<fmt:formatNumber pattern="#,##0.00;-#,##0.00" type="number" minFractionDigits="2" maxFractionDigits="2" value="${invoiceLine.discount}" />%
																</c:if>
																<c:if test="${not isItemCodeVisible}">
																	<c:out value="${encryptedMsrp}" />
																</c:if>
															</td>
															<td class="${isItemCodeVisible ? '' : 'hide-item'}">
																<c:if test="${isItemCodeVisible}">
																	<fmt:formatNumber pattern="#,##0.00;-#,##0.00" type="number" minFractionDigits="2" maxFractionDigits="2" value="${invoiceLine.lineAmount}" />
																</c:if>
																<c:if test="${not isItemCodeVisible}">
																	<c:out value="${encryptedMsrp}" />
																</c:if>
															</td>
														</c:when>
														<c:otherwise>
															<td colspan="3"> ${invoiceLine.lineStatus } </td>
														</c:otherwise>
													</c:choose>
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
		<c:if test="${not empty formparam['invoices-loadmore'] && formparam['invoices-loadmore'] eq 'true'}" >
			<div class="row text-center">
				<div class="col-xs-12">
					<button id="loadMore-invoices" class="btn btn-theme loadMore" name="${loadMoreType}"> 
						Load Next <%=ApplicationConstant.PAGE_SIZE %> Results
					</button>
				</div>
			</div>
		</c:if>
		<div id="invoices-loading" class=""></div>
	</div>
	
	</div>
	</div>
</div>

<c:if test="${not empty loadMoreType}">
	<script type="text/javascript">
		document.title='HBG Retailer Portal: Invoice Search Results';
	</script>
</c:if>


<script type="text/javascript">
	function searchByPO(formId){
		document.getElementById(formId).submit();
	}
	
	function searchByStore(formId){
		document.getElementById(formId).submit();
	}
	
	function searchByDestinationZip(formId){
		document.getElementById(formId).submit();
	}
	
	function searchByShipDestinationZip(formId){
		document.getElementById(formId).submit();
	}
	
</script>
<script src="<%=request.getContextPath()%>/js/clear_form.js?t=<%=timestamp%>"
	type="text/javascript"></script>
