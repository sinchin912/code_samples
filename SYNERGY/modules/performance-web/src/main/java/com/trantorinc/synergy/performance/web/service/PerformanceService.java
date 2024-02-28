package com.trantorinc.synergy.performance.web.service;

import com.liferay.portal.kernel.exception.PortalException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.ResourceRequest;

public interface PerformanceService {
    void getFormDetails(RenderRequest request);
    void addKpiDetails(ActionRequest request, ActionResponse response);
    void addNewAccount(ActionRequest request);
    void deleteReview(ActionRequest request) throws PortalException;
    void deleteAccount(ActionRequest request) throws PortalException;
    void selfReviewSubmit(ActionRequest request);
    void updateKpiDetails(ActionRequest request, ActionResponse response);
    void reviewWorkflow(ActionRequest request, ActionResponse response);
    void submitKpiForm(ActionRequest request) throws PortalException;
    void generateReview(ActionRequest request) ;
    void raiseMeetingToManager(ActionRequest request) ;
    void acceptManagerRating(ActionRequest request);
    void rejectManagerRating(ActionRequest request);
    String getManagerName(ResourceRequest resourceRequest);
    String getReviewerName(ResourceRequest resourceRequest);
    String uploadAttachments(ResourceRequest resourceRequest);
    void deleteAttachments(ResourceRequest resourceRequest);
    void saveFormData(ResourceRequest resourceRequest);
    String downloadAttachments(ResourceRequest resourceRequest);
    String getManagerEmailForEditAccount(ResourceRequest resourceRequest);
    String getReviewerEmailForEditAccount(ResourceRequest resourceRequest);
    String getYearlyEntries(ResourceRequest resourceRequest);

}
