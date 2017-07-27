package com.sda.commons;

/**
 * Created by RENT on 2017-07-26.
 */
public class Encrypter {

    public static final int ALPHABET_SIZE = 26;

    public static String encrypt(String plainText, int key) {
        return shiftText(plainText, key);
    }

    public static String decrypt(String encryptedText, int key){
        return shiftText(encryptedText, -key);
    }

    private static String shiftText(String text, int key) {
        char[] textAsCarArray = text.toCharArray();
        String result = "";
        for (int i = 0; i < textAsCarArray.length; i++) {
            char c = (char) (textAsCarArray[i] + key);
            result += c;
        }
        return result;
    }
}
