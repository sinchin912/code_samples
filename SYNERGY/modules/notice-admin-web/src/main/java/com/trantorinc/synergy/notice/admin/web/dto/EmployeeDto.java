package com.trantorinc.synergy.notice.admin.web.dto;

import java.util.Map;

public class EmployeeDto {
    private String employeeCode;
    private String employeeName;
    private String employeeEmail;
    private String employeeDesignation;
    private String employeeBand;
    private String managerEmail;
    private String managerName;
    private String empMobNo;
    private String account;
    private String reviewerName;
    private String reviewerEmail;
    private String comment;
    private String location;
    private String employeeDoj;
    private long resignationId;
    private String resignationDate;
    private String managerComment;
    private String hrComment;
    private String employeeComment;
    private String empWithdrawComment;
    private String hrWithdrawComment;
    private String type;
    private String status;
    private boolean exitStatus;
    private  String reason;
    private String doj;
    private String personalEmail;
    private String lastWorkingDate;
    private String abscondTerminateDate;
    private String noticePeriod;
    private int statusInt;
    private String stateName;
    private String cityName;
    private String pincode;
    private String postalAddress;
    private String secondaryManagerEmailId;
    private String separationType;
    private String keyToCompany;
    private String keyToProject;
    private String clientImpact;
    private String rating;
    private String eligibleForRehire;
    private String otherIssue;
    private String reasonNonRetention;
    private String reasonForLeaving;
    private String replacementPlan;
    private String roleType;
    private String mobile;
    private String keyToRetention;
    private String hasLaptop;
    private String hasMouse;
    private String hasCharger;
    private String hasHeadSet;
    private String hasLaptopBag;
    private String hasHomeDesktop;
    private String hasHomeMonitor;
    private String otherAssetsList;
    private String itAssetsSubmissionDate;
    private String tentativeLWD;
    private String terminationType;
    private Map<String,String> stateMap;
    private String assigneeEmail;
    private String assigneeName;
    private Boolean assigneeTo;
    private boolean primaryManager;
    private boolean itAssetsSubmitted;
    private String dateOfResignation;
    private String project;
    private boolean questionnaireFormSubmitted;
    private boolean managerFormSubmitted;
    private boolean clearanceFormGenerated;
    private boolean showPdfDownloadButton;
    private boolean showHideHrUpdateButton;
    private boolean managerSubmitted;
    private boolean showManagerMessage;
    private boolean showQuesFormManager;
    private String loggedUserEmail;
    private boolean abscondedByHR;
    private boolean abscondRetained;
    private boolean clearanceCompleted;

    private String replacementDetail;
    private String reasonForLeavingByHr;
    private boolean exitQuestionnaire;
    private String alternateEmail;
    private String empStatus;

    public boolean isShowPdfDownloadButton() {
        return showPdfDownloadButton;
    }

    public void setShowPdfDownloadButton(boolean showPdfDownloadButton) {
        this.showPdfDownloadButton = showPdfDownloadButton;
    }

    public String getEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(String empStatus) {
        this.empStatus = empStatus;
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

    public String getEmpMobNo() {
        return empMobNo;
    }

    public void setEmpMobNo(String empMobNo) {
        this.empMobNo = empMobNo;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmployeeDoj() {
        return employeeDoj;
    }

    public void setEmployeeDoj(String employeeDoj) {
        this.employeeDoj = employeeDoj;
    }

    public long getResignationId() {
        return resignationId;
    }

    public void setResignationId(long resignationId) {
        this.resignationId = resignationId;
    }

    public String getResignationDate() {
        return resignationDate;
    }

    public void setResignationDate(String resignationDate) {
        this.resignationDate = resignationDate;
    }

    public String getManagerComment() {
        return managerComment;
    }

    public void setManagerComment(String managerComment) {
        this.managerComment = managerComment;
    }

    public String getHrComment() {
        return hrComment;
    }

    public void setHrComment(String hrComment) {
        this.hrComment = hrComment;
    }

    public String getEmployeeComment() {
        return employeeComment;
    }

    public void setEmployeeComment(String employeeComment) {
        this.employeeComment = employeeComment;
    }

    public String getEmpWithdrawComment() {
        return empWithdrawComment;
    }

    public void setEmpWithdrawComment(String empWithdrawComment) {
        this.empWithdrawComment = empWithdrawComment;
    }

    public String getHrWithdrawComment() {
        return hrWithdrawComment;
    }

    public void setHrWithdrawComment(String hrWithdrawComment) {
        this.hrWithdrawComment = hrWithdrawComment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isExitStatus() {
        return exitStatus;
    }

    public void setExitStatus(boolean exitStatus) {
        this.exitStatus = exitStatus;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public String getLastWorkingDate() {
        return lastWorkingDate;
    }

    public void setLastWorkingDate(String lastWorkingDate) {
        this.lastWorkingDate = lastWorkingDate;
    }

    public String getAbscondTerminateDate() {
        return abscondTerminateDate;
    }

    public void setAbscondTerminateDate(String abscondTerminateDate) {
        this.abscondTerminateDate = abscondTerminateDate;
    }

    public String getNoticePeriod() {
        return noticePeriod;
    }

    public void setNoticePeriod(String noticePeriod) {
        this.noticePeriod = noticePeriod;
    }

    public int getStatusInt() {
        return statusInt;
    }

    public void setStatusInt(int statusInt) {
        this.statusInt = statusInt;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    public String getSecondaryManagerEmailId() {
        return secondaryManagerEmailId;
    }

    public void setSecondaryManagerEmailId(String secondaryManagerEmailId) {
        this.secondaryManagerEmailId = secondaryManagerEmailId;
    }

    public String getSeparationType() {
        return separationType;
    }

    public void setSeparationType(String separationType) {
        this.separationType = separationType;
    }

    public String getKeyToCompany() {
        return keyToCompany;
    }

    public void setKeyToCompany(String keyToCompany) {
        this.keyToCompany = keyToCompany;
    }

    public String getKeyToProject() {
        return keyToProject;
    }

    public void setKeyToProject(String keyToProject) {
        this.keyToProject = keyToProject;
    }

    public String getClientImpact() {
        return clientImpact;
    }

    public void setClientImpact(String clientImpact) {
        this.clientImpact = clientImpact;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getEligibleForRehire() {
        return eligibleForRehire;
    }

    public void setEligibleForRehire(String eligibleForRehire) {
        this.eligibleForRehire = eligibleForRehire;
    }

    public String getOtherIssue() {
        return otherIssue;
    }

    public void setOtherIssue(String otherIssue) {
        this.otherIssue = otherIssue;
    }

    public String getReasonNonRetention() {
        return reasonNonRetention;
    }

    public void setReasonNonRetention(String reasonNonRetention) {
        this.reasonNonRetention = reasonNonRetention;
    }

    public String getReasonForLeaving() {
        return reasonForLeaving;
    }

    public void setReasonForLeaving(String reasonForLeaving) {
        this.reasonForLeaving = reasonForLeaving;
    }

    public String getReplacementPlan() {
        return replacementPlan;
    }

    public void setReplacementPlan(String replacementPlan) {
        this.replacementPlan = replacementPlan;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getKeyToRetention() {
        return keyToRetention;
    }

    public void setKeyToRetention(String keyToRetention) {
        this.keyToRetention = keyToRetention;
    }

    public String getHasLaptop() {
        return hasLaptop;
    }

    public void setHasLaptop(String hasLaptop) {
        this.hasLaptop = hasLaptop;
    }

    public String getHasMouse() {
        return hasMouse;
    }

    public void setHasMouse(String hasMouse) {
        this.hasMouse = hasMouse;
    }

    public String getHasCharger() {
        return hasCharger;
    }

    public void setHasCharger(String hasCharger) {
        this.hasCharger = hasCharger;
    }

    public String getHasHeadSet() {
        return hasHeadSet;
    }

    public void setHasHeadSet(String hasHeadSet) {
        this.hasHeadSet = hasHeadSet;
    }

    public String getHasLaptopBag() {
        return hasLaptopBag;
    }

    public void setHasLaptopBag(String hasLaptopBag) {
        this.hasLaptopBag = hasLaptopBag;
    }

    public String getHasHomeDesktop() {
        return hasHomeDesktop;
    }

    public void setHasHomeDesktop(String hasHomeDesktop) {
        this.hasHomeDesktop = hasHomeDesktop;
    }

    public String getHasHomeMonitor() {
        return hasHomeMonitor;
    }

    public void setHasHomeMonitor(String hasHomeMonitor) {
        this.hasHomeMonitor = hasHomeMonitor;
    }

    public String getOtherAssetsList() {
        return otherAssetsList;
    }

    public void setOtherAssetsList(String otherAssetsList) {
        this.otherAssetsList = otherAssetsList;
    }

    public String getItAssetsSubmissionDate() {
        return itAssetsSubmissionDate;
    }

    public void setItAssetsSubmissionDate(String itAssetsSubmissionDate) {
        this.itAssetsSubmissionDate = itAssetsSubmissionDate;
    }

    public String getTentativeLWD() {
        return tentativeLWD;
    }

    public void setTentativeLWD(String tentativeLWD) {
        this.tentativeLWD = tentativeLWD;
    }

    public String getTerminationType() {
        return terminationType;
    }

    public void setTerminationType(String terminationType) {
        this.terminationType = terminationType;
    }

    public Map<String, String> getStateMap() {
        return stateMap;
    }

    public void setStateMap(Map<String, String> stateMap) {
        this.stateMap = stateMap;
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

    public Boolean getAssigneeTo() {
        return assigneeTo;
    }

    public void setAssigneeTo(Boolean assigneeTo) {
        this.assigneeTo = assigneeTo;
    }

    public boolean isPrimaryManager() {
        return primaryManager;
    }

    public void setPrimaryManager(boolean primaryManager) {
        this.primaryManager = primaryManager;
    }

    public boolean isItAssetsSubmitted() {
        return itAssetsSubmitted;
    }

    public void setItAssetsSubmitted(boolean itAssetsSubmitted) {
        this.itAssetsSubmitted = itAssetsSubmitted;
    }

    public String getDateOfResignation() {
        return dateOfResignation;
    }

    public void setDateOfResignation(String dateOfResignation) {
        this.dateOfResignation = dateOfResignation;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public boolean isQuestionnaireFormSubmitted() {
        return questionnaireFormSubmitted;
    }

    public void setQuestionnaireFormSubmitted(boolean questionnaireFormSubmitted) {
        this.questionnaireFormSubmitted = questionnaireFormSubmitted;
    }

    public boolean isManagerFormSubmitted() {
        return managerFormSubmitted;
    }

    public void setManagerFormSubmitted(boolean managerFormSubmitted) {
        this.managerFormSubmitted = managerFormSubmitted;
    }

    public boolean isClearanceFormGenerated() {
        return clearanceFormGenerated;
    }

    public void setClearanceFormGenerated(boolean clearanceFormGenerated) {
        this.clearanceFormGenerated = clearanceFormGenerated;
    }

    public boolean isShowHideHrUpdateButton() {
        return showHideHrUpdateButton;
    }

    public void setShowHideHrUpdateButton(boolean showHideHrUpdateButton) {
        this.showHideHrUpdateButton = showHideHrUpdateButton;
    }

    public boolean isManagerSubmitted() {
        return managerSubmitted;
    }

    public void setManagerSubmitted(boolean managerSubmitted) {
        this.managerSubmitted = managerSubmitted;
    }

    public boolean isShowManagerMessage() {
        return showManagerMessage;
    }

    public void setShowManagerMessage(boolean showManagerMessage) {
        this.showManagerMessage = showManagerMessage;
    }

    public boolean isShowQuesFormManager() {
        return showQuesFormManager;
    }

    public void setShowQuesFormManager(boolean showQuesFormManager) {
        this.showQuesFormManager = showQuesFormManager;
    }

    public String getLoggedUserEmail() {
        return loggedUserEmail;
    }

    public void setLoggedUserEmail(String loggedUserEmail) {
        this.loggedUserEmail = loggedUserEmail;
    }

    public boolean isAbscondedByHR() {
        return abscondedByHR;
    }

    public void setAbscondedByHR(boolean abscondedByHR) {
        this.abscondedByHR = abscondedByHR;
    }

    public boolean isAbscondRetained() {
        return abscondRetained;
    }

    public void setAbscondRetained(boolean abscondRetained) {
        this.abscondRetained = abscondRetained;
    }

    public boolean isClearanceCompleted() {
        return clearanceCompleted;
    }

    public void setClearanceCompleted(boolean clearanceCompleted) {
        this.clearanceCompleted = clearanceCompleted;
    }

    public String getReplacementDetail() {
        return replacementDetail;
    }

    public void setReplacementDetail(String replacementDetail) {
        this.replacementDetail = replacementDetail;
    }

    public String getReasonForLeavingByHr() {
        return reasonForLeavingByHr;
    }

    public void setReasonForLeavingByHr(String reasonForLeavingByHr) {
        this.reasonForLeavingByHr = reasonForLeavingByHr;
    }

    public boolean isExitQuestionnaire() {
        return exitQuestionnaire;
    }

    public void setExitQuestionnaire(boolean exitQuestionnaire) {
        this.exitQuestionnaire = exitQuestionnaire;
    }

    public String getAlternateEmail() {
        return alternateEmail;
    }

    public void setAlternateEmail(String alternateEmail) {
        this.alternateEmail = alternateEmail;
    }
}
