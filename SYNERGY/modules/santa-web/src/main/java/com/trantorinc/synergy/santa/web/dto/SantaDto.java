package com.trantorinc.synergy.santa.web.dto;


import com.trantorinc.synergy.lms.core.model.Employee;

import java.util.List;

public class SantaDto {
    private String employeeCode;
    private String employeeName;
    private String employeeMobile;
    private String account;
    private String stateName;
    private String cityName;
    private String pincode;
    private String postalAddress;
    private String employeeGiftPic;
    private String secretSantaEcode;
    private String createdDate;
    private int currentYear;
    private String guessedEcode;
    private String secretSantaName;
    private List<Employee> santaOptions;
    //create allocationDate and giftDispatchDate
    private String registrationEndDate;
    private String allocationDate;
    private String allocationEndDate;
    private String giftDispatchDate;
    private String gameDate;
    private String gameEndDate;

    private Boolean giftSent;
    private Boolean emailSent;
    private String pageMode;
    private List<String> states;

    private Boolean guessedCorrect;
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

    public String getEmployeeMobile() {
        return employeeMobile;
    }

    public void setEmployeeMobile(String employeeMobile) {
        this.employeeMobile = employeeMobile;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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

    public int getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(int currentYear) {
        this.currentYear = currentYear;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getGuessedEcode() {
        return guessedEcode;
    }

    public void setGuessedEcode(String guessedEcode) {
        this.guessedEcode = guessedEcode;
    }

    public String getSecretSantaEcode() {
        return secretSantaEcode;
    }

    public void setSecretSantaEcode(String secretSantaEcode) {
        this.secretSantaEcode = secretSantaEcode;
    }

    public String getSecretSantaName() {
        return secretSantaName;
    }

    public void setSecretSantaName(String secretSantaName) {
        this.secretSantaName = secretSantaName;
    }

    public String getEmployeeGiftPic() {
        return employeeGiftPic;
    }

    public void setEmployeeGiftPic(String employeeGiftPic) {
        this.employeeGiftPic = employeeGiftPic;
    }

    public List<Employee> getSantaOptions() {
        return santaOptions;
    }

    public void setSantaOptions(List<Employee> santaOptions) {
        this.santaOptions = santaOptions;
    }

    public String getAllocationDate() {
        return allocationDate;
    }

    public void setAllocationDate(String allocationDate) {
        this.allocationDate = allocationDate;
    }

    public String getGiftDispatchDate() {
        return giftDispatchDate;
    }

    public void setGiftDispatchDate(String giftDispatchDate) {
        this.giftDispatchDate = giftDispatchDate;
    }


    public String getGameDate() {
        return gameDate;
    }

    public void setGameDate(String gameDate) {
        this.gameDate = gameDate;
    }
    public Boolean getEmailSent() {
        return emailSent;
    }

    public void setEmailSent(Boolean emailSent) {
        this.emailSent = emailSent;
    }

    public String getPageMode() {
        return pageMode;
    }

    public void setPageMode(String pageMode) {
        this.pageMode = pageMode;
    }

    public List<String> getStates() {
        return states;
    }

    public void setStates(List<String> states) {
        this.states = states;
    }

    public Boolean getGiftSent() {
        return giftSent;
    }

    public void setGiftSent(Boolean giftSent) {
        this.giftSent = giftSent;
    }

    public Boolean getGuessedCorrect() {
        return guessedCorrect;
    }

    public void setGuessedCorrect(Boolean guessedCorrect) {
        this.guessedCorrect = guessedCorrect;
    }

    public String getRegistrationEndDate() {
        return registrationEndDate;
    }

    public void setRegistrationEndDate(String registrationEndDate) {
        this.registrationEndDate = registrationEndDate;
    }

    public String getAllocationEndDate() {
        return allocationEndDate;
    }

    public void setAllocationEndDate(String allocationEndDate) {
        this.allocationEndDate = allocationEndDate;
    }

    public String getGameEndDate() {
        return gameEndDate;
    }

    public void setGameEndDate(String gameEndDate) {
        this.gameEndDate = gameEndDate;
    }
}
