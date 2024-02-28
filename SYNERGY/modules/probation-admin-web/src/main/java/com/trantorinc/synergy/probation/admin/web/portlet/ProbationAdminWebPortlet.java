package com.trantorinc.synergy.probation.admin.web.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.trantorinc.synergy.lms.core.model.Employee;
import com.trantorinc.synergy.lms.core.service.EmployeeLocalServiceUtil;
import com.trantorinc.synergy.probation.admin.web.constants.ProbationAdminWebPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import com.trantorinc.synergy.probation.admin.web.service.ProbationService;
import com.trantorinc.synergy.probation.admin.web.service.ProbationServiceImpl;
import com.trantorinc.synergy.probation.core.model.Probation;
import com.trantorinc.synergy.probation.core.service.ProbationLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

import static com.trantorinc.synergy.common.service.constant.AppConstantService.*;
import static com.trantorinc.synergy.common.service.util.UtilService.*;

/**
 * @author sachin.goyal
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=ProbationAdminWeb",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + ProbationAdminWebPortletKeys.PROBATIONADMINWEB,
		 "javax.portlet.init-param.add-process-action-success-action=false",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class ProbationAdminWebPortlet extends MVCPortlet {

	private static final Log log = LogFactoryUtil.getLog(ProbationAdminWebPortlet.class.getName());

	private ProbationService probationService = null;

	@Override
	public void init() throws PortletException {
		probationService = new ProbationServiceImpl();
		super.init();
	}

	@Override
	public void destroy() {
		probationService = null;
		super.destroy();
	}

	@Override
	public void doView(RenderRequest request, RenderResponse response) throws IOException, PortletException {
		Set<String> userRoles  = getLoggedUserRoles(request);
		List<Employee> employees = EmployeeLocalServiceUtil.findAllEmployees();
		List<Probation> probations = ProbationLocalServiceUtil.getProbations(-1, -1);
		if (userRoles.contains(ROLE_HR)) {
			request.setAttribute("hrPendingList", probationService.getHrPendingProbations(probations, employees));
			request.setAttribute("hrCompleteList", probationService.getHrCompleteProbations(probations, employees));
		}
		if (userRoles.contains(ROLE_TEAMLEAD) || userRoles.contains(ROLE_MANAGER)) {
			request.setAttribute("managerList", probationService.getManagerProbations(probations, employees, userRoles.contains(ROLE_MANAGER),getLoggedUser(request)));
		}
		super.doView(request, response);
	}

	@ProcessAction(name = "abscondProbation")
	public void abscondProbation(ActionRequest request, ActionResponse response) throws IOException {
		String abscondEmpId = ParamUtil.getString(request, "abscondId");
		probationService.abscondProbation(abscondEmpId);
		redirect(request, response);
	}


	@ProcessAction(name = "saveProbation")
	public void saveProbation(ActionRequest request, ActionResponse response) throws IOException {
		probationService.saveProbation(request);
		redirect(request, response);
	}

	@ProcessAction(name = "probationWorkflow")
	public void probationWorkflow(ActionRequest request, ActionResponse response) {
		String ecode = ParamUtil.getString(request, "probationId");
		request.setAttribute("probation", probationService.probationWorkflow(ecode));
		response.getRenderParameters().setValue("mvcPath", "/jsp/probation.jsp");
	}

	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws IOException {
		Object dataToWrite = null;
		if (resourceRequest.getResourceID().equals("downloadHrUserManual")) {
			dataToWrite = "\"" + getUserManual(MODULE_PROBATION, ROLE_HR) + "\"";
		} else if (resourceRequest.getResourceID().equals("downloadManagerUserManual")) {
			dataToWrite = "\"" + getUserManual(MODULE_PROBATION, ROLE_MANAGER) + "\"";
		} else if (resourceRequest.getResourceID().equals("downloadPendingProbations")) {
			probationService.exportProbation(false, resourceResponse);
		} else if (resourceRequest.getResourceID().equals("downloadCompletedProbations")) {
			probationService.exportProbation(true, resourceResponse);
		}
		try (PrintWriter writer = resourceResponse.getWriter()) {
			writer.print("{\"data\": " + dataToWrite + "}");
			writer.flush();
		} catch (IOException exception) {
			log.error(exception.getStackTrace()[0].getMethodName()+":" +exception.getStackTrace()[0].getLineNumber()+":"+ exception.getMessage());
		}
	}
}