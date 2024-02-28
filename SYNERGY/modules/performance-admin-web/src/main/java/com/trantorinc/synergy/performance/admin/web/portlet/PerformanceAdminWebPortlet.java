package com.trantorinc.synergy.performance.admin.web.portlet;


import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;

import com.trantorinc.synergy.performance.admin.web.constants.PerformanceAdminWebPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import com.trantorinc.synergy.performance.admin.web.service.PerformanceAdminService;
import com.trantorinc.synergy.performance.admin.web.service.PerformanceAdminServiceImpl;

import org.osgi.service.component.annotations.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;


import static com.trantorinc.synergy.common.service.constant.AppConstantService.FORMATTER_YYYY_MM_DD;
import static com.trantorinc.synergy.common.service.util.UtilService.getCurrentYear;
import static com.trantorinc.synergy.common.service.util.UtilService.redirect;

/**
 * @author saurabh.kumar
 */
@Component(
		immediate = true,
		property = {
				"com.liferay.portlet.display-category=category.sample",
				"com.liferay.portlet.header-portlet-css=/css/main.css",
				"com.liferay.portlet.instanceable=true",
				"javax.portlet.display-name=PerformanceAdminWeb",
				"javax.portlet.init-param.template-path=/",
				"javax.portlet.init-param.view-template=/view.jsp",
				"javax.portlet.name=" + PerformanceAdminWebPortletKeys.PERFORMANCEADMINWEB,
				"javax.portlet.resource-bundle=content.Language",
				"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
)
public class PerformanceAdminWebPortlet extends MVCPortlet {
	private PerformanceAdminService performanceService = null;
	private final Log log = LogFactoryUtil.getLog(PerformanceAdminWebPortlet.class);

	@Override
	public void init() throws PortletException {
		performanceService = new PerformanceAdminServiceImpl();
		super.init();
	}

	@Override
	public void destroy() {
		performanceService = null;
		super.destroy();
	}
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
		performanceService.getFormDetails(renderRequest);
		super.doView(renderRequest, renderResponse);
	}
	@ProcessAction(name = "kpiWorkflow")
	public void kpiWorkflow(ActionRequest request, ActionResponse response) {
		performanceService.getKpiDetails(request,response);
	}
	@ProcessAction(name="rejectKpiForm")
	public void rejectKpiForm(ActionRequest request, ActionResponse response) {
		performanceService.rejectKpiForm(request,response);

	}
	@ProcessAction(name="reviewWorkflow")
	public void reviewWorkflow(ActionRequest request, ActionResponse response) {
		performanceService.reviewFormWorkflow(request,response);

	}
	@ProcessAction(name="rejectReviewLines")
	public void rejectReviewLines(ActionRequest request, ActionResponse response) throws IOException {
		performanceService.rejectReviewLines(request);
		redirect(request,response);

	}
	@ProcessAction(name="submitManagerReview")
	public void submitManagerReview(ActionRequest request, ActionResponse response) throws IOException {
		performanceService.submitManagerReview(request);
		redirect(request,response);
	}
	@ProcessAction(name="submitHrReview")
	public void submitHrReview(ActionRequest request, ActionResponse response) throws IOException {
		performanceService.submitHrReview(request);
		redirect(request,response);
	}
	@ProcessAction(name="submitRollbackRequest")
	public void submitRollbackRequest(ActionRequest request, ActionResponse response) throws IOException {
		performanceService.submitRollbackRequest(request);
		redirect(request,response);
	}
	@ProcessAction(name="submitCloseRequest")
	public void submitCloseRequest(ActionRequest request, ActionResponse response) throws IOException {
		performanceService.submitCloseRequest(request);
		redirect(request,response);
	}
	@ProcessAction(name="allowSelfReview")
	public void allowSelfReview(ActionRequest request, ActionResponse response) throws IOException {
		performanceService.allowSelfReviewByHr(request);
		redirect(request,response);
	}
	@ProcessAction(name="closeReviewForm")
	public void closeReviewForm(ActionRequest request, ActionResponse response) throws IOException {
		performanceService.closeReviewFormByHr(request);
		redirect(request,response);
	}
	@ProcessAction(name="submitActionOnRollbackByHR")
	public void submitActionOnRollbackByHR(ActionRequest request, ActionResponse response) throws IOException {
		performanceService.submitActionOnRollbackByHR(request);
		redirect(request,response);
	}
	@ProcessAction(name="submitActionOnCloseByHR")
	public void submitActionOnCloseByHR(ActionRequest request, ActionResponse response) throws IOException {
		performanceService.submitActionOnCloseByHR(request);
		redirect(request,response);
	}
	@ProcessAction(name="updateFinalReviewTimeline")
	public void updateFinalReviewTimeline(ActionRequest request, ActionResponse response) throws IOException {
		performanceService.updateFinalYearReviewTimeline(request);
		redirect(request,response);
	}
	@ProcessAction(name="updateMidReviewTimeline")
	public void updateMidReviewTimeline(ActionRequest request, ActionResponse response) throws IOException {
		performanceService.updateMidYearYearReviewTimeline(request);
		redirect(request,response);
	}
	@ProcessAction(name="rollbackReview")
	public void rollbackReview(ActionRequest request, ActionResponse response) throws IOException {
		performanceService.rollbackReviewFromForm(request);
		redirect(request,response);
	}
	@ProcessAction(name="assigneeReview")
	public void assigneeReview(ActionRequest request, ActionResponse response) throws IOException {
		performanceService.assigneeReview(request);
		redirect(request,response);
	}
	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws IOException, PortletException {
		Object dataToWrite = null;
		if(resourceRequest.getResourceID().equals("getReviewerName")) {
			dataToWrite = performanceService.getReviewerName(resourceRequest);
		} else if(resourceRequest.getResourceID().equals("setManagerForm"))	{
			performanceService.setReviewFormValue(resourceRequest);
		} else if(resourceRequest.getResourceID().equals("getRollbackRequestStatusEntries")) {
			dataToWrite = performanceService.getRollbackRequestStatusEntries(resourceRequest);
		} else if(resourceRequest.getResourceID().equals("getCloseRequestStatusEntries")) {
			dataToWrite = performanceService.getCloseRequestStatusEntries(resourceRequest);
		} else if(resourceRequest.getResourceID().equals("getEmployeeEntriesByHr")) {
			dataToWrite = performanceService.getEmployeeEntriesByHr(resourceRequest);
		} else if(resourceRequest.getResourceID().equals("getManagerYearlyEntries")) {
			dataToWrite = performanceService.getManagerYearlyEntries(resourceRequest);
		} else if(resourceRequest.getResourceID().equals("getHRYearlyEntriesURL")) {
			dataToWrite = performanceService.getHRYearlyEntriesURL(resourceRequest);
		} else if(resourceRequest.getResourceID().equals("downloadReviewReport")) {
			performanceService.downloadReviewReport(resourceResponse,getCurrentYear());
		} else if(resourceRequest.getResourceID().equals("downloadHrYearWiseReport")) {
			String year = ParamUtil.getString(resourceRequest,"year");
			performanceService.downloadReviewReport(resourceResponse,LocalDate.parse(year, FORMATTER_YYYY_MM_DD).atStartOfDay().getYear());
		} else if(resourceRequest.getResourceID().equals("downloadKpiReport")){
			performanceService.downloadKpiReport(resourceResponse);
		}	else if(resourceRequest.getResourceID().equals("saveFormData")){
			performanceService.setReviewFormValue(resourceRequest);
		} else if(resourceRequest.getResourceID().equals("saveHrFormData")){
			performanceService.saveHrForm(resourceRequest);
		} else if (resourceRequest.getResourceID().equals("downloadAttachments")) {
			dataToWrite = performanceService.downloadAttachments(resourceRequest);
		} else if (resourceRequest.getResourceID().equals("downloadUserManual")) {
			dataToWrite = performanceService.getUserManualObject(resourceRequest);
		}
		try (PrintWriter writer = resourceResponse.getWriter()) {
			writer.print("{\"data\": " + dataToWrite+ "}");
			writer.flush();
		} catch (IOException exception) {
			log.error(exception.getStackTrace()[0].getMethodName() + ":" + exception.getStackTrace()[0].getLineNumber() + ":" + exception.getMessage());
		}

	}
}