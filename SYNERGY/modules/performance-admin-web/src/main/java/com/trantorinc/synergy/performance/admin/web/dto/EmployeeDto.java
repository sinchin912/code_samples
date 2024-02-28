package com.trantorinc.synergy.performance.admin.web.dto;

public class EmployeeDto {
    private String employeeCode;
    private String employeeName;
    private String employeeEmail;
    private String employeeDesignation;
    private String employeeBand;
    private String managerEcode;
    private String managerEmail;
    private String reviewerEmail;
    private String managerName;
    private String reviewerName;
    private String account;

    private String overview;
    private  boolean status;

    public  EmployeeDto(){

    }

    public EmployeeDto(String employeeCode, String employeeName,String account, String designation, String managerName,String managerEmail) {
        this.employeeCode = employeeCode;
        this.employeeName = employeeName;
        this.managerName = managerName;
        this.account = account;
        this.employeeDesignation = designation;
        this.managerEmail = managerEmail;
    }
    public EmployeeDto(String employeeCode, String employeeName,String account, String designation, String managerName,String managerEmail, String reviewerName,String reviewerEmail,boolean status) {
        this.employeeCode = employeeCode;
        this.employeeName = employeeName;
        this.managerName = managerName;
        this.account = account;
        this.employeeDesignation = designation;
        this.managerEmail = managerEmail;
        this.reviewerName=reviewerName;
        this.reviewerEmail=reviewerEmail;
        this.status = status;
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

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeeDesignation() {
        return employeeDesignation;
    }

    public void setEmployeeDesignation(String employeeDesignation) {
        this.employeeDesignation = employeeDesignation;
    }

    public String getEmployeeBand() {
        return employeeBand;
    }

    public void setEmployeeBand(String employeeBand) {
        this.employeeBand = employeeBand;
    }

    public String getManagerEcode() {
        return managerEcode;
    }

    public void setManagerEcode(String managerEcode) {
        this.managerEcode = managerEcode;
    }

    public String getManagerEmail() {
        return managerEmail;
    }

    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
    }

    public String getReviewerEmail() {
        return reviewerEmail;
    }

    public void setReviewerEmail(String reviewerEmail) {
        this.reviewerEmail = reviewerEmail;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
