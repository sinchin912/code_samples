package com.trantorinc.synergy.performance.admin.web.dto;

import java.util.List;
import java.util.Map;

public class ReviewDto {
    private Long reviewId;
    private String employeeCode;
    private String employeeName;
    private String employeeEmail;
    private String employeeDesignation;
    private String employeeBand;
    private String managerEmail;
    private String managerName;
    private String reviewerEmail;
    private String reviewerName;
    private String account;
    private Long kpiId;
    private Long rollbackId;
    private String rollbackStatus;
    private Long closeId;
    private String closeStatus;
    private String location;
    private String reviewStartDate;
    private String reviewEndDate;
    private int reviewState;
    private String reviewStateName;
    private String rating;
//    private int ratingState;
    private String trainingsReqd;
    private String comment;
    private String raiseToHrComment;
    private boolean currentYRReview;
    private boolean promotionReqd;
//    private String attachmentName;
    private String excelledArea;
    private String rsnForPromotion;
    private String rolePlayed;
    private int criticalToAcct;
    private int criticalToCompany;
//    private int keyEmp;
    private int replacement;
    private String replacementDetail;
    private String replacementReason;
    private String replacementDetailEscaped;
    private boolean replacementReasonOther;
    private String attributeText;
    private List<ReviewUploadDto> uploadsFiles;
    private boolean currentAccount;
    private String achievementsByEmp;
    private String overallEmployeeComment;
    private String overallManagerComment;
    private String overallHrComment;
    private String improvementComment;
    private List<ReviewLineDto> reviewLines;
    private List<OtherAccountDto> otherAccounts;
    private Map<Integer, String> progressMap ;
    private String reviewType;
    private String accountType;
    private String exceptedDesignation;
    private String exceptedSalary;
    private String readyAns;
//    private boolean twoYearOld;
    private boolean secondaryAssessed;
    private boolean assigned;
    private String assigneeEmail;
    private String assigneeName;
    private boolean managerViewStatus;
    private boolean adminRole;
    private boolean leadViewStatus;
//
    private Map<String,String> teamMembers;
    private boolean editMode;

    public boolean isEditMode() {
        return editMode;
    }

    public List<ReviewLineDto> getReviewLines() {
        return reviewLines;
    }

    public Long getKpiId() {
        return kpiId;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public boolean isSecondaryAssessed() {
        return secondaryAssessed;
    }

    public void setSecondaryAssessed(boolean secondaryAssessed) {
        this.secondaryAssessed = secondaryAssessed;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }

    public Map<String, String> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(Map<String, String> teamMembers) {
        this.teamMembers = teamMembers;
    }

    public boolean isAdminRole() {
        return adminRole;
    }

    public void setAdminRole(boolean adminRole) {
        this.adminRole = adminRole;
    }

    public Long getRollbackId() {
        return rollbackId;
    }

    public void setRollbackId(Long rollbackId) {
        this.rollbackId = rollbackId;
    }

    public Long getCloseId() {
        return closeId;
    }

    public void setCloseId(Long closeId) {
        this.closeId = closeId;
    }

    public String getRollbackStatus() {
        return rollbackStatus;
    }

    public void setRollbackStatus(String rollbackStatus) {
        this.rollbackStatus = rollbackStatus;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getReviewStartDate() {
        return reviewStartDate;
    }

    public void setReviewStartDate(String reviewStartDate) {
        this.reviewStartDate = reviewStartDate;
    }

    public String getCloseStatus() {
        return closeStatus;
    }

    public void setCloseStatus(String closeStatus) {
        this.closeStatus = closeStatus;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public String getExceptedDesignation() {
        return exceptedDesignation;
    }

    public void setExceptedDesignation(String exceptedDesignation) {
        this.exceptedDesignation = exceptedDesignation;
    }

    public String getExceptedSalary() {
        return exceptedSalary;
    }

    public List<ReviewUploadDto> getUploadsFiles() {
        return uploadsFiles;
    }

    public void setUploadsFiles(List<ReviewUploadDto> uploadsFiles) {
        this.uploadsFiles = uploadsFiles;
    }

    public void setExceptedSalary(String exceptedSalary) {
        this.exceptedSalary = exceptedSalary;
    }

    public String getReadyAns() {
        return readyAns;
    }

    public void setReadyAns(String readyAns) {
        this.readyAns = readyAns;
    }

    public String getTrainingsReqd() {
        return trainingsReqd;
    }

    public void setTrainingsReqd(String trainingsReqd) {
        this.trainingsReqd = trainingsReqd;
    }

    public String getComment() {
        return comment;
    }

    public boolean isPromotionReqd() {
        return promotionReqd;
    }

    public void setPromotionReqd(boolean promotionReqd) {
        this.promotionReqd = promotionReqd;
    }

    public String getExcelledArea() {
        return excelledArea;
    }

    public void setExcelledArea(String excelledArea) {
        this.excelledArea = excelledArea;
    }

    public String getRsnForPromotion() {
        return rsnForPromotion;
    }

    public void setRsnForPromotion(String rsnForPromotion) {
        this.rsnForPromotion = rsnForPromotion;
    }

    public String getRolePlayed() {
        return rolePlayed;
    }

    public void setRolePlayed(String rolePlayed) {
        this.rolePlayed = rolePlayed;
    }

    public int getCriticalToAcct() {
        return criticalToAcct;
    }

    public void setCriticalToAcct(int criticalToAcct) {
        this.criticalToAcct = criticalToAcct;
    }

    public int getCriticalToCompany() {
        return criticalToCompany;
    }

    public void setCriticalToCompany(int criticalToCompany) {
        this.criticalToCompany = criticalToCompany;
    }

    public int getReplacement() {
        return replacement;
    }

    public void setReplacement(int replacement) {
        this.replacement = replacement;
    }

    public String getReplacementDetail() {
        return replacementDetail;
    }

    public void setReplacementDetail(String replacementDetail) {
        this.replacementDetail = replacementDetail;
    }

    public String getReplacementReason() {
        return replacementReason;
    }

    public void setReplacementReason(String replacementReason) {
        this.replacementReason = replacementReason;
    }

    public String getReplacementDetailEscaped() {
        return replacementDetailEscaped;
    }

    public void setReplacementDetailEscaped(String replacementDetailEscaped) {
        this.replacementDetailEscaped = replacementDetailEscaped;
    }

    public boolean isReplacementReasonOther() {
        return replacementReasonOther;
    }

    public void setReplacementReasonOther(boolean replacementReasonOther) {
        this.replacementReasonOther = replacementReasonOther;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRaiseToHrComment() {
        return raiseToHrComment;
    }

    public void setRaiseToHrComment(String raiseToHrComment) {
        this.raiseToHrComment = raiseToHrComment;
    }

    public String getAchievementsByEmp() {
        return achievementsByEmp;
    }

    public void setAchievementsByEmp(String achievementsByEmp) {
        this.achievementsByEmp = achievementsByEmp;
    }

    public String getOverallEmployeeComment() {
        return overallEmployeeComment;
    }

    public List<OtherAccountDto> getOtherAccounts() {
        return otherAccounts;
    }

    public void setOtherAccounts(List<OtherAccountDto> otherAccounts) {
        this.otherAccounts = otherAccounts;
    }

    public void setOverallEmployeeComment(String overallEmployeeComment) {
        this.overallEmployeeComment = overallEmployeeComment;
    }

    public String getOverallManagerComment() {
        return overallManagerComment;
    }

    public void setOverallManagerComment(String overallManagerComment) {
        this.overallManagerComment = overallManagerComment;
    }

    public String getOverallHrComment() {
        return overallHrComment;
    }

    public void setOverallHrComment(String overallHrComment) {
        this.overallHrComment = overallHrComment;
    }

    public String getImprovementComment() {
        return improvementComment;
    }

    public void setImprovementComment(String improvementComment) {
        this.improvementComment = improvementComment;
    }

    public boolean isLeadViewStatus() {
        return leadViewStatus;
    }

    public void setLeadViewStatus(boolean leadViewStatus) {
        this.leadViewStatus = leadViewStatus;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public boolean isCurrentYRReview() {
        return currentYRReview;
    }

    public void setCurrentYRReview(boolean currentYRReview) {
        this.currentYRReview = currentYRReview;
    }

    public String getReviewEndDate() {
        return reviewEndDate;
    }

    public void setReviewEndDate(String reviewEndDate) {
        this.reviewEndDate = reviewEndDate;
    }

    public String getAssigneeEmail() {
        return assigneeEmail;
    }

    public void setAssigneeEmail(String assigneeEmail) {
        this.assigneeEmail = assigneeEmail;
    }

    public String getAssigneeName() {
        return assigneeName;
    }

    public void setAssigneeName(String assigneeName) {
        this.assigneeName = assigneeName;
    }

    public void setKpiId(Long kpiId) {
        this.kpiId = kpiId;
    }

    public boolean isManagerViewStatus() {
        return managerViewStatus;
    }

    public void setManagerViewStatus(boolean managerViewStatus) {
        this.managerViewStatus = managerViewStatus;
    }

    public void setReviewLines(List<ReviewLineDto> reviewLines) {
        this.reviewLines = reviewLines;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public String getEmployeeDesignation() {
        return employeeDesignation;
    }

    public String getReviewerEmail() {
        return reviewerEmail;
    }

    public void setReviewerEmail(String reviewerEmail) {
        this.reviewerEmail = reviewerEmail;
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAttributeText() {
        return attributeText;
    }

    public void setAttributeText(String attributeText) {
        this.attributeText = attributeText;
    }

    public boolean isCurrentAccount() {
        return currentAccount;
    }

    public void setCurrentAccount(boolean currentAccount) {
        this.currentAccount = currentAccount;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public Map<Integer, String> getProgressMap() {
        return progressMap;
    }

    public void setProgressMap(Map<Integer, String> progressMap) {
        this.progressMap = progressMap;
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

    public int getReviewState() {
        return reviewState;
    }

    public void setReviewState(int reviewState) {
        this.reviewState = reviewState;
    }

    public String getReviewStateName() {
        return reviewStateName;
    }

    public void setReviewStateName(String reviewStateName) {
        this.reviewStateName = reviewStateName;
    }

    public String getRating() {
        return rating;
    }

    public String getReviewType() {
        return reviewType;
    }

    public void setReviewType(String reviewType) {
        this.reviewType = reviewType;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
