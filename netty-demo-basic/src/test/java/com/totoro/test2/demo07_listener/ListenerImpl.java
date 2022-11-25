package com.totoro.test2.demo07_listener;

/**
 * @author:totoro
 * @createDate:2022/11/25
 * @description:
 */
public class ListenerImpl implements Listener {
    @Override
    public void onClick(Event event) {
        System.out.println("触发事件，type="+event.getType()+", data=" + event.getData());
    }
}
