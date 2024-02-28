package com.trantorinc.synergy.notice.core.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.trantorinc.synergy.notice.core.model.RecoveryReimbursement;
import com.trantorinc.synergy.notice.core.model.impl.RecoveryReimbursementImpl;
import com.trantorinc.synergy.notice.core.service.RecoveryReimbursementLocalServiceUtil;
import com.trantorinc.synergy.notice.core.service.persistence.RecoveryReimbursementFinder;
import org.osgi.service.component.annotations.Component;

import java.util.List;
@Component(
        service = RecoveryReimbursementFinder.class
)
public class RecoveryReimbursementFinderImpl extends RecoveryReimbursementPersistenceImpl implements RecoveryReimbursementFinder {

    @Override
    public List<RecoveryReimbursement> findRecoveryReimbursementByDepartmentFormId(long departmentFormId, int department)
    {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RecoveryReimbursementImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("departmentFormId").eq(departmentFormId));
        dynamicQuery.add(PropertyFactoryUtil.forName("department").eq(department));
        return RecoveryReimbursementLocalServiceUtil.dynamicQuery(dynamicQuery);
    }
    @Override
    public List<RecoveryReimbursement> findAllRecoveryFormByDepartment(long departmentFormId , int department)
    {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RecoveryReimbursementImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("departmentFormId").eq(departmentFormId));
        dynamicQuery.add(PropertyFactoryUtil.forName("department").eq(department));
        dynamicQuery.add(PropertyFactoryUtil.forName("recoveryType").eq(true));
        return RecoveryReimbursementLocalServiceUtil.dynamicQuery(dynamicQuery);
    }
    @Override
    public List<RecoveryReimbursement> findSelectedRecoveryFormByDepartment(long departmentFormId , int department)
    {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RecoveryReimbursementImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("departmentFormId").eq(departmentFormId));
        dynamicQuery.add(PropertyFactoryUtil.forName("department").eq(department));
        dynamicQuery.add(PropertyFactoryUtil.forName("recoveryType").eq(true));
        dynamicQuery.add(PropertyFactoryUtil.forName("recoveryStatus").eq(1));
        return RecoveryReimbursementLocalServiceUtil.dynamicQuery(dynamicQuery);
    }
}
