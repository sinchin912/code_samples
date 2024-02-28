package com.trantorinc.synergy.performance.core.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.trantorinc.synergy.performance.core.model.KpiLine;
import com.trantorinc.synergy.performance.core.model.impl.KpiLineImpl;
import com.trantorinc.synergy.performance.core.service.KpiLineLocalServiceUtil;
import com.trantorinc.synergy.performance.core.service.persistence.KpiLineFinder;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
        service = KpiLineFinder.class
)

public class KpiLineFinderImpl extends KpiPersistenceImpl implements KpiLineFinder {
    @Override
    public List<KpiLine> findKpiLineByKpiId(long kpiId) {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(KpiLineImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("kpiId").eq(kpiId));
        return KpiLineLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

}
