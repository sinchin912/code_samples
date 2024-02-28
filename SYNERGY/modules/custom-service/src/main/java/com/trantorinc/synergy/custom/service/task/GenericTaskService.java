package com.trantorinc.synergy.custom.service.task;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;
import com.trantorinc.synergy.email.core.model.Email;
import com.trantorinc.synergy.email.core.service.EmailLocalServiceUtil;
import com.trantorinc.synergy.lms.core.model.Employee;
import com.trantorinc.synergy.lms.core.model.Scheduler;
import com.trantorinc.synergy.lms.core.service.EmployeeLocalServiceUtil;
import com.trantorinc.synergy.lms.core.service.SchedulerLocalServiceUtil;

import java.text.MessageFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.trantorinc.synergy.common.service.constant.AppConstantService.*;
import static com.trantorinc.synergy.common.service.util.UtilService.convertLocalDateTimeToDate;
import static com.trantorinc.synergy.common.service.util.UtilService.getStartOfDayDate;
import static com.trantorinc.synergy.custom.service.constants.SynergyServicePortletKeys.*;

public class GenericTaskService {
	private static final Log log = LogFactoryUtil.getLog(GenericTaskService.class.getName());

	private GenericTaskService(){

	}

	public static void execute(LocalDateTime now){

		log.info(MODULE_GENERIC + " task started !");

		Scheduler scheduler = SchedulerLocalServiceUtil.findSchedulerByNameAndDate(TASK_GENERIC, getStartOfDayDate(now.toLocalDate()));
		if(null == scheduler){
			scheduler = SchedulerLocalServiceUtil.createScheduler(CounterLocalServiceUtil.increment());
			scheduler.setName(TASK_GENERIC);
			scheduler.setStatus(false);
			scheduler.setOnDate(getStartOfDayDate(now.toLocalDate()));
			scheduler.setRunDate(convertLocalDateTimeToDate(now));
			SchedulerLocalServiceUtil.addScheduler(scheduler);
		} else {
			scheduler.setStatus(false);
			scheduler.setRunDate(convertLocalDateTimeToDate(now));
			SchedulerLocalServiceUtil.updateScheduler(scheduler);
		}
		log.info(MODULE_GENERIC + " task preset !!");

		sendBloodDonateEmail(now);
		log.info(MODULE_GENERIC + " task completed ...(1/4)");

		sendCovidVaccinationUpdateEmail(now);
		log.info(MODULE_GENERIC + " task completed ...(2/4)");

		sendLmsAttendanceEmail(now);
		log.info(MODULE_GENERIC + " task completed ...(3/4)");

		sendRoleMail(now);
		log.info(MODULE_GENERIC + " task completed ...(4/4)");

		scheduler.setStatus(true);
		SchedulerLocalServiceUtil.updateScheduler(scheduler);
		log.info(MODULE_GENERIC + " task completed !!!");
	}

	private static void sendBloodDonateEmail(LocalDateTime now){
		TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
		int weekNumber = now.get(woy);
		if(weekNumber % 2 == 0 && now.getDayOfWeek() == DayOfWeek.FRIDAY) {
			Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
			email.setBccAddress(DL_TRANTORINDIA);
			email.setSubject(SUBJECT_BLOOD_DONATION);
			email.setBody(BODY_BLOOD_DONATION);
			email.setModule(MODULE_GENERIC);
			email.setScheduled(true);
			email.setSent(false);
			email.setCreatedDate(convertLocalDateTimeToDate(now));
			EmailLocalServiceUtil.addEmail(email);
		}
	}

	private static void sendCovidVaccinationUpdateEmail(LocalDateTime now) {
		if (now.getDayOfMonth()==1 || now.getDayOfMonth()==16) {
			Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
			email.setBccAddress(DL_TRANTORINDIA);
			email.setSubject(SUBJECT_COVID);
			email.setBody(MessageFormat.format(BODY_COVID, COVID_LINK));
			email.setModule(MODULE_GENERIC);
			email.setScheduled(true);
			email.setSent(false);
			email.setCreatedDate(convertLocalDateTimeToDate(now));
			EmailLocalServiceUtil.addEmail(email);
		}
	}

	private static void sendLmsAttendanceEmail(LocalDateTime now){
		if(now.getDayOfWeek() == DayOfWeek.WEDNESDAY) {
			Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
			email.setBccAddress(DL_TRANTORINDIA);
			email.setSubject(SUBJECT_LMS);
			email.setBody(MessageFormat.format(BODY_LMS,LMS_LINK));
			email.setModule(MODULE_GENERIC);
			email.setScheduled(true);
			email.setSent(false);
			email.setCreatedDate(convertLocalDateTimeToDate(now));
			EmailLocalServiceUtil.addEmail(email);
		}
	}

	private static void sendRoleMail(LocalDateTime now) {
		Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
		email.setToAddress(DL_INTRANET);
		email.setSubject(SUBJECT_ROLE);
		email.setBody(TABLE_START_ROLE + String.join(BLANK, getRolesData())+TABLE_END);
		email.setModule(MODULE_GENERIC);
		email.setScheduled(true);
		email.setSent(false);
		email.setCreatedDate(convertLocalDateTimeToDate(now));
		EmailLocalServiceUtil.addEmail(email);
	}

	private static List<String> getRolesData() {
		List<Employee> employees = EmployeeLocalServiceUtil.findAllActiveEmployees();
		Predicate<Role> filterUnwantedRoles = role -> !role.getName().equals("Guest") && !role.getName().equals("User") && !role.getName().equals("Power User");
		Function<Role, String> rolesDataToString = role -> {
			List<User> users = UserLocalServiceUtil.getRoleUsers(role.getRoleId());
			if (!users.isEmpty()) {
				StringBuilder finalRoleString = new StringBuilder();
				finalRoleString.append(ROW_START).append(role.getName()).append(CELL_SEPARATOR);
				for(User user : users){
					List<Employee> ecodeEmployees = employees.stream().filter(e -> e.getEmail().equalsIgnoreCase(user.getEmailAddress())).collect(Collectors.toList());
					if(!ecodeEmployees.isEmpty()){
						finalRoleString.append(user.getFullName()).append(COMMA);
					}
				}
				finalRoleString.append(ROW_END);
				return finalRoleString.toString();
			}
			return BLANK;
		};

		return RoleLocalServiceUtil.getRoles(0, RoleLocalServiceUtil.getRolesCount()).stream()
				.filter(filterUnwantedRoles).map(rolesDataToString).filter(entry -> !Validator.isBlank(entry))
				.collect(Collectors.toList());
	}

}
