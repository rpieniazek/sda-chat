package com.sda.server;

import com.sda.commons.config.ConfigService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sda.commons.config.ConfigKeys.SERVER_PORT;
import static com.sda.commons.config.ConfigService.*;

/**
 * Created by RENT on 2017-07-24.
 */
public class Server {
    Map<String, ClientHandler> clients;

    public Server() {
        clients = new HashMap<>();
        try {
            ServerSocket listener = new ServerSocket(getInt(SERVER_PORT));
            System.out.println("Server listening");
            while (true) {
                Socket socket = listener.accept();
                ClientHandler clientHandler = new ClientHandler(socket, clients);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
