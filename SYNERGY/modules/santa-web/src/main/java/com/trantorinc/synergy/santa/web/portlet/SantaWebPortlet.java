package com.trantorinc.synergy.santa.web.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.trantorinc.synergy.game.core.model.GameTimeline;
import com.trantorinc.synergy.game.core.model.Santa;
import com.trantorinc.synergy.game.core.service.GameTimelineLocalServiceUtil;
import com.trantorinc.synergy.game.core.service.SantaLocalServiceUtil;
import com.trantorinc.synergy.lms.core.model.Employee;
import com.trantorinc.synergy.lms.core.service.EmployeeLocalServiceUtil;
import com.trantorinc.synergy.santa.web.constants.SantaWebPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import com.trantorinc.synergy.santa.web.service.SantaService;
import com.trantorinc.synergy.santa.web.service.SantaServiceImpl;
import org.osgi.service.component.annotations.Component;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
		"javax.portlet.display-name=SantaWeb",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + SantaWebPortletKeys.SANTAWEB,
		"javax.portlet.init-param.add-process-action-success-action=false",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class SantaWebPortlet extends MVCPortlet {

	private static final Log log = LogFactoryUtil.getLog(SantaWebPortlet.class);
	private SantaService santaService = null;

	@Override
	public void init() throws PortletException {
		santaService = new SantaServiceImpl();
		super.init();
	}

	@Override
	public void destroy() {
		santaService = null;
		super.destroy();
	}

	@Override
	public void doView(RenderRequest request, RenderResponse response) throws IOException, PortletException {
		LocalDateTime now = getIstLocalDateTime();
		int currentYear = getCurrentYear();
		GameTimeline santaTimeline = GameTimelineLocalServiceUtil.getCalibratedTimeline().stream().filter(g -> g.getName().equalsIgnoreCase("SANTA")).collect(Collectors.toList()).get(0);
		ThemeDisplay td = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		User loggedUser = td.getUser();
		Employee employee = EmployeeLocalServiceUtil.findByEmail(loggedUser.getEmailAddress());

		List<Santa> santas = SantaLocalServiceUtil.findSantaByYear(currentYear);
		List<Santa> employeeNominations = santas.stream().filter(s -> s.getEcode().equalsIgnoreCase(employee.getEcode())).collect(Collectors.toList());

		if(now.isBefore(convertDateToLocalDateTime(santaTimeline.getOpenDate()))){
			request.setAttribute("mode","preOpen");
		} else if (now.isBefore(convertDateToLocalDateTime(santaTimeline.getFreezeDate()))){
			request.setAttribute("registrationModel",santaService.getRegistrationModel(employee, employeeNominations, santaTimeline));
			request.setAttribute("mode","registration");
		} else if (now.isBefore(convertDateToLocalDateTime(santaTimeline.getActionDate()))){
			if(!employeeNominations.isEmpty()){
				request.setAttribute("allocationModel",santaService.getAllocationModel(employee, santas, santaTimeline));
				request.setAttribute("mode", "allocation");
			} else {
				request.setAttribute("mode", "postClose");
			}
		} else if (now.isBefore(convertDateToLocalDateTime(santaTimeline.getCloseDate()))){
			if(!employeeNominations.isEmpty()){
				if (employeeNominations.get(0).getGuessedEcode()==null || employeeNominations.get(0).getGuessedEcode().isEmpty()) {
					request.setAttribute("gameModel",santaService.getGameModel(employeeNominations.get(0), santaTimeline));
					request.setAttribute("mode", "game");
				} else {
					request.setAttribute("resultModel",santaService.getResultModel(employeeNominations.get(0), santaTimeline));
					request.setAttribute("mode", "result");
				}

			} else {
				request.setAttribute("mode", "postClose");
			}
		} else {
			if(!employeeNominations.isEmpty()){
				request.setAttribute("resultModel",santaService.getResultModel(employeeNominations.get(0), santaTimeline));
				request.setAttribute("mode", "result");
			} else {
				request.setAttribute("mode","postClose");
			}
		}
		request.setAttribute("currentYear",currentYear);
		super.doView(request, response);
	}

	@ProcessAction(name = "submitRegistrationForm")
	public void submitRegistrationForm(ActionRequest request, ActionResponse response) throws IOException {
		santaService.submitRegistration(request);
		redirect(request,response);
	}

	@ProcessAction(name = "submitAllocationForm")
	public void submitAllocationForm(ActionRequest request, ActionResponse response) throws IOException {
		santaService.submitAllocation(getLoggedUser(request));
		redirect(request,response);
	}

	@ProcessAction(name = "submitSendEmailForm")
	public void submitSendEmailForm(ActionRequest request, ActionResponse response) throws IOException {
		santaService.submitSendEmail(getLoggedUser(request));
		redirect(request,response);
	}

	@ProcessAction(name = "submitGameForm")
	public void submitGameForm(ActionRequest request, ActionResponse response) throws IOException {
		UploadRequest uploadRequest = PortalUtil.getUploadPortletRequest(request);
		File photoImage = uploadRequest.getFile("uploadPic");
		String guessedEcode = ParamUtil.getString(request, "guessedEcode");
		santaService.submitGame(getLoggedUser(request), guessedEcode, photoImage);
		redirect(request,response);
	}
}