/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author isend_000
 */
 
import java.sql.Connection;
 
public class DBConnectionManager {
 
    private String url;
    private String user;
    private String password;
    private Connection con;
     
    public DBConnectionManager(String url, String u, String p){
        this.url = url;
        this.user = u;
        this.password = p;       
    }
     
    public Connection getConnection(){
        return this.con;
    }
     
    public void closeConnection(){
        
    }
}
