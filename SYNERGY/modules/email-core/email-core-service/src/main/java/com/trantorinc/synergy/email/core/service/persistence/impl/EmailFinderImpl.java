package com.trantorinc.synergy.email.core.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.*;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.trantorinc.synergy.email.core.model.Email;
import com.trantorinc.synergy.email.core.model.impl.EmailImpl;
import com.trantorinc.synergy.email.core.service.EmailLocalServiceUtil;
import com.trantorinc.synergy.email.core.service.persistence.EmailFinder;
import org.osgi.service.component.annotations.Component;

import java.util.Date;
import java.util.List;

@Component(
        service = EmailFinder.class
)
public class EmailFinderImpl extends EmailPersistenceImpl implements EmailFinder {


    public List<Email> findTopEmailBySent(boolean isSent)
	{
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(EmailImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("sent").eq(isSent));
        return EmailLocalServiceUtil.dynamicQuery(dynamicQuery,0,1);
		
	}

    public List<Email> findEmailByDate(Date fromDate, Date toDate)
    {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(EmailImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("createdDate").ge(fromDate));
        dynamicQuery.add(PropertyFactoryUtil.forName("createdDate").le(toDate));
        return EmailLocalServiceUtil.dynamicQuery(dynamicQuery);

    }

}
