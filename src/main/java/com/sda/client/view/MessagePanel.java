package com.sda.client.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by RENT on 2017-08-02.
 */
public class MessagePanel extends JPanel {
    private ClientView clientView;

    private JButton sendMessageButton;
    private JTextField messageInputField;
    private JTextArea messagesList;

    public MessagePanel(ClientView clientView) {
        this.clientView = clientView;
        createMessagePanel();
    }

    public void addToMessageList(String line) {
        messagesList.append(line);
    }

    public void addPrivateMessageToList(String line) {
        messagesList.append(String.format("PRIV*%s", line));
    }

    private void createMessagePanel() {
        setLayout(new BorderLayout());
        setSize(400, 200);
        addMessageList();
        addMessageLinePanel();
    }

    private void addMessageList() {
        messagesList = new JTextArea();
        messagesList.setEditable(false);
        messagesList.setFont(new Font("Serif", Font.PLAIN, 15));
        messagesList.setLineWrap(true);

        add(new JScrollPane(messagesList), BorderLayout.CENTER);
    }

    private void addMessageLinePanel() {
        JPanel messageLinePanel = new JPanel();
        createMessageInput();
        createSendMessageButton();
        messageLinePanel.setBackground(Color.BLUE);
        messageLinePanel.setLayout(new GridBagLayout());
        messageLinePanel.add(messageInputField, createLeftConstraint());
        messageLinePanel.add(sendMessageButton, createRightConstraint());
        add(BorderLayout.SOUTH, messageLinePanel);
    }

    private void createMessageInput() {
        messageInputField = new JTextField(30);
        messageInputField.requestFocusInWindow();
    }

    private void createSendMessageButton() {
        sendMessageButton = new JButton("Send Message");
        sendMessageButton.addActionListener(event -> {
            sendMessage();
        });
        clientView.setDefaultButton(sendMessageButton);
    }

    private GridBagConstraints createRightConstraint() {
        GridBagConstraints right = new GridBagConstraints();
        right.insets = new Insets(0, 10, 0, 0);
        right.anchor = GridBagConstraints.LINE_END;
        right.fill = GridBagConstraints.NONE;
        right.weightx = 1.0D;
        right.weighty = 1.0D;
        return right;
    }

    private GridBagConstraints createLeftConstraint() {
        GridBagConstraints left = new GridBagConstraints();
        left.anchor = GridBagConstraints.LINE_START;
        left.fill = GridBagConstraints.HORIZONTAL;
        left.weightx = 512.0D;
        left.weighty = 1.0D;
        return left;
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
