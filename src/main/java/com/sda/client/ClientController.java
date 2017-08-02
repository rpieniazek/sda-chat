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
        ConnectionDto dto = new ConnectionDto();
        dto.setUsername(username);
        out.println(messageMapper.mapToJson(dto));
    }

    private void waitForResponse() throws IOException {
        String inMessage;
        System.out.println("waiting for messages");
        while ((inMessage = in.readLine()) != null) {
            System.out.printf("received message%s\n", inMessage);
            AbstractDto abstractDto = messageMapper.mapFromJson(inMessage);
            if (isNormal(abstractDto)) {
                MessageDto messageDto = (MessageDto) abstractDto;
                messageDto.setContent(decrypt(messageDto.getContent()));
                incomingEventsHandler.handleMessage(messageDto);
            } else {
                UsersDto usersDto = (UsersDto) abstractDto;
                incomingEventsHandler.refreshUsers(usersDto.getUsernames());
            }
        }
    }

    private boolean isNormal(AbstractDto dto) {
        return dto.getEventType().equals(EventType.MESSAGE);
    }

    private void initSocket() throws IOException {
        Socket socket = new Socket(getString(SERVER_IP), getInt(SERVER_PORT));
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    private void initView() {
        incomingEventsHandler = new ClientView(this);
    }
}
