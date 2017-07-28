package com.sda.server;

import com.sda.commons.config.ConfigService;

import static com.sda.commons.config.ConfigService.*;

/**
 * Created by RENT on 2017-07-28.
 */
public class ServerLauncher {
    public static void main(String[] args) {
        loadProperties();
        new Server();
    }
}
