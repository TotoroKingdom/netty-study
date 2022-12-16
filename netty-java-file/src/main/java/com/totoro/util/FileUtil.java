package com.totoro.util;

import com.totoro.domain.Constants;
import com.totoro.domain.FileBurstData;
import com.totoro.domain.FileBurstInstruct;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author:totoro
 * @createDate:2022/12/16
 * @description:
 */
public class FileUtil {

    public static FileBurstData readFile(String fileUrl, Integer readPosition) throws IOException {
        File file = new File(fileUrl);
        RandomAccessFile randomAccessFile = new RandomAccessFile(fileUrl, "r"); //r:只读模式 rw:读写模式
        randomAccessFile.seek(readPosition);
        byte[] bytes = new byte[1024 * 1024];
        int readSize = randomAccessFile.read(bytes);
        if ( readSize <= 0 ){
            randomAccessFile.close();
            return new FileBurstData(Constants.FileStatus.COMPLETE);
        }
        FileBurstData fileInfo = new FileBurstData();
        fileInfo.setFileUrl(fileUrl);
        fileInfo.setFileName(file.getName());
        fileInfo.setBeginPos(readPosition);
        fileInfo.setEndPos(readPosition + readSize);

        if (readSize < 1024 * 100){
            byte[] copy = new byte[readSize];
            System.arraycopy(bytes, 0, copy, 0, readSize);
            fileInfo.setBytes(copy);
            fileInfo.setStatus(Constants.FileStatus.END);
        } else {
            fileInfo.setBytes(bytes);
            fileInfo.setStatus(Constants.FileStatus.CENTER);
        }

        randomAccessFile.close();
        return fileInfo;
    }

    public static FileBurstInstruct writeFile(String baseUrl, FileBurstData fileBurstData) throws IOException {
        if (Constants.FileStatus.COMPLETE == fileBurstData.getStatus()){
            return new FileBurstInstruct(Constants.FileStatus.COMPLETE);
        }

        File file = new File(baseUrl + "/" + fileBurstData.getFileName());
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw"); //rw:读写模式
        randomAccessFile.seek(fileBurstData.getBeginPos());
        randomAccessFile.write(fileBurstData.getBytes());
        randomAccessFile.close();

        if (Constants.FileStatus.END == fileBurstData.getStatus()){
            return new FileBurstInstruct(Constants.FileStatus.COMPLETE);
        }

        FileBurstInstruct fileBurstInstruct = new FileBurstInstruct();
        fileBurstInstruct.setStatus(Constants.FileStatus.CENTER);
        fileBurstInstruct.setClientFileUrl(fileBurstData.getFileUrl());
        fileBurstInstruct.setReadPosition(fileBurstData.getEndPos() + 1);

        return fileBurstInstruct;
    }
}
