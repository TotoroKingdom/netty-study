package com.totoro.domain;

/**
 * @author:totoro
 * @createDate:2022/12/16
 * @description:
 */
public class FileBurstInstruct {

    private Integer status;
    private String clientFileUrl;
    private Integer readPosition;

    public FileBurstInstruct(){}


    public FileBurstInstruct(Integer status){
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getClientFileUrl() {
        return clientFileUrl;
    }

    public void setClientFileUrl(String clientFileUrl) {
        this.clientFileUrl = clientFileUrl;
    }

    public Integer getReadPosition() {
        return readPosition;
    }

    public void setReadPosition(Integer readPosition) {
        this.readPosition = readPosition;
    }
}
