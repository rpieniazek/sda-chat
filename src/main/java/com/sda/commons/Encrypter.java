package com.sda.commons;

/**
 * Created by RENT on 2017-07-26.
 */
public class Encrypter {

    public static final int ALPHABET_SIZE = 26;

    public static String encrypt(String plainText, int key) {

        char[] text = plainText.toCharArray();
        String result = "";
        for (int i = 0; i < text.length; i++) {
            char c = (char) (text[i] + key);

            if (isOutOfRange(c)) {
                c -= ALPHABET_SIZE;
            }
            result += c;
        }
        return result;
    }

    public static String decrypt(String encryptedText, int key){
        return encrypt(encryptedText, -key);
    }

    private static boolean isOutOfRange(char c) {
        return c > 'z' || (c > 'Z' && c < 'a');
    }
}
