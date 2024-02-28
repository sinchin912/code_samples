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


<%		
	LiferayPortletContext context = (LiferayPortletContext) portletConfig.getPortletContext();
	long ts = context.getPortlet().getTimestamp();
	String timestamp = ApplicationConstant.BUILD_VERSION+"_"+ts;
	String pageName = themeDisplay.getLayout().getName(themeDisplay.getLocale());
	String layoutId = themeDisplay.getLayout().getPlid() + "";
	int authorMaxLength = ApplicationConstant.AUTHOR_SEARCH_MAX_LENGTH;
 
%>

<portlet:actionURL name="searchCatalogs" var="getcatalogDetail">
	<portlet:param name="viewId" value="search-catalogs" />
	<portlet:param name="catalog-search-param" value="catalogs" />
	<portlet:param name="search-catalogs" value="true" />
</portlet:actionURL>

<!-- .advanced-search -->
<div class="advanced-search search-options" id="search-catalogs">
	<div class="container">
		<springfm:form name="searchCatalogForm" id="searchCatalogForm" action="<%=getcatalogDetail%>" method="get">
		     
     		<input type="hidden" name="layoutId" value="<%=layoutId%>" />
			<input type="hidden" name="viewId" value="search-catalogs" />
			<input type="hidden" name="catalog-search-param" value="catalogs" />
			<input type="hidden" name="search-catalogs" value="true" />
			<input type="hidden" name="p_p_id" value="rpsearchweb_WAR_rpsearchweb" />
			<input type="hidden" name="p_p_lifecycle" value="1" />
			<input type="hidden" name="p_p_state" value="normal" />
			<input type="hidden" name="p_p_mode" value="view" />
			<input type="hidden" name="_rpsearchweb_WAR_rpsearchweb_javax.portlet.action" value="searchCatalogs" />
			<input type="hidden" name="_rpsearchweb_WAR_rpsearchweb_search-catalogs" value="true" />
			<input type="hidden" name="_rpsearchweb_WAR_rpsearchweb_viewId" value="search-catalogs" />
			<input type="hidden" name="_rpsearchweb_WAR_rpsearchweb_catalog-search-param" value="catalogs" />
			<input type="hidden" name="_rpsearchweb_WAR_rpsearchweb_layoutId" value="<%=layoutId%>" />
			<input type="hidden" name="p_auth" id="p_auth_token_Catalogs" value="" />
                    
		  	<div class="row">
                 <div class="col-sm-4"><h5 class="search-criteria-info">
                 	Search criteria for Titles/Items: <a class="clear_all" href="javascript:void(0)"> Clear All </a> </h5>
                 </div>
                 <div class="col-sm-8 text-right"><a href="javascript:void(0)" class="close_link">
                 	<b> <i class="glyphicon glyphicon-remove"></i> CLOSE</b></a></div>
             </div>

			<div class="row">
				<div class="form-group col-xs-3">
					<div class="input-group search-input">
						<input class="form-control" id="catalog-title"
							name="<portlet:namespace/>catalog-title" placeholder="Title..."
							value="${fn:replace(formparam['catalog-title'],'&quot;', '&quot;')}">
					</div>
				</div>
				<div class="form-group col-xs-3">
					<div class="input-group search-input">
						<input class="form-control" id="catalog-itemcode"
							name="<portlet:namespace/>catalog-itemcode"
							placeholder="ISBN or item Code..."
							value='${formparam["catalog-itemcode"]}'>
							<span class="error text-danger" id="itemCodeError"></span>
					</div>
				</div>
				<div class="form-group col-xs-3">
					<div class="input-group search-input">
						<input class="form-control" id="catalog_subject"
							name="<portlet:namespace/>catalog_subject"
							placeholder="Subject or BISAC..." value="${fn:replace(formparam['catalog_subject'],'&quot;', '&quot;')}">
					</div>
				</div>

				<div class="form-group col-xs-3">
					     <div class="search-input date-calendar" id="catalog-daterange" aria-label="Text input with segmented button dropdown">
                                <span class="date-icon" id="sizing-addon1">
				                    <i class="glyphicon glyphicon-calendar"></i>
				                </span>
                                <input class="form-control osdclass" type="text" placeholder="Search within OSD Range"
                                	name="<portlet:namespace/>daterange" readonly="readonly" value="${formparam['daterange']}" />
                                <div class="date-right-arrow">
		                       		<span class="caret"></span>
	                    		</div>
                            </div>
				</div>

			</div>
			<div class="row">

				<div class="form-group col-sm-3 col-xs-3">
					<div class="input-group search-input">
						<div class="format-data custom-select-box">
							<select class="catalog_formatdata" id="catalog_formatdata"
								name="<portlet:namespace/>catalog_formatdata">
								<option value="">All Formats</option>
								<c:if test="${not empty formats && formats.size() > 0}">
									 <c:choose>
									 <c:when test="${not empty formparam['catalog_formatdata']}">
									      <c:forEach begin="0" end="${formats.size()-1}" var="index"> 
										   <option
												value="${formats[index]}"
												${formparam['catalog_formatdata'] == formats[index] ? 'selected="selected"' : ''}>
												${formats[index]}</option>
										  </c:forEach>	
									 </c:when>
									 <c:otherwise>
										<c:forEach begin="0" end="${formats.size()-1}" var="index">
											<option
													value="${formats[index]}">
													${formats[index]}</option>
										</c:forEach>
								    </c:otherwise>									
								  </c:choose>	
								</c:if>	
							</select>
						</div>
					</div>
				</div>
			
							
				<div class="form-group col-sm-3 col-xs-3">
					<div class="input-group search-input">
					  <div class="reportinggroup-data custom-select-box">
						<select id="catalog_reportinggroup" class="catalog_reportinggroup" name="<portlet:namespace/>catalog_reportinggroup">
							
							<option value="">All Publisher Groups</option>
							<c:if test="${not empty reportinggroups && reportinggroups.size() > 0}">
									 <c:choose>
									 <c:when test="${not empty formparam['catalog_reportinggroup']}">
									      <c:forEach begin="0" end="${reportinggroups.size()-1}" var="index"> 
										   <option
												value="${reportinggroups[index]}"
												${formparam['catalog_reportinggroup'] == reportinggroups[index] ? 'selected="selected"' : ''}>
												${reportinggroups[index]}</option>
										  </c:forEach>	
									 </c:when>
									 <c:otherwise>
										<c:forEach begin="0" end="${reportinggroups.size()-1}" var="index">
											<option
													value="${reportinggroups[index]}">
													${reportinggroups[index]}</option>
										</c:forEach>
								    </c:otherwise>									
								  </c:choose>	
								</c:if>	
						 </select>
					 </div>
					</div>
				</div>
				
				<div class="form-group col-sm-3 col-xs-3">
					<div class="input-group search-input">
						<div class="format-data custom-select-box">
							<select class="pubstatusdata" id="catalog_pubstatusdata"
								name="<portlet:namespace/>catalog_pubstatusdata">
								<option value="">All Pub Status</option>
								
								<c:choose>
									<c:when test="${not empty formparam['catalog_pubstatusdata']}">
										<c:forEach begin="0" end="${pubStatus.length()-1}" var="index">
											<option
												value="${pubStatus.getJSONObject(index).getString('code')}"
												${formparam['catalog_pubstatusdata'] == pubStatus.getJSONObject(index).getString('code') ? 'selected="selected"' : ''}>
												${pubStatus.getJSONObject(index).getString('code')}&nbsp;&nbsp;&nbsp;&nbsp;${pubStatus.getJSONObject(index).getString('desc')}</option>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<c:forEach begin="0" end="${pubStatus.length()-1}" var="index">
											<option
												value="${pubStatus.getJSONObject(index).getString('code')}">
												${pubStatus.getJSONObject(index).getString('code')}&nbsp;&nbsp;&nbsp;&nbsp;${pubStatus.getJSONObject(index).getString('desc')}</option>
										</c:forEach>


									</c:otherwise>
								</c:choose>
							</select>
						</div>
					</div>
				</div>
				
				<div class="form-group col-sm-3 col-xs-3">
					<div class="input-group search-input">
						<input class="form-control" id="catalog_author" maxlength="<%=authorMaxLength%>"
							name="<portlet:namespace/>catalog_author" placeholder="Author..."
							value="${fn:replace(formparam['catalog_author'],'&quot;', '&quot;')}">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-3 col-xs-3">
						<button type="button" onclick="javascript:validateAndProcess()" class="btn btn-theme search-btn">Search</button>
				</div>
	
			</div>
					<input type="hidden" name="<portlet:namespace/>short_author" id="short_author" value="${fn:replace(formparam['short_author'],'&quot;', '&quot;')}" />
					<input type="hidden" id="catalogs-offset" name="<portlet:namespace/>catalogs-offset" value="0" />
					<input type="hidden" id="catalogs-loadmore" name="<portlet:namespace/>catalogs-loadmore" />
					<input type="hidden" id="catalogs-size" name="<portlet:namespace/>catalogs-size" />
		</springfm:form>
	</div>
</div>


<script src="<%=request.getContextPath()%>/js/search/search_catalog.js?t=<%=timestamp%>"
	portlet_namespace="<portlet:namespace/>" type="text/javascript"></script>
<!-- Changes for NRP-2627 -->
	<style>
	@media only screen and (max-width: 1480px) {
		#catalog_pubstatusdata-menu {
			width :600px !important;
		}
	}
	</style>