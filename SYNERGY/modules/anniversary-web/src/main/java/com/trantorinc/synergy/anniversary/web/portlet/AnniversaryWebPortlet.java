package com.trantorinc.synergy.anniversary.web.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.trantorinc.synergy.anniversary.web.constants.AnniversaryWebPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import com.trantorinc.synergy.anniversary.web.service.AnniversaryWebService;
import com.trantorinc.synergy.anniversary.web.service.AnniversaryWebServiceImpl;
import org.osgi.service.component.annotations.Component;

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
		"javax.portlet.display-name=AnniversaryWeb",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + AnniversaryWebPortletKeys.ANNIVERSARYWEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class AnniversaryWebPortlet extends MVCPortlet {
	private static final Log log = LogFactoryUtil.getLog(AnniversaryWebPortlet.class.getName());
	private AnniversaryWebService anniversaryWebService =null;

	@Override
	public void init() throws PortletException {
		anniversaryWebService = new AnniversaryWebServiceImpl();
		super.init();
	}

	@Override
	public void destroy() {
		anniversaryWebService = null;
		super.destroy();
	}

	@Override
	public void doView(RenderRequest request, RenderResponse response) throws IOException, PortletException {
		request.setAttribute("birthdays", anniversaryWebService.getBirthDays());
		request.setAttribute("anniversaries", anniversaryWebService.getAnniversaries());
		super.doView(request,response);
	}

	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) {
		if(resourceRequest.getResourceID().equals("getPhoto")) {
			String photoId = ParamUtil.getString(resourceRequest, "photoId");
			try(PrintWriter writer = resourceResponse.getWriter()) {
				String dataToWrite = anniversaryWebService.getPhoto(photoId);
				writer.print("{\"data\": \"" + dataToWrite+ "\"}");
				writer.flush();
			} catch (IOException exception) {
				log.error(exception.getStackTrace()[0].getMethodName()+":" +exception.getStackTrace()[0].getLineNumber()+":"+ exception.getMessage());
			}
		}
	}
}