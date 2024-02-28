package com.trantorinc.synergy.notice.admin.web.portlet;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.trantorinc.synergy.notice.admin.web.constants.NoticeAdminWebPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import com.trantorinc.synergy.notice.admin.web.service.NoticeAdminService;
import com.trantorinc.synergy.notice.admin.web.service.NoticeAdminServiceImpl;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import static com.trantorinc.synergy.common.service.util.UtilService.*;

/**
 * @author saurabh.kumar
 */
@Component(
        immediate = true,
        property = {
                "com.liferay.portlet.display-category=category.sample",
                "com.liferay.portlet.header-portlet-css=/css/main.css",
                "com.liferay.portlet.instanceable=true",
                "javax.portlet.display-name=NoticeAdminWeb",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/view.jsp",
                "javax.portlet.name=" + NoticeAdminWebPortletKeys.NOTICEADMINWEB,
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
)
public class NoticeAdminWebPortlet extends MVCPortlet {
    Log log = LogFactoryUtil.getLog(NoticeAdminWebPortlet.class);
    private NoticeAdminService noticeAdminService = null;

    @Override
    public void init() throws PortletException {
        noticeAdminService = new NoticeAdminServiceImpl();
        super.init();
    }

    @Override
    public void destroy() {
        noticeAdminService = null;
        super.destroy();
    }

    @Override
    public void doView(RenderRequest request, RenderResponse response) throws IOException, PortletException {
        noticeAdminService.getAllFormData(request);
        super.doView(request, response);
    }

    @ProcessAction(name = "submitHrResign")
    public void submitHrResign(ActionRequest request, ActionResponse response) throws IOException, PortalException {
        noticeAdminService.submitHrResign(request);
        redirect(request, response);
    }

    @ProcessAction(name = "submitManagerResign")
    public void submitManagerResign(ActionRequest request, ActionResponse response) throws IOException, PortalException {
        noticeAdminService.submitManagerResign(request);
        redirect(request, response);
    }

    @ProcessAction(name = "submitItAssetsResign")
    public void submitItAssetsResign(ActionRequest request, ActionResponse response) throws IOException {
        noticeAdminService.submitItAssetsResign(request);
        redirect(request, response);
    }

    @ProcessAction(name = "submitHrWithdrawalActionForm")
    public void submitHrWithdrawalActionForm(ActionRequest request, ActionResponse response) throws IOException {
        noticeAdminService.submitHrWithdrawalActionForm(request);
        redirect(request, response);
    }

    @ProcessAction(name = "submitFinalLwd")
    public void submitFinalLwd(ActionRequest request, ActionResponse response) throws IOException, PortalException {
        noticeAdminService.submitFinalLwd(request);
        redirect(request, response);
    }

    @ProcessAction(name = "submitAbscondTerminateForm")
    public void submitAbscondTerminateForm(ActionRequest request, ActionResponse response) throws IOException, ParseException, PortalException {
        noticeAdminService.submitAbscondTerminateForm(request);
        redirect(request, response);
    }

    @ProcessAction(name = "reopenForm")
    public void reopenForm(ActionRequest request, ActionResponse response) throws IOException {
        noticeAdminService.reopenForm(request);
        redirect(request, response);
    }

    @ProcessAction(name = "hrRemarksSubmissionUrl")
    public void hrRemarksSubmissionUrl(ActionRequest request, ActionResponse response) throws IOException {
        noticeAdminService.hrRemarksSubmissionUrl(request);
        redirect(request, response);
    }

    @ProcessAction(name = "exitClearanceWorkflow")
    public void exitClearanceWorkflow(ActionRequest request, ActionResponse response) {

        noticeAdminService.exitClearanceWorkflow(request,response);
    }

    @ProcessAction(name = "exitQuestionnaireFormWorkflow")
    public void exitQuestionnaireFormWorkflow(ActionRequest request, ActionResponse response) {
        noticeAdminService.exitQuestionnaireFormWorkflow(request,response);

    }

    @ProcessAction(name = "submitClearance")
    public void submitClearance(ActionRequest request, ActionResponse response) throws IOException {
        noticeAdminService.submitClearance(request);
        redirect(request, response);
    }

    @ProcessAction(name = "exitWorkflow")
    public void exitWorkflow(ActionRequest request, ActionResponse response) {
        noticeAdminService.exitWorkflow(request,response);
    }

    @ProcessAction(name = "assigneeSubmission")
    public void assigneeSubmission(ActionRequest request, ActionResponse response) throws IOException {
        noticeAdminService.assigneeSubmission(request);
        redirect(request, response);
    }

    @Override
    public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws IOException, PortletException {
        Object dataToWrite = null;
        if (resourceRequest.getResourceID().equals("employeeEcodeSearch")) {
            dataToWrite = noticeAdminService.getEmployeeSearchByEcode(resourceRequest);
        }
        else if(resourceRequest.getResourceID().equals("saveClearanceForm")) {
            noticeAdminService.saveClearanceForm(resourceRequest);
        }else if (resourceRequest.getResourceID().equals("downloadUserManual")) {
            dataToWrite = noticeAdminService.getUserManualObject(resourceRequest);
        }
        else if (resourceRequest.getResourceID().equals("downloadPdf")) {
           noticeAdminService.exitClearancePdfDownload(resourceRequest,resourceResponse);
        }
        else if (resourceRequest.getResourceID().equals("downloadExcel")) {
            String resignationFormId = ParamUtil.getString(resourceRequest, "year");
            log.info(resignationFormId);
        }
        try (PrintWriter writer = resourceResponse.getWriter()) {
            writer.print("{\"data\": " + dataToWrite + "}");
            writer.flush();
        } catch (IOException exception) {
			log.error(exception.getStackTrace()[0].getMethodName() + ":" + exception.getStackTrace()[0].getLineNumber() + ":" + exception.getMessage());
        }
    }
}