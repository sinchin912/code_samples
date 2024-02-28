package com.trantorinc.synergy.employee.web.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.trantorinc.synergy.employee.web.constants.EmployeeWebPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import com.trantorinc.synergy.employee.web.dto.EmployeeDto;
import com.trantorinc.synergy.employee.web.service.EmployeePortletService;
import com.trantorinc.synergy.employee.web.service.EmployeePortletServiceImpl;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static com.trantorinc.synergy.common.service.util.UtilService.convertToJson;


/**
 * @author saurabh.kumar
 */
@Component(
		immediate = true,
		property = {
				"com.liferay.portlet.display-category=category.sample",
				"com.liferay.portlet.header-portlet-css=/css/main.css",
				"com.liferay.portlet.instanceable=true",
				"javax.portlet.display-name=EmployeeWeb",
				"javax.portlet.init-param.template-path=/",
				"javax.portlet.init-param.view-template=/view.jsp",
				"javax.portlet.name=" + EmployeeWebPortletKeys.EMPLOYEEWEB,
				"javax.portlet.resource-bundle=content.Language",
				"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
)
public class EmployeeWebPortlet extends MVCPortlet {

	private static final Log log = LogFactoryUtil.getLog(EmployeeWebPortlet.class.getName());

	private EmployeePortletService employeePortletService = null;

	@Override
	public void init() throws PortletException {
		employeePortletService = new EmployeePortletServiceImpl();
		super.init();
	}

	@Override
	public void destroy() {
		employeePortletService = null;
		super.destroy();
	}
	@Override
	public void doView(RenderRequest request, RenderResponse response) throws IOException, PortletException {
		super.doView(request, response);
	}
	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse){

		String employeeSearchResult = null;
		if(resourceRequest.getResourceID().equals("employeeDirectorySearch")) {
			String name = ParamUtil.getString(resourceRequest, "name").trim();
			String ecode = ParamUtil.getString(resourceRequest, "ecode").trim();
                List<EmployeeDto> employeeData= employeePortletService.findByEcodeOrName(ecode,name);
				employeeSearchResult = convertToJson(employeeData);
		}
		try(PrintWriter writer = resourceResponse.getWriter()) {
			writer.print("{\"data\": " + employeeSearchResult+ "}");
			writer.flush();
		} catch (IOException exception) {
			log.error(exception.getStackTrace()[0].getMethodName()+":" +exception.getStackTrace()[0].getLineNumber()+":"+ exception.getMessage());
		}
	}
}