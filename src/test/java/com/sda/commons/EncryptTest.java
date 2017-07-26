package com.sda.commons;

import junit.framework.Assert;
import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Created by RENT on 2017-07-26.
 */
public class EncryptTest {

    public static final String EXPECTED = "edfdcb";

    @Test
    public void name() throws Exception {
        //given
        String inputMessage = "bacazy";
        //when
        String encryptedText = Encrypter.encrypt(inputMessage);

        //then

        assertEquals(EXPECTED,encryptedText);
    }

}
