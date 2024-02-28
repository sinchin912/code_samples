package com.trantorinc.synergy.lms.core.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.trantorinc.synergy.lms.core.model.Scheduler;
import com.trantorinc.synergy.lms.core.model.impl.SchedulerImpl;
import com.trantorinc.synergy.lms.core.service.SchedulerLocalServiceUtil;
import com.trantorinc.synergy.lms.core.service.persistence.SchedulerFinder;
import org.osgi.service.component.annotations.Component;

import java.util.Date;
import java.util.List;


@Component(
        service = SchedulerFinder.class
)
public class SchedulerFinderImpl extends SchedulerPersistenceImpl implements SchedulerFinder {

    public List<Scheduler> findByNameAndDate(String name, Date onDate)
    {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(SchedulerImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("name").eq(name));
        dynamicQuery.add(PropertyFactoryUtil.forName("onDate").eq(onDate));
        return SchedulerLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    public List<Scheduler> findByDate(Date onDate)
    {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(SchedulerImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("onDate").eq(onDate));
        return SchedulerLocalServiceUtil.dynamicQuery(dynamicQuery);
    }
}

