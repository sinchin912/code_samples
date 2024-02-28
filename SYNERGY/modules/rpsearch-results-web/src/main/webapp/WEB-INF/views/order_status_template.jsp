<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.hbg.rp.search.dto.Tracking"%>
<%@ include file="init.jsp" %>
<%@page import="com.liferay.portal.kernel.portlet.LiferayPortletContext"%>
<%@ page import = "java.util.Date" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<liferay-theme:defineObjects/>

<%
	LiferayPortletContext context = (LiferayPortletContext) portletConfig.getPortletContext();
	long timestamp = context.getPortlet().getTimestamp();
	Tracking trackingData = (Tracking) request.getAttribute("trackingData");
	String showZendeskFlag = String.valueOf(request.getAttribute("showZendeskFlag"));
	Boolean ZENDESK = (Boolean)request.getAttribute("ZENDESK");
	String shipmentHeaderIdRef = (String)request.getAttribute("shipmentHeaderIdRef");
	Date orderRecievedDate = (Date)request.getAttribute("orderRecievedDate");
	Date shipDate = (Date)request.getAttribute("shipDate");
%>

<portlet:resourceURL id="initiateZendeskTicket" var="initiateZendeskTicketURL"> </portlet:resourceURL>


<fmt:formatDate type="date" dateStyle="short" value="${orderRecievedDate}" var="formatOrderRecievedDate" />
<fmt:formatDate type="date" dateStyle="short" value="${shipDate}" var="formatShipDate" />

<div id="shipment-data-row">
<c:set var="trackingStatus" value="${fn:toUpperCase(trackingData.status)}"></c:set>
<input class="trackingURL" name="${trackingData.trackingNumber}" type="hidden" value="${trackingData.url}"/>
<input type="hidden" class="trackingStatus" value="${trackingStatus}" />
<input type="hidden" class="" value="${ZENDESK}" />

<c:choose>
		   <c:when test="${trackingStatus == 'DELIVERED' }"><!-- If the shipment status is Delivered. -->
		       <div class="row bs-wizard" style="border-bottom:0;">
					<div class="col-xs-3 bs-wizard-step complete"> <!-- Step-1 Complete -->
						<div class="progress">
							<div class="progress-bar"></div>
						</div>
						<div class="bs-wizard-dot"><span><i class="fa fa-check" aria-hidden="true"></i>
							</span></div>
						<div class="bs-wizard-info text-center">Order Placed <br> <span id="orderRecievedDate">${formatOrderRecievedDate}</span> </div>
					</div>
					<div class="col-xs-3 bs-wizard-step complete"> <!-- Step-2 complete -->
						<div class="progress">
							<div class="progress-bar"></div>
						</div>
						<div class="bs-wizard-dot"><span><i class="fa fa-check" aria-hidden="true"></i>
							</span></div>
						<div class="bs-wizard-info text-center">Items Shipped <br> <span id="shipDate">${formatShipDate}</span> </div>
					</div>
					<div class="col-xs-3 bs-wizard-step complete"> <!-- Step-3 complete -->
						<!-- complete -->
						<div class="progress">
							<div class="progress-bar"></div>
						</div>
						<div class="bs-wizard-dot"><span><i class="fa fa-check" aria-hidden="true"></i>
							</span></div>
						<div class="bs-wizard-info text-center">In Transit</div>
					</div>
					<div class="col-xs-3 bs-wizard-step complete"> <!-- Step-4 complete -->
						<!-- active -->
						<div class="progress">
							<div class="progress-bar"></div>
						</div>
						<div class="bs-wizard-dot"><span><i class="fa fa-check" aria-hidden="true"></i></span></div>
						<div class="bs-wizard-info text-center"> Delivered <br> 
							<fmt:formatDate type="date" dateStyle="short" value="${trackingData.deliveryDate }" /></div>
					</div>
				</div>	
		   </c:when>
		   
		   <c:when test="${trackingStatus == 'IN_TRANSIT' }"> <!-- If the shipment status is IN_TRANSIT. -->

		     <div class="row bs-wizard" style="border-bottom:0;"> 
				<div class="col-xs-3 bs-wizard-step complete"> <!-- Step-1 Complete -->
					<div class="progress">
						<div class="progress-bar"></div>
					</div>
					<div class="bs-wizard-dot"><span><i class="fa fa-check" aria-hidden="true"></i>
						</span></div>
					<div class="bs-wizard-info text-center">Order Placed <br> <span id="orderRecievedDate">${formatOrderRecievedDate}</span> </div>
				</div>
				<div class="col-xs-3 bs-wizard-step complete"> <!-- Step-2 complete -->
					<div class="progress">
						<div class="progress-bar"></div>
					</div>
					<div class="bs-wizard-dot"><span><i class="fa fa-check" aria-hidden="true"></i>
						</span></div>
					<div class="bs-wizard-info text-center">Items Shipped <br> <span id="shipDate">${formatShipDate}</span> </div>
				</div>
				<div class="col-xs-3 bs-wizard-step active"> <!-- Step-3 Active -->
					<!-- complete -->
					<div class="progress">
						<div class="progress-bar"></div>
					</div>
					<div class="bs-wizard-dot"><span><i class="fa fa-check" aria-hidden="true"></i>
						</span></div>
					<div class="bs-wizard-info text-center">In Transit <br> Currently: <c:out value="${trackingData.location}"></c:out> </div>
				</div>
				<div class="col-xs-3 bs-wizard-step disabled"> <!-- Step-4 disabled -->
					<!-- active -->
					<div class="progress">
						<div class="progress-bar"></div>
					</div>
					<div class="bs-wizard-dot">
						<span> 4 </span>
					</div>
					<div class="bs-wizard-info text-center"> Delivered </div>
				</div>
			  </div>	
		   </c:when>
		   <c:when test="${(trackingStatus == 'UNKNOWN' or trackingStatus =='ERROR' or empty trackingData.trackingNumber) && empty trackingData.message}">	   	   
                  <div class="col-xs-6">
                  <div class="row bs-wizard" style="border-bottom:0;">
                   <div class="col-xs-6 bs-wizard-step complete">
                   <div class="progress">
                 <div class="progress-bar"></div>
                  </div>
                  <a href="#" class="bs-wizard-dot"><span><i class="fa fa-check" aria-hidden="true"></i>
                  </span></a>
                  <div class="bs-wizard-info text-center">Order Placed <br> <span id="orderRecievedDate">${formatOrderRecievedDate}</span></div>
                    </div>

                   <div class="col-xs-6 bs-wizard-step complete">
                    <!-- complete -->
                    <div class="progress">
                    <div class="progress-bar"></div>
                    </div>
                   <a href="#" class="bs-wizard-dot"><span><i class="fa fa-check" aria-hidden="true"></i>
                   </span></a>
                    <div class="bs-wizard-info text-center">Items Shipped <br>  <span id="shipDate">${formatShipDate}</span> </div>
                   </div>
                    </div>
                    </div>
                   <div class="${showZendeskFlag ? 'col-xs-6' : 'hidden'}">
                      <div class="request-tracking-block">
	                    <div class="inner-section"> 
		                    <div class="${ZENDESK ? 'hidden': ''}" id="sendRequest-${shipmentHeaderIdRef}">
		                    	<div class="row">
		                    		<div class="col-sm-8">Please click the Request Tracking link to initiate a request with Customer Service to gather the tracking number and information from the carrier.</div>
		                   			<div class="col-sm-4">
									<springfm:form name="initiateZendesk" id="initiateZendesk">
											<button id="initiateZendesk_-${shipmentHeaderIdRef}" class="btn btn-theme"> Request Tracking</button>
									</springfm:form>
		                   			</div>
		                   		</div>
		                    </div>
	                    <div class="${ZENDESK ? '': 'hidden'}" id="emailMessage-${shipmentHeaderIdRef}">
		                    <div class="row">
			                     <div class="col-sm-1"><img class="pl" src="<%=request.getContextPath()%>/images/sentenvelope.svg" alt="SENT ENVELOPE"></div>
			                     <div class="col-sm-11">Please check your email within 48 hours for tracking number or information about this carrier from your Customer Service Rep.</div>
		                    </div>
	                    </div>
	                  </div>
	                   </div>
                   </div>
		   </c:when>
		   
		   <c:when test="${!empty trackingData.message}">	
				<div class="col-xs-8">
					<div class="col-sm-12">
						<font color="red"><b><c:out value="${trackingData.message}"></c:out></b></font>
					</div>
				</div>
		   </c:when>
			
		   <c:otherwise>
		     <div class="row bs-wizard" style="border-bottom:0;"> 
			    <div class="col-xs-3 bs-wizard-step complete"> <!-- Step-1 Complete -->
					<div class="progress">
						<div class="progress-bar"></div>
					</div>
					<div class="bs-wizard-dot"><span><i class="fa fa-check" aria-hidden="true"></i>
						</span></div>
					<div class="bs-wizard-info text-center">Order Placed <br> <span id="orderRecievedDate">${formatOrderRecievedDate}</span> </div>
				</div>
				<div class="col-xs-3 bs-wizard-step complete"> <!-- Step-2 complete -->
					<div class="progress">
						<div class="progress-bar"></div>
					</div>
					<div class="bs-wizard-dot"><span><i class="fa fa-check" aria-hidden="true"></i>
						</span></div>
					<div class="bs-wizard-info text-center">Items Shipped <br> <span id="shipDate">${formatShipDate}</span> </div>
				</div>
				<div class="col-xs-3 bs-wizard-step active">  <!-- Step-3 active -->
					<!-- complete -->
					<div class="progress">
						<div class="progress-bar"></div>
					</div>
					<div class="bs-wizard-dot"><span><i class="fa fa-check" aria-hidden="true"></i>
						</span></div>
					<div class="bs-wizard-info text-center"> ${trackingStatus} </div>
				</div>
				<div class="col-xs-3 bs-wizard-step disabled"> <!-- Step-4 disabled -->
					<!-- active -->
					<div class="progress">
						<div class="progress-bar"></div>
					</div>
					<div class="bs-wizard-dot">
						<span> 4 </span>
					</div>
					<div class="bs-wizard-info text-center"> Delivered </div>
				</div>
			  </div>	
		   </c:otherwise>

</c:choose>
 
 </div>
 
  <script src="<%=request.getContextPath()%>/js/order_detail.js?t=<%=timestamp%>"
	portlet_namespace="<portlet:namespace/>"
	zendesk_url="<%=initiateZendeskTicketURL %>" type="text/javascript"></script>
