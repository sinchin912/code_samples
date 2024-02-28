/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.trantorinc.synergy.game.core.service.impl;

import com.liferay.portal.aop.AopService;

import com.trantorinc.synergy.game.core.model.GameTimeline;
import com.trantorinc.synergy.game.core.model.impl.GameTimelineImpl;
import com.trantorinc.synergy.game.core.service.base.GameTimelineLocalServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.trantorinc.synergy.game.core.model.GameTimeline",
	service = AopService.class
)
public class GameTimelineLocalServiceImpl
	extends GameTimelineLocalServiceBaseImpl {

	public List<GameTimeline> getCalibratedTimeline(){
		List<GameTimeline> gameTimelines = gameTimelineLocalService.getGameTimelines(-1,-1);
		LocalDate today = LocalDate.now();
		int currentMonth = today.getMonthValue();
		int currentYear  = today.getYear();
		if(currentMonth < 4){
			currentYear = currentYear -1;
		}
		List<GameTimeline> calibratedGameTimelines = new ArrayList<>();
		for(GameTimeline gameTimeline : gameTimelines){
			GameTimeline calibratedGameTimeline = new GameTimelineImpl();
			calibratedGameTimeline.setName(gameTimeline.getName());

			LocalDateTime openDate =  gameTimeline.getOpenDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			if(openDate.getMonthValue()< 4){
				openDate = openDate.withYear(currentYear+1);
			} else {
				openDate = openDate.withYear(currentYear);
			}
			calibratedGameTimeline.setOpenDate(Date.from(openDate.atZone(ZoneId.systemDefault()).toInstant()));

			LocalDateTime freezeDate =  gameTimeline.getFreezeDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			if(freezeDate.getMonthValue()< 4){
				freezeDate = freezeDate.withYear(currentYear+1);
			} else {
				freezeDate = freezeDate.withYear(currentYear);
			}
			calibratedGameTimeline.setFreezeDate(Date.from(freezeDate.atZone(ZoneId.systemDefault()).toInstant()));

			LocalDateTime actionDate =  gameTimeline.getActionDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			if(actionDate.getMonthValue()< 4){
				actionDate = actionDate.withYear(currentYear+1);
			} else {
				actionDate = actionDate.withYear(currentYear);
			}
			calibratedGameTimeline.setActionDate(Date.from(actionDate.atZone(ZoneId.systemDefault()).toInstant()));

			LocalDateTime closeDate =  gameTimeline.getCloseDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			if(closeDate.getMonthValue()< 4){
				closeDate = closeDate.withYear(currentYear+1);
			} else {
				closeDate = closeDate.withYear(currentYear);
			}
			calibratedGameTimeline.setCloseDate(Date.from(closeDate.atZone(ZoneId.systemDefault()).toInstant()));

			calibratedGameTimelines.add(calibratedGameTimeline);
		}
		return calibratedGameTimelines;
	}
}