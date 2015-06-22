/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import exceptions.PersistException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Vladimir Smolko
 */
public interface DaoFactory {   
    
    public Connection getConnection() throws SQLException;
    
    //public StudentDAO getStudentDao(Connection connection);
    
    //public GroupDAO getGroupDao(Connection connection);
    
    //public S_UserDAO getS_UserDao(Connection connection);
    
    public GenericDAO getDao(Connection connection, Class dtoClass) throws PersistException; 
    
    public interface DaoCreator<Context> { 
        public GenericDAO create(Context context); 
    }    
}
