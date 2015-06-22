/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UtilsTest;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import entities.Entity;
import entities.S_User;
import entities.Tutor;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.EntityManager;
//import static utils.EntityManager.findUser;

/**
 *
 * @author isend_000
 */
public class EntityManagerTest {
    
    

   @Test
    public  void testFind() throws SQLException{
        boolean flag = false;
        boolean expFlag = true;
       Entity entity = (Tutor)EntityManager.find("Tutor", "Department", "Прикладной математики");
       if (entity != null)
            flag = true;
        assertEquals(expFlag, flag);
    }
    
    @Test
    public void testFindByInt() throws SQLException{        
       Entity entity = (Tutor)EntityManager.findByInt("Tutor", "T_ID", 1);       
       assertNotNull(entity);
    }
    
    @Test
    public void testRegister() throws SQLException{
        
        Tutor t = new Tutor();
        t.setT_id(999);
        t.setT_Name("Sam Siera");
        t.setT_Mail("test@test.com");
        t.setT_Kaf("Test");
        
        Tutor t2 = (Tutor)EntityManager.register(t);
        assertNotNull(t2);
    }
    
    @Test
    public void testFindUser() throws SQLException{
        
        S_User u = new S_User();
        u.setU_Id(999);
        u.setU_Name("Test Student");
                
        S_User u2 = (S_User)findUser(u, u.getU_Name());
        assertNotNull(u2);
    }
      
    @Test
    public void testGetUser() throws SQLException{
        S_User u3 = EntityManager.getUser("ivan", "123");
        assertNotNull(u3);
    }
}
