package com.sda.server;

import com.sda.commons.model.*;
import com.sda.commons.MessageMapperSingleton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

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
            waitForEvent();
            System.out.println("exit");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printMessageToClient(String requestMessage) {
        out.println(requestMessage);
    }

    private void waitForEvent() throws IOException {
        String requestMessage;
        while ((requestMessage = in.readLine()) != null) {
            handleEvent(requestMessage);
        }
    }

    private void handleEvent(String requestMessage) {
        System.out.println("handling message from client " + requestMessage);
        AbstractDto abstractDto = messageMapper.mapFromJson(requestMessage);
        switch (abstractDto.getEventType()) {
            case CONNECT: {
                connectNewUser((ConnectionDto) abstractDto);
                break;
            }
            case MESSAGE: {
                handleMessage(requestMessage, (MessageDto) abstractDto);
                break;
            }
        }
    }

    private void handleMessage(String requestMessage, MessageDto messageDto) {
        String receiverName = messageDto.getReceiverName();
        if (receiverName == null) {
            sendToAll(requestMessage);
        } else {
            sendToOne(receiverName, requestMessage);
        }
    }

    private void sendToOne(String receiverName, String requestMessage) {
        ClientHandler receiverHandler = clients.get(receiverName);
        receiverHandler.printMessageToClient(requestMessage);
    }

    private void connectNewUser(ConnectionDto connectionDto) {
        String senderName = connectionDto.getUsername();
        notifyClientsAboutNewUser(senderName);
        clients.put(senderName, this);
        notifyUserAboutAllUsers();
    }

    private void notifyClientsAboutNewUser(String senderName) {
        MessageDto newClientDto = new MessageDto();
        String encryptedConnectedInfo = encrypt(format("User %s connected", senderName));
        newClientDto.setContent(encryptedConnectedInfo);
        newClientDto.setSenderName("Server");
        sendToAll(messageMapper.mapToJson(newClientDto));
    }

    private void notifyUserAboutAllUsers() {
        UsersDto usersDto = new UsersDto();
        List<String> usersList = new ArrayList<>(clients.keySet());
        usersDto.setUsernames(usersList);
        sendToAll(messageMapper.mapToJson(usersDto));
    }

    private void sendToAll(String requestMessage) {
        for (ClientHandler client : clients.values()) {
            client.printMessageToClient(requestMessage);
        }
    }
}
