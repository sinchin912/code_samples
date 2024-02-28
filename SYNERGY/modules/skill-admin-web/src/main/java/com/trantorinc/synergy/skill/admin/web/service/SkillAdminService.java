package com.trantorinc.synergy.skill.admin.web.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.trantorinc.synergy.skill.admin.web.dto.CertificateDto;
import com.trantorinc.synergy.skill.admin.web.dto.EmployeeDto;
import com.trantorinc.synergy.skill.admin.web.dto.SkillDto;
import com.trantorinc.synergy.skill.core.model.Skill;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;
import java.util.List;

public interface SkillAdminService {
    List<EmployeeDto> getEmployeeDataList();

    List<EmployeeDto> getAllLeadEntries(List<EmployeeDto> employeeList , String loggedUserEmail);
    List<EmployeeDto> getAllManagerEntries(List<EmployeeDto> employeeList , List<Skill> allEmployeeSkillLists , String loggedUserEmail);
    List<EmployeeDto> getAllHrLeaderRecruiterEntries(List<EmployeeDto> employeeList , List<Skill> allEmployeeSkillLists );
     void addNewSubSkill(ActionRequest request);
     void addNewCertificate(ActionRequest request);
    EmployeeDto getEmployeeData(String role , String empCode , List<Skill> skillList) throws PortalException;
    List<SkillDto> getEmployeeSkillData(String role , String empCode , List<Skill> skillList) throws PortalException;
    void submitEmpSkillRating(ActionRequest request);
    void downloadEmployeeDataReport(ResourceResponse request) throws IOException;
    List<CertificateDto> findEmployeeCertificates(String ecode);
    String getCertificate(long certificateId) ;
    Object getUserManualObject(ResourceRequest request);
    List<SkillDto> getEmployeeDetailsByKeyword(ResourceRequest request);


}
