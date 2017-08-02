package com.sda.client;

import com.sda.commons.model.MessageDto;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

/**
 * Created by RENT on 2017-07-24.
 */
public class ClientView implements IncomingEventsHandler {
    private JFrame mainFrame = new JFrame("SDA Chat");
    private JButton sendMessage;
    private JTextField messageBox; //wpisywana wiadomosc
    private JTextArea chatBox; //lista wiadomosci
    private JPanel loginPanel;

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
        chatBox.append(messageDto.toString());
    }

    @Override
    public void refreshUsers(Set<String> usernames) {
        String[] usernamesAsArray = usernames.toArray(new String[usernames.size()]);
        usersList.setListData(usernamesAsArray);
        usersList.repaint();
    }

    private void display() {
        configMainPanel();
        loginPanel = createLoginPanel();
        mainFrame.add(loginPanel);
    }

    private JPanel createLoginPanel() {

        JPanel loginPanel = new JPanel();
        JLabel loginLabel = new JLabel("Login");
        JTextField loginField = new JTextField(30);
        JButton loginButton = new JButton("Sign in(it's free)");

        loginPanel.add(loginLabel);
        loginPanel.add(loginField);
        loginPanel.add(loginButton);

        loginButton.addActionListener(e -> onSignIn(loginField.getText()));
        return loginPanel;
    }

    private void onSignIn(String loginName) {
        loginPanel.setVisible(false);
        JPanel chatPanel = createChatPanel();
        usersList = createListPanel();
        mainFrame.setLayout(new BorderLayout());
        mainFrame.add(usersList, BorderLayout.LINE_START);
        mainFrame.add(chatPanel, BorderLayout.LINE_END);

        new Thread(() -> loginCommand.connectUser(loginName)).start();
    }

    private JList createListPanel() {
        DefaultListModel<String> model = new DefaultListModel<>();
        JList<String> userListPanel = new JList<String>(model);
        userListPanel.setSize(200, 300);
        userListPanel.setFixedCellWidth(222);
        return userListPanel;
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
        mainFrame.getRootPane().setDefaultButton(sendMessage);


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
        mainPanel.setSize(400, 200);
        return mainPanel;
    }

    private void configMainPanel() {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(600, 300);
        mainFrame.setVisible(true);
    }

    private void sendMessage() {
        String inputText = messageBox.getText();
        if (inputText.length() >= 1) {
            messageCommand.sendMessage(inputText);
            messageBox.setText("");
        }
        messageBox.requestFocusInWindow();
    }
}