package com.trantorinc.synergy.notice.admin.web.dto;

public class ManagerClearanceDto {
    private Boolean managerSubmitted;
    private Integer ticketNo;
    private String ticketNoRemark;
    private Integer separationDocument;
    private String separationDocumentRemark;
    private String updatedDate;
    private Boolean managerResignSubmitted;

    public Boolean getManagerSubmitted() {
        return managerSubmitted;
    }

    public void setManagerSubmitted(Boolean managerSubmitted) {
        this.managerSubmitted = managerSubmitted;
    }

    public Integer getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(Integer ticketNo) {
        this.ticketNo = ticketNo;
    }

    public String getTicketNoRemark() {
        return ticketNoRemark;
    }

    public void setTicketNoRemark(String ticketNoRemark) {
        this.ticketNoRemark = ticketNoRemark;
    }

    public Integer getSeparationDocument() {
        return separationDocument;
    }

    public void setSeparationDocument(Integer separationDocument) {
        this.separationDocument = separationDocument;
    }

    public String getSeparationDocumentRemark() {
        return separationDocumentRemark;
    }

    public void setSeparationDocumentRemark(String separationDocumentRemark) {
        this.separationDocumentRemark = separationDocumentRemark;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Boolean getManagerResignSubmitted() {
        return managerResignSubmitted;
    }

    public void setManagerResignSubmitted(Boolean managerResignSubmitted) {
        this.managerResignSubmitted = managerResignSubmitted;
    }
}
