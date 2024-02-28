package com.trantorinc.synergy.performance.admin.web.service;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.trantorinc.synergy.common.service.drive.DriveService;
import com.trantorinc.synergy.common.service.dto.ExcelDto;
import com.trantorinc.synergy.common.service.excel.ExcelService;
import com.trantorinc.synergy.email.core.model.Email;
import com.trantorinc.synergy.email.core.service.EmailLocalServiceUtil;
import com.trantorinc.synergy.lms.core.model.Employee;
import com.trantorinc.synergy.lms.core.model.EmployeeModel;
import com.trantorinc.synergy.lms.core.service.EmployeeLocalServiceUtil;
import com.trantorinc.synergy.performance.admin.web.dto.*;
import com.trantorinc.synergy.performance.core.model.*;
import com.trantorinc.synergy.performance.core.service.*;

import javax.portlet.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static com.trantorinc.synergy.common.service.constant.AppConstantService.*;
import static com.trantorinc.synergy.common.service.constant.AppConstantService.APPROVED;
import static com.trantorinc.synergy.common.service.constant.AppConstantService.REJECTED;
import static com.trantorinc.synergy.common.service.util.UtilService.*;
import static com.trantorinc.synergy.common.service.util.UtilService.getCurrentYear;
import static com.trantorinc.synergy.performance.admin.web.constants.PerformanceAdminWebPortletKeys.*;
import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

public class PerformanceAdminServiceImpl implements PerformanceAdminService {
    private final Log log = LogFactoryUtil.getLog(PerformanceAdminServiceImpl.class);

    @Override
    public void getFormDetails(RenderRequest request) {
        List<Employee> allActiveEmployees = EmployeeLocalServiceUtil.findAllActiveEmployees();
        int currentYear = getCurrentYear();
        LocalDate today = getIstLocalDateTime().toLocalDate();
        LocalDate financialYearStart = getFinancialStartDate(currentYear);
        LocalDate previousFinancialYearStart = getFinancialStartDate(currentYear - 1);
        LocalDate financialYearEnd = getFinancialEndDate(currentYear);
        LocalDate financialMidYear = getMidFinancialEndDate(currentYear);
        Map<Integer, String> reviewStateMapAnnual = new HashMap<>();
        Map<Integer, String> reviewStateMapMidYear = new HashMap<>();
        Map<String, String> yearList = new HashMap<>();
        List<ReviewTimeline> reviewTimelines = ReviewTimelineLocalServiceUtil.getCalibratedTimelines();
        for (ReviewTimeline timeline : reviewTimelines) {
            if (timeline.isFinalYear()) {
                reviewStateMapAnnual.put(timeline.getReviewState(), timeline.getStateName());
            } else {
                reviewStateMapMidYear.put(timeline.getReviewState(), timeline.getStateName());
            }
        }

        List<Date> uniqueYear = ReviewLocalServiceUtil.findUniqueReviewYears();
        for (Date date : uniqueYear) {
            LocalDate localDate = convertDateToLocalDateTime(date).toLocalDate();
            if (!localDate.isAfter(previousFinancialYearStart)) {
                yearList.put(localDate.format(FORMATTER_YYYY_MM_DD), getDisplayYear(localDate));
            }
        }
        String displayReviewPeriod = displayCurrentAY(currentYear);
        request.setAttribute("yearList", yearList);
        request.setAttribute("displayReviewPeriod", displayReviewPeriod);
        request.setAttribute("reviewStateMapAnnual", reviewStateMapAnnual);
        request.setAttribute("reviewStateMapMidYear", reviewStateMapMidYear);

        List<EmployeeDto> finalEmployeeList = new ArrayList<>();
        for (Employee employee : allActiveEmployees) {
            List<Employee> managerData = allActiveEmployees.stream().filter(e -> e.getEcode().equalsIgnoreCase(employee.getManager())).collect(Collectors.toList());
            List<Employee> reviewerData = allActiveEmployees.stream().filter(e -> e.getEcode().equalsIgnoreCase(employee.getReviewer())).collect(Collectors.toList());
            String managerName = BLANK;
            String managerEmail = BLANK;
            String reviewerName = BLANK;
            String reviewerEmail = BLANK;
            if (!managerData.isEmpty()) {
                managerName = managerData.get(0).getName();
                managerEmail = managerData.get(0).getEmail();
            }
            if (!reviewerData.isEmpty()) {
                reviewerName = reviewerData.get(0).getName();
                reviewerEmail = reviewerData.get(0).getEmail();
            }
            EmployeeDto employeeDto = new EmployeeDto(employee.getEcode(), employee.getName(), employee.getAccount(), employee.getDesignation(), managerName, managerEmail, reviewerName, reviewerEmail, employee.isStatus());
            finalEmployeeList.add(employeeDto);
        }
        List<Review> currentYearReview = ReviewLocalServiceUtil.findReviewByReviewStartDate(convertLocalDateTimeToDate(financialYearStart.atStartOfDay()));
        boolean accountAnnual = today.isBefore(financialYearStart) || today.isAfter(financialMidYear);
        Set<String> userRoles = getLoggedUserRoles(request);
        request.setAttribute("loggedManagerEmail", getLoggedUser(request));
        if (userRoles.contains(ROLE_MANAGER) || userRoles.contains(ROLE_PERFORMANCE)) {
            List<ReviewDto> reviews = new ArrayList<>();

            Employee loggedUserDetails = allActiveEmployees.stream().filter(s -> s.getEmail().equalsIgnoreCase(getLoggedUser(request))).collect(Collectors.toList()).get(0);
            List<Employee> managerReporteeList = allActiveEmployees.stream().filter(s -> s.getManager().equalsIgnoreCase(loggedUserDetails.getEcode())).collect(Collectors.toList());
            Set<String> ecodeList = managerReporteeList.stream().map(Employee::getEcode).collect(Collectors.toSet());
            List<Kpi> kpiListAssignedToManager = KpiLocalServiceUtil.findKpiByManagerEmail(getLoggedUser(request));
            List<KpiDto> kpis = setKpis(kpiListAssignedToManager, finalEmployeeList, allActiveEmployees);
            /** for Manager Manage Reviews **/
            for (Review review : currentYearReview) {
                if (review.getManager().equalsIgnoreCase(getLoggedUser(request))) {
                    ReviewDto reviewDto = new ReviewDto();
                    reviewDto.setReviewId(review.getReviewId());
                    reviewDto.setEmployeeCode(review.getEcode());
                    List<Employee> managerData = allActiveEmployees.stream().filter(s -> s.getEmail().equalsIgnoreCase(review.getManager())).collect(Collectors.toList());
                    if (!managerData.isEmpty()) {
                        reviewDto.setManagerName(managerData.get(0).getName());
                    } else {
                        reviewDto.setManagerName(review.getManager());
                    }
                    EmployeeDto employeeInfo = getEmployee(review.getEcode(), finalEmployeeList);
                    reviewDto.setEmployeeName(employeeInfo.getEmployeeName());
                    reviewDto.setReviewStateName(review.isFinalYearReview() ? reviewStateMapAnnual.get(review.getReviewState()) : reviewStateMapMidYear.get(review.getReviewState()));
                    reviewDto.setManagerEmail(review.getManager());
                    List<Employee> reviewerData = allActiveEmployees.stream().filter(s -> s.getEmail().equalsIgnoreCase(review.getAssignee())).collect(Collectors.toList());
                    if (!reviewerData.isEmpty()) {
                        reviewDto.setReviewerName(reviewerData.get(0).getName());
                    } else {
                        reviewDto.setReviewerName(review.getAssignee());
                    }
                    if (review.isFinalYearReview()) {
                        reviewDto.setRating(RATING_FINAL_YEAR);
                    } else {
                        reviewDto.setRating(RATING_MID_YEAR);
                    }
                    if (review.isAccountPrimary()) {
                        reviewDto.setReviewType(PRIMARY_ACCOUNT);
                    } else {
                        reviewDto.setReviewType(SECONDARY_ACCOUNT);
                    }
                    reviewDto.setReviewState(review.getReviewState());
                    reviews.add(reviewDto);
                }
            }
            request.setAttribute("allCurrentYearPrimaryMReview", reviews);

            /** for manager Review Tab **/
            List<Review> currentYearTeamReviews = currentYearReview.stream().filter(a -> ecodeList.contains(a.getEcode()) || a.getManager().equalsIgnoreCase(getLoggedUser(request)) || (a.getAssignee().equalsIgnoreCase(getLoggedUser(request)) && !userRoles.contains(ROLE_TEAMLEAD) && a.getAssigned())).collect(Collectors.toList());
            Set<String> currentYearEcodes = currentYearTeamReviews.stream().map(Review::getEcode).collect(Collectors.toSet());
            Map<EmployeeDto, List<Review>> currentYearMap = new HashMap<>();
            for (String s : currentYearEcodes) {
                EmployeeDto mapKey = getEmployee(s, finalEmployeeList);
                List<Review> mapValue = currentYearTeamReviews.stream().filter(e -> e.getEcode().equalsIgnoreCase(s) && e.getReviewState() > 1).collect(Collectors.toList());
                List<Review> managerActionReviews = mapValue.stream().filter(a -> a.getReviewState() == 2).filter(a -> a.getManager().equalsIgnoreCase(getLoggedUser(request))).collect(Collectors.toList());
                if (!managerActionReviews.isEmpty()) {
                    mapKey.setOverview(ACTION_REQUIRED);
                } else {
                    mapKey.setOverview(NO_ACTION_REQUIRED);
                }
                if (!mapValue.isEmpty()) {
                    currentYearMap.put(mapKey, mapValue);
                }
            }
            List<Review> rollbackTeamReview = currentYearReview.stream().filter(s -> s.getManager().equalsIgnoreCase(getLoggedUser(request))).filter(s -> s.getFinalYearReview() == accountAnnual).filter(s -> (s.getReviewState() > 2 && s.getReviewState() < 6)).collect(Collectors.toList());
            List<ReviewRollback> rollbackEntries = ReviewRollbackLocalServiceUtil.findReviewRollbackByStatus(getLoggedUser(request));
            List<ReviewDto> allRollbackReviewForManagerToRequest = new ArrayList<>();
            for (Review review : rollbackTeamReview) {
                List<ReviewRollback> rollbackEntriesStatus = rollbackEntries.stream().filter(s -> s.getReviewId() == review.getReviewId() && s.getStatus() == 0).collect(Collectors.toList());
                if (rollbackEntriesStatus.isEmpty()) {
                    ReviewDto reviewDto = new ReviewDto();
                    reviewDto.setEmployeeCode(review.getEcode());
                    reviewDto.setEmployeeName(getEmployee(review.getEcode(), finalEmployeeList).getEmployeeName());
                    reviewDto.setAccount(review.getAccount());
                    reviewDto.setReviewId(review.getReviewId());
                    reviewDto.setReviewType(review.isFinalYearReview() ? ANNUAL : MID_YEAR);
                    reviewDto.setAccountType(review.isAccountPrimary() ? PRIMARY : SECONDARY);
                    reviewDto.setReviewStateName(review.isFinalYearReview() ? reviewStateMapAnnual.get(review.getReviewState()) : reviewStateMapMidYear.get(review.getReviewState()));
                    reviewDto.setReviewStartDate(getDisplayYear(convertDateToLocalDateTime(review.getReviewStartDate()).toLocalDate()));
                    allRollbackReviewForManagerToRequest.add(reviewDto);
                }
            }
            List<Review> closeTeamReviews = currentYearReview.stream().filter(s -> s.getManager().equalsIgnoreCase(getLoggedUser(request))).filter(s -> s.getFinalYearReview() == accountAnnual).filter(s -> s.getReviewState() < 6).collect(Collectors.toList());
            List<ReviewClose> closeEntries = ReviewCloseLocalServiceUtil.findReviewCloseByEmail(getLoggedUser(request));
            List<ReviewDto> allCloseReviewForManagerToRequest = new ArrayList<>();
            for (Review review : closeTeamReviews) {
                List<ReviewClose> closeEntriesStatus = closeEntries.stream().filter(s -> s.getReviewId() == review.getReviewId() && (s.getStatus() == 0 || s.getStatus() == 1)).collect(Collectors.toList());
                if (closeEntriesStatus.isEmpty()) {
                    ReviewDto reviewDto = new ReviewDto();
                    reviewDto.setEmployeeCode(review.getEcode());
                    reviewDto.setEmployeeName(getEmployee(review.getEcode(), finalEmployeeList).getEmployeeName());
                    reviewDto.setAccount(review.getAccount());
                    reviewDto.setReviewId(review.getReviewId());
                    reviewDto.setReviewType(review.isFinalYearReview() ? ANNUAL : MID_YEAR);
                    reviewDto.setAccountType(review.isAccountPrimary() ? PRIMARY : SECONDARY);
                    reviewDto.setReviewStateName(review.isFinalYearReview() ? reviewStateMapAnnual.get(review.getReviewState()) : reviewStateMapMidYear.get(review.getReviewState()));
                    reviewDto.setReviewStartDate(getDisplayYear(convertDateToLocalDateTime(review.getReviewStartDate()).toLocalDate()));
                    allCloseReviewForManagerToRequest.add(reviewDto);
                }
            }
            request.setAttribute("allCloseReviewForManagerToRequest", allCloseReviewForManagerToRequest);
            request.setAttribute("allRollbackReviewForManagerToRequest", allRollbackReviewForManagerToRequest);

            request.setAttribute("currentYearMap", currentYearMap);
            request.setAttribute("managerEmployeeKpiList", kpis);
            if (userRoles.contains(ROLE_PERFORMANCE)) {
                request.setAttribute("allRollbackReviewForAdminManagerToRequest", allRollbackReviewForManagerToRequest);
            }
        }
        if (userRoles.contains(ROLE_TEAMLEAD)) {
            List<Review> actionReviews = currentYearReview.stream().filter(Review::isAssigned).filter(a -> a.getReviewState() == 2).filter(a -> a.getAssignee().equalsIgnoreCase(getLoggedUser(request))).collect(Collectors.toList());
            List<ReviewDto> leadReviews = new ArrayList<>();
            for (Review review : actionReviews) {
                ReviewDto reviewDto = new ReviewDto();
                reviewDto.setEmployeeCode(review.getEcode());
                reviewDto.setEmployeeName(getEmployee(review.getEcode(), finalEmployeeList).getEmployeeName());
                reviewDto.setAccount(review.getAccount());
                reviewDto.setReviewId(review.getReviewId());
                reviewDto.setReviewType(review.isFinalYearReview() ? ANNUAL : MID_YEAR);
                reviewDto.setAccountType(review.isAccountPrimary() ? PRIMARY : SECONDARY);
                reviewDto.setAssigned(review.getReviewState() == 2);
                leadReviews.add(reviewDto);
            }
            List<Kpi> kpiListAssignedToLead = KpiLocalServiceUtil.findKpiByReviewerEmail(getLoggedUser(request));
            List<KpiDto> kpis = setKpis(kpiListAssignedToLead, finalEmployeeList, allActiveEmployees);
            request.setAttribute("leadEmployeeKpiList", kpis);
            request.setAttribute("actionReviews", leadReviews);
        }

        if (userRoles.contains(ROLE_HR)) {
            /** for HR Project List **/
            List<String> accountLists = EmployeeLocalServiceUtil.findUniqueAccounts();
            Set<String> availAccounts = accountLists.stream().map(e -> e.trim().toUpperCase()).collect(Collectors.toSet());
            availAccounts.remove(BLANK);
            TreeMap<String, Integer> accountMap = new TreeMap<>();
            int counter = 1;
            for (String s : availAccounts) {
                accountMap.put(s, counter++);
            }
            request.setAttribute("accountList", accountMap);
            Map<EmployeeDto, List<Review>> hrCurrentYearMap = new HashMap<>();
            List<Review> filteredReviews = currentYearReview.stream().filter(e -> e.getReviewState() > 2).collect(Collectors.toList());
            Set<String> ecodeList = finalEmployeeList.stream().map(EmployeeDto::getEmployeeCode).collect(Collectors.toSet());
            for (String s : ecodeList) {
                EmployeeDto mapKey = getEmployee(s, finalEmployeeList);
                List<Review> mapValue = filteredReviews.stream().filter(e -> e.getEcode().equalsIgnoreCase(s)).collect(Collectors.toList());
                List<Review> hrActionReviews = mapValue.stream().filter(a -> a.getReviewState() == 4).collect(Collectors.toList());
                if (!hrActionReviews.isEmpty()) {
                    mapKey.setOverview(ACTION_REQUIRED);
                } else {
                    mapKey.setOverview(NO_ACTION_REQUIRED);
                }
                if (!mapValue.isEmpty()) {
                    hrCurrentYearMap.put(mapKey, mapValue);
                }
            }
//            For hr side manager request for rollback
            List<ReviewRollback> allRollbackReviewRequestsByManager = ReviewRollbackLocalServiceUtil.getReviewRollbacks(-1, -1).stream().filter(s -> s.getStatus() == 0).collect(Collectors.toList());
            List<ReviewDto> allRollbackReviewForHr = new ArrayList<>();
            for (ReviewRollback rollbackRequest : allRollbackReviewRequestsByManager) {
                ReviewDto reviewDto = new ReviewDto();
                Review review = ReviewLocalServiceUtil.fetchReview(rollbackRequest.getReviewId());
                reviewDto.setEmployeeCode(review.getEcode());
                reviewDto.setEmployeeName(getEmployee(review.getEcode(), finalEmployeeList).getEmployeeName());
                reviewDto.setAccount(review.getAccount());
                reviewDto.setReviewId(review.getReviewId());
                reviewDto.setReviewType(review.isFinalYearReview() ? ANNUAL : MID_YEAR);
                reviewDto.setAccountType(review.isAccountPrimary() ? PRIMARY : SECONDARY);
                reviewDto.setReviewStateName(review.isFinalYearReview() ? reviewStateMapAnnual.get(review.getReviewState()) : reviewStateMapMidYear.get(review.getReviewState()));
                reviewDto.setReviewStartDate(getDisplayYear(convertDateToLocalDateTime(review.getReviewStartDate()).toLocalDate()));
                reviewDto.setRollbackId(rollbackRequest.getRollbackId());
                allRollbackReviewForHr.add(reviewDto);
            }
            List<ReviewClose> allCloseReviewRequestsByManager = ReviewCloseLocalServiceUtil.getReviewCloses(-1, -1);
            allCloseReviewRequestsByManager = allCloseReviewRequestsByManager.stream().filter(s -> s.getStatus() == 0).collect(Collectors.toList());
            List<ReviewDto> allCloseReviewForHr = new ArrayList<>();
            for (ReviewClose closeRequest : allCloseReviewRequestsByManager) {
                ReviewDto reviewDto = new ReviewDto();
                Review review = ReviewLocalServiceUtil.fetchReview(closeRequest.getReviewId());
                reviewDto.setEmployeeCode(review.getEcode());
                reviewDto.setEmployeeName(getEmployee(review.getEcode(), finalEmployeeList).getEmployeeName());
                reviewDto.setAccount(review.getAccount());
                reviewDto.setReviewId(review.getReviewId());
                reviewDto.setReviewType(review.isFinalYearReview() ? ANNUAL : MID_YEAR);
                reviewDto.setAccountType(review.isAccountPrimary() ? PRIMARY : SECONDARY);
                reviewDto.setReviewStateName(review.isFinalYearReview() ? reviewStateMapAnnual.get(review.getReviewState()) : reviewStateMapMidYear.get(review.getReviewState()));
                reviewDto.setReviewStartDate(getDisplayYear(convertDateToLocalDateTime(review.getReviewStartDate()).toLocalDate()));
                reviewDto.setCloseId(closeRequest.getCloseId());
                allCloseReviewForHr.add(reviewDto);
            }
            /** for HR Manage Review glance **/
            List<ReviewDto> reviewDtoList = new ArrayList<>();
            for (Review review : currentYearReview) {
                ReviewDto reviewDto = new ReviewDto();
                reviewDto.setEmployeeCode(review.getEcode());
                List<Employee> managerData = allActiveEmployees.stream().filter(s -> s.getEmail().equalsIgnoreCase(review.getManager())).collect(Collectors.toList());
                reviewDto.setManagerName(managerData.isEmpty() ? BLANK : managerData.get(0).getName());
                reviewDto.setEmployeeName(getEmployee(review.getEcode(), finalEmployeeList).getEmployeeName());
                reviewDto.setReviewStateName(review.isFinalYearReview() ? reviewStateMapAnnual.get(review.getReviewState()) : reviewStateMapMidYear.get(review.getReviewState()));
                reviewDto.setAccount(BLANK + accountMap.get(review.getAccount().toUpperCase()));
                if (review.isFinalYearReview()) {
                    reviewDto.setRating(RATING_FINAL_YEAR);
                } else {
                    reviewDto.setRating(RATING_MID_YEAR);
                }
                if (review.isAccountPrimary()) {
                    reviewDto.setReviewType(PRIMARY_ACCOUNT);
                } else {
                    reviewDto.setReviewType(SECONDARY_ACCOUNT);
                }
                reviewDtoList.add(reviewDto);
            }
            Map<String, Integer> finalReviewAnalyticsMap = new LinkedHashMap<>();
            Map<String, Integer> midReviewAnalyticsMap = new LinkedHashMap<>();
            Map<String, Integer> finalReviewSecondaryAnalyticsMap = new LinkedHashMap<>();
            Map<String, Integer> midReviewSecondaryAnalyticsMap = new LinkedHashMap<>();
            Map<Integer, String> finalDates = new LinkedHashMap<>();
            Map<Integer, String> midDates = new LinkedHashMap<>();
            for (int x = 0; x < 6; x++) {
                final int count = x;
                String stateName = reviewStateMapAnnual.get(x);
                List<Review> statePrimaryFinalAReviews = currentYearReview.stream().filter(Review::isFinalYearReview).filter(Review::isAccountPrimary).filter(a -> a.getReviewState() == count).collect(Collectors.toList());
                finalReviewAnalyticsMap.put(stateName, statePrimaryFinalAReviews.size());
                finalDates.put(count, convertDateToLocalDateTime(reviewTimelines.stream().filter(ReviewTimeline::isFinalYear).filter(t -> t.getReviewState() == count).collect(Collectors.toList()).get(0).getEndDate()).format(FORMATTER_YYYY_MM_DD));
                List<Review> stateSecondaryFinalReviews = currentYearReview.stream().filter(Review::isFinalYearReview).filter(a -> !a.isAccountPrimary()).filter(a -> a.getReviewState() == count).collect(Collectors.toList());
                finalReviewSecondaryAnalyticsMap.put(stateName, stateSecondaryFinalReviews.size());
                if ((x != 3) && (x != 4)) {
                    stateName = reviewStateMapMidYear.get(x);
                    List<Review> stateMidReviews = currentYearReview.stream().filter(a -> !a.isFinalYearReview()).filter(Review::isAccountPrimary).filter(a -> a.getReviewState() == count).collect(Collectors.toList());
                    midReviewAnalyticsMap.put(stateName, stateMidReviews.size());
                    midDates.put(count, convertDateToLocalDateTime(reviewTimelines.stream().filter(t -> !t.isFinalYear()).filter(t -> t.getReviewState() == count).collect(Collectors.toList()).get(0).getEndDate()).format(FORMATTER_YYYY_MM_DD));
                    List<Review> stateSecondaryMidReviews = currentYearReview.stream().filter(a -> !a.isFinalYearReview()).filter(a -> !a.isAccountPrimary()).filter(a -> a.getReviewState() == count).collect(Collectors.toList());
                    midReviewSecondaryAnalyticsMap.put(stateName, stateSecondaryMidReviews.size());
                }
            }
            request.setAttribute("financialYearStart", financialYearStart);
            request.setAttribute("financialYearEnd", financialYearEnd);
            request.setAttribute("financialMidYear", financialMidYear);
            request.setAttribute("financialNextMidDateYear", financialMidYear.plusDays(1));
            request.setAttribute("finalDates", convertToJson(finalDates));
            request.setAttribute("midDates", convertToJson(midDates));
            request.setAttribute("finalReviewAnalyticsMap", convertToJson(finalReviewAnalyticsMap));
            request.setAttribute("midReviewAnalyticsMap", convertToJson(midReviewAnalyticsMap));
            request.setAttribute("finalReviewSecondaryAnalyticsMap", convertToJson(finalReviewSecondaryAnalyticsMap));
            request.setAttribute("midReviewSecondaryAnalyticsMap", convertToJson(midReviewSecondaryAnalyticsMap));
            request.setAttribute("allCurrentYearPrimaryHReview", reviewDtoList);
            request.setAttribute("allCloseReviewForHr", allCloseReviewForHr);
            request.setAttribute("allRollbackReviewForHr", allRollbackReviewForHr);
            request.setAttribute("hrCurrentYearMap", hrCurrentYearMap);
            List<Kpi> allEmployeeKpiList = KpiLocalServiceUtil.getKpis(-1, -1);
            List<KpiDto> kpis = setKpis(allEmployeeKpiList, finalEmployeeList, allActiveEmployees);
            request.setAttribute("hrEmployeeKpiList", kpis);
        }


    }

    private String getDisplayYear(LocalDate date) {
        int nextYear = date.getYear() + 1;
        return "AY " + date.getYear() + " - " + nextYear;
    }

    private List<KpiDto> setKpis(List<Kpi> kpiListAssigned, List<EmployeeDto> finalEmployeeList, List<Employee> activeEmployees) {

        List<KpiDto> kpis = new ArrayList<>();
        if (!kpiListAssigned.isEmpty()) {
            for (Kpi kpi : kpiListAssigned) {
                List<EmployeeDto> empInfo = finalEmployeeList.stream().filter(e -> e.getEmployeeCode().equalsIgnoreCase(kpi.getEcode())).collect(Collectors.toList());
                if (!empInfo.isEmpty()) {
                    List<Employee> managerData = activeEmployees.stream().filter(s -> s.getEmail().equalsIgnoreCase(kpi.getManagerEmail())).collect(Collectors.toList());
                    List<Employee> reviewerData = activeEmployees.stream().filter(s -> s.getEmail().equalsIgnoreCase(kpi.getReviewerEmail())).collect(Collectors.toList());
                    KpiDto dto = new KpiDto();
                    dto.setKpiId(kpi.getKpiId());
                    dto.setEcode(kpi.getEcode());
                    dto.setEmployeeName(empInfo.get(0).getEmployeeName());
                    dto.setAccountName(kpi.getAccount());
                    dto.setAccountPrimary(kpi.getAccountPrimary());
                    dto.setManagerName(managerData.isEmpty() ? BLANK : managerData.get(0).getName());
                    dto.setReviewerName(reviewerData.isEmpty() ? BLANK : reviewerData.get(0).getName());
                    dto.setUpdateDate(convertDateToLocalDateTime(kpi.getUpdateDate()).format(FORMATTER_YYYY_MM_DD));
                    dto.setKpiSettingStatus(kpi.getKpiSettingStatus());
                    kpis.add(dto);
                }
            }
        }
        return kpis;
    }

    private List<KpiGuide> getAttributes(List<KpiGuide> guides) {
        return guides.stream().filter(KpiGuide::isAttribute).collect(Collectors.toList());
    }

    @Override
    public void getKpiDetails(ActionRequest request, ActionResponse response) {
        String role = ParamUtil.getString(request, "role");
        long kpiId = ParamUtil.getLong(request, KPI_ID);
        List<KpiGuide> guides = KpiGuideLocalServiceUtil.getKpiGuides(-1, -1);
        List<KpiGuide> attributes = getAttributes(guides);
        int countAttribute = 1;
        StringBuilder attributeText = new StringBuilder();

        for (KpiGuide attribute : attributes) {
            attributeText.append(countAttribute++);
            attributeText.append(BRACES);
            attributeText.append(attribute.getTitle());
            attributeText.append(System.lineSeparator());
        }
        Map<Integer, KpiGuide> guideMap = new HashMap<>();
        for (KpiGuide guide : guides) {
            guideMap.put((int) guide.getGuideId(), guide);
        }
        Kpi kpi = KpiLocalServiceUtil.fetchKpi(kpiId);
        Employee employeeData = EmployeeLocalServiceUtil.fetchEmployee(kpi.getEcode());
        List<KpiLine> kpiLines = KpiLineLocalServiceUtil.findKpiLineByKpiId(kpiId);
        ReviewDto reviewDto = new ReviewDto();
        List<ReviewLineDto> reviewLineDtos = new ArrayList<>();
        for (KpiLine line : kpiLines) {
            ReviewLineDto reviewLineDto = new ReviewLineDto();
            reviewLineDto.setReviewLineId(line.getLineId());
            reviewLineDto.setGuideId(line.getGuideId());
            KpiGuide guide = guideMap.get((int) line.getGuideId());
            if (guide.isOther()) {
                reviewLineDto.setReviewLineTitle(line.getTitle());
                reviewLineDto.setReviewLineDescription(line.getDescription());
                reviewLineDto.setReviewLineTarget(line.getTarget());
            } else {
                reviewLineDto.setReviewLineTitle(guide.getTitle());
                if (guide.isAttribute()) {
                    reviewLineDto.setReviewLineDescription(BLANK);
                    reviewLineDto.setReviewLineTarget(BLANK);
                } else {
                    reviewLineDto.setReviewLineDescription(guide.getDescription());
                    reviewLineDto.setReviewLineTarget(line.getTarget());
                }
            }
            reviewLineDto.setAttribute(guide.isAttribute());
            reviewLineDto.setOther(guide.isOther());
            reviewLineDto.setMandatory(guide.getMandatory());
            reviewLineDtos.add(reviewLineDto);
        }
        reviewDto.setEmployeeName(employeeData.getName());
        reviewDto.setKpiId(kpi.getKpiId());
        reviewDto.setEmployeeCode(employeeData.getEcode());
        reviewDto.setEmployeeBand(employeeData.getBand());
        reviewDto.setEmployeeDesignation(employeeData.getDesignation());
        reviewDto.setAccount(kpi.getAccount());
        reviewDto.setAttributeText(attributeText.toString());
        reviewDto.setLocation(employeeData.getLocation());
        reviewDto.setCurrentAccount(kpi.getAccountPrimary());
        if (kpi.isAccountPrimary()) {
            Employee managerData = EmployeeLocalServiceUtil.fetchEmployee(employeeData.getManager());
            reviewDto.setManagerName(managerData.getName());
            reviewDto.setManagerEmail(managerData.getEmail());
            Employee reviewerData = EmployeeLocalServiceUtil.fetchEmployee(employeeData.getReviewer());
            reviewDto.setReviewerName(reviewerData.getName());
            reviewDto.setReviewerEmail(reviewerData.getEmail());
        } else {
            Employee managerData = EmployeeLocalServiceUtil.findByEmail(kpi.getManagerEmail());
            reviewDto.setManagerName(managerData.getName());
            reviewDto.setManagerEmail(kpi.getManagerEmail());
            Employee reviewerData = EmployeeLocalServiceUtil.findByEmail(kpi.getReviewerEmail());
            reviewDto.setReviewerName(reviewerData.getName());
            reviewDto.setReviewerEmail(kpi.getReviewerEmail());
        }
        reviewDto.setEditMode(role.equalsIgnoreCase(ROLE_TEAMLEAD) || role.equalsIgnoreCase(ROLE_MANAGER));
        if (role.equalsIgnoreCase(ROLE_HR)) {
            reviewDto.setManagerViewStatus(false);
            reviewDto.setLeadViewStatus(false);
        } else if (role.equalsIgnoreCase(ROLE_MANAGER)) {
            reviewDto.setManagerViewStatus(true);
            reviewDto.setLeadViewStatus(false);
        } else {
            reviewDto.setManagerViewStatus(true);
            reviewDto.setLeadViewStatus(true);
        }
        reviewDto.setReviewLines(reviewLineDtos);
        request.setAttribute("reviewDetails", reviewDto);
        response.getRenderParameters().setValue("mvcPath", "/jsp/kpi.jsp");
    }

    @Override
    public void reviewFormWorkflow(ActionRequest request, ActionResponse response) {
        String role = ParamUtil.getString(request, "role");
        long reviewId = ParamUtil.getLong(request, REVIEW_ID);
        Review review = ReviewLocalServiceUtil.fetchReview(reviewId);
        List<ReviewTimeline> timelines = ReviewTimelineLocalServiceUtil.getCalibratedTimelines();
        List<Employee> allActiveEmployees = EmployeeLocalServiceUtil.findAllActiveEmployees();
        List<KpiGuide> guides = KpiGuideLocalServiceUtil.getKpiGuides(-1, -1);
        List<KpiGuide> attributes = getAttributes(guides);
        int countAttribute = 1;
        StringBuilder attributeText = new StringBuilder();

        for (KpiGuide attribute : attributes) {
            attributeText.append(countAttribute++);
            attributeText.append(BRACES);
            attributeText.append(attribute.getTitle());
            attributeText.append(System.lineSeparator());
        }
        Map<Integer, KpiGuide> guideMap = new HashMap<>();
        for (KpiGuide guide : guides) {
            guideMap.put((int) guide.getGuideId(), guide);
        }
        Map<String, String> ratingMap = new HashMap<>();
        ratingMap.put("A", CONSISTENTLY_EXCEEDS_EXPECTATIONS);
        ratingMap.put("B", SOMETIMES_EXCEEDS_EXPECTATIONS);
        ratingMap.put("C", CONSISTENTLY_MEETS_EXPECTATIONS);
        ratingMap.put("D", INCONSISTENTLY_MEETS_EXPECTATIONS);
        ratingMap.put("E", DOES_NOT_MEETS_EXPECTATIONS);
        Map<Integer, String> reviewStateMap = new HashMap<>();
        if (review.isFinalYearReview()) {
            timelines = timelines.stream().filter(ReviewTimeline::isFinalYear).collect(Collectors.toList());
        } else {
            timelines = timelines.stream().filter(s -> !s.isFinalYear()).collect(Collectors.toList());
        }

        for (ReviewTimeline timeline : timelines) {
            if (timeline.getReviewState() != 0) {
                reviewStateMap.put(timeline.getReviewState(), timeline.getStateName());
            }
        }
        Employee employeeData = EmployeeLocalServiceUtil.fetchEmployee(review.getEcode());
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setProgressMap(getProgressMap(review));
        reviewDto.setAttributeText(attributeText.toString());
        reviewDto.setReviewId(review.getReviewId());
        reviewDto.setAccount(review.getAccount());
        reviewDto.setManagerEmail(review.getManager());

        List<Employee> manager = allActiveEmployees.stream().filter(s -> s.getEmail().equalsIgnoreCase(review.getManager())).collect(Collectors.toList());
        if (!manager.isEmpty())
            reviewDto.setManagerName(manager.get(0).getName());
        else
            reviewDto.setManagerName(review.getManager());
        reviewDto.setAssigned(review.isAssigned());
        reviewDto.setAssigneeEmail(review.getAssignee());
        List<Employee> reviewer = allActiveEmployees.stream().filter(s -> s.getEmail().equalsIgnoreCase(review.getAssignee())).collect(Collectors.toList());
        if (!reviewer.isEmpty())
            reviewDto.setAssigneeName(reviewer.get(0).getName());
        else
            reviewDto.setAssigneeName(review.getAssignee());
        reviewDto.setEmployeeCode(review.getEcode());
        reviewDto.setReviewState(review.getReviewState());
        reviewDto.setReviewStateName(reviewStateMap.get(review.getReviewState()));
        String reviewStartDate = convertDateToLocalDateTime(review.getReviewStartDate()).format(FORMATTER_YYYY_MM_DD);
        reviewDto.setReviewStartDate(reviewStartDate);
        String fyStartYear = reviewStartDate.split("-")[0];
        if (review.isFinalYearReview()) {
            int funEndYear = Integer.parseInt(fyStartYear) + 1;
            reviewDto.setReviewEndDate(getFinancialEndDate(funEndYear).format(FORMATTER_YYYY_MM_DD));
            reviewDto.setRating(review.getRating());
        } else {
            reviewDto.setReviewEndDate(getMidFinancialEndDate(Integer.parseInt(fyStartYear)).format(FORMATTER_YYYY_MM_DD));
            reviewDto.setRating(getEmployeeRatingText(review.getRating()));
        }
        reviewDto.setEmployeeName(employeeData.getName());
        reviewDto.setEmployeeEmail(employeeData.getEmail());
        reviewDto.setEmployeeBand(employeeData.getBand());
        reviewDto.setEmployeeDesignation(employeeData.getDesignation());
        reviewDto.setCurrentAccount(review.getAccountPrimary());
        reviewDto.setCurrentYRReview(review.getFinalYearReview());
        if (role.equalsIgnoreCase(ROLE_HR) || role.equalsIgnoreCase(ROLE_PERFORMANCE)) {
            reviewDto.setManagerViewStatus(false);
            reviewDto.setLeadViewStatus(false);
            reviewDto.setAdminRole(role.equalsIgnoreCase(ROLE_PERFORMANCE));
        } else if (role.equalsIgnoreCase(ROLE_MANAGER)) {
            reviewDto.setManagerViewStatus(true);
            reviewDto.setLeadViewStatus(false);
        } else {
            reviewDto.setManagerViewStatus(true);
            reviewDto.setLeadViewStatus(true);
        }
        List<ReviewComment> auditComments = new ArrayList<>(ReviewCommentLocalServiceUtil.findReviewCommentByReviewId(reviewId));
        StringBuilder rejectionComment = new StringBuilder();
        if (!auditComments.isEmpty()) {
            auditComments.sort((ReviewComment ac1, ReviewComment ac2) -> ac2.getCreatedDate().compareTo(ac1.getCreatedDate()));
            rejectionComment.append("====== History ======");
            for (ReviewComment auditComment : auditComments) {
                rejectionComment.append("\n\n");
                rejectionComment.append(convertDateToLocalDateTime(auditComment.getCreatedDate()).format(FORMATTER_YYYY_MM_DD));
                rejectionComment.append(" - ");
                rejectionComment.append(auditComment.getDescription());
            }
        }
        reviewDto.setComment(rejectionComment.toString());
        reviewDto.setRaiseToHrComment(review.getComment());
        reviewDto.setImprovementComment(review.getImprovementComment());
        reviewDto.setTrainingsReqd(review.getTrainings());
        reviewDto.setAchievementsByEmp(review.getAchievements());
        reviewDto.setOverallEmployeeComment(review.getEmployeeComment());
        reviewDto.setOverallManagerComment(review.getManagerComment());
        reviewDto.setOverallHrComment(review.getHrComment());
        reviewDto.setCriticalToCompany(review.getCriticalToCompany());
        reviewDto.setCriticalToAcct(review.getCriticalToAccount());

        reviewDto.setRsnForPromotion(review.getReasonRecommendation());
        reviewDto.setRolePlayed(review.getRolePlayed());
        reviewDto.setExcelledArea(review.getExcelledArea());
        reviewDto.setExceptedDesignation(review.getExpectedDesignation());
        reviewDto.setExceptedSalary(review.getExpectedSalary());
        reviewDto.setReadyAns(review.getReadyReason());
        reviewDto.setPromotionReqd(review.getPromotionRecommendation());
        if (review.isFinalYearReview() && review.isAccountPrimary()) {
            reviewDto.setReplacement(review.getReplacement());
            reviewDto.setReplacementDetail(review.getReplacementDetail());
            reviewDto.setReplacementReason(review.getReplacementReason());
            reviewDto.setReplacementReasonOther(review.getReplacementReasonOther());
            Map<String, String> unsortedTeamMap = allActiveEmployees.stream().filter(EmployeeModel::getStatus).filter(e -> e.getAccount().trim().equalsIgnoreCase(review.getAccount().trim())).collect(toMap(Employee::getEcode, Employee::getName));
            reviewDto.setTeamMembers(unsortedTeamMap.entrySet().stream().sorted(comparingByValue()).collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new)));
        }
        List<OtherAccountDto> otherAccountDtos = new ArrayList<>();
        boolean secondaryAssessed = true;
        if (review.isAccountPrimary()) {
            List<Review> reviews = ReviewLocalServiceUtil.findReviewByEcode(review.getEcode()).stream().filter(a -> a.isFinalYearReview() == review.isFinalYearReview()).filter(a -> !a.isAccountPrimary()).filter(a -> a.getReviewStartDate().compareTo(review.getReviewStartDate()) == 0).collect(Collectors.toList());
            for (Review review1 : reviews) {
                if (review1.getReviewState() != 6) {
                    OtherAccountDto otherProjectDto = new OtherAccountDto();
                    otherProjectDto.setAccount(review1.getAccount());
                    otherProjectDto.setManagerEmail(review1.getManager());
                    if (!review1.getRating().trim().equalsIgnoreCase(BLANK) && Double.parseDouble(review1.getRating()) != 0 && review1.getFirstSubmit()) {
                        if (!reviewDto.isManagerViewStatus()) {
                            otherProjectDto.setRating(review1.getRating());
                        } else {
                            otherProjectDto.setRating(getEmployeeRatingText(review1.getRating()));
                        }
                    } else {
                        otherProjectDto.setRating(BLANK);
                        secondaryAssessed = false;
                    }
                    otherProjectDto.setManagerName(allActiveEmployees.stream().filter(s -> s.getEmail().equalsIgnoreCase(review1.getManager())).collect(Collectors.toList()).get(0).getName());
                    otherAccountDtos.add(otherProjectDto);
                }
            }
        }
        reviewDto.setSecondaryAssessed(secondaryAssessed);
        reviewDto.setOtherAccounts(otherAccountDtos);
        List<ReviewLine> reviewLines = ReviewLineLocalServiceUtil.findReviewLineByReviewId(reviewId);
        List<ReviewUpload> uploadedAttachements = ReviewUploadLocalServiceUtil.findReviewUploadByReviewId(reviewId);
        List<ReviewUploadDto> attachementsList = new ArrayList<>();
        for (ReviewUpload items : uploadedAttachements) {
            ReviewUploadDto uploadDto = new ReviewUploadDto();
            uploadDto.setFileName(items.getAttachmentName());
            uploadDto.setFileId(items.getFileId());
            uploadDto.setUploadId(items.getUploadId());
            attachementsList.add(uploadDto);
        }
        reviewDto.setUploadsFiles(attachementsList);
        List<ReviewLineDto> reviewLineLists = new ArrayList<>();
        for (ReviewLine line : reviewLines) {
            ReviewLineDto reviewLineDto = new ReviewLineDto();
            reviewLineDto.setReviewLineId(line.getLineId());
            reviewLineDto.setGuideId(line.getGuideId());
            KpiGuide guide = guideMap.get((int) line.getGuideId());
            if (guide.isOther()) {
                reviewLineDto.setReviewLineTitle(line.getTitle());
                reviewLineDto.setReviewLineDescription(line.getDescription());
                reviewLineDto.setReviewLineTarget(line.getTarget());
            } else {
                reviewLineDto.setReviewLineTitle(guide.getTitle());
                if (guide.isAttribute()) {
                    reviewLineDto.setReviewLineDescription(BLANK);
                    reviewLineDto.setReviewLineTarget(BLANK);
                } else {
                    reviewLineDto.setReviewLineDescription(guide.getDescription());
                    reviewLineDto.setReviewLineTarget(line.getTarget());
                }
            }
            reviewLineDto.setEmployeeRating(ratingMap.get(line.getEmployeeRating()));
            reviewLineDto.setEmployeeComment(line.getEmployeeComment());
            reviewLineDto.setManagerRating(ratingMap.get(line.getManagerRating()));
            reviewLineDto.setManagerComment(line.getManagerComment());
            reviewLineDto.setHrRating(ratingMap.get(line.getHrRating()));
            reviewLineDto.setHrComment(line.getHrComment());
            reviewLineDto.setAttribute(guide.isAttribute());
            reviewLineDto.setOther(guide.isOther());
            reviewLineDto.setMandatory(guide.getMandatory());
            reviewLineLists.add(reviewLineDto);
        }
        reviewDto.setReviewLines(reviewLineLists);
        request.setAttribute("reviewDetails", reviewDto);
        request.setAttribute("stateMapping", reviewStateMap);
        request.setAttribute("progressMap", getProgressMap(review));
        response.getRenderParameters().setValue("mvcPath", "/jsp/review.jsp");

    }

    @Override
    public String downloadAttachments(ResourceRequest resourceRequest) {
        String uploadId = ParamUtil.getString(resourceRequest, "uploadId");
        ReviewUpload upload = ReviewUploadLocalServiceUtil.fetchReviewUpload(Long.parseLong(uploadId));
        String fileExtension = upload.getAttachmentName().substring(upload.getAttachmentName().lastIndexOf('.') + 1);
        File file = DriveService.getFile(upload.getFileId(), upload.getFileId(), fileExtension);
        return "\"" + getBase64File(file) + "\"";
    }

    @Override
    public void rejectReviewLines(ActionRequest request) {
        long reviewId = ParamUtil.getLong(request, REVIEW_ID);
        String rsnRejectObj = ParamUtil.getString(request, "rejectObjRsn");
        ReviewComment reviewComment = ReviewCommentLocalServiceUtil.createReviewComment(CounterLocalServiceUtil.increment());
        reviewComment.setReviewId(reviewId);
        reviewComment.setCreatedDate(getIstDate());
        reviewComment.setDescription(rsnRejectObj);
        reviewComment.setAuthor(getLoggedUser(request));
        ReviewCommentLocalServiceUtil.addReviewComment(reviewComment);
        Review review = ReviewLocalServiceUtil.fetchReview(reviewId);
        review.setReviewState(1);
        review.setStage1Date(null);
        review.setStage2Date(null);
        review.setStage3Date(null);
        ReviewLocalServiceUtil.updateReview(review);
        String reviewType = review.isFinalYearReview() ? ANNUAL : MID_YEAR;
        Employee empData = EmployeeLocalServiceUtil.fetchEmployee(review.getEcode());
        String managerEmail = review.getManager().equalsIgnoreCase(review.getAssignee()) ? review.getManager() : review.getManager() + COMMA + review.getAssignee();
        Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
        email.setBody(MessageFormat.format(BODY_REVIEW_LINE_REJECT, review.getAccount(), getPortalUrl() + URL_PERFORMANCE));
        email.setSubject(MessageFormat.format(SUBJECT_REVIEW_LINE_REJECT, reviewType));
        email.setToAddress(empData.getEmail());
        email.setCcAddress(managerEmail);
        email.setModule(MODULE_PERFORMANCE);
        email.setCreatedDate(getIstDate());
        email.setScheduled(false);
        email.setSent(false);
        EmailLocalServiceUtil.addEmail(email);
    }

    @Override
    public void submitManagerReview(ActionRequest request) {
        long reviewId = ParamUtil.getLong(request, REVIEW_ID);
        Review review = ReviewLocalServiceUtil.fetchReview(reviewId);
        String managerOverallComments = ParamUtil.getString(request, "managerOverallComments");
        String improvementComment = ParamUtil.getString(request, "improvementComment");
        List<ReviewLine> lineByReviewId = ReviewLineLocalServiceUtil.findReviewLineByReviewId(reviewId);
        if (review.getFinalYearReview()) {
            String managerFinalRating = ParamUtil.getString(request, "managerFinalRating");
            String trainingReqd = ParamUtil.getString(request, "trainingReqd");
            String promotionReqd = ParamUtil.getString(request, "promotionReqd");
            String reasonRecommendAns = ParamUtil.getString(request, "reasonRecommendAns");
            String rolePlayedAns = ParamUtil.getString(request, "rolePlayedAns");
            String excelledAreaAns = ParamUtil.getString(request, "excelledAreaAns");
            String exceptedDesignation = ParamUtil.getString(request, "exceptedDesignation");
            String exceptedSalary = ParamUtil.getString(request, "exceptedSalary");
            String readyAns = ParamUtil.getString(request, "readyAns");
            String criticalToAcct = ParamUtil.getString(request, "criticalToAcct");
            String criticalToCompany = ParamUtil.getString(request, "criticalToCompany");
            String replacement = ParamUtil.getString(request, "replacement");
            String replacementReason = ParamUtil.getString(request, "replacementReason");
            String replacementOtherReason = ParamUtil.getString(request, "replacementOtherReason");
            String replacementDetail = null;
            if (replacement.equalsIgnoreCase("YES")) {
                replacementDetail = ParamUtil.getString(request, "replacementEcode");
                review.setReplacementReason(replacementReason.equalsIgnoreCase("Other") ? replacementOtherReason : replacementReason);
                review.setReplacementReasonOther(replacementReason.equalsIgnoreCase("Other"));

            } else if (replacement.equalsIgnoreCase("NO")) {
                replacementDetail = ParamUtil.getString(request, "replacementDetail");
            }
            for (int x = 0; x < lineByReviewId.size(); x++) {
                long reviewLineId = ParamUtil.getLong(request, REVIEW_LINE_ID + x);
                ReviewLine managerReview = ReviewLineLocalServiceUtil.fetchReviewLine(reviewLineId);
                String managerRating = ParamUtil.getString(request, "managerRating" + x);
                String managerComments = ParamUtil.getString(request, "managerComment" + x);
                managerReview.setManagerRating(managerRating);
                managerReview.setManagerComment(managerComments);
                managerReview.setHrRating(managerRating);
                managerReview.setHrComment(managerComments);
                ReviewLineLocalServiceUtil.updateReviewLine(managerReview);
            }
            review.setPromotionRecommendation(promotionReqd.equalsIgnoreCase("Promotion is Required"));
            review.setExpectedDesignation(exceptedDesignation);
            review.setExpectedSalary(exceptedSalary);
            review.setReadyReason(readyAns);
            review.setCriticalToAccount(getRadioValue(criticalToAcct));
            review.setCriticalToCompany(getRadioValue(criticalToCompany));
            review.setReplacement(getRadioValue(replacement));
            review.setReplacementDetail(replacementDetail);
            review.setReasonRecommendation(reasonRecommendAns);
            review.setRolePlayed(rolePlayedAns);
            review.setExcelledArea(excelledAreaAns);
            review.setManagerComment(managerOverallComments);
            review.setRating(managerFinalRating);
            review.setTrainings(trainingReqd);
            review.setImprovementComment(improvementComment);
            if (!review.isAccountPrimary()) {
                review.setReviewState(5);
            } else {
                review.setReviewState(3);
            }
            review.setStage2Date(getIstDate());
        } else {
            String managerFinalRating = ParamUtil.getString(request, "managerFinalRating");
            for (ReviewLine obj : lineByReviewId) {
                ReviewLine managerReview = ReviewLineLocalServiceUtil.fetchReviewLine(obj.getLineId());
                managerReview.setManagerRating(managerFinalRating);
                managerReview.setHrRating(managerFinalRating);
                ReviewLineLocalServiceUtil.updateReviewLine(managerReview);
            }
            String apraiseeAchievements = ParamUtil.getString(request, "apraiseeAchievements");
            review.setRating(getEmployeeRatingNumerical(managerFinalRating));
            review.setReviewState(5);
            review.setManagerComment(managerOverallComments);
            review.setAchievements(apraiseeAchievements);
            review.setImprovementComment(improvementComment);
            review.setStage2Date(getIstDate());
        }
        review.setFirstSubmit(true);
        ReviewLocalServiceUtil.updateReview(review);
        String reviewType = review.isFinalYearReview() ? ANNUAL : MID_YEAR;
        String managerEmail = review.getManager().equalsIgnoreCase(review.getAssignee()) ? review.getManager() : review.getManager() + COMMA + review.getAssignee();
        Employee empData = EmployeeLocalServiceUtil.fetchEmployee(review.getEcode());
        Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
        email.setSubject(MessageFormat.format(SUBJECT_MANAGER_REVIEW, reviewType));
        if (review.isFinalYearReview()) {
            email.setBody(MessageFormat.format(BODY_MANAGER_REVIEW, getPortalUrl() + URL_PERFORMANCE));
        } else {
            email.setBody(MessageFormat.format(BODY_MIDYEAR_MANAGER_REVIEW, getPortalUrl() + URL_PERFORMANCE));
        }
        email.setToAddress(empData.getEmail());
        email.setCcAddress(managerEmail);
        email.setModule(MODULE_PERFORMANCE);
        email.setCreatedDate(getIstDate());
        email.setScheduled(false);
        email.setSent(false);
        EmailLocalServiceUtil.addEmail(email);
    }

    @Override
    public void submitHrReview(ActionRequest request) {
        long reviewId = ParamUtil.getLong(request, REVIEW_ID);
        List<ReviewLine> reviewLines = ReviewLineLocalServiceUtil.findReviewLineByReviewId(reviewId);
        for (int x = 0; x < reviewLines.size(); x++) {
            long reviewLineId = ParamUtil.getLong(request, REVIEW_LINE_ID + x);
            ReviewLine hrReview = ReviewLineLocalServiceUtil.fetchReviewLine(reviewLineId);
            String hrRating = ParamUtil.getString(request, "hrRating" + x);
            String hrComments = ParamUtil.getString(request, "hrComment" + x);
            hrReview.setHrRating(hrRating);
            hrReview.setHrComment(hrComments);
            ReviewLineLocalServiceUtil.updateReviewLine(hrReview);
        }
        String hrFinalRating = ParamUtil.getString(request, "hrFinalRating");
        String hrFinalCmmnts = ParamUtil.getString(request, "hrOverallComments");
        Review review = ReviewLocalServiceUtil.fetchReview(reviewId);
        review.setReviewState(5);
        review.setHrComment(hrFinalCmmnts);
        review.setRating(hrFinalRating);
        review.setStage4Date(getIstDate());
        ReviewLocalServiceUtil.updateReview(review);
        Employee employee = EmployeeLocalServiceUtil.fetchEmployee(review.getEcode());
        String reviewTye = review.isFinalYearReview() ? ANNUAL : MID_YEAR;
        String managerEmail = review.getManager().equalsIgnoreCase(review.getAssignee()) ? review.getManager() : review.getManager() + COMMA + review.getAssignee();
        Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
        email.setSubject(MessageFormat.format(SUBJECT_HR_REVIEW, reviewTye));
        email.setBody(MessageFormat.format(BODY_HR_REVIEW, getPortalUrl() + URL_PERFORMANCE));
        email.setToAddress(employee.getEmail());
        email.setCcAddress(managerEmail + COMMA + DL_HR);
        email.setModule(MODULE_PERFORMANCE);
        email.setCreatedDate(getIstDate());
        email.setScheduled(false);
        email.setSent(false);
        EmailLocalServiceUtil.addEmail(email);
    }

    @Override
    public void allowSelfReviewByHr(ActionRequest request) {
        LocalDate today = getIstLocalDateTime().toLocalDate();
        long kpiId = ParamUtil.getLong(request, KPI_ID);
        Kpi kpiInfo = KpiLocalServiceUtil.fetchKpi(kpiId);
        int currentYear = getCurrentYear();
        LocalDate financialYearStart = getFinancialStartDate(currentYear);
        LocalDate financialMidYear = getMidFinancialEndDate(currentYear);
        boolean accountAnnual = today.isBefore(financialYearStart) || today.isAfter(financialMidYear);
        Review review = ReviewLocalServiceUtil.createReview(CounterLocalServiceUtil.increment());
        review.setKpiId(kpiInfo.getKpiId());
        review.setEcode(kpiInfo.getEcode());
        review.setAccount(kpiInfo.getAccount());
        review.setManager(kpiInfo.getManagerEmail());
        review.setAssignee(kpiInfo.getReviewerEmail());
        review.setAssigned(!kpiInfo.getManagerEmail().equalsIgnoreCase(kpiInfo.getReviewerEmail()));
        review.setReviewStartDate(getStartOfDayDate(financialYearStart));
        review.setAccountPrimary(kpiInfo.getAccountPrimary());
        if (accountAnnual) {
            review.setReviewState(1);
            review.setFinalYearReview(true);
        } else {
            review.setReviewState(2);
            review.setStage1Date(getIstDate());
            review.setFinalYearReview(false);
        }
        ReviewLocalServiceUtil.addReview(review);
        List<KpiLine> kpiReviewInfo = KpiLineLocalServiceUtil.findKpiLineByKpiId(kpiInfo.getKpiId());
        for (KpiLine info : kpiReviewInfo) {
            ReviewLine reviewLine = ReviewLineLocalServiceUtil.createReviewLine(CounterLocalServiceUtil.increment());
            reviewLine.setReviewId(review.getReviewId());
            reviewLine.setGuideId(info.getGuideId());
            reviewLine.setTitle(info.getTitle());
            reviewLine.setDescription(info.getDescription());
            reviewLine.setTarget(info.getTarget());
            ReviewLineLocalServiceUtil.addReviewLine(reviewLine);
        }
        Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
        if (accountAnnual) {
            Employee empData = EmployeeLocalServiceUtil.fetchEmployee(review.getEcode());
            email.setToAddress(empData.getEmail());
            email.setBody(MessageFormat.format(BODY_OVERRIDE, getPortalUrl() + URL_PERFORMANCE));
            email.setSubject(MessageFormat.format(SUBJECT_OVERRIDE, ANNUAL));
        } else {
            email.setToAddress(review.getManager());
            email.setBody(MessageFormat.format(BODY_MIDYEAR_OVERRIDE, getPortalUrl() + URL_PERFORMANCE_ADMIN));
            email.setSubject(MessageFormat.format(SUBJECT_MIDYEAR_OVERRIDE, MID_YEAR));
        }
        email.setModule(MODULE_PERFORMANCE);
        email.setCreatedDate(getIstDate());
        email.setScheduled(false);
        email.setSent(false);
        EmailLocalServiceUtil.addEmail(email);
    }

    @Override
    public void closeReviewFormByHr(ActionRequest request) {
        long reviewId = ParamUtil.getLong(request, REVIEW_ID);
        Review review = ReviewLocalServiceUtil.fetchReview(reviewId);
        review.setReviewState(6);
        review.setStage5Date(getIstDate());
        ReviewLocalServiceUtil.updateReview(review);
        Employee empData = EmployeeLocalServiceUtil.fetchEmployee(review.getEcode());
        Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
        email.setBody(BODY_CLOSE);
        email.setSubject(SUBJECT_CLOSE);
        email.setToAddress(empData.getEmail());
        email.setCcAddress(review.getManager() + COMMA + DL_HR);
        email.setModule(MODULE_PERFORMANCE);
        email.setCreatedDate(getIstDate());
        email.setScheduled(false);
        email.setSent(false);
        EmailLocalServiceUtil.addEmail(email);

    }

    @Override
    public void submitRollbackRequest(ActionRequest request) {
        String[] selected = ParamUtil.getParameterValues(request, "managerSelected", null);
        String managerEmail = getLoggedUser(request);
        List<Employee> employeeDataList = EmployeeLocalServiceUtil.findAllActiveEmployees();
        List<ReviewRollback> rollbackEntries = ReviewRollbackLocalServiceUtil.findReviewRollbackByStatus(managerEmail);
        StringBuilder tableContent = new StringBuilder(TABLE_START);
        for (String review : selected) {
            List<ReviewRollback> rollbackEntriesStatus = rollbackEntries.stream().filter(s -> String.valueOf(s.getReviewId()).equalsIgnoreCase(review)).collect(Collectors.toList());
            if (rollbackEntriesStatus.isEmpty()) {
                ReviewRollback rollbackHeaders = ReviewRollbackLocalServiceUtil.createReviewRollback(CounterLocalServiceUtil.increment());
                rollbackHeaders.setReviewId(Long.parseLong(review));
                rollbackHeaders.setRequestBy(managerEmail);
                rollbackHeaders.setRequestDate(getIstDate());
                rollbackHeaders.setStatus(0);
                ReviewRollbackLocalServiceUtil.addReviewRollback(rollbackHeaders);
            } else {
                ReviewRollback rollbackHeaders = ReviewRollbackLocalServiceUtil.fetchReviewRollback(rollbackEntriesStatus.get(0).getRollbackId());
                rollbackHeaders.setRequestDate(getIstDate());
                rollbackHeaders.setRequestBy(managerEmail);
                rollbackHeaders.setStatus(0);
                ReviewRollbackLocalServiceUtil.updateReviewRollback(rollbackHeaders);
            }
            Review review1 = ReviewLocalServiceUtil.fetchReview(Long.parseLong(review));
            List<Employee> employeeData = employeeDataList.stream().filter(e -> e.getEcode().equalsIgnoreCase(review1.getEcode())).collect(Collectors.toList());
            tableContent.append(ROW_START);
            tableContent.append(employeeData.isEmpty() ? BLANK : employeeData.get(0).getEcode());
            tableContent.append(CELL_SEPARATOR);
            tableContent.append(employeeData.isEmpty() ? BLANK : employeeData.get(0).getName());
            tableContent.append(CELL_SEPARATOR);
            tableContent.append(review1.getAccount());
            tableContent.append(CELL_SEPARATOR);
            tableContent.append(review1.isFinalYearReview() ? ANNUAL : MID_YEAR);
            tableContent.append(CELL_SEPARATOR);
            tableContent.append(review1.isAccountPrimary() ? PRIMARY : SECONDARY);
            tableContent.append(ROW_END);
        }
        tableContent.append(TABLE_END);
        List<Employee> loggedUserData = employeeDataList.stream().filter(e -> e.getEmail().equalsIgnoreCase(getLoggedUser(request))).collect(Collectors.toList());
        Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
        email.setBody(MessageFormat.format(BODY_REQUEST_ROLLBACK, loggedUserData.get(0).getName(), tableContent.toString(), getPortalUrl() + URL_PERFORMANCE_ADMIN));
        email.setSubject(MessageFormat.format(SUBJECT_REQUEST_ROLLBACK, loggedUserData.get(0).getName()));
        email.setToAddress(DL_HR);
        email.setCcAddress(loggedUserData.get(0).getEmail());
        email.setModule(MODULE_PERFORMANCE);
        email.setCreatedDate(getIstDate());
        email.setScheduled(false);
        email.setSent(false);
        EmailLocalServiceUtil.addEmail(email);
    }

    @Override
    public void submitCloseRequest(ActionRequest request) {
        String[] selected = ParamUtil.getParameterValues(request, "managerSelectedClose", null);
        String managerEmail = getLoggedUser(request);
        List<ReviewClose> closeEntries = ReviewCloseLocalServiceUtil.findReviewCloseByEmail(managerEmail);
        List<Employee> employeeDataList = EmployeeLocalServiceUtil.findAllActiveEmployees();
        StringBuilder tableContent = new StringBuilder(TABLE_START);
        for (String review : selected) {
            List<ReviewClose> closeEntriesStatus = closeEntries.stream().filter(s -> String.valueOf(s.getReviewId()).equalsIgnoreCase(review)).collect(Collectors.toList());
            if (closeEntriesStatus.isEmpty()) {
                ReviewClose closeHeaders = ReviewCloseLocalServiceUtil.createReviewClose(CounterLocalServiceUtil.increment());
                closeHeaders.setReviewId(Long.parseLong(review));
                closeHeaders.setRequestBy(managerEmail);
                closeHeaders.setRequestDate(getIstDate());
                closeHeaders.setStatus(0);
                ReviewCloseLocalServiceUtil.addReviewClose(closeHeaders);
            } else {
                ReviewClose closeHeaders = ReviewCloseLocalServiceUtil.fetchReviewClose(closeEntriesStatus.get(0).getCloseId());
                closeHeaders.setRequestDate(getIstDate());
                closeHeaders.setRequestBy(managerEmail);
                closeHeaders.setStatus(0);
                ReviewCloseLocalServiceUtil.updateReviewClose(closeHeaders);
            }
            Review review1 = ReviewLocalServiceUtil.fetchReview(Long.parseLong(review));
            List<Employee> employeeData = employeeDataList.stream().filter(e -> e.getEcode().equalsIgnoreCase(review1.getEcode())).collect(Collectors.toList());
            tableContent.append(ROW_START);
            tableContent.append(employeeData.isEmpty() ? BLANK : employeeData.get(0).getEcode());
            tableContent.append(CELL_SEPARATOR);
            tableContent.append(employeeData.isEmpty() ? BLANK : employeeData.get(0).getName());
            tableContent.append(CELL_SEPARATOR);
            tableContent.append(review1.getAccount());
            tableContent.append(CELL_SEPARATOR);
            tableContent.append(review1.isFinalYearReview() ? ANNUAL : MID_YEAR);
            tableContent.append(CELL_SEPARATOR);
            tableContent.append(review1.isAccountPrimary() ? PRIMARY : SECONDARY);
            tableContent.append(ROW_END);
        }
        tableContent.append(TABLE_END);
        List<Employee> employeeList = employeeDataList.stream().filter(s -> s.getEmail().equalsIgnoreCase(getLoggedUser(request))).collect(Collectors.toList());
        Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
        email.setBody(MessageFormat.format(BODY_REQUEST_CLOSE, employeeList.get(0).getName(), tableContent.toString(), getPortalUrl() + URL_PERFORMANCE_ADMIN));
        email.setSubject(SUBJECT_REQUEST_CLOSE);
        email.setToAddress(DL_HR);
        email.setCcAddress(employeeList.get(0).getEmail());
        email.setModule(MODULE_PERFORMANCE);
        email.setCreatedDate(getIstDate());
        email.setScheduled(false);
        email.setSent(false);
        EmailLocalServiceUtil.addEmail(email);
    }

    @Override
    public void submitActionOnRollbackByHR(ActionRequest request) {
        int actionPerformed = ParamUtil.getInteger(request, "actionPerformed");
        String[] selected = ParamUtil.getParameterValues(request, "hrSelected", null);
        List<Review> reviewList = new ArrayList<>();
        StringBuilder tableContent = new StringBuilder(TABLE_START);
        if (actionPerformed == 1) {
            for (String rollbackId : selected) {
                ReviewRollback rollbackRequest = ReviewRollbackLocalServiceUtil.fetchReviewRollback(Long.parseLong(rollbackId));
                rollbackRequest.setStatus(1);
                rollbackRequest.setActionDate(getIstDate());
                ReviewRollbackLocalServiceUtil.updateReviewRollback(rollbackRequest);
                List<ReviewLine> reviewLines = ReviewLineLocalServiceUtil.findReviewLineByReviewId(rollbackRequest.getReviewId());
                for (ReviewLine reviewLine : reviewLines) {
                    ReviewLine line = ReviewLineLocalServiceUtil.fetchReviewLine(reviewLine.getLineId());
                    line.setManagerRating(null);
                    ReviewLineLocalServiceUtil.updateReviewLine(line);
                }
                Review review = ReviewLocalServiceUtil.fetchReview(rollbackRequest.getReviewId());
                review.setReviewState(2);
                review.setRating(null);
                review.setComment(null);
                review.setStage2Date(null);
                review.setStage3Date(null);
                review.setStage4Date(null);
                review.setStage5Date(null);
                ReviewLocalServiceUtil.updateReview(review);
                reviewList.add(review);
            }
        } else {
            for (String rollbackId : selected) {
                ReviewRollback rollbackRequest = ReviewRollbackLocalServiceUtil.fetchReviewRollback(Long.parseLong(rollbackId));
                rollbackRequest.setStatus(2);
                rollbackRequest.setActionDate(getIstDate());
                ReviewRollbackLocalServiceUtil.updateReviewRollback(rollbackRequest);
                Review review = ReviewLocalServiceUtil.fetchReview(rollbackRequest.getReviewId());
                reviewList.add(review);
            }
        }
        Set<String> managerUniqueEmail = reviewList.stream().map(Review::getManager).collect(Collectors.toSet());
        for (String emailId : managerUniqueEmail) {
            List<Review> managerReviewList = reviewList.stream().filter(s -> s.getManager().equalsIgnoreCase(emailId)).collect(Collectors.toList());
            for (Review list : managerReviewList) {
                Review reviewData = ReviewLocalServiceUtil.fetchReview(list.getReviewId());
                Employee employeeData = EmployeeLocalServiceUtil.fetchEmployee(list.getEcode());
                tableContent.append(ROW_START);
                tableContent.append(employeeData.getEcode());
                tableContent.append(CELL_SEPARATOR);
                tableContent.append(employeeData.getName());
                tableContent.append(CELL_SEPARATOR);
                tableContent.append(reviewData.getAccount());
                tableContent.append(CELL_SEPARATOR);
                tableContent.append(reviewData.isFinalYearReview() ? ANNUAL : MID_YEAR);
                tableContent.append(CELL_SEPARATOR);
                tableContent.append(reviewData.isAccountPrimary() ? PRIMARY : SECONDARY);
                tableContent.append(ROW_END);
            }
            tableContent.append(TABLE_END);
            Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
            if (actionPerformed == 1) {
                email.setSubject(SUBJECT_APPROVE_ROLLBACK_MANAGER);
                email.setBody(MessageFormat.format(BODY_APPROVE_ROLLBACK_MANAGER, tableContent.toString(), getPortalUrl() + URL_PERFORMANCE_ADMIN));
            } else {
                email.setSubject(SUBJECT_REJECT_ROLLBACK_MANAGER);
                email.setBody(MessageFormat.format(BODY_REJECT_ROLLBACK_MANAGER, tableContent.toString(), getPortalUrl() + URL_PERFORMANCE_ADMIN));
            }
            email.setToAddress(emailId);
            email.setModule(MODULE_PERFORMANCE);
            email.setCreatedDate(getIstDate());
            email.setScheduled(false);
            email.setSent(false);
            EmailLocalServiceUtil.addEmail(email);
        }
    }

    @Override
    public void submitActionOnCloseByHR(ActionRequest request) {
        int actionPerformed = ParamUtil.getInteger(request, "actionPerformedOnClose");
        String[] selected = ParamUtil.getParameterValues(request, "hrSelectedClose", null);
        if (actionPerformed == 1) {
            for (String closeId : selected) {
                ReviewClose closeRequest = ReviewCloseLocalServiceUtil.fetchReviewClose(Long.parseLong(closeId));
                closeRequest.setStatus(1);
                closeRequest.setActionDate(getIstDate());
                ReviewCloseLocalServiceUtil.updateReviewClose(closeRequest);
                Review review = ReviewLocalServiceUtil.fetchReview(closeRequest.getReviewId());
                review.setReviewState(6);
                ReviewLocalServiceUtil.updateReview(review);
                Employee empInfo = EmployeeLocalServiceUtil.fetchEmployee(review.getEcode());
                Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
                email.setSubject(SUBJECT_CLOSE);
                email.setBody(BODY_CLOSE);
                email.setToAddress(empInfo.getEmail());
                email.setCcAddress(review.getManager() + COMMA + DL_HR);
                email.setModule(MODULE_PERFORMANCE);
                email.setCreatedDate(getIstDate());
                email.setScheduled(false);
                email.setSent(false);
                EmailLocalServiceUtil.addEmail(email);
            }
        } else {
            for (String closeId : selected) {
                ReviewClose closeRequest = ReviewCloseLocalServiceUtil.fetchReviewClose(Long.parseLong(closeId));
                closeRequest.setStatus(2);
                closeRequest.setActionDate(getIstDate());
                ReviewCloseLocalServiceUtil.updateReviewClose(closeRequest);
                Review review = ReviewLocalServiceUtil.fetchReview(closeRequest.getReviewId());
                Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
                email.setSubject(SUBJECT_REJECT_CLOSE);
                email.setBody(BODY_REJECT_CLOSE);
                email.setToAddress(review.getManager());
                email.setModule(MODULE_PERFORMANCE);
                email.setCreatedDate(getIstDate());
                email.setScheduled(false);
                email.setSent(false);
                EmailLocalServiceUtil.addEmail(email);
            }
        }
    }

    @Override
    public void updateFinalYearReviewTimeline(ActionRequest request) {
        List<ReviewTimeline> timelines = ReviewTimelineLocalServiceUtil.getReviewTimelines(-1, -1);
        for (int index = 0; index < 5; index++) {
            String finalyearDate = ParamUtil.getString(request, "finalyearDate" + index);
            Date midyearStageEndDate = convertLocalDateTimeToDate(LocalDate.parse(finalyearDate, FORMATTER_YYYY_MM_DD).atStartOfDay());
            final int state = index;
            long timelineId = timelines.stream().filter(ReviewTimeline::isFinalYear).filter(t -> t.getReviewState() == state).collect(Collectors.toList()).get(0).getTimelineId();
            ReviewTimeline timeline = ReviewTimelineLocalServiceUtil.fetchReviewTimeline(timelineId);
            timeline.setEndDate(midyearStageEndDate);
            ReviewTimelineLocalServiceUtil.updateReviewTimeline(timeline);
        }
        int year = Integer.parseInt(ParamUtil.getString(request, "finalyearDate4").split("-")[0]);
        Date autoMomentYearEnd = convertLocalDateTimeToDate(getFinancialEndDate(year - 1).atStartOfDay());
        long completeTimelineId = timelines.stream().filter(ReviewTimeline::isFinalYear).filter(t -> t.getReviewState() == 5).collect(Collectors.toList()).get(0).getTimelineId();
        ReviewTimeline completeTimeline = ReviewTimelineLocalServiceUtil.fetchReviewTimeline(completeTimelineId);
        completeTimeline.setEndDate(autoMomentYearEnd);
        ReviewTimelineLocalServiceUtil.updateReviewTimeline(completeTimeline);
        long closedTimelineId = timelines.stream().filter(ReviewTimeline::isFinalYear).filter(t -> t.getReviewState() == 6).collect(Collectors.toList()).get(0).getTimelineId();
        ReviewTimeline closedTimeline = ReviewTimelineLocalServiceUtil.fetchReviewTimeline(closedTimelineId);
        closedTimeline.setEndDate(autoMomentYearEnd);
        ReviewTimelineLocalServiceUtil.updateReviewTimeline(closedTimeline);
    }

    @Override
    public void rollbackReviewFromForm(ActionRequest request) {
        long reviewId = ParamUtil.getLong(request, REVIEW_ID);
        int rollbackState = ParamUtil.getInteger(request, "rollbackState");
        if (rollbackState == 1) {
            Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
            List<ReviewLine> lineByReviewId = ReviewLineLocalServiceUtil.findReviewLineByReviewId(reviewId);
            for (ReviewLine line : lineByReviewId) {
                ReviewLine reviewLine = ReviewLineLocalServiceUtil.fetchReviewLine(line.getLineId());
                reviewLine.setEmployeeRating(null);
                reviewLine.setManagerRating(null);
                ReviewLineLocalServiceUtil.updateReviewLine(reviewLine);
            }
            Review review = ReviewLocalServiceUtil.fetchReview(reviewId);
            review.setReviewState(rollbackState);
            if (review.isFinalYearReview()) {
                email.setSubject(MessageFormat.format(SUBJECT_ROLLBACK_EMPLOYEE, ANNUAL));
                email.setBody(MessageFormat.format(BODY_ROLLBACK_EMPLOYEE, ANNUAL));
            } else {
                email.setSubject(MessageFormat.format(SUBJECT_ROLLBACK_EMPLOYEE, MID_YEAR));
                email.setBody(MessageFormat.format(BODY_ROLLBACK_EMPLOYEE, MID_YEAR));
            }
            review.setComment(null);
            review.setRating(null);
            review.setStage1Date(null);
            review.setStage2Date(null);
            review.setStage3Date(null);
            review.setStage4Date(null);
            review.setStage5Date(null);
            ReviewLocalServiceUtil.updateReview(review);
            String managerEmail = review.getManager().equalsIgnoreCase(review.getAssignee()) ? review.getManager() : review.getManager() + COMMA + review.getAssignee();
            email.setToAddress(EmployeeLocalServiceUtil.fetchEmployee(review.getEcode()).getEmail());
            email.setCcAddress(managerEmail);
            email.setModule(MODULE_PERFORMANCE);
            email.setCreatedDate(getIstDate());
            email.setScheduled(false);
            email.setSent(false);
            EmailLocalServiceUtil.addEmail(email);
        } else if (rollbackState == 2) {
            List<ReviewLine> reviewLines = ReviewLineLocalServiceUtil.findReviewLineByReviewId(reviewId);
            Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
            for (ReviewLine line : reviewLines) {
                ReviewLine reviewLine = ReviewLineLocalServiceUtil.fetchReviewLine(line.getLineId());
                reviewLine.setManagerRating(null);
                ReviewLineLocalServiceUtil.updateReviewLine(reviewLine);
            }
            Review review = ReviewLocalServiceUtil.fetchReview(reviewId);
            review.setReviewState(rollbackState);
            review.setRating(null);
            Employee employee = EmployeeLocalServiceUtil.fetchEmployee(review.getEcode());
            if (review.isFinalYearReview()) {
                email.setSubject(MessageFormat.format(SUBJECT_ROLLBACK_MANAGER, ANNUAL));
                email.setBody(MessageFormat.format(BODY_ROLLBACK_MANAGER, ANNUAL));
            } else {
                email.setSubject(MessageFormat.format(SUBJECT_ROLLBACK_MANAGER, MID_YEAR));
                email.setBody(MessageFormat.format(BODY_ROLLBACK_MANAGER, MID_YEAR));
            }
            review.setComment(null);
            review.setStage2Date(null);
            review.setStage3Date(null);
            review.setStage4Date(null);
            review.setStage5Date(null);
            ReviewLocalServiceUtil.updateReview(review);
            String managerEmail = review.getManager().equalsIgnoreCase(review.getAssignee()) ? review.getManager() : review.getManager() + COMMA + review.getAssignee();
            email.setToAddress(managerEmail);
            email.setCcAddress(employee.getEmail());
            email.setModule(MODULE_PERFORMANCE);
            email.setCreatedDate(getIstDate());
            email.setScheduled(false);
            email.setSent(false);
            EmailLocalServiceUtil.addEmail(email);

        } else if (rollbackState == 4) {
            Review review = ReviewLocalServiceUtil.fetchReview(reviewId);
            review.setReviewState(rollbackState);
            ReviewLocalServiceUtil.updateReview(review);
        }
    }

    @Override
    public void assigneeReview(ActionRequest request) {
        long reviewId = ParamUtil.getLong(request, REVIEW_ID);
        String assigneeEmail = ParamUtil.getString(request, "assigneeEmail");
        Employee assigneeData = EmployeeLocalServiceUtil.findByEmail(assigneeEmail.trim());
        String assigneeName = assigneeData.getName();
        Review review = ReviewLocalServiceUtil.fetchReview(reviewId);
        review.setAssigned(true);
        review.setAssignee(assigneeEmail);
        ReviewLocalServiceUtil.updateReview(review);
        Employee employeeData = EmployeeLocalServiceUtil.fetchEmployee(review.getEcode());

        Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
        List<ReviewTimeline> timelines = ReviewTimelineLocalServiceUtil.getCalibratedTimelines();
        Date managerEndDate = timelines.stream().filter(t -> t.getReviewState() == 2).filter(t -> t.isFinalYear() == review.isFinalYearReview()).collect(Collectors.toList()).get(0).getEndDate();
        if (review.isFinalYearReview()) {
            email.setSubject(MessageFormat.format(SUBJECT_REVIEW_ASSIGNED, ANNUAL));
            email.setBody(MessageFormat.format(BODY_REVIEW_ASSIGNED, assigneeName, ANNUAL, employeeData.getName(), convertDateToLocalDateTime(managerEndDate).format(FORMATTER_YYYY_MM_DD), getPortalUrl() + URL_PERFORMANCE_ADMIN));
        } else {
            email.setSubject(MessageFormat.format(SUBJECT_REVIEW_ASSIGNED, MID_YEAR));
            email.setBody(MessageFormat.format(BODY_REVIEW_ASSIGNED, assigneeName, MID_YEAR, employeeData.getName(), convertDateToLocalDateTime(managerEndDate).format(FORMATTER_YYYY_MM_DD), getPortalUrl() + URL_PERFORMANCE_ADMIN));
        }
        email.setToAddress(assigneeEmail);
        email.setCcAddress(review.getManager() + COMMA + employeeData.getEmail());
        email.setModule(MODULE_PERFORMANCE);
        email.setCreatedDate(getIstDate());
        email.setScheduled(false);
        email.setSent(false);
        EmailLocalServiceUtil.addEmail(email);
    }

    @Override
    public void updateMidYearYearReviewTimeline(ActionRequest request) {
        List<ReviewTimeline> timelines = ReviewTimelineLocalServiceUtil.getReviewTimelines(-1, -1);
        for (int index = 0; index < 3; index++) {
            String midYearStageDate = ParamUtil.getString(request, "midyearDate" + index);
            Date midyearStageEndDate = convertLocalDateTimeToDate(LocalDate.parse(midYearStageDate, FORMATTER_YYYY_MM_DD).atStartOfDay());

            final int state = index;
            long timelineId = timelines.stream().filter(t -> !t.isFinalYear()).filter(t -> t.getReviewState() == state).collect(Collectors.toList()).get(0).getTimelineId();
            ReviewTimeline timeline = ReviewTimelineLocalServiceUtil.fetchReviewTimeline(timelineId);
            timeline.setEndDate(midyearStageEndDate);
            ReviewTimelineLocalServiceUtil.updateReviewTimeline(timeline);
        }
        int year = Integer.parseInt(ParamUtil.getString(request, "midyearDate2").split("-")[0]);
        Date autoMomentYearEnd = convertLocalDateTimeToDate(getFinancialEndDate(year - 1).atStartOfDay());
        long completeTimelineId = timelines.stream().filter(t -> !t.isFinalYear()).filter(t -> t.getReviewState() == 5).collect(Collectors.toList()).get(0).getTimelineId();
        ReviewTimeline completeTimeline = ReviewTimelineLocalServiceUtil.fetchReviewTimeline(completeTimelineId);
        completeTimeline.setEndDate(autoMomentYearEnd);
        ReviewTimelineLocalServiceUtil.updateReviewTimeline(completeTimeline);
        long closedTimelineId = timelines.stream().filter(t -> !t.isFinalYear()).filter(t -> t.getReviewState() == 6).collect(Collectors.toList()).get(0).getTimelineId();
        ReviewTimeline closedTimeline = ReviewTimelineLocalServiceUtil.fetchReviewTimeline(closedTimelineId);
        closedTimeline.setEndDate(autoMomentYearEnd);
        ReviewTimelineLocalServiceUtil.updateReviewTimeline(closedTimeline);
    }

    @Override
    public void rejectKpiForm(ActionRequest request, ActionResponse response) {
        String kpiId = ParamUtil.getString(request, KPI_ID);
        String rejectionComment = ParamUtil.getString(request, "rejectObjRsn");
        Kpi kpi = KpiLocalServiceUtil.fetchKpi(Long.parseLong(kpiId));
        kpi.setKpiSettingStatus(false);
        kpi.setRejectionComment(rejectionComment);
        KpiLocalServiceUtil.updateKpi(kpi);
        Employee empData = EmployeeLocalServiceUtil.fetchEmployee(kpi.getEcode());

        Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
        email.setBody(MessageFormat.format(BODY_KPIREJECT, getPortalUrl() + URL_PERFORMANCE));
        email.setSubject(SUBJECT_KPIREJECT);
        email.setToAddress(empData.getEmail());
        email.setCcAddress(kpi.getManagerEmail() + COMMA + kpi.getReviewerEmail());
        email.setModule(MODULE_PERFORMANCE);
        email.setCreatedDate(getIstDate());
        email.setScheduled(false);
        email.setSent(false);
        EmailLocalServiceUtil.addEmail(email);
    }

    @Override
    public void downloadReviewReport(ResourceResponse response, int year) throws IOException {
        LocalDate today = getIstLocalDateTime().toLocalDate();
        LocalDate financialYearStart = getFinancialStartDate(year);
        List<ReviewTimeline> reviewTimelines = ReviewTimelineLocalServiceUtil.getCalibratedTimelines().stream().filter(ReviewTimeline::getFinalYear).collect(Collectors.toList());
        List<Review> reviews = ReviewLocalServiceUtil.findReviewByReviewStartDate(convertLocalDateTimeToDate(financialYearStart.atStartOfDay()));
        ExcelDto sheet = new ExcelDto();
        List<ExcelDto> employeeExcelDto = new ArrayList<>();
        sheet.setSheetName("All Reviews");
        sheet.setHeaders(Admin_Account_Table_Headers);
        LocalDate financialMidYear = getMidFinancialEndDate(year);
        boolean accountAnnual = today.isBefore(financialYearStart) ||today.isAfter(financialMidYear);
        boolean primaryAccountStatus = false;
        Map<Integer, String> statusMap = new HashMap<>();
        for (ReviewTimeline timeline : reviewTimelines) {
            statusMap.put(timeline.getReviewState(), timeline.getStateName());
        }
        List<Employee> allEmployeeList = EmployeeLocalServiceUtil.findAllActiveEmployees();
        Set<String> allEcode = allEmployeeList.stream().map(Employee::getEcode).collect(Collectors.toSet());
        List<List<String>> accountTableRows = new ArrayList<>();
        Set<String> accountEcodes = reviews.stream().map(Review::getEcode).collect(Collectors.toSet());
        for (String ecode : accountEcodes) {
            List<Review> accountEcodeReviewList = reviews.stream().filter(a -> a.getEcode().equals(ecode)).filter(s -> s.isFinalYearReview() == accountAnnual).collect(Collectors.toList());
            List<Employee> employeeData = allEmployeeList.stream().filter(a -> a.getEcode().equalsIgnoreCase(ecode)).collect(Collectors.toList());
            if (!employeeData.isEmpty()) {
                for (Review review : accountEcodeReviewList) {
                    List<String> accountTableRow = new ArrayList<>();
                    accountTableRow.add(ecode);
                    accountTableRow.add(employeeData.get(0).getName());
                    if (review.isAccountPrimary()) {
                        primaryAccountStatus = true;
                    }
                    accountTableRow.add(review.isAccountPrimary() ? PRIMARY : SECONDARY);
                    accountTableRow.add(review.isFinalYearReview() ? ANNUAL : MID_YEAR);
                    accountTableRow.add(statusMap.get(review.getReviewState()));
                    accountTableRow.add(review.getAccount());
                    accountTableRow.add(review.getManager());
                    accountTableRow.add(review.getAssignee());
                    if (review.getReviewState() >= STAGE_MANAGER_REVIEW) {
                        accountTableRow.add(review.getRating());
                        if (review.getCriticalToAccount() == 1) {
                            accountTableRow.add(YES);
                        } else if (review.getCriticalToAccount() == 2) {
                            accountTableRow.add(NO);
                        } else {
                            accountTableRow.add(BLANK);
                        }
                        if (review.getCriticalToCompany() == 1) {
                            accountTableRow.add(YES);
                        } else if (review.getCriticalToCompany() == 2) {
                            accountTableRow.add(NO);
                        } else {
                            accountTableRow.add(BLANK);
                        }
                        if (review.isFinalYearReview()) {
                            accountTableRow.add(review.getExpectedDesignation());
                            accountTableRow.add(review.getExpectedSalary());
                            if (review.isPromotionRecommendation()) {
                                accountTableRow.add(YES);
                                accountTableRow.add(review.getExcelledArea());
                                accountTableRow.add(review.getRolePlayed());
                                accountTableRow.add(review.getReasonRecommendation());
                                accountTableRow.add(review.getReadyReason());
                            } else {
                                accountTableRow.add(NO);
                                accountTableRow.add(BLANK);
                                accountTableRow.add(BLANK);
                                accountTableRow.add(BLANK);
                                accountTableRow.add(BLANK);
                            }
                        } else {
                            accountTableRow.add(BLANK);
                            accountTableRow.add(BLANK);
                            accountTableRow.add(BLANK);
                            accountTableRow.add(BLANK);
                            accountTableRow.add(BLANK);
                            accountTableRow.add(BLANK);
                            accountTableRow.add(BLANK);
                        }
                    } else {
                        accountTableRow.add(BLANK);
                        accountTableRow.add(BLANK);
                        accountTableRow.add(BLANK);
                        accountTableRow.add(BLANK);
                        accountTableRow.add(BLANK);
                        accountTableRow.add(BLANK);
                        accountTableRow.add(BLANK);
                        accountTableRow.add(BLANK);
                        accountTableRow.add(BLANK);
                        accountTableRow.add(BLANK);
                    }
                    accountTableRows.add(accountTableRow);
                    if (primaryAccountStatus) {
                        allEcode.remove(ecode);
                    }
                }
            }
        }
        if (year == getCurrentYear()) {
            for (String ecode : allEcode) {
                Employee employeeData = allEmployeeList.stream().filter(e -> e.getEcode().equalsIgnoreCase(ecode)).collect(Collectors.toList()).get(0);
                List<String> noReviewTableRow = new ArrayList<>();
                if (employeeData.isStatus()) {
                    noReviewTableRow.add(ecode);
                    noReviewTableRow.add(employeeData.getName());
                    noReviewTableRow.add(PRIMARY);
                    noReviewTableRow.add(accountAnnual ? ANNUAL : MID_YEAR);
                    noReviewTableRow.add("Self review not yet generated");
                    noReviewTableRow.add(employeeData.getAccount());
                    String managerName = BLANK;
                    List<Employee> manager = allEmployeeList.stream().filter(e -> e.getEcode().equalsIgnoreCase(employeeData.getManager())).collect(Collectors.toList());
                    if (!manager.isEmpty()) {
                        managerName = manager.get(0).getEmail();
                    }
                    noReviewTableRow.add(managerName);
                    String reviewerName = BLANK;
                    List<Employee> reviewer = allEmployeeList.stream().filter(e -> e.getEcode().equalsIgnoreCase(employeeData.getReviewer())).collect(Collectors.toList());
                    if (!reviewer.isEmpty()) {
                        reviewerName = reviewer.get(0).getEmail();
                    }
                    noReviewTableRow.add(reviewerName);
                    accountTableRows.add(noReviewTableRow);
                }
            }
        }
        sheet.setData(accountTableRows);
        sheet.setSheetIndex(1);
        employeeExcelDto.add(sheet);
        List<List<String>> summaryRows = new ArrayList<>();
        int totalReviews = 0;
        for (ReviewTimeline timeline : reviewTimelines) {
            List<String> summaryEntry = new ArrayList<>();
            summaryEntry.add(timeline.getStateName());
            List<List<String>> perStageReviews = accountTableRows.stream().filter(e -> e.get(4).equalsIgnoreCase(timeline.getStateName())).collect(Collectors.toList());
            summaryEntry.add(BLANK + perStageReviews.size());
            totalReviews += perStageReviews.size();
            summaryRows.add(summaryEntry);
        }
        ExcelDto certificateSheet = new ExcelDto();
        certificateSheet.setSheetName("Summary");
        certificateSheet.setHeaders(adminSummaryTableHeaders);
        List<String> summaryTotalEntry = new ArrayList<>();
        summaryTotalEntry.add("Total");
        summaryTotalEntry.add(BLANK + totalReviews);
        certificateSheet.setTotal(summaryTotalEntry);
        certificateSheet.setData(summaryRows);
        certificateSheet.setSheetIndex(0);
        employeeExcelDto.add(certificateSheet);
        ExcelService.createWorkBook(response, "Reviews_FY_" + displayCurrentAY(year), employeeExcelDto);

    }

    @Override
    public void downloadKpiReport(ResourceResponse resourceResponse) throws IOException {
        List<List<String>> kpiTableRows = new ArrayList<>();

        List<Kpi> allEmployeeKpiList = KpiLocalServiceUtil.getKpis(-1, -1);
        List<Employee> allActiveEmployees = EmployeeLocalServiceUtil.findAllActiveEmployees();
        for (Kpi kpi : allEmployeeKpiList) {
            List<Employee> empInfo = allActiveEmployees.stream().filter(e -> e.getEcode().equalsIgnoreCase(kpi.getEcode())).collect(Collectors.toList());
            if (!empInfo.isEmpty()) {
                List<Employee> managerData = allActiveEmployees.stream().filter(s -> s.getEmail().equalsIgnoreCase(kpi.getManagerEmail())).collect(Collectors.toList());
                List<Employee> reviewerData = allActiveEmployees.stream().filter(s -> s.getEmail().equalsIgnoreCase(kpi.getReviewerEmail())).collect(Collectors.toList());
                List<String> kpiTableRow = new ArrayList<>();
                kpiTableRow.add(kpi.getEcode());
                kpiTableRow.add(empInfo.get(0).getName());
                kpiTableRow.add(managerData.isEmpty() ? BLANK : managerData.get(0).getName());
                kpiTableRow.add(reviewerData.isEmpty() ? BLANK : reviewerData.get(0).getName());
                kpiTableRow.add(kpi.getAccount());
                kpiTableRow.add(kpi.getAccountPrimary() ? PRIMARY : SECONDARY);
                kpiTableRow.add(kpi.getKpiSettingStatus() ? COMPLETE : PENDING);

                kpiTableRows.add(kpiTableRow);
            }
        }
        ExcelDto sheet = new ExcelDto();
        List<ExcelDto> employeeExcelDto = new ArrayList<>();
        sheet.setSheetName("All Kpis");
        sheet.setHeaders(kpiSummaryTableHeaders);
        sheet.setData(kpiTableRows);
        sheet.setSheetIndex(0);
        employeeExcelDto.add(sheet);
        ExcelService.createWorkBook(resourceResponse, "KPIs", employeeExcelDto);
    }

    private int getRadioValue(String value) {
        int radio = 0;
        if (value.equalsIgnoreCase("yes")) {
            radio = 1;
        } else if (value.equalsIgnoreCase("no")) {
            radio = 2;
        }
        return radio;
    }

    @Override
    public String getReviewerName(ResourceRequest resourceRequest) {
        String reviewerDetails = null;
        String reviewerEmail = ParamUtil.getString(resourceRequest, "reviewerEmail");
        try {
            Employee reviewerData = EmployeeLocalServiceUtil.findByEmail(reviewerEmail);
            User liferayUser = UserLocalServiceUtil.fetchUserByEmailAddress(COMPANY_ID, reviewerEmail);
            String[] roles = {ROLE_TEAMLEAD, ROLE_MANAGER};
            if (RoleLocalServiceUtil.hasUserRoles(liferayUser.getUserId(), liferayUser.getCompanyId(), roles, false)) {
                reviewerDetails = convertToJson(reviewerData);
            }
        } catch (NullPointerException | PortalException exception) {
            log.error(exception.getStackTrace()[0].getMethodName() + ":" + exception.getStackTrace()[0].getLineNumber() + ":" + exception.getMessage());
        }
        return reviewerDetails;
    }

    @Override
    public String getRollbackRequestStatusEntries(ResourceRequest resourceRequest) {
        int status = ParamUtil.getInteger(resourceRequest, "rollbackRequestSearch");
        String loggedManagerEmail = getLoggedUser(resourceRequest);
        List<EmployeeDto> finalEmployeeList = new ArrayList<>();
        List<Employee> allEmployees = EmployeeLocalServiceUtil.findAllActiveEmployees();
        for (Employee edata : allEmployees) {
            List<Employee> manager = allEmployees.stream().filter(e -> e.getEcode().equalsIgnoreCase(edata.getManager())).collect(Collectors.toList());
            String managerName = BLANK;
            String managerEmail = BLANK;
            if (!manager.isEmpty()) {
                managerName = manager.get(0).getName();
                managerEmail = manager.get(0).getEmail();
            }
            EmployeeDto employeeDataDto = new EmployeeDto(edata.getEcode(), edata.getName(), edata.getAccount(), edata.getDesignation(), managerName, managerEmail);
            finalEmployeeList.add(employeeDataDto);
        }
        Map<Integer, String> reviewStateMapAnnual = new HashMap<>();
        Map<Integer, String> reviewStateMapMidYear = new HashMap<>();
        List<ReviewTimeline> reviewTimelines = ReviewTimelineLocalServiceUtil.getCalibratedTimelines();
        for (ReviewTimeline timeline : reviewTimelines) {
            if (timeline.getReviewState() > 0) {
                if (timeline.isFinalYear()) {
                    reviewStateMapAnnual.put(timeline.getReviewState(), timeline.getStateName());
                } else {
                    reviewStateMapMidYear.put(timeline.getReviewState(), timeline.getStateName());
                }
            }
        }

        List<ReviewRollback> rollbackEntries = ReviewRollbackLocalServiceUtil.findReviewRollbackByStatus(loggedManagerEmail).stream().filter(s -> s.getStatus() == status).collect(Collectors.toList());
        List<ReviewDto> allRollbackReviewForManagerToRequest = new ArrayList<>();
        for (ReviewRollback rollback : rollbackEntries) {
            ReviewDto reviewDto = new ReviewDto();
            Review review = ReviewLocalServiceUtil.fetchReview(rollback.getReviewId());


            reviewDto.setEmployeeCode(review.getEcode());
            reviewDto.setEmployeeName(getEmployee(review.getEcode(), finalEmployeeList).getEmployeeName());
            reviewDto.setAccount(review.getAccount());
            reviewDto.setReviewId(review.getReviewId());
            reviewDto.setReviewType(review.isFinalYearReview() ? ANNUAL : MID_YEAR);
            reviewDto.setAccountType(review.isAccountPrimary() ? PRIMARY : SECONDARY);
            reviewDto.setReviewStateName(review.isFinalYearReview() ? reviewStateMapAnnual.get(review.getReviewState()) : reviewStateMapMidYear.get(review.getReviewState()));
            reviewDto.setReviewStartDate(getDisplayYear(convertDateToLocalDateTime(review.getReviewStartDate()).toLocalDate()));
            if (rollback.getStatus() == 0) {
                reviewDto.setRollbackStatus(PENDING);
            } else if (rollback.getStatus() == 1) {
                reviewDto.setRollbackStatus(APPROVED);
            } else {
                reviewDto.setRollbackStatus(REJECTED);
            }
            allRollbackReviewForManagerToRequest.add(reviewDto);
        }
        return convertToJson(allRollbackReviewForManagerToRequest);
    }

    @Override
    public void saveHrForm(ResourceRequest request) {
        long reviewId = ParamUtil.getLong(request, REVIEW_ID);
        List<ReviewLine> reviewLines = ReviewLineLocalServiceUtil.findReviewLineByReviewId(reviewId);
        for (int x = 0; x < reviewLines.size(); x++) {
            long reviewLineId = ParamUtil.getLong(request, REVIEW_LINE_ID + x);
            ReviewLine hrReview = ReviewLineLocalServiceUtil.fetchReviewLine(reviewLineId);
            String hrRating = ParamUtil.getString(request, "hrRating" + x);
            String hrComments = ParamUtil.getString(request, "hrComment" + x);
            hrReview.setHrRating(hrRating);
            hrReview.setHrComment(hrComments);
            ReviewLineLocalServiceUtil.updateReviewLine(hrReview);
        }
        String hrFinalRating = ParamUtil.getString(request, "hrFinalRating");
        String hrFinalCmmnts = ParamUtil.getString(request, "hrOverallComments");
        Review review = ReviewLocalServiceUtil.fetchReview(reviewId);
        review.setHrComment(hrFinalCmmnts);
        review.setRating(hrFinalRating);
        ReviewLocalServiceUtil.updateReview(review);
    }

    @Override
    public void setReviewFormValue(ResourceRequest resourceRequest) {
        long reviewId = ParamUtil.getLong(resourceRequest, REVIEW_ID);
        Review review = ReviewLocalServiceUtil.fetchReview(reviewId);
        String overallManagerCmmnts = ParamUtil.getString(resourceRequest, "managerOverallComments");
        String improvementCmmnts = ParamUtil.getString(resourceRequest, "improvementComment");
        if (review.getFinalYearReview()) {
            List<ReviewLine> reviewLines = ReviewLineLocalServiceUtil.findReviewLineByReviewId(reviewId);
            String trainingReqd = ParamUtil.getString(resourceRequest, "trainingReqd");
            String finalManagerRating = ParamUtil.getString(resourceRequest, "managerFinalRating");
            String criticalToAcct = ParamUtil.getString(resourceRequest, "criticalToAcct");
            String criticalToCompany = ParamUtil.getString(resourceRequest, "criticalToCompany");
            String[] promotionReqd = ParamUtil.getParameterValues(resourceRequest, "promotionReqd", null);
            String areaExcelled = ParamUtil.getString(resourceRequest, "excelledAreaAns");
            String rolePlayedAns = ParamUtil.getString(resourceRequest, "rolePlayedAns");
            String rznRecommend = ParamUtil.getString(resourceRequest, "reasonRecommendAns");
            String exceptedDesignation = ParamUtil.getString(resourceRequest, "exceptedDesignation");
            String exceptedSalary = ParamUtil.getString(resourceRequest, "exceptedSalary");
            String readyAns = ParamUtil.getString(resourceRequest, "readyAns");
            String replacement = ParamUtil.getString(resourceRequest, "replacement");
            String replacementReason = ParamUtil.getString(resourceRequest, "replacementReason");
            String replacementOtherReason = ParamUtil.getString(resourceRequest, "replacementOtherReason");
            String replacementDetail = null;
            if (replacement.equalsIgnoreCase("YES")) {
                replacementDetail = ParamUtil.getString(resourceRequest, "replacementEcode");
                review.setReplacementReasonOther(replacementReason.equalsIgnoreCase("Other"));
                review.setReplacementReason(replacementReason.equalsIgnoreCase("Other") ? replacementOtherReason : replacementReason);
            } else if (replacement.equalsIgnoreCase("NO")) {
                replacementDetail = ParamUtil.getString(resourceRequest, "replacementDetail");
            }
            for (int x = 0; x < reviewLines.size(); x++) {
                String objId = ParamUtil.getString(resourceRequest, REVIEW_LINE_ID + x);
                ReviewLine managerReview = ReviewLineLocalServiceUtil.fetchReviewLine(Long.parseLong(objId));
                String managerRating = ParamUtil.getString(resourceRequest, "managerRating" + x);
                String managerCmmnts = ParamUtil.getString(resourceRequest, "managerComment" + x);
                managerReview.setManagerRating(managerRating);
                managerReview.setManagerComment(managerCmmnts);
                ReviewLineLocalServiceUtil.updateReviewLine(managerReview);
            }
            review.setTrainings(trainingReqd);
            review.setExpectedDesignation(exceptedDesignation);
            review.setExpectedSalary(exceptedSalary);
            review.setCriticalToAccount(getRadioValue(criticalToAcct));
            review.setCriticalToCompany(getRadioValue(criticalToCompany));
            review.setReplacement(getRadioValue(replacement));
            review.setReplacementDetail(replacementDetail);
            if (promotionReqd != null) {
                review.setPromotionRecommendation(true);
                review.setExcelledArea(areaExcelled);
                review.setRolePlayed(rolePlayedAns);
                review.setReasonRecommendation(rznRecommend);
                review.setReadyReason(readyAns);
            }
            review.setRating(finalManagerRating);
            review.setImprovementComment(improvementCmmnts);
            review.setManagerComment(overallManagerCmmnts);
        } else {
            List<ReviewLine> reviewLines = ReviewLineLocalServiceUtil.findReviewLineByReviewId(reviewId);
            String managerFinalRating = ParamUtil.getString(resourceRequest, "managerFinalRating");
            for (ReviewLine line : reviewLines) {
                ReviewLine managerReview = ReviewLineLocalServiceUtil.fetchReviewLine(line.getLineId());
                managerReview.setManagerRating(managerFinalRating);
                ReviewLineLocalServiceUtil.updateReviewLine(managerReview);
            }
            String apraiseeAchievements = ParamUtil.getString(resourceRequest, "apraiseeAchievements");
            review.setRating(getEmployeeRatingNumerical(managerFinalRating));
            review.setManagerComment(overallManagerCmmnts);
            review.setAchievements(apraiseeAchievements);
            review.setImprovementComment(improvementCmmnts);
        }
        ReviewLocalServiceUtil.updateReview(review);

    }

    @Override
    public String getCloseRequestStatusEntries(ResourceRequest resourceRequest) {
        int status = ParamUtil.getInteger(resourceRequest, "closeRequestSearch");
        String loggedManagerEmail = getLoggedUser(resourceRequest);
        List<EmployeeDto> finalEmployeeList = new ArrayList<>();
        List<Employee> allEmployees = EmployeeLocalServiceUtil.findAllActiveEmployees();
        for (Employee edata : allEmployees) {
            List<Employee> manager = allEmployees.stream().filter(e -> e.getEcode().equalsIgnoreCase(edata.getManager())).collect(Collectors.toList());
            String managerName = BLANK;
            String managerEmail = BLANK;
            if (!manager.isEmpty()) {
                managerName = manager.get(0).getName();
                managerEmail = manager.get(0).getEmail();
            }
            EmployeeDto employeeDataDto = new EmployeeDto(edata.getEcode(), edata.getName(), edata.getAccount(), edata.getDesignation(), managerName, managerEmail);
            finalEmployeeList.add(employeeDataDto);
        }
        Map<Integer, String> reviewStateMapAnnual = new HashMap<>();
        Map<Integer, String> reviewStateMapMidYear = new HashMap<>();
        List<ReviewTimeline> reviewTimelines = ReviewTimelineLocalServiceUtil.getCalibratedTimelines();
        for (ReviewTimeline timeline : reviewTimelines) {
            if (timeline.getReviewState() > 0) {
                if (timeline.isFinalYear()) {
                    reviewStateMapAnnual.put(timeline.getReviewState(), timeline.getStateName());
                } else {
                    reviewStateMapMidYear.put(timeline.getReviewState(), timeline.getStateName());
                }
            }
        }
        List<ReviewClose> closeEntries = ReviewCloseLocalServiceUtil.findReviewCloseByEmail(loggedManagerEmail).stream().filter(s -> s.getStatus() == status).collect(Collectors.toList());
        List<ReviewDto> allCloseReviewForManagerToRequest = new ArrayList<>();
        for (ReviewClose close : closeEntries) {
            ReviewDto reviewDto = new ReviewDto();
            Review review = ReviewLocalServiceUtil.fetchReview(close.getReviewId());
            reviewDto.setEmployeeCode(review.getEcode());
            reviewDto.setEmployeeName(getEmployee(review.getEcode(), finalEmployeeList).getEmployeeName());
            reviewDto.setAccount(review.getAccount());
            reviewDto.setReviewId(review.getReviewId());
            reviewDto.setReviewType(review.isFinalYearReview() ? ANNUAL : MID_YEAR);
            reviewDto.setAccountType(review.isAccountPrimary() ? PRIMARY : SECONDARY);
            reviewDto.setReviewStateName(review.isFinalYearReview() ? reviewStateMapAnnual.get(review.getReviewState()) : reviewStateMapMidYear.get(review.getReviewState()));
            reviewDto.setReviewStartDate(getDisplayYear(convertDateToLocalDateTime(review.getReviewStartDate()).toLocalDate()));
            if (close.getStatus() == 0) {
                reviewDto.setCloseStatus(PENDING);
            } else if (close.getStatus() == 1) {
                reviewDto.setCloseStatus(APPROVED);
            } else {
                reviewDto.setCloseStatus(REJECTED);
            }
            allCloseReviewForManagerToRequest.add(reviewDto);
        }
        return convertToJson(allCloseReviewForManagerToRequest);
    }

    @Override
    public String getEmployeeEntriesByHr(ResourceRequest resourceRequest) {
        String ecode = ParamUtil.getString(resourceRequest, "ecode");
        Employee edata = null;
        int currentYear = getCurrentYear();
        LocalDate financialYearStart = getFinancialStartDate(currentYear);
        LocalDate financialMidYear = getMidFinancialEndDate(currentYear);
        LocalDate today = getIstLocalDateTime().toLocalDate();
        boolean accountAnnual = today.isBefore(financialYearStart) || today.isAfter(financialMidYear);

        Map<Integer, String> reviewStateMapAnnual = new HashMap<>();
        Map<Integer, String> reviewStateMapMidYear = new HashMap<>();
        List<ReviewTimeline> reviewTimelines = ReviewTimelineLocalServiceUtil.getCalibratedTimelines();
        for (ReviewTimeline timeline : reviewTimelines) {
            if (timeline.getReviewState() > 0) {
                if (timeline.isFinalYear()) {
                    reviewStateMapAnnual.put(timeline.getReviewState(), timeline.getStateName());
                } else {
                    reviewStateMapMidYear.put(timeline.getReviewState(), timeline.getStateName());
                }
            }
        }
        List<Employee> activeEmployees = EmployeeLocalServiceUtil.findAllActiveEmployees();
        List<Employee> allSearchedActiveEmployees = activeEmployees.stream().filter(s -> s.getEcode().toUpperCase().contains(ecode.toUpperCase()) || s.getEcode().equalsIgnoreCase(ecode)).collect(Collectors.toList());
        Set<String> ecodeList = allSearchedActiveEmployees.stream().map(Employee::getEcode).collect(Collectors.toSet());
        OverrideDto dto = new OverrideDto();
        List<ReviewDto> employeeReviews = new ArrayList<>();
        List<KpiDto> employeeKpis = new ArrayList<>();

        for (String empId : ecodeList) {
            List<Kpi> kpiInfo = KpiLocalServiceUtil.findKpiByEcode(empId);
            List<Review> review = ReviewLocalServiceUtil.findReviewByEcode(empId).stream().filter(a -> (convertDateToLocalDateTime(a.getReviewStartDate()).toLocalDate().equals(financialYearStart))).collect(Collectors.toList());
            if (!review.isEmpty() || !kpiInfo.isEmpty()) {
                List<Employee> list = activeEmployees.stream().filter(s -> s.getEcode().equalsIgnoreCase(empId)).collect(Collectors.toList());
                if (!list.isEmpty()) {
                    edata = list.get(0);
                }
            }
            for (Kpi headers : kpiInfo) {
                KpiDto kpiDto = new KpiDto();

                List<Review> accountReviewEntryStatus = review.stream().filter(s -> (s.getKpiId() == headers.getKpiId() && s.getFinalYearReview() == accountAnnual)).collect(Collectors.toList());
                kpiDto.setOverrideActionButtonEnable(accountReviewEntryStatus.isEmpty() && headers.isKpiSettingStatus());
                kpiDto.setKpiId(headers.getKpiId());
                kpiDto.setEmployeeName(null != edata ? edata.getName() : BLANK);
                kpiDto.setAccountType(headers.isAccountPrimary() ? PRIMARY : SECONDARY);
                kpiDto.setKpiSettingStatusString(headers.isKpiSettingStatus() ? COMPLETE : PENDING);
                kpiDto.setKpiSettingStatus(headers.isKpiSettingStatus());
                kpiDto.setAccountName(headers.getAccount());
                Employee managerData = EmployeeLocalServiceUtil.findByEmail(headers.getManagerEmail());
                kpiDto.setManagerName(managerData.getName());
                employeeKpis.add(kpiDto);
            }
            for (Review headers : review) {
                ReviewDto reviewDto = new ReviewDto();
                reviewDto.setReviewId(headers.getReviewId());
                reviewDto.setEmployeeName(null != edata ? edata.getName() : BLANK);
                reviewDto.setReviewType(headers.isFinalYearReview() ? ANNUAL : MID_YEAR);
                reviewDto.setAccountType(headers.isAccountPrimary() ? PRIMARY : SECONDARY);
                reviewDto.setReviewStateName(headers.isFinalYearReview() ? reviewStateMapAnnual.get(headers.getReviewState()) : reviewStateMapMidYear.get(headers.getReviewState()));
                reviewDto.setReviewState(headers.getReviewState());
                reviewDto.setAccount(headers.getAccount());
                Employee managerData = EmployeeLocalServiceUtil.findByEmail(headers.getManager());
                reviewDto.setManagerName(managerData.getName());
                employeeReviews.add(reviewDto);
            }
            dto.setReviewList(employeeReviews);
            dto.setKpiList(employeeKpis);
        }
        return convertToJson(dto);
    }

    @Override
    public String getManagerYearlyEntries(ResourceRequest resourceRequest) {
        String forDate = ParamUtil.getString(resourceRequest, FOR_DATE);
        String managerEmail = getLoggedUser(resourceRequest);
        Date reviewYear = convertLocalDateTimeToDate(LocalDate.parse(forDate, FORMATTER_YYYY_MM_DD).atStartOfDay());

        Map<Integer, String> reviewStateMapAnnual = new HashMap<>();
        Map<Integer, String> reviewStateMapMidYear = new HashMap<>();
        List<ReviewTimeline> reviewTimelines = ReviewTimelineLocalServiceUtil.getCalibratedTimelines();
        for (ReviewTimeline timeline : reviewTimelines) {
            if (timeline.getReviewState() > 0) {
                if (timeline.isFinalYear()) {
                    reviewStateMapAnnual.put(timeline.getReviewState(), timeline.getStateName());
                } else {
                    reviewStateMapMidYear.put(timeline.getReviewState(), timeline.getStateName());
                }
            }
        }
        List<Employee> allEmployees = EmployeeLocalServiceUtil.findAllActiveEmployees();
        List<ReviewDto> previousYearHeaderList = new ArrayList<>();
        List<Review> reviews = ReviewLocalServiceUtil.findReviewByReviewStartDate(reviewYear).stream().filter(a -> a.getManager().equalsIgnoreCase(managerEmail)).collect(Collectors.toList());
        for (Review headers : reviews) {
            if (headers.getReviewStartDate().compareTo(reviewYear) == 0) {
                List<Employee> employeeData = allEmployees.stream().filter(a -> a.getEcode().equalsIgnoreCase(headers.getEcode())).collect(Collectors.toList());
                if (!employeeData.isEmpty()) {
                    ReviewDto reviewDto = new ReviewDto();
                    reviewDto.setEmployeeCode(headers.getEcode());
                    reviewDto.setEmployeeName(employeeData.get(0).getName());
                    reviewDto.setReviewType(headers.isFinalYearReview() ? ANNUAL : MID_YEAR);
                    reviewDto.setAccountType(headers.isAccountPrimary() ? PRIMARY : SECONDARY);
                    reviewDto.setReviewStateName(headers.isFinalYearReview() ? reviewStateMapAnnual.get(headers.getReviewState()) : reviewStateMapMidYear.get(headers.getReviewState()));
                    reviewDto.setReviewState(headers.getReviewState());
                    reviewDto.setReviewId(headers.getReviewId());
                    previousYearHeaderList.add(reviewDto);
                }
            }
        }
        return convertToJson(previousYearHeaderList);
    }

    @Override
    public String getUserManualObject(ResourceRequest request) {
        int roleAction = ParamUtil.getInteger(request, "roleAction");
        String role;
        switch (roleAction) {
            case 1:
                role = ROLE_TEAMLEAD;
                break;
            case 2:
                role = ROLE_MANAGER;
                break;
            default:
                role = ROLE_HR;

        }
        return "\"" + new String(getUserManual(MODULE_PERFORMANCE, role).getBytes(), StandardCharsets.UTF_8) + "\"";
    }


    @Override
    public String getHRYearlyEntriesURL(ResourceRequest resourceRequest) {
        String forDate = ParamUtil.getString(resourceRequest, FOR_DATE);
        Date reviewYear = convertLocalDateTimeToDate(LocalDate.parse(forDate, FORMATTER_YYYY_MM_DD).atStartOfDay());

        Map<Integer, String> reviewStateMapAnnual = new HashMap<>();
        Map<Integer, String> reviewStateMapMidYear = new HashMap<>();
        List<ReviewTimeline> reviewTimelines = ReviewTimelineLocalServiceUtil.getCalibratedTimelines();
        for (ReviewTimeline timeline : reviewTimelines) {
            if (timeline.getReviewState() > 0) {
                if (timeline.isFinalYear()) {
                    reviewStateMapAnnual.put(timeline.getReviewState(), timeline.getStateName());
                } else {
                    reviewStateMapMidYear.put(timeline.getReviewState(), timeline.getStateName());
                }
            }
        }
        List<Employee> allEmployees = EmployeeLocalServiceUtil.findAllActiveEmployees();
        List<ReviewDto> previousYearHeaderList = new ArrayList<>();
        List<Review> projectInfo = ReviewLocalServiceUtil.findReviewByReviewStartDate(reviewYear);
        for (Review headers : projectInfo) {
            if (headers.getReviewStartDate().compareTo(reviewYear) == 0) {
                List<Employee> employeeData = allEmployees.stream().filter(a -> a.getEcode().equalsIgnoreCase(headers.getEcode())).collect(Collectors.toList());
                if (!employeeData.isEmpty()) {
                    ReviewDto reviewDto = new ReviewDto();
                    reviewDto.setEmployeeCode(headers.getEcode());
                    reviewDto.setEmployeeName(employeeData.get(0).getName());
                    reviewDto.setReviewType(headers.isFinalYearReview() ? ANNUAL : MID_YEAR);
                    reviewDto.setAccountType(headers.isAccountPrimary() ? PRIMARY : SECONDARY);
                    reviewDto.setReviewStateName(headers.isFinalYearReview() ? reviewStateMapAnnual.get(headers.getReviewState()) : reviewStateMapMidYear.get(headers.getReviewState()));
                    reviewDto.setReviewState(headers.getReviewState());
                    reviewDto.setReviewId(headers.getReviewId());
                    previousYearHeaderList.add(reviewDto);
                }
            }
        }
        return convertToJson(previousYearHeaderList);
    }

    private String getEmployeeRatingNumerical(String rating) {
        String numericRating = BLANK;
        if (!rating.trim().equalsIgnoreCase(BLANK)) {
            if (rating.equalsIgnoreCase("A")) {
                numericRating = "4.5";
            } else if (rating.equalsIgnoreCase("B")) {
                numericRating = "3.5";
            } else if (rating.equalsIgnoreCase("C")) {
                numericRating = "3.0";
            } else if (rating.equalsIgnoreCase("D")) {
                numericRating = "1.5";
            } else {
                numericRating = "1.0";
            }
        }
        return numericRating;
    }

    private String getEmployeeRatingText(String rating) {
        String employeRating = BLANK;
        if (!rating.trim().equalsIgnoreCase(BLANK)) {
            Double numericRating = Double.parseDouble(rating);
            if (numericRating >= 4.5) {
                employeRating = CONSISTENTLY_EXCEEDS_EXPECTATIONS;
            } else if (numericRating >= 3.5) {
                employeRating = SOMETIMES_EXCEEDS_EXPECTATIONS;
            } else if (numericRating >= 3) {
                employeRating = CONSISTENTLY_MEETS_EXPECTATIONS;
            } else if (numericRating >= 1.5) {
                employeRating = INCONSISTENTLY_MEETS_EXPECTATIONS;
            } else {
                employeRating = DOES_NOT_MEETS_EXPECTATIONS;
            }
        }
        return employeRating;
    }

    private Map<Integer, String> getProgressMap(Review review) {
        Map<Integer, String> progressMap = new HashMap<>();
        if (null != review.getStage1Date()) {
            progressMap.put(1, convertDateToLocalDateTime(review.getStage1Date()).format(FORMATTER_YYYY_MM_DD));
        } else {
            progressMap.put(1, BLANK);
        }
        if (null != review.getStage2Date()) {
            progressMap.put(2, convertDateToLocalDateTime(review.getStage2Date()).format(FORMATTER_YYYY_MM_DD));
        } else {
            progressMap.put(2, BLANK);
        }

        if (null != review.getStage3Date()) {
            progressMap.put(3, convertDateToLocalDateTime(review.getStage3Date()).format(FORMATTER_YYYY_MM_DD));
        } else {
            progressMap.put(3, BLANK);
        }

        if (null != review.getStage4Date()) {
            progressMap.put(4, convertDateToLocalDateTime(review.getStage4Date()).format(FORMATTER_YYYY_MM_DD));
        } else {
            progressMap.put(4, BLANK);
        }

        if (null != review.getStage5Date()) {
            progressMap.put(5, convertDateToLocalDateTime(review.getStage5Date()).format(FORMATTER_YYYY_MM_DD));
        } else {
            progressMap.put(5, BLANK);
        }
        progressMap.put(6, BLANK);
        progressMap.put(7, BLANK);
        return progressMap;
    }

    private EmployeeDto getEmployee(String s, List<EmployeeDto> finalEmployeeList) {
        EmployeeDto employee;
        List<EmployeeDto> ecodeList = finalEmployeeList.stream().filter(e -> e.getEmployeeCode().equalsIgnoreCase(s)).collect(Collectors.toList());
        if (ecodeList.isEmpty()) {
            employee = new EmployeeDto();
            employee.setEmployeeCode(s);
            employee.setEmployeeName(BLANK);
            employee.setAccount(BLANK);
            employee.setManagerName(BLANK);
            employee.setStatus(false);
        } else {
            employee = ecodeList.get(0);
        }
        return employee;
    }
}
