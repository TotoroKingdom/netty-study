package com.totoro.test2.demo07_listener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:totoro
 * @createDate:2022/11/25
 * @description:
 */
public class ListenerSupport {

    private List<Listener> list = new ArrayList<>();

    public void addListener(Listener listener){
        list.add(listener);
    }

    public void triggerEvent(Event event){
        for (Listener listener : list) {
            listener.onClick(event);
        }
    }





}
