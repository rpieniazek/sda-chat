package com.sda.commons.model;

/**
 * Created by RENT on 2017-08-02.
 */
public class ConnectionDto extends AbstractDto {
    private String username;

    public ConnectionDto() {
        super(EventType.CONNECT);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
