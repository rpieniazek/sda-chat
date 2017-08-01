package com.sda.client;

import com.sda.commons.MessageDto;

import java.util.Set;

/**
 * Created by RENT on 2017-07-25.
 */
public interface IncomingEventsHandler {
    void handleMessage (MessageDto messageDto);
    void refreshUsers(Set<String> usernames);
}
