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

    private BufferedReader in;
    private PrintWriter out;
    private IncomingMessageHandler incomingMessageHandler;

    public ClientController() {
        try {
            initSocket();
            initView();
            waitForResponse();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connectUser(String username) {
        MessageDto dto = new MessageDto();
        dto.setSenderName(username);
        dto.setMessageType(MessageType.CONFIG);

    }

    public void waitForResponse() throws IOException {
        String inMessage;
        System.out.println("waiting for messages");
        while ((inMessage = in.readLine()) != null) {

            MessageMapperSingleton mapper = MessageMapperSingleton.getInstance();
            MessageDto messageDto = mapper.mapFromJson(inMessage);
            messageDto.setContent(decrypt(messageDto.getContent()));
            incomingMessageHandler.handleMessage(messageDto);
        }
    }

    @Override
    public void sendMessage(String message) {
        message = encrypt(message);
        MessageMapperSingleton mapper = MessageMapperSingleton.getInstance();
        out.println(mapper.mapToJson(message));
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
