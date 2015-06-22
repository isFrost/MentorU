/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author isend_000
 */
public class Tutor extends Entity{
    
       
    private int T_id;
    private String T_Name;
    private String T_Mail;
    private String T_Kaf;
    
    public Tutor(){
        this.setType(Type.Tutor);
    }   
       
    
    //геттеры
    public int getT_id() {
        return T_id;
    }

    public String getT_Name() {
        return T_Name;
    }

    public String getT_Mail() {
        return T_Mail;
    }

    public String getT_Kaf() {
        return T_Kaf;
    }
    
    //сеттеры

    public void setT_id(int T_id) {
        this.T_id = T_id;
    }

    public void setT_Name(String T_Name) {
        this.T_Name = T_Name;
    }

    public void setT_Mail(String T_Mail) {
        this.T_Mail = T_Mail;
    }

    public void setT_Kaf(String T_Kaf) {
        this.T_Kaf = T_Kaf;
    }    
    
}
