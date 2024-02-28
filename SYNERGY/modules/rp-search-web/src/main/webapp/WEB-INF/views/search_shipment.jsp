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
	<portlet:actionURL var="searchShipmentsURL" name="searchShipments">
	    <portlet:param name="layoutId" value="<%=layoutId%>" />
	    <portlet:param name="viewId" value="search-shipments" />
	    <portlet:param name="search-shipment" value="true" />
	</portlet:actionURL>
	
	<portlet:resourceURL var="titleDataURL" id="getTitleData">
    </portlet:resourceURL>
    
    <portlet:resourceURL id="findAllStores" var="storeDataURL">
    </portlet:resourceURL>
    
    <portlet:resourceURL id="findTypeAheadStores" var="typeAheadStores">
	</portlet:resourceURL>

	<portlet:resourceURL id="getAccounts" var="accountsURL">
	</portlet:resourceURL>
	
<!-- .advanced-search -->
   <div class="advanced-search search-options"  id="search-shipments">
        <div class="container">
             <springfm:form name="searchShipmentForm"  id="searchShipmentForm" action="<%=searchShipmentsURL%>" method="get">
                  	
                  	<input type="hidden" name="layoutId" value="<%=layoutId%>" />
					<input type="hidden" name="viewId" value="search-shipments" />
					<input type="hidden" name="search-shipment" value="true" />
					<input type="hidden" name="p_p_id" value="rpsearchweb_WAR_rpsearchweb" />
					<input type="hidden" name="p_p_lifecycle" value="1" />
					<input type="hidden" name="p_p_state" value="normal" />
					<input type="hidden" name="p_p_mode" value="view" />
					<input type="hidden" name="_rpsearchweb_WAR_rpsearchweb_javax.portlet.action" value="searchShipments" />
					<input type="hidden" name="_rpsearchweb_WAR_rpsearchweb_search-shipment" value="true" />
					<input type="hidden" name="_rpsearchweb_WAR_rpsearchweb_viewId" value="search-shipments" />
					<input type="hidden" name="_rpsearchweb_WAR_rpsearchweb_layoutId" value="<%=layoutId%>" />
					<input type="hidden" name="p_auth" id="p_auth_token_Shipments" value="" />
                  
                    	<div class="row">
		                    <div class="col-sm-4"><h5 class="search-criteria-info">
		                    	Search criteria for Shipments: <a class="clear_all" href="javascript:void(0)"> Clear All </a> </h5>
		                    </div>
		                    <div class="col-sm-8 text-right"><a href="javascript:void(0)" class="close_link">
		                    	<b> <i class="glyphicon glyphicon-remove"></i> CLOSE</b></a></div>
		                </div>
                        <div class="row">
                        	<div class="form-group col-sm-3">
                            <div class="input-group search-input eual-to pono_link">
					                <select id="ship-poNbrOpt" class="ship-poNbrOpt" name="<portlet:namespace/>ship-poNbrOpt">
						                <option value="EQ" ${formparam['ship-poNbrOpt'] == "EQ" ? 'selected="selected"' : ''} >Equal To</option>
										<option value="STARTS_WITH" ${formparam['ship-poNbrOpt'] == "STARTS_WITH" ? 'selected="selected"' : ''} >Starts With</option>
										<option value="ENDS_WITH" ${formparam['ship-poNbrOpt'] == "ENDS_WITH" ? 'selected="selected"' : ''} >Ends With</option>
										<option value="CONTAINS" ${formparam['ship-poNbrOpt'] == "CONTAINS" ? 'selected="selected"' : ''} >Contains</option>  
					                </select>
                                <input class="form-control" id="ship-poNbr" name="<portlet:namespace/>ship-poNbr" placeholder="PO Number..." value="${fn:replace(formparam['ship-poNbr'],'&quot;', '&quot;')}" />
                            </div>
                        </div>
                        <div class="form-group col-sm-3">
                            <div class="input-group search-input">
                            	<input name="<portlet:namespace/>ship-invoiceNbr" id="ship-invoiceNbr" class="form-control" placeholder="Invoice Number..." value="${formparam['ship-invoiceNbr']}" />
                            	<span class="error text-danger" id="ship-invoiceNbr-error"></span>
                            </div>
                        </div>
                        <div class="form-group col-sm-3">
                            <div class="input-group search-input">
                                <input class="form-control" id="ship-referenceNbr" name="<portlet:namespace/>ship-referenceNbr" placeholder="HBG Reference Number..." value="${formparam['ship-referenceNbr']}" />
                                <span class="error text-danger" id="ship-referenceNbr-error"></span>
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
					<input class="form-control accountTypeAhead" maxlength="50" id="shipmentaccountdata" value="${fn:replace(formparam['accountdata'],'&quot;', '&quot;')}" name="<portlet:namespace/>accountdata"
									aria-label="Text input with segmented button dropdown" 
									placeholder="All Accounts..."/> 
					<input type="hidden" class="hiddenAccountKey" name="<portlet:namespace/>accountKey" value="${formparam['accountKey']}"/> 
					</div>
				</div>
				<div class="form-group col-sm-3">
					<div class="input-group search-input">
					<input class="storedataRetain" type="hidden" name="<portlet:namespace/>storedataRetain" value="${formparam['storedataRetain']}" />
						
						<input class="form-control storeTypeAhead" maxlength="50" id="shipmentstoredata" name="<portlet:namespace/>storedata" value="${fn:replace(formparam['storedata'],'&quot;', '&quot;')}"
									aria-label="Text input with segmented button dropdown" 
									placeholder="All Stores..."/>
					</div>
				</div>
                        
                        
                        <div class="form-group col-sm-3">
                            <div class="input-group search-input destination">
                                <input class="form-control" id="ship-destination" name="<portlet:namespace/>ship-destination" placeholder="Destination Zip Code..." value='${formparam["ship-destination"]}' />
                                <span class="error text-danger" id="ship-destination-error"></span>
                            </div>
                        </div>
                        
                        <div class="form-group col-sm-3">
							<div class="input-group search-input inclued-title">
								<input class="form-control itemcode" id="ship-itemcode" name="<portlet:namespace/>ship-itemcode"
									aria-label="Text input with segmented button dropdown" placeholder="ISBN/Item Code..." value='${formparam["ship-itemcode"]}' />
								<span class="error text-danger" id="ship-itemcode-error"></span>
								<input name="<portlet:namespace/>ship-hiddenitemcode" id="ship-hiddenitemcode" class="hiddenitemcode" type="hidden" />
								<!-- <div class="input-group-btn">
									<button type="button" class="btn lookupButton" data-toggle="modal" data-target="#myModal">
										Lookup <i class="fa fa-search"></i>
									</button>
								</div> -->
							</div>
						</div>
						
               	 	</div> <!-- row ended -->
               	 	
	               	<!-- Search button -->
					<div class="row">
						<div class="col-sm-9 col-xs-6">
								<button type="button" class="btn btn-theme search-btn" onclick="javascript:validateShipmentForm()" style="border-radius: 3px; width: 150px;">Search </button>
						</div>
						<div class="form-group col-sm-3 col-xs-6">
                            <div class="input-group search-input">
                                <input class="form-control" id="ship-trackingNbr" name="<portlet:namespace/>ship-trackingNbr" placeholder="Shipment Carrier Tracking Number..." value='${formparam["ship-trackingNbr"]}' >
                                <span class="error text-danger" id="ship-trackingNbr-error"></span>
                            </div>
                        </div>
					</div>
					<input type="hidden" name="<portlet:namespace/>ship-shipGroup" id="ship-shipGroup" value='${formparam["ship-shipGroup"]}' />
					<input type="hidden" name="<portlet:namespace/>shipments-offset" id="shipments-offset" value="0" />
					<input id="shipments-loadmore" name="<portlet:namespace/>shipments-loadmore" type="hidden" />
					<input id="shipments-size" name="<portlet:namespace/>shipments-size" type="hidden" />
               </springfm:form>
            </div>
        </div>

<script src="<%=request.getContextPath()%>/js/search/search_shipment.js?t=<%=timestamp%>"
	type_ahead_stores_url="<%=typeAheadStores %>" portlet_namespace="<portlet:namespace/>"
	store_data_url="<%=storeDataURL%>" accounts_url="<%=accountsURL %>" 
	type="text/javascript"></script>
