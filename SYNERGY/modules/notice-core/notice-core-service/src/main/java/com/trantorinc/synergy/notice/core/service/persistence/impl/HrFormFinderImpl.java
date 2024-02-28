package com.trantorinc.synergy.notice.core.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.trantorinc.synergy.notice.core.model.HrForm;
import com.trantorinc.synergy.notice.core.model.impl.HrFormImpl;
import com.trantorinc.synergy.notice.core.service.HrFormLocalServiceUtil;
import com.trantorinc.synergy.notice.core.service.persistence.HrFormFinder;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
        service = HrFormFinder.class
)
public class HrFormFinderImpl extends HrFormPersistenceImpl implements HrFormFinder {
    @Override
    public List<HrForm> findHrFormByExitId(long exitId) {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HrFormImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("exitId").eq(exitId));
        return HrFormLocalServiceUtil.dynamicQuery(dynamicQuery);
    }
}