package com.trantorinc.synergy.billing.web.dto;

public class SkillsetDto {

    private String coreSkill;
    private String subSkill;
    private boolean type;
    private String version;
    private String tool;
    private String validity;
    private int proficiencyLevel;
    private String proficiencyLevelStr;
    private  String project;
    private String typeStr;

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

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
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

    public int getProficiencyLevel() {
        return proficiencyLevel;
    }

    public void setProficiencyLevel(int proficiencyLevel) {
        this.proficiencyLevel = proficiencyLevel;
    }

    public String getProficiencyLevelStr() {
        return proficiencyLevelStr;
    }

    public void setProficiencyLevelStr(String proficiencyLevelStr) {
        this.proficiencyLevelStr = proficiencyLevelStr;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }
}
