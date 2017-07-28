package com.sda.server;

import com.sda.commons.config.ConfigService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static com.sda.commons.config.ConfigKeys.SERVER_PORT;
import static com.sda.commons.config.ConfigService.*;

/**
 * Created by RENT on 2017-07-24.
 */
public class Server {
    List<ClientHandler> clients;

    public Server() {
        clients = new ArrayList<>();
        try {
            ServerSocket listener = new ServerSocket(getInt(SERVER_PORT));
            System.out.println("Server listening");
            while (true) {
                Socket socket = listener.accept();
                ClientHandler clientHandler = new ClientHandler(socket, clients);
                clients.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
