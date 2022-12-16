package com.totoro.server;

import com.alibaba.fastjson.JSON;
import com.totoro.domain.*;
import com.totoro.util.CacheUtil;
import com.totoro.util.FileUtil;
import com.totoro.util.MsgUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;

import java.lang.invoke.ConstantCallSite;
import java.time.LocalDateTime;

/**
 * @author:totoro
 * @createDate:2022/12/16
 * @description:
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel channel = (SocketChannel) ctx.channel();
        System.out.println("有客户端连接成功： channelId = " + channel.id());

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端断开连接："+ctx.channel().localAddress().toString());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!(msg instanceof FileTransferProtocol)){
            return;
        }
        FileTransferProtocol fileTransferProtocol = (FileTransferProtocol) msg;

        switch (fileTransferProtocol.getTransferType()){
            case 0:
                FileDescInfo fileDescInfo = (FileDescInfo) fileTransferProtocol.getTransferObj();
                //断点续传信息，实际应用中需要将断点续传信息保存到数据库中
                FileBurstInstruct fileBurstInstructOld = CacheUtil.burstDataMap.get(fileDescInfo.getFileName());
                if (null != fileBurstInstructOld){
                    if (fileBurstInstructOld.getStatus() == Constants.FileStatus.COMPLETE){
                        CacheUtil.burstDataMap.remove(fileDescInfo.getFileName());
                    }
                    System.out.println(LocalDateTime.now() + "接收客户端传输文件【断点续传】" + JSON.toJSONString(fileBurstInstructOld));
                    ctx.writeAndFlush(MsgUtil.buildTransferInstruct(fileBurstInstructOld));
                    return;
                }
                //发送信息
                FileTransferProtocol sendFileTransferProtocol = MsgUtil.buildTransferInstruct(Constants.FileStatus.BEGIN, fileDescInfo.getFileUrl(), 0);
                ctx.writeAndFlush(sendFileTransferProtocol);
                System.out.println(LocalDateTime.now() + "接收客户端传输文件请求。" + JSON.toJSONString(fileDescInfo));
                break;
            case 2:
                FileBurstData fileBurstData = (FileBurstData) fileTransferProtocol.getTransferObj();
                FileBurstInstruct fileBurstInstruct = FileUtil.writeFile("E://", fileBurstData);
                CacheUtil.burstDataMap.put(fileBurstData.getFileName(), fileBurstInstruct);
                System.out.println("接收客户端传输文件数据：" + JSON.toJSONString(fileBurstData));
                //传输完成删除断点信息
                if (fileBurstInstruct.getStatus() == Constants.FileStatus.COMPLETE){
                    CacheUtil.burstDataMap.remove(fileBurstData.getFileName());
                }
                break;
            default:
                break;
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        System.out.printf("异常信息： \r\n" + cause.getMessage());
    }
}
