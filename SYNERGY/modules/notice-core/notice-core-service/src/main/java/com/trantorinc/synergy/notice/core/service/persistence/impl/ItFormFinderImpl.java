package com.trantorinc.synergy.notice.core.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.trantorinc.synergy.notice.core.model.ItForm;
import com.trantorinc.synergy.notice.core.model.impl.ItFormImpl;
import com.trantorinc.synergy.notice.core.service.ItFormLocalServiceUtil;
import com.trantorinc.synergy.notice.core.service.persistence.ItFormFinder;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
        service = ItFormFinder.class
)
public class ItFormFinderImpl   extends ItFormPersistenceImpl implements ItFormFinder {
    @Override
    public List<ItForm> findItFormByExitId(long exitId){
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItFormImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("exitId").eq(exitId));
        return ItFormLocalServiceUtil.dynamicQuery(dynamicQuery);
    }
}
