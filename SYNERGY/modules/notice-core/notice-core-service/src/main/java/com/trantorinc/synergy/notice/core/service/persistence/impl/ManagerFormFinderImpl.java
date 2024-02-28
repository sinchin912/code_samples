package com.trantorinc.synergy.notice.core.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.trantorinc.synergy.notice.core.model.ManagerForm;
import com.trantorinc.synergy.notice.core.model.impl.ManagerFormImpl;
import com.trantorinc.synergy.notice.core.service.ManagerFormLocalServiceUtil;
import com.trantorinc.synergy.notice.core.service.persistence.ManagerFormFinder;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
        service = ManagerFormFinder.class
)
public class ManagerFormFinderImpl extends ManagerFormPersistenceImpl implements ManagerFormFinder {
    @Override
    public List<ManagerForm> findManagerFormByExitId(long exitId){
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ManagerFormImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("exitId").eq(exitId));
        return ManagerFormLocalServiceUtil.dynamicQuery(dynamicQuery);
    }
}
