package com.sda.commons.model;

import static java.lang.String.*;

/**
 * Created by RENT on 2017-07-27.
 */

public class MessageDto extends AbstractDto {
    private String senderName;
    private String receiverName;
    private String content;

    public MessageDto() {
        super(EventType.MESSAGE);
    }

    public MessageDto(String content) {
        this();
        this.content = content;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return format("<%s|%s> %s\n", getTime().toString().substring(0, 8), senderName, content);
    }
}
