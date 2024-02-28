package com.trantorinc.synergy.employee.web.service;

import com.trantorinc.synergy.employee.web.dto.EmployeeDto;
import com.trantorinc.synergy.lms.core.model.Employee;
import com.trantorinc.synergy.lms.core.service.EmployeeLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.trantorinc.synergy.employee.web.constants.EmployeeWebPortletKeys.*;
import static com.trantorinc.synergy.employee.web.constants.EmployeeWebPortletKeys.BLANK;

public class EmployeePortletServiceImpl implements EmployeePortletService{

    @Override
    public List<EmployeeDto> findByEcodeOrName(String ecode, String name) {
        List<EmployeeDto> finalEmployeeList = new ArrayList<>();
        if (!(name.equalsIgnoreCase(BLANK) && ecode.equalsIgnoreCase(BLANK))) {
            String managerName = BLANK;
            List<Employee> allEmployees = EmployeeLocalServiceUtil.findAllActiveEmployees();
            for (Employee employee : allEmployees) {
                if (employee.getSkype().equalsIgnoreCase(NULL) || employee.getSkype().equalsIgnoreCase(BLANK)) {
                    employee.setSkype(NA);
                }
                List<Employee> managerData = allEmployees.stream().filter(e -> e.getEcode().equalsIgnoreCase(employee.getManager().trim())).collect(Collectors.toList());
                if (!managerData.isEmpty()) {
                    managerName = managerData.get(0).getName();
                }
                EmployeeDto employeeDto = new EmployeeDto(employee.getEcode(), employee.getName(), employee.getAccount(), employee.getDesignation(), employee.getEmail(), employee.getSkype(), managerName, employee.isStatus());
                finalEmployeeList.add(employeeDto);
            }
            if (!name.equalsIgnoreCase(BLANK)) {
                finalEmployeeList = finalEmployeeList.stream().filter(s -> s.getName().toUpperCase().contains(name.toUpperCase())).collect(Collectors.toList());
            }
            if (!ecode.equalsIgnoreCase(BLANK)) {
                finalEmployeeList = finalEmployeeList.stream().filter(s -> s.getEcode().equalsIgnoreCase(ecode)).collect(Collectors.toList());
            }
        }
        return finalEmployeeList;
    }
}
