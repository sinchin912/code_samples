package com.trantorinc.synergy.admin.panel.service;


import com.google.common.collect.ListMultimap;
import com.google.common.collect.MultimapBuilder;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.model.UserTracker;
import com.liferay.portal.kernel.model.UserTrackerPath;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.UserTrackerLocalServiceUtil;
import com.liferay.portal.kernel.service.UserTrackerPathLocalServiceUtil;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.trantorinc.synergy.admin.panel.dto.DriveDto;
import com.trantorinc.synergy.admin.panel.dto.EmailDto;
import com.trantorinc.synergy.admin.panel.dto.SchedulerDto;
import com.trantorinc.synergy.admin.panel.dto.UserDto;
import com.trantorinc.synergy.common.service.drive.DriveService;
import com.trantorinc.synergy.custom.service.task.*;
import com.trantorinc.synergy.email.core.model.Email;
import com.trantorinc.synergy.email.core.service.EmailLocalServiceUtil;
import com.trantorinc.synergy.lms.core.model.Drive;
import com.trantorinc.synergy.lms.core.model.Scheduler;
import com.trantorinc.synergy.lms.core.service.DriveLocalServiceUtil;
import com.trantorinc.synergy.lms.core.service.SchedulerLocalServiceUtil;

import javax.portlet.ActionRequest;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import static com.trantorinc.synergy.admin.panel.constants.AdminPanelPortletKeys.*;
import static com.trantorinc.synergy.common.service.constant.AppConstantService.*;
import static com.trantorinc.synergy.common.service.constant.AppConstantService.BLANK;
import static com.trantorinc.synergy.common.service.util.UtilService.*;


public class AdminPortletServiceImpl implements AdminPortletService{

	@Override
	public List<SchedulerDto> getSchedulersOnDate(LocalDate onDate) {
		List<Scheduler> schedulerList = SchedulerLocalServiceUtil.findSchedulersByDate(getStartOfDayDate(onDate));
		List<SchedulerDto> schedulerDtos = new ArrayList<>();
		for(String taskName : TASK_LIST){
			SchedulerDto schedulerDto = new SchedulerDto();
			schedulerDto.setTaskName(taskName);
			schedulerDto.setSchedule(getTaskSchedule(taskName));
			schedulerDto.setDescription(getTaskDescription(taskName));
			schedulerDto.setExecute(getIstLocalDateTime().toLocalDate().isEqual(onDate));
			List<Scheduler> schedulerNameList = schedulerList.stream().filter(s -> s.getName().equalsIgnoreCase(taskName)).collect(Collectors.toList());
			if(!schedulerNameList.isEmpty()){
				Scheduler schedulerName = schedulerNameList.get(0);
				schedulerDto.setStatus(schedulerName.getStatus() ? SUCCESS : FAIL );
				schedulerDto.setTriggered(convertDateToLocalDateTime(schedulerName.getRunDate()).format(FORMATTER_YYYY_MM_DD_HH_MM_SS));
			} else {
				schedulerDto.setTriggered(BLANK);
				schedulerDto.setStatus(BLANK);
			}
			schedulerDtos.add(schedulerDto);
		}
		return schedulerDtos;
	}

	@Override
	public List<DriveDto> getDrives(){
		List<DriveDto> driveDtos = new ArrayList<>();
		List<Drive> drives = DriveLocalServiceUtil.getDrives(-1, -1);
		for(String moduleName : MODULE_LIST) {
			if (!moduleName.equalsIgnoreCase(MODULE_PROBATION) && !moduleName.equalsIgnoreCase(MODULE_EXIT) && !moduleName.equalsIgnoreCase(MODULE_GENERIC)) {
				DriveDto driveDto = new DriveDto();
				driveDto.setName(moduleName);
				List<Drive> driveName = drives.stream().filter(d -> d.getFolderName().equalsIgnoreCase(moduleName)).collect(Collectors.toList());
				if (!driveName.isEmpty()) {
					driveDto.setId(driveName.get(0).getFolderId());
					driveDto.setFileCount(driveName.get(0).getFilesCount());
					driveDto.setUpdatedOn(convertDateToLocalDateTime(driveName.get(0).getUpdateDate()).format(FORMATTER_YYYY_MM_DD_HH_MM_SS));
				}
				driveDtos.add(driveDto);
			}
		}
		return driveDtos;
	}

	@Override
	public void createDrive(String driveName){
		String folderId = DriveService.createFolder(GOOGLE_FOLDER_ID, driveName);
		Drive drive = DriveLocalServiceUtil.createDrive(CounterLocalServiceUtil.increment());
		drive.setFolderName(driveName);
		drive.setFolderId(folderId);
		drive.setUpdateDate(getIstDate());
		DriveLocalServiceUtil.addDrive(drive);
	}

	@Override
	public void uploadFile(ActionRequest actionRequest){
		String driveName = ParamUtil.getString(actionRequest, "driveName");
		String newFilename = ParamUtil.getString(actionRequest, "newFilename");
		String[] fileNames = newFilename.split(";");
		UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(actionRequest);
		File[] files = uploadPortletRequest.getFiles("newFile");
		String driveId = DriveLocalServiceUtil.findFolderIdByFolderName(driveName);
		for(int x =0; x<files.length ; x++) {
			DriveService.uploadFile(driveId,fileNames[x] ,files[x]);
		}
	}

	@Override
	public List<UserDto> getUsersOnDate(LocalDate onDate) {
		List<UserDto> userDtoList = new ArrayList<>();
		LocalDateTime startZone = convertIstToGmt(onDate.atStartOfDay(ZoneId.systemDefault()).toLocalDateTime());
		LocalDateTime endZone = convertIstToGmt(onDate.atTime(23,59,59).atZone(ZoneId.systemDefault()).toLocalDateTime());
		Date startDate = Date.from(startZone.atZone(ZoneId.systemDefault()).toInstant());
		Date endDate = Date.from(endZone.atZone(ZoneId.systemDefault()).toInstant());
		DynamicQuery userTrackerQuery = DynamicQueryFactoryUtil.forClass(UserTracker.class, PortalClassLoaderUtil.getClassLoader());
		userTrackerQuery.add(PropertyFactoryUtil.forName("modifiedDate").ge(startDate));
		userTrackerQuery.add(PropertyFactoryUtil.forName("modifiedDate").le(endDate));
		List<UserTracker> userTrackerDynamicList = UserTrackerLocalServiceUtil.dynamicQuery(userTrackerQuery);
		for(UserTracker userTracker : userTrackerDynamicList){
			UserDto userDto = new UserDto();
			userDto.setUserId(userTracker.getUserId());
			userDto.setUserName(UserLocalServiceUtil.fetchUser(userTracker.getUserId()).getFullName());
			userDto.setUserTrackerId("U"+userTracker.getUserTrackerId());
			userDto.setUserLoginTime(convertGmtToIst(convertDateToLocalDateTime(userTracker.getModifiedDate())).format(FORMATTER_YYYY_MM_DD_HH_MM_SS));
			userDto.setUserIp(userTracker.getRemoteAddr());
			userDto.setUserAgent(userTracker.getUserAgent());
			List<UserTrackerPath> userTrackerPathList = UserTrackerPathLocalServiceUtil.getUserTrackerPaths(userTracker.getUserTrackerId(),-1,-1);
			if(!userTrackerPathList.isEmpty()) {
				Map<String, String> userTrackerPathMap = new HashMap<>();
				for(UserTrackerPath userTrackerPath : userTrackerPathList){
					userTrackerPathMap.put(userTrackerPath.getPath(),convertGmtToIst(convertDateToLocalDateTime(userTrackerPath.getPathDate())).format(FORMATTER_YYYY_MM_DD_HH_MM_SS));
				}
				userDto.setUserTrackerPath(userTrackerPathMap.entrySet().stream()
						.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
						.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
								(oldValue, newValue) -> oldValue, LinkedHashMap::new)));
			}
			userDtoList.add(userDto);
		}
		Collections.sort(userDtoList);
		return userDtoList;
	}

	@Override
	public List<EmailDto> getEmailsOnDate(LocalDate onDate) {
		List<EmailDto> emailDtos = new ArrayList<>();
		Date startDate = getStartOfDayDate(onDate);
		Date endDate = getEndOfDayDate(onDate);
		List<Email> emails = EmailLocalServiceUtil.findEmailByOnDate(startDate,endDate);
		for(Email email : emails){
			EmailDto emailDto = new EmailDto();
			emailDto.setEmailId("E"+ email.getEmailId());
			emailDto.setToAddress(null == email.getToAddress() ? BLANK : email.getToAddress());
			emailDto.setCcAddress(null == email.getCcAddress() ? BLANK : email.getCcAddress());
			emailDto.setBccAddress(null == email.getBccAddress() ? BLANK : email.getBccAddress());
			emailDto.setModule(email.getModule());
			emailDto.setScheduled(email.getScheduled() ? YES : NO);
			emailDto.setSubject(email.getSubject());
			emailDto.setBody(email.getBody());
			emailDtos.add(emailDto);
		}
		return emailDtos;
	}

	@Override
	public String getEmailStats(LocalDate today) {
		LocalDate tommorow = today.plusDays(1);
		ListMultimap<String, Object> emailStatsMap = MultimapBuilder.linkedHashKeys().arrayListValues().build();
		LocalDate startDate = today.minusDays(365);
		List<Email> emails = EmailLocalServiceUtil.findEmailByOnDate(getStartOfDayDate(startDate), getEndOfDayDate(today));
		for (LocalDate date = startDate; date.isBefore(tommorow); date = date.plusDays(1)) {
			LocalDate finalDate = date;
			emailStatsMap.put("legend", date.format(FORMATTER_YYYY_MM_DD));
			for(String module : MODULE_LIST) {
				int count = (int) emails.stream().filter(e -> e.getModule().equalsIgnoreCase(module)).filter(e -> convertDateToLocalDateTime(e.getCreatedDate()).toLocalDate().isEqual(finalDate)).count();
				emailStatsMap.put(module, count);
			}
		}
		return convertToJson(emailStatsMap.asMap());
	}

	@Override
	public Map<String, String> executeScheduler(String taskName){
		Map<String, String> schedulerStatus = new HashMap<>();
		schedulerStatus.put("status" , FAIL);
		LocalDateTime now = getIstLocalDateTime();
		switch (taskName){
			case TASK_GENERIC :
				GenericTaskService.execute(now);
				break;
			case TASK_AM_LMS :
				LmsTaskService.execute(now);
				break;
			case TASK_PM_LMS :
				LmsTaskService.execute(now);
				break;
			case TASK_AM_PROBATION:
				ProbationTaskService.executeAm(now);
				break;
			case TASK_PM_PROBATION:
				ProbationTaskService.executePm(now);
				break;
			case TASK_GAME:
				GameTaskService.execute(now);
				break;
			case TASK_AM_SKILL:
				SkillTaskService.executeAm(now);
				break;
			case TASK_PM_SKILL:
				SkillTaskService.executePm(now);
				break;
			case TASK_AM_EXIT:
				break;
			case TASK_PM_EXIT:
				break;
			case TASK_PERFORMANCE:
				break;
			default:
				schedulerStatus.put("triggered", now.format(FORMATTER_YYYY_MM_DD_HH_MM_SS));
		}
		Scheduler scheduler = SchedulerLocalServiceUtil.findSchedulerByNameAndDate(taskName,getStartOfDayDate(now.toLocalDate()));
		if(null != scheduler) {
			schedulerStatus.put("status", scheduler.getStatus() ? SUCCESS : FAIL);
			schedulerStatus.put("triggered", convertDateToLocalDateTime(scheduler.getRunDate()).format(FORMATTER_YYYY_MM_DD_HH_MM_SS));
		}
		return schedulerStatus;
	}

	private String getTaskDescription(String taskName) {
		String schedule;
		switch (taskName){
			case TASK_AM_LMS :
				schedule = TASK_LMS_DESCRIPTION;
				break;
			case TASK_GAME :
				schedule = TASK_GAME_DESCRIPTION;
				break;
			case TASK_AM_PROBATION :
				schedule = TASK_AM_PROBATION_DESCRIPTION;
				break;
			case TASK_AM_EXIT :
				schedule = TASK_AM_EXIT_DESCRIPTION;
				break;
			case TASK_PM_EXIT :
				schedule = TASK_PM_EXIT_DESCRIPTION;
				break;
			case TASK_AM_SKILL :
				schedule = TASK_AM_SKILL_DESCRIPTION;
				break;
			case TASK_GENERIC :
				schedule = TASK_GENERIC_DESCRIPTION;
				break;
			case TASK_PM_SKILL :
				schedule = TASK_PM_SKILL_DESCRIPTION;
				break;
			case TASK_PM_LMS :
				schedule = TASK_LMS_DESCRIPTION;
				break;
			case TASK_PM_PROBATION :
				schedule = TASK_PM_PROBATION_DESCRIPTION;
				break;
			case TASK_PERFORMANCE :
				schedule = TASK_PERFORMANCE_DESCRIPTION;
				break;
			default:
				schedule = BLANK;
		}
		return schedule;
	}


	private String getTaskSchedule(String taskName) {
		String schedule;
		switch (taskName){
			case TASK_AM_LMS :
				schedule = "1.25 AM";
				break;
			case TASK_GAME :
				schedule = "5.25 AM";
				break;
			case TASK_AM_PROBATION :
				schedule = "5.55 AM";
				break;
			case TASK_AM_EXIT :
				schedule = "6.25 AM";
				break;
			case TASK_AM_SKILL :
				schedule = "7.25 AM";
				break;
			case TASK_GENERIC :
				schedule = "10.55 AM";
				break;
			case TASK_PM_SKILL :
				schedule = "1.55 PM";
				break;
			case TASK_PM_LMS :
				schedule = "2.25 PM";
				break;
			case TASK_PM_PROBATION :
				schedule = "2.55 PM";
				break;
			case TASK_PM_EXIT:
				schedule = "8.55 PM";
				break;
			case TASK_PERFORMANCE :
				schedule = "11.55 PM";
				break;
			default:
				schedule = BLANK;
		}
		return schedule;
	}

	private LocalDateTime convertGmtToIst(LocalDateTime localDateTime){
		return localDateTime.atZone(ZoneId.of("GMT"))
				.withZoneSameInstant(ZoneId.of("Asia/Calcutta"))
				.toLocalDateTime();
	}

	private LocalDateTime convertIstToGmt(LocalDateTime localDateTime){
		return localDateTime.atZone(ZoneId.of("Asia/Calcutta"))
				.withZoneSameInstant(ZoneId.of("GMT"))
				.toLocalDateTime();
	}
}