/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entities.S_User;
import exceptions.PersistException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author isend_000
 */
public class MySqlS_UserDAO extends AbstractDAO<S_User, Integer> implements S_UserDAO{
    private class PersistS_User extends S_User {         
       @Override
       public void setU_Id(int id){
            super.setU_Id(id); 
       }
    }    
   
   @Override 
   public String getSelectQuery() { 
       return "SELECT U_Id, U_Name, U_Pass FROM vclassroom.S_Users";
   }   
   
   @Override public String getCreateQuery() {
       return "INSERT INTO vclassroom.S_Users (U_Name, U_Pass) \n" + "VALUES (?, ?);"; 
   }
   
   @Override public String getUpdateQuery() { 
       return "UPDATE vclassroom.S_Users SET U_Name = ? WHERE id = ?;"; 
   }
   
   @Override public String getDeleteQuery() { 
       return "DELETE FROM vclassroom.U_Name WHERE id = ?;"; 
   } 
   
   @Override 
   public S_User create() throws PersistException { 
       S_User u = new S_User(); 
       return persist(u); 
   }   
   
   public MySqlS_UserDAO(Connection connection) { 
       super(connection); 
   } 
          
   @Override
   public List<S_User> parseResultSet(ResultSet rs) throws PersistException{       
       LinkedList<S_User> list = new LinkedList<>();
       try{
           while (rs.next()) { 
               PersistS_User user = new PersistS_User(); 
               user.setU_Id(rs.getInt("U_Id")); 
               user.setU_Name(rs.getString("U_Name")); 
               user.setU_Pass(rs.getString("U_Pass"));
               
               list.add(user); 
           } 
       }
       catch (SQLException e){
           
       }
       return list;
   }   
   @Override 
   protected void prepareStatementForInsert(PreparedStatement statement, S_User object) throws PersistException { 
       try {            
           statement.setString(2, object.getU_Name()); 
           statement.setString(3, object.getU_Pass());
       } 
       catch (Exception e) { 
           try { 
               throw new PersistException(e.toString());
           } catch (SQLException ex) {
               Logger.getLogger(MySqlGroupDAO.class.getName()).log(Level.SEVERE, null, ex);
           }
       } 
   } 
   @Override 
   protected void prepareStatementForUpdate(PreparedStatement statement, S_User object) throws PersistException { 
       try {            
           statement.setString(2, object.getU_Name()); 
           statement.setString(3, object.getU_Pass()); } 
       catch (Exception e) { 
            throw new PersistException(e); 
       } 
   }
   
   @Override
   protected  void prepareStatementForDelete(PreparedStatement statement, S_User object) throws PersistException{
       
   }
   
   
   @Override
   public S_User read(int key) throws PersistException { 
       String sql = "SELECT * FROM daotalk.S_User WHERE U_Name = ? AND U_Pass = ?;"; 
       PreparedStatement stm; 
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, key); 
            ResultSet rs = stm.executeQuery(); 
            rs.next(); S_User u = new S_User(); 
            u.setU_Id(rs.getInt("U_Id")); 
            u.setU_Name(rs.getString("U_Name")); 
            u.setU_Pass(rs.getString("U_Pass")); 
            return u;
        } catch (SQLException ex) {
            Logger.getLogger(MySqlS_UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       return null;
   } 
}
