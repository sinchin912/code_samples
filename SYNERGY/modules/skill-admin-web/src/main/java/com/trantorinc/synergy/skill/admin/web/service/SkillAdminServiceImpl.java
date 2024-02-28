package com.trantorinc.synergy.skill.admin.web.service;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.trantorinc.synergy.common.service.drive.DriveService;
import com.trantorinc.synergy.common.service.dto.ExcelDto;
import com.trantorinc.synergy.common.service.excel.ExcelService;
import com.trantorinc.synergy.email.core.model.Email;
import com.trantorinc.synergy.email.core.service.EmailLocalServiceUtil;
import com.trantorinc.synergy.lms.core.model.Employee;
import com.trantorinc.synergy.lms.core.service.EmployeeLocalServiceUtil;
import com.trantorinc.synergy.skill.admin.web.dto.CertificateDto;
import com.trantorinc.synergy.skill.admin.web.dto.EmployeeDto;
import com.trantorinc.synergy.skill.admin.web.dto.SkillDto;
import com.trantorinc.synergy.skill.core.model.Certificate;
import com.trantorinc.synergy.skill.core.model.Skill;
import com.trantorinc.synergy.skill.core.model.SkillGuide;
import com.trantorinc.synergy.skill.core.model.SkillRejectionComment;
import com.trantorinc.synergy.skill.core.service.CertificateLocalServiceUtil;
import com.trantorinc.synergy.skill.core.service.SkillGuideLocalServiceUtil;
import com.trantorinc.synergy.skill.core.service.SkillLocalServiceUtil;
import com.trantorinc.synergy.skill.core.service.SkillRejectionCommentLocalServiceUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.trantorinc.synergy.common.service.constant.AppConstantService.*;
import static com.trantorinc.synergy.common.service.util.UtilService.*;
import static com.trantorinc.synergy.common.service.util.UtilService.getUserManual;
import static com.trantorinc.synergy.skill.admin.web.constants.SkillAdminWebPortletKeys.*;

public class SkillAdminServiceImpl implements SkillAdminService {
    Log log = LogFactoryUtil.getLog(SkillAdminServiceImpl.class);

    @Override
    public List<EmployeeDto> getEmployeeDataList() {
        List<Employee> allActiveEmployees = EmployeeLocalServiceUtil.findAllActiveEmployees();
        List<EmployeeDto> employeeList = new ArrayList<>();
        for (Employee empData : allActiveEmployees) {
            List<Employee> managerData = allActiveEmployees.stream().filter(e -> e.getEcode().equalsIgnoreCase(empData.getManager())).collect(Collectors.toList());
            List<Employee> reviewerData = allActiveEmployees.stream().filter(e -> e.getEcode().equalsIgnoreCase(empData.getReviewer().trim())).collect(Collectors.toList());
            String managerName = managerData.isEmpty() ? BLANK : managerData.get(0).getName();
            String reviewerName = reviewerData.isEmpty() ? BLANK : reviewerData.get(0).getName();
            if (!managerData.isEmpty() && !reviewerData.isEmpty()) {
                EmployeeDto employeeDto = new EmployeeDto(empData.getEcode(), empData.getEmail(), empData.getName(), empData.getDesignation(), managerName, empData.getAccount(), reviewerName);
                employeeList.add(employeeDto);
            } else {
                log.info("Manager or Reviewer not found for ecode : " + empData.getEcode());
            }
        }
        return employeeList;
    }

    @Override
    public List<EmployeeDto> getAllLeadEntries(List<EmployeeDto> employeeList, String loggedUserEmail) {
        List<Skill> leadAssignedList = SkillLocalServiceUtil.findSkillsByReviewer(loggedUserEmail).stream().filter(s -> !s.getReviewer().equalsIgnoreCase(s.getManager())).collect(Collectors.toList());
        Set<String> ecodeLists = leadAssignedList.stream().map(Skill::getEcode).collect(Collectors.toSet());
        List<EmployeeDto> leadDataList = new ArrayList<>();
        for (String ecode : ecodeLists) {
            List<EmployeeDto> empDetails = employeeList.stream().filter(s -> s.getEmpEcode().equalsIgnoreCase(ecode)).collect(Collectors.toList());
            if (!empDetails.isEmpty()) {
                EmployeeDto employeeDetails = empDetails.get(0);
                List<Skill> empSkill = leadAssignedList.stream().filter(s -> s.getEcode().equalsIgnoreCase(ecode)).filter(s -> s.getStatus() == SUBMISSION_STATE).collect(Collectors.toList());
                employeeDetails.setOverview(empSkill.isEmpty() ? NO_ACTION_REQUIRED : ACTION_REQUIRED);
                leadDataList.add(employeeDetails);
            }
        }
        return leadDataList;
    }

    @Override
    public List<EmployeeDto> getAllManagerEntries(List<EmployeeDto> employeeList, List<Skill> allEmployeeSkillLists, String loggedUserEmail) {
        List<EmployeeDto> employeeSkillList = new ArrayList<>();
        List<EmployeeDto> empData = employeeList.stream().filter(s -> s.getEmpEmail().equalsIgnoreCase(loggedUserEmail)).collect(Collectors.toList());
        if (!empData.isEmpty()) {
            List<Employee> managerReportingList = EmployeeLocalServiceUtil.findManagerReporters(empData.get(0).getEmpEcode());
            Set<String> managerReportingEcodes = managerReportingList.stream().map(Employee::getEcode).collect(Collectors.toSet());
            for (String ecode : managerReportingEcodes) {
//            TODO : Can we use get(0) here directly , because in finder we are already checking status+lmsuser-true
                EmployeeDto employeeDetails = employeeList.stream().filter(s -> s.getEmpEcode().equalsIgnoreCase(ecode)).collect(Collectors.toList()).get(0);
                List<Skill> empSkills = allEmployeeSkillLists.stream().filter(s -> s.getEcode().equalsIgnoreCase(ecode)).collect(Collectors.toList());
                if (empSkills.isEmpty()) {
                    employeeDetails.setOverview(NO_SKILL_ADDED);
                } else {
                    List<Skill> skill = empSkills.stream().filter(s -> s.getStatus() == SUBMISSION_STATE).collect(Collectors.toList());
                    if (skill.isEmpty()) {
                        employeeDetails.setOverview(NO_ACTION_REQUIRED);
                    } else {
                        employeeDetails.setOverview(ACTION_REQUIRED);
                    }
                }
                employeeSkillList.add(employeeDetails);
            }
        }
        return employeeSkillList;
    }

    @Override
    public List<EmployeeDto> getAllHrLeaderRecruiterEntries(List<EmployeeDto> employeeList, List<Skill> allEmployeeSkillLists) {

        List<EmployeeDto> empList = new ArrayList<>();
        Set<String> ecodes = employeeList.stream().map(EmployeeDto::getEmpEcode).collect(Collectors.toSet());
        if (!ecodes.isEmpty()) {
            for (String ecode : ecodes) {
                List<Skill> empSkill = allEmployeeSkillLists.stream().filter(s -> s.getEcode().equalsIgnoreCase(ecode)).collect(Collectors.toList());
                EmployeeDto employeeDto = employeeList.stream().filter(s -> s.getEmpEcode().equalsIgnoreCase(ecode)).collect(Collectors.toList()).get(0);
                employeeDto.setOverview(empSkill.isEmpty() ? NO_SKILL_ADDED : MODE_VIEW);
                empList.add(employeeDto);
            }
        }
        return empList;
    }

    @Override
    public List<SkillDto> getEmployeeDetailsByKeyword(ResourceRequest request) {
        String keyWord = ParamUtil.getString(request, "keyword").toLowerCase().trim();
        ArrayList<SkillDto> skillLists = new ArrayList<>();
        List<Skill> skillList = SkillLocalServiceUtil.getSkills(-1, -1);
        List<Certificate> certificateLists = CertificateLocalServiceUtil.getCertificates(-1, -1);
        List<Employee> employeeList = EmployeeLocalServiceUtil.findAllActiveEmployees();
        Set<String> ecodes = new HashSet<>();
        for (Skill skill : skillList) {
            List<Employee> empDetail = employeeList.stream().filter(s -> s.getEcode().equalsIgnoreCase(skill.getEcode())).collect(Collectors.toList());
            if (!ecodes.contains(skill.getEcode()) && !empDetail.isEmpty() && skill.getStatus() == 1  && (skill.getEcode().toLowerCase().contains(keyWord) || skill.getCoreSkill().toLowerCase().contains(keyWord) || skill.getSubSkill().toLowerCase().contains(keyWord) || skill.getTool().toLowerCase().contains(keyWord) || skill.getVersion().toLowerCase().contains(keyWord))) {
                List<Employee> managerDetail = employeeList.stream().filter(s -> s.getEcode().equalsIgnoreCase(empDetail.get(0).getManager())).collect(Collectors.toList());
                SkillDto skillDto = new SkillDto(empDetail.get(0).getAccount(), managerDetail.isEmpty() ? BLANK : managerDetail.get(0).getName(), skill.getEcode(),empDetail.get(0).getName(),true);
                ecodes.add(skill.getEcode());
                skillLists.add(skillDto);
            }
        }
        for (Certificate certificate : certificateLists) {
            List<Employee> empDetail = employeeList.stream().filter(s -> s.getEcode().equalsIgnoreCase(certificate.getEcode())).collect(Collectors.toList());
            if (!empDetail.isEmpty() && !ecodes.contains(certificate.getEcode()) && (certificate.getEcode().toLowerCase().contains(keyWord) || certificate.getCategory().toLowerCase().contains(keyWord) || certificate.getName().toLowerCase().contains(keyWord))) {
                List<Employee> managerDetail = employeeList.stream().filter(s -> s.getEcode().equalsIgnoreCase(empDetail.get(0).getManager())).collect(Collectors.toList());
                SkillDto skillDto = new SkillDto(empDetail.get(0).getAccount(), managerDetail.isEmpty() ? BLANK : managerDetail.get(0).getName(), certificate.getEcode(), empDetail.get(0).getName(),true);
                ecodes.add(certificate.getEcode());
                skillLists.add(skillDto);
            }
        }
        for (Employee employee : employeeList) {
            boolean skillCertificateExist = false;
            List<Employee> managerDetail = employeeList.stream().filter(s -> s.getEcode().equalsIgnoreCase(employee.getManager())).collect(Collectors.toList());
            if (!ecodes.contains(employee.getEcode()) && (employee.getName().toLowerCase().contains(keyWord.toLowerCase()) || employee.getAccount().toLowerCase().contains(keyWord.toLowerCase()))) {
                List<Skill> skills1 = skillList.stream().filter(s -> s.getEcode().equalsIgnoreCase(employee.getEcode())).collect(Collectors.toList());
                if (skills1.isEmpty()) {
                    List<Certificate> certificateList1 = certificateLists.stream().filter(s -> s.getEcode().equalsIgnoreCase(employee.getEcode())).collect(Collectors.toList());
                    if (!certificateList1.isEmpty()) {
                        skillCertificateExist = true;
                    }
                } else {
                    skillCertificateExist = true;
                }
                SkillDto skillDto = new SkillDto(employee.getAccount(), managerDetail.isEmpty() ? BLANK : managerDetail.get(0).getName(), employee.getEcode(), employee.getName(), skillCertificateExist);
                ecodes.add(employee.getEcode());
                skillLists.add(skillDto);
            }
        }


        return skillLists;
    }

    @Override
    public void addNewSubSkill(ActionRequest request) {
        SkillGuide newSkill = SkillGuideLocalServiceUtil.createSkillGuide(CounterLocalServiceUtil.increment());
        newSkill.setCoreSkill(ParamUtil.getString(request, "coreSkill"));
        newSkill.setSubSkill(ParamUtil.getString(request, "newSubSkill"));
        SkillGuideLocalServiceUtil.addSkillGuide(newSkill);
    }

    @Override
    public void addNewCertificate(ActionRequest request) {
        SkillGuide newCertificate = SkillGuideLocalServiceUtil.createSkillGuide(CounterLocalServiceUtil.increment());
        newCertificate.setCoreSkill(CERTIFICATE);
        newCertificate.setSubSkill(ParamUtil.getString(request, "newCertificateName"));
        SkillGuideLocalServiceUtil.addSkillGuide(newCertificate);
    }

    @Override
    public List<SkillDto> getEmployeeSkillData(String role, String ecode, List<Skill> skillList) throws PortalException {
        List<SkillDto> dtoList = new ArrayList<>();
        for (Skill skill : skillList) {
            SkillDto skillDto = new SkillDto();
            skillDto.setSkillId(skill.getSkillId());
            skillDto.setCoreSkill(skill.getCoreSkill());
            skillDto.setSubSkill(skill.getSubSkill());
            skillDto.setSkillType(skill.getPrimarySkill() ? PRIMARY : SECONDARY);
//            If proficiency level is Int type , then i have to create one more value in DTO.
            skillDto.setProficiencyLevel(String.valueOf(skill.getProficiencyLevel()));
            skillDto.setTool(skill.getTool());
            skillDto.setVersion(skill.getVersion());
            skillDto.setValidity(skill.getValidity());

            skillDto.setStatus(getStatus(skill.getStatus()));
            skillDto.setRequiredLevel(skill.getRequiredLevel());
            dtoList.add(skillDto);
        }
        return dtoList;
    }

    @Override
    public void submitEmpSkillRating(ActionRequest request) {
        String loggedUserName = EmployeeLocalServiceUtil.findByEmail(getLoggedUser(request)).getName();
        int actionPerformed = ParamUtil.getInteger(request, "actionPerformed");
        log.info("actionPerformed" + actionPerformed);
        String[] selectedSkills = ParamUtil.getParameterValues(request, "checkbox", null);
        String skillRejectionReason = ParamUtil.getString(request, "skillRejectionReason");
        String ecode = ParamUtil.getString(request, "ecode");
        Employee employeeDetails = EmployeeLocalServiceUtil.fetchEmployee(ecode);
        StringBuilder updateSkillLevel = new StringBuilder();
        Set<String> emailList = new HashSet<>();
        boolean changeSkillLevel = false;
        for (String skillId : selectedSkills) {
            int proficiencyLevel = ParamUtil.getInteger(request, "proficiencyLevel" + skillId);
            int proficiencyLevelRating = ParamUtil.getInteger(request, "proficiencyLevelRating" + skillId);
            Skill skill = SkillLocalServiceUtil.fetchSkill(Long.parseLong(skillId));
            emailList.add(skill.getReviewer());
            emailList.add(skill.getManager());
            if (actionPerformed == 1) {
                skill.setStatus(1);
                if (skill.getProficiencyLevel() != proficiencyLevel) {
                    skill.setProficiencyLevel(proficiencyLevel);
                    updateSkillLevel.append(ARROW);
                    updateSkillLevel.append(skill.getSubSkill());
                    updateSkillLevel.append(OPEN_BRACES);
                    updateSkillLevel.append(skill.getCoreSkill());
                    updateSkillLevel.append(CLOSE_BRACES);
                    changeSkillLevel = true;
                }
                skill.setRequiredLevel(proficiencyLevelRating);
            } else {
                skill.setStatus(2);
            }
            SkillLocalServiceUtil.updateSkill(skill);
        }
        if (actionPerformed == 2) {
            SkillRejectionComment rejectionComment = SkillRejectionCommentLocalServiceUtil.createSkillRejectionComment(CounterLocalServiceUtil.increment());
            rejectionComment.setEcode(ecode);
            rejectionComment.setCreatedDate(getIstDate());
            rejectionComment.setDescription(skillRejectionReason);
            rejectionComment.setAuthor(getLoggedUser(request));
            SkillRejectionCommentLocalServiceUtil.addSkillRejectionComment(rejectionComment);
            Email employeeEmail = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
            employeeEmail.setSubject(SUBJECT_SKILL_REJECTION);
            employeeEmail.setBody(MessageFormat.format(BODY_SKILL_REJECTION, loggedUserName, getPortalUrl() + URL_SKILL));
            employeeEmail.setToAddress(employeeDetails.getEmail());
            employeeEmail.setModule(MODULE_SKILL);
            employeeEmail.setScheduled(false);
            employeeEmail.setSent(false);
            employeeEmail.setCreatedDate(getIstDate());
            EmailLocalServiceUtil.addEmail(employeeEmail);

        } else if (changeSkillLevel) {
            Email employeeEmail = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
            employeeEmail.setSubject(SUBJECT_CHANGE_SKILL_PROFICIENCYLEVEL);
            employeeEmail.setBody(MessageFormat.format(BODY_CHANGE_SKILL_PROFICIENCYLEVEL, loggedUserName, updateSkillLevel.toString(), getPortalUrl() + URL_SKILL));
            employeeEmail.setToAddress(employeeDetails.getEmail());
            employeeEmail.setCcAddress(String.join(COMMA, emailList));
            employeeEmail.setModule(MODULE_SKILL);
            employeeEmail.setScheduled(false);
            employeeEmail.setSent(false);
            employeeEmail.setCreatedDate(getIstDate());
            EmailLocalServiceUtil.addEmail(employeeEmail);
        }
    }

    @Override
    public void downloadEmployeeDataReport(ResourceResponse response) throws IOException {
        List<Skill> skillList = SkillLocalServiceUtil.getSkills(-1, -1);
        List<Certificate> certificateList = CertificateLocalServiceUtil.getCertificates(-1, -1);
        List<EmployeeDto> employeeDtoList = getEmployeeDataList();
        Set<String> ecodes = employeeDtoList.stream().map(EmployeeDto::getEmpEcode).collect(Collectors.toSet());


        List<ExcelDto> employeeSkillExcelDto = new ArrayList<>();
        if (!skillList.isEmpty()) {
            ExcelDto sheet = new ExcelDto();
            sheet.setSheetName("Skill Set Sheet");
            sheet.setHeaders(HEADERS_SKILL_REPORT);
            List<List<String>> skillRows = new ArrayList<>();

            for (String ecode : ecodes) {
                EmployeeDto employeeDataList = employeeDtoList.stream().filter(s -> s.getEmpEcode().equalsIgnoreCase(ecode)).collect(Collectors.toList()).get(0);
                List<Skill> empSkills = skillList.stream().filter(s -> s.getStatus() != STATUS_DISAPPROVED).filter(s -> s.getEcode().equalsIgnoreCase(ecode)).collect(Collectors.toList());

                for (Skill empSkill : empSkills) {
                    List<String> skill = new ArrayList<>();
                    skill.add(ecode);
                    skill.add(employeeDataList.getEmpName());
                    skill.add(employeeDataList.getAccount());
                    skill.add(employeeDataList.getManagerName());
                    skill.add(employeeDataList.getReviewerName());
                    skill.add(empSkill.getCoreSkill());
                    skill.add(empSkill.getSubSkill());
                    skill.add(getStatus(empSkill.getStatus()));
                    skill.add(empSkill.getPrimarySkill() ? PRIMARY : SECONDARY);
                    skill.add(getProficiencyLevel(empSkill.getProficiencyLevel()));
                    skill.add(getProficiencyLevel(empSkill.getRequiredLevel()));
                    skill.add(empSkill.getTool());
                    skill.add(empSkill.getValidity());
                    skill.add(empSkill.getVersion());
                    skillRows.add(skill);
                }
            }
            sheet.setData(skillRows);
            sheet.setSheetIndex(0);
            employeeSkillExcelDto.add(sheet);
        }
        if (!certificateList.isEmpty()) {
            ExcelDto certificateSheet = new ExcelDto();
            certificateSheet.setSheetName("Certificate Sheet");
            certificateSheet.setHeaders(HEADERS_CERTIFICATE_REPORT);
            List<List<String>> certificateRows = new ArrayList<>();
            for (String ecode : ecodes) {
                EmployeeDto employeeDataList = employeeDtoList.stream().filter(s -> s.getEmpEcode().equalsIgnoreCase(ecode)).collect(Collectors.toList()).get(0);
                List<Certificate> certificates = certificateList.stream().filter(s -> s.getEcode().equalsIgnoreCase(ecode)).collect(Collectors.toList());
                for (Certificate certificateData : certificates) {
                    List<String> certificate = new ArrayList<>();
                    certificate.add(ecode);
                    certificate.add(employeeDataList.getEmpName());
                    certificate.add(employeeDataList.getAccount());
                    certificate.add(employeeDataList.getManagerName());
                    certificate.add(employeeDataList.getReviewerName());
                    certificate.add(certificateData.getCategory());
                    certificate.add(certificateData.getName());
                    certificate.add(certificateData.getDescription());
                    if (certificateData.getExpiryDate() != null) {
                        certificate.add(convertDateToLocalDateTime(certificateData.getExpiryDate()).format(FORMATTER_YYYY_MM_DD));
                    }
                    certificateRows.add(certificate);

                }
            }
            certificateSheet.setData(certificateRows);
            certificateSheet.setSheetIndex(1);
            employeeSkillExcelDto.add(certificateSheet);
        }
        ExcelService.createWorkBook(response, "SKILL_AND_CERTIFICATE_REPORT", employeeSkillExcelDto);
    }

    @Override
    public List<CertificateDto> findEmployeeCertificates(String ecode) {
        ArrayList<CertificateDto> certificateList = new ArrayList<>();
        List<Certificate> empCertificateList = CertificateLocalServiceUtil.findCertificatesByEcode(ecode);
        for (Certificate certificate : empCertificateList) {
            CertificateDto certificateDto = new CertificateDto();
            certificateDto.setCertificateId(certificate.getCertificateId());
            certificateDto.setCertificateName(certificate.getName());
            certificateDto.setFileId(certificate.getFileId());
            certificateDto.setFileName(certificate.getFilename());
            if (null != certificate.getExpiryDate()) {
                certificateDto.setExpiryDate(convertDateToLocalDateTime(certificate.getExpiryDate()).format(FORMATTER_YYYY_MM_DD));
            }
            certificateDto.setDescription(certificate.getDescription());
            certificateDto.setCategory(certificate.getCategory());
            certificateList.add(certificateDto);
        }
        return certificateList;
    }

    @Override
    public EmployeeDto getEmployeeData(String role, String ecode, List<Skill> skillList) throws PortalException {
        Employee employeeList = EmployeeLocalServiceUtil.getEmployee(ecode);
        Employee managerData = EmployeeLocalServiceUtil.getEmployee(employeeList.getManager());
        Employee reviewerData = EmployeeLocalServiceUtil.getEmployee(employeeList.getReviewer());
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmpEcode(ecode);
        if (null != employeeList) {
            employeeDto.setEmpName(employeeList.getName());
            employeeDto.setAccount(employeeList.getAccount());
            employeeDto.setEmpBand(employeeList.getBand());
            employeeDto.setEmpDesignation(employeeList.getDesignation());
            employeeDto.setEmpMobileNo(employeeList.getMobile());
            employeeDto.setManagerEmail(null == managerData ? BLANK : managerData.getEmail());
            employeeDto.setManagerName(null == managerData ? BLANK : managerData.getName());
            employeeDto.setReviewerEmail(null == reviewerData ? BLANK : reviewerData.getEmail());
            employeeDto.setReviewerName(null == reviewerData ? BLANK : reviewerData.getName());
        }
        List<SkillRejectionComment> commentList = new ArrayList<>(SkillRejectionCommentLocalServiceUtil.findRejectionCommentByEcode(ecode));
        StringBuilder comments = new StringBuilder();
        if (commentList.isEmpty()) {
            comments.append(NO_HISTORY);
        } else {
            commentList.sort((SkillRejectionComment s1, SkillRejectionComment s2) -> s2.getCreatedDate().compareTo(s1.getCreatedDate()));
            comments.append(HISTORY);
            for (SkillRejectionComment comment : commentList) {
                comments.append(System.getProperty(LINE_SEPARATOR));
                comments.append(convertDateToLocalDateTime(comment.getCreatedDate()).format(FORMATTER_YYYY_MM_DD));
                comments.append(HYPHEN);
                comments.append(comment.getDescription());
            }
        }
        employeeDto.setComment(String.valueOf(comments));
        List<Skill> submittedSkillList = skillList.stream().filter(s -> s.getStatus() == SUBMISSION_STATE).collect(Collectors.toList());
        if ((role.equalsIgnoreCase(ROLE_MANAGER) || role.equalsIgnoreCase(ROLE_TEAMLEAD)) && !submittedSkillList.isEmpty()) {
            employeeDto.setMode(MODE_EDIT);
        } else {
            employeeDto.setMode(MODE_VIEW);
        }
        return employeeDto;
    }

    @Override
    public String getCertificate(long certificateId) {
        Certificate certificate = CertificateLocalServiceUtil.fetchCertificate(certificateId);
        String fileExtension = certificate.getFilename().substring(certificate.getFilename().lastIndexOf('.') + 1);
        File file = DriveService.getFile(certificate.getFileId(), certificate.getFileId(), fileExtension);
        return "\"" + getBase64File(file) + "\"";
    }

    @Override
    public Object getUserManualObject(ResourceRequest request) {
        Integer roleAction = ParamUtil.getInteger(request, "roleAction");
        String role;
        switch (roleAction) {
            case 1:
                role = ROLE_TEAMLEAD;
                break;
            case 2:
                role = ROLE_MANAGER;
                break;
            case 3:
                role = ROLE_HR;
                break;
            case 4:
                role = ROLE_LEADER;
                break;
            default:
                role = ROLE_RECRUITER;
        }
        return "\"" + new String(getUserManual(MODULE_SKILL, role).getBytes(), StandardCharsets.UTF_8) + "\"";
    }


    public String getProficiencyLevel(int status) {
        String returnValue;
        if (status == 1) {
            returnValue = BASIC;
        } else if (status == 2) {
            returnValue = BEGINNER;
        } else if (status == 3) {
            returnValue = INTERMEDIATE;
        } else if (status == 4) {
            returnValue = ADVANCED;
        } else {
            returnValue = EXPERT;
        }
        return returnValue;
    }

    public String getStatus(int status) {
        String returnValue = BLANK;
        if (status == 0) {
            returnValue = SUBMITTED;
        } else if (status == 1) {
            returnValue = APPROVED;
        } else if (status == 2) {
            returnValue = DISAPPROVED;
        }
        return returnValue;
    }
}
