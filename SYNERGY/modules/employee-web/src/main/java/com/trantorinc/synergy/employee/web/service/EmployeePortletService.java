package com.trantorinc.synergy.employee.web.service;

import com.trantorinc.synergy.employee.web.dto.EmployeeDto;

import java.util.List;

public interface EmployeePortletService {

    List<EmployeeDto> findByEcodeOrName(String ecode,String name);
}
