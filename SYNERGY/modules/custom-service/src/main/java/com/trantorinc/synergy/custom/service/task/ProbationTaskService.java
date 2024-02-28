package com.trantorinc.synergy.custom.service.task;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.trantorinc.synergy.lms.core.model.Employee;
import com.trantorinc.synergy.lms.core.model.Scheduler;
import com.trantorinc.synergy.lms.core.service.EmployeeLocalServiceUtil;
import com.trantorinc.synergy.email.core.model.Email;
import com.trantorinc.synergy.email.core.service.EmailLocalServiceUtil;
import com.trantorinc.synergy.lms.core.service.SchedulerLocalServiceUtil;
import com.trantorinc.synergy.probation.core.model.Probation;
import com.trantorinc.synergy.probation.core.service.ProbationLocalServiceUtil;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.trantorinc.synergy.common.service.constant.AppConstantService.*;
import static com.trantorinc.synergy.common.service.util.UtilService.*;
import static com.trantorinc.synergy.common.service.util.UtilService.getPortalUrl;
import static com.trantorinc.synergy.custom.service.constants.SynergyServicePortletKeys.*;


public class ProbationTaskService {

    private static final Log log = LogFactoryUtil.getLog(ProbationTaskService.class.getName());

    private ProbationTaskService(){

    }

    public static void executeAm(LocalDateTime now){
        log.info(MODULE_PROBATION + " task started !");
        String schedulerName = TASK_AM_PROBATION;
        Scheduler scheduler = SchedulerLocalServiceUtil.findSchedulerByNameAndDate(schedulerName, getStartOfDayDate(now.toLocalDate()));
        if(null == scheduler){
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
        log.info(MODULE_PROBATION + " task preset !!");

        updateProbation(now);
        log.info(MODULE_PROBATION + " task completed ...(1/7)");

        sendInitMail(now);
        log.info(MODULE_PROBATION + " task completed ...(2/7)");

        sendFirstReminderEmail(now);
        log.info(MODULE_PROBATION + " task completed ...(3/7)");

        sendSecondReminderEmail(now);
        log.info(MODULE_PROBATION + " task completed ...(4/7)");

        sendSubmittedHrMail(now);
        log.info(MODULE_PROBATION + " task completed ...(5/7)");

        sendDailyReminderEmail60(now);
        log.info(MODULE_PROBATION + " task completed ...(6/7)");

        sendDailyReminderEmailTwoTimes65(now);
        log.info(MODULE_PROBATION + " task completed ...(7/7)");

        scheduler.setStatus(true);
        SchedulerLocalServiceUtil.updateScheduler(scheduler);
        log.info(MODULE_PROBATION + " task completed !!!");
    }


    public static void executePm(LocalDateTime now){
        log.info(MODULE_PROBATION + " task started !");
        String schedulerName = TASK_PM_PROBATION;
        Scheduler scheduler = SchedulerLocalServiceUtil.findSchedulerByNameAndDate(schedulerName, getStartOfDayDate(now.toLocalDate()));
        if(null == scheduler){
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
        log.info(MODULE_PROBATION + " task preset !!");

        sendDailyReminderEmailTwoTimes65(now);
        log.info(MODULE_PROBATION + " task completed ...(1/1)");

        scheduler.setStatus(true);
        SchedulerLocalServiceUtil.updateScheduler(scheduler);
        log.info(MODULE_PROBATION + " task completed !!!");
    }

    private static void updateProbation(LocalDateTime now) {
        List<Employee> allEmployeeList = EmployeeLocalServiceUtil.findAllEmployees();
        List<Probation> allProbationList = ProbationLocalServiceUtil.getProbations(-1,-1);

        // Auto update extended \ revised probations
        List<Probation>  extendedProbation = allProbationList.stream().filter(p -> (p.getState()==3 || p.getState()==4)).filter(p -> convertDateToLocalDateTime(p.getAlertDate()).format(FORMATTER_YYYY_MM_DD).equals(now.format(FORMATTER_YYYY_MM_DD))).collect(Collectors.toList());
        for(Probation data : extendedProbation) {
            data.setState(0);
            data.setStateName("Pending");
            data.setCreateDate(getStartOfDayDate(now.toLocalDate()));
            data.setUpdateDate(getStartOfDayDate(now.toLocalDate()));
            ProbationLocalServiceUtil.updateProbation(data);
        }

        Set<String> managerList = extendedProbation.stream().map(Probation::getManager).collect(Collectors.toSet());
        for(String managerEmail : managerList)
        {
            Employee managerData = allEmployeeList.stream().filter(s -> s.getEmail().equalsIgnoreCase(managerEmail)).collect(Collectors.toList()).get(0);
            List<Probation> managerProbationList = extendedProbation.stream().filter(c -> c.getManager().equalsIgnoreCase(managerEmail)).collect(Collectors.toList());
            StringBuilder tableContent = new StringBuilder(TABLE_START_PROBATION);
            for(Probation managerEntry : managerProbationList){
                Employee employeeData = allEmployeeList.stream().filter(s -> s.getEcode().equalsIgnoreCase(managerEntry.getEcode())).collect(Collectors.toList()).get(0);
                tableContent.append(ROW_START);
                tableContent.append(managerEntry.getEcode());
                tableContent.append(CELL_SEPARATOR);
                tableContent.append(employeeData.getName());
                tableContent.append(CELL_SEPARATOR);
                tableContent.append(managerData.getName());
                tableContent.append(CELL_SEPARATOR);
                tableContent.append(now.format(FORMATTER_YYYY_MM_DD));
                tableContent.append(ROW_END);
            }
            tableContent.append(TABLE_END);

            Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
            email.setToAddress(DL_HR);
            email.setCcAddress(managerEmail);
            email.setSubject(SUBJECT_PROBATION_AUTO);
            email.setBody(MessageFormat.format(BODY_PROBATION_AUTO, managerData.getName(), tableContent.toString()));
            email.setModule(MODULE_PROBATION);
            email.setScheduled(true);
            email.setSent(false);
            email.setCreatedDate(convertLocalDateTimeToDate(now));
            EmailLocalServiceUtil.addEmail(email);
        }

        //Auto update manager \ reviewer
        List<Probation> probationList = allProbationList.stream().filter(s -> (s.getState() == 0)).collect(Collectors.toList());
        for(Probation data : probationList)
        {
            Employee employees = allEmployeeList.stream().filter(s -> s.getEcode().equalsIgnoreCase(data.getEcode())).collect(Collectors.toList()).get(0);
            Employee managers = allEmployeeList.stream().filter(s -> s.getEcode().equalsIgnoreCase(employees.getManager())).collect(Collectors.toList()).get(0);
            if(!data.getManager().equalsIgnoreCase(managers.getEmail()))
            {
                Probation probation = ProbationLocalServiceUtil.fetchProbation(data.getEcode());
                probation.setManager(managers.getEmail());
                ProbationLocalServiceUtil.updateProbation(probation);
                String tableContent = TABLE_START_PROBATION + ROW_START +
                        employees.getEcode() +
                        CELL_SEPARATOR +
                        employees.getName() +
                        CELL_SEPARATOR +
                        managers.getName() +
                        CELL_SEPARATOR +
                        now.format(FORMATTER_YYYY_MM_DD) +
                        ROW_END +
                        TABLE_END;

                Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
                email.setToAddress(managers.getEmail());
                email.setCcAddress(DL_HR);
                email.setSubject(SUBJECT_PROBATION_INITIATED);
                email.setBody(MessageFormat.format(BODY_PROBATION_INITIATED, managers.getName(), getPortalUrl() + URL_PROBATION_ADMIN, tableContent));
                email.setModule(MODULE_PROBATION);
                email.setScheduled(true);
                email.setSent(false);
                email.setCreatedDate(convertLocalDateTimeToDate(now));
                EmailLocalServiceUtil.addEmail(email);
            }
            if(data.getReviewer() != null)
            {
                Employee reviewers = allEmployeeList.stream().filter(s -> s.getEcode().equalsIgnoreCase(employees.getReviewer())).collect(Collectors.toList()).get(0);
                if(!data.getReviewer().equalsIgnoreCase(reviewers.getEmail()))
                {
                    Probation probation = ProbationLocalServiceUtil.fetchProbation(data.getEcode());
                    probation.setReviewer(reviewers.getEmail());
                    ProbationLocalServiceUtil.updateProbation(probation);
                    String tableContent = TABLE_START_PROBATION + ROW_START +
                            employees.getEcode() +
                            CELL_SEPARATOR +
                            employees.getName() +
                            CELL_SEPARATOR +
                            managers.getName() +
                            CELL_SEPARATOR +
                            now.format(FORMATTER_YYYY_MM_DD) +
                            ROW_END +
                            TABLE_END;

                    Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
                    email.setToAddress(reviewers.getEmail());
                    email.setCcAddress(DL_HR);
                    email.setSubject(SUBJECT_PROBATION_INITIATED);
                    email.setBody(MessageFormat.format(BODY_PROBATION_INITIATED, managers.getName(), getPortalUrl()+ URL_PROBATION_ADMIN, tableContent));
                    email.setModule(MODULE_PROBATION);
                    email.setScheduled(true);
                    email.setSent(false);
                    email.setCreatedDate(convertLocalDateTimeToDate(now));
                    EmailLocalServiceUtil.addEmail(email);
                }
            }
        }
    }

    private static void sendInitMail(LocalDateTime now) {
        LocalDateTime fiftyDaysBefore = now.minusDays(50);
        List<Employee> activeEmployees = EmployeeLocalServiceUtil.findAllActiveEmployees();
        List<Employee> newEmployees = activeEmployees.stream().filter(s -> convertDateToLocalDateTime(s.getDoj()).format(FORMATTER_YYYY_MM_DD).equalsIgnoreCase(fiftyDaysBefore.format(FORMATTER_YYYY_MM_DD))).filter(e -> e.getEmployeeType().equalsIgnoreCase(FTE)).filter(e -> !e.isConfirmed()).collect(Collectors.toList());
        Set<String> newProbations = new HashSet<>();
        for(Employee employee : newEmployees) {
            Probation existingProbation = ProbationLocalServiceUtil.fetchProbation(employee.getEcode());
            if(null == existingProbation){
                Probation probation = ProbationLocalServiceUtil.createProbation(employee.getEcode());
                probation.setCreateDate(getStartOfDayDate(now.toLocalDate()));
                probation.setUpdateDate(getStartOfDayDate(now.toLocalDate()));
                probation.setState(0);
                probation.setAlertDate(getStartOfDayDate(now.toLocalDate()));
                Employee managerData = activeEmployees.stream().filter(s -> s.getEcode().equalsIgnoreCase(employee.getManager())).collect(Collectors.toList()).get(0);
                Employee reviewerData = activeEmployees.stream().filter(s -> s.getEcode().equalsIgnoreCase(employee.getReviewer())).collect(Collectors.toList()).get(0);
                probation.setManager(managerData.getEmail());
                probation.setReviewer(reviewerData.getEmail());
                probation.setStateName("Pending");
                ProbationLocalServiceUtil.addProbation(probation);
                newProbations.add(employee.getEcode());
            }
        }
        Set<String> managerEcodes = newEmployees.stream().map(Employee::getManager).collect(Collectors.toSet());
        for(String managerEcode : managerEcodes)
        {
            Employee manager = activeEmployees.stream().filter(s -> s.getEcode().equalsIgnoreCase(managerEcode)).collect(Collectors.toList()).get(0);
            List<Employee> managerProbationList = newEmployees.stream().filter(c -> c.getManager().equalsIgnoreCase(managerEcode)).filter(c -> newProbations.contains(c.getEcode())).collect(Collectors.toList());
            StringBuilder tableContent = new StringBuilder(TABLE_START_PROBATION);
            for(Employee employeeData : managerProbationList){
                tableContent.append(ROW_START);
                tableContent.append(employeeData.getEcode());
                tableContent.append(CELL_SEPARATOR);
                tableContent.append(employeeData.getName());
                tableContent.append(CELL_SEPARATOR);
                tableContent.append(manager.getName());
                tableContent.append(CELL_SEPARATOR);
                tableContent.append(now.format(FORMATTER_YYYY_MM_DD));
                tableContent.append(ROW_END);
            }
            tableContent.append(TABLE_END);

            Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
            email.setToAddress(manager.getEmail());
            email.setCcAddress(DL_HR);
            email.setSubject(SUBJECT_PROBATION_INITIATED);
            email.setBody(MessageFormat.format(BODY_PROBATION_INITIATED, manager.getName(), getPortalUrl() + URL_PROBATION_ADMIN, tableContent));
            email.setModule(MODULE_PROBATION);
            email.setScheduled(true);
            email.setSent(false);
            email.setCreatedDate(convertLocalDateTimeToDate(now));
            EmailLocalServiceUtil.addEmail(email);
        }
    }

    private static void sendFirstReminderEmail(LocalDateTime now) {
        LocalDateTime fiveDaysBefore = now.minusDays(5);
        List<Employee> allEmployeeList = EmployeeLocalServiceUtil.findAllEmployees();
        List<Probation> probations = ProbationLocalServiceUtil.findPendingByDate(getStartOfDayDate(fiveDaysBefore.toLocalDate()));
        Set<String> managerList = probations.stream().map(Probation::getManager).collect(Collectors.toSet());
        for(String managerEmail : managerList){
            List<Probation> managerProbationList = probations.stream().filter(c -> c.getManager().equalsIgnoreCase(managerEmail)).collect(Collectors.toList());
            StringBuilder tableContent = new StringBuilder(TABLE_START_PROBATION);
            Employee managerData = allEmployeeList.stream().filter(s -> s.getEmail().equalsIgnoreCase(managerEmail)).collect(Collectors.toList()).get(0);
            for(Probation probation : managerProbationList){
                Employee employeeData = allEmployeeList.stream().filter(s -> s.getEcode().equalsIgnoreCase(probation.getEcode())).collect(Collectors.toList()).get(0);
                tableContent.append(ROW_START);
                tableContent.append(probation.getEcode());
                tableContent.append(CELL_SEPARATOR);
                tableContent.append(employeeData.getName());
                tableContent.append(CELL_SEPARATOR);
                tableContent.append(managerData.getName());
                tableContent.append(CELL_SEPARATOR);
                tableContent.append(convertDateToLocalDateTime(probation.getCreateDate()).format(FORMATTER_YYYY_MM_DD));
                tableContent.append(ROW_END);
            }
            tableContent.append(TABLE_END);

            Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
            email.setToAddress(managerEmail);
            email.setSubject(REMINDER+SUBJECT_PROBATION_INITIATED);
            email.setBody(MessageFormat.format(BODY_PROBATION_INITIATED, managerData.getName(), getPortalUrl() + URL_PROBATION_ADMIN, tableContent));
            email.setModule(MODULE_PROBATION);
            email.setScheduled(true);
            email.setSent(false);
            email.setCreatedDate(convertLocalDateTimeToDate(now));
            EmailLocalServiceUtil.addEmail(email);
        }
    }

    private static void sendSecondReminderEmail(LocalDateTime now) {
        LocalDateTime sevenDaysBefore = now.minusDays(7);
        List<Employee> allEmployeeList = EmployeeLocalServiceUtil.findAllEmployees();
        List<Probation> probations = ProbationLocalServiceUtil.findPendingByDate(getStartOfDayDate(sevenDaysBefore.toLocalDate()));
        Set<String> managerList = probations.stream().map(Probation::getManager).collect(Collectors.toSet());
        for(String managerEmail : managerList)
        {
            List<Probation> managerProbationList = probations.stream().filter(c -> c.getManager().equalsIgnoreCase(managerEmail)).collect(Collectors.toList());
            StringBuilder tableContent = new StringBuilder(TABLE_START_PROBATION);
            Employee managerData = allEmployeeList.stream().filter(s -> s.getEmail().equalsIgnoreCase(managerEmail)).collect(Collectors.toList()).get(0);
            for(Probation probation : managerProbationList){
                Employee employeeData = allEmployeeList.stream().filter(s -> s.getEcode().equalsIgnoreCase(probation.getEcode())).collect(Collectors.toList()).get(0);
                tableContent.append(ROW_START);
                tableContent.append(probation.getEcode());
                tableContent.append(CELL_SEPARATOR);
                tableContent.append(employeeData.getName());
                tableContent.append(CELL_SEPARATOR);
                tableContent.append(managerData.getName());
                tableContent.append(CELL_SEPARATOR);
                tableContent.append(convertDateToLocalDateTime(probation.getCreateDate()).format(FORMATTER_YYYY_MM_DD));
                tableContent.append(ROW_END);
            }
            tableContent.append(TABLE_END);

            Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
            email.setToAddress(managerEmail);
            email.setCcAddress(DL_HR);
            email.setSubject(REMINDER+SUBJECT_PROBATION_INITIATED);
            email.setBody(MessageFormat.format(BODY_PROBATION_INITIATED, managerData.getName(), getPortalUrl() + URL_PROBATION_ADMIN, tableContent));
            email.setModule(MODULE_PROBATION);
            email.setScheduled(true);
            email.setSent(false);
            email.setCreatedDate(convertLocalDateTimeToDate(now));
            EmailLocalServiceUtil.addEmail(email);
        }
    }

    private static void sendSubmittedHrMail(LocalDateTime now) {
        LocalDateTime oneDaysBefore = now.minusDays(1);
        List<Probation> probations = ProbationLocalServiceUtil.findCompletedByDate(getStartOfDayDate(oneDaysBefore.toLocalDate()));
        StringBuilder tableContent = new StringBuilder(TABLE_START_PROBATION_RECEIVED);
        for(Probation submittedProbation : probations){
            Employee employeeData = EmployeeLocalServiceUtil.fetchEmployee(submittedProbation.getEcode());
            Employee managerData = EmployeeLocalServiceUtil.findByEmail(submittedProbation.getManager());
            tableContent.append(ROW_START);
            tableContent.append(submittedProbation.getEcode());
            tableContent.append(CELL_SEPARATOR);
            tableContent.append(employeeData.getName());
            tableContent.append(CELL_SEPARATOR);
            tableContent.append(employeeData.getEmail());
            tableContent.append(CELL_SEPARATOR);
            tableContent.append(convertDateToLocalDateTime(employeeData.getDoj()).format(FORMATTER_YYYY_MM_DD));
            tableContent.append(CELL_SEPARATOR);
            tableContent.append(managerData.getName());
            tableContent.append(CELL_SEPARATOR);
            tableContent.append(submittedProbation.getStateName());
            tableContent.append(CELL_SEPARATOR);
            tableContent.append(convertDateToLocalDateTime(submittedProbation.getCreateDate()).format(FORMATTER_YYYY_MM_DD));
            tableContent.append(CELL_SEPARATOR);
            tableContent.append(convertDateToLocalDateTime(submittedProbation.getUpdateDate()).format(FORMATTER_YYYY_MM_DD));
            tableContent.append(CELL_SEPARATOR);
            tableContent.append(employeeData.getProject());
            tableContent.append(CELL_SEPARATOR);
            tableContent.append(submittedProbation.getComment());
            tableContent.append(ROW_END);
        }
        tableContent.append(TABLE_END);
        if(!probations.isEmpty()){
            Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
            email.setToAddress(DL_HR);
            email.setSubject(SUBJECT_PROBATION_RECEIVED);
            email.setBody(MessageFormat.format(BODY_PROBATION_RECEIVED,  tableContent.toString()));
            email.setModule(MODULE_PROBATION);
            email.setScheduled(true);
            email.setSent(false);
            email.setCreatedDate(convertLocalDateTimeToDate(now));
            EmailLocalServiceUtil.addEmail(email);
        }
    }

    private static void sendDailyReminderEmail60(LocalDateTime now) {
        LocalDateTime fourteenDaysBefore = now.minusDays(14);
        LocalDateTime nineDaysBefore = now.minusDays(9);
        List<Employee> allEmployeeList = EmployeeLocalServiceUtil.findAllEmployees();
        List<Probation> allPendingProbations = ProbationLocalServiceUtil.findAllPending();
        List<Probation> probations = allPendingProbations.stream().filter(s -> (s.getCreateDate().after(getStartOfDayDate(fourteenDaysBefore.toLocalDate())) && s.getCreateDate().before(getStartOfDayDate(nineDaysBefore.toLocalDate())))).collect(Collectors.toList());
        Set<String> managerList = probations.stream().map(Probation::getManager).collect(Collectors.toSet());
        for(String managerEmail : managerList)
        {
            List<Probation> managerProbationList = probations.stream().filter(c -> c.getManager().equalsIgnoreCase(managerEmail)).collect(Collectors.toList());
            StringBuilder tableContent = new StringBuilder(TABLE_START_PROBATION);
            Employee managerData = allEmployeeList.stream().filter(s -> s.getEmail().equalsIgnoreCase(managerEmail)).collect(Collectors.toList()).get(0);
            for(Probation probation : managerProbationList){
                Employee employeeData = allEmployeeList.stream().filter(s -> s.getEcode().equalsIgnoreCase(probation.getEcode())).collect(Collectors.toList()).get(0);
                tableContent.append(ROW_START);
                tableContent.append(probation.getEcode());
                tableContent.append(CELL_SEPARATOR);
                tableContent.append(employeeData.getName());
                tableContent.append(CELL_SEPARATOR);
                tableContent.append(managerData.getName());
                tableContent.append(CELL_SEPARATOR);
                tableContent.append(convertDateToLocalDateTime(probation.getCreateDate()).format(FORMATTER_YYYY_MM_DD));
                tableContent.append(ROW_END);
            }
            tableContent.append(TABLE_END);

            Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
            email.setToAddress(managerEmail);
            email.setCcAddress(DL_HR);
            email.setSubject(DAILY_REMINDER+SUBJECT_PROBATION_INITIATED);
            email.setBody(MessageFormat.format(BODY_PROBATION_INITIATED, managerData.getName(), getPortalUrl() + URL_PROBATION_ADMIN, tableContent));
            email.setModule(MODULE_PROBATION);
            email.setScheduled(true);
            email.setSent(false);
            email.setCreatedDate(convertLocalDateTimeToDate(now));
            EmailLocalServiceUtil.addEmail(email);
        }
    }

    private static void sendDailyReminderEmailTwoTimes65(LocalDateTime now) {
        LocalDateTime fourteenDaysBefore = now.minusDays(14);
        List<Employee> allEmployeeList = EmployeeLocalServiceUtil.findAllEmployees();
        List<Probation> allPendingProbations = ProbationLocalServiceUtil.findAllPending();
        List<Probation> probations = allPendingProbations.stream().filter(s -> s.getCreateDate().before(getStartOfDayDate(fourteenDaysBefore.toLocalDate()))).collect(Collectors.toList());
        Set<String> managerList = probations.stream().map(Probation::getManager).collect(Collectors.toSet());
        for(String managerEmail : managerList)
        {
            List<Probation> managerProbationList = probations.stream().filter(c -> c.getManager().equalsIgnoreCase(managerEmail)).collect(Collectors.toList());
            StringBuilder tableContent = new StringBuilder(TABLE_START_PROBATION);
            Employee managerData = allEmployeeList.stream().filter(s -> s.getEmail().equalsIgnoreCase(managerEmail)).collect(Collectors.toList()).get(0);
            for(Probation probation : managerProbationList){
                Employee employeeData = allEmployeeList.stream().filter(s -> s.getEcode().equalsIgnoreCase(probation.getEcode())).collect(Collectors.toList()).get(0);
                tableContent.append(ROW_START);
                tableContent.append(probation.getEcode());
                tableContent.append(CELL_SEPARATOR);
                tableContent.append(employeeData.getName());
                tableContent.append(CELL_SEPARATOR);
                tableContent.append(managerData.getName());
                tableContent.append(CELL_SEPARATOR);
                tableContent.append(convertDateToLocalDateTime(probation.getCreateDate()).format(FORMATTER_YYYY_MM_DD));
                tableContent.append(ROW_END);
            }
            tableContent.append(TABLE_END);

            Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
            email.setToAddress(managerEmail);
            email.setCcAddress(DL_HR);
            email.setSubject(DAILY_REMINDER+SUBJECT_PROBATION_INITIATED);
            email.setBody(MessageFormat.format(BODY_PROBATION_INITIATED, managerData.getName(), getPortalUrl() + URL_PROBATION_ADMIN, tableContent));
            email.setModule(MODULE_PROBATION);
            email.setScheduled(true);
            email.setSent(false);
            email.setCreatedDate(convertLocalDateTimeToDate(now));
            EmailLocalServiceUtil.addEmail(email);
        }
    }
}
