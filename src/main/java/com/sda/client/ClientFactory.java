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
            try {
                UIManager.setLookAndFeel(UIManager
                        .getSystemLookAndFeelClassName());
            } catch (Exception e) {

                e.printStackTrace();
            }

            ClientView mainGUI = new ClientView();
            ClientController clientController = new ClientController();
            mainGUI.setMessageCommand(clientController);
            clientController.setIncomingMessageHandler(mainGUI);
            mainGUI.display();

            try {
                clientController.waitForResponse();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}