package com.trantorinc.synergy.email.core.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.trantorinc.synergy.email.core.model.EmailAttachment;
import com.trantorinc.synergy.email.core.model.impl.EmailAttachmentImpl;
import com.trantorinc.synergy.email.core.service.EmailAttachmentLocalServiceUtil;
import com.trantorinc.synergy.email.core.service.persistence.EmailAttachmentFinder;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
        service = EmailAttachmentFinder.class
)
public class EmailAttachmentFinderImpl extends EmailAttachmentPersistenceImpl implements EmailAttachmentFinder {


    public List<EmailAttachment> findByEmailId(long emailId)
	{
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(EmailAttachmentImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("emailId").eq(emailId));
        return EmailAttachmentLocalServiceUtil.dynamicQuery(dynamicQuery);
		
	}

}
