package com.trantorinc.synergy.expense.web.dto;

import java.time.LocalDate;
import java.util.Map;

public class ExpenseDto {
    private Long expenseId;
    private String createdDate;
    private String ecode;
    private String name;
    private String band;
    private String account;
    private String mobile;
    private String location;
    private String approvingManager;
    private String expenseType;
    private String spouseName;
    private String dobSpouse;
    private String marriageDate;
    private String selectGender;
    private String babyName;
    private String dobBaby;
    private String selectBabyGender;
    private LocalDate fyStartDate;
    private LocalDate fyEndDate;
    private Map<String,String> managers;
    private Long totalBillAmount;
    private String status;
    private Integer statusInt;
    private String assignee;
    private String fileId;
    private Boolean viewMode;
    private Boolean showApprovingManagerField;
    private String financeComment;
    private String managerComment;
    private Boolean managerFinanceSection;

    public Long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Long expenseId) {
        this.expenseId = expenseId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getEcode() {
        return ecode;
    }

    public void setEcode(String ecode) {
        this.ecode = ecode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getApprovingManager() {
        return approvingManager;
    }

    public void setApprovingManager(String approvingManager) {
        this.approvingManager = approvingManager;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public String getSpouseName() {
        return spouseName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    public String getDobSpouse() {
        return dobSpouse;
    }

    public void setDobSpouse(String dobSpouse) {
        this.dobSpouse = dobSpouse;
    }

    public String getMarriageDate() {
        return marriageDate;
    }

    public void setMarriageDate(String marriageDate) {
        this.marriageDate = marriageDate;
    }

    public String getSelectGender() {
        return selectGender;
    }

    public void setSelectGender(String selectGender) {
        this.selectGender = selectGender;
    }

    public String getBabyName() {
        return babyName;
    }

    public void setBabyName(String babyName) {
        this.babyName = babyName;
    }

    public String getDobBaby() {
        return dobBaby;
    }

    public void setDobBaby(String dobBaby) {
        this.dobBaby = dobBaby;
    }

    public String getSelectBabyGender() {
        return selectBabyGender;
    }

    public void setSelectBabyGender(String selectBabyGender) {
        this.selectBabyGender = selectBabyGender;
    }

    public LocalDate getFyStartDate() {
        return fyStartDate;
    }

    public void setFyStartDate(LocalDate fyStartDate) {
        this.fyStartDate = fyStartDate;
    }

    public LocalDate getFyEndDate() {
        return fyEndDate;
    }

    public void setFyEndDate(LocalDate fyEndDate) {
        this.fyEndDate = fyEndDate;
    }

    public Map<String, String> getManagers() {
        return managers;
    }

    public void setManagers(Map<String, String> managers) {
        this.managers = managers;
    }

    public Long getTotalBillAmount() {
        return totalBillAmount;
    }

    public void setTotalBillAmount(Long totalBillAmount) {
        this.totalBillAmount = totalBillAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getStatusInt() {
        return statusInt;
    }

    public void setStatusInt(Integer statusInt) {
        this.statusInt = statusInt;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public Boolean getViewMode() {
        return viewMode;
    }

    public void setViewMode(Boolean viewMode) {
        this.viewMode = viewMode;
    }

    public Boolean getShowApprovingManagerField() {
        return showApprovingManagerField;
    }

    public void setShowApprovingManagerField(Boolean showApprovingManagerField) {
        this.showApprovingManagerField = showApprovingManagerField;
    }

    public String getFinanceComment() {
        return financeComment;
    }

    public void setFinanceComment(String financeComment) {
        this.financeComment = financeComment;
    }

    public String getManagerComment() {
        return managerComment;
    }

    public void setManagerComment(String managerComment) {
        this.managerComment = managerComment;
    }

    public Boolean getManagerFinanceSection() {
        return managerFinanceSection;
    }

    public void setManagerFinanceSection(Boolean managerFinanceSection) {
        this.managerFinanceSection = managerFinanceSection;
    }
}
