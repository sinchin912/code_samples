package com.trantorinc.synergy.performance.admin.web.dto;

public class ReviewLineDto {
    private long reviewLineId;
    private String reviewLineTitle;
    private String reviewLineDescription;
    private String employeeComment;
    private String managerRating;
    private String managerComment;
    private String hrRating;
    private String hrComment;
    private String employeeRating;
    private boolean attribute;
    private boolean other;
    private boolean mandatory;
    private Long guideId;
    private String reviewLineTarget;

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public String getEmployeeRating() {
        return employeeRating;
    }

    public void setEmployeeRating(String employeeRating) {
        this.employeeRating = employeeRating;
    }

    public Long getGuideId() {
        return guideId;
    }

    public void setGuideId(Long guideId) {
        this.guideId = guideId;
    }

    public String getReviewLineTarget() {
        return reviewLineTarget;
    }

    public void setReviewLineTarget(String reviewLineTarget) {
        this.reviewLineTarget = reviewLineTarget;
    }

    public long getReviewLineId() {
        return reviewLineId;
    }

    public void setReviewLineId(long reviewLineId) {
        this.reviewLineId = reviewLineId;
    }

    public String getReviewLineTitle() {
        return reviewLineTitle;
    }

    public void setReviewLineTitle(String reviewLineTitle) {
        this.reviewLineTitle = reviewLineTitle;
    }

    public String getReviewLineDescription() {
        return reviewLineDescription;
    }

    public void setReviewLineDescription(String reviewLineDescription) {
        this.reviewLineDescription = reviewLineDescription;
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
}
