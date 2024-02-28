package com.test.video.web.portlet;

import com.test.video.web.constants.VideoWebPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import com.trantorinc.synergy.common.service.drive.DriveService;
import com.trantorinc.synergy.common.service.util.UtilService;
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
		"javax.portlet.display-name=VideoWeb",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + VideoWebPortletKeys.VIDEOWEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class VideoWebPortlet extends MVCPortlet {

	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws IOException, PortletException {
		Object dataToWrite = null;
		if(resourceRequest.getResourceID().equals("getVideoBase64")) {
			dataToWrite = UtilService.getBase64File(DriveService.getFile("1fp-eY3GBRp7AMeSOHdOu2HAcl3JBnr87","test",".mp4"));
		}
		try (PrintWriter writer = resourceResponse.getWriter()) {
			writer.print("{\"data\": \"" + dataToWrite+ "\"}");
			writer.flush();
		} catch (IOException exception) {
		}
	}
}