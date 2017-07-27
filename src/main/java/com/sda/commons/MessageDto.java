package com.sda.commons;

import java.time.LocalTime;

/**
 * Created by RENT on 2017-07-27.
 */
public class MessageDto {
    private String name;
    private String content;
    private LocalTime time;

    public MessageDto() {
    }

    public MessageDto(String content) {
        this.content = content;
        this.name = "Rafal";
        this.time = LocalTime.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
