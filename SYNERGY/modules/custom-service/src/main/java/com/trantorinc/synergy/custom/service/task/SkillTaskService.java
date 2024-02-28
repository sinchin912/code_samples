package com.trantorinc.synergy.custom.service.task;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.trantorinc.synergy.email.core.model.Email;
import com.trantorinc.synergy.email.core.service.EmailLocalServiceUtil;
import com.trantorinc.synergy.lms.core.model.Employee;
import com.trantorinc.synergy.lms.core.model.Scheduler;
import com.trantorinc.synergy.lms.core.service.EmployeeLocalServiceUtil;
import com.trantorinc.synergy.lms.core.service.SchedulerLocalServiceUtil;
import com.trantorinc.synergy.skill.core.model.Skill;
import com.trantorinc.synergy.skill.core.service.SkillLocalServiceUtil;

import java.text.MessageFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.trantorinc.synergy.common.service.constant.AppConstantService.*;
import static com.trantorinc.synergy.common.service.util.UtilService.*;
import static com.trantorinc.synergy.common.service.util.UtilService.getIstDate;
import static com.trantorinc.synergy.custom.service.constants.SynergyServicePortletKeys.*;
import static com.trantorinc.synergy.custom.service.constants.SynergyServicePortletKeys.BODY_NEW_JOINEE_REMINDER;

public class SkillTaskService {

    private static final Log log = LogFactoryUtil.getLog(SkillTaskService.class.getName());

    public static void executeAm(LocalDateTime now) {
        log.info(MODULE_SKILL + " task started !");
        String schedulerName = TASK_AM_SKILL;
        Scheduler scheduler = SchedulerLocalServiceUtil.findSchedulerByNameAndDate(schedulerName, getStartOfDayDate(now.toLocalDate()));
        if (null == scheduler) {
            scheduler = SchedulerLocalServiceUtil.createScheduler(CounterLocalServiceUtil.increment());
            scheduler.setName(schedulerName);
            scheduler.setStatus(false);
            scheduler.setOnDate(getStartOfDayDate(now.toLocalDate()));
            scheduler.setRunDate(convertLocalDateTimeToDate(now));
            SchedulerLocalServiceUtil.addScheduler(scheduler);
        } else {
            scheduler.setStatus(false);
            scheduler.setRunDate(convertLocalDateTimeToDate(now));
            SchedulerLocalServiceUtil.updateScheduler(scheduler);
        }
        log.info(MODULE_SKILL + " task preset !!");

        dailyUpdateLms();
        log.info(MODULE_SKILL + " task completed ...(1/1)");

        scheduler.setStatus(true);
        SchedulerLocalServiceUtil.updateScheduler(scheduler);
        log.info(MODULE_SKILL + " task completed !!!");
    }

    public static void executePm(LocalDateTime now) {
        log.info(MODULE_SKILL + " task started !");
        String schedulerName = TASK_PM_SKILL;
        Scheduler scheduler = SchedulerLocalServiceUtil.findSchedulerByNameAndDate(schedulerName, getStartOfDayDate(now.toLocalDate()));
        if (null == scheduler) {
            scheduler = SchedulerLocalServiceUtil.createScheduler(CounterLocalServiceUtil.increment());
            scheduler.setName(schedulerName);
            scheduler.setStatus(false);
            scheduler.setOnDate(getStartOfDayDate(now.toLocalDate()));
            scheduler.setRunDate(convertLocalDateTimeToDate(now));
            SchedulerLocalServiceUtil.addScheduler(scheduler);
        } else {
            scheduler.setStatus(false);
            scheduler.setRunDate(convertLocalDateTimeToDate(now));
            SchedulerLocalServiceUtil.updateScheduler(scheduler);
        }
        log.info(MODULE_SKILL + " task preset !!");

        checkNewJoinees(now);
        log.info(MODULE_SKILL + " task completed ...(1/4)");

        dailyReminderMail(now);
        log.info(MODULE_SKILL + " task completed ...(2/4)");

        quarterlyEmail(now);
        log.info(MODULE_SKILL + " task completed ...(3/4)");

        weeklyEmail(now);
        log.info(MODULE_SKILL + " task completed ...(4/4)");

        scheduler.setStatus(true);
        SchedulerLocalServiceUtil.updateScheduler(scheduler);
        log.info(MODULE_SKILL + " task completed !!!");
    }

    public static void dailyUpdateLms() {
        List<Employee> activeEmployee = EmployeeLocalServiceUtil.findAllActiveEmployees();
        for (Employee employeeData : activeEmployee) {
            List<Skill> employeeSkill = SkillLocalServiceUtil.findSkillsByEmployee(employeeData.getEcode());
            List<Employee> reviewerDetails = activeEmployee.stream().filter(e -> e.getEcode().equalsIgnoreCase(employeeData.getReviewer())).collect(Collectors.toList());
            List<Employee> managerDetails = activeEmployee.stream().filter(e -> e.getEcode().equalsIgnoreCase(employeeData.getManager())).collect(Collectors.toList());
            if (!employeeSkill.isEmpty() && !reviewerDetails.isEmpty() && !managerDetails.isEmpty()) {
                for (Skill empSkill : employeeSkill) {
                    if (!empSkill.getReviewer().equalsIgnoreCase(reviewerDetails.get(0).getEmail()) || !empSkill.getManager().equalsIgnoreCase(managerDetails.get(0).getEmail())) {
                        if (!empSkill.getReviewer().equalsIgnoreCase(reviewerDetails.get(0).getEmail())) {
                            empSkill.setReviewer(reviewerDetails.get(0).getEmail());
                        }
                        if (!empSkill.getManager().equalsIgnoreCase(managerDetails.get(0).getEmail())) {
                            empSkill.setManager(managerDetails.get(0).getEmail());
                        }
                        SkillLocalServiceUtil.updateSkill(empSkill);
                    }
                }
            }
        }

    }

    public static void quarterlyEmail(LocalDateTime now) {
        if ((now.getDayOfMonth() == 1) && ((now.getMonth().equals(Month.JANUARY)) || (now.getMonth().equals(Month.APRIL)) || (now.getMonth().equals(Month.JULY)) || (now.getMonth().equals(Month.OCTOBER)))) {
            Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
            email.setSubject(SUBJECT_SKILL_QUATERLY);
            email.setBody(MessageFormat.format(BODY_SKILL_QUATERLY, getPortalUrl() + URL_SKILL));
            email.setBccAddress(DL_TRANTORINDIA);
            email.setModule(MODULE_SKILL);
            email.setScheduled(true);
            email.setSent(false);
            email.setCreatedDate(getIstDate());
            EmailLocalServiceUtil.addEmail(email);
        }
    }

    public static void weeklyEmail(LocalDateTime now) {
        if (now.getDayOfWeek() == DayOfWeek.MONDAY) {
            Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
            email.setSubject(SUBJECT_SKILL_WEEKLY);
            email.setBody(MessageFormat.format(BODY_SKILL_WEEKLY, getPortalUrl() + URL_SKILL_ADMIN));
            email.setBccAddress(DL_MANAGER + COMMA + DL_REVIEWER);
            email.setModule(MODULE_SKILL);
            email.setScheduled(true);
            email.setSent(false);
            email.setCreatedDate(getIstDate());
            EmailLocalServiceUtil.addEmail(email);
        }
    }

    public static void checkNewJoinees(LocalDateTime now) {
        LocalDate tenDaysBeforeToday = now.minusDays(10).toLocalDate();
        List<Employee> allActiveEmployees = EmployeeLocalServiceUtil.findAllActiveEmployees();
        List<Employee> tenDaysActiveEmployees = allActiveEmployees.stream().filter(s -> convertDateToLocalDateTime(s.getDoj()).toLocalDate().equals(tenDaysBeforeToday)).collect(Collectors.toList());
        for (Employee employee : tenDaysActiveEmployees) {
            List<Employee> managerData = allActiveEmployees.stream().filter(s -> s.getEcode().equalsIgnoreCase(employee.getManager())).collect(Collectors.toList());
            Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
            email.setSubject(REMINDER + SUBJECT_NEW_JOINEE);
            email.setBody(MessageFormat.format(BODY_NEW_JOINEE, getPortalUrl() + URL_SKILL));
            email.setToAddress(employee.getEmail());
            if (!managerData.isEmpty()) {
                email.setCcAddress(managerData.get(0).getEmail());
            }
            email.setModule(MODULE_SKILL);
            email.setScheduled(true);
            email.setSent(false);
            email.setCreatedDate(getIstDate());
            EmailLocalServiceUtil.addEmail(email);
        }
        LocalDate fifteenDaysBeforeToday = tenDaysBeforeToday.minusDays(5);
        List<Employee> fifteenDaysActiveEmployees = allActiveEmployees.stream().filter(s -> convertDateToLocalDateTime(s.getDoj()).toLocalDate().equals(fifteenDaysBeforeToday)).collect(Collectors.toList());
        for (Employee employee : fifteenDaysActiveEmployees) {
            List<Employee> managerData = allActiveEmployees.stream().filter(s -> s.getEcode().equalsIgnoreCase(employee.getManager())).collect(Collectors.toList());
            Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
            email.setSubject(REMINDER + SUBJECT_NEW_JOINEE);
            email.setBody(MessageFormat.format(BODY_NEW_JOINEE, getPortalUrl() + URL_SKILL));
            email.setToAddress(employee.getEmail());
            if (!managerData.isEmpty()) {
                email.setCcAddress(managerData.get(0).getEmail());
            }
            email.setModule(MODULE_SKILL);
            email.setScheduled(true);
            email.setSent(false);
            email.setCreatedDate(getIstDate());
            EmailLocalServiceUtil.addEmail(email);
        }

    }

    public static void dailyReminderMail(LocalDateTime now) {
        LocalDate twentyDaysBeforeToday = now.minusDays(20).toLocalDate();
        LocalDate joiningAfterDate = LocalDate.parse(DATE_SKILL_REMINDER_DOJ_POST, FORMATTER_YYYY_MM_DD);
        List<Employee> allActiveEmployees = EmployeeLocalServiceUtil.findAllActiveEmployees();
        List<Employee> twentyDaysActiveEmployees = allActiveEmployees.stream().filter(s -> convertDateToLocalDateTime(s.getDoj()).toLocalDate().isAfter(joiningAfterDate) && convertDateToLocalDateTime(s.getDoj()).toLocalDate().isBefore(twentyDaysBeforeToday)).filter(s -> Arrays.stream(noSkillProjectList).noneMatch(s.getAccount().toUpperCase()::contains)).collect(Collectors.toList());
        for (Employee employee : twentyDaysActiveEmployees) {
            List<Skill> skillList = SkillLocalServiceUtil.findSkillsByEmployee(employee.getEcode());
            if (skillList.isEmpty() && !employee.getEmail().isEmpty()&& !employee.getEmail().equalsIgnoreCase(BLANK)) {
                List<Employee> managerData = allActiveEmployees.stream().filter(s -> s.getEcode().equalsIgnoreCase(employee.getManager())).collect(Collectors.toList());
                Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
                email.setSubject(DAILY_REMINDER + SUBJECT_NEW_JOINEE);
                email.setBody(MessageFormat.format(BODY_NEW_JOINEE_REMINDER, getPortalUrl() + URL_SKILL));
                email.setToAddress(employee.getEmail());
                if (!managerData.isEmpty()) {
                    email.setCcAddress(managerData.get(0).getEmail() + COMMA + DL_HR);
                } else {
                    email.setCcAddress(DL_HR);
                }
                email.setModule(MODULE_SKILL);
                email.setScheduled(true);
                email.setSent(false);
                email.setCreatedDate(getIstDate());
                EmailLocalServiceUtil.addEmail(email);
            }
        }

    }
}

