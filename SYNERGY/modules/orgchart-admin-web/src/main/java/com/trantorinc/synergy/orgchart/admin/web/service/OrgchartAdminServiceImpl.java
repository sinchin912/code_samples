package com.trantorinc.synergy.orgchart.admin.web.service;

import com.trantorinc.synergy.lms.core.model.Employee;
import com.trantorinc.synergy.lms.core.model.EmployeeModel;
import com.trantorinc.synergy.lms.core.service.EmployeeLocalServiceUtil;
import com.trantorinc.synergy.orgchart.admin.web.dto.EmployeeDto;
import com.trantorinc.synergy.orgchart.admin.web.dto.OrgchartDto;

import java.util.*;
import java.util.stream.Collectors;

import static com.trantorinc.synergy.common.service.constant.AppConstantService.BLANK;

public class OrgchartAdminServiceImpl implements OrgchartAdminService {

    @Override
    public TreeSet<String> getAllAccounts() {
        List<Employee> employees = EmployeeLocalServiceUtil.findAllActiveEmployees();
        Set<String> accounts = employees.stream().map(EmployeeModel::getAccount).collect(Collectors.toSet());
        accounts.remove(BLANK);
        return new TreeSet<>(accounts);
    }

    @Override
    public LinkedHashMap<String, String> getAllManagers() {
        List<Employee> employees = EmployeeLocalServiceUtil.findAllActiveEmployees();
        Set<String> managerEcodes = employees.stream().map(EmployeeModel::getManager).collect(Collectors.toSet());
        Map<String,String> managerMap = new HashMap<>();
        for (String ecode : managerEcodes) {
            List<Employee> managers =  employees.stream().filter(e -> e.getEcode().equalsIgnoreCase(ecode)).collect(Collectors.toList());
            if(!managers.isEmpty()) {
                Employee manager = managers.get(0);
                managerMap.put(manager.getEcode(),manager.getName());
            }
        }
       return managerMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    @Override
    public OrgchartDto getManagerOrgchart(String managerEcode) {
        List<Employee> employees = EmployeeLocalServiceUtil.findAllActiveEmployees();
        OrgchartDto orgchart = new OrgchartDto();
        if(BLANK.equalsIgnoreCase(managerEcode)){
            orgchart.setName("Org Chart");
            orgchart.setTitle("Sachin");
        } else {
            Employee managerDetails = employees.stream().filter(e -> e.getEcode().equalsIgnoreCase(managerEcode)).collect(Collectors.toList()).get(0);
            orgchart.setName(managerDetails.getName());
            orgchart.setTitle(managerDetails.getDesignation());
            orgchart.setClassName("leader");
            List<Employee> allManagerReporter = employees.stream().filter(e->e.getManager().equalsIgnoreCase(managerEcode)).collect(Collectors.toList());
            TreeSet<String> accounts = allManagerReporter.stream().sorted().map(EmployeeModel::getAccount).collect(Collectors.toCollection(TreeSet::new));
            List<OrgchartDto> managerChildren = new ArrayList<>();
            for (String account : accounts) {
                managerChildren.add(getManagerMap(account,allManagerReporter.stream().filter(e->e.getAccount().equalsIgnoreCase(account)).collect(Collectors.toList())));
            }
            orgchart.setChildren(managerChildren);
        }
        return orgchart;
    }

    @Override
    public OrgchartDto getAccountOrgchart(String accountName) {
        List<Employee> employees = EmployeeLocalServiceUtil.findAllActiveEmployees();
        OrgchartDto orgchart = new OrgchartDto();
        if(BLANK.equalsIgnoreCase(accountName)){
            orgchart.setName("Org Chart");
            orgchart.setTitle("Sachin");
        } else {
            List<Employee> projectEmployees = employees.stream().filter(e -> e.getAccount().equalsIgnoreCase(accountName)).collect(Collectors.toList());
            Set<String> allManagerEcodes = projectEmployees.stream().map(Employee::getManager).collect(Collectors.toSet());
            Map<Employee, OrgchartDto> managersDataMap = new HashMap<>();
            for(String managerEcode : allManagerEcodes){
                Employee managerData =  employees.stream().filter(e -> e.getEcode().equalsIgnoreCase(managerEcode)).collect(Collectors.toList()).get(0);
                managersDataMap.put(managerData,getAccountMap(managerData,projectEmployees));
            }
            Set<String> managerOfManager = managersDataMap.keySet().stream().map(Employee::getManager).collect(Collectors.toSet());
            Set<String> managersWithNoMapping=managersDataMap.keySet().stream().map(Employee::getEcode).collect(Collectors.toSet());
            managerOfManager.retainAll(allManagerEcodes);
            managersWithNoMapping.removeAll(managerOfManager);
            Set<String> projectEcodes = projectEmployees.stream().map(Employee::getEcode).collect(Collectors.toSet());
            managerOfManager.removeAll(projectEcodes);

            if(managerOfManager.isEmpty()){
                orgchart.setName(accountName);
                orgchart.setTitle("ORG CHART");
                orgchart.setClassName("project");
                List<OrgchartDto> leaderList = new ArrayList<>();
                List<OrgchartDto> projectManagersMap = new ArrayList<>();
                OrgchartDto projectLeadMap = new OrgchartDto();
                projectLeadMap.setName("Rajat Julka (E00061)");
                projectLeadMap.setTitle("Vice President - India Delivery Center ");
                projectLeadMap.setClassName("leader");
                for(String ecode : allManagerEcodes){
                    Employee managerData = employees.stream().filter(e -> e.getEcode().equalsIgnoreCase(ecode)).collect(Collectors.toList()).get(0);
                    projectManagersMap.add(getAccountMap(managerData,projectEmployees));
                }
                projectLeadMap.setChildren(projectManagersMap);
                leaderList.add(projectLeadMap);
                orgchart.setChildren(leaderList);
            } else {
                orgchart.setName(accountName);
                orgchart.setTitle("ORG CHART");
                orgchart.setClassName("project");
                List<OrgchartDto> leaderList = new ArrayList<>();
                for(String leaderEcode : managerOfManager){
                    allManagerEcodes.remove(leaderEcode);
                    OrgchartDto projectLeadMap = new OrgchartDto();
                    Employee leaderData = employees.stream().filter(e -> e.getEcode().equalsIgnoreCase(leaderEcode)).collect(Collectors.toList()).get(0);
                    projectLeadMap.setName(leaderData.getName()+" ("+leaderData.getEcode()+")");
                    projectLeadMap.setTitle(leaderData.getDesignation());
                    projectLeadMap.setClassName("leader");
                    List<Employee> leaderReportee =  managersDataMap.keySet().stream().filter(e -> e.getManager().equalsIgnoreCase(leaderEcode)).filter(e -> ! e.getEcode().equalsIgnoreCase(leaderEcode)).collect(Collectors.toList());
                    managersWithNoMapping.removeAll(leaderReportee.stream().map(EmployeeModel::getEcode).collect(Collectors.toList()));
                    List<OrgchartDto> projectManagersMap = new ArrayList<>();
                    for(Employee managerLeader : leaderReportee){
                        allManagerEcodes.remove(managerLeader.getEcode());
                        projectManagersMap.add(getAccountMap(managerLeader,projectEmployees));
                    }
                    List<Employee> leaderReportee1 =  projectEmployees.stream().filter(e -> ! leaderReportee.contains(e)).filter(e -> e.getManager().equalsIgnoreCase(leaderEcode)).collect(Collectors.toList());
                    for(Employee managerLeader : leaderReportee1){
                        OrgchartDto leaderDirectReporteeButNotManager = new OrgchartDto();
                        leaderDirectReporteeButNotManager.setName(managerLeader.getName()+ " ("+managerLeader.getEcode()+")");
                        leaderDirectReporteeButNotManager.setTitle(managerLeader.getDesignation()+"<span>"+managerLeader.getProject()+"</span>");
                        leaderDirectReporteeButNotManager.setClassName("employee");
                        projectManagersMap.add(leaderDirectReporteeButNotManager);
                    }
                    projectLeadMap.setChildren(projectManagersMap);
                    leaderList.add(projectLeadMap);
                }
                for(String managersWithNoMappingEcode : managersWithNoMapping){
                    allManagerEcodes.remove(managersWithNoMappingEcode);
                    Employee managerData = employees.stream().filter(e -> e.getEcode().equalsIgnoreCase(managersWithNoMappingEcode)).collect(Collectors.toList()).get(0);
                    leaderList.add(getAccountMap(managerData,projectEmployees));
                }
                orgchart.setChildren(leaderList);
            }
        }
        return orgchart;
    }

    @Override
    public List<EmployeeDto> getAccountDirectory(String accountName) {
        List<Employee> employees = EmployeeLocalServiceUtil.findAllActiveEmployees();
        List<Employee> accountEmployees = employees.stream().filter(e -> e.getAccount().equalsIgnoreCase(accountName)).collect(Collectors.toList());
        List<EmployeeDto> employeeList = new ArrayList<>();
        for(Employee employee : accountEmployees){
            employeeList.add(new EmployeeDto(employee.getEcode(), employee.getName(), employee.getEmail(),employee.getDesignation(),
                    employees.stream().filter(e -> e.getEcode().equalsIgnoreCase(employee.getManager())).collect(Collectors.toList()).get(0).getName(),
                    employees.stream().filter(e -> e.getEcode().equalsIgnoreCase(employee.getReviewer())).collect(Collectors.toList()).get(0).getName(),employee.getProject()));
        }
        return employeeList;
    }

    private OrgchartDto getManagerMap(String projectName,List<Employee> projectEmployees){
        OrgchartDto project = new OrgchartDto();
        project.setName(projectName);
        project.setTitle("Account");
        project.setClassName("manager");
        List<OrgchartDto> projectChildren = new ArrayList<>();
        for(Employee projectEmployee : projectEmployees){
            OrgchartDto employee = new OrgchartDto();
            employee.setClassName("employee");
            employee.setName(projectEmployee.getName()+ " ("+projectEmployee.getEcode()+")");
            employee.setTitle(projectEmployee.getDesignation());
            projectChildren.add(employee);
        }
        project.setChildren(projectChildren);
        return project;
    }

    private OrgchartDto getAccountMap(Employee managerData, List<Employee> projectEmployees) {
        OrgchartDto manager = new OrgchartDto();
        manager.setName(managerData.getName()+" ("+managerData.getEcode()+")");
        manager.setClassName("manager");
        manager.setTitle(managerData.getDesignation()+"<span>"+managerData.getProject()+"</span>");
        List<OrgchartDto> managerChildren = new ArrayList<>();
        List<Employee> managerReporters = projectEmployees.stream().filter(e -> e.getManager().equalsIgnoreCase(managerData.getEcode())).collect(Collectors.toList());
        for(Employee managerReporter : managerReporters){
            OrgchartDto reviewer = new OrgchartDto();
            reviewer.setClassName("employee");
            reviewer.setName(managerReporter.getName()+ " ("+managerReporter.getEcode()+")");
            reviewer.setTitle(managerReporter.getDesignation()+"<span>"+managerReporter.getProject()+"</span>");
            managerChildren.add(reviewer);
        }
        manager.setChildren(managerChildren);
        return  manager;
    }
}
