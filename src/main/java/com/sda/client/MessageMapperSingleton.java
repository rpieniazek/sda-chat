package com.sda.client;

/**
 * Created by RENT on 2017-07-28.
 */
public class MessageMapperSingleton {
    private static MessageMapperSingleton INSTANCE ;

    private MessageMapperSingleton() {
    }

    public static MessageMapperSingleton getInstance(){
        if(INSTANCE == null){
            INSTANCE = new MessageMapperSingleton();
        }
        return INSTANCE;
    }
}
