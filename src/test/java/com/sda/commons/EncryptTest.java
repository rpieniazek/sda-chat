package com.sda.commons;

import junit.framework.Assert;
import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Created by RENT on 2017-07-26.
 */
public class EncryptTest {

    @Test
    public void shouldEncrypt() throws Exception {
        //given
        String inputMessage = "Hej! !@#123 :-)\n";
        //when
        String encryptedText = Encrypter.encrypt(inputMessage, 3);
        String decryptedText = Encrypter.decrypt(encryptedText, 3);

        //then
        assertNotSame(encryptedText,decryptedText);
        assertEquals(decryptedText,inputMessage);
    }
}
