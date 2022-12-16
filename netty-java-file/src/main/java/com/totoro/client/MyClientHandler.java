package com.totoro.client;

import com.totoro.domain.Constants;
import com.totoro.domain.FileBurstData;
import com.totoro.domain.FileBurstInstruct;
import com.totoro.domain.FileTransferProtocol;
import com.totoro.util.FileUtil;
import com.totoro.util.MsgUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author:totoro
 * @createDate:2022/12/16
 * @description:
 */
public class MyClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel channel = (SocketChannel) ctx.channel();
        System.out.println("连接到服务端： channelId=" + channel.id());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("断开连接："+ ctx.channel().localAddress().toString());
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!(msg instanceof FileTransferProtocol)){
            return;
        }

        FileTransferProtocol fileTransferProtocol = (FileTransferProtocol) msg;
        switch (fileTransferProtocol.getTransferType()){
            case 1:
                FileBurstInstruct fileBurstInstruct = (FileBurstInstruct) fileTransferProtocol.getTransferObj();

                if (Constants.FileStatus.COMPLETE == fileBurstInstruct.getStatus()){
                    ctx.flush();
                    ctx.close();
                    System.exit(-1);
                    return;
                }
                FileBurstData fileBurstData = FileUtil.readFile(fileBurstInstruct.getClientFileUrl(), fileBurstInstruct.getReadPosition());
                ctx.writeAndFlush(MsgUtil.buildTransferData(fileBurstData));
                System.out.println(LocalDateTime.now() + "这是文件的信息 name =" + fileBurstData.getFileName());
                System.out.println(LocalDateTime.now() + "这是文件的信息 size" + (fileBurstData.getEndPos() - fileBurstData.getBeginPos()));
                break;
            default:
                break;
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        System.out.println("异常信息：\r\n" + cause.getMessage());
    }
}
