package com.trantorinc.synergy.notice.web.portlet;

import com.trantorinc.synergy.common.service.util.UtilService;
import com.trantorinc.synergy.notice.web.constants.NoticeWebPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import com.trantorinc.synergy.notice.web.service.NoticePortletService;
import com.trantorinc.synergy.notice.web.service.NoticePortletServiceImpl;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;

/**
 * @author saurabh.kumar
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=NoticeWeb",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + NoticeWebPortletKeys.NOTICEWEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class NoticeWebPortlet extends MVCPortlet {
	private NoticePortletService noticePortletService = null;

	@Override
	public void init() throws PortletException {
		noticePortletService = new NoticePortletServiceImpl();
		super.init();
	}

	@Override
	public void destroy() {
		noticePortletService = null;
		super.destroy();
	}
	@Override
	public void doView(RenderRequest request, RenderResponse response) throws IOException, PortletException {
		noticePortletService.findAllDataList(request,response);
		super.doView(request, response);
	}

	@ProcessAction(name = "submitResign")
	public void submitResign(ActionRequest request, ActionResponse response) throws IOException {
		noticePortletService.submitResignForm(request);
		UtilService.redirect(request,response);
	}

	@ProcessAction(name = "exitQuestionnaireFormWorkflow")
	public void exitQuestionnaireFormWorkflow(ActionRequest request, ActionResponse response) {
		noticePortletService.exitQuestionnaireFormWorkflow(request, response);
	}

	@ProcessAction(name = "submitQuestionnaireForm")
	public void submitQuestionnaireForm(ActionRequest request, ActionResponse response) throws IOException {
		noticePortletService.submitQuestionnaireForm(request);
		UtilService.redirect(request,response);
	}

	@ProcessAction(name = "exitWorkflow")
	public void exitWorkflowFormWorkflow(ActionRequest request, ActionResponse response) {
		noticePortletService.exitWorkflowFormWorkflow(request, response);
	}

}