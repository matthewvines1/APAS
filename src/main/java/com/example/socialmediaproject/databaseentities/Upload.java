package com.example.socialmediaproject.databaseentities;

import java.sql.Blob;
import java.sql.Date;
import java.sql.Time;

public class Upload {

    private int uploadId;
    private Blob fileContent;
    private String fileName;
    private String filePath;
    private String fileUrl;
    private boolean isDeleted;
    private boolean isUrl;
    private Date uploadDate;
    private Time uploadTime;
    private int userId;

    public Upload(int uploadId, Blob fileContent, String fileName, String filePath, String fileUrl, boolean isDeleted,
                  boolean isUrl, Date uploadDate, Time uploadTime, int userId) {
        this.uploadId = uploadId;
        this.fileContent = fileContent;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileUrl = fileUrl;
        this.isDeleted = isDeleted;
        this.isUrl = isUrl;
        this.uploadDate = uploadDate;
        this.uploadTime = uploadTime;
        this.userId = userId;
    }

    public int getUploadId() {
        return uploadId;
    }

    public void setUploadId(int uploadId) {
        this.uploadId = uploadId;
    }

    public Blob getFileContent() {
        return fileContent;
    }

    public void setFileContent(Blob fileContent) {
        this.fileContent = fileContent;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public boolean isUrl() {
        return isUrl;
    }

    public void setUrl(boolean url) {
        isUrl = url;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Time getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Time uploadTime) {
        this.uploadTime = uploadTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
