package com.totoro.netty_12_http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;

import java.nio.charset.Charset;

/**
 * @author:totoro
 * @createDate:2022/12/9
 * @description:
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpRequest){
            DefaultHttpRequest request = (DefaultHttpRequest) msg;
            System.out.println("URI: " + request.getUri());
            System.err.println(msg);
        }

        if (msg instanceof HttpContent){
            LastHttpContent httpContent = (LastHttpContent) msg;
            ByteBuf byteData = httpContent.content();
            if (!(byteData instanceof EmptyByteBuf)){
                byte[] msgByte = new byte[byteData.readableBytes()];
                byteData.readBytes(msgByte);
                System.out.println(new String(msgByte, Charset.forName("UTF-8")));
            }
        }

        String sendMsg = "明月几时有，把酒问青天";

        FullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK,
                Unpooled.wrappedBuffer(sendMsg.getBytes(Charset.forName("UTF-8"))));

        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset=UTF-8");
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
        response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        ctx.write(response);
        ctx.flush();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        cause.printStackTrace();
    }
}
