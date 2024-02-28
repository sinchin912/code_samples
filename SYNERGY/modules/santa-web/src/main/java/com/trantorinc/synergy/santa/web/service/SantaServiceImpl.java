package com.trantorinc.synergy.santa.web.service;


import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.trantorinc.synergy.common.service.drive.DriveService;
import com.trantorinc.synergy.email.core.model.Email;
import com.trantorinc.synergy.email.core.service.EmailLocalServiceUtil;
import com.trantorinc.synergy.game.core.model.GameTimeline;
import com.trantorinc.synergy.game.core.model.Santa;
import com.trantorinc.synergy.game.core.service.GameTimelineLocalServiceUtil;
import com.trantorinc.synergy.game.core.service.SantaLocalServiceUtil;
import com.trantorinc.synergy.lms.core.model.Employee;
import com.trantorinc.synergy.lms.core.service.DriveLocalServiceUtil;
import com.trantorinc.synergy.lms.core.service.EmployeeLocalServiceUtil;
import com.trantorinc.synergy.santa.web.dto.SantaDto;

import javax.portlet.ActionRequest;
import java.io.File;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.trantorinc.synergy.common.service.constant.AppConstantService.*;
import static com.trantorinc.synergy.common.service.util.UtilService.*;
import static com.trantorinc.synergy.santa.web.constants.SantaWebPortletKeys.*;

public class SantaServiceImpl implements SantaService {

	@Override
	public SantaDto getRegistrationModel(Employee employeeData, List<Santa> employeeNominations, GameTimeline santaTimeline) {
		SantaDto registrationModel = new SantaDto();
		registrationModel.setEmployeeCode(employeeData.getEcode());
		registrationModel.setEmployeeName(employeeData.getName());
		registrationModel.setAccount(employeeData.getAccount());

		registrationModel.setStates(INDIA_STATE);
		if(employeeNominations.isEmpty()){
			registrationModel.setPageMode(MODE_EDIT);
			registrationModel.setEmployeeMobile(employeeData.getMobile());
		} else {
			registrationModel.setEmployeeMobile(employeeNominations.get(0).getMobile());
			registrationModel.setCityName(employeeNominations.get(0).getCity());
			registrationModel.setStateName(employeeNominations.get(0).getState());
			registrationModel.setPincode(employeeNominations.get(0).getPincode());
			registrationModel.setPostalAddress(employeeNominations.get(0).getPostalAddress());
			registrationModel.setPageMode(MODE_VIEW);
		}

		LocalDateTime freezeDate = convertDateToLocalDateTime(santaTimeline.getFreezeDate());
		LocalDateTime gameEndDate = convertDateToLocalDateTime(santaTimeline.getCloseDate()).minusDays(1);
		LocalDateTime gameDate = convertDateToLocalDateTime(santaTimeline.getActionDate());
		LocalDateTime registrationEndDate = freezeDate.minusDays(1);
		LocalDateTime giftDispatchDate = freezeDate.plusDays(10);
		LocalDateTime allocationEndDate = gameDate.minusDays(1);
		registrationModel.setAllocationDate(freezeDate.format(FORMATTER_DD_MMM_YYYY));
		registrationModel.setAllocationEndDate(allocationEndDate.format(FORMATTER_DD_MMM_YYYY));
		registrationModel.setGameDate(gameDate.format(FORMATTER_DD_MMM_YYYY));
		registrationModel.setGameEndDate(gameEndDate.format(FORMATTER_DD_MMM_YYYY));
		registrationModel.setRegistrationEndDate(registrationEndDate.format(FORMATTER_DD_MMM_YYYY));
		registrationModel.setGiftDispatchDate(giftDispatchDate.format(FORMATTER_DD_MMM_YYYY));
		return  registrationModel;
	}

	@Override
	public SantaDto getAllocationModel(Employee employeeData, List<Santa> employeeNominations, GameTimeline santaTimeline) {
		SantaDto allocationModel = new SantaDto();
		Santa santaFor = employeeNominations.stream().filter(s -> s.getSantaEcode().equalsIgnoreCase(employeeData.getEcode())).collect(Collectors.toList()).get(0);
		Employee santaForEmployee = EmployeeLocalServiceUtil.fetchEmployee(santaFor.getEcode());
		Santa santa = employeeNominations.stream().filter(s -> s.getEcode().equalsIgnoreCase(employeeData.getEcode())).collect(Collectors.toList()).get(0);
		allocationModel.setEmployeeCode(employeeData.getEcode());
		allocationModel.setEmployeeMobile(santaFor.getMobile());
		allocationModel.setAccount(santaForEmployee.getAccount());
		allocationModel.setCityName(santaFor.getCity());
		allocationModel.setStateName(santaFor.getState());
		allocationModel.setPincode(santaFor.getPincode());
		allocationModel.setPostalAddress(santaFor.getPostalAddress());
		allocationModel.setPageMode(santa.isGiftSent()?MODE_VIEW:MODE_EDIT);
		allocationModel.setSecretSantaEcode(santaFor.getEcode());
		allocationModel.setSecretSantaName(santaForEmployee.getName());
		LocalDateTime freezeDate = convertDateToLocalDateTime(santaTimeline.getFreezeDate());
		LocalDateTime gameEndDate = convertDateToLocalDateTime(santaTimeline.getCloseDate()).minusDays(1);
		LocalDateTime gameDate = convertDateToLocalDateTime(santaTimeline.getActionDate());
		LocalDateTime registrationEndDate = freezeDate.minusDays(1);
		LocalDateTime giftDispatchDate = freezeDate.plusDays(10);
		LocalDateTime allocationEndDate = gameDate.minusDays(1);
		allocationModel.setAllocationDate(freezeDate.format(FORMATTER_DD_MMM_YYYY));
		allocationModel.setAllocationEndDate(allocationEndDate.format(FORMATTER_DD_MMM_YYYY));
		allocationModel.setGameDate(gameDate.format(FORMATTER_DD_MMM_YYYY));
		allocationModel.setGameEndDate(gameEndDate.format(FORMATTER_DD_MMM_YYYY));
		allocationModel.setRegistrationEndDate(registrationEndDate.format(FORMATTER_DD_MMM_YYYY));
		allocationModel.setGiftDispatchDate(giftDispatchDate.format(FORMATTER_DD_MMM_YYYY));
		return allocationModel;
	}
	@Override
	public SantaDto getGameModel(Santa nomination, GameTimeline santaTimeline) {
		List<Employee> empList = EmployeeLocalServiceUtil.findAllActiveEmployees();
		Collections.shuffle(empList);
		List<Employee> userSecretSantaOptions= new ArrayList<>();
		userSecretSantaOptions.addAll(empList.stream().filter(n->!n.getEcode().equalsIgnoreCase(nomination.getEcode())).filter(n->!n.getEcode().equalsIgnoreCase(nomination.getSantaEcode())).limit(9)
				.collect(Collectors.toList()));
		userSecretSantaOptions.add(empList.stream().filter(n->n.getEcode().equalsIgnoreCase(nomination.getSantaEcode())).limit(1)
				.collect(Collectors.toList()).get(0));
		Collections.shuffle(userSecretSantaOptions);
		SantaDto gameModel = new SantaDto();
		gameModel.setSantaOptions(userSecretSantaOptions);

		LocalDateTime freezeDate = convertDateToLocalDateTime(santaTimeline.getFreezeDate());
		LocalDateTime gameEndDate = convertDateToLocalDateTime(santaTimeline.getCloseDate()).minusDays(1);
		LocalDateTime gameDate = convertDateToLocalDateTime(santaTimeline.getActionDate());
		LocalDateTime registrationEndDate = freezeDate.minusDays(1);
		LocalDateTime giftDispatchDate = freezeDate.plusDays(10);
		LocalDateTime allocationEndDate = gameDate.minusDays(1);
		gameModel.setAllocationDate(freezeDate.format(FORMATTER_DD_MMM_YYYY));
		gameModel.setAllocationEndDate(allocationEndDate.format(FORMATTER_DD_MMM_YYYY));
		gameModel.setGameDate(gameDate.format(FORMATTER_DD_MMM_YYYY));
		gameModel.setGameEndDate(gameEndDate.format(FORMATTER_DD_MMM_YYYY));
		gameModel.setRegistrationEndDate(registrationEndDate.format(FORMATTER_DD_MMM_YYYY));
		gameModel.setGiftDispatchDate(giftDispatchDate.format(FORMATTER_DD_MMM_YYYY));
		return gameModel;
	}

	@Override
	public SantaDto getResultModel(Santa santa, GameTimeline santaTimeline) {
		SantaDto resultModel = new SantaDto();
		String username = santa.getSantaEcode();
		Employee employee = EmployeeLocalServiceUtil.fetchEmployee(username);
		if(employee != null){
			username = employee.getName();
		}
		String base64Encoded = BLANK;
		if(santa.getFileId() != null && !santa.getFileId().equalsIgnoreCase(BLANK)){
			base64Encoded = getBase64File(DriveService.getFile(santa.getFileId(), String.valueOf(santa.getPrimaryKey()),JPG));
		}
		resultModel.setEmployeeGiftPic(base64Encoded);
		resultModel.setSecretSantaName(username);
		resultModel.setEmailSent(santa.isEmailSent());


		if(santa.getGuessedEcode() != null && !santa.getGuessedEcode().isEmpty() ){
			resultModel.setGuessedCorrect(santa.getSantaEcode().equalsIgnoreCase(santa.getGuessedEcode()));
		}

		LocalDateTime freezeDate = convertDateToLocalDateTime(santaTimeline.getFreezeDate());
		LocalDateTime gameEndDate = convertDateToLocalDateTime(santaTimeline.getCloseDate()).minusDays(1);
		LocalDateTime gameDate = convertDateToLocalDateTime(santaTimeline.getActionDate());
		LocalDateTime registrationEndDate = freezeDate.minusDays(1);
		LocalDateTime giftDispatchDate = freezeDate.plusDays(10);
		LocalDateTime allocationEndDate = gameDate.minusDays(1);
		resultModel.setAllocationDate(freezeDate.format(FORMATTER_DD_MMM_YYYY));
		resultModel.setAllocationEndDate(allocationEndDate.format(FORMATTER_DD_MMM_YYYY));
		resultModel.setGameDate(gameDate.format(FORMATTER_DD_MMM_YYYY));
		resultModel.setGameEndDate(gameEndDate.format(FORMATTER_DD_MMM_YYYY));
		resultModel.setRegistrationEndDate(registrationEndDate.format(FORMATTER_DD_MMM_YYYY));
		resultModel.setGiftDispatchDate(giftDispatchDate.format(FORMATTER_DD_MMM_YYYY));

		if(getIstLocalDateTime().toLocalDate().isAfter(gameEndDate.toLocalDate())){
			resultModel.setEmailSent(true);
		}

		return resultModel;

	}

	@Override
	public void submitRegistration(ActionRequest request) {
		Santa santa = SantaLocalServiceUtil.createSanta(CounterLocalServiceUtil.increment());
		santa.setYear(getCurrentYear());
		Employee employee = EmployeeLocalServiceUtil.findByEmail(getLoggedUser(request));
		santa.setEcode(employee.getEcode());
		santa.setCity(ParamUtil.getString(request, "city"));
		santa.setState(ParamUtil.getString(request, "state"));
		santa.setPincode(ParamUtil.getString(request, "pincode"));
		santa.setMobile(ParamUtil.getString(request, "mobile"));
		santa.setPostalAddress(ParamUtil.getString(request, "postalAddress"));
		santa.setCreateDatetime(getIstDate());
		SantaLocalServiceUtil.addSanta(santa);
		GameTimeline santaTimeline = GameTimelineLocalServiceUtil.getCalibratedTimeline().stream().filter(g -> g.getName().equalsIgnoreCase("SANTA")).collect(Collectors.toList()).get(0);
		LocalDateTime freezeDate = convertDateToLocalDateTime(santaTimeline.getFreezeDate());
		LocalDateTime giftDispatchDate = freezeDate.plusDays(10);
		String gifTtDispatchDate = giftDispatchDate.format(FORMATTER_DD_MMM_YYYY);
		Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
		email.setSubject(SUBJECT_REGISTRATION);
		email.setBody(MessageFormat.format(BODY_REGISTRATION,employee.getName(),gifTtDispatchDate));
		email.setToAddress(employee.getEmail());
		email.setCreatedDate(getIstDate());
		email.setScheduled(false);
		email.setModule(MODULE_GAME);
		email.setSent(false);
		EmailLocalServiceUtil.addEmail(email);
	}

	@Override
	public void submitAllocation(String loggedUserEmail) {
		Employee employee = EmployeeLocalServiceUtil.findByEmail(loggedUserEmail);
		List<Santa> santas = SantaLocalServiceUtil.findSantaByEcodeAndYear(employee.getEcode(),getCurrentYear());
		if(!santas.isEmpty()){
			Santa santa = santas.get(0);
			santa.setGiftSent(true);
			SantaLocalServiceUtil.updateSanta(santa);
		}
	}

	@Override
	public void submitSendEmail(String loggedUserEmail) {
		Employee employee = EmployeeLocalServiceUtil.findByEmail(loggedUserEmail);
		List<Santa> santas = SantaLocalServiceUtil.findSantaByEcodeAndYear(employee.getEcode(),getCurrentYear());
		if(!santas.isEmpty()){
			Employee santaDetails= EmployeeLocalServiceUtil.fetchEmployee(santas.get(0).getSantaEcode());
			Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
			email.setSubject(SUBJECT_THANKYOU);
			email.setBody(MessageFormat.format(BODY_THANKYOU,santaDetails.getName(),employee.getName()));
			email.setToAddress(santaDetails.getEmail());
			email.setCcAddress(employee.getEmail());
			email.setCreatedDate(getIstDate());
			email.setScheduled(false);
			email.setModule(MODULE_GAME);
			email.setSent(false);
			EmailLocalServiceUtil.addEmail(email);
			Santa santa = santas.get(0);
			santa.setEmailSent(true);
			SantaLocalServiceUtil.updateSanta(santa);
		}
	}

	@Override
	public void submitGame(String loggedUserEmail, String guessedEcode, File photoImage) {
		Employee employee = EmployeeLocalServiceUtil.findByEmail(loggedUserEmail);
		List<Santa> santas = SantaLocalServiceUtil.findSantaByEcodeAndYear(employee.getEcode(),getCurrentYear());
		if(!santas.isEmpty()){
			Santa santa = santas.get(0);
			String folderId = DriveLocalServiceUtil.findFolderIdByFolderName(MODULE_GAME);
			String fileId = DriveService.uploadFile(folderId,santa.getPrimaryKey()+JPG, photoImage);
			santa.setFileId(fileId);
			santa.setGuessedEcode(guessedEcode);
			SantaLocalServiceUtil.updateSanta(santa);
		}
	}

}