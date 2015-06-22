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
public class Doc extends Entity{
    int Doc_Id;
    int Subj_Id;
    String Doc_Name;
    String Doc_Path;

    public Doc() {
        this.setType(Entity.Type.Doc);
    }

    public int getDoc_Id() {
        return Doc_Id;
    }

    public int getSubj_Id() {
        return Subj_Id;
    }

    public String getDoc_Name() {
        return Doc_Name;
    }

    public String getDoc_Path() {
        return Doc_Path;
    }

    public void setDoc_Id(int Doc_Id) {
        this.Doc_Id = Doc_Id;
    }

    public void setSubj_Id(int Subj_Id) {
        this.Subj_Id = Subj_Id;
    }

    public void setDoc_Name(String Doc_Name) {
        this.Doc_Name = Doc_Name;
    }

    public void setDoc_Path(String Doc_Path) {
        this.Doc_Path = Doc_Path;
    }
    
    
}
