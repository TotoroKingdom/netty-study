package com.totoro.server;

import com.totoro.msg.Request;
import com.totoro.msg.Response;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * @author:totoro
 * @createDate:2023/3/27
 * @description:
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object obj) throws Exception {

        Request msg = (Request) obj;

        Response response = new Response();
        response.setRequestId(msg.getRequestId());
        response.setParam(msg.getResult() + "请求成功");
        ctx.writeAndFlush(response);

        ReferenceCountUtil.release(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.flush();
    }
}
