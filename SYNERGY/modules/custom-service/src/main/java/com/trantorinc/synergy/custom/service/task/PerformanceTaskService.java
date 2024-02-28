package com.trantorinc.synergy.custom.service.task;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.trantorinc.synergy.email.core.model.Email;
import com.trantorinc.synergy.email.core.service.EmailLocalServiceUtil;
import com.trantorinc.synergy.lms.core.model.Employee;
import com.trantorinc.synergy.lms.core.model.Scheduler;
import com.trantorinc.synergy.lms.core.service.EmployeeLocalServiceUtil;
import com.trantorinc.synergy.lms.core.service.SchedulerLocalServiceUtil;
import com.trantorinc.synergy.performance.core.model.*;
import com.trantorinc.synergy.performance.core.service.*;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import static com.trantorinc.synergy.common.service.constant.AppConstantService.*;
import static com.trantorinc.synergy.common.service.util.UtilService.*;
import static com.trantorinc.synergy.custom.service.constants.SynergyServicePortletKeys.*;


public class PerformanceTaskService {
    private static final Log log = LogFactoryUtil.getLog(PerformanceTaskService.class.getName());

    private PerformanceTaskService() {

    }

    public static void execute(LocalDateTime now) {

        log.info(MODULE_PERFORMANCE + " task started !");

        Scheduler scheduler = SchedulerLocalServiceUtil.findSchedulerByNameAndDate(TASK_PERFORMANCE, getStartOfDayDate(now.toLocalDate()));
        if (null == scheduler) {
            scheduler = SchedulerLocalServiceUtil.createScheduler(CounterLocalServiceUtil.increment());
            scheduler.setName(TASK_PERFORMANCE);
            scheduler.setStatus(false);
            scheduler.setOnDate(getStartOfDayDate(now.toLocalDate()));
            scheduler.setRunDate(convertLocalDateTimeToDate(now));
            SchedulerLocalServiceUtil.addScheduler(scheduler);
        } else {
            scheduler.setStatus(false);
            scheduler.setRunDate(convertLocalDateTimeToDate(now));
            SchedulerLocalServiceUtil.updateScheduler(scheduler);
        }
        log.info(MODULE_PERFORMANCE + " task preset !!");
        dailyLmsUpdate(now);

        log.info(MODULE_PERFORMANCE + " task completed ...(1/4)");
        checkNewJoinees(now);


        log.info(MODULE_PERFORMANCE + " task completed ...(2/4)");
        updateReviewOnTimeline(now);

        log.info(MODULE_PERFORMANCE + " task completed ...(3/4)");
        quarterlyEmail(now);

        log.info(MODULE_PERFORMANCE + " task completed ...(4/4)");

        scheduler.setStatus(true);
        SchedulerLocalServiceUtil.updateScheduler(scheduler);
        log.info(MODULE_PERFORMANCE + " task completed !!!");
    }

    public static void dailyLmsUpdate(LocalDateTime date) {
        List<Employee> allActiveEmployeeList = EmployeeLocalServiceUtil.findAllActiveEmployees();
        List<Kpi> allKpiList = KpiLocalServiceUtil.getKpis(-1, -1);
        for (Employee employeeData : allActiveEmployeeList) {
            List<Employee> managerEmailList = allActiveEmployeeList.stream().filter(s -> s.getEcode().equalsIgnoreCase(employeeData.getManager())).collect(Collectors.toList());
            List<Employee> reviewerEmailList = allActiveEmployeeList.stream().filter(s -> s.getEcode().equalsIgnoreCase(employeeData.getReviewer())).collect(Collectors.toList());
            if ((!managerEmailList.isEmpty()) && (!reviewerEmailList.isEmpty())) {
                List<Kpi> employeeKpiList = allKpiList.stream().filter(s -> s.getEcode().equalsIgnoreCase(employeeData.getEcode()) && s.getAccount().equalsIgnoreCase(employeeData.getAccount())).collect(Collectors.toList());
                List<Kpi> employeeKpiList1 = allKpiList.stream().filter(s -> s.getEcode().equalsIgnoreCase(employeeData.getEcode()) && s.isAccountPrimary()).collect(Collectors.toList());
                if (employeeKpiList.isEmpty()) {
                    Kpi kpiHeaders = KpiLocalServiceUtil.createKpi(CounterLocalServiceUtil.increment());
                    kpiHeaders.setEcode(employeeData.getEcode());
                    kpiHeaders.setAccount(employeeData.getAccount());
                    kpiHeaders.setAccountPrimary(true);
                    kpiHeaders.setUpdateDate(getIstDate());
                    kpiHeaders.setManagerEmail(managerEmailList.get(0).getEmail());
                    kpiHeaders.setReviewerEmail(reviewerEmailList.get(0).getEmail());
                    KpiLocalServiceUtil.addKpi(kpiHeaders);
                    for (Kpi kpi : employeeKpiList1) {
                        Kpi kpis = KpiLocalServiceUtil.fetchKpi(kpi.getKpiId());
                        kpis.setAccountPrimary(false);
                        KpiLocalServiceUtil.updateKpi(kpis);
                    }
                } else {
                    Kpi kpi = KpiLocalServiceUtil.fetchKpi(employeeKpiList.get(0).getKpiId());
//                    TODO
                    if (!kpi.getAccountPrimary()) {
                        kpi.setAccountPrimary(true);
                    }
                    kpi.setManagerEmail(managerEmailList.get(0).getEmail());
                    kpi.setReviewerEmail(reviewerEmailList.get(0).getEmail());
                    KpiLocalServiceUtil.updateKpi(kpi);
                }
            }
        }
    }

    public static void updateReviewOnTimeline(LocalDateTime date) {
        LocalDate today = date.toLocalDate();
        try {
            int currentYear = getCurrentYear();
            LocalDate midStartDate = getFinancialStartDate(currentYear);
            LocalDate annualStartDate = getMidFinancialEndDate(currentYear);
            List<ReviewTimeline> timelines = ReviewTimelineLocalServiceUtil.getCalibratedTimelines();
            List<Employee> allActiveEmployees = EmployeeLocalServiceUtil.findAllActiveEmployees();
            List<Review> currentYearReview = ReviewLocalServiceUtil.getReviews(-1, -1).stream().filter(a -> !convertDateToLocalDateTime(a.getReviewStartDate()).toLocalDate().isBefore(midStartDate)).collect(Collectors.toList());
            Map<Integer, LocalDate> finalYearTimelinesMap = new HashMap<>();
            Map<Integer, LocalDate> midYearTimelinesMap = new HashMap<>();
            Map<Integer, String> stateMap = new HashMap<>();
            LocalDate timelineDate;
            if (today.compareTo(midStartDate) >= 0 && today.compareTo(annualStartDate) <= 0) {
                timelineDate = timelines.stream().filter(s -> !s.getFinalYear()).collect(Collectors.toList()).get(1).getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            } else {
                timelineDate = timelines.stream().filter(ReviewTimeline::getFinalYear).collect(Collectors.toList()).get(1).getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            }
            for (ReviewTimeline timeline : timelines) {
                LocalDate endDate = timeline.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                if (timeline.isFinalYear()) {
                    finalYearTimelinesMap.put(timeline.getReviewState(), endDate);
                } else {
                    midYearTimelinesMap.put(timeline.getReviewState(), endDate);
                }
                stateMap.put(timeline.getReviewState(), timeline.getStateName());
            }

            LocalDate selfReviewFinalDate = finalYearTimelinesMap.get(STAGE_SELF_REVIEW);
            LocalDate selfReviewMidDate = midYearTimelinesMap.get(STAGE_SELF_REVIEW);
            LocalDate managerReviewFinalDate = finalYearTimelinesMap.get(STAGE_MANAGER_REVIEW);
            LocalDate managerReviewMidDate = midYearTimelinesMap.get(STAGE_MANAGER_REVIEW);

            /** 23 days before self review - bcc trantor all reminder email **/
            if (today.equals(finalYearTimelinesMap.get(STAGE_FREEZE_DATE_REVIEW))) {
//            if (ChronoUnit.DAYS.between(today, finalYearTimelinesMap.get(STAGE_SELF_REVIEW)) == NOOFDAYS) {-OLD
                Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
                email.setBody(MessageFormat.format(BODY_ANNUAL_SELF_REVIEW, LINE_ANNUAL_SELF_REVIEW, selfReviewFinalDate.format(FORMATTER_YYYY_MM_DD), getPortalUrl() + URL_PERFORMANCE));
                email.setSubject(SUBJECT_ANNUAL_SELF_REVIEW);
                email.setBccAddress(DL_TRANTORINDIA);
                email.setCreatedDate(getIstDate());
                email.setModule(MODULE_PERFORMANCE);
                email.setScheduled(true);
                email.setSent(false);
                EmailLocalServiceUtil.addEmail(email);
            }

            /** 23 days before self review - bcc trantor all reminder email **/
//            if (ChronoUnit.DAYS.between(today, midYearTimelinesMap.get(STAGE_SELF_REVIEW)) == NOOFDAYS_MIDYR) {
            if (today.isEqual(midYearTimelinesMap.get(STAGE_FREEZE_DATE_REVIEW))) {
                Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
                email.setBody(MessageFormat.format(BODY_MIDYEAR_SELF_REVIEW, LINE_MIDYEAR_SELF_REVIEW, selfReviewMidDate.format(FORMATTER_YYYY_MM_DD), getPortalUrl() + URL_PERFORMANCE));
                email.setSubject(SUBJECT_MIDYEAR_SELF_REVIEW);
                email.setBccAddress(DL_TRANTORINDIA);
                email.setCreatedDate(getIstDate());
                email.setModule(MODULE_PERFORMANCE);
                email.setScheduled(true);
                email.setSent(false);
                EmailLocalServiceUtil.addEmail(email);
            }

            /** 10 days before self review - bcc trantor all reminder email **/
            if (today.isEqual(finalYearTimelinesMap.get(STAGE_SELF_REVIEW).minusDays(10))) {
//            if (ChronoUnit.DAYS.between(today, finalYearTimelinesMap.get(STAGE_SELF_REVIEW)) == 10) {
                Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
                email.setBody(MessageFormat.format(BODY_ANNUAL_SELF_REVIEW, LINE_ANNUAL_SELF_REVIEW, selfReviewFinalDate.format(FORMATTER_YYYY_MM_DD), getPortalUrl() + URL_PERFORMANCE));
                email.setSubject(REMINDER + SUBJECT_ANNUAL_SELF_REVIEW);
                email.setBccAddress(DL_TRANTORINDIA);
                email.setCreatedDate(getIstDate());
                email.setModule(MODULE_PERFORMANCE);
                email.setScheduled(true);
                email.setSent(false);
                EmailLocalServiceUtil.addEmail(email);
            }
//            if (ChronoUnit.DAYS.between(today, midYearTimelinesMap.get(STAGE_SELF_REVIEW)) == 10) {
            if (today.isEqual(midYearTimelinesMap.get(STAGE_SELF_REVIEW).minusDays(10))) {
//                TODO - EQUALS OR ISEQUALS
                Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
                email.setBody(MessageFormat.format(BODY_MIDYEAR_SELF_REVIEW, LINE_MIDYEAR_SELF_REVIEW, selfReviewMidDate.format(FORMATTER_YYYY_MM_DD), getPortalUrl() + URL_PERFORMANCE));
                email.setSubject(REMINDER + SUBJECT_MIDYEAR_SELF_REVIEW);
                email.setBccAddress(DL_TRANTORINDIA);
                email.setCreatedDate(getIstDate());
                email.setModule(MODULE_PERFORMANCE);
                email.setScheduled(true);
                email.setSent(false);
                EmailLocalServiceUtil.addEmail(email);
            }

            /** 5 days before self review - bcc trantor all employee email , 1 consolidated Manager mail for employee with no objective finalized **/
            if (!today.isBefore(finalYearTimelinesMap.get(STAGE_SELF_REVIEW).minusDays(5)) && today.isBefore(finalYearTimelinesMap.get(STAGE_SELF_REVIEW))) {
//        if (ChronoUnit.DAYS.between(today, finalYearTimelinesMap.get(STAGE_SELF_REVIEW)) <= 5 && ChronoUnit.DAYS.between(today, finalYearTimelinesMap.get(STAGE_SELF_REVIEW)) > 0) {
                LocalDate dojAnnual = LocalDate.of(currentYear, 12, 31);

//                Set<String> allEcode = allActiveEmployees.stream().map(Employee::getEcode).collect(Collectors.toSet());
                boolean projectAnnual = true;
                Set<String> ecodesForFinalRemainderToBeSent = new HashSet<>();
                for (Employee employee : allActiveEmployees) {
                    List<Review> accountEcodeReviewList = currentYearReview.stream().filter(a -> a.getEcode().equalsIgnoreCase(employee.getEcode())).filter(s -> s.getFinalYearReview() == projectAnnual).collect(Collectors.toList());
                    if (accountEcodeReviewList.isEmpty()) {
                        ecodesForFinalRemainderToBeSent.add(employee.getEcode());
                    } else {
                        List<Review> primaryReviewList = accountEcodeReviewList.stream().filter(a -> a.getEcode().equalsIgnoreCase(employee.getEcode())).filter(s -> s.getFinalYearReview() == projectAnnual).filter(Review::isAccountPrimary).collect(Collectors.toList());
                        if (primaryReviewList.isEmpty()) {
                            ecodesForFinalRemainderToBeSent.add(employee.getEcode());
                        } else {
                            for (Review reviewP : primaryReviewList) {
                                if (reviewP.getReviewState() == 1) {
                                    ecodesForFinalRemainderToBeSent.add(employee.getEcode());
                                }
                            }
                        }
                        List<Review> secondaryReviewList = accountEcodeReviewList.stream().filter(a -> a.getEcode().equalsIgnoreCase(employee.getEcode())).filter(s -> s.getFinalYearReview() == projectAnnual).filter(s -> !s.isAccountPrimary()).collect(Collectors.toList());
                        if (!secondaryReviewList.isEmpty()) {
                            for (Review reviewS : secondaryReviewList) {
                                if (reviewS.getReviewState() == 1) {
                                    ecodesForFinalRemainderToBeSent.add(employee.getEcode());
                                }
                            }
                        }
                    }
                }
                List<String> ecodeListForFinalReminder = new ArrayList<>();
                ecodeListForFinalReminder.addAll(ecodesForFinalRemainderToBeSent);
                List<List<String>> partitions = new ArrayList<>();
//                TODO - WITH SIR
                for (int i = 0; i < ecodeListForFinalReminder.size(); i += MAX_EMAIL_LIST) {
                    partitions.add(ecodeListForFinalReminder.subList(i, Math.min(i + MAX_EMAIL_LIST,
                            ecodeListForFinalReminder.size())));
                }
                for (List<String> partitionList : partitions) {
                    StringBuilder emailaddress = new StringBuilder(BLANK);
                    for (String empId : partitionList) {
                        List<Employee> employeeData = allActiveEmployees.stream().filter(s -> s.getEcode().equalsIgnoreCase(empId)).collect(Collectors.toList());
                        if (!employeeData.isEmpty()) {
                            String employeeEmail = employeeData.get(0).getEmail();
//                            boolean isNotNewJoinee = ChronoUnit.DAYS.between(employeeData.getDoj().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
//                                    timelineDate.minusDays(JOININGNOOFDAYS)) >= 0;
                            boolean isNotNewJoinee = !convertDateToLocalDateTime(employeeData.get(0).getDoj()).toLocalDate().isAfter(dojAnnual);
                            if (isNotNewJoinee) {
                                emailaddress.append(COMMA).append(employeeEmail);
                            }
                        }
                    }
                    if (emailaddress.length() > 1) {
                        emailaddress = new StringBuilder(emailaddress.substring(1));
                    }
                    Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
                    email.setBody(MessageFormat.format(BODY_ANNUAL_SELF_REVIEW, LINE_ANNUAL_SELF_REVIEW_FINAL_REMINDER, selfReviewFinalDate.format(FORMATTER_YYYY_MM_DD), getPortalUrl() + URL_PERFORMANCE));
                    email.setSubject(REMINDER + SUBJECT_ANNUAL_SELF_REVIEW);
                    email.setBccAddress(emailaddress.toString());
                    email.setCreatedDate(getIstDate());
                    email.setModule(MODULE_PERFORMANCE);
                    email.setScheduled(true);
                    email.setSent(false);
                    EmailLocalServiceUtil.addEmail(email);
                }
//                TODO - DESCRIBE
                Set<String> managerUniqueEcode = allActiveEmployees.stream().map(Employee::getManager).collect(Collectors.toSet());
                for (String managerEcode : managerUniqueEcode) {
                    Set<String> ecodesWithPendingReview = new HashSet<>();
                    StringBuilder tableContent = new StringBuilder(TABLE_START_NO_SELF_REVIEW);
                    Set<String> managerLMSReporteeList = allActiveEmployees.stream().filter(s -> s.getManager().equalsIgnoreCase(managerEcode)).map(Employee::getEcode).collect(Collectors.toSet());
                    for (String employeeEcode : managerLMSReporteeList) {
                        List<Review> accountEcodeReviewList = currentYearReview.stream().filter(a -> a.getEcode().equals(employeeEcode)).filter(Review::isFinalYearReview).filter(Review::isAccountPrimary).collect(Collectors.toList());
                        if (accountEcodeReviewList.isEmpty()) {
                            ecodesWithPendingReview.add(String.valueOf(employeeEcode));
                        } else {
                            for (Review review : accountEcodeReviewList) {
                                if (review.getReviewState() == 1) {
                                    ecodesWithPendingReview.add(String.valueOf(employeeEcode));
                                }
                            }
                        }
                    }
                    List<Employee> managerInfoList = allActiveEmployees.stream().filter(s -> s.getEcode().equalsIgnoreCase(managerEcode)).collect(Collectors.toList());
                    if (!managerInfoList.isEmpty()) {
                        Employee managerInfo = managerInfoList.get(0);
                        boolean sendManagerEmail = false;
                        List<Review> managerReviewList = currentYearReview.stream().filter(s -> s.getManager().equalsIgnoreCase(managerInfo.getEmail())).filter(Review::isFinalYearReview).filter(s -> !s.isAccountPrimary()).filter(s -> s.getReviewState() == STAGE_SELF_REVIEW).collect(Collectors.toList());
                        if (!managerReviewList.isEmpty()) {
                            for (Review list : managerReviewList) {
                                List<Employee> employeeData = allActiveEmployees.stream().filter(s -> s.getEcode().equalsIgnoreCase(list.getEcode())).collect(Collectors.toList());
                                if (!employeeData.isEmpty()) {
                                    sendManagerEmail = true;
                                    List<Review> reviewData = managerReviewList.stream().filter(s -> s.getReviewId() == list.getReviewState()).collect(Collectors.toList());
                                    tableContent.append(ROW_START);
                                    tableContent.append(employeeData.get(0).getEcode());
                                    tableContent.append(CELL_SEPARATOR);
                                    tableContent.append(employeeData.get(0).getName());
                                    tableContent.append(CELL_SEPARATOR);
                                    tableContent.append(reviewData.isEmpty() ? BLANK : reviewData.get(0).getAccount());
                                    tableContent.append(CELL_SEPARATOR);
                                    tableContent.append(ANNUAL);
                                    tableContent.append(CELL_SEPARATOR);
                                    tableContent.append(SECONDARY);
                                    tableContent.append(ROW_END);
                                }
                            }
                        }
                        for (String ee : ecodesWithPendingReview) {
                            List<Employee> employeeInfo = allActiveEmployees.stream().filter(s -> s.getEcode().equalsIgnoreCase(ee)).collect(Collectors.toList());
//                                TODO - isNewJoinee isNotNewJoinee
//                                boolean isNewJoinee = ChronoUnit.DAYS.between(employeeInfo.getDoj().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
//                                        timelineDate.minusDays(JOININGNOOFDAYS)) >= 0;
//                            boolean isNotNewJoinee = ChronoUnit.DAYS.between(employeeData.getDoj().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
//                                    timelineDate.minusDays(JOININGNOOFDAYS)) >= 0;
                            if (!employeeInfo.isEmpty()) {
                                boolean isNewJoinee = !convertDateToLocalDateTime(employeeInfo.get(0).getDoj()).toLocalDate().isAfter(dojAnnual);

                                if (!isNewJoinee) {
                                    sendManagerEmail = true;
                                    tableContent.append(ROW_START);
                                    tableContent.append(employeeInfo.get(0).getEcode());
                                    tableContent.append(CELL_SEPARATOR);
                                    tableContent.append(employeeInfo.get(0).getName());
                                    tableContent.append(CELL_SEPARATOR);
                                    tableContent.append(employeeInfo.get(0).getAccount());
                                    tableContent.append(CELL_SEPARATOR);
                                    tableContent.append(ANNUAL);
                                    tableContent.append(CELL_SEPARATOR);
                                    tableContent.append(PRIMARY);
                                    tableContent.append(ROW_END);
                                }
                            }
                        }
                        tableContent.append(TABLE_END);
                        if (sendManagerEmail) {
                            Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
//                                TODO - CHECK WITH OLD 1, START
                            email.setBody(MessageFormat.format(BODY_ANNUAL_NO_SELF_REVIEW, YEAR_END_SMALL, selfReviewFinalDate.format(FORMATTER_YYYY_MM_DD), tableContent.toString()));
                            email.setSubject(SUBJECT_ANNUAL_NO_SELF_REVIEW);
                            email.setToAddress(managerInfo.getEmail());
                            email.setCreatedDate(getIstDate());
                            email.setModule(MODULE_PERFORMANCE);
                            email.setScheduled(true);
                            email.setSent(false);
                            EmailLocalServiceUtil.addEmail(email);
                        }
                    }
                }
            }
            if (!today.isBefore(midYearTimelinesMap.get(STAGE_SELF_REVIEW).minusDays(5)) && today.isBefore(midYearTimelinesMap.get(STAGE_SELF_REVIEW))) {
//            if (ChronoUnit.DAYS.between(today, midYearTimelinesMap.get(STAGE_SELF_REVIEW)) <= 5 && ChronoUnit.DAYS.between(today, midYearTimelinesMap.get(STAGE_SELF_REVIEW)) > 0) {
                LocalDate dojMid = LocalDate.of(currentYear, 6, 30);
                Set<String> allEcode = allActiveEmployees.stream().map(Employee::getEcode).collect(Collectors.toSet());
                boolean projectAnnual = false;
                Set<String> ecodesForFinalRemainderToBeSent = new HashSet<>();
                for (String ecode : allEcode) {
                    List<Review> accountEcodeReviewList = currentYearReview.stream().filter(a -> a.getEcode().equals(ecode)).filter(s -> s.getFinalYearReview() == projectAnnual).filter(Review::isAccountPrimary).collect(Collectors.toList());
                    if (accountEcodeReviewList.isEmpty()) {
                        ecodesForFinalRemainderToBeSent.add(ecode);
                    } else {
                        List<Review> primaryAccountList = accountEcodeReviewList.stream().filter(a -> a.getEcode().equals(ecode)).filter(s -> s.getFinalYearReview() == projectAnnual).filter(Review::isAccountPrimary).collect(Collectors.toList());
                        if (primaryAccountList.isEmpty()) {
                            ecodesForFinalRemainderToBeSent.add(ecode);
                        } else {
                            for (Review reviewP : primaryAccountList) {
                                if (reviewP.getReviewState() == 1) {
                                    ecodesForFinalRemainderToBeSent.add(ecode);
                                }
                            }
                        }
                        List<Review> secondaryReviewList = accountEcodeReviewList.stream().filter(a -> a.getEcode().equals(ecode)).filter(s -> s.getFinalYearReview() == projectAnnual).filter(s -> !s.isAccountPrimary()).collect(Collectors.toList());
                        if (!secondaryReviewList.isEmpty()) {
                            for (Review reviewS : secondaryReviewList) {
                                if (reviewS.getReviewState() == 1) {
                                    ecodesForFinalRemainderToBeSent.add(ecode);
                                }
                            }
                        }
                    }
                }
                List<String> ecodeListForFinalReminder = new ArrayList<>();
                ecodeListForFinalReminder.addAll(ecodesForFinalRemainderToBeSent);
                List<List<String>> partitions = new ArrayList<>();
                for (int i = 0; i < ecodeListForFinalReminder.size(); i += MAX_EMAIL_LIST) {
                    partitions.add(ecodeListForFinalReminder.subList(i, Math.min(i + MAX_EMAIL_LIST,
                            ecodeListForFinalReminder.size())));
                }
                for (List<String> partitionList : partitions) {
                    StringBuilder emailaddress = new StringBuilder(BLANK);
                    for (String empId : partitionList) {
                        List<Employee> employeeData = allActiveEmployees.stream().filter(s -> s.getEcode().equalsIgnoreCase(empId)).collect(Collectors.toList());
                        if (!employeeData.isEmpty()) {
                            String employeeEmail = employeeData.get(0).getEmail();
//                        TODO - SELF
                            boolean isNotNewJoinee = !convertDateToLocalDateTime(employeeData.get(0).getDoj()).toLocalDate().isAfter(dojMid);

                            if (isNotNewJoinee) {
                                emailaddress.append(COMMA).append(employeeEmail);
                            }
                        }
                    }
                    if (emailaddress.length() > 1) {
                        emailaddress = new StringBuilder(emailaddress.substring(1));
                    }
                    Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
                    email.setBody(MessageFormat.format(BODY_MIDYEAR_SELF_REVIEW, LINE_MIDYEAR_SELF_REVIEW_FINAL_REMINDER, selfReviewMidDate.format(FORMATTER_YYYY_MM_DD), getPortalUrl() + URL_PERFORMANCE));
                    email.setSubject(REMINDER + SUBJECT_MIDYEAR_SELF_REVIEW);
                    email.setBccAddress(emailaddress.toString());
                    email.setCreatedDate(getIstDate());
                    email.setSent(false);
                    email.setModule(MODULE_PERFORMANCE);
                    email.setScheduled(true);
                    email.setSent(false);
                    EmailLocalServiceUtil.addEmail(email);
                }

                Set<String> managerUniqueEcode = allActiveEmployees.stream().map(Employee::getManager).collect(Collectors.toSet());
                for (String managerEcode : managerUniqueEcode) {
                    Set<String> ecodesWithPendingReview = new HashSet<>();
                    StringBuilder tableContent = new StringBuilder(TABLE_START_NO_SELF_REVIEW);
                    Set<String> managerReporteeList = allActiveEmployees.stream().filter(s -> s.getManager().equalsIgnoreCase(managerEcode)).map(Employee::getEcode).collect(Collectors.toSet());
                    for (String employeeEcode : managerReporteeList) {
                        List<Review> accountEcodeReviewList = currentYearReview.stream().filter(s -> s.getEcode().equalsIgnoreCase(employeeEcode)).filter(s -> !s.isFinalYearReview()).filter(Review::isAccountPrimary).collect(Collectors.toList());
                        if (accountEcodeReviewList.isEmpty()) {
                            ecodesWithPendingReview.add(String.valueOf(employeeEcode));
                        } else {
                            for (Review review : accountEcodeReviewList) {
                                if (review.getReviewState() == 1) {
                                    ecodesWithPendingReview.add(String.valueOf(employeeEcode));
                                }
                            }
                        }
                    }
                    List<Employee> managerInfoList = allActiveEmployees.stream().filter(s -> s.getEcode().equalsIgnoreCase(managerEcode)).collect(Collectors.toList());
                    if (!managerInfoList.isEmpty()) {
                        Employee managerInfo = managerInfoList.get(0);
                        boolean sendManagerEmail = false;
                        List<Review> managerReviewList = currentYearReview.stream().filter(s -> s.getManager().equalsIgnoreCase(managerInfo.getEmail())).filter(s -> !s.getFinalYearReview()).filter(s -> !s.isAccountPrimary()).filter(s -> s.getReviewState() == STAGE_SELF_REVIEW).collect(Collectors.toList());
                        if (!managerReviewList.isEmpty()) {
                            for (Review list : managerReviewList) {
                                List<Employee> employeeData = allActiveEmployees.stream().filter(s -> s.getEcode().equalsIgnoreCase(list.getEcode())).collect(Collectors.toList());
                                if (!employeeData.isEmpty()) {
                                    sendManagerEmail = true;
                                    List<Review> reviewData = managerReviewList.stream().filter(s -> s.getReviewId() == list.getReviewId()).collect(Collectors.toList());
                                    tableContent.append(ROW_START);
                                    tableContent.append(employeeData.get(0).getEcode());
                                    tableContent.append(CELL_SEPARATOR);
                                    tableContent.append(employeeData.get(0).getName());
                                    tableContent.append(CELL_SEPARATOR);
                                    tableContent.append(reviewData.isEmpty() ? BLANK : reviewData.get(0).getAccount());
                                    tableContent.append(CELL_SEPARATOR);
                                    tableContent.append(MID_YEAR);
                                    tableContent.append(CELL_SEPARATOR);
                                    tableContent.append(SECONDARY);
                                    tableContent.append(ROW_END);
                                }
                            }
                            for (String ee : ecodesWithPendingReview) {
                                List<Employee> employeeInfo = allActiveEmployees.stream().filter(s -> s.getEcode().equalsIgnoreCase(ee)).collect(Collectors.toList());
                                if (!employeeInfo.isEmpty()) {
                                    boolean isNotNewJoinee = !convertDateToLocalDateTime(employeeInfo.get(0).getDoj()).toLocalDate().isAfter(dojMid);
//                                boolean isNotNewJoinee = ChronoUnit.DAYS.between(employeeInfo.getDoj().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
//                                        timelineDate.minusDays(JOININGNOOFDAYS)) >= 0;
                                    if (isNotNewJoinee) {
                                        sendManagerEmail = true;
                                        tableContent.append(ROW_START);
                                        tableContent.append(employeeInfo.get(0).getEcode());
                                        tableContent.append(CELL_SEPARATOR);
                                        tableContent.append(employeeInfo.get(0).getName());
                                        tableContent.append(CELL_SEPARATOR);
                                        tableContent.append(employeeInfo.get(0).getAccount());
                                        tableContent.append(CELL_SEPARATOR);
                                        tableContent.append(MID_YEAR);
                                        tableContent.append(CELL_SEPARATOR);
                                        tableContent.append(PRIMARY);
                                        tableContent.append(ROW_END);
                                    }
                                }
                            }
                            tableContent.append(TABLE_END);
                            if (sendManagerEmail) {
                                Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
                                email.setBody(MessageFormat.format(BODY_MIDYEAR_NO_SELF_REVIEW, selfReviewMidDate.format(FORMATTER_YYYY_MM_DD), tableContent.toString()));
                                email.setSubject(SUBJECT_MIDYEAR_NO_SELF_REVIEW);
                                email.setToAddress(managerInfo.getEmail());
                                email.setCreatedDate(getIstDate());
                                email.setModule(MODULE_PERFORMANCE);
                                email.setScheduled(true);
                                email.setSent(false);
                                EmailLocalServiceUtil.addEmail(email);
                            }
                        }
                    }
                }
            }

            /** on self review - Update all assessment from self review to manager review with self rating 2 and 1-1 employee update email, Also send generic email to all assignee(reviewer) **/
            if (today.isEqual(finalYearTimelinesMap.get(STAGE_SELF_REVIEW))) {
                Email emailManager = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
                emailManager.setBody(MessageFormat.format(BODY_MANAGER_READY, ANNUAL, managerReviewFinalDate.format(FORMATTER_YYYY_MM_DD), getPortalUrl() + URL_PERFORMANCE_ADMIN));
                emailManager.setSubject(MessageFormat.format(SUBJECT_MANAGER_READY, ANNUAL));
                emailManager.setBccAddress(DL_MANAGER + COMMA + DL_REVIEWER);
                emailManager.setCreatedDate(getIstDate());
                emailManager.setModule(MODULE_PERFORMANCE);
                emailManager.setScheduled(true);
                emailManager.setSent(false);
                EmailLocalServiceUtil.addEmail(emailManager);

                List<Review> incompleteSelfReviewFinalYrReviews = currentYearReview.stream().filter(a -> (a.getReviewState() == STAGE_SELF_REVIEW)).filter(Review::isFinalYearReview).collect(Collectors.toList());
                for (Review review : incompleteSelfReviewFinalYrReviews) {
                    List<Employee> employeeData = allActiveEmployees.stream().filter(e -> e.getEcode().equalsIgnoreCase(review.getEcode())).collect(Collectors.toList());
                    List<ReviewLine> employeeReviews = ReviewLineLocalServiceUtil.findReviewLineByReviewId(review.getReviewId());
                    for (ReviewLine line : employeeReviews) {
                        ReviewLine updateReview = ReviewLineLocalServiceUtil.fetchReviewLine(line.getLineId());
                        updateReview.setEmployeeRating(SYSTEM_RATING);
                        updateReview.setEmployeeComment(SYSTEM_COMMENT);
                        ReviewLineLocalServiceUtil.updateReviewLine(updateReview);
                    }
                    review.setStage1Date(getStartOfDayDate(today));
                    review.setReviewState(STAGE_MANAGER_REVIEW);
                    review.setEmployeeComment(SYSTEM_COMMENT);
                    review.setAchievements(NA);
                    ReviewLocalServiceUtil.updateReview(review);
                    if (!employeeData.isEmpty()) {
                        Email employeeEmail = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
                        employeeEmail.setBody(MessageFormat.format(BODY_REVIEW_AUTO_UPDATE, YEAR_END_SMALL, review.getAccount(), stateMap.get(STAGE_MANAGER_REVIEW)));
                        employeeEmail.setSubject(MessageFormat.format(SUBJECT_REVIEW_AUTO_UPDATE, ANNUAL, stateMap.get(STAGE_MANAGER_REVIEW)));
                        employeeEmail.setToAddress(employeeData.get(0).getEmail());
                        emailManager.setCreatedDate(getIstDate());
                        emailManager.setModule(MODULE_PERFORMANCE);
                        emailManager.setScheduled(true);
                        emailManager.setSent(false);
                        EmailLocalServiceUtil.addEmail(employeeEmail);

                    }
                }
            }
            if (today.isEqual(midYearTimelinesMap.get(STAGE_SELF_REVIEW))) {
                Email managerEmail = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
                managerEmail.setBody(MessageFormat.format(BODY_MANAGER_READY, MID_YEAR, managerReviewMidDate.format(FORMATTER_YYYY_MM_DD), getPortalUrl() + URL_PERFORMANCE_ADMIN));
                managerEmail.setSubject(MessageFormat.format(SUBJECT_MANAGER_READY, MID_YEAR));
                managerEmail.setBccAddress(DL_MANAGER + COMMA + DL_REVIEWER);
                managerEmail.setCreatedDate(getIstDate());
                managerEmail.setModule(MODULE_PERFORMANCE);
                managerEmail.setScheduled(true);
                managerEmail.setSent(false);
                EmailLocalServiceUtil.addEmail(managerEmail);

                List<Review> incompleteSelfReviewMidYrReviews = currentYearReview.stream().filter(a -> (a.getReviewState() == STAGE_SELF_REVIEW)).filter(a -> !a.getFinalYearReview()).collect(Collectors.toList());
                for (Review review : incompleteSelfReviewMidYrReviews) {
                    List<Employee> employeeData = allActiveEmployees.stream().filter(e -> e.getEcode().equalsIgnoreCase(review.getEcode())).collect(Collectors.toList());
                    review.setStage1Date(getStartOfDayDate(today));
                    review.setReviewState(STAGE_MANAGER_REVIEW);
                    ReviewLocalServiceUtil.updateReview(review);
                    if (!employeeData.isEmpty()) {
                        Email employeeEmail = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
                        employeeEmail.setBody(MessageFormat.format(BODY_REVIEW_AUTO_UPDATE, MID_YEAR_SMALL, review.getAccount(), stateMap.get(STAGE_MANAGER_REVIEW)));
                        employeeEmail.setSubject(MessageFormat.format(SUBJECT_REVIEW_AUTO_UPDATE, MID_YEAR, stateMap.get(STAGE_MANAGER_REVIEW)));
                        employeeEmail.setToAddress(employeeData.get(0).getEmail());
                        employeeEmail.setCreatedDate(getIstDate());
                        employeeEmail.setModule(MODULE_PERFORMANCE);
                        employeeEmail.setScheduled(true);
                        employeeEmail.setSent(false);
                        EmailLocalServiceUtil.addEmail(employeeEmail);
                    }
                }
            }

            if ((today.isEqual(finalYearTimelinesMap.get(STAGE_MANAGER_REVIEW).minusDays(20)) || today.isEqual(finalYearTimelinesMap.get(STAGE_MANAGER_REVIEW).minusDays(15)) || today.isEqual(finalYearTimelinesMap.get(STAGE_MANAGER_REVIEW).minusDays(10)) || !today.isBefore(finalYearTimelinesMap.get(STAGE_MANAGER_REVIEW).minusDays(5))) && today.isBefore(finalYearTimelinesMap.get(STAGE_MANAGER_REVIEW))) {
//            if ((ChronoUnit.DAYS.between(today, finalYearTimelinesMap.get(STAGE_MANAGER_REVIEW)) == 20 || ChronoUnit.DAYS.between(today, finalYearTimelinesMap.get(STAGE_MANAGER_REVIEW)) == 15 || ChronoUnit.DAYS.between(today, finalYearTimelinesMap.get(STAGE_MANAGER_REVIEW)) == 10 || ChronoUnit.DAYS.between(today, finalYearTimelinesMap.get(STAGE_MANAGER_REVIEW)) <= 5) && ChronoUnit.DAYS.between(today, finalYearTimelinesMap.get(STAGE_MANAGER_REVIEW)) > 0) {
                List<Review> incompleteManagerReviewFinalYrReviews = currentYearReview.stream().filter(a -> a.getReviewState() == STAGE_MANAGER_REVIEW).filter(Review::isFinalYearReview).collect(Collectors.toList());
                Set<String> managerList = incompleteManagerReviewFinalYrReviews.stream().map(Review::getManager).collect(Collectors.toSet());
                for (String manager : managerList) {
                    List<Review> managerReviews = incompleteManagerReviewFinalYrReviews.stream().filter(a -> a.getManager().equalsIgnoreCase(manager)).collect(Collectors.toList());
                    if (!managerReviews.isEmpty()) {
                        StringBuilder tableContent = new StringBuilder(TABLE_START_MANAGER_NO_MANAGER_REVIEW);
                        for (Review review : managerReviews) {
                            List<Employee> employee = allActiveEmployees.stream().filter(e -> e.getEcode().equalsIgnoreCase(review.getEcode())).collect(Collectors.toList());
                            if (!employee.isEmpty()) {
                                Employee employeeData = employee.get(0);
                                tableContent.append(ROW_START);
                                tableContent.append(employeeData.getEcode());
                                tableContent.append(CELL_SEPARATOR);
                                tableContent.append(employeeData.getName());
                                tableContent.append(CELL_SEPARATOR);
                                tableContent.append(employeeData.getEmail());
                                tableContent.append(CELL_SEPARATOR);
                                tableContent.append(review.getAccount());
                                tableContent.append(CELL_SEPARATOR);
                                if (review.isAccountPrimary()) {
                                    tableContent.append(PRIMARY);
                                } else {
                                    tableContent.append(SECONDARY);
                                }
                                tableContent.append(ROW_END);
                            } else {
                                log.info("Employee Data Not Found : " + review.getEcode());
                            }
                        }
                        tableContent.append(TABLE_END);
                        Email managerEmail = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
                        managerEmail.setBody(MessageFormat.format(BODY_MANAGER_NO_MANAGER_REVIEW, managerReviewFinalDate.format(FORMATTER_YYYY_MM_DD), tableContent.toString(), getPortalUrl() + URL_PERFORMANCE_ADMIN));
                        managerEmail.setSubject(MessageFormat.format(SUBJECT_MANAGER_NO_MANAGER_REVIEW, ANNUAL));
                        managerEmail.setToAddress(manager);
                        managerEmail.setCreatedDate(getIstDate());
                        managerEmail.setModule(MODULE_PERFORMANCE);
                        managerEmail.setScheduled(true);
                        managerEmail.setSent(false);
                        EmailLocalServiceUtil.addEmail(managerEmail);
                    }
                }
            }
            if (!today.isBefore(finalYearTimelinesMap.get(STAGE_MANAGER_REVIEW).minusDays(5)) && today.isBefore(finalYearTimelinesMap.get(STAGE_MANAGER_REVIEW))) {
//            if (ChronoUnit.DAYS.between(today, finalYearTimelinesMap.get(STAGE_MANAGER_REVIEW)) <= 5 && ChronoUnit.DAYS.between(today, finalYearTimelinesMap.get(STAGE_MANAGER_REVIEW)) > 0) {
                Set<Long> reviewListInManagerReviewState = currentYearReview.stream().filter(a -> a.getReviewState() == STAGE_MANAGER_REVIEW).filter(Review::isFinalYearReview).map(Review::getReviewId).collect(Collectors.toSet());
                if (!reviewListInManagerReviewState.isEmpty()) {
                    for (Long reviewId : reviewListInManagerReviewState) {
                        Review reviewInfo = ReviewLocalServiceUtil.fetchReview(reviewId);
                        Employee employeeInfo = EmployeeLocalServiceUtil.fetchEmployee(reviewInfo.getEcode());
                        Email employeeEmail = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
                        employeeEmail.setBody(MessageFormat.format(BODY_EMPLOYEE_NO_MANAGER_REVIEW, reviewInfo.getAccount(), managerReviewFinalDate.format(FORMATTER_YYYY_MM_DD)));
                        employeeEmail.setSubject(MessageFormat.format(SUBJECT_EMPLOYEE_NO_MANAGER_REVIEW, ANNUAL));
                        employeeEmail.setToAddress(employeeInfo.getEmail());
                        employeeEmail.setCreatedDate(getIstDate());
                        employeeEmail.setModule(MODULE_PERFORMANCE);
                        employeeEmail.setScheduled(true);
                        employeeEmail.setSent(false);
                        EmailLocalServiceUtil.addEmail(employeeEmail);
                    }
                }
            }
            if ((today.isEqual(midYearTimelinesMap.get(STAGE_MANAGER_REVIEW).minusDays(15)) || today.isEqual(midYearTimelinesMap.get(STAGE_MANAGER_REVIEW).minusDays(10)) || !today.isBefore(midYearTimelinesMap.get(STAGE_MANAGER_REVIEW).minusDays(5))) && today.isBefore(midYearTimelinesMap.get(STAGE_MANAGER_REVIEW))) {
//            if ((ChronoUnit.DAYS.between(today, midYearTimelinesMap.get(STAGE_MANAGER_REVIEW)) == 15 || ChronoUnit.DAYS.between(today, midYearTimelinesMap.get(STAGE_MANAGER_REVIEW)) == 10 || ChronoUnit.DAYS.between(today, midYearTimelinesMap.get(STAGE_MANAGER_REVIEW)) <= 5) && ChronoUnit.DAYS.between(today, midYearTimelinesMap.get(STAGE_MANAGER_REVIEW)) > 0) {
                List<Review> incompleteManagerReviewMidYrReviews = currentYearReview.stream().filter(a -> a.getReviewState() == STAGE_MANAGER_REVIEW).filter(a -> !a.isFinalYearReview()).collect(Collectors.toList());
                Set<String> managerList = incompleteManagerReviewMidYrReviews.stream().map(Review::getManager).collect(Collectors.toSet());
                for (String manager : managerList) {
                    List<Review> managerReviews = incompleteManagerReviewMidYrReviews.stream().filter(a -> a.getManager().equalsIgnoreCase(manager)).collect(Collectors.toList());
                    if (!managerReviews.isEmpty()) {
                        StringBuilder tableContent = new StringBuilder(TABLE_START_MANAGER_NO_MANAGER_REVIEW);
                        for (Review review : managerReviews) {
                            List<Employee> employee = allActiveEmployees.stream().filter(e -> e.getEcode().equalsIgnoreCase(review.getEcode())).collect(Collectors.toList());
                            if (!employee.isEmpty()) {
                                Employee employeeData = employee.get(0);
                                tableContent.append(ROW_START);
                                tableContent.append(employeeData.getEcode());
                                tableContent.append(CELL_SEPARATOR);
                                tableContent.append(employeeData.getName());
                                tableContent.append(CELL_SEPARATOR);
                                tableContent.append(employeeData.getEmail());
                                tableContent.append(CELL_SEPARATOR);
                                tableContent.append(review.getAccount());
                                tableContent.append(CELL_SEPARATOR);
                                if (review.isAccountPrimary()) {
                                    tableContent.append(PRIMARY);
                                } else {
                                    tableContent.append(SECONDARY);
                                }
                                tableContent.append(ROW_END);
                            } else {
                                log.info("Employee Data Not Found : " + review.getEcode());
                            }
                        }
                        tableContent.append(TABLE_END);
                        Email managerEmail = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
                        managerEmail.setBody(MessageFormat.format(BODY_MANAGER_NO_MANAGER_REVIEW, managerReviewMidDate.format(FORMATTER_YYYY_MM_DD), tableContent.toString(), getPortalUrl() + URL_PERFORMANCE_ADMIN));
                        managerEmail.setSubject(MessageFormat.format(SUBJECT_MANAGER_NO_MANAGER_REVIEW, MID_YEAR));
                        managerEmail.setToAddress(manager);
                        managerEmail.setCreatedDate(getIstDate());
                        managerEmail.setModule(MODULE_PERFORMANCE);
                        managerEmail.setScheduled(true);
                        managerEmail.setSent(false);
                        EmailLocalServiceUtil.addEmail(managerEmail);
                    }
                }
            }
            if (!today.isBefore(midYearTimelinesMap.get(STAGE_MANAGER_REVIEW).minusDays(5)) && today.isBefore(midYearTimelinesMap.get(STAGE_MANAGER_REVIEW))) {
//            if (ChronoUnit.DAYS.between(today, midYearTimelinesMap.get(STAGE_MANAGER_REVIEW)) <= 5 && ChronoUnit.DAYS.between(today, midYearTimelinesMap.get(STAGE_MANAGER_REVIEW)) > 0) {
                Set<Long> reviewListInManagerReviewState = currentYearReview.stream().filter(a -> a.getReviewState() == STAGE_MANAGER_REVIEW).filter(a -> !a.isFinalYearReview()).map(Review::getReviewId).collect(Collectors.toSet());
                if (!reviewListInManagerReviewState.isEmpty()) {
                    for (Long reviewId : reviewListInManagerReviewState) {
                        Review reviewInfo = ReviewLocalServiceUtil.fetchReview(reviewId);
                        Employee employeeInfo = EmployeeLocalServiceUtil.fetchEmployee(reviewInfo.getEcode());
                        Email employeeEmail = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
                        employeeEmail.setBody(MessageFormat.format(BODY_EMPLOYEE_NO_MANAGER_REVIEW, reviewInfo.getAccount(), managerReviewMidDate.format(FORMATTER_YYYY_MM_DD)));
                        employeeEmail.setSubject(MessageFormat.format(SUBJECT_EMPLOYEE_NO_MANAGER_REVIEW, MID_YEAR));
                        employeeEmail.setToAddress(employeeInfo.getEmail());
                        employeeEmail.setCreatedDate(getIstDate());
                        employeeEmail.setModule(MODULE_PERFORMANCE);
                        employeeEmail.setScheduled(true);
                        employeeEmail.setSent(false);
                        EmailLocalServiceUtil.addEmail(employeeEmail);
                    }
                }
            }

            /** day after manager review end date, send consolidated HR email for those whose rating is less than 3 **/
//        if (ChronoUnit.DAYS.between(finalYearTimelinesMap.get(STAGE_MANAGER_REVIEW), today) == 1) {
            if (finalYearTimelinesMap.get(STAGE_MANAGER_REVIEW).minusDays(1).isEqual(today)) {
                List<Review> dailyCompletedManagerReviewFinalYrReviews = currentYearReview.stream().filter(a -> a.getStage2Date() != null).filter(a -> Double.parseDouble(a.getRating()) < 3).filter(Review::isFinalYearReview).collect(Collectors.toList());
                if (!dailyCompletedManagerReviewFinalYrReviews.isEmpty()) {
                    StringBuilder tableContent = new StringBuilder(TABLE_START_POOR_RATING);
                    for (Review review : dailyCompletedManagerReviewFinalYrReviews) {
                        List<Employee> employeeData = allActiveEmployees.stream().filter(e -> e.getEcode().equalsIgnoreCase(review.getEcode())).collect(Collectors.toList());
                        tableContent.append(ROW_START);
                        tableContent.append(review.getEcode());
                        tableContent.append(CELL_SEPARATOR);
                        tableContent.append(employeeData.isEmpty() ? BLANK : employeeData.get(0).getName());
                        tableContent.append(CELL_SEPARATOR);
                        tableContent.append(review.getRating());
                        tableContent.append(CELL_SEPARATOR);
                        tableContent.append(review.getAccount());
                        tableContent.append(CELL_SEPARATOR);
                        if (review.isAccountPrimary()) {
                            tableContent.append(PRIMARY);
                        } else {
                            tableContent.append(SECONDARY);
                        }
                        tableContent.append(ROW_END);
                    }
                    tableContent.append(TABLE_END);
                    Email emailHR = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
                    emailHR.setBody(MessageFormat.format(BODY_POOR_RATING, tableContent.toString()));
                    emailHR.setSubject(MessageFormat.format(SUBJECT_POOR_RATING, ANNUAL));
                    emailHR.setToAddress(DL_HR);
                    emailHR.setCreatedDate(getIstDate());
                    emailHR.setModule(MODULE_PERFORMANCE);
                    emailHR.setScheduled(true);
                    emailHR.setSent(false);
                    EmailLocalServiceUtil.addEmail(emailHR);

                }
            }
            if (midYearTimelinesMap.get(STAGE_MANAGER_REVIEW).minusDays(1).isEqual(today)) {
//            if (ChronoUnit.DAYS.between(midYearTimelinesMap.get(STAGE_MANAGER_REVIEW), today) == 1) {
                List<Review> dailyCompletedManagerReviewMidYrReviews = currentYearReview.stream().filter(a -> a.getStage2Date() != null).filter(a -> Double.parseDouble(a.getRating()) < 3).filter(a -> !a.isFinalYearReview()).collect(Collectors.toList());
                if (!dailyCompletedManagerReviewMidYrReviews.isEmpty()) {
                    StringBuilder tableContent = new StringBuilder(TABLE_START_POOR_RATING);
                    for (Review review : dailyCompletedManagerReviewMidYrReviews) {
                        List<Employee> employeeData = allActiveEmployees.stream().filter(e -> e.getEcode().equalsIgnoreCase(review.getEcode())).collect(Collectors.toList());
                        tableContent.append(ROW_START);
                        tableContent.append(review.getEcode());
                        tableContent.append(CELL_SEPARATOR);
                        tableContent.append(employeeData.isEmpty() ? BLANK : employeeData.get(0).getName());
                        tableContent.append(CELL_SEPARATOR);
                        tableContent.append(review.getRating());
                        tableContent.append(CELL_SEPARATOR);
                        tableContent.append(review.getAccount());
                        tableContent.append(CELL_SEPARATOR);
                        if (review.isAccountPrimary()) {
                            tableContent.append(PRIMARY);
                        } else {
                            tableContent.append(SECONDARY);
                        }
                        tableContent.append(ROW_END);
                    }
                    tableContent.append(TABLE_END);

                    Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
                    email.setBody(MessageFormat.format(BODY_POOR_RATING, tableContent.toString()));
                    email.setSubject(MessageFormat.format(SUBJECT_POOR_RATING, MID_YEAR));
                    email.setToAddress(DL_HR);
                    email.setCreatedDate(getIstDate());
                    email.setModule(MODULE_PERFORMANCE);
                    email.setScheduled(true);
                    email.setSent(false);
                    EmailLocalServiceUtil.addEmail(email);
                }
            }

            /** on manager review - Update all assessment to employee sign-off, COmplete or HR review with manager rating 2 and 1-1 employee update email **/
            if (today.isEqual(finalYearTimelinesMap.get(STAGE_MANAGER_REVIEW))) {
//        if (ChronoUnit.DAYS.between(today, finalYearTimelinesMap.get(STAGE_MANAGER_REVIEW)) == 0) {
                List<Review> incompleteManagerReviewFinalYrReviews = currentYearReview.stream().filter(a -> a.getReviewState() == STAGE_MANAGER_REVIEW).filter(Review::isFinalYearReview).collect(Collectors.toList());
                for (Review review : incompleteManagerReviewFinalYrReviews) {
                    boolean adminRole = false;
                    List<Employee> employeeData = allActiveEmployees.stream().filter(e -> e.getEcode().equalsIgnoreCase(review.getEcode())).collect(Collectors.toList());
                    if (!employeeData.isEmpty()) {
                        List<Employee> managerData = allActiveEmployees.stream().filter(e -> e.getEcode().equalsIgnoreCase(employeeData.get(0).getManager())).collect(Collectors.toList());
                        List<ReviewLine> employeeReviews = ReviewLineLocalServiceUtil.findReviewLineByReviewId(review.getReviewId());
                        //                        TODO - doubttt
                        if (!managerData.isEmpty()) {
                            User liferayUser = UserLocalServiceUtil.fetchUserByEmailAddress(COMPANY_ID, managerData.get(0).getEmail());
                            String[] role = {ROLE_PERFORMANCE};
                            if (RoleLocalServiceUtil.hasUserRoles(liferayUser.getUserId(), liferayUser.getCompanyId(), role, false)) {
                                adminRole = true;
                            }
                        }
                        for (ReviewLine reviewLine : employeeReviews) {
                            ReviewLine line = ReviewLineLocalServiceUtil.fetchReviewLine(reviewLine.getLineId());
                            if (adminRole) {
                                line.setManagerRating(SYSTEM_RATING_ADMIN);
                                review.setRating(SYSTEM_ADMIN_NUMERIC_RATING);
                            } else {
                                line.setManagerRating(SYSTEM_RATING);
                                review.setRating(SYSTEM_NUMERIC_RATING);
                            }
                            line.setManagerComment(SYSTEM_COMMENT);
                            ReviewLineLocalServiceUtil.updateReviewLine(line);
                        }
//                        todo :doubtt
                        if (review.isAccountPrimary()) {
                            review.setReviewState(STAGE_EMPLOYEE_SIGNOFF);
                        } else {
                            review.setReviewState(STAGE_COMPLETE);
                        }
                        review.setImprovementComment(SYSTEM_COMMENT);
                        review.setManagerComment(SYSTEM_COMMENT);
//                        todo :doubtt
//                        review.setRating(SYSTEM_NUMERIC_RATING);
                        review.setStage2Date(getStartOfDayDate(today));
                        ReviewLocalServiceUtil.updateReview(review);
                        if (!employeeData.isEmpty()) {
                            Email employeeEmail = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
                            if (review.isAccountPrimary()) {
                                employeeEmail.setBody(MessageFormat.format(BODY_REVIEW_AUTO_UPDATE, YEAR_END_SMALL, review.getAccount(), stateMap.get(STAGE_EMPLOYEE_SIGNOFF)));
                                employeeEmail.setSubject(MessageFormat.format(SUBJECT_REVIEW_AUTO_UPDATE, ANNUAL, stateMap.get(STAGE_EMPLOYEE_SIGNOFF)));
                            } else {
                                employeeEmail.setBody(MessageFormat.format(BODY_REVIEW_AUTO_UPDATE, YEAR_END_SMALL, review.getAccount(), stateMap.get(STAGE_COMPLETE)));
                                employeeEmail.setSubject(MessageFormat.format(SUBJECT_REVIEW_AUTO_UPDATE, ANNUAL, stateMap.get(STAGE_COMPLETE)));
                            }
                            employeeEmail.setToAddress(employeeData.get(0).getEmail());
                            employeeEmail.setCreatedDate(getIstDate());
                            employeeEmail.setModule(MODULE_PERFORMANCE);
                            employeeEmail.setScheduled(true);
                            employeeEmail.setSent(false);
                            EmailLocalServiceUtil.addEmail(employeeEmail);
                        }
                    }
                }
            }
            if (ChronoUnit.DAYS.between(today, midYearTimelinesMap.get(STAGE_MANAGER_REVIEW)) == 0) {
                List<Review> incompleteManagerReviewMidYrReviews = currentYearReview.stream().filter(a -> a.getReviewState() == STAGE_MANAGER_REVIEW).filter(a -> !a.isFinalYearReview()).collect(Collectors.toList());
                for (Review review1 : incompleteManagerReviewMidYrReviews) {
                    List<Employee> employeeData = allActiveEmployees.stream().filter(e -> e.getEcode().equalsIgnoreCase(review1.getEcode())).collect(Collectors.toList());
                    List<ReviewLine> employeeReviews = ReviewLineLocalServiceUtil.findReviewLineByReviewId(review1.getReviewId());
                    List<Employee> managerData = allActiveEmployees.stream().filter(e -> e.getEcode().equalsIgnoreCase(employeeData.get(0).getManager())).collect(Collectors.toList());
                    boolean adminRole = false;
                    if (!managerData.isEmpty()) {
                        User liferayUser = UserLocalServiceUtil.fetchUserByEmailAddress(COMPANY_ID, managerData.get(0).getEmail());
                        String[] role = {ROLE_PERFORMANCE};
                        if (RoleLocalServiceUtil.hasUserRoles(liferayUser.getUserId(), liferayUser.getCompanyId(), role, false)) {
                            adminRole = true;
                        }
                    }
                    for (ReviewLine review : employeeReviews) {
                        ReviewLine line = ReviewLineLocalServiceUtil.fetchReviewLine(review.getLineId());
                        if (adminRole) {
                            line.setManagerRating(SYSTEM_RATING_ADMIN);
                            review1.setRating(SYSTEM_ADMIN_NUMERIC_RATING);
                        } else {
                            line.setManagerRating(SYSTEM_RATING);
                            review1.setRating(SYSTEM_NUMERIC_RATING);
                        }
                        line.setManagerComment(SYSTEM_COMMENT);
                        line.setHrRating(SYSTEM_RATING);
                        line.setHrComment(BLANK);
                        ReviewLineLocalServiceUtil.updateReviewLine(line);
                    }
                    review1.setReviewState(STAGE_COMPLETE);
                    review1.setImprovementComment(SYSTEM_COMMENT);
                    review1.setManagerComment(SYSTEM_COMMENT);
//                    review1.setRating(SYSTEM_NUMERIC_RATING);
                    review1.setStage2Date(getStartOfDayDate(today));
                    ReviewLocalServiceUtil.updateReview(review1);
                    if (!employeeData.isEmpty()) {
                        Email employeeEmail = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
                        employeeEmail.setToAddress(employeeData.get(0).getEmail());
                        employeeEmail.setBody(MessageFormat.format(BODY_REVIEW_AUTO_UPDATE, MID_YEAR_SMALL, review1.getAccount(), stateMap.get(STAGE_COMPLETE)));
                        employeeEmail.setSubject(MessageFormat.format(SUBJECT_REVIEW_AUTO_UPDATE, MID_YEAR, stateMap.get(STAGE_COMPLETE)));
                        employeeEmail.setCreatedDate(getIstDate());
                        employeeEmail.setModule(MODULE_PERFORMANCE);
                        employeeEmail.setScheduled(true);
                        employeeEmail.setSent(false);
                        EmailLocalServiceUtil.addEmail(employeeEmail);
                    }
                }
            }

            /** on employee signoff - Update all assessment to hr-review and 1-1 employee update email **/
            if (today.isEqual(finalYearTimelinesMap.get(STAGE_EMPLOYEE_SIGNOFF))) {
//            if (ChronoUnit.DAYS.between(today, finalYearTimelinesMap.get(STAGE_EMPLOYEE_SIGNOFF)) == 0) {
                List<Review> incompleteEmployeeSignOffReviews = currentYearReview.stream().filter(a -> a.getReviewState() == STAGE_EMPLOYEE_SIGNOFF).collect(Collectors.toList());
                for (Review review1 : incompleteEmployeeSignOffReviews) {
                    List<ReviewLine> employeeReviews = ReviewLineLocalServiceUtil.findReviewLineByReviewId(review1.getReviewId());
                    for (ReviewLine line : employeeReviews) {
                        ReviewLine review = ReviewLineLocalServiceUtil.fetchReviewLine(line.getLineId());
                        review.setHrRating(line.getManagerRating());
                        review.setHrComment(BLANK);
                        ReviewLineLocalServiceUtil.updateReviewLine(review);
                    }
                    review1.setReviewState(STAGE_HR_REVIEW);
                    review1.setStage3Date(getStartOfDayDate(today));
                    ReviewLocalServiceUtil.updateReview(review1);

                    List<Employee> edata = allActiveEmployees.stream().filter(e -> e.getEcode().equalsIgnoreCase(review1.getEcode())).collect(Collectors.toList());
                    if (edata.isEmpty()) {
                        Email employeeEmail = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
                        employeeEmail.setBody(MessageFormat.format(BODY_REVIEW_AUTO_UPDATE, YEAR_END_SMALL, review1.getAccount(), stateMap.get(STAGE_HR_REVIEW)));
                        employeeEmail.setSubject(MessageFormat.format(SUBJECT_REVIEW_AUTO_UPDATE, ANNUAL, stateMap.get(STAGE_HR_REVIEW)));
                        employeeEmail.setToAddress(edata.get(0).getEmail());
                        employeeEmail.setCreatedDate(getIstDate());
                        employeeEmail.setModule(MODULE_PERFORMANCE);
                        employeeEmail.setScheduled(true);
                        employeeEmail.setSent(false);
                        EmailLocalServiceUtil.addEmail(employeeEmail);
                    }
                }
            }

            /** on hr review - Update all assessment to complete with hr rating same as manager and 1-1 employee update email **/
            if (today.isEqual(finalYearTimelinesMap.get(STAGE_HR_REVIEW))) {
//            if (ChronoUnit.DAYS.between(today, finalYearTimelinesMap.get(STAGE_HR_REVIEW)) == 0) {
                List<Review> incompleteHrReviewFinalYrReviews = currentYearReview.stream().filter(a -> a.getReviewState() == STAGE_HR_REVIEW).filter(Review::isFinalYearReview).collect(Collectors.toList());
                for (Review review : incompleteHrReviewFinalYrReviews) {
                    List<Employee> edata = allActiveEmployees.stream().filter(e -> e.getEcode().equalsIgnoreCase(review.getEcode())).collect(Collectors.toList());
                    review.setReviewState(STAGE_COMPLETE);
                    review.setStage4Date(getStartOfDayDate(today));
                    review.setHrComment(review.getManagerComment());
                    ReviewLocalServiceUtil.updateReview(review);
                    if (!edata.isEmpty()) {
                        Email employeeEmail = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
                        employeeEmail.setBody(MessageFormat.format(BODY_REVIEW_AUTO_UPDATE, YEAR_END_SMALL, review.getAccount(), stateMap.get(STAGE_COMPLETE)));
                        employeeEmail.setSubject(MessageFormat.format(SUBJECT_REVIEW_AUTO_UPDATE, ANNUAL, stateMap.get(STAGE_COMPLETE)));
                        employeeEmail.setToAddress(edata.get(0).getEmail());
                        employeeEmail.setCreatedDate(getIstDate());
                        employeeEmail.setModule(MODULE_PERFORMANCE);
                        employeeEmail.setScheduled(true);
                        employeeEmail.setSent(false);
                        EmailLocalServiceUtil.addEmail(employeeEmail);
                    }
                }
            }
            if (today.isEqual(finalYearTimelinesMap.get(STAGE_COMPLETE)) || today.isEqual(midYearTimelinesMap.get(STAGE_COMPLETE))) {
                List<ReviewRollback> pendingRollbackRequests = ReviewRollbackLocalServiceUtil.findReviewRollbackByStatus(0);
                if (!pendingRollbackRequests.isEmpty()) {
                    for (ReviewRollback requestRollbacks : pendingRollbackRequests) {
                        ReviewRollback rollbackHeaders = ReviewRollbackLocalServiceUtil.fetchReviewRollback(requestRollbacks.getRollbackId());
                        rollbackHeaders.setStatus(2);
                        ReviewRollbackLocalServiceUtil.updateReviewRollback(rollbackHeaders);
                    }
                }
                List<ReviewClose> pendingCloseRequests = ReviewCloseLocalServiceUtil.findReviewCloseByStatus(0);
                if (!pendingCloseRequests.isEmpty()) {
                    for (ReviewClose requestClosed : pendingCloseRequests) {
                        ReviewClose closeRequestHeaders = ReviewCloseLocalServiceUtil.fetchReviewClose(requestClosed.getCloseId());
                        closeRequestHeaders.setStatus(2);
                        ReviewCloseLocalServiceUtil.updateReviewClose(closeRequestHeaders);

                    }
                }
                LocalDate cStart = getFinancialStartDate(today.getYear());
                LocalDate cEnd = getMidFinancialEndDate(today.getYear());
                boolean projectAnnual = LocalDate.now().isBefore(cStart) || LocalDate.now().isAfter(cEnd);
                Set<Long> allPendingReviewExceptComplete = currentYearReview.stream().filter(s -> s.isFinalYearReview() == projectAnnual).filter(s -> (s.getReviewState() != 5 && s.getReviewState() != 6)).map(Review::getReviewId).collect(Collectors.toSet());
                if (!allPendingReviewExceptComplete.isEmpty()) {
                    for (Long reviewId : allPendingReviewExceptComplete) {
                        Review reviewHeaders = ReviewLocalServiceUtil.fetchReview(reviewId);
                        if (reviewHeaders.isFinalYearReview()) {
                            if (reviewHeaders.getReviewState() == STAGE_SELF_REVIEW) {
                                List<ReviewLine> employeeReviews = ReviewLineLocalServiceUtil.findReviewLineByReviewId(reviewHeaders.getReviewId());
                                for (ReviewLine line : employeeReviews) {
                                    ReviewLine reviewLine = ReviewLineLocalServiceUtil.fetchReviewLine(line.getLineId());
                                    reviewLine.setEmployeeRating(SYSTEM_RATING);
                                    reviewLine.setEmployeeComment(SYSTEM_COMMENT);
                                    reviewLine.setManagerRating(SYSTEM_RATING);
                                    reviewLine.setManagerComment(SYSTEM_COMMENT);
                                    reviewLine.setHrRating(SYSTEM_RATING);
                                    reviewLine.setHrComment(SYSTEM_COMMENT);
                                    ReviewLineLocalServiceUtil.updateReviewLine(reviewLine);
                                }
                                reviewHeaders.setStage1Date(getStartOfDayDate(today));
                                reviewHeaders.setStage2Date(getStartOfDayDate(today));
                                reviewHeaders.setStage3Date(getStartOfDayDate(today));
                                reviewHeaders.setStage4Date(getStartOfDayDate(today));
                                reviewHeaders.setEmployeeComment(SYSTEM_COMMENT);
                                reviewHeaders.setAchievements(NA);
                                reviewHeaders.setImprovementComment(SYSTEM_COMMENT);
                                reviewHeaders.setManagerComment(SYSTEM_COMMENT);
                                reviewHeaders.setHrComment(SYSTEM_COMMENT);
                                reviewHeaders.setRating(SYSTEM_NUMERIC_RATING);
                            } else if (reviewHeaders.getReviewState() == STAGE_MANAGER_REVIEW) {
                                List<ReviewLine> employeeReviews = ReviewLineLocalServiceUtil.findReviewLineByReviewId(reviewHeaders.getReviewId());
                                for (ReviewLine review : employeeReviews) {
                                    ReviewLine reviewLine = ReviewLineLocalServiceUtil.fetchReviewLine(review.getLineId());
                                    reviewLine.setManagerRating(SYSTEM_RATING);
                                    reviewLine.setManagerComment(SYSTEM_COMMENT);
                                    reviewLine.setHrRating(SYSTEM_RATING);
                                    reviewLine.setHrComment(SYSTEM_COMMENT);
                                    ReviewLineLocalServiceUtil.updateReviewLine(reviewLine);
                                }
                                reviewHeaders.setStage2Date(getStartOfDayDate(today));
                                reviewHeaders.setStage3Date(getStartOfDayDate(today));
                                reviewHeaders.setStage4Date(getStartOfDayDate(today));
                                reviewHeaders.setImprovementComment(SYSTEM_COMMENT);
                                reviewHeaders.setManagerComment(SYSTEM_COMMENT);
                                reviewHeaders.setHrComment(SYSTEM_COMMENT);
                                reviewHeaders.setRating(SYSTEM_NUMERIC_RATING);
                            } else if (reviewHeaders.getReviewState() == STAGE_EMPLOYEE_SIGNOFF) {
                                List<ReviewLine> employeeReviews = ReviewLineLocalServiceUtil.findReviewLineByReviewId(reviewHeaders.getReviewId());
                                for (ReviewLine review : employeeReviews) {
                                    ReviewLine reviewLine = ReviewLineLocalServiceUtil.fetchReviewLine(review.getLineId());
                                    reviewLine.setHrRating(SYSTEM_RATING);
                                    reviewLine.setHrComment(SYSTEM_COMMENT);
                                    reviewHeaders.setRating(SYSTEM_NUMERIC_RATING);
                                    ReviewLineLocalServiceUtil.updateReviewLine(reviewLine);
                                }
                                reviewHeaders.setStage3Date(getStartOfDayDate(today));
                                reviewHeaders.setStage4Date(getStartOfDayDate(today));
                                reviewHeaders.setHrComment(SYSTEM_COMMENT);
                            } else {
                                List<ReviewLine> employeeReviews = ReviewLineLocalServiceUtil.findReviewLineByReviewId(reviewHeaders.getReviewId());
                                for (ReviewLine review : employeeReviews) {
                                    ReviewLine reviewLine = ReviewLineLocalServiceUtil.fetchReviewLine(review.getLineId());
                                    reviewLine.setHrRating(SYSTEM_RATING);
                                    reviewLine.setHrComment(SYSTEM_COMMENT);
                                    reviewHeaders.setRating(SYSTEM_NUMERIC_RATING);
                                    ReviewLineLocalServiceUtil.updateReviewLine(reviewLine);
                                }
                                reviewHeaders.setStage4Date(getStartOfDayDate(today));
                                reviewHeaders.setHrComment(SYSTEM_COMMENT);
                            }
                        } else {
                            List<ReviewLine> employeeReviews = ReviewLineLocalServiceUtil.findReviewLineByReviewId(reviewHeaders.getReviewId());
                            for (ReviewLine review : employeeReviews) {
                                ReviewLine reviewLine = ReviewLineLocalServiceUtil.fetchReviewLine(review.getLineId());
                                reviewLine.setManagerRating(SYSTEM_RATING);
                                reviewLine.setManagerComment(SYSTEM_COMMENT);
                                reviewLine.setHrRating(SYSTEM_RATING);
                                reviewLine.setHrComment(SYSTEM_COMMENT);
                                ReviewLineLocalServiceUtil.updateReviewLine(reviewLine);
                            }
                            reviewHeaders.setImprovementComment(SYSTEM_COMMENT);
                            reviewHeaders.setManagerComment(SYSTEM_COMMENT);
                            reviewHeaders.setHrComment(SYSTEM_COMMENT);
                            reviewHeaders.setRating(SYSTEM_NUMERIC_RATING);
                            if (reviewHeaders.getReviewState() == STAGE_SELF_REVIEW) {
                                reviewHeaders.setStage1Date(getStartOfDayDate(today));
                                reviewHeaders.setStage2Date(getStartOfDayDate(today));
                            } else {
                                reviewHeaders.setStage2Date(getStartOfDayDate(today));
                            }
                        }
                        reviewHeaders.setReviewState(STAGE_COMPLETE);
                        ReviewLocalServiceUtil.updateReview(reviewHeaders);
                    }
                }
            }
        } catch (PortalException ex) {
            ex.printStackTrace();
        }
    }

    public static void checkNewJoinees(LocalDateTime date) {
        log.info("date "+getStartOfDayDate(date.minusDays(10).toLocalDate()));
        List<Employee> newEmployeeList1 = EmployeeLocalServiceUtil.findByDoj(getStartOfDayDate(date.minusDays(10).toLocalDate())).stream().filter(e -> e.getEmployeeType().equalsIgnoreCase(FULLTIME)).collect(Collectors.toList());
        if(newEmployeeList1.isEmpty()){
            log.info("empty");
        }
        for (Employee employeeData : newEmployeeList1) {
            log.info(employeeData.getEcode());
            Email employeeEmail = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
            employeeEmail.setBody(MessageFormat.format(BODY_KPI_NEW_JOINEE, LINE_KPI_NEW_JOINEE, KPI_DOCUMENT, getPortalUrl() + URL_PERFORMANCE));
            employeeEmail.setSubject(SUBJECT_KPI_NEW_JOINEE);
            employeeEmail.setToAddress(employeeData.getEmail());
            employeeEmail.setCreatedDate(getIstDate());
            employeeEmail.setModule(MODULE_PERFORMANCE);
            employeeEmail.setCreatedDate(getIstDate());
            employeeEmail.setScheduled(true);
            employeeEmail.setSent(false);
            EmailLocalServiceUtil.addEmail(employeeEmail);
        }

        List<Employee> newEmployeeList2 = EmployeeLocalServiceUtil.findByDoj(getStartOfDayDate(date.minusDays(15).toLocalDate())).stream().filter(e -> e.getEmployeeType().equalsIgnoreCase(FULLTIME)).collect(Collectors.toList());
        for (Employee employeeData : newEmployeeList2) {
            List<Kpi> employeeKpis = KpiLocalServiceUtil.findKpiByEcode(employeeData.getEcode());
            Employee managerData = EmployeeLocalServiceUtil.fetchEmployee(employeeData.getManager());
            if (employeeKpis.isEmpty()) {
                Email employeeEmail = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
                employeeEmail.setBody(MessageFormat.format(BODY_KPI_NEW_JOINEE, LINE_KPI_NEW_JOINEE_REMINDER, KPI_DOCUMENT, getPortalUrl() + URL_PERFORMANCE));
                employeeEmail.setSubject(REMINDER + SUBJECT_KPI_NEW_JOINEE);
                employeeEmail.setToAddress(employeeData.getEmail());
                employeeEmail.setCcAddress(null != managerData ? managerData.getEmail() : BLANK);
                employeeEmail.setModule(MODULE_PERFORMANCE);
                employeeEmail.setCreatedDate(getIstDate());
                employeeEmail.setScheduled(true);
                employeeEmail.setSent(false);
                EmailLocalServiceUtil.addEmail(employeeEmail);
            }
        }

        List<Employee> newEmployeeList3 = EmployeeLocalServiceUtil.findByDoj(getStartOfDayDate(date.minusDays(20).toLocalDate())).stream().filter(e -> e.getEmployeeType().equalsIgnoreCase(FULLTIME)).collect(Collectors.toList());
        for (Employee employeeData : newEmployeeList3) {
            List<Kpi> employeeKpis = KpiLocalServiceUtil.findKpiByEcode(employeeData.getEcode());
            Employee managerData = EmployeeLocalServiceUtil.fetchEmployee(employeeData.getManager());
            if (employeeKpis.isEmpty()) {
                Email employeeEmail = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
                employeeEmail.setBody(MessageFormat.format(BODY_KPI_NEW_JOINEE, LINE_KPI_NEW_JOINEE_FINAL_REMINDER, KPI_DOCUMENT, getPortalUrl() + URL_PERFORMANCE));
                employeeEmail.setSubject(REMINDER + SUBJECT_KPI_NEW_JOINEE);
                employeeEmail.setToAddress(employeeData.getEmail());
                employeeEmail.setCcAddress(DL_HR + COMMA + (null != managerData ? managerData.getEmail() : BLANK));
                employeeEmail.setModule(MODULE_PERFORMANCE);
                employeeEmail.setCreatedDate(getIstDate());
                employeeEmail.setScheduled(true);
                employeeEmail.setSent(false);
                EmailLocalServiceUtil.addEmail(employeeEmail);
            }
        }
    }

    public static void quarterlyEmail(LocalDateTime now) {
//        if( (cal.get(Calendar.DAY_OF_MONTH)  == 1) && ((cal.get(Calendar.MONTH) == Calendar.JANUARY) || (cal.get(Calendar.MONTH) == Calendar.APRIL) || (cal.get(Calendar.MONTH) == Calendar.JULY) || (cal.get(Calendar.MONTH) == Calendar.OCTOBER)))
        if ((now.getDayOfMonth() == 1) && ((now.getMonth().equals(Month.JANUARY)) || (now.getMonth().equals(Month.APRIL)) || (now.getMonth().equals(Month.JULY)) || (now.getMonth().equals(Month.OCTOBER)))) {
            Email trantorIndiaBean = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
            trantorIndiaBean.setBody(MessageFormat.format(BODY_KPI_UPDATE, getPortalUrl() + URL_PERFORMANCE));
            trantorIndiaBean.setSubject(SUBJECT_KPI_UPDATE);
            trantorIndiaBean.setBccAddress(DL_TRANTORINDIA);
            trantorIndiaBean.setModule(MODULE_PERFORMANCE);
            trantorIndiaBean.setCreatedDate(getIstDate());
            trantorIndiaBean.setScheduled(true);
            trantorIndiaBean.setSent(false);
            EmailLocalServiceUtil.addEmail(trantorIndiaBean);
        }
    }

}
