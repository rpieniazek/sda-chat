package com.sda.client;

import com.sda.commons.MessageDto;

/**
 * Created by RENT on 2017-07-25.
 */
public interface IncomingMessageHandler {
    void handleMessage (MessageDto messageDto);
}
