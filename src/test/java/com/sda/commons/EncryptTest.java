package com.sda.commons;

import junit.framework.Assert;
import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Created by RENT on 2017-07-26.
 */
public class EncryptTest {

    @Test
    public void name() throws Exception {
        String inputMessage = "baca";
        String expectedMessage = "edfd";
        String encryptedText = Encrypter.encrypt(inputMessage);

        assertEquals(encryptedText,expectedMessage);
    }
}
