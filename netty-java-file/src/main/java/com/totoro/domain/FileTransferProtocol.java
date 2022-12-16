package com.totoro.domain;

/**
 * @author:totoro
 * @createDate:2022/12/16
 * @description:
 */
public class FileTransferProtocol {

    private Integer transferType;
    private Object transferObj;

    public Integer getTransferType() {
        return transferType;
    }

    public void setTransferType(Integer transferType) {
        this.transferType = transferType;
    }

    public Object getTransferObj() {
        return transferObj;
    }

    public void setTransferObj(Object transferObj) {
        this.transferObj = transferObj;
    }
}
