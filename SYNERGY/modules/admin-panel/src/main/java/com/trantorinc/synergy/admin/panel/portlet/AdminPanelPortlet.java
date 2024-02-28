package com.trantorinc.synergy.admin.panel.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.trantorinc.synergy.admin.panel.constants.AdminPanelPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import com.trantorinc.synergy.admin.panel.dto.DriveDto;
import com.trantorinc.synergy.admin.panel.dto.EmailDto;
import com.trantorinc.synergy.admin.panel.dto.SchedulerDto;
import com.trantorinc.synergy.admin.panel.dto.UserDto;
import com.trantorinc.synergy.admin.panel.service.AdminPortletService;
import com.trantorinc.synergy.admin.panel.service.AdminPortletServiceImpl;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static com.trantorinc.synergy.common.service.constant.AppConstantService.*;
import static com.trantorinc.synergy.common.service.util.UtilService.*;

/**
 * @author sachin.goyal
 */
@Component(
		immediate = true,
		property = {
				"com.liferay.portlet.add-default-resource=true",
				"com.liferay.portlet.display-category=category.hidden",
				"com.liferay.portlet.header-portlet-css=/css/main.css",
				"com.liferay.portlet.header-portlet-js=/js/adminPanel.js",
				"com.liferay.portlet.layout-cacheable=true",
				"com.liferay.portlet.private-request-attributes=false",
				"com.liferay.portlet.private-session-attributes=false",
				"com.liferay.portlet.render-weight=50",
				"com.liferay.portlet.use-default-template=true",
				"javax.portlet.display-name=AdminPanel",
				"javax.portlet.expiration-cache=0",
				"javax.portlet.init-param.template-path=/",
				"javax.portlet.init-param.view-template=/view.jsp",
				"javax.portlet.name=" + AdminPanelPortletKeys.ADMINPANEL,
				"javax.portlet.resource-bundle=content.Language",
				"javax.portlet.security-role-ref=power-user,user",

		},
		service = Portlet.class
)
public class AdminPanelPortlet extends MVCPortlet {

	private AdminPortletService adminPortletService =null;
	private static final Log log = LogFactoryUtil.getLog(AdminPanelPortlet.class.getName());

	@Override
	public void init() throws PortletException {
		adminPortletService = new AdminPortletServiceImpl();
		super.init();
	}

	@Override
	public void destroy() {
		adminPortletService = null;
		super.destroy();
	}

	@Override
	public void doView(RenderRequest request, RenderResponse response) throws IOException, PortletException {
		LocalDateTime now = getIstLocalDateTime();
		request.setAttribute("today", now.format(FORMATTER_YYYY_MM_DD));
		request.setAttribute("emailStats", adminPortletService.getEmailStats(now.toLocalDate()));
		List<DriveDto> drives = adminPortletService.getDrives();
		request.setAttribute("drives",  drives);
		request.setAttribute("allDrive", drives.size() == drives.stream().filter(d -> null != d.getId()).count());
		request.setAttribute("hasDrive", drives.stream().anyMatch(d -> null != d.getId()));
		super.doView(request,response);
	}

	@ProcessAction(name = "createFolder")
	public void createFolder(ActionRequest actionRequest, ActionResponse actionResponse) {
		adminPortletService.createDrive(ParamUtil.getString(actionRequest, "driveName"));
	}

	@ProcessAction(name = "uploadFile")
	public void uploadFile(ActionRequest actionRequest, ActionResponse actionResponse) {
		adminPortletService.uploadFile(actionRequest);
	}

	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) {
		Object dataToWrite = null;
		if(resourceRequest.getResourceID().equals("getScheduler")) {
			String forDate = ParamUtil.getString(resourceRequest, "schedulerDate");
			LocalDate searchDate = LocalDate.parse(forDate);
			List<SchedulerDto> schedulerDtos = adminPortletService.getSchedulersOnDate(searchDate);
			dataToWrite =  convertToJson(schedulerDtos);
		} else if(resourceRequest.getResourceID().equals("getEmail")) {
			String forDate = ParamUtil.getString(resourceRequest, "emailDate");
			LocalDate searchDate = LocalDate.parse(forDate);
			List<EmailDto> emailDtos = adminPortletService.getEmailsOnDate(searchDate);
			dataToWrite =  convertToJson(emailDtos);
		} else if(resourceRequest.getResourceID().equals("getUser")) {
			String forDate = ParamUtil.getString(resourceRequest, "userDate");
			LocalDate searchDate = LocalDate.parse(forDate);
			List<UserDto> userDtos = adminPortletService.getUsersOnDate(searchDate);
			dataToWrite =  convertToJson(userDtos);
		}else if(resourceRequest.getResourceID().equals("executeScheduler")) {
			String taskName = ParamUtil.getString(resourceRequest, "taskName");
			Map<String, String> executeStatus = adminPortletService.executeScheduler(taskName);
			dataToWrite =  convertToJson(executeStatus);
		}
		try(PrintWriter writer = resourceResponse.getWriter()) {
			writer.print("{\"data\": " + dataToWrite+ "}");
			writer.flush();
		} catch (IOException exception) {
			log.error(exception.getStackTrace()[0].getMethodName()+":" +exception.getStackTrace()[0].getLineNumber()+":"+ exception.getMessage());
		}
	}

}