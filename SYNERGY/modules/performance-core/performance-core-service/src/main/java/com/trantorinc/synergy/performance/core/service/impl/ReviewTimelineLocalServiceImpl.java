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

import com.trantorinc.synergy.performance.core.model.ReviewTimeline;
import com.trantorinc.synergy.performance.core.service.base.ReviewTimelineLocalServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.trantorinc.synergy.common.service.util.UtilService.getCurrentYear;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
        property = "model.class.name=com.trantorinc.synergy.performance.core.model.ReviewTimeline",
        service = AopService.class
)
public class ReviewTimelineLocalServiceImpl
        extends ReviewTimelineLocalServiceBaseImpl {
    public List<ReviewTimeline> getCalibratedTimelines() {
        List<ReviewTimeline> timelines = reviewTimelineLocalService.getReviewTimelines(-1, -1);
        int currentYear = getCurrentYear();
        List<ReviewTimeline> calibratedTimelines = new ArrayList<>();
        for (ReviewTimeline timeline : timelines) {
            LocalDate endDate = timeline.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (endDate.getMonthValue() < 4) {
                endDate = endDate.withYear(currentYear + 1);
            } else {
                endDate = endDate.withYear(currentYear);
            }
            Date calibratedDate = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            timeline.setEndDate(calibratedDate);
            calibratedTimelines.add(timeline);
        }
        return calibratedTimelines;
    }
}