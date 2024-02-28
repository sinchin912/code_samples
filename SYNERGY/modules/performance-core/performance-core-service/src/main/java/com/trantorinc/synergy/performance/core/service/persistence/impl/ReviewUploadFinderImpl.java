package com.trantorinc.synergy.performance.core.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.trantorinc.synergy.performance.core.model.ReviewUpload;
import com.trantorinc.synergy.performance.core.model.impl.ReviewUploadImpl;
import com.trantorinc.synergy.performance.core.service.ReviewUploadLocalServiceUtil;
import com.trantorinc.synergy.performance.core.service.persistence.ReviewUploadFinder;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
        service = ReviewUploadFinder.class
)

public class ReviewUploadFinderImpl extends ReviewUploadPersistenceImpl implements ReviewUploadFinder {
    @Override
    public List<ReviewUpload> findReviewUploadByReviewId(long reviewId) {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ReviewUploadImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("reviewId").eq(reviewId));
        return ReviewUploadLocalServiceUtil.dynamicQuery(dynamicQuery);
    }
}
