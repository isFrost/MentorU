/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UtilsTest;

import entities.Entity;
import entities.T_User;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.UserManager;

/**
 *
 * @author isend_000
 */
public class UserManagerTest {
    
    public UserManagerTest() {
    }
    @Test
    public void testRegisterT_User() throws SQLException{
       
        int id = UserManager.registerTutorUser("Test","Test","Test","Test","Test");
        if (id == 0){
            fail("метод возвращает 0");
        }
    }
    
    @Test
    public void testRegisterS_User() throws SQLException{
       
        int id = UserManager.registerStudentUser("Test","Test","Test", 999,"Test");
        if (id == 0){
            fail("метод возвращает 0");
        }
    }
    @Test
    public void testLoginUser() throws SQLException{
        int id = UserManager.loginUser("Test", "Test", Entity.Type.S_User);
        if (id == 0){
            fail("метод возвращает 0");
        }
    }
}

