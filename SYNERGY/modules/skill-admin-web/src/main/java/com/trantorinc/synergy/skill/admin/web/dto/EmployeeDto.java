package com.trantorinc.synergy.skill.admin.web.dto;

public class EmployeeDto {
    private String empEcode;
    private String empName;
    private String empEmail;
    private String empDesignation;
    private String empBand;
    private String managerEmail;
    private String managerName;
    private String account;
    private String managerEcode;
    private String overview;
    private String reviewerEmail;
    private String reviewerName;
    private String empMobileNo;
    private String mode;
    private  String comment;

    public EmployeeDto(String empEcode,String empEmail , String empName, String empDesignation, String managerName, String account, String reviewerName) {
        this.empEcode = empEcode;
        this.empEmail = empEmail;
        this.empName = empName;
        this.empDesignation = empDesignation;
        this.managerName = managerName;
        this.account = account;
        this.reviewerName = reviewerName;
    }

    public EmployeeDto() {

    }

    public String getEmpEcode() {
        return empEcode;
    }

    public void setEmpEcode(String empEcode) {
        this.empEcode = empEcode;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public String getEmpDesignation() {
        return empDesignation;
    }

    public void setEmpDesignation(String empDesignation) {
        this.empDesignation = empDesignation;
    }

    public String getEmpBand() {
        return empBand;
    }

    public void setEmpBand(String empBand) {
        this.empBand = empBand;
    }

    public String getManagerEmail() {
        return managerEmail;
    }

    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getManagerEcode() {
        return managerEcode;
    }

    public void setManagerEcode(String managerEcode) {
        this.managerEcode = managerEcode;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReviewerEmail() {
        return reviewerEmail;
    }

    public void setReviewerEmail(String reviewerEmail) {
        this.reviewerEmail = reviewerEmail;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public String getEmpMobileNo() {
        return empMobileNo;
    }

    public void setEmpMobileNo(String empMobileNo) {
        this.empMobileNo = empMobileNo;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


}
