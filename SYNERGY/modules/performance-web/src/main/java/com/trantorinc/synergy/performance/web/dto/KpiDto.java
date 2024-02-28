package com.trantorinc.synergy.performance.web.dto;

import java.util.List;

public class KpiDto {
    private Long kpiId;
    private String ecode;
    private String account;
    private String managerName;
    private String managerEmail;
    private String updatedDate;
    private String reviewerName;
    private String reviewerEmail;
    private boolean accountPrimary;
    private boolean kpiSettingStatus ;
    private boolean generateReviewButtonDisplay ;


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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerEmail() {
        return managerEmail;
    }

    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public String getReviewerEmail() {
        return reviewerEmail;
    }

    public void setReviewerEmail(String reviewerEmail) {
        this.reviewerEmail = reviewerEmail;
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

    public boolean isGenerateReviewButtonDisplay() {
        return generateReviewButtonDisplay;
    }

    public void setGenerateReviewButtonDisplay(boolean generateReviewButtonDisplay) {
        this.generateReviewButtonDisplay = generateReviewButtonDisplay;
    }
}
