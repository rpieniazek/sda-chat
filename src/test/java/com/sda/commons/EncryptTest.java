package com.sda.commons;

import junit.framework.Assert;
import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Created by RENT on 2017-07-26.
 */
public class EncryptTest {

    @Test
    public void shouldEncryptAndDecryptForAllKeys() throws Exception {
        for(int i = 1;i< 127;i++){
            assertEncryptedAndDecryptedForKey(i);
        }
    }

    private void assertEncryptedAndDecryptedForKey(int key) {
        //given
        String inputMessage = "Hej! !@#123 :-)\n";
        //when
        String encryptedText = Encrypter.encrypt(inputMessage, key);
        String decryptedText = Encrypter.decrypt(encryptedText, key);

        //then
        assertNotSame(encryptedText,decryptedText);
        assertEquals(decryptedText,inputMessage);
    }
}
