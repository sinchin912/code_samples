package com.trantorinc.synergy.performance.core.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.trantorinc.synergy.performance.core.model.ReviewLine;
import com.trantorinc.synergy.performance.core.model.impl.ReviewLineImpl;
import com.trantorinc.synergy.performance.core.service.ReviewLineLocalServiceUtil;
import com.trantorinc.synergy.performance.core.service.persistence.ReviewLineFinder;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
        service = ReviewLineFinder.class
)

public class ReviewLineFinderImpl extends ReviewLinePersistenceImpl implements ReviewLineFinder {
    @Override
    public List<ReviewLine> findReviewLineByReviewId(long reviewId) {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ReviewLineImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("reviewId").eq(reviewId));
        return ReviewLineLocalServiceUtil.dynamicQuery(dynamicQuery);
    }
}
