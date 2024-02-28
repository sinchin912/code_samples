package com.trantorinc.synergy.performance.admin.web.dto;

public class KpiDto {
    private Long kpiId;
    private String ecode;
    private String employeeName;
    private String accountName;
    private String accountType;
    private String managerEmail;
    private String managerName;
    private String reviewerEmail;
    private String reviewerName;
    private String updateDate;
    private String kpiSettingStatusString;
    private boolean accountPrimary;
    private boolean kpiSettingStatus;
    private boolean overrideActionButtonEnable;

    public Long getKpiId() {
        return kpiId;
    }

    public void setKpiId(Long kpiId) {
        this.kpiId = kpiId;
    }

    public String getEcode() {
        return ecode;
    }

    public void setEcode(String ecode) {
        this.ecode = ecode;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
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

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getKpiSettingStatusString() {
        return kpiSettingStatusString;
    }

    public void setKpiSettingStatusString(String kpiSettingStatusString) {
        this.kpiSettingStatusString = kpiSettingStatusString;
    }

    public boolean isAccountPrimary() {
        return accountPrimary;
    }

    public void setAccountPrimary(boolean accountPrimary) {
        this.accountPrimary = accountPrimary;
    }

    public boolean isKpiSettingStatus() {
        return kpiSettingStatus;
    }

    public void setKpiSettingStatus(boolean kpiSettingStatus) {
        this.kpiSettingStatus = kpiSettingStatus;
    }

    public boolean isOverrideActionButtonEnable() {
        return overrideActionButtonEnable;
    }

    public void setOverrideActionButtonEnable(boolean overrideActionButtonEnable) {
        this.overrideActionButtonEnable = overrideActionButtonEnable;
    }
}
