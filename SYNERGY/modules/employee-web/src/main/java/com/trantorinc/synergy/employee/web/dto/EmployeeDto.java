package com.trantorinc.synergy.employee.web.dto;

public class EmployeeDto {
    private String ecode;
    private String name;
    private String account;
    private String designation;
    private String email;
    private String skype;
    private String managerName;
    private boolean status;

    public EmployeeDto(String ecode, String name, String account, String designation, String email, String skype, String managerName, boolean status) {
        this.ecode = ecode;
        this.name = name;
        this.account = account;
        this.designation = designation;
        this.email = email;
        this.skype = skype;
        this.managerName = managerName;
        this.status = status;
    }

    public String getEcode() {
        return ecode;
    }

    public void setEcode(String ecode) {
        this.ecode = ecode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
