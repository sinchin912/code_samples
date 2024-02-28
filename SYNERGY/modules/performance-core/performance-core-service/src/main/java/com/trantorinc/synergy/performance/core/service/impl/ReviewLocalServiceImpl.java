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

import com.trantorinc.synergy.performance.core.model.Review;
import com.trantorinc.synergy.performance.core.service.base.ReviewLocalServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
        property = "model.class.name=com.trantorinc.synergy.performance.core.model.Review",
        service = AopService.class
)
public class ReviewLocalServiceImpl extends ReviewLocalServiceBaseImpl {
    public List<Review> findReviewByReviewStartDate(Date fyStartDate) {
        return reviewFinder.findReviewByReviewStartDate(fyStartDate);
    }

    public List<Review> findReviewByKpiId(long kpiId) {
        return reviewFinder.findReviewByKpiId(kpiId);
    }

    public List<Date> findUniqueReviewYears() {
        return  reviewFinder.findUniqueReviewYears().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
    }

    public List<Review> findReviewByEcode(String ecode) {
        return reviewFinder.findReviewByEcode(ecode);
    }

    public List<Review> findReviewByState(int state) {
        return reviewFinder.findReviewByState(state);
    }
    public List<Review> findReviewByManager(String managerEmail, List<String> empCodes) {
        return reviewFinder.findReviewByManager(managerEmail, empCodes);
    }
}