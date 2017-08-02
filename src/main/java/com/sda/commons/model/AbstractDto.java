package com.sda.commons.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import java.time.LocalTime;

/**
 * Created by RENT on 2017-08-02.
 */
public abstract class AbstractDto {
    private MessageType messageType;

    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime time;

    protected AbstractDto(MessageType messageType) {
        this.messageType = messageType;
        this.time = LocalTime.now();
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public MessageType getMessageType() {
        return messageType;
    }

}
