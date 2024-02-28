package com.trantorinc.synergy.admin.panel.application.list;

import com.trantorinc.synergy.admin.panel.constants.AdminPanelPanelCategoryKeys;
import com.trantorinc.synergy.admin.panel.constants.AdminPanelPortletKeys;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.portal.kernel.model.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author sachin.goyal
 */
@Component(
	immediate = true,
	property = {
		"panel.app.order:Integer=100",
		"panel.category.key=" + AdminPanelPanelCategoryKeys.CONTROL_PANEL_CATEGORY
	},
	service = PanelApp.class
)
public class AdminPanelPanelApp extends BasePanelApp {

	@Override
	public String getPortletId() {
		return AdminPanelPortletKeys.ADMINPANEL;
	}

	@Override
	@Reference(
		target = "(javax.portlet.name=" + AdminPanelPortletKeys.ADMINPANEL + ")",
		unbind = "-"
	)
	public void setPortlet(Portlet portlet) {
		super.setPortlet(portlet);
	}

}