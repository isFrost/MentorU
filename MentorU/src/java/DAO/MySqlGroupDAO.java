/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entities.Group;
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
 * @author Vladimir Smolko
 */
class MySqlGroupDAO extends AbstractDAO<Group, Integer> implements GroupDAO{
    
   private class PersistGroup extends Group { 
       @Override
       public void setGr_Id(int id){
            super.setGr_Id(id); 
       }
    }    
   
   @Override public String getSelectQuery() { 
       return "SELECT id, number, department FROM vclassroom.Group";
   }   
   
   @Override public String getCreateQuery() {
       return "INSERT INTO vclassroom.Groups (Gr_Name) \n" + "VALUES (?);"; 
   }
   
   @Override public String getUpdateQuery() { 
       return "UPDATE vclassroom.Groups SET name = ? WHERE id = ?;"; 
   }
   
   @Override public String getDeleteQuery() { 
       return "DELETE FROM vclassroom.Groups WHERE id = ?;"; 
   } 
   
   @Override public Group create() throws PersistException { 
       Group g = new Group(); 
       return persist(g); 
   }   
   
   public MySqlGroupDAO(Connection connection) { 
       super(connection); 
   } 
          
   @Override
   public List<Group> parseResultSet(ResultSet rs) throws PersistException{       
       LinkedList<Group> list = new LinkedList<>();
       try{
           while (rs.next()) { 
               PersistGroup group = new PersistGroup(); 
               group.setGr_Id(rs.getInt("Gr_Id")); 
               group.setGr_Name(rs.getString("Gr_Name")); 
               list.add(group); 
           } 
       }
       catch (SQLException e){
           
       }
       return list;
   }   
   @Override 
   protected void prepareStatementForInsert(PreparedStatement statement, Group object) throws PersistException { 
       try {            
           statement.setString(2, object.getGr_Name()); 
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
   protected void prepareStatementForUpdate(PreparedStatement statement, Group object) throws PersistException { 
       try {            
           statement.setString(2, object.getGr_Name()); 
           statement.setInt(3, object.getGr_Id()); } 
       catch (Exception e) { 
            throw new PersistException(e); 
       } 
   }
   
   @Override
   protected  void prepareStatementForDelete(PreparedStatement statement, Group object) throws PersistException{
       
   }
   
   
   @Override
   public Group read(int key) throws PersistException { 
       return  null;
   } 
   
}
   

