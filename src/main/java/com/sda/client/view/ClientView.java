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
    private JFrame mainFrame = new JFrame("SDA Chat");
    private LoginPanel loginPanel;
    private MessagePanel messagePanel;

    private MessageCommand messageCommand;
    private LoginCommand loginCommand;
    private JList<String> usersList;


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
        messagePanel.addToMessageList(messageDto.toString());
    }

    public void sendMessage(String message){
        messageCommand.sendMessage(message);
    }

    public void setDefaultButton(JButton button){
        mainFrame.getRootPane().setDefaultButton(button);
    }

    @Override
    public void refreshUsers(List<String> usernames) {
        String[] usernamesAsArray = usernames.toArray(new String[usernames.size()]);
        usersList.setListData(usernamesAsArray);
        usersList.repaint();
    }

    private void display() {
        configMainPanel();
        loginPanel = new LoginPanel(this);
        mainFrame.add(loginPanel);
    }

    public void onSignIn(String loginName) {
        loginPanel.setVisible(false);
        messagePanel = new MessagePanel(this);
        usersList = createListPanel();
        mainFrame.setLayout(new BorderLayout());
        mainFrame.add(usersList, BorderLayout.LINE_START);
        mainFrame.add(messagePanel, BorderLayout.LINE_END);

        new Thread(() -> loginCommand.connectUser(loginName)).start();
    }

    private JList createListPanel() {
        DefaultListModel<String> model = new DefaultListModel<>();
        JList<String> userListPanel = new JList<String>(model);
        userListPanel.setSize(200, 300);
        userListPanel.setFixedCellWidth(222);
        return userListPanel;
    }

    private void configMainPanel() {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(600, 300);
        mainFrame.setVisible(true);
    }
}