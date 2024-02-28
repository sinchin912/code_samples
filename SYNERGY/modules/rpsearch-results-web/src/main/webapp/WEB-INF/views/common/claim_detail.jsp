<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">
<link
href="https://fonts.googleapis.com/css?family=Lato:100,300,300i,400,400i,700|Merriweather:300,300i,400,400i,700"
rel="stylesheet">
<%@page import="com.hbg.rp.search.constants.ClaimConstants"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayPortletContext"%>
<%@page import="com.hbg.rp.search.util.IPortalMappingsUtil"%>
<%@page import="com.hbg.rp.search.model.ClaimDTO"%>
<%@page import="java.util.Map"%>
<%@page import="com.liferay.portal.kernel.json.JSONArray"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@include file="init.jsp"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
LiferayPortletContext context = (LiferayPortletContext) portletConfig.getPortletContext();
long timestamp = context.getPortlet().getTimestamp();

String pageName = themeDisplay.getLayout().getName(themeDisplay.getLocale());
IPortalMappingsUtil portalMappingsUtil = (IPortalMappingsUtil)request.getAttribute("portalMappingsUtil");
ClaimDTO claimDTO = (ClaimDTO) request.getAttribute("data");
String requesterEmail = themeDisplay.getUser().getEmailAddress();
String zendeskErrorMsg = ClaimConstants.ZENDESK_ERROR_MSG;
JSONArray claimJsonArray = (JSONArray) request.getAttribute("claimTypeJsonArray");
%>

<c:set var="zendeskErrorMsg" value="<%=zendeskErrorMsg %>" />
<c:set var="invoiceno" value="${data.invoiceNumber }" scope="page" />
<portlet:resourceURL var="submitClaimForm" id="submitClaimForm" />

<portlet:renderURL var="getInvoiceDetail">
<portlet:param name="action" value="getInvoiceDetail" />
<portlet:param name="invoiceno" value="${invoiceno}" />
<portlet:param name="claimCallingPage" value="claimPage" />
</portlet:renderURL>


<div style="display:none;" id="loader-wrapper"><div id="loader"></div></div>


<div class="modal fade" id="successModalData" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog claim-modal">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-body">
					<div class="text-center">
						<h3 class="my-3 font-merriweather">
							<i class="fa fa-check check-success" aria-hidden="true"></i>
							<span> Claim Submitted Successfully </span>
						</h3>
						<h5 class=" font-merriweather text-left">Thank you for submitting your claim. Please check your email for updates about your submissions.</h5>
					  
						<a class="btn btn-theme search-btn mt-1 btn-ok" href="<%=getInvoiceDetail %>">Ok</a>
					</div>
				</div>
			</div>

		</div>
	</div>
	
<div class="modal fade" id="erroModalData" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog claim-modal">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-body">
					<div class="text-center">
					  
						   <h3 class="my-3 font-merriweather">
							<i class="fa fa-exclamation-circle text-warning" aria-hidden="true"></i>
							<span> Error </span>
						</h3>
						<h5 class="text-left" id="errorClaimMsg"></h5>
					  
						<button type="button" class="btn btn-theme search-btn mt-1 btn-ok" data-dismiss="modal">OK</button>
					</div>


				</div>
			</div>

		</div>
	</div>

<div class="main_wrapper" style="margin: 30px auto;">
<div class="container">
	<!-- .order-details -->
	<div class="row">
		<div class="col-md-12">
			<div class="recent-orders order-details">
				<div class="row headding-row" id="heading-part">
					<div class="col-md-8 p-0">
						<h2 class="table_hedding">
							Build a Claim for invoice <span>${invoiceno}</span>
						</h2>
						<p>Add items from your invoice below to start building a claim.</p>
					</div>
				</div>
				<div class="row">

					<div id="detail-part">
						<!-- INVOICE - TAB CODE: STARTED -->
						<div id="invoice-detail" class="order_overview transaction-detail">					
							<div class="tab-content">
								<div role="tabpanel" class="tab-pane active" id="invoice-items">
									<div class="table_outer">
										<div class="inline_table">
											<table class="table">
												<thead>
													<tr class="invoice-cliam-column">
														<th>#</th>
														<th>Item Code<br> (ISBN/EAN)</th>
														<th>Title</th>
														<th class="long_title">Author(s)</th>
														<th>Quantity Shipped</th>
														<th>Previously Claimed</th>
														<th>Unit Price</th>
														<th>Discount %</th>
														<th>Net Charge</th>
														<th>Build the<br> Claim</th>
													</tr>
												</thead>
												<tbody>
													<c:set value="${data }" scope="request" var="data" />
													<p id="claimTypeJsonArray" style="display:none;"> <%= claimJsonArray %> </p>
													<jsp:include page="invoice_claim_line_template.jsp"></jsp:include>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
							<div class="border-gray p-2">
								<div class="bg-light-gray border-gray p-3">
									<h3 class="table_hedding m-0 mb-1">Submit a Claim for Invoice <span>${invoiceno}</span></h3>
									<h5 class="font-merriweather">All claims must be made within ${invoiceGeneratedDays} days from invoice date. <br>Prices
										subject to change without notice.</h5>
									<p class="w-100 d-table"><a href="/web/guest/faqs" class="nav-header link-invoice">
                       				<b>View Claims FAQ</b> <i class="fa fa-angle-right"></i></a></p>
									<!-- <button type="button" class="btn btn-theme search-btn mt-1">Start a Claim</button> -->
									<div id="pannelClaimEmpty" class="table_outer mt-1"> 
										<div class="inline_table" > 
											<div class="bg-white w-100 h-100 text-center p-4">
												<a href="#main-content" style="text-align: center;"> 
													<b><i class="fa fa-plus"></i> Add items from invoice above</b></a>
											</div>
										</div> 
									</div>
									<form:form action="#" method="POST" modelAttribute="submitClaimDTO" id="submitClaimFm" name="submitClaimFm" >
									
									<c:forEach items="${data.invoiceDetail}" var="invoiceDetail" varStatus="count">
										<c:if test="${count.index eq '0'}">
											<form:hidden path="submitClaimHeader.billToKey" value="${invoiceDetail.billToKey}"/>
											<form:hidden path="submitClaimHeader.billToName" value="${invoiceDetail.billToName}"/>
											<form:hidden path="submitClaimHeader.invoiceNumber" value="${data.invoiceNumber}"/>
											<form:hidden path="submitClaimHeader.invoiceHeaderKey" value="${invoiceDetail.invoiceHeaderId}"/>
											<form:hidden path="submitClaimHeader.invoceDate" value="${invoiceDate}"/>
											<form:hidden path="submitClaimHeader.claimFormPage" value="${claimFormPage}"/>
											<form:hidden path="submitClaimHeader.accountNumber" value="${accountNumber}"/>
											<c:set var="accountName" value="${invoiceDetail.billToName}" />
										</c:if>
									</c:forEach>
									<input id="submitClaimHeader.claimTypeKey" name="submitClaimHeader.claimTypeKey" value="" type="hidden">
									
										<div id="pannelClaimData" class="table_outer mt-2" style="float:left;">
											<div class="inline_table">
												<table id="tblClaimForm" class="table claim-table">
													<thead>
														<tr class="total-row invoice-total gray-color-row">
															<td colspan="11">
																<div class="form-inline">
																	<div class="row">
																		<div class="col-sm-6 pr-0">
																			<h4>Damage, Shortage and Wrong Book Claims</h4>
																		</div>
																		<div class="col-sm-6  text-end text-right">
																			<div class="form-group ml-auto">
																			<input onpaste="retailclaimNumberPasteValidation(event)" maxlength="25" type="text" name="submitClaimHeader.retailerClaimNumber"  id="submitClaimHeader.retailerClaimNumber" placeholder="Retailer Claim #:" name="submitClaimHeader.retailerClaimNumber" class="form-control w-100">
																			<input maxlength="100" type="text" name="lblretailerClaimNumber"  id="txtretailerClaimNumber" placeholder="Retailer Claim #:" class="form-control w-100">
																			</div>
																		</div>
																		<%-- <div class="col-sm-2 pl-0">
																			<div class="form-group w-100">
																			
																				<select class="form-control w-100 p-0" id="claimTypeSelect" style="padding-top:4px;">
																					<c:forEach begin="0" end="${claimTypeJsonArray.length() -1}" var="i">
																					<option value="${claimTypeJsonArray.getJSONObject(i).getString('claimTypeKey')}">${claimTypeJsonArray.getJSONObject(i).getString('claimTypeDesc')}</option>
																					</c:forEach>
																				</select>
																			</div>
																		</div> --%>
																	</div>
																</div>
															</td>
														
														</tr>
														<tr>
															<th width="30">#</th>
															<th>Item Code<br> (ISBN/EAN)</th>
															<th class="long_title">Title</th>
															<!-- <th width="120">Short Author</th> -->
															<th width="150">Wrong Book<br> (Type ISBN/EAN)</th>
															<th width="80">Quantity Shipped</th>
															<th width="80">Previously Claimed</th>
															<th width="90">Select To Reship</th>
															<th width="140">Claim Type</th>
															<th width="80">Quantity <br> Claimed</thClaimed>
															<th width="80">Remove From Claim</th>
														</tr>
													</thead>
													<tbody>
													<tr></tr>
													<tr></tr>
														<tr class="total-row invoice-total gray-color-row">
															<td colspan="11">&nbsp;</td>
															
														</tr>
													</tbody>
												</table>
											</div>
										</div>
										
										<div class="claim-form-content w-100 d-inline-block">
											<div class="row">
												<div class="col-sm-12">
													<label>Account Name:</label>
													<p>${accountName} </p>
												</div>
											</div>
											<div class="row">
												<div class="col-sm-12">
													<label>Requestor:</label>
													<p><%=requesterEmail%></p>
												</div>
											</div>
											<div class="row">
												<div class="col-sm-12">
													<label>Additional comments <em>(please add different reship
															address here if needed)</em>:</label>
															<textarea onkeyup ="claimCommentValidation(event)" id="submitClaimHeader.claimComments" name="submitClaimHeader.claimComments" class="form-control mt-1" maxlength="1650" rows="3"></textarea>
												
												</div>
											</div>
										</div>
										<p id="claimErrorMessage" class="text-danger">
											Please adjust the quantities claimed above. You may not claim a higher quantity for each product than on your invoice.
										</p>
										<button id="btnSubmitClaim" type="submit" value="Submit"
											class="btn btn-theme-gray mt-1">Submit Claim</button>												
										<input type="hidden" id="submaitClaimFormUrl" name="submaitClaimFormUrl" value="<%=submitClaimForm %>">	
										<input type="hidden" id="portletNameSpaceValue" name="portletNameSpaceValue" value="<portlet:namespace/>">
										<input type="hidden" id="zendeskErrorMsg" name="zendeskErrorMsg" value="${zendeskErrorMsg}">	
										</div>
									</form:form>
							</div>
						</div> <!-- INVOICE - TAB CODE: STARTED -->
					</div> <!-- detail-part ended -->
				</div> <!-- /.order-details -->
			</div>
		</div>
	</div>
</div>
<!--======= /.Wrapper =========-->
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script
src="<%=request.getContextPath()%>/js/claim_detail.js?t=<%=timestamp%>"
portlet_namespace_claim="<portlet:namespace/>" submit_data="<%=submitClaimForm %>" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/clear_form.js?t=<%=timestamp%>"
	type="text/javascript"></script>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"
	integrity="sha384-nvAa0+6Qg9clwYCGGPpDQLVpLNn0fRaROjHqs13t4Ggj3Ez50XnGQqc/r8MhnRDZ"
	crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"
	integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd"
	crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/es6-object-assign@1.1.0/dist/object-assign-auto.min.js"></script>