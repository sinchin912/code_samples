package com.trantorinc.synergy.skill.web.portlet;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.trantorinc.synergy.skill.web.constants.SkillWebPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import com.trantorinc.synergy.skill.web.service.SkillService;
import com.trantorinc.synergy.skill.web.service.SkillServiceImpl;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;


import static com.trantorinc.synergy.common.service.constant.AppConstantService.*;
import static com.trantorinc.synergy.common.service.constant.AppConstantService.MODULE_EXPENSE;
import static com.trantorinc.synergy.common.service.util.UtilService.*;

/**
 * @author saurabh.kumar
 */
@Component(
        immediate = true,
        property = {
                "com.liferay.portlet.display-category=category.sample",
                "com.liferay.portlet.header-portlet-css=/css/main.css",
                "com.liferay.portlet.instanceable=true",
                "javax.portlet.display-name=SkillWeb",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/view.jsp",
                "javax.portlet.name=" + SkillWebPortletKeys.SKILLWEB,
                "javax.portlet.init-param.add-process-action-success-action=false",
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
)

public class SkillWebPortlet extends MVCPortlet {
    final Log log = LogFactoryUtil.getLog(SkillWebPortlet.class.getName());

    private SkillService skillService = null;

    @Override
    public void init() throws PortletException {
        skillService = new SkillServiceImpl();
        super.init();
    }

    @Override
    public void destroy() {
        skillService = null;
        super.destroy();
    }

    @Override
    public void doView(RenderRequest request, RenderResponse response) throws IOException, PortletException {
            skillService.getAllSkillDetails(request);
        super.doView(request, response);
    }

    @ProcessAction(name = "updateSkills")
    public void updateSkills(ActionRequest request, ActionResponse response) throws IOException, PortalException {
        log.info("skillAddDelete");
        skillService.updateSkills(request);
        redirect(request, response);
    }

    @ProcessAction(name = "updateCertificates")
    public void updateCertificates(ActionRequest request, ActionResponse response) throws IOException, PortalException {
        skillService.updateCertificates(request);
        redirect(request, response);
    }

    @Override
    public void serveResource(ResourceRequest request, ResourceResponse response)  {
        Object dataToWrite = null;
        if (request.getResourceID().equals("downloadUserManual")) {
            dataToWrite = new String(getUserManual(MODULE_EXPENSE, ROLE_EMPLOYEE).getBytes(), StandardCharsets.UTF_8);
        } else if (request.getResourceID().equals("downloadCertificates")) {
            long certificateId = ParamUtil.getLong(request, "certificateId");
            dataToWrite = skillService.getCertificate(certificateId);
        }
        try (PrintWriter writer = response.getWriter()) {
            writer.print("{\"data\": \"" + dataToWrite + "\"}");
            writer.flush();
        } catch (IOException exception) {
            log.error(exception.getStackTrace()[0].getMethodName() + ":" + exception.getStackTrace()[0].getLineNumber() + ":" + exception.getMessage());
        }
    }
}