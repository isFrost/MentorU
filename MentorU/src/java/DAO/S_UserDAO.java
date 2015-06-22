/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entities.S_User;
import exceptions.PersistException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author isend_000
 */
public interface S_UserDAO {
  
    public S_User create() throws  PersistException;
    public S_User read(int key) throws  PersistException;
    public void  update(S_User student) throws  PersistException;
    public  void delete(S_User student) throws  PersistException;
   
    public List<S_User> getAll() throws  PersistException;
}
