package com.sda.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.commons.Encrypter;
import com.sda.commons.MessageDto;

import java.io.*;
import java.net.Socket;

/**
 * Created by RENT on 2017-07-24.
 */
public class ClientController implements MessageCommand {
    private static final String HOST_ADDRESS = "127.0.0.1";
    private static final Integer PORT = 8888;

    private BufferedReader in;
    private PrintWriter out;
    private IncomingMessageHandler incomingMessageHandler;
    private ObjectMapper objectMapper;

    public ClientController() {
        try {
            initSocketAndObjectMapper();
            initView();
            waitForResponse();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void waitForResponse() throws IOException {
        String inMessage;
        System.out.println("waiting for messages");
        while ((inMessage = in.readLine()) != null) {
            MessageDto messageDto = convertMessageFromJson(inMessage);
            messageDto.setContent(Encrypter.decrypt(messageDto.getContent()));
            incomingMessageHandler.handleMessage(messageDto);
        }
    }

    @Override
    public void sendMessage(String message) {
        message = Encrypter.encrypt(message);
        out.println(convertMessageToJson(message));
    }

    private void initSocketAndObjectMapper() throws IOException {
        Socket socket = new Socket(HOST_ADDRESS, PORT);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        objectMapper = new ObjectMapper();
    }

    private void initView() {
        incomingMessageHandler = new ClientView(this);
    }


    private String convertMessageToJson(String message) {
        String messageAsJson = null;
        MessageDto messageDto = new MessageDto(message);
        try {
            messageAsJson = objectMapper.writeValueAsString(messageDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return messageAsJson;
    }

    private MessageDto convertMessageFromJson(String inMessage) {
        MessageDto messageDto = null;
        try {
            messageDto = objectMapper.readValue(inMessage, MessageDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return messageDto;
    }
}
