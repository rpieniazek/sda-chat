package com.sda.client;

import com.sda.commons.MessageDto;

import java.io.*;
import java.net.Socket;

import static com.sda.commons.Encrypter.*;
import static com.sda.commons.config.ConfigKeys.SERVER_IP;

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
            System.out.println("in client controller");
            System.out.println(System.getProperty(SERVER_IP));
            System.out.println(System.getProperty("server.port"));
            waitForResponse();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        Socket socket = new Socket(HOST_ADDRESS, PORT);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    private void initView() {
        incomingMessageHandler = new ClientView(this);
    }
}
