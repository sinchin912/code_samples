package com.trantorinc.synergy.notice.core.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.*;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.trantorinc.synergy.notice.core.model.QuestionnaireForm;
import com.trantorinc.synergy.notice.core.model.impl.QuestionnaireFormImpl;
import com.trantorinc.synergy.notice.core.service.QuestionnaireFormLocalServiceUtil;
import com.trantorinc.synergy.notice.core.service.persistence.QuestionnaireFormFinder;
import org.osgi.service.component.annotations.Component;

import java.util.Date;
import java.util.List;

@Component(
        service = QuestionnaireFormFinder.class
)
public class QuestionnaireFormFinderImpl extends QuestionnaireFormPersistenceImpl implements QuestionnaireFormFinder
{
    @Override
    public List<QuestionnaireForm> findQuestionnaireFormByResignationId(long resignationId){
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(QuestionnaireFormImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("resignationId").eq(resignationId));
        return QuestionnaireFormLocalServiceUtil.dynamicQuery(dynamicQuery);
    }
    @Override
    public List<Object[]> findAllQuestionnaireFormEntries() {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(QuestionnaireFormImpl.class, PortalClassLoaderUtil.getClassLoader());
        ProjectionList projectionList = ProjectionFactoryUtil.projectionList();
        projectionList.add(ProjectionFactoryUtil.property("id"));
        projectionList.add(ProjectionFactoryUtil.property("resignationId"));
        projectionList.add(ProjectionFactoryUtil.property("submittedDate"));
        dynamicQuery.setProjection(projectionList);
        return QuestionnaireFormLocalServiceUtil.dynamicQuery(dynamicQuery);
    }
    @Override
    public List<QuestionnaireForm> findYearlyEntries(Date startDate , Date endDate) {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(QuestionnaireFormImpl.class, PortalClassLoaderUtil.getClassLoader());
        Criterion criterion;
        Criterion criterion1;
        criterion=RestrictionsFactoryUtil.le("submittedDate",endDate);
        criterion1=RestrictionsFactoryUtil.ge("submittedDate",startDate);
        criterion=RestrictionsFactoryUtil.and(criterion,criterion1);
        dynamicQuery.add(criterion);
        return QuestionnaireFormLocalServiceUtil.dynamicQuery(dynamicQuery);
    }
}