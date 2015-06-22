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
public class Group extends Entity{
    private  int Gr_Id;
    private String Gr_Name;    

    public Group(){
        this.setType(Type.Group);
    }    
    public String getGr_Name() {
        return Gr_Name;
    }

    public void setGr_Name(String Gr_Name) {
        this.Gr_Name = Gr_Name;
    }

    public int getGr_Id() {
        return Gr_Id;
    }

    public void setGr_Id(int Gr_Id) {
        this.Gr_Id = Gr_Id;
    }
    
    
}
