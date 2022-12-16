package com.totoro.domain;

/**
 * @author:totoro
 * @createDate:2022/12/16
 * @description:
 */
public class FileDescInfo {

    private String fileUrl;
    private String fileName;
    private Long fileSize;

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
}
