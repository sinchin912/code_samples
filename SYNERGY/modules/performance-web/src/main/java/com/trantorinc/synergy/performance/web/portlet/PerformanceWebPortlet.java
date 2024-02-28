package com.trantorinc.synergy.performance.web.portlet;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.trantorinc.synergy.performance.web.constants.PerformanceWebPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import com.trantorinc.synergy.performance.web.service.PerformanceService;
import com.trantorinc.synergy.performance.web.service.PerformanceServiceImpl;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;


import static com.trantorinc.synergy.common.service.constant.AppConstantService.*;
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
                "javax.portlet.display-name=PerformanceWeb",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/view.jsp",
                "javax.portlet.name=" + PerformanceWebPortletKeys.PERFORMANCEWEB,
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
)
public class PerformanceWebPortlet extends MVCPortlet {
    private final Log log = LogFactoryUtil.getLog(PerformanceWebPortlet.class.getName());

    private PerformanceService performanceService = null;

    @Override
    public void init() throws PortletException {
        performanceService = new PerformanceServiceImpl();
        super.init();
    }

    @Override
    public void destroy() {
        performanceService = null;
        super.destroy();
    }

    @Override
    public void doView(RenderRequest request, RenderResponse response) throws IOException, PortletException {
        performanceService.getFormDetails(request);
        super.doView(request, response);
    }

    @ProcessAction(name = "addKpiDetails")
    public void addKpiDetails(ActionRequest request, ActionResponse response) {
        performanceService.addKpiDetails(request, response);

    }

    @ProcessAction(name = "submitKpiForm")
    public void submitKpiForm(ActionRequest request, ActionResponse response) throws PortalException, IOException {
        performanceService.submitKpiForm(request);
        redirect(request, response);
    }

    @ProcessAction(name = "updateKpiDetails")
    public void updateKpiDetails(ActionRequest request, ActionResponse response) {
        log.info("updateKpiDetails");
        performanceService.updateKpiDetails(request, response);
    }

    @ProcessAction(name = "addNewAccount")
    public void addNewAccount(ActionRequest request, ActionResponse response) throws IOException {
        performanceService.addNewAccount(request);
        redirect(request, response);
    }

    @ProcessAction(name = "generateReview")
    public void generateReview(ActionRequest request, ActionResponse response) throws IOException {
        performanceService.generateReview(request);
        redirect(request, response);
    }

    @ProcessAction(name = "reviewWorkflow")
    public void reviewWorkflow(ActionRequest request, ActionResponse response) {
        performanceService.reviewWorkflow(request, response);
    }

    @ProcessAction(name = "raiseMeetingToManager")
    public void raiseMeetingToManager(ActionRequest request, ActionResponse response) throws IOException {
        performanceService.raiseMeetingToManager(request);
		redirect(request, response);
    }

    @ProcessAction(name = "acceptManagerRating")
    public void acceptManagerRating(ActionRequest request, ActionResponse response) throws IOException {
        performanceService.acceptManagerRating(request);
		redirect(request, response);
    }

    @ProcessAction(name = "rejectManagerRating")
    public void rejectManagerRating(ActionRequest request, ActionResponse response) throws IOException {
        performanceService.rejectManagerRating(request);
		redirect(request, response);
    }

    @ProcessAction(name = "selfReviewSubmit")
    public void selfReviewSubmit(ActionRequest request, ActionResponse response) throws IOException {
        performanceService.selfReviewSubmit(request);
		redirect(request, response);
    }

    @ProcessAction(name = "deleteReview")
    public void deleteReview(ActionRequest request, ActionResponse response) throws IOException, PortalException {
        performanceService.deleteReview(request);
		redirect(request, response);
    }

    @ProcessAction(name = "deleteKpiAccount")
    public void deleteAccount(ActionRequest request, ActionResponse response) throws IOException, PortalException {
        long kpiId= ParamUtil.getLong(request, "deleteId");
        log.info("kpiId"+kpiId);
        performanceService.deleteAccount(request);
		redirect(request, response);
    }

    @Override
    public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws IOException, PortletException {
        Object dataToWrite = null;
        log.info(resourceRequest.getResourceID());
        if (resourceRequest.getResourceID().equals("getManagerName")) {
            dataToWrite = performanceService.getManagerName(resourceRequest);
        } else if (resourceRequest.getResourceID().equals("getReviewerName")) {
            dataToWrite = performanceService.getReviewerName(resourceRequest);
        } else if (resourceRequest.getResourceID().equals("uploadAttachments")) {
            log.info("uploadAttachments");
            dataToWrite = performanceService.uploadAttachments(resourceRequest);
        } else if (resourceRequest.getResourceID().equals("saveFormData")) {
            performanceService.saveFormData(resourceRequest);
        } else if (resourceRequest.getResourceID().equals("deleteAttachments")) {
            performanceService.deleteAttachments(resourceRequest);
        } else if (resourceRequest.getResourceID().equals("downloadAttachments")) {
            dataToWrite = performanceService.downloadAttachments(resourceRequest);
        } else if (resourceRequest.getResourceID().equals("getYearlyEntries")) {
            dataToWrite = performanceService.getYearlyEntries(resourceRequest);

        }else if (resourceRequest.getResourceID().equals("downloadUserManual")) {
            dataToWrite ="\"" + new String(getUserManual(MODULE_PERFORMANCE, ROLE_EMPLOYEE).getBytes(), StandardCharsets.UTF_8)+ "\"";
        } else if(resourceRequest.getResourceID().equals("getManagerEmailForEditAccount")) {
            dataToWrite = performanceService.getManagerEmailForEditAccount(resourceRequest);
        }else if(resourceRequest.getResourceID().equals("getReviewerEmailForEditAccount")) {
            dataToWrite = performanceService.getReviewerEmailForEditAccount(resourceRequest);
        }
        try (PrintWriter writer = resourceResponse.getWriter()) {
            writer.print("{\"data\": " + dataToWrite + "}");
            writer.flush();
        } catch (IOException exception) {
            log.error(exception.getStackTrace()[0].getMethodName() + ":" + exception.getStackTrace()[0].getLineNumber() + ":" + exception.getMessage());
        }

    }
}