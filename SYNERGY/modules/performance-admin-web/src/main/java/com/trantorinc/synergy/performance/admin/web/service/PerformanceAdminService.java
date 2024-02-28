package com.trantorinc.synergy.performance.admin.web.service;

import javax.portlet.*;
import java.io.IOException;

public interface PerformanceAdminService {
    void getFormDetails(RenderRequest request);
    void getKpiDetails(ActionRequest request, ActionResponse response);
    void reviewFormWorkflow(ActionRequest request, ActionResponse response);
    void rejectReviewLines(ActionRequest request);
    void submitManagerReview(ActionRequest request);
    void submitHrReview(ActionRequest request);
    void submitRollbackRequest(ActionRequest request);
    void submitCloseRequest(ActionRequest request);
    void submitActionOnRollbackByHR(ActionRequest request);
    void submitActionOnCloseByHR(ActionRequest request);
    void updateFinalYearReviewTimeline(ActionRequest request);
    void rollbackReviewFromForm(ActionRequest request);
    void assigneeReview(ActionRequest request);
    void updateMidYearYearReviewTimeline(ActionRequest request);
    void rejectKpiForm(ActionRequest request, ActionResponse response);
    void downloadReviewReport(ResourceResponse resourceResponse,int year) throws IOException;
    void downloadKpiReport(ResourceResponse resourceResponse) throws IOException;
    String getReviewerName(ResourceRequest resourceRequest);
    String getRollbackRequestStatusEntries(ResourceRequest resourceRequest);
    void setReviewFormValue(ResourceRequest resourceRequest);
    String getCloseRequestStatusEntries(ResourceRequest resourceRequest);
    String getEmployeeEntriesByHr(ResourceRequest resourceRequest);
    String getManagerYearlyEntries(ResourceRequest resourceRequest);
    String getHRYearlyEntriesURL(ResourceRequest resourceRequest);
    void allowSelfReviewByHr(ActionRequest request);
    void closeReviewFormByHr(ActionRequest request);
    String downloadAttachments(ResourceRequest resourceRequest);
    String getUserManualObject(ResourceRequest request);
    void saveHrForm(ResourceRequest request);


}
