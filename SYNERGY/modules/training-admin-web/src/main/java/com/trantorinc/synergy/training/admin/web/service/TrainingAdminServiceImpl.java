package com.trantorinc.synergy.training.admin.web.service;

import com.trantorinc.synergy.common.service.dto.ExcelDto;
import com.trantorinc.synergy.common.service.excel.ExcelService;
import com.trantorinc.synergy.lms.core.model.Employee;
import com.trantorinc.synergy.lms.core.service.EmployeeLocalServiceUtil;

import javax.portlet.ResourceResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.trantorinc.synergy.common.service.constant.AppConstantService.*;
import static com.trantorinc.synergy.common.service.util.UtilService.convertDateToLocalDateTime;
import static com.trantorinc.synergy.training.admin.web.constants.TrainingAdminWebPortletKeys.HEADERS_EMPLOYEE_EXPORT;


public class TrainingAdminServiceImpl implements TrainingAdminService {

    @Override
    public void exportEmployee(ResourceResponse response) throws IOException {
        List<ExcelDto> employeeExcelDto = new ArrayList<>();
        ExcelDto sheet = new ExcelDto();
        sheet.setSheetName("All Active Employees");
        sheet.setHeaders(HEADERS_EMPLOYEE_EXPORT);

        List<Employee> employees = EmployeeLocalServiceUtil.findAllActiveEmployees();
        List<List<String>> data = new ArrayList<>();
        for(Employee employee : employees){
            List<String> lineData = new ArrayList<>();
            lineData.add(employee.getEcode());
            lineData.add(employee.getName());
            lineData.add(employee.getAccount());
            lineData.add(employee.getProject());
            lineData.add(employee.getDesignation());
            lineData.add(convertDateToLocalDateTime(employee.getDoj()).format(FORMATTER_DD_MMM_YYYY));
            lineData.add(employee.getExperience());
            lineData.add(employee.getSkill());
            lineData.add(employee.getEmployeeType());
            lineData.add(convertDateToLocalDateTime(employee.getDob()).format(FORMATTER_DD_MMM_YYYY));
            lineData.add(employee.getBand());
            lineData.add(employee.getLocation());
            lineData.add(employee.getMobile());
            lineData.add(employee.getManager());
            List<Employee> managerEmployees = employees.stream().filter(e -> e.getEcode().equalsIgnoreCase(employee.getManager())).collect(Collectors.toList());
            if(!managerEmployees.isEmpty()){
                lineData.add(managerEmployees.get(0).getName());
            } else {
                lineData.add(BLANK);
            }
            lineData.add(employee.getReviewer());
            List<Employee> reviewerEmployees = employees.stream().filter(e -> e.getEcode().equalsIgnoreCase(employee.getReviewer())).collect(Collectors.toList());
            if(!reviewerEmployees.isEmpty()){
                lineData.add(reviewerEmployees.get(0).getName());
            } else {
                lineData.add(BLANK);
            }
            lineData.add(employee.getCoordinator());
            List<Employee> coordinatorEmployees = employees.stream().filter(e -> e.getEcode().equalsIgnoreCase(employee.getCoordinator())).collect(Collectors.toList());
            if(!coordinatorEmployees.isEmpty()){
                lineData.add(coordinatorEmployees.get(0).getName());
            } else {
                lineData.add(BLANK);
            }

            data.add(lineData);
        }
        sheet.setData(data);
        sheet.setSheetIndex(0);
        employeeExcelDto.add(sheet);
        ExcelService.createWorkBook(response,"TRANTOR_EMPLOYEES",employeeExcelDto);
    }
}
