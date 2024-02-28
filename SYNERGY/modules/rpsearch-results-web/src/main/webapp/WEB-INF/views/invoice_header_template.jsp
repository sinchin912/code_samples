<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="row" style="margin-bottom: 40px;">
    <div class="col-sm-12">
    	  <div class="col-sm-3">
            <div class="table_content">
                <table class="table">
                    <tbody>
                    	<tr>
                   			<c:choose>
                   				<c:when test="${ not empty data.invoiceHeader.destinationName }">
                   					<td>Ship to: 
	                            	<div class="text-capitalize"> <b>${data.invoiceHeader.destinationName} </b> </div>
	                            	<c:if test="${not empty data.destinationAddress }">
	                            		<div class="text-capitalize"> <b>${fn:toLowerCase(data.destinationAddress) } </b> </div>
	                            	</c:if>
	                            	<div><b>
		                            	<c:if test="${not empty data.invoiceHeader.destinationCity }">
		                            		<span class="text-capitalize"> ${fn:toLowerCase(data.invoiceHeader.destinationCity)}, </span>
		                            	</c:if>
		                            	<c:if test="${not empty data.invoiceHeader.destinationState }">
		                            		<span class="text-capitalize"> ${data.invoiceHeader.destinationState } </span>
		                            	</c:if>
		                            	<c:if test="${not empty data.invoiceHeader.destinationZip }">
		                            		<span class="text-capitalize"> ${data.invoiceHeader.destinationZip } </span>
		                               	</c:if>
	                               	</b></div>
	                               	</td>
                   				</c:when>
                   				<c:when test="${not empty data.invoiceHeader.shipToName }">
                   					<td>Ship to:
                   					<c:if test="${not empty data.invoiceHeader.shipToName }">
	                            		<div class="text-capitalize"> <b>${data.invoiceHeader.shipToName} </b> </div>
	                            	</c:if>
	                            	<c:if test="${not empty data.shipToAddress }">
	                            		<div class="text-capitalize"> <b>${fn:toLowerCase(data.shipToAddress) } </b> </div>
	                            	</c:if>
	                            	<div><b>
		                            	<c:if test="${not empty data.invoiceHeader.shipToCity }">
		                            		<span class="text-capitalize"> ${fn:toLowerCase(data.invoiceHeader.shipToCity)}, </span>
		                            	</c:if>
		                            	<c:if test="${not empty data.invoiceHeader.shipToState }">
		                            		<span class="text-capitalize"> ${data.invoiceHeader.shipToState } </span>
		                            	</c:if>
		                            	<c:if test="${not empty data.invoiceHeader.shipToZip }">
		                            		<span class="text-capitalize"> ${data.invoiceHeader.shipToZip } </span>
		                               	</c:if>
	                               	</b></div>
	                               	</td>
	                            </c:when>
                   			  </c:choose> 
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
                               <td>Bill to:  
                               	<c:if test="${not empty data.invoiceHeader.billToName }">
                            		<div class="text-capitalize"> <b>${data.invoiceHeader.billToName} </b> </div>
                            	</c:if>
                            	
                            	<c:if test="${not empty data.billToAddress }">
                            		<div class="text-capitalize"> <b>${fn:toLowerCase(data.billToAddress) } </b> </div>
                            	</c:if>
                            	
                            	<div><b>
                            	<c:if test="${not empty data.invoiceHeader.billToCity }">
                            		<span class="text-capitalize"> ${fn:toLowerCase(data.invoiceHeader.billToCity) }, </span>
                            	</c:if>
                            	<c:if test="${not empty data.invoiceHeader.billToState }">
                            		<span class="text-capitalize"> ${data.invoiceHeader.billToState } </span>
                            	</c:if>
                            	<c:if test="${not empty data.invoiceHeader.billToZip }">
                            		<span class="text-capitalize"> ${data.invoiceHeader.billToZip } </span>
                            	</c:if>
                            	</b></div>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
          <div class="col-sm-6">
            <div class="table_content">
                <table class="table">
                    <tbody>
                        <tr>
                            <td style="padding-left: 20px;"> Promo Code Used on Order:
                                <c:choose>
                                	<c:when test="${not empty data.invoiceHeader.offerCode }">
                                		<b> ${data.invoiceHeader.offerCode } </b>
                                	</c:when>
                                	<c:otherwise> <b>N/A</b> </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
          </div>
    </div>
</div>