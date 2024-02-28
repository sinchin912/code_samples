package com.trantorinc.synergy.training.admin.web.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.trantorinc.synergy.training.admin.web.constants.TrainingAdminWebPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import com.trantorinc.synergy.training.admin.web.service.TrainingAdminService;
import com.trantorinc.synergy.training.admin.web.service.TrainingAdminServiceImpl;
import org.osgi.service.component.annotations.Component;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author sachin.goyal
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=TrainingAdminWeb",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + TrainingAdminWebPortletKeys.TRAININGADMINWEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class TrainingAdminWebPortlet extends MVCPortlet {

	private static final Log log = LogFactoryUtil.getLog(TrainingAdminWebPortlet.class.getName());
	private TrainingAdminService trainingAdminService = null;
	@Override
	public void init() throws PortletException {
		trainingAdminService = new TrainingAdminServiceImpl();
		super.init();
	}

	@Override
	public void destroy() {
		trainingAdminService = null;
		super.destroy();
	}

	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws IOException {
		Object dataToWrite = null;
		if (resourceRequest.getResourceID().equals("exportEmployee")) {
			trainingAdminService.exportEmployee(resourceResponse);
		}
		try(PrintWriter writer = resourceResponse.getWriter()) {
			writer.print("{\"data\": " + dataToWrite+ "}");
			writer.flush();
		} catch (IOException exception) {
			log.error(exception.getStackTrace()[0].getMethodName()+":" +exception.getStackTrace()[0].getLineNumber()+":"+ exception.getMessage());
		}
	}


}