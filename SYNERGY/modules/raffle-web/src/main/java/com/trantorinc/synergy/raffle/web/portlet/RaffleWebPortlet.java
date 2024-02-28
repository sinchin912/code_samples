package com.trantorinc.synergy.raffle.web.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.trantorinc.synergy.game.core.model.GameTimeline;
import com.trantorinc.synergy.game.core.service.GameTimelineLocalServiceUtil;
import com.trantorinc.synergy.lms.core.model.Employee;
import com.trantorinc.synergy.lms.core.service.EmployeeLocalServiceUtil;
import com.trantorinc.synergy.raffle.web.constants.RaffleWebPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import com.trantorinc.synergy.raffle.web.service.RaffleService;
import com.trantorinc.synergy.raffle.web.service.RaffleServiceImpl;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import static com.trantorinc.synergy.common.service.util.UtilService.*;
import static com.trantorinc.synergy.raffle.web.constants.RaffleWebPortletKeys.TICKET_COST;

/**
 * @author sachin.goyal
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=RaffleWeb",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + RaffleWebPortletKeys.RAFFLEWEB,
		"javax.portlet.init-param.add-process-action-success-action=false",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class RaffleWebPortlet extends MVCPortlet {

	private static final Log log = LogFactoryUtil.getLog(RaffleWebPortlet.class.getName());

	private RaffleService raffleService = null;
	@Override
	public void init() throws PortletException {
		raffleService = new RaffleServiceImpl();
		super.init();
	}

	@Override
	public void destroy() {
		raffleService = null;
		super.destroy();
	}

	@Override
	public void doView(RenderRequest request, RenderResponse response) throws IOException, PortletException {
		LocalDateTime now = getIstLocalDateTime();
		int currentYear = getCurrentYear();
		GameTimeline raffleTimeline = GameTimelineLocalServiceUtil.getCalibratedTimeline().stream().filter(g -> g.getName().equalsIgnoreCase("RAFFLE")).collect(Collectors.toList()).get(0);

		ThemeDisplay td = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		User loggedUser = td.getUser();
		Employee employee = EmployeeLocalServiceUtil.findByEmail(loggedUser.getEmailAddress());

		if(now.isAfter(convertDateToLocalDateTime(raffleTimeline.getOpenDate()))){
			if(now.isBefore(convertDateToLocalDateTime(raffleTimeline.getFreezeDate()))){
				request.setAttribute("timeline",raffleService.getTimeline(raffleTimeline));
				request.setAttribute("buyOpen","on");
			} else {
				request.setAttribute("buyOpen","off");
			}
			request.setAttribute("prizes",raffleService.getPrizes());
			request.setAttribute("tickets", raffleService.getTickets(employee.getEcode()));
			request.setAttribute("mode","on");
		} else {
			request.setAttribute("mode","off");
		}
		request.setAttribute("ticketCost",TICKET_COST);
		request.setAttribute("currentYear",currentYear);
		super.doView(request, response);
	}


	@ProcessAction(name="applyTickets")
	public void applyTickets(ActionRequest request, ActionResponse response) throws IOException {
		GameTimeline raffleTimeline = GameTimelineLocalServiceUtil.getCalibratedTimeline().stream().filter(g -> g.getName().equalsIgnoreCase("RAFFLE")).collect(Collectors.toList()).get(0);
		raffleService.saveTickets(getLoggedUser(request), request, raffleTimeline);
		redirect(request,response);
	}

	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) {
		Object dataToWrite = null;
		if(resourceRequest.getResourceID().equals("getPrizePic")) {
			String photoId = ParamUtil.getString(resourceRequest, "prizePicId");
			dataToWrite = raffleService.getPrizePic(photoId);
		} else if (resourceRequest.getResourceID().equals("getTicketAvailability")) {
			int ticketId = ParamUtil.getInteger(resourceRequest, "ticketId");
			dataToWrite = raffleService.getTicketAvailability(ticketId);
		}
		try(PrintWriter writer = resourceResponse.getWriter()) {
			writer.print("{\"data\": \"" + dataToWrite+ "\"}");
			writer.flush();
		} catch (IOException exception) {
			log.error(exception.getStackTrace()[0].getMethodName()+":" +exception.getStackTrace()[0].getLineNumber()+":"+ exception.getMessage());
		}
	}



}