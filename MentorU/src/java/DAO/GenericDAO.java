/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import exceptions.PersistException;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author isend_000
 * @param <T>
 * @param <PK>
 */
public interface GenericDAO <T, PK extends Serializable> {
    
    public T create() throws PersistException;
    
    public T persist(T object) throws PersistException;
    
    public T getByPK(int key) throws PersistException;
    
    public void update(T object) throws PersistException;
    
    public void delete(T object) throws PersistException;
    
    public List<T> getAll() throws PersistException;
    
}
