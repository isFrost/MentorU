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
public class Student extends Entity{
    
    private int S_Id;
    private String S_Name;  
    private int Year;
    private String S_Mail;
    private int Gr_Id;

    public Student() {
        this.setType(Type.Student);
    }

    
    //геттеры
    public int getS_Id() {
        return S_Id;
    }

    public String getS_Name() {
        return S_Name;
    }
    
    public String getS_Mail() {
        return S_Mail;
    }

    public int getGr_Id() {
        return Gr_Id;
    }

    public int getYear() {
        return Year;
    }
    
    
    //сеттеры
    public void setS_Id(int S_Id) {
        this.S_Id = S_Id;
    }

    public void setS_Name(String S_Name) {
        this.S_Name = S_Name;
    }
    
    public void setS_Mail(String S_Mail) {
        this.S_Mail = S_Mail;
    }

    public void setGr_Id(int Gr_Id) {
        this.Gr_Id = Gr_Id;
    }

    public void setYear(int Year) {
        this.Year = Year;
    }   
    
}
