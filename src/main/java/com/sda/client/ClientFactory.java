package com.sda.client;

import javax.swing.*;
import java.io.IOException;

/**
 * Created by RENT on 2017-07-25.
 */
public class ClientFactory {
    public static void main(String[] args) {
        create();
    }

    public static void create() {

        SwingUtilities.invokeLater(() -> {
            ClientView mainGUI;

            try {
                UIManager.setLookAndFeel(UIManager
                        .getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            mainGUI = new ClientView();
            ClientController clientController = new ClientController(mainGUI);
            System.out.println("sending message");
            clientController.sendMessage("test");
//            try {
//                clientController.waitForResponse();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        });


    }
}