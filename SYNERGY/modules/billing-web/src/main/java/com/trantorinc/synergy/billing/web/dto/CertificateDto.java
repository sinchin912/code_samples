package com.trantorinc.synergy.billing.web.dto;

import java.sql.Blob;
import java.util.Date;

public class CertificateDto {

    private Long certificateId;
    private String certificateName;
    private String certificateDesc;
    private String expiryDateStr;
    private Date expiryDate;
    private String certificateCategory;
    private Blob attachement;
    private  String attachmentName;
    private String ecode;
    private  String name;
    private String project;

    public Long getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(Long certificateId) {
        this.certificateId = certificateId;
    }

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    public String getCertificateDesc() {
        return certificateDesc;
    }

    public void setCertificateDesc(String certificateDesc) {
        this.certificateDesc = certificateDesc;
    }

    public String getExpiryDateStr() {
        return expiryDateStr;
    }

    public void setExpiryDateStr(String expiryDateStr) {
        this.expiryDateStr = expiryDateStr;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCertificateCategory() {
        return certificateCategory;
    }

    public void setCertificateCategory(String certificateCategory) {
        this.certificateCategory = certificateCategory;
    }

    public Blob getAttachement() {
        return attachement;
    }

    public void setAttachement(Blob attachement) {
        this.attachement = attachement;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
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

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
}
