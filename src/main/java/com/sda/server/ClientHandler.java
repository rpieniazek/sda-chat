package com.sda.server;

import com.sda.commons.MessageDto;
import com.sda.commons.MessageMapperSingleton;
import com.sda.commons.MessageType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

/**
 * Created by RENT on 2017-07-25.
 */
public class ClientHandler implements Runnable {
    private Map<String, ClientHandler> clients;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private MessageMapperSingleton messageMapper;

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
        boolean isConfigMessage = messageDto.getMessageType().equals(MessageType.CONFIG);
        if (isConfigMessage) {
            clients.put(messageDto.getSenderName(), this);
        } else {
            sendToAll(requestMessage);
        }
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
