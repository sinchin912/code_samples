package com.trantorinc.synergy.skill.admin.web.dto;

public class SkillDto {
    private Long skillId;
    private String account;
    private String coreSkill;
    private String subSkill;
    private String empCode;
    private String status;
    private String version;
    private String tool;
    private String validity;
    private String proficiencyLevel;
    private int requiredLevel;
    private String name;
    private String managerName;
    private String skillType;
    private boolean skillCertificateAdded;


    public SkillDto() {
    }

    public SkillDto(String account, String managerName, String empCode, String name , boolean skillCertificateAdded) {
        this.account = account;
        this.managerName = managerName;
        this.empCode = empCode;
        this.name = name;
        this.skillCertificateAdded = skillCertificateAdded;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCoreSkill() {
        return coreSkill;
    }

    public void setCoreSkill(String coreSkill) {
        this.coreSkill = coreSkill;
    }

    public String getSubSkill() {
        return subSkill;
    }

    public void setSubSkill(String subSkill) {
        this.subSkill = subSkill;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTool() {
        return tool;
    }

    public void setTool(String tool) {
        this.tool = tool;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getProficiencyLevel() {
        return proficiencyLevel;
    }

    public void setProficiencyLevel(String proficiencyLevel) {
        this.proficiencyLevel = proficiencyLevel;
    }

    public int getRequiredLevel() {
        return requiredLevel;
    }

    public void setRequiredLevel(int requiredLevel) {
        this.requiredLevel = requiredLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkillType() {
        return skillType;
    }

    public void setSkillType(String skillType) {
        this.skillType = skillType;
    }

    public boolean isSkillCertificateAdded() {
        return skillCertificateAdded;
    }

    public void setSkillCertificateAdded(boolean skillCertificateAdded) {
        this.skillCertificateAdded = skillCertificateAdded;
    }
}
