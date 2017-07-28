package com.sda.client;

import com.sda.commons.ConfigService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

/**
 * Created by RENT on 2017-07-25.
 */
public class ClientLauncher {

    public static void main(String[] args) {
        ConfigService.loadProperties();
        new ClientController();
    }
}