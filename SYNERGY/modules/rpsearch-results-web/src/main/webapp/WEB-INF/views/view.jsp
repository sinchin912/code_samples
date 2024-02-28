<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="javax.portlet.PortletRequest"%>
<%@page import="com.liferay.portal.kernel.portlet.PortletURLFactoryUtil"%>
<%@page import="javax.portlet.PortletURL"%>
<%@page import="com.liferay.portal.kernel.exception.SystemException"%>
<%@page import="com.liferay.portal.kernel.exception.PortalException"%>
<%@page import="com.liferay.portal.kernel.service.LayoutLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.service.GroupLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.model.Layout"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayPortletContext"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="init.jsp"%>

<%@page import="com.liferay.journal.model.JournalArticle"%>
<%@page import="com.liferay.journal.service.JournalArticleLocalServiceUtil"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.liferay.portal.kernel.theme.ThemeDisplay"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>

<%
	String pageName = themeDisplay.getLayout().getName(themeDisplay.getLocale());
	String layoutId = themeDisplay.getLayout().getPlid() + "";
	
%>

<% if (pageName.equalsIgnoreCase("Recent Activity")) { %>
	<c:set var="isRecentActivityPage" value="true"></c:set>
<% } %>

<% if (pageName.equalsIgnoreCase("orders")) { %>
	<c:set var="isOrdersPage" value="true"></c:set>
<% } %>


 <div class="container">
	<div class="row" style="display:block">
		<div class="col-sm-9">
	        <c:if test="${isRecentActivityPage}">
	            <jsp:include page="/WEB-INF/views/order_results.jsp"/>
	            <jsp:include page="/WEB-INF/views/shipment_results.jsp"/>
	        </c:if>
	
	        <c:if test="${isOrdersPage}">
	          <jsp:include page="/WEB-INF/views/order_results.jsp"/>
	        </c:if>
		</div>
		 <c:if test="${isRecentActivityPage || isOrdersPage}">
			<div class="col-sm-3">
				<!-- deal of the Day web content Import -->
				 <%
	                String articleName = "DEAL-OF-THE-DAY";
	                String content = StringPool.BLANK;
	                String articleName2 = "BEST-SELLERS";
	                String content2 = StringPool.BLANK;
	                String languageId = "en_US";
	                JournalArticle journalArticle = JournalArticleLocalServiceUtil.getArticleByUrlTitle(themeDisplay.getScopeGroupId(), articleName);// getting the journalArticle Object based on name
	                content = JournalArticleLocalServiceUtil.getArticleContent(themeDisplay.getLayout().getGroupId(), journalArticle.getArticleId(), "view", journalArticle.getDDMTemplateKey(), languageId, null, themeDisplay);
	                JournalArticle journalArticle2 = JournalArticleLocalServiceUtil.getArticleByUrlTitle(themeDisplay.getScopeGroupId(), articleName2);// getting the journalArticle Object based on name
	                content2 = JournalArticleLocalServiceUtil.getArticleContent(themeDisplay.getLayout().getGroupId(), journalArticle2.getArticleId(), "view", journalArticle2.getDDMTemplateKey(), languageId, null, themeDisplay);
				  %>
				 <%=content %>
				 <%=content2 %>
	        </div>
        </c:if>
    </div>
</div>