package com.trantorinc.synergy.skill.web.dto;

import java.sql.Blob;
import java.util.Date;

public class CertificateDataDto {
    private Long certificateId;
    private String certificateName;
    private String certificateDesc;
    private String expiryDate;
    private String certificateCategory;
    private  String fileName;

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


    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCertificateCategory() {
        return certificateCategory;
    }

    public void setCertificateCategory(String certificateCategory) {
        this.certificateCategory = certificateCategory;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
