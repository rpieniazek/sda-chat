package com.sda.server;

import com.sda.commons.model.MessageDto;
import com.sda.commons.MessageMapperSingleton;
import com.sda.commons.model.MessageType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

import static com.sda.commons.Encrypter.*;
import static java.lang.String.*;

/**
 * Created by RENT on 2017-07-25.
 */
public class ClientHandler implements Runnable {
    private Socket socket;
    private Map<String, ClientHandler> clients;
    private MessageMapperSingleton messageMapper;

    private BufferedReader in;
    private PrintWriter out;

    public ClientHandler(Socket socket, Map<String, ClientHandler> clients) {
        this.socket = socket;
        this.clients = clients;
        this.messageMapper = MessageMapperSingleton.getInstance();
    }

    @Override
    public void run() {
        System.out.println("Client handler created");
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            waitForMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void waitForMessage() throws IOException {
        String requestMessage;
        while ((requestMessage = in.readLine()) != null) {
            handleMessage(requestMessage);
        }
    }

    private void handleMessage(String requestMessage) {
        System.out.println("handling message from client " + requestMessage);

        MessageDto messageDto = messageMapper.mapFromJson(requestMessage);
        if (isConnectMessage(messageDto)) {
            String senderName = messageDto.getSenderName();
            notifyClientsAboutNewUser(senderName);
            clients.put(senderName, this);
            notifyUserAboutAllUsers();
        } else {
            sendToAll(requestMessage);
        }
    }

    private void notifyUserAboutAllUsers() {
        MessageDto usersDto = new MessageDto();
        usersDto.setUsernames(clients.keySet());
        usersDto.setMessageType(MessageType.USERS_UPDATE);
        sendToAll(messageMapper.mapToJson(usersDto));
    }

    private void notifyClientsAboutNewUser(String senderName) {
        MessageDto newClientDto = new MessageDto();
        String encryptedConnectedInfo = encrypt(format("User %s connected", senderName));
        newClientDto.setContent(encryptedConnectedInfo);
        newClientDto.setMessageType(MessageType.NORMAL);
        newClientDto.setSenderName("Server");
        sendToAll(messageMapper.mapToJson(newClientDto));
    }

    private boolean isConnectMessage(MessageDto messageDto) {
        return messageDto.getMessageType().equals(MessageType.CONNECT);
    }

    private void sendToAll(String requestMessage) {
        for (ClientHandler client : clients.values()) {
            client.printMessageToClient(requestMessage);
        }
    }

    public void printMessageToClient(String requestMessage) {
        out.println(requestMessage);
    }
}
