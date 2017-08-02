package com.sda.client;

import com.sda.commons.model.MessageDto;

import java.util.List;
import java.util.Set;

/**
 * Created by RENT on 2017-07-25.
 */
public interface IncomingEventsHandler {
    void handleMessage (MessageDto messageDto);
    void refreshUsers(List<String> usernames);
}
