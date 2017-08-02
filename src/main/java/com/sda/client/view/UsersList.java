package com.sda.client.view;

import javax.swing.*;

/**
 * Created by RENT on 2017-08-02.
 */
public class UsersList extends JList <String>{

    public UsersList(){
        createListPanel();
    }

    private void createListPanel() {
        DefaultListModel<String> model = new DefaultListModel<>();
        setModel(model);
        setSize(200, 300);
        setFixedCellWidth(222);
    }
}
