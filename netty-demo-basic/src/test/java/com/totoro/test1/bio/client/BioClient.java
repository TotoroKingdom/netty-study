package com.totoro.test1.bio.client;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * @author:totoro
 * @createDate:2022/11/23
 * @description:
 */
public class BioClient {
    public static void main(String[] args) {

        try {
            Socket socket = new Socket("localhost", 10089);
            System.out.println("bio client start done");
            BioClientHandler handler = new BioClientHandler(socket, Charset.forName("GBK"));
            handler.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
