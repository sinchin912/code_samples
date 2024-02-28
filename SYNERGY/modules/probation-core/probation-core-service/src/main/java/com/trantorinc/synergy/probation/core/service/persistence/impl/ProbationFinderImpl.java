package com.trantorinc.synergy.probation.core.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.*;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.trantorinc.synergy.probation.core.model.Probation;
import com.trantorinc.synergy.probation.core.model.impl.ProbationImpl;
import com.trantorinc.synergy.probation.core.service.ProbationLocalServiceUtil;
import com.trantorinc.synergy.probation.core.service.persistence.ProbationFinder;
import org.osgi.service.component.annotations.Component;

import java.util.Date;
import java.util.List;

@Component(
        service = ProbationFinder.class
)
public class ProbationFinderImpl extends ProbationPersistenceImpl implements ProbationFinder {

    @Override
    public List<Probation> findByManager(String manager){
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProbationImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("manager").eq(manager));
        return ProbationLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    @Override
    public List<Probation> findByState(boolean completed){
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProbationImpl.class, PortalClassLoaderUtil.getClassLoader());
        if(completed){
            dynamicQuery.add(PropertyFactoryUtil.forName("state").ne(0));
            dynamicQuery.addOrder(OrderFactoryUtil.desc("updateDate"));
        } else {
            dynamicQuery.add(PropertyFactoryUtil.forName("state").eq(0));
            dynamicQuery.addOrder(OrderFactoryUtil.desc("createDate"));
        }
        return ProbationLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    @Override
    public List<Probation> findByStateAndDate(boolean completed, Date onDate){
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProbationImpl.class, PortalClassLoaderUtil.getClassLoader());
        if(completed){
            dynamicQuery.add(PropertyFactoryUtil.forName("state").ne(0));
            dynamicQuery.add(PropertyFactoryUtil.forName("updateDate").ge(onDate));
        } else {
            dynamicQuery.add(PropertyFactoryUtil.forName("state").eq(0));
            dynamicQuery.add(PropertyFactoryUtil.forName("createDate").eq(onDate));
        }
        return ProbationLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    @Override
    public List<Probation> findExtendedByDate(Date onDate){
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProbationImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("alertDate").eq(onDate));
        Criterion criterion1 = RestrictionsFactoryUtil.eq("state",3);
        Criterion criterion2 = RestrictionsFactoryUtil.eq("state",4);
        dynamicQuery.add(RestrictionsFactoryUtil.or(criterion1,criterion2));
        return ProbationLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

}