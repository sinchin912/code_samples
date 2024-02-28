package com.trantorinc.synergy.billing.web.dto;

import java.util.Date;
import java.util.List;

public class EmployeeDataDto {
    private String billingId;
    private String employeeCode;
    private String employeeName;
    private String managerName;
    private String managerEcode;
    private String leadName;
    private String leadEcode;
    private String coordinatorEcode;
    private String coordinatorName;
    private String employeeDesignation;
    private String doj;
    private String experience;
    private String skill;
    private String account;
    private String project;
    private String current;
    private String currentPlusOne;
    private String currentPlusTwo;
    private String shadowResourceType;
    private String shadowStartDate;
    private String benchStartDate;
    private String billingStartDate;
    private String billingEndDate;
    private String employeeStatus;
    private String lastWorkingDate;
    private boolean shared;
    private String percentUtilization;
    private String updatedBy;
    private boolean activeStatus; // Contains status value of employee data
    private List<SkillsetDto> skills;
    private List<CertificateDto> certificates;
    private String allocationStatus;
    private String remarks;
    private String billingStatus;
    private String billableHours;
    private String monthHours;
    private String employeeRole;
    private String vertical;
    private Date updatedDate;
    private boolean projectUpdated;
    public String getBillingId() {
        return billingId;
    }

    public void setBillingId(String billingId) {
        this.billingId = billingId;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerEcode() {
        return managerEcode;
    }

    public void setManagerEcode(String managerEcode) {
        this.managerEcode = managerEcode;
    }

    public String getEmployeeDesignation() {
        return employeeDesignation;
    }

    public void setEmployeeDesignation(String employeeDesignation) {
        this.employeeDesignation = employeeDesignation;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getLeadName() {
        return leadName;
    }

    public void setLeadName(String leadName) {
        this.leadName = leadName;
    }

    public String getLeadEcode() {
        return leadEcode;
    }

    public void setLeadEcode(String leadEcode) {
        this.leadEcode = leadEcode;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getCurrentPlusOne() {
        return currentPlusOne;
    }

    public void setCurrentPlusOne(String currentPlusOne) {
        this.currentPlusOne = currentPlusOne;
    }

    public String getCurrentPlusTwo() {
        return currentPlusTwo;
    }

    public void setCurrentPlusTwo(String currentPlusTwo) {
        this.currentPlusTwo = currentPlusTwo;
    }

    public String getShadowResourceType() {
        return shadowResourceType;
    }

    public void setShadowResourceType(String shadowResourceType) {
        this.shadowResourceType = shadowResourceType;
    }

    public String getShadowStartDate() {
        return shadowStartDate;
    }

    public void setShadowStartDate(String shadowStartDate) {
        this.shadowStartDate = shadowStartDate;
    }

    public String getBenchStartDate() {
        return benchStartDate;
    }

    public void setBenchStartDate(String benchStartDate) {
        this.benchStartDate = benchStartDate;
    }

    public String getBillingStartDate() {
        return billingStartDate;
    }

    public void setBillingStartDate(String billingStartDate) {
        this.billingStartDate = billingStartDate;
    }

    public String getBillingEndDate() {
        return billingEndDate;
    }

    public void setBillingEndDate(String billingEndDate) {
        this.billingEndDate = billingEndDate;
    }

    public String getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(String employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public String getLastWorkingDate() {
        return lastWorkingDate;
    }

    public void setLastWorkingDate(String lastWorkingDate) {
        this.lastWorkingDate = lastWorkingDate;
    }

    public boolean isShared() {
        return shared;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
    }

    public String getPercentUtilization() {
        return percentUtilization;
    }

    public void setPercentUtilization(String percentUtilization) {
        this.percentUtilization = percentUtilization;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public boolean isActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public List<SkillsetDto> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillsetDto> skills) {
        this.skills = skills;
    }

    public List<CertificateDto> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<CertificateDto> certificates) {
        this.certificates = certificates;
    }

    public String getCoordinatorEcode() {
        return coordinatorEcode;
    }

    public void setCoordinatorEcode(String coordinatorEcode) {
        this.coordinatorEcode = coordinatorEcode;
    }

    public String getCoordinatorName() {
        return coordinatorName;
    }

    public void setCoordinatorName(String coordinatorName) {
        this.coordinatorName = coordinatorName;
    }

    public String getAllocationStatus() {
        return allocationStatus;
    }

    public void setAllocationStatus(String allocationStatus) {
        this.allocationStatus = allocationStatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getBillingStatus() {
        return billingStatus;
    }

    public void setBillingStatus(String billingStatus) {
        this.billingStatus = billingStatus;
    }

    public String getBillableHours() {
        return billableHours;
    }

    public void setBillableHours(String billableHours) {
        this.billableHours = billableHours;
    }

    public String getMonthHours() {
        return monthHours;
    }

    public void setMonthHours(String monthHours) {
        this.monthHours = monthHours;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }

    public String getVertical() {
        return vertical;
    }

    public void setVertical(String vertical) {
        this.vertical = vertical;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public boolean isProjectUpdated() {
        return projectUpdated;
    }

    public void setProjectUpdated(boolean projectUpdated) {
        this.projectUpdated = projectUpdated;
    }
}
