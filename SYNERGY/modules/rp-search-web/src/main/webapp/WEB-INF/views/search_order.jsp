<%@page import="com.liferay.portal.kernel.portlet.LiferayPortletContext"%>
<%@page import="com.hbg.rp.search.constants.ApplicationConstant"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="com.hbg.rp.search.constants.SearchStaticData"%>
<%@page import="javax.portlet.PortletRequest"%>
<%@page import="javax.portlet.PortletMode"%>
<%@page import="com.liferay.portal.kernel.portlet.PortletURLFactoryUtil"%>

<%@page import="javax.portlet.WindowState"%>
<%@page import="javax.portlet.PortletURL"%>
<%@include file="init.jsp"%>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">


<%		
	LiferayPortletContext context = (LiferayPortletContext) portletConfig.getPortletContext();
	long timestamp = context.getPortlet().getTimestamp();	
	String pageName = themeDisplay.getLayout().getName(themeDisplay.getLocale());
	String layoutId = themeDisplay.getLayout().getPlid() + "";
%>

<portlet:actionURL var="searchOrderURL" name="searchOrders">
	<portlet:param name="layoutId" value="<%=layoutId%>" />
	<portlet:param name="viewId" value="search-orders" />
	<portlet:param name="search" value="true" />
</portlet:actionURL>

<portlet:resourceURL id="findAllStores" var="storeDataURL">
</portlet:resourceURL>

<portlet:resourceURL id="findOrderStatuses" var="orderStatusURL">
</portlet:resourceURL>

<portlet:resourceURL id="findAllFormats" var="formatsURL">
</portlet:resourceURL>

<portlet:resourceURL id="findAllPublishers" var="publishersURL">
</portlet:resourceURL>

<portlet:resourceURL id="findAllImprints" var="imprintsURL">
</portlet:resourceURL>

<portlet:resourceURL id="findPubStatusData" var="pubStatusURL">
</portlet:resourceURL>

<portlet:resourceURL id="findTypeAheadStores" var="typeAheadStores">
</portlet:resourceURL>

<portlet:resourceURL id="getAccounts" var="accountsURL">
</portlet:resourceURL>

<script>
var nrpGetAccountUrl='<%=accountsURL%>';
var nrpSearchOrderNameSpace='<portlet:namespace/>';
</script>
<!-- .advanced-search -->
<div class="advanced-search search-options" id="search-orders">
   <div class="container">
	 <springfm:form name="searchForm"  id="order-search-form" action="<%=searchOrderURL%>" method="get">
	 
			<input type="hidden" name="layoutId" value="<%=layoutId%>" />
			<input type="hidden" name="viewId" value="search-orders" />
			<input type="hidden" name="search" value="true" />
			<input type="hidden" name="p_p_id" value="rpsearchweb_WAR_rpsearchweb" />
			<input type="hidden" name="p_p_lifecycle" value="1" />
			<input type="hidden" name="p_p_state" value="normal" />
			<input type="hidden" name="p_p_mode" value="view" />
			<input type="hidden" name="_rpsearchweb_WAR_rpsearchweb_javax.portlet.action" value="searchOrders" />
			<input type="hidden" name="_rpsearchweb_WAR_rpsearchweb_search" value="true" />
			<input type="hidden" name="_rpsearchweb_WAR_rpsearchweb_viewId" value="search-orders" />
			<input type="hidden" name="_rpsearchweb_WAR_rpsearchweb_layoutId" value="<%=layoutId%>" />
			<input type="hidden" name="p_auth" id="p_auth_token_Orders" value="" />
			
             <div class="row">
				<div class="col-sm-4">
					<h5 class="search-criteria-info">Search criteria for
						Orders: <a class="clear_all" href="javascript:void(0)"> Clear All </a>
					</h5>
				</div>
				<div class="col-sm-8 text-right">
					<a href="javascript:void(0)" class="close_link"><b><i class="glyphicon glyphicon-remove"></i> CLOSE</b></a>
				</div>
			</div>
			
			<div id="search">
			 <div class="row">
				<div class="form-group col-sm-3">
					<div class="input-group search-input eual-to pono_link">
						<select id="pono_operator" class="pono_operator"
							name="<portlet:namespace/>pono_operator">
							<option value="EQ" ${formparam['pono_operator'] == "EQ" ? 'selected="selected"' : ''} >Equal To</option>
							<option value="STARTS_WITH" ${formparam['pono_operator'] == "STARTS_WITH" ? 'selected="selected"' : ''} >Starts With</option>
							<option value="ENDS_WITH" ${formparam['pono_operator'] == "ENDS_WITH" ? 'selected="selected"' : ''} >Ends With</option>
							<option value="CONTAINS" ${formparam['pono_operator'] == "CONTAINS" ? 'selected="selected"' : ''} >Contains</option>
						</select>
						<input class="form-control" id="pono" maxlength="24" name="<portlet:namespace/>pono"
							placeholder="PO Number..." value="${fn:replace(formparam['pono'],'&quot;', '&quot;')}" />
							<span class="error text-danger" id="ponoError"></span>
					</div>
				</div>
				<div class="form-group col-sm-3">
					<div class="input-group search-input orderstatus">
						<select id="statusopt" class="statusopt" name="<portlet:namespace/>statusopt">
							<option value="" selected>All Order Statuses</option>
							<c:forEach items="${statuses}" var="status" varStatus="count">
								<option value="${status}" ${formparam['statusopt'] == status ? 'selected="selected"' : ''} >${status}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group col-sm-3">
					<div class="input-group search-input">
						<input class="form-control" id="refno" value="${formparam['refno']}" 
							name="<portlet:namespace/>refno" placeholder="HBG Reference Number...">
							<span class="error text-danger" id="refnoError"></span>
					</div>
				</div>
				<div class="form-group col-sm-3">
					<div class="search-input date-calendar" id="daterange" aria-label="Text input with segmented button dropdown">
                       <span class="date-icon" id="sizing-addon1">
                       		<i class="glyphicon glyphicon-calendar"></i>
                       </span>
                       <input class="form-control" type="text" placeholder="Search within Date Range" name="<portlet:namespace/>daterange"
                       		readonly="readonly" value="${formparam['daterange']}" />
                       <div class="date-right-arrow">
                       <span class="caret"></span>
                       </div>
                   </div>
                </div>
                </div>
                <div class="row">
				<div class="form-group col-sm-3">
					<div class="input-group search-input">
					<input class="form-control accountTypeAhead" maxlength="50" id="accountdata" value="${fn:replace(formparam['accountdata'],'&quot;', '&quot;')}" name="<portlet:namespace/>accountdata"
									aria-label="Text input with segmented button dropdown" 
									placeholder="All Accounts..."/> 
					<input type="hidden" class="hiddenAccountKey" name="<portlet:namespace/>accountKey" value="${formparam['accountKey']}"/> 
					</div>
				</div>
				<div class="form-group col-sm-3">
					<div class="input-group search-input">
					<input class="storedataRetain" type="hidden" name="<portlet:namespace/>storedataRetain" value="${formparam['storedataRetain']}" />
						
						<input class="form-control storeTypeAhead" maxlength="50" id="storedata" name="<portlet:namespace/>storedata" value="${fn:replace(formparam['storedata'],'&quot;', '&quot;')}"
									aria-label="Text input with segmented button dropdown" 
									placeholder="All Stores..."/>
					</div>
				</div>
				<div class="form-group col-sm-3 ">
					<div class="input-group search-input destination">
						<input class="form-control" id="address" value='${formparam["address"]}' 
							name="<portlet:namespace/>address" 
							aria-label="Text input with segmented button dropdown" placeholder="Destination Zip Code...">
							<span class="error text-danger" id="addressError"></span>
					</div>
				</div>
				<!-- <div class="col-sm-3">
					<div class="row"> -->
				<!-- <div class="form-group col-sm-6">
							<div class="input-group search-input item_code">
								<input class="form-control" id="itemcode" name="itemcode"
									aria-label="Text input with segmented button dropdown"
									placeholder="ISBN/Item Code...">
							</div>
						</div> -->

						<div class="form-group col-sm-3">
							<div class="input-group search-input inclued-title">
								<input class="form-control itemcode" maxlength="20" id="itemcode" name="<portlet:namespace/>itemcode"
									aria-label="Text input with segmented button dropdown" value='${formparam["itemcode"]}' 
									placeholder="ISBN/Item Code...">
									<span class="error text-danger" id="itemcodeError"></span>
								<input name="<portlet:namespace/>hiddenitemcode" id="hiddenitemcode" class="hiddenitemcode"
									 type="hidden" value="${formparam['hiddenitemcode']}"  />
								<!-- <div class="input-group-btn">
									<button id="" type="button" class="btn lookupButton"
										data-toggle="modal" data-target="#myModal">
										Lookup <i class="fa fa-search"></i>
									</button>
								</div> -->
							</div>
						</div>
			</div>
			</div>
			<div class="row">
				<div class="col-sm-12">
					<button type="button" onclick="javascript:validate()" class="btn btn-theme search-btn">Search</button>
				</div>
			</div>
			<input id="order-offset" name="<portlet:namespace/>order-offset" type="hidden" 
				value="${formparam['order-offset'] }" />
			<input id="order-loadmore" name="<portlet:namespace/>order-loadmore" type="hidden" 
				value="${formparam['order-loadmore'] }" />
			<input id="order-rowCount" name="<portlet:namespace/>order-rowCount" type="hidden" 
				value="${formparam['order-rowCount'] }" />
		</springfm:form>

	</div>
</div>

<script src="<%=request.getContextPath()%>/js/search/search_order.js?t=<%=timestamp%>"
	store_url="<%=typeAheadStores %>" portlet_namespace="<portlet:namespace/>"
	accounts_url="<%=accountsURL %>" type="text/javascript"></script>
