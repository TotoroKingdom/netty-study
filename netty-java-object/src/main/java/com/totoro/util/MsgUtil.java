package com.totoro.util;

import com.totoro.domain.MsgInfo;

/**
 * @author:totoro
 * @createDate:2022/12/16
 * @description:
 */
public class MsgUtil {

    public static MsgInfo buildMsg(String channelId, String msgContent){
        return new MsgInfo(channelId, msgContent);
    }
}
