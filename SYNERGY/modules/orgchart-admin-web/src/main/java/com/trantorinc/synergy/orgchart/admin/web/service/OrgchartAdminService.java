package com.trantorinc.synergy.orgchart.admin.web.service;

import com.trantorinc.synergy.orgchart.admin.web.dto.EmployeeDto;
import com.trantorinc.synergy.orgchart.admin.web.dto.OrgchartDto;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeSet;

public interface OrgchartAdminService {
    TreeSet<String> getAllAccounts();
    LinkedHashMap<String, String> getAllManagers();
    OrgchartDto getManagerOrgchart(String managerEcode);
    OrgchartDto getAccountOrgchart(String accountName);
    List<EmployeeDto> getAccountDirectory(String accountName);
}
