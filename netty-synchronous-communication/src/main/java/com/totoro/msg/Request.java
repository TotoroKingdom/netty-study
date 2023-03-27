package com.totoro.msg;

import lombok.Data;

/**
 * @author:totoro
 * @createDate:2023/3/27
 * @description:
 */
@Data
public class Request {

    private String requestId;

    private Object result;
}
