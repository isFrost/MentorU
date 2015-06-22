/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import exceptions.PersistException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vladimir Smolko
 * @param <T>
 * @param <PK>
 */
public abstract class AbstractDAO <T, PK extends Serializable> implements GenericDAO<T, PK> {
    
    Connection connection;
    
    public abstract String getSelectQuery();
    
    public abstract String getCreateQuery();
    
    public abstract String getUpdateQuery();
    
    public abstract String getDeleteQuery();
    
    protected abstract List<T> parseResultSet(ResultSet rs) throws PersistException;
    
    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws PersistException;
    
    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws PersistException;
    
    protected abstract void prepareStatementForDelete(PreparedStatement statement, T object) throws PersistException;
    
    @Override 
    public T persist(T object) throws PersistException { 
        if (object != null) { 
            try {
                throw new PersistException("Object is already persist.");
            } catch (SQLException ex) {
                Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
        T persistInstance; // Добавляем запись 
        String sql = getCreateQuery(); 
        try (PreparedStatement statement = connection.prepareStatement(sql)) { 
            prepareStatementForInsert(statement, object);         
            int count = statement.executeUpdate(); 
            if (count != 1) { 
                throw new PersistException("On persist modify more then 1 record: " + count);
            } 
        }  
        catch (Exception e) { 
            throw new PersistException(e); 
        } // Получаем только что вставленную запись 
        sql = getSelectQuery() + " where id = last_insert_id();"; 
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery(); 
            List<T> list = parseResultSet(rs); 
            if ((list == null) || (list.size() != 1)) { 
                throw new PersistException("Exception on findByPK new persist data."); 
            } 
            persistInstance = list.iterator().next(); 
        } 
        catch (Exception e) { 
            throw new PersistException(e); 
        } 
        return persistInstance;
    } 
    
    @Override
    public T getByPK (int key) throws PersistException {
        List<T> list;
        String sql = getSelectQuery();
        sql += " where id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, key);
            ResultSet rs = statement.executeQuery(); 
            list = parseResultSet(rs);
        }
        catch(Exception e){
            throw new PersistException(e);
        }
        
        if (list == null || list.isEmpty()){
            return  null;
        }
        if (list.size() > 1){
            try {
                throw new PersistException("");
            } catch (SQLException ex) {
                Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list.iterator().next();
    }    
    @Override
    public List<T> getAll() throws PersistException {
        List<T> list;
        String sql = getSelectQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) { 
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);            
        }
        catch (Exception e){
            throw new PersistException(e);
        }
        return list;
    }    
    @Override 
    public void update(T object) throws PersistException { 
        String sql = getUpdateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql);) { 
            prepareStatementForUpdate(statement, object);
            int count = statement.executeUpdate(); 
            if (count != 1) { 
                throw new PersistException("On update modify more then 1 record: " + count);
            } 
        } catch (Exception e) { 
            throw new PersistException(e);
        } 
    } 
    @Override 
    public void delete(T object) throws PersistException { 
        String sql = getDeleteQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) { 
            prepareStatementForDelete(statement, object);
            int count = statement.executeUpdate(); 
            if (count != 1) { 
                throw new PersistException("On delete modify more then 1 record: " + count); 
            } 
            statement.close(); 
        } 
        catch (Exception e) { 
            throw new PersistException(e); 
        }
    }
    
    public AbstractDAO(Connection conection){
        this.connection = connection;
    }
}
