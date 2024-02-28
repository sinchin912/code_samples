<%@taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayPortletContext"%>
<%@page import="com.liferay.journal.model.JournalArticle"%>
<%@page import="com.liferay.journal.service.JournalArticleLocalServiceUtil"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.liferay.portal.kernel.theme.ThemeDisplay"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@ include file="init.jsp" %>
<%-- <jsp:include page="/WEB-INF/views/view.jsp"/>
 --%>
 
 <div class="container">
	<div class="row">
		<div class="col-sm-9">
			<c:choose>
				<c:when test="${!empty renderjsp}">
					<jsp:include page="${renderjsp}"></jsp:include>
				</c:when>
			</c:choose>
		</div>
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
	</div>
</div>
<%
LiferayPortletContext context = (LiferayPortletContext) portletConfig.getPortletContext();
long timestamp = context.getPortlet().getTimestamp();
%>
<script src="<%=request.getContextPath()%>/js/show_view.js?t=<%=timestamp%>"
	view-id="${formparam['viewId']}" ></script>