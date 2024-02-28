package com.trantorinc.synergy.custom.service.task;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.trantorinc.synergy.common.service.drive.DriveService;
import com.trantorinc.synergy.custom.service.dto.EmployeeDto;
import com.trantorinc.synergy.custom.service.dto.HolidayDto;
import com.trantorinc.synergy.email.core.model.Email;
import com.trantorinc.synergy.email.core.service.EmailLocalServiceUtil;
import com.trantorinc.synergy.lms.core.model.Drive;
import com.trantorinc.synergy.lms.core.model.Employee;
import com.trantorinc.synergy.lms.core.model.Holiday;
import com.trantorinc.synergy.lms.core.model.Scheduler;
import com.trantorinc.synergy.lms.core.service.DriveLocalServiceUtil;
import com.trantorinc.synergy.lms.core.service.EmployeeLocalServiceUtil;
import com.trantorinc.synergy.lms.core.service.HolidayLocalServiceUtil;
import com.trantorinc.synergy.lms.core.service.SchedulerLocalServiceUtil;
import org.apache.commons.io.FileUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import static com.trantorinc.synergy.common.service.constant.AppConstantService.*;
import static com.trantorinc.synergy.common.service.util.UtilService.convertLocalDateTimeToDate;
import static com.trantorinc.synergy.common.service.util.UtilService.getStartOfDayDate;
import static com.trantorinc.synergy.custom.service.constants.SynergyServicePortletKeys.*;


public class LmsTaskService {
    private static final Log log = LogFactoryUtil.getLog(LmsTaskService.class.getName());

    private static List<EmployeeDto> changedEmployees = new ArrayList<>();
    private LmsTaskService() {

    }

    public static void execute(LocalDateTime now){

        log.info(MODULE_LMS + " task started !");
        String schedulerName = now.getHour()<=12 ? TASK_AM_LMS : TASK_PM_LMS;
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
        String folderId = DriveLocalServiceUtil.findFolderIdByFolderName(MODULE_LMS);
        changedEmployees = new ArrayList<>();
        log.info(MODULE_LMS + " task preset !!");

        syncLmsEmployee(now, folderId);
        log.info(MODULE_LMS + " task completed ...(1/5)");

        validateManagerReviewerOfEmployee(now);
        log.info(MODULE_LMS + " task completed ...(2/5)");

        sendChangedStatusEmail(now);
        log.info(MODULE_LMS + " task completed ...(3/5)");

        syncLmsHolidays(now);
        log.info(MODULE_LMS + " task completed ...(4/5)");

        updateDriveFileCount(now);
        log.info(MODULE_LMS + " task completed ...(5/5)");

        scheduler.setStatus(true);
        SchedulerLocalServiceUtil.updateScheduler(scheduler);
        log.info(MODULE_LMS + " task completed !!!");
    }

    private static void syncLmsEmployee(LocalDateTime now, String folderId) {
        List<EmployeeDto> employeeDataLMS = new ArrayList<>();
        try{
            Class.forName(DRIVER_NAME); // HAVE to use as this driver is supplied by lib.
            Connection conn = DriverManager.getConnection(LMS_CONNECTION, LMS_USER ,LMS_PWD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL_LMS_EMPLOYEE);
            while(rs.next()) {
                EmployeeDto employeeDto = new EmployeeDto();
                employeeDto.setEcode(rs.getString("ecode").trim().toUpperCase());
                String status = rs.getString("status");
                if (!rs.getBoolean("deleted")) {
                    employeeDto.setActive((status != null && status.equalsIgnoreCase("Active")));}
                else{
                    employeeDto.setActive(false);
                }

                if (rs.getString("type").equalsIgnoreCase("Contractual")) {
                    employeeDto.setType("Contractual");
                } else {
                    employeeDto.setType("FTE");
                }
                employeeDto.setName(rs.getString("name").trim());
                employeeDto.setDoj(rs.getDate("doj"));
                employeeDto.setDob(rs.getDate("dob"));
                employeeDto.setEmail(rs.getString("email").trim());
                employeeDto.setBand(rs.getString("band").trim());
                employeeDto.setDesignation(rs.getString("designation").trim());
                employeeDto.setManager(rs.getString("manager").trim());
                employeeDto.setReviewer(rs.getString("reviewer").trim());
                employeeDto.setAccount(rs.getString("account").trim().toUpperCase());
                String project = rs.getString("project").trim();
                if (project.length() > MAX_PROJECT_LENGTH) {
                    project = project.substring(0, MAX_PROJECT_LENGTH);
                }
                employeeDto.setProject(project);
                employeeDto.setExperience(rs.getString("experience").trim());
                String skill = rs.getString("skill").trim();
                if (skill.length() > MAX_SKILL_LENGTH) {
                    skill = skill.substring(0, MAX_SKILL_LENGTH);
                }
                employeeDto.setSkill(skill);
                employeeDto.setLocation(rs.getString("location").trim());
                String confirmed = rs.getString("confirmed").trim();
                employeeDto.setConfirmed(confirmed.equalsIgnoreCase("Confirmed") || confirmed.equalsIgnoreCase("NA"));
                employeeDto.setMobile(rs.getString("mobile").trim());
                String base64Photo =rs.getString("photo");
                if (null != base64Photo) {
                    String fileName = employeeDto.getEcode();
                    byte[] photoBinary = Base64.getDecoder().decode(base64Photo);
                    ByteArrayInputStream inputStream = new ByteArrayInputStream(photoBinary);
                    if(employeeDto.getEcode().length()<3){
                        fileName = "00" + fileName;
                    }
                    File photo= File.createTempFile(fileName, JPG);
                    FileUtils.copyInputStreamToFile(inputStream, photo);
                    employeeDto.setPhoto(photo);
                } else {
                    employeeDto.setPhoto(null);
                }
                if (null != rs.getString("coordinator") && !rs.getString("coordinator").trim().equalsIgnoreCase(BLANK)) {
                    employeeDto.setCoordinator(rs.getString("coordinator").trim());
                } else {
                    employeeDto.setCoordinator(rs.getString("manager").trim());
                }
                employeeDto.setLmsUser(true);
                employeeDataLMS.add(employeeDto);
            }
        }
        catch(ClassNotFoundException | SQLException | IOException  exception){
            log.error(exception.getStackTrace()[0].getMethodName()+":" +exception.getStackTrace()[0].getLineNumber()+":"+ exception.getMessage());
            Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
            email.setToAddress(DL_INTRANET);
            email.setSubject(MessageFormat.format(SUBJECT_EMPLOYEE_SYNC_FAIL,ENVIRONMENT));
            email.setBody(MessageFormat.format(BODY_EMPLOYEE_SYNC_FAIL, exception.getClass(), exception.getMessage()));
            email.setModule(MODULE_LMS);
            email.setScheduled(true);
            email.setSent(false);
            email.setCreatedDate(convertLocalDateTimeToDate(now));
            EmailLocalServiceUtil.addEmail(email);
        }
        if(!employeeDataLMS.isEmpty()){
            List<Employee> allEmployees = EmployeeLocalServiceUtil.findAllEmployees();
            for(EmployeeDto entry : employeeDataLMS){
                updateEmployeeDetails(folderId,entry, allEmployees.stream().filter(e -> e.getEmail().equalsIgnoreCase(entry.getEmail())).collect(Collectors.toList()),  allEmployees.stream().filter(e -> e.getEcode().equalsIgnoreCase(entry.getEcode())).collect(Collectors.toList()));
            }
        }
    }

    private static void validateManagerReviewerOfEmployee(LocalDateTime now) {
        List<Employee> allActiveEmployees = EmployeeLocalServiceUtil.findAllActiveEmployees();
        List<EmployeeDto> employeeList = new ArrayList<>();
        for(Employee employee : allActiveEmployees)
        {
            List<Employee> managers = allActiveEmployees.stream().filter(s -> s.getEcode().equalsIgnoreCase(employee.getManager())).collect(Collectors.toList());
            List<Employee> reviewers = allActiveEmployees.stream().filter(s -> s.getEcode().equalsIgnoreCase(employee.getReviewer())).collect(Collectors.toList());
            if(managers.isEmpty() && reviewers.isEmpty()){
                EmployeeDto dto = new EmployeeDto();
                dto.setEcode(employee.getEcode());
                dto.setName(employee.getName());
                dto.setAccount(employee.getAccount());
                dto.setToValidate("Manager & Reviewer");
                dto.setManager(employee.getManager());
                dto.setReviewer(employee.getReviewer());
                employeeList.add(dto);
            }
            else if(managers.isEmpty())
            {
                EmployeeDto dto = new EmployeeDto();
                dto.setEcode(employee.getEcode());
                dto.setName(employee.getName());
                dto.setAccount(employee.getAccount());
                dto.setToValidate("Manager");
                dto.setManager(employee.getManager());
                dto.setReviewer(employee.getReviewer());
                employeeList.add(dto);
            }
            else if(reviewers.isEmpty())
            {
                EmployeeDto dto = new EmployeeDto();
                dto.setEcode(employee.getEcode());
                dto.setName(employee.getName());
                dto.setAccount(employee.getAccount());
                dto.setToValidate("Reviewer");
                dto.setManager(employee.getManager());
                dto.setReviewer(employee.getReviewer());
                employeeList.add(dto);
            }
        }
        if(!employeeList.isEmpty()) {
            StringBuilder tableContent = new StringBuilder();
            tableContent.append(TABLE_START_INCORRECT_LMS);
            for(EmployeeDto empData : employeeList)
            {
                tableContent.append(ROW_START);
                tableContent.append(empData.getEcode());
                tableContent.append(CELL_SEPARATOR);
                tableContent.append(empData.getName());
                tableContent.append(CELL_SEPARATOR);
                tableContent.append(empData.getAccount());
                tableContent.append(CELL_SEPARATOR);
                tableContent.append(empData.getManager());
                tableContent.append(CELL_SEPARATOR);
                tableContent.append(empData.getReviewer());
                tableContent.append(CELL_SEPARATOR);
                tableContent.append(empData.getToValidate());
                tableContent.append(ROW_END);
            }
            tableContent.append(TABLE_END);

            Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
            email.setToAddress(DL_HR + COMMA + DL_INTRANET);
            email.setSubject(SUBJECT_INCORRECT_LMS);
            email.setBody(MessageFormat.format(BODY_INCORRECT_LMS, tableContent.toString()));
            email.setModule(MODULE_LMS);
            email.setScheduled(true);
            email.setSent(false);
            email.setCreatedDate(convertLocalDateTimeToDate(now));
            EmailLocalServiceUtil.addEmail(email);
        }
    }

    private static void syncLmsHolidays(LocalDateTime now) {
        int currentYear = now.getYear();
        List<HolidayDto> lmsHolidays = new ArrayList<>();
        try {
            Class.forName(DRIVER_NAME); // HAVE to use as this driver is supplied by lib.
            Connection conn = DriverManager.getConnection(LMS_CONNECTION, LMS_USER, LMS_PWD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL_LMS_HOLIDAY);
            while (rs.next()) {
                HolidayDto holidayDto = new HolidayDto();
                holidayDto.setDescription(rs.getString("description"));
                holidayDto.setHolidayDate(rs.getDate("holidayDate"));
                lmsHolidays.add(holidayDto);
            }

            if (!lmsHolidays.isEmpty()) {
                List<Holiday> holidays = HolidayLocalServiceUtil.findHolidaysByYear(currentYear);
                for (Holiday holiday : holidays) {
                    HolidayLocalServiceUtil.deleteHoliday(holiday.getHolidayId());
                }

                for (HolidayDto holidayDto : lmsHolidays) {
                    Holiday holiday = HolidayLocalServiceUtil.createHoliday(CounterLocalServiceUtil.increment());
                    holiday.setHolidayYear(currentYear);
                    holiday.setName(holidayDto.getDescription());
                    holiday.setOnDate(holidayDto.getHolidayDate());
                    HolidayLocalServiceUtil.addHoliday(holiday);
                }
            }
        }catch(ClassNotFoundException | SQLException | PortalException exception){
            log.error(exception.getStackTrace()[0].getMethodName()+":" +exception.getStackTrace()[0].getLineNumber()+":"+ exception.getMessage());
            Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
            email.setToAddress(DL_INTRANET);
            email.setSubject(MessageFormat.format(SUBJECT_HOLIDAY_SYNC_FAIL,ENVIRONMENT));
            email.setBody(MessageFormat.format(BODY_HOLIDAY_SYNC_FAIL, exception.getClass(), exception.getMessage()));
            email.setModule(MODULE_LMS);
            email.setScheduled(true);
            email.setSent(false);
            email.setCreatedDate(convertLocalDateTimeToDate(now));
            EmailLocalServiceUtil.addEmail(email);
        }
    }

    private static void updateEmployeeDetails(String folderId,EmployeeDto employeeDto, List<Employee> sameEmailEmployees, List<Employee> sameEcodeEmployees) {

        if (sameEmailEmployees.isEmpty() && sameEcodeEmployees.isEmpty()) { // this is a new employee
            Employee employeeData = EmployeeLocalServiceUtil.createEmployee(employeeDto.getEcode());
            employeeData.setStatus(employeeDto.isActive());
            employeeData.setEmployeeType(employeeDto.getType());
            employeeData.setName(employeeDto.getName());
            employeeData.setDoj(employeeDto.getDoj());
            employeeData.setDob(employeeDto.getDob());
            employeeData.setEmail(employeeDto.getEmail());
            employeeData.setBand(employeeDto.getBand());
            employeeData.setDesignation(employeeDto.getDesignation());
            employeeData.setManager(employeeDto.getManager());
            employeeData.setReviewer(employeeDto.getReviewer());
            employeeData.setProject(employeeDto.getProject());
            employeeData.setAccount(employeeDto.getAccount());
            employeeData.setExperience(employeeDto.getExperience());
            employeeData.setSkill(employeeDto.getSkill());
            employeeData.setLocation(employeeDto.getLocation());
            employeeData.setConfirmed(employeeDto.isConfirmed());
            employeeData.setMobile(employeeDto.getMobile());
            employeeData.setLmsUser(employeeDto.isLmsUser());
            employeeData.setCoordinator(employeeDto.getCoordinator());
            if (null != employeeDto.getPhoto()) {
                String photoId = DriveService.uploadFile(folderId,employeeDto.getEcode()+JPG,employeeDto.getPhoto());
                employeeData.setFileId(photoId);
            }
            EmployeeLocalServiceUtil.addEmployee(employeeData);
        } else if (sameEcodeEmployees.size() == 1) { //existing employee -- update

            Employee employeeData = sameEcodeEmployees.get(0);
            if(employeeDto.isActive() && (!employeeData.getAccount().equalsIgnoreCase(employeeDto.getAccount())
                    || !employeeData.getManager().equalsIgnoreCase(employeeDto.getManager())
                    || !employeeData.getReviewer().equalsIgnoreCase(employeeDto.getReviewer()))) {
                employeeDto.setPreviousAccount(employeeData.getAccount());
                employeeDto.setPreviousManager(employeeData.getManager());
                employeeDto.setPreviousReviewer(employeeData.getReviewer());
                employeeDto.setPreviousProject(employeeData.getProject());
                changedEmployees.add(employeeDto);
            }
            employeeData.setStatus(employeeDto.isActive());
            employeeData.setEmployeeType(employeeDto.getType());
            employeeData.setName(employeeDto.getName());
            employeeData.setDoj(employeeDto.getDoj());
            employeeData.setDob(employeeDto.getDob());
            employeeData.setEmail(employeeDto.getEmail().trim());
            employeeData.setBand(employeeDto.getBand());
            employeeData.setDesignation(employeeDto.getDesignation());
            employeeData.setManager(employeeDto.getManager());
            employeeData.setReviewer(employeeDto.getReviewer());
            employeeData.setProject(employeeDto.getProject());
            employeeData.setAccount(employeeDto.getAccount());
            employeeData.setExperience(employeeDto.getExperience());
            employeeData.setSkill(employeeDto.getSkill());
            employeeData.setLocation(employeeDto.getLocation());
            employeeData.setConfirmed(employeeDto.isConfirmed());
            employeeData.setMobile(employeeDto.getMobile());
            employeeData.setLmsUser(employeeDto.isLmsUser());
            employeeData.setSkype(employeeData.getSkype());
            employeeData.setCoordinator(employeeDto.getCoordinator());
            if (null != employeeDto.getPhoto() && (null == employeeData.getFileId() || employeeData.getFileId().equalsIgnoreCase(BLANK))) {
                String photoId = DriveService.uploadFile(folderId,employeeDto.getEcode()+JPG,employeeDto.getPhoto());
                employeeData.setFileId(photoId);
            }
            EmployeeLocalServiceUtil.updateEmployee(employeeData);
        } else { //clean all entry and insert new e.g- converted. contractual to FTE
            for (Employee existing : sameEmailEmployees) {
                Employee completeEntry = EmployeeLocalServiceUtil.fetchEmployee(existing.getEcode());
                DriveService.deleteFile(completeEntry.getFileId());
                EmployeeLocalServiceUtil.deleteEmployee(completeEntry);
            }
            Employee employeeData = EmployeeLocalServiceUtil.createEmployee(employeeDto.getEcode());
            employeeData.setStatus(employeeDto.isActive());
            employeeData.setEmployeeType(employeeDto.getType());
            employeeData.setName(employeeDto.getName());
            employeeData.setDoj(employeeDto.getDoj());
            employeeData.setDob(employeeDto.getDob());
            employeeData.setEmail(employeeDto.getEmail());
            employeeData.setBand(employeeDto.getBand());
            employeeData.setDesignation(employeeDto.getDesignation());
            employeeData.setManager(employeeDto.getManager());
            employeeData.setReviewer(employeeDto.getReviewer());
            employeeData.setProject(employeeDto.getProject());
            employeeData.setAccount(employeeDto.getAccount());
            employeeData.setExperience(employeeDto.getExperience());
            employeeData.setSkill(employeeDto.getSkill());
            employeeData.setLocation(employeeDto.getLocation());
            employeeData.setConfirmed(employeeDto.isConfirmed());
            employeeData.setMobile(employeeDto.getMobile());
            employeeData.setLmsUser(employeeDto.isLmsUser());
            employeeData.setCoordinator(employeeDto.getCoordinator());
            if (null != employeeDto.getPhoto()) {
                String photoId = DriveService.uploadFile(folderId,employeeDto.getEcode()+JPG,employeeDto.getPhoto());
                employeeData.setFileId(photoId);
            }
            EmployeeLocalServiceUtil.addEmployee(employeeData);
        }
    }


    private static void sendChangedStatusEmail(LocalDateTime now) {
        List<Employee> allActiveEmployees = EmployeeLocalServiceUtil.findAllActiveEmployees();
        for(EmployeeDto employeeDto : changedEmployees){
            List<Employee> managers = allActiveEmployees.stream().filter(e -> e.getEcode().equalsIgnoreCase(employeeDto.getManager())).collect(Collectors.toList());
            List<Employee> previousManagers = allActiveEmployees.stream().filter(e -> e.getEcode().equalsIgnoreCase(employeeDto.getPreviousManager())).collect(Collectors.toList());
            List<Employee> reviewers = allActiveEmployees.stream().filter(e -> e.getEcode().equalsIgnoreCase(employeeDto.getReviewer())).collect(Collectors.toList());
            List<Employee> previousReviewers = allActiveEmployees.stream().filter(e -> e.getEcode().equalsIgnoreCase(employeeDto.getPreviousReviewer())).collect(Collectors.toList());

            String emailBody = TABLE_START_ACCOUNT_MANAGER_CHANGE + now.format(FORMATTER_YYYY_MM_DD) +
                    ROW_START_ACCOUNT_MANAGER_CHANGE +
                    employeeDto.getEcode() +
                    CELL_SEPARATOR +
                    employeeDto.getEcode() +
                    ROW_END + ROW_START + "Name" + CELL_SEPARATOR +
                    employeeDto.getName() +
                    CELL_SEPARATOR +
                    employeeDto.getName() +
                    ROW_END + ROW_START + "Account" + CELL_SEPARATOR +
                    employeeDto.getPreviousAccount() +
                    CELL_SEPARATOR +
                    employeeDto.getAccount() +
                    ROW_END + ROW_START + "Project" + CELL_SEPARATOR +
                    employeeDto.getPreviousProject() +
                    CELL_SEPARATOR +
                    employeeDto.getProject() +
                    ROW_END + ROW_START + "Manager Name" + CELL_SEPARATOR +
                    ((!previousManagers.isEmpty()) ? previousManagers.get(0).getName() : BLANK) +
                    CELL_SEPARATOR +
                    ((!managers.isEmpty()) ? managers.get(0).getName() : BLANK) +
                    ROW_END + ROW_START + "Manager Ecode" + CELL_SEPARATOR +
                    employeeDto.getPreviousManager() +
                    CELL_SEPARATOR +
                    employeeDto.getManager() +
                    ROW_END + ROW_START + "Reviewer Name" + CELL_SEPARATOR +
                    ((!previousReviewers.isEmpty()) ? previousReviewers.get(0).getName() : BLANK) +
                    CELL_SEPARATOR +
                    ((!reviewers.isEmpty()) ? reviewers.get(0).getName() : BLANK) +
                    ROW_END + ROW_START + "Reviewer Ecode" + CELL_SEPARATOR +
                    employeeDto.getPreviousReviewer() +
                    CELL_SEPARATOR +
                    employeeDto.getReviewer() +
                    ROW_END + "</table>";

            Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
            email.setModule(MODULE_LMS);
            email.setScheduled(true);
            email.setSent(false);
            email.setCreatedDate(convertLocalDateTimeToDate(now));
            email.setSubject(MessageFormat.format(SUBJECT_ACCOUNT_MANAGER_CHANGE,employeeDto.getName(), employeeDto.getEcode()));

            if(!employeeDto.getAccount().equalsIgnoreCase(employeeDto.getPreviousAccount())){
                String billingManagerName = (!previousManagers.isEmpty()) ? previousManagers.get(0).getName():BLANK;
                StringBuilder toAddress=new StringBuilder();
                if(!managers.isEmpty()){
                    toAddress.append(managers.get(0).getEmail()).append(COMMA);
                }
                if(!previousManagers.isEmpty()){
                    toAddress.append(previousManagers.get(0).getEmail());
                }
                email.setToAddress(toAddress.toString());
                email.setCcAddress(DL_IT + COMMA + DL_HR + COMMA + DL_FINANCE + COMMA + DL_AMINDIA + COMMA + DL_BGV + COMMA + employeeDto.getEmail());
                email.setBody(MessageFormat.format(BODY_ACCOUNT_CHANGE,  billingManagerName, emailBody));
            } else {
                email.setToAddress(DL_HR + COMMA + DL_FINANCE);
                email.setBody(MessageFormat.format(BODY_MANAGER_CHANGE, emailBody));
            }
            EmailLocalServiceUtil.addEmail(email);
        }
        changedEmployees = new ArrayList<>();
    }



    private static void updateDriveFileCount(LocalDateTime now) {
        List<Drive> drives = DriveLocalServiceUtil.getDrives(-1, -1);
        for(Drive drive : drives) {
            List<String> ids = DriveService.getFolderFiles(drive.getFolderId());
            drive.setFilesCount(ids.size());
            drive.setUpdateDate(convertLocalDateTimeToDate(now));
            DriveLocalServiceUtil.updateDrive(drive);
        }
    }
}
