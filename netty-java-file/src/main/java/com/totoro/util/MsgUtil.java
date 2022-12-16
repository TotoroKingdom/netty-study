package com.totoro.util;

import com.totoro.domain.*;

/**
 * @author:totoro
 * @createDate:2022/12/16
 * @description:
 */
public class MsgUtil {

    public static FileTransferProtocol buildRequestTransferFile(String fileUrl, String fileName, Long fileSize){

        FileDescInfo fileDescInfo = new FileDescInfo();
        fileDescInfo.setFileUrl(fileUrl);
        fileDescInfo.setFileName(fileName);
        fileDescInfo.setFileSize(fileSize);

        FileTransferProtocol fileTransferProtocol = new FileTransferProtocol();
        fileTransferProtocol.setTransferType(0);
        fileTransferProtocol.setTransferObj(fileDescInfo);

        return fileTransferProtocol;
    }

    public static FileTransferProtocol buildTransferInstruct(Integer status ,String clientFileUrl, Integer readPosition){
        FileBurstInstruct fileBurstInstruct = new FileBurstInstruct();
        fileBurstInstruct.setStatus(status);
        fileBurstInstruct.setClientFileUrl(clientFileUrl);
        fileBurstInstruct.setReadPosition(readPosition);

        FileTransferProtocol fileTransferProtocol = new FileTransferProtocol();
        fileTransferProtocol.setTransferObj(fileBurstInstruct);
        fileTransferProtocol.setTransferType(Constants.TransferType.INSTRUCT);

        return fileTransferProtocol;
    }

    public static FileTransferProtocol buildTransferInstruct(FileBurstInstruct fileBurstInstruct){
        FileTransferProtocol fileTransferProtocol = new FileTransferProtocol();
        fileTransferProtocol.setTransferType(Constants.TransferType.INSTRUCT);
        fileTransferProtocol.setTransferObj(fileBurstInstruct);
        return fileTransferProtocol;
    }

    public static FileTransferProtocol buildTransferData(FileBurstData fileBurstData){
        FileTransferProtocol fileTransferProtocol = new FileTransferProtocol();
        fileTransferProtocol.setTransferType(Constants.TransferType.DATA);
        fileTransferProtocol.setTransferObj(fileBurstData);
        return fileTransferProtocol;
    }

}
