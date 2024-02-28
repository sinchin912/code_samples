package com.trantorinc.synergy.notice.core.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.*;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.trantorinc.synergy.notice.core.model.Resignation;
import com.trantorinc.synergy.notice.core.model.impl.ResignationImpl;
import com.trantorinc.synergy.notice.core.service.ResignationLocalServiceUtil;
import com.trantorinc.synergy.notice.core.service.persistence.ResignationFinder;
import org.osgi.service.component.annotations.Component;

import java.util.Date;
import java.util.List;

@Component(
        service = ResignationFinder.class
)
public class ResignationFinderImpl extends ResignationPersistenceImpl implements ResignationFinder {
    @Override
    public List<Resignation> findResignationByEcode(String ecode)
    {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ResignationImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("ecode").eq(ecode));
        dynamicQuery.addOrder(OrderFactoryUtil.asc("creationDate"));
        return ResignationLocalServiceUtil.dynamicQuery(dynamicQuery);
    }
    public List<Resignation> findEntryByManagerEmail(String email)
    {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ResignationImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("managerEmail").eq(email));
        return ResignationLocalServiceUtil.dynamicQuery(dynamicQuery);
    }
    @Override
    public List<Resignation> findResignationsByLastWorkingDate(Date lastWorkingDay) {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ResignationImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("lastWorkingDate").eq(lastWorkingDay));
        Criterion criterion;
        Criterion criterion1;
        Criterion criterion2;
        criterion=RestrictionsFactoryUtil.eq("status",1);
        criterion=RestrictionsFactoryUtil.or(criterion,RestrictionsFactoryUtil.eq("status",2));
        criterion1=RestrictionsFactoryUtil.ne("noticePeriod","0");
        criterion2=RestrictionsFactoryUtil.and(criterion1,RestrictionsFactoryUtil.eq("status",5));
        criterion=RestrictionsFactoryUtil.or(criterion,criterion2);
        dynamicQuery.add(criterion);
        return ResignationLocalServiceUtil.dynamicQuery(dynamicQuery);
    }
    @Override
    public List<Resignation> findActiveResignationEntries(Date todaysDate) {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ResignationImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("status").ne(6));
        Criterion criterion;
        Criterion criterion1;
        Criterion criterion2;
        criterion=RestrictionsFactoryUtil.ge("lastWorkingDate",todaysDate);
        criterion1=RestrictionsFactoryUtil.eq("status",0);
        criterion2=RestrictionsFactoryUtil.eq("status",1);
        criterion1=RestrictionsFactoryUtil.or(criterion1,criterion2);
        criterion=RestrictionsFactoryUtil.or(criterion,criterion1);
        dynamicQuery.add(criterion);
        return ResignationLocalServiceUtil.dynamicQuery(dynamicQuery);
    }
    @Override
    public List<Resignation> findYearlyEntriesByLWD(Date startDate , Date endDate) {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ResignationImpl.class, PortalClassLoaderUtil.getClassLoader());
        Criterion criterion;
        Criterion criterion1;
        criterion=RestrictionsFactoryUtil.le("lastWorkingDate",endDate);
        criterion1=RestrictionsFactoryUtil.ge("lastWorkingDate",startDate);
        criterion=RestrictionsFactoryUtil.and(criterion,criterion1);
        dynamicQuery.add(criterion);
        return ResignationLocalServiceUtil.dynamicQuery(dynamicQuery);
    }
    public List<Resignation> findYearlyEntriesByCreationDate(Date startDate , Date endDate) {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ResignationImpl.class, PortalClassLoaderUtil.getClassLoader());
        Criterion criterion;
        Criterion criterion1;
        criterion=RestrictionsFactoryUtil.le("creationDate",endDate);
        criterion1=RestrictionsFactoryUtil.ge("creationDate",startDate);
        criterion=RestrictionsFactoryUtil.and(criterion,criterion1);
        dynamicQuery.add(criterion);
        return ResignationLocalServiceUtil.dynamicQuery(dynamicQuery);
    }
    public List<Object[]> findUniqueResignationYears() {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ResignationImpl.class, PortalClassLoaderUtil.getClassLoader());
        ProjectionList projectionList = ProjectionFactoryUtil.projectionList();
        projectionList.add(ProjectionFactoryUtil.property("creationDate"));
        projectionList.add(ProjectionFactoryUtil.property("lastWorkingDate"));
        dynamicQuery.setProjection(projectionList);
        return ResignationLocalServiceUtil.dynamicQuery(dynamicQuery);
    }
    @Override
    public List<Resignation> findActiveResignationsByLastWorkingDate(Date lastWorkingDay) {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ResignationImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("lastWorkingDate").eq(lastWorkingDay));
        dynamicQuery.add(PropertyFactoryUtil.forName("status").ne(6));
        return ResignationLocalServiceUtil.dynamicQuery(dynamicQuery);
    }
    @Override
    public List<Resignation> findAllResignationsByHrState() {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ResignationImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("status").eq(1));
        return ResignationLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

}


