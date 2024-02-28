package com.trantorinc.synergy.notice.admin.web.dto;

public class ClearanceFormDto {
    private ManagerClearanceDto managerClearanceDto;
    private ItClearanceDto itClearanceDto;
    private FinanceClearanceDto financeClearanceDto;
    private HrClearanceDto hrClearanceDto;
    private AdminClearanceDto adminClearanceDto;
    private Boolean saveSubmitEnable;
    private Boolean showSubmitButton;
    private Boolean hrSubmitted;

    public Boolean getHrSubmitted() {
        return hrSubmitted;
    }

    public void setHrSubmitted(Boolean hrSubmitted) {
        this.hrSubmitted = hrSubmitted;
    }

    public ManagerClearanceDto getManagerClearanceDto() {
        return managerClearanceDto;
    }

    public void setManagerClearanceDto(ManagerClearanceDto managerClearanceDto) {
        this.managerClearanceDto = managerClearanceDto;
    }

    public ItClearanceDto getItClearanceDto() {
        return itClearanceDto;
    }

    public void setItClearanceDto(ItClearanceDto itClearanceDto) {
        this.itClearanceDto = itClearanceDto;
    }

    public FinanceClearanceDto getFinanceClearanceDto() {
        return financeClearanceDto;
    }

    public void setFinanceClearanceDto(FinanceClearanceDto financeClearanceDto) {
        this.financeClearanceDto = financeClearanceDto;
    }

    public HrClearanceDto getHrClearanceDto() {
        return hrClearanceDto;
    }

    public void setHrClearanceDto(HrClearanceDto hrClearanceDto) {
        this.hrClearanceDto = hrClearanceDto;
    }

    public AdminClearanceDto getAdminClearanceDto() {
        return adminClearanceDto;
    }

    public void setAdminClearanceDto(AdminClearanceDto adminClearanceDto) {
        this.adminClearanceDto = adminClearanceDto;
    }

    public Boolean getSaveSubmitEnable() {
        return saveSubmitEnable;
    }

    public void setSaveSubmitEnable(Boolean saveSubmitEnable) {
        this.saveSubmitEnable = saveSubmitEnable;
    }

    public Boolean getShowSubmitButton() {
        return showSubmitButton;
    }

    public void setShowSubmitButton(Boolean showSubmitButton) {
        this.showSubmitButton = showSubmitButton;
    }
}
