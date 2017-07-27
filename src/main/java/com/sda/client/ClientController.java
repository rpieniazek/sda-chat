package com.sda.client;

import com.sda.commons.MessageDto;

import java.io.*;
import java.net.Socket;
import java.time.LocalTime;

/**
 * Created by RENT on 2017-07-24.
 */
public class ClientController implements MessageCommand {
    private static final String HOST_ADDRESS = "127.0.0.1";
    private static final Integer PORT = 8888;

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

    public void waitForResponse() throws IOException {
        String inMessage;
        System.out.println("waiting for messages");
        while ((inMessage = in.readLine()) != null) {
            System.out.println(" message from server " + inMessage);
            incomingMessageHandler.handleMessage(inMessage);
        }
    }

    private void initSocket() throws IOException {
        Socket socket = new Socket(HOST_ADDRESS, PORT);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    private void initView() {
        incomingMessageHandler = new ClientView(this);
    }

    @Override
    public void sendMessage(String message) {
        MessageDto messageDto = new MessageDto();
        out.println(message);
    }
}
