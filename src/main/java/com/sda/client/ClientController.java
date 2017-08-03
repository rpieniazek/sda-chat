package com.sda.client;

import com.sda.client.view.ClientView;
import com.sda.commons.model.*;
import com.sda.commons.MessageMapperSingleton;

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
    private IncomingEventsHandler incomingEventsHandler;
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
            waitForEvent();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMessage(String message, String receiverName) {
        message = encrypt(message);
        MessageDto messageDto = new MessageDto(message);
        messageDto.setSenderName(username);
        messageDto.setReceiverName(receiverName);
        out.println(messageMapper.mapToJson(messageDto));
    }

    private void initView() {
        incomingEventsHandler = new ClientView(this);
    }

    private void initSocket() throws IOException {
        Socket socket = new Socket(getString(SERVER_IP), getInt(SERVER_PORT));
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    private void sendConnectRequest() {
        ConnectionDto dto = new ConnectionDto();
        dto.setUsername(username);
        out.println(messageMapper.mapToJson(dto));
    }

    private void waitForEvent() throws IOException {
        System.out.println("waiting for events");
        String event;
        while ((event = in.readLine()) != null) {
            System.out.printf("received event%s\n", event);
            resolveEvent(event);
        }
    }

    private void resolveEvent(String inMessage) {
        AbstractDto abstractDto = messageMapper.mapFromJson(inMessage);
        switch (abstractDto.getEventType()) {
            case MESSAGE:
                handleMessage((MessageDto) abstractDto);
                break;
            case USERS_UPDATE:
                handleUsersListUpdate((UsersDto) abstractDto);
                break;
        }
    }

    private void handleUsersListUpdate(UsersDto usersDto) {
        incomingEventsHandler.refreshUsers(usersDto.getUsernames());
    }

    private void handleMessage(MessageDto messageDto) {
        messageDto.setContent(decrypt(messageDto.getContent()));
        incomingEventsHandler.handleMessage(messageDto);
    }
}
