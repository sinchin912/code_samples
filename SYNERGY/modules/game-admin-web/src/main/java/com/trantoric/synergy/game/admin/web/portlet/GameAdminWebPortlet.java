package com.trantoric.synergy.game.admin.web.portlet;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.trantoric.synergy.game.admin.web.constants.GameAdminWebPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import com.trantoric.synergy.game.admin.web.service.GameAdminService;
import com.trantoric.synergy.game.admin.web.service.GameAdminServiceImpl;
import com.trantorinc.synergy.game.core.model.GameTimeline;
import com.trantorinc.synergy.game.core.model.Prize;
import com.trantorinc.synergy.game.core.service.GameTimelineLocalServiceUtil;
import com.trantorinc.synergy.game.core.service.PrizeLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.trantorinc.synergy.common.service.constant.AppConstantService.FORMATTER_YYYY_MM_DD;
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
				"javax.portlet.display-name=GameAdminWeb",
				"javax.portlet.init-param.template-path=/",
				"javax.portlet.init-param.view-template=/view.jsp",
				"javax.portlet.name=" + GameAdminWebPortletKeys.GAMEADMINWEB,
				"javax.portlet.init-param.add-process-action-success-action=false",
				"javax.portlet.resource-bundle=content.Language",
				"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
)
public class GameAdminWebPortlet extends MVCPortlet {

	private static final Log log = LogFactoryUtil.getLog(GameAdminWebPortlet.class.getName());
	private GameAdminService gameAdminService = null;
	@Override
	public void init() throws PortletException {
		gameAdminService = new GameAdminServiceImpl();
		super.init();
	}

	@Override
	public void destroy() {
		gameAdminService = null;
		super.destroy();
	}
	@Override
	public void doView(RenderRequest request, RenderResponse response) throws IOException, PortletException {
		int currentYear = getCurrentYear();
		List<Prize> prizes = PrizeLocalServiceUtil.findPrizesByYear(currentYear);
		List<GameTimeline> gameTimelines = GameTimelineLocalServiceUtil.getCalibratedTimeline();
		GameTimeline raffleTimeline = gameTimelines.stream().filter(g -> g.getName().equalsIgnoreCase("RAFFLE")).collect(Collectors.toList()).get(0);
		LocalDateTime now = getIstLocalDateTime();
		request.setAttribute("beforeLastDay",now.isBefore(convertDateToLocalDateTime(raffleTimeline.getActionDate())));
		request.setAttribute("raffleTimeline", gameAdminService.getRaffleTimeline(raffleTimeline));
		request.setAttribute("santaTimeline", gameAdminService.getSantaTimelines(gameTimelines.stream().filter(g -> g.getName().equalsIgnoreCase("SANTA")).collect(Collectors.toList()).get(0)));
		request.setAttribute("prizes", gameAdminService.getPrizes(prizes));
		request.setAttribute("raffleDrawPrizes", gameAdminService.getRaffleDrawPrizes(prizes));
		request.setAttribute("startDate", getFinancialStartDate(currentYear).format(FORMATTER_YYYY_MM_DD));
		request.setAttribute("endDate", getFinancialEndDate(currentYear).format(FORMATTER_YYYY_MM_DD));
		request.setAttribute("drawCompleted", prizes.stream().noneMatch(p -> 0 == p.getTicketId()));
		super.doView(request, response);
	}

	@ProcessAction(name = "saveRafflePrize")
	public void saveRafflePrize(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException, PortalException {
		gameAdminService.saveRafflePrize(actionRequest);
		redirect(actionRequest,actionResponse);
	}

	@ProcessAction(name = "saveRaffleTimeline")
	public void saveRaffleTimeline(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException {
		String openDate = ParamUtil.getString(actionRequest, "raffleOpenDate");
		String freezeDate = ParamUtil.getString(actionRequest, "raffleFreezeDate");
		String actionDate = ParamUtil.getString(actionRequest, "raffleActionDate");
		gameAdminService.saveRaffleTimeline(openDate, freezeDate, actionDate);
		redirect(actionRequest,actionResponse);
	}

	@ProcessAction(name = "saveSantaTimeline")
	public void saveSantaTimeline(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException {
		String openDate = ParamUtil.getString(actionRequest, "santaOpenDate");
		String freezeDate = ParamUtil.getString(actionRequest, "santaFreezeDate");
		String actionDate = ParamUtil.getString(actionRequest, "santaActionDate");
		String closeDate = ParamUtil.getString(actionRequest, "santaCloseDate");
		gameAdminService.saveSantaTimeline(openDate, freezeDate, actionDate,closeDate);
		redirect(actionRequest,actionResponse);
	}

	@ProcessAction(name="confirmWinner")
	public void confirmWinner(ActionRequest request, ActionResponse response) throws IOException {
		long prizeId = ParamUtil.getLong(request, "prizeId");
		long ticketId = ParamUtil.getLong(request, "ticketId"+prizeId);
		gameAdminService.confirmWinner(prizeId, ticketId,getCurrentYear());
		redirect(request,response);
	}

	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws IOException {
		Object dataToWrite = null;
		if(resourceRequest.getResourceID().equals("getPrizePic")) {
			String photoId = ParamUtil.getString(resourceRequest, "prizePicId");
			dataToWrite = gameAdminService.getPrizePic(photoId);
		} else if(resourceRequest.getResourceID().equals("updatePrizePic")){
			long prizeId = ParamUtil.getLong(resourceRequest, "prizeId");
			UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
			File prizePic = uploadPortletRequest.getFile("prizePic");
			dataToWrite = gameAdminService.updatePrizePic(prizeId, prizePic);
		} else if (resourceRequest.getResourceID().equals("getWinner")) {
			dataToWrite = gameAdminService.getWinner();
		} else if (resourceRequest.getResourceID().equals("exportRaffle")) {
			gameAdminService.exportRaffle(resourceResponse);
		}else if (resourceRequest.getResourceID().equals("exportSanta")) {
			gameAdminService.exportSanta(resourceResponse);
		}
		try(PrintWriter writer = resourceResponse.getWriter()) {
			writer.print("{\"data\": " + dataToWrite+ "}");
			writer.flush();
		} catch (IOException exception) {
			log.error(exception.getStackTrace()[0].getMethodName()+":" +exception.getStackTrace()[0].getLineNumber()+":"+ exception.getMessage());
		}
	}

}