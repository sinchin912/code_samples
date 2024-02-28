package com.trantorinc.synergy.notice.admin.web.dto;

public class RecoveryReimbursmentDto {
    private Long id;
    private Long departmentFormId;
    private String recoveryItem;
    private Integer recoveryStatus;
    private String recoveryAmount;
    private Boolean recoveryType;
    private String reimbursementItem;
    private Integer reimbursementStatus;
    private String reimbursementAmount;
    private String comment;
    private String nameSpace;
    private Boolean approved;

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDepartmentFormId() {
        return departmentFormId;
    }

    public void setDepartmentFormId(Long departmentFormId) {
        this.departmentFormId = departmentFormId;
    }

    public String getRecoveryItem() {
        return recoveryItem;
    }

    public void setRecoveryItem(String recoveryItem) {
        this.recoveryItem = recoveryItem;
    }

    public Integer getRecoveryStatus() {
        return recoveryStatus;
    }

    public void setRecoveryStatus(Integer recoveryStatus) {
        this.recoveryStatus = recoveryStatus;
    }

    public String getRecoveryAmount() {
        return recoveryAmount;
    }

    public void setRecoveryAmount(String recoveryAmount) {
        this.recoveryAmount = recoveryAmount;
    }

    public Boolean getRecoveryType() {
        return recoveryType;
    }

    public void setRecoveryType(Boolean recoveryType) {
        this.recoveryType = recoveryType;
    }

    public String getReimbursementItem() {
        return reimbursementItem;
    }

    public void setReimbursementItem(String reimbursementItem) {
        this.reimbursementItem = reimbursementItem;
    }

    public Integer getReimbursementStatus() {
        return reimbursementStatus;
    }

    public void setReimbursementStatus(Integer reimbursementStatus) {
        this.reimbursementStatus = reimbursementStatus;
    }

    public String getReimbursementAmount() {
        return reimbursementAmount;
    }

    public void setReimbursementAmount(String reimbursementAmount) {
        this.reimbursementAmount = reimbursementAmount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }
}
