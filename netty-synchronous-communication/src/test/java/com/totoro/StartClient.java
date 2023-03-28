package com.totoro;

import cn.hutool.json.JSONUtil;
import com.totoro.client.ClientSocket;
import com.totoro.future.SyncWrite;
import com.totoro.msg.Request;
import com.totoro.msg.Response;
import io.netty.channel.ChannelFuture;

/**
 * @author:totoro
 * @createDate:2023/3/28
 * @description:
 */
public class StartClient {

    private static ChannelFuture future;


    public static void main(String[] args) {

        ClientSocket client = new ClientSocket();
        new Thread(client).start();

        while (true){
            try{
                if (null == future){
                    future = client.getFuture();
                    Thread.sleep(500);
                    continue;
                }

                Request request = new Request();
                request.setResult("用户信息查询");
                SyncWrite s = new SyncWrite();
                Response response = s.writeAndSync(future.channel(), request, 1000);
                System.out.println(JSONUtil.parse(response));
                Thread.sleep(1000);
            } catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
