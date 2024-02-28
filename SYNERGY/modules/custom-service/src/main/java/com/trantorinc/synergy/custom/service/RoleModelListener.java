package com.trantorinc.synergy.custom.service;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.Role;
import com.trantorinc.synergy.custom.service.constants.SynergyServicePortletKeys;
import com.trantorinc.synergy.email.core.model.Email;
import com.trantorinc.synergy.email.core.service.EmailLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import java.text.MessageFormat;

import static com.trantorinc.synergy.common.service.constant.AppConstantService.*;
import static com.trantorinc.synergy.common.service.util.UtilService.getIstDate;


@Component(
		immediate = true,
		service = com.liferay.portal.kernel.model.ModelListener.class
)
public class RoleModelListener extends BaseModelListener<Role> {

	/**
	 * To send email if there is any update of existing role from Liferay Admin
	 * @param originalModel : existing role
	 * @param model : role with updated values
	 * @throws ModelListenerException : exception from super class
	 */
	@Override
	public void onBeforeUpdate(Role originalModel, Role model) throws ModelListenerException {
		Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
		email.setToAddress(DL_INTRANET);
		email.setSubject(MessageFormat.format(SynergyServicePortletKeys.SUBJECT_UPDATING_ROLE,ENVIRONMENT));
		email.setBody(MessageFormat.format(SynergyServicePortletKeys.BODY_UPDATING_ROLE, model.getTitleCurrentValue(),model.getTitleCurrentValue(),model.getDescriptionCurrentValue(), model.getName()));
		email.setModule(MODULE_GENERIC);
		email.setScheduled(false);
		email.setSent(false);
		email.setCreatedDate(getIstDate());
		EmailLocalServiceUtil.addEmail(email);
		super.onBeforeUpdate(originalModel, model);
	}

}
