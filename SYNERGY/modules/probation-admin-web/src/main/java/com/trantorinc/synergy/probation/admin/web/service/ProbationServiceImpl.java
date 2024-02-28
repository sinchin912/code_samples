package com.trantorinc.synergy.probation.admin.web.service;


import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.trantorinc.synergy.common.service.dto.ExcelDto;
import com.trantorinc.synergy.common.service.excel.ExcelService;
import com.trantorinc.synergy.email.core.model.Email;
import com.trantorinc.synergy.email.core.service.EmailLocalServiceUtil;
import com.trantorinc.synergy.lms.core.model.Employee;
import com.trantorinc.synergy.lms.core.service.EmployeeLocalServiceUtil;
import com.trantorinc.synergy.probation.admin.web.dto.ProbationDto;
import com.trantorinc.synergy.probation.admin.web.dto.ProbationLineDto;
import com.trantorinc.synergy.probation.core.model.Probation;
import com.trantorinc.synergy.probation.core.model.ProbationLine;
import com.trantorinc.synergy.probation.core.service.ProbationLineLocalServiceUtil;
import com.trantorinc.synergy.probation.core.service.ProbationLocalServiceUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.trantorinc.synergy.common.service.constant.AppConstantService.*;
import static com.trantorinc.synergy.common.service.util.UtilService.*;
import static com.trantorinc.synergy.probation.admin.web.constants.ProbationAdminWebPortletKeys.*;

public class ProbationServiceImpl implements ProbationService {

    private Map<Integer, String> statenameMap;

    @Override
    public List<ProbationDto> getHrPendingProbations(List<Probation> probations, List<Employee> employees) {
        List<ProbationDto> probationDtos = new ArrayList<>();
        List<Probation> pendingProbations = probations.stream().filter(p -> p.getState() == 0).collect(Collectors.toList());
        for(Probation probation : pendingProbations){
            ProbationDto probationDto = new ProbationDto();
            probationDto.setEmpCode(probation.getEcode());
            List<Employee> ecodeEmployees = employees.stream().filter(s -> s.getEcode().equalsIgnoreCase(probation.getEcode())).collect(Collectors.toList());
            if(!ecodeEmployees.isEmpty()){
                Employee employee = ecodeEmployees.get(0);
                probationDto.setEmpName(employee.getName());
                probationDto.setUpdateDate(convertDateToLocalDateTime(probation.getUpdateDate()).format(FORMATTER_YYYY_MM_DD));
                probationDto.setCreateDate(convertDateToLocalDateTime(probation.getCreateDate()).format(FORMATTER_YYYY_MM_DD));
                probationDto.setManagerEmail(probation.getManager());
                probationDto.setProbationStateName(probation.getStateName());
                probationDto.setProbationState(probation.getState());
                probationDtos.add(probationDto);
            }
        }
        return probationDtos;
    }

    @Override
    public List<ProbationDto> getHrCompleteProbations(List<Probation> probations, List<Employee> employees) {
        List<ProbationDto> probationDtos = new ArrayList<>();
        List<Probation> completeProbations = probations.stream().filter(p -> p.getState() != 0).collect(Collectors.toList());
        for(Probation probation : completeProbations){
            ProbationDto probationDto = new ProbationDto();
            probationDto.setEmpCode(probation.getEcode());
            List<Employee> ecodeEmployees = employees.stream().filter(s -> s.getEcode().equalsIgnoreCase(probation.getEcode())).collect(Collectors.toList());
            if(!ecodeEmployees.isEmpty()){
                Employee employee = ecodeEmployees.get(0);
                probationDto.setEmpName(employee.getName());
                probationDto.setUpdateDate(convertDateToLocalDateTime(probation.getUpdateDate()).format(FORMATTER_YYYY_MM_DD));
                probationDto.setCreateDate(convertDateToLocalDateTime(probation.getCreateDate()).format(FORMATTER_YYYY_MM_DD));
                probationDto.setManagerEmail(probation.getManager());
                probationDto.setProbationStateName(probation.getStateName());
                probationDto.setProbationState(probation.getState());
                probationDtos.add(probationDto);
            }
        }
        return probationDtos;
    }

    @Override
    public List<ProbationDto> getManagerProbations(List<Probation> probations, List<Employee> employees, boolean isManager, String loggedUser) {
        List<ProbationDto> probationDtos = new ArrayList<>();
        List<Probation> managerProbations = probations.stream().filter(s -> s.getReviewer().equalsIgnoreCase(loggedUser)).collect(Collectors.toList());
        if(isManager) {
            managerProbations.addAll(probations.stream().filter(s -> (s.getManager().equalsIgnoreCase(loggedUser) && !s.getReviewer().equalsIgnoreCase(loggedUser))).collect(Collectors.toList()));
        }
        for(Probation probation : managerProbations){
            ProbationDto probationDto = new ProbationDto();
            probationDto.setEmpCode(probation.getEcode());
            List<Employee> ecodeEmployees = employees.stream().filter(s -> s.getEcode().equalsIgnoreCase(probation.getEcode())).collect(Collectors.toList());
            if(!ecodeEmployees.isEmpty()){
                Employee employee = ecodeEmployees.get(0);
                probationDto.setEmpName(employee.getName());
                probationDto.setUpdateDate(convertDateToLocalDateTime(probation.getUpdateDate()).format(FORMATTER_YYYY_MM_DD));
                probationDto.setCreateDate(convertDateToLocalDateTime(probation.getCreateDate()).format(FORMATTER_YYYY_MM_DD));
                probationDto.setEmpDoj(convertDateToLocalDateTime(employee.getDoj()).format(FORMATTER_YYYY_MM_DD));
                probationDto.setManagerEmail(probation.getManager());
                probationDto.setProbationStateName(probation.getStateName());
                probationDto.setProbationState(probation.getState());
                probationDtos.add(probationDto);
            }
        }
        return probationDtos;
    }

    @Override
    public void abscondProbation(String abscondEmpId) {
        Employee employee = EmployeeLocalServiceUtil.fetchEmployee(abscondEmpId);
        Probation probation = ProbationLocalServiceUtil.fetchProbation(abscondEmpId);
        Employee manager = EmployeeLocalServiceUtil.findByEmail(probation.getManager());
        probation.setUpdateDate(getStartOfDayDate(getIstLocalDateTime().toLocalDate()));
        Map<Integer, String> statusMap = getStateNameMap();
        probation.setStateName(statusMap.get(5));
        probation.setState(5);
        ProbationLocalServiceUtil.updateProbation(probation);

        Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
        email.setBody(MessageFormat.format(BODY_CLOSED, manager.getName(), employee.getName()));
        email.setSubject(SUBJECT_CLOSED);
        email.setToAddress(probation.getManager());
        email.setCcAddress(DL_HR); //CC manager
        email.setModule(MODULE_PROBATION);
        email.setScheduled(false);
        email.setSent(false);
        email.setCreatedDate(getIstDate());
        EmailLocalServiceUtil.addEmail(email);
    }

    @Override
    public void saveProbation(ActionRequest request) {
        Map<Integer, String> statusMap = getStateNameMap();
        String probationId = ParamUtil.getString(request, "probationId");
        Employee employee= EmployeeLocalServiceUtil.fetchEmployee(probationId);
        int probationState = ParamUtil.getInteger(request, "probationState");
        Probation probation = ProbationLocalServiceUtil.fetchProbation(probationId);
        probation.setAreaOfImprovement(ParamUtil.getString(request, "areaOfImprovement"));
        probation.setAreaOfStrength(ParamUtil.getString(request, "areaOfStrength"));
        probation.setComment(ParamUtil.getString(request, "managerComment"));
        probation.setState(probationState);
        probation.setUpdateDate(getStartOfDayDate(getIstLocalDateTime().toLocalDate()));
        String probationStateName = statusMap.get(probationState);
        if (probationState == 3 || probationState == 4) {
            int numOfDays = ParamUtil.getInteger(request, "numOfDays");
            LocalDateTime now = getIstLocalDateTime();
            now = now.plusDays(numOfDays);
            probationStateName = probationStateName + now.format(FORMATTER_DD_MMM_YYYY);
            now = now.minusDays(10);
            probation.setAlertDate(getStartOfDayDate(now.toLocalDate()));
        }
        probation.setStateName(probationStateName);
        ProbationLocalServiceUtil.updateProbation(probation);
        List<ProbationLine> probationLines = ProbationLineLocalServiceUtil.findByProbationId(probationId);
        for(ProbationLine probationLine : probationLines){
            ProbationLineLocalServiceUtil.deleteProbationLine(probationLine);
        }
        for (int count = 1; count <= 25; count++) {
            int rating = ParamUtil.getInteger(request, "rating" + count);
            String comment = ParamUtil.getString(request, "comment" + count);
            ProbationLine probationLine = ProbationLineLocalServiceUtil.createProbationLine(CounterLocalServiceUtil.increment());
            probationLine.setLineNumber(count);
            probationLine.setRating(rating);
            probationLine.setComment(comment);
            probationLine.setProbationId(probationId);
            ProbationLineLocalServiceUtil.addProbationLine(probationLine);
        }
        Employee manager = EmployeeLocalServiceUtil.findByEmail(probation.getManager());

        Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
        email.setBody(MessageFormat.format(BODY_SUBMITTED, manager.getName(), employee.getName(), probation.getStateName()));
        email.setSubject(SUBJECT_SUBMITTED);
        email.setToAddress(probation.getManager());
        email.setModule(MODULE_PROBATION);
        email.setScheduled(false);
        email.setSent(false);
        email.setCreatedDate(getIstDate());
        EmailLocalServiceUtil.addEmail(email);
    }

    @Override
    public ProbationDto probationWorkflow(String ecode) {
        ProbationDto probationDto = new ProbationDto();
        Probation probation = ProbationLocalServiceUtil.fetchProbation(ecode);
        Employee employee = EmployeeLocalServiceUtil.fetchEmployee(ecode);
        if (employee != null) {
            probationDto.setEmpName(employee.getName());
            probationDto.setEmpDoj(convertDateToLocalDateTime(employee.getDoj()).format(FORMATTER_YYYY_MM_DD));
            probationDto.setEmpDesignation(employee.getDesignation());
            probationDto.setEmpAccount(employee.getAccount());
            probationDto.setEmpEmail(employee.getEmail());
        } else {
            probationDto.setEmpName(BLANK);
            probationDto.setEmpDoj(BLANK);
            probationDto.setEmpDesignation(BLANK);
            probationDto.setEmpAccount(BLANK);
            probationDto.setEmpEmail(BLANK);
        }
        Employee manager = EmployeeLocalServiceUtil.findByEmail(probation.getManager());
        probationDto.setEmpCode(ecode);

        probationDto.setManagerName(manager.getName());
        probationDto.setManagerEmail(manager.getEmail());
        String reviewer = probation.getReviewer();
        if (null  == reviewer || reviewer.trim().equalsIgnoreCase(BLANK)) {
            reviewer = manager.getName();
        } else {
            Employee reviewerEmployee = EmployeeLocalServiceUtil.findByEmail(reviewer);
            if(null != reviewerEmployee){
                reviewer = reviewerEmployee.getName();
            } else {
                reviewer = BLANK;
            }
        }
        probationDto.setReviewerName(reviewer);
        probationDto.setProbationState(probation.getState());
        probationDto.setProbationStateName(probation.getStateName());
        probationDto.setAreaOfImprovement(probation.getAreaOfImprovement());
        probationDto.setAreaOfStrength(probation.getAreaOfStrength());
        probationDto.setManagerComment(probation.getComment());

        List<ProbationLine> probationLines = ProbationLineLocalServiceUtil.findByProbationId(ecode);
        List<ProbationLineDto> probationLineDtos = new ArrayList<>();
        for (ProbationLine probationLine : probationLines) {
            ProbationLineDto probationLineDto = new ProbationLineDto();
            probationLineDto.setLineNumber(probationLine.getLineNumber());
            probationLineDto.setRating(probationLine.getRating());
            String ratingDescription;
            switch (probationLine.getRating()){
                case 1:
                    ratingDescription = "Very Poor";
                    break;
                case 2:
                    ratingDescription = "Poor";
                    break;
                case 3:
                    ratingDescription = "Good";
                    break;
                case 4:
                    ratingDescription = "Very Good";
                    break;
                case 5:
                    ratingDescription = "NA";
                    break;
                default:
                    ratingDescription = BLANK;
            }
            probationLineDto.setRatingDescription(ratingDescription);
            probationLineDto.setComment(probationLine.getComment());
            probationLineDtos.add(probationLineDto);
        }
        probationDto.setLines(probationLineDtos);
        return probationDto;
    }

    @Override
    public void exportProbation(boolean isCompleted, ResourceResponse response) throws IOException {
        List<ExcelDto> employeeExcelDto = new ArrayList<>();
        ExcelDto sheet = new ExcelDto();
        List<Probation> probationList;
        sheet.setSheetName("Probations");
        String filename ;
        if (isCompleted) {
            sheet.setHeaders(HEADERS_COMPLETED_EXPORT);
            probationList = ProbationLocalServiceUtil.findAllCompleted();
            filename = "TRANTOR_COMPLETED_PROBATIONS";
        } else {
            sheet.setHeaders(HEADERS_PENDING_EXPORT);
            probationList = ProbationLocalServiceUtil.findAllPending();
            filename = "TRANTOR_PENDING_PROBATIONS";
        }

        List<Employee> employees = EmployeeLocalServiceUtil.findAllEmployees();
        List<List<String>> data = new ArrayList<>();
        for(Probation probation : probationList){
            List<String> lineData = new ArrayList<>();
            lineData.add(probation.getEcode());
            List<Employee> ecodeEmployees = employees.stream().filter(e -> e.getEcode().equalsIgnoreCase(probation.getEcode())).collect(Collectors.toList());
            if(!ecodeEmployees.isEmpty()){
                lineData.add(ecodeEmployees.get(0).getName());
                lineData.add(ecodeEmployees.get(0).getAccount());
            } else {
                lineData.add(BLANK);
                lineData.add(BLANK);
            }
            lineData.add(probation.getManager());
            lineData.add(probation.getStateName());
            lineData.add(convertDateToLocalDateTime(probation.getCreateDate()).format(FORMATTER_YYYY_MM_DD));
            if (isCompleted) {
                lineData.add(convertDateToLocalDateTime(probation.getUpdateDate()).format(FORMATTER_YYYY_MM_DD));
                lineData.add(probation.getComment());
            }
            data.add(lineData);
        }
        sheet.setData(data);
        sheet.setSheetIndex(0);
        employeeExcelDto.add(sheet);
        ExcelService.createWorkBook(response,filename,employeeExcelDto);
    }

    private Map<Integer, String> getStateNameMap() {
        if (statenameMap == null) {
            Map<Integer, String> statusMap = new HashMap<>();
            statusMap.put(1, "Confirmed");
            statusMap.put(2, "Not Confirmed");
            statusMap.put(3, "Extended till ");
            statusMap.put(4, "Revised till ");
            statusMap.put(5, "Closed by HR");
            statusMap.put(0, "Pending");
            statenameMap = statusMap;
        }
        return statenameMap;
    }
}