package com.sda.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RENT on 2017-07-24.
 */
public class Server {
    List<ClientHandler> clients;

    public Server() {
        clients = new ArrayList<>();
        try {
            ServerSocket listener = new ServerSocket(8888);
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

    public static void main(String[] args) {
        new Server();
    }
}
