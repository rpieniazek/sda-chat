package com.sda.client.view;

import javax.swing.*;

/**
 * Created by RENT on 2017-08-02.
 */
public class LoginPanel extends JPanel {
    private ClientView clientView;

    public LoginPanel(ClientView clientView) {
        this.clientView = clientView;
        createLoginPanel();
    }

    private void createLoginPanel() {
        JLabel loginLabel = new JLabel("Login");
        JTextField loginField = new JTextField(30);
        JButton loginButton = new JButton("Sign in(it's free)");

        add(loginLabel);
        add(loginField);
        add(loginButton);

        loginButton.addActionListener(e -> clientView.onSignIn(loginField.getText()));

        clientView.setDefaultButton(loginButton);
    }
}
