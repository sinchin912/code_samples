package com.trantorinc.synergy.custom.service;

import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.LifecycleEvent;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.trantorinc.synergy.lms.core.model.Employee;
import com.trantorinc.synergy.lms.core.service.EmployeeLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import javax.servlet.http.HttpServletRequest;

@Component(immediate = true, property = { "key=login.events.pre" }, service = LifecycleAction.class)
public class CustomPreLoginAction implements LifecycleAction {
    private static final Log log = LogFactoryUtil.getLog(CustomPreLoginAction.class);
    @Override
    public void processLifecycleEvent(LifecycleEvent lifecycleEvent) throws ActionException {
        HttpServletRequest request = lifecycleEvent.getRequest();
        try {
            String emailAddress=PortalUtil.getUser(request).getEmailAddress();
            Employee employee = EmployeeLocalServiceUtil.findByEmail(emailAddress);
            if(null != employee && employee.isStatus()){
                log.info(employee.getName()+" logged in");
                UserLocalServiceUtil.addGroupUser(20119, PortalUtil.getUser(request).getUserId());
            }else{
                log.info(emailAddress+" not active in LMS");
                throw new NullPointerException();
            }
        } catch (PortalException exception) {
            log.error(exception.getStackTrace()[0].getMethodName()+":" +exception.getStackTrace()[0].getLineNumber()+":"+ exception.getMessage());
        }
    }
}

