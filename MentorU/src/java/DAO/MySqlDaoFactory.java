/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entities.Group;
import entities.Student;
import exceptions.PersistException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vladimir Smolko
 */
public class MySqlDaoFactory implements DaoFactory{
    
    private String user = "root";//Логин пользователя 
    private String password = "adelaida";//Пароль пользователя 
    private String url = "jdbc:mysql://localhost:3306/vclassroom";//URL адрес private 
    private String driver = "com.mysql.jdbc.Driver";//Имя драйвера 
    private Map<Class, DaoCreator> creators;
     
    
    @Override
    public Connection getConnection() throws SQLException { 
        return DriverManager.getConnection(url, user, password); 
    } 
    
    public Connection getContext() throws PersistException { 
        Connection connection = null; 
        try { 
            connection = DriverManager.getConnection(url, user, password); 
        } 
        catch (SQLException e) { 
            throw new PersistException(e); 
        } 
        return connection; 
    } 
    
    @Override 
    public GenericDAO getDao(Connection connection, Class dtoClass) throws PersistException { 
        DaoCreator creator = creators.get(dtoClass); 
        if (creator == null) { 
            try { 
                throw new PersistException("Dao object for " + dtoClass + " not found.");
            } catch (SQLException ex) {
                Logger.getLogger(MySqlDaoFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        return creator.create(connection); 
    } 
    
                
    public MySqlDaoFactory() { 
        try { 
            Class.forName(driver);//Регистрируем драйвер 
        } 
        catch (ClassNotFoundException e) 
        { 
            e.printStackTrace(); 
        } 
        creators = new HashMap<Class, DaoCreator>(); 
        creators.put(Group.class, new DaoCreator<Connection>() { 
            @Override public GenericDAO create(Connection connection) { 
                return new MySqlGroupDAO(connection); 
            } 
        }); 
        creators.put(Student.class, new DaoCreator<Connection>() { 
            @Override public GenericDAO create(Connection connection) { 
                return null; } 
        }); 
        
    } 
}