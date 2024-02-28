package com.trantorinc.synergy.notice.admin.web.service;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.trantorinc.synergy.common.service.drive.DriveService;
import com.trantorinc.synergy.common.service.pdf.PdfService;
import com.trantorinc.synergy.email.core.model.Email;
import com.trantorinc.synergy.email.core.model.EmailAttachment;
import com.trantorinc.synergy.email.core.service.EmailAttachmentLocalServiceUtil;
import com.trantorinc.synergy.email.core.service.EmailLocalServiceUtil;
import com.trantorinc.synergy.lms.core.model.Employee;
import com.trantorinc.synergy.lms.core.model.Holiday;
import com.trantorinc.synergy.lms.core.service.DriveLocalServiceUtil;
import com.trantorinc.synergy.lms.core.service.EmployeeLocalServiceUtil;
import com.trantorinc.synergy.lms.core.service.HolidayLocalServiceUtil;
import com.trantorinc.synergy.notice.admin.web.dto.*;
import com.trantorinc.synergy.notice.core.model.*;
import com.trantorinc.synergy.notice.core.service.*;

import javax.portlet.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.trantorinc.synergy.common.service.constant.AppConstantService.*;
import static com.trantorinc.synergy.common.service.constant.AppConstantService.ROW_END;
import static com.trantorinc.synergy.common.service.util.UtilService.*;
import static com.trantorinc.synergy.notice.admin.web.constants.NoticeAdminWebPortletKeys.*;

public class NoticeAdminServiceImpl implements NoticeAdminService {
    private final Log log = LogFactoryUtil.getLog(NoticeAdminServiceImpl.class);

    @Override
    public void getAllFormData(RenderRequest request) {
        List<ExitState> statusMap = ExitStateLocalServiceUtil.getExitStates(-1, -1);
        List<Resignation> allResignEntries = ResignationLocalServiceUtil.getResignations(-1, -1);
        List<QuestionnaireForm> allQuestionnaireFormEntries = QuestionnaireFormLocalServiceUtil.findAllQuestionnaireFormEntries();
        List<Employee> employeeDataList = EmployeeLocalServiceUtil.findAllActiveEmployees();
        List<ExitForm> exitFormList = ExitFormLocalServiceUtil.getExitForms(-1, -1);
        List<ManagerForm> managerDataList = ManagerFormLocalServiceUtil.getManagerForms(-1, -1);
        List<EmployeeDto> entryList = new ArrayList<>();
        for (Resignation entry : allResignEntries) {
            List<Employee> employeeData = employeeDataList.stream().filter(s -> s.getEcode().equalsIgnoreCase(entry.getEcode())).collect(Collectors.toList());
            List<QuestionnaireForm> form = allQuestionnaireFormEntries.stream().filter(s -> s.getResignationId() == entry.getId()).collect(Collectors.toList());
            List<ExitForm> exitForm = exitFormList.stream().filter(s -> s.getResignationId() == entry.getId()).collect(Collectors.toList());
            EmployeeDto dto = new EmployeeDto();
            if (!form.isEmpty() && null != form.get(0).getSubmittedDate()) {
                dto.setQuestionnaireFormSubmitted(entry.getStatus() != 6);
            }
            if (!employeeData.isEmpty()) {
                Employee employeeInfo = employeeData.get(0);
                dto.setEmployeeName(employeeInfo.getName());
                dto.setDoj(convertDateToLocalDateTime(employeeInfo.getDoj()).format(FORMATTER_YYYY_MM_DD));
                dto.setEmployeeDesignation(employeeInfo.getDesignation());
                dto.setEmpMobNo(employeeInfo.getMobile());
                dto.setLocation(employeeInfo.getLocation());
            }
            dto.setEmployeeCode(entry.getEcode());
            dto.setAccount(entry.getAccount());
            List<Employee> managerDetail = employeeDataList.stream().filter(s -> s.getEmail().equalsIgnoreCase(entry.getManagerEmail())).collect(Collectors.toList());
            dto.setManagerName(managerDetail.isEmpty() ? BLANK : managerDetail.get(0).getName());
            dto.setManagerEmail(entry.getManagerEmail());
            dto.setAssigneeEmail(entry.getAssigneeEmail());
            dto.setAssigneeTo(entry.getAssigneeTo());
            dto.setResignationId(entry.getId());
            dto.setResignationDate(convertDateToLocalDateTime(entry.getCreationDate()).format(FORMATTER_YYYY_MM_DD));
            dto.setStatus(statusMap.get(entry.getStatus()).getExitStateDisplay());
            if (null != entry.getLastWorkingDate()) {
                dto.setLastWorkingDate(convertDateToLocalDateTime(entry.getLastWorkingDate()).format(FORMATTER_YYYY_MM_DD));
            }
            if (!exitForm.isEmpty() && entry.getStatus() != 6) {
                List<ManagerForm> mangerForm = managerDataList.stream().filter(s -> s.getExitId() == exitForm.get(0).getId()).collect(Collectors.toList());
                dto.setShowQuesFormManager(((entry.getStatus() == 2 || entry.getStatus() == 3) && (!entry.getExitQuestionnaire() || (!form.isEmpty() && null != form.get(0).getSubmittedDate()))) || entry.getStatus() == 4 || entry.getStatus() == 5);
                dto.setClearanceFormGenerated(entry.getStatus() != 6);
                dto.setShowPdfDownloadButton(true);
                if (!mangerForm.isEmpty() && null != mangerForm.get(0).getUpdatedDate() && entry.getStatus() != 6) {
                    dto.setManagerFormSubmitted(true);
                }
                if (exitForm.get(0).getHrFormStatus() && entry.getStatus() != 6) {
                    dto.setStatus(CLEARANCES_COMPLETED);
                    dto.setClearanceCompleted(true);
                }
                if (!exitForm.get(0).getManagerFormStatus() || !exitForm.get(0).getAdminFormStatus() || !exitForm.get(0).getItFormStatus() || !exitForm.get(0).getFinanceFormStatus() || !exitForm.get(0).getHrFormStatus()) {
                    if (!exitForm.get(0).getManagerFormStatus() && !exitForm.get(0).getAdminFormStatus() && !exitForm.get(0).getItFormStatus() && !exitForm.get(0).getFinanceFormStatus() && !exitForm.get(0).getHrFormStatus()) {
                        dto.setEmpStatus(ALL + CLEARANCE + PENDING);
                    }  else {
                        StringBuilder status = new StringBuilder();
                        if (!exitForm.get(0).getManagerFormStatus()) {
                            status.append(MANAGER).append(COMMA);
                        }
                        if (!exitForm.get(0).getAdminFormStatus()) {
                            status.append(ADMIN).append(COMMA);
                        }
                        if (!exitForm.get(0).getItFormStatus()) {
                            status.append(IT).append(COMMA);
                        }
                        if (!exitForm.get(0).getFinanceFormStatus()) {
                            status.append(FINANCE).append(COMMA);
                        }
                        if (!exitForm.get(0).getHrFormStatus()) {
                            status.append(HR).append(COMMA);
                        }
                        dto.setEmpStatus(status.deleteCharAt(status.length() - 1).append(CLEARANCE).append(PENDING).toString());
                    }
                } else if(exitForm.get(0).getManagerFormStatus() && exitForm.get(0).getAdminFormStatus() && exitForm.get(0).getItFormStatus() && exitForm.get(0).getFinanceFormStatus()&& exitForm.get(0).getHrFormStatus()){
                    dto.setEmpStatus(ALL + CLEARANCE + COMPLETED);
                }
            } else {
                if (entry.getStatus() == 0) {
                    dto.setEmpStatus(HR + APPROVAL + PENDING);
                } else if (entry.getStatus() == 1) {
                    dto.setEmpStatus(MANAGER + APPROVAL + PENDING);
                } else if (entry.getStatus() == 6) {
                    dto.setEmpStatus(RETAINED);
                } else {
                    dto.setEmpStatus(CLEARANCE_TO_BE_INITIATED);
                }
            }
            dto.setReason(entry.getReason());
            dto.setStatusInt(entry.getStatus());
            dto.setEmployeeComment(entry.getEmployeeComment());
            dto.setManagerComment(entry.getManagerComment());
            dto.setHrComment(entry.getHrComment());
            entryList.add(dto);
        }
        LocalDate now = LocalDate.now();
        List<Integer> uniqueResignDates = ResignationLocalServiceUtil.findUniqueResignationYears();
        List<Holiday> holidayList = HolidayLocalServiceUtil.findHolidaysByYear(now.getYear());
        List<String> holidayDate = holidayList.stream().map(Holiday::getOnDate).map(d -> convertDateToLocalDateTime(d).format(FORMATTER_YYYY_MM_DD)).collect(Collectors.toList());
        request.setAttribute("holidayDates", convertToJson(holidayDate));
        request.setAttribute("statusMap", statusMap);
        request.setAttribute("statusMapJSON", convertToJson(statusMap));

        request.setAttribute("years", uniqueResignDates);
        request.setAttribute("yearJson", convertToJson(uniqueResignDates));
        Set<String> userRoles = getLoggedUserRoles(request);
        if (userRoles.contains(ROLE_MANAGER) || userRoles.contains(ROLE_COORDINATOR) || userRoles.contains(ROLE_TEAMLEAD)) {
            List<EmployeeDto> resignEntryListForManager = new ArrayList<>();
            if (userRoles.contains(ROLE_MANAGER)) {
                resignEntryListForManager.addAll(entryList.stream().filter(s -> s.getManagerEmail().equalsIgnoreCase(getLoggedUser(request))).collect(Collectors.toList()));
            }
            if (userRoles.contains(ROLE_COORDINATOR) || userRoles.contains(ROLE_MANAGER) || userRoles.contains(ROLE_TEAMLEAD)) {
                resignEntryListForManager.addAll(entryList.stream().filter(s -> s.getAssigneeEmail().equalsIgnoreCase(getLoggedUser(request))).filter(EmployeeDto::getAssigneeTo).collect(Collectors.toList()));
            }
            request.setAttribute("employeeResignListAssignedToManager", resignEntryListForManager);
        }
        if (userRoles.contains(ROLE_IT) || userRoles.contains(ROLE_ADMIN) || userRoles.contains(ROLE_FINANCE) || userRoles.contains(ROLE_HR)) {
            request.setAttribute("employeeResignList", entryList);
        }
    }

    @Override
    public void submitHrResign(ActionRequest request) throws PortalException {
        long resignationId = ParamUtil.getLong(request, "hrResignationId");
        boolean sendReplacementMail = false;
        Date now = getIstDate();
        Resignation empResignationDetails = ResignationLocalServiceUtil.fetchResignation(resignationId);
        Employee employeeData = EmployeeLocalServiceUtil.fetchEmployee(empResignationDetails.getEcode());
        Employee managerData = EmployeeLocalServiceUtil.findByEmail(empResignationDetails.getManagerEmail());
        List<ExitState> statusMap = ExitStateLocalServiceUtil.getExitStates(-1, -1);
        Date finalLwd = null;
        if (null == empResignationDetails.getAbscondTerminateDate()) {
            empResignationDetails.setStatus(statusMap.get(1).getExitStateKey());
            empResignationDetails.setSeparationType(ParamUtil.getString(request, "separationType"));
            empResignationDetails.setKeyToCompany(ParamUtil.getString(request, "keyToCompany"));
            empResignationDetails.setNoticePeriod(ParamUtil.getString(request, "noticePeriod"));
            empResignationDetails.setReasonForLeavingByHr(ParamUtil.getString(request, "reasonForLeavingByHr"));
            empResignationDetails.setExitQuestionnaire(ParamUtil.getBoolean(request, "exitQuestionnaire"));
            finalLwd = convertLocalDateTimeToDate(LocalDate.parse(ParamUtil.getString(request, "finalLwd"), FORMATTER_YYYY_MM_DD).atStartOfDay());
            empResignationDetails.setLastWorkingDate(finalLwd);
        } else {
            String hrFinalLwd = ParamUtil.getString(request, "hrFinalLwd");
            empResignationDetails.setLastWorkingDate(convertLocalDateTimeToDate(LocalDate.parse(hrFinalLwd, FORMATTER_YYYY_MM_DD).atStartOfDay()));
            sendReplacementMail = true;
        }

        empResignationDetails.setHrComment(!ParamUtil.getString(request, "hrComment").isEmpty() ? ParamUtil.getString(request, "hrComment") : BLANK);
        empResignationDetails.setHrSubmissionDate(now);
        ResignationLocalServiceUtil.updateResignation(empResignationDetails);

        if (empResignationDetails.getStatus() == 4) {
            generateClearances(resignationId, empResignationDetails, managerData, employeeData);
            Email managerEmail = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
            managerEmail.setToAddress(managerData.getEmail());
            managerEmail.setCcAddress(DL_HR);
            managerEmail.setSubject(MessageFormat.format(MANAGER_CLEARANCE_EMAIL_SUBJECT, employeeData.getEcode(), employeeData.getName()));
            managerEmail.setBody(MessageFormat.format(MANAGER_CLEARANCE_EMAIL_BODY, managerData.getName(), employeeData.getName(), employeeData.getEcode(), getPortalUrl() + URL_EXIT_ADMIN));
            managerEmail.setCreatedDate(now);
            managerEmail.setModule(MODULE_EXIT);
            managerEmail.setScheduled(false);
            managerEmail.setSent(false);
            EmailLocalServiceUtil.addEmail(managerEmail);

        } else if (empResignationDetails.getStatus() == 1) {
            Email emailer = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
            emailer.setToAddress(empResignationDetails.getAssigneeTo() ? managerData.getEmail() + COMMA + empResignationDetails.getAssigneeEmail() : managerData.getEmail());
            emailer.setCcAddress(DL_HR);
            emailer.setSubject(MessageFormat.format(HR_PUTS_NOTICE_PERIOD_EMAIL_SUBJECT, employeeData.getEcode(), employeeData.getName()));
            emailer.setBody(MessageFormat.format(HR_PUTS_NOTICE_PERIOD_EMAIL_BODY, managerData.getName(), employeeData.getEcode(), employeeData.getAccount(), convertDateToLocalDateTime(empResignationDetails.getCreationDate()).format(FORMATTER_YYYY_MM_DD), empResignationDetails.getReason(), empResignationDetails.getNoticePeriod().equalsIgnoreCase(ZERO) ? String.valueOf(empResignationDetails.getCreationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()) : String.valueOf(empResignationDetails.getCreationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusDays(Long.parseLong(empResignationDetails.getNoticePeriod()) - 1)), getPortalUrl() + URL_EXIT_ADMIN));
            emailer.setCreatedDate(now);
            emailer.setModule(MODULE_EXIT);
            emailer.setScheduled(false);
            emailer.setSent(false);
            EmailLocalServiceUtil.addEmail(emailer);
        }
        if (sendReplacementMail) {
            Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
            email.setToAddress(DL_IT);
            email.setCcAddress(DL_HR);
            email.setSubject(MessageFormat.format(IT_ASSESTS_SUBMISSION_EMAIL_SUBJECT, empResignationDetails.getEcode(), employeeData.getName()));
            email.setBody(MessageFormat.format(IT_ASSESTS_SUBMISSION_EMAIL_BODY, employeeData.getName(), employeeData.getEcode(), getPortalUrl() + URL_EXIT_ADMIN));
            email.setCreatedDate(now);
            email.setModule(MODULE_EXIT);
            email.setScheduled(false);
            email.setSent(false);
            EmailLocalServiceUtil.addEmail(email);

            Email emailer = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
            emailer.setToAddress(DL_AMINDIA);
            emailer.setCcAddress("nidhi.ayri@trantorinc.com");
            emailer.setSubject(EMPLOYEE_REPLACEMENT_EMAIL_SUBJECT);
            emailer.setBody(MessageFormat.format(EMPLOYEE_REPLACEMENT_EMAIL_BODY, empResignationDetails.getEcode(), employeeData.getName(), employeeData.getAccount(), managerData.getName(), empResignationDetails.getLastWorkingDate(), empResignationDetails.getReplacementPlan()));
            emailer.setCreatedDate(now);
            emailer.setModule(MODULE_EXIT);
            emailer.setScheduled(false);
            emailer.setSent(false);
            EmailLocalServiceUtil.addEmail(emailer);
        }
    }

    @Override
    public void submitManagerResign(ActionRequest request) {
        LocalDate localDate = LocalDate.now();
        long resignationId = ParamUtil.getLong(request, "mResignationId");
        Date now = getIstDate();
        Resignation empResignationDetails = ResignationLocalServiceUtil.fetchResignation(resignationId);
        Employee managerData = EmployeeLocalServiceUtil.findByEmail(empResignationDetails.getManagerEmail());
        Employee employeeData = EmployeeLocalServiceUtil.fetchEmployee(empResignationDetails.getEcode());
        Date mFinalLwd = null;
        List<ExitState> statusMap = ExitStateLocalServiceUtil.getExitStates(-1, -1);
        if (null == empResignationDetails.getAbscondTerminateDate()) {
            empResignationDetails.setStatus(statusMap.get(2).getExitStateKey());
            mFinalLwd = convertLocalDateTimeToDate(LocalDate.parse(ParamUtil.getString(request, "mFinalLwd"), FORMATTER_YYYY_MM_DD).atStartOfDay());
            empResignationDetails.setLastWorkingDate(mFinalLwd);
        }
        String replacementPlan = ParamUtil.getString(request, REPLACEMENT_PLAN);
        empResignationDetails.setReplacementPlan(replacementPlan);
        empResignationDetails.setKeyToProject(ParamUtil.getString(request, KEY_TO_PROJECT));
        empResignationDetails.setClientImpact(ParamUtil.getString(request, CLIENT_IMPACT));
        empResignationDetails.setRating(ParamUtil.getString(request, RATING));
        if (replacementPlan.equalsIgnoreCase("Internal Allocation")) {
            empResignationDetails.setReplacementDetail(ParamUtil.getString(request, "replacementDetail"));
        }

        if (empResignationDetails.getStatus() != 4 && empResignationDetails.getStatus() != 5) {
            empResignationDetails.setReasonForLeaving(ParamUtil.getString(request, "reasonForLeaving"));
            empResignationDetails.setKeyToRetention(ParamUtil.getString(request, "keyToRetention"));
            if (empResignationDetails.getKeyToRetention().equalsIgnoreCase("No")) {
                empResignationDetails.setReasonNonRetention(ParamUtil.getString(request, "reasonNonRetention"));
            }
        }
        empResignationDetails.setEligibleForRehire(ParamUtil.getString(request, ELIGIBLE_FOR_REHIRE));
        empResignationDetails.setOtherIssue(ParamUtil.getString(request, OTHER_ISSUE));
        empResignationDetails.setManagerComment(!ParamUtil.getString(request, MANAGER_COMMENT).isEmpty() ? ParamUtil.getString(request, MANAGER_COMMENT) : BLANK);

        empResignationDetails.setManagerSubmissionDate(now);
        ResignationLocalServiceUtil.updateResignation(empResignationDetails);

        if (null != mFinalLwd && localDate.isEqual(convertDateToLocalDateTime(mFinalLwd).toLocalDate()) && empResignationDetails.getStatus() == 2) {
            generateClearances(resignationId, empResignationDetails, managerData, employeeData);
        }
        if (empResignationDetails.getStatus() == 2) {
            String managerEmail = empResignationDetails.getAssigneeTo() ? empResignationDetails.getManagerEmail() + COMMA + empResignationDetails.getAssigneeEmail() : empResignationDetails.getManagerEmail();
            Email emailer = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
            emailer.setToAddress(employeeData.getEmail());
            emailer.setCcAddress(managerEmail + COMMA + DL_HR);
            emailer.setSubject(MessageFormat.format(MANAGER_RESIGNATION_APPROVED_EMAIL_SUBJECT, employeeData.getEcode(), employeeData.getName()));
            emailer.setBody(MessageFormat.format(MANAGER_RESIGNATION_APPROVED_EMAIL_BODY, managerData.getName(), convertDateToLocalDateTime(mFinalLwd).format(FORMATTER_YYYY_MM_DD)));
            emailer.setCreatedDate(now);
            emailer.setModule(MODULE_EXIT);
            emailer.setScheduled(false);
            emailer.setSent(false);
            EmailLocalServiceUtil.addEmail(emailer);

            Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
            email.setToAddress(DL_IT);
            email.setCcAddress(DL_HR);
            email.setSubject(MessageFormat.format(IT_ASSESTS_SUBMISSION_EMAIL_SUBJECT, empResignationDetails.getEcode(), employeeData.getName()));
            email.setBody(MessageFormat.format(IT_ASSESTS_SUBMISSION_EMAIL_BODY, employeeData.getName(), employeeData.getEcode(), getPortalUrl() + URL_EXIT_ADMIN));
            email.setCreatedDate(now);
            email.setModule(MODULE_EXIT);
            email.setScheduled(false);
            email.setSent(false);
            EmailLocalServiceUtil.addEmail(email);
        }
        if (empResignationDetails.getStatus() == 2 || empResignationDetails.getStatus() == 4 || empResignationDetails.getStatus() == 5) {
            Email emailer = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
            emailer.setToAddress(DL_AMINDIA);
            emailer.setCcAddress("nidhi.ayri@trantorinc.com");
            emailer.setSubject(EMPLOYEE_REPLACEMENT_EMAIL_SUBJECT);
            emailer.setBody(MessageFormat.format(EMPLOYEE_REPLACEMENT_EMAIL_BODY, empResignationDetails.getEcode(), employeeData.getName(), employeeData.getAccount(), managerData.getName(), convertDateToLocalDateTime(empResignationDetails.getLastWorkingDate()).format(FORMATTER_YYYY_MM_DD), empResignationDetails.getReplacementPlan()));
            emailer.setCreatedDate(now);
            emailer.setModule(MODULE_EXIT);
            emailer.setScheduled(false);
            emailer.setSent(false);
            EmailLocalServiceUtil.addEmail(emailer);
        }
    }

    @Override
    public void submitItAssetsResign(ActionRequest request) {
        long resignationId = ParamUtil.getLong(request, "itResignationId");
        Date now = getIstDate();
        Resignation empResignationDetails = ResignationLocalServiceUtil.fetchResignation(resignationId);
        empResignationDetails.setHasLaptop(ParamUtil.getString(request, "laptop"));
        empResignationDetails.setHasCharger(ParamUtil.getString(request, "charger"));
        empResignationDetails.setHasHeadSet(ParamUtil.getString(request, "headset"));
        empResignationDetails.setHasMouse(ParamUtil.getString(request, "mouse"));
        empResignationDetails.setHasLaptopBag(ParamUtil.getString(request, "laptopBag"));
        empResignationDetails.setHasHomeDesktop(ParamUtil.getString(request, "homeDesktop"));
        empResignationDetails.setHasHomeMonitor(ParamUtil.getString(request, "homeMonitor"));
        empResignationDetails.setOtherAssetsList(!ParamUtil.getString(request, "otherAssetsList").isEmpty() ? ParamUtil.getString(request, "otherAssetsList") : BLANK);
        empResignationDetails.setItAssetsSubmissionDate(now);
        ResignationLocalServiceUtil.updateResignation(empResignationDetails);
        Employee employeeData = EmployeeLocalServiceUtil.fetchEmployee(empResignationDetails.getEcode());
        StringBuilder emailBody = new StringBuilder();
        if (empResignationDetails.getHasLaptop().equalsIgnoreCase(ONE) || empResignationDetails.getHasCharger().equalsIgnoreCase(ONE) || empResignationDetails.getHasHeadSet().equalsIgnoreCase(ONE) || empResignationDetails.getHasMouse().equalsIgnoreCase(ONE) || empResignationDetails.getHasLaptopBag().equalsIgnoreCase(ONE) || empResignationDetails.getHasHomeDesktop().equalsIgnoreCase(ONE) || empResignationDetails.getHasHomeMonitor().equalsIgnoreCase(ONE) || !empResignationDetails.getOtherAssetsList().equalsIgnoreCase(BLANK)) {
            emailBody.append("<ol>");
            if (empResignationDetails.getHasLaptop().equalsIgnoreCase(ONE)) {
                emailBody.append(LIST_START + LAPTOP + LIST_CLOSE);
            }
            if (empResignationDetails.getHasCharger().equalsIgnoreCase(ONE)) {
                emailBody.append(LIST_START + CHARGER + LIST_CLOSE);
            }
            if (empResignationDetails.getHasHeadSet().equalsIgnoreCase(ONE)) {
                emailBody.append(LIST_START + HEADSET + LIST_CLOSE);
            }
            if (empResignationDetails.getHasMouse().equalsIgnoreCase(ONE)) {
                emailBody.append(LIST_START + MOUSE + LIST_CLOSE);
            }
            if (empResignationDetails.getHasLaptopBag().equalsIgnoreCase(ONE)) {
                emailBody.append(LIST_START + LAPTOP_BAG + LIST_CLOSE);
            }
            if (empResignationDetails.getHasHomeDesktop().equalsIgnoreCase(ONE)) {
                emailBody.append(LIST_START + DESKTOP + LIST_CLOSE);
            }
            if (empResignationDetails.getHasHomeMonitor().equalsIgnoreCase(ONE)) {
                emailBody.append(LIST_START + MONITOR + LIST_CLOSE);
            }
            if (!empResignationDetails.getOtherAssetsList().equalsIgnoreCase(BLANK)) {
                emailBody.append(LIST_START + empResignationDetails.getOtherAssetsList() + LIST_CLOSE);
            }
            emailBody.append("</ol>");
        } else {
            emailBody.append(NO_IT_ASSETS_ISSUED);
        }
        if (empResignationDetails.getStatus() == 2) {
            Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
            email.setToAddress(employeeData.getEmail());
            email.setCcAddress(DL_IT + COMMA + DL_HR);
            email.setSubject(MessageFormat.format(IT_ASSESTS_SUBMITTED_EMAIL_SUBJECT, employeeData.getEcode(), employeeData.getName()));
            email.setBody(MessageFormat.format(IT_ASSESTS_SUBMITTED_EMAIL_BODY, emailBody.toString()));
            email.setCreatedDate(getIstDate());
            email.setSent(false);
            email.setScheduled(false);
            email.setModule(MODULE_EXIT);
            EmailLocalServiceUtil.addEmail(email);
        } else if (empResignationDetails.getStatus() == 4 || empResignationDetails.getStatus() == 5) {
            Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
            email.setToAddress(DL_HR);
            email.setSubject(MessageFormat.format(IMMEDIATE_IT_ASSESTS_SUBMITTED_EMAIL_SUBJECT, employeeData.getEcode(), employeeData.getName()));
            email.setBody(MessageFormat.format(IMMEDIATE_IT_ASSESTS_SUBMITTED_EMAIL_BODY, employeeData.getName(), employeeData.getEcode(), emailBody.toString()));
            email.setCreatedDate(now);
            email.setSent(false);
            email.setScheduled(false);
            email.setModule(MODULE_EXIT);
            EmailLocalServiceUtil.addEmail(email);
        }
    }

    @Override
    public void exitClearancePdfDownload(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws IOException {
        String resignationFormId = ParamUtil.getString(resourceRequest, RESIGNATION_ID);
        Resignation empResignationDetails = ResignationLocalServiceUtil.fetchResignation(Long.parseLong(resignationFormId));
        PdfService.downloadPdf(resourceResponse, "ClearanceForm_" + empResignationDetails.getEcode(), PdfService.createPdf(generateHtmlString(resignationFormId)));
    }


    @Override
    public void submitHrWithdrawalActionForm(ActionRequest request) {
        List<ExitState> statusMap = ExitStateLocalServiceUtil.getExitStates(-1, -1);
        long withdrawResignationId = ParamUtil.getLong(request, "withdrawResignationId");
        int withdrawActionPerformed = ParamUtil.getInteger(request, "withdrawActionPerform");
        boolean withdrawApproved = false;
        boolean withdrawRejected = false;
        Date now = getIstDate();
        Resignation empResignationDetails = ResignationLocalServiceUtil.fetchResignation(withdrawResignationId);
        Employee employeeData = EmployeeLocalServiceUtil.fetchEmployee(empResignationDetails.getEcode());
        empResignationDetails.setHrWithdrawComment(ParamUtil.getString(request, "hrWithdrawComments"));
        if (withdrawActionPerformed == 1 || (withdrawActionPerformed == 2 && empResignationDetails.getStatus() == 3)) { //Accept Withdraw Request
            empResignationDetails.setStatus(statusMap.get(6).getExitStateKey());
            empResignationDetails.setRetainEmployeeDate(now);
            ResignationLocalServiceUtil.updateResignation(empResignationDetails);
            withdrawApproved = true;
        } else if (withdrawActionPerformed == 3 && empResignationDetails.getStatus() == 3) { //Reject Withdraw Request
            if (null != empResignationDetails.getHrSubmissionDate() && null != empResignationDetails.getManagerSubmissionDate()) {
                empResignationDetails.setStatus(statusMap.get(2).getExitStateKey());
            } else if (null != empResignationDetails.getHrSubmissionDate()) {
                empResignationDetails.setStatus(statusMap.get(1).getExitStateKey());
            } else {
                empResignationDetails.setStatus(statusMap.get(0).getExitStateKey());
            }
            ResignationLocalServiceUtil.updateResignation(empResignationDetails);
            withdrawRejected = true;
        }
        if (withdrawApproved) {
            List<ExitForm> exitFormData = ExitFormLocalServiceUtil.findExitFormByResignationId(empResignationDetails.getId());
            String managerEmail = empResignationDetails.getAssigneeTo() ? empResignationDetails.getManagerEmail() + COMMA + empResignationDetails.getAssigneeEmail() : empResignationDetails.getManagerEmail();
            Email withdrawApprovedEmail = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
            withdrawApprovedEmail.setToAddress(employeeData.getEmail());
            withdrawApprovedEmail.setCcAddress(DL_HR + COMMA + managerEmail);
            withdrawApprovedEmail.setSubject(MessageFormat.format(WITHDRAW_RESIGN_REQUEST_APPROVED_EMAIL_SUBJECT, employeeData.getEcode(), employeeData.getName()));
            withdrawApprovedEmail.setBody(MessageFormat.format(WITHDRAW_RESIGN_REQUEST_APPROVED_EMAIL_BODY, employeeData.getName()));
            withdrawApprovedEmail.setCreatedDate(now);
            withdrawApprovedEmail.setSent(false);
            withdrawApprovedEmail.setScheduled(false);
            withdrawApprovedEmail.setModule(MODULE_EXIT);
            EmailLocalServiceUtil.addEmail(withdrawApprovedEmail);
            if (!exitFormData.isEmpty()) {
                Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
                email.setToAddress(DL_FINANCE + COMMA + DL_ADMIN + COMMA + DL_IT);
                email.setCcAddress(DL_HR + COMMA + DL_AMINDIA);
                email.setSubject(MessageFormat.format(RETAINED_EMAIL_SUBJECT, employeeData.getEcode(), employeeData.getName()));
                email.setBody(MessageFormat.format(RETAINED_EMAIL_BODY, employeeData.getName(), employeeData.getEcode()));
                email.setCreatedDate(getIstDate());
                email.setSent(false);
                email.setScheduled(false);
                email.setModule(MODULE_EXIT);
                EmailLocalServiceUtil.addEmail(email);
            }
        } else if (withdrawRejected) {
            String managerEmail = empResignationDetails.getAssigneeTo() ? empResignationDetails.getManagerEmail() + COMMA + empResignationDetails.getAssigneeEmail() : empResignationDetails.getManagerEmail();
            Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
            email.setToAddress(employeeData.getEmail());
            email.setCcAddress(DL_HR + COMMA + managerEmail);
            email.setSubject(MessageFormat.format(WITHDRAW_RESIGN_REQUEST_REJECTED_EMAIL_SUBJECT, employeeData.getEcode(), employeeData.getName()));
            email.setBody(WITHDRAW_RESIGN_REQUEST_REJECTED_EMAIL_BODY);
            email.setCreatedDate(now);
            email.setSent(false);
            email.setScheduled(false);
            email.setModule(MODULE_EXIT);
            EmailLocalServiceUtil.addEmail(email);
        }
    }

    @Override
    public void submitFinalLwd(ActionRequest request) {
        LocalDate localDate = LocalDate.now();
        long resignationId = ParamUtil.getLong(request, "lwdResignationId");
        String roleType = ParamUtil.getString(request, ROLE_TYPE);
        Date now = getIstDate();
        boolean clearanceGenerated = false;
        boolean clearanceGeneration = false;
        boolean managerClearanceGeneration = false;
        Resignation empResignationDetails = ResignationLocalServiceUtil.fetchResignation(resignationId);
        Employee employeeData = EmployeeLocalServiceUtil.fetchEmployee(empResignationDetails.getEcode());
        Employee managerData = EmployeeLocalServiceUtil.findByEmail(empResignationDetails.getManagerEmail());
        LocalDate finalLwd = LocalDate.parse(ParamUtil.getString(request, "finalLwd"), FORMATTER_YYYY_MM_DD);
        List<ExitForm> exitFormData = ExitFormLocalServiceUtil.findExitFormByResignationId(resignationId);
        if (!exitFormData.isEmpty()) {
            deleteClearances(resignationId, exitFormData);
            if (empResignationDetails.getStatus() != 1) {
                clearanceGenerated = true;
            }
        }
        if (!finalLwd.isAfter(localDate) && empResignationDetails.getStatus() != 1) {
            generateClearances(resignationId, empResignationDetails, managerData, employeeData);
            if (empResignationDetails.getStatus() == 2 && !empResignationDetails.getExitQuestionnaire()) {
                managerClearanceGeneration = true;
            }
            clearanceGeneration = true;
        }
        empResignationDetails.setLastWorkingDate(convertLocalDateTimeToDate(finalLwd.atStartOfDay()));
        if (roleType.equalsIgnoreCase(ROLE_MANAGER)) {
            empResignationDetails.setManagerSubmissionDate(now);
        } else if (roleType.equalsIgnoreCase(ROLE_HR) && null == empResignationDetails.getHrSubmissionDate()) {
            empResignationDetails.setHrSubmissionDate(now);
        }

        ResignationLocalServiceUtil.updateResignation(empResignationDetails);
        String managerEmail = empResignationDetails.getAssigneeTo() ? empResignationDetails.getManagerEmail() + COMMA + empResignationDetails.getAssigneeEmail() : empResignationDetails.getManagerEmail();
        Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
        email.setToAddress(employeeData.getEmail());
        email.setCcAddress(managerEmail + COMMA + DL_HR);
        email.setSubject(MessageFormat.format(LWD_UPDATION_EMAIL_SUBJECT, employeeData.getEcode(), employeeData.getName()));
        email.setBody(MessageFormat.format(LWD_UPDATION_EMAIL_BODY, finalLwd));
        email.setCreatedDate(getIstDate());
        email.setSent(false);
        email.setScheduled(false);
        email.setModule(MODULE_EXIT);
        EmailLocalServiceUtil.addEmail(email);
        if (clearanceGenerated) {
            Email clearanceEmail = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
            clearanceEmail.setToAddress(DL_HR + COMMA + DL_FINANCE + COMMA + DL_ADMIN + COMMA + DL_IT + COMMA + managerEmail);
            clearanceEmail.setSubject(MessageFormat.format(LWD_CHANGED_AFTER_CLEARANCE_GENERATION_EMAIL_SUBJECT, employeeData.getEcode(), employeeData.getName()));
            clearanceEmail.setBody(MessageFormat.format(LWD_CHANGED_AFTER_CLEARANCE_GENERATION_EMAIL_BODY, employeeData.getName(), employeeData.getEcode()));
            clearanceEmail.setCreatedDate(now);
            clearanceEmail.setSent(false);
            clearanceEmail.setScheduled(false);
            clearanceEmail.setModule(MODULE_EXIT);
            EmailLocalServiceUtil.addEmail(clearanceEmail);
        }
        if (clearanceGeneration && (empResignationDetails.getStatus() == 5 || managerClearanceGeneration)) {
            Email manager = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
            manager.setToAddress(managerEmail);
            manager.setCcAddress(DL_HR);
            manager.setSubject(MessageFormat.format(MANAGER_CLEARANCE_EMAIL_SUBJECT, employeeData.getEcode(), employeeData.getName()));
            manager.setBody(MessageFormat.format(MANAGER_CLEARANCE_EMAIL_BODY, managerData.getName(), employeeData.getName(), employeeData.getEcode(), getPortalUrl() + URL_EXIT_ADMIN));
            manager.setCreatedDate(now);
            manager.setSent(false);
            manager.setScheduled(false);
            manager.setModule(MODULE_EXIT);
            EmailLocalServiceUtil.addEmail(manager);
        }
    }

    @Override
    public void submitAbscondTerminateForm(ActionRequest request) throws PortalException {
        Date now = getIstDate();
        Date absFinalLwd = null;
        Date tentativeAbscondDate = null;
        String empEcode = ParamUtil.getString(request, "empEcode");
        String terminationStartDate = ParamUtil.getString(request, "terminationStartDate");
        String hrComment = ParamUtil.getString(request, "hrComments");
        String actionPerformed = ParamUtil.getString(request, "actionPerformed");
        String mFinalLwd = ParamUtil.getString(request, "mFinalLwd");
        if (null != mFinalLwd && !mFinalLwd.equalsIgnoreCase(BLANK)) {
            absFinalLwd = convertLocalDateTimeToDate(LocalDate.parse(mFinalLwd, FORMATTER_YYYY_MM_DD).atStartOfDay());
        }
        String role = ParamUtil.getString(request, "role");
        String tentativeAbscond = ParamUtil.getString(request, "tentativeAbscondDate");
        if (null != tentativeAbscond && !tentativeAbscond.equalsIgnoreCase(BLANK)) {
            tentativeAbscondDate = convertLocalDateTimeToDate(LocalDate.parse(tentativeAbscond, FORMATTER_YYYY_MM_DD).atStartOfDay());
        }
        String emailId = ParamUtil.getString(request, "emailId");
        Employee employeeData = EmployeeLocalServiceUtil.fetchEmployee(empEcode);
        List<ExitState> statusMap = ExitStateLocalServiceUtil.getExitStates(-1, -1);
        Date abscondTerminateDate = role.equalsIgnoreCase(ROLE_MANAGER) ? tentativeAbscondDate : absFinalLwd;
        long resignationId;
        boolean absImmediateTermination = false;
        boolean abscondTerminate = false;
        Employee managerData = EmployeeLocalServiceUtil.fetchEmployee(employeeData.getManager());
        List<Resignation> resignedEntry = ResignationLocalServiceUtil.findResignationByEcode(empEcode).stream()
                .filter(s -> s.getStatus() == statusMap.get(0).getExitStateKey() || s.getStatus() == statusMap.get(1).getExitStateKey() || s.getStatus() == statusMap.get(2).getExitStateKey() || s.getStatus() == statusMap.get(5).getExitStateKey()).collect(Collectors.toList());
        //Employee has already resigned
        if (!resignedEntry.isEmpty()) {
            Resignation resign = ResignationLocalServiceUtil.fetchResignation(resignedEntry.get(0).getId());
            resignationId = resign.getId();
            resign.setReason(actionPerformed);
            resign.setHrComment(hrComment);
            resign.setCreationDate(now);
            if (role.equalsIgnoreCase(ROLE_MANAGER) && null != resign.getLastWorkingDate()) {
                resign.setLastWorkingDate(null);
            }
            if (actionPerformed.equalsIgnoreCase(statusMap.get(4).getExitStateDescription())) {
                if (role.equalsIgnoreCase(ROLE_MANAGER)) {
                    resign.setReplacementPlan(ParamUtil.getString(request, REPLACEMENT_PLAN));
                    resign.setKeyToProject(ParamUtil.getString(request, KEY_TO_PROJECT));
                    resign.setClientImpact(ParamUtil.getString(request, CLIENT_IMPACT));
                    resign.setRating(ParamUtil.getString(request, RATING));
                    resign.setEligibleForRehire(ParamUtil.getString(request, ELIGIBLE_FOR_REHIRE));
                    resign.setOtherIssue(ParamUtil.getString(request, OTHER_ISSUE));
                    resign.setManagerComment(!ParamUtil.getString(request, MANAGER_COMMENT).isEmpty() ? ParamUtil.getString(request, MANAGER_COMMENT) : BLANK);
                    resign.setManagerSubmissionDate(now);
                }
                resign.setStatus(statusMap.get(4).getExitStateKey());
                resign.setAbscondTerminateDate(abscondTerminateDate);
                if (role.equalsIgnoreCase(ROLE_HR)) {
                    resign.setLastWorkingDate(absFinalLwd);
                }
            } else {
                resign.setStatus(statusMap.get(5).getExitStateKey());
                resign.setAbscondTerminateDate(convertLocalDateTimeToDate(LocalDate.parse(terminationStartDate, FORMATTER_YYYY_MM_DD).atStartOfDay()));
                resign.setTerminationType(ParamUtil.getString(request, "terminationType"));
                resign.setNoticePeriod(String.valueOf(ParamUtil.getInteger(request, TERMINATION_NOTICE_PERIOD)));
                String lwd = ParamUtil.getString(request, "lwd");
                resign.setLastWorkingDate(convertLocalDateTimeToDate(LocalDate.parse(lwd, FORMATTER_YYYY_MM_DD).atStartOfDay()));
                if (!emailId.equalsIgnoreCase(BLANK)) {
                    resign.setAlternateEmail(emailId);
                }
            }
            if (role.equalsIgnoreCase(ROLE_HR) && null == resign.getHrSubmissionDate()) {
                resign.setHrSubmissionDate(now);
            } else {
                resign.setManagerSubmissionDate(now);
            }
            ResignationLocalServiceUtil.updateResignation(resign);
        } else { //new entry to be created
            Resignation resign = ResignationLocalServiceUtil.createResignation(CounterLocalServiceUtil.increment());
            resign.setEcode(employeeData.getEcode());
            resign.setAccount(employeeData.getAccount());
            resign.setManagerEmail(managerData.getEmail());
            resign.setAssigneeEmail(managerData.getEmail());
            resign.setHrComment(hrComment);
            resign.setCreationDate(now);
            resignationId = resign.getId();
            if (role.equalsIgnoreCase(ROLE_MANAGER)) {
                resign.setReplacementPlan(ParamUtil.getString(request, REPLACEMENT_PLAN));
                resign.setKeyToProject(ParamUtil.getString(request, KEY_TO_PROJECT));
                resign.setClientImpact(ParamUtil.getString(request, CLIENT_IMPACT));
                resign.setRating(ParamUtil.getString(request, RATING));
                resign.setEligibleForRehire(ParamUtil.getString(request, ELIGIBLE_FOR_REHIRE));
                resign.setOtherIssue(ParamUtil.getString(request, OTHER_ISSUE));
                resign.setManagerComment(!ParamUtil.getString(request, MANAGER_COMMENT).isEmpty() ? ParamUtil.getString(request, MANAGER_COMMENT) : BLANK);
                resign.setManagerSubmissionDate(now);
            } else {
                resign.setHrSubmissionDate(now);
            }
            if (actionPerformed.equalsIgnoreCase(statusMap.get(4).getExitStateDescription())) {
                resign.setReason(statusMap.get(4).getExitStateDescription());
                resign.setStatus(statusMap.get(4).getExitStateKey());
                resign.setAbscondTerminateDate(abscondTerminateDate);
                if (role.equalsIgnoreCase(ROLE_HR)) {
                    resign.setLastWorkingDate(absFinalLwd);
                }
            } else {
                resign.setReason(statusMap.get(5).getExitStateDescription());
                resign.setAbscondTerminateDate(convertLocalDateTimeToDate(LocalDate.parse(terminationStartDate, FORMATTER_YYYY_MM_DD).atStartOfDay()));
                resign.setStatus(statusMap.get(5).getExitStateKey());
                resign.setTerminationType(ParamUtil.getString(request, "terminationType"));
                resign.setNoticePeriod(String.valueOf(ParamUtil.getInteger(request, "terminationNoticePeriod")));
                String lwd = ParamUtil.getString(request, "lwd");
                resign.setLastWorkingDate(convertLocalDateTimeToDate(LocalDate.parse(lwd, FORMATTER_YYYY_MM_DD).atStartOfDay()));
                resign.setAlternateEmail(emailId);
            }
            ResignationLocalServiceUtil.addResignation(resign);
        }
        if (role.equalsIgnoreCase(ROLE_HR) && (actionPerformed.equalsIgnoreCase(statusMap.get(4).getExitStateDescription()) || actionPerformed.equalsIgnoreCase(statusMap.get(5).getExitStateDescription()))) {
            abscondTerminate = true;
        }


        if (role.equalsIgnoreCase(ROLE_HR) && (actionPerformed.equalsIgnoreCase(statusMap.get(4).getExitStateDescription()) || (actionPerformed.equalsIgnoreCase(statusMap.get(5).getExitStateDescription()) && !(LocalDate.parse(ParamUtil.getString(request, "lwd"), FORMATTER_YYYY_MM_DD).atStartOfDay().isAfter(LocalDate.now().atStartOfDay()))))) {
            generateClearances(resignationId, ResignationLocalServiceUtil.fetchResignation(resignationId), managerData, employeeData);
            absImmediateTermination = true;

        } else if (role.equalsIgnoreCase(ROLE_MANAGER) && (actionPerformed.equalsIgnoreCase(statusMap.get(4).getExitStateDescription()))) {
            Resignation resign = ResignationLocalServiceUtil.fetchResignation(resignationId);
            String managerEmails = resign.getAssigneeTo() ? resign.getManagerEmail() + COMMA + resign.getManagerEmail() : resign.getManagerEmail();
            Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
            email.setToAddress(DL_HR);
            email.setCcAddress(managerEmails);
            email.setSubject(MessageFormat.format(MANAGER_MARKED_ABSCONDED_EMAIL_SUBJECT, employeeData.getEcode(), employeeData.getName()));
            email.setBody(MessageFormat.format(MANAGER_MARKED_ABSCONDED_EMAIL_BODY, managerData.getName(), employeeData.getEcode(), employeeData.getName(), convertDateToLocalDateTime(resign.getAbscondTerminateDate()).format(FORMATTER_YYYY_MM_DD), getPortalUrl() + URL_EXIT_ADMIN));
            email.setCreatedDate(now);
            email.setSent(false);
            email.setScheduled(false);
            email.setModule(MODULE_EXIT);
            EmailLocalServiceUtil.addEmail(email);
        }
        Resignation resign = ResignationLocalServiceUtil.fetchResignation(resignationId);
        String managerEmails = resign.getAssigneeTo() ? resign.getManagerEmail() + COMMA + resign.getManagerEmail() : resign.getManagerEmail();
        if (role.equalsIgnoreCase(ROLE_HR) && (actionPerformed.equalsIgnoreCase(statusMap.get(4).getExitStateDescription()))) {
            Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
            email.setToAddress(managerEmails);
            email.setCcAddress(DL_HR);
            email.setSubject(MessageFormat.format(HR_MARKED_ABSCONDED_EMAIL_SUBJECT, employeeData.getEcode(), employeeData.getName()));
            email.setBody(MessageFormat.format(HR_MARKED_ABSCONDED_EMAIL_BODY, employeeData.getEcode(), employeeData.getName(), convertDateToLocalDateTime(resign.getLastWorkingDate()).format(FORMATTER_YYYY_MM_DD), getPortalUrl() + URL_EXIT_ADMIN));
            email.setCreatedDate(now);
            email.setSent(false);
            email.setScheduled(false);
            email.setModule(MODULE_EXIT);
            EmailLocalServiceUtil.addEmail(email);
        } else if (role.equalsIgnoreCase(ROLE_HR) && (actionPerformed.equalsIgnoreCase(statusMap.get(5).getExitStateDescription()))) {
            Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
            email.setToAddress(managerEmails);
            email.setCcAddress(DL_HR);
            email.setSubject(MessageFormat.format(TERMINATION_EMAIL_SUBJECT, employeeData.getEcode(), employeeData.getName()));
            email.setBody(MessageFormat.format(TERMINATION_EMAIL_BODY, employeeData.getEcode(), employeeData.getName(), convertDateToLocalDateTime(resign.getAbscondTerminateDate()).format(FORMATTER_YYYY_MM_DD), convertDateToLocalDateTime(resign.getLastWorkingDate()).format(FORMATTER_YYYY_MM_DD), getPortalUrl() + URL_EXIT_ADMIN));
            email.setCreatedDate(now);
            email.setSent(false);
            email.setScheduled(false);
            email.setModule(MODULE_EXIT);
            EmailLocalServiceUtil.addEmail(email);
        }

        if (abscondTerminate) {
            Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
            email.setToAddress(DL_IT);
            email.setCcAddress(DL_HR);
            email.setSubject(MessageFormat.format(IT_ASSESTS_SUBMISSION_EMAIL_SUBJECT, employeeData.getEcode(), employeeData.getName()));
            email.setBody(MessageFormat.format(IT_ASSESTS_SUBMISSION_EMAIL_BODY, employeeData.getName(), employeeData.getEcode(), getPortalUrl() + URL_EXIT_ADMIN));
            email.setCreatedDate(now);
            email.setSent(false);
            email.setScheduled(false);
            email.setModule(MODULE_EXIT);
            EmailLocalServiceUtil.addEmail(email);
        }
        if (absImmediateTermination) {
            Email managerEmail = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
            managerEmail.setToAddress(managerEmails);
            managerEmail.setCcAddress(DL_HR);
            managerEmail.setSubject(MessageFormat.format(MANAGER_CLEARANCE_EMAIL_SUBJECT, employeeData.getEcode(), employeeData.getName()));
            managerEmail.setBody(MessageFormat.format(MANAGER_CLEARANCE_EMAIL_BODY, managerData.getName(), employeeData.getName(), employeeData.getEcode(), getPortalUrl() + URL_EXIT_ADMIN));
            managerEmail.setCreatedDate(now);
            managerEmail.setSent(false);
            managerEmail.setScheduled(false);
            managerEmail.setModule(MODULE_EXIT);
            EmailLocalServiceUtil.addEmail(managerEmail);
        }
    }

    @Override
    public void reopenForm(ActionRequest request) {
        String resignationId = ParamUtil.getString(request, RESIGNATION_ID);
        Resignation employeeResignData = ResignationLocalServiceUtil.fetchResignation(Long.parseLong(resignationId));
        Employee employeeData = EmployeeLocalServiceUtil.fetchEmployee(employeeResignData.getEcode());
        List<ExitForm> exitForm = ExitFormLocalServiceUtil.findExitFormByResignationId(Long.parseLong(resignationId));
        boolean otherDepartment = false;
        StringBuilder emailTO = new StringBuilder();
        if (!exitForm.isEmpty()) {
            ExitForm formStatus = exitForm.get(0);
            if (ParamUtil.getInteger(request, "manager") == 1) {
                otherDepartment = true;
                String managerEmail = employeeResignData.getAssigneeTo() ? employeeResignData.getAssigneeEmail() + COMMA + employeeResignData.getManagerEmail() : employeeResignData.getManagerEmail();
                formStatus.setManagerFormStatus(false);
                emailTO.append(managerEmail);
                emailTO.append(COMMA);
            }
            if (ParamUtil.getInteger(request, "it") == 1) {
                otherDepartment = true;
                formStatus.setItFormStatus(false);
                emailTO.append(DL_IT);
                emailTO.append(COMMA);
            }
            if (ParamUtil.getInteger(request, "admin") == 1) {
                otherDepartment = true;
                formStatus.setAdminFormStatus(false);
                emailTO.append(DL_ADMIN);
                emailTO.append(COMMA);
            }
            if (ParamUtil.getInteger(request, "finance") == 1) {
                otherDepartment = true;
                formStatus.setFinanceFormStatus(false);
                emailTO.append(DL_FINANCE);
                emailTO.append(COMMA);
            }
            if (ParamUtil.getInteger(request, "hr") == 1) {
                formStatus.setHrFormStatus(false);
                emailTO.append(DL_HR);
                emailTO.append(COMMA);
            }
            ExitFormLocalServiceUtil.updateExitForm(formStatus);
        }
        Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
        email.setSubject(MessageFormat.format(REOPEN_CLEARANCE_EMAIL_SUBJECT, employeeData.getEcode(), employeeData.getName()));
        email.setBody(MessageFormat.format(REOPEN_CLEARANCE_EMAIL_BODY, employeeData.getName(), employeeData.getEcode()));
        email.setToAddress(emailTO.deleteCharAt(emailTO.length() - 1).toString());
        if (otherDepartment) {
            email.setCcAddress(DL_HR);
        }
        email.setCreatedDate(getIstDate());
        email.setSent(false);
        email.setScheduled(false);
        email.setModule(MODULE_EXIT);
        EmailLocalServiceUtil.addEmail(email);
    }

    @Override
    public void hrRemarksSubmissionUrl(ActionRequest request) {
        List<QuestionnaireForm> questionnaireFormData = QuestionnaireFormLocalServiceUtil.findQuestionnaireFormByResignationId(ParamUtil.getLong(request, RESIGNATION_ID));
        if (!questionnaireFormData.isEmpty()) {
            QuestionnaireForm questionnaireForm = questionnaireFormData.get(0);
            questionnaireForm.setHrRemark(ParamUtil.getString(request, "hrRemarksQues"));
            QuestionnaireFormLocalServiceUtil.updateQuestionnaireForm(questionnaireForm);
        }
    }

    @Override
    public void exitClearanceWorkflow(ActionRequest request, ActionResponse response) {
        long resignationFormId = ParamUtil.getLong(request, RESIGNATION_ID);
        String roleType = ParamUtil.getString(request, ROLE_TYPE);
        Resignation employeeResignData = ResignationLocalServiceUtil.fetchResignation(resignationFormId);
        Employee employeeData = EmployeeLocalServiceUtil.fetchEmployee(employeeResignData.getEcode());
        Employee managerData = EmployeeLocalServiceUtil.findByEmail(employeeResignData.getManagerEmail());
        List<ExitForm> exitFormData = ExitFormLocalServiceUtil.findExitFormByResignationId(resignationFormId);
        List<RecoveryReimbursmentDto> recoveryFormData = new ArrayList<>();
        List<RecoveryReimbursmentDto> selectedRecoveryFormData = new ArrayList<>();
        List<RecoveryReimbursmentDto> recoveryFormData1 = new ArrayList<>();
        List<RecoveryReimbursmentDto> reimbursmentFormData = new ArrayList<>();
        List<RecoveryReimbursmentDto> adminRecoveryFormData = new ArrayList<>();
        List<RecoveryReimbursmentDto> adminSelectedRecoveryFormData = new ArrayList<>();
        List<RecoveryReimbursmentDto> adminRecoveryFormData1 = new ArrayList<>();
        List<RecoveryReimbursmentDto> hrRecoveryFormData = new ArrayList<>();
        List<RecoveryReimbursmentDto> itRecoveryFormData = new ArrayList<>();
        List<RecoveryReimbursmentDto> itSelectedRecoveryFormData = new ArrayList<>();
        List<RecoveryReimbursmentDto> itRecoveryFormData1 = new ArrayList<>();
        EmployeeDto dataDto = new EmployeeDto();
        if (null != employeeData) {
            dataDto.setEmployeeCode(employeeData.getEcode());
            dataDto.setEmployeeName(employeeData.getName());
            dataDto.setDoj(convertDateToLocalDateTime(employeeData.getDoj()).format(FORMATTER_YYYY_MM_DD));
            dataDto.setEmployeeDesignation(employeeData.getDesignation());
            dataDto.setProject(employeeData.getAccount());
        }

        dataDto.setResignationId(employeeResignData.getId());
        dataDto.setRoleType(roleType);

        if (null != employeeResignData.getLastWorkingDate()) {
            dataDto.setLastWorkingDate(convertDateToLocalDateTime(employeeResignData.getLastWorkingDate()).format(FORMATTER_YYYY_MM_DD));
        }

        dataDto.setManagerName(null != managerData ? managerData.getName() : BLANK);
        if (null != employeeResignData.getCreationDate()) {
            dataDto.setResignationDate(convertDateToLocalDateTime(employeeResignData.getCreationDate()).format(FORMATTER_YYYY_MM_DD));
        }
        long exitFormId = exitFormData.get(0).getId();
        List<HrForm> hrFormData = HrFormLocalServiceUtil.findHrFormByExitId(exitFormId);
        List<ManagerForm> managerform = ManagerFormLocalServiceUtil.findManagerFormByExitId(exitFormId);
        List<AdminForm> adminFormData = AdminFormLocalServiceUtil.findAdminFormByExitId(exitFormId);
        List<ItForm> itform = ItFormLocalServiceUtil.findItFormByExitId(exitFormId);
        List<FinanceForm> financeFormData = FinanceFormLocalServiceUtil.findFinanceFormByExitId(exitFormId);
        ExitForm exitForm = exitFormData.get(0);
        ClearanceFormDto clearanceFormData = new ClearanceFormDto();
        clearanceFormData.setHrSubmitted(exitForm.getHrFormStatus());
        clearanceFormData.setShowSubmitButton(roleType.equalsIgnoreCase(ROLE_HR) && exitForm.getManagerFormStatus() && exitForm.getAdminFormStatus() && exitForm.getFinanceFormStatus() && exitForm.getItFormStatus());
        clearanceFormData.setSaveSubmitEnable((roleType.equalsIgnoreCase(ROLE_MANAGER) && !exitForm.getManagerFormStatus()) || (roleType.equalsIgnoreCase(ROLE_IT) && !exitForm.getItFormStatus()) || (roleType.equalsIgnoreCase(ROLE_ADMIN) && !exitForm.getAdminFormStatus()) || (roleType.equalsIgnoreCase(ROLE_FINANCE) && !exitForm.getFinanceFormStatus()) || (roleType.equalsIgnoreCase(ROLE_HR) && !exitForm.getHrFormStatus()));
        if (!managerform.isEmpty() && (roleType.equalsIgnoreCase(ROLE_MANAGER) || ((roleType.equalsIgnoreCase(ROLE_HR) || roleType.equalsIgnoreCase(ROLE_FINANCE)) && exitForm.getManagerFormStatus()))) {
            ManagerClearanceDto managerClearanceDataDto = new ManagerClearanceDto();
            ManagerForm managerFormData = managerform.get(0);
            managerClearanceDataDto.setTicketNo(managerFormData.getTicketNo());
            managerClearanceDataDto.setTicketNoRemark(managerFormData.getTicketNoRemark());
            managerClearanceDataDto.setSeparationDocument(managerFormData.getSeparationDocument());
            managerClearanceDataDto.setSeparationDocumentRemark(managerFormData.getSeparationDocumentRemark());
            managerClearanceDataDto.setManagerSubmitted(exitForm.getManagerFormStatus());
            managerClearanceDataDto.setManagerResignSubmitted(null != employeeResignData.getManagerSubmissionDate());
            clearanceFormData.setManagerClearanceDto(managerClearanceDataDto);
        }
        if (!itform.isEmpty() && (roleType.equalsIgnoreCase(ROLE_IT) || ((roleType.equalsIgnoreCase(ROLE_HR) || roleType.equalsIgnoreCase(ROLE_FINANCE)) && exitForm.getItFormStatus()))) {
            ItForm itFormData = itform.get(0);
            List<RecoveryReimbursement> itRecoveryData = RecoveryReimbursementLocalServiceUtil.findAllRecoveryFormByDepartment(itFormData.getId(), DEPARTMENT_IT);
            for (RecoveryReimbursement recovery : itRecoveryData) {
                RecoveryReimbursmentDto dto = new RecoveryReimbursmentDto();
                dto.setId(recovery.getId());
                dto.setRecoveryItem(recovery.getRecoveryItem());
                dto.setRecoveryStatus(recovery.getRecoveryStatus());
                dto.setRecoveryAmount(recovery.getRecoveryAmount());
                dto.setApproved(recovery.getApproved());
                dto.setComment(recovery.getComment());
                itRecoveryFormData.add(dto);
                if (recovery.getRecoveryStatus() == 1) {
                    itSelectedRecoveryFormData.add(dto);
                }
            }
            if (itFormData.getSystemRecoveryAmtStatus() == 1) {
                RecoveryReimbursmentDto dto = new RecoveryReimbursmentDto();
                dto.setRecoveryItem(DESKTOP);
                dto.setNameSpace(DESKTOP_NAMESPACE);
                dto.setApproved(itFormData.getSystemRecoveryApproved());
                dto.setComment(itFormData.getSystemRecoveryComment());
                dto.setRecoveryAmount(itFormData.getSystemRecoveryAmt());
                itRecoveryFormData1.add(dto);
            }
            if (itFormData.getLaptopRecoveryAmtStatus() == 1) {
                RecoveryReimbursmentDto dto = new RecoveryReimbursmentDto();
                dto.setRecoveryItem(LAPTOP);
                dto.setNameSpace(LAPTOP_NAMESPACE);
                dto.setApproved(itFormData.getLaptopRecoveryApproved());
                dto.setComment(itFormData.getLaptopRecoveryComment());
                dto.setRecoveryAmount(itFormData.getLaptopRecoveryAmt());
                itRecoveryFormData1.add(dto);
            }
            if (itFormData.getCommunicationRecoveryStatus() == 1) {
                RecoveryReimbursmentDto dto = new RecoveryReimbursmentDto();
                dto.setRecoveryItem(TELEPHONE_MOBILE_SIMCARD);
                dto.setNameSpace(TELEPHONE_NAMESPACE);
                dto.setApproved(itFormData.getCommunicationRecoveryApproved());
                dto.setComment(itFormData.getCommunicationRecoveryComment());
                dto.setRecoveryAmount(itFormData.getCommunicationRecoveryAmt());
                itRecoveryFormData1.add(dto);
            }
            if (itFormData.getHeadphoneRecoveryAmtStatus() == 1) {
                RecoveryReimbursmentDto dto = new RecoveryReimbursmentDto();
                dto.setRecoveryItem(HEADPHONE);
                dto.setNameSpace(HEADPHONE_NAMESPACE);
                dto.setApproved(itFormData.getHeadphoneRecoveryApproved());
                dto.setComment(itFormData.getHeadphoneRecoveryComment());
                dto.setRecoveryAmount(itFormData.getHeadphoneRecoveryAmt());
                itRecoveryFormData1.add(dto);
            }
            if (itFormData.getLaptopBagRecoveryAmtStatus() == 1) {
                RecoveryReimbursmentDto dto = new RecoveryReimbursmentDto();
                dto.setRecoveryItem(LAPTOP_BAG);
                dto.setNameSpace(LAPTOP_BAG_NAMESPACE);
                dto.setApproved(itFormData.getLaptopBagRecoveryApproved());
                dto.setComment(itFormData.getLaptopBagRecoveryComment());
                dto.setRecoveryAmount(itFormData.getLaptopBagRecoveryAmt());
                itRecoveryFormData1.add(dto);
            }
            if (itFormData.getMonitorRecoveryAmtStatus() == 1) {
                RecoveryReimbursmentDto dto = new RecoveryReimbursmentDto();
                dto.setRecoveryItem(MONITOR);
                dto.setNameSpace(MONITOR_NAMESPACE);
                dto.setApproved(itFormData.getMonitorRecoveryApproved());
                dto.setComment(itFormData.getMonitorRecoveryComment());
                dto.setRecoveryAmount(itFormData.getMonitorRecoveryAmt());
                itRecoveryFormData1.add(dto);
            }
            if (itFormData.getChargerRecoveryAmtStatus() == 1) {
                RecoveryReimbursmentDto dto = new RecoveryReimbursmentDto();
                dto.setRecoveryItem(CHARGER);
                dto.setNameSpace(CHARGER_NAMESPACE);
                dto.setApproved(itFormData.getChargerRecoveryApproved());
                dto.setComment(itFormData.getChargerRecoveryComment());
                dto.setRecoveryAmount(itFormData.getChargerRecoveryAmt());
                itRecoveryFormData1.add(dto);
            }
            if (itFormData.getMouseRecoveryAmtStatus() == 1) {
                RecoveryReimbursmentDto dto = new RecoveryReimbursmentDto();
                dto.setRecoveryItem(MOUSE);
                dto.setNameSpace(MOUSE_NAMESPACE);
                dto.setApproved(itFormData.getMouseRecoveryApproved());
                dto.setComment(itFormData.getMouseRecoveryComment());
                dto.setRecoveryAmount(itFormData.getMouseRecoveryAmt());
                itRecoveryFormData1.add(dto);
            }
            ItClearanceDto itClearanceDataDto = new ItClearanceDto();
            itClearanceDataDto.setViewMode(!exitForm.getItFormStatus() && roleType.equalsIgnoreCase(ROLE_IT));
            itClearanceDataDto.setMailPst(itFormData.getMailPst());
            itClearanceDataDto.setMailPstRemark(itFormData.getMailPstRemark());
            itClearanceDataDto.setDownloadFolder(itFormData.getDownloadFolder());
            itClearanceDataDto.setDownloadFolderRemark(itFormData.getDownloadFolderRemark());
            itClearanceDataDto.setDocumentFolder(itFormData.getDocumentFolder());
            itClearanceDataDto.setDocumentFolderRemark(itFormData.getDocumentFolderRemark());
            itClearanceDataDto.setOtherData(itFormData.getOtherData());
            itClearanceDataDto.setOtherDataRemark(itFormData.getOtherDataRemark());
            itClearanceDataDto.setGoogleDrive(itFormData.getGoogleDrive());
            itClearanceDataDto.setGoogleDriveRemark(itFormData.getGoogleDriveRemark());
            itClearanceDataDto.setOthers(itFormData.getOthers());
            itClearanceDataDto.setClientSystemRecovered(itFormData.getClientSystemRecovered());
            itClearanceDataDto.setClientSystemRecoveredRemark(itFormData.getClientSystemRecoveredRemark());
            itClearanceDataDto.setMailForwarding(itFormData.getMailForwarding());
            itClearanceDataDto.setMailForwardingRemark(itFormData.getMailForwardingRemark());
            itClearanceDataDto.setEmailDisable(itFormData.getEmailDisable());
            itClearanceDataDto.setEmailDisableRemark(itFormData.getEmailDisableRemark());
            itClearanceDataDto.setSystemRecovered(itFormData.getSystemRecovered());
            itClearanceDataDto.setSystemRecoveredRemark(itFormData.getSystemRecoveredRemark());
            itClearanceDataDto.setAccessCardDisable(itFormData.getAccessCardDisable());
            itClearanceDataDto.setAccessCardDisableRemark(itFormData.getAccessCardDisableRemark());
            itClearanceDataDto.setSeparationDocument(itFormData.getSeparationDocument());
            itClearanceDataDto.setSeparationDocumentRemark(itFormData.getSeparationDocumentRemark());
            itClearanceDataDto.setSystemRecoveryAmt(itFormData.getSystemRecoveryAmt());
            itClearanceDataDto.setSystemRecoveryAmtStatus(itFormData.getSystemRecoveryAmtStatus() == 0 && !exitForm.getItFormStatus() && !employeeResignData.getHasHomeDesktop().equalsIgnoreCase(BLANK) ? Integer.parseInt(employeeResignData.getHasHomeDesktop()) : itFormData.getSystemRecoveryAmtStatus());
            itClearanceDataDto.setApprovedSystemRecovery(itFormData.getSystemRecoveryApproved());
            itClearanceDataDto.setCommentSystemRecovery(itFormData.getSystemRecoveryComment());
            itClearanceDataDto.setLaptopRecoveryAmt(itFormData.getLaptopRecoveryAmt());
            itClearanceDataDto.setLaptopRecoveryAmtStatus(itFormData.getLaptopRecoveryAmtStatus() == 0 && !exitForm.getItFormStatus() && !employeeResignData.getHasLaptop().equalsIgnoreCase(BLANK) ? Integer.parseInt(employeeResignData.getHasLaptop()) : itFormData.getLaptopRecoveryAmtStatus());
            itClearanceDataDto.setApprovedLaptopRecovery(itFormData.getLaptopBagRecoveryApproved());
            itClearanceDataDto.setCommentLaptopRecovery(itFormData.getLaptopRecoveryComment());
            itClearanceDataDto.setHeadphoneRecoveryAmt(itFormData.getHeadphoneRecoveryAmt());
            itClearanceDataDto.setHeadphoneRecoveryAmtStatus(itFormData.getHeadphoneRecoveryAmtStatus() == 0 && !exitForm.getItFormStatus() && !employeeResignData.getHasHeadSet().equalsIgnoreCase(BLANK) ? Integer.parseInt(employeeResignData.getHasHeadSet()) : itFormData.getHeadphoneRecoveryAmtStatus());
            itClearanceDataDto.setApprovedHeadphoneRecovery(itFormData.getHeadphoneRecoveryApproved());
            itClearanceDataDto.setCommentHeadphoneRecovery(itFormData.getHeadphoneRecoveryComment());
            itClearanceDataDto.setLaptopBagRecoveryAmt(itFormData.getLaptopBagRecoveryAmt());
            itClearanceDataDto.setLaptopBagRecoveryAmtStatus(itFormData.getLaptopBagRecoveryAmtStatus() == 0 && !exitForm.getItFormStatus() && !employeeResignData.getHasLaptopBag().equalsIgnoreCase(BLANK) ? Integer.parseInt(employeeResignData.getHasLaptopBag()) : itFormData.getLaptopBagRecoveryAmtStatus());
            itClearanceDataDto.setApprovedLaptopBagRecovery(itFormData.getLaptopBagRecoveryApproved());
            itClearanceDataDto.setCommentLaptopBagRecovery(itFormData.getLaptopBagRecoveryComment());
            itClearanceDataDto.setMonitorAmt(itFormData.getMonitorRecoveryAmt());
            itClearanceDataDto.setMonitorRecoveryAmtStatus(itFormData.getMonitorRecoveryAmtStatus() == 0 && !exitForm.getItFormStatus() && !employeeResignData.getHasHomeMonitor().equalsIgnoreCase(BLANK) ? Integer.parseInt(employeeResignData.getHasHomeMonitor()) : itFormData.getMonitorRecoveryAmtStatus());
            itClearanceDataDto.setApprovedMonitorRecovery(itFormData.getMonitorRecoveryApproved());
            itClearanceDataDto.setCommentMonitorRecovery(itFormData.getMonitorRecoveryComment());
            itClearanceDataDto.setChargerAmt(itFormData.getChargerRecoveryAmt());
            itClearanceDataDto.setChargerRecoveryAmtStatus(itFormData.getChargerRecoveryAmtStatus() == 0 && !exitForm.getItFormStatus() && !employeeResignData.getHasCharger().equalsIgnoreCase(BLANK) ? Integer.parseInt(employeeResignData.getHasCharger()) : itFormData.getChargerRecoveryAmtStatus());
            itClearanceDataDto.setApprovedChargerRecovery(itFormData.getChargerRecoveryApproved());
            itClearanceDataDto.setCommentChargerRecovery(itFormData.getChargerRecoveryComment());
            itClearanceDataDto.setMouseRecoveryAmt(itFormData.getMouseRecoveryAmt());
            itClearanceDataDto.setMouseRecoveryAmtStatus(itFormData.getMouseRecoveryAmtStatus() == 0 && !exitForm.getItFormStatus() && !employeeResignData.getHasMouse().equalsIgnoreCase(BLANK) ? Integer.parseInt(employeeResignData.getHasMouse()) : itFormData.getMouseRecoveryAmtStatus());
            itClearanceDataDto.setApprovedMouseRecovery(itFormData.getMouseRecoveryApproved());
            itClearanceDataDto.setCommentMouseRecovery(itFormData.getMouseRecoveryComment());
            itClearanceDataDto.setItTicket(!managerform.isEmpty() && exitForm.getManagerFormStatus() ? managerform.get(0).getTicketNo() : itFormData.getTicketNo());
            itClearanceDataDto.setItTicketRemark(!managerform.isEmpty() && exitForm.getManagerFormStatus() ? managerform.get(0).getTicketNoRemark() : itFormData.getTicketNoRemark());
            itClearanceDataDto.setOthersRemark(itFormData.getOthersRemark());
            itClearanceDataDto.setCommunicationRecoveryAmt(itFormData.getCommunicationRecoveryAmt());
            itClearanceDataDto.setCommunicationRecoveryStatus(itFormData.getCommunicationRecoveryStatus());
            itClearanceDataDto.setApprovedCommunicationRecovery(itFormData.getCommunicationRecoveryApproved());
            itClearanceDataDto.setCommentCommunicationRecovery(itFormData.getCommunicationRecoveryComment());
            itClearanceDataDto.setItSubmitted(exitForm.getItFormStatus());
            clearanceFormData.setItClearanceDto(itClearanceDataDto);
        }
        if (!adminFormData.isEmpty() && (roleType.equalsIgnoreCase(ROLE_ADMIN) || (roleType.equalsIgnoreCase(ROLE_HR) || roleType.equalsIgnoreCase(ROLE_FINANCE) && exitForm.getAdminFormStatus()))) {
            AdminClearanceDto adminClearanceDataDTO = new AdminClearanceDto();
            AdminForm adminForm = adminFormData.get(0);
            List<RecoveryReimbursement> adminRecoveryData = RecoveryReimbursementLocalServiceUtil.findAllRecoveryFormByDepartment(adminForm.getId(), DEPARTMENT_ADMIN);
            for (RecoveryReimbursement recovery : adminRecoveryData) {
                RecoveryReimbursmentDto dto = new RecoveryReimbursmentDto();
                dto.setId(recovery.getId());
                dto.setRecoveryItem(recovery.getRecoveryItem());
                dto.setRecoveryStatus(recovery.getRecoveryStatus());
                dto.setRecoveryAmount(recovery.getRecoveryAmount());
                dto.setComment(recovery.getComment());
                dto.setApproved(recovery.getApproved());
                adminRecoveryFormData.add(dto);
                if (recovery.getRecoveryStatus() == 1) {
                    adminSelectedRecoveryFormData.add(dto);
                }
            }

            if (adminForm.getStationaryRecoveryAmtStatus() == 1) {
                RecoveryReimbursmentDto dto = new RecoveryReimbursmentDto();
                dto.setRecoveryItem(STATIONARY);
                dto.setNameSpace(STATIONARY_NAMESPACE);
                dto.setApproved(adminForm.getStationaryRecoveryApproved());
                dto.setComment(adminForm.getStationaryRecoveryComment());
                dto.setRecoveryAmount(adminForm.getStationaryRecoveryAmt());
                adminRecoveryFormData1.add(dto);
            }
            if (adminForm.getKeysRecoveryAmtStatus() == 1) {
                RecoveryReimbursmentDto dto = new RecoveryReimbursmentDto();
                dto.setRecoveryItem(KEYS);
                dto.setNameSpace(KEYS_NAMESPACE);
                dto.setRecoveryAmount(adminForm.getKeysRecoveryAmt());
                dto.setApproved(adminForm.getKeyRecoveryApproved());
                dto.setComment(adminForm.getKeyRecoveryComment());
                adminRecoveryFormData1.add(dto);
            }
            if (adminForm.getLibraryRecoveryAmtStatus() == 1) {
                RecoveryReimbursmentDto dto = new RecoveryReimbursmentDto();
                dto.setRecoveryItem(LIBRARY);
                dto.setNameSpace(LIBRARY_NAMESPACE);
                dto.setRecoveryAmount(adminForm.getLibraryRecoveryAmt());
                dto.setApproved(adminForm.getLibraryRecoveryApproved());
                dto.setComment(adminForm.getLibraryRecoveryComment());
                adminRecoveryFormData1.add(dto);
            }
            if (adminForm.getSportsRecoveryAmtStatus() == 1) {
                RecoveryReimbursmentDto dto = new RecoveryReimbursmentDto();
                dto.setRecoveryItem(SPORTS_EQUIPMENT);
                dto.setNameSpace(SPORTS_EQUIPMENT_NAMESPACE);
                dto.setRecoveryAmount(adminForm.getSportsRecoveryAmt());
                dto.setApproved(adminForm.getSportsRecoveryApproved());
                dto.setComment(adminForm.getSportsRecoveryComment());
                adminRecoveryFormData1.add(dto);
            }
            if (adminForm.getInfraRecoveryAmtStatus() == 1) {
                RecoveryReimbursmentDto dto = new RecoveryReimbursmentDto();
                dto.setRecoveryItem(INFRASTRUCTURE_ISSUED);
                dto.setNameSpace(INFRASTRUCTURE_ISSUED_NAMESPACE);
                dto.setRecoveryAmount(adminForm.getInfraRecoveryAmt());
                dto.setApproved(adminForm.getInfraRecoveryApproved());
                dto.setComment(adminForm.getInfraRecoveryComment());
                adminRecoveryFormData1.add(dto);
            }
            if (adminForm.getLunchRecoveryAmtStatus() == 1) {
                RecoveryReimbursmentDto dto = new RecoveryReimbursmentDto();
                dto.setRecoveryItem(LUNCH_DEDUCTION);
                dto.setNameSpace(LUNCH_DEDUCTION_NAMESPACE);
                dto.setRecoveryAmount(adminForm.getLunchRecoveryAmt());
                adminRecoveryFormData1.add(dto);
            }
            if (adminForm.getAccessCardRecoveryAmtStatus() == 1) {
                RecoveryReimbursmentDto dto = new RecoveryReimbursmentDto();
                dto.setRecoveryItem(ACCESS_CARD);
                dto.setNameSpace(ACCESS_CARD_NAMESPACE);
                dto.setRecoveryAmount(adminForm.getAccessCardRecoveryAmt());
                dto.setApproved(adminForm.getAccessCardRecoveryApproved());
                dto.setComment(adminForm.getAccessCardRecoveryComment());
                adminRecoveryFormData1.add(dto);
            }
            if (adminForm.getIdentityCardRecoveryStatus() == 1) {
                RecoveryReimbursmentDto dto = new RecoveryReimbursmentDto();
                dto.setRecoveryItem(IDENTITY_CARD);
                dto.setNameSpace(IDENTITY_CARD_NAMESPACE);
                dto.setRecoveryAmount(adminForm.getIdentityCardRecoveryAmt());
                dto.setApproved(adminForm.getIdentityCardRecoveryApproved());
                dto.setComment(adminForm.getIdentityCardRecoveryComment());
                adminRecoveryFormData1.add(dto);
            }
            adminClearanceDataDTO.setViewMode(!exitForm.getAdminFormStatus() && roleType.equalsIgnoreCase(ROLE_ADMIN));
            adminClearanceDataDTO.setStationaryRecoveryAmt(adminForm.getStationaryRecoveryAmt());
            adminClearanceDataDTO.setStationaryRecoveryAmtStatus(adminForm.getStationaryRecoveryAmtStatus());
            adminClearanceDataDTO.setApprovedStationaryRecovery(adminForm.getStationaryRecoveryApproved());
            adminClearanceDataDTO.setCommentStationaryRecovery(adminForm.getStationaryRecoveryComment());
            adminClearanceDataDTO.setKeysRecoveryAmt(adminForm.getKeysRecoveryAmt());
            adminClearanceDataDTO.setKeysRecoveryAmtStatus(adminForm.getKeysRecoveryAmtStatus());
            adminClearanceDataDTO.setApprovedKeysRecovery(adminForm.getKeyRecoveryApproved());
            adminClearanceDataDTO.setCommentKeysRecovery(adminForm.getStationaryRecoveryComment());
            adminClearanceDataDTO.setLibraryRecoveryAmt(adminForm.getLibraryRecoveryAmt());
            adminClearanceDataDTO.setLibraryRecoveryAmtStatus(adminForm.getLibraryRecoveryAmtStatus());
            adminClearanceDataDTO.setApprovedLibraryRecovery(adminForm.getLibraryRecoveryApproved());
            adminClearanceDataDTO.setCommentLibraryRecovery(adminForm.getLibraryRecoveryComment());
            adminClearanceDataDTO.setInfrastructureIssuedAmt(adminForm.getInfraRecoveryAmt());
            adminClearanceDataDTO.setInfrastructureIssuedAmtStatus(adminForm.getInfraRecoveryAmtStatus());
            adminClearanceDataDTO.setApprovedInfrastructureIssuedRecovery(adminForm.getInfraRecoveryApproved());
            adminClearanceDataDTO.setCommentInfrastructureIssuedRecovery(adminForm.getInfraRecoveryComment());
            adminClearanceDataDTO.setLunchDeductionAmt(adminForm.getLunchRecoveryAmt());
            adminClearanceDataDTO.setLunchDeductionAmtStatus(adminForm.getLunchRecoveryAmtStatus());
            adminClearanceDataDTO.setApprovedLunchDeductionRecovery(adminForm.getLunchRecoveryApproved());
            adminClearanceDataDTO.setCommentLunchDeductionRecovery(adminForm.getLunchRecoveryComment());
            adminClearanceDataDTO.setSportsRecoveryAmt(adminForm.getSportsRecoveryAmt());
            adminClearanceDataDTO.setSportsRecoveryAmtStatus(adminForm.getSportsRecoveryAmtStatus());
            adminClearanceDataDTO.setApprovedSportsRecovery(adminForm.getSportsRecoveryApproved());
            adminClearanceDataDTO.setCommentSportsRecovery(adminForm.getSportsRecoveryComment());
            adminClearanceDataDTO.setAccessCardRecoveryAmt(adminForm.getAccessCardRecoveryAmt());
            adminClearanceDataDTO.setAccessCardRecoveryAmtStatus(adminForm.getAccessCardRecoveryAmtStatus());
            adminClearanceDataDTO.setApprovedAccessCardRecovery(adminForm.getAccessCardRecoveryApproved());
            adminClearanceDataDTO.setCommentAccessCardRecovery(adminForm.getAccessCardRecoveryComment());
            adminClearanceDataDTO.setIdentityCardAmt(adminForm.getIdentityCardRecoveryAmt());
            adminClearanceDataDTO.setIdentityCardStatus(adminForm.getIdentityCardRecoveryStatus());
            adminClearanceDataDTO.setApprovedIdentityCardRecovery(adminForm.getIdentityCardRecoveryApproved());
            adminClearanceDataDTO.setCommentIdentityCardRecovery(adminForm.getIdentityCardRecoveryComment());
            adminClearanceDataDTO.setAdminSubmitted(exitForm.getAdminFormStatus());
            clearanceFormData.setAdminClearanceDto(adminClearanceDataDTO);
        }

        if (!hrFormData.isEmpty() && (roleType.equalsIgnoreCase(ROLE_HR) || (roleType.equalsIgnoreCase(ROLE_FINANCE) && exitForm.getHrFormStatus()))) {
            HrClearanceDto hrClearanceDataDTO = new HrClearanceDto();
            HrForm hrForm = hrFormData.get(0);
            List<RecoveryReimbursement> hrRecoveryData = RecoveryReimbursementLocalServiceUtil.findAllRecoveryFormByDepartment(hrForm.getId(), DEPARTMENT_HR);
            for (RecoveryReimbursement recovery : hrRecoveryData) {
                RecoveryReimbursmentDto dto = new RecoveryReimbursmentDto();
                dto.setRecoveryItem(recovery.getRecoveryItem());
                dto.setRecoveryStatus(recovery.getRecoveryStatus());
                dto.setRecoveryAmount(recovery.getRecoveryAmount());
                hrRecoveryFormData.add(dto);
            }

            hrClearanceDataDTO.setViewMode(!exitForm.getHrFormStatus() && roleType.equalsIgnoreCase(ROLE_HR));
            hrClearanceDataDTO.setFoodOption(hrForm.getFoodOption());
            hrClearanceDataDTO.setFoodOptionRemark(hrForm.getFoodOptionRemark());
            hrClearanceDataDTO.setInductionFeedbackStatus(hrForm.getInductionFeedbackStatus());
            hrClearanceDataDTO.setInductionFeedbackRemark(hrForm.getInductionFeedbackRemark());
            hrClearanceDataDTO.setInductionQuizStatus(hrForm.getInductionQuizStatus());
            hrClearanceDataDTO.setInductionQuizRemark(hrForm.getInductionQuizRemark());
            hrClearanceDataDTO.setTrainingFeedbackStatus(hrForm.getTrainingFeedbackStatus());
            hrClearanceDataDTO.setTrainingFeedbackRemark(hrForm.getTrainingFeedbackRemark());
            hrClearanceDataDTO.setExitInterviewStatus(hrForm.getExitInterviewStatus());
            hrClearanceDataDTO.setExitInterviewRemark(hrForm.getExitInterviewRemark());
            hrClearanceDataDTO.setEmployeeDirectoryStatus(hrForm.getEmployeeDirectoryStatus());
            hrClearanceDataDTO.setEmployeeDirectoryRemark(hrForm.getEmployeeDirectoryRemark());
            hrClearanceDataDTO.setLmsStatus(hrForm.getLmsStatus());
            hrClearanceDataDTO.setLmsRemark(hrForm.getLmsRemark());
            hrClearanceDataDTO.setVantageCircleStatus(hrForm.getVantageCircleStatus());
            hrClearanceDataDTO.setVantageCircleRemark(hrForm.getVantageCircleRemark());
            hrClearanceDataDTO.setBirthdaySynergyStatus(hrForm.getBirthdaySynergyStatus());
            hrClearanceDataDTO.setBirthdaySynergyRemark(hrForm.getBirthdaySynergyRemark());
            hrClearanceDataDTO.setExperienceLetterStatus(hrForm.getExperienceLetterStatus());
            hrClearanceDataDTO.setExperienceLetterRemark(hrForm.getExperienceLetterRemark());
            hrClearanceDataDTO.setNdaFormStatus(hrForm.getNdaFormStatus());
            hrClearanceDataDTO.setNdaFormRemark(hrForm.getNdaFormRemark());
            hrClearanceDataDTO.setSeparationDocumentStatus(hrForm.getSeparationDocumentStatus());
            hrClearanceDataDTO.setSeparationDocumentRemark(hrForm.getSeparationDocumentRemark());
            hrClearanceDataDTO.setTrainingAgreementAmt(hrForm.getTrainingAgreementAmt());
            hrClearanceDataDTO.setTrainingAgreementStatus(hrForm.getTrainingAgreementStatus());
            hrClearanceDataDTO.setRecoverableBonusAmt(hrForm.getRecoverableBonusAmt());
            hrClearanceDataDTO.setRecoverableBonusStatus(hrForm.getRecoverableBonusStatus());
            hrClearanceDataDTO.setHrRemarks(hrForm.getHrRemark());
            hrClearanceDataDTO.setNoticePeriodRecoveryAmt(hrForm.getNoticePeriodRecoveryAmt());
            hrClearanceDataDTO.setNoticePeriodRecoveryStatus(hrForm.getNoticePeriodRecoveryStatus());
            hrClearanceDataDTO.setLeavesMonth1(hrForm.getLeavesMonth1());
            hrClearanceDataDTO.setLeavesMonth2(hrForm.getLeavesMonth2());
            hrClearanceDataDTO.setLeavesMonth3(hrForm.getLeavesMonth3());
            hrClearanceDataDTO.setLeaveDaysMonth1(hrForm.getLeaveDaysMonth1());
            hrClearanceDataDTO.setLeaveDaysMonth2(hrForm.getLeaveDaysMonth2());
            hrClearanceDataDTO.setLeaveDaysMonth3(hrForm.getLeaveDaysMonth3());
            hrClearanceDataDTO.setLeaveDateMonth1(hrForm.getLeaveDateMonth1());
            hrClearanceDataDTO.setLeaveDateMonth2(hrForm.getLeaveDateMonth2());
            hrClearanceDataDTO.setLeaveDateMonth3(hrForm.getLeaveDateMonth3());
            hrClearanceDataDTO.setLopMonth1(hrForm.getLopMonth1());
            hrClearanceDataDTO.setLopMonth2(hrForm.getLopMonth2());
            hrClearanceDataDTO.setLopMonth3(hrForm.getLopMonth3());
            hrClearanceDataDTO.setLopDaysMonth1(hrForm.getLopDaysMonth1());
            hrClearanceDataDTO.setLopDaysMonth2(hrForm.getLopDaysMonth2());
            hrClearanceDataDTO.setLopDaysMonth3(hrForm.getLopDaysMonth3());
            hrClearanceDataDTO.setLopDateMonth1(hrForm.getLopDateMonth1());
            hrClearanceDataDTO.setLopDateMonth2(hrForm.getLopDateMonth2());
            hrClearanceDataDTO.setLopDateMonth3(hrForm.getLopDateMonth3());
            hrClearanceDataDTO.setEarnedLeaveBalance(hrForm.getEarnedLeaveBalance());
            hrClearanceDataDTO.setHrSubmitted(exitForm.getHrFormStatus());
            clearanceFormData.setHrClearanceDto(hrClearanceDataDTO);
        }

        if (!financeFormData.isEmpty() && (roleType.equalsIgnoreCase(ROLE_FINANCE) || (roleType.equalsIgnoreCase(ROLE_HR) && exitForm.getFinanceFormStatus()))) {
            FinanceForm financeForm = financeFormData.get(0);
            List<RecoveryReimbursement> recoveryReimbursementForm = RecoveryReimbursementLocalServiceUtil.findRecoveryReimbursementFormByDepartmentFormId(financeForm.getId(), DEPARTMENT_FINANCE);
            for (RecoveryReimbursement recoveryForm : recoveryReimbursementForm) {
                RecoveryReimbursmentDto dataDTO = new RecoveryReimbursmentDto();
                if (recoveryForm.getRecoveryType()) {
                    dataDTO.setRecoveryItem(recoveryForm.getRecoveryItem());
                    dataDTO.setRecoveryStatus(recoveryForm.getRecoveryStatus());
                    dataDTO.setRecoveryAmount(recoveryForm.getRecoveryAmount());
                    dataDTO.setId(recoveryForm.getId());
                    dataDTO.setComment(recoveryForm.getComment());
                    dataDTO.setApproved(recoveryForm.getApproved());
                    recoveryFormData.add(dataDTO);
                    if (recoveryForm.getRecoveryStatus() == 1) {
                        selectedRecoveryFormData.add(dataDTO);
                    }
                }
            }
            for (RecoveryReimbursement reimbursmentForm : recoveryReimbursementForm) {
                RecoveryReimbursmentDto dataDTO = new RecoveryReimbursmentDto();
                if (!reimbursmentForm.getRecoveryType()) {
                    dataDTO.setReimbursementItem(reimbursmentForm.getReimbursementItem());
                    dataDTO.setReimbursementStatus(reimbursmentForm.getReimbursementStatus());
                    dataDTO.setReimbursementAmount(reimbursmentForm.getReimbursementAmount());
                    reimbursmentFormData.add(dataDTO);
                }
            }
            if (financeForm.getTravelRecoveryStatus() == 1) {
                RecoveryReimbursmentDto dto = new RecoveryReimbursmentDto();
                dto.setRecoveryItem(RECOVERY_FOR_TRAVEL);
                dto.setNameSpace(RECOVERY_FOR_TRAVEL_NAMESPACE);
                dto.setRecoveryAmount(financeForm.getTravelRecoveryAmt());
                dto.setApproved(financeForm.getTravelRecoveryApproved());
                dto.setComment(financeForm.getTravelRecoveryComment());
                recoveryFormData1.add(dto);
            }
            if (financeForm.getHotelRecoveryStatus() == 1) {
                RecoveryReimbursmentDto dto = new RecoveryReimbursmentDto();
                dto.setRecoveryItem(RECOVERY_FOR_HOTEL);
                dto.setNameSpace(RECOVERY_FOR_HOTEL_NAMESPACE);
                dto.setRecoveryAmount(financeForm.getHotelRecoveryAmt());
                dto.setApproved(financeForm.getHotelRecoveryApproved());
                dto.setComment(financeForm.getHotelRecoveryComment());
                recoveryFormData1.add(dto);
            }
            if (financeForm.getInternationalRecoveryStatus() == 1) {
                RecoveryReimbursmentDto dto = new RecoveryReimbursmentDto();
                dto.setRecoveryItem(RECOVERY_FOR_INTERNATIONAL_TRAVEL);
                dto.setNameSpace(RECOVERY_FOR_INTERNATIONAL_TRAVEL_NAMESPACE);
                dto.setRecoveryAmount(financeForm.getInternationalRecoveryAmt());
                dto.setApproved(financeForm.getInternationalRecoveryApproved());
                dto.setComment(financeForm.getInternationalRecoveryComment());
                recoveryFormData1.add(dto);
            }
            if (financeForm.getEducationRecoveryStatus() == 1) {
                RecoveryReimbursmentDto dto = new RecoveryReimbursmentDto();
                dto.setRecoveryItem(RECOVERY_FOR_EDUCATION);
                dto.setNameSpace(RECOVERY_FOR_EDUCATION_NAMESPACE);
                dto.setRecoveryAmount(financeForm.getEducationRecoveryAmt());
                dto.setApproved(financeForm.getEducationRecoveryApproved());
                dto.setComment(financeForm.getEducationRecoveryComment());
                recoveryFormData1.add(dto);
            }
            if (financeForm.getJoiningBonusRecoveryStatus() == 1) {
                RecoveryReimbursmentDto dto = new RecoveryReimbursmentDto();
                dto.setRecoveryItem(RECOVERY_FOR_BONUS);
                dto.setNameSpace(RECOVERY_FOR_BONUS_NAMESPACE);
                dto.setRecoveryAmount(financeForm.getJoiningBonusRecoveryAmt());
                dto.setApproved(financeForm.getJoiningRecoveryApproved());
                dto.setComment(financeForm.getJoiningRecoveryComment());
                recoveryFormData1.add(dto);
            }
            if (financeForm.getNoticePeriodRecoveryStatus() == 1) {
                RecoveryReimbursmentDto dto = new RecoveryReimbursmentDto();
                dto.setRecoveryItem(RECOVERY_FOR_NOTICE_PERIOD);
                dto.setNameSpace(RECOVERY_FOR_NOTICE_PERIOD_NAMESPACE);
                dto.setRecoveryAmount(financeForm.getNoticePeriodRecoveryAmt());
                dto.setApproved(financeForm.getNoticePeriodRecoveryApproved());
                dto.setComment(financeForm.getNoticePeriodRecoveryComment());
                recoveryFormData1.add(dto);
            }

            FinanceClearanceDto financeClearanceDataDTO = new FinanceClearanceDto();
            financeClearanceDataDTO.setViewMode(roleType.equalsIgnoreCase(ROLE_FINANCE) && !exitForm.getFinanceFormStatus());
            financeClearanceDataDTO.setLastSalaryPaidDays(financeForm.getLastSalaryPaidDays());
            financeClearanceDataDTO.setLastSalaryPaidMonth(financeForm.getLastSalaryPaidMonth());
            financeClearanceDataDTO.setLastSalaryPaidYear(financeForm.getLastSalaryPaidYear());
            financeClearanceDataDTO.setTaxProofStatus(financeForm.getTaxProofStatus());
            financeClearanceDataDTO.setTaxProofRemark(financeForm.getTaxProofRemark());
            financeClearanceDataDTO.setFoodReimbursementStatus(financeForm.getFoodReimbursementStatus());
            financeClearanceDataDTO.setFoodReimbursementAmt(financeForm.getFoodReimbursementAmt());
            financeClearanceDataDTO.setTravelRecoveryStatus(financeForm.getTravelRecoveryStatus());
            financeClearanceDataDTO.setTravelRecoveryAmt(financeForm.getTravelRecoveryAmt());
            financeClearanceDataDTO.setApprovedTravelRecovery(financeForm.getTravelRecoveryApproved());
            financeClearanceDataDTO.setCommentTravelRecovery(financeForm.getTravelRecoveryComment());
            financeClearanceDataDTO.setHotelRecoveryStatus(financeForm.getHotelRecoveryStatus());
            financeClearanceDataDTO.setHotelRecoveryAmt(financeForm.getHotelRecoveryAmt());
            financeClearanceDataDTO.setApprovedHotelRecovery(financeForm.getHotelRecoveryApproved());
            financeClearanceDataDTO.setCommentHotelRecovery(financeForm.getHotelRecoveryComment());
            financeClearanceDataDTO.setInternationalRecoveryStatus(financeForm.getInternationalRecoveryStatus());
            financeClearanceDataDTO.setInternationalRecoveryAmt(financeForm.getInternationalRecoveryAmt());
            financeClearanceDataDTO.setApprovedInternationalRecovery(financeForm.getInternationalRecoveryApproved());
            financeClearanceDataDTO.setCommentInternationalRecovery(financeForm.getInternationalRecoveryComment());
            financeClearanceDataDTO.setEducationRecoveryStatus(financeForm.getEducationRecoveryStatus());
            financeClearanceDataDTO.setEducationRecoveryAmt(financeForm.getEducationRecoveryAmt());
            financeClearanceDataDTO.setApprovedEducationRecovery(financeForm.getEducationRecoveryApproved());
            financeClearanceDataDTO.setCommentEducationRecovery(financeForm.getEducationRecoveryComment());
            financeClearanceDataDTO.setJoiningBonusRecoveryStatus(financeForm.getJoiningBonusRecoveryStatus());
            financeClearanceDataDTO.setJoiningBonusRecoveryAmt(financeForm.getJoiningBonusRecoveryAmt());
            financeClearanceDataDTO.setApprovedJoiningRecovery(financeForm.getJoiningRecoveryApproved());
            financeClearanceDataDTO.setCommentJoiningRecovery(financeForm.getJoiningRecoveryComment());
            financeClearanceDataDTO.setNoticePeriodRecoveryStatus(financeForm.getNoticePeriodRecoveryStatus());
            financeClearanceDataDTO.setNoticePeriodRecoveryAmt(financeForm.getNoticePeriodRecoveryAmt());
            financeClearanceDataDTO.setApprovedNoticePeriodRecovery(financeForm.getNoticePeriodRecoveryApproved());
            financeClearanceDataDTO.setCommentNoticePeriodRecovery(financeForm.getNoticePeriodRecoveryComment());
            financeClearanceDataDTO.setFinanceSubmitted(exitForm.getFinanceFormStatus());
            clearanceFormData.setFinanceClearanceDto(financeClearanceDataDTO);
        }

        request.setAttribute("recoveryFormData", recoveryFormData);
        request.setAttribute("recoveryFormData1", recoveryFormData1);
        request.setAttribute("selectedRecoveryFormData", selectedRecoveryFormData);
        request.setAttribute("reimbursementFormData", reimbursmentFormData);
        request.setAttribute("hrRecoveryFormData", hrRecoveryFormData);
        request.setAttribute("adminRecoveryFormData", adminRecoveryFormData);
        request.setAttribute("adminSelectedRecoveryFormData", adminSelectedRecoveryFormData);
        request.setAttribute("adminRecoveryFormData1", adminRecoveryFormData1);
        request.setAttribute("itRecoveryFormData", itRecoveryFormData);
        request.setAttribute("itRecoveryFormData1", itRecoveryFormData1);
        request.setAttribute("itSelectedRecoveryFormData", itSelectedRecoveryFormData);
        request.setAttribute("clearanceFormData", clearanceFormData);
        request.setAttribute("empCoreDetails", dataDto);
        response.getRenderParameters().setValue(MVCPATH, "/jsp/clearanceForm.jsp");
    }

    @Override
    public void exitQuestionnaireFormWorkflow(ActionRequest request, ActionResponse response) {
        long resignationFormId = ParamUtil.getLong(request, RESIGNATION_ID);
        Resignation employeeResignData = ResignationLocalServiceUtil.fetchResignation(resignationFormId);
        EmployeeDto dataDto = new EmployeeDto();
        QuestionnaireFormDto questionnaireFormDataDTO = new QuestionnaireFormDto();
        questionnaireFormDataDTO.setSubmitted(false);
        List<QuestionnaireForm> questionnaireForm = QuestionnaireFormLocalServiceUtil.findQuestionnaireFormByResignationId(resignationFormId);
        if (!questionnaireForm.isEmpty()) {
            QuestionnaireForm questionnaireFormData = questionnaireForm.get(0);
            questionnaireFormDataDTO.setResignationId(questionnaireFormData.getResignationId());
            questionnaireFormDataDTO.setWorkExperience(questionnaireFormData.getWorkExperience());
            questionnaireFormDataDTO.setCommunicationWithEmployees(questionnaireFormData.getCommunicationWithEmployees());
            questionnaireFormDataDTO.setTrainingOpportunity(questionnaireFormData.getTrainingOpportunity());
            questionnaireFormDataDTO.setDealingWithStaff(questionnaireFormData.getDealingWithStaff());
            questionnaireFormDataDTO.setSatisfactionLevel(questionnaireFormData.getSatisfactionLevel());
            questionnaireFormDataDTO.setReasonForLeaving(questionnaireFormData.getReasonForLeaving());
            questionnaireFormDataDTO.setReasonForLeavingDescribe(questionnaireFormData.getReasonForLeavingDescribe());
            questionnaireFormDataDTO.setReasonForJoining(questionnaireFormData.getReasonForJoining());
            questionnaireFormDataDTO.setWorkAgain(questionnaireFormData.getWorkAgain());
            questionnaireFormDataDTO.setNotWorkAgain(questionnaireFormData.getNotWorkAgain());
            questionnaireFormDataDTO.setRecommendTrantor(questionnaireFormData.getRecommendTrantor());
            questionnaireFormDataDTO.setNotRecommendTrantor(questionnaireFormData.getNotRecommendTrantor());
            questionnaireFormDataDTO.setCompanyName(questionnaireFormData.getCompanyName());
            questionnaireFormDataDTO.setCompanyDetails(questionnaireFormData.getCompanyDetails());
            questionnaireFormDataDTO.setDesignation(questionnaireFormData.getDesignation());
            questionnaireFormDataDTO.setLocation(questionnaireFormData.getLocation());
            questionnaireFormDataDTO.setSalaryHike(questionnaireFormData.getSalaryHike());
            questionnaireFormDataDTO.setFeedback(questionnaireFormData.getFeedback());
            questionnaireFormDataDTO.setHrRemarks(questionnaireFormData.getHrRemark());
            questionnaireFormDataDTO.setSubmittedDate(convertDateToLocalDateTime(questionnaireFormData.getSubmittedDate()).format(FORMATTER_YYYY_MM_DD));
            questionnaireFormDataDTO.setSubmitted(true);
        }
        Employee employeeData = EmployeeLocalServiceUtil.fetchEmployee(employeeResignData.getEcode());
        if (null != employeeData) {
            Employee managerData = EmployeeLocalServiceUtil.fetchEmployee(employeeData.getManager());
            dataDto.setEmployeeCode(employeeData.getEcode());
            dataDto.setEmployeeName(employeeData.getName());
            dataDto.setAccount(employeeData.getAccount());
            dataDto.setManagerName(null != managerData ? managerData.getName() : BLANK);
        }
        dataDto.setEmpMobNo(employeeResignData.getMobile());
        dataDto.setPostalAddress(employeeResignData.getPostalAddress());
        dataDto.setCityName(employeeResignData.getCityName());
        dataDto.setLastWorkingDate(convertDateToLocalDateTime(employeeResignData.getLastWorkingDate()).format(FORMATTER_YYYY_MM_DD));

        request.setAttribute("questionnaireFormData", questionnaireFormDataDTO);
        request.setAttribute("empCoreDetails", dataDto);
        response.getRenderParameters().setValue(MVCPATH, "/jsp/questionnaireForm.jsp");
    }

    @Override
    public void submitClearance(ActionRequest request) throws IOException {
        long resignationId = ParamUtil.getLong(request, RESIGNATION_ID);
        String roleType = ParamUtil.getString(request, ROLE_TYPE);
        Date now = getIstDate();
        Resignation employeeResignData = ResignationLocalServiceUtil.fetchResignation(resignationId);
        Employee employeeData = EmployeeLocalServiceUtil.fetchEmployee(employeeResignData.getEcode());
        List<ExitForm> exitFormData = ExitFormLocalServiceUtil.findExitFormByResignationId(resignationId);
        long exitFormId = exitFormData.get(0).getId();
        List<ManagerForm> managerformData = ManagerFormLocalServiceUtil.findManagerFormByExitId(exitFormId);
        List<HrForm> hrFormData = HrFormLocalServiceUtil.findHrFormByExitId(exitFormId);
        List<ItForm> itFormData = ItFormLocalServiceUtil.findItFormByExitId(exitFormId);
        List<AdminForm> adminFormData = AdminFormLocalServiceUtil.findAdminFormByExitId(exitFormId);
        List<FinanceForm> financeFormData = FinanceFormLocalServiceUtil.findFinanceFormByExitId(exitFormId);
        if (!financeFormData.isEmpty() && roleType.equalsIgnoreCase(ROLE_FINANCE)) {
            FinanceForm financeForm = financeFormData.get(0);
            ExitForm exitForm = exitFormData.get(0);
            exitForm.setFinanceFormStatus(true);
            financeForm.setLastSalaryPaidDays(ParamUtil.getLong(request, "lastSalaryPaidDays"));
            financeForm.setLastSalaryPaidMonth(ParamUtil.getString(request, "lastSalaryPaidMonth"));
            financeForm.setLastSalaryPaidYear(ParamUtil.getString(request, "lastSalaryPaidYear"));
            financeForm.setTaxProofStatus(ParamUtil.getInteger(request, "taxProofStatus"));
            financeForm.setTaxProofRemark(ParamUtil.getString(request, "taxProofRemark"));
            financeForm.setFoodReimbursementStatus(ParamUtil.getInteger(request, "foodReimbursementStatus"));
            financeForm.setFoodReimbursementAmt(ParamUtil.getString(request, "foodReimbursementAmt"));
            int travelRecoveryStatus = ParamUtil.getInteger(request, "travelRecoveryStatus");
            financeForm.setTravelRecoveryStatus(travelRecoveryStatus);
            financeForm.setTravelRecoveryAmt(ParamUtil.getString(request, "travelRecoveryAmt"));
            financeForm.setTravelRecoveryApproved(travelRecoveryStatus == 1);
            int hotelRecoveryStatus = ParamUtil.getInteger(request, "hotelRecoveryStatus");
            financeForm.setHotelRecoveryStatus(hotelRecoveryStatus);
            financeForm.setHotelRecoveryAmt(ParamUtil.getString(request, "hotelRecoveryAmt"));
            financeForm.setHotelRecoveryApproved(hotelRecoveryStatus == 1);
            int internationalRecoveryStatus = ParamUtil.getInteger(request, "internationalRecoveryStatus");
            financeForm.setInternationalRecoveryStatus(internationalRecoveryStatus);
            financeForm.setInternationalRecoveryAmt(ParamUtil.getString(request, "internationalRecoveryAmt"));
            financeForm.setInternationalRecoveryApproved(internationalRecoveryStatus == 1);
            int educationRecoveryStatus = ParamUtil.getInteger(request, "educationRecoveryStatus");
            financeForm.setEducationRecoveryStatus(educationRecoveryStatus);
            financeForm.setEducationRecoveryAmt(ParamUtil.getString(request, "educationRecoveryAmt"));
            financeForm.setEducationRecoveryApproved(educationRecoveryStatus == 1);
            int joiningBonusRecoveryStatus = ParamUtil.getInteger(request, "joiningBonusRecoveryStatus");
            financeForm.setJoiningBonusRecoveryStatus(joiningBonusRecoveryStatus);
            financeForm.setJoiningBonusRecoveryAmt(ParamUtil.getString(request, "joiningBonusRecoveryAmt"));
            financeForm.setJoiningRecoveryApproved(joiningBonusRecoveryStatus == 1);
            int noticePeriodRecoveryStatusFinance = ParamUtil.getInteger(request, "noticePeriodRecoveryStatusFinance");
            financeForm.setNoticePeriodRecoveryStatus(noticePeriodRecoveryStatusFinance);
            financeForm.setNoticePeriodRecoveryAmt(ParamUtil.getString(request, "noticePeriodRecoveryAmtFinance"));
            financeForm.setNoticePeriodRecoveryApproved(noticePeriodRecoveryStatusFinance == 1);
            financeForm.setUpdatedDate(now);
            ExitFormLocalServiceUtil.updateExitForm(exitForm);
            FinanceFormLocalServiceUtil.updateFinanceForm(financeForm);
            int totalRows = ParamUtil.getInteger(request, TOTAL_ROWS);
            int reimbursementTotalRows = ParamUtil.getInteger(request, REIMBURSEMENT_TOTAL_ROWS);
            List<RecoveryReimbursement> recoveryReimbursementForm = RecoveryReimbursementLocalServiceUtil.findRecoveryReimbursementFormByDepartmentFormId(financeForm.getId(), DEPARTMENT_FINANCE);
            if (!recoveryReimbursementForm.isEmpty()) {
                for (RecoveryReimbursement recoveryForm : recoveryReimbursementForm) {
                    RecoveryReimbursementLocalServiceUtil.deleteRecoveryReimbursement(recoveryForm);
                }
            }
            if (totalRows > 6) {
                for (int i = 6; i < totalRows; i++) {
                    String recoveryItem = ParamUtil.getString(request, "recoveryItem" + i);
                    int recoveryStatus = ParamUtil.getInteger(request, "recoveryStatus" + i);
                    String recoveryAmount = ParamUtil.getString(request, "recoveryAmount" + i);
                    if (!recoveryItem.isEmpty() || recoveryStatus != 0 || !recoveryAmount.isEmpty()) {
                        RecoveryReimbursement recoveryForm = RecoveryReimbursementLocalServiceUtil.createRecoveryReimbursement(CounterLocalServiceUtil.increment());
                        recoveryForm.setDepartmentFormId(financeForm.getId());
                        recoveryForm.setDepartment(4);
                        recoveryForm.setRecoveryItem(recoveryItem);
                        recoveryForm.setRecoveryStatus(recoveryStatus);
                        recoveryForm.setRecoveryAmount(recoveryAmount);
                        recoveryForm.setRecoveryType(true);
                        recoveryForm.setApproved(recoveryStatus == 1);
                        RecoveryReimbursementLocalServiceUtil.addRecoveryReimbursement(recoveryForm);
                    }
                }
            }
            if (reimbursementTotalRows > 1) {
                for (int i = 1; i < reimbursementTotalRows; i++) {
                    String reimbursementItem = ParamUtil.getString(request, "reimbursementItem" + i);
                    int reimbursementStatus = ParamUtil.getInteger(request, "reimbursementStatus" + i);
                    String reimbursementAmount = ParamUtil.getString(request, "reimbursementAmount" + i);
                    if (!reimbursementItem.isEmpty() || reimbursementStatus != 0 || !reimbursementAmount.isEmpty()) {
                        RecoveryReimbursement reimbursementForm = RecoveryReimbursementLocalServiceUtil.createRecoveryReimbursement(CounterLocalServiceUtil.increment());
                        reimbursementForm.setDepartmentFormId(financeForm.getId());
                        reimbursementForm.setDepartment(4);
                        reimbursementForm.setReimbursementItem(reimbursementItem);
                        reimbursementForm.setReimbursementStatus(reimbursementStatus);
                        reimbursementForm.setReimbursementAmount(reimbursementAmount);
                        reimbursementForm.setRecoveryType(false);
                        RecoveryReimbursementLocalServiceUtil.addRecoveryReimbursement(reimbursementForm);
                    }
                }
            }
            if (exitForm.getManagerFormStatus() && exitForm.getItFormStatus() && exitForm.getAdminFormStatus() && exitForm.getFinanceFormStatus()) {
                Email hrEmail = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
                hrEmail.setToAddress(DL_HR);
                hrEmail.setSubject(MessageFormat.format(HR_CLEARANCE_EMAIL_SUBJECT, employeeData.getEcode(), employeeData.getName()));
                hrEmail.setBody(MessageFormat.format(HR_CLEARANCE_EMAIL_BODY, employeeData.getName(), employeeData.getEcode(), getPortalUrl() + URL_EXIT_ADMIN));
                hrEmail.setCreatedDate(now);
                hrEmail.setModule(MODULE_EXIT);
                hrEmail.setScheduled(false);
                hrEmail.setSent(false);
                EmailLocalServiceUtil.addEmail(hrEmail);
            }
        }
        if (!managerformData.isEmpty() && roleType.equalsIgnoreCase(ROLE_MANAGER)) {
            ManagerForm managerForm = managerformData.get(0);
            ExitForm exitForm = exitFormData.get(0);
            managerForm.setTicketNo(ParamUtil.getInteger(request, "ticketNo"));
            managerForm.setTicketNoRemark(ParamUtil.getString(request, "ticketNoRemark"));
            managerForm.setSeparationDocument(ParamUtil.getInteger(request, "separationDocumentManager"));
            managerForm.setSeparationDocumentRemark(ParamUtil.getString(request, "separationDocumentRemarkManager"));
            managerForm.setUpdatedDate(now);
            exitForm.setManagerFormStatus(true);
            ManagerFormLocalServiceUtil.updateManagerForm(managerForm);
            ExitFormLocalServiceUtil.updateExitForm(exitForm);

            Email itEmail = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
            itEmail.setToAddress(DL_IT);
            itEmail.setCcAddress(DL_HR);
            itEmail.setSubject(MessageFormat.format(IT_CLEARANCE_EMAIL_SUBJECT, employeeData.getEcode(), employeeData.getName()));
            itEmail.setBody(MessageFormat.format(IT_CLEARANCE_EMAIL_BODY, employeeData.getName(), employeeData.getEcode(), getPortalUrl() + URL_EXIT_ADMIN));
            itEmail.setCreatedDate(now);
            itEmail.setModule(MODULE_EXIT);
            itEmail.setScheduled(false);
            itEmail.setSent(false);
            EmailLocalServiceUtil.addEmail(itEmail);
            if (exitForm.getManagerFormStatus() && exitForm.getItFormStatus() && exitForm.getAdminFormStatus() && exitForm.getFinanceFormStatus()) {
                Email hrEmail = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
                hrEmail.setToAddress(DL_HR);
                hrEmail.setSubject(MessageFormat.format(HR_CLEARANCE_EMAIL_SUBJECT, employeeData.getEcode(), employeeData.getName()));
                hrEmail.setBody(MessageFormat.format(HR_CLEARANCE_EMAIL_BODY, employeeData.getName(), employeeData.getEcode(), getPortalUrl() + URL_EXIT_ADMIN));
                hrEmail.setCreatedDate(now);
                hrEmail.setModule(MODULE_EXIT);
                hrEmail.setScheduled(false);
                hrEmail.setSent(false);
                EmailLocalServiceUtil.addEmail(hrEmail);
            }
        }

        if (!hrFormData.isEmpty() && roleType.equalsIgnoreCase(ROLE_HR) && !financeFormData.isEmpty() && !itFormData.isEmpty() && !adminFormData.isEmpty()) {
            ExitForm exitForm = exitFormData.get(0);
            HrForm hrForm = hrFormData.get(0);
            AdminForm adminData = adminFormData.get(0);
            List<RecoveryReimbursement> adminRecoveryData = RecoveryReimbursementLocalServiceUtil.findSelectedRecoveryFormByDepartment(adminData.getId(), DEPARTMENT_ADMIN);
            for (RecoveryReimbursement recovery : adminRecoveryData) {
                String comment = ParamUtil.getString(request, COMMENT + recovery.getId());
                List<RecoveryReimbursement> adminSelectedRecoveryData = adminRecoveryData.stream().filter(s -> s.getId() == recovery.getId()).collect(Collectors.toList());
                if (!adminSelectedRecoveryData.isEmpty()) {
                    RecoveryReimbursement recoveryData = adminSelectedRecoveryData.get(0);
                    recoveryData.setComment(comment);
                    recoveryData.setApproved(ParamUtil.getBoolean(request, APPROVE + recovery.getId()));
                    RecoveryReimbursementLocalServiceUtil.updateRecoveryReimbursement(recoveryData);
                }
            }
            if (adminData.getStationaryRecoveryAmtStatus() == 1) {
                adminData.setStationaryRecoveryApproved(ParamUtil.getBoolean(request, "approvedStationary"));
                adminData.setStationaryRecoveryComment(ParamUtil.getString(request, "commentStationary"));
            }
            if (adminData.getKeysRecoveryAmtStatus() == 1) {
                adminData.setKeyRecoveryApproved(ParamUtil.getBoolean(request, "approvedKeys"));
                adminData.setKeyRecoveryComment(ParamUtil.getString(request, "commentKeys"));
            }
            if (adminData.getLibraryRecoveryAmtStatus() == 1) {
                adminData.setLibraryRecoveryApproved(ParamUtil.getBoolean(request, "approvedLibrary"));
                adminData.setLibraryRecoveryComment(ParamUtil.getString(request, "commentLibrary"));
            }
            if (adminData.getSportsRecoveryAmtStatus() == 1) {
                adminData.setSportsRecoveryApproved(ParamUtil.getBoolean(request, "approvedSportsEquipmentIssued"));
                adminData.setSportsRecoveryComment(ParamUtil.getString(request, "commentSportsEquipmentIssued"));
            }
            if (adminData.getInfraRecoveryAmtStatus() == 1) {
                adminData.setInfraRecoveryApproved(ParamUtil.getBoolean(request, "approvedInfrastructureIssued"));
                adminData.setInfraRecoveryComment(ParamUtil.getString(request, "commentInfrastructureIssued"));
            }
            if (adminData.getLunchRecoveryAmtStatus() == 1) {
                adminData.setLunchRecoveryApproved(ParamUtil.getBoolean(request, "approvedLunchDeduction"));
                adminData.setLunchRecoveryComment(ParamUtil.getString(request, "commentLunchDeduction"));
            }
            if (adminData.getAccessCardRecoveryAmtStatus() == 1) {
                adminData.setAccessCardRecoveryApproved(ParamUtil.getBoolean(request, "approvedAccessCard"));
                adminData.setAccessCardRecoveryComment(ParamUtil.getString(request, "commentAccessCard"));
            }
            if (adminData.getIdentityCardRecoveryStatus() == 1) {
                adminData.setIdentityCardRecoveryApproved(ParamUtil.getBoolean(request, "approvedIdentityCard"));
                adminData.setIdentityCardRecoveryComment(ParamUtil.getString(request, "commentIdentityCard"));
            }
            FinanceForm financeForm = financeFormData.get(0);
            List<RecoveryReimbursement> recoveryFormData = RecoveryReimbursementLocalServiceUtil.findSelectedRecoveryFormByDepartment(financeForm.getId(), DEPARTMENT_FINANCE);
            for (RecoveryReimbursement recovery : recoveryFormData) {
                List<RecoveryReimbursement> financeSelectedRecoveryData = recoveryFormData.stream().filter(s -> s.getId() == recovery.getId()).collect(Collectors.toList());
                if (!financeSelectedRecoveryData.isEmpty()) {
                    RecoveryReimbursement recoveryData = financeSelectedRecoveryData.get(0);
                    recoveryData.setComment(ParamUtil.getString(request, COMMENT_FINANCE + recovery.getId()));
                    recoveryData.setApproved(ParamUtil.getBoolean(request, APPROVED_FINANCE + recovery.getId()));
                    RecoveryReimbursementLocalServiceUtil.updateRecoveryReimbursement(recoveryData);
                }
            }
            if (financeForm.getTravelRecoveryStatus() == 1) {
                financeForm.setTravelRecoveryApproved(ParamUtil.getBoolean(request, APPROVE + RECOVERY_FOR_TRAVEL_NAMESPACE));
                financeForm.setTravelRecoveryComment(ParamUtil.getString(request, COMMENT + RECOVERY_FOR_TRAVEL_NAMESPACE));
            }
            if (financeForm.getHotelRecoveryStatus() == 1) {
                financeForm.setHotelRecoveryApproved(ParamUtil.getBoolean(request, APPROVE + RECOVERY_FOR_HOTEL_NAMESPACE));
                financeForm.setHotelRecoveryComment(ParamUtil.getString(request, COMMENT + RECOVERY_FOR_HOTEL_NAMESPACE));
            }
            if (financeForm.getInternationalRecoveryStatus() == 1) {
                financeForm.setInternationalRecoveryApproved(ParamUtil.getBoolean(request, APPROVE + RECOVERY_FOR_INTERNATIONAL_TRAVEL_NAMESPACE));
                financeForm.setInternationalRecoveryComment(ParamUtil.getString(request, COMMENT + RECOVERY_FOR_INTERNATIONAL_TRAVEL_NAMESPACE));
            }
            if (financeForm.getEducationRecoveryStatus() == 1) {
                financeForm.setEducationRecoveryApproved(ParamUtil.getBoolean(request, APPROVE + RECOVERY_FOR_EDUCATION_NAMESPACE));
                financeForm.setEducationRecoveryComment(ParamUtil.getString(request, COMMENT + RECOVERY_FOR_EDUCATION_NAMESPACE));


            }
            if (financeForm.getJoiningBonusRecoveryStatus() == 1) {
                financeForm.setJoiningRecoveryApproved(ParamUtil.getBoolean(request, APPROVE + RECOVERY_FOR_BONUS_NAMESPACE));
                financeForm.setJoiningRecoveryComment(ParamUtil.getString(request, COMMENT + RECOVERY_FOR_BONUS_NAMESPACE));

            }
            if (financeForm.getNoticePeriodRecoveryStatus() == 1) {
                financeForm.setNoticePeriodRecoveryApproved(ParamUtil.getBoolean(request, APPROVE + RECOVERY_FOR_NOTICE_PERIOD_NAMESPACE));
                financeForm.setNoticePeriodRecoveryComment(ParamUtil.getString(request, COMMENT + RECOVERY_FOR_NOTICE_PERIOD_NAMESPACE));
            }

            ItForm itData = itFormData.get(0);
            List<RecoveryReimbursement> itRecoveryData = RecoveryReimbursementLocalServiceUtil.findSelectedRecoveryFormByDepartment(itData.getId(), DEPARTMENT_IT);
            for (RecoveryReimbursement recovery : itRecoveryData) {
                List<RecoveryReimbursement> itSelectedRecoveryData = itRecoveryData.stream().filter(s -> s.getId() == recovery.getId()).collect(Collectors.toList());
                if (!itSelectedRecoveryData.isEmpty()) {
                    RecoveryReimbursement recoveryData = itSelectedRecoveryData.get(0);
                    recoveryData.setComment(ParamUtil.getString(request, COMMENT_IT + recovery.getId()));
                    recoveryData.setApproved(ParamUtil.getBoolean(request, APPROVE_IT + recovery.getId()));
                    RecoveryReimbursementLocalServiceUtil.updateRecoveryReimbursement(recoveryData);
                }
            }
            if (itData.getSystemRecoveryAmtStatus() == 1) {
                itData.setSystemRecoveryApproved(ParamUtil.getBoolean(request, APPROVE_IT + DESKTOP_NAMESPACE));
                itData.setSystemRecoveryComment(ParamUtil.getString(request, COMMENT_IT + DESKTOP_NAMESPACE));
            }
            if (itData.getLaptopRecoveryAmtStatus() == 1) {
                itData.setLaptopRecoveryApproved(ParamUtil.getBoolean(request, APPROVE_IT + LAPTOP_NAMESPACE));
                itData.setLaptopRecoveryComment(ParamUtil.getString(request, COMMENT_IT + LAPTOP_NAMESPACE));
            }
            if (itData.getCommunicationRecoveryStatus() == 1) {
                itData.setCommunicationRecoveryApproved(ParamUtil.getBoolean(request, APPROVE_IT + TELEPHONE_NAMESPACE));
                itData.setCommunicationRecoveryComment(ParamUtil.getString(request, COMMENT_IT + TELEPHONE_NAMESPACE));
            }
            if (itData.getHeadphoneRecoveryAmtStatus() == 1) {
                itData.setHeadphoneRecoveryApproved(ParamUtil.getBoolean(request, APPROVE_IT + HEADPHONE_NAMESPACE));
                itData.setHeadphoneRecoveryComment(ParamUtil.getString(request, COMMENT_IT + HEADPHONE_NAMESPACE));

            }
            if (itData.getLaptopBagRecoveryAmtStatus() == 1) {
                itData.setLaptopBagRecoveryApproved(ParamUtil.getBoolean(request, APPROVE_IT + LAPTOP_BAG_NAMESPACE));
                itData.setLaptopBagRecoveryComment(ParamUtil.getString(request, COMMENT_IT + LAPTOP_BAG_NAMESPACE));

            }
            if (itData.getMonitorRecoveryAmtStatus() == 1) {
                itData.setMonitorRecoveryApproved(ParamUtil.getBoolean(request, APPROVE_IT + MONITOR_NAMESPACE));
                itData.setMonitorRecoveryComment(ParamUtil.getString(request, COMMENT_IT + MONITOR_NAMESPACE));
            }
            if (itData.getChargerRecoveryAmtStatus() == 1) {
                itData.setChargerRecoveryApproved(ParamUtil.getBoolean(request, APPROVE_IT + CHARGER_NAMESPACE));
                itData.setChargerRecoveryComment(ParamUtil.getString(request, COMMENT_IT + CHARGER_NAMESPACE));
            }
            if (itData.getMouseRecoveryAmtStatus() == 1) {
                itData.setMouseRecoveryApproved(ParamUtil.getBoolean(request, APPROVE_IT + MOUSE_NAMESPACE));
                itData.setMouseRecoveryComment(ParamUtil.getString(request, COMMENT_IT + MOUSE_NAMESPACE));
            }
            hrForm.setFoodOption(ParamUtil.getLong(request, "foodOption"));
            hrForm.setFoodOptionRemark(ParamUtil.getString(request, "foodOptionRemark"));
            hrForm.setInductionFeedbackStatus(ParamUtil.getInteger(request, "inductionFeedbackStatus"));
            hrForm.setInductionFeedbackRemark(ParamUtil.getString(request, "inductionFeedbackRemark"));
            hrForm.setInductionQuizStatus(ParamUtil.getInteger(request, "inductionQuizStatus"));
            hrForm.setInductionQuizRemark(ParamUtil.getString(request, "inductionQuizRemark"));
            hrForm.setTrainingFeedbackStatus(ParamUtil.getInteger(request, "trainingFeedbackStatus"));
            hrForm.setTrainingFeedbackRemark(ParamUtil.getString(request, "trainingFeedbackRemark"));
            hrForm.setExitInterviewStatus(ParamUtil.getInteger(request, "exitInterviewStatus"));
            hrForm.setExitInterviewRemark(ParamUtil.getString(request, "exitInterviewRemark"));
            hrForm.setEmployeeDirectoryStatus(ParamUtil.getInteger(request, "employeeDirectoryStatus"));
            hrForm.setEmployeeDirectoryRemark(ParamUtil.getString(request, "employeeDirectoryRemark"));
            hrForm.setLmsStatus(ParamUtil.getInteger(request, "lmsStatus"));
            hrForm.setHrRemark(ParamUtil.getString(request, "hrRemarks"));
            hrForm.setLmsRemark(ParamUtil.getString(request, "lmsRemark"));
            hrForm.setVantageCircleStatus(ParamUtil.getInteger(request, "vantageCircleStatus"));
            hrForm.setVantageCircleRemark(ParamUtil.getString(request, "vantageCircleRemark"));
            hrForm.setBirthdaySynergyStatus(ParamUtil.getInteger(request, "birthdaySynergyStatus"));
            hrForm.setBirthdaySynergyRemark(ParamUtil.getString(request, "birthdaySynergyRemark"));
            hrForm.setExperienceLetterStatus(ParamUtil.getInteger(request, "experienceLetterStatus"));
            hrForm.setExperienceLetterRemark(ParamUtil.getString(request, "experienceLetterRemark"));
            hrForm.setNdaFormStatus(ParamUtil.getInteger(request, "ndaFormStatus"));
            hrForm.setNdaFormRemark(ParamUtil.getString(request, "ndaFormRemark"));
            hrForm.setSeparationDocumentStatus(ParamUtil.getInteger(request, "separationDocumentStatus"));
            hrForm.setSeparationDocumentRemark(ParamUtil.getString(request, "separationDocumentRemarkHR"));
            hrForm.setTrainingAgreementAmt(ParamUtil.getString(request, "trainingAgreementAmt"));
            hrForm.setTrainingAgreementStatus(ParamUtil.getInteger(request, "trainingAgreementStatus"));
            hrForm.setRecoverableBonusAmt(ParamUtil.getString(request, "recoverableBonusAmt"));
            hrForm.setRecoverableBonusStatus(ParamUtil.getInteger(request, "recoverableBonusStatus"));
            hrForm.setNoticePeriodRecoveryAmt(ParamUtil.getString(request, "noticePeriodRecoveryAmt"));
            hrForm.setNoticePeriodRecoveryStatus(ParamUtil.getInteger(request, "noticePeriodRecoveryStatus"));
            hrForm.setLeavesMonth1(ParamUtil.getString(request, "leavesMonth1"));
            hrForm.setLeavesMonth2(ParamUtil.getString(request, "leavesMonth2"));
            hrForm.setLeavesMonth3(ParamUtil.getString(request, "leavesMonth3"));
            hrForm.setLeaveDaysMonth1(ParamUtil.getString(request, "leaveDaysMonth1"));
            hrForm.setLeaveDaysMonth2(ParamUtil.getString(request, "leaveDaysMonth2"));
            hrForm.setLeaveDaysMonth3(ParamUtil.getString(request, "leaveDaysMonth3"));
            hrForm.setLeaveDateMonth1(ParamUtil.getString(request, "leaveDateMonth1"));
            hrForm.setLeaveDateMonth2(ParamUtil.getString(request, "leaveDateMonth2"));
            hrForm.setLeaveDateMonth3(ParamUtil.getString(request, "leaveDateMonth3"));
            hrForm.setLopMonth1(ParamUtil.getString(request, "lopMonth1"));
            hrForm.setLopMonth2(ParamUtil.getString(request, "lopMonth2"));
            hrForm.setLopMonth3(ParamUtil.getString(request, "lopMonth3"));
            hrForm.setLopDaysMonth1(ParamUtil.getString(request, "lopDaysMonth1"));
            hrForm.setLopDaysMonth2(ParamUtil.getString(request, "lopDaysMonth2"));
            hrForm.setLopDaysMonth3(ParamUtil.getString(request, "lopDaysMonth3"));
            hrForm.setLopDateMonth1(ParamUtil.getString(request, "lopDateMonth1"));
            hrForm.setLopDateMonth2(ParamUtil.getString(request, "lopDateMonth2"));
            hrForm.setLopDateMonth3(ParamUtil.getString(request, "lopDateMonth3"));
            hrForm.setEarnedLeaveBalance(ParamUtil.getString(request, "earnedLeaveBalance"));
            hrForm.setUpdatedDate(now);
            HrFormLocalServiceUtil.updateHrForm(hrForm);
            AdminFormLocalServiceUtil.updateAdminForm(adminData);
            FinanceFormLocalServiceUtil.updateFinanceForm(financeForm);
            ItFormLocalServiceUtil.updateItForm(itData);
            int hrTotalRows = ParamUtil.getInteger(request, "hrTotalRows");
            List<RecoveryReimbursement> recoveryReimbursementForm = RecoveryReimbursementLocalServiceUtil.findAllRecoveryFormByDepartment(hrForm.getId(), DEPARTMENT_HR);
            if (!recoveryReimbursementForm.isEmpty()) {
                for (RecoveryReimbursement recoveryForm : recoveryReimbursementForm) {
                    RecoveryReimbursementLocalServiceUtil.deleteRecoveryReimbursement(recoveryForm);
                }
            }
            if (hrTotalRows > 3) {
                for (int i = 3; i < hrTotalRows; i++) {
                    String hrItem = ParamUtil.getString(request, "hrItem" + i);
                    int hrRecoveryStatus = ParamUtil.getInteger(request, "hrRecoveryStatus" + i);
                    String hrRecoveryAmount = ParamUtil.getString(request, "hrRecoveryAmount" + i);
                    if (!hrItem.isEmpty() || hrRecoveryStatus != 0 || !hrRecoveryAmount.isEmpty()) {
                        RecoveryReimbursement recoveryForm = RecoveryReimbursementLocalServiceUtil.createRecoveryReimbursement(CounterLocalServiceUtil.increment());
                        recoveryForm.setDepartmentFormId(hrForm.getId());
                        recoveryForm.setDepartment(5);
                        recoveryForm.setRecoveryItem(hrItem);
                        recoveryForm.setRecoveryStatus(hrRecoveryStatus);
                        recoveryForm.setRecoveryAmount(hrRecoveryAmount);
                        recoveryForm.setRecoveryType(true);
                        recoveryForm.setApproved(hrRecoveryStatus == 1);
                        RecoveryReimbursementLocalServiceUtil.addRecoveryReimbursement(recoveryForm);
                    }
                }
            }
            exitForm.setHrFormStatus(true);
            ExitFormLocalServiceUtil.updateExitForm(exitForm);

            Resignation empResignationDetails = ResignationLocalServiceUtil.fetchResignation(resignationId);
            String htmlString = generateHtmlString(String.valueOf(resignationId));


            StringBuilder tableContent = new StringBuilder();

            StringBuilder leaveTableContent = new StringBuilder();
            List<RecoveryReimbursement> recoveryData = RecoveryReimbursementLocalServiceUtil.findSelectedRecoveryFormByDepartment(hrFormData.get(0).getId(), DEPARTMENT_HR);
            if (hrForm.getTrainingAgreementStatus() == 1 || hrForm.getRecoverableBonusStatus() == 1 || hrForm.getNoticePeriodRecoveryStatus() == 1) {
                tableContent.append(RECOVERY_TABLE_START);
            }
            if (hrForm.getTrainingAgreementStatus() == 1 || hrForm.getRecoverableBonusStatus() == 1 || hrForm.getNoticePeriodRecoveryStatus() == 1) {
                tableContent.append(ROW_START_ROWSPAN);
                tableContent.append(HR_RECOVERY);
                tableContent.append(ROW_END);
                if (hrForm.getTrainingAgreementStatus() == 1) {
                    tableContent.append(ROW_START);
                    tableContent.append(TRAINING_AGGREMENT_TRAINEES);
                    tableContent.append(CELL_SEPERATOR);
                    tableContent.append(hrForm.getTrainingAgreementAmt());
                    tableContent.append(CELL_SEPERATOR);
                    tableContent.append(YES);
                    tableContent.append(ROW_END);
                }
                if (hrForm.getRecoverableBonusStatus() == 1) {
                    tableContent.append(ROW_START);
                    tableContent.append(RECOVERABLE_BONUS);
                    tableContent.append(CELL_SEPERATOR);
                    tableContent.append(hrForm.getRecoverableBonusAmt());
                    tableContent.append(CELL_SEPERATOR);
                    tableContent.append(YES);
                    tableContent.append(ROW_END);
                }
                if (hrForm.getNoticePeriodRecoveryStatus() == 1) {
                    tableContent.append(ROW_START);
                    tableContent.append(NOTICE_PERIOD_RECOVERY);
                    tableContent.append(CELL_SEPERATOR);
                    tableContent.append(hrForm.getNoticePeriodRecoveryAmt());
                    tableContent.append(CELL_SEPERATOR);
                    tableContent.append(YES);
                    tableContent.append(ROW_END);
                }
                if (!recoveryData.isEmpty()) {
                    for (RecoveryReimbursement recoveryForm : recoveryData) {
                        tableContent.append(ROW_START);
                        tableContent.append(recoveryForm.getRecoveryItem());
                        tableContent.append(CELL_SEPERATOR);
                        tableContent.append(recoveryForm.getRecoveryAmount());
                        tableContent.append(CELL_SEPERATOR);
                        tableContent.append(getYesNoNaForInteger(recoveryForm.getRecoveryStatus()));
                        tableContent.append(ROW_END);
                    }
                }
            }
            tableContent.append(TABLE_END);
            leaveTableContent.append(LEAVE_TABLE_START);
            leaveTableContent.append(LEAVE_DURING_NOTICE_PERIOD);
            leaveTableContent.append(CELL_SEPERATOR);
            leaveTableContent.append(MONTHS);
            leaveTableContent.append(CELL_SEPERATOR);
            leaveTableContent.append(hrForm.getLeavesMonth1());
            leaveTableContent.append(CELL_SEPERATOR);
            leaveTableContent.append(hrForm.getLeavesMonth2());
            leaveTableContent.append(CELL_SEPERATOR);
            leaveTableContent.append(hrForm.getLeavesMonth3());
            leaveTableContent.append(ROW_END);
            leaveTableContent.append(DAYS);
            leaveTableContent.append(CELL_SEPERATOR);
            leaveTableContent.append(hrForm.getLeaveDaysMonth1());
            leaveTableContent.append(CELL_SEPERATOR);
            leaveTableContent.append(hrForm.getLeaveDaysMonth2());
            leaveTableContent.append(CELL_SEPERATOR);
            leaveTableContent.append(hrForm.getLeaveDaysMonth3());
            leaveTableContent.append(ROW_END);
            leaveTableContent.append(DATE);
            leaveTableContent.append(CELL_SEPERATOR);
            leaveTableContent.append(hrForm.getLeaveDateMonth1());
            leaveTableContent.append(CELL_SEPERATOR);
            leaveTableContent.append(hrForm.getLeaveDateMonth2());
            leaveTableContent.append(CELL_SEPERATOR);
            leaveTableContent.append(hrForm.getLeaveDateMonth3());
            leaveTableContent.append(ROW_END);
            leaveTableContent.append(LOP_DURING_NOTICE_PERIOD);
            leaveTableContent.append(CELL_SEPERATOR);
            leaveTableContent.append(MONTHS);
            leaveTableContent.append(CELL_SEPERATOR);
            leaveTableContent.append(hrForm.getLopMonth1());
            leaveTableContent.append(CELL_SEPERATOR);
            leaveTableContent.append(hrForm.getLopMonth2());
            leaveTableContent.append(CELL_SEPERATOR);
            leaveTableContent.append(hrForm.getLopMonth3());
            leaveTableContent.append(ROW_END);
            leaveTableContent.append(DAYS);
            leaveTableContent.append(CELL_SEPERATOR);
            leaveTableContent.append(hrForm.getLopDaysMonth1());
            leaveTableContent.append(CELL_SEPERATOR);
            leaveTableContent.append(hrForm.getLopDaysMonth2());
            leaveTableContent.append(CELL_SEPERATOR);
            leaveTableContent.append(hrForm.getLopDaysMonth3());
            leaveTableContent.append(ROW_END);
            leaveTableContent.append(DATE);
            leaveTableContent.append(CELL_SEPERATOR);
            leaveTableContent.append(hrForm.getLopDateMonth1());
            leaveTableContent.append(CELL_SEPERATOR);
            leaveTableContent.append(hrForm.getLopDateMonth2());
            leaveTableContent.append(CELL_SEPERATOR);
            leaveTableContent.append(hrForm.getLopDateMonth3());
            leaveTableContent.append(ROW_END);
            leaveTableContent.append(EARNED_LEAVE);
            leaveTableContent.append(CELL_SEPERATOR_COLSPAN);
            leaveTableContent.append(hrForm.getEarnedLeaveBalance());
            leaveTableContent.append(ROW_END);
            leaveTableContent.append(LEAVE_TABLE_END);
            LocalDateTime localDateTime = LocalDateTime.now();
            LocalDate localDate = localDateTime.toLocalDate();
            Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
            email.setBody(MessageFormat.format(HR_CLEARANCE_COMPLETED_EMAIL_BODY, localDate, localDate.plusDays(30), tableContent.toString(), leaveTableContent.toString()));
            email.setSubject(MessageFormat.format(HR_CLEARANCE_COMPLETED_EMAIL_SUBJECT, empResignationDetails.getEcode(), employeeData.getName()));
            if (empResignationDetails.getStatus() == 5 || (empResignationDetails.getStatus() == 2 && !empResignationDetails.getReasonForLeavingByHr().equalsIgnoreCase("Absconded"))) {
                email.setToAddress(employeeResignData.getAlternateEmail());
            }
            email.setCcAddress(DL_HR);
            email.setCreatedDate(now);
            email.setModule(MODULE_EXIT);
            email.setScheduled(false);
            email.setSent(false);
            EmailLocalServiceUtil.addEmail(email);
            Email emailer = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
            emailer.setBody(MessageFormat.format(CLEARANCE_COMPLETED_EMAIL_BODY, employeeData.getName(), getPortalUrl() + URL_EXIT_ADMIN));
            emailer.setSubject(MessageFormat.format(CLEARANCE_COMPLETED_EMAIL_SUBJECT, empResignationDetails.getEcode(), employeeData.getName()));
            emailer.setToAddress(DL_HR);
            emailer.setModule(MODULE_EXIT);
            emailer.setScheduled(false);
            emailer.setCreatedDate(now);
            emailer.setSent(false);
            EmailLocalServiceUtil.addEmail(emailer);

            EmailAttachment email1 = EmailAttachmentLocalServiceUtil.createEmailAttachment(CounterLocalServiceUtil.increment());
            email1.setEmailId(emailer.getEmailId());
            email1.setAttachmentName(employeeData.getEcode() + ".pdf");
            email1.setAttachmentFileId(BLANK);
            EmailAttachmentLocalServiceUtil.addEmailAttachment(email1);
            String attachmentId = String.valueOf(email1.getEmailAttachmentId());
            File temp = File.createTempFile(attachmentId.length() < 3 ? "00" + attachmentId : attachmentId, ".pdf");
            temp.deleteOnExit();
            OutputStream os = new FileOutputStream(temp);
            // Starting writing the bytes in it
            os.write(PdfService.createPdf(htmlString));
            os.close();
            String fileId = DriveService.uploadFile(DriveLocalServiceUtil.findFolderIdByFolderName(MODULE_EMAIL), email1.getEmailAttachmentId() + ".pdf", temp);
            email1.setAttachmentFileId(fileId);
            EmailAttachmentLocalServiceUtil.updateEmailAttachment(email1);
        }
        if (!itFormData.isEmpty() && roleType.equalsIgnoreCase(ROLE_IT)) {
            ExitForm exitForm = exitFormData.get(0);
            ItForm itForm = itFormData.get(0);
            itForm.setTicketNo(ParamUtil.getInteger(request, IT_TICKET));
            itForm.setTicketNoRemark(ParamUtil.getString(request, IT_TICKET_REMARK));
            itForm.setMailPst(ParamUtil.getInteger(request, "mailPst"));
            itForm.setMailPstRemark(ParamUtil.getString(request, "mailPstRemarkIT"));
            itForm.setDownloadFolder(ParamUtil.getInteger(request, "downloadFolder"));
            itForm.setDownloadFolderRemark(ParamUtil.getString(request, "downloadFolderRemarkIT"));
            itForm.setDocumentFolder(ParamUtil.getInteger(request, "documentFolder"));
            itForm.setDocumentFolderRemark(ParamUtil.getString(request, "documentFolderRemarkIT"));
            itForm.setOtherData(ParamUtil.getInteger(request, "otherData"));
            itForm.setOtherDataRemark(ParamUtil.getString(request, "otherDataRemarkIT"));
            itForm.setGoogleDrive(ParamUtil.getInteger(request, "googleDrive"));
            itForm.setGoogleDriveRemark(ParamUtil.getString(request, "googleDriveRemarkIT"));
            itForm.setClientSystemRecovered(ParamUtil.getInteger(request, "clientSystemRecovered"));
            itForm.setClientSystemRecoveredRemark(ParamUtil.getString(request, "clientSystemRecoveredRemark"));
            itForm.setOthers(ParamUtil.getInteger(request, "others"));
            itForm.setOthersRemark(ParamUtil.getString(request, "othersRemarkIT"));
            itForm.setMailForwarding(ParamUtil.getInteger(request, "mailForwarding"));
            itForm.setMailForwardingRemark(ParamUtil.getString(request, "mailForwardingRemarkIT"));
            itForm.setEmailDisable(ParamUtil.getInteger(request, "emailDisable"));
            itForm.setEmailDisableRemark(ParamUtil.getString(request, "emailDisableRemark"));
            itForm.setSystemRecovered(ParamUtil.getInteger(request, "systemRecovered"));
            itForm.setSystemRecoveredRemark(ParamUtil.getString(request, "systemRecoveredRemark"));
            itForm.setAccessCardDisable(ParamUtil.getInteger(request, "accessCardDisable"));
            itForm.setAccessCardDisableRemark(ParamUtil.getString(request, "accessCardDisableRemark"));
            itForm.setSeparationDocument(ParamUtil.getInteger(request, "separationDocument"));
            itForm.setSeparationDocumentRemark(ParamUtil.getString(request, "separationDocumentRemark"));
            itForm.setSystemRecoveryAmt(ParamUtil.getString(request, "systemRecoveryAmt"));
            int setSystemRecoveryAmtStatus = ParamUtil.getInteger(request, "systemRecoveryAmtStatus");
            itForm.setSystemRecoveryAmtStatus(setSystemRecoveryAmtStatus);
            itForm.setSystemRecoveryApproved(setSystemRecoveryAmtStatus == 1);
            itForm.setLaptopRecoveryAmt(ParamUtil.getString(request, "laptopRecoveryAmt"));
            int laptopRecoveryAmtStatus = ParamUtil.getInteger(request, "laptopRecoveryAmtStatus");
            itForm.setLaptopRecoveryAmtStatus(laptopRecoveryAmtStatus);
            itForm.setLaptopRecoveryApproved(laptopRecoveryAmtStatus == 1);
            itForm.setMouseRecoveryAmt(ParamUtil.getString(request, "mouseRecoveryAmt"));
            int mouseRecoveryAmtStatus = ParamUtil.getInteger(request, "mouseRecoveryAmtStatus");
            itForm.setMouseRecoveryAmtStatus(mouseRecoveryAmtStatus);
            itForm.setMouseRecoveryApproved(mouseRecoveryAmtStatus == 1);
            itForm.setMonitorRecoveryAmt(ParamUtil.getString(request, "monitorAmt"));
            int monitorRecoveryAmtStatus = ParamUtil.getInteger(request, "monitorRecoveryAmtStatus");
            itForm.setMonitorRecoveryAmtStatus(monitorRecoveryAmtStatus);
            itForm.setMonitorRecoveryApproved(monitorRecoveryAmtStatus == 1);
            itForm.setChargerRecoveryAmt(ParamUtil.getString(request, "chargerAmt"));
            int chargerRecoveryAmtStatus = ParamUtil.getInteger(request, "chargerRecoveryAmtStatus");
            itForm.setChargerRecoveryAmtStatus(chargerRecoveryAmtStatus);
            itForm.setChargerRecoveryApproved(chargerRecoveryAmtStatus == 1);
            itForm.setCommunicationRecoveryAmt(ParamUtil.getString(request, "communicationRecoveryAmt"));
            int communicationRecoveryStatus = ParamUtil.getInteger(request, "communicationRecoveryStatus");
            itForm.setCommunicationRecoveryStatus(communicationRecoveryStatus);
            itForm.setCommunicationRecoveryApproved(communicationRecoveryStatus == 1);
            itForm.setHeadphoneRecoveryAmt(ParamUtil.getString(request, "headphoneRecoveryAmt"));
            int headphoneRecoveryAmtStatus = ParamUtil.getInteger(request, "headphoneRecoveryAmtStatus");
            itForm.setHeadphoneRecoveryAmtStatus(headphoneRecoveryAmtStatus);
            itForm.setHeadphoneRecoveryApproved(headphoneRecoveryAmtStatus == 1);
            itForm.setLaptopBagRecoveryAmt(ParamUtil.getString(request, "laptopBagRecoveryAmt"));
            int laptopBagRecoveryAmtStatus = ParamUtil.getInteger(request, "laptopBagRecoveryAmtStatus");
            itForm.setLaptopBagRecoveryAmtStatus(laptopBagRecoveryAmtStatus);
            itForm.setLaptopBagRecoveryApproved(laptopBagRecoveryAmtStatus == 1);
            itForm.setUpdatedDate(now);
            ItFormLocalServiceUtil.updateItForm(itForm);
            exitForm.setItFormStatus(true);
            ExitFormLocalServiceUtil.updateExitForm(exitForm);
            int itTotalRows = ParamUtil.getInteger(request, "itTotalRows");
            List<RecoveryReimbursement> recoveryReimbursementForm = RecoveryReimbursementLocalServiceUtil.findAllRecoveryFormByDepartment(itForm.getId(), DEPARTMENT_IT);
            if (!recoveryReimbursementForm.isEmpty()) {
                for (RecoveryReimbursement recoveryForm : recoveryReimbursementForm) {
                    RecoveryReimbursementLocalServiceUtil.deleteRecoveryReimbursement(recoveryForm);
                }
            }
            if (itTotalRows > 8) {
                for (int i = 8; i < itTotalRows; i++) {
                    String itItem = ParamUtil.getString(request, "itItem" + i);
                    int itRecoveryStatus = ParamUtil.getInteger(request, "itRecoveryStatus" + i);
                    String itRecoveryAmount = ParamUtil.getString(request, "itRecoveryAmount" + i);
                    if (!itItem.isEmpty() || itRecoveryStatus != 0 || !itRecoveryAmount.isEmpty()) {
                        RecoveryReimbursement recoveryForm = RecoveryReimbursementLocalServiceUtil.createRecoveryReimbursement(CounterLocalServiceUtil.increment());
                        recoveryForm.setDepartmentFormId(itForm.getId());
                        recoveryForm.setDepartment(2);
                        recoveryForm.setRecoveryItem(itItem);
                        recoveryForm.setRecoveryStatus(itRecoveryStatus);
                        recoveryForm.setRecoveryAmount(itRecoveryAmount);
                        recoveryForm.setRecoveryType(true);
                        recoveryForm.setApproved(itRecoveryStatus == 1);
                        RecoveryReimbursementLocalServiceUtil.addRecoveryReimbursement(recoveryForm);
                    }
                }
            }
            if (exitForm.getManagerFormStatus() && exitForm.getItFormStatus() && exitForm.getAdminFormStatus() && exitForm.getFinanceFormStatus()) {
                Email hrEmail = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
                hrEmail.setToAddress(DL_HR);
                hrEmail.setSubject(MessageFormat.format(HR_CLEARANCE_EMAIL_SUBJECT, employeeData.getEcode(), employeeData.getName()));
                hrEmail.setBody(MessageFormat.format(HR_CLEARANCE_EMAIL_BODY, employeeData.getName(), employeeData.getEcode(), getPortalUrl() + URL_EXIT_ADMIN));
                hrEmail.setCreatedDate(now);
                hrEmail.setModule(MODULE_EXIT);
                hrEmail.setScheduled(false);
                hrEmail.setSent(false);
                EmailLocalServiceUtil.addEmail(hrEmail);
            }
        }
        if (!adminFormData.isEmpty() && roleType.equalsIgnoreCase(ROLE_ADMIN)) {
            ExitForm exitForm = exitFormData.get(0);
            AdminForm adminForm = adminFormData.get(0);
            adminForm.setStationaryRecoveryAmt(ParamUtil.getString(request, "stationaryRecoveryAmt"));
            int stationaryRecoveryAmtStatus = ParamUtil.getInteger(request, "stationaryRecoveryAmtStatus");
            adminForm.setStationaryRecoveryAmtStatus(stationaryRecoveryAmtStatus);
            adminForm.setStationaryRecoveryApproved(stationaryRecoveryAmtStatus == 1);
            adminForm.setKeysRecoveryAmt(ParamUtil.getString(request, "keysRecoveryAmt"));
            int keysRecoveryAmtStatus = ParamUtil.getInteger(request, "keysRecoveryAmtStatus");
            adminForm.setKeysRecoveryAmtStatus(keysRecoveryAmtStatus);
            adminForm.setKeyRecoveryApproved(keysRecoveryAmtStatus == 1);
            adminForm.setLibraryRecoveryAmt(ParamUtil.getString(request, "libraryRecoveryAmt"));
            int libraryRecoveryAmtStatus = ParamUtil.getInteger(request, "libraryRecoveryAmtStatus");
            adminForm.setLibraryRecoveryAmtStatus(libraryRecoveryAmtStatus);
            adminForm.setLibraryRecoveryApproved(libraryRecoveryAmtStatus == 1);
            adminForm.setInfraRecoveryAmt(ParamUtil.getString(request, "infrastructureIssuedAmt"));
            int infrastructureIssuedAmtStatus = ParamUtil.getInteger(request, "infrastructureIssuedAmtStatus");
            adminForm.setInfraRecoveryAmtStatus(infrastructureIssuedAmtStatus);
            adminForm.setInfraRecoveryApproved(infrastructureIssuedAmtStatus == 1);
            adminForm.setLunchRecoveryAmt(ParamUtil.getString(request, "lunchDeductionAmt"));
            int lunchDeductionAmtStatus = ParamUtil.getInteger(request, LUNCH_DEDUCTION_AMOUNT_STATUS);
            adminForm.setLunchRecoveryAmtStatus(lunchDeductionAmtStatus);
            adminForm.setLunchRecoveryApproved(lunchDeductionAmtStatus == 1);
            adminForm.setSportsRecoveryAmt(ParamUtil.getString(request, "sportsRecoveryAmt"));
            int sportsRecoveryAmtStatus = ParamUtil.getInteger(request, "sportsRecoveryAmtStatus");
            adminForm.setSportsRecoveryAmtStatus(sportsRecoveryAmtStatus);
            adminForm.setSportsRecoveryApproved(sportsRecoveryAmtStatus == 1);
            adminForm.setAccessCardRecoveryAmt(ParamUtil.getString(request, "accessCardRecoveryAmt"));
            int accessCardRecoveryAmtStatus = ParamUtil.getInteger(request, "accessCardRecoveryAmtStatus");
            adminForm.setAccessCardRecoveryAmtStatus(accessCardRecoveryAmtStatus);
            adminForm.setAccessCardRecoveryApproved(accessCardRecoveryAmtStatus == 1);
            adminForm.setIdentityCardRecoveryAmt(ParamUtil.getString(request, "identityCardAmt"));
            int identityCardStatus = ParamUtil.getInteger(request, "identityCardStatus");
            adminForm.setIdentityCardRecoveryStatus(identityCardStatus);
            adminForm.setIdentityCardRecoveryApproved(identityCardStatus == 1);
            exitForm.setAdminFormStatus(true);
            AdminFormLocalServiceUtil.updateAdminForm(adminForm);
            ExitFormLocalServiceUtil.updateExitForm(exitForm);
            int adminTotalRows = ParamUtil.getInteger(request, "adminTotalRows");
            List<RecoveryReimbursement> recoveryReimbursementForm = RecoveryReimbursementLocalServiceUtil.findAllRecoveryFormByDepartment(adminForm.getId(), DEPARTMENT_ADMIN);
            if (!recoveryReimbursementForm.isEmpty()) {
                for (RecoveryReimbursement recoveryForm : recoveryReimbursementForm) {
                    RecoveryReimbursementLocalServiceUtil.deleteRecoveryReimbursement(recoveryForm);
                }
            }
            if (adminTotalRows > 8) {
                for (int i = 8; i < adminTotalRows; i++) {
                    String adminItem = ParamUtil.getString(request, "adminItem" + i);
                    int adminRecoveryStatus = ParamUtil.getInteger(request, "adminRecoveryStatus" + i);
                    String adminRecoveryAmount = ParamUtil.getString(request, "adminRecoveryAmount" + i);
                    if (!adminItem.isEmpty() || adminRecoveryStatus != 0 || !adminRecoveryAmount.isEmpty()) {
                        RecoveryReimbursement recoveryForm = RecoveryReimbursementLocalServiceUtil.createRecoveryReimbursement(CounterLocalServiceUtil.increment());
                        recoveryForm.setDepartmentFormId(adminForm.getId());
                        recoveryForm.setDepartment(3);
                        recoveryForm.setRecoveryItem(adminItem);
                        recoveryForm.setRecoveryStatus(adminRecoveryStatus);
                        recoveryForm.setRecoveryAmount(adminRecoveryAmount);
                        recoveryForm.setRecoveryType(true);
                        recoveryForm.setApproved(adminRecoveryStatus == 1);
                        RecoveryReimbursementLocalServiceUtil.addRecoveryReimbursement(recoveryForm);
                    }
                }
            }
            if (exitForm.getManagerFormStatus() && exitForm.getItFormStatus() && exitForm.getAdminFormStatus() && exitForm.getFinanceFormStatus()) {
                Email hrEmail = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
                hrEmail.setToAddress(DL_HR);
                hrEmail.setSubject(MessageFormat.format(HR_CLEARANCE_EMAIL_SUBJECT, employeeData.getEcode(), employeeData.getName()));
                hrEmail.setBody(MessageFormat.format(HR_CLEARANCE_EMAIL_BODY, employeeData.getName(), employeeData.getEcode(), getPortalUrl() + URL_EXIT_ADMIN));
                hrEmail.setCreatedDate(now);
                hrEmail.setModule(MODULE_EXIT);
                hrEmail.setScheduled(false);
                hrEmail.setSent(false);
                EmailLocalServiceUtil.addEmail(hrEmail);
            }
        }
    }

    @Override
    public void exitWorkflow(ActionRequest request, ActionResponse response) {

        String roleType = ParamUtil.getString(request, ROLE_TYPE);
        long resignationId = ParamUtil.getLong(request, RESIGNATION_ID);
        List<ExitState> statusMap = ExitStateLocalServiceUtil.getExitStates(-1, -1);
        EmployeeDto dataDto = new EmployeeDto();
        Resignation employeeResignData = ResignationLocalServiceUtil.fetchResignation(resignationId);
        Employee employeeData = EmployeeLocalServiceUtil.fetchEmployee(employeeResignData.getEcode());
        Employee managerData = EmployeeLocalServiceUtil.findByEmail(employeeResignData.getManagerEmail());
        Employee assigneeData = EmployeeLocalServiceUtil.findByEmail(employeeResignData.getAssigneeEmail());
        List<ExitForm> exitFormData = ExitFormLocalServiceUtil.findExitFormByResignationId(employeeResignData.getId());
        dataDto.setResignationId(employeeResignData.getId());
        dataDto.setAlternateEmail(employeeResignData.getAlternateEmail());
        if (null != employeeData) {
            dataDto.setEmpMobNo(!employeeResignData.getMobile().isEmpty() ? employeeResignData.getMobile() : employeeData.getMobile());
            dataDto.setEmployeeCode(employeeData.getEcode());
            dataDto.setEmployeeName(employeeData.getName());
            dataDto.setEmployeeDesignation(employeeData.getDesignation());
            dataDto.setEmployeeBand(employeeData.getBand());
            dataDto.setDoj(convertDateToLocalDateTime(employeeData.getDoj()).format(FORMATTER_YYYY_MM_DD));
            dataDto.setLocation(employeeData.getLocation());
        } else {
            dataDto.setEmpMobNo(!employeeResignData.getMobile().isEmpty() ? employeeResignData.getMobile() : BLANK);
        }
        dataDto.setAccount(employeeResignData.getAccount());
        dataDto.setManagerName(null != managerData ? managerData.getName() : BLANK);
        dataDto.setManagerEmail(employeeResignData.getManagerEmail());
        dataDto.setAssigneeEmail(employeeResignData.getAssigneeEmail());
        dataDto.setLoggedUserEmail(getLoggedUser(request));
        dataDto.setPersonalEmail(employeeResignData.getAlternateEmail());

        dataDto.setAbscondRetained(employeeResignData.getStatus() == 6 && employeeResignData.getReason().equalsIgnoreCase(statusMap.get(4).getExitStateDescription()));
        if (null != employeeResignData.getManagerSubmissionDate() && employeeResignData.getStatus() == 4 && employeeResignData.getManagerSubmissionDate().compareTo(employeeResignData.getCreationDate()) == 0) {
            dataDto.setShowManagerMessage(true);
        }
        if (null != employeeResignData.getHrSubmissionDate() && employeeResignData.getStatus() == 4 && employeeResignData.getHrSubmissionDate().compareTo(employeeResignData.getCreationDate()) == 0) {
            dataDto.setAbscondedByHR(true);
        }
        dataDto.setAssigneeName(null != assigneeData ? assigneeData.getName() : BLANK);
        if (getLoggedUser(request).equalsIgnoreCase(employeeResignData.getManagerEmail())) {
            dataDto.setPrimaryManager(true);
        }
        if (null != employeeResignData.getLastWorkingDate()) {
            dataDto.setLastWorkingDate(convertDateToLocalDateTime(employeeResignData.getLastWorkingDate()).format(FORMATTER_YYYY_MM_DD));
        }
        dataDto.setItAssetsSubmitted(null != employeeResignData.getItAssetsSubmissionDate());
        dataDto.setManagerSubmitted(null != employeeResignData.getManagerSubmissionDate());

        dataDto.setReason(employeeResignData.getReason());
        dataDto.setStatusInt(employeeResignData.getStatus());
        dataDto.setStateName(employeeResignData.getStateName());
        dataDto.setCityName(employeeResignData.getCityName());
        dataDto.setPincode(employeeResignData.getPincode());
        dataDto.setPostalAddress(employeeResignData.getPostalAddress());
        dataDto.setStatus(!exitFormData.isEmpty() && exitFormData.get(0).getHrFormStatus() && employeeResignData.getStatus() != 6 ? CLEARANCES_COMPLETED : statusMap.get(employeeResignData.getStatus()).getExitStateDisplay());
        dataDto.setResignationDate(convertDateToLocalDateTime(employeeResignData.getCreationDate()).format(FORMATTER_YYYY_MM_DD));
        dataDto.setSeparationType(employeeResignData.getSeparationType());
        dataDto.setKeyToCompany(employeeResignData.getKeyToCompany());
        dataDto.setKeyToProject(employeeResignData.getKeyToProject());
        dataDto.setKeyToRetention(employeeResignData.getKeyToRetention());
        dataDto.setClientImpact(employeeResignData.getClientImpact());
        dataDto.setRating(employeeResignData.getRating());
        dataDto.setReasonNonRetention(employeeResignData.getReasonNonRetention());
        dataDto.setReasonForLeaving(employeeResignData.getReasonForLeaving());
        dataDto.setReasonForLeavingByHr(employeeResignData.getReasonForLeavingByHr());
        dataDto.setReplacementPlan(employeeResignData.getReplacementPlan());
        dataDto.setReplacementDetail(employeeResignData.getReplacementDetail());
        dataDto.setEmployeeComment(employeeResignData.getEmployeeComment());
        dataDto.setManagerComment(employeeResignData.getManagerComment());
        dataDto.setHrComment(employeeResignData.getHrComment());
        dataDto.setRoleType(roleType);
        dataDto.setExitQuestionnaire(employeeResignData.getExitQuestionnaire());
        dataDto.setShowHideHrUpdateButton((employeeResignData.getStatus() == 0 || employeeResignData.getStatus() == 4 || employeeResignData.getStatus() == 5) && null == employeeResignData.getLastWorkingDate() && roleType.equalsIgnoreCase(ROLE_HR));
        dataDto.setNoticePeriod(employeeResignData.getNoticePeriod());
        if (!exitFormData.isEmpty() && employeeResignData.getStatus() != 6) {
            if (!exitFormData.get(0).getManagerFormStatus() || !exitFormData.get(0).getAdminFormStatus() || !exitFormData.get(0).getItFormStatus() || !exitFormData.get(0).getFinanceFormStatus() || !exitFormData.get(0).getHrFormStatus()) {
                if (!exitFormData.get(0).getManagerFormStatus() && !exitFormData.get(0).getAdminFormStatus() && !exitFormData.get(0).getItFormStatus() && !exitFormData.get(0).getFinanceFormStatus() && !exitFormData.get(0).getHrFormStatus()) {
                    dataDto.setEmpStatus(ALL + CLEARANCE + PENDING);
                }  else {
                    StringBuilder status = new StringBuilder();
                    if (!exitFormData.get(0).getManagerFormStatus()) {
                        status.append(MANAGER).append(COMMA);
                    }
                    if (!exitFormData.get(0).getAdminFormStatus()) {
                        status.append(ADMIN).append(COMMA);
                    }
                    if (!exitFormData.get(0).getItFormStatus()) {
                        status.append(IT).append(COMMA);
                    }
                    if (!exitFormData.get(0).getFinanceFormStatus()) {
                        status.append(FINANCE).append(COMMA);
                    }
                    if (!exitFormData.get(0).getHrFormStatus()) {
                        status.append(HR).append(COMMA);
                    }
                    dataDto.setEmpStatus(status.deleteCharAt(status.length() - 1).append(CLEARANCE).append(PENDING).toString());
                }
            } else if(exitFormData.get(0).getManagerFormStatus() && exitFormData.get(0).getAdminFormStatus() && exitFormData.get(0).getItFormStatus() && exitFormData.get(0).getFinanceFormStatus() && exitFormData.get(0).getHrFormStatus()){
                dataDto.setEmpStatus(ALL + CLEARANCE + COMPLETED);
            }
        } else {
            if (employeeResignData.getStatus() == 0) {
                dataDto.setEmpStatus(HR + APPROVAL + PENDING);
            } else if (employeeResignData.getStatus() == 1) {
                dataDto.setEmpStatus(MANAGER + APPROVAL + PENDING);
            } else if (employeeResignData.getStatus() == 6) {
                dataDto.setEmpStatus(RETAINED);
            } else if (employeeResignData.getStatus() != 6) {
                dataDto.setEmpStatus(CLEARANCE_TO_BE_INITIATED);
            }
        }
        if (null != employeeResignData.getAbscondTerminateDate()) {
            dataDto.setAbscondTerminateDate(convertDateToLocalDateTime(employeeResignData.getAbscondTerminateDate()).format(FORMATTER_YYYY_MM_DD));
        }
        dataDto.setOtherIssue(employeeResignData.getOtherIssue());
        dataDto.setEligibleForRehire(employeeResignData.getEligibleForRehire());
        if (!employeeResignData.getNoticePeriod().isEmpty()) {
            dataDto.setTentativeLWD(employeeResignData.getNoticePeriod().equalsIgnoreCase(ZERO) ? String.valueOf(employeeResignData.getCreationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()) : String.valueOf(employeeResignData.getCreationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusDays(Long.parseLong(employeeResignData.getNoticePeriod()) - 1)));
        }
        dataDto.setHasCharger(employeeResignData.getHasCharger());
        dataDto.setHasHeadSet(employeeResignData.getHasHeadSet());
        dataDto.setHasHomeMonitor(employeeResignData.getHasHomeMonitor());
        dataDto.setHasHomeDesktop(employeeResignData.getHasHomeDesktop());
        dataDto.setHasLaptop(employeeResignData.getHasLaptop());
        dataDto.setHasMouse(employeeResignData.getHasMouse());
        dataDto.setHasLaptopBag(employeeResignData.getHasLaptopBag());
        dataDto.setOtherAssetsList(employeeResignData.getOtherAssetsList());
        dataDto.setEmpWithdrawComment(employeeResignData.getEmpWithdrawComment());
        dataDto.setHrWithdrawComment(employeeResignData.getHrWithdrawComment());
        if (null != employeeResignData.getItAssetsSubmissionDate()) {
            dataDto.setItAssetsSubmissionDate(convertDateToLocalDateTime(employeeResignData.getItAssetsSubmissionDate()).format(FORMATTER_YYYY_MM_DD));
        }
        dataDto.setTerminationType(employeeResignData.getTerminationType());
        dataDto.setExitStatus(!exitFormData.isEmpty());
        //Set holidays in calendar
        LocalDate now = LocalDate.now();
        List<Holiday> holidayList = HolidayLocalServiceUtil.findHolidaysByYear(now.getYear());
        List<String> holidayDate = holidayList.stream().map(Holiday::getOnDate).map(d -> convertDateToLocalDateTime(d).format(FORMATTER_YYYY_MM_DD)).collect(Collectors.toList());
        request.setAttribute("employeeDetails", dataDto);
        request.setAttribute("holidayDates", holidayDate);
        response.getRenderParameters().setValue(MVCPATH, "/jsp/resignation.jsp");
    }

    @Override
    public void assigneeSubmission(ActionRequest request) {
        long resignationId = ParamUtil.getLong(request, "assigneeResignationId");
        String assigneeEmail = ParamUtil.getString(request, "assigneeEmail");
        Resignation employeeResignData = ResignationLocalServiceUtil.fetchResignation(resignationId);
        Employee employeeData = EmployeeLocalServiceUtil.fetchEmployee(employeeResignData.getEcode());
        Employee assigneeData = EmployeeLocalServiceUtil.findByEmail(assigneeEmail);
        List<ExitForm> exitFormData = ExitFormLocalServiceUtil.findExitFormByResignationId(resignationId);
        employeeResignData.setAssigneeEmail(assigneeEmail);
        employeeResignData.setAssigneeTo(true);
        ResignationLocalServiceUtil.updateResignation(employeeResignData);
        Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
        email.setToAddress(assigneeEmail);
        email.setCcAddress(employeeResignData.getManagerEmail() + COMMA + DL_HR);
        email.setSubject(MessageFormat.format(ASSIGNEE_EMAIL_SUBJECT, employeeData.getEcode(), employeeData.getName()));
        if (!exitFormData.isEmpty()) {
            email.setBody(MessageFormat.format(ASSIGNEE_CLEARANCE_GENERATED_EMAIL_BODY, assigneeData.getName(), employeeData.getName(), employeeData.getEcode(), convertDateToLocalDateTime(employeeResignData.getLastWorkingDate()).format(FORMATTER_YYYY_MM_DD), getPortalUrl() + URL_EXIT_ADMIN));
        } else {
            email.setBody(MessageFormat.format(ASSIGNEE_CLEARANCE_NOT_GENERATED_BODY, assigneeData.getName(), employeeData.getName(), employeeData.getEcode(), getPortalUrl() + URL_EXIT_ADMIN));
        }
        email.setCreatedDate(getIstDate());
        email.setSent(false);
        email.setScheduled(false);
        email.setModule(MODULE_EXIT);
        EmailLocalServiceUtil.addEmail(email);
    }

    @Override
    public String getEmployeeSearchByEcode(ResourceRequest resourceRequest) {
        String employeeDetails = null;
        String empId = ParamUtil.getString(resourceRequest, "ecode").toUpperCase();
        String loggedUserEmail = getLoggedUser(resourceRequest);
        int flag = ParamUtil.getInteger(resourceRequest, "flag");
        EmployeeDto dataDto = new EmployeeDto();
        Employee employeeData = EmployeeLocalServiceUtil.fetchEmployee(empId);
        if (null != employeeData && employeeData.isStatus()) {
            List<ExitState> statusMap = ExitStateLocalServiceUtil.getExitStates(-1, -1);
            List<Resignation> terminateResignedEntry = ResignationLocalServiceUtil.findResignationByEcode(empId).stream()
                    .filter(s -> s.getStatus() == statusMap.get(5).getExitStateKey()).filter(s -> !s.getNoticePeriod().equalsIgnoreCase(ZERO)).collect(Collectors.toList());
            List<Resignation> abscondresignedEntry = ResignationLocalServiceUtil.findResignationByEcode(empId).stream()
                    .filter(s -> s.getStatus() == statusMap.get(4).getExitStateKey()).collect(Collectors.toList());
            List<Resignation> immediateTerminateResignedEntry = ResignationLocalServiceUtil.findResignationByEcode(empId).stream()
                    .filter(s -> s.getStatus() == statusMap.get(5).getExitStateKey()).filter(s -> s.getNoticePeriod().equalsIgnoreCase(ZERO)).collect(Collectors.toList());

            Employee managerData = EmployeeLocalServiceUtil.fetchEmployee(employeeData.getManager());
            if ((flag == 1 && loggedUserEmail.equalsIgnoreCase(managerData.getEmail()) && !loggedUserEmail.equalsIgnoreCase(employeeData.getEmail())) || flag == 2) {
                dataDto.setEmployeeCode(empId);
                dataDto.setEmployeeName(employeeData.getName());
                dataDto.setEmployeeDesignation(employeeData.getDesignation());
                dataDto.setAccount(employeeData.getAccount());
                dataDto.setEmployeeEmail(employeeData.getEmail());
                dataDto.setManagerName(managerData.getName());
                dataDto.setEmployeeBand(employeeData.getBand());
                dataDto.setEmployeeDoj(convertDateToLocalDateTime(employeeData.getDoj()).format(FORMATTER_YYYY_MM_DD));
                dataDto.setLocation(employeeData.getLocation());
                if (!terminateResignedEntry.isEmpty()) {
                    dataDto.setHrComment(terminateResignedEntry.get(0).getHrComment());
                    dataDto.setStatus(statusMap.get(5).getExitStateDescription());
                } else if (!abscondresignedEntry.isEmpty()) {
                    dataDto.setHrComment(abscondresignedEntry.get(0).getHrComment());
                    dataDto.setStatus(statusMap.get(4).getExitStateDescription());
                } else if (!immediateTerminateResignedEntry.isEmpty()) {
                    dataDto.setHrComment(immediateTerminateResignedEntry.get(0).getHrComment());
                    dataDto.setStatus(IMMEDIATE_TERMINATION);
                }
                employeeDetails = convertToJson(dataDto);
            }
        }
        return employeeDetails;
    }

    @Override
    public void saveClearanceForm(ResourceRequest request) {
        long resignationId = ParamUtil.getLong(request, RESIGNATION_ID);
        String roleType = ParamUtil.getString(request, ROLE_TYPE);
        List<ExitForm> exitFormData = ExitFormLocalServiceUtil.findExitFormByResignationId(resignationId);
        long exitFormId = exitFormData.get(0).getId();
        List<ManagerForm> managerformData = ManagerFormLocalServiceUtil.findManagerFormByExitId(exitFormId);
        List<HrForm> hrFormData = HrFormLocalServiceUtil.findHrFormByExitId(exitFormId);
        List<ItForm> itFormData = ItFormLocalServiceUtil.findItFormByExitId(exitFormId);
        List<AdminForm> adminFormData = AdminFormLocalServiceUtil.findAdminFormByExitId(exitFormId);
        List<FinanceForm> financeFormData = FinanceFormLocalServiceUtil.findFinanceFormByExitId(exitFormId);
        if (!financeFormData.isEmpty() && roleType.equalsIgnoreCase(ROLE_FINANCE)) {
            FinanceForm financeForm = financeFormData.get(0);
            financeForm.setLastSalaryPaidDays(ParamUtil.getLong(request, "lastSalaryPaidDays"));
            financeForm.setLastSalaryPaidMonth(ParamUtil.getString(request, "lastSalaryPaidMonth"));
            financeForm.setLastSalaryPaidYear(ParamUtil.getString(request, "lastSalaryPaidYear"));
            financeForm.setTaxProofStatus(ParamUtil.getInteger(request, "taxProofStatus"));
            financeForm.setTaxProofRemark(ParamUtil.getString(request, "taxProofRemark"));
            financeForm.setFoodReimbursementStatus(ParamUtil.getInteger(request, "foodReimbursementStatus"));
            financeForm.setFoodReimbursementAmt(ParamUtil.getString(request, "foodReimbursementAmt"));
            financeForm.setTravelRecoveryStatus(ParamUtil.getInteger(request, "travelRecoveryStatus"));
            financeForm.setTravelRecoveryAmt(ParamUtil.getString(request, "travelRecoveryAmt"));
            financeForm.setHotelRecoveryStatus(ParamUtil.getInteger(request, "hotelRecoveryStatus"));
            financeForm.setHotelRecoveryAmt(ParamUtil.getString(request, "hotelRecoveryAmt"));
            financeForm.setInternationalRecoveryStatus(ParamUtil.getInteger(request, "internationalRecoveryStatus"));
            financeForm.setInternationalRecoveryAmt(ParamUtil.getString(request, "internationalRecoveryAmt"));
            financeForm.setEducationRecoveryStatus(ParamUtil.getInteger(request, "educationRecoveryStatus"));
            financeForm.setEducationRecoveryAmt(ParamUtil.getString(request, "educationRecoveryAmt"));
            financeForm.setJoiningBonusRecoveryStatus(ParamUtil.getInteger(request, "joiningBonusRecoveryStatus"));
            financeForm.setJoiningBonusRecoveryAmt(ParamUtil.getString(request, "joiningBonusRecoveryAmt"));
            financeForm.setNoticePeriodRecoveryStatus(ParamUtil.getInteger(request, "noticePeriodRecoveryStatusFinance"));
            financeForm.setNoticePeriodRecoveryAmt(ParamUtil.getString(request, "noticePeriodRecoveryAmtFinance"));
            FinanceFormLocalServiceUtil.updateFinanceForm(financeForm);
            int totalRows = ParamUtil.getInteger(request, TOTAL_ROWS);
            int reimbursementTotalRows = ParamUtil.getInteger(request, REIMBURSEMENT_TOTAL_ROWS);
            List<RecoveryReimbursement> recoveryReimbursementForm = RecoveryReimbursementLocalServiceUtil.findRecoveryReimbursementFormByDepartmentFormId(financeForm.getId(), DEPARTMENT_FINANCE);
            if (!recoveryReimbursementForm.isEmpty()) {
                for (RecoveryReimbursement recoveryForm : recoveryReimbursementForm) {
                    RecoveryReimbursementLocalServiceUtil.deleteRecoveryReimbursement(recoveryForm);
                }
            }
            if (totalRows > 6) {
                for (int i = 6; i < totalRows; i++) {
                    String recoveryItem = ParamUtil.getString(request, "recoveryItem" + i);
                    int recoveryStatus = ParamUtil.getInteger(request, "recoveryStatus" + i);
                    String recoveryAmount = ParamUtil.getString(request, "recoveryAmount" + i);
                    if (!recoveryItem.isEmpty() || recoveryStatus != 0 || !recoveryAmount.isEmpty()) {
                        RecoveryReimbursement recoveryForm = RecoveryReimbursementLocalServiceUtil.createRecoveryReimbursement(CounterLocalServiceUtil.increment());
                        recoveryForm.setDepartmentFormId(financeForm.getId());
                        recoveryForm.setDepartment(4);
                        recoveryForm.setRecoveryItem(recoveryItem);
                        recoveryForm.setRecoveryStatus(recoveryStatus);
                        recoveryForm.setRecoveryAmount(recoveryAmount);
                        recoveryForm.setRecoveryType(true);
                        RecoveryReimbursementLocalServiceUtil.addRecoveryReimbursement(recoveryForm);
                    }
                }
            }
            if (reimbursementTotalRows > 1) {
                for (int i = 1; i < reimbursementTotalRows; i++) {
                    String reimbursementItem = ParamUtil.getString(request, "reimbursementItem" + i);
                    int reimbursementStatus = ParamUtil.getInteger(request, "reimbursementStatus" + i);
                    String reimbursementAmount = ParamUtil.getString(request, "reimbursementAmount" + i);
                    if (!reimbursementItem.isEmpty() || reimbursementStatus != 0 || !reimbursementAmount.isEmpty()) {
                        RecoveryReimbursement reimbursementForm = RecoveryReimbursementLocalServiceUtil.createRecoveryReimbursement(CounterLocalServiceUtil.increment());
                        reimbursementForm.setDepartmentFormId(financeForm.getId());
                        reimbursementForm.setDepartment(4);
                        reimbursementForm.setReimbursementItem(reimbursementItem);
                        reimbursementForm.setReimbursementStatus(reimbursementStatus);
                        reimbursementForm.setReimbursementAmount(reimbursementAmount);
                        reimbursementForm.setRecoveryType(false);
                        RecoveryReimbursementLocalServiceUtil.addRecoveryReimbursement(reimbursementForm);
                    }
                }
            }
        }
        if (!managerformData.isEmpty() && roleType.equalsIgnoreCase(ROLE_MANAGER)) {
            ManagerForm managerForm = managerformData.get(0);
            managerForm.setTicketNo(ParamUtil.getInteger(request, "ticketNo"));
            managerForm.setTicketNoRemark(ParamUtil.getString(request, "ticketNoRemark"));
            managerForm.setSeparationDocument(ParamUtil.getInteger(request, "separationDocumentManager"));
            managerForm.setSeparationDocumentRemark(ParamUtil.getString(request, "separationDocumentRemarkManager"));
            ManagerFormLocalServiceUtil.updateManagerForm(managerForm);
        }

        if (!hrFormData.isEmpty() && roleType.equalsIgnoreCase(ROLE_HR) && !financeFormData.isEmpty() && !itFormData.isEmpty() && !adminFormData.isEmpty()) {
            HrForm hrForm = hrFormData.get(0);
            AdminForm adminData = adminFormData.get(0);
            List<RecoveryReimbursement> adminRecoveryData = RecoveryReimbursementLocalServiceUtil.findSelectedRecoveryFormByDepartment(adminData.getId(), DEPARTMENT_ADMIN);
            for (RecoveryReimbursement recovery : adminRecoveryData) {
                List<RecoveryReimbursement> adminSelectedRecoveryData = adminRecoveryData.stream().filter(s -> s.getId() == recovery.getId()).collect(Collectors.toList());
                if (!adminSelectedRecoveryData.isEmpty()) {
                    RecoveryReimbursement recoveryData = adminSelectedRecoveryData.get(0);
                    recoveryData.setComment(ParamUtil.getString(request, COMMENT + recovery.getId()));
                    recoveryData.setApproved(ParamUtil.getBoolean(request, APPROVE + recovery.getId()));
                    RecoveryReimbursementLocalServiceUtil.updateRecoveryReimbursement(recoveryData);
                }
            }
            if (adminData.getStationaryRecoveryAmtStatus() == 1) {
                adminData.setStationaryRecoveryApproved(ParamUtil.getBoolean(request, "approvedStationary"));
                adminData.setStationaryRecoveryComment(ParamUtil.getString(request, "commentStationary"));
            }
            if (adminData.getKeysRecoveryAmtStatus() == 1) {
                adminData.setKeyRecoveryApproved(ParamUtil.getBoolean(request, APPROVE + KEYS_NAMESPACE));
                adminData.setKeyRecoveryComment(ParamUtil.getString(request, COMMENT + KEYS_NAMESPACE));
            }
            if (adminData.getLibraryRecoveryAmtStatus() == 1) {
                adminData.setLibraryRecoveryApproved(ParamUtil.getBoolean(request, "approvedLibrary"));
                adminData.setLibraryRecoveryComment(ParamUtil.getString(request, "commentLibrary"));
            }
            if (adminData.getSportsRecoveryAmtStatus() == 1) {
                adminData.setSportsRecoveryApproved(ParamUtil.getBoolean(request, "approvedSportsEquipmentIssued"));
                adminData.setSportsRecoveryComment(ParamUtil.getString(request, "commentSportsEquipmentIssued"));
            }
            if (adminData.getInfraRecoveryAmtStatus() == 1) {
                adminData.setInfraRecoveryApproved(ParamUtil.getBoolean(request, "approvedInfrastructureIssued"));
                adminData.setInfraRecoveryComment(ParamUtil.getString(request, "commentInfrastructureIssued"));
            }
            if (adminData.getLunchRecoveryAmtStatus() == 1) {
                adminData.setLunchRecoveryApproved(ParamUtil.getBoolean(request, "approvedLunchDeduction"));
                adminData.setLunchRecoveryComment(ParamUtil.getString(request, "commentLunchDeduction"));
            }
            if (adminData.getAccessCardRecoveryAmtStatus() == 1) {
                adminData.setAccessCardRecoveryApproved(ParamUtil.getBoolean(request, "approvedAccessCard"));
                adminData.setAccessCardRecoveryComment(ParamUtil.getString(request, "commentAccessCard"));
            }
            if (adminData.getIdentityCardRecoveryStatus() == 1) {
                adminData.setIdentityCardRecoveryApproved(ParamUtil.getBoolean(request, "approvedIdentityCard"));
                adminData.setIdentityCardRecoveryComment(ParamUtil.getString(request, "commentIdentityCard"));
            }
            FinanceForm financeForm = financeFormData.get(0);
            List<RecoveryReimbursement> recoveryFormData = RecoveryReimbursementLocalServiceUtil.findSelectedRecoveryFormByDepartment(financeForm.getId(), DEPARTMENT_FINANCE);
            for (RecoveryReimbursement recovery : recoveryFormData) {
                List<RecoveryReimbursement> financeSelectedRecoveryData = recoveryFormData.stream().filter(s -> s.getId() == recovery.getId()).collect(Collectors.toList());
                if (!financeSelectedRecoveryData.isEmpty()) {
                    RecoveryReimbursement recoveryData = financeSelectedRecoveryData.get(0);
                    recoveryData.setComment(ParamUtil.getString(request, COMMENT_FINANCE + recovery.getId()));
                    recoveryData.setApproved(ParamUtil.getBoolean(request, APPROVED_FINANCE + recovery.getId()));
                    RecoveryReimbursementLocalServiceUtil.updateRecoveryReimbursement(recoveryData);
                }
            }
            if (financeForm.getTravelRecoveryStatus() == 1) {
                financeForm.setTravelRecoveryApproved(ParamUtil.getBoolean(request, APPROVE + RECOVERY_FOR_TRAVEL_NAMESPACE));
                financeForm.setTravelRecoveryComment(ParamUtil.getString(request, COMMENT + RECOVERY_FOR_TRAVEL_NAMESPACE));
            }
            if (financeForm.getHotelRecoveryStatus() == 1) {
                financeForm.setHotelRecoveryApproved(ParamUtil.getBoolean(request, APPROVE + RECOVERY_FOR_HOTEL_NAMESPACE));
                financeForm.setHotelRecoveryComment(ParamUtil.getString(request, COMMENT + RECOVERY_FOR_HOTEL_NAMESPACE));
            }
            if (financeForm.getInternationalRecoveryStatus() == 1) {
                financeForm.setInternationalRecoveryApproved(ParamUtil.getBoolean(request, APPROVE + RECOVERY_FOR_INTERNATIONAL_TRAVEL_NAMESPACE));
                financeForm.setInternationalRecoveryComment(ParamUtil.getString(request, COMMENT + RECOVERY_FOR_INTERNATIONAL_TRAVEL_NAMESPACE));
            }
            if (financeForm.getEducationRecoveryStatus() == 1) {
                financeForm.setEducationRecoveryApproved(ParamUtil.getBoolean(request, APPROVE + RECOVERY_FOR_EDUCATION_NAMESPACE));
                financeForm.setEducationRecoveryComment(ParamUtil.getString(request, COMMENT + RECOVERY_FOR_EDUCATION_NAMESPACE));
            }
            if (financeForm.getJoiningBonusRecoveryStatus() == 1) {
                financeForm.setJoiningRecoveryApproved(ParamUtil.getBoolean(request, APPROVE + RECOVERY_FOR_BONUS_NAMESPACE));
                financeForm.setJoiningRecoveryComment(ParamUtil.getString(request, COMMENT + RECOVERY_FOR_BONUS_NAMESPACE));
            }
            if (financeForm.getNoticePeriodRecoveryStatus() == 1) {
                financeForm.setNoticePeriodRecoveryApproved(ParamUtil.getBoolean(request, APPROVE + RECOVERY_FOR_NOTICE_PERIOD_NAMESPACE));
                financeForm.setNoticePeriodRecoveryComment(ParamUtil.getString(request, COMMENT + RECOVERY_FOR_NOTICE_PERIOD_NAMESPACE));
            }

            ItForm itData = itFormData.get(0);
            List<RecoveryReimbursement> itRecoveryData = RecoveryReimbursementLocalServiceUtil.findSelectedRecoveryFormByDepartment(itData.getId(), DEPARTMENT_IT);
            for (RecoveryReimbursement recovery : itRecoveryData) {
                List<RecoveryReimbursement> itSelectedRecoveryData = itRecoveryData.stream().filter(s -> s.getId() == recovery.getId()).collect(Collectors.toList());
                if (!itSelectedRecoveryData.isEmpty()) {
                    RecoveryReimbursement recoveryData = itSelectedRecoveryData.get(0);
                    recoveryData.setComment(ParamUtil.getString(request, COMMENT_IT + recovery.getId()));
                    recoveryData.setApproved(ParamUtil.getBoolean(request, APPROVE_IT + recovery.getId()));
                    RecoveryReimbursementLocalServiceUtil.updateRecoveryReimbursement(recoveryData);
                }
            }
            if (itData.getSystemRecoveryAmtStatus() == 1) {
                itData.setSystemRecoveryApproved(ParamUtil.getBoolean(request, APPROVE_IT + DESKTOP_NAMESPACE));
                itData.setSystemRecoveryComment(ParamUtil.getString(request, COMMENT_IT + DESKTOP_NAMESPACE));
            }
            if (itData.getLaptopRecoveryAmtStatus() == 1) {
                itData.setLaptopRecoveryApproved(ParamUtil.getBoolean(request, APPROVE_IT + LAPTOP_NAMESPACE));
                itData.setLaptopRecoveryComment(ParamUtil.getString(request, COMMENT_IT + LAPTOP_NAMESPACE));
            }
            if (itData.getCommunicationRecoveryStatus() == 1) {
                itData.setCommunicationRecoveryApproved(ParamUtil.getBoolean(request, APPROVE_IT + TELEPHONE_NAMESPACE));
                itData.setCommunicationRecoveryComment(ParamUtil.getString(request, COMMENT_IT + TELEPHONE_NAMESPACE));
            }
            if (itData.getHeadphoneRecoveryAmtStatus() == 1) {
                itData.setHeadphoneRecoveryApproved(ParamUtil.getBoolean(request, APPROVE_IT + HEADPHONE_NAMESPACE));
                itData.setHeadphoneRecoveryComment(ParamUtil.getString(request, COMMENT_IT + HEADPHONE_NAMESPACE));

            }
            if (itData.getLaptopBagRecoveryAmtStatus() == 1) {
                itData.setLaptopBagRecoveryApproved(ParamUtil.getBoolean(request, APPROVE_IT + LAPTOP_BAG_NAMESPACE));
                itData.setLaptopBagRecoveryComment(ParamUtil.getString(request, COMMENT_IT + LAPTOP_BAG_NAMESPACE));
            }
            if (itData.getMonitorRecoveryAmtStatus() == 1) {
                itData.setMonitorRecoveryApproved(ParamUtil.getBoolean(request, APPROVE_IT + MONITOR_NAMESPACE));
                itData.setMonitorRecoveryComment(ParamUtil.getString(request, COMMENT_IT + MONITOR_NAMESPACE));
            }
            if (itData.getChargerRecoveryAmtStatus() == 1) {
                itData.setChargerRecoveryApproved(ParamUtil.getBoolean(request, APPROVE_IT + CHARGER_NAMESPACE));
                itData.setChargerRecoveryComment(ParamUtil.getString(request, COMMENT_IT + CHARGER_NAMESPACE));
            }
            if (itData.getMouseRecoveryAmtStatus() == 1) {
                itData.setMouseRecoveryApproved(ParamUtil.getBoolean(request, APPROVE_IT + MOUSE_NAMESPACE));
                itData.setMouseRecoveryComment(ParamUtil.getString(request, COMMENT_IT + MOUSE_NAMESPACE));
            }
            hrForm.setFoodOption(ParamUtil.getLong(request, "foodOption"));
            hrForm.setFoodOptionRemark(ParamUtil.getString(request, "foodOptionRemark"));
            hrForm.setInductionFeedbackStatus(ParamUtil.getInteger(request, "inductionFeedbackStatus"));
            hrForm.setInductionFeedbackRemark(ParamUtil.getString(request, "inductionFeedbackRemark"));
            hrForm.setInductionQuizStatus(ParamUtil.getInteger(request, "inductionQuizStatus"));
            hrForm.setInductionQuizRemark(ParamUtil.getString(request, "inductionQuizRemark"));
            hrForm.setTrainingFeedbackStatus(ParamUtil.getInteger(request, "trainingFeedbackStatus"));
            hrForm.setTrainingFeedbackRemark(ParamUtil.getString(request, "trainingFeedbackRemark"));
            hrForm.setExitInterviewStatus(ParamUtil.getInteger(request, "exitInterviewStatus"));
            hrForm.setExitInterviewRemark(ParamUtil.getString(request, "exitInterviewRemark"));
            hrForm.setEmployeeDirectoryStatus(ParamUtil.getInteger(request, "employeeDirectoryStatus"));
            hrForm.setEmployeeDirectoryRemark(ParamUtil.getString(request, "employeeDirectoryRemark"));
            hrForm.setLmsStatus(ParamUtil.getInteger(request, "lmsStatus"));
            hrForm.setHrRemark(ParamUtil.getString(request, "hrRemarks"));
            hrForm.setLmsRemark(ParamUtil.getString(request, "lmsRemark"));
            hrForm.setVantageCircleStatus(ParamUtil.getInteger(request, "vantageCircleStatus"));
            hrForm.setVantageCircleRemark(ParamUtil.getString(request, "vantageCircleRemark"));
            hrForm.setBirthdaySynergyStatus(ParamUtil.getInteger(request, "birthdaySynergyStatus"));
            hrForm.setBirthdaySynergyRemark(ParamUtil.getString(request, "birthdaySynergyRemark"));
            hrForm.setExperienceLetterStatus(ParamUtil.getInteger(request, "experienceLetterStatus"));
            hrForm.setExperienceLetterRemark(ParamUtil.getString(request, "experienceLetterRemark"));
            hrForm.setNdaFormStatus(ParamUtil.getInteger(request, "ndaFormStatus"));
            hrForm.setNdaFormRemark(ParamUtil.getString(request, "ndaFormRemark"));
            hrForm.setSeparationDocumentStatus(ParamUtil.getInteger(request, "separationDocumentStatus"));
            hrForm.setSeparationDocumentRemark(ParamUtil.getString(request, "separationDocumentRemarkHR"));
            hrForm.setTrainingAgreementAmt(ParamUtil.getString(request, "trainingAgreementAmt"));
            hrForm.setTrainingAgreementStatus(ParamUtil.getInteger(request, "trainingAgreementStatus"));
            hrForm.setRecoverableBonusAmt(ParamUtil.getString(request, "recoverableBonusAmt"));
            hrForm.setRecoverableBonusStatus(ParamUtil.getInteger(request, "recoverableBonusStatus"));
            hrForm.setNoticePeriodRecoveryAmt(ParamUtil.getString(request, "noticePeriodRecoveryAmt"));
            hrForm.setNoticePeriodRecoveryStatus(ParamUtil.getInteger(request, "noticePeriodRecoveryStatus"));
            hrForm.setLeavesMonth1(ParamUtil.getString(request, "leavesMonth1"));
            hrForm.setLeavesMonth2(ParamUtil.getString(request, "leavesMonth2"));
            hrForm.setLeavesMonth3(ParamUtil.getString(request, "leavesMonth3"));
            hrForm.setLeaveDaysMonth1(ParamUtil.getString(request, "leaveDaysMonth1"));
            hrForm.setLeaveDaysMonth2(ParamUtil.getString(request, "leaveDaysMonth2"));
            hrForm.setLeaveDaysMonth3(ParamUtil.getString(request, "leaveDaysMonth3"));
            hrForm.setLeaveDateMonth1(ParamUtil.getString(request, "leaveDateMonth1"));
            hrForm.setLeaveDateMonth2(ParamUtil.getString(request, "leaveDateMonth2"));
            hrForm.setLeaveDateMonth3(ParamUtil.getString(request, "leaveDateMonth3"));
            hrForm.setLopMonth1(ParamUtil.getString(request, "lopMonth1"));
            hrForm.setLopMonth2(ParamUtil.getString(request, "lopMonth2"));
            hrForm.setLopMonth3(ParamUtil.getString(request, "lopMonth3"));
            hrForm.setLopDaysMonth1(ParamUtil.getString(request, "lopDaysMonth1"));
            hrForm.setLopDaysMonth2(ParamUtil.getString(request, "lopDaysMonth2"));
            hrForm.setLopDaysMonth3(ParamUtil.getString(request, "lopDaysMonth3"));
            hrForm.setLopDateMonth1(ParamUtil.getString(request, "lopDateMonth1"));
            hrForm.setLopDateMonth2(ParamUtil.getString(request, "lopDateMonth2"));
            hrForm.setLopDateMonth3(ParamUtil.getString(request, "lopDateMonth3"));
            hrForm.setEarnedLeaveBalance(ParamUtil.getString(request, "earnedLeaveBalance"));
            HrFormLocalServiceUtil.updateHrForm(hrForm);
            AdminFormLocalServiceUtil.updateAdminForm(adminData);
            FinanceFormLocalServiceUtil.updateFinanceForm(financeForm);
            ItFormLocalServiceUtil.updateItForm(itData);
            int hrTotalRows = ParamUtil.getInteger(request, "hrTotalRows");
            List<RecoveryReimbursement> recoveryReimbursementForm = RecoveryReimbursementLocalServiceUtil.findAllRecoveryFormByDepartment(hrForm.getId(), DEPARTMENT_HR);
            if (!recoveryReimbursementForm.isEmpty()) {
                for (RecoveryReimbursement recoveryForm : recoveryReimbursementForm) {
                    RecoveryReimbursementLocalServiceUtil.deleteRecoveryReimbursement(recoveryForm);
                }
            }
            if (hrTotalRows > 3) {
                for (int i = 3; i < hrTotalRows; i++) {
                    String hrItem = ParamUtil.getString(request, "hrItem" + i);
                    int hrRecoveryStatus = ParamUtil.getInteger(request, "hrRecoveryStatus" + i);
                    String hrRecoveryAmount = ParamUtil.getString(request, "hrRecoveryAmount" + i);
                    if (!hrItem.isEmpty() || hrRecoveryStatus != 0 || !hrRecoveryAmount.isEmpty()) {
                        RecoveryReimbursement recoveryForm = RecoveryReimbursementLocalServiceUtil.createRecoveryReimbursement(CounterLocalServiceUtil.increment());
                        recoveryForm.setDepartmentFormId(hrForm.getId());
                        recoveryForm.setDepartment(5);
                        recoveryForm.setRecoveryItem(hrItem);
                        recoveryForm.setRecoveryStatus(hrRecoveryStatus);
                        recoveryForm.setRecoveryAmount(hrRecoveryAmount);
                        recoveryForm.setRecoveryType(true);
                        RecoveryReimbursementLocalServiceUtil.addRecoveryReimbursement(recoveryForm);
                    }
                }
            }
        }
        if (!itFormData.isEmpty() && roleType.equalsIgnoreCase(ROLE_IT)) {
            ItForm itForm = itFormData.get(0);
            itForm.setTicketNo(ParamUtil.getInteger(request, IT_TICKET));
            itForm.setTicketNoRemark(ParamUtil.getString(request, IT_TICKET_REMARK));
            itForm.setMailPst(ParamUtil.getInteger(request, "mailPst"));
            itForm.setMailPstRemark(ParamUtil.getString(request, "mailPstRemarkIT"));
            itForm.setDownloadFolder(ParamUtil.getInteger(request, "downloadFolder"));
            itForm.setDownloadFolderRemark(ParamUtil.getString(request, "downloadFolderRemarkIT"));
            itForm.setDocumentFolder(ParamUtil.getInteger(request, "documentFolder"));
            itForm.setDocumentFolderRemark(ParamUtil.getString(request, "documentFolderRemarkIT"));
            itForm.setOtherData(ParamUtil.getInteger(request, "otherData"));
            itForm.setOtherDataRemark(ParamUtil.getString(request, "otherDataRemarkIT"));
            itForm.setGoogleDrive(ParamUtil.getInteger(request, "googleDrive"));
            itForm.setGoogleDriveRemark(ParamUtil.getString(request, "googleDriveRemarkIT"));
            itForm.setClientSystemRecovered(ParamUtil.getInteger(request, "clientSystemRecovered"));
            itForm.setClientSystemRecoveredRemark(ParamUtil.getString(request, "clientSystemRecoveredRemark"));
            itForm.setOthers(ParamUtil.getInteger(request, "others"));
            itForm.setOthersRemark(ParamUtil.getString(request, "othersRemarkIT"));
            itForm.setMailForwarding(ParamUtil.getInteger(request, "mailForwarding"));
            itForm.setMailForwardingRemark(ParamUtil.getString(request, "mailForwardingRemarkIT"));
            itForm.setEmailDisable(ParamUtil.getInteger(request, "emailDisable"));
            itForm.setEmailDisableRemark(ParamUtil.getString(request, "emailDisableRemark"));
            itForm.setSystemRecovered(ParamUtil.getInteger(request, "systemRecovered"));
            itForm.setSystemRecoveredRemark(ParamUtil.getString(request, "systemRecoveredRemark"));
            itForm.setAccessCardDisable(ParamUtil.getInteger(request, "accessCardDisable"));
            itForm.setAccessCardDisableRemark(ParamUtil.getString(request, "accessCardDisableRemark"));
            itForm.setSeparationDocument(ParamUtil.getInteger(request, "separationDocument"));
            itForm.setSeparationDocumentRemark(ParamUtil.getString(request, "separationDocumentRemark"));
            itForm.setSystemRecoveryAmt(ParamUtil.getString(request, "systemRecoveryAmt"));
            itForm.setSystemRecoveryAmtStatus(ParamUtil.getInteger(request, "systemRecoveryAmtStatus"));
            itForm.setLaptopRecoveryAmt(ParamUtil.getString(request, "laptopRecoveryAmt"));
            itForm.setLaptopRecoveryAmtStatus(ParamUtil.getInteger(request, "laptopRecoveryAmtStatus"));
            itForm.setMouseRecoveryAmt(ParamUtil.getString(request, "mouseRecoveryAmt"));
            itForm.setMouseRecoveryAmtStatus(ParamUtil.getInteger(request, "mouseRecoveryAmtStatus"));
            itForm.setMonitorRecoveryAmt(ParamUtil.getString(request, "monitorAmt"));
            itForm.setMonitorRecoveryAmtStatus(ParamUtil.getInteger(request, "monitorRecoveryAmtStatus"));
            itForm.setChargerRecoveryAmt(ParamUtil.getString(request, "chargerAmt"));
            itForm.setChargerRecoveryAmtStatus(ParamUtil.getInteger(request, "chargerRecoveryAmtStatus"));
            itForm.setCommunicationRecoveryAmt(ParamUtil.getString(request, "communicationRecoveryAmt"));
            itForm.setCommunicationRecoveryStatus(ParamUtil.getInteger(request, "communicationRecoveryStatus"));
            itForm.setHeadphoneRecoveryAmt(ParamUtil.getString(request, "headphoneRecoveryAmt"));
            itForm.setHeadphoneRecoveryAmtStatus(ParamUtil.getInteger(request, "headphoneRecoveryAmtStatus"));
            itForm.setLaptopBagRecoveryAmt(ParamUtil.getString(request, "laptopBagRecoveryAmt"));
            itForm.setLaptopBagRecoveryAmtStatus(ParamUtil.getInteger(request, "laptopBagRecoveryAmtStatus"));
            ItFormLocalServiceUtil.updateItForm(itForm);
            int itTotalRows = ParamUtil.getInteger(request, "itTotalRows");
            List<RecoveryReimbursement> recoveryReimbursementForm = RecoveryReimbursementLocalServiceUtil.findAllRecoveryFormByDepartment(itForm.getId(), DEPARTMENT_IT);
            if (!recoveryReimbursementForm.isEmpty()) {
                for (RecoveryReimbursement recoveryForm : recoveryReimbursementForm) {
                    RecoveryReimbursementLocalServiceUtil.deleteRecoveryReimbursement(recoveryForm);
                }
            }
            if (itTotalRows > 8) {
                for (int i = 8; i < itTotalRows; i++) {
                    String itItem = ParamUtil.getString(request, "itItem" + i);
                    int itRecoveryStatus = ParamUtil.getInteger(request, "itRecoveryStatus" + i);
                    String itRecoveryAmount = ParamUtil.getString(request, "itRecoveryAmount" + i);
                    if (!itItem.isEmpty() || itRecoveryStatus != 0 || !itRecoveryAmount.isEmpty()) {
                        RecoveryReimbursement recoveryForm = RecoveryReimbursementLocalServiceUtil.createRecoveryReimbursement(CounterLocalServiceUtil.increment());
                        recoveryForm.setDepartmentFormId(itForm.getId());
                        recoveryForm.setDepartment(2);
                        recoveryForm.setRecoveryItem(itItem);
                        recoveryForm.setRecoveryStatus(itRecoveryStatus);
                        recoveryForm.setRecoveryAmount(itRecoveryAmount);
                        recoveryForm.setRecoveryType(true);
                        RecoveryReimbursementLocalServiceUtil.addRecoveryReimbursement(recoveryForm);
                    }
                }
            }
        }
        if (!adminFormData.isEmpty() && roleType.equalsIgnoreCase(ROLE_ADMIN)) {
            AdminForm adminForm = adminFormData.get(0);
            adminForm.setStationaryRecoveryAmt(ParamUtil.getString(request, "stationaryRecoveryAmt"));
            adminForm.setStationaryRecoveryAmtStatus(ParamUtil.getInteger(request, "stationaryRecoveryAmtStatus"));
            adminForm.setKeysRecoveryAmt(ParamUtil.getString(request, "keysRecoveryAmt"));
            adminForm.setKeysRecoveryAmtStatus(ParamUtil.getInteger(request, "keysRecoveryAmtStatus"));
            adminForm.setLibraryRecoveryAmt(ParamUtil.getString(request, "libraryRecoveryAmt"));
            adminForm.setLibraryRecoveryAmtStatus(ParamUtil.getInteger(request, "libraryRecoveryAmtStatus"));
            adminForm.setInfraRecoveryAmt(ParamUtil.getString(request, "infrastructureIssuedAmt"));
            adminForm.setInfraRecoveryAmtStatus(ParamUtil.getInteger(request, "infrastructureIssuedAmtStatus"));
            adminForm.setLunchRecoveryAmt(ParamUtil.getString(request, "lunchDeductionAmt"));
            adminForm.setLunchRecoveryAmtStatus(ParamUtil.getInteger(request, LUNCH_DEDUCTION_AMOUNT_STATUS));
            adminForm.setSportsRecoveryAmt(ParamUtil.getString(request, "sportsRecoveryAmt"));
            adminForm.setSportsRecoveryAmtStatus(ParamUtil.getInteger(request, "sportsRecoveryAmtStatus"));
            adminForm.setAccessCardRecoveryAmt(ParamUtil.getString(request, "accessCardRecoveryAmt"));
            adminForm.setAccessCardRecoveryAmtStatus(ParamUtil.getInteger(request, "accessCardRecoveryAmtStatus"));
            adminForm.setIdentityCardRecoveryAmt(ParamUtil.getString(request, "identityCardAmt"));
            adminForm.setIdentityCardRecoveryStatus(ParamUtil.getInteger(request, "identityCardStatus"));
            AdminFormLocalServiceUtil.updateAdminForm(adminForm);
            int adminTotalRows = ParamUtil.getInteger(request, "adminTotalRows");
            List<RecoveryReimbursement> recoveryReimbursementForm = RecoveryReimbursementLocalServiceUtil.findAllRecoveryFormByDepartment(adminForm.getId(), DEPARTMENT_ADMIN);
            if (!recoveryReimbursementForm.isEmpty()) {
                for (RecoveryReimbursement recoveryForm : recoveryReimbursementForm) {
                    RecoveryReimbursementLocalServiceUtil.deleteRecoveryReimbursement(recoveryForm);
                }
            }
            if (adminTotalRows > 8) {
                for (int i = 8; i < adminTotalRows; i++) {
                    String adminItem = ParamUtil.getString(request, "adminItem" + i);
                    int adminRecoveryStatus = ParamUtil.getInteger(request, "adminRecoveryStatus" + i);
                    String adminRecoveryAmount = ParamUtil.getString(request, "adminRecoveryAmount" + i);
                    if (!adminItem.isEmpty() || adminRecoveryStatus != 0 || !adminRecoveryAmount.isEmpty()) {
                        RecoveryReimbursement recoveryForm = RecoveryReimbursementLocalServiceUtil.createRecoveryReimbursement(CounterLocalServiceUtil.increment());
                        recoveryForm.setDepartmentFormId(adminForm.getId());
                        recoveryForm.setDepartment(3);
                        recoveryForm.setRecoveryItem(adminItem);
                        recoveryForm.setRecoveryStatus(adminRecoveryStatus);
                        recoveryForm.setRecoveryAmount(adminRecoveryAmount);
                        recoveryForm.setRecoveryType(true);
                        RecoveryReimbursementLocalServiceUtil.addRecoveryReimbursement(recoveryForm);
                    }
                }
            }
        }
    }

    @Override
    public String getUserManualObject(ResourceRequest request) {
        int roleAction = ParamUtil.getInteger(request, "roleAction");
        String role;
        switch (roleAction) {
            case 1:
                role = ROLE_MANAGER;
                break;
            case 2:
                role = ROLE_IT;
                break;
            case 3:
                role = ROLE_ADMIN;
                break;
            case 4:
                role = ROLE_FINANCE;
                break;
            default:
                role = ROLE_HR;
        }
        return "\"" + new String(getUserManual(MODULE_EXIT, role).getBytes(), StandardCharsets.UTF_8) + "\"";
    }

    private String getYesNoNaForInteger(int value) {
        String returnValue = NA;
        if (value == 1) {
            returnValue = YES;
        } else if (value == 2) {
            returnValue = NO;
        }
        return returnValue;
    }

    private String getYesNoForInteger(int value) {
        if (value == 1) {
            return YES;
        } else {
            return NO;
        }
    }

    private String checkString(String value) {
        return value.replace("&", " And ").replace("'", " ").replace("\"", "").replace(";", " ").replace(">", BLANK).replace("<", BLANK);
    }

    private String generateHtmlString(String resignationFormId) {

        StringBuilder recoveryFormContent = new StringBuilder();
        StringBuilder reimbursementContent = new StringBuilder();
        StringBuilder iTFormContent = new StringBuilder();
        StringBuilder adminFormContent = new StringBuilder();
        StringBuilder hrFormContent = new StringBuilder();
        String htmlPdfString = BLANK;
        Resignation empResignationDetails = ResignationLocalServiceUtil.fetchResignation(Long.parseLong(resignationFormId));
        Employee employeeData = EmployeeLocalServiceUtil.fetchEmployee(empResignationDetails.getEcode());
        Employee managerDetail = null;
        if (null != employeeData) {
            managerDetail = EmployeeLocalServiceUtil.fetchEmployee(employeeData.getManager());
        }
        List<ExitForm> exitFormData = ExitFormLocalServiceUtil.findExitFormByResignationId(Long.parseLong(resignationFormId));
        if (!exitFormData.isEmpty()) {
            long exitFormId = exitFormData.get(0).getId();
            List<ManagerForm> managerformData = ManagerFormLocalServiceUtil.findManagerFormByExitId(exitFormId);
            List<HrForm> hrFormData = HrFormLocalServiceUtil.findHrFormByExitId(exitFormId);
            List<ItForm> itFormData = ItFormLocalServiceUtil.findItFormByExitId(exitFormId);
            List<AdminForm> adminFormData = AdminFormLocalServiceUtil.findAdminFormByExitId(exitFormId);
            List<FinanceForm> financeFormData = FinanceFormLocalServiceUtil.findFinanceFormByExitId(exitFormId);
            List<RecoveryReimbursement> recoveryReimbursementForms = RecoveryReimbursementLocalServiceUtil.findRecoveryReimbursementFormByDepartmentFormId(financeFormData.get(0).getId(), DEPARTMENT_FINANCE);
            if (!managerformData.isEmpty() && !hrFormData.isEmpty() && !itFormData.isEmpty() && !adminFormData.isEmpty() && !financeFormData.isEmpty()) {
                boolean managerSubmitted = exitFormData.get(0).getManagerFormStatus();
                boolean hrSubmitted = exitFormData.get(0).getHrFormStatus();
                boolean itSubmitted = exitFormData.get(0).getItFormStatus();
                boolean adminSubmitted = exitFormData.get(0).getAdminFormStatus();
                boolean financeSubmitted = exitFormData.get(0).getFinanceFormStatus();
                ManagerForm managerData = managerformData.get(0);
                HrForm hrData = hrFormData.get(0);
                ItForm itData = itFormData.get(0);
                AdminForm adminData = adminFormData.get(0);
                FinanceForm finanaceData = financeFormData.get(0);
                int recSno = 97;
                int remSno = 98;
                int hrSno = 100;
                List<RecoveryReimbursement> hrRecoveryData = RecoveryReimbursementLocalServiceUtil.findSelectedRecoveryFormByDepartment(itData.getId(), DEPARTMENT_HR);
                for (RecoveryReimbursement recovery : hrRecoveryData) {
                    if (recovery.getApproved()) {
                        hrFormContent.append(ROW_START1);
                        hrFormContent.append((char) hrSno).append(")");
                        hrFormContent.append(CELL_SEPERATOR1);
                        hrFormContent.append(checkString(recovery.getRecoveryItem()));
                        hrFormContent.append(CELL_SEPERATOR1);
                        hrFormContent.append(getYesNoNaForInteger(recovery.getRecoveryStatus()));
                        hrFormContent.append(CELL_SEPERATOR2);
                        hrFormContent.append("<b>" + recovery.getRecoveryAmount() + "</b>");
                        hrFormContent.append(ROW_END);
                        hrSno++;
                    }
                }
                if (finanaceData.getTravelRecoveryStatus() == 1 && finanaceData.getTravelRecoveryApproved()) {
                    recoveryFormContent.append(ROW_START1);
                    recoveryFormContent.append((char) recSno).append(")");
                    recoveryFormContent.append(CELL_SEPERATOR1);
                    recoveryFormContent.append(RECOVERY_FOR_TRAVEL);
                    recoveryFormContent.append(CELL_SEPERATOR1);
                    recoveryFormContent.append(getYesNoNaForInteger(finanaceData.getTravelRecoveryStatus()));
                    recoveryFormContent.append(CELL_SEPERATOR2);
                    recoveryFormContent.append("<b>" + finanaceData.getTravelRecoveryAmt() + "</b>");
                    recoveryFormContent.append(ROW_END);
                    recSno++;
                }
                if (finanaceData.getHotelRecoveryStatus() == 1 && finanaceData.getHotelRecoveryApproved()) {
                    recoveryFormContent.append(ROW_START1);
                    recoveryFormContent.append((char) recSno).append(")");
                    recoveryFormContent.append(CELL_SEPERATOR1);
                    recoveryFormContent.append(RECOVERY_FOR_HOTEL);
                    recoveryFormContent.append(CELL_SEPERATOR1);
                    recoveryFormContent.append(getYesNoNaForInteger(finanaceData.getHotelRecoveryStatus()));
                    recoveryFormContent.append(CELL_SEPERATOR2);
                    recoveryFormContent.append("<b>" + finanaceData.getHotelRecoveryAmt() + "</b>");
                    recoveryFormContent.append(ROW_END);
                    recSno++;
                }
                if (finanaceData.getInternationalRecoveryStatus() == 1 && finanaceData.getInternationalRecoveryApproved()) {
                    recoveryFormContent.append(ROW_START1);
                    recoveryFormContent.append((char) recSno).append(")");
                    recoveryFormContent.append(CELL_SEPERATOR1);
                    recoveryFormContent.append(RECOVERY_FOR_INTERNATIONAL_TRAVEL);
                    recoveryFormContent.append(CELL_SEPERATOR1);
                    recoveryFormContent.append(getYesNoNaForInteger(finanaceData.getInternationalRecoveryStatus()));
                    recoveryFormContent.append(CELL_SEPERATOR2);
                    recoveryFormContent.append("<b>" + finanaceData.getInternationalRecoveryAmt() + "</b>");
                    recoveryFormContent.append(ROW_END);
                    recSno++;
                }
                if (finanaceData.getEducationRecoveryStatus() == 1 && finanaceData.getEducationRecoveryApproved()) {
                    recoveryFormContent.append(ROW_START1);
                    recoveryFormContent.append((char) recSno).append(")");
                    recoveryFormContent.append(CELL_SEPERATOR1);
                    recoveryFormContent.append(RECOVERY_FOR_EDUCATION);
                    recoveryFormContent.append(CELL_SEPERATOR1);
                    recoveryFormContent.append(getYesNoNaForInteger(finanaceData.getEducationRecoveryStatus()));
                    recoveryFormContent.append(CELL_SEPERATOR2);
                    recoveryFormContent.append("<b>" + finanaceData.getEducationRecoveryAmt() + "</b>");
                    recoveryFormContent.append(ROW_END);
                    recSno++;
                }
                if (finanaceData.getJoiningBonusRecoveryStatus() == 1 && finanaceData.getJoiningRecoveryApproved()) {
                    recoveryFormContent.append(ROW_START1);
                    recoveryFormContent.append((char) recSno).append(")");
                    recoveryFormContent.append(CELL_SEPERATOR1);
                    recoveryFormContent.append(RECOVERY_FOR_BONUS);
                    recoveryFormContent.append(CELL_SEPERATOR1);
                    recoveryFormContent.append(getYesNoNaForInteger(finanaceData.getJoiningBonusRecoveryStatus()));
                    recoveryFormContent.append(CELL_SEPERATOR2);
                    recoveryFormContent.append("<b>" + finanaceData.getJoiningBonusRecoveryAmt() + "</b>");
                    recoveryFormContent.append(ROW_END);
                    recSno++;
                }
                if (finanaceData.getNoticePeriodRecoveryStatus() == 1 && finanaceData.getNoticePeriodRecoveryApproved()) {
                    recoveryFormContent.append(ROW_START1);
                    recoveryFormContent.append((char) recSno).append(")");
                    recoveryFormContent.append(CELL_SEPERATOR1);
                    recoveryFormContent.append(RECOVERY_FOR_NOTICE_PERIOD);
                    recoveryFormContent.append(CELL_SEPERATOR1);
                    recoveryFormContent.append(getYesNoNaForInteger(finanaceData.getNoticePeriodRecoveryStatus()));
                    recoveryFormContent.append(CELL_SEPERATOR2);
                    recoveryFormContent.append("<b>").append(finanaceData.getNoticePeriodRecoveryAmt()).append("</b>");
                    recoveryFormContent.append(ROW_END);
                    recSno++;
                }
                for (RecoveryReimbursement recoveryReimbursementForm : recoveryReimbursementForms) {
                    if (recoveryReimbursementForm.getRecoveryType() && recoveryReimbursementForm.getApproved() && recoveryReimbursementForm.getRecoveryStatus() == 1) {
                        recoveryFormContent.append(ROW_START1);
                        recoveryFormContent.append((char) recSno).append(")");
                        recoveryFormContent.append(CELL_SEPERATOR1);
                        recoveryFormContent.append(checkString(recoveryReimbursementForm.getRecoveryItem()));
                        recoveryFormContent.append(CELL_SEPERATOR1);
                        recoveryFormContent.append(getYesNoNaForInteger(recoveryReimbursementForm.getRecoveryStatus()));
                        recoveryFormContent.append(CELL_SEPERATOR2);
                        recoveryFormContent.append("<b>" + recoveryReimbursementForm.getRecoveryAmount() + "</b>");
                        recoveryFormContent.append(ROW_END);
                        recSno++;
                    } else if (!recoveryReimbursementForm.getRecoveryType()) {
                        reimbursementContent.append(ROW_START1);
                        reimbursementContent.append((char) remSno).append(")");
                        reimbursementContent.append(CELL_SEPERATOR1);
                        reimbursementContent.append(checkString(recoveryReimbursementForm.getReimbursementItem()));
                        reimbursementContent.append(CELL_SEPERATOR1);
                        reimbursementContent.append(getYesNoNaForInteger(recoveryReimbursementForm.getReimbursementStatus()));
                        reimbursementContent.append(CELL_SEPERATOR2);
                        reimbursementContent.append("<b>" + recoveryReimbursementForm.getReimbursementAmount() + "</b>");
                        reimbursementContent.append(ROW_END);
                        remSno++;
                    }
                }
                int iTSno = 97;
                List<RecoveryReimbursement> itRecoveryData = RecoveryReimbursementLocalServiceUtil.findSelectedRecoveryFormByDepartment(itData.getId(), DEPARTMENT_IT);

                if (itData.getSystemRecoveryAmtStatus() == 1 && itData.getSystemRecoveryApproved()) {
                    iTFormContent.append(ROW_START1);
                    iTFormContent.append((char) iTSno).append(")");
                    iTFormContent.append(CELL_SEPERATOR1);
                    iTFormContent.append(DESKTOP);
                    iTFormContent.append(CELL_SEPERATOR1);
                    iTFormContent.append(getYesNoNaForInteger(itData.getSystemRecoveryAmtStatus()));
                    iTFormContent.append(CELL_SEPERATOR2);
                    iTFormContent.append("<b>" + itData.getSystemRecoveryAmt() + "</b>");
                    iTFormContent.append(ROW_END);
                    iTSno++;

                }
                if (itData.getLaptopRecoveryAmtStatus() == 1 && itData.getLaptopRecoveryApproved()) {
                    iTFormContent.append(ROW_START1);
                    iTFormContent.append((char) iTSno).append(")");
                    iTFormContent.append(CELL_SEPERATOR1);
                    iTFormContent.append(LAPTOP);
                    iTFormContent.append(CELL_SEPERATOR1);
                    iTFormContent.append(getYesNoNaForInteger(itData.getLaptopRecoveryAmtStatus()));
                    iTFormContent.append(CELL_SEPERATOR2);
                    iTFormContent.append("<b>" + itData.getLaptopRecoveryAmt() + "</b>");
                    iTFormContent.append(ROW_END);
                    iTSno++;
                }
                if (itData.getCommunicationRecoveryStatus() == 1 && itData.getCommunicationRecoveryApproved()) {
                    iTFormContent.append(ROW_START1);
                    iTFormContent.append((char) iTSno).append(")");
                    iTFormContent.append(CELL_SEPERATOR1);
                    iTFormContent.append(TELEPHONE_MOBILE_SIMCARD);
                    iTFormContent.append(CELL_SEPERATOR1);
                    iTFormContent.append(getYesNoNaForInteger(itData.getCommunicationRecoveryStatus()));
                    iTFormContent.append(CELL_SEPERATOR2);
                    iTFormContent.append("<b>" + itData.getCommunicationRecoveryAmt() + "</b>");
                    iTFormContent.append(ROW_END);
                    iTSno++;
                }
                if (itData.getHeadphoneRecoveryAmtStatus() == 1 && itData.getHeadphoneRecoveryApproved()) {
                    iTFormContent.append(ROW_START1);
                    iTFormContent.append((char) iTSno).append(")");
                    iTFormContent.append(CELL_SEPERATOR1);
                    iTFormContent.append(TELEPHONE_MOBILE_SIMCARD);
                    iTFormContent.append(CELL_SEPERATOR1);
                    iTFormContent.append(getYesNoNaForInteger(itData.getHeadphoneRecoveryAmtStatus()));
                    iTFormContent.append(CELL_SEPERATOR2);
                    iTFormContent.append("<b>" + itData.getHeadphoneRecoveryAmt() + "</b>");
                    iTFormContent.append(ROW_END);
                    iTSno++;

                }
                if (itData.getLaptopBagRecoveryAmtStatus() == 1 && itData.getLaptopBagRecoveryApproved()) {
                    iTFormContent.append(ROW_START1);
                    iTFormContent.append((char) iTSno).append(")");
                    iTFormContent.append(CELL_SEPERATOR1);
                    iTFormContent.append(LAPTOP_BAG);
                    iTFormContent.append(CELL_SEPERATOR1);
                    iTFormContent.append(getYesNoNaForInteger(itData.getLaptopBagRecoveryAmtStatus()));
                    iTFormContent.append(CELL_SEPERATOR2);
                    iTFormContent.append("<b>" + itData.getLaptopBagRecoveryAmt() + "</b>");
                    iTFormContent.append(ROW_END);
                    iTSno++;

                }
                if (itData.getMonitorRecoveryAmtStatus() == 1 && itData.getMonitorRecoveryApproved()) {
                    iTFormContent.append(ROW_START1);
                    iTFormContent.append((char) iTSno).append(")");
                    iTFormContent.append(CELL_SEPERATOR1);
                    iTFormContent.append(MONITOR);
                    iTFormContent.append(CELL_SEPERATOR1);
                    iTFormContent.append(getYesNoNaForInteger(itData.getMonitorRecoveryAmtStatus()));
                    iTFormContent.append(CELL_SEPERATOR2);
                    iTFormContent.append("<b>" + itData.getMonitorRecoveryAmt() + "</b>");
                    iTFormContent.append(ROW_END);
                    iTSno++;
                }
                if (itData.getChargerRecoveryAmtStatus() == 1 && itData.getChargerRecoveryApproved()) {
                    iTFormContent.append(ROW_START1);
                    iTFormContent.append((char) iTSno).append(")");
                    iTFormContent.append(CELL_SEPERATOR1);
                    iTFormContent.append(CHARGER);
                    iTFormContent.append(CELL_SEPERATOR1);
                    iTFormContent.append(getYesNoNaForInteger(itData.getChargerRecoveryAmtStatus()));
                    iTFormContent.append(CELL_SEPERATOR2);
                    iTFormContent.append("<b>" + itData.getChargerRecoveryAmt() + "</b>");
                    iTFormContent.append(ROW_END);
                    iTSno++;
                }
                if (itData.getMouseRecoveryAmtStatus() == 1 && itData.getMouseRecoveryApproved()) {
                    iTFormContent.append(ROW_START1);
                    iTFormContent.append((char) iTSno).append(")");
                    iTFormContent.append(CELL_SEPERATOR1);
                    iTFormContent.append(MOUSE);
                    iTFormContent.append(CELL_SEPERATOR1);
                    iTFormContent.append(getYesNoNaForInteger(itData.getMouseRecoveryAmtStatus()));
                    iTFormContent.append(CELL_SEPERATOR2);
                    iTFormContent.append("<b>" + itData.getMouseRecoveryAmt() + "</b>");
                    iTFormContent.append(ROW_END);
                    iTSno++;
                }
                for (RecoveryReimbursement recovery : itRecoveryData) {
                    if (recovery.getApproved()) {
                        iTFormContent.append(ROW_START1);
                        iTFormContent.append((char) iTSno).append(")");
                        iTFormContent.append(CELL_SEPERATOR1);
                        iTFormContent.append(checkString(recovery.getRecoveryItem()));
                        iTFormContent.append(CELL_SEPERATOR1);
                        iTFormContent.append(getYesNoNaForInteger(recovery.getRecoveryStatus()));
                        iTFormContent.append(CELL_SEPERATOR2);
                        iTFormContent.append("<b>" + recovery.getRecoveryAmount() + "</b>");
                        iTFormContent.append(ROW_END);
                        iTSno++;
                    }
                }
                int adminSno = 97;


                if (adminData.getStationaryRecoveryAmtStatus() == 1 && adminData.getStationaryRecoveryApproved()) {
                    adminFormContent.append(ROW_START1);
                    adminFormContent.append((char) iTSno).append(")");
                    adminFormContent.append(CELL_SEPERATOR1);
                    adminFormContent.append(STATIONARY);
                    adminFormContent.append(CELL_SEPERATOR1);
                    adminFormContent.append(getYesNoNaForInteger(adminData.getStationaryRecoveryAmtStatus()));
                    adminFormContent.append(CELL_SEPERATOR2);
                    adminFormContent.append("<b>" + adminData.getStationaryRecoveryAmt() + "</b>");
                    adminFormContent.append(ROW_END);
                    adminSno++;
                }
                if (adminData.getKeysRecoveryAmtStatus() == 1 && adminData.getKeyRecoveryApproved()) {
                    adminFormContent.append(ROW_START1);
                    adminFormContent.append((char) iTSno).append(")");
                    adminFormContent.append(CELL_SEPERATOR1);
                    adminFormContent.append(KEYS);
                    adminFormContent.append(CELL_SEPERATOR1);
                    adminFormContent.append(getYesNoNaForInteger(adminData.getKeysRecoveryAmtStatus()));
                    adminFormContent.append(CELL_SEPERATOR2);
                    adminFormContent.append("<b>" + adminData.getKeysRecoveryAmt() + "</b>");
                    adminFormContent.append(ROW_END);
                    adminSno++;

                }
                if (adminData.getLibraryRecoveryAmtStatus() == 1 && adminData.getLibraryRecoveryApproved()) {
                    adminFormContent.append(ROW_START1);
                    adminFormContent.append((char) iTSno).append(")");
                    adminFormContent.append(CELL_SEPERATOR1);
                    adminFormContent.append(LIBRARY);
                    adminFormContent.append(CELL_SEPERATOR1);
                    adminFormContent.append(getYesNoNaForInteger(adminData.getLibraryRecoveryAmtStatus()));
                    adminFormContent.append(CELL_SEPERATOR2);
                    adminFormContent.append("<b>" + adminData.getLibraryRecoveryAmt() + "</b>");
                    adminFormContent.append(ROW_END);
                    adminSno++;

                }
                if (adminData.getSportsRecoveryAmtStatus() == 1 && adminData.getSportsRecoveryApproved()) {
                    adminFormContent.append(ROW_START1);
                    adminFormContent.append((char) iTSno).append(")");
                    adminFormContent.append(CELL_SEPERATOR1);
                    adminFormContent.append(SPORTS_EQUIPMENT);
                    adminFormContent.append(CELL_SEPERATOR1);
                    adminFormContent.append(getYesNoNaForInteger(adminData.getSportsRecoveryAmtStatus()));
                    adminFormContent.append(CELL_SEPERATOR2);
                    adminFormContent.append("<b>" + adminData.getSportsRecoveryAmt() + "</b>");
                    adminFormContent.append(ROW_END);
                    adminSno++;

                }
                if (adminData.getInfraRecoveryAmtStatus() == 1 && adminData.getInfraRecoveryApproved()) {
                    adminFormContent.append(ROW_START1);
                    adminFormContent.append((char) iTSno).append(")");
                    adminFormContent.append(CELL_SEPERATOR1);
                    adminFormContent.append(INFRASTRUCTURE_ISSUED);
                    adminFormContent.append(CELL_SEPERATOR1);
                    adminFormContent.append(getYesNoNaForInteger(adminData.getInfraRecoveryAmtStatus()));
                    adminFormContent.append(CELL_SEPERATOR2);
                    adminFormContent.append("<b>" + adminData.getInfraRecoveryApproved() + "</b>");
                    adminFormContent.append(ROW_END);
                    adminSno++;

                }
                if (adminData.getLunchRecoveryAmtStatus() == 1 && adminData.getLunchRecoveryApproved()) {
                    adminFormContent.append(ROW_START1);
                    adminFormContent.append((char) iTSno).append(")");
                    adminFormContent.append(CELL_SEPERATOR1);
                    adminFormContent.append(LUNCH_DEDUCTION);
                    adminFormContent.append(CELL_SEPERATOR1);
                    adminFormContent.append(getYesNoNaForInteger(adminData.getLunchRecoveryAmtStatus()));
                    adminFormContent.append(CELL_SEPERATOR2);
                    adminFormContent.append("<b>" + adminData.getLunchRecoveryAmt() + "</b>");
                    adminFormContent.append(ROW_END);
                    adminSno++;

                }
                if (adminData.getAccessCardRecoveryAmtStatus() == 1 && adminData.getAccessCardRecoveryApproved()) {
                    adminFormContent.append(ROW_START1);
                    adminFormContent.append((char) iTSno).append(")");
                    adminFormContent.append(CELL_SEPERATOR1);
                    adminFormContent.append(ACCESS_CARD);
                    adminFormContent.append(CELL_SEPERATOR1);
                    adminFormContent.append(getYesNoNaForInteger(adminData.getAccessCardRecoveryAmtStatus()));
                    adminFormContent.append(CELL_SEPERATOR2);
                    adminFormContent.append("<b>" + adminData.getAccessCardRecoveryApproved() + "</b>");
                    adminFormContent.append(ROW_END);
                    adminSno++;

                }
                if (adminData.getIdentityCardRecoveryStatus() == 1 && adminData.getIdentityCardRecoveryApproved()) {
                    adminFormContent.append(ROW_START1);
                    adminFormContent.append((char) iTSno).append(")");
                    adminFormContent.append(CELL_SEPERATOR1);
                    adminFormContent.append(IDENTITY_CARD);
                    adminFormContent.append(CELL_SEPERATOR1);
                    adminFormContent.append(getYesNoNaForInteger(adminData.getIdentityCardRecoveryStatus()));
                    adminFormContent.append(CELL_SEPERATOR2);
                    adminFormContent.append("<b>" + adminData.getIdentityCardRecoveryAmt() + "</b>");
                    adminFormContent.append(ROW_END);
                    adminSno++;

                }
                List<RecoveryReimbursement> adminRecoveryData = RecoveryReimbursementLocalServiceUtil.findSelectedRecoveryFormByDepartment(adminData.getId(), DEPARTMENT_ADMIN);
                for (RecoveryReimbursement recovery : adminRecoveryData) {
                    if (recovery.getApproved()) {
                        adminFormContent.append(ROW_START1);
                        adminFormContent.append((char) iTSno).append(")");
                        adminFormContent.append(CELL_SEPERATOR1);
                        adminFormContent.append(recovery.getRecoveryItem());
                        adminFormContent.append(CELL_SEPERATOR1);
                        adminFormContent.append(getYesNoNaForInteger(recovery.getRecoveryStatus()));
                        adminFormContent.append(CELL_SEPERATOR2);
                        adminFormContent.append("<b>" + recovery.getRecoveryAmount() + "</b>");
                        adminFormContent.append(ROW_END);
                        adminSno++;
                    }
                }

                String ticketStatus = itSubmitted ? getYesNoNaForInteger(itData.getTicketNo()) : BLANK;
                ClassLoader classLoader = getClass().getClassLoader();
                InputStream inputStream = classLoader.getResourceAsStream("/pdfTemplate/clearanceForm.html");
                String htmlString = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining(""));
                htmlPdfString = (MessageFormat.format(htmlString,
                        itSubmitted ? iTFormContent.toString() : BLANK,
                        adminSubmitted ? adminFormContent.toString() : BLANK,
                        managerSubmitted ? getYesNoForInteger(managerData.getTicketNo()) : BLANK,
                        managerSubmitted ? managerData.getTicketNoRemark() : BLANK,
                        hrSubmitted ? hrFormContent.toString() : BLANK,
                        BLANK,
                        managerSubmitted ? getYesNoForInteger(managerData.getSeparationDocument()) : BLANK,
                        managerSubmitted ? checkString(managerData.getSeparationDocumentRemark()) : BLANK,
                        itSubmitted && managerSubmitted ? getYesNoNaForInteger(managerData.getTicketNo()) : ticketStatus,
                        itSubmitted && managerSubmitted ? checkString(managerData.getTicketNoRemark()) : itSubmitted ? checkString(itData.getTicketNoRemark()) : BLANK,
                        itSubmitted ? getYesNoNaForInteger(itData.getMailPst()) : BLANK,
                        itSubmitted ? checkString(itData.getMailPstRemark()) : BLANK,
                        itSubmitted ? getYesNoNaForInteger(itData.getDownloadFolder()) : BLANK,
                        itSubmitted ? checkString(itData.getDownloadFolderRemark()) : BLANK,
                        itSubmitted ? getYesNoNaForInteger(itData.getDocumentFolder()) : BLANK,
                        itSubmitted ? checkString(itData.getDocumentFolderRemark()) : BLANK,
                        itSubmitted ? getYesNoNaForInteger(itData.getOtherData()) : BLANK,
                        itSubmitted ? checkString(itData.getOtherDataRemark()) : BLANK,
                        itSubmitted ? getYesNoNaForInteger(itData.getGoogleDrive()) : BLANK,
                        itSubmitted ? checkString(itData.getGoogleDriveRemark()) : BLANK,
                        itSubmitted ? getYesNoNaForInteger(itData.getOthers()) : BLANK,
                        itSubmitted ? checkString(itData.getOthersRemark()) : BLANK,
                        itSubmitted ? getYesNoNaForInteger(itData.getMailForwarding()) : BLANK,
                        itSubmitted ? checkString(itData.getMailForwardingRemark()) : BLANK,
                        itSubmitted ? getYesNoNaForInteger(itData.getEmailDisable()) : BLANK,
                        itSubmitted ? checkString(itData.getEmailDisableRemark()) : BLANK,
                        itSubmitted ? getYesNoNaForInteger(itData.getSystemRecovered()) : BLANK,
                        itSubmitted ? checkString(itData.getSystemRecoveredRemark()) : BLANK,
                        itSubmitted ? getYesNoNaForInteger(itData.getClientSystemRecovered()) : BLANK,
                        itSubmitted ? checkString(itData.getClientSystemRecoveredRemark()) : BLANK,
                        itSubmitted ? getYesNoNaForInteger(itData.getAccessCardDisable()) : BLANK,
                        itSubmitted ? checkString(itData.getAccessCardDisableRemark()) : BLANK,
                        itSubmitted ? getYesNoNaForInteger(itData.getSeparationDocument()) : BLANK,
                        itSubmitted ? checkString(itData.getSeparationDocumentRemark()) : BLANK,
                        itSubmitted ? getYesNoForInteger(itData.getSystemRecoveryAmtStatus()) : BLANK,
                        BLANK,
                        BLANK,
                        BLANK,
                        BLANK,
                        BLANK,
                        BLANK,
                        BLANK,
                        BLANK,
                        BLANK,
                        BLANK,
                        BLANK,
                        BLANK,
                        BLANK,
                        BLANK,
                        BLANK,
                        BLANK,
                        BLANK,
                        BLANK,
                        adminSubmitted ? getYesNoForInteger(adminData.getStationaryRecoveryAmtStatus()) : BLANK,
                        adminSubmitted ? adminData.getStationaryRecoveryAmt() : BLANK,
                        adminSubmitted ? getYesNoForInteger(adminData.getKeysRecoveryAmtStatus()) : BLANK,
                        adminSubmitted ? adminData.getKeysRecoveryAmt() : BLANK,
                        adminSubmitted ? getYesNoForInteger(adminData.getLibraryRecoveryAmtStatus()) : BLANK,
                        adminSubmitted ? adminData.getLibraryRecoveryAmt() : BLANK,
                        adminSubmitted ? getYesNoForInteger(adminData.getSportsRecoveryAmtStatus()) : BLANK,
                        adminSubmitted ? adminData.getSportsRecoveryAmt() : BLANK,
                        adminSubmitted ? getYesNoForInteger(adminData.getInfraRecoveryAmtStatus()) : BLANK,
                        adminSubmitted ? adminData.getInfraRecoveryAmt() : BLANK,
                        adminSubmitted ? getYesNoForInteger(adminData.getLunchRecoveryAmtStatus()) : BLANK,
                        adminSubmitted ? adminData.getLunchRecoveryAmt() : BLANK,
                        adminSubmitted ? getYesNoForInteger(adminData.getAccessCardRecoveryAmtStatus()) : BLANK,
                        adminSubmitted ? adminData.getAccessCardRecoveryAmt() : BLANK,
                        adminSubmitted ? getYesNoForInteger(adminData.getIdentityCardRecoveryStatus()) : BLANK,
                        adminSubmitted ? adminData.getIdentityCardRecoveryAmt() : BLANK,
                        BLANK,
                        BLANK,
                        BLANK,
                        financeSubmitted ? finanaceData.getLastSalaryPaidDays() : BLANK,
                        financeSubmitted ? finanaceData.getLastSalaryPaidMonth() : BLANK,
                        financeSubmitted ? finanaceData.getLastSalaryPaidYear() : BLANK,
                        financeSubmitted ? getYesNoNaForInteger(finanaceData.getTaxProofStatus()) : BLANK,
                        financeSubmitted ? checkString(finanaceData.getTaxProofRemark()) : BLANK,
                        financeSubmitted ? getYesNoNaForInteger(finanaceData.getFoodReimbursementStatus()) : BLANK,
                        financeSubmitted ? finanaceData.getFoodReimbursementAmt() : BLANK,
                        financeSubmitted ? reimbursementContent.toString() : BLANK,
                        financeSubmitted ? getYesNoNaForInteger(finanaceData.getTravelRecoveryStatus()) : BLANK,
                        financeSubmitted ? finanaceData.getTravelRecoveryAmt() : BLANK,
                        financeSubmitted ? getYesNoNaForInteger(finanaceData.getHotelRecoveryStatus()) : BLANK,
                        financeSubmitted ? finanaceData.getHotelRecoveryAmt() : BLANK,
                        financeSubmitted ? getYesNoNaForInteger(finanaceData.getInternationalRecoveryStatus()) : BLANK,
                        financeSubmitted ? finanaceData.getInternationalRecoveryAmt() : BLANK,
                        financeSubmitted ? getYesNoNaForInteger(finanaceData.getEducationRecoveryStatus()) : BLANK,
                        financeSubmitted ? finanaceData.getEducationRecoveryAmt() : BLANK,
                        financeSubmitted ? getYesNoNaForInteger(finanaceData.getJoiningBonusRecoveryStatus()) : BLANK,
                        financeSubmitted ? finanaceData.getJoiningBonusRecoveryAmt() : BLANK,
                        financeSubmitted ? getYesNoNaForInteger(finanaceData.getNoticePeriodRecoveryStatus()) : BLANK,
                        financeSubmitted ? finanaceData.getNoticePeriodRecoveryAmt() : BLANK,
                        financeSubmitted ? recoveryFormContent.toString() : BLANK,
                        hrSubmitted ? getYesNoNaForInteger((int) hrData.getFoodOption()) : BLANK,
                        hrSubmitted ? checkString(hrData.getFoodOptionRemark()) : BLANK,
                        hrSubmitted ? getYesNoNaForInteger(hrData.getInductionFeedbackStatus()) : BLANK,
                        hrSubmitted ? checkString(hrData.getInductionFeedbackRemark()) : BLANK,
                        hrSubmitted ? getYesNoNaForInteger(hrData.getInductionQuizStatus()) : BLANK,
                        hrSubmitted ? checkString(hrData.getInductionQuizRemark()) : BLANK,
                        BLANK,
                        BLANK,
                        hrSubmitted ? getYesNoNaForInteger(hrData.getTrainingFeedbackStatus()) : BLANK,
                        hrSubmitted ? checkString(hrData.getTrainingFeedbackRemark()) : BLANK,
                        hrSubmitted ? getYesNoNaForInteger(hrData.getExitInterviewStatus()) : BLANK,
                        hrSubmitted ? checkString(hrData.getExitInterviewRemark()) : BLANK,
                        hrSubmitted ? getYesNoNaForInteger(hrData.getEmployeeDirectoryStatus()) : BLANK,
                        hrSubmitted ? checkString(hrData.getEmployeeDirectoryRemark()) : BLANK,
                        hrSubmitted ? getYesNoNaForInteger(hrData.getLmsStatus()) : BLANK,
                        hrSubmitted ? checkString(hrData.getLmsRemark()) : BLANK,
                        hrSubmitted ? getYesNoNaForInteger(hrData.getVantageCircleStatus()) : BLANK,
                        hrSubmitted ? checkString(hrData.getVantageCircleRemark()) : BLANK,
                        hrSubmitted ? getYesNoNaForInteger(hrData.getBirthdaySynergyStatus()) : BLANK,
                        hrSubmitted ? checkString(hrData.getBirthdaySynergyRemark()) : BLANK,
                        hrSubmitted ? getYesNoNaForInteger(hrData.getExperienceLetterStatus()) : BLANK,
                        hrSubmitted ? checkString(hrData.getExperienceLetterRemark()) : BLANK,
                        hrSubmitted ? getYesNoNaForInteger(hrData.getNdaFormStatus()) : BLANK,
                        hrSubmitted ? checkString(hrData.getNdaFormRemark()) : BLANK,
                        hrSubmitted ? getYesNoNaForInteger(hrData.getSeparationDocumentStatus()) : BLANK,
                        hrSubmitted ? checkString(hrData.getSeparationDocumentRemark()) : BLANK,
                        hrSubmitted ? getYesNoForInteger(hrData.getTrainingAgreementStatus()) : BLANK,
                        hrSubmitted ? hrData.getTrainingAgreementAmt() : BLANK,
                        hrSubmitted ? getYesNoForInteger(hrData.getRecoverableBonusStatus()) : BLANK,
                        hrSubmitted ? hrData.getRecoverableBonusAmt() : BLANK,
                        hrSubmitted ? getYesNoForInteger(hrData.getNoticePeriodRecoveryStatus()) : BLANK,
                        hrSubmitted ? hrData.getNoticePeriodRecoveryAmt() : BLANK,
                        BLANK,
                        BLANK,
                        BLANK,
                        hrSubmitted ? hrData.getLeavesMonth1() : BLANK,
                        hrSubmitted ? hrData.getLeavesMonth2() : BLANK,
                        hrSubmitted ? hrData.getLeavesMonth3() : BLANK,
                        hrSubmitted ? hrData.getLeaveDaysMonth1() : BLANK,
                        hrSubmitted ? hrData.getLeaveDaysMonth2() : BLANK,
                        hrSubmitted ? hrData.getLeaveDaysMonth3() : BLANK,
                        hrSubmitted ? hrData.getLopMonth1() : BLANK,
                        hrSubmitted ? hrData.getLopMonth2() : BLANK,
                        hrSubmitted ? hrData.getLopMonth3() : BLANK,
                        hrSubmitted ? hrData.getLopDaysMonth1() : BLANK,
                        hrSubmitted ? hrData.getLopDaysMonth2() : BLANK,
                        hrSubmitted ? hrData.getLopDaysMonth3() : BLANK,
                        hrSubmitted ? hrData.getLopDateMonth1() : BLANK,
                        hrSubmitted ? hrData.getLopDateMonth2() : BLANK,
                        hrSubmitted ? hrData.getLopDateMonth3() : BLANK,
                        hrSubmitted ? hrData.getEarnedLeaveBalance() : BLANK,
                        hrSubmitted ? checkString(hrData.getHrRemark()) : BLANK,
                        hrSubmitted ? hrData.getLeaveDateMonth1() : BLANK,
                        hrSubmitted ? hrData.getLeaveDateMonth2() : BLANK,
                        hrSubmitted ? hrData.getLeaveDateMonth3() : BLANK,
                        null != employeeData ? employeeData.getName() : BLANK,
                        null != empResignationDetails ? empResignationDetails.getEcode() : BLANK,
                        null != empResignationDetails && null != empResignationDetails.getLastWorkingDate() ? convertDateToLocalDateTime(empResignationDetails.getLastWorkingDate()).format(FORMATTER_YYYY_MM_DD) : BLANK,
                        null != employeeData ? employeeData.getAccount() : BLANK,
                        null != managerDetail ? managerDetail.getName() : BLANK,
                        null != employeeData ? employeeData.getLocation() : BLANK,
                        null != empResignationDetails ? convertDateToLocalDateTime(empResignationDetails.getCreationDate()).format(FORMATTER_YYYY_MM_DD) : BLANK
                ));
            }
        }
        return htmlPdfString;
    }

    private void deleteClearances(long resignationId, List<ExitForm> exitFormData) {
        if (!exitFormData.isEmpty()) {
            long exitFormId = exitFormData.get(0).getId();
            List<QuestionnaireForm> questionnaireFormData = QuestionnaireFormLocalServiceUtil.findQuestionnaireFormByResignationId(resignationId);
            List<HrForm> hrFormData = HrFormLocalServiceUtil.findHrFormByExitId(exitFormId);
            List<ManagerForm> managerform = ManagerFormLocalServiceUtil.findManagerFormByExitId(exitFormId);
            List<AdminForm> adminFormData = AdminFormLocalServiceUtil.findAdminFormByExitId(exitFormId);
            List<ItForm> itform = ItFormLocalServiceUtil.findItFormByExitId(exitFormId);
            List<FinanceForm> financeFormData = FinanceFormLocalServiceUtil.findFinanceFormByExitId(exitFormId);
            List<RecoveryReimbursement> recoveryReimbursementForm = RecoveryReimbursementLocalServiceUtil.findRecoveryReimbursementFormByDepartmentFormId(financeFormData.get(0).getId(), DEPARTMENT_FINANCE);
            List<RecoveryReimbursement> adminRecoveryForm = RecoveryReimbursementLocalServiceUtil.findAllRecoveryFormByDepartment(adminFormData.get(0).getId(), DEPARTMENT_ADMIN);
            List<RecoveryReimbursement> hrRecoveryForm = RecoveryReimbursementLocalServiceUtil.findAllRecoveryFormByDepartment(hrFormData.get(0).getId(), DEPARTMENT_HR);
            List<RecoveryReimbursement> ItRecoveryForm = RecoveryReimbursementLocalServiceUtil.findAllRecoveryFormByDepartment(itform.get(0).getId(), DEPARTMENT_IT);
            if (!recoveryReimbursementForm.isEmpty()) {
                for (RecoveryReimbursement recoveryForm : recoveryReimbursementForm) {
                    RecoveryReimbursementLocalServiceUtil.deleteRecoveryReimbursement(recoveryForm);
                }
            }
            if (!adminRecoveryForm.isEmpty()) {
                for (RecoveryReimbursement recoveryForm : adminRecoveryForm) {
                    RecoveryReimbursementLocalServiceUtil.deleteRecoveryReimbursement(recoveryForm);
                }
            }
            if (!hrRecoveryForm.isEmpty()) {
                for (RecoveryReimbursement recoveryForm : hrRecoveryForm) {
                    RecoveryReimbursementLocalServiceUtil.deleteRecoveryReimbursement(recoveryForm);
                }
            }
            if (!ItRecoveryForm.isEmpty()) {
                for (RecoveryReimbursement recoveryForm : ItRecoveryForm) {
                    RecoveryReimbursementLocalServiceUtil.deleteRecoveryReimbursement(recoveryForm);
                }
            }
            try {
                HrFormLocalServiceUtil.deleteHrForm(hrFormData.get(0).getId());
                ManagerFormLocalServiceUtil.deleteManagerForm(managerform.get(0).getId());
                AdminFormLocalServiceUtil.deleteAdminForm(adminFormData.get(0).getId());
                ItFormLocalServiceUtil.deleteItForm(itform.get(0).getId());
                FinanceFormLocalServiceUtil.deleteFinanceForm(financeFormData.get(0).getId());
                ExitFormLocalServiceUtil.deleteExitForm(exitFormData.get(0).getId());
                if (!questionnaireFormData.isEmpty()) {
                    QuestionnaireFormLocalServiceUtil.deleteQuestionnaireForm(questionnaireFormData.get(0).getId());
                }
            } catch (PortalException exception) {
                log.error(exception.getStackTrace()[0].getMethodName() + ":" + exception.getStackTrace()[0].getLineNumber() + ":" + exception.getMessage());
            }
        }
    }

    private void generateClearances(long resignationId, Resignation empResignationDetails, Employee managerData, Employee employeeData) {
        List<ExitForm> exitFormData = ExitFormLocalServiceUtil.findExitFormByResignationId(resignationId);
        if (!exitFormData.isEmpty()) {
            deleteClearances(resignationId, exitFormData);
        }
        long exitFormId = CounterLocalServiceUtil.increment();
        ExitForm exitForm = ExitFormLocalServiceUtil.createExitForm(exitFormId);
        exitForm.setResignationId(resignationId);
        ExitFormLocalServiceUtil.addExitForm(exitForm);
        ManagerForm exitManagerForm = ManagerFormLocalServiceUtil.createManagerForm(CounterLocalServiceUtil.increment());
        exitManagerForm.setExitId(exitFormId);
        ManagerFormLocalServiceUtil.addManagerForm(exitManagerForm);
        HrForm exitHrForm = HrFormLocalServiceUtil.createHrForm(CounterLocalServiceUtil.increment());
        exitHrForm.setExitId(exitFormId);
        HrFormLocalServiceUtil.addHrForm(exitHrForm);
        ItForm exitItForm = ItFormLocalServiceUtil.createItForm(CounterLocalServiceUtil.increment());
        exitItForm.setExitId(exitFormId);
        ItFormLocalServiceUtil.addItForm(exitItForm);
        AdminForm exitAdminForm = AdminFormLocalServiceUtil.createAdminForm(CounterLocalServiceUtil.increment());
        exitAdminForm.setExitId(exitFormId);
        AdminFormLocalServiceUtil.addAdminForm(exitAdminForm);
        FinanceForm exitFinanceForm = FinanceFormLocalServiceUtil.createFinanceForm(CounterLocalServiceUtil.increment());
        exitFinanceForm.setExitId(exitFormId);
        FinanceFormLocalServiceUtil.addFinanceForm(exitFinanceForm);
        if (empResignationDetails.getExitQuestionnaire() && (empResignationDetails.getStatus() == 2)) {
            QuestionnaireForm questionnaireForm = QuestionnaireFormLocalServiceUtil.createQuestionnaireForm(CounterLocalServiceUtil.increment());
            questionnaireForm.setResignationId(resignationId);
            QuestionnaireFormLocalServiceUtil.addQuestionnaireForm(questionnaireForm);
        } else if (!empResignationDetails.getExitQuestionnaire() && (empResignationDetails.getStatus() == 2)) {
            String managerAssigneeEmail = empResignationDetails.getAssigneeTo() ? empResignationDetails.getManagerEmail() + COMMA + empResignationDetails.getAssigneeEmail() : empResignationDetails.getManagerEmail();
            Email managerEmail = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
            managerEmail.setToAddress(managerAssigneeEmail);
            managerEmail.setCcAddress(DL_HR);
            managerEmail.setSubject(MessageFormat.format(MANAGER_CLEARANCE_EMAIL_SUBJECT, employeeData.getEcode(), employeeData.getName()));
            managerEmail.setBody(MessageFormat.format(MANAGER_CLEARANCE_EMAIL_BODY, managerData.getName(), employeeData.getName(), employeeData.getEcode(), getPortalUrl() + URL_EXIT_ADMIN));
            managerEmail.setCreatedDate(getIstDate());
            managerEmail.setModule(MODULE_EXIT);
            managerEmail.setScheduled(false);
            managerEmail.setSent(false);
            EmailLocalServiceUtil.addEmail(managerEmail);
        }
        Date now = getIstDate();
        Email financeEmail = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
        financeEmail.setToAddress(DL_FINANCE);
        financeEmail.setCcAddress(DL_HR);
        financeEmail.setSubject(MessageFormat.format(FINANCE_CLEARANCE_EMAIL_SUBJECT, employeeData.getEcode(), employeeData.getName()));
        financeEmail.setBody(MessageFormat.format(FINANCE_CLEARANCE_EMAIL_BODY, employeeData.getName(), employeeData.getEcode(), getPortalUrl() + URL_EXIT_ADMIN));
        financeEmail.setCreatedDate(now);
        financeEmail.setModule(MODULE_EXIT);
        financeEmail.setScheduled(false);
        financeEmail.setSent(false);
        EmailLocalServiceUtil.addEmail(financeEmail);

        Email adminEmail = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
        adminEmail.setToAddress(DL_ADMIN);
        adminEmail.setCcAddress(DL_HR);
        adminEmail.setSubject(MessageFormat.format(ADMIN_CLEARANCE_EMAIL_SUBJECT, employeeData.getEcode(), employeeData.getName()));
        adminEmail.setBody(MessageFormat.format(ADMIN_CLEARANCE_EMAIL_BODY, employeeData.getName(), employeeData.getEcode(), getPortalUrl() + URL_EXIT_ADMIN));
        adminEmail.setCreatedDate(now);
        adminEmail.setModule(MODULE_EXIT);
        adminEmail.setScheduled(false);
        adminEmail.setSent(false);
        EmailLocalServiceUtil.addEmail(adminEmail);
        String managerEmail = empResignationDetails.getAssigneeTo() ? empResignationDetails.getManagerEmail() + COMMA + empResignationDetails.getAssigneeEmail() : empResignationDetails.getManagerEmail();
        if ((empResignationDetails.getStatus() == 2)) {
            Email emailer = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
            emailer.setSubject(MessageFormat.format(RESIGNATION_CLEARANCE_GUIDELINE_EMAIL_SUBJECT, employeeData.getEcode(), employeeData.getName()));
            if (empResignationDetails.getExitQuestionnaire()) {
                emailer.setBody(MessageFormat.format(RESIGNATION_CLEARANCE_GUIDELINE_EMAIL_BODY, convertDateToLocalDateTime(empResignationDetails.getLastWorkingDate()).format(FORMATTER_YYYY_MM_DD), getPortalUrl() + URL_EXIT, LMS_LINK));
            } else {
                emailer.setBody(MessageFormat.format(EXIT_RESIGNATION_CLEARANCE_GUIDELINE_EMAIL_BODY, convertDateToLocalDateTime(empResignationDetails.getLastWorkingDate()).format(FORMATTER_YYYY_MM_DD), LMS_LINK));
            }
            emailer.setToAddress(employeeData.getEmail());
            emailer.setCcAddress(DL_HR + COMMA + managerEmail);
            emailer.setCreatedDate(now);
            emailer.setSent(false);
            emailer.setScheduled(false);
            emailer.setModule(MODULE_EXIT);
            EmailLocalServiceUtil.addEmail(emailer);
        } else if (empResignationDetails.getStatus() == 5) {
            Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
            email.setSubject(MessageFormat.format(TERMINATION_CLEARANCE_GUIDELINE_EMAIL_SUBJECT, employeeData.getEcode(), employeeData.getName()));
            email.setBody(MessageFormat.format(TERMINATION_CLEARANCE_GUIDELINE_EMAIL_BODY, convertDateToLocalDateTime(empResignationDetails.getLastWorkingDate()).format(FORMATTER_YYYY_MM_DD), LMS_LINK));
            email.setToAddress(employeeData.getEmail());
            email.setCcAddress(DL_HR + COMMA + managerEmail);
            email.setCreatedDate(now);
            email.setSent(false);
            email.setScheduled(false);
            email.setModule(MODULE_EXIT);
            EmailLocalServiceUtil.addEmail(email);
        }
    }
}
