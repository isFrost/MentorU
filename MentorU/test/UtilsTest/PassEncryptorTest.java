/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UtilsTest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.PassEncryptor;

/**
 *
 * @author isend_000
 */
public class PassEncryptorTest {
    
    public PassEncryptorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

   @Test
    public void testGeberateSalt() { 
        
        String str1 = PassEncryptor.generateSalt();
        String str2  = PassEncryptor.generateSalt();        
        assertNotSame(str1, str2);
        //fail("Сгенерированы одинаковые строки");
    }
    
    @Test
    public void testGeneratePassword(){              
        
        String salt = "u4h5";
        String pass = "somepass";
        
        String pass1 = PassEncryptor.generatePassword(pass, salt);
        String pass2 = PassEncryptor.generatePassword(pass, salt);
        
        
        assertEquals(pass1, pass2);
        //fail("Пароли не совпадают.");       
    }
}
