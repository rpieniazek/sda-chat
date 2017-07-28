package com.sda.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.commons.MessageDto;

import java.io.IOException;

/**
 * Created by RENT on 2017-07-28.
 */
public class MessageMapperSingleton {
    private static MessageMapperSingleton INSTANCE ;
    private ObjectMapper objectMapper;

    private MessageMapperSingleton() {
        objectMapper = new ObjectMapper();
    }

    public static MessageMapperSingleton getInstance(){
        if(INSTANCE == null){
            INSTANCE = new MessageMapperSingleton();
        }
        return INSTANCE;
    }

    public String mapToJson(String message) {
        String messageAsJson = null;
        MessageDto messageDto = new MessageDto(message);
        try {
            messageAsJson = objectMapper.writeValueAsString(messageDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return messageAsJson;
    }

    public MessageDto mapFromJson(String inMessage) {
        MessageDto messageDto = null;
        try {
            messageDto = objectMapper.readValue(inMessage, MessageDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return messageDto;
    }
}
