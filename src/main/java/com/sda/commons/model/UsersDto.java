package com.sda.commons.model;

import com.sda.commons.MessageType;

import java.util.Set;

/**
 * Created by RENT on 2017-08-02.
 */
public class UsersDto extends AbstractDto {
    private Set<String> usernames;

    protected UsersDto() {
        super(MessageType.USERS_UPDATE);
    }

    public Set<String> getUsernames() {
        return usernames;
    }

    public void setUsernames(Set<String> usernames) {
        this.usernames = usernames;
    }

}
