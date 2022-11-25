package com.totoro.test2.demo07_listener;

/**
 * @author:totoro
 * @createDate:2022/11/25
 * @description:
 */
public class Event {

    private String data;

    private String type;

    public Event(String data, String type){
        this.data = data;
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
