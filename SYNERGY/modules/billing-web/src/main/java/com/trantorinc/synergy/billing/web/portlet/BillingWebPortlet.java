package com.trantorinc.synergy.billing.web.portlet;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.google.gson.Gson;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.trantorinc.synergy.billing.core.model.Billing;
import com.trantorinc.synergy.billing.core.model.Submission;
import com.trantorinc.synergy.billing.core.service.BillingLocalServiceUtil;
import com.trantorinc.synergy.billing.core.service.SubmissionLocalServiceUtil;
import com.trantorinc.synergy.billing.web.constants.BillingWebPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trantorinc.synergy.billing.web.dto.CertificateDto;
import com.trantorinc.synergy.billing.web.dto.EmployeeDataDto;
import com.trantorinc.synergy.billing.web.dto.SkillsetDto;
import com.trantorinc.synergy.billing.web.util.BillingUtil;
import com.trantorinc.synergy.birthday.core.model.EmployeeData;
import com.trantorinc.synergy.birthday.core.service.EmployeeDataLocalServiceUtil;
import com.trantorinc.synergy.emailer.core.model.Emailer;
import com.trantorinc.synergy.emailer.core.service.EmailerLocalServiceUtil;
import com.trantorinc.synergy.skillset.core.model.Certificate;
import com.trantorinc.synergy.skillset.core.model.Skill;
import com.trantorinc.synergy.skillset.core.service.CertificateLocalServiceUtil;
import com.trantorinc.synergy.skillset.core.service.SkillLocalServiceUtil;
import org.apache.poi.ss.usermodel.*;
import org.osgi.service.component.annotations.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

import static com.trantorinc.synergy.billing.web.constants.BillingWebPortletKeys.*;

/**
 * @author riya.gupta
 */
@Component(
        immediate = true,
        property = {
                "com.liferay.portlet.display-category=category.sample",
                "com.liferay.portlet.header-portlet-css=/css/main.css",
                "com.liferay.portlet.instanceable=true",
                "javax.portlet.display-name=BillingWeb",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/view.jsp",
                "javax.portlet.name=" + BillingWebPortletKeys.BILLINGWEB,
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
)
public class BillingWebPortlet extends MVCPortlet {

    private static final Log _log = LogFactoryUtil.getLog(BillingWebPortlet.class.getName());

    @Override
    public void doView(RenderRequest request, RenderResponse response) throws IOException, PortletException {
        try {
            ThemeDisplay td = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
            User loggedUser = td.getUser();
            HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
            LocalDate todaydate = LocalDate.now();
            String selectedMonth = httpReq.getParameter("selectedMonth");
            int month = todaydate.getMonthValue();
            int year = todaydate.getYear();
            if (selectedMonth != null) {
                String[] exportValues = selectedMonth.split("/");
                month = Integer.parseInt(exportValues[0]);
                year = Integer.parseInt(exportValues[1]);
            }

            String[] managerRole = {"Manager"};
            String[] coordinatorRole = {"Coordinator"};
            String[] financeRole = {"Finance_Admin"};
            String[] hrRole = {"HR_Admin"};
            String[] leaderRole = {"Leaders"};
            String[] billingRole = {"Billing_Bench"};

            List<EmployeeData> allEmployeeList = EmployeeDataLocalServiceUtil.findAllEmployees();
            List<Billing> allActiveBillingData = BillingLocalServiceUtil.findMonthlyBilling(year, month).stream().filter(s -> !s.getDeleted()).collect(Collectors.toList());
            List<EmployeeDataDto> formattedBillingData = formatBillingData(allActiveBillingData,allEmployeeList);
            List<EmployeeDataDto> formattedNonSharedBillingData = formattedBillingData.stream().filter(fb -> !fb.isShared()).filter(s -> !(s.getAccount().equalsIgnoreCase(GOOGLE) || s.getAccount().equalsIgnoreCase(CONTINUUM) || s.getAccount().equalsIgnoreCase(CONTINUUMGLOBAL))).collect(Collectors.toList());
            List<EmployeeDataDto> formattedSharedBillingData = formattedBillingData.stream().filter(EmployeeDataDto::isShared).collect(Collectors.toList());

            //Set Current month year for all the roles for selectedmonth parameter
            String currMonth = String.format("%02d", todaydate.getMonthValue()) + "/" + todaydate.getYear();
            request.setAttribute("currMonthYear", currMonth);
            request.setAttribute("billingUrl", HOSTNAME + BILLING_URL);
            request.setAttribute("selectedMonthYear", selectedMonth);

            //HR list
            if (RoleLocalServiceUtil.hasUserRoles(loggedUser.getUserId(), loggedUser.getCompanyId(), hrRole, false)) {
                request.setAttribute("hrEmployeeList", formattedNonSharedBillingData);
                List<EmployeeDataDto> hrFormattedSharedBillingData = formattedSharedBillingData.stream().filter(e -> !e.isProjectUpdated()).collect(Collectors.toList());
                request.setAttribute("hrSharedEmployeeList", hrFormattedSharedBillingData);
                request.setAttribute("hrRole", true);
            }

            //leadership list
            if (RoleLocalServiceUtil.hasUserRoles(loggedUser.getUserId(), loggedUser.getCompanyId(), leaderRole, false)) {
                request.setAttribute("leaderShipEmployeeList", formattedNonSharedBillingData);
                request.setAttribute("leadershipSharedEmployeeList", formattedSharedBillingData);
                request.setAttribute("leadershipRole", true);
            }

            //Finance list
            if (RoleLocalServiceUtil.hasUserRoles(loggedUser.getUserId(), loggedUser.getCompanyId(), financeRole, false)) {
                request.setAttribute("financeEmployeeList", formattedNonSharedBillingData);
                request.setAttribute("financeSharedEmployeeList", formattedSharedBillingData);
                request.setAttribute("fncRole", true);
            }

            //Bench List
            if (RoleLocalServiceUtil.hasUserRoles(loggedUser.getUserId(), loggedUser.getCompanyId(), billingRole, false)) {
                List<EmployeeDataDto> benchEmployeeList = formattedNonSharedBillingData.stream().filter(fb -> fb.getCurrent().contains(BENCH) ).collect(Collectors.toList());
                List<EmployeeDataDto> sharedBenchEmployeeList = formattedSharedBillingData.stream().filter(fb -> fb.getCurrent().contains(BENCH)).collect(Collectors.toList());
                request.setAttribute("benchEmployeeList", benchEmployeeList);
                request.setAttribute("sharedBenchEmployeeList", sharedBenchEmployeeList);
                request.setAttribute("benchRole",true);
            }

            //Manager/Coordinator list
            if ((RoleLocalServiceUtil.hasUserRoles(loggedUser.getUserId(), loggedUser.getCompanyId(), managerRole, false))
                    || (RoleLocalServiceUtil.hasUserRoles(loggedUser.getUserId(), loggedUser.getCompanyId(), coordinatorRole, false)) ) {
                boolean reqFlag;
                boolean prevFlag = false;
                boolean currDaysCheckFlag = false;
                boolean isManager = false;

                EmployeeData loginedUser = EmployeeDataLocalServiceUtil.findByEmail(loggedUser.getEmailAddress());
                int date = todaydate.getDayOfMonth();
                //Find previous month and year
                LocalDate prevMonthYear = todaydate.minusMonths(1);
                int prevMon = prevMonthYear.getMonthValue();
                int prevYear = prevMonthYear.getYear();

                //To check whether date lies between 1 & 2  of current month
                if (date == 1 || date == 2) {
                    currDaysCheckFlag = true;
                }
                //To check whether manager has submitted current month data to finance team or not
                Submission requestFlag = SubmissionLocalServiceUtil.findFinalSubmission(loginedUser.getEmpId(), month, year);
                reqFlag = requestFlag != null;

                //Change the month year to selected month year
                if (selectedMonth != null) {
                    String[] exportValues = selectedMonth.split("/");
                    month = Integer.parseInt(exportValues[0]);
                    year = Integer.parseInt(exportValues[1]);
                    if (month == prevMon && year == prevYear) {
                        prevFlag = true;
                    }
                }

                if (reqFlag) {
                    request.setAttribute(VIEW_MODE, "view"); //if request flag is true then always view mode
                } else {                                          //if request flag is false then-
                    if (prevFlag) {                            //case 1- previous month is selected
                        if (currDaysCheckFlag) {
                            request.setAttribute(VIEW_MODE, "edit"); // case 1.1 -if previous month is selected and 1,2 of current month-edit mode
                        } else {
                            request.setAttribute(VIEW_MODE, "view");//case 1.2- prev month is selected but dates are greater than 3- view mode
                        }
                    } else {                                          //case 2- other then previous month is selected
                        if (month == todaydate.getMonthValue() && year == todaydate.getYear()) //case2.1 - current month is selected -edit mode
                        {
                            request.setAttribute(VIEW_MODE, "edit");
                        } else {                            //case 2.2 any other month is selected - view mode
                            request.setAttribute(VIEW_MODE, "view");
                        }
                    }
                }

                List<EmployeeDataDto> managerNonSharedEmployeeList = new ArrayList<>();
                List<EmployeeDataDto> managerSharedEmployeeList = new ArrayList<>();

                if (RoleLocalServiceUtil.hasUserRoles(loggedUser.getUserId(), loggedUser.getCompanyId(), managerRole, false)) {
                    isManager = true;
                    managerNonSharedEmployeeList.addAll(formattedNonSharedBillingData.stream().filter(s -> s.getManagerEcode().equalsIgnoreCase(loginedUser.getEmpId())).collect(Collectors.toList()));
                    managerSharedEmployeeList.addAll(formattedSharedBillingData.stream().filter(s -> s.getManagerEcode().equalsIgnoreCase(loginedUser.getEmpId())).collect(Collectors.toList()));
                }
                if (RoleLocalServiceUtil.hasUserRoles(loggedUser.getUserId(), loggedUser.getCompanyId(), coordinatorRole, false)) {
                    if(isManager){
                        managerNonSharedEmployeeList.addAll(formattedNonSharedBillingData.stream().filter(s -> s.getCoordinatorEcode().equalsIgnoreCase(loginedUser.getEmpId())).filter(s -> !s.getManagerEcode().equalsIgnoreCase(loginedUser.getEmpId())).collect(Collectors.toList()));
                        managerSharedEmployeeList.addAll(formattedSharedBillingData.stream().filter(s -> s.getCoordinatorEcode().equalsIgnoreCase(loginedUser.getEmpId())).filter(s -> !s.getManagerEcode().equalsIgnoreCase(loginedUser.getEmpId())).collect(Collectors.toList()));
                    } else {
                        managerNonSharedEmployeeList.addAll(formattedNonSharedBillingData.stream().filter(s -> s.getCoordinatorEcode().equalsIgnoreCase(loginedUser.getEmpId())).collect(Collectors.toList()));
                        managerSharedEmployeeList.addAll(formattedSharedBillingData.stream().filter(s -> s.getCoordinatorEcode().equalsIgnoreCase(loginedUser.getEmpId())).collect(Collectors.toList()));
                    }
                }
                request.setAttribute("managerEmployeeList", managerNonSharedEmployeeList);
                request.setAttribute("managerSharedEmployeeList", managerSharedEmployeeList);
                request.setAttribute("managerRole", true);
            }
        } catch (PortalException e) {
            _log.error("Billing : Exception in do View -->" + e.getMessage());
        }
        super.doView(request, response);
    }

    private List<EmployeeDataDto> formatBillingData(List<Billing> allBillingData, List<EmployeeData> allEmployeeList) {
        List<EmployeeDataDto> employeeList = new ArrayList<>();
        for(Billing billing : allBillingData){
            EmployeeDataDto empDataDto = new EmployeeDataDto();
            empDataDto.setBillingId(String.valueOf(billing.getBillingId()));
            empDataDto.setShared(billing.getShared());
            empDataDto.setProjectUpdated(billing.getProjectUpdated());
            empDataDto.setCurrent(billing.getCurrent() != null ? billing.getCurrent() : "-");
            empDataDto.setCurrentPlusOne(billing.getCurrentPlusOneMonth() != null ? billing.getCurrentPlusOneMonth() : "-");
            empDataDto.setCurrentPlusTwo(billing.getCurrentPlusTwoMonth() != null ? billing.getCurrentPlusTwoMonth() : "-");
            empDataDto.setShadowResourceType(billing.getShadowResourceType().equalsIgnoreCase("") ? "-" : billing.getShadowResourceType());
            empDataDto.setShadowStartDate(billing.getShadowStartDate() != null ? formatter.format(billing.getShadowStartDate()) : "-");
            empDataDto.setBenchStartDate(billing.getBenchStartDate() != null ? formatter.format(billing.getBenchStartDate()) : "-");
            empDataDto.setBillingStartDate(billing.getBillingStartDate() != null ? formatter.format(billing.getBillingStartDate()) : "-");
            empDataDto.setBillingEndDate(billing.getBillingEndDate() != null ? formatter.format(billing.getBillingEndDate()) : "-");
            empDataDto.setEmployeeStatus(billing.getEmployeeStatus() != null ? billing.getEmployeeStatus() : "-");
            empDataDto.setLastWorkingDate(billing.getLastWorkingDate() != null ? formatter.format(billing.getLastWorkingDate()) : "-");
            empDataDto.setAllocationStatus(billing.getAllocationStatus() != null ?  billing.getAllocationStatus():"-");
            empDataDto.setRemarks(billing.getRemarks() != null ? billing.getRemarks() : "-");
            empDataDto.setBillableHours(billing.getBillableHours() != null ? billing.getBillableHours() : "-");
            empDataDto.setMonthHours(billing.getMonthHours() != null ? billing.getMonthHours() : "-");
            empDataDto.setEmployeeRole(billing.getEmployeeRole() != null ? billing.getEmployeeRole() : "-");
            empDataDto.setVertical(billing.getVertical() != null ? billing.getVertical() : "-");
            empDataDto.setPercentUtilization(billing.getPercentUtilization() != null ? billing.getPercentUtilization() : "-" );
            empDataDto.setAccount(billing.getAccount() != null ? billing.getAccount():"-");
            empDataDto.setProject(billing.getProject() != null ? billing.getProject():"-");
            empDataDto.setBillingStatus((billing.getCurrent() == null || billing.getCurrent().equalsIgnoreCase("")) ? "Pending" : "Complete");
            empDataDto.setEmployeeCode(billing.getEcode());
            empDataDto.setManagerEcode(billing.getManagerEcode());
            empDataDto.setLeadEcode(billing.getLeadEcode());
            empDataDto.setCoordinatorEcode(billing.getCoordinatorEcode());
            List<EmployeeData> employeeDataList = allEmployeeList.stream().filter(e -> e.getEmpId().equalsIgnoreCase(billing.getEcode())).collect(Collectors.toList());
            empDataDto.setEmployeeName(!employeeDataList.isEmpty() ?employeeDataList.get(0).getUserName(): "-");
            empDataDto.setEmployeeDesignation(!employeeDataList.isEmpty() ?employeeDataList.get(0).getDesignation(): "-");
            empDataDto.setDoj(!employeeDataList.isEmpty() ? formatter.format(employeeDataList.get(0).getDoj()):"-");
            empDataDto.setExperience(!employeeDataList.isEmpty() ?employeeDataList.get(0).getExperience(): "-");
            empDataDto.setSkill(!employeeDataList.isEmpty() ?employeeDataList.get(0).getSkill(): "-");
            if(!employeeDataList.isEmpty()){
                String managerEcode=employeeDataList.get(0).getManager();
                String leadEcode=employeeDataList.get(0).getReviewer();
                String coordinatorEcode=employeeDataList.get(0).getProjectCoordinatorEcode();
                if(billing.getShared()){
                    managerEcode=empDataDto.getManagerEcode();
                    leadEcode=empDataDto.getLeadEcode();
                    coordinatorEcode=empDataDto.getCoordinatorEcode();
                }
                String finalManagerEcode = managerEcode;
                List<EmployeeData> managerDatas = allEmployeeList.stream().filter(s -> s.getEmpId().equalsIgnoreCase(finalManagerEcode)).collect(Collectors.toList());
                String finalLeadEcode = leadEcode;
                List<EmployeeData> reviewerDatas = allEmployeeList.stream().filter(s -> s.getEmpId().equalsIgnoreCase(finalLeadEcode)).collect(Collectors.toList());
                String finalCoordinatorEcode = coordinatorEcode;
                List<EmployeeData> coordinatorDatas = allEmployeeList.stream().filter(s -> s.getEmpId().equalsIgnoreCase(finalCoordinatorEcode)).collect(Collectors.toList());
                empDataDto.setManagerName(!managerDatas.isEmpty() ? managerDatas.get(0).getUserName():BLANK);
                empDataDto.setLeadName(!reviewerDatas.isEmpty() ? reviewerDatas.get(0).getUserName():BLANK);
                empDataDto.setCoordinatorName(!coordinatorDatas.isEmpty() ? coordinatorDatas.get(0).getUserName():BLANK);
            }
            employeeList.add(empDataDto);
        }
        return employeeList;
    }


    @ProcessAction(name = "submitBillingManagerDetails")
    public void submitBillingManagerDetails(ActionRequest request, ActionResponse response) throws IOException {
        ThemeDisplay td = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        User loginedUser = td.getUser();
        LocalDate todaydate = LocalDate.now();
        Date updateDate = new Date();
        List<EmployeeData> allEmployeeList = EmployeeDataLocalServiceUtil.findAllEmployees();
        EmployeeData actionPerformedBy=allEmployeeList.stream().filter(e->e.getEmail().equalsIgnoreCase(loginedUser.getEmailAddress())).collect(Collectors.toList()).get(0);
        String actionPerformed = ParamUtil.getString(request, ACTION_PERFORMED);
        String selectedMonth = ParamUtil.getString(request, "submitDatem");
        Integer managerAction = ParamUtil.getInteger(request, "managerAction");
        int month = todaydate.getMonthValue();
        int year = todaydate.getYear();
        Date billingDate = null;
        if (selectedMonth != null) {
            String[] exportValues = selectedMonth.split("/");
            month = Integer.parseInt(exportValues[0]);
            year = Integer.parseInt(exportValues[1]);
            LocalDate date = LocalDate.of(year, month, 1);
            billingDate = Date.from(date.atStartOfDay().toInstant(ZoneOffset.UTC));
        }
        int validateAction = Integer.parseInt(ParamUtil.getString(request, "mvalidateAction"));

        if (validateAction == 1) {
            long billingId = ParamUtil.getLong(request, "mdeleting");
            Billing billingInfo = BillingLocalServiceUtil.fetchBilling(billingId);
            billingInfo.setDeleted(true);
            billingInfo.setUpdatedDate(updateDate);
            billingInfo.setUpdatedBy(actionPerformedBy.getEmpId());
            BillingLocalServiceUtil.updateBilling(billingInfo);
        } else {
            String[] selectedBillingIds = ParamUtil.getParameterValues(request, actionPerformed + CHECKBOX, null);
            if (selectedBillingIds != null) {
                for (String billingId : selectedBillingIds) {
                    String current = ParamUtil.getString(request, actionPerformed + CURRENT + billingId);
                    String currentPlusOne = ParamUtil.getString(request, actionPerformed + CURRENT_PLUS_ONE + billingId);
                    String currentPlusTwo = ParamUtil.getString(request, actionPerformed + CURRENT_PLUS_TWO + billingId);
                    String shadowResourceType = ParamUtil.getString(request, actionPerformed + SHADOW_RESOURCE_TYPE + billingId);
                    Date shadowStartDate = null;
                    if (!(ParamUtil.getString(request, actionPerformed + SHADOW_START_DATE + billingId).isEmpty())) {
                        shadowStartDate = ParamUtil.getDate(request, actionPerformed + SHADOW_START_DATE + billingId, formatter);
                    }
                    Date benchStartDate = null;
                    if (!(ParamUtil.getString(request, actionPerformed + BENCH_START_DATE + billingId).isEmpty())) {
                        benchStartDate = ParamUtil.getDate(request, actionPerformed + BENCH_START_DATE + billingId, formatter);
                    }
                    Date billingStartDate = null;
                    if (!(ParamUtil.getString(request, actionPerformed + BILLING_START_DATE + billingId).isEmpty())) {
                        billingStartDate = ParamUtil.getDate(request, actionPerformed + BILLING_START_DATE + billingId, formatter);
                    }
                    Date endDate = null;
                    if (!(ParamUtil.getString(request, actionPerformed + END_DATE_STR + billingId).isEmpty())) {
                        endDate = ParamUtil.getDate(request, actionPerformed + END_DATE_STR + billingId, formatter);
                    }
                    String allocationStatus = ParamUtil.getString(request, actionPerformed + ALLOCATION_STATUS + billingId);
                    String employeeStatus = ParamUtil.getString(request, actionPerformed + EMPLOYEE_STATUS + billingId);
                    Date lwdDate = null;
                    if (!(ParamUtil.getString(request, actionPerformed + LAST_WORKING_DAY + billingId).isEmpty())) {
                        lwdDate = ParamUtil.getDate(request, actionPerformed + LAST_WORKING_DAY + billingId, formatter);
                    }

                    String percentUtilization = ParamUtil.getString(request, actionPerformed + PERCENT_UTILIZATION + billingId);
                    String remarks = ParamUtil.getString(request, actionPerformed +REMARKS + billingId);
                    String billableHours = ParamUtil.getString(request, actionPerformed + BILLABLE_HOURS + billingId);
                    String employeeRole = ParamUtil.getString(request, actionPerformed + EMPLOYEE_ROLE + billingId);
                    String vertical = ParamUtil.getString(request, actionPerformed + VERTICAL + billingId);
                    Billing employeeBillingDetails = BillingLocalServiceUtil.fetchBilling(Long.parseLong(billingId));
                    employeeBillingDetails.setCurrent(current);
                    employeeBillingDetails.setCurrentPlusOneMonth(currentPlusOne);
                    employeeBillingDetails.setCurrentPlusTwoMonth(currentPlusTwo);
                    employeeBillingDetails.setShadowResourceType(shadowResourceType);
                    employeeBillingDetails.setShadowStartDate(shadowStartDate);
                    employeeBillingDetails.setBenchStartDate(benchStartDate);
                    employeeBillingDetails.setBillingStartDate(billingStartDate);
                    employeeBillingDetails.setBillingEndDate(endDate);
                    employeeBillingDetails.setAllocationStatus(allocationStatus);
                    employeeBillingDetails.setEmployeeStatus(employeeStatus);
                    employeeBillingDetails.setLastWorkingDate(lwdDate);
                    employeeBillingDetails.setBillableHours(billableHours);
                    employeeBillingDetails.setEmployeeRole(employeeRole);
                    employeeBillingDetails.setVertical(vertical);
                    employeeBillingDetails.setPercentUtilization(percentUtilization);
                    employeeBillingDetails.setRemarks(remarks);
                    employeeBillingDetails.setUpdatedDate(updateDate);
                    employeeBillingDetails.setUpdatedBy(actionPerformedBy.getEmpId());
                    BillingLocalServiceUtil.updateBilling(employeeBillingDetails);
                }
            }
            int existingRows = Integer.parseInt(ParamUtil.getString(request, EXISTING_ROWS));
            int totalRows = Integer.parseInt(ParamUtil.getString(request, TOTAL_ROWS));
            for (int i = 0; i < existingRows; i++) {
                //updating shared
                String[] billingId = ParamUtil.getParameterValues(request, actionPerformed + SHARED_CHECKBOX + i, null);
                if (billingId != null && billingId.length == 1) {
                    Billing employeeBillingInfo = BillingLocalServiceUtil.fetchBilling(Long.parseLong(billingId[0]));
                    String ecode = ParamUtil.getString(request, actionPerformed + SHARED_ECODE + i);
                    String managerEcode = ParamUtil.getString(request, actionPerformed + SHARED_MANAGER_ECODE + i);
                    String leadEode = ParamUtil.getString(request, actionPerformed + SHARED_LEAD_ECODE + i);
                    String current = ParamUtil.getString(request, actionPerformed + SHARED_CURRENT + i);
                    String currentPlusOne = ParamUtil.getString(request, actionPerformed + SHARED_CURRENT_PLUS_ONE + i);
                    String currentPlusTwo = ParamUtil.getString(request, actionPerformed + SHARED_CURRENT_PLUS_TWO + i);
                    String shadowResourceType = ParamUtil.getString(request, actionPerformed + SHARED_SHADOW_RESOURCE_TYPE + i);
                    String employeeStatus = ParamUtil.getString(request, actionPerformed + SHARED_EMPLOYEE_STATUS + i);
                    String percentageUtilization = ParamUtil.getString(request, actionPerformed + SHARED_SHARED_PERCENTAGE + i);
                    String account = ParamUtil.getString(request, actionPerformed + SHARED_ACCOUNT + i);
                    String project = ParamUtil.getString(request, actionPerformed + SHARED_PROJECT + i);
                    String coordinatorEcode = ParamUtil.getString(request, actionPerformed + SHARED_COORDINATOR_ECODE + i);
                    String remarks = ParamUtil.getString(request, actionPerformed + SHARED_REMARKS + i);
                    String billableHours = ParamUtil.getString(request, actionPerformed + SHARED_BILLABLE_HOURS + i);
                    String employeeRole = ParamUtil.getString(request, actionPerformed + SHARED_EMPLOYEE_ROLE + i);
                    String vertical = ParamUtil.getString(request, actionPerformed + SHARED_VERTICAL + i);
                    Date shadowStartDate = null;
                    if (!(ParamUtil.getString(request, actionPerformed + SHARED_SHADOW_START_DATE + i).isEmpty())) {
                        shadowStartDate = ParamUtil.getDate(request, actionPerformed + SHARED_SHADOW_START_DATE + i, formatter);
                    }
                    Date benchStartDate = null;
                    if (!(ParamUtil.getString(request, actionPerformed + SHARED_BENCH_START_DATE + i).isEmpty())) {
                        benchStartDate = ParamUtil.getDate(request, actionPerformed + SHARED_BENCH_START_DATE + i, formatter);
                    }
                    Date billingStartDate = null;
                    if (!(ParamUtil.getString(request, actionPerformed + SHARED_BILLING_START_DATE + i).isEmpty())) {
                        billingStartDate = ParamUtil.getDate(request, actionPerformed + SHARED_BILLING_START_DATE + i, formatter);
                    }
                    Date billingEndDate = null;
                    if (!(ParamUtil.getString(request, actionPerformed + SHARED_BILLING_END_DATE + i).isEmpty())) {
                        billingEndDate = ParamUtil.getDate(request, actionPerformed + SHARED_BILLING_END_DATE + i, formatter);
                    }
                    Date lwdDate = null;
                    if (!(ParamUtil.getString(request, actionPerformed + SHARED_LAST_WORKING_DATE + i).isEmpty())) {
                        lwdDate = ParamUtil.getDate(request, actionPerformed + SHARED_LAST_WORKING_DATE + i, formatter);
                    }

                    employeeBillingInfo.setEcode(ecode.toUpperCase());
                    employeeBillingInfo.setAccount(account);
                    employeeBillingInfo.setProject(project);
                    employeeBillingInfo.setCurrent(current);
                    employeeBillingInfo.setCurrentPlusOneMonth(currentPlusOne);
                    employeeBillingInfo.setCurrentPlusTwoMonth(currentPlusTwo);
                    employeeBillingInfo.setShadowResourceType(shadowResourceType);
                    employeeBillingInfo.setShadowStartDate(shadowStartDate);
                    employeeBillingInfo.setBenchStartDate(benchStartDate);
                    employeeBillingInfo.setBillingStartDate(billingStartDate);
                    employeeBillingInfo.setBillingEndDate(billingEndDate);
                    employeeBillingInfo.setEmployeeStatus(employeeStatus);
                    employeeBillingInfo.setLastWorkingDate(lwdDate);
                    employeeBillingInfo.setManagerEcode(managerEcode.toUpperCase());
                    employeeBillingInfo.setLeadEcode(leadEode.toUpperCase());
                    employeeBillingInfo.setShared(true);
                    employeeBillingInfo.setPercentUtilization(percentageUtilization);
                    employeeBillingInfo.setCoordinatorEcode(coordinatorEcode.toUpperCase());
                    employeeBillingInfo.setRemarks(remarks);
                    employeeBillingInfo.setBillableHours(billableHours);
                    employeeBillingInfo.setEmployeeRole(employeeRole);
                    employeeBillingInfo.setVertical(vertical);
                    employeeBillingInfo.setUpdatedDate(updateDate);
                    employeeBillingInfo.setUpdatedBy(actionPerformedBy.getEmpId());
                    BillingLocalServiceUtil.updateBilling(employeeBillingInfo);
                }
            }

            // adding new shared
            if (totalRows > existingRows) {
                for (int i = existingRows; i < totalRows; i++) {
                    String ecode = ParamUtil.getString(request, actionPerformed + SHARED_ECODE + i);
                    String managerEcode = ParamUtil.getString(request, actionPerformed + SHARED_MANAGER_ECODE + i);
                    String leadEode = ParamUtil.getString(request, actionPerformed + SHARED_LEAD_ECODE + i);
                    String current = ParamUtil.getString(request, actionPerformed + SHARED_CURRENT + i);
                    String currentPlusOne = ParamUtil.getString(request, actionPerformed + SHARED_CURRENT_PLUS_ONE + i);
                    String currentPlusTwo = ParamUtil.getString(request, actionPerformed + SHARED_CURRENT_PLUS_TWO + i);
                    String shadowResourceType = ParamUtil.getString(request, actionPerformed + SHARED_SHADOW_RESOURCE_TYPE + i);
                    String account = ParamUtil.getString(request, actionPerformed + SHARED_ACCOUNT + i);
                    String project = ParamUtil.getString(request, actionPerformed + SHARED_PROJECT + i);
                    String coordinatorEcode = ParamUtil.getString(request, actionPerformed + SHARED_COORDINATOR_ECODE + i);
                    String percentageUtilization = ParamUtil.getString(request, actionPerformed + SHARED_SHARED_PERCENTAGE + i);
                    String remarks = ParamUtil.getString(request, actionPerformed + SHARED_REMARKS + i);
                    String billableHours = ParamUtil.getString(request, actionPerformed + SHARED_BILLABLE_HOURS + i);
                    String employeeRole = ParamUtil.getString(request, actionPerformed + SHARED_EMPLOYEE_ROLE + i);
                    String vertical = ParamUtil.getString(request, actionPerformed + SHARED_VERTICAL + i);
                    String employeeStatus = ParamUtil.getString(request, actionPerformed + SHARED_EMPLOYEE_STATUS + i);

                    Date shadowStartDate = null;
                    if (!(ParamUtil.getString(request, actionPerformed + SHARED_SHADOW_START_DATE + i).isEmpty())) {
                        shadowStartDate = ParamUtil.getDate(request, actionPerformed + SHARED_SHADOW_START_DATE + i, formatter);
                    }
                    Date benchStartDate = null;
                    if (!(ParamUtil.getString(request, actionPerformed + SHARED_BENCH_START_DATE + i).isEmpty())) {
                        benchStartDate = ParamUtil.getDate(request, actionPerformed + SHARED_BENCH_START_DATE + i, formatter);
                    }
                    Date billingStartDate = null;
                    if (!(ParamUtil.getString(request, actionPerformed + SHARED_BILLING_START_DATE + i).isEmpty())) {
                        billingStartDate = ParamUtil.getDate(request, actionPerformed + SHARED_BILLING_START_DATE + i, formatter);
                    }
                    Date billingEndDate = null;
                    if (!(ParamUtil.getString(request, actionPerformed + SHARED_BILLING_END_DATE + i).isEmpty())) {
                        billingEndDate = ParamUtil.getDate(request, actionPerformed + SHARED_BILLING_END_DATE + i, formatter);
                    }
                    Date lwdDate = null;
                    if (!(ParamUtil.getString(request, actionPerformed + SHARED_LAST_WORKING_DATE + i).isEmpty())) {
                        lwdDate = ParamUtil.getDate(request, actionPerformed + SHARED_LAST_WORKING_DATE + i, formatter);
                    }
                    Billing employeeBillingDetails = BillingLocalServiceUtil.createBilling(CounterLocalServiceUtil.increment());
                    employeeBillingDetails.setEcode(ecode.toUpperCase());
                    employeeBillingDetails.setAccount(account);
                    employeeBillingDetails.setProject(project);
                    employeeBillingDetails.setCurrent(current);
                    employeeBillingDetails.setCurrentPlusOneMonth(currentPlusOne);
                    employeeBillingDetails.setCurrentPlusTwoMonth(currentPlusTwo);
                    employeeBillingDetails.setShadowResourceType(shadowResourceType);
                    employeeBillingDetails.setShadowStartDate(shadowStartDate);
                    employeeBillingDetails.setBenchStartDate(benchStartDate);
                    employeeBillingDetails.setBillingStartDate(billingStartDate);
                    employeeBillingDetails.setBillingEndDate(billingEndDate);
                    employeeBillingDetails.setEmployeeStatus(employeeStatus);
                    employeeBillingDetails.setLastWorkingDate(lwdDate);
                    employeeBillingDetails.setRemarks(remarks);
                    employeeBillingDetails.setBillableHours(billableHours);
                    employeeBillingDetails.setEmployeeRole(employeeRole);
                    employeeBillingDetails.setVertical(vertical);
                    employeeBillingDetails.setManagerEcode(managerEcode.toUpperCase());
                    employeeBillingDetails.setLeadEcode(leadEode.toUpperCase());
                    employeeBillingDetails.setShared(true);
                    employeeBillingDetails.setPercentUtilization(percentageUtilization);
                    employeeBillingDetails.setCoordinatorEcode(coordinatorEcode.toUpperCase());
                    employeeBillingDetails.setBillingDate(billingDate);
                    employeeBillingDetails.setUpdatedBy(actionPerformedBy.getEmpId());
                    employeeBillingDetails.setUpdatedDate(updateDate);
                    BillingLocalServiceUtil.addBilling(employeeBillingDetails);
                }
            }
            if (managerAction == 2) {
                EmployeeData managerData = EmployeeDataLocalServiceUtil.findByEmail(loginedUser.getEmailAddress());
                Submission sendRequest = SubmissionLocalServiceUtil.createSubmission(CounterLocalServiceUtil.increment());
                sendRequest.setSubmittedBy(managerData.getEmpId());
                sendRequest.setMonth(month);
                sendRequest.setYear(year);
                sendRequest.setSubmitDate(new Date());
                SubmissionLocalServiceUtil.addSubmission(sendRequest);
                String monthName = todaydate.getMonth().toString();
                monthName= monthName.substring(0, 1).toUpperCase()  + monthName.substring(1).toLowerCase();
                Emailer email = EmailerLocalServiceUtil.createEmailer(CounterLocalServiceUtil.increment());
                email.setSubject(MessageFormat.format(SUBJECT_BILLING_SUBMISSION, managerData.getUserName(),monthName+" "+year));
                email.setBody(MessageFormat.format(BODY_BILLING_SUBMISSION, managerData.getUserName(),monthName+" "+year, HOSTNAME + BILLING_URL+SELECTED_MONTH+selectedMonth));
                email.setToAddress("kamal.sharma@trantorinc.com"+","+"hema.sharma@trantorinc.com");
                email.setCreatedDate(new Date());
                email.setSent(false);
                EmailerLocalServiceUtil.addEmailer(email);
            }
        }
        String portletName = td.getPortletDisplay().getPortletName();
        PortletURL portletURL = PortletURLFactoryUtil.create(PortalUtil.getHttpServletRequest(request), portletName, td.getLayout().getPlid(), PortletRequest.RENDER_PHASE);
        String redirectUrl = portletURL.toString().split("\\?")[0] + SELECTED_MONTH+selectedMonth;
        request.setAttribute(WebKeys.REDIRECT, redirectUrl);
        response.sendRedirect(redirectUrl);
    }

    //To Submit Finance details
    @ProcessAction(name = "submitBillingFinanceDetails")
    public void submitBillingFinanceDetails(ActionRequest request, ActionResponse response) throws IOException {
        ThemeDisplay td = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        User loginedUser = td.getUser();
        LocalDate todaydate = LocalDate.now();
        List<EmployeeData> allEmployeeList = EmployeeDataLocalServiceUtil.findAllEmployees();
        EmployeeData actionPerformedBy = allEmployeeList.stream().filter(e -> e.getEmail().equalsIgnoreCase(loginedUser.getEmailAddress())).collect(Collectors.toList()).get(0);
        String actionPerformed = ParamUtil.getString(request, ACTION_PERFORMED);
        String selectedMonth = ParamUtil.getString(request, "submitDatef");
        int month = todaydate.getMonthValue();
        int year = todaydate.getYear();
        Date updateDate = new Date();
        Date billingDate = null;
        if (selectedMonth != null) {
            String[] exportValues = selectedMonth.split("/");
            month = Integer.parseInt(exportValues[0]);
            year = Integer.parseInt(exportValues[1]);
            LocalDate date = LocalDate.of(year, month, 1);
            billingDate = Date.from(date.atStartOfDay().toInstant(ZoneOffset.UTC));
        }
        int validateAction = Integer.parseInt(ParamUtil.getString(request, "fvalidateAction"));
        if (validateAction == 1) {
            long billingId = ParamUtil.getLong(request, "fdeleting");
            Billing billingInfo = BillingLocalServiceUtil.fetchBilling(billingId);
            billingInfo.setDeleted(true);
            billingInfo.setUpdatedBy(actionPerformedBy.getEmpId());
            billingInfo.setUpdatedDate(updateDate);
            BillingLocalServiceUtil.updateBilling(billingInfo);
        } else {
            String[] selectedBillingIds = ParamUtil.getParameterValues(request, actionPerformed + CHECKBOX, null);
            if (selectedBillingIds != null) {
                for (String billingId : selectedBillingIds) {
                    String current = ParamUtil.getString(request, actionPerformed + CURRENT + billingId);
                    String currentPlusOne = ParamUtil.getString(request, actionPerformed + CURRENT_PLUS_ONE + billingId);
                    String currentPlusTwo = ParamUtil.getString(request, actionPerformed + CURRENT_PLUS_TWO + billingId);
                    String shadowResourceType = ParamUtil.getString(request, actionPerformed + SHADOW_RESOURCE_TYPE + billingId);
                    String allocationStatus = ParamUtil.getString(request, actionPerformed + ALLOCATION_STATUS + billingId);
                    String employeeStatus = ParamUtil.getString(request, actionPerformed + EMPLOYEE_STATUS + billingId);
                    String percentUtilization = ParamUtil.getString(request, actionPerformed + PERCENT_UTILIZATION + billingId);
                    String remarks = ParamUtil.getString(request, actionPerformed + REMARKS + billingId);
                    String billableHours = ParamUtil.getString(request, actionPerformed + BILLABLE_HOURS + billingId);
                    String monthHours = ParamUtil.getString(request, actionPerformed + MONTH_HOURS + billingId);
                    String employeeRole = ParamUtil.getString(request, actionPerformed + EMPLOYEE_ROLE + billingId);
                    String vertical = ParamUtil.getString(request, actionPerformed + VERTICAL + billingId);

                    Date shadowStartDate = null;
                    if (!(ParamUtil.getString(request, actionPerformed + SHADOW_START_DATE + billingId).isEmpty())) {
                        shadowStartDate = ParamUtil.getDate(request, actionPerformed + SHADOW_START_DATE + billingId, formatter);
                    }
                    Date benchStartDate = null;
                    if (!(ParamUtil.getString(request, actionPerformed + BENCH_START_DATE + billingId).isEmpty())) {
                        benchStartDate = ParamUtil.getDate(request, actionPerformed + BENCH_START_DATE + billingId, formatter);
                    }
                    Date billingStartDate = null;
                    if (!(ParamUtil.getString(request, actionPerformed + BILLING_START_DATE + billingId).isEmpty())) {
                        billingStartDate = ParamUtil.getDate(request, actionPerformed + BILLING_START_DATE + billingId, formatter);
                    }
                    Date endDate = null;
                    if (!(ParamUtil.getString(request, actionPerformed + END_DATE_STR + billingId).isEmpty())) {
                        endDate = ParamUtil.getDate(request, actionPerformed + END_DATE_STR + billingId, formatter);
                    }
                    Date lwdDate = null;
                    if (!(ParamUtil.getString(request, actionPerformed + LAST_WORKING_DAY + billingId).isEmpty())) {
                        lwdDate = ParamUtil.getDate(request, actionPerformed + LAST_WORKING_DAY + billingId, formatter);
                    }
                    Billing employeeBillingDetails = BillingLocalServiceUtil.fetchBilling(Long.parseLong(billingId));
                    employeeBillingDetails.setCurrent(current);
                    employeeBillingDetails.setCurrentPlusOneMonth(currentPlusOne);
                    employeeBillingDetails.setCurrentPlusTwoMonth(currentPlusTwo);
                    employeeBillingDetails.setShadowResourceType(shadowResourceType);
                    employeeBillingDetails.setShadowStartDate(shadowStartDate);
                    employeeBillingDetails.setBenchStartDate(benchStartDate);
                    employeeBillingDetails.setBillingStartDate(billingStartDate);
                    employeeBillingDetails.setBillingEndDate(endDate);
                    employeeBillingDetails.setAllocationStatus(allocationStatus);
                    employeeBillingDetails.setEmployeeStatus(employeeStatus);
                    employeeBillingDetails.setLastWorkingDate(lwdDate);
                    employeeBillingDetails.setBillableHours(billableHours);
                    employeeBillingDetails.setMonthHours(monthHours);
                    employeeBillingDetails.setEmployeeRole(employeeRole);
                    employeeBillingDetails.setVertical(vertical);
                    employeeBillingDetails.setPercentUtilization(percentUtilization);
                    employeeBillingDetails.setRemarks(remarks);
                    employeeBillingDetails.setUpdatedBy(actionPerformedBy.getEmpId());
                    employeeBillingDetails.setUpdatedDate(updateDate);
                    BillingLocalServiceUtil.updateBilling(employeeBillingDetails);
                }
            }
            int existingRows = Integer.parseInt(ParamUtil.getString(request, EXISTING_ROWS));
            int totalRows = Integer.parseInt(ParamUtil.getString(request, TOTAL_ROWS));
            for (int i = 0; i < existingRows; i++) {
                String[] billingId = ParamUtil.getParameterValues(request, actionPerformed + SHARED_CHECKBOX + i, null);
                if (billingId != null && billingId.length == 1) {
                    Billing employeeBillingInfo = BillingLocalServiceUtil.fetchBilling(Long.parseLong(billingId[0]));
                    String ecode = ParamUtil.getString(request, actionPerformed + SHARED_ECODE + i);
                    String managerEcode = ParamUtil.getString(request, actionPerformed + SHARED_MANAGER_ECODE + i);
                    String leadEode = ParamUtil.getString(request, actionPerformed + SHARED_LEAD_ECODE + i);
                    String current = ParamUtil.getString(request, actionPerformed + SHARED_CURRENT + i);
                    String currentPlusOne = ParamUtil.getString(request, actionPerformed + SHARED_CURRENT_PLUS_ONE + i);
                    String currentPlusTwo = ParamUtil.getString(request, actionPerformed + SHARED_CURRENT_PLUS_TWO + i);
                    String shadowResourceType = ParamUtil.getString(request, actionPerformed + SHARED_SHADOW_RESOURCE_TYPE + i);
                    String employeeStatus = ParamUtil.getString(request, actionPerformed + SHARED_EMPLOYEE_STATUS + i);
                    String percentageUtilization = ParamUtil.getString(request, actionPerformed + SHARED_SHARED_PERCENTAGE + i);
                    String account = ParamUtil.getString(request, actionPerformed + SHARED_ACCOUNT + i);
                    String project = ParamUtil.getString(request, actionPerformed + SHARED_PROJECT + i);
                    String coordinatorEcode = ParamUtil.getString(request, actionPerformed + SHARED_COORDINATOR_ECODE + i);
                    String remarks = ParamUtil.getString(request, actionPerformed + SHARED_REMARKS + i);
                    String billableHours = ParamUtil.getString(request, actionPerformed + SHARED_BILLABLE_HOURS + i);
                    String monthHours = ParamUtil.getString(request, actionPerformed + SHARED_MONTH_HOURS+ i);
                    String employeeRole = ParamUtil.getString(request, actionPerformed + SHARED_EMPLOYEE_ROLE + i);
                    String vertical = ParamUtil.getString(request, actionPerformed + SHARED_VERTICAL+ i);
                    Date shadowStartDate = null;
                    if (!(ParamUtil.getString(request, actionPerformed + SHARED_SHADOW_START_DATE + i).isEmpty())) {
                        shadowStartDate = ParamUtil.getDate(request, actionPerformed + SHARED_SHADOW_START_DATE + i, formatter);
                    }
                    Date benchStartDate = null;
                    if (!(ParamUtil.getString(request, actionPerformed + SHARED_BENCH_START_DATE + i).isEmpty())) {
                        benchStartDate = ParamUtil.getDate(request, actionPerformed + SHARED_BENCH_START_DATE + i, formatter);
                    }
                    Date billingStartDate = null;
                    if (!(ParamUtil.getString(request, actionPerformed + SHARED_BILLING_START_DATE + i).isEmpty())) {
                        billingStartDate = ParamUtil.getDate(request, actionPerformed + SHARED_BILLING_START_DATE + i, formatter);
                    }
                    Date billingEndDate = null;
                    if (!(ParamUtil.getString(request, actionPerformed + SHARED_BILLING_END_DATE + i).isEmpty())) {
                        billingEndDate = ParamUtil.getDate(request, actionPerformed + SHARED_BILLING_END_DATE + i, formatter);
                    }

                    Date lwdDate = null;
                    if (!(ParamUtil.getString(request, actionPerformed + SHARED_LAST_WORKING_DATE + i).isEmpty())) {
                        lwdDate = ParamUtil.getDate(request, actionPerformed + SHARED_LAST_WORKING_DATE + i, formatter);
                    }
                    employeeBillingInfo.setEcode(ecode.toUpperCase());
                    employeeBillingInfo.setAccount(account);
                    employeeBillingInfo.setProject(project);
                    employeeBillingInfo.setCurrent(current);
                    employeeBillingInfo.setCurrentPlusOneMonth(currentPlusOne);
                    employeeBillingInfo.setCurrentPlusTwoMonth(currentPlusTwo);
                    employeeBillingInfo.setShadowResourceType(shadowResourceType);
                    employeeBillingInfo.setShadowStartDate(shadowStartDate);
                    employeeBillingInfo.setBenchStartDate(benchStartDate);
                    employeeBillingInfo.setBillingStartDate(billingStartDate);
                    employeeBillingInfo.setBillingEndDate(billingEndDate);
                    employeeBillingInfo.setEmployeeStatus(employeeStatus);
                    employeeBillingInfo.setLastWorkingDate(lwdDate);
                    employeeBillingInfo.setManagerEcode(managerEcode.toUpperCase());
                    employeeBillingInfo.setLeadEcode(leadEode.toUpperCase());
                    employeeBillingInfo.setShared(true);
                    employeeBillingInfo.setPercentUtilization(percentageUtilization);
                    employeeBillingInfo.setCoordinatorEcode(coordinatorEcode.toUpperCase());
                    employeeBillingInfo.setRemarks(remarks);
                    employeeBillingInfo.setBillableHours(billableHours);
                    employeeBillingInfo.setMonthHours(monthHours);
                    employeeBillingInfo.setEmployeeRole(employeeRole);
                    employeeBillingInfo.setVertical(vertical);
                    employeeBillingInfo.setUpdatedBy(actionPerformedBy.getEmpId());
                    employeeBillingInfo.setUpdatedDate(updateDate);
                    BillingLocalServiceUtil.updateBilling(employeeBillingInfo);
                }
            }
            if (totalRows > existingRows) {
                for (int i = existingRows; i < totalRows; i++) {
                    String ecode = ParamUtil.getString(request, actionPerformed + SHARED_ECODE + i);
                    String managerEcode = ParamUtil.getString(request, actionPerformed + SHARED_MANAGER_ECODE + i);
                    String leadEode = ParamUtil.getString(request, actionPerformed + SHARED_LEAD_ECODE + i);
                    String current = ParamUtil.getString(request, actionPerformed + SHARED_CURRENT + i);
                    String currentPlusOne = ParamUtil.getString(request, actionPerformed + SHARED_CURRENT_PLUS_ONE + i);
                    String currentPlusTwo = ParamUtil.getString(request, actionPerformed + SHARED_CURRENT_PLUS_TWO + i);
                    String shadowResourceType = ParamUtil.getString(request, actionPerformed + SHARED_SHADOW_RESOURCE_TYPE + i);
                    String account = ParamUtil.getString(request, actionPerformed + SHARED_ACCOUNT + i);
                    String project = ParamUtil.getString(request, actionPerformed + SHARED_PROJECT + i);
                    String coordinatorEcode = ParamUtil.getString(request, actionPerformed + SHARED_COORDINATOR_ECODE + i);
                    String percentageUtilization = ParamUtil.getString(request, actionPerformed + SHARED_SHARED_PERCENTAGE + i);
                    String remarks = ParamUtil.getString(request, actionPerformed + SHARED_REMARKS + i);
                    String billableHours = ParamUtil.getString(request, actionPerformed + SHARED_BILLABLE_HOURS + i);
                    String monthHours = ParamUtil.getString(request, actionPerformed + SHARED_MONTH_HOURS + i);
                    String employeeRole = ParamUtil.getString(request, actionPerformed + SHARED_EMPLOYEE_ROLE + i);
                    String vertical = ParamUtil.getString(request, actionPerformed + SHARED_VERTICAL + i);
                    String employeeStatus = ParamUtil.getString(request, actionPerformed + SHARED_EMPLOYEE_STATUS + i);

                    Date shadowStartDate = null;
                    if (!(ParamUtil.getString(request, actionPerformed + SHARED_SHADOW_START_DATE + i).isEmpty())) {
                        shadowStartDate = ParamUtil.getDate(request, actionPerformed + SHARED_SHADOW_START_DATE + i, formatter);
                    }
                    Date benchStartDate = null;
                    if (!(ParamUtil.getString(request, actionPerformed + SHARED_BENCH_START_DATE + i).isEmpty())) {
                        benchStartDate = ParamUtil.getDate(request, actionPerformed + SHARED_BENCH_START_DATE + i, formatter);
                    }
                    Date billingStartDate = null;
                    if (!(ParamUtil.getString(request, actionPerformed + SHARED_BILLING_START_DATE + i).isEmpty())) {
                        billingStartDate = ParamUtil.getDate(request, actionPerformed + SHARED_BILLING_START_DATE + i, formatter);
                    }
                    Date billingEndDate = null;
                    if (!(ParamUtil.getString(request, actionPerformed + SHARED_BILLING_END_DATE + i).isEmpty())) {
                        billingEndDate = ParamUtil.getDate(request, actionPerformed + SHARED_BILLING_END_DATE + i, formatter);
                    }

                    Date lwdDate = null;
                    if (!(ParamUtil.getString(request, actionPerformed + SHARED_LAST_WORKING_DATE + i).isEmpty())) {
                        lwdDate = ParamUtil.getDate(request, actionPerformed + SHARED_LAST_WORKING_DATE + i, formatter);
                    }

                    Billing employeeBillingDetails = BillingLocalServiceUtil.createBilling(CounterLocalServiceUtil.increment());
                    employeeBillingDetails.setEcode(ecode.toUpperCase());
                    employeeBillingDetails.setAccount(account);
                    employeeBillingDetails.setProject(project);
                    employeeBillingDetails.setCurrent(current);
                    employeeBillingDetails.setCurrentPlusOneMonth(currentPlusOne);
                    employeeBillingDetails.setCurrentPlusTwoMonth(currentPlusTwo);
                    employeeBillingDetails.setShadowResourceType(shadowResourceType);
                    employeeBillingDetails.setShadowStartDate(shadowStartDate);
                    employeeBillingDetails.setBenchStartDate(benchStartDate);
                    employeeBillingDetails.setBillingStartDate(billingStartDate);
                    employeeBillingDetails.setBillingEndDate(billingEndDate);
                    employeeBillingDetails.setEmployeeStatus(employeeStatus);
                    employeeBillingDetails.setLastWorkingDate(lwdDate);
                    employeeBillingDetails.setRemarks(remarks);
                    employeeBillingDetails.setBillableHours(billableHours);
                    employeeBillingDetails.setMonthHours(monthHours);
                    employeeBillingDetails.setEmployeeRole(employeeRole);
                    employeeBillingDetails.setVertical(vertical);
                    employeeBillingDetails.setManagerEcode(managerEcode.toUpperCase());
                    employeeBillingDetails.setLeadEcode(leadEode.toUpperCase());
                    employeeBillingDetails.setShared(true);
                    employeeBillingDetails.setPercentUtilization(percentageUtilization);
                    employeeBillingDetails.setCoordinatorEcode(coordinatorEcode.toUpperCase());
                    employeeBillingDetails.setUpdatedDate(updateDate);
                    employeeBillingDetails.setUpdatedBy(actionPerformedBy.getEmpId());
                    employeeBillingDetails.setBillingDate(billingDate);
                    BillingLocalServiceUtil.addBilling(employeeBillingDetails);
                }
            }
        }
        String portletName = td.getPortletDisplay().getPortletName();
        PortletURL portletURL = PortletURLFactoryUtil.create(PortalUtil.getHttpServletRequest(request), portletName, td.getLayout().getPlid(), PortletRequest.RENDER_PHASE);
        String redirectUrl = portletURL.toString().split("\\?")[0] + SELECTED_MONTH+selectedMonth;
        request.setAttribute(WebKeys.REDIRECT, redirectUrl);
        response.sendRedirect(redirectUrl);
    }

    @ProcessAction(name = "submitBillingHrDetails")
    public void submitBillingHrDetails(ActionRequest request, ActionResponse response) throws IOException {
        ThemeDisplay td = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        User loginedUser = td.getUser();
        LocalDate todayDate = LocalDate.now();
        Date updateDate = new Date();
        List<EmployeeData> allEmployeeList = EmployeeDataLocalServiceUtil.findAllEmployees();
        EmployeeData actionPerformedBy = allEmployeeList.stream().filter(e -> e.getEmail().equalsIgnoreCase(loginedUser.getEmailAddress())).collect(Collectors.toList()).get(0);
        String actionPerformed = ParamUtil.getString(request, ACTION_PERFORMED);
        String[] selectedBillingIds = ParamUtil.getParameterValues(request, actionPerformed +CHECKBOX, null);
        for (String billingId : selectedBillingIds) {
            String current = ParamUtil.getString(request, actionPerformed + CURRENT + billingId);
            String currentPlusOne = ParamUtil.getString(request, actionPerformed + CURRENT_PLUS_ONE + billingId);
            String currentPlusTwo = ParamUtil.getString(request, actionPerformed + CURRENT_PLUS_TWO + billingId);
            String shadowResourceType = ParamUtil.getString(request, actionPerformed + SHADOW_RESOURCE_TYPE + billingId);
            Date shadowStartDate = null;
            if (!(ParamUtil.getString(request, actionPerformed + SHADOW_START_DATE + billingId).isEmpty())) {
                shadowStartDate = ParamUtil.getDate(request, actionPerformed + SHADOW_START_DATE + billingId, formatter);
            }
            Date benchStartDate = null;
            if (!(ParamUtil.getString(request, actionPerformed + BENCH_START_DATE + billingId).isEmpty())) {
                benchStartDate = ParamUtil.getDate(request, actionPerformed + BENCH_START_DATE + billingId, formatter);
                _log.info("bench start date== "+benchStartDate);
            }
            Date billingStartDate = null;
            if (!(ParamUtil.getString(request, actionPerformed + BILLING_START_DATE + billingId).isEmpty())) {
                billingStartDate = ParamUtil.getDate(request, actionPerformed + BILLING_START_DATE + billingId, formatter);
            }
            Date endDate = null;
            if (!(ParamUtil.getString(request, actionPerformed +END_DATE_STR + billingId).isEmpty())) {
                endDate = ParamUtil.getDate(request, actionPerformed + END_DATE_STR + billingId, formatter);
            }
            String allocationStatus = ParamUtil.getString(request, actionPerformed + ALLOCATION_STATUS + billingId);
            String employeeStatus = ParamUtil.getString(request, actionPerformed + EMPLOYEE_STATUS + billingId);
            Date lwdDate = null;
            if (!(ParamUtil.getString(request, actionPerformed + LAST_WORKING_DAY + billingId).isEmpty())) {
                lwdDate = ParamUtil.getDate(request, actionPerformed + LAST_WORKING_DAY + billingId, formatter);
            }

            String percentUtilization = ParamUtil.getString(request, actionPerformed + PERCENT_UTILIZATION + billingId);
            String remarks = ParamUtil.getString(request, actionPerformed + REMARKS + billingId);
            String vertical = ParamUtil.getString(request, actionPerformed + VERTICAL + billingId);
            Billing employeeBillingDetails = BillingLocalServiceUtil.fetchBilling(Long.parseLong(billingId));
            employeeBillingDetails.setCurrent(current);
            employeeBillingDetails.setCurrentPlusOneMonth(currentPlusOne);
            employeeBillingDetails.setCurrentPlusTwoMonth(currentPlusTwo);
            employeeBillingDetails.setShadowResourceType(shadowResourceType);
            employeeBillingDetails.setShadowStartDate(shadowStartDate);
            employeeBillingDetails.setBenchStartDate(benchStartDate);
            employeeBillingDetails.setBillingStartDate(billingStartDate);
            employeeBillingDetails.setBillingEndDate(endDate);
            employeeBillingDetails.setAllocationStatus(allocationStatus);
            employeeBillingDetails.setEmployeeStatus(employeeStatus);
            employeeBillingDetails.setLastWorkingDate(lwdDate);
            employeeBillingDetails.setUpdatedDate(updateDate);
            employeeBillingDetails.setUpdatedBy(actionPerformedBy.getEmpId());
            employeeBillingDetails.setVertical(vertical);
            employeeBillingDetails.setPercentUtilization(percentUtilization);
            employeeBillingDetails.setRemarks(remarks);
            BillingLocalServiceUtil.updateBilling(employeeBillingDetails);
        }
        String portletName = td.getPortletDisplay().getPortletName();
        PortletURL portletURL = PortletURLFactoryUtil.create(PortalUtil.getHttpServletRequest(request), portletName, td.getLayout().getPlid(), PortletRequest.RENDER_PHASE);
        String selectedMonth=String.format("%02d",todayDate.getMonthValue()) + "/" + todayDate.getYear();
        String redirectUrl = portletURL.toString().split("\\?")[0] + SELECTED_MONTH+selectedMonth;
        request.setAttribute(WebKeys.REDIRECT, redirectUrl);
        response.sendRedirect(redirectUrl);
    }

    @ProcessAction(name = "submitHrSharedBillingDetails")
    public void submitHrSharedBillingDetails(ActionRequest request, ActionResponse response) throws IOException {
        ThemeDisplay td = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        User loginedUser = td.getUser();
        EmployeeData actionPerformedBy = EmployeeDataLocalServiceUtil.findByEmail(loginedUser.getEmailAddress());
        String actionPerformed = ParamUtil.getString(request, "actionPerformedShared");
        Date updateDate = new Date();
        LocalDate todayDate = LocalDate.now();
        int validateAction = Integer.parseInt(ParamUtil.getString(request, "validateAction"));
        if (validateAction == 1) {
            long billingId = ParamUtil.getLong(request, "deleting");
            Billing billingInfo = BillingLocalServiceUtil.fetchBilling(billingId);
            billingInfo.setDeleted(true);
            billingInfo.setUpdatedBy(actionPerformedBy.getEmpId());
            billingInfo.setUpdatedDate(updateDate);
            BillingLocalServiceUtil.updateBilling(billingInfo);
        } else {
            int existingRows = Integer.parseInt(ParamUtil.getString(request, EXISTING_ROWS));
            int totalRows = Integer.parseInt(ParamUtil.getString(request, TOTAL_ROWS));
            for (int i = 0; i < existingRows; i++) {
                String[] billingId = ParamUtil.getParameterValues(request, actionPerformed + SHARED_CHECKBOX + i, null);
                if (billingId != null && billingId.length == 1) {
                    Billing employeeBillingInfo = BillingLocalServiceUtil.fetchBilling(Long.parseLong(billingId[0]));
                    String ecode = ParamUtil.getString(request, actionPerformed + SHARED_ECODE + i);
                    String managerEcode = ParamUtil.getString(request, actionPerformed + SHARED_MANAGER_ECODE + i);
                    String leadEode = ParamUtil.getString(request, actionPerformed + SHARED_LEAD_ECODE + i);
                    String current = ParamUtil.getString(request, actionPerformed + SHARED_CURRENT + i);
                    String currentPlusOne = ParamUtil.getString(request, actionPerformed + SHARED_CURRENT_PLUS_ONE + i);
                    String currentPlusTwo = ParamUtil.getString(request, actionPerformed + SHARED_CURRENT_PLUS_TWO + i);
                    String shadowResourceType = ParamUtil.getString(request, actionPerformed + SHARED_SHADOW_RESOURCE_TYPE + i);
                    String employeeStatus = ParamUtil.getString(request, actionPerformed + SHARED_EMPLOYEE_STATUS + i);
                    String percentageUtilization = ParamUtil.getString(request, actionPerformed + SHARED_SHARED_PERCENTAGE + i);
                    String account = ParamUtil.getString(request, actionPerformed + SHARED_ACCOUNT + i);
                    String project = ParamUtil.getString(request, actionPerformed + SHARED_PROJECT + i);
                    String coordinatorEcode = ParamUtil.getString(request, actionPerformed + SHARED_COORDINATOR_ECODE + i);
                    String remarks = ParamUtil.getString(request, actionPerformed + SHARED_REMARKS + i);
                    String vertical = ParamUtil.getString(request, actionPerformed + SHARED_VERTICAL + i);

                    Date shadowStartDate = null;
                    if (!(ParamUtil.getString(request, actionPerformed + SHARED_SHADOW_START_DATE + i).isEmpty())) {
                        shadowStartDate = ParamUtil.getDate(request, actionPerformed + SHARED_SHADOW_START_DATE + i, formatter);
                    }
                    Date benchStartDate = null;
                    if (!(ParamUtil.getString(request, actionPerformed + SHARED_BENCH_START_DATE + i).isEmpty())) {
                        benchStartDate = ParamUtil.getDate(request, actionPerformed + SHARED_BENCH_START_DATE + i, formatter);
                    }
                    Date billingStartDate = null;
                    if (!(ParamUtil.getString(request, actionPerformed + SHARED_BILLING_START_DATE + i).isEmpty())) {
                        billingStartDate = ParamUtil.getDate(request, actionPerformed + SHARED_BILLING_START_DATE + i, formatter);
                    }
                    Date billingEndDate = null;
                    if (!(ParamUtil.getString(request, actionPerformed + SHARED_BILLING_END_DATE + i).isEmpty())) {
                        billingEndDate = ParamUtil.getDate(request, actionPerformed + SHARED_BILLING_END_DATE + i, formatter);
                    }
                    Date lwdDate = null;
                    if (!(ParamUtil.getString(request, actionPerformed + SHARED_LAST_WORKING_DATE + i).isEmpty())) {
                        lwdDate = ParamUtil.getDate(request, actionPerformed + SHARED_LAST_WORKING_DATE + i, formatter);
                    }

                    employeeBillingInfo.setEcode(ecode.toUpperCase());
                    employeeBillingInfo.setAccount(account);
                    employeeBillingInfo.setProject(project);
                    employeeBillingInfo.setCurrent(current);
                    employeeBillingInfo.setCurrentPlusOneMonth(currentPlusOne);
                    employeeBillingInfo.setCurrentPlusTwoMonth(currentPlusTwo);
                    employeeBillingInfo.setShadowResourceType(shadowResourceType);
                    employeeBillingInfo.setShadowStartDate(shadowStartDate);
                    employeeBillingInfo.setBenchStartDate(benchStartDate);
                    employeeBillingInfo.setBillingStartDate(billingStartDate);
                    employeeBillingInfo.setBillingEndDate(billingEndDate);
                    employeeBillingInfo.setEmployeeStatus(employeeStatus);
                    employeeBillingInfo.setLastWorkingDate(lwdDate);
                    employeeBillingInfo.setUpdatedDate(updateDate);
                    employeeBillingInfo.setUpdatedBy(actionPerformedBy.getEmpId());
                    employeeBillingInfo.setManagerEcode(managerEcode.toUpperCase());
                    employeeBillingInfo.setLeadEcode(leadEode.toUpperCase());
                    employeeBillingInfo.setPercentUtilization(percentageUtilization);
                    employeeBillingInfo.setCoordinatorEcode(coordinatorEcode.toUpperCase());
                    employeeBillingInfo.setRemarks(remarks);
                    employeeBillingInfo.setVertical(vertical);
                    BillingLocalServiceUtil.updateBilling(employeeBillingInfo);
                }
            }
            if (totalRows > existingRows) {
                LocalDate date = LocalDate.of(todayDate.getYear(), todayDate.getMonthValue(), 1);
                Date billingDate = Date.from(date.atStartOfDay().toInstant(ZoneOffset.UTC));
                for (int i = existingRows; i < totalRows; i++) {
                    String ecode = ParamUtil.getString(request, actionPerformed + SHARED_ECODE + i);
                    String managerEcode = ParamUtil.getString(request, actionPerformed + SHARED_MANAGER_ECODE + i);
                    String leadEode = ParamUtil.getString(request, actionPerformed + SHARED_LEAD_ECODE + i);
                    String current = ParamUtil.getString(request, actionPerformed + SHARED_CURRENT + i);
                    String currentPlusOne = ParamUtil.getString(request, actionPerformed +SHARED_CURRENT_PLUS_ONE + i);
                    String currentPlusTwo = ParamUtil.getString(request, actionPerformed + SHARED_CURRENT_PLUS_TWO + i);
                    String shadowResourceType = ParamUtil.getString(request, actionPerformed + SHARED_SHADOW_RESOURCE_TYPE + i);
                    String account = ParamUtil.getString(request, actionPerformed + SHARED_ACCOUNT + i);
                    String project = ParamUtil.getString(request, actionPerformed + SHARED_PROJECT + i);
                    String coordinatorEcode = ParamUtil.getString(request, actionPerformed + SHARED_COORDINATOR_ECODE + i);
                    String employeeStatus = ParamUtil.getString(request, actionPerformed + SHARED_EMPLOYEE_STATUS + i);
                    String percentageUtilization = ParamUtil.getString(request, actionPerformed + SHARED_SHARED_PERCENTAGE + i);
                    String remarks = ParamUtil.getString(request, actionPerformed + SHARED_REMARKS + i);
                    String vertical = ParamUtil.getString(request, actionPerformed + SHARED_VERTICAL + i);
                    Date shadowStartDate = null;
                    if (!(ParamUtil.getString(request, actionPerformed + SHARED_SHADOW_START_DATE + i).isEmpty())) {
                        shadowStartDate = ParamUtil.getDate(request, actionPerformed + SHARED_SHADOW_START_DATE + i, formatter);
                    }
                    Date benchStartDate = null;
                    if (!(ParamUtil.getString(request, actionPerformed + SHARED_BENCH_START_DATE + i).isEmpty())) {
                        benchStartDate = ParamUtil.getDate(request, actionPerformed + SHARED_BENCH_START_DATE + i, formatter);
                    }
                    Date billingStartDate = null;
                    if (!(ParamUtil.getString(request, actionPerformed + SHARED_BILLING_START_DATE + i).isEmpty())) {
                        billingStartDate = ParamUtil.getDate(request, actionPerformed + SHARED_BILLING_START_DATE + i, formatter);
                    }
                    Date endDate = null;
                    if (!(ParamUtil.getString(request, actionPerformed + SHARED_BILLING_END_DATE + i).isEmpty())) {
                        endDate = ParamUtil.getDate(request, actionPerformed + SHARED_BILLING_END_DATE + i, formatter);
                    }
                    Date lwdDate = null;
                    if (!(ParamUtil.getString(request, actionPerformed + SHARED_LAST_WORKING_DATE + i).isEmpty())) {
                        lwdDate = ParamUtil.getDate(request, actionPerformed + SHARED_LAST_WORKING_DATE + i, formatter);
                    }
                    Billing employeeBillingDetails = BillingLocalServiceUtil.createBilling(CounterLocalServiceUtil.increment());
                    employeeBillingDetails.setEcode(ecode.toUpperCase());
                    employeeBillingDetails.setAccount(account);
                    employeeBillingDetails.setProject(project);
                    employeeBillingDetails.setCurrent(current);
                    employeeBillingDetails.setCurrentPlusOneMonth(currentPlusOne);
                    employeeBillingDetails.setCurrentPlusTwoMonth(currentPlusTwo);
                    employeeBillingDetails.setShadowResourceType(shadowResourceType);
                    employeeBillingDetails.setShadowStartDate(shadowStartDate);
                    employeeBillingDetails.setBenchStartDate(benchStartDate);
                    employeeBillingDetails.setBillingStartDate(billingStartDate);
                    employeeBillingDetails.setBillingEndDate(endDate);
                    employeeBillingDetails.setEmployeeStatus(employeeStatus);
                    employeeBillingDetails.setLastWorkingDate(lwdDate);
                    employeeBillingDetails.setUpdatedDate(updateDate);
                    employeeBillingDetails.setBillingDate(billingDate);
                    employeeBillingDetails.setRemarks(remarks);
                    employeeBillingDetails.setVertical(vertical);
                    employeeBillingDetails.setUpdatedBy(actionPerformedBy.getEmpId());
                    employeeBillingDetails.setManagerEcode(managerEcode.toUpperCase());
                    employeeBillingDetails.setLeadEcode(leadEode.toUpperCase());
                    employeeBillingDetails.setShared(true);
                    employeeBillingDetails.setPercentUtilization(percentageUtilization);
                    employeeBillingDetails.setCoordinatorEcode(coordinatorEcode.toUpperCase());
                    BillingLocalServiceUtil.addBilling(employeeBillingDetails);
                }
            }
        }
        String portletName = td.getPortletDisplay().getPortletName();
        PortletURL portletURL = PortletURLFactoryUtil.create(PortalUtil.getHttpServletRequest(request), portletName, td.getLayout().getPlid(), PortletRequest.RENDER_PHASE);
        String selectedMonth= String.format("%02d",todayDate.getMonthValue()) + "/" + todayDate.getYear();
        String redirectUrl = portletURL.toString().split("\\?")[0] + SELECTED_MONTH+selectedMonth;
        request.setAttribute(WebKeys.REDIRECT, redirectUrl);
        response.sendRedirect(redirectUrl);
    }

    @ProcessAction(name = "downloadEmployeeFinanceReport")
    public void downloadEmployeeFinanceReport(ActionRequest request, ActionResponse response) throws IOException {
        ThemeDisplay td = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        String reporting = ParamUtil.getString(request, "reporting");
        String[] exportValues = reporting.split("/");
        int month = Integer.parseInt(exportValues[0]);
        int year = Integer.parseInt(exportValues[1]);
        String monthName = Month.of(month).name();
        List<EmployeeData> allEmployeeList = EmployeeDataLocalServiceUtil.findAllEmployees();
        List<Billing> allBillingData = BillingLocalServiceUtil.findMonthlyBilling(year, month);
        if (!allBillingData.isEmpty()) {
            try (Workbook workbook = BillingUtil.createFinanceWorkbook(allEmployeeList, allBillingData)) {
                HttpServletResponse httpServletResponse = PortalUtil.getHttpServletResponse(response);
                httpServletResponse.setContentType(EXCEL_HEADER);
                httpServletResponse.setHeader(CONTENT_DISPOSITION, "attachment; filename=Finance_Billing_Report_" + monthName + "-" + year + XLSX);
                workbook.write(httpServletResponse.getOutputStream());
                httpServletResponse.flushBuffer();
            }
        } else {
            SessionMessages.add(request, SUCCESS);
        }
        String portletName = td.getPortletDisplay().getPortletName();
        PortletURL portletURL = PortletURLFactoryUtil.create(PortalUtil.getHttpServletRequest(request), portletName, td.getLayout().getPlid(), PortletRequest.RENDER_PHASE);
        String redirectUrl = portletURL.toString().split("\\?")[0] + SELECTED_MONTH+reporting;
        request.setAttribute(WebKeys.REDIRECT, redirectUrl);
        response.sendRedirect(redirectUrl);
    }

    @ProcessAction(name = "downloadEmployeeHrReport")
    public void downloadEmployeeHrReport(ActionRequest request, ActionResponse response) throws IOException {
        ThemeDisplay td = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        LocalDate todaydate = LocalDate.now();
        int month = todaydate.getMonthValue();
        int year = todaydate.getYear();
        String monthName = Month.of(month).name();
        List<EmployeeData> allEmployeeList = EmployeeDataLocalServiceUtil.findAllEmployees();
        List<Billing> allBillingData = BillingLocalServiceUtil.findMonthlyBilling(year, month);
        if (!allBillingData.isEmpty()) {
            try (Workbook workbook = BillingUtil.createHrWorkbook(allEmployeeList, allBillingData)) {
                HttpServletResponse httpServletResponse = PortalUtil.getHttpServletResponse(response);
                httpServletResponse.setContentType(EXCEL_HEADER);
                httpServletResponse.setHeader(CONTENT_DISPOSITION, "attachment; filename=HR_Billing_Report_" + monthName + "-" + year + XLSX);
                workbook.write(httpServletResponse.getOutputStream());
                httpServletResponse.flushBuffer();
            }
            String portletName = td.getPortletDisplay().getPortletName();
            PortletURL portletURL = PortletURLFactoryUtil.create(PortalUtil.getHttpServletRequest(request), portletName, td.getLayout().getPlid(), PortletRequest.RENDER_PHASE);
            String reporting = String.format("%02d",month) + "/" +year;
            String redirectUrl = portletURL.toString().split("\\?")[0] + SELECTED_MONTH+reporting;
            request.setAttribute(WebKeys.REDIRECT, redirectUrl);
            response.sendRedirect(redirectUrl);

        } else {
            SessionMessages.add(request, SUCCESS);
        }
    }

    @ProcessAction(name = "downloadEmployeeManagerReport")
    public void downloadEmployeeManagerReport(ActionRequest request, ActionResponse response) throws IOException {
        ThemeDisplay td = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        User loggedUser = td.getUser();
        String reporting = ParamUtil.getString(request, "dataMonth");
        String[] exportValues = reporting.split("/");
        int month = Integer.parseInt(exportValues[0]);
        int year = Integer.parseInt(exportValues[1]);
        String monthName = Month.of(month).name();
        EmployeeData managerData = EmployeeDataLocalServiceUtil.findByEmail(loggedUser.getEmailAddress());
        List<EmployeeData> allEmployeeList = EmployeeDataLocalServiceUtil.findAllEmployees();
        List<Billing> allBillingData = BillingLocalServiceUtil.findMonthlyBilling(year, month).stream().filter(s -> s.getManagerEcode().equalsIgnoreCase(managerData.getEmpId())).collect(Collectors.toList());
        if (!allBillingData.isEmpty()) {
            try (Workbook workbook = BillingUtil.createManagerWorkbook(allEmployeeList, allBillingData)) {
                HttpServletResponse httpServletResponse = PortalUtil.getHttpServletResponse(response);
                httpServletResponse.setContentType(EXCEL_HEADER);
                httpServletResponse.setHeader(CONTENT_DISPOSITION, "attachment; filename=Manager_Billing_Report_" + monthName + "-" + year + XLSX);
                workbook.write(httpServletResponse.getOutputStream());
                httpServletResponse.flushBuffer();
            }
        } else {
            SessionMessages.add(request, SUCCESS);
        }
        String portletName = td.getPortletDisplay().getPortletName();
        PortletURL portletURL = PortletURLFactoryUtil.create(PortalUtil.getHttpServletRequest(request), portletName, td.getLayout().getPlid(), PortletRequest.RENDER_PHASE);
        String redirectUrl = portletURL.toString().split("\\?")[0] + SELECTED_MONTH+reporting;
        request.setAttribute(WebKeys.REDIRECT, redirectUrl);
        response.sendRedirect(redirectUrl);
    }

    @Override
    public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) {
        try {
            Object dataToWrite = null;
            if (resourceRequest.getResourceID().equals("getEmployeeDetails")) {
                String ecode = ParamUtil.getString(resourceRequest, "ecode");
                String empType = ParamUtil.getString(resourceRequest, "empType");
                EmployeeDataDto empDataDto = new EmployeeDataDto();
                EmployeeData employeeData = EmployeeDataLocalServiceUtil.fetchEmployeeData(ecode);
                User liferayUser = UserLocalServiceUtil.fetchUserByEmailAddress(20099,employeeData.getEmail());
                String[] managerRole = {"Manager"};
                String[] coordinatorRole = {"Coordinator"};
                String[] projectLeadRole = {"Project_Lead"};
                switch(empType){
                    case "m":
                        if(RoleLocalServiceUtil.hasUserRoles(liferayUser.getUserId(), liferayUser.getCompanyId(), managerRole, false) && employeeData.isStatus()) {
                            empDataDto.setEmployeeName(employeeData.getUserName());
                        }
                        break;
                    case "c":
                        if(RoleLocalServiceUtil.hasUserRoles(liferayUser.getUserId(), liferayUser.getCompanyId(), coordinatorRole, false) && employeeData.isStatus()) {
                            empDataDto.setEmployeeName(employeeData.getUserName());
                        }
                        break;
                    case "l":
                        if(RoleLocalServiceUtil.hasUserRoles(liferayUser.getUserId(), liferayUser.getCompanyId(), projectLeadRole, false) && employeeData.isStatus()) {
                            empDataDto.setEmployeeName(employeeData.getUserName());
                        }
                        break;
                    default:
                        if(employeeData.isStatus()) {
                            empDataDto.setEmployeeName(employeeData.getUserName());
                            empDataDto.setEmployeeDesignation(employeeData.getDesignation());
                            empDataDto.setDoj(formatter.format(employeeData.getDoj()));
                            empDataDto.setExperience(employeeData.getExperience());
                            empDataDto.setSkill(employeeData.getSkill());
                            empDataDto.setAccount(employeeData.getProject());
                            empDataDto.setProject(employeeData.getSubproject());
                        }
                }
                Gson gson = new Gson();
                dataToWrite = gson.toJson(empDataDto);

            } else if (resourceRequest.getResourceID().equals("downloadManual")) {
                int type = ParamUtil.getInteger(resourceRequest, "type");
                String filename;
                switch (type) {
                    case 1:
                        filename = MANAGER_USER_MANUAL;
                        break;
                    case 2:
                        filename = HR_USER_MANUAL;
                        break;
                    case 3:
                        filename = FINANCE_USER_MANUAL;
                        break;
                    default:
                        filename = LEADER_USER_MANUAL;
                }
                List<DLFileEntry> ls = DLFileEntryLocalServiceUtil.getDLFileEntries(-1, -1);
                long fileEntryId = 0L;
                String base64Encoded = "";
                for (DLFileEntry ts : ls) {
                    if (ts.getFileName().equalsIgnoreCase(filename)) {
                        fileEntryId = ts.getFileEntryId();
                    }
                }
                FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileEntryId);
                InputStream inputStream = DLFileEntryLocalServiceUtil.getFileAsStream(fileEntry.getPrimaryKey(), fileEntry.getVersion());
                byte[] bytesArray = new byte[(int) fileEntry.getSize()];
                inputStream.read(bytesArray);
                inputStream.close();
                byte[] encodeBase64 = Base64.getEncoder().encode(bytesArray);
                base64Encoded = new String(encodeBase64, StandardCharsets.UTF_8);
                dataToWrite = "\"" + base64Encoded + "\"";
            } else if (resourceRequest.getResourceID().equals("employeeSkill")) {
                String ecode = ParamUtil.getString(resourceRequest, "ecode");
                EmployeeDataDto empDataDto = new EmployeeDataDto();
                EmployeeData employeeData = EmployeeDataLocalServiceUtil.fetchEmployeeData(ecode);
                List<Skill> empSkills = SkillLocalServiceUtil.findSkillsByEmployee(ecode);
                List<Certificate> empCertificates = CertificateLocalServiceUtil.findEmployeeCertificates(ecode);
                empDataDto.setEmployeeCode(ecode);
                empDataDto.setEmployeeName(employeeData.getUserName());
                empDataDto.setAccount(employeeData.getProject());

                Map<Integer, String> statusMap = new HashMap<>();
                statusMap.put(0, "Submitted");
                statusMap.put(1, "Approved");
                statusMap.put(2, "Disapproved");

                Map<Integer, String> levelMap = new HashMap<>();
                levelMap.put(1, "Basic");
                levelMap.put(2, "Beginner");
                levelMap.put(3, "Intermediate");
                levelMap.put(4, "Advanced");
                levelMap.put(5, "Expert");
                List<SkillsetDto> employeeSkillsSet = new ArrayList<>();
                for (Skill skills : empSkills) {
                    SkillsetDto skillDto = new SkillsetDto();
                    skillDto.setCoreSkill(skills.getCoreSkill());
                    skillDto.setSubSkill(skills.getSubSkill());
                    if (skills.getPrimarySkill()) {
                        skillDto.setTypeStr("Primary");
                    } else {
                        skillDto.setTypeStr("Secondary");
                    }
                    skillDto.setTool(skills.getTool());
                    skillDto.setProficiencyLevelStr(levelMap.get(skills.getProficiencyLevel()));
                    employeeSkillsSet.add(skillDto);
                }
                List<CertificateDto> certificateList = new ArrayList<>();
                for (Certificate certificates : empCertificates) {
                    CertificateDto certificateDto = new CertificateDto();
                    certificateDto.setCertificateCategory(certificates.getCategory());
                    certificateDto.setCertificateName(certificates.getName());
                    certificateDto.setCertificateDesc(certificates.getDescription());
                    if (certificates.getExpiryDate() != null) {
                        certificateDto.setExpiryDateStr(formatter.format(certificates.getExpiryDate()));
                    }
                    certificateDto.setCertificateId(certificates.getCertificateId());
                    certificateDto.setAttachmentName(certificates.getFilename());
                    certificateList.add(certificateDto);
                }
                empDataDto.setSkills(employeeSkillsSet);
                empDataDto.setCertificates(certificateList);
                Gson gson = new Gson();
                dataToWrite = gson.toJson(empDataDto);
            }
            PrintWriter writer = resourceResponse.getWriter();
            writer.print("{\"data\": " + dataToWrite + "}");
            writer.flush();
            writer.close();
        } catch (Exception e) {
            _log.error(e.getClass() + "-->" + e.getMessage());
        }
    }
}