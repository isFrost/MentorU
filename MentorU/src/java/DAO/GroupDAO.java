/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entities.Group;
import exceptions.PersistException;
import java.util.List;

/**
 *
 * @author isend_000
 */
public interface GroupDAO {
   public Group create() throws PersistException;
   public Group read(int key) throws PersistException;
   public void  update(Group group) throws PersistException;
   public  void delete(Group group) throws PersistException;
   
   public List<Group> getAll() throws  PersistException;
}
