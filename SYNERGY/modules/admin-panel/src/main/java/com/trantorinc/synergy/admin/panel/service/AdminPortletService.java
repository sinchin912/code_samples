package com.trantorinc.synergy.admin.panel.service;

import com.trantorinc.synergy.admin.panel.dto.DriveDto;
import com.trantorinc.synergy.admin.panel.dto.EmailDto;
import com.trantorinc.synergy.admin.panel.dto.SchedulerDto;
import com.trantorinc.synergy.admin.panel.dto.UserDto;

import javax.portlet.ActionRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface AdminPortletService {
	List<SchedulerDto> getSchedulersOnDate(LocalDate onDate);
	List<DriveDto> getDrives();
	void createDrive(String driveName);
	void uploadFile(ActionRequest actionRequest);
	List<UserDto> getUsersOnDate(LocalDate onDate);
	List<EmailDto> getEmailsOnDate(LocalDate onDate);
	String getEmailStats(LocalDate today);
	Map<String, String> executeScheduler(String taskName);
}
