package com.totoro;

import com.totoro.server.ServerSocket;

/**
 * @author:totoro
 * @createDate:2023/3/28
 * @description:
 */
public class StartServer {

    public static void main(String[] args) {

        new Thread(new ServerSocket()).start();

    }
}
