package com.trantorinc.synergy.performance.core.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.trantorinc.synergy.performance.core.model.ReviewComment;
import com.trantorinc.synergy.performance.core.model.impl.ReviewCommentImpl;
import com.trantorinc.synergy.performance.core.service.ReviewCommentLocalServiceUtil;
import com.trantorinc.synergy.performance.core.service.persistence.ReviewCommentFinder;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
        service = ReviewCommentFinder.class
)
public class ReviewCommentFinderImpl extends ReviewCommentPersistenceImpl implements ReviewCommentFinder {
    @Override
    public List<ReviewComment> findReviewCommentByReviewId(long reviewId) {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ReviewCommentImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("reviewId").eq(reviewId));
        return ReviewCommentLocalServiceUtil.dynamicQuery(dynamicQuery);
    }
}
