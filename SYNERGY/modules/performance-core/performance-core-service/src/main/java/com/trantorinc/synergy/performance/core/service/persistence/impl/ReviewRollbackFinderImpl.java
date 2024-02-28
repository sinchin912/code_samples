package com.trantorinc.synergy.performance.core.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.trantorinc.synergy.performance.core.model.ReviewRollback;
import com.trantorinc.synergy.performance.core.model.impl.ReviewRollbackImpl;
import com.trantorinc.synergy.performance.core.service.ReviewRollbackLocalServiceUtil;
import com.trantorinc.synergy.performance.core.service.persistence.ReviewRollbackFinder;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
        service = ReviewRollbackFinder.class
)

public class ReviewRollbackFinderImpl extends ReviewRollbackPersistenceImpl implements ReviewRollbackFinder {
    @Override
    public List<ReviewRollback> findReviewRollbackByEmail(String managerEmail) {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ReviewRollbackImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("requestBy").eq(managerEmail));
        return ReviewRollbackLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    @Override
    public List<ReviewRollback> findReviewRollbackByStatus(int status) {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ReviewRollbackImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("status").eq(status));
        return ReviewRollbackLocalServiceUtil.dynamicQuery(dynamicQuery);
    }
    @Override
    public List<ReviewRollback> findReviewRollbackByReviewId(long reviewId) {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ReviewRollbackImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("reviewId").eq(reviewId));
        return ReviewRollbackLocalServiceUtil.dynamicQuery(dynamicQuery);
    }
}
