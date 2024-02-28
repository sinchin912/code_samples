package com.trantorinc.synergy.notice.core.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.trantorinc.synergy.notice.core.model.AdminForm;
import com.trantorinc.synergy.notice.core.model.impl.AdminFormImpl;
import com.trantorinc.synergy.notice.core.service.AdminFormLocalServiceUtil;
import com.trantorinc.synergy.notice.core.service.persistence.AdminFormFinder;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
        service = AdminFormFinder.class
)
public class AdminFormFinderImpl extends AdminFormPersistenceImpl implements AdminFormFinder {
    public List<AdminForm> findAdminFormByExitId(long exitId){
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AdminFormImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("exitId").eq(exitId));
        return AdminFormLocalServiceUtil.dynamicQuery(dynamicQuery);
    }
}
