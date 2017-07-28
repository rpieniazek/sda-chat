package com.sda.client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

/**
 * Created by RENT on 2017-07-25.
 */
public class ClientLauncher {

    public static void main(String[] args) {
        try {
            FileInputStream fileInputStream = new FileInputStream("application.properties");
            Properties properties = new Properties(System.getProperties());
            properties.load(fileInputStream);
            System.setProperties(properties);
            System.out.println(System.getProperty("server.ip"));
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        new ClientController();
    }
}