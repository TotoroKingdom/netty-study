package com.totoro.server;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONNull;
import cn.hutool.json.JSONUtil;
import com.totoro.domain.ClientMsgProtocol;
import com.totoro.util.ChannelHandler;
import com.totoro.util.MsgUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/**
 * @author:totoro
 * @createDate:2023/3/27
 * @description:
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    private WebSocketServerHandshaker handshaker;


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        SocketChannel channel = (SocketChannel) ctx.channel();

        InetSocketAddress inetSocketAddress = channel.localAddress();
        System.out.println("有客户端连接到本服务端："+channel.localAddress().getHostString()+":"+channel.localAddress().getPort());

        ChannelHandler.channelGroup.add(ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        if (msg instanceof FullHttpRequest){
            FullHttpRequest httpRequest = (FullHttpRequest) msg;

            if (!httpRequest.decoderResult().isSuccess()){

                DefaultFullHttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST);

                if (httpResponse.status().code() != 200){
                    ByteBuf buf = Unpooled.copiedBuffer(httpResponse.status().toString(), CharsetUtil.UTF_8);
                    httpResponse.content().writeBytes(buf);
                    buf.release();
                }

                ChannelFuture f = ctx.channel().writeAndFlush(httpResponse);
                if (httpResponse.status().code() != 200){
                    f.addListener(ChannelFutureListener.CLOSE);
                }

                return;
            }

            WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory("ws:/" + ctx.channel() + "/websocket", null, false);

            handshaker = wsFactory.newHandshaker(httpRequest);

            if (null == handshaker){
                WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
            } else{
                handshaker.handshake(ctx.channel(), httpRequest);
            }

            return;

        }

        if (msg instanceof WebSocketFrame){

            WebSocketFrame webSocketFrame = (WebSocketFrame) msg;

            if (webSocketFrame instanceof CloseWebSocketFrame){
                handshaker.close(ctx.channel(), (CloseWebSocketFrame) webSocketFrame.retain());
                return;
            }

            if (webSocketFrame instanceof PingWebSocketFrame) {
                ctx.channel().write(new PongWebSocketFrame(webSocketFrame.content().retain()));
                return;
            }

            if (!(webSocketFrame instanceof TextWebSocketFrame)){
                throw new Exception("仅支持文本格式");
            }

            String request = ((TextWebSocketFrame) webSocketFrame).text();
            System.out.println("服务端收到："+request);

            ClientMsgProtocol clientMsgProtocol = JSONUtil.toBean(request, ClientMsgProtocol.class);

            if (1 == clientMsgProtocol.getType()){
                ctx.channel().writeAndFlush(MsgUtil.buildMsgOwner(ctx.channel().id().toString()));
                return;
            }

            if (2 == clientMsgProtocol.getType()){
                TextWebSocketFrame textWebSocketFrame = MsgUtil.buildMsgAll(ctx.channel().id().toString(), clientMsgProtocol.getMsgInfo());
                ChannelHandler.channelGroup.writeAndFlush(textWebSocketFrame);
            }


        }



        super.channelRead(ctx, msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        System.out.println("error:" + cause.getMessage());
    }
}
