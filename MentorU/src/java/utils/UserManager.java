/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;


import entities.Entity;
import entities.S_User;
import entities.Student;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.HashMap;
import entities.T_User;
import entities.Tutor;
import entities.User;
import exceptions.TechnicalException;
import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import static java.lang.System.out;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

/**
 *
 * @author isend_000
 */
public class UserManager {           
    static String path = "E:" + File.separator + "lg.xml";
    private static Map<String, String> saltyMap = new HashMap<>(); 
    
    //метод регистрирует аккаунт преподователя
    public static int registerTutorUser(String name, String UserName, String pass, String department, String mail) throws SQLException{       
                     
        
        String salt = PassEncryptor.generateSalt();      
        pass = PassEncryptor.generatePassword(pass, salt);
        
        Tutor tutor = new Tutor();
        tutor.setT_Name(name);
        tutor.setT_Mail(mail);
        tutor.setT_Kaf(department);        
        
        tutor = (Tutor)EntityManager.register(tutor);     
                   
        if (tutor != null){
            T_User user = new T_User();
            user.setU_Id(tutor.getT_id());
            user.setU_Name(UserName);
            user.setU_Pass(pass);
                
            
        
        
            user = (T_User)EntityManager.register(user);
        
            saltyMap.put(String.valueOf(user.getU_Id()) , salt);
        
            try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(path)))) {
                encoder.writeObject(saltyMap);
                return user.getU_Id();
            }
            catch(FileNotFoundException ex){
                
            }
        }
        return 0;
    }
    
    //метод регистрирует аккаунт студента
    public static int registerStudentUser(String name, String UserName, String pass, int Gr_Id, String mail) throws TechnicalException{      
              
        String salt = PassEncryptor.generateSalt();      
        pass = PassEncryptor.generatePassword(pass, salt);
        
        Student student = new Student();
        student.setS_Name(name);
        student.setS_Mail(mail);
        student.setGr_Id(Gr_Id);
            
        try{            
            student = (Student)EntityManager.register(student);
        }
        catch (SQLException e)
        {
           
        }
        
        if (student != null){
            S_User user = new S_User();
            user.setU_Id(student.getS_Id());
            user.setU_Name(UserName);
            user.setU_Pass(pass);
        try{        
            if( EntityManager.findUser(user, name) != null) {
                out.print("Пользователь с таким именем уже существует"); 
                return  0;
            }        
        }
        catch (Exception e){
            
        }
        
        try{
            user = (S_User)EntityManager.register(user);
        }
        catch(SQLException e){
            
        }
        saltyMap.put(String.valueOf(user.getU_Id()) , salt);
        
            try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(path)))) {
                encoder.writeObject(saltyMap);
                return user.getU_Id();
            }
            catch(FileNotFoundException ex){
                out.println(ex.toString());
            }
        }
        return 0;
    }
    
    //метод авторизирует пользователя
    public static int loginUser(String name, String pass, Entity.Type type) throws SQLException{
        S_User user;        
        ResultSet rs;
        
        if (type == Entity.Type.S_User)
            user = new S_User();
        else 
            user = new S_User();
        
        //rs = EntityManager.findUser(user, name);
        //user.setU_Id(rs.getInt("U_Id"));
        //user.setU_Name(rs.getString("U_Name"));
        //user.setU_Pass(rs.getString("U_Pass"));
        
        
        String salt = null;
        try (XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(path)))) {
            saltyMap = (HashMap<String, String>)decoder.readObject();
            Iterator iterator = saltyMap.entrySet().iterator();
            while(iterator.hasNext())
            {
                Map.Entry pair = (Map.Entry)iterator.next();
                if (pair.getKey().equals(String.valueOf(user.getU_Id())))
                       salt = pair.getValue().toString();
            }
        }
        catch(FileNotFoundException ex){
            
        }
        
        if (PassEncryptor.generatePassword(pass, salt).equals(user.getU_Pass()))
            return user.getU_Id();
        else
            return 0;
    }  
    
    
    
   /* public static void deleteUser(String name, String pass){
        File file = new File(path);
        try {
            if (file.isFile()) {
                FileInputStream fis = new FileInputStream(path);
                ObjectInputStream ois = new ObjectInputStream(fis);
                while(fis.available() != -1){
                    User u = (User)ois.readObject();
                    users.add(u);
                }      
                for (User u : users)
                {
                    if (name.equals(u.getUserName()))
                    {
                        users.remove(u);
                        FileOutputStream fos = new FileOutputStream(path, true);
                        try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                            oos.writeObject(users);
                            oos.flush();
                        }  
                    }
                }
            }
        }
        catch (IOException | ClassNotFoundException ex){
        
        }
    }
}
*/
}