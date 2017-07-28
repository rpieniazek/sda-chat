package com.sda.client;

import com.sda.commons.MessageDto;

import javax.swing.*;
import java.awt.*;

/**
 * Created by RENT on 2017-07-24.
 */
public class ClientView implements IncomingMessageHandler {
    private String appName = "SDA Chat";
    private JFrame newFrame = new JFrame(appName);
    private JButton sendMessage;
    private JTextField messageBox; //wpisywana wiadomosc
    private JTextArea chatBox; //lista wiadomosci
    private MessageCommand messageCommand;

    public ClientView(ClientController clientController) {
        this.messageCommand = clientController;

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

    public void display() {

        JPanel chatPanel = createChatPanel();
        JList usersList = createListPanel();

        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setSize(470, 300);
        newFrame.setVisible(true);
        newFrame.getRootPane().setDefaultButton(sendMessage);

        newFrame.setLayout(new BorderLayout());
        newFrame.add(usersList, BorderLayout.WEST);
        newFrame.add(chatPanel, BorderLayout.EAST);
    }

    private JList createListPanel() {
        DefaultListModel<String> usersListModel = new DefaultListModel<>();
        usersListModel.addElement("Rafal");
        usersListModel.addElement("Tomek");
        usersListModel.addElement("Adam");

        return new JList<>(usersListModel);
    }

    private JPanel createChatPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.BLUE);
        southPanel.setLayout(new GridBagLayout());

        messageBox = new JTextField(30);
        messageBox.requestFocusInWindow();

        sendMessage = new JButton("Send Message");
        sendMessage.addActionListener(event -> {
            sendMessage();
        });

        chatBox = new JTextArea();
        chatBox.setEditable(false);
        chatBox.setFont(new Font("Serif", Font.PLAIN, 15));
        chatBox.setLineWrap(true);

        mainPanel.add(new JScrollPane(chatBox), BorderLayout.CENTER);

        GridBagConstraints left = new GridBagConstraints();
        left.anchor = GridBagConstraints.LINE_START;
        left.fill = GridBagConstraints.HORIZONTAL;
        left.weightx = 512.0D;
        left.weighty = 1.0D;

        GridBagConstraints right = new GridBagConstraints();
        right.insets = new Insets(0, 10, 0, 0);
        right.anchor = GridBagConstraints.LINE_END;
        right.fill = GridBagConstraints.NONE;
        right.weightx = 1.0D;
        right.weighty = 1.0D;

        southPanel.add(messageBox, left);
        southPanel.add(sendMessage, right);

        mainPanel.add(BorderLayout.SOUTH, southPanel);
        return mainPanel;
    }

    private void sendMessage() {
        String inputText = messageBox.getText();
        if (inputText.length() >= 1) {
            messageCommand.sendMessage(inputText);
            messageBox.setText("");
        }
        messageBox.requestFocusInWindow();
    }

    @Override
    public void handleMessage(MessageDto messageDto) {
        chatBox.append(messageDto.toString());
    }
}