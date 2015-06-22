/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entities.Student;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author isend_000
 */
public interface StudentDAO {
    
   public Student create();
   public Student read(int key);
   public void  update(Student student);
   public  void delete(Student student);
   
   public List<Student> getAllStudents() throws  SQLException;
   
}
