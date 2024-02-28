package com.trantorinc.synergy.performance.admin.web.dto;

import java.util.Date;
import java.util.List;

public class ReviewUploadDto {
    private long uploadId;
    private String fileName;
    private String fileId;
    private Date createdDate;
    private List<ReviewUploadDto> filesUpload;

    public long getUploadId() {
        return uploadId;
    }

    public void setUploadId(long uploadId) {
        this.uploadId = uploadId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public List<ReviewUploadDto> getFilesUpload() {
        return filesUpload;
    }

    public void setFilesUpload(List<ReviewUploadDto> filesUpload) {
        this.filesUpload = filesUpload;
    }
}
