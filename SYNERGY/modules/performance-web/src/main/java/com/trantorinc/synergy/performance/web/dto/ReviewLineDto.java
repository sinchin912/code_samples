package com.trantorinc.synergy.performance.web.dto;

public class ReviewLineDto {
    private long reviewLineId;
    private long guideId;
    private long kpiReviewId;
    private long kpiId;
    private long kpiReviewGuideId;
    private String reviewLineTitle;
    private String reviewLineDescription;
    private String reviewLineTarget;
    private String employeeRating;
    private String employeeComment;
    private String managerRating;
    private String managerComment;
    private String hrRating;
    private String hrComment;
    private boolean attribute;
    private boolean other;
    private boolean mandatory;
    private boolean selected;

    public String getReviewLineTitle() {
        return reviewLineTitle;
    }

    public void setReviewLineTitle(String reviewLineTitle) {
        this.reviewLineTitle = reviewLineTitle;
    }

    public long getReviewLineId() {
        return reviewLineId;
    }

    public void setReviewLineId(long reviewLineId) {
        this.reviewLineId = reviewLineId;
    }

    public long getGuideId() {
        return guideId;
    }

    public void setGuideId(long guideId) {
        this.guideId = guideId;
    }

    public long getKpiReviewId() {
        return kpiReviewId;
    }

    public void setKpiReviewId(long kpiReviewId) {
        this.kpiReviewId = kpiReviewId;
    }

    public long getKpiId() {
        return kpiId;
    }

    public void setKpiId(long kpiId) {
        this.kpiId = kpiId;
    }

    public long getKpiReviewGuideId() {
        return kpiReviewGuideId;
    }

    public void setKpiReviewGuideId(long kpiReviewGuideId) {
        this.kpiReviewGuideId = kpiReviewGuideId;
    }


    public String getReviewLineDescription() {
        return reviewLineDescription;
    }

    public void setReviewLineDescription(String reviewLineDescription) {
        this.reviewLineDescription = reviewLineDescription;
    }


    public String getReviewLineTarget() {
        return reviewLineTarget;
    }

    public void setReviewLineTarget(String reviewLineTarget) {
        this.reviewLineTarget = reviewLineTarget;
    }

    public String getEmployeeRating() {
        return employeeRating;
    }

    public void setEmployeeRating(String employeeRating) {
        this.employeeRating = employeeRating;
    }

    public String getEmployeeComment() {
        return employeeComment;
    }

    public void setEmployeeComment(String employeeComment) {
        this.employeeComment = employeeComment;
    }

    public String getManagerRating() {
        return managerRating;
    }

    public void setManagerRating(String managerRating) {
        this.managerRating = managerRating;
    }

    public String getManagerComment() {
        return managerComment;
    }

    public void setManagerComment(String managerComment) {
        this.managerComment = managerComment;
    }

    public String getHrRating() {
        return hrRating;
    }

    public void setHrRating(String hrRating) {
        this.hrRating = hrRating;
    }

    public String getHrComment() {
        return hrComment;
    }

    public void setHrComment(String hrComment) {
        this.hrComment = hrComment;
    }

    public boolean isAttribute() {
        return attribute;
    }

    public void setAttribute(boolean attribute) {
        this.attribute = attribute;
    }

    public boolean isOther() {
        return other;
    }

    public void setOther(boolean other) {
        this.other = other;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
