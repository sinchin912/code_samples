package com.trantorinc.synergy.skill.web.service;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.trantorinc.synergy.common.service.drive.DriveService;
import com.trantorinc.synergy.lms.core.model.Employee;
import com.trantorinc.synergy.lms.core.service.DriveLocalServiceUtil;
import com.trantorinc.synergy.lms.core.service.EmployeeLocalServiceUtil;
import com.trantorinc.synergy.skill.core.model.Certificate;
import com.trantorinc.synergy.skill.core.model.Skill;
import com.trantorinc.synergy.skill.core.model.SkillGuide;
import com.trantorinc.synergy.skill.core.model.SkillRejectionComment;
import com.trantorinc.synergy.skill.core.service.CertificateLocalServiceUtil;
import com.trantorinc.synergy.skill.core.service.SkillGuideLocalServiceUtil;
import com.trantorinc.synergy.skill.core.service.SkillLocalServiceUtil;
import com.trantorinc.synergy.skill.core.service.SkillRejectionCommentLocalServiceUtil;
import com.trantorinc.synergy.skill.web.dto.CertificateDataDto;
import com.trantorinc.synergy.skill.web.dto.EmployeeDto;
import com.trantorinc.synergy.skill.web.dto.SkillDto;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;


import java.io.File;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static com.trantorinc.synergy.common.service.constant.AppConstantService.*;
import static com.trantorinc.synergy.common.service.constant.AppConstantService.BLANK;
import static com.trantorinc.synergy.common.service.util.UtilService.*;
import static com.trantorinc.synergy.skill.web.constants.SkillWebPortletKeys.*;

public class SkillServiceImpl implements SkillService {

    @Override
    public void getAllSkillDetails(RenderRequest request) {
        List<SkillDto> empSkillsSet = new ArrayList<>();
        List<SkillGuide> guides = SkillGuideLocalServiceUtil.getSkillGuides(-1, -1);
        Set<String> coreSkills = guides.stream().map(SkillGuide::getCoreSkill).filter(coreSkill -> !coreSkill.equalsIgnoreCase(CERTIFICATE)).collect(Collectors.toSet());
        Map<String, Set<String>> skillsMap = new HashMap<>();
        for (String skill : coreSkills) {
            skillsMap.put(skill, new TreeSet<>(guides.stream().filter(guide -> guide.getCoreSkill().equalsIgnoreCase(skill)).map(SkillGuide::getSubSkill).collect(Collectors.toSet())));
        }
        Map<String, Set<String>> result = skillsMap.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(oldValue, newValue) -> oldValue, LinkedHashMap::new));
        Employee employeeData = EmployeeLocalServiceUtil.findByEmail(getLoggedUser(request));
        List<Skill> empSkills = SkillLocalServiceUtil.findSkillsByEmployee(employeeData.getEcode());
        for (Skill skills : empSkills) {
            SkillDto skillDto = new SkillDto();
            skillDto.setCoreSkill(skills.getCoreSkill());
            skillDto.setSubSkill(skills.getSubSkill());
            skillDto.setType(skills.getPrimarySkill());
            skillDto.setVersion(skills.getVersion());
            skillDto.setTool(skills.getTool());
            skillDto.setValidity(skills.getValidity());
            skillDto.setSkillId(skills.getSkillId());
            String skillStatus;
            switch (skills.getStatus()) {
                case 1:
                    skillStatus = APPROVED;
                    break;
                case 2:
                    skillStatus = DISAPPROVED;
                    break;
                default:
                    skillStatus = SUBMITTED;
            }
            skillDto.setStatus(skillStatus);
            skillDto.setProficiencyLevel(skills.getProficiencyLevel());
            empSkillsSet.add(skillDto);
        }
        List<Certificate> certificateList = CertificateLocalServiceUtil.findCertificatesByEcode(employeeData.getEcode());
        List<CertificateDataDto> dataDtoList = new ArrayList<>();
        for (Certificate certificate : certificateList) {
            CertificateDataDto dataDto = new CertificateDataDto();
            dataDto.setCertificateCategory(certificate.getCategory());
            dataDto.setCertificateDesc(certificate.getDescription());
            dataDto.setCertificateName(certificate.getName());
            dataDto.setCertificateId(certificate.getCertificateId());
            dataDto.setFileName(certificate.getFilename());
            if(null !=certificate.getExpiryDate()) {
                dataDto.setExpiryDate(convertDateToLocalDateTime(certificate.getExpiryDate()).format(FORMATTER_YYYY_MM_DD));
            }
            dataDtoList.add(dataDto);
        }
        Employee managerData = EmployeeLocalServiceUtil.fetchEmployee(employeeData.getManager());
        Employee reviewerData = EmployeeLocalServiceUtil.fetchEmployee(employeeData.getReviewer());
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmpEcode(employeeData.getEcode());
        employeeDto.setEmpName(employeeData.getName());
        employeeDto.setAccount(employeeData.getAccount());
        employeeDto.setEmpBand(employeeData.getBand());
        employeeDto.setEmpDesignation(employeeData.getDesignation());
        employeeDto.setEmpMobileNo(employeeData.getMobile());
        employeeDto.setManagerEmail(null == managerData ? BLANK : managerData.getEmail());
        employeeDto.setManagerName(null == managerData ? BLANK : managerData.getName());
        employeeDto.setReviewerEmail(null == reviewerData ? BLANK : reviewerData.getEmail());
        employeeDto.setReviewerName(null == reviewerData ? BLANK : reviewerData.getName());
        List<SkillRejectionComment> commentList = new ArrayList<>(SkillRejectionCommentLocalServiceUtil.findRejectionCommentByEcode(employeeData.getEcode()));
        StringBuilder comments = new StringBuilder();
        if (!commentList.isEmpty()) {
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
        Set<String> certificates = guides.stream().filter(g -> g.getCoreSkill().equalsIgnoreCase(CERTIFICATE)).map(SkillGuide::getSubSkill).collect(Collectors.toSet());
        request.setAttribute("certificatesList", dataDtoList);
        request.setAttribute("certificateSet", convertToJson(new TreeSet<>(certificates)));
        request.setAttribute("empSkillsSet", empSkillsSet);
        request.setAttribute("skillMap", result);
        request.setAttribute("skillsMap", convertToJson(result));
        request.setAttribute("empCoreDetails", employeeDto);
    }

    @Override
    public void updateSkills(PortletRequest request) throws PortalException {

        int action = ParamUtil.getInteger(request, "skillActionPerformed");
        if (action == 1) {
            int totalRows = ParamUtil.getInteger(request, "totalRows");
            for (int i = 1; i <= totalRows; i++) {
                String coreSkill = ParamUtil.getString(request, "coreSkill" + i);
                String subSkill = ParamUtil.getString(request, "subSkill" + i);
                String skillType = ParamUtil.getString(request, "type" + i);
                Integer proficiencyLevel = ParamUtil.getInteger(request, "proficiencyLevel" + i);
                String tool = ParamUtil.getString(request, "tool" + i);
                String validity = ParamUtil.getString(request, "validity" + i);
                String version = ParamUtil.getString(request, "version" + i);

                long skillId = ParamUtil.getLong(request, "skillId" + i);
                if (skillId == 0) {

                    Skill skill = SkillLocalServiceUtil.createSkill(CounterLocalServiceUtil.increment());
                    Skill skillValues = setSkillValues(skill, getLoggedUser(request), coreSkill, subSkill, skillType.equalsIgnoreCase(PRIMARY), tool, validity, version, proficiencyLevel);
                    SkillLocalServiceUtil.addSkill(skillValues);
                } else {
                    Skill skill = SkillLocalServiceUtil.fetchSkill(skillId);
                    if (!(coreSkill.equalsIgnoreCase(skill.getCoreSkill()) && subSkill.equalsIgnoreCase(skill.getSubSkill()) && version.equals(skill.getVersion()) && (skillType.equalsIgnoreCase(PRIMARY) == skill.getPrimarySkill()) && tool.equals(skill.getTool()) && validity.equalsIgnoreCase(skill.getValidity()) && (proficiencyLevel == skill.getProficiencyLevel()))) {
                        Skill skillValues = setSkillValues(skill, getLoggedUser(request), coreSkill, subSkill, skillType.equalsIgnoreCase(PRIMARY), tool, validity, version, proficiencyLevel);
                        SkillLocalServiceUtil.updateSkill(skillValues);
                    }
                }
            }
        } else if (action == 2) {
            long deleteSkillId = ParamUtil.getLong(request, "deletedSkillId");
            SkillLocalServiceUtil.deleteSkill(deleteSkillId);
        }
    }

    @Override
    public void updateCertificates(ActionRequest request) throws PortalException {
        Employee employeeData = EmployeeLocalServiceUtil.findByEmail(getLoggedUser(request));
        int action = ParamUtil.getInteger(request, "certificateActionPerformed");
        if (action == 1)//Update-add Certificate
        {
            int totalRowsCertificate = ParamUtil.getInteger(request, "totalRowsCertificate");
            for (int i = 1; i <= totalRowsCertificate; i++) {
                String certificateCategory = ParamUtil.getString(request, "certificateCategory" + i);
                String certificateName = ParamUtil.getString(request, "certificateName" + i);
                String certificateDesc = ParamUtil.getString(request, "certificateDesc" + i);
                String filename = ParamUtil.getString(request, "filename" + i);
                UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(request);
                File file = uploadPortletRequest.getFile("upload" + i);
                Certificate certificate = CertificateLocalServiceUtil.createCertificate(CounterLocalServiceUtil.increment());
                String folderId = DriveLocalServiceUtil.findFolderIdByFolderName(MODULE_SKILL);
                certificate.setName(certificateName);
                certificate.setCategory(certificateCategory);
                certificate.setDescription(certificateDesc);
                String expiryDate = ParamUtil.getString(request, "expiryDate" + i);
                if (null != expiryDate && !expiryDate.isEmpty()) {
                    certificate.setExpiryDate(convertLocalDateTimeToDate(LocalDate.parse(expiryDate, FORMATTER_YYYY_MM_DD).atStartOfDay()));
                }
                certificate.setCreatedDate(getIstDate());
                certificate.setEcode(employeeData.getEcode());
                String fileId = DriveService.uploadFile(folderId, filename, file);
                certificate.setFilename(filename);
                certificate.setFileId(fileId);
                CertificateLocalServiceUtil.addCertificate(certificate);
            }
        } else if (action == 2) {
            long certificateId = ParamUtil.getLong(request, "deletedCertificateId");
            CertificateLocalServiceUtil.deleteCertificate(certificateId);
        }
    }


    @Override
    public String getCertificate(long certificateId) {
        Certificate certificate = CertificateLocalServiceUtil.fetchCertificate(certificateId);
        String fileExtension = certificate.getFilename().substring(certificate.getFilename().lastIndexOf('.') + 1);
        File file = DriveService.getFile(certificate.getFileId(), certificate.getFileId(), fileExtension);
        return getBase64File(file);
    }

    private Skill setSkillValues(Skill skill, String empEmail, String coreSkill, String subSkill, boolean skillType, String tool, String validity, String version, Integer proficiencyLevel) {
        Employee employeeData = EmployeeLocalServiceUtil.findByEmail(empEmail);
        Employee managerData = EmployeeLocalServiceUtil.fetchEmployee(employeeData.getManager());
        Employee reviewerData = EmployeeLocalServiceUtil.fetchEmployee(employeeData.getReviewer());
        skill.setCoreSkill(coreSkill);
        skill.setSubSkill(subSkill);
        skill.setPrimarySkill(skillType);
        skill.setTool(tool);
        skill.setValidity(validity);
        skill.setVersion(version);
        skill.setProficiencyLevel(proficiencyLevel);
        skill.setRequiredLevel(proficiencyLevel);
        skill.setStatus(SUBMITTED_STATUS);
        skill.setProject(employeeData.getAccount());
        skill.setEcode(employeeData.getEcode());
        skill.setManager(managerData.getEmail());
        skill.setReviewer(reviewerData.getEmail());
        return skill;
    }
}
