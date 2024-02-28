/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 * <p>
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * <p>
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.trantorinc.synergy.performance.core.service.impl;

import com.liferay.portal.aop.AopService;

import com.trantorinc.synergy.performance.core.model.ReviewUpload;
import com.trantorinc.synergy.performance.core.service.base.ReviewUploadLocalServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
        property = "model.class.name=com.trantorinc.synergy.performance.core.model.ReviewUpload",
        service = AopService.class
)
public class ReviewUploadLocalServiceImpl
        extends ReviewUploadLocalServiceBaseImpl {
    public List<ReviewUpload> findReviewUploadByReviewId(long reviewId) {
        return reviewUploadFinder.findReviewUploadByReviewId(reviewId);
    }
}