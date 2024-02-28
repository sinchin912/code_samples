package com.trantorinc.synergy.feedback.web.portlet;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.trantorinc.synergy.email.core.model.Email;
import com.trantorinc.synergy.email.core.service.EmailLocalServiceUtil;
import com.trantorinc.synergy.feedback.web.constants.FeedbackWebPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import com.trantorinc.synergy.feedback.web.dto.FeedbackDto;
import com.trantorinc.synergy.lms.core.model.Employee;
import com.trantorinc.synergy.lms.core.service.EmployeeLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;
import java.text.MessageFormat;

import static com.trantorinc.synergy.common.service.constant.AppConstantService.*;
import static com.trantorinc.synergy.common.service.util.UtilService.*;
import static com.trantorinc.synergy.feedback.web.constants.FeedbackWebPortletKeys.BODY_FEEDBACK_RECEIVED;
import static com.trantorinc.synergy.feedback.web.constants.FeedbackWebPortletKeys.SUBJECT_FEEDBACK_RECEIVED;

/**
 * @author sachin.goyal
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=FeedbackWeb",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + FeedbackWebPortletKeys.FEEDBACKWEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class FeedbackWebPortlet extends MVCPortlet {

	private static final Log log = LogFactoryUtil.getLog(FeedbackWebPortlet.class.getName());

	@Override
	public void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		Employee employeeDetails = EmployeeLocalServiceUtil.findByEmail(getLoggedUser(request));
		FeedbackDto feedbackDto = new FeedbackDto();
		feedbackDto.setEcode(employeeDetails.getEcode());
		feedbackDto.setName(employeeDetails.getName());
		feedbackDto.setEmail(employeeDetails.getEmail());
		request.setAttribute("employee", feedbackDto);
		super.doView(request, response);
	}

	@ProcessAction(name = "submitFeedback")
	public void submitFeedback(ActionRequest request, ActionResponse response) throws IOException {
		String ecode = ParamUtil.getString(request, "ecode");
		String name = ParamUtil.getString(request, "name");
		String rating = ParamUtil.getString(request, "rating");
		String suggestion = ParamUtil.getString(request, "suggestion");
		Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
		email.setToAddress(DL_INTRANET);
		email.setSubject(SUBJECT_FEEDBACK_RECEIVED);
		email.setBody(MessageFormat.format(BODY_FEEDBACK_RECEIVED, name,ecode,rating, suggestion));
		email.setModule(MODULE_GENERIC);
		email.setScheduled(false);
		email.setSent(false);
		email.setCreatedDate(getIstDate());
		EmailLocalServiceUtil.addEmail(email);
		redirect(request, response);
	}
}