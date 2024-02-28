package com.trantorinc.synergy.orgchart.admin.web.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.trantorinc.synergy.orgchart.admin.web.constants.OrgchartAdminWebPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import com.trantorinc.synergy.orgchart.admin.web.service.OrgchartAdminService;
import com.trantorinc.synergy.orgchart.admin.web.service.OrgchartAdminServiceImpl;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;
import java.io.PrintWriter;

import static com.trantorinc.synergy.common.service.constant.AppConstantService.BLANK;
import static com.trantorinc.synergy.common.service.util.UtilService.convertToJson;

/**
 * @author sachin.goyal
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/orgchartAdmin.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=OrgchartAdminWeb",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + OrgchartAdminWebPortletKeys.ORGCHARTWEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class OrgchartAdminWebPortlet extends MVCPortlet {

	private static final Log log = LogFactoryUtil.getLog(OrgchartAdminWebPortlet.class.getName());

	private OrgchartAdminService orgchartAdminService = null;

	@Override
	public void init() throws PortletException {
		orgchartAdminService = new OrgchartAdminServiceImpl();
		super.init();
	}

	@Override
	public void destroy() {
		orgchartAdminService = null;
		super.destroy();
	}
	@Override
	public void doView(RenderRequest request, RenderResponse response) throws IOException, PortletException {
		request.setAttribute("accounts", orgchartAdminService.getAllAccounts());
		request.setAttribute("managers", orgchartAdminService.getAllManagers());
		request.setAttribute("managerOrgchart", convertToJson(orgchartAdminService.getManagerOrgchart(BLANK)));
		request.setAttribute("accountOrgchart", convertToJson(orgchartAdminService.getAccountOrgchart(BLANK)));
		super.doView(request, response);
	}

	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) {
		Object dataToWrite = null;
		if(resourceRequest.getResourceID().equals("accountDirectory")){
			String account = ParamUtil.getString(resourceRequest, "account").trim();
			dataToWrite =  convertToJson(orgchartAdminService.getAccountDirectory(account));
		} else if(resourceRequest.getResourceID().equals("accountOrgchart")){
			String account = ParamUtil.getString(resourceRequest, "account").trim();
			dataToWrite =  convertToJson(orgchartAdminService.getAccountOrgchart(account));
		} else if(resourceRequest.getResourceID().equals("managerOrgchart")){
			String managerEcode = ParamUtil.getString(resourceRequest, "managerEcode").trim();
			dataToWrite =  convertToJson(orgchartAdminService.getManagerOrgchart(managerEcode));
		}
		try (PrintWriter writer = resourceResponse.getWriter()){
			writer.print("{\"data\": " + dataToWrite+ "}");
			writer.flush();
		} catch (IOException exception) {
			log.error(exception.getStackTrace()[0].getMethodName()+":" +exception.getStackTrace()[0].getLineNumber()+":"+ exception.getMessage());
		}
	}
}