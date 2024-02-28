package com.trantorinc.synergy.custom.service.dto;

import java.io.File;
import java.util.List;

public class MailDto {
	private List<String> toAddress;
    private List<String> ccAddress;
    private List<String> bccAddress;
    private String subject;
    private String body;
    private List<File> attachments;
    public List<String> getToAddress() {
        return toAddress;
    }
    public void setToAddress(List<String> toAddress) {
        this.toAddress = toAddress;
    }
    public List<String> getCcAddress() {
        return ccAddress;
    }
    public void setCcAddress(List<String> ccAddress) {
        this.ccAddress = ccAddress;
    }
    public List<String> getBccAddress() {
        return bccAddress;
    }
    public void setBccAddress(List<String> bccAddress) {
        this.bccAddress = bccAddress;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public List<File> getAttachments() {
        return attachments;
    }
    public void setAttachments(List<File> attachments) {
        this.attachments = attachments;
    }
}
