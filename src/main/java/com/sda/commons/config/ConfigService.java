package com.sda.commons.config;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by RENT on 2017-07-28.
 */
public class ConfigService {
    public static void loadProperties(){
        try {
            FileInputStream fileInputStream = new FileInputStream("application.properties");
            Properties properties = new Properties(System.getProperties());
            properties.load(fileInputStream);
            System.setProperties(properties);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public static String getString(String key){
        throw new IllegalStateException("not yet implemented");
    }

    public static int getInt(String key){
        throw new IllegalStateException("not yet implemented");
    }
}
