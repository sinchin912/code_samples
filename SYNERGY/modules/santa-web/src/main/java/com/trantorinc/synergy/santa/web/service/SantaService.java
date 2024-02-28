package com.trantorinc.synergy.santa.web.service;


import com.trantorinc.synergy.game.core.model.GameTimeline;
import com.trantorinc.synergy.game.core.model.Santa;
import com.trantorinc.synergy.lms.core.model.Employee;
import com.trantorinc.synergy.santa.web.dto.SantaDto;

import javax.portlet.ActionRequest;
import java.io.File;
import java.util.List;

public interface SantaService {
	SantaDto getRegistrationModel(Employee employee, List<Santa> employeeNominations, GameTimeline santaTimeline);
	SantaDto getAllocationModel(Employee employee, List<Santa> employeeNominations, GameTimeline santaTimeline);
	SantaDto getGameModel(Santa santa, GameTimeline santaTimeline);
	SantaDto getResultModel(Santa santa, GameTimeline santaTimeline);
	void submitRegistration(ActionRequest request);
	void submitAllocation(String loggedUserEmail);
	void submitSendEmail(String loggedUserEmail);
	void submitGame(String loggedUserEmail, String guessedEcode, File photoImage);
}
