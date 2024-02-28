<%@page import="com.hbg.rp.model.InvoiceLine"%>
<%@page import="com.hbg.rp.search.constants.ApplicationConstant"%>
<%@page import="com.hbg.rp.model.OrderLine"%>
<%@page import="com.hbg.rp.search.util.*"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="init.jsp" %>
<portlet:defineObjects />
<%
	CommonUtil commonUtil = new CommonUtil();
	String allReportingGroups = commonUtil.getAllReportingGroups();
%>
<c:set var="rowInvType" value="${data.invoiceHeader.invoiceType == 'DBN' ? 'Debit' : (data.invoiceHeader.invoiceType == 'CRN' ? 'Credit' : 'Invoice')}"></c:set>
<c:forEach items="${data.invoiceLines}" var="invoiceLine" varStatus="count">
<c:set var="shortAuthor" value="${invoiceLine.shortAuthor}"></c:set>
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

	<%	
		InvoiceLine invoiceLine = ((InvoiceLine)pageContext.getAttribute("invoiceLine"));
		boolean isItemCodeVisible = false;
		boolean allowedOwnerCode = false;
		String reportingGroups = commonUtil.getPermittedReportingGroups(renderRequest);

		if(null != allReportingGroups && allReportingGroups.contains(invoiceLine.getReportingGroupName())){
			allowedOwnerCode = true;
		}
		if(null != reportingGroups && "0".equals(reportingGroups)){
			isItemCodeVisible = true;
		}else{
			isItemCodeVisible = reportingGroups.contains(invoiceLine.getReportingGroupCode());
		}
		String encryptedIsbn = ApplicationConstant.ENCRYPTED_ISBN;
		String encryptedTitle = ApplicationConstant.ENCRYPTED_TITLE;
		String encryptedAuthor = ApplicationConstant.ENCRYPTED_AUTHOR;
		String encryptedMsrp = ApplicationConstant.ENCRYPTED_MSRP;
		String originalISBN = invoiceLine.getIsbn();
		String field_7 = "";
		if(invoiceLine.getIsbn().contains("-")) {
			originalISBN = invoiceLine.getIsbn().split("-")[0].trim();
			field_7 = invoiceLine.getIsbn().split("-")[1].trim();
		}
		
		String encryptedAmount = ApplicationConstant.ENCRYPTED_AMOUNT;
	%>
	<c:set var="allowedOwnerCode" value="<%=allowedOwnerCode %>" />
	<c:set var="isItemCodeVisible" value="<%=isItemCodeVisible %>" />
	<c:set var="encryptedIsbn" value="<%=encryptedIsbn %>" />
	<c:set var="encryptedTitle" value="<%=encryptedTitle %>" />
	<c:set var="encryptedAuthor" value="<%=encryptedAuthor %>" />
	<c:set var="encryptedMsrp" value="<%=encryptedMsrp %>" />
	<c:set var="originalISBN" value="<%=originalISBN %>" />
	<c:set var="field_7" value="<%=field_7 %>" />
	<tr>
		<td class="${isItemCodeVisible ? '' : 'hide-item'}">
			<c:if test="${not allowedOwnerCode}">
				<c:out value="${isItemCodeVisible ? originalISBN : encryptedIsbn}" />
			</c:if>
			<c:if test="${allowedOwnerCode}">
				<c:choose>
					<c:when test="${isItemCodeVisible}">
					   <a href="<%=ApplicationConstant.VIEW_CATALOG_BOOK_URL %>${originalISBN}"><c:out value="${originalISBN}" /></a>
					</c:when>
					<c:otherwise>
					  <b><c:out value="${encryptedIsbn}" /></b>
					</c:otherwise>
				</c:choose>
			</c:if>
			<c:if test="${field_7 ne ''}">
				<br>
				<c:out value="${isItemCodeVisible ? field_7 : encryptedIsbn}" />
			</c:if>
		</td>
		<td class="${isItemCodeVisible ? '' : 'hide-item'}">
			<c:if test="${not allowedOwnerCode}">
				<c:out value="${isItemCodeVisible ? invoiceLine.shortTitle : encryptedTitle}" />
			</c:if>
			<c:if test="${allowedOwnerCode}">
				<c:choose>
					<c:when test="${isItemCodeVisible}">
					   <a href="<%=ApplicationConstant.VIEW_CATALOG_BOOK_URL %>${originalISBN}"><c:out value="${invoiceLine.shortTitle}" /></a>
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
				<c:choose>
			      <c:when test="${isItemCodeVisible}">
					 <portlet:actionURL name="getSearchByShortAuthor" var="getSearchByShortAuthorURL">
						<portlet:param name="search-catalogs" value="true" />
						<portlet:param name="catalog-search-param" value="catalogs" />
						<portlet:param name="viewId" value="search-catalogs" />
						<portlet:param name="catalog_author" value="<%=shortAuthor%>" />
					</portlet:actionURL>
	                                                                           
		            <a href="<%=getSearchByShortAuthorURL%>">${invoiceLine.shortAuthor} </a>
					</c:when>
		   				<c:otherwise>
		       				<a href="javascript:void(0)" >"${encryptedAuthor}"</a>
		   				</c:otherwise>
					</c:choose>
			</c:if>
		</td>
		
		<td> 
		<c:if test="${!(invoiceLine.lineStatus ne 'Fulfilled' and invoiceLine.bomType eq 'P')}">
			${invoiceLine.quantity} 
		</c:if>
		</td>
		<c:if test="${rowInvType ne 'Invoice' }">
			<td>${invoiceLine.quantityClaim } </td>
		</c:if>
		<c:choose>
		  <c:when test="${invoiceLine.lineStatus eq 'Fulfilled' }">
			  <td class="${isItemCodeVisible ? '' : 'hide-item'}">
			  	<c:choose>
			  		<c:when test="${isItemCodeVisible}">
						<fmt:formatNumber pattern="#,##0.00;-#,##0.00" type="number" minFractionDigits="2" maxFractionDigits="2" value="${invoiceLine.unitPrice}" />
					</c:when>
					<c:otherwise> <c:out value="${encryptedMsrp}" /> </c:otherwise>
			  	</c:choose>
			  </td>
			  
			  <td class="${isItemCodeVisible ? '' : 'hide-item'}">
			  	<c:choose>
			  		<c:when test="${isItemCodeVisible}">
						<fmt:formatNumber pattern="#,##0.00;-#,##0.00" type="number" minFractionDigits="2" maxFractionDigits="2" value="${invoiceLine.discount}" />%
					</c:when>
					<c:otherwise> <c:out value="${encryptedMsrp}" /> </c:otherwise>
			  	</c:choose>
			  </td>
			  
			  <td class="${isItemCodeVisible ? '' : 'hide-item'}">
			  	<c:choose>
			  		<c:when test="${isItemCodeVisible}">
						<fmt:formatNumber pattern="#,##0.00;-#,##0.00" type="number" minFractionDigits="2" maxFractionDigits="2" value="${invoiceLine.lineAmount}" />
					</c:when>
					<c:otherwise> <c:out value="${encryptedMsrp}" /> </c:otherwise>
			  	</c:choose>
			  </td>
		 </c:when>
		 <c:otherwise>
		 	<td colspan="3"> ${invoiceLine.lineStatus } </td>
		 </c:otherwise>
		</c:choose>
	</tr>
  	<!-- DISPLAY logic ended here. -->
</c:forEach>

<script type="text/javascript">
	function searchByShortAuthor(formId){
		document.getElementById(formId).submit();
	}
</script>
