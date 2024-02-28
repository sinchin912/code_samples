package com.trantorinc.synergy.notice.core.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.trantorinc.synergy.notice.core.model.FinanceForm;
import com.trantorinc.synergy.notice.core.model.impl.FinanceFormImpl;
import com.trantorinc.synergy.notice.core.service.FinanceFormLocalServiceUtil;
import com.trantorinc.synergy.notice.core.service.persistence.FinanceFormFinder;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
        service = FinanceFormFinder.class
)
public class FinanceFormFinderImpl extends FinanceFormPersistenceImpl implements FinanceFormFinder {
    @Override
    public List<FinanceForm> findFinanceFormByExitId(long exitId) {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(FinanceFormImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("exitId").eq(exitId));
        return FinanceFormLocalServiceUtil.dynamicQuery(dynamicQuery);
    }
}