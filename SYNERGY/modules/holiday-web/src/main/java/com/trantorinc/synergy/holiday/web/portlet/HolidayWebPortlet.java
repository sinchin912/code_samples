package com.trantorinc.synergy.holiday.web.portlet;

import com.trantorinc.synergy.holiday.web.constants.HolidayWebPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.trantorinc.synergy.holiday.web.service.HolidayWebService;
import com.trantorinc.synergy.holiday.web.service.HolidayWebServiceImpl;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;

/**
 * @author sachin.goyal
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=HolidayWeb",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + HolidayWebPortletKeys.HOLIDAYWEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class HolidayWebPortlet extends MVCPortlet {

	private HolidayWebService holidayWebService =null;

	@Override
	public void init() throws PortletException {
		holidayWebService = new HolidayWebServiceImpl();
		super.init();
	}

	@Override
	public void destroy() {
		holidayWebService = null;
		super.destroy();
	}

	@Override
	public void doView(RenderRequest request, RenderResponse response) throws IOException, PortletException {
		request.setAttribute("holidays", holidayWebService.getHolidays());
		super.doView(request,response);
	}
}