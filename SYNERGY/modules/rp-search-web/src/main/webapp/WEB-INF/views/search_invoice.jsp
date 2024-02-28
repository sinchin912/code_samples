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
	<portlet:actionURL var="searchInvoicesURL" name="searchInvoices">
	    <portlet:param name="layoutId" value="<%=layoutId%>" />
	    <portlet:param name="viewId" value="search-invoices" />
	    <portlet:param name="invoice-search-param" value="invoices" />
	    <portlet:param name="search-invoices" value="true" />
	</portlet:actionURL>
	
    <portlet:resourceURL id="findAllStores" var="storeDataURL">
    </portlet:resourceURL>
    
    <portlet:resourceURL id="findTypeAheadStores" var="typeAheadStores">
	</portlet:resourceURL>

	<portlet:resourceURL id="getAccounts" var="accountsURL">
	</portlet:resourceURL>
	
	<!-- .advanced-search -->
  <div class="advanced-search search-options" id="search-invoices">
     <div class="container">
        <springfm:form name="searchInvoiceForm" id="searchInvoiceForm" action="<%=searchInvoicesURL%>" method="get">
                    	
                    <input type="hidden" name="layoutId" value="<%=layoutId%>" />
					<input type="hidden" name="viewId" value="search-invoices" />
					<input type="hidden" name="invoice-search-param" value="invoices" />
					<input type="hidden" name="search-invoices" value="true" />
					<input type="hidden" name="p_p_id" value="rpsearchweb_WAR_rpsearchweb" />
					<input type="hidden" name="p_p_lifecycle" value="1" />
					<input type="hidden" name="p_p_state" value="normal" />
					<input type="hidden" name="p_p_mode" value="view" />
					<input type="hidden" name="_rpsearchweb_WAR_rpsearchweb_javax.portlet.action" value="searchInvoices" />
					<input type="hidden" name="_rpsearchweb_WAR_rpsearchweb_search-invoices" value="true" />
					<input type="hidden" name="_rpsearchweb_WAR_rpsearchweb_viewId" value="search-invoices" />
					<input type="hidden" name="_rpsearchweb_WAR_rpsearchweb_invoice-search-param" value="invoices" />
					<input type="hidden" name="_rpsearchweb_WAR_rpsearchweb_layoutId" value="<%=layoutId%>" />
					<input type="hidden" name="p_auth" id="p_auth_token_Invoice" value="" />
                    	
                    	<div class="row">
		                    <div class="col-sm-4"><h5 class="search-criteria-info">
		                    	<span id="search-invoice-text">Search criteria for Invoices: </span><a class="clear_all" href="javascript:void(0)"> Clear All </a> </h5>
		                    </div>
		                    <div class="col-sm-8 text-right"><a href="javascript:void(0)" class="close_link">
		                    	<b> <i class="glyphicon glyphicon-remove"></i> CLOSE</b></a></div>
		                </div>
                        <div class="row">
                        	<div class="form-group col-sm-3">
                            <div class="input-group search-input eual-to pono_link">
					                <select id="invoice-poNbrOpt" class="invoice-poNbrOpt" name="<portlet:namespace/>invoice-poNbrOpt">
						                <option value="EQ" ${formparam['invoice-poNbrOpt'] == "EQ" ? 'selected="selected"' : ''} >Equal To</option>
										<option value="STARTS_WITH" ${formparam['invoice-poNbrOpt'] == "STARTS_WITH" ? 'selected="selected"' : ''} >Starts With</option>
										<option value="ENDS_WITH" ${formparam['invoice-poNbrOpt'] == "ENDS_WITH" ? 'selected="selected"' : ''} >Ends With</option>
										<option value="CONTAINS" ${formparam['invoice-poNbrOpt'] == "CONTAINS" ? 'selected="selected"' : ''} >Contains</option>
					                </select>
                                <input class="form-control" id="invoice-poNbr" name="<portlet:namespace/>invoice-poNbr" placeholder="PO Number..." value="${fn:replace(formparam['invoice-poNbr'],'&quot;','&quot;')}" />
                            </div>
                        </div>
                        <div class="form-group col-sm-3">
                            <div class="input-group search-input">
                            	<input name="<portlet:namespace/>invoiceno" id="invoiceno" class="form-control" placeholder="Invoice Number..." value="${formparam['invoiceno']}" />
                            	<span class="error text-danger" id="invoiceno-error"></span>
                            </div>
                        </div>
                        <div class="form-group col-sm-3">
                            <div class="input-group search-input">
                                <input class="form-control" id="invoice-referenceNbr" name="<portlet:namespace/>invoice-referenceNbr" placeholder="HBG Reference Number..." value="${formparam['invoice-referenceNbr']}" />
                                <span class="error text-danger" id="invoice-refno-error"></span>
                            </div>
                        </div>
                        
                        <div class="form-group col-sm-3">
                            <div class="search-input date-calendar" id="daterange" aria-label="Text input with segmented button dropdown">
                                <span class="date-icon" id="sizing-addon1">
				                    <i class="glyphicon glyphicon-calendar"></i>
				                </span>
                                <input class="form-control" type="text" placeholder="Search within Date Range"
                                	name="<portlet:namespace/>daterange" readonly="readonly" value="${formparam['daterange']}" />
                                <div class="date-right-arrow">
		                       		<span class="caret"></span>
	                    		</div>
                            </div>
                        </div>
                        </div>
                        <div class="row">
                       <div class="form-group col-sm-3">
					<div class="input-group search-input">
					<input class="form-control accountTypeAhead" maxlength="50" id="invoiceaccountdata" value="${fn:replace(formparam['accountdata'],'&quot;','&quot;')}" name="<portlet:namespace/>accountdata"
									aria-label="Text input with segmented button dropdown" 
									placeholder="All Accounts..."/> 
					<input type="hidden" class="hiddenAccountKey" name="<portlet:namespace/>accountKey" value="${formparam['accountKey']}"/> 
					</div>
				</div>
				<div class="form-group col-sm-3">
					<div class="input-group search-input">
					<input id="invoicestoredataRetain" class="storedataRetain" type="hidden" name="<portlet:namespace/>storedataRetain" value="${formparam['storedataRetain']}" />
						
						<input class="form-control storeTypeAhead" maxlength="50" id="invoicestoredata" name="<portlet:namespace/>storedata" value="${fn:replace(formparam['storedata'],'&quot;','&quot;')}"
									aria-label="Text input with segmented button dropdown" 
									placeholder="All Stores..."/>
					</div>
				</div>
                        
                        
                        <div class="form-group col-sm-3">
                            <div class="input-group search-input destination">
                                <input class="form-control" id="invoice-destination" name="<portlet:namespace/>invoice-destination" placeholder="Destination Zip Code..." value='${formparam["invoice-destination"]}' />
                                <span class="error text-danger" id="invoice-destination-error"></span>
                            </div>
                        </div>
                        
                        <div class="form-group col-sm-3">
							<div class="input-group search-input inclued-title">
								<input class="form-control itemcode" id="invoice-itemcode" name="<portlet:namespace/>invoice-itemcode"
									aria-label="Text input with segmented button dropdown" placeholder="ISBN/Item Code..." value='${formparam["invoice-itemcode"]}' />
								<span class="error text-danger" id="invoice-itemcode-error"></span>
								<input name="<portlet:namespace/>invoice-hiddenitemcode" id="invoice-hiddenitemcode" class="hiddenitemcode" type="hidden" />
							</div>
						</div>
						
               	 	</div> <!-- row ended -->
               	 	
	               	<!-- Search button -->
					<div class="row">
						<div class="col-sm-9 col-xs-6">
								<button type="button" class="btn btn-theme search-btn" onclick="javascript:validateInvoiceForm('Invoice')">Search </button>
						</div>
						<div class="form-group col-sm-3 col-xs-6">
                            <div class="input-group search-input">
                                <input class="form-control" id="invoice-trackingNbr" name="<portlet:namespace/>invoice-trackingNbr" placeholder="Shipment Carrier Tracking Number..." value='${formparam["invoice-trackingNbr"]}' >
                                <span class="error text-danger" id="invoice-trackingNbr-error"></span>
                            </div>
                        </div>
					</div>
					<input type="hidden" id="invoices-offset" name="<portlet:namespace/>invoices-offset" value="0" />
					<input type="hidden" id="invoices-loadmore" name="<portlet:namespace/>invoices-loadmore" />
					<input type="hidden" id="invoices-size" name="<portlet:namespace/>invoices-size" />
               </springfm:form>
            </div>
        </div>

<script src="<%=request.getContextPath()%>/js/search/search_invoice.js?t=<%=timestamp%>"
	type_ahead_stores_url="<%=typeAheadStores %>" portlet_namespace="<portlet:namespace/>"
	store_data_url="<%=storeDataURL%>" accounts_url="<%=accountsURL %>" 
	type="text/javascript"></script>

	
<script>
	
</script>