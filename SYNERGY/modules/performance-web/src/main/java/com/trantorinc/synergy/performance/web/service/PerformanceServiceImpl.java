package com.trantorinc.synergy.performance.web.service;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.upload.FileItem;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.trantorinc.synergy.common.service.drive.DriveService;
import com.trantorinc.synergy.email.core.model.Email;
import com.trantorinc.synergy.email.core.service.EmailLocalServiceUtil;
import com.trantorinc.synergy.lms.core.model.Employee;
import com.trantorinc.synergy.lms.core.service.DriveLocalServiceUtil;
import com.trantorinc.synergy.lms.core.service.EmployeeLocalServiceUtil;
import com.trantorinc.synergy.performance.core.model.*;
import com.trantorinc.synergy.performance.core.service.*;
import com.trantorinc.synergy.performance.web.dto.KpiDto;
import com.trantorinc.synergy.performance.web.dto.ReviewDto;
import com.trantorinc.synergy.performance.web.dto.ReviewLineDto;
import com.trantorinc.synergy.performance.web.dto.ReviewUploadDto;
import com.trantorinc.synergy.performance.web.portlet.PerformanceWebPortlet;

import static com.trantorinc.synergy.common.service.constant.AppConstantService.*;
import static com.trantorinc.synergy.common.service.util.UtilService.convertDateToLocalDateTime;
import static com.trantorinc.synergy.performance.web.constants.PerformanceWebPortletKeys.*;


import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.ResourceRequest;
import java.io.File;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import static com.trantorinc.synergy.common.service.util.UtilService.*;

public class PerformanceServiceImpl implements PerformanceService {
    private final Log log = LogFactoryUtil.getLog(PerformanceServiceImpl.class.getName());

    @Override
    public void getFormDetails(RenderRequest request) {
        LocalDate today = getIstLocalDateTime().toLocalDate();
        List<Employee> activeEmployeeList = EmployeeLocalServiceUtil.findAllActiveEmployees();
        Employee employee = activeEmployeeList.stream().filter(s -> s.getEmail().equalsIgnoreCase(getLoggedUser(request))).collect(Collectors.toList()).get(0);
        ReviewDto reviewDto = setHeaderValues(activeEmployeeList, employee);
        int currentYear = getCurrentYear();
        LocalDate financialYearStart = getFinancialStartDate(currentYear);
        LocalDate financialYearEnd = getFinancialEndDate(currentYear);
        LocalDate financialMidYear = getMidFinancialEndDate(currentYear);
        request.setAttribute("currentFYStartDate", financialYearStart);
        request.setAttribute("currentFYEndDate", financialYearEnd);
        String dateString = BLANK;
        Map<Integer, String> reviewStateMapAnnual = new HashMap<>();
        Map<Integer, String> reviewStateMapMidYear = new HashMap<>();
        Map<Integer, String> finalYearMap = new HashMap<>();
        Map<Integer, String> midYearMap = new HashMap<>();
        Map<String, String> yearList = new HashMap<>();
        List<ReviewTimeline> reviewTimelines = ReviewTimelineLocalServiceUtil.getCalibratedTimelines();
        List<String> accountList = EmployeeLocalServiceUtil.findUniqueAccounts();
        Set<String> availAccounts = accountList.stream().map(e -> e.trim().toUpperCase()).collect(Collectors.toSet());
        availAccounts.remove(BLANK);
        availAccounts.remove(employee.getAccount().toUpperCase());
        LocalDate startDate = null;
        LocalDate nextDate = null;
        for (ReviewTimeline timeline : reviewTimelines) {
            LocalDate endDate = timeline.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (timeline.getReviewState() == 0) {
                startDate = endDate.plusDays(1);
            } else if (timeline.getReviewState() == 1) {
                dateString = startDate.format(FORMATTER_YYYY_MM_DD) + TO + endDate.format(FORMATTER_YYYY_MM_DD);
                nextDate = endDate.plusDays(1);
            } else {
                dateString = nextDate.format(FORMATTER_YYYY_MM_DD) + TO + endDate.format(FORMATTER_YYYY_MM_DD);
                nextDate = endDate.plusDays(1);
            }
            if (timeline.getReviewState() > 0) {
                if (timeline.isFinalYear()) {
                    reviewStateMapAnnual.put(timeline.getReviewState(), timeline.getStateName());
                    finalYearMap.put(timeline.getReviewState(), dateString);
                } else {
                    reviewStateMapMidYear.put(timeline.getReviewState(), timeline.getStateName());
                    midYearMap.put(timeline.getReviewState(), dateString);
                }
            }
        }
        List<Date> uniqueYear = ReviewLocalServiceUtil.findUniqueReviewYears();
        for (Date date : uniqueYear) {
            LocalDate localDate = convertDateToLocalDateTime(date).toLocalDate();
            if (!localDate.equals(financialYearStart)) {
                int nextYear = localDate.getYear() + 1;
                String displayYear = AY + localDate.getYear() + HYPHEN + nextYear;
                yearList.put(localDate.format(FORMATTER_YYYY_MM_DD), displayYear);
            }
        }


        LocalDate timelineDate;
        boolean isNewJoinee;
        LocalDate empDoj = convertDateToLocalDateTime(employee.getDoj()).toLocalDate();
        LocalDate dojAnnual = LocalDate.of(currentYear, 12, 31);
        LocalDate dojMid = LocalDate.of(currentYear, 6, 30);
        boolean annualAccount = today.isBefore(financialYearStart) || today.isAfter(financialMidYear);
          if(!annualAccount){
            timelineDate = reviewTimelines.stream().filter(s -> !s.getFinalYear()).collect(Collectors.toList()).get(1).getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            isNewJoinee = empDoj.isBefore(dojMid) || empDoj.isEqual(dojMid);
        } else {
            timelineDate = reviewTimelines.stream().filter(s -> s.getFinalYear()).collect(Collectors.toList()).get(1).getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            isNewJoinee = empDoj.isBefore(dojAnnual) || empDoj.isEqual(dojAnnual);
        }
        List<KpiDto> employeeKpiList = new ArrayList<>();
        List<Review> allReviews = ReviewLocalServiceUtil.findReviewByEcode(employee.getEcode());

        List<Kpi> empKpiList = KpiLocalServiceUtil.findKpiByEcode(employee.getEcode());
        boolean generateReviewTextDisplay = false;
        for (Kpi kpi : empKpiList) {
            List<Employee> managerDetails = activeEmployeeList.stream().filter(s -> s.getEmail().equalsIgnoreCase(kpi.getManagerEmail())).collect(Collectors.toList());
            List<Employee> reviewerDetails = activeEmployeeList.stream().filter(s -> s.getEmail().equalsIgnoreCase(kpi.getReviewerEmail())).collect(Collectors.toList());
            KpiDto dto = new KpiDto();
            dto.setKpiId(kpi.getKpiId());
            dto.setAccount(kpi.getAccount());
            dto.setManagerEmail(kpi.getManagerEmail());
            dto.setReviewerEmail(kpi.getReviewerEmail());
            dto.setManagerName(managerDetails.isEmpty() ? BLANK : managerDetails.get(0).getName());
            dto.setReviewerName(reviewerDetails.isEmpty() ? BLANK : reviewerDetails.get(0).getName());
            dto.setAccountPrimary(kpi.getAccountPrimary());
            dto.setUpdatedDate(convertDateToLocalDateTime(kpi.getUpdateDate()).format(FORMATTER_YYYY_MM_DD));
            dto.setKpiSettingStatus(kpi.getKpiSettingStatus());
            List<Review> accountReviewEntryStatus = allReviews.stream().filter(s -> (convertDateToLocalDateTime(s.getReviewStartDate()).toLocalDate().equals(financialYearStart) && s.getKpiId() == kpi.getKpiId() && s.getFinalYearReview() == annualAccount)).collect(Collectors.toList());
            boolean newPrimaryAccount = !(!startDate.isBefore(convertDateToLocalDateTime(kpi.getUpdateDate()).toLocalDate()) && !timelineDate.isAfter(convertDateToLocalDateTime(kpi.getUpdateDate()).toLocalDate()) && kpi.getAccountPrimary());

            if (accountReviewEntryStatus.isEmpty() && isNewJoinee && newPrimaryAccount && kpi.isKpiSettingStatus() && today.isAfter(startDate)  && !today.isAfter(timelineDate)) {
                dto.setGenerateReviewButtonDisplay(true);
                generateReviewTextDisplay = true;
            } else {
                dto.setGenerateReviewButtonDisplay(false);
            }
            employeeKpiList.add(dto);
        }

        List<ReviewDto> currentYearHeaderList = new ArrayList<>();
        List<Review> reviewLists = allReviews.stream().filter(s -> convertDateToLocalDateTime(s.getReviewStartDate()).toLocalDate().equals(financialYearStart)).collect(Collectors.toList());
        for (Review review : reviewLists) {
            List<Employee> managerInfo = activeEmployeeList.stream().filter(s -> s.getEmail().equalsIgnoreCase(review.getManager())).collect(Collectors.toList());
            ReviewDto dto = new ReviewDto();
            dto.setAccount(review.getAccount());
            dto.setKpiId(review.getReviewId());
            dto.setManagerEmail(review.getManager());
            dto.setManagerName(managerInfo.isEmpty() ? BLANK : managerInfo.get(0).getName());
            dto.setReviewId(review.getReviewId());
            dto.setCurrentYRReview(review.getFinalYearReview());
            dto.setCurrentAccount(review.getAccountPrimary());
            dto.setReviewState(review.getReviewState());
            if (review.isFinalYearReview()) {
                dto.setReviewStateName(reviewStateMapAnnual.get(review.getReviewState()));
            } else {
                dto.setReviewStateName(reviewStateMapMidYear.get(review.getReviewState()));
            }
            currentYearHeaderList.add(dto);
        }
        request.setAttribute("employeeKpiList", employeeKpiList);
        request.setAttribute("displayCurrentAY", displayCurrentAY(currentYear));
        request.setAttribute("midYearMap", midYearMap);
        request.setAttribute("reviewStateMapAnnual", reviewStateMapAnnual);
        request.setAttribute("reviewStateMapMidYear", reviewStateMapMidYear);
        request.setAttribute("finalYearMap", finalYearMap);
        reviewDto.setGenerateReviewButtonTextDisplay(generateReviewTextDisplay);
        request.setAttribute("empCoreDetails", reviewDto);
        request.setAttribute("currentYearEntries", currentYearHeaderList);
        request.setAttribute("yearList", yearList);
        request.setAttribute("accountList", new TreeSet<>(availAccounts));
    }

    @Override
    public void addKpiDetails(ActionRequest request, ActionResponse response) {
        List<Employee> activeEmployeeList = EmployeeLocalServiceUtil.findAllActiveEmployees();
        long kpiId = ParamUtil.getLong(request, KPI_ID);
        Kpi kpi = KpiLocalServiceUtil.fetchKpi(kpiId);
        List<KpiGuide> guides = KpiGuideLocalServiceUtil.getKpiGuides(-1, -1);
        List<KpiGuide> attributes = getAttributes(guides);
        int countAttribute = 1;
        StringBuilder attributeText = new StringBuilder();
        for (KpiGuide attribute : attributes) {
            attributeText.append(countAttribute++);
            attributeText.append(") ");
            attributeText.append(attribute.getTitle());
            attributeText.append(System.lineSeparator());
        }
        Map<Integer, KpiGuide> guideMap = new HashMap<>();
        for (KpiGuide guide : guides) {
            guideMap.put((int) guide.getGuideId(), guide);
        }

        Employee employee = activeEmployeeList.stream().filter(s -> s.getEmail().equalsIgnoreCase(getLoggedUser(request))).collect(Collectors.toList()).get(0);
        List<Employee> manager = activeEmployeeList.stream().filter(s -> s.getEmail().equalsIgnoreCase(kpi.getManagerEmail())).collect(Collectors.toList());
        List<Employee> reviewer = activeEmployeeList.stream().filter(s -> s.getEmail().equalsIgnoreCase(kpi.getReviewerEmail())).collect(Collectors.toList());
        List<KpiLine> kpiLines = KpiLineLocalServiceUtil.findKpiLineByKpiId(kpiId);
        List<ReviewLineDto> reviewLineDtos = new ArrayList<>();
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setEmpEcode(employee.getEcode());
        reviewDto.setEmpName(employee.getName());
        reviewDto.setEmpDesignation(employee.getDesignation());
        reviewDto.setAccount(kpi.getAccount());
        reviewDto.setEmpMobileNo(employee.getMobile());
        reviewDto.setEmpBand(employee.getBand());
        reviewDto.setManagerName(manager.isEmpty() ? BLANK : manager.get(0).getName());
        reviewDto.setManagerEmail(kpi.getManagerEmail());
        reviewDto.setReviewerEmail(kpi.getReviewerEmail());
        reviewDto.setReviewerName(reviewer.isEmpty() ? BLANK : reviewer.get(0).getName());
        if (!kpi.getKpiSettingStatus()) {
            for (int x = 0; x < guideMap.size(); x++) {
                KpiGuide guide = guideMap.get(x);
                if (!guide.isOther() && !guide.isAttribute()) {
                    ReviewLineDto lineDto = new ReviewLineDto();
                    lineDto.setGuideId(guide.getGuideId());
                    lineDto.setAttribute(guide.isAttribute());
                    lineDto.setOther(guide.isOther());
                    lineDto.setMandatory(guide.getMandatory());
                    lineDto.setReviewLineTitle(guide.getTitle());
                    lineDto.setReviewLineDescription(guide.getDescription());
                    if (!kpiLines.isEmpty()) {
                        List<KpiLine> list = kpiLines.stream().filter(c -> c.getGuideId() == guide.getGuideId()).collect(Collectors.toList());
                        if (!list.isEmpty()) {
                            lineDto.setReviewLineTarget(list.get(0).getTarget());
                            lineDto.setSelected(true);
                        } else {
                            lineDto.setSelected(false);
                        }
                    }
                    reviewLineDtos.add(lineDto);
                }
            }
            if (!kpiLines.isEmpty()) {
                KpiGuide guide = guideMap.get(GUIDEID_ZERO);
                List<ReviewLineDto> dtoList = new ArrayList<>();
                for (KpiLine kpiLine : kpiLines) {
                    if (kpiLine.getGuideId() == GUIDEID_ZERO) {
                        ReviewLineDto lineDto = new ReviewLineDto();
                        lineDto.setGuideId(kpiLine.getGuideId());
                        lineDto.setAttribute(guide.isAttribute());
                        lineDto.setOther(guide.isOther());
                        lineDto.setMandatory(guide.getMandatory());
                        lineDto.setReviewLineTitle(kpiLine.getTitle());
                        lineDto.setReviewLineDescription(kpiLine.getDescription());
                        lineDto.setReviewLineTarget(kpiLine.getTarget());
                        dtoList.add(lineDto);
                    }
                }
                reviewDto.setOtherReviewLines(dtoList);
            }
            reviewDto.setReviewState(1);
        } else {
            reviewDto.setReviewState(2);
            for (KpiLine line : kpiLines) {
                ReviewLineDto lineDto = new ReviewLineDto();
                lineDto.setReviewLineId(line.getLineId());
                lineDto.setGuideId(line.getGuideId());
                KpiGuide guide = guideMap.get((int) line.getGuideId());
                if (guide.isOther()) {
                    lineDto.setReviewLineTitle(line.getTitle());
                    lineDto.setReviewLineDescription(line.getDescription());
                    lineDto.setReviewLineTarget(line.getTarget());
                } else {
                    lineDto.setReviewLineTitle(guide.getTitle());
                    if (!guide.isAttribute()) {
                        lineDto.setReviewLineDescription(guide.getDescription());
                        lineDto.setReviewLineTarget(line.getTarget());
                    }
                }
                lineDto.setAttribute(guide.isAttribute());
                lineDto.setOther(guide.isOther());
                lineDto.setMandatory(guide.getMandatory());
                reviewLineDtos.add(lineDto);
            }
        }
        reviewDto.setReviewId(kpiId);
        reviewDto.setKpiId(kpiId);
        reviewDto.setAttributeText(attributeText.toString());
        reviewDto.setLineDtoList(reviewLineDtos);
        reviewDto.setComment(kpi.getRejectionComment());
        request.setAttribute("reviewDetails", reviewDto);
        response.getRenderParameters().setValue("mvcPath", "/jsp/kpi.jsp");
    }

    @Override
    public void addNewAccount(ActionRequest request) {
        Employee employee = EmployeeLocalServiceUtil.findByEmail(getLoggedUser(request));
        String kpiId = ParamUtil.getString(request, KPI_ID);
        String accountName = ParamUtil.getString(request, "account");
        String managerEmail = ParamUtil.getString(request, "managerEmail");
        String reviewerEmail = ParamUtil.getString(request, "reviewerEmail");
        int currentYear = getCurrentYear();
        LocalDate financialYearStart = getFinancialStartDate(currentYear);
        LocalDate financialMidYear = getMidFinancialEndDate(currentYear);
        LocalDate today = getIstLocalDateTime().toLocalDate();
        boolean accountAnnual = today.isBefore(financialYearStart) || today.isAfter(financialMidYear);
        if (null != kpiId && !kpiId.isEmpty()) {
            Kpi kpi = KpiLocalServiceUtil.fetchKpi(Long.parseLong(kpiId));
            kpi.setAccount(accountName);
            kpi.setManagerEmail(managerEmail);
            kpi.setReviewerEmail(reviewerEmail);
            kpi.setUpdateDate(getIstDate());
            kpi.setRejectionComment(BLANK);
            KpiLocalServiceUtil.updateKpi(kpi);
            List<Review> allReviews = ReviewLocalServiceUtil.findReviewByEcode(employee.getEcode());
            List<Review> accountReviewEntryStatus = allReviews.stream().filter(s -> (convertDateToLocalDateTime(s.getReviewStartDate()).toLocalDate().equals(financialYearStart) && s.getKpiId() == kpi.getKpiId() && s.getFinalYearReview() == accountAnnual)).collect(Collectors.toList());

            if (!accountReviewEntryStatus.isEmpty()) {
                for (Review review : accountReviewEntryStatus) {
                    Review employeeReview = ReviewLocalServiceUtil.fetchReview(review.getReviewId());
                    employeeReview.setManager(managerEmail);
                    employeeReview.setAssignee(reviewerEmail);
                    employeeReview.setAssigned(!managerEmail.equalsIgnoreCase(reviewerEmail));
                    ReviewLocalServiceUtil.updateReview(employeeReview);
                }
            }
        } else {
            Kpi kpiHeaders = KpiLocalServiceUtil.createKpi(CounterLocalServiceUtil.increment());
            kpiHeaders.setEcode(employee.getEcode());
            kpiHeaders.setAccount(accountName);
            kpiHeaders.setAccountPrimary(false);
            kpiHeaders.setManagerEmail(managerEmail);
            kpiHeaders.setReviewerEmail(reviewerEmail);
            kpiHeaders.setUpdateDate(getIstDate());
            KpiLocalServiceUtil.addKpi(kpiHeaders);
        }
    }

    @Override
    public void deleteReview(ActionRequest request) throws PortalException {
        long reviewId = ParamUtil.getLong(request, "reviewId");
        ReviewLocalServiceUtil.deleteReview(reviewId);
        List<ReviewLine> lineByReviewId = ReviewLineLocalServiceUtil.findReviewLineByReviewId(reviewId);
        List<ReviewRollback> reviewRollbacks = ReviewRollbackLocalServiceUtil.findReviewRollbackByReviewId(reviewId);
        List<ReviewClose> reviewCloseByReviewId = ReviewCloseLocalServiceUtil.findReviewCloseByReviewId(reviewId);
        if (!lineByReviewId.isEmpty()) {
            for (ReviewLine line : lineByReviewId) {
                ReviewLineLocalServiceUtil.deleteReviewLine(line);
            }
        }
        if (!reviewRollbacks.isEmpty()) {
            for (ReviewRollback rollback : reviewRollbacks) {
                ReviewRollbackLocalServiceUtil.deleteReviewRollback(rollback);
            }
        }
        if (!reviewCloseByReviewId.isEmpty()) {
            for (ReviewClose close : reviewCloseByReviewId) {
                ReviewCloseLocalServiceUtil.deleteReviewClose(close);
            }
        }
    }

    @Override
    public void deleteAccount(ActionRequest request) throws PortalException {
        long kpiId= ParamUtil.getLong(request, "deleteId");
        KpiLocalServiceUtil.deleteKpi(kpiId);
        List<KpiLine> lineByKpiId = KpiLineLocalServiceUtil.findKpiLineByKpiId(kpiId);
        for(KpiLine line:lineByKpiId){
            KpiLineLocalServiceUtil.deleteKpiLine(line);
        }
    }

    @Override
    public void selfReviewSubmit(ActionRequest request) {
        long reviewId = ParamUtil.getLong(request, "reviewId");
        Review review = ReviewLocalServiceUtil.fetchReview(reviewId);
        List<ReviewLine> reviewLines = ReviewLineLocalServiceUtil.findReviewLineByReviewId(reviewId);
        for (int x = 0; x < reviewLines.size(); x++) {
            long reviewLineId = ParamUtil.getLong(request, "reviewId" + x);
            ReviewLine selfReview = ReviewLineLocalServiceUtil.fetchReviewLine(reviewLineId);
            String empRating = ParamUtil.getString(request, "selfRating" + x);
            String empComments = ParamUtil.getString(request, "selfComments" + x);
            selfReview.setEmployeeRating(empRating);
            selfReview.setEmployeeComment(empComments);
            ReviewLineLocalServiceUtil.updateReviewLine(selfReview);
        }
        String empComment = ParamUtil.getString(request, "empComments");
        String achievementsByEmp = ParamUtil.getString(request, "achievementsByEmpComment");
        review.setReviewState(2);
        review.setStage1Date(getIstDate());
        review.setAchievements(achievementsByEmp);
        review.setEmployeeComment(empComment);
        ReviewLocalServiceUtil.updateReview(review);
        Employee empData = EmployeeLocalServiceUtil.findByEmail(getLoggedUser(request));
        String managerEmail = review.getManager().equalsIgnoreCase(review.getAssignee()) ? review.getManager() : review.getManager() + COMMA + review.getAssignee();
        Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
        email.setSubject(MessageFormat.format(SUBJECT_SELF_REVIEW, empData.getName()));
        email.setBody(MessageFormat.format(BODY_SELF_REVIEW, empData.getName(), getPortalUrl() + URL_PERFORMANCE_ADMIN));
        email.setToAddress(managerEmail);
        email.setCcAddress(empData.getEmail());
        email.setModule(MODULE_PERFORMANCE);
        email.setCreatedDate(getIstDate());
        email.setScheduled(false);
        email.setSent(false);
        EmailLocalServiceUtil.addEmail(email);
    }

    @Override
    public void updateKpiDetails(ActionRequest request, ActionResponse response) {
        String kpiId = ParamUtil.getString(request, KPI_ID);
        Kpi kpi = KpiLocalServiceUtil.fetchKpi(Long.parseLong(kpiId));
        kpi.setKpiSettingStatus(false);
        kpi.setRejectionComment(SYSTEM_UPDATE);
        KpiLocalServiceUtil.updateKpi(kpi);
        addKpiDetails(request, response);
    }

    @Override
    public void reviewWorkflow(ActionRequest request, ActionResponse response) {
        LocalDate todayDate = getIstLocalDateTime().toLocalDate();
        List<Employee> activeEmployeeList = EmployeeLocalServiceUtil.findAllActiveEmployees();
        long reviewId = ParamUtil.getLong(request, "reviewId");
        Review review = ReviewLocalServiceUtil.fetchReview(reviewId);
        Employee employee = activeEmployeeList.stream().filter(s -> s.getEmail().equalsIgnoreCase(getLoggedUser(request))).collect(Collectors.toList()).get(0);
        ReviewDto reviewDto = new ReviewDto();
        List<Employee> managerData = activeEmployeeList.stream().filter(s -> s.getEmail().equalsIgnoreCase(review.getManager())).collect(Collectors.toList());
        List<Employee> reviewerData = activeEmployeeList.stream().filter(s -> s.getEmail().equalsIgnoreCase(review.getAssignee())).collect(Collectors.toList());
        reviewDto.setEmpEcode(employee.getEcode());
        reviewDto.setEmpName(employee.getName());
        reviewDto.setEmpDesignation(employee.getDesignation());
        reviewDto.setAccount(review.getAccount());
        reviewDto.setEmpMobileNo(employee.getMobile());
        reviewDto.setEmpBand(employee.getBand());
        reviewDto.setManagerName(managerData.isEmpty() ? BLANK : managerData.get(0).getName());
        reviewDto.setManagerEmail(review.getManager());
        reviewDto.setReviewerEmail(review.getAssignee());
        reviewDto.setReviewerName(reviewerData.isEmpty() ? BLANK : reviewerData.get(0).getName());
        List<ReviewTimeline> timelines = ReviewTimelineLocalServiceUtil.getCalibratedTimelines();
        List<KpiGuide> guides = KpiGuideLocalServiceUtil.getKpiGuides(-1, -1);
        List<KpiGuide> attributes = getAttributes(guides);
        int countAttribute = 1;
        StringBuilder attributeText = new StringBuilder();
        for (KpiGuide attribute : attributes) {
            attributeText.append(countAttribute++);
            attributeText.append(") ");
            attributeText.append(attribute.getTitle());
            attributeText.append(System.lineSeparator());
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
        List<ReviewLine> reviewLines = ReviewLineLocalServiceUtil.findReviewLineByReviewId(reviewId);
        List<ReviewLineDto> reviewLineDtos = new ArrayList<>();
        for (ReviewLine reviewLine : reviewLines) {
            ReviewLineDto lineDto = new ReviewLineDto();
            lineDto.setReviewLineId(reviewLine.getLineId());
            lineDto.setGuideId(reviewLine.getGuideId());
            KpiGuide guide = guides.get((int) reviewLine.getGuideId());
            if (guide.isOther()) {
                lineDto.setReviewLineTitle(reviewLine.getTitle());
                lineDto.setReviewLineDescription(reviewLine.getDescription());
                lineDto.setReviewLineTarget(reviewLine.getTarget());
            } else {
                lineDto.setReviewLineTitle(guide.getTitle());
                if (!guide.isAttribute()) {
                    lineDto.setReviewLineDescription(guide.getDescription());
                    lineDto.setReviewLineTarget(reviewLine.getTarget());
                }

            }
            lineDto.setEmployeeRating(ratingMap.get(reviewLine.getEmployeeRating()));
            lineDto.setEmployeeComment(reviewLine.getEmployeeComment());
            lineDto.setManagerRating(ratingMap.get(reviewLine.getManagerRating()));
            lineDto.setManagerComment(reviewLine.getManagerComment());
            lineDto.setHrRating(ratingMap.get(reviewLine.getHrRating()));
            lineDto.setHrComment(reviewLine.getHrComment());
            lineDto.setAttribute(guide.isAttribute());
            lineDto.setOther(guide.isOther());
            lineDto.setMandatory(guide.getMandatory());
            reviewLineDtos.add(lineDto);
        }
        boolean isKpiExisted = false;
        if (null != Long.valueOf(review.getKpiId())) {
            Kpi kpiExistingStatus = KpiLocalServiceUtil.fetchKpi(review.getKpiId());
            if (kpiExistingStatus != null) {
                reviewDto.setKpiId(review.getKpiId());
                isKpiExisted = true;
            }
        }
        reviewDto.setKpiExistingStatus(isKpiExisted);
        reviewDto.setKpiId(review.getKpiId());
        reviewDto.setCurrentAccount(review.getAccountPrimary());
        reviewDto.setCurrentYRReview(review.getFinalYearReview());
        reviewDto.setLineDtoList(reviewLineDtos);
        reviewDto.setAttributeText(attributeText.toString());
        reviewDto.setReviewId(reviewId);
        reviewDto.setRating(getEmployeeRatingText(review.getRating()));
        reviewDto.setRaiseMeetingButtonDisplay(review.getReviewState() == 5 && !todayDate.isBefore(convertDateToLocalDateTime(review.getStage2Date()).toLocalDate().minusDays(5)) && todayDate.isBefore(convertDateToLocalDateTime(review.getStage2Date()).toLocalDate().minusDays(5)));
        reviewDto.setReviewState(review.getReviewState());
        reviewDto.setOverallManagerComment(review.getManagerComment());
        reviewDto.setOverallHrComment(review.getHrComment());
        reviewDto.setOverallEmployeeComment(review.getEmployeeComment());
        reviewDto.setAchievementsByEmp(review.getAchievements());
        reviewDto.setReviewStateName(reviewStateMap.get(review.getReviewState()));
        reviewDto.setImprovementComment(review.getImprovementComment());
        reviewDto.setTrainingsNeeded(review.getTrainings());
        reviewDto.setRaiseToHrComments(review.getComment());
        List<ReviewComment> auditCmmts = new ArrayList<>(ReviewCommentLocalServiceUtil.findReviewCommentByReviewId(reviewId));
        StringBuilder rejectionComment = new StringBuilder();
        if(!auditCmmts.isEmpty()){
            auditCmmts.sort((ReviewComment ac1, ReviewComment ac2)-> ac2.getCreatedDate().compareTo(ac1.getCreatedDate()));
            rejectionComment.append("====== History ======");
            if(review.getReviewState() > 1){
                rejectionComment.append("&#013;");
            }
            for(ReviewComment auditComment : auditCmmts){
                if(review.getReviewState() > 1){
                    rejectionComment.append("&#013;");
                } else {
                    rejectionComment.append("\n\n");
                }
                rejectionComment.append(convertDateToLocalDateTime(auditComment.getCreatedDate()).format(FORMATTER_YYYY_MM_DD));
                rejectionComment.append(" - ");
                rejectionComment.append(auditComment.getDescription());
            }
        }
        reviewDto.setComment(rejectionComment.toString());
        List<ReviewUpload> upload = ReviewUploadLocalServiceUtil.findReviewUploadByReviewId(reviewId);
        List<ReviewUploadDto> dtoList = new ArrayList<>();
        for (ReviewUpload files : upload) {
            ReviewUploadDto uploaddto = new ReviewUploadDto();
            uploaddto.setFileName(files.getAttachmentName());
            uploaddto.setFileId(files.getFileId());
            uploaddto.setUploadId(files.getUploadId());
            dtoList.add(uploaddto);
        }
        reviewDto.setUploadsAttachments(dtoList);
        request.setAttribute("progressMap", getProgressMap(review));
        request.setAttribute("reviewStateMap", reviewStateMap);
        request.setAttribute("reviewDetails", reviewDto);
        response.getRenderParameters().setValue("mvcPath", "/jsp/reviewLine.jsp");
    }

    private String getEmployeeRatingText(String rating) {
        String employeRating = BLANK;
        if (!rating.trim().equalsIgnoreCase(BLANK)) {
            Double numericRating = Double.parseDouble(rating);
            if (numericRating >= 4.5) {
                employeRating = "Consistently Exceeds Expectation";
            } else if (numericRating >= 3.5) {
                employeRating = "Sometimes Exceeds Expectation";
            } else if (numericRating >= 3) {
                employeRating = "Consistently Meets Expectation";
            } else if (numericRating >= 1.5) {
                employeRating = "Inconsistently Meets Expectation";
            } else {
                employeRating = "Does Not Meet Expectation";
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

    @Override
    public void submitKpiForm(ActionRequest request) throws PortalException {
        LocalDate today = getIstLocalDateTime().toLocalDate();
        int currentYear = getCurrentYear();
        LocalDate financialYearStart = getFinancialStartDate(currentYear);
        LocalDate financialMidYear = getMidFinancialEndDate(currentYear);
        int numOfOtherReviewLines = ParamUtil.getInteger(request, "numOfOtherReviewLines");
        String[] selectedGuides = ParamUtil.getParameterValues(request, "checkbox", null);
        long kpiId = ParamUtil.getLong(request, "reviewId");
        boolean accountAnnual = today.isBefore(financialYearStart) || today.isAfter(financialMidYear);
        List<Review> allReviewAccountList = ReviewLocalServiceUtil.findReviewByKpiId(kpiId);
        List<Review> accountReviewEntryStatus = allReviewAccountList.stream().filter(s -> (convertDateToLocalDateTime(s.getReviewStartDate()).toLocalDate().equals(financialYearStart) && s.getReviewState() == 1 && s.getFinalYearReview() == accountAnnual)).collect(Collectors.toList());
        long reviewId = 0;
        List<ReviewLine> reviewList = null;
        if (!accountReviewEntryStatus.isEmpty()) {
            Review reviewHeaders = ReviewLocalServiceUtil.fetchReview(accountReviewEntryStatus.get(0).getReviewId());
            reviewId = reviewHeaders.getReviewId();
            if (!reviewHeaders.isFinalYearReview()) {
                reviewHeaders.setReviewState(2);
                reviewHeaders.setStage1Date(getIstDate());
            }
            ReviewLocalServiceUtil.updateReview(reviewHeaders);
            reviewList = ReviewLineLocalServiceUtil.findReviewLineByReviewId(reviewId);
            if (null != reviewList) {
                for (ReviewLine line : reviewList) {
                    ReviewLineLocalServiceUtil.deleteReviewLine(line.getLineId());
                }
            }
        }
        List<KpiGuide> guides = KpiGuideLocalServiceUtil.getKpiGuides(-1, -1);
        List<KpiLine> kpiLines = KpiLineLocalServiceUtil.findKpiLineByKpiId(kpiId);
        if (!kpiLines.isEmpty()) {
            for (KpiLine line : kpiLines) {
                KpiLineLocalServiceUtil.deleteKpiLine(line.getLineId());
            }
        }

        for (String guideId : selectedGuides) {
            KpiLine line = KpiLineLocalServiceUtil.createKpiLine(CounterLocalServiceUtil.increment());
            line.setKpiId(kpiId);
            line.setGuideId(Integer.parseInt(guideId));
            String objTarget = ParamUtil.getString(request, "objTarget" + guideId);
            line.setTarget(objTarget);
            line.setCreateDate(getIstDate());
            KpiLineLocalServiceUtil.addKpiLine(line);
            if (!accountReviewEntryStatus.isEmpty()) {
                ReviewLine review = ReviewLineLocalServiceUtil.createReviewLine(CounterLocalServiceUtil.increment());
                review.setReviewId(reviewId);
                review.setGuideId(Integer.parseInt(guideId));
                review.setTarget(objTarget);
                List<ReviewLine> preReviewLineList = reviewList.stream().filter(s -> s.getGuideId() == Integer.parseInt(guideId)).collect(Collectors.toList());
                if (!preReviewLineList.isEmpty()) {
                    review.setEmployeeComment(preReviewLineList.get(0).getEmployeeComment());
                    review.setEmployeeRating(preReviewLineList.get(0).getEmployeeRating());
                    review.setManagerRating(preReviewLineList.get(0).getManagerRating());
                    review.setManagerComment(preReviewLineList.get(0).getManagerComment());
                    review.setHrComment(preReviewLineList.get(0).getHrComment());
                    review.setHrRating(preReviewLineList.get(0).getHrRating());
                }
                ReviewLineLocalServiceUtil.addReviewLine(review);
            }
        }
        for (int x = 1; x <= numOfOtherReviewLines; x++) {
            String otherTitle = ParamUtil.getString(request, "otherTitle" + x);
            String otherDescription = ParamUtil.getString(request, "otherDescription" + x);
            String otherTarget = ParamUtil.getString(request, "otherTarget" + x);
            if (otherTitle != null && !otherTitle.equalsIgnoreCase("")) {
                KpiLine line = KpiLineLocalServiceUtil.createKpiLine(CounterLocalServiceUtil.increment());
                line.setKpiId(kpiId);
                line.setGuideId(GUIDEID_ZERO);
                line.setTarget(otherTarget);
                line.setTitle(otherTitle);
                line.setDescription(otherDescription);
                line.setCreateDate(getIstDate());
                KpiLineLocalServiceUtil.addKpiLine(line);
                if (!accountReviewEntryStatus.isEmpty()) {
                    ReviewLine reviewLine = ReviewLineLocalServiceUtil.createReviewLine(CounterLocalServiceUtil.increment());
                    reviewLine.setReviewId(reviewId);
                    reviewLine.setTitle(otherTitle);
                    reviewLine.setGuideId(GUIDEID_ZERO);
                    reviewLine.setTarget(otherTarget);
                    reviewLine.setDescription(otherDescription);
                    List<ReviewLine> preLineList = reviewList.stream().filter(s -> s.getGuideId() == GUIDEID_ZERO).filter(s -> s.getTitle().equalsIgnoreCase(otherTitle)).collect(Collectors.toList());
                    if (!preLineList.isEmpty()) {
                        reviewLine.setEmployeeComment(preLineList.get(0).getEmployeeComment());
                        reviewLine.setEmployeeRating(preLineList.get(0).getEmployeeRating());
                        reviewLine.setManagerRating(preLineList.get(0).getManagerRating());
                        reviewLine.setManagerComment(preLineList.get(0).getManagerComment());
                        reviewLine.setHrComment(preLineList.get(0).getHrComment());
                        reviewLine.setHrRating(preLineList.get(0).getHrRating());
                    }
                    ReviewLineLocalServiceUtil.addReviewLine(reviewLine);
                }
            }
        }
        List<KpiGuide> attributeList = getAttributes(guides);
        for (KpiGuide guide : attributeList) {
            KpiLine line = KpiLineLocalServiceUtil.createKpiLine(CounterLocalServiceUtil.increment());
            line.setKpiId(kpiId);
            line.setGuideId(guide.getGuideId());
            line.setTitle(guide.getTitle());
            line.setCreateDate(getIstDate());
            KpiLineLocalServiceUtil.addKpiLine(line);
            if (!accountReviewEntryStatus.isEmpty()) {
                ReviewLine reviewLine = ReviewLineLocalServiceUtil.createReviewLine(CounterLocalServiceUtil.increment());
                reviewLine.setReviewId(reviewId);
                reviewLine.setTitle(guide.getTitle());
                reviewLine.setGuideId(guide.getGuideId());
                List<ReviewLine> preLineList = reviewList.stream().filter(s -> s.getGuideId() == guide.getGuideId()).collect(Collectors.toList());
                if (!preLineList.isEmpty()) {
                    reviewLine.setEmployeeComment(preLineList.get(0).getEmployeeComment());
                    reviewLine.setEmployeeRating(preLineList.get(0).getEmployeeRating());
                    reviewLine.setManagerRating(preLineList.get(0).getManagerRating());
                    reviewLine.setManagerComment(preLineList.get(0).getManagerComment());
                    reviewLine.setHrComment(preLineList.get(0).getHrComment());
                    reviewLine.setHrRating(preLineList.get(0).getHrRating());
                }
                ReviewLineLocalServiceUtil.addReviewLine(reviewLine);
            }
        }
        Kpi kpiHeaders = KpiLocalServiceUtil.fetchKpi(kpiId);
        kpiHeaders.setKpiSettingStatus(true);
        kpiHeaders.setRejectionComment(BLANK);
        KpiLocalServiceUtil.updateKpi(kpiHeaders);
        String managerEmail = kpiHeaders.getManagerEmail().equalsIgnoreCase(kpiHeaders.getReviewerEmail()) ? kpiHeaders.getManagerEmail() : kpiHeaders.getManagerEmail() + COMMA + kpiHeaders.getReviewerEmail();
        Employee employee = EmployeeLocalServiceUtil.findByEmail(getLoggedUser(request));
        Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
        email.setSubject(SUBJECT_SUBMIT_KPIS);
        email.setBody(MessageFormat.format(BODY_SUBMIT_KPIS, employee.getName(), kpiHeaders.getAccount()));
        email.setToAddress(managerEmail);
        email.setCcAddress(employee.getEmail());
        email.setCreatedDate(getIstDate());
        email.setScheduled(false);
        email.setModule(MODULE_PERFORMANCE);
        email.setSent(false);
        EmailLocalServiceUtil.addEmail(email);
    }

    @Override
    public void generateReview(ActionRequest request) {
        long kpiId = ParamUtil.getLong(request, KPI_ID);
        Kpi kpiInfo = KpiLocalServiceUtil.fetchKpi(kpiId);
        LocalDate today = getIstLocalDateTime().toLocalDate();
        int currentYear = getCurrentYear();
        LocalDate financialYearStart = getFinancialStartDate(currentYear);
        LocalDate financialMidYear = getMidFinancialEndDate(currentYear);
        Review review = ReviewLocalServiceUtil.createReview(CounterLocalServiceUtil.increment());
        review.setKpiId(kpiInfo.getKpiId());
        review.setEcode(kpiInfo.getEcode());
        review.setAccount(kpiInfo.getAccount());
        review.setManager(kpiInfo.getManagerEmail());
        review.setAssignee(kpiInfo.getReviewerEmail());
        review.setAssigned(!kpiInfo.getManagerEmail().equalsIgnoreCase(kpiInfo.getReviewerEmail()));
        review.setReviewStartDate(getStartOfDayDate(financialYearStart));
        if (today.isBefore(financialYearStart) || today.isAfter(financialMidYear)) {
            review.setReviewState(1);
            review.setFinalYearReview(true);
        } else {
            review.setReviewState(2);
            review.setStage1Date(getIstDate());
            review.setFinalYearReview(false);
        }
        review.setAccountPrimary(kpiInfo.getAccountPrimary());
        ReviewLocalServiceUtil.addReview(review);
        List<KpiLine> kpiLineInfo = KpiLineLocalServiceUtil.findKpiLineByKpiId(kpiInfo.getKpiId());
        for (KpiLine info : kpiLineInfo) {
            ReviewLine reviewLine = ReviewLineLocalServiceUtil.createReviewLine(CounterLocalServiceUtil.increment());
            reviewLine.setReviewId(review.getReviewId());
            reviewLine.setGuideId(info.getGuideId());
            reviewLine.setTitle(info.getTitle());
            reviewLine.setDescription(info.getDescription());
            reviewLine.setTarget(info.getTarget());
            ReviewLineLocalServiceUtil.addReviewLine(reviewLine);
        }
        if (!review.isFinalYearReview()) {
            String managerEmail = review.getManager().equalsIgnoreCase(review.getAssignee()) ? review.getManager() : review.getManager() + COMMA + review.getAssignee();
            Employee employee = EmployeeLocalServiceUtil.findByEmail(getLoggedUser(request));
            Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
            email.setSubject(MessageFormat.format(SUBJECT_MIDYEAR_SELF_REVIEW, employee.getName()));
            email.setBody(MessageFormat.format(BODY_MIDYEAR_SELF_REVIEW, employee.getName(), getPortalUrl() + URL_PERFORMANCE_ADMIN));
            email.setToAddress(managerEmail);
            email.setCcAddress(employee.getEmail());
            email.setModule(MODULE_PERFORMANCE);
            email.setCreatedDate(getIstDate());
            email.setScheduled(false);
            email.setSent(false);
            EmailLocalServiceUtil.addEmail(email);
        }
    }

    @Override
    public void raiseMeetingToManager(ActionRequest request) {
        long reviewId = ParamUtil.getLong(request, "reviewId");
        Review review = ReviewLocalServiceUtil.fetchReview(reviewId);
        review.setReviewState(2);
        review.setStage2Date(null);
        ReviewLocalServiceUtil.updateReview(review);
        Employee empData = EmployeeLocalServiceUtil.findByEmail(getLoggedUser(request));
        String managerEmail = review.getManager().equalsIgnoreCase(review.getAssignee()) ? review.getManager() : review.getManager() + COMMA + review.getAssignee();
        Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
        email.setBody(MessageFormat.format(BODY_RAISE_REQUEST_MEETING_TO_MANAGER, empData.getName(), empData.getEcode(), review.getAccount(), getPortalUrl() + URL_PERFORMANCE_ADMIN));
        email.setSubject(SUBJECT_RAISE_REQUEST_MEETING_TO_MANAGER);
        email.setToAddress(empData.getEmail());
        email.setCcAddress(managerEmail);
        email.setModule(MODULE_PERFORMANCE);
        email.setCreatedDate(getIstDate());
        email.setScheduled(false);
        email.setSent(false);
        EmailLocalServiceUtil.addEmail(email);
    }

    @Override
    public void acceptManagerRating(ActionRequest request) {
        long reviewId = ParamUtil.getLong(request, "reviewId");
        Review review = ReviewLocalServiceUtil.fetchReview(reviewId);
        review.setReviewState(4);
        review.setStage3Date(getIstDate());
        review.setHrComment(review.getManagerComment());
        ReviewLocalServiceUtil.updateReview(review);
    }

    @Override
    public void rejectManagerRating(ActionRequest request) {
        long reviewId = ParamUtil.getLong(request, "reviewId");
        String raiseToHrComments = ParamUtil.getString(request, "raiseToHrComments");
        Review review = ReviewLocalServiceUtil.fetchReview(reviewId);
        review.setReviewState(4);
        review.setStage3Date(getIstDate());
        review.setComment(raiseToHrComments);
        ReviewLocalServiceUtil.updateReview(review);
        Employee employeeData = EmployeeLocalServiceUtil.fetchEmployee(review.getEcode());
        Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
        if(review.isFinalYearReview()){
            email.setSubject(MessageFormat.format(SUBJECT_RAISETOHR,ANNUAL,employeeData.getName(),employeeData.getEcode(),YEAR_END_SMALL));
            email.setBody(MessageFormat.format(BODY_RAISETOHR,employeeData.getName(),employeeData.getEcode(),employeeData.getProject(),YEAR_END_SMALL,raiseToHrComments,getPortalUrl()+URL_PERFORMANCE_ADMIN));
        } else {
            email.setSubject(MessageFormat.format(SUBJECT_RAISETOHR,MID_YEAR,employeeData.getName(),employeeData.getEcode(),MID_YEAR_SMALL));
            email.setBody(MessageFormat.format(BODY_RAISETOHR,employeeData.getName(),employeeData.getEcode(),employeeData.getProject(),MID_YEAR_SMALL,raiseToHrComments,getPortalUrl()+URL_PERFORMANCE_ADMIN));
        }
        email.setToAddress(DL_HR);
        email.setCcAddress(employeeData.getEmail());
        email.setCreatedDate(getIstDate());
        email.setSent(false);
        email.setScheduled(false);
        email.setModule(MODULE_PERFORMANCE);
        EmailLocalServiceUtil.addEmail(email);
    }

    @Override
    public String getManagerName(ResourceRequest resourceRequest) {
        String managerDetails = null;
        String managerEmail = ParamUtil.getString(resourceRequest, "mEmail");
        Employee managerData = EmployeeLocalServiceUtil.findByEmail(managerEmail);
        User liferayUser = UserLocalServiceUtil.fetchUserByEmailAddress(COMPANY_ID, managerEmail);
        String[] roles = {ROLE_MANAGER};
        try {
            if (RoleLocalServiceUtil.hasUserRoles(liferayUser.getUserId(), liferayUser.getCompanyId(), roles, false) && null != managerData) {
                managerDetails = convertToJson(managerData);
            }
        } catch (PortalException exception) {
            log.error(exception.getStackTrace()[0].getMethodName() + ":" + exception.getStackTrace()[0].getLineNumber() + ":" + exception.getMessage());
        }
        return managerDetails;
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
    public String uploadAttachments(ResourceRequest resourceRequest) {
        long reviewId = ParamUtil.getLong(resourceRequest, "reviewId");
        UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
        FileItem[] uploadedFiles = uploadRequest.getMultipartParameterMap().get("myFiles");
        String folderId = DriveLocalServiceUtil.findFolderIdByFolderName(MODULE_PERFORMANCE);
        for (FileItem fileItem : uploadedFiles) {
            File file = fileItem.getStoreLocation();
            String fileExtension = file.getName().substring(file.getName().lastIndexOf('.') + 1);
            if (!(fileExtension.isEmpty() || fileExtension.equalsIgnoreCase(BLANK))) {
                ReviewUpload uploadedFile = ReviewUploadLocalServiceUtil.createReviewUpload(CounterLocalServiceUtil.increment());
                uploadedFile.setReviewId(reviewId);
                uploadedFile.setAttachmentName(fileItem.getFileName());
                uploadedFile.setFileId(BLANK);
                uploadedFile.setCreatedDate(getIstDate());
                ReviewUploadLocalServiceUtil.addReviewUpload(uploadedFile);
                String fileId = DriveService.uploadFile(folderId, uploadedFile.getUploadId() + "." + fileExtension, fileItem.getStoreLocation());
                uploadedFile.setFileId(fileId);
                ReviewUploadLocalServiceUtil.updateReviewUpload(uploadedFile);
            }
        }
        List<ReviewUpload> upload = ReviewUploadLocalServiceUtil.findReviewUploadByReviewId(reviewId);
        List<ReviewUploadDto> dtoList = new ArrayList<>();
        ReviewDto reviewDto = new ReviewDto();
        for (ReviewUpload files : upload) {
            ReviewUploadDto uploaddto = new ReviewUploadDto();
            uploaddto.setFileName(files.getAttachmentName());
            uploaddto.setFileId(files.getFileId());
            uploaddto.setUploadId(files.getUploadId());
            dtoList.add(uploaddto);
        }
        reviewDto.setUploadsAttachments(dtoList);

        return convertToJson(reviewDto);
    }

    @Override
    public void deleteAttachments(ResourceRequest resourceRequest) {
        String uploadId = ParamUtil.getString(resourceRequest, "uploadId");
        ReviewUpload upload = ReviewUploadLocalServiceUtil.fetchReviewUpload(Long.parseLong(uploadId));
        if (null != upload.getFileId()) {
            DriveService.deleteFile(upload.getFileId());
        }
        ReviewUploadLocalServiceUtil.deleteReviewUpload(upload);
    }

    @Override
    public void saveFormData(ResourceRequest resourceRequest) {
        long reviewId = ParamUtil.getLong(resourceRequest, "reviewId");
        Review review = ReviewLocalServiceUtil.fetchReview(reviewId);
        List<ReviewLine> reviewLines = ReviewLineLocalServiceUtil.findReviewLineByReviewId(reviewId);
        for (int x = 0; x < reviewLines.size(); x++) {
            long reviewLineId = ParamUtil.getLong(resourceRequest, "reviewId" + x);
            ReviewLine selfReview = ReviewLineLocalServiceUtil.fetchReviewLine(reviewLineId);
            String empRating = ParamUtil.getString(resourceRequest, "selfRating" + x);
            String empComments = ParamUtil.getString(resourceRequest, "selfComments" + x);
            selfReview.setEmployeeRating(empRating);
            selfReview.setEmployeeComment(empComments);
            ReviewLineLocalServiceUtil.updateReviewLine(selfReview);
        }
        String empComment = ParamUtil.getString(resourceRequest, "empComments");
        String achievementsByEmp = ParamUtil.getString(resourceRequest, "achievementsByEmpComment");
        review.setAchievements(achievementsByEmp);
        review.setEmployeeComment(empComment);
        ReviewLocalServiceUtil.updateReview(review);
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
    public String getManagerEmailForEditAccount(ResourceRequest resourceRequest) {
        long kpiId = ParamUtil.getLong(resourceRequest, "editKpiId");
        Kpi kpiHeaders = KpiLocalServiceUtil.fetchKpi(kpiId);
        KpiDto dataDTO = new KpiDto();
        dataDTO.setReviewerEmail(kpiHeaders.getManagerEmail());
        return  convertToJson(dataDTO);
    }

    @Override
    public String getReviewerEmailForEditAccount(ResourceRequest resourceRequest) {
        long kpiId = ParamUtil.getLong(resourceRequest, "editKpiId");
        Kpi kpiHeaders = KpiLocalServiceUtil.fetchKpi(kpiId);
        KpiDto dataDTO = new KpiDto();
        dataDTO.setReviewerEmail(kpiHeaders.getReviewerEmail());
        return  convertToJson(dataDTO);
    }

    @Override
    public String getYearlyEntries(ResourceRequest resourceRequest) {
        List<Employee> allEmployeeList = EmployeeLocalServiceUtil.findAllActiveEmployees();
        String forDate = ParamUtil.getString(resourceRequest, "forDate");
        String ecode = ParamUtil.getString(resourceRequest, "ecode");
        Map<Integer, String> reviewStateMapAnnual = new HashMap<>();
        Map<Integer, String> reviewStateMapMidYear = new HashMap<>();
        List<ReviewTimeline> reviewTimelines = ReviewTimelineLocalServiceUtil.getCalibratedTimelines();
        for(ReviewTimeline timeline : reviewTimelines){
            if(timeline.isFinalYear())
                reviewStateMapAnnual.put(timeline.getReviewState(),timeline.getStateName());
            else
                reviewStateMapMidYear.put(timeline.getReviewState(),timeline.getStateName());
        }
        List<ReviewDto> previousYearHeaderList = new ArrayList<>();
        List<Review> projectInfo = ReviewLocalServiceUtil.findReviewByEcode(ecode);
        for(Review headers: projectInfo) {
            List<Employee> managerData = allEmployeeList.stream().filter(s -> s.getEmail().equalsIgnoreCase(headers.getManager())).collect(Collectors.toList());
            if(convertDateToLocalDateTime(headers.getReviewStartDate()).isEqual(LocalDate.parse(forDate, FORMATTER_YYYY_MM_DD).atStartOfDay())) {
                ReviewDto reviewDto = new ReviewDto();
                reviewDto.setAccount(headers.getAccount());
                if(headers.isFinalYearReview()){
                    reviewDto.setReviewType(ANNUAL);
                    reviewDto.setReviewStateName(reviewStateMapAnnual.get(headers.getReviewState()));
                } else {
                    reviewDto.setReviewType(MID_YEAR);
                    reviewDto.setReviewStateName(reviewStateMapMidYear.get(headers.getReviewState()));
                }
                if(headers.isAccountPrimary()){
                    reviewDto.setAccountType(PRIMARY);
                } else {
                    reviewDto.setAccountType(SECONDARY);
                }
                reviewDto.setManagerName(managerData.isEmpty()? BLANK:managerData.get(0).getName());
                reviewDto.setReviewState(headers.getReviewState());
                reviewDto.setReviewId(headers.getReviewId());
                previousYearHeaderList.add(reviewDto);
            }
        }
        return convertToJson(previousYearHeaderList);
    }

    private List<KpiGuide> getAttributes(List<KpiGuide> guides) {
        return guides.stream().filter(KpiGuide::isAttribute).collect(Collectors.toList());
    }

    private ReviewDto setHeaderValues(List<Employee> activeEmployeeList, Employee employee) {
        ReviewDto reviewDto = new ReviewDto();
        List<Employee> managerData = activeEmployeeList.stream().filter(s -> s.getEcode().equalsIgnoreCase(employee.getManager())).collect(Collectors.toList());
        List<Employee> reviewerData = activeEmployeeList.stream().filter(s -> s.getEcode().equalsIgnoreCase(employee.getReviewer())).collect(Collectors.toList());
        reviewDto.setEmpEcode(employee.getEcode());
        reviewDto.setEmpName(employee.getName());
        reviewDto.setEmpDesignation(employee.getDesignation());
        reviewDto.setAccount(employee.getAccount());
        reviewDto.setEmpMobileNo(employee.getMobile());
        reviewDto.setEmpBand(employee.getBand());
        reviewDto.setManagerName(managerData.isEmpty() ? BLANK : managerData.get(0).getName());
        reviewDto.setManagerEmail(managerData.isEmpty() ? BLANK : managerData.get(0).getEmail());
        reviewDto.setReviewerEmail(reviewerData.isEmpty() ? BLANK : reviewerData.get(0).getEmail());
        reviewDto.setReviewerName(reviewerData.isEmpty() ? BLANK : reviewerData.get(0).getName());
        return reviewDto;
    }
}
