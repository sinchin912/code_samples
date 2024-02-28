package com.trantorinc.synergy.skill.web.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;

public interface SkillService {

    void updateSkills(PortletRequest request) throws PortalException;
    void updateCertificates(ActionRequest request) throws PortalException;
    void getAllSkillDetails(RenderRequest request);
    String getCertificate(long certificateId) ;
}
