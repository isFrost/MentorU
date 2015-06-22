/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import org.jboss.logging.Logger;
/**
 *
 * @author isend_000
 */
public class PassEncryptor {
    
    private  static Logger logger = Logger.getLogger("mentorLogAppender");
    
    public static  String generateSalt(){
        final Random random = new SecureRandom();
        byte[] salt = new byte[64];
        random.nextBytes(salt);
        String encodedSalt = Base64.encode(salt);
        return encodedSalt;
    }

    public static String generatePassword(String password, String salt) {
        MessageDigest digest;
        String s = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");        
            digest.reset();
            digest.update(Base64.decode(salt));
            s = Base64.encode(digest.digest(password.getBytes()));
        } 
        catch (NoSuchAlgorithmException ex) {
            logger.debug("Ошибка SQL в методе executeRegQuery");
        }
        return s;
    }
}
