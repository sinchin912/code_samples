<%@page import="com.hbg.rp.search.model.InvoiceDetail"%>
<%@page import="com.hbg.rp.search.constants.ApplicationConstant"%>
<%@page import="com.hbg.rp.model.OrderLine"%>
<%@page import="com.hbg.rp.search.util.*"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayPortletContext"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<portlet:defineObjects />
<%
LiferayPortletContext context = (LiferayPortletContext) portletConfig.getPortletContext();
long timestamp = context.getPortlet().getTimestamp();
	CommonUtil commonUtil = new CommonUtil();
	String allReportingGroups = commonUtil.getAllReportingGroups();
%>
<c:forEach items="${data.invoiceDetail}" var="invoiceDetail" varStatus="count">
	<%	
		InvoiceDetail invoiceDetail = ((InvoiceDetail) pageContext.getAttribute("invoiceDetail"));
		boolean isItemCodeVisible = false;
		boolean allowedOwnerCode = false;
		String reportingGroups = commonUtil.getPermittedReportingGroups(renderRequest);

		if(null != allReportingGroups && allReportingGroups.contains(invoiceDetail.getReportingGroupName())){
			allowedOwnerCode = true;
		}
		if(null != reportingGroups && "0".equals(reportingGroups)){
			isItemCodeVisible = true;
		}else{
			isItemCodeVisible = reportingGroups.contains(invoiceDetail.getReportingGroupCode());
		}
		String encryptedIsbn = ApplicationConstant.ENCRYPTED_ISBN;
		String encryptedTitle = ApplicationConstant.ENCRYPTED_TITLE;
		String encryptedAuthor = ApplicationConstant.ENCRYPTED_AUTHOR;
		String encryptedMsrp = ApplicationConstant.ENCRYPTED_MSRP;
		String originalISBN = invoiceDetail.getIsbn();
		String field_7 = "";
		if(invoiceDetail.getIsbn().contains("-")) {
			originalISBN = invoiceDetail.getIsbn().split("-")[0].trim();
			field_7 = invoiceDetail.getIsbn().split("-")[1].trim();
		}
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
		<td>${count.index+1}</td>
		<td class="${isItemCodeVisible ? '' : 'hide-item'}">
			<c:if test="${not allowedOwnerCode}">
				<c:out value="${isItemCodeVisible ? originalISBN : encryptedIsbn}" />
			</c:if>
			<c:if test="${allowedOwnerCode}">
				<c:out value="${isItemCodeVisible ? originalISBN : encryptedIsbn}" />
			</c:if>
			<c:if test="${field_7 ne ''}">
				<br>
				<c:out value="${isItemCodeVisible ? field_7 : encryptedIsbn}" />
			</c:if>
		</td>
		<td class="${isItemCodeVisible ? '' : 'hide-item'}">
			<c:if test="${not allowedOwnerCode}">
				<c:out value="${isItemCodeVisible ? invoiceDetail.title : encryptedTitle}" />
			</c:if>
			<c:if test="${allowedOwnerCode}">
				<c:out value="${isItemCodeVisible ? invoiceDetail.title : encryptedTitle}" />
			</c:if>
		</td>
		<td class="${isItemCodeVisible ? '' : 'hide-item'}">
			<c:if test="${not allowedOwnerCode}">
				<c:out value="${isItemCodeVisible ? invoiceDetail.author : encryptedAuthor}" />
			</c:if>
			<c:if test="${allowedOwnerCode}">
				<c:out value="${isItemCodeVisible ? invoiceDetail.author : encryptedAuthor}" />
			</c:if>
		</td>
		
		<td> 
		<c:if test="${!(invoiceDetail.lineStatus ne 'Fulfilled' and invoiceDetail.bomType eq 'P')}">
			${invoiceDetail.quantity} 
		</c:if>
		</td>
		<td>${invoiceDetail.previouslyClaimed } </td>
		<c:choose>
		  <c:when test="${invoiceDetail.lineStatus eq 'Fulfilled' }">
			  <td class="${isItemCodeVisible ? '' : 'hide-item'}">
			  	<c:choose>
			  		<c:when test="${isItemCodeVisible}">
						<fmt:formatNumber pattern="#,##0.00;-#,##0.00" type="number" minFractionDigits="2" maxFractionDigits="2" value="${invoiceDetail.unitPrice}" />
					</c:when>
					<c:otherwise> <c:out value="${encryptedMsrp}" /> </c:otherwise>
			  	</c:choose>
			  </td>
			  
			  <td class="${isItemCodeVisible ? '' : 'hide-item'}">
			  	<c:choose>
			  		<c:when test="${isItemCodeVisible}">
						<fmt:formatNumber pattern="#,##0.00;-#,##0.00" type="number" minFractionDigits="2" maxFractionDigits="2" value="${invoiceDetail.discount}" />%
					</c:when>
					<c:otherwise> <c:out value="${encryptedMsrp}" /> </c:otherwise>
			  	</c:choose>
			  </td>
			  
			  <td class="${isItemCodeVisible ? '' : 'hide-item'}">
			  	<c:choose>
			  		<c:when test="${isItemCodeVisible}">
						<fmt:formatNumber pattern="#,##0.00;-#,##0.00" type="number" minFractionDigits="2" maxFractionDigits="2" value="${invoiceDetail.lineAmount}" />
					</c:when>
					<c:otherwise> <c:out value="${encryptedMsrp}" /> </c:otherwise>
			  	</c:choose>
			  </td>
		 </c:when>
		 <c:otherwise>
		 	<td colspan="3"> ${invoiceDetail.lineStatus } </td>
		 </c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${invoiceDetail.lineStatus eq 'Fulfilled' }">
				<c:if test="${!(invoiceDetail.previouslyClaimed ge invoiceDetail.quantity)}">
					<td width="120" class="add-btn-space">
						<c:if test="${isItemCodeVisible}">
						
							<c:if  test="${(invoiceDetail.bomType eq 'P' || invoiceDetail.bomType eq 'S')}">
							 	   <a href = "mailto:customerservice@hbgusa.com">Please email Customer Service</a>
							</c:if>
							<c:if  test="${!(invoiceDetail.bomType eq 'P' || invoiceDetail.bomType eq 'S')}">
								<button id="${invoiceDetail.invoiceLineId}_btnAddClaim"  onclick="addClaimForm('${invoiceDetail.invoiceLineId}')"  type="button" class="btn btn-theme w-100">
									<i class="fa fa-plus" aria-hidden="true"></i> Add
								</button>
							</c:if>
						</c:if>
						<c:if test="${!isItemCodeVisible}">
							<button id="${invoiceDetail.invoiceLineId}_btnAddClaim" disabled  type="button" class="btn btn-theme-gray w-100">
								<i class="fa fa-plus" aria-hidden="true"></i> Add
							</button>
						</c:if>
					</td>
				</c:if>
				
		        <c:if test="${(invoiceDetail.previouslyClaimed ge invoiceDetail.quantity)}">
					<td>Maximum Quantity Claimed</td>
				</c:if>
	         </c:when>
			 <c:otherwise>
					<td width="120" class="add-btn-space">
						<button id="${invoiceDetail.invoiceLineId}_btnAddClaim" disabled  type="button" class="btn btn-theme-gray w-100">
							<i class="fa fa-plus" aria-hidden="true"></i> Add
						</button>
					</td>
			 </c:otherwise>
		</c:choose>
	</tr>
  	<!-- DISPLAY logic ended here. -->
</c:forEach>
<script>
	var invoiceDetailList = ${jsonArray};
	var invoiceClaimList = [];
	
</script>

<script
src="<%=request.getContextPath()%>/js/claim_detail.js?t=<%=timestamp%>" type="text/javascript"></script>