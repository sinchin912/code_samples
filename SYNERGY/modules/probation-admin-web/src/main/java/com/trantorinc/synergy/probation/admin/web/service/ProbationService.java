package com.trantorinc.synergy.probation.admin.web.service;


import com.trantorinc.synergy.lms.core.model.Employee;
import com.trantorinc.synergy.probation.admin.web.dto.ProbationDto;
import com.trantorinc.synergy.probation.core.model.Probation;

import javax.portlet.ActionRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;
import java.util.List;

public interface ProbationService {

    List<ProbationDto>  getHrPendingProbations(List<Probation> probations, List<Employee> employees);
    List<ProbationDto>  getHrCompleteProbations(List<Probation> probations, List<Employee> employees);
    List<ProbationDto> getManagerProbations(List<Probation> probations, List<Employee> employees, boolean isManager, String loggedUser);
    void abscondProbation(String abscondEmpId);
    void saveProbation(ActionRequest request);
    ProbationDto probationWorkflow(String ecode);
    void exportProbation(boolean isCompleted, ResourceResponse response) throws IOException;
}
