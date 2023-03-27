package com.totoro.future;

import com.totoro.msg.Response;

import java.util.concurrent.Future;

/**
 * @author:totoro
 * @createDate:2023/3/27
 * @description:
 */
public interface WriteFuture<T> extends Future<T> {

    Throwable cause();

    void setCause(Throwable cause);

    boolean isWriteSuccess();

    void setWriteResult(boolean result);

    String requestId();

    T response();

    void setResponse(Response response);

    boolean isTimeout();

}
