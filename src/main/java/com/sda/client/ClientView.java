package com.sda.client;

import javax.swing.*;
import java.awt.*;

/**
 * Created by RENT on 2017-07-24.
 */
public class ClientView implements IncomingMessageHandler {
    String appName = "SDA Chat";
    JFrame newFrame = new JFrame(appName);
    JButton sendMessage;
    JTextField messageBox; //wpisywana wiadomosc
    JTextArea chatBox; //lista wiadomosci
    MessageCommand messageCommand;

    public ClientView() {
//        display();
    }

    public void setMessageCommand(MessageCommand messageCommand) {
        this.messageCommand = messageCommand;
    }


    public void display() {

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.BLUE);
        southPanel.setLayout(new GridBagLayout());

        messageBox = new JTextField(30);
        messageBox.requestFocusInWindow();

        sendMessage = new JButton("Send Message");
        sendMessage.addActionListener(event -> {
            addMessageToList();
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

        newFrame.add(mainPanel);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setSize(470, 300);
        newFrame.setVisible(true);
    }

    private void addMessageToList() {
        String inputText = messageBox.getText();
        if (inputText.length() < 1) {
            // do nothing
        }  else {
            messageCommand.sendMessage(inputText);
            messageBox.setText("");
        }
        messageBox.requestFocusInWindow();
    }

    @Override
    public void handleMessage(String incomingMessage) {
        chatBox.append("<" + "Server" + ">:  " + incomingMessage
                + "\n");
    }
}