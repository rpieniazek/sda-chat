package com.sda.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by RENT on 2017-07-24.
 */
public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket listener = new ServerSocket(8888);
            System.out.println("Server listening");
            while (true) {
                Socket socket = listener.accept();
                ClientHandler clientHandler = new ClientHandler(socket);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
