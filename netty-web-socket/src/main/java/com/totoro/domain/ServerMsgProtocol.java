package com.totoro.domain;

import lombok.Data;

/**
 * @author:totoro
 * @createDate:2023/3/27
 * @description:
 */
@Data
public class ServerMsgProtocol {

    private int type;
    private String channelId;
    private String userHeadImg;
    private String msgInfo;

}
