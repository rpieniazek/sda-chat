package com.sda.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by RENT on 2017-07-24.
 */
public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket listener  = new ServerSocket(8888);
            System.out.println("Server listening");
            while(true){
                Socket socket = listener.accept();
                new ClientHandler(socket).run();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
