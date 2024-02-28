<%@page import="com.liferay.portal.kernel.portlet.LiferayPortletContext"%>

<%@include file="init.jsp" %>

<%-- <jsp:include page="/WEB-INF/views/view.jsp"/> --%>


<div class="container">
	<div class="row">
		<div class="">
			<c:choose>
				<c:when test="${!empty renderjsp}">
					<jsp:include page="${renderjsp}"></jsp:include>
				</c:when>
			</c:choose>
		</div>
	</div>
</div>
<%
LiferayPortletContext context = (LiferayPortletContext) portletConfig.getPortletContext();
long timestamp = context.getPortlet().getTimestamp();
%>
<script src="<%=request.getContextPath()%>/js/show_view.js?t=<%=timestamp%>"
	 view-id="${formparam['viewId']}" ></script>