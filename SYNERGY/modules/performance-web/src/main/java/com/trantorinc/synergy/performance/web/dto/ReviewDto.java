package com.trantorinc.synergy.performance.web.dto;

import java.util.List;

public class ReviewDto {
    long kpiId;
    private Long reviewId;
    private String empEcode;
    private String empName;
    private String empDesignation;
    private String empBand;
    private String managerEmail;
    private String managerName;
    private String account;
    private String reviewerEmail;
    private String reviewerName;
    private String empMobileNo;
    private boolean generateReviewButtonTextDisplay;
    private int reviewState;
    private String reviewStateName;
    private boolean currentAccount;
    private String comment;
    private List<ReviewLineDto> lineDtoList;
    private List<ReviewLineDto> otherReviewLines;
    private String attributeText;
    private boolean currentYRReview;
    private String accountDate;
    private String achievementsByEmp;
    private String improvementComment;
    private String overallEmployeeComment;
    private String overallManagerComment;
    private String overallHrComment;
    private String rating;
    private String trainingsNeeded;
    private String reviewType;
    private String accountType;
    private String raiseToHrComments;
    private List<ReviewUploadDto> uploadsAttachments;
    private boolean kpiExistingStatus;
    private boolean raiseMeetingButtonDisplay;

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getReviewType() {
        return reviewType;
    }

    public void setReviewType(String reviewType) {
        this.reviewType = reviewType;
    }

    public String getTrainingsNeeded() {
        return trainingsNeeded;
    }

    public String getRaiseToHrComments() {
        return raiseToHrComments;
    }

    public void setRaiseToHrComments(String raiseToHrComments) {
        this.raiseToHrComments = raiseToHrComments;
    }

    public void setTrainingsNeeded(String trainingsNeeded) {
        this.trainingsNeeded = trainingsNeeded;
    }

    public boolean isRaiseMeetingButtonDisplay() {
        return raiseMeetingButtonDisplay;
    }

    public void setRaiseMeetingButtonDisplay(boolean raiseMeetingButtonDisplay) {
        this.raiseMeetingButtonDisplay = raiseMeetingButtonDisplay;
    }

    public boolean isKpiExistingStatus() {
        return kpiExistingStatus;
    }

    public void setKpiExistingStatus(boolean kpiExistingStatus) {
        this.kpiExistingStatus = kpiExistingStatus;
    }

    public List<ReviewUploadDto> getUploadsAttachments() {
        return uploadsAttachments;
    }

    public void setUploadsAttachments(List<ReviewUploadDto> uploadsAttachments) {
        this.uploadsAttachments = uploadsAttachments;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getAchievementsByEmp() {
        return achievementsByEmp;
    }

    public void setAchievementsByEmp(String achievementsByEmp) {
        this.achievementsByEmp = achievementsByEmp;
    }

    public String getImprovementComment() {
        return improvementComment;
    }

    public void setImprovementComment(String improvementComment) {
        this.improvementComment = improvementComment;
    }

    public String getOverallEmployeeComment() {
        return overallEmployeeComment;
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

    public boolean isCurrentYRReview() {
        return currentYRReview;
    }

    public void setCurrentYRReview(boolean currentYRReview) {
        this.currentYRReview = currentYRReview;
    }

    public String getAccountDate() {
        return accountDate;
    }

    public void setAccountDate(String accountDate) {
        this.accountDate = accountDate;
    }

    public String getAttributeText() {
        return attributeText;
    }

    public void setAttributeText(String attributeText) {
        this.attributeText = attributeText;
    }

    public List<ReviewLineDto> getLineDtoList() {
        return lineDtoList;
    }

    public void setLineDtoList(List<ReviewLineDto> lineDtoList) {
        this.lineDtoList = lineDtoList;
    }


    public List<ReviewLineDto> getOtherReviewLines() {
        return otherReviewLines;
    }

    public void setOtherReviewLines(List<ReviewLineDto> otherReviewLines) {
        this.otherReviewLines = otherReviewLines;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
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

    public boolean isCurrentAccount() {
        return currentAccount;
    }

    public void setCurrentAccount(boolean currentAccount) {
        this.currentAccount = currentAccount;
    }

    public long getKpiId() {
        return kpiId;
    }

    public void setKpiId(long kpiId) {
        this.kpiId = kpiId;
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

    public boolean isGenerateReviewButtonTextDisplay() {
        return generateReviewButtonTextDisplay;
    }

    public void setGenerateReviewButtonTextDisplay(boolean generateReviewButtonTextDisplay) {
        this.generateReviewButtonTextDisplay = generateReviewButtonTextDisplay;
    }
}
