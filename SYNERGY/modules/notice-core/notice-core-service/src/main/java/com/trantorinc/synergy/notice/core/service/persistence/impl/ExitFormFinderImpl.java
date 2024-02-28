package com.trantorinc.synergy.notice.core.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.*;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.trantorinc.synergy.notice.core.model.ExitForm;
import com.trantorinc.synergy.notice.core.model.impl.ExitFormImpl;
import com.trantorinc.synergy.notice.core.service.ExitFormLocalServiceUtil;
import com.trantorinc.synergy.notice.core.service.persistence.ExitFormFinder;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
        service = ExitFormFinder.class
)
public class ExitFormFinderImpl extends ExitFormPersistenceImpl implements ExitFormFinder {
    @Override
    public List<ExitForm> findExitFormByResignationId(long resignationId){
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ExitFormImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("resignationId").eq(resignationId));
        return ExitFormLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    @Override
    public List<Object[]> findAllExitFormEntries() {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ExitFormImpl.class, PortalClassLoaderUtil.getClassLoader());
        ProjectionList projectionList = ProjectionFactoryUtil.projectionList();
        projectionList.add(ProjectionFactoryUtil.property("id"));
        projectionList.add(ProjectionFactoryUtil.property("resignationId"));
        projectionList.add(ProjectionFactoryUtil.property("managerFormStatus"));
        dynamicQuery.setProjection(projectionList);
        return ExitFormLocalServiceUtil.dynamicQuery(dynamicQuery);
    }
}
