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
                    new Thread(()->{
                        try {
                            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                            String requestMessage;
                            while ((requestMessage = in.readLine()) != null){
                                System.out.println(" message from server "+ requestMessage);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
