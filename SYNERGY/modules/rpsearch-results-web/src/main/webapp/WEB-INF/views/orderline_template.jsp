<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.hbg.rp.search.constants.ApplicationConstant"%>
<%@page import="com.hbg.rp.model.OrderLine"%>
<%@page import="com.hbg.rp.search.util.*"%>
<%@page import="com.hbg.rp.service.*"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%@ include file="init.jsp" %> --%>

<%@page import="javax.portlet.PortletSession"%>


<portlet:defineObjects />
<portlet:actionURL name="getSearchCatalogByAuthor" var="getSearchCatalogByAuthorURL"></portlet:actionURL>

<%
	Map<String, String> onSaleDateMap = null; 
	IOrderUtil ordersUtil = (IOrderUtil) request.getAttribute("ordersUtil");
	if (ordersUtil != null) {
		List<OrderLine> orderLines = (List<OrderLine>) request.getAttribute("orderLines");
		onSaleDateMap = ordersUtil.getOnSaleDateMap(orderLines);
	}
	CommonUtil commonUtil = new CommonUtil();
	String allReportingGroups = commonUtil.getAllReportingGroups();
	Map<String, String> messageMap = NrpPubstatusMappingLocalServiceUtil.getAllPubStatusMap();
	String reportingGroups = commonUtil.getPermittedReportingGroups(renderRequest);
	IPortalMappingsUtil portalMappingsUtil = (IPortalMappingsUtil)request.getAttribute("portalMappingsUtil");
	Map<String, String> reasonCodeMap= portalMappingsUtil.getReasonCodeMap();
	Map<String, String> lineStatusMap= portalMappingsUtil.getLineStatusMap();
	Long extRepId = (long) renderRequest.getPortletSession().getAttribute(ApplicationConstant.LIFERAY_SHARED_EXT_REP_ID, PortletSession.APPLICATION_SCOPE);
	Boolean isRepUser = (extRepId > 0) ? true : false;
%>
<c:set var="isRepUser" value="<%=isRepUser%>" />
<c:set value="<%=onSaleDateMap %>" var="onSaleDateMap" />
<c:set var="MessageMap" value="<%=messageMap %>" />
<c:forEach items="${orderLines}" var="orderLine" varStatus="count">
	<portlet:renderURL var="orderStatusDataURL">
		<portlet:param name="action" value="getOrderStatusPage" />
		<portlet:param name="refno" value="${refno}" />
	</portlet:renderURL>
	
	<c:set var="shortAuthor" value="${orderLine.shortAuthor}"></c:set>
	<jsp:useBean id="shortAuthor" type="java.lang.String" />
    <%
		int authorMaxLength = ApplicationConstant.AUTHOR_SEARCH_MAX_LENGTH;
		if(shortAuthor == null || shortAuthor.equalsIgnoreCase("null") || shortAuthor.length() == 0) {
			shortAuthor = "";
		}
		if(shortAuthor.length() != 0 && shortAuthor.length() > authorMaxLength) {
			shortAuthor = shortAuthor.substring(0,authorMaxLength);
		}
	 	OrderLine orderLine = ((OrderLine)pageContext.getAttribute("orderLine"));
		boolean isItemCodeVisible = false;
		boolean allowedOwnerCode = false;
		if(null != allReportingGroups && allReportingGroups.contains(orderLine.getReportingGroupName())){
			allowedOwnerCode = true;
		}
		if(null != reportingGroups && "0".equals(reportingGroups)){
			isItemCodeVisible = true;
		}else{
			isItemCodeVisible = reportingGroups.contains(orderLine.getReportingGroupCode());
		}
		String reasonCode = orderLine.getLineStatusReasonCode();
		String lineStatus = orderLine.getLineStatus();
		if (lineStatus == null || "".equals(lineStatus.trim())) {
        	lineStatus =  "";
        } else {
        	lineStatus = lineStatus.trim().toLowerCase();
        }
        if (reasonCode == null || "".equals(reasonCode.trim())) {
           reasonCode =  "";
        } else {
           reasonCode = reasonCode.trim().toLowerCase();
        }
		String reasonCodeValue = reasonCodeMap.get(reasonCode);
		String lineStatusCodeValue = lineStatusMap.get(lineStatus);
		String statusDetails = reasonCodeValue != null ? reasonCodeValue : "";
		String mappedLineStatus = lineStatusCodeValue != null ? lineStatusCodeValue : "";
		String encryptedIsbn = ApplicationConstant.ENCRYPTED_ISBN;
		String encryptedTitle = ApplicationConstant.ENCRYPTED_TITLE;
		String encryptedAuthor = ApplicationConstant.ENCRYPTED_AUTHOR;
		String encryptedMsrp = ApplicationConstant.ENCRYPTED_MSRP;
		
	%>

	<c:set var="MappedLineStatus" value="<%=mappedLineStatus %>" />
	<c:if test="${fn:toLowerCase(MappedLineStatus) eq 'cancelled'}">
		<c:set var="MappedLineStatus" value="Canceled" />
	</c:if>
	
	<c:set var="allowedOwnerCode" value="<%=allowedOwnerCode %>" />
	<c:set var="isItemCodeVisible" value="<%=isItemCodeVisible %>" />
	<c:set var="encryptedIsbn" value="<%=encryptedIsbn %>" />
	<c:set var="encryptedTitle" value="<%=encryptedTitle %>" />
	<c:set var="encryptedAuthor" value="<%=encryptedAuthor %>" />
	<c:set var="encryptedMsrp" value="<%=encryptedMsrp %>" />

	<!-- Logic for Column: LINE_STATUS started. -->
	<c:set var="StatusDetailsVal" value='<%=statusDetails %>' />
	<c:choose>
		<c:when test="${fn:toLowerCase(orderLine.lineStatus) eq 'shipped'}">
			<c:set var="statusDetailsCol" value='url'></c:set>
		</c:when>
		<c:when test="${orderLine.pubStatusCode eq 'NYP'}">
		<c:choose>
			<c:when test="${not empty onSaleDateMap[orderLine.isbn] }">
				<fmt:parseDate var="onSaleDate" value="${onSaleDateMap[orderLine.isbn]}" pattern="yyyy-MM-dd" />
				<fmt:formatDate var="onSaleDateFormatted" value="${onSaleDate}" pattern="M/d/yy" />
				<c:set var="statusDetailsCol" value='${MessageMap[orderLine.pubStatusCode]} OSD [${onSaleDateFormatted}]'></c:set>
			</c:when>
			<c:otherwise> 
					<c:set var="statusDetailsCol" value='${MessageMap[orderLine.pubStatusCode]} OSD TBD'></c:set>
			</c:otherwise>
		</c:choose>
		</c:when>
		<c:when test="${orderLine.pubStatusCode eq 'OS'}">
		<c:choose>
			<c:when test="${not empty onSaleDateMap[orderLine.isbn] }">
				<fmt:parseDate var="onSaleDate" value="${onSaleDateMap[orderLine.isbn]}" pattern="yyyy-MM-dd" />
				<fmt:formatDate var="onSaleDateFormatted" value="${onSaleDate}" pattern="M/d/yy" />
				<c:set var="statusDetailsCol" value='${MessageMap[orderLine.pubStatusCode]} Expected availability date is [${onSaleDateFormatted}]'></c:set>
			</c:when>
			<c:otherwise> 
					<c:set var="statusDetailsCol" value='${MessageMap[orderLine.pubStatusCode]} Expected availability date is TBD'></c:set>
			</c:otherwise>
		</c:choose>
		</c:when>
		<c:otherwise> <c:set var="statusDetailsCol" value='${MessageMap[orderLine.pubStatusCode]}'></c:set> </c:otherwise>
	</c:choose>

	<!-- DISPLAY logic started. -->
	<c:choose>
		<c:when test="${not empty orderResultsPage }"> <!-- when called from ORDER_RESULTS page. -->
			<tr>
				<td class="${isItemCodeVisible ? '' : 'hide-item'}">
					<c:if test="${not allowedOwnerCode}">
						<c:out value="${isItemCodeVisible ? orderLine.isbn : encryptedIsbn}" />
					</c:if>
					<c:if test="${allowedOwnerCode}">
						<c:choose>
							<c:when test="${isItemCodeVisible}">
							   <a href="<%=ApplicationConstant.VIEW_CATALOG_BOOK_URL %>${orderLine.isbn}"><c:out value="${orderLine.isbn}" /></a>
							</c:when>
							<c:otherwise>
							  <b><c:out value="${encryptedIsbn}" /></b>
							</c:otherwise>
						</c:choose>
					</c:if>
				</td>
				<td class="${isItemCodeVisible ? '' : 'hide-item'}">
					<c:if test="${not allowedOwnerCode}">
						<c:out value="${isItemCodeVisible ? orderLine.shortTitle : encryptedTitle}" />
					</c:if>
					<c:if test="${allowedOwnerCode}">
						<c:choose>
							<c:when test="${isItemCodeVisible}">
							   <a href="<%=ApplicationConstant.VIEW_CATALOG_BOOK_URL %>${orderLine.isbn}"><c:out value="${orderLine.shortTitle}" /></a>
							</c:when>
							<c:otherwise>
							  <b><c:out value="${encryptedTitle}" /></b>
							</c:otherwise>
						</c:choose>
					</c:if>
				</td>
				<td class="${isItemCodeVisible ? '' : 'hide-item'}">
					<c:if test="${not allowedOwnerCode}">
						<c:out value="${isItemCodeVisible ? orderLine.shortAuthor : encryptedAuthor}" />
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
	                            <a href="<%=getSearchByShortAuthorURL%>">${orderLine.shortAuthor} </a>
				 		</c:when>
		   				<c:otherwise>
		       				<a href="javascript:void(0)" >"${encryptedAuthor}"</a>
		   				</c:otherwise>
   			 		 </c:choose>
					</c:if>
				</td>
				<td>${orderLine.orderQuantity}</td>
				<td>${orderLine.pubStatusCode}</td>
				<td><span class="text-capitalize">${fn:toLowerCase(MappedLineStatus)}</span></td>
				<td>
					<c:choose>
	    				<c:when test="${statusDetailsCol == 'url'}">
	     		   			<a class="view-shipment-link" href="<%=orderStatusDataURL%>#Shipped">View Shipment</a>
	    				</c:when>
	    				<c:otherwise>
	        				${statusDetailsCol }
	    				</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</c:when>
		<c:when test="${not empty orderDetailPageByStatus }"> <!-- when called from ORDER_DETAIL page for ORDER_BY_STATUS. -->
			<tr>
				<th scope="row">${orderLine.lineNbr}</th>
				<td class="${isItemCodeVisible ? '' : 'hide-item'}">
					<c:if test="${not allowedOwnerCode}">
						<c:out value="${isItemCodeVisible ? orderLine.isbn : encryptedIsbn}" />
					</c:if>
					<c:if test="${allowedOwnerCode}">
						<c:choose>
							<c:when test="${isItemCodeVisible}">
							   <a href="<%=ApplicationConstant.VIEW_CATALOG_BOOK_URL %>${orderLine.isbn}"><c:out value="${orderLine.isbn}" /></a>
							</c:when>
							<c:otherwise>
							  <b><c:out value="${encryptedIsbn}" /></b>
							</c:otherwise>
						</c:choose>
					</c:if>
				</td>
				<td class="${isItemCodeVisible ? '' : 'hide-item'}">
					<c:if test="${not allowedOwnerCode}">
						<c:out value="${isItemCodeVisible ? orderLine.shortTitle : encryptedTitle}" />
					</c:if>
					<c:if test="${allowedOwnerCode}">
						<c:choose>
							<c:when test="${isItemCodeVisible}">
							   <a href="<%=ApplicationConstant.VIEW_CATALOG_BOOK_URL %>${orderLine.isbn}"><c:out value="${orderLine.shortTitle}" /></a>
							</c:when>
							<c:otherwise>
							  <b><c:out value="${encryptedTitle}" /></b>
							</c:otherwise>
						</c:choose>
					</c:if>
				</td>
				<td class="${isItemCodeVisible ? '' : 'hide-item'}">
					<c:if test="${not allowedOwnerCode}">
						<c:out value="${isItemCodeVisible ? orderLine.shortAuthor : encryptedAuthor}" />
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
	                            <a href="<%=getSearchByShortAuthorURL%>" >${orderLine.shortAuthor} </a>
				 		</c:when>
		   				<c:otherwise>
		       				<a href="javascript:void(0)" >"${encryptedAuthor}"</a>
		   				</c:otherwise>
   			 		 </c:choose>
					</c:if>
				</td>
				<td>${orderLine.orderQuantity}</td>
				<td>${orderLine.pubStatusCode}</td>
				<td><c:choose>
    				<c:when test="${statusDetailsCol == 'url'}">
     		   			<a class="view-shipment-link" href="<%=orderStatusDataURL%>#Shipped">View Shipment</a>
    				</c:when>
    				<c:otherwise>
        				${statusDetailsCol }
    				</c:otherwise>
				</c:choose></td>
				<td class="${isItemCodeVisible ? '' : 'hide-item'}">
					<c:choose>
						<c:when test="${not empty fn:trim(orderLine.msrp) and fn:trim(orderLine.msrp) ne '$' }">
							<c:out value="${isItemCodeVisible ? orderLine.msrp : encryptedMsrp}" />
						</c:when>
						<c:otherwise>N/A</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<c:if test="${count.last }"> 
			             <tr class="total-row rep-user-total">
                             <td>&nbsp;</td>
                             <td colspan="3">
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
							<td>&nbsp;</td>
						</tr>
		   </c:if> 
		</c:when>
		<c:when test="${not empty orderDetailPage }"> <!-- when called from ORDER_DETAIL page for ORDER-SUMMARY. -->
			<tr>
				<th scope="row">${orderLine.lineNbr}</th>
				<td class="${isItemCodeVisible ? '' : 'hide-item'}">
					<c:if test="${not allowedOwnerCode}">
						<c:out value="${isItemCodeVisible ? orderLine.isbn : encryptedIsbn}" />
					</c:if>
					<c:if test="${allowedOwnerCode}">
						<c:choose>
							<c:when test="${isItemCodeVisible}">
							   <a href="<%=ApplicationConstant.VIEW_CATALOG_BOOK_URL %>${orderLine.isbn}"><c:out value="${orderLine.isbn}" /></a>
							</c:when>
							<c:otherwise>
							  <b><c:out value="${encryptedIsbn}" /></b>
							</c:otherwise>
						</c:choose>
					</c:if>
				</td>
				<td class="${isItemCodeVisible ? '' : 'hide-item'}">
					<c:if test="${not allowedOwnerCode}">
						<c:out value="${isItemCodeVisible ? orderLine.shortTitle : encryptedTitle}" />
					</c:if>
					<c:if test="${allowedOwnerCode}">
						<c:choose>
							<c:when test="${isItemCodeVisible}">
							   <a href="<%=ApplicationConstant.VIEW_CATALOG_BOOK_URL %>${orderLine.isbn}"><c:out value="${orderLine.shortTitle}" /></a>
							</c:when>
							<c:otherwise>
							  <b><c:out value="${encryptedTitle}" /></b>
							</c:otherwise>
						</c:choose>
					</c:if>
				</td>
				<td class="${isItemCodeVisible ? '' : 'hide-item'}">
					<c:if test="${not allowedOwnerCode}">
						<c:out value="${isItemCodeVisible ? orderLine.shortAuthor : encryptedAuthor}" />
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
	                            <a href="<%=getSearchByShortAuthorURL%>">${orderLine.shortAuthor} </a>
				 		</c:when>
		   				<c:otherwise>
		       				<a href="javascript:void(0)" >"${encryptedAuthor}"</a>
		   				</c:otherwise>
   			 		 </c:choose>
					</c:if>
					
				</td>
				<td>${orderLine.orderQuantity}</td>
				<td>${orderLine.pubStatusCode}</td>
				<td><span class="text-capitalize">${fn:toLowerCase(MappedLineStatus)}</span></td>
				<td>
				<c:choose>
    				<c:when test="${statusDetailsCol == 'url'}">
     		   			<a class="view-shipment-link" href="<%=orderStatusDataURL%>#Shipped">View Shipment</a>
    				</c:when>
    				<c:otherwise>
        				${statusDetailsCol }
    				</c:otherwise>
				</c:choose></td>
				<td class="${isItemCodeVisible ? '' : 'hide-item'}">
					<c:choose>
						<c:when test="${not empty fn:trim(orderLine.msrp) and fn:trim(orderLine.msrp) ne '$' }">
							<c:out value="${isItemCodeVisible ? orderLine.msrp : encryptedMsrp}" />
						</c:when>
						<c:otherwise>N/A</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</c:when>
	</c:choose>
  	<!-- DISPLAY logic ended here. -->

</c:forEach>
<script type="text/javascript">
	function searchByShortAuthor(formId){
		document.getElementById(formId).submit();
	}
</script>