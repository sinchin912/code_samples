<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.hbg.rp.search.constants.ApplicationConstant"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@page import="com.liferay.portal.kernel.model.Role"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.theme.ThemeDisplay"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="javax.portlet.PortletSession"%>

<portlet:defineObjects />
<liferay-theme:defineObjects/>
<c:set var="rowInvType" value="${data.invoiceHeader.invoiceType == 'DBN' ? 'Debit' : (data.invoiceHeader.invoiceType == 'CRN' ? 'Credit' : 'Invoice')}"></c:set>
<c:set value="1" var="serialNumber" />

 <%
	 List<Role> loggedInUserRoles = themeDisplay.getUser().getRoles();
	 Long extRepId = (long) renderRequest.getPortletSession().getAttribute(ApplicationConstant.LIFERAY_SHARED_EXT_REP_ID, PortletSession.APPLICATION_SCOPE);
	 Boolean isRepUser = (extRepId > 0) ? true : false;
 %>


<c:forEach var="role" items="<%=loggedInUserRoles%>">
	<c:set var="userRole" value="${role.getName()}"></c:set>
	<jsp:useBean id="userRole" type="java.lang.String" />

	<c:if test='<%=userRole.equalsIgnoreCase("all")%>'>
       	<c:set var="ALL" value="true"></c:set>
    </c:if>

	<c:if test='<%=userRole.equalsIgnoreCase(ApplicationConstant.NRP_RETRIEVE_CREDIT_MEMO_PDF)%>'>
		<c:set var="NRP_RETRIEVE_CREDIT_MEMO_PDF" value="true"></c:set>
	</c:if>

	<c:if test='<%=userRole.equalsIgnoreCase(ApplicationConstant.NRP_RETRIEVE_DEBIT_MEMO_PDF)%>'>
		<c:set var="NRP_RETRIEVE_DEBIT_MEMO_PDF" value="true"></c:set>
	</c:if>
</c:forEach>

<c:set var="isRepUser" value="<%=isRepUser%>" />
<c:set var="encryptedAmount" value="<%=ApplicationConstant.ENCRYPTED_AMOUNT %>" />

<div role="tabpanel" class="tab-pane" id="credit-memos">
    <div class="table_outer">
    <c:choose>
    	<c:when test="${not empty data }">
    		<div></div>
		    <div class="inline_table">
            <header class="table_header table-header-border border-none-bottom">
                <span class="serial_number"> ${serialNumber } </span>
                <span class="headding">${rowInvType} Memo Summary</span>
                <b class="download-pdf">
                	<portlet:renderURL var="getPdfConduent"
						windowState="<%=LiferayWindowState.MAXIMIZED.toString()%>">
						<portlet:param name="action" value="getPdfConduent" />
						<portlet:param name="<%=ApplicationConstant.INV_INVOICE_NBR %>" 
							value="${data.invoiceHeader.invoiceNumber }" />
					</portlet:renderURL>
					
					<c:if test="${(rowInvType=='Debit' && ((not empty ALL && ALL) || NRP_RETRIEVE_DEBIT_MEMO_PDF)) || (rowInvType=='Credit' && ((not empty ALL && ALL) || NRP_RETRIEVE_CREDIT_MEMO_PDF))}">
                    <a href="<%=getPdfConduent %>" target="_blank">
                            <i class="fa fa-download"></i>&nbsp; Download ${rowInvType} ${data.invoiceHeader.invoiceNumber} PDF </a>
                     </c:if>
                    </b>
                    
                   
                    
            </header>
            <div class="invoice-sub-header">
                <div class="col-sm-3">
                    <p>${rowInvType} Memo Number:
                        <b>${data.invoiceHeader.invoiceNumber } </b>
                    </p>
                </div>
                <div class="col-sm-3">
                    <p>${rowInvType} Memo Date:
                       <b>
                       		<fmt:parseDate var="invoiceDate" value="${data.invoiceHeader.invoiceDate}" pattern="yyyy-MM-dd" />
							<fmt:formatDate value="${invoiceDate}" pattern="M/d/yy" />
                       </b>
                    </p>
                </div>
                <div class="col-sm-6">
                	<c:if test="${not empty data.invoiceHeader.chargebackNumber && fn:trim(data.invoiceHeader.chargebackNumber) ne ''}">
                		<p>Charge Back Number:
	                        <b>${data.invoiceHeader.chargebackNumber }</b>
	                    </p>
                	</c:if>
                </div>
            </div>
            <table class="table">
                <thead>
                    <tr>
                        <th>Item Code <br> (ISBN/EAN)</th>
                        <th>Title</th>
                        <th class="long_title">Author(s)</th>
                        <th>Quantity ${rowInvType}</th>
                        <th>Quantity Claim</th>
                        <th>Unit Price</th>
                        <th>Discount %</th>
                        <th class="status_details"> Net Charge </th>
                    </tr>
                </thead>
                <tbody>
                    <c:set value="${data }" scope="request" var="data" />
					<jsp:include page="/WEB-INF/views/invoiceline_template.jsp"></jsp:include>
					<c:set scope="request" value="0" var="totalQty"></c:set>
					<c:set scope="request" value="0" var="totalNetCharge"></c:set>
					<c:forEach items="${data.invoiceLines }" var="invLine">
						<c:if test="${invLine.lineStatus eq 'Fulfilled' }">
							<c:set scope="request" value="${totalQty + invLine.quantity }" var="totalQty"></c:set>
							<c:set scope="request" value="${totalNetCharge + invLine.lineAmount }" var="totalNetCharge"></c:set>
							</c:if>
					</c:forEach>
                    <tr class="total-row invoice-total rep-invoice-total">
                       <td>&nbsp;</td>
                       <td colspan="2" style="text-align: right;font-weight: bold;">
                        <c:if test="${isRepUser}">
							<%=ApplicationConstant.SALES_REPRESENTATIVE %>
						</c:if>
						 Quantity ${rowInvType} Total: 
						 </td>
						 <td>
						  ${totalQty } 
						 </td>
                         <td colspan="3" class="text-right font-weight-bold">
                           <c:choose>
							    <c:when test="${isRepUser}">
									Total Net Charge(Tax Not Included): 
							    </c:when>
							    <c:otherwise>
									Total Net Charge: 
							    </c:otherwise>
			               </c:choose>
                        </td>
                        <td class="text-center font-weight-bold">
                           <c:choose>
							    <c:when test="${isRepUser}">
									$<fmt:formatNumber pattern="#,##0.00;-#,##0.00" type="number" minFractionDigits="2" 
	                                               										   maxFractionDigits="2" value="${totalNetCharge}" /> 
							    </c:when>
							    <c:otherwise>
									$<fmt:formatNumber pattern="#,##0.00;-#,##0.00" type="number" minFractionDigits="2" 
	                                               						 maxFractionDigits="2" value="${data.invoiceHeader.totalAmt }" />
							    </c:otherwise>
			               </c:choose>
                        </td>
                     </tr>
                     <tr class="invoice-total-row border-none">
                        <td colspan="8">
                           	<c:if test="${not empty data.invoiceComments }">
                           		<p>Comments: </p>
                           		<c:forEach var="comment" items="${data.invoiceComments }">
	                            	<p> ${comment } </p>
	                        	</c:forEach>
                           	</c:if>
                        </td>
                     </tr>
                </tbody>
              </table>
        	</div>
        	<c:set value="${serialNumber + 1 }" var="serialNumber" />
    	</c:when>
    	<c:otherwise>
    		Credit Memo details not found.
    	</c:otherwise>
    </c:choose>
    </div>
    <c:if test="${isRepUser}">
	    <p><b><%=ApplicationConstant.SALES_REPRESENTATIVE_DISTRIBUTION_CLIENTS %> <a href="mailto:<%=ApplicationConstant.MAIL_TO_INVOICES_HBG%>"><%=ApplicationConstant.MAIL_TO_INVOICES_HBG%></a>. </b></p>
	</c:if>
</div>