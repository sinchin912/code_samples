package com.trantorinc.synergy.performance.core.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.trantorinc.synergy.performance.core.model.Kpi;
import com.trantorinc.synergy.performance.core.model.impl.KpiImpl;
import com.trantorinc.synergy.performance.core.service.KpiLocalServiceUtil;
import com.trantorinc.synergy.performance.core.service.persistence.KpiFinder;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
        service = KpiFinder.class
)

public class KpiFinderImpl extends KpiPersistenceImpl implements KpiFinder {
    @Override
    public List<Kpi> findKpiByEcode(String ecode) {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(KpiImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("ecode").eq(ecode));
        return KpiLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    @Override
    public List<Kpi> findKpiByManagerEmail(String email) {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(KpiImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("managerEmail").eq(email));
        return KpiLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    @Override
    public List<Kpi> findKpiByReviewerEmail(String email) {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(KpiImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("reviewerEmail").eq(email));
        return KpiLocalServiceUtil.dynamicQuery(dynamicQuery);
    }
}
