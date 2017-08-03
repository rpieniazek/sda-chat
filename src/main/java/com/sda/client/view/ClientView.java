package com.sda.client.view;

import com.sda.client.ClientController;
import com.sda.client.IncomingEventsHandler;
import com.sda.client.LoginCommand;
import com.sda.client.MessageCommand;
import com.sda.commons.model.MessageDto;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by RENT on 2017-07-24.
 */
public class ClientView implements IncomingEventsHandler {
    public static final String ALL = "ALL";
    private JFrame mainFrame = new JFrame("SDA Chat");
    private LoginPanel loginPanel;
    private MessagePanel messagePanel;
    private UsersList usersList;

    private MessageCommand messageCommand;
    private LoginCommand loginCommand;


    public ClientView(ClientController clientController) {
        this.messageCommand = clientController;
        this.loginCommand = clientController;

        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager
                        .getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            display();
        });
    }

    @Override
    public void handleMessage(MessageDto messageDto) {
        String receiverName = messageDto.getReceiverName();
        if (receiverName == null || receiverName.equals(ALL)) {
            messagePanel.addToMessageList(messageDto.toString());
        } else {
            messagePanel.addPrivateMessageToList(messageDto.toString());
        }
    }

    public void sendMessage(String message) {
        messageCommand.sendMessage(message, usersList.getSelectedValue());
    }

    public void setDefaultButton(JButton button) {
        mainFrame.getRootPane().setDefaultButton(button);
    }

    @Override
    public void refreshUsers(List<String> usernames) {
        String[] usernamesAsArray = usernames.toArray(new String[usernames.size()]);
        usersList.setListData(usernamesAsArray);
        usersList.repaint();
    }

    public void onSignIn(String loginName) {
        swapViewOnSignIn();

        new Thread(() -> loginCommand.connectUser(loginName)).start();
    }

    private void swapViewOnSignIn() {
        loginPanel.setVisible(false);
        messagePanel = new MessagePanel(this);
        usersList = new UsersList();
        mainFrame.setLayout(new BorderLayout());
        mainFrame.add(usersList, BorderLayout.LINE_START);
        mainFrame.add(messagePanel, BorderLayout.LINE_END);
    }

    private void display() {
        configMainPanel();
        loginPanel = new LoginPanel(this);
        mainFrame.add(loginPanel);
    }

    private void configMainPanel() {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(600, 300);
        mainFrame.setVisible(true);
    }
}