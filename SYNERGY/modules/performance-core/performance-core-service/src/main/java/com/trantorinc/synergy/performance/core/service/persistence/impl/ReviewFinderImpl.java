package com.trantorinc.synergy.performance.core.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.*;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.trantorinc.synergy.performance.core.model.Review;
import com.trantorinc.synergy.performance.core.model.impl.ReviewImpl;
import com.trantorinc.synergy.performance.core.service.ReviewLocalServiceUtil;
import com.trantorinc.synergy.performance.core.service.persistence.ReviewFinder;
import org.osgi.service.component.annotations.Component;

import java.util.Date;
import java.util.List;

@Component(
        service = ReviewFinder.class
)

public class ReviewFinderImpl extends ReviewPersistenceImpl implements ReviewFinder {
    private static final Log log = LogFactoryUtil.getLog(ReviewFinderImpl.class.getName());


    @Override
    public List<Review> findReviewByReviewStartDate(Date fyStartDate) {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ReviewImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("reviewStartDate").eq(fyStartDate));
        return ReviewLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    @Override
    public List<Review> findReviewByKpiId(long kpiId) {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ReviewImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("kpiId").eq(kpiId));
        return ReviewLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    @Override
    public List<Date> findUniqueReviewYears() {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ReviewImpl.class, PortalClassLoaderUtil.getClassLoader());
        Projection projection = ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property("reviewStartDate"));
        dynamicQuery.setProjection(projection);
        return ReviewLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    @Override
    public List<Review> findReviewByEcode(String ecode) {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ReviewImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("ecode").eq(ecode));
        return ReviewLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    @Override
    public List<Review> findReviewByState(int state) {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ReviewImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("reviewState").eq(state));
        return ReviewLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

//    TODO: Change Custom to Dynamic and  remove try catch block
    @Override
    public List<Review> findReviewByManager(String managerEmail, List<String> empCodes)
    {
        Session session = null;
        try {
            session = openSession();
            String sql = "SELECT PERFORMANCE_REVIEW.* FROM PERFORMANCE_REVIEW WHERE PERFORMANCE_REVIEW.manager='"+managerEmail+"' and  PERFORMANCE_REVIEW.reviewState != 1";
            if(!empCodes.isEmpty()) {
                StringBuilder ecodeList = new StringBuilder();
                for(String s : empCodes) {
                    ecodeList.append(",'");
                    ecodeList.append(s);
                    ecodeList.append("'");
                }
                String finalecodeList= ecodeList.toString().substring(1);
                sql = sql+" And PERFORMANCE_REVIEW.ecode NOT IN ("+finalecodeList+")";
            }
            SQLQuery q = session.createSQLQuery(sql);
            q.addEntity("Review", ReviewImpl.class);
            return (List<Review>) q.list();
        }
        catch (Exception exception) {
            log.error(exception.getStackTrace()[0].getMethodName()+":" +exception.getStackTrace()[0].getLineNumber()+":"+ exception.getMessage());
        }
        finally {
            closeSession(session);
        }
        return null;
    }
}
