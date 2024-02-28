package com.trantorinc.synergy.probation.admin.web.dto;

import java.util.Date;
import java.util.List;

public class ProbationDto {
    private String empCode;
    private String empName;
    private String empDoj;
    private String empDesignation;
    private String empAccount;
    private String empEmail;
    private String managerEmail;
    private String managerName;
    private String reviewerName;
    private int probationState;
    private Date alertDate;
    private String createDate;
    private String updateDate;
    private String areaOfStrength;
    private String areaOfImprovement;
    private String managerComment;
    private List<ProbationLineDto> lines;
    private String probationStateName;

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpDoj() {
        return empDoj;
    }

    public void setEmpDoj(String empDoj) {
        this.empDoj = empDoj;
    }

    public String getEmpDesignation() {
        return empDesignation;
    }

    public void setEmpDesignation(String empDesignation) {
        this.empDesignation = empDesignation;
    }

    public String getEmpAccount() {
        return empAccount;
    }

    public void setEmpAccount(String empAccount) {
        this.empAccount = empAccount;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
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

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public int getProbationState() {
        return probationState;
    }

    public void setProbationState(int probationState) {
        this.probationState = probationState;
    }

    public Date getAlertDate() {
        return alertDate;
    }

    public void setAlertDate(Date alertDate) {
        this.alertDate = alertDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getAreaOfStrength() {
        return areaOfStrength;
    }

    public void setAreaOfStrength(String areaOfStrength) {
        this.areaOfStrength = areaOfStrength;
    }

    public String getAreaOfImprovement() {
        return areaOfImprovement;
    }

    public void setAreaOfImprovement(String areaOfImprovement) {
        this.areaOfImprovement = areaOfImprovement;
    }

    public String getManagerComment() {
        return managerComment;
    }

    public void setManagerComment(String managerComment) {
        this.managerComment = managerComment;
    }

    public List<ProbationLineDto> getLines() {
        return lines;
    }

    public void setLines(List<ProbationLineDto> lines) {
        this.lines = lines;
    }

    public String getProbationStateName() {
        return probationStateName;
    }

    public void setProbationStateName(String probationStateName) {
        this.probationStateName = probationStateName;
    }
}
