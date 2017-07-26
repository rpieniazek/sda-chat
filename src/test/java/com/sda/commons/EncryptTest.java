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
        String testMessage = "baca";
        String encryptedText = Encrypter.encrypt(testMessage);

        assertEquals(encryptedText,testMessage);
    }
}
