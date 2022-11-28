/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kerberos;

import java.security.NoSuchAlgorithmException;
import javax.crypto.SecretKey;

/**
 *
 * @author jorge
 */
public class KeyManager {

    public KeyManager() {
    }

    public SecretKey generateSecretKey() throws NoSuchAlgorithmException {

        SecretKey secretkey;
        int KEY_SIZE = 128;
        
        javax.crypto.KeyGenerator keyGenerator = javax.crypto.KeyGenerator.getInstance("AES");
        keyGenerator.init(KEY_SIZE);
        secretkey = keyGenerator.generateKey();

        return secretkey;
    }
}
