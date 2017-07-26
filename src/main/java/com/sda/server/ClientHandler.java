package com.sda.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

/**
 * Created by RENT on 2017-07-25.
 */
public class ClientHandler implements Runnable {
    private List<ClientHandler> clients;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ClientHandler(Socket socket, List<ClientHandler> clients) {
        this.socket = socket;
        this.clients = clients;
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
            System.out.println("message from client " + requestMessage);
            printMessageToClient(requestMessage);

        }
    }

    public void printMessageToClient(String requestMessage) {
        out.println(requestMessage);
    }
}
