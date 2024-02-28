package com.trantorinc.synergy.holiday.web.service;

import com.trantorinc.synergy.holiday.web.dto.HolidayDto;
import com.trantorinc.synergy.lms.core.model.Holiday;
import com.trantorinc.synergy.lms.core.service.HolidayLocalServiceUtil;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static com.trantorinc.synergy.common.service.constant.AppConstantService.FORMATTER_DD_MMM_EEE;
import static com.trantorinc.synergy.common.service.util.UtilService.convertDateToLocalDateTime;
import static com.trantorinc.synergy.common.service.util.UtilService.getIstLocalDateTime;


public class HolidayWebServiceImpl implements HolidayWebService {


	@Override
	public List<HolidayDto> getHolidays() {
		LocalDate today = getIstLocalDateTime().toLocalDate();
		List<Holiday> holidays = HolidayLocalServiceUtil.findHolidaysByYear(today.getYear()).stream().sorted(Comparator.comparing(Holiday::getOnDate)).collect(Collectors.toList());
		List<HolidayDto> holidayDtos = new ArrayList<>();
		for(Holiday holiday : holidays){
			HolidayDto holidayDto = new HolidayDto();
			holidayDto.setName(holiday.getName());
			holidayDto.setOnDate(convertDateToLocalDateTime(holiday.getOnDate()).format(FORMATTER_DD_MMM_EEE));
			holidayDtos.add(holidayDto);
		}
		return holidayDtos;
	}

}