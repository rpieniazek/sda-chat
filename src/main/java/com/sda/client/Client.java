package com.sda.client;

import jdk.internal.util.xml.impl.Input;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by RENT on 2017-07-24.
 */
public class Client {
    public static void main(String[] args) {

        Socket socket = null;
        try {
            socket = new Socket("192.168.16.138", 8080);
//            PrintStream printStream = new PrintStream(socket.getOutputStream());
//            printStream.println("hi");

            //second method:
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("test1");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
