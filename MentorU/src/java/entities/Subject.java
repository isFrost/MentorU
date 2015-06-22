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
public class Subject extends  Entity{
    private int Subj_Id;
    private  String Subj_Name;

    public Subject() {
        this.setType(Type.Subject);
    }    
    
    public String getSubj_Name() {
        return Subj_Name;
    }

    public int getSubj_Id() {
        return Subj_Id;
    }
    
    public void setSubj_Name(String Subj_Name) {
        this.Subj_Name = Subj_Name;
    }        

    public void setSubj_Id(int Subj_Id) {
        this.Subj_Id = Subj_Id;
    }    
}
