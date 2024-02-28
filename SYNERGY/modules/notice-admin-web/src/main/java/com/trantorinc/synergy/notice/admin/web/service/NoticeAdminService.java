package com.trantorinc.synergy.notice.admin.web.service;

import com.liferay.portal.kernel.exception.PortalException;

import javax.portlet.*;
import java.io.IOException;

public interface NoticeAdminService {
    void getAllFormData(RenderRequest request);
    void submitHrResign(ActionRequest request) throws PortalException;
    void submitManagerResign(ActionRequest request) throws PortalException;
    void submitItAssetsResign(ActionRequest request);
    void exitClearancePdfDownload(ResourceRequest resourceRequest,ResourceResponse resourceResponse) throws IOException;
    void submitHrWithdrawalActionForm(ActionRequest request);
    void submitFinalLwd(ActionRequest request) throws PortalException;
    void submitAbscondTerminateForm(ActionRequest request) throws PortalException;
    void reopenForm(ActionRequest request);
    void hrRemarksSubmissionUrl(ActionRequest request);
    void exitClearanceWorkflow(ActionRequest request , ActionResponse response);
    void exitQuestionnaireFormWorkflow(ActionRequest request , ActionResponse response);
    void submitClearance(ActionRequest request) throws IOException;
    void exitWorkflow(ActionRequest request , ActionResponse response);
    void assigneeSubmission(ActionRequest request);
    String getEmployeeSearchByEcode(ResourceRequest resourceRequest);
    void saveClearanceForm(ResourceRequest resourceRequest);
    String getUserManualObject(ResourceRequest request);
}
