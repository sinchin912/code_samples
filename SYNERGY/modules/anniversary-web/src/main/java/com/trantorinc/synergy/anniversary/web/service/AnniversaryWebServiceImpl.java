package com.trantorinc.synergy.anniversary.web.service;


import com.trantorinc.synergy.anniversary.web.dto.AnniversaryDto;
import com.trantorinc.synergy.common.service.drive.DriveService;
import com.trantorinc.synergy.lms.core.model.Employee;
import com.trantorinc.synergy.lms.core.service.EmployeeLocalServiceUtil;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static com.trantorinc.synergy.common.service.constant.AppConstantService.DEFAULT_ECODE;
import static com.trantorinc.synergy.common.service.constant.AppConstantService.JPG;
import static com.trantorinc.synergy.common.service.util.UtilService.*;


public class AnniversaryWebServiceImpl implements AnniversaryWebService {
	@Override
	public LinkedHashMap<String, List<AnniversaryDto>> getBirthDays(){
		LocalDateTime today = getIstLocalDateTime();
		LocalDateTime tomorrow = today.plusDays(1);
		LocalDateTime yesterday = today.minusDays(1);

		Employee defaultEmployee = EmployeeLocalServiceUtil.fetchEmployee(DEFAULT_ECODE);
		LinkedHashMap<String, List<AnniversaryDto>> birthdayMap = new LinkedHashMap<>();

		List<AnniversaryDto> birthdayTomorrow = new ArrayList<>();
		List<Employee> employeesTomorrow = EmployeeLocalServiceUtil.findBirthdays(convertLocalDateTimeToDate(tomorrow));
		for(Employee employee : employeesTomorrow){
			AnniversaryDto anniversaryDto = new AnniversaryDto();
			anniversaryDto.setName(employee.getName());
			anniversaryDto.setPhotoId(employee.getFileId().equalsIgnoreCase("null") ? defaultEmployee.getFileId() : employee.getFileId());
			birthdayTomorrow.add(anniversaryDto);
		}
		birthdayMap.put("Tomorrow",birthdayTomorrow);

		List<AnniversaryDto> birthdayToday = new ArrayList<>();
		List<Employee> employeesToday = EmployeeLocalServiceUtil.findBirthdays(convertLocalDateTimeToDate(today));
		for(Employee employee : employeesToday){
			AnniversaryDto anniversaryDto = new AnniversaryDto();
			anniversaryDto.setName(employee.getName());
			anniversaryDto.setPhotoId(employee.getFileId().equalsIgnoreCase("null") ? defaultEmployee.getFileId() : employee.getFileId());
			birthdayToday.add(anniversaryDto);
		}
		birthdayMap.put("Today",birthdayToday);

		List<AnniversaryDto> birthdayYesterday = new ArrayList<>();
		List<Employee> employeesYesterday = EmployeeLocalServiceUtil.findBirthdays(convertLocalDateTimeToDate(yesterday));
		for(Employee employee : employeesYesterday){
			AnniversaryDto anniversaryDto = new AnniversaryDto();
			anniversaryDto.setName(employee.getName());
			anniversaryDto.setPhotoId(employee.getFileId().equalsIgnoreCase("null") ? defaultEmployee.getFileId() : employee.getFileId());
			birthdayYesterday.add(anniversaryDto);
		}
		birthdayMap.put("Yesterday",birthdayYesterday);

		return birthdayMap;
	}

	@Override
	public LinkedHashMap<String, List<AnniversaryDto>> getAnniversaries(){
		LocalDateTime today = getIstLocalDateTime();
		LocalDateTime tomorrow = today.plusDays(1);
		LocalDateTime yesterday = today.minusDays(1);
		LinkedHashMap<String, List<AnniversaryDto>> anniversaryMap = new LinkedHashMap<>();
		Employee defaultEmployee = EmployeeLocalServiceUtil.fetchEmployee(DEFAULT_ECODE);

		List<AnniversaryDto> anniversaryTomorrow = new ArrayList<>();
		List<Employee> employeesTomorrow = EmployeeLocalServiceUtil.findAnniversaries(convertLocalDateTimeToDate(tomorrow));
		for(Employee employee : employeesTomorrow){
			LocalDateTime doj = convertDateToLocalDateTime(employee.getDoj());
			if(doj.getYear() < today.getYear()){
				AnniversaryDto anniversaryDto = new AnniversaryDto();
				anniversaryDto.setName(employee.getName());
				anniversaryDto.setPhotoId(employee.getFileId().equalsIgnoreCase("null") ? defaultEmployee.getFileId() : employee.getFileId());
				anniversaryDto.setComment(getAnniversaryComment(today.getYear(), doj.getYear()));
				anniversaryTomorrow.add(anniversaryDto);
			}
		}
		anniversaryMap.put("Tomorrow",anniversaryTomorrow);

		List<AnniversaryDto> anniversaryToday = new ArrayList<>();
		List<Employee> employeesToday = EmployeeLocalServiceUtil.findAnniversaries(convertLocalDateTimeToDate(today));
		for(Employee employee : employeesToday){
			LocalDateTime doj = convertDateToLocalDateTime(employee.getDoj());
			if(doj.getYear() < today.getYear()) {
				AnniversaryDto anniversaryDto = new AnniversaryDto();
				anniversaryDto.setName(employee.getName());
				anniversaryDto.setPhotoId(employee.getFileId().equalsIgnoreCase("null") ? defaultEmployee.getFileId() : employee.getFileId());
				anniversaryDto.setComment(getAnniversaryComment(today.getYear(), doj.getYear()));
				anniversaryToday.add(anniversaryDto);
			}
		}
		anniversaryMap.put("Today",anniversaryToday);

		List<AnniversaryDto> anniversaryYesterday = new ArrayList<>();
		List<Employee> employeesYesterday = EmployeeLocalServiceUtil.findAnniversaries(convertLocalDateTimeToDate(yesterday));
		for(Employee employee : employeesYesterday){
			LocalDateTime doj = convertDateToLocalDateTime(employee.getDoj());
			if(doj.getYear() < today.getYear()) {
				AnniversaryDto anniversaryDto = new AnniversaryDto();
				anniversaryDto.setName(employee.getName());
				anniversaryDto.setPhotoId(employee.getFileId().equalsIgnoreCase("null") ? defaultEmployee.getFileId() : employee.getFileId());
				anniversaryDto.setComment(getAnniversaryComment(today.getYear(), doj.getYear()));
				anniversaryYesterday.add(anniversaryDto);
			}
		}
		anniversaryMap.put("Yesterday",anniversaryYesterday);


		return anniversaryMap;
	}

	@Override
	public String getPhoto(String photoId){
		File photo = DriveService.getFile(photoId,photoId,JPG);
		return getBase64File(photo);
	}


	private String getAnniversaryComment(int currentYear , int dojYear) {
		int diff = currentYear - dojYear;
		String years = "";
		if(diff != 0) {
			if(diff == 1) {
				years = "(1st Anniversary)";
			} else if(diff == 2) {
				years = "(2nd Anniversary)";
			} else if(diff == 3) {
				years = "(3rd Anniversary)";
			} else {
				years = "(" + diff+"th Anniversary)";
			}
		}
		return years;
	}

}