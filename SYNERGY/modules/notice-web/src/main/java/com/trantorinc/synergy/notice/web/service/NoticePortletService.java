package com.trantorinc.synergy.notice.web.service;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

public interface NoticePortletService {
    void findAllDataList(RenderRequest request, RenderResponse response);
    void submitResignForm(ActionRequest request);
    void submitQuestionnaireForm(ActionRequest request);
    void exitQuestionnaireFormWorkflow(ActionRequest request, ActionResponse response);
    void exitWorkflowFormWorkflow(ActionRequest request, ActionResponse response);
}
