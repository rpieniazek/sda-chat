package com.sda.client;

import com.sda.commons.MessageDto;
import com.sda.commons.MessageMapperSingleton;
import com.sda.commons.MessageType;

import java.io.*;
import java.net.Socket;

import static com.sda.commons.Encrypter.*;
import static com.sda.commons.config.ConfigKeys.SERVER_IP;
import static com.sda.commons.config.ConfigKeys.SERVER_PORT;
import static com.sda.commons.config.ConfigService.*;

/**
 * Created by RENT on 2017-07-24.
 */
public class ClientController implements MessageCommand, LoginCommand {
    private IncomingMessageHandler incomingMessageHandler;
    private final MessageMapperSingleton messageMapper;
    private String username;

    private BufferedReader in;
    private PrintWriter out;

    public ClientController() {
        messageMapper = MessageMapperSingleton.getInstance();
        initView();
    }

    @Override
    public void connectUser(String username) {
        this.username = username;
        try {
            initSocket();
            sendConnectRequest();
            waitForResponse();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMessage(String message) {
        message = encrypt(message);
        MessageDto messageDto = new MessageDto(message);
        messageDto.setSenderName(username);
        out.println(messageMapper.mapToJson(messageDto));
    }

    private void sendConnectRequest() {
        MessageDto dto = new MessageDto();
        dto.setSenderName(username);
        dto.setMessageType(MessageType.CONNECT);
        out.println(messageMapper.mapToJson(dto));
    }

    private void waitForResponse() throws IOException {
        String inMessage;
        System.out.println("waiting for messages");
        while ((inMessage = in.readLine()) != null) {
            System.out.printf("received message%s\n", inMessage);
            MessageDto messageDto = messageMapper.mapFromJson(inMessage);
            if (isNormal(messageDto)) {
                messageDto.setContent(decrypt(messageDto.getContent()));
                incomingMessageHandler.handleMessage(messageDto);
            } else {
                System.out.println(messageDto.getUsernames());

            }
        }
    }

    private boolean isNormal(MessageDto dto) {
        return dto.getMessageType().equals(MessageType.NORMAL);
    }

    private void initSocket() throws IOException {
        Socket socket = new Socket(getString(SERVER_IP), getInt(SERVER_PORT));
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    private void initView() {
        incomingMessageHandler = new ClientView(this);
    }
}
