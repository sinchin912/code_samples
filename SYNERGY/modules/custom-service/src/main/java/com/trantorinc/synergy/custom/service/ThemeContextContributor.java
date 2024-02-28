package com.trantorinc.synergy.custom.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.template.TemplateContextContributor;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.trantorinc.synergy.common.service.constant.AppConstantService.*;

@Component(
		immediate = true,
		property = {"type=" + TemplateContextContributor.TYPE_GLOBAL},
		service = TemplateContextContributor.class
)
public class ThemeContextContributor implements TemplateContextContributor {

	private static final Log log = LogFactoryUtil.getLog(ThemeContextContributor.class);
	/**
	 * To set site fields in themeDisplay
	 * @param contextObjects
	 * @param request
	 */

	@Override
	public void prepare(Map<String, Object> contextObjects, HttpServletRequest request) {
		try {
			List<String> applicationRoles = Arrays.asList(
					ROLE_HR,
					ROLE_ADMIN,
					ROLE_COORDINATOR,
					ROLE_FINANCE,
					ROLE_IT,
					ROLE_MANAGER,
					ROLE_LEADER,
					ROLE_RECRUITER,
					ROLE_TEAMLEAD,
					ROLE_PERFORMANCE,
					ROLE_DOCUMENT,
					ROLE_ANNOUNCEMENT);
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			if(themeDisplay.isSignedIn()) {
				User user = PortalUtil.getUser(request);
				List<String> userRoles = user.getRoles().stream().map(Role::getName).collect(Collectors.toList());
				for(String applicationRole : applicationRoles){
						contextObjects.put("is_"+applicationRole, String.valueOf(userRoles.contains(applicationRole)));
				}
			}
		} catch (PortalException exception) {
			log.error(exception.getStackTrace()[0].getMethodName()+":" +exception.getStackTrace()[0].getLineNumber()+":"+ exception.getMessage());
		}
	}
}
