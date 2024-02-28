package com.trantorinc.synergy.common.service.dto;

import java.time.LocalDate;
import java.util.Date;

public class LdapDto {

    private String employeeFirstName;
    private String employeeLastName;
    private String employeeId;
    private String employeeName;
    private String employeeEmail;
    private String employeeMobile;
    private String employeeDesignation;
    private String employeeLocation;
    private String employeeProject;
    private String employeeSkype;
    private LocalDate employeeJoiningDate;
    private String employeeManagerName;

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeeMobile() {
        return employeeMobile;
    }

    public void setEmployeeMobile(String employeeMobile) {
        this.employeeMobile = employeeMobile;
    }

    public String getEmployeeDesignation() {
        return employeeDesignation;
    }

    public void setEmployeeDesignation(String employeeDesignation) {
        this.employeeDesignation = employeeDesignation;
    }

    public String getEmployeeLocation() {
        return employeeLocation;
    }

    public void setEmployeeLocation(String employeeLocation) {
        this.employeeLocation = employeeLocation;
    }

    public String getEmployeeProject() {
        return employeeProject;
    }

    public void setEmployeeProject(String employeeProject) {
        this.employeeProject = employeeProject;
    }

    public String getEmployeeSkype() {
        return employeeSkype;
    }

    public void setEmployeeSkype(String employeeSkype) {
        this.employeeSkype = employeeSkype;
    }

    public LocalDate getEmployeeJoiningDate() {
        return employeeJoiningDate;
    }

    public void setEmployeeJoiningDate(LocalDate employeeJoiningDate) {
        this.employeeJoiningDate = employeeJoiningDate;
    }

    public String getEmployeeManagerName() {
        return employeeManagerName;
    }

    public void setEmployeeManagerName(String employeeManagerName) {
        this.employeeManagerName = employeeManagerName;
    }
}
