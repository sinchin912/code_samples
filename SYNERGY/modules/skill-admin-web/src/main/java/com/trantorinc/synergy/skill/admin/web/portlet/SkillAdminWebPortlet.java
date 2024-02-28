package com.trantorinc.synergy.skill.admin.web.portlet;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;

import com.trantorinc.synergy.skill.admin.web.constants.SkillAdminWebPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import com.trantorinc.synergy.skill.admin.web.dto.EmployeeDto;
import com.trantorinc.synergy.skill.admin.web.dto.SkillDto;
import com.trantorinc.synergy.skill.admin.web.service.SkillAdminService;
import com.trantorinc.synergy.skill.admin.web.service.SkillAdminServiceImpl;
import com.trantorinc.synergy.skill.core.model.Skill;
import com.trantorinc.synergy.skill.core.model.SkillGuide;
import com.trantorinc.synergy.skill.core.service.SkillGuideLocalServiceUtil;
import com.trantorinc.synergy.skill.core.service.SkillLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

import static com.trantorinc.synergy.common.service.constant.AppConstantService.*;
import static com.trantorinc.synergy.common.service.util.UtilService.*;
import static com.trantorinc.synergy.skill.admin.web.constants.SkillAdminWebPortletKeys.*;
import static com.trantorinc.synergy.skill.admin.web.constants.SkillAdminWebPortletKeys.SUBSKILLS;

/**
 * @author saurabh.kumar
 */
@Component(
        immediate = true,
        property = {
                "com.liferay.portlet.display-category=category.sample",
                "com.liferay.portlet.header-portlet-css=/css/main.css",
                "com.liferay.portlet.instanceable=true",
                "javax.portlet.display-name=SkillAdminWeb",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/view.jsp",
                "javax.portlet.name=" + SkillAdminWebPortletKeys.SKILLADMINWEB,
                "javax.portlet.init-param.add-process-action-success-action=false",
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
)
public class SkillAdminWebPortlet extends MVCPortlet {
    Log log = LogFactoryUtil.getLog(SkillAdminWebPortlet.class);
    private SkillAdminService skillAdminService;

    @Override
    public void init() throws PortletException {
        skillAdminService = new SkillAdminServiceImpl();
        super.init();
    }

    @Override
    public void destroy() {
        skillAdminService = null;
        super.destroy();
    }

    @Override
    public void doView(RenderRequest request, RenderResponse response) throws IOException, PortletException {
        Set<String> userRoles = getLoggedUserRoles(request);
        List<EmployeeDto> employeeList = skillAdminService.getEmployeeDataList();
        List<Skill> allEmployeeSkillLists = SkillLocalServiceUtil.getSkills(-1, -1);
        List<SkillGuide> skillGuideList = SkillGuideLocalServiceUtil.getSkillGuides(-1, -1);
        Set<String> coreSkills = skillGuideList.stream().filter(s -> !s.getCoreSkill().equalsIgnoreCase(CERTIFICATE)).map(SkillGuide::getCoreSkill).collect(Collectors.toSet());
        Set<String> subSkills = skillGuideList.stream().filter(s -> !s.getCoreSkill().equalsIgnoreCase(CERTIFICATE)).map(SkillGuide::getSubSkill).collect(Collectors.toSet());
        Set<String> certificateNames = skillGuideList.stream().filter(s -> s.getCoreSkill().equalsIgnoreCase(CERTIFICATE)).map(SkillGuide::getSubSkill).collect(Collectors.toSet());


        if (userRoles.contains(ROLE_HR) || userRoles.contains(ROLE_LEADER) || userRoles.contains(ROLE_RECRUITER)) {
            List<EmployeeDto> allEntries = skillAdminService.getAllHrLeaderRecruiterEntries(employeeList, allEmployeeSkillLists);
            if (userRoles.contains(ROLE_HR)) {
                Map<String, Set<String>> skillsMap = new HashMap<>();
                for (String skill : coreSkills) {
                    skillsMap.put(skill, skillGuideList.stream().filter(s -> s.getCoreSkill().equalsIgnoreCase(skill)).map(SkillGuide::getSubSkill).collect(Collectors.toSet()));
                }
                request.setAttribute("hrEmployeeList", allEntries);
                request.setAttribute("skillMapGson", convertToJson(skillsMap));
            }
            if (userRoles.contains(ROLE_LEADER)) {
                request.setAttribute("leaderShipEmployeeList", allEntries);
            }
            if (userRoles.contains(ROLE_RECRUITER)) {
                request.setAttribute("recruiterEmployeeList", allEntries);
            }
            request.setAttribute(CORESKILLS, new TreeSet<>(coreSkills));
            request.setAttribute(SUBSKILLS, new TreeSet<>(subSkills));
            request.setAttribute(CERTIFICATES, new TreeSet<>(certificateNames));
        }
        if (userRoles.contains(ROLE_TEAMLEAD)) {
            List<EmployeeDto> leadEntries = skillAdminService.getAllLeadEntries(employeeList, getLoggedUser(request));
            request.setAttribute("leadEmployeeList", leadEntries);
        }
        if (userRoles.contains(ROLE_MANAGER)) {
            List<EmployeeDto> managerDataEntries = skillAdminService.getAllManagerEntries(employeeList, allEmployeeSkillLists, getLoggedUser(request));
            request.setAttribute("managerDataList", managerDataEntries);
        }
        super.doView(request, response);
    }

    @ProcessAction(name = "addSubSkill")
    public void addSubSkill(ActionRequest request, ActionResponse response) throws IOException {
        skillAdminService.addNewSubSkill(request);
        redirect(request, response);
    }

    @ProcessAction(name = "addNewCertificate")
    public void addNewCertificate(ActionRequest request, ActionResponse response) throws IOException {
        skillAdminService.addNewCertificate(request);
        redirect(request, response);
    }

    @ProcessAction(name = "skillWorkflow")
    public void skillWorkflow(ActionRequest request, ActionResponse response) throws PortalException {
        String role = ParamUtil.getString(request, "role");
        String ecode = ParamUtil.getString(request, "ecode");
        List<Skill> skillList = SkillLocalServiceUtil.findSkillsByEmployee(ecode);
        EmployeeDto employeeDto = skillAdminService.getEmployeeData(role, ecode, skillList);
        request.setAttribute("empCoreDetails", employeeDto);
        request.setAttribute("empSkillsSet", skillAdminService.getEmployeeSkillData(role, ecode, skillList));
        request.setAttribute("certificatesList", skillAdminService.findEmployeeCertificates(ecode));
        response.getRenderParameters().setValue("mvcPath", "/jsp/skill.jsp");
    }

    @ProcessAction(name = "submitEmpSkillRating")
    public void submitEmpSkillRating(ActionRequest request, ActionResponse response) throws IOException {
        skillAdminService.submitEmpSkillRating(request);
        redirect(request, response);
    }

    @Override
    public void serveResource(ResourceRequest request, ResourceResponse response) throws IOException {
        Object dataToWrite = null;
         if (request.getResourceID().equals("employeeDetailsByKeywords")) {
            List<SkillDto> skillDtos = skillAdminService.getEmployeeDetailsByKeyword(request);
            dataToWrite = convertToJson(skillDtos);
        } else if (request.getResourceID().equals("downloadCertificates")) {
            Long certificateId = ParamUtil.getLong(request, "certificateId");
            dataToWrite =  skillAdminService.getCertificate(certificateId) ;
        } else if (request.getResourceID().equals("downloadEmployeeDataReport")) {
            skillAdminService.downloadEmployeeDataReport(response);
        } else if (request.getResourceID().equals("downloadUserManual")) {
            dataToWrite = skillAdminService.getUserManualObject(request);
        }
        try (PrintWriter writer = response.getWriter()) {
            writer.print("{\"data\": " + dataToWrite + "}");
            writer.flush();
        } catch (IOException exception) {
            log.error(exception.getStackTrace()[0].getMethodName() + ":" + exception.getStackTrace()[0].getLineNumber() + ":" + exception.getMessage());
        }

    }
}