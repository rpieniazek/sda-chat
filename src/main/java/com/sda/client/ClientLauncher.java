package com.sda.client;

import static com.sda.commons.config.ConfigService.*;

/**
 * Created by RENT on 2017-07-25.
 */
public class ClientLauncher {

    public static void main(String[] args) {
        loadProperties();
        new ClientController();
    }
}