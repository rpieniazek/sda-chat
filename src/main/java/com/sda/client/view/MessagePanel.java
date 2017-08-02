package com.sda.client.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by RENT on 2017-08-02.
 */
public class MessagePanel extends JPanel {
    private ClientView clientView;

    private JButton sendMessageButton;
    private JTextField messageInputField; //wpisywana wiadomosc
    private JTextArea messagesList; //lista wiadomosci

    public MessagePanel(ClientView clientView) {
        this.clientView = clientView;
        createMessagePanel();
    }

    public void addToMessageList(String line) {
        messagesList.append(line);
    }

    private void createMessagePanel() {
        setLayout(new BorderLayout());
        addMessageInput();
        addSendMessageButton();
        addMessageList();
        addMessageLinePanel();
        setSize(400, 200);
    }

    private void addMessageInput() {
        messageInputField = new JTextField(30);
        messageInputField.requestFocusInWindow();
    }

    private void addSendMessageButton() {
        sendMessageButton = new JButton("Send Message");
        sendMessageButton.addActionListener(event -> {
            sendMessage();
        });
        clientView.setDefaultButton(sendMessageButton);
    }

    private void addMessageList() {
        messagesList = new JTextArea();
        messagesList.setEditable(false);
        messagesList.setFont(new Font("Serif", Font.PLAIN, 15));
        messagesList.setLineWrap(true);

        add(new JScrollPane(messagesList), BorderLayout.CENTER);
    }

    private void addMessageLinePanel() {
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

        JPanel messageLinePanel = new JPanel();
        messageLinePanel.setBackground(Color.BLUE);
        messageLinePanel.setLayout(new GridBagLayout());

        messageLinePanel.add(messageInputField, left);
        messageLinePanel.add(sendMessageButton, right);

        add(BorderLayout.SOUTH, messageLinePanel);
    }

    private void sendMessage() {
        String inputText = messageInputField.getText();
        if (inputText.length() >= 1) {
            clientView.sendMessage(inputText);
            messageInputField.setText("");
        }
        messageInputField.requestFocusInWindow();
    }
}
