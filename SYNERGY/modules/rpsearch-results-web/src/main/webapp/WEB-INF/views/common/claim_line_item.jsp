<%@page import="com.hbg.rp.search.constants.ApplicationConstant"%>
<%@page import="com.hbg.rp.search.util.CommonUtil"%>
<%@page import="com.hbg.rp.search.constants.ClaimConstants"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<portlet:defineObjects />
<div id="claim-line-items">
<%
	CommonUtil commonUtil = new CommonUtil();
	String allReportingGroups = commonUtil.getAllReportingGroups();
	String reportingGroups = commonUtil.getPermittedReportingGroups(renderRequest);
	String encryptedIsbn = ApplicationConstant.ENCRYPTED_ISBN;
	String encryptedTitle = ApplicationConstant.ENCRYPTED_TITLE;
	String wrongBookLabel = ClaimConstants.WRONG_BOOK_LABEL;
	String wrongBookNextLine = "<br>"+wrongBookLabel+ " ";
	String wrongBookWithEmptySpace = wrongBookLabel+ " ";
%>
<c:set var="allReportingGroups" value="<%=allReportingGroups %>" />
<c:set var="reportingGroups" value="<%=reportingGroups %>" />
<c:set var="encryptedIsbn" value="<%=encryptedIsbn %>" />
<c:set var="encryptedTitle" value="<%=encryptedTitle %>" />
<c:set var="wrongBookLabel" value="<%=wrongBookLabel %>" />
<c:set var="wrongBookNextLine" value="<%=wrongBookNextLine %>" />
<c:set var="wrongBookWithEmptySpace" value="<%=wrongBookWithEmptySpace %>" />
	<c:choose>
		<c:when test="${!empty claimLineItemData}">
		<div class="table_outer m-0">
			<div class="inline_table">
				<table class="table claim-table">
				<thead>
							<tr>
								<th width = 50>#</th>
								<th width ="135">Retailer<br>Claim #
								</th>
								<th width="115">Item Code<br>(ISBN/EAN)
								</th>
								<th width="160">Title</th>
								<th width="90">Quantity Shipped</th>
								<c:if test="${nonRpCLaim ne 'true'}">
								<th width="90">Reship</th>
								</c:if>
								<th width="200">Claim Type</th>
								<th width="75">Quantity <br> Claimed </th>
								<th width="100">Claim <br>${nonRpCLaim eq 'true' ? "Processed" :"Submitted"}  </th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${claimLineItemData}" var="claimlineItem"
								varStatus="claimLineItemCount">
								<c:set var="isItemCodeVisible" value="${reportingGroups == '0' ? true : reportingGroups.contains(claimlineItem.reportingGroupCode)}" />
								<c:set var="allowedOwnerCode" value="${allReportingGroups.contains(claimlineItem.reportingGroupName) ? true : false}" />
								<tr>
									<%-- <td>${claimLineItemCount.count}</td> --%>
									<td>${nonRpCLaim eq 'true' ? claimlineItem.invoiceLineNumber : claimlineItem.claimLineNumber}</td>
									<td>${retailerClaimNumber}</td>
									<td class="${isItemCodeVisible ? '' : 'hide-item'}">
										<c:if test="${not allowedOwnerCode}">
											<c:out value="${isItemCodeVisible ? claimlineItem.isbn : encryptedIsbn}" />
										</c:if>
										<c:if test="${allowedOwnerCode}">
											<c:out value="${isItemCodeVisible ? claimlineItem.isbn : encryptedIsbn}" />
										</c:if>
									</td>
									<td  class="${isItemCodeVisible ? '' : 'hide-item'}">
										<c:if test="${not allowedOwnerCode}">
											<c:out value="${isItemCodeVisible ? claimlineItem.shortTitle : encryptedTitle}" />
										</c:if>
										<c:if test="${allowedOwnerCode}">
											<c:out value="${isItemCodeVisible ? claimlineItem.shortTitle : encryptedTitle}" />
										</c:if>
									</td>
									<td>${nonRpCLaim eq 'true' ? claimlineItem.quantityClaim : claimlineItem.shipQuantity}</td>
									<c:if test="${nonRpCLaim ne 'true'}">
		                            <td>${nonRpCLaim eq 'true' ? "" : (claimlineItem.reShipFlag eq 'Y' ?  'Yes': 'No')}</td>
		                            </c:if>
									<td>${claimTypeDesc}</td>
									<td>${nonRpCLaim eq 'true' ? (claimlineItem.quantity * (-1)) :claimlineItem.claimQuantity}</td>
									<td><b><fmt:formatDate type="date" dateStyle="short"
												value="${nonRpCLaim eq 'true' ? createdDate : claimlineItem.createdDate}" /></b></td>	
							 </tr>
							</c:forEach>
							
							<tr class="total-row invoice-total gray-color-row">
								<td colspan="9">&nbsp;</td>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
					<c:choose>
					<c:when test="${nonRpCLaim ne 'true'}">
						<div class="claim-form-content p-1">
							<div class="row">
								<div class="col-sm-12">
									<p class="m-0">Claim Comments:</p>
									<c:set var="replaceClaimCommentValue" value="${claimComment.startsWith(wrongBookLabel) ? wrongBookWithEmptySpace : wrongBookNextLine}" />
									<label class="mb-1">${fn:replace(fn:replace(claimComment,wrongBookLabel, replaceClaimCommentValue),';','; ')}</label>
								</div>
							</div>
						</div>
					</c:when>
				</c:choose>
							
			</div>
		</c:when>
		<c:otherwise>
			<div class="col-sm-12 no-info">
				<br> <b>Sorry, Information is not available</b>
			</div>
			<div class="clearfix"></div>
		</c:otherwise>
	</c:choose>
</div>