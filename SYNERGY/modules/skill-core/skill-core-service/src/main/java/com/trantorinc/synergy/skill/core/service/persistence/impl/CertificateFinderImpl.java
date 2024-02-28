package com.trantorinc.synergy.skill.core.service.persistence.impl;


import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.*;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.trantorinc.synergy.skill.core.model.Certificate;
import com.trantorinc.synergy.skill.core.model.impl.CertificateImpl;
import com.trantorinc.synergy.skill.core.model.impl.SkillImpl;
import com.trantorinc.synergy.skill.core.service.CertificateLocalServiceUtil;
import com.trantorinc.synergy.skill.core.service.persistence.CertificateFinder;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
        service = CertificateFinder.class
)

public class CertificateFinderImpl extends CertificatePersistenceImpl implements CertificateFinder {

    @Override
    public List<Certificate> findCertificatesByEcode(String ecode) {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CertificateImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("ecode").eq(ecode));
        return CertificateLocalServiceUtil.dynamicQuery(dynamicQuery);
    }
}

