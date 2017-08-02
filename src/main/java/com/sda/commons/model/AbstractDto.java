package com.sda.commons.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import java.time.LocalTime;

import static com.fasterxml.jackson.annotation.JsonSubTypes.*;

/**
 * Created by RENT on 2017-08-02.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @Type(value = ConnectionDto.class),
        @Type(value = MessageDto.class),
        @Type(value = UsersDto.class)
})
public abstract class AbstractDto {
    private EventType eventType;

    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime time;

    public AbstractDto() {
    }

    protected AbstractDto(EventType eventType) {
        this.eventType = eventType;
        this.time = LocalTime.now();
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public EventType getEventType() {
        return eventType;
    }

}
