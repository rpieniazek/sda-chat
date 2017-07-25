package com.sda.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by RENT on 2017-07-25.
 */
public class ClientHandler implements Runnable {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("Client handler created");
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            handleMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleMessage() throws IOException {
        String requestMessage;
        while ((requestMessage = in.readLine()) != null) {
            System.out.println(" message from server " + requestMessage);
            out.println(requestMessage);
        }
    }
}
