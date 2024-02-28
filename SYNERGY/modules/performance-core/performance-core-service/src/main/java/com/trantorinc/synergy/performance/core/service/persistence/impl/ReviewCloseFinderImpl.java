package com.trantorinc.synergy.performance.core.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.trantorinc.synergy.performance.core.model.ReviewClose;
import com.trantorinc.synergy.performance.core.model.impl.ReviewCloseImpl;
import com.trantorinc.synergy.performance.core.service.ReviewCloseLocalServiceUtil;
import com.trantorinc.synergy.performance.core.service.persistence.ReviewCloseFinder;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
        service = ReviewCloseFinder.class
)
public class ReviewCloseFinderImpl extends ReviewClosePersistenceImpl implements ReviewCloseFinder {
    @Override
    public List<ReviewClose> findReviewCloseByEmail(String managerEmail) {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ReviewCloseImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("requestBy").eq(managerEmail));
        return ReviewCloseLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    @Override
    public List<ReviewClose> findReviewCloseByStatus(int status) {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ReviewCloseImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("status").eq(status));
        return ReviewCloseLocalServiceUtil.dynamicQuery(dynamicQuery);
    }
    @Override
    public List<ReviewClose> findReviewCloseByReviewId(long reviewId) {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ReviewCloseImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("reviewId").eq(reviewId));
        return ReviewCloseLocalServiceUtil.dynamicQuery(dynamicQuery);
    }
}
