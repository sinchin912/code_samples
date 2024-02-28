/**
 * Copyright 2000-present Liferay, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.trantorinc.synergy.custom.service;

import com.liferay.announcements.kernel.model.AnnouncementsEntry;
import com.liferay.announcements.kernel.service.AnnouncementsEntryLocalServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.trantorinc.synergy.custom.service.constants.SynergyServicePortletKeys;
import com.trantorinc.synergy.email.core.model.Email;
import com.trantorinc.synergy.email.core.service.EmailLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import static com.trantorinc.synergy.common.service.constant.AppConstantService.MODULE_GENERIC;
import static com.trantorinc.synergy.common.service.util.UtilService.getIstDate;
import static com.trantorinc.synergy.common.service.util.UtilService.getPortalUrl;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=com_liferay_announcements_web_portlet_AnnouncementsPortlet",
		"mvc.command.name=announcements_send_email"
	},
	service = MVCResourceCommand.class
)
public class AnnouncementMvcResourceCommand implements MVCResourceCommand {

	private static final Log log = LogFactoryUtil.getLog(AnnouncementMvcResourceCommand.class);

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) {
		String toAddress = ParamUtil.getString(resourceRequest, "toAddress");
		toAddress = toAddress.replace(SynergyServicePortletKeys.ANNOUNCEMENT_EMAIL_DELIMITER, SynergyServicePortletKeys.EMAIL_DELIMITER);
		try {
			AnnouncementsEntry entry = AnnouncementsEntryLocalServiceUtil.getAnnouncementsEntry(ParamUtil.getLong(resourceRequest, "entryId"));
			String body = entry.getContent();
			body = body.replace("src=\"", "src=\""+getPortalUrl() );
			String title = entry.getTitle();
			Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
			email.setBccAddress(toAddress);
			email.setSubject(SynergyServicePortletKeys.ANNOUNCEMENT_SUBJECT_PREFIX + title);
			email.setBody(body);
			email.setModule(MODULE_GENERIC);
			email.setScheduled(false);
			email.setSent(false);
			email.setCreatedDate(getIstDate());
			EmailLocalServiceUtil.addEmail(email);
		} catch (PortalException exception) {
			log.error(exception.getStackTrace()[0].getMethodName()+":" +exception.getStackTrace()[0].getLineNumber()+":"+ exception.getMessage());
		}
		return false;
	}
}