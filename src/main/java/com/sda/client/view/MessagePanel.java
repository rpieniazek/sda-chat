package com.sda.client.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by RENT on 2017-08-02.
 */
public class MessagePanel extends JPanel {
    private ClientView clientView;

    private JButton sendMessage;
    private JTextField messageBox; //wpisywana wiadomosc
    private JTextArea chatBox; //lista wiadomosci

    public MessagePanel(ClientView clientView) {
        this.clientView = clientView;
        createChatPanel();
    }

    public void addToMessageList(String line) {
        chatBox.append(line);
    }

    private void createChatPanel() {
        setLayout(new BorderLayout());

        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.BLUE);
        southPanel.setLayout(new GridBagLayout());

        messageBox = new JTextField(30);
        messageBox.requestFocusInWindow();

        sendMessage = new JButton("Send Message");
        sendMessage.addActionListener(event -> {
            sendMessage();
        });
        clientView.setDefaultButton(sendMessage);


        chatBox = new JTextArea();
        chatBox.setEditable(false);
        chatBox.setFont(new Font("Serif", Font.PLAIN, 15));
        chatBox.setLineWrap(true);

        add(new JScrollPane(chatBox), BorderLayout.CENTER);

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

        add(BorderLayout.SOUTH, southPanel);
        setSize(400, 200);
    }

    private void sendMessage() {
        String inputText = messageBox.getText();
        if (inputText.length() >= 1) {
            clientView.sendMessage(inputText);
            messageBox.setText("");
        }
        messageBox.requestFocusInWindow();
    }
}
