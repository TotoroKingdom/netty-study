package com.totoro.netty_09_codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @author:totoro
 * @createDate:2022/12/9
 * @description:
 */
public class MyDecoder extends ByteToMessageDecoder {

    private final int BASE_LENGTH = 4;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        //基础长度不足
        if (in.readableBytes() < BASE_LENGTH){
            return;
        }

        int beginIdx;//包头位置

        while (true){
            beginIdx = in.readerIndex();
            in.markReaderIndex();
            if (in.readByte() == 0x02){
                break;
            }

            in.resetReaderIndex();
            in.readByte();

            if (in.readableBytes() < BASE_LENGTH){
                return;
            }
        }

        int readableCount = in.readableBytes();
        if (readableCount <= 1){
            in.readerIndex(beginIdx);
            return;
        }

        ByteBuf byteBuf = in.readBytes(1);
        String msgLengthStr = byteBuf.toString(Charset.forName("GBK"));
        int msgLength = Integer.parseInt(msgLengthStr);

        readableCount = in.readableBytes();
        if (readableCount < msgLength + 1){
            in.readerIndex();
            return;
        }

        ByteBuf msgContent = in.readBytes(msgLength);

        byte end = in.readByte();
        if (end != 0x03){
            in.readerIndex(beginIdx);
            return;
        }

        out.add(msgContent.toString(Charset.forName("GBK")));
    }
}
