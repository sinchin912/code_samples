<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="javax.portlet.PortletRequest"%>
<%@page import="com.liferay.portal.kernel.portlet.PortletURLFactoryUtil"%>
<%@page import="javax.portlet.PortletURL"%>
<%@page import="com.liferay.portal.kernel.exception.SystemException"%>
<%@page import="com.liferay.portal.kernel.exception.PortalException"%>
<%@page import="com.liferay.portal.kernel.service.LayoutLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.service.GroupLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.model.Layout"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayPortletContext"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="init.jsp"%>
<%@page import="com.liferay.portal.kernel.theme.ThemeDisplay" %>
<%@page import="com.liferay.portal.kernel.model.Role"%>
<%@page import="java.util.List"%>

<portlet:actionURL name="searchOrders" var="searchOrderURL" />
<portlet:resourceURL var="titleDataURL" id="getTitleData"></portlet:resourceURL>
<input type="hidden" name="sOrder" id="sOrder" value="${searchOrderURL}" />

<portlet:resourceURL id="findAllStores" var="storeDataURL">
</portlet:resourceURL>
<%
	String pageName = themeDisplay.getLayout().getName(themeDisplay.getLocale());
	String layoutId = themeDisplay.getLayout().getPlid() + "";
	
	String jspPage = (String) request.getAttribute("jspPage");
	
	List<Role> loggedInUserRoles = themeDisplay.getUser().getRoles();
	String themeImagesfolder = themeDisplay.getPathThemeImages();
%>


<% if (pageName.equalsIgnoreCase("Recent Activity")) { %>
	<c:set var="isRecentActivityPage" value="true"></c:set>
<% } %>

<% if (jspPage.equalsIgnoreCase("search-order")) { %>
	<c:set var="isOrderSearchPage" value="true"></c:set>
<% } %>

<% if (jspPage.equalsIgnoreCase("search-shipment")) { %>
	<c:set var="isShipmentSearchPage" value="true"></c:set>
<% } %>

<% if (jspPage.equalsIgnoreCase("search-invoices")) { %>
	<c:set var="isInvoiceSearchPage" value="true"></c:set>
<% } %>

<% if (jspPage.equalsIgnoreCase("search-debits")) { %>
	<c:set var="isDebitSearchPage" value="true"></c:set>
<% } %>

<% if (jspPage.equalsIgnoreCase("search-credits")) { %>
	<c:set var="isCreditSearchPage" value="true"></c:set>
<% } %>

<% if (jspPage.equalsIgnoreCase("search-transactions")) { %>
	<c:set var="isTransactionSearchPage" value="true"></c:set>
<% } %>

<% if (jspPage.equalsIgnoreCase("search-catalogs")) { %>
	<c:set var="isCatalogSearchPage" value="true"></c:set>
<% } %>


<c:forEach var="role" items="<%=loggedInUserRoles%>">
	<c:set var="userRole" value="${role.getName()}"></c:set>
	<jsp:useBean id="userRole" type="java.lang.String" />

	<c:if test='<%=userRole.equalsIgnoreCase("all")%>'>
    	<c:set var="ALL" value="true"></c:set>
    </c:if>

	<c:if test='<%=userRole.equalsIgnoreCase(ApplicationConstant.NRP_VIEW_AND_SEARCH_CATALOG_DATA)%>'>
		<c:set var="NRP_VIEW_AND_SEARCH_CATALOG_DATA" value="true"></c:set>
	</c:if>

	<c:if test='<%=userRole.equalsIgnoreCase(ApplicationConstant.NRP_VIEW_AND_SEARCH_INVOICE_CREDIT_DEBIT_DATA)%>'>
		<c:set var="NRP_VIEW_AND_SEARCH_INVOICE_CREDIT_DEBIT_DATA" value="true"></c:set>
	</c:if>
	
	<c:if test='<%=userRole.equalsIgnoreCase("Administrator")%>'>
       	<c:set var="Administrator" value="true"></c:set>
    </c:if>
    
</c:forEach>

 <c:if test="${(empty Administrator && !Administrator)}">
	 <style>
		  .portlet > .portlet-topper {
    			display: none; }
	 </style>
 </c:if>

<div class="searchbar">
	<div class="container">
		<div class="row">
			<div class="col-md-8 col-sm-6">
				<div class="input-group search-input">
					<div class="custom-search-select" id="search-select">
						<select name="search-select-dropdown" id="search-select-dropdown">
							<option value="0">Search for...</option>
							<option value="orders">Orders</option>
							<option value="shipments">Shipments</option>
							<c:if
								test="${(not empty ALL && ALL) || (not empty NRP_VIEW_AND_SEARCH_INVOICE_CREDIT_DEBIT_DATA && NRP_VIEW_AND_SEARCH_INVOICE_CREDIT_DEBIT_DATA)}">
								<option value="invoices">Invoices</option>
								<option value="debits">Debits</option>
								<option value="credits">Credits</option>
								<option value="transactions">All Financial Transactions</option>
							</c:if>
							<c:if
								test="${(not empty ALL && ALL) || (not empty NRP_VIEW_AND_SEARCH_CATALOG_DATA && NRP_VIEW_AND_SEARCH_CATALOG_DATA)}">
								 <option value="catalogs">Titles/Items</option>
							</c:if>
						</select>
					</div>
				</div>
			</div>
			<div class="col-md-4 col-sm-6 text-right hidden">
				<!-- Single button -->
				<a href="javascript:void(0)" class="notification"><i
					class="fa fa-bell-o" aria-hidden="true"></i> Notifications (6)</a>
				<button type="button" class="btn btn-theme btn_switch_retailer">
					Switch Retailer <img
						src="<%=themeImagesfolder%>/ap/icons/switch.svg"
						style="width: 16px;">
				</button>
			</div>
		</div>
	</div>
</div>

<div id="allSearchPage" class="d-none">
	<jsp:include page="search_order.jsp"></jsp:include>
	<jsp:include page="search_shipment.jsp"></jsp:include>
	<jsp:include page="search_invoice.jsp"></jsp:include>
	<jsp:include page="search_debit.jsp"></jsp:include>
	<jsp:include page="search_credit.jsp"></jsp:include>
	<jsp:include page="search_transaction.jsp"></jsp:include> 
	<jsp:include page="search_catalog.jsp"></jsp:include>
</div>

<% if (pageName.equalsIgnoreCase("Orders")) { %>
<c:set var="isOrdersPage" value="true"></c:set>
<% } %>

<c:if test="${fn:contains(renderjsp, 'detail')}">
	<c:set var="isDetailPage" value="true"></c:set>
</c:if>
<div class="happi-border hidden-xs"></div>

<c:set var="isShipmentSearch" value="${formparam['search-shipment'] == 'true'}"></c:set>
<c:set var="isInvoiceSearch" value="${formparam['search-invoices'] == 'true' || formparam['search-debits'] == 'true' || formparam['search-credits'] == 'true' || formparam['search-transactions'] == 'true'}"></c:set>
<c:set var="isCatalogSearch" value="${formparam['search-catalogs'] == 'true'}"></c:set>
<c:set var="isOrderSearch"
	value="${ (not empty formparam['search-order'] && formparam['search-order'] == 'true') || 
				(not empty isOrdersPage && isOrdersPage == 'true') }"></c:set>

<c:choose>
	<c:when test="${ not empty isDetailPage && isDetailPage == 'true' }">
		<!-- No header in case of detail pages. -->
	</c:when>
	
	<c:when test="${isShipmentSearch || isInvoiceSearch || isCatalogSearch || isOrderSearch}">
		<div class="main-wrapper" style="margin-top:30px !important;">
			<div class="search-result-bar">
				<div class="container">
					<div class="row">
						<div class="col-xs-9 text-left">
							<!-- This content will be pushed dynamically from search jsp in class 'text-left' -->
						</div>
						<div class="col-xs-3 text-right">
							<!-- This content will be pushed dynamically from search jsp in class 'text-right' -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</c:when>

</c:choose>

<!-- .inclued-title -->
<!-- Modal -->
<div class="advanced-search modal fade" id="myModal" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
	style="display: none;">
	<div class="modal-dialog catalog-modal-width">
		<!-- Modal content-->
		<div class="modal-content inclued-title">
			<div class="title-header">
				<h3 class="modal-title title text-center">Lookup an ISBN/Item
					Code</h3>
			</div>
			<div class="modal-body">
				<div class="body-content">
					<div class="content-block">
						<blockquote>
							<p>Search for your title from the options below.</p>
						</blockquote>
					</div>
					<!-- .advanced-search -->
					<div class="advanced-search-title">
						<div class="search-result-title">
							<div class="col-sm-7">
								<span id="no_of_results"></span><br />
							</div>
							<div class="col-sm-5">
								<span id="bookSelected"></span><br />
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<div class="title-search">
									<div onclick="getProductData();" id="searchbuttondiv"
										class="btn-group dropup search-input">
										<button type="button" class="btn btn-theme-gray">
											Search for Title <span
												class="glyphicon glyphicon-search text-right"></span>
										</button>
									</div>
									<a id="clearButton" class="clear-all"
										onclick="fnClrModalSearchForm();" href="javascript:void(0)">Clear
										All</a>
								</div>
							</div>
							<div class="col-sm-6 text-right">
								<button id="cancelButton" type="button" class="btn btn-theme"
									data-dismiss="modal">Cancel</button>
								<button id="doneButton" type="button" class="btn btn-theme-gray"
									data-dismiss="modal">Done</button>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-7">
								<div class="form-group col-xs-6">
									<div class="input-group search-input">
										<input id="modeltitle" class="form-control"
											placeholder="Title..." onkeyup="enableSearchButton(this);">
									</div>
								</div>
								<div class="form-group col-xs-6">
									<div class="input-group search-input">
										<input id="modelisbn" class="form-control"
											placeholder="Item Code..."
											onkeyup="enableSearchButton(this);">
									</div>
								</div>
							</div>
							<div class="col-sm-5">
								<div class="form-group col-xs-12">
									<div class="input-group search-input">
										<input class="form-control" id="subject" name="subject"
											placeholder="Subject or BISAC..."
											onkeyup="enableSearchButton(this);">
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-7">
								<div class="form-group col-xs-6">
									<div class="select-offset-top input-group search-input">
										<select id="formatdata">
											<option selected hidden value="All Formats">All
												Formats</option>
											<c:forEach items="${formats}" var="formats" varStatus="count">
												<option value="${formats}">${formats}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="form-group col-xs-6">
									<div class="select-offset-top input-group search-input">
										<select id="publisherdata">
											<option selected hidden value="">All Publishers</option>
											<c:if
												test="${not empty publishers && publishers.length() > 0}">
												<c:forEach begin="0" end="${publishers.length()-1}"
													var="index">
													<option
														value="${publishers.getJSONObject(index).getString('publisher')}">${publishers.getJSONObject(index).getString('publisher')}</option>
												</c:forEach>
											</c:if>
										</select>
									</div>
								</div>
							</div>
							<div class="col-sm-5">
								<div class="form-group col-xs-12">
									<div class="select-offset-top input-group search-input">
										<select id="imprintdata">
											<option selected hidden value="">All Imprints</option>
											<c:if test="${not empty imprints  && imprints.length() > 0}">
												<c:forEach begin="0" end="${imprints.length()-1}"
													var="index">
													<option
														value="${imprints.getJSONObject(index).getString('imprint')}">${imprints.getJSONObject(index).getString('imprint')}</option>
												</c:forEach>
											</c:if>
										</select>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-7">
								<div class="select-offset-top form-group col-xs-6">
									<select id="pubstatusdata">
										<option selected hidden value="">All Pub Status</option>
										<c:if test="${not empty pubStatus  && pubStatus.length() > 0}">
											<c:forEach begin="0" end="${pubStatus.length()-1}"
												var="index">
												<option
													value="${pubStatus.getJSONObject(index).getString('desc')}">
													${pubStatus.getJSONObject(index).getString('desc')}</option>
											</c:forEach>
										</c:if>
									</select>
								</div>
								<div class="form-group col-xs-6">
									<div class="input-group search-input">
										<input class="form-control" name="author" id="author"
											placeholder="Author..." onkeyup="enableSearchButton(this);">
									</div>
								</div>
							</div>
							<div class="col-sm-5">
								<div class="form-group col-xs-12">
									<div class="input-group search-input">
										<input class="form-control" name="authorresidence"
											id="authorresidence"
											placeholder="Author Residence (City/State)..."
											onkeyup="enableSearchButton(this);">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- /.advanced-search -->
			</div>
		</div>
		<div></div>
	</div>
</div>
<!-- /.inclued-title -->

<%
LiferayPortletContext context = (LiferayPortletContext) portletConfig.getPortletContext();
long timestamp = context.getPortlet().getTimestamp();
%>
<script src="<%=request.getContextPath()%>/js/main.js?t=<%=timestamp%>"
	type="text/javascript"></script>
<script
	src="<%=request.getContextPath()%>/js/search/search_common.js?t=<%=timestamp%>"
	store_data_url="<%=storeDataURL%>"
	portlet_namespace="<portlet:namespace/>"
	title_data_url="<%=titleDataURL%>"
	<%-- change_password_url="<%=liferayURL.toString() %>" --%>
	jspPage="<%=jspPage%>"
	type="text/javascript"></script>
<script
	src="<%=request.getContextPath()%>/js/daterangepicker.js?t=<%=timestamp%>"
	type="text/javascript"></script>
<script
	src="<%=request.getContextPath()%>/js/clear_form.js?t=<%=timestamp%>"
	type="text/javascript"></script>
<script>
	$('body').click(function() {
		if ($('ul.navright li:last-child').hasClass('custom-open')) {
			$('ul.navright li:last-child').removeClass('open');
			$('ul.navright li:last-child').removeClass('custom-open');
		}
		if ($('ul.navright li:last-child').hasClass('open')) {
			$('ul.navright li:last-child').addClass('custom-open');
		}
	});
</script>
