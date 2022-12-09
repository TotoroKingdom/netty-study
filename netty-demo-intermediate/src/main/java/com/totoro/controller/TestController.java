package com.totoro.controller;

import com.totoro.netty.NettyServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author:totoro
 * @createDate:2022/12/9
 * @description:
 */
@RestController
public class TestController {

    @Resource
    private NettyServer nettyServer;

    @RequestMapping("localAddress")
    public String localAddress(){
        return "nettyServer localAddress" + nettyServer.getChannel().localAddress();
    }

    @RequestMapping("isOpen")
    public String isOpen(){
        return "nettyServer isOpen" + nettyServer.getChannel().isOpen();
    }
}
