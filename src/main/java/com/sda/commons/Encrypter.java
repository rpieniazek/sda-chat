package com.sda.commons;

/**
 * Created by RENT on 2017-07-26.
 */
public class Encrypter {

    public static final int ALPHABET_SIZE = 26;

    public static String encrypt(String plainText) {

        char[] text = plainText.toCharArray();
        String result = "";
        for (int i = 0; i < text.length; i++) {
            char c = (char) (text[i] + 3);

            if (c > 'z') {
                c -= ALPHABET_SIZE;
            }

            result += c;
        }

        return result;
    }


}
