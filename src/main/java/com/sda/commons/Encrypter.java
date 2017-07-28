package com.sda.commons;

/**
 * Created by RENT on 2017-07-26.
 */
public class Encrypter {

    private static int DEFAULT_KEY = 3;

    public static String encrypt(String plainText){
        return encrypt(plainText, DEFAULT_KEY);
    }

    public static String decrypt(String encryptedText){
        return decrypt(encryptedText, DEFAULT_KEY);
    }

    public static String encrypt(String plainText, int key) {
        return shiftText(plainText, key);
    }

    public static String decrypt(String encryptedText, int key){
        return shiftText(encryptedText, -key);
    }

    private static String shiftText(String text, int key) {
        char[] textAsCarArray = text.toCharArray();
        String result = "";
        for (char sign : textAsCarArray) {
            result += (char) (sign + key);
        }
        return result;
    }
}
