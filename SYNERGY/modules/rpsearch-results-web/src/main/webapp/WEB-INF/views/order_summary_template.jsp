<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="init.jsp" %>
<portlet:defineObjects />

<portlet:actionURL name="getSearchOrderDetail" var="getSearchOrderDetailURL">
	<portlet:param name="pono" value="${data.invoiceHeader.poNbr}" />
	<portlet:param name="pono_operator" value="EQ" />
</portlet:actionURL>

<portlet:actionURL name="getSearchByAccount" var="getSearchByAccountURL">
	<portlet:param name="accountpk" value="${data.orderDTO.orderHeader.billToKey}" />
	<portlet:param name="accountname" value="${data.orderDTO.orderHeader.accountName}" />
	<portlet:param name="accountnumber" value="${data.orderDTO.orderHeader.accountNumber}" />
	<portlet:param name="accountdata" value="${data.orderDTO.orderHeader.accountName} | ${data.orderDTO.orderHeader.accountNumber}" />
	<portlet:param name="accountKey" value="${data.orderDTO.orderHeader.billToKey}" />
</portlet:actionURL>

<portlet:actionURL name="getSearchByAccount" var="getSearchByAccountNoURL">
	<portlet:param name="accountpk" value="${data.orderDTO.orderHeader.billToKey}" />
	<portlet:param name="accountname" value="${data.orderDTO.orderHeader.accountName}" />
	<portlet:param name="accountnumber" value="${data.orderDTO.orderHeader.accountNumber}" />
	<portlet:param name="accountdata" value="${data.orderDTO.orderHeader.accountName} | ${data.orderDTO.orderHeader.accountNumber}" />
	<portlet:param name="accountKey" value="${data.orderDTO.orderHeader.billToKey}" />
</portlet:actionURL>

<portlet:actionURL name="getSearchByStore" var="getSearchByStoreURL">
	<portlet:param name="storepk" value="${data.orderDTO.orderHeader.shipToKey}" />
	<portlet:param name="accountpk" value="${data.orderDTO.orderHeader.billToKey}" />
	<portlet:param name="storename" value="${data.orderDTO.orderHeader.shiptoName}" />
	<portlet:param name="storenumber" value="${data.orderDTO.orderHeader.shiptoNumber}" />
	<portlet:param name="accountname" value="${data.orderDTO.orderHeader.accountName}" />
	<portlet:param name="accountnumber" value="${data.orderDTO.orderHeader.accountNumber}" />
	<portlet:param name="storedata" value="${data.orderDTO.orderHeader.shiptoName} | ${data.orderDTO.orderHeader.shiptoNumber}" />
	<portlet:param name="storedataRetain" value="${data.orderDTO.orderHeader.shipToKey}" />
	<portlet:param name="accountdata" value="${data.orderDTO.orderHeader.accountName} | ${data.orderDTO.orderHeader.accountNumber}" />
	<portlet:param name="accountKey" value="${data.orderDTO.orderHeader.billToKey}" />
</portlet:actionURL>

<div class="table_outer">
	<header class="table_header">
	    <span class="headding">Order Summary</span>
	</header>
    <div class="body_content">
        <div class="row">
            <div class="col-sm-3">
                <div class="table_content">
                    <table class="table">
                        <tbody>
                       		<tr>
								<td>PO: 
									<a href="<%=getSearchOrderDetailURL%>" >${data.invoiceHeader.poNbr} </a>
						       </td>	
							</tr>
                            <tr>
								<td>Order Received
									Date: <b><fmt:formatDate type="date" dateStyle="short"
											value="${data.orderDTO.orderHeader.orderRecievedDate}" /></b></td>
							</tr>
							<tr>
								<td>Order Processed
									Date: <b><fmt:formatDate type="date" dateStyle="short"
											value="${data.orderDTO.orderHeader.orderProcessedDate}" /></b></td>
							</tr>
                            <tr>
                               	<c:choose>
									<c:when test="${ fn:toLowerCase(data.orderDTO.orderHeader.orderStatus) eq 'cancelled' || 
											fn:toLowerCase(data.orderDTO.orderHeader.orderStatus) eq 'deleted' }">
										 <c:set var="statusMessage" value="Please contact Customer Service for more information" />
										 <c:set var="statusText" value="CANCELED" />
									</c:when>
									<c:when test="${data.orderDTO.orderHeader.orderStatus eq 'Shipped with Exceptions'}">
										<c:set var="statusMessage" value="We consider this order complete" />
										<c:set var="statusText" value="${data.orderDTO.orderHeader.orderStatus}" />
									</c:when>
									<c:otherwise>
										<c:set var="statusText" value="${data.orderDTO.orderHeader.orderStatus}" />
									</c:otherwise>
								</c:choose>
                                <td>Order Status: 
									<b class="text-capitalize" title="${statusMessage}">${fn:toLowerCase(statusText)}</b>
								</td>
                             </tr>
                             <tr>
                                 <td>Order Source:  <b>${data.orderDTO.orderHeader.orderSource}</b></td>
							 </tr>
                         </tbody>
                      </table>
                   </div>
               </div>
               <div class="col-sm-3">
                   <div class="table_content">
                       <table class="table">
                           <tbody>
                          		<tr>
									<td>Account Name: 
									    <a href="<%=getSearchByAccountURL%>" >${fn:toLowerCase(data.orderDTO.orderHeader.accountName)}
											<span id="accountpk" hidden>${data.orderDTO.orderHeader.billToKey}</span>
											<span id="accountname" hidden>${data.orderDTO.orderHeader.accountName}</span>
											<span id="accountnumber" hidden>${data.orderDTO.orderHeader.accountNumber}</span>
										</a>
								   </td>
							     </tr>
                                 <tr>
									<td>Account Number: 
										<a href="<%=getSearchByAccountNoURL%>">${data.orderDTO.orderHeader.accountNumber}
											    <span id="accountpk" hidden>${data.orderDTO.orderHeader.billToKey}</span>
												<span id="accountname" hidden>${data.orderDTO.orderHeader.accountName}</span>
												<span id="accountnumber" hidden>${data.orderDTO.orderHeader.accountNumber}</span>
										</a>
								   </td>
								</tr>	
                                <tr>
                                     <td>Offer Code:
                                          <c:choose>
										  <c:when test="${ not empty data.orderDTO.specialOffers }">
											<c:forEach items="${data.orderDTO.specialOffers }" var="offerCode"
												varStatus="count">
												<c:if test="${count.count > 1}">,</c:if>
												<b>${offerCode.offerCode}</b>
											</c:forEach>
										  </c:when>
										  <c:otherwise> N/A </c:otherwise>
								       </c:choose>
                                       </td>
                                    </tr>
                                 </tbody>
                             </table>
                         </div>
                     </div>
                  <div class="col-sm-3">
                      <div class="table_content">
                          <table class="table">
                              <tbody>
                                 <tr> <td>Destination:
								 <c:choose>
										<c:when test="${ not empty data.orderDTO.orderHeader.destinationName }">
										<span class="text-capitalize">
										<c:out value="${data.orderDTO.orderHeader.destinationName}" />
										</span>
										</c:when>
										<c:otherwise>
										<span class="text-capitalize">
										<c:out value="${data.orderDTO.orderHeader.shiptoName}" />
										</span>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
							<tr>
							   <c:set var="shipToNumber" value="${data.orderDTO.orderHeader.shiptoNumber}" />
								<td><c:if test="${shipToNumber > 0}">  Store #: 
									   <a href="<%=getSearchByStoreURL%>">${data.orderDTO.orderHeader.shiptoNumber}
										    <span id="storepk" hidden>${data.orderDTO.orderHeader.shipToKey}</span>
											<span id="accountpk" hidden>${data.orderDTO.orderHeader.billToKey}</span>
											<input id="storename" type="hidden" value="${data.orderDTO.orderHeader.shiptoName}" />
											<span id="storenumber" hidden>${data.orderDTO.orderHeader.shiptoNumber}</span>
											<span id="accountname" hidden>${data.orderDTO.orderHeader.accountName}</span>
											<span id="accountnumber" hidden>${data.orderDTO.orderHeader.accountNumber}</span>
                                       </a>
									</c:if>
								 </td>
							</tr>
							 <tr>
								<td>
									<c:choose>
										<c:when test="${ not empty data.orderDTO.orderHeader.destinationName }">
											   <portlet:actionURL name="getSearchByZip" var="getSearchByZipURL">
													<portlet:param name="address" value="${data.orderDTO.orderHeader.destinationZip}" />
													<portlet:param name="invoice-destination" value="${data.orderDTO.orderHeader.destinationZip}" />
												</portlet:actionURL>
											Destination Address:
											<span class="text-capitalize">
												<c:out value="${data.orderDTO.orderHeader.destinationName},
												${fn:toLowerCase(data.orderDTO.orderHeader.destinationCity)},
												${data.orderDTO.orderHeader.destinationState}" />
											</span>
											<c:set var="destination" value="${data.orderDTO.orderHeader.destinationZip}" />
											
										  <a href="<%=getSearchByZipURL%>"> ${data.orderDTO.orderHeader.destinationZip}
											  <span id="zipcode" hidden>${data.orderDTO.orderHeader.destinationZip}</span>
										  </a>
											
										</c:when>
										<c:when test="${ not empty data.orderDTO.orderHeader.shiptoName }">
										
											<portlet:actionURL name="getSearchByZip" var="getSearchByShipZipURL">
												<portlet:param name="address" value="${data.orderDTO.orderHeader.shiptoZip}" />
												<portlet:param name="invoice-destination" value="${data.orderDTO.orderHeader.shiptoZip}" />
											</portlet:actionURL>
										
											Destination Address:
											<span class="text-capitalize">
												<c:out value="${data.orderDTO.orderHeader.shiptoName},
													${fn:toLowerCase(data.orderDTO.orderHeader.shiptoCity)},
													${data.orderDTO.orderHeader.shiptoState}" />
											</span>
											<c:set var="ship_destination" value="${data.orderDTO.orderHeader.shiptoZip}" />
											
										   <a href="<%=getSearchByShipZipURL%>"> ${data.orderDTO.orderHeader.shiptoZip}
											  <span id="zipcode" hidden>${data.orderDTO.orderHeader.shiptoZip}</span>
										   </a>
										</c:when>
									</c:choose>
								</td>
							</tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">
	
	function searchByPO(formId){
		document.getElementById(formId).submit();
	}
	
	function searchByAccountName(formId){
		document.getElementById(formId).submit();
	}
	
	function searchByAccountNo(formId){
		document.getElementById(formId).submit();
	}
	
	function searchByPO(formId){
		document.getElementById(formId).submit();
	}
	
	function searchByStore(formId){
		document.getElementById(formId).submit();
	}
	
	function searchByDestinationZip(formId){
		document.getElementById(formId).submit();
	}
	
	function searchByShipDestinationZip(formId){
		document.getElementById(formId).submit();
	}

</script>