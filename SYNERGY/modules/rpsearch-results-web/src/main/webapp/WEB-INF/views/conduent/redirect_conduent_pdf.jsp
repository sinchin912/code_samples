<%@page import="com.hbg.rp.model.OrderLine"%>
<%@page import="com.hbg.rp.search.util.PortalMappingsUtil"%>
<%@page import="com.hbg.rp.search.constants.ApplicationConstant"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@include file="../init.jsp"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="com.liferay.portal.kernel.model.Role"%>
<%@page import="java.util.List"%>

 
 <%
	 List<Role> loggedInUserRoles = themeDisplay.getUser().getRoles();
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
	
	<c:if test='<%=userRole.equalsIgnoreCase(ApplicationConstant.NRP_RETRIEVE_INVOICE_PDF)%>'>
		<c:set var="NRP_RETRIEVE_INVOICE_PDF" value="true"></c:set>
	</c:if>
</c:forEach>
 
 
<c:if test="${not empty rowInvType}">
	<c:set var="typeOfInvoice" value="${rowInvType == 'DBN' ? 'Debit' : (rowInvType == 'CRN' ? 'Credit' : 'Invoice')}"></c:set>
</c:if>
<c:choose>
	<c:when test="${(typeOfInvoice=='Debit' && ((not empty ALL && ALL) ||  NRP_RETRIEVE_DEBIT_MEMO_PDF)) ||
					(typeOfInvoice=='Credit' && ((not empty ALL && ALL) ||  NRP_RETRIEVE_CREDIT_MEMO_PDF)) ||
					(typeOfInvoice=='Invoice' && ((not empty ALL && ALL) ||  NRP_RETRIEVE_INVOICE_PDF)) }">
		<div class="content">
			<div class="overlay">
				<img src="<%=request.getContextPath()%>/images/oval.svg" alt="Redirecting..." style="height: 80px;z-index:20;">
			</div>
			<div id="message" style="padding:5px 15px;background:white; display:none;">	
				<h4>Some thing went wrong..</h4>
			</div>
		</div>
		
		 <springfm:form action="#" id="getInvoicePdfForm" name="getInvoicePdfForm" >
			<input type="submit" style="display:none">
		</springfm:form>
		<portlet:resourceURL id="getInvoicePdfUrl" var="getInvoicePdfUrlURL"> </portlet:resourceURL>
		<script type="text/javascript">
			$(document).ready(function (e) {
				$("#getInvoicePdfForm").trigger("submit");  
			});
			$("#getInvoicePdfForm").submit(function (e) {
				var categoryName="ICA", reportName="ICA_Document";
				AUI().use('aui-io-request', function(A){
					$('body').append($('.content'));
					//aui ajax call to get updated content
					A.io.request('<%=getInvoicePdfUrlURL%>', {
						method : 'post',
						headers: {"X-CSRF-TOKEN": $("#getInvoicePdfForm input[name='_csrf']").val()},
						data : {
							<portlet:namespace/>reportName : reportName,
							<portlet:namespace/>categoryName : categoryName
					    },
						on : {
							success : function() {
								resp = this.get('responseData');
								if (resp == undefined || resp == null || !resp)
									return false;
								//console.log('resp:'+resp);
								resp = resp.replace(/'/g, '"');
								var json = JSON.parse(resp);
								var code = json.code;
								var message = json.message;
								var url = json.data;
								if(code != undefined && code == '400') { // Case of not getting the url.
									// window.location.href = window.location.href;
									$('.overlay').hide();
									$('#message h4').html(message);
									$('#message').show("slow");
								} else {
									// Url getting.
									$('.overlay').hide();
									$('#message h4').html("Loading the document...");
									$('#message').show("slow");
									window.location.href = url;						
								}
							},
							failure : function(res) {
								//alert('Failure:'+res);
							}
						}
					});
				});
				e.preventDefault(); //STOP default action
			});
		</script>
	</c:when>
	<c:otherwise>
		<div class="content">
			<div class="overlay">
			</div>
			<div id="message" style="padding:5px 15px;background:white;">
				<h4> You do not have permission to access this PDF (Or the invoice number is not valid). </h4>
			</div>
		</div>
		<script>
			$(document).ready(function() {
				$('body').append($('.content'));
			});
		</script>
	</c:otherwise>
</c:choose>

<style>
	.aui #wrapper { min-height: 180px; }
	.overlay { position: absolute; top: 50%; right: auto; bottom: auto; left: 47%; }
	.content { background-color: rgba(0,0,0,0.5); width: 100%; height: 100vh; position: absolute; top: 0; }
</style>
