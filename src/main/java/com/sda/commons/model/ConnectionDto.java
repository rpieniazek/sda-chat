package com.sda.commons.model;

import com.sda.commons.MessageType;

/**
 * Created by RENT on 2017-08-02.
 */
public class ConnectionDto extends AbstractDto {
    private String username;

    protected ConnectionDto() {
        super(MessageType.CONNECT);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
