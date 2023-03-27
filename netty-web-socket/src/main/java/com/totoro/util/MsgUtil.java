package com.totoro.util;

import cn.hutool.json.JSONUtil;
import com.totoro.domain.ServerMsgProtocol;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * @author:totoro
 * @createDate:2023/3/27
 * @description:
 */
public class MsgUtil {

    public static TextWebSocketFrame buildMsgAll(String channelId, String msgInfo){

        int i = Math.abs(channelId.hashCode() % 10);

        ServerMsgProtocol msg = new ServerMsgProtocol();

        msg.setType(2);
        msg.setChannelId(channelId);
        msg.setUserHeadImg("head" + i + ".jpg");
        msg.setMsgInfo(msgInfo);

        return new TextWebSocketFrame(JSONUtil.toJsonPrettyStr(msg));
    }

    public static TextWebSocketFrame buildMsgOwner(String channelId){
        ServerMsgProtocol msg = new ServerMsgProtocol();
        msg.setType(1);
        msg.setChannelId(channelId);
        return new TextWebSocketFrame(JSONUtil.toJsonPrettyStr(msg));
    }
}
